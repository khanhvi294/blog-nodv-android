package com.khanhvi.nodv_android_app.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post  extends Timestamp{
    private String id;
    private String title;
    private String subtitle;
    private String content;
    private String thumbnail;
    private String userId;
    private Integer timeRead;
    private Boolean isPublish;
    private List<String> userLikeIds;
    private List<Topic> topics;
}