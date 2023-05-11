package com.khanhvi.nodv_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

        User user1 = new User("Khánh vi",R.drawable.avatar);
        User user2 = new User("vi vi", R.drawable.avatar);
        postArr.add(new Post("khanh vi 123","not","https://firebasestorage.googleapis.com/v0/b/blog-nodv.appspot.com/o/images%2F1677571804851amanitas_mushrooms_autumn_129262_4950x7421.jpg?alt=media&token=4e6d1d28-c17c-4411-957e-64bd273121ba",user1,2));
        postArr.add(new Post("khanh vi","not","https://firebasestorage.googleapis.com/v0/b/blog-nodv.appspot.com/o/images%2F1677571804851amanitas_mushrooms_autumn_129262_4950x7421.jpg?alt=media&token=4e6d1d28-c17c-4411-957e-64bd273121ba",user2,2));
        postArr.add(new Post("khanh vi","not","https://firebasestorage.googleapis.com/v0/b/blog-nodv.appspot.com/o/images%2F1677571804851amanitas_mushrooms_autumn_129262_4950x7421.jpg?alt=media&token=4e6d1d28-c17c-4411-957e-64bd273121ba",user1,2));
        postArr.add(new Post("khanh vi","not","https://firebasestorage.googleapis.com/v0/b/blog-nodv.appspot.com/o/images%2F1677571804851amanitas_mushrooms_autumn_129262_4950x7421.jpg?alt=media&token=4e6d1d28-c17c-4411-957e-64bd273121ba",user2,2));
        postArr.add(new Post("khanh vi","not","https://firebasestorage.googleapis.com/v0/b/blog-nodv.appspot.com/o/images%2F1677571804851amanitas_mushrooms_autumn_129262_4950x7421.jpg?alt=media&token=4e6d1d28-c17c-4411-957e-64bd273121ba",user1,2));

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