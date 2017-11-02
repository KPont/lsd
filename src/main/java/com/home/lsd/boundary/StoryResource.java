package com.home.lsd.boundary;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.home.lsd.control.StoryController;

@Path("api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoryResource {

	private final StoryController controller = new StoryController();

	@GET
	@Path("stories")
	public Response getAllStories() {
		return controller.getAllStories().build();
	}

	@GET
	@Path("test")
	public Response getTesting() {
		return Response.status(Status.ACCEPTED).build();
	}

}
