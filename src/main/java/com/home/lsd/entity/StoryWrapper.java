package com.home.lsd.entity;

import java.util.List;

import javax.ws.rs.core.GenericEntity;

public class StoryWrapper {
	private int id;
	private String title;
	private String link;
	private String type;
	private String user;
	private GenericEntity<List<Comment>> commentList;

	public StoryWrapper(int id, String title, String link, String type, String user,
			GenericEntity<List<Comment>> commentList) {
		this.id = id;
		this.title = title;
		this.link = link;
		this.type = type;
		this.user = user;
		this.commentList = commentList;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getType() {
		return type;
	}

	public String getUser() {
		return user;
	}

	public GenericEntity<List<Comment>> getCommentList() {
		return commentList;
	}

}
