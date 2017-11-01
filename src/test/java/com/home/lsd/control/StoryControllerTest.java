package com.home.lsd.control;

import javax.ws.rs.core.Response;

import org.junit.Test;

class StoryControllerTest {

	StoryController controller = new StoryController();

	@Test
	void test() {
		Response res = controller.getAllStories().build();

		System.out.println(res.getEntity());
	}

}
