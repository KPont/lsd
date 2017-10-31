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
public class User {

    int userId;
    String userName;
    String userPw;
    String email;

    public User(int userId, String userName, String userPw) {
        this.userId = userId;
        this.userName = userName;
        this.userPw = userPw;
    }

    public User(String userName, String userPw, String email) {
        this.userName = userName;
        this.userPw = userPw;
        this.email = email;
    }
    
    public User(int userId, String userName, String userPw, String email) {
        this.userId = userId;
        this.userName = userName;
        this.userPw = userPw;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", userPw=" + userPw + ", email=" + email + '}';
    }

}
