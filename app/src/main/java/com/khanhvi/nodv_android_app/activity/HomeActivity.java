package com.khanhvi.nodv_android_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.khanhvi.nodv_android_app.R;
import com.khanhvi.nodv_android_app.adapter.PostApdater;
import com.khanhvi.nodv_android_app.model.Post;
import com.khanhvi.nodv_android_app.model.User;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ListView listview;
    ArrayList<Post> postArr;
    PostApdater postApdater;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setControl();
        setEvent();

    }

    private void setEvent() {
        postArr = new ArrayList<>();

        User user1 = new User("Khánh vi",R.drawable.avatar);
        User user2 = new User("vi vi", R.drawable.avatar);
        postArr.add(new Post("khanh vi","not","https://firebasestorage.googleapis.com/v0/b/blog-nodv.appspot.com/o/images%2F1677571804851amanitas_mushrooms_autumn_129262_4950x7421.jpg?alt=media&token=4e6d1d28-c17c-4411-957e-64bd273121ba",user1,2));
        postArr.add(new Post("khanh vi","not","https://firebasestorage.googleapis.com/v0/b/blog-nodv.appspot.com/o/images%2F1677571804851amanitas_mushrooms_autumn_129262_4950x7421.jpg?alt=media&token=4e6d1d28-c17c-4411-957e-64bd273121ba",user2,2));
        postArr.add(new Post("khanh vi","not","https://firebasestorage.googleapis.com/v0/b/blog-nodv.appspot.com/o/images%2F1677571804851amanitas_mushrooms_autumn_129262_4950x7421.jpg?alt=media&token=4e6d1d28-c17c-4411-957e-64bd273121ba",user1,2));
        postArr.add(new Post("khanh vi","not","https://firebasestorage.googleapis.com/v0/b/blog-nodv.appspot.com/o/images%2F1677571804851amanitas_mushrooms_autumn_129262_4950x7421.jpg?alt=media&token=4e6d1d28-c17c-4411-957e-64bd273121ba",user2,2));
        postArr.add(new Post("khanh vi","not","https://firebasestorage.googleapis.com/v0/b/blog-nodv.appspot.com/o/images%2F1677571804851amanitas_mushrooms_autumn_129262_4950x7421.jpg?alt=media&token=4e6d1d28-c17c-4411-957e-64bd273121ba",user1,2));

        postApdater = new PostApdater(this,R.layout.layout_post_item,postArr);
        listview.setAdapter(postApdater);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(HomeActivity.this,PostEditorActivity.class);
                startActivityForResult(i, 5);
                startActivity(i);
            }
        });

        Post newPost = (Post) getIntent().getSerializableExtra("newPost");
        if(newPost!=null){
        postArr.add(0,newPost);
        postApdater.notifyDataSetChanged();}
    }

    private void setControl() {
        listview = (ListView) findViewById(R.id.postList);

    }

}