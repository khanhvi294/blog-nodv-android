package com.khanhvi.nodv_android_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.khanhvi.nodv_android_app.R;
import com.khanhvi.nodv_android_app.model.Post;

import jp.wasabeef.richeditor.RichEditor;

public class PostDetailActivity extends AppCompatActivity {
    RichEditor richEditor;
    TextView postTitle;

    ImageView closeDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        setControl();
        setEvent();
    }

    private void setControl() {
        richEditor = findViewById(R.id.postContent);
        postTitle = findViewById(R.id.postTitle);
        closeDetail = findViewById(R.id.closeDetail);
    }

    private void setEvent() {
        Post newPost = (Post) getIntent().getSerializableExtra("newPost");
        richEditor.setInputEnabled(false);
        richEditor.setHtml(newPost.getContent());
        postTitle.setText(newPost.getTitle());
        closeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostDetailActivity.this,HomeActivity.class);
                intent.putExtra("newPost",newPost);
                startActivity(intent);
            }
        });
    }


}