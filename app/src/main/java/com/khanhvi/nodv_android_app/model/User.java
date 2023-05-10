package com.khanhvi.nodv_android_app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class User {
    private String username;
    private int avatar;

    public User(String username, int avatar) {
        this.username = username;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}