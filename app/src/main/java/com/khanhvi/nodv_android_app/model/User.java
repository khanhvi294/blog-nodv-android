package com.khanhvi.nodv_android_app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class User implements Serializable {
    private int id;
    private String username;
    private int avatar;

    public User(int id, String username, int avatar) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}