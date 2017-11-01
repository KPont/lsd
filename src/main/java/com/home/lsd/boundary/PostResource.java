package com.home.lsd.boundary;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.home.lsd.control.StoryController;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

	private final StoryController controller = new StoryController();

	@POST
	@Path("post")
	public Response post(JsonObject input) {
		return controller.createPost(input).build();
	}

	@GET
	@Path("latest")
	public Response getLatest() {
		return controller.getLatest().build();
	}

	@GET
	@Path("status")
	public Response getStatus() {
		return controller.getStatus().build();
	}

}
