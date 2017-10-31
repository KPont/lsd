/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.lsd.boundary;

import com.home.lsd.control.FileIO;
import com.home.lsd.entity.Comment;
import com.home.lsd.entity.Story;
import com.home.lsd.entity.User;
import com.home.lsd.persistence.MySQL;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasper
 */
public class Facade implements IBackend{

    MySQL ms = new MySQL("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/hackernews", "root", "pwd");

    public static void main(String[] args) throws Exception {
        Facade dbc = new Facade();
//        dbc.testConnection();
//        dbc.testAddUsers();
//        dbc.testGetStories();
//        dbc.testAddStory();
//        dbc.testAddUser();
//        dbc.testGetUserById();
//        dbc.testGetUserByName();
//        dbc.testGetStoryById();
//        dbc.testAddCommentToStory();
        dbc.testLogin();
    }

    public void testConnection() throws Exception {
        ArrayList<String> result = ms.getUsers();

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    public void testAddUsers() throws Exception {
        FileIO fio = new FileIO();
        BufferedReader br = fio.read("D:\\Kasper\\School\\lsd-data\\users.csv");
        ms.insertUsersToDB(br);
    }

    public void testGetStories() throws Exception {
        ArrayList<Story> stories = ms.getStories();

        for (int i = 0; i < stories.size(); i++) {
            System.out.println(stories.get(i).toString());
        }
    }

    public void testAddStory() throws Exception {
        Story story = new Story(99999999, "testAdd", "www.testadd.com", "testaddtype", "Karsten", null);
        ms.addStory(story);
    }

    public void testAddUser() throws Exception {
        User user = new User("Anton-Arne", "Anton-Arne's Password", "Anton-Arne@Anton-Arnes.dk");
        ms.addUser(user);
    }
    
    public void testGetUserById() throws Exception{
        User user = ms.getUserById(340997);
        
        System.out.println(user.toString());
    }
    
    public void testGetUserByName() throws Exception{
        User user = ms.getUserByName("Anton-Arne");
        
        System.out.println(user.toString());
    }
    
    public void testGetStoryById() throws Exception{
        Story story = ms.getStoryById(99999999);
        
        System.out.println(story.toString());
    }
    
    public void testAddCommentToStory() throws Exception{
        ms.addCommentToStory(99999999, new Comment("New Comment Test", "Karsten"));
    }
    
    public void testLogin() throws Exception{
        System.out.println(ms.getLogin("Karsten", "hej2"));
        
    }

    @Override
    public ArrayList<Story> getStories() {
        ArrayList<Story> stories = null;
        try {
            stories = ms.getStories();
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return stories;
    }

    @Override
    public Story getStory(int id) {
        Story story = null;
        try {
            story = ms.getStoryById(id);
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return story;
    }

    @Override
    public void addStory(int storyId, String storyTitle, String storyLink, String storyType, String userName) {
        Story story = new Story(storyId, storyTitle, storyLink, storyType, userName, null);
        
        try {
            ms.addStory(story);
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void manageStory(int storyID, Story story) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String login(String userName, String password) {
        String result = "Error";
        try {
            result = ms.getLogin(userName, password);
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void registerUser(String userName, String password, String email) {
        User user = new User(userName, password, email);
        try {
            ms.addUser(user);
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addCommentToStory(int storyId, String userName, String comment) {
        try {
            ms.addCommentToStory(storyId, new Comment(comment, userName));
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
