package com.khanhvi.nodv_android_app.model;

import java.util.List;
public class Post  extends Timestamp{
    private String id;
    private String title;
    private String subtitle;
    private String content;
    private int thumbnail;
    private String userId;
    private Integer timeRead;
    private Boolean isPublish;
//    private List<String> userLikeIds;
//    private List<Topic> topics;

    public Post() {
    }

    public Post(String id, String title, String subtitle, String content, int thumbnail, String userId, Integer timeRead, Boolean isPublish) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.thumbnail = thumbnail;
        this.userId = userId;
        this.timeRead = timeRead;
        this.isPublish = isPublish;
//        this.userLikeIds = userLikeIds;
//        this.topics = topics;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getContent() {
        return content;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getTimeRead() {
        return timeRead;
    }

    public Boolean getPublish() {
        return isPublish;
    }

//    public List<String> getUserLikeIds() {
//        return userLikeIds;
//    }
//
//    public List<Topic> getTopics() {
//        return topics;
//    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTimeRead(Integer timeRead) {
        this.timeRead = timeRead;
    }

    public void setPublish(Boolean publish) {
        isPublish = publish;
    }

//    public void setUserLikeIds(List<String> userLikeIds) {
//        this.userLikeIds = userLikeIds;
//    }
//
//    public void setTopics(List<Topic> topics) {
//        this.topics = topics;
//    }
}