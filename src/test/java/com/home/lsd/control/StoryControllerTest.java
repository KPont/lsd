package com.home.lsd.control;

import javax.ws.rs.core.Response;

import org.junit.Ignore;
import org.junit.Test;

public class StoryControllerTest {

	
	private StoryController controller = new StoryController();

	@Test
	public void testGetAllStories() {
		Response res = controller.getAllStories().build();

		System.out.println(res.getEntity());
	}

	
	@Test
	public void testGet() {
		Response res = controller.getLatest().build();
		System.out.println(res.getEntity());
	}
}
