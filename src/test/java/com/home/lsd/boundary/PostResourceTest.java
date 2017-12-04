package com.home.lsd.boundary;

import java.time.Duration;
import java.time.Instant;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.junit.Ignore;
import org.junit.Test;

public class PostResourceTest {

	PostResource res = new PostResource();

	@Test
	@Ignore
	public void testPost() {
		res.init();

		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("post_title", "tester");
		builder.add("post_url", "tester.dk");
		builder.add("post_type", "story");
		builder.add("username", "perler");
		builder.add("pwd_hash", "jRPTZerJBK");
		builder.add("post_text", "tester text");
		JsonObject input = builder.build();
		
		long timeavg = 0;
		
		for (int i = 0; i < 10; i++) {

			Instant start = Instant.now();
			res.post(input);
			Instant end = Instant.now();

			long timespend = Duration.between(start, end).toMillis();
			timeavg += timespend;
		}

		System.out.println("Time spend avg: " + timeavg / 10);
		/**
		 * RESULT = 901 milliseconds
		 */

	}

	@Test
	@Ignore
	public void testPostUpdated() {
		res.init();

		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("post_title", "tester");
		builder.add("post_url", "tester.dk");
		builder.add("post_type", "story");
		builder.add("username", "perler");
		builder.add("pwd_hash", "jRPTZerJBK");
		builder.add("post_text", "tester text");
		JsonObject input = builder.build();
		
		long timeavg = 0;
		
		for (int i = 0; i < 10; i++) {

			Instant start = Instant.now();
			res.postPerformance(input);
			Instant end = Instant.now();

			long timespend = Duration.between(start, end).toMillis();
			timeavg += timespend;
		}

		System.out.println("Time spend avg: " + timeavg / 10);
		/**
		 * RESULT = 451 milliseconds
		 */

	}

}
