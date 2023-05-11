package com.khanhvi.nodv_android_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.khanhvi.nodv_android_app.MainActivity;
import com.khanhvi.nodv_android_app.R;
import com.khanhvi.nodv_android_app.model.Post;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

public class PostDetailActivity extends AppCompatActivity {
    RichEditor richEditor;
    TextView postTitle;

    SharedPreferences preferences;
    ImageView closeDetail;
    ArrayList<Post> postArr;

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
        Data data = new Data();
    postArr = data.getPostList(getBaseContext());
        System.out.println(postArr);
//                preferences = getSharedPreferences("postArr", Context.MODE_PRIVATE);
//        String json = preferences.getString("postArr", "");
//
//        Gson gson = new Gson();
//
//        Type type = new TypeToken<List<Post>>() {}.getType();
//
//        postArr = new ArrayList<>();

//here you get your list
//        postArr = gson.fromJson(json, type);
        System.out.println("POst " + postArr.get(0).getTitle());


        Post newPost = (Post) getIntent().getSerializableExtra("newPost");
        System.out.println("newwwww "+newPost);
        richEditor.setInputEnabled(false);
        richEditor.setHtml(newPost.getContent());
        postTitle.setText(newPost.getTitle());
        closeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostDetailActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }


}