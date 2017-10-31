/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.lsd.boundary;

import com.home.lsd.entity.Story;
import java.util.ArrayList;

/**
 *
 * @author Kasper
 */
public interface IBackend {
    ArrayList<Story> getStories();
    
    Story getStory(int id);
    
    void addStory(int storyId, String storyTitle, String storyLink, String storyType, String userName);
    
    void manageStory(int storyID, Story story);
    
    String login(String userName, String password);
    
    void registerUser(String userName, String password, String email);
    
    void addCommentToStory(int storyId, String userId, String comment);
    
    
}
