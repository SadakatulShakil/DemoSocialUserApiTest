package com.example.demosocialuserapitest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demosocialuserapitest.R;
import com.example.demosocialuserapitest.UserUtils.User;

public class AboutInfoActivity extends AppCompatActivity {

    private TextView userNameTV, userAlbums, userPosts, userHomeTV, userStreetTv, userZipCodeTV, userCurrentLat, userCurrentLong;
    private User userInfo = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_info);

        init();
        final Intent intent = getIntent();
        userInfo = (User) intent.getSerializableExtra("userInfo");

        if(userInfo!= null){

            userNameTV.setText(userInfo.getUsername());

            userHomeTV.setText(userInfo.getAddress().getCity());
            userStreetTv.setText(userInfo.getAddress().getStreet());
            userZipCodeTV.setText(userInfo.getAddress().getZipcode());

            userCurrentLat.setText(userInfo.getAddress().getGeo().getLat());
            userCurrentLong.setText(userInfo.getAddress().getGeo().getLng());
        }

        userPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(AboutInfoActivity.this, PostsActivity.class);
                intent.putExtra("userInfo", userInfo);
                startActivity(intent);
            }
        });


    }

    private void init() {
        userNameTV = findViewById(R.id.tvUserName);
        userAlbums = findViewById(R.id.tvAlbums);
        userPosts = findViewById(R.id.tvPosts);
        userHomeTV = findViewById(R.id.tvHome);
        userStreetTv = findViewById(R.id.tvStreet);
        userZipCodeTV = findViewById(R.id.tvZipCode);
        userCurrentLat = findViewById(R.id.tvLatitude);
        userCurrentLong = findViewById(R.id.tvLongitude);
    }
}

