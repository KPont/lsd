package com.home.lsd.control;

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class StoryControllerTest {

	private static StoryController controller;

	@BeforeClass
	public static void init() {
		controller = new StoryController();
	}

	@Test
	@Ignore
	public void testGetAllStories() {
		Response res = controller.getAllStories().build();
		System.out.println(res.getEntity());
	}

	@Test
	public void testGetLatest() {
		Response res = controller.getLatest().build();
		System.out.println(res.getEntity());
	}
}
