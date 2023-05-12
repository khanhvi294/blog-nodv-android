package com.khanhvi.nodv_android_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.khanhvi.nodv_android_app.MainActivity;
import com.khanhvi.nodv_android_app.R;
import com.khanhvi.nodv_android_app.adapter.PostAdapter;
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
    Data data = new Data();
    Post post;
    int viTri;
    PostAdapter postAdapter;

    ImageView menuMore;

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
        post = (Post) getIntent().getSerializableExtra("newPost");
        viTri = getIntent().getIntExtra("viTri",-1);
        menuMore = findViewById(R.id.menuMore);
    }

    private void setEvent() {
        Data data = new Data();
    postArr = data.getPostList(getBaseContext());

        richEditor.setInputEnabled(false);
        richEditor.setHtml(post.getContent());
        postTitle.setText(post.getTitle());
        closeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostDetailActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        menuMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(menuMore);
            }
        });
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this,v);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_item_edit:

                        return true;
                    case R.id.menu_item_delete:
                        AlertDialog.Builder dialog = new AlertDialog.Builder(PostDetailActivity.this);
                        dialog.setTitle("Confirm");
                        dialog.setMessage("Are you sure delete post?");
                        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                data.removePostList(getBaseContext(),viTri);
                                Intent intent = new Intent(PostDetailActivity.this,HomeActivity.class);
                                startActivity(intent);
                            }
                        });
                        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog alertDialog = dialog.create();
                        alertDialog.show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.inflate(R.menu.menu);
        popup.show();
    }

}