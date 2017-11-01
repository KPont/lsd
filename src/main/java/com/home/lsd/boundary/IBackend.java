/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.lsd.boundary;

import java.util.List;

import com.home.lsd.entity.Story;

/**
 *
 * @author Kasper
 */
public interface IBackend {
    List<Story> getStories();
    
    Story getStory(int id);
    
    void addStory(int storyId, String storyTitle, String storyLink, String storyType, String userName, String userPw);
    
    void manageStory(int storyID, Story story);
    
    String login(String userName, String password);
    
    void registerUser(String userName, String password, String email);
    
    void addCommentToStory(int storyId, String userId, String userPw, String comment);
    
    
}
