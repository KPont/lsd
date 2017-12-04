/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.lsd.boundary;

import java.util.List;

import javax.json.JsonObject;

import org.apache.log4j.Logger;

import com.home.lsd.entity.Comment;
import com.home.lsd.entity.Story;
import com.home.lsd.entity.User;
import com.home.lsd.persistence.MySQL;

/**
 *
 * @author Kasper
 */
public class Facade implements IBackend {

	MySQL ms = new MySQL("com.mysql.jdbc.Driver", "jdbc:mysql://46.101.111.112:3306/lsd", "admin", "password");

	private final static Logger logger = Logger.getLogger(Facade.class.getName());

	public Story getLatestStory() throws Exception {
		return ms.getLatestStory();
	}

	@Override
	public List<Story> getStories() throws Exception {
		return ms.getStories();
	}

	@Override
	public Story getStory(int id) {
		Story story = null;
		try {
			story = ms.getStoryById(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return story;
	}

	@Override
	public String addStory(int storyId, String storyTitle, String storyLink, String storyType, String userName,
			String userPw) {
		try {
			return ms.addStory(new Story(storyTitle, storyLink, storyType, userName, userPw, null));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}

	}

	@Override
	public void manageStory(int storyID, Story story) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String login(String userName, String password) {
		String result = "Error";
		try {
			result = ms.getLogin(userName, password);
		} catch (Exception ex) {
		}
		return result;
	}

	@Override
	public void registerUser(String userName, String password, String email) {
		User user = new User(userName, password, email);
		try {
			ms.addUser(user);
		} catch (Exception ex) {
		}
	}

	@Override
	public String addCommentToStory(int storyId, String userName, String userPw, String comment) {
		try {
			return ms.addCommentToStory(storyId, new Comment(comment, userName, userPw));
		} catch (Exception ex) {
		}
		return comment;
	}

	public Object addStoryPerformance(JsonObject input) {
		try {
			return ms.addStoryPerformance(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
