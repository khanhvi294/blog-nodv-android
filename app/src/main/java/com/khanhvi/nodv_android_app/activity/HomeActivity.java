package com.khanhvi.nodv_android_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.khanhvi.nodv_android_app.R;
import com.khanhvi.nodv_android_app.adapter.PostAdapter;
import com.khanhvi.nodv_android_app.model.Post;
import com.khanhvi.nodv_android_app.model.User;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ListView listview;
    int vitri = -1;
    PostAdapter postAdapter;
    ArrayList<Post> postArr;
    FloatingActionButton fabCreate;

    ImageView deletePost;
    Data data = new Data();

    SharedPreferences preferences; //Create Object of SharedPreferences
    SharedPreferences.Editor editor;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setControl();
        setEvent();
    }

    private void setEvent() {

        preferences = getSharedPreferences("postArr", Context.MODE_PRIVATE);
        getPosts();

        postAdapter = new PostAdapter(this,R.layout.layout_post_item,postArr);
        listview.setAdapter(postAdapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent i = new Intent(HomeActivity.this,PostDetailActivity.class);
                startActivity(i);
            }
        });


        Post newPost = (Post) getIntent().getSerializableExtra("new_Post");
        if(newPost!=null){
        postArr.add(0,newPost);
        postAdapter.notifyDataSetChanged();}

        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,PostEditorActivity.class);
                startActivityForResult(intent, 5);
                startActivity(intent);
            }
        });


    }

    private void getPosts() {
        String json = preferences.getString("postArr", "");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Post>>() {}.getType();

        postArr = new ArrayList<>();
//here you get your list
        postArr = gson.fromJson(json, type);
    }

    private void setControl() {
        listview = (ListView) findViewById(R.id.postList);
        fabCreate = findViewById(R.id.fabCreate);
        deletePost = findViewById(R.id.deletePost);
    }



}