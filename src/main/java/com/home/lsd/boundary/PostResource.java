package com.home.lsd.boundary;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.home.lsd.control.Metrics;
import com.home.lsd.control.StoryController;

import io.prometheus.client.Summary;

@Path("")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN })
@ApplicationScoped
public class PostResource {

	private StoryController controller;

	@PostConstruct
	public void init() {
		controller = new StoryController();
	}

	@POST
	@Path("post")
	public Response post(JsonObject input) {
		Summary.Timer timer = Metrics.requestLatency.startTimer();
		Response result = controller.createPost(input).build();
		timer.observeDuration();
		return result;
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
