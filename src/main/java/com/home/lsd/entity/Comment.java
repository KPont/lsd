/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.lsd.entity;

/**
 *
 * @author Kasper
 */
public class Comment {

    int id;
    String content;
    String user;

    public Comment(int id, String content, String user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }

    public Comment(String content, String user) {
        this.content = content;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
