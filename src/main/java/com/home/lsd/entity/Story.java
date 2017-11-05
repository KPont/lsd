/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.lsd.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kasper
 */
public class Story {

	int id;
	String title;
	String link;
	String type;
	String user;
	String userPw;
	List<Comment> comments;

	public Story(int id, String title, String link, String type, String user, List<Comment> comments) {
		this.id = id;
		this.title = title;
		this.link = link;
		this.type = type;
		this.user = user;
		this.comments = comments;
	}

	public Story(String title, String link, String type, String user, String userPw, List<Comment> comments) {
		this.title = title;
		this.link = link;
		this.type = type;
		this.user = user;
		this.userPw = userPw;
		this.comments = comments;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
