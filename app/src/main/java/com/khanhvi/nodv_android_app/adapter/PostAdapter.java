package com.khanhvi.nodv_android_app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.khanhvi.nodv_android_app.R;
import com.khanhvi.nodv_android_app.activity.Data;
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
                intent.putExtra("viTri",position);
                context.startActivity(intent);
            }
        });

        viewHolder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view,position);
            }
        });


        return convertView;
    }

    private class ViewHolder {

        TextView txtPostTitle,txtAuthor;
        ImageView imgPost, menu;
        LinearLayout postItem;



        public ViewHolder(View view){
            menu = view.findViewById(R.id.menu);
            txtPostTitle = view.findViewById(R.id.txtPostTitle);
            txtAuthor = view.findViewById(R.id.txtAuthor);
            imgPost = view.findViewById(R.id.imgPost);
            postItem = (LinearLayout) view.findViewById(R.id.postItem);
        }
    }

    public void showMenu(View v,int viTri) {
        PopupMenu popup = new PopupMenu(context,v);

        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_item_edit:

                        return true;
                    case R.id.menu_item_delete:
                        Data data = new Data();
                        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                        dialog.setTitle("Confirm");
                        dialog.setMessage("Are you sure delete post?");
                        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                data.removePostList(context,viTri);
                                postList.remove(viTri);
                                notifyDataSetChanged();
                            };
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
