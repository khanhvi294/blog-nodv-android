package com.khanhvi.nodv_android_app.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.khanhvi.nodv_android_app.model.Post;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Data {

    SharedPreferences preferences; //Create Object of SharedPreferences
    SharedPreferences.Editor editor;
    ArrayList<Post> postArr;

    public ArrayList<Post> getPostList(Context context) {
//        preferences = PreferenceManager.getDefaultSharedPreferences("postArr", context.);
        preferences = context.getSharedPreferences("postArr", Context.MODE_PRIVATE);

        String json = preferences.getString("postArr", "");

        Gson gson = new Gson();

        Type type = new TypeToken<List<Post>>() {
        }.getType();

        postArr = new ArrayList<>();
        postArr = gson.fromJson(json, type);

        return postArr;
    }

    public void addToPostList(Context context, Post post){
        postArr = getPostList(context);
        if(postArr.isEmpty()) {
            postArr = new ArrayList<>();
            postArr.add(post);
        }else {
            postArr.add(0, post);
        }
        Gson gson = new Gson();
        String json;
        editor = preferences.edit();
        json = gson.toJson(postArr);
        editor.putString("postArr", json);
        editor.commit();
    }

    public void removePostList(Context context, int viTri) {
        postArr = getPostList(context);
        postArr.remove(viTri);
        Gson gson = new Gson();
        String json;
        editor = preferences.edit();
        json = gson.toJson(postArr);
        editor.putString("postArr", json);
        editor.commit();
    }
    public void updatePost(Context context, int viTri, Post post) {
        postArr = getPostList(context);
        postArr.set(viTri,post);
        Gson gson = new Gson();
        String json;
        editor = preferences.edit();
        json = gson.toJson(postArr);
        editor.putString("postArr", json);
        editor.commit();
    }
}
