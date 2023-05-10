package com.khanhvi.nodv_android_app.model;

import java.io.Serializable;
import java.util.List;
public class Post implements Serializable {
//    private String id;
    private String title;
//    private String subtitle;
    private String content;
    private String thumbnail;
    private User user;
    private int timeRead;

//    private List<String> userLikeIds;
//    private List<Topic> topics;

    public Post( String title,  String content, String thumbnail, User user, int timeRead){
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.user = user;
        this.timeRead = timeRead;
//        this.userLikeIds = userLikeIds;
//        this.topics = topics;
    }



    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public User getUser() {
        return user;
    }

    public int getTimeRead() {
        return timeRead;
    }


//    public List<String> getUserLikeIds() {
//        return userLikeIds;
//    }
//
//    public List<Topic> getTopics() {
//        return topics;
//    }



    public void setTitle(String title) {
        this.title = title;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setUserId(User userId) {
        this.user = user;
    }

    public void setTimeRead(int timeRead) {
        this.timeRead = timeRead;
    }



//    public void setUserLikeIds(List<String> userLikeIds) {
//        this.userLikeIds = userLikeIds;
//    }
//
//    public void setTopics(List<Topic> topics) {
//        this.topics = topics;
//    }
}