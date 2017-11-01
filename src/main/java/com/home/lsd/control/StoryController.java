package com.home.lsd.control;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.home.lsd.boundary.Facade;
import com.home.lsd.entity.Story;
import com.home.lsd.entity.SystemStatus;

@Stateless
public class StoryController {

	private final Facade facade = new Facade();

	public ResponseBuilder createPost(JsonObject input) {
		String storyTitle = input.getString("post_title");
		String storyLink = input.getString("post_url");
		String storyType = input.getString("post_type");
		String userName = input.getString("username");
		String userPw = input.getString("pwd_hash");
		String comment = input.getString("post_text");

		switch (input.getString("post_type")) {

		case "story":
			facade.addStory(0, storyTitle, storyLink, storyType, userName, userPw);
			break;

		case "comment":
			int storyId = input.getInt("hanesst_id");
			if (storyExists(input)) {

				facade.addCommentToStory(storyId, userName, userPw, comment);
				break;
			}
			return Response.status(Status.BAD_REQUEST).entity(input);

		case "poll":
			break;

		case "pollopt":
			break;
		}

		return Response.status(Status.CREATED).entity(input);
	}

	private boolean storyExists(JsonObject input) {
		int id = input.getInt("hanesst_id");
		return facade.getStory(id) != null ? true : false;
	}

	public ResponseBuilder getLatest() {
		try {
			Story latest = facade.getLatestStory();
			return Response.status(Status.OK).entity(latest);
		} catch (Exception e) {
			JsonObjectBuilder json = Json.createObjectBuilder();
			json.add("error", "Kunne ikke finde seneste story");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(json.build());
		}
	}

	public ResponseBuilder getStatus() {
		return Response.status(Status.OK).entity(SystemStatus.ALIVE);
	}

}
