package com.home.lsd.control;

import org.junit.jupiter.api.Test;

class StoryControllerTest {

	StoryController controller = new StoryController();

	@Test
	void test() {
		System.out.println(controller.getAllStories());
	}

}
