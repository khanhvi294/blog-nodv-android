package com.khanhvi.nodv_android_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.khanhvi.nodv_android_app.R;
import com.khanhvi.nodv_android_app.activity.PostDetailActivity;
import com.khanhvi.nodv_android_app.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ArrayAdapter {

    Context context;
    int resource;
    List<Post> postList;

    public PostAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Post> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.postList = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Post post = postList.get(position);
        viewHolder.txtPostTitle.setText(post.getTitle());
        viewHolder.imgPost.setImageURI(Uri.parse(post.getThumbnail()));
        viewHolder.txtAuthor.setText(post.getUser().getUsername());

        viewHolder.postItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostDetailActivity.class);
                intent.putExtra("newPost", post);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private class ViewHolder {

        TextView txtPostTitle,txtAuthor;
        ImageView imgPost;
        LinearLayout postItem;


        public ViewHolder(View view){

            txtPostTitle = view.findViewById(R.id.txtPostTitle);
            txtAuthor = view.findViewById(R.id.txtAuthor);
            imgPost = view.findViewById(R.id.imgPost);
            postItem = (LinearLayout) view.findViewById(R.id.postItem);
        }
    }
}
