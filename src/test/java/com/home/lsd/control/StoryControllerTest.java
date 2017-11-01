package com.home.lsd.control;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

class StoryControllerTest {

	StoryController controller = new StoryController();

	@Test
	void test() {
		Response res = controller.getAllStories().build();

		System.out.println(res.getEntity());
	}

}
