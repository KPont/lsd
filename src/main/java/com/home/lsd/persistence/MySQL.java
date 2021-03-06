/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.lsd.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.JsonObject;

import com.home.lsd.entity.Comment;
import com.home.lsd.entity.Story;
import com.home.lsd.entity.User;

/**
 *
 * @author Kasper
 */
public class MySQL {

	private String url;
	private String username;
	private String password;
	private String driver;

	public MySQL(String driver, String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.driver = driver;
	}

	public Connection getConnection() throws Exception {
		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}

	public ArrayList<User> getUsers() throws Exception {
		ArrayList<User> users = new ArrayList();

		try (Connection conn = getConnection()) {
			final String command = "SELECT * FROM Users";

			PreparedStatement ps = conn.prepareStatement(command);
			// ps.setString(1, "Users");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int userid = rs.getInt("Users.user_id");
				String userName = rs.getString("Users.user_name");
				String userPw = rs.getString("Users.user_pwd");

				users.add(new User(userid, userName, userPw));
			}

		}

		return users;
	}

	public void insertUsersToDB(BufferedReader br) throws Exception {
		ArrayList<User> users = new ArrayList();

		String strLine;
		int count = 0;
		while ((strLine = br.readLine()) != null) {
			User user = new User(count, strLine.split(",")[0], strLine.split(",")[1]);
			users.add(user);
			count++;
		}
		try {
			br.close();
		} catch (IOException ex) {
			Logger.getLogger(Format.class.getName()).log(Level.SEVERE, null, ex);
		}
		PreparedStatement ps = null;
		try (Connection conn = getConnection()) {
			for (int i = 0; i < users.size(); i++) {
				final String command = "INSERT INTO Users(user_id, user_name, user_pwd) VALUES(" + "?," + "?," + "?)";
				ps = conn.prepareStatement(command);
				ps.setInt(1, users.get(i).getUserId());
				ps.setString(2, users.get(i).getUserName());
				ps.setString(3, users.get(i).getUserPw());

				if (ps != null) {
					// ResultSet rs = ps.executeQuery();
					ps.executeUpdate();

					// while (rs.next()) {
					// result.add(rs.getString("Users.user_pw"));
					// }
				}
			}

		}
	}

	public List<Story> getStories() throws Exception {
		List<Story> stories = new ArrayList();
		List<Comment> storyComments = new ArrayList();

		try (Connection conn = getConnection()) {
			final String command = "SELECT * FROM Stories limit 5";

			PreparedStatement ps = conn.prepareStatement(command);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int storyid = rs.getInt("Stories.story_id");
				String storyTitle = rs.getString("Stories.story_title");
				String storyLink = rs.getString("Stories.story_link");
				String storyType = rs.getString("Stories.story_type");
				String storyUser = rs.getString("Stories.user_id");

				final String command2 = "SELECT * FROM Comments WHERE Comments.story_id = " + storyid;

				PreparedStatement ps2 = conn.prepareStatement(command2);
				ResultSet rs2 = ps2.executeQuery();

				while (rs2.next()) {
					int commentId = rs2.getInt("Comments.comment_id");
					String commentContent = rs2.getString("Comments.content");
					String commentUser = rs2.getString("Comments.user_id");
					storyComments.add(new Comment(commentId, commentContent, commentUser));
				}
				Story story = new Story(storyid, storyTitle, storyLink, storyType, storyUser, storyComments);
				stories.add(story);
			}
		}

		return stories;

	}

	public String addStory(Story story) throws Exception {
		String result = "Invalid user credentials";
		String login = getLogin(story.getUser(), story.getUserPw());

		if (login.equals("Login success")) {
			PreparedStatement ps = null;

			try (Connection conn = getConnection()) {

				int user_id = 0;

				final String command2 = "SELECT user_id FROM Users WHERE Users.user_name = '" + story.getUser() + "';";
				PreparedStatement ps2 = conn.prepareStatement(command2);
				ResultSet rs2 = ps2.executeQuery();

				while (rs2.next()) {
					user_id = rs2.getInt("Users.user_id");
				}

				final String command = "INSERT INTO Stories(story_id, story_title, story_link, story_type, user_id) VALUES("
						+ "NULL," + "?," + "?," + "?," + "?)";
				ps = conn.prepareStatement(command);
				ps.setString(1, story.getTitle());
				ps.setString(2, story.getLink());
				ps.setString(3, story.getType());
				ps.setInt(4, user_id);

				if (ps != null) {
					ps.executeUpdate();
					result = "Successfully added story";
				}

			}
		}
		return result;
	}

	public Story getLatestStory() throws Exception {

		Story story = null;
		PreparedStatement ps = null;

		try (Connection conn = getConnection()) {

			int count = 0;
			final String command3 = "SELECT count(*) AS rowcount FROM Stories;";

			PreparedStatement ps3 = conn.prepareStatement(command3);
			ResultSet rs3 = ps3.executeQuery();

			while (rs3.next()) {
				count = rs3.getInt("rowcount");
			}
			final String command = "SELECT * FROM Stories WHERE Stories.story_id = " + count;
			ps = conn.prepareStatement(command);
			if (ps != null) {
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					int storyId = rs.getInt("Stories.story_id");
					String storyTitle = rs.getString("Stories.story_title");
					String storyLink = rs.getString("Stories.story_link");
					String storyType = rs.getString("Stories.story_type");
					int userId = rs.getInt("Stories.user_id");

					final String command2 = "SELECT * FROM Comments WHERE Comments.story_id = " + storyId;

					PreparedStatement ps2 = conn.prepareStatement(command2);

					ResultSet rs2 = ps2.executeQuery();
					ArrayList<Comment> storyComments = new ArrayList();
					while (rs2.next()) {
						int commentId = rs2.getInt("Comments.comment_id");
						String commentContent = rs2.getString("Comments.content");
						String commentUser = rs2.getString("Comments.user_id");

						storyComments.add(new Comment(commentId, commentContent, commentUser));
					}

					story = new Story(storyId, storyTitle, storyLink, storyType, getUserById(userId).getUserName(),
							storyComments);
				}
			}

		}
		return story;
	}

	public String addCommentToStory(int storyId, Comment comment) throws Exception {
		String result = "Error in user credentials";
		String login = getLogin(comment.getUser(), comment.getUserPw());

		if (login.equals("Login success")) {
			PreparedStatement ps = null;

			try (Connection conn = getConnection()) {

				int count = 0;

				final String command2 = "SELECT count(*) AS rowcount FROM Comments;";

				PreparedStatement ps2 = conn.prepareStatement(command2);
				// ps.setString(1, "Users");

				ResultSet rs2 = ps2.executeQuery();

				while (rs2.next()) {
					count = rs2.getInt("rowcount");
				}
				count++;

				final String command = "INSERT INTO Comments(comment_id, content, user_id, story_id) VALUES(" + "?,"
						+ "?," + "?," + "?)";
				ps = conn.prepareStatement(command);
				ps.setInt(1, count);
				ps.setString(2, comment.getContent());
				ps.setInt(3, getUserByName(comment.getUser()).getUserId());
				ps.setInt(4, storyId);

				if (ps != null) {
					// ResultSet rs = ps.executeQuery();
					ps.executeUpdate();
					result = "Comment added successfully";
					// while (rs.next()) {
					// result.add(rs.getString("Users.user_pw"));
					// }
				}

			}
		}
		return result;
	}

	public void addUser(User user) throws Exception {

		PreparedStatement ps = null;

		try (Connection conn = getConnection()) {
			final String command = "INSERT INTO Users(user_id, user_name, user_pwd) VALUES(" + "NULL," + "?," + "?,"
					+ "?)";
			ps = conn.prepareStatement(command);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPw());
			if (ps != null) {
				ps.executeUpdate();
			}

		}

	}

	public User getUserById(int id) throws Exception {
		User user = null;

		try (Connection conn = getConnection()) {

			final String command2 = "SELECT * FROM Users WHERE user_id = '" + id + "';";

			PreparedStatement ps2 = conn.prepareStatement(command2);
			// ps.setString(1, "Users");

			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				int userId = rs2.getInt("Users.user_id");
				String userName = rs2.getString("Users.user_name");
				String userPw = rs2.getString("Users.user_pwd");

				user = new User(userId, userName, userPw);
			}

		}
		return user;
	}

	public User getUserByName(String name) throws Exception {
		User user = null;

		try (Connection conn = getConnection()) {

			final String command2 = "SELECT * FROM Users WHERE user_name = '" + name + "';";

			PreparedStatement ps2 = conn.prepareStatement(command2);
			// ps.setString(1, "Users");

			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				int userId = rs2.getInt("Users.user_id");
				String userName = rs2.getString("Users.user_name");
				String userPw = rs2.getString("Users.user_pwd");

				user = new User(userId, userName, userPw);
			}

		}
		return user;
	}

	public Story getStoryById(int id) throws Exception {
		Story story = null;

		try (Connection conn = getConnection()) {

			final String command2 = "SELECT * FROM Stories WHERE story_id = '" + id + "';";

			PreparedStatement ps2 = conn.prepareStatement(command2);
			// ps.setString(1, "Users");

			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				int storyId = rs2.getInt("Stories.story_id");
				String storyTitle = rs2.getString("Stories.story_title");
				String storyLink = rs2.getString("Stories.story_link");
				String storyType = rs2.getString("Stories.story_type");
				int userId = rs2.getInt("Stories.user_id");

				String userName = "";

				final String command = "SELECT user_name FROM Users WHERE Users.user_id= '" + userId + "';";

				PreparedStatement ps = conn.prepareStatement(command);
				// ps.setString(1, "Users");

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					userName = rs.getString("Users.user_name");
				}

				ArrayList<Comment> comments = new ArrayList();

				final String command3 = "SELECT * FROM Comments WHERE Comments.story_id= '" + storyId + "';";

				PreparedStatement ps3 = conn.prepareStatement(command3);
				// ps.setString(1, "Users");

				ResultSet rs3 = ps3.executeQuery();

				while (rs3.next()) {
					int commentId = rs3.getInt("Comments.comment_id");
					String commentContent = rs3.getString("Comments.content");
					String commentUser = rs3.getString("Comments.user_id");

					comments.add(new Comment(commentId, commentContent, commentUser));
				}

				story = new Story(storyId, storyTitle, storyLink, storyType, userName, comments);
			}

		}
		return story;
	}

	public String getLogin(String name, String password) throws Exception {
		String result = "Login error";

		try (Connection conn = getConnection()) {

			final String command2 = "SELECT * FROM Users WHERE user_name = '" + name + "';";

			PreparedStatement ps2 = conn.prepareStatement(command2);
			// ps.setString(1, "Users");

			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				String userPw = rs2.getString("Users.user_pwd");

				if (password.equals(userPw)) {
					result = "Login success";
				}
			}

		}
		return result;
	}

	public int addStoryPerformance(JsonObject input) throws Exception {
		try (Connection conn = getConnection()) {

			final String command2 = "SELECT user_id FROM Users WHERE Users.user_name = ?";
			PreparedStatement ps2 = conn.prepareStatement(command2);
			ps2.setString(1, input.getString("username"));
			ResultSet rs2 = ps2.executeQuery();
			int user_id = 0;
			while (rs2.next()) {
				user_id = rs2.getInt("Users.user_id");
			}

			final String command = "INSERT INTO Stories(story_id, story_title, story_link, story_type, user_id) VALUES("
					+ "NULL," + "?," + "?," + "?," + "?)";
			PreparedStatement ps = conn.prepareStatement(command);
			ps.setString(1, input.getString("post_title"));
			ps.setString(2, input.getString("post_url"));
			ps.setString(3, input.getString("post_type"));
			ps.setInt(4, user_id);
			return ps.executeUpdate();

		}
	}

}
