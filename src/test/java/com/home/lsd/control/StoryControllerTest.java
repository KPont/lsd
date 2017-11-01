package com.home.lsd.control;

import javax.ws.rs.core.Response;

import org.junit.Ignore;
import org.junit.Test;

public class StoryControllerTest {

	private StoryController controller = new StoryController();

	@Test
	@Ignore
	public void test() {
		Response res = controller.getAllStories().build();

		System.out.println(res.getEntity());
	}

}
