package com.khanhvi.nodv_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.khanhvi.nodv_android_app.activity.Data;
import com.khanhvi.nodv_android_app.activity.HomeActivity;
import com.khanhvi.nodv_android_app.model.Post;
import com.khanhvi.nodv_android_app.model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Post> postArr;
    SharedPreferences preferences; //Create Object of SharedPreferences
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        postArr = new ArrayList<>();

        Data data = new Data();
        User user1 = data.getCurrentUser(getBaseContext());
        User user2 = new User(2,"vi vi", R.drawable.avatar);
        postArr.add(new Post("post title 1","not 1<br> <img src=\"https://firebasestorage.googleapis.com/v0/b/nodv-android.appspot.com/o/uploads%2F1683904745202.jpg?alt=media&amp;token=57b9f56d-c007-4a4f-a599-695de8782ff5\" alt=\"hihi\" width=\"150\" height=\"100\"><br>","https://firebasestorage.googleapis.com/v0/b/nodv-android.appspot.com/o/uploads%2F1683904745202.jpg?alt=media&amp;token=57b9f56d-c007-4a4f-a599-695de8782ff5",user1,2));
        postArr.add(new Post("post title 2","not 2 <br> <img src=\"https://firebasestorage.googleapis.com/v0/b/nodv-android.appspot.com/o/uploads%2F1683904745202.jpg?alt=media&amp;token=57b9f56d-c007-4a4f-a599-695de8782ff5\" alt=\"hihi\" width=\"150\" height=\"100\"><br>","https://firebasestorage.googleapis.com/v0/b/nodv-android.appspot.com/o/uploads%2F1683904745202.jpg?alt=media&amp;token=57b9f56d-c007-4a4f-a599-695de8782ff5",user1,2));
        postArr.add(new Post("post title 3","not 3<br> <img src=\"https://firebasestorage.googleapis.com/v0/b/nodv-android.appspot.com/o/uploads%2F1683904745202.jpg?alt=media&amp;token=57b9f56d-c007-4a4f-a599-695de8782ff5\" alt=\"hihi\" width=\"150\" height=\"100\"><br>","https://firebasestorage.googleapis.com/v0/b/nodv-android.appspot.com/o/uploads%2F1683904745202.jpg?alt=media&amp;token=57b9f56d-c007-4a4f-a599-695de8782ff5",user2,2));
        postArr.add(new Post("post title 4","not 4 <br> <img src=\"https://firebasestorage.googleapis.com/v0/b/nodv-android.appspot.com/o/uploads%2F1683904745202.jpg?alt=media&amp;token=57b9f56d-c007-4a4f-a599-695de8782ff5\" alt=\"hihi\" width=\"150\" height=\"100\"><br>","https://firebasestorage.googleapis.com/v0/b/nodv-android.appspot.com/o/uploads%2F1683904745202.jpg?alt=media&amp;token=57b9f56d-c007-4a4f-a599-695de8782ff5",user2,2));
        postArr.add(new Post("post title 5","not 5 <br> <img src=\"https://firebasestorage.googleapis.com/v0/b/nodv-android.appspot.com/o/uploads%2F1683904745202.jpg?alt=media&amp;token=57b9f56d-c007-4a4f-a599-695de8782ff5\" alt=\"hihi\" width=\"150\" height=\"100\"><br>","https://firebasestorage.googleapis.com/v0/b/nodv-android.appspot.com/o/uploads%2F1683904745202.jpg?alt=media&amp;token=57b9f56d-c007-4a4f-a599-695de8782ff5",user1,2));

        Gson gson = new Gson();
        preferences = getSharedPreferences("postArr", Context.MODE_PRIVATE);
        editor = preferences.edit();
        String json = gson.toJson(postArr);
        editor.putString("postArr", json);
        editor.commit();

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    private void setControl() {

    }

    public ArrayList<Post> getPostList(){
        String json = preferences.getString("postArr", "");

        Gson gson = new Gson();

        Type type = new TypeToken<List<Post>>() {}.getType();

        postArr = new ArrayList<>();
        return postArr;
    }



}