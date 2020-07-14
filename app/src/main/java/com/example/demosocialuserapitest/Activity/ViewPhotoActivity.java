package com.example.demosocialuserapitest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demosocialuserapitest.PhotoUtils.UserPhoto;
import com.example.demosocialuserapitest.R;
import com.example.demosocialuserapitest.UserUtils.User;
import com.squareup.picasso.Picasso;

public class ViewPhotoActivity extends AppCompatActivity {

    private ImageView originalImage;
    private TextView photoTitle;
    private UserPhoto userPhotoInfo = new UserPhoto();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);

        init();

        Intent intent = getIntent();
        userPhotoInfo = (UserPhoto) intent.getSerializableExtra("photoInfo");

        photoTitle.setText(userPhotoInfo.getTitle());
        Picasso.get().load(userPhotoInfo.getUrl()).into(originalImage);
    }

    private void init() {
        originalImage = findViewById(R.id.originalImage);
        photoTitle = findViewById(R.id.photoTitle);
    }
}
