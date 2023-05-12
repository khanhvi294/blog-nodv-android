package com.khanhvi.nodv_android_app.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.khanhvi.nodv_android_app.R;
import com.khanhvi.nodv_android_app.databinding.ActivityMainBinding;
import com.khanhvi.nodv_android_app.model.Post;
import com.khanhvi.nodv_android_app.model.User;

import jp.wasabeef.richeditor.RichEditor;

public class PostEditorActivity extends AppCompatActivity {

    private RichEditor mEditor;
    String urlImage;
    ActivityMainBinding binding;
    StorageReference storageReference;
    boolean isEdit = false;
    Post postEdit;
    int viTri = -1;

    ImageView action_undo,
            action_redo,
            action_bold,
            action_italic,
            action_heading1,
            action_heading2,
            action_heading3,
            action_heading4,
            action_heading5,
            action_heading6,
            action_indent,
            action_outdent,
            action_align_left,
            action_align_center,
            action_align_right,
            action_insert_bullets,
            action_insert_numbers,
            action_insert_image,
            action_insert_checkbox,
            closeEditor;

Button publish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_editor);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setControl();
        setEvent();
    }

    private void setEvent() {
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.BLACK);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("Write something...");
        isEdit = getIntent().getBooleanExtra("isEdit",false);

        if(isEdit) {
            publish.setText("Update");
            postEdit = (Post)getIntent().getSerializableExtra("postEdit");
            viTri = getIntent().getIntExtra("viTri",-1);
            System.out.println("nhan"+viTri);
            String content = postEdit.getTitle() + "<br>" +  postEdit.getContent();
            mEditor.setHtml(content);
        }
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {

            }
        });

        closeEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostEditorActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        action_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.undo();
            }
        });

        action_redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.redo();
            }
        });

        action_bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBold();
            }
        });

        action_italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setItalic();
            }
        });

        action_heading1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(1);
            }
        });

        action_heading2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(2);
            }
        });

        action_heading3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(3);
            }
        });

        action_heading4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(4);
            }
        });

        action_heading5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(5);
            }
        });

        action_heading6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(6);
            }
        });


        action_indent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setIndent();
            }
        });

        action_outdent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setOutdent();
            }
        });

        action_align_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignLeft();
            }
        });

        action_align_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignCenter();
            }
        });

        action_align_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignRight();
            }
        });

        action_insert_bullets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBullets();
            }
        });

        action_insert_numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setNumbers();
            }
        });

        action_insert_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        action_insert_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.insertTodo();
            }
        });
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputString = mEditor.getHtml();
                String[] lines = inputString.split("<br>", 2);
                String title = lines[0];
                String content = lines[1];
                String thumbnail;
                Data data = new Data();
                Post newPost;
                if (urlImage == null) {
                    thumbnail = "https://firebasestorage.googleapis.com/v0/b/blog-nodv.appspot.com/o/images%2F1671427078388the-ky-1.jpg?alt=media&token=bb2c88cc-a368-4d28-9735-fe788f6bef6d";
                } else {
                    thumbnail = urlImage;
                }

                if(isEdit){
                    postEdit.setTitle(title);
                    postEdit.setContent(content);
                    postEdit.setThumbnail(thumbnail);
                    data.updatePost(getBaseContext(),viTri,postEdit);

                    newPost = postEdit;
                }
                else{
                    User user = new User("khanh vi", R.drawable.avatar);
                    newPost = new Post(title, content, thumbnail, user, 5);
                    data.addToPostList(getBaseContext() ,newPost);
                }
                Intent resultIntent = new Intent(getBaseContext(), PostDetailActivity.class);
                resultIntent.putExtra("newPost",newPost);
                startActivity(resultIntent);


            }
        });
    }

    private void setControl() {
        publish = findViewById(R.id.publish);
        closeEditor = findViewById(R.id.close);

        mEditor = (RichEditor) findViewById(R.id.editor);
        action_undo = findViewById(R.id.action_undo);
        action_redo = findViewById(R.id.action_redo);
        action_bold = findViewById(R.id.action_bold);
        action_italic = findViewById(R.id.action_italic);
        action_heading1 = findViewById(R.id.action_heading1);
        action_heading2 = findViewById(R.id.action_heading2);
        action_heading3 = findViewById(R.id.action_heading3);
        action_heading4 = findViewById(R.id.action_heading4);
        action_heading5 = findViewById(R.id.action_heading5);
        action_heading6 = findViewById(R.id.action_heading6);
        action_indent = findViewById(R.id.action_indent);
        action_outdent = findViewById(R.id.action_outdent);
        action_align_left = findViewById(R.id.action_align_left);
        action_align_center = findViewById(R.id.action_align_center);
        action_align_right = findViewById(R.id.action_align_right);
        action_insert_bullets = findViewById(R.id.action_insert_bullets);
        action_insert_numbers = findViewById(R.id.action_insert_numbers);
        action_insert_image = findViewById(R.id.action_insert_image);
        action_insert_checkbox = findViewById(R.id.action_insert_checkbox);


    }


    private String getPathFromUri(Context context, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            return uri.getPath();
        } else {
            ((Cursor) cursor).moveToFirst();
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            return filePath;
        }
    }


    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String imagePath = getPathFromUri(this, selectedImage);
            storageReference = FirebaseStorage.getInstance().getReference().child("uploads").child(System.currentTimeMillis() + "." + getFileExtension(selectedImage));
            storageReference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            mEditor.insertImage(url, "hihi", 150, 100);
                        }
                    });
                }
            });
        }
    }


    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


}
