package com.khanhvi.nodv_android_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.khanhvi.nodv_android_app.R;
import com.khanhvi.nodv_android_app.model.Post;

import jp.wasabeef.richeditor.RichEditor;

public class PostDetailActivity extends AppCompatActivity {
    RichEditor richEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        setControl();
        setEvent();
    }

    private void setControl() {
        richEditor = findViewById(R.id.postContent);

    }

    private void setEvent() {
        Post newPost = (Post) getIntent().getSerializableExtra("newPost");
        richEditor.setInputEnabled(false);
        richEditor.setHtml(newPost.getContent());
    }


}