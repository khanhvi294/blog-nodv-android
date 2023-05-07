package com.khanhvi.nodv_android_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.khanhvi.nodv_android_app.R;
import com.khanhvi.nodv_android_app.model.Post;

import java.util.List;

public class PostApdater extends BaseAdapter {

    Context context;
    int layout;
    List<Post> postList;

    public PostApdater(Context context, int layout, List<Post> postList) {
        this.context = context;
        this.layout = layout;
        this.postList = postList;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(layout, null);


           TextView editTitle = convertView.findViewById(R.id.txtPostTitle);
           ImageView imgPost = convertView.findViewById(R.id.imgPost);
           TextView timecreate = convertView.findViewById(R.id.timeCreate);
            TextView timeRead = convertView.findViewById(R.id.timeRead);
        System.out.println("testttt"+postList.get(position).getTitle());
            editTitle.setText(postList.get(position).getTitle());
            imgPost.setImageResource(postList.get(position).getThumbnail());




        return convertView;
    }
}
