package com.khanhvi.nodv_android_app.activity;

import androidx.appcompat.app.AppCompatActivity;

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

        listview = (ListView) findViewById(R.id.postList);
        postArr = new ArrayList<>();

        postArr.add(new Post("1","khanh vi","not","1223333",R.drawable.avatar,"1",2,true));
        postArr.add(new Post("1","khanh vi","not","1223333",R.drawable.avatar,"1",2,true));
        postArr.add(new Post("1","khanh vi","not","1223333",R.drawable.avatar,"1",2,true));
        postArr.add(new Post("1","khanh vi","not","1223333",R.drawable.avatar,"1",2,true));
        postArr.add(new Post("1","khanh vi","not","1223333",R.drawable.avatar,"1",2,true));

        postApdater = new PostApdater(this,R.layout.layout_post_item,postArr);
        listview.setAdapter(postApdater);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(HomeActivity.this,PostEdittorActivity.class);
                startActivity(i);
            }
        });
    }
}