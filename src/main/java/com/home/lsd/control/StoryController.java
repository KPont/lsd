package com.home.lsd.control;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.home.lsd.boundary.Facade;
import com.home.lsd.entity.SystemStatus;

@Stateless
public class StoryController {

	Facade facade = new Facade();

	public ResponseBuilder createPost(JsonObject input) {
		int storyId = input.getInt("hanesst_id");
		String storyTitle = input.getString("post_title");
		String storyLink = input.getString("");
		String storyType = input.getString("post_type");
		String userName = input.getString("");
		facade.addStory(storyId, storyTitle, storyLink, storyType, userName);
		return Response.status(Status.OK).entity(input);
	}

	public ResponseBuilder getLatest() {
		int hanesst_id = 42;
		return Response.status(Status.OK).entity(hanesst_id);
	}

	public ResponseBuilder getStatus() {
		return Response.status(Status.OK).entity(SystemStatus.ALIVE);
	}

}
