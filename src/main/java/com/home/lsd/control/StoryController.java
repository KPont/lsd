package com.home.lsd.control;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.home.lsd.boundary.Facade;
import com.home.lsd.entity.Story;

import io.prometheus.client.Summary;

public class StoryController {

	private final Facade facade = new Facade();

	private final static Logger logger = Logger.getLogger(StoryController.class);

	public ResponseBuilder createPost(JsonObject input) {
		String result = "";
		String storyTitle = input.getString("post_title");
		String storyLink = input.getString("post_url");
		String storyType = input.getString("post_type");
		String userName = input.getString("username");
		String userPw = input.getString("pwd_hash");
		String comment = input.getString("post_text");

		switch (input.getString("post_type")) {

		case "story":
			result = facade.addStory(0, storyTitle, storyLink, storyType, userName, userPw);
			break;

		case "comment":
			int storyId = input.getInt("hanesst_id");
			if (storyExists(input)) {

				result = facade.addCommentToStory(storyId, userName, userPw, comment);
				break;
			}
			return Response.status(Status.BAD_REQUEST).entity(input);

		case "poll":
			break;

		case "pollopt":
			break;
		}
		return Response.status(Status.OK).entity(result);
	}

	private boolean storyExists(JsonObject input) {
		int id = input.getInt("hanesst_id");
		return facade.getStory(id) != null ? true : false;
	}

	public ResponseBuilder getLatest() {
		try {
			Story latest = facade.getLatestStory();
			logger.trace("Latest user created:  " + latest.getUser());
			return Response.status(Status.OK).entity(latest.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			JsonObjectBuilder json = Json.createObjectBuilder();
			json.add("error", "Kunne ikke finde seneste story:" + e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(json.build());
		}
	}

	public ResponseBuilder getStatus() {
		logger.error("Status, error Test");
		return Response.status(Status.OK).entity("Alive");
	}

	public ResponseBuilder getAllStories() {
		try {
			GenericEntity<List<Story>> storyList = new GenericEntity<List<Story>>(facade.getStories()) {
			};
			return Response.status(Status.OK).entity(storyList);
		} catch (Exception e) {
			JsonObjectBuilder json = Json.createObjectBuilder();
			json.add("error", "Kunne ikke hente alle stories");
			json.add("message", e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(json.build());
		}
	}

	public ResponseBuilder createPostPerformance(JsonObject input) {
		if (input.getString("post_type").equals("story")) {
			facade.addStoryPerformance(input);
			return Response.status(Status.OK);
		} else if (input.getString("post_type").equals("comment")) {
			//return Response.status(Status.OK).entity(facade.addCommentToStory(storyId, userName, userPw, comment));
			return Response.status(Status.OK);
		} else {
			return Response.status(Status.OK);
		}
	}

}
