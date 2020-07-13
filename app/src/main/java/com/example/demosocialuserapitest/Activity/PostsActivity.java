package com.example.demosocialuserapitest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demosocialuserapitest.Adapters.PostAdapter;
import com.example.demosocialuserapitest.Adapters.UserAdapter;
import com.example.demosocialuserapitest.Api.ApiInterface;
import com.example.demosocialuserapitest.Api.RetrofitClient;
import com.example.demosocialuserapitest.PostUtils.UserPost;
import com.example.demosocialuserapitest.R;
import com.example.demosocialuserapitest.UserUtils.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostsActivity extends AppCompatActivity {
    private TextView tvUserName;
    private RecyclerView pRecyclerView;
    private PostAdapter postAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<UserPost> postInfoList = new ArrayList<>();
    private ArrayList<UserPost> speceficPostInfoList = new ArrayList<>();
    private User userInfo = new User();

    private static final String TAG = "PostsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        init();

        final Intent intent = getIntent();
        userInfo = (User) intent.getSerializableExtra("userInfo");

        if (userInfo != null) {
            String name = userInfo.getUsername();
            tvUserName.setText("Posts of " + name + ":");

        }

        postAdapter = new PostAdapter(PostsActivity.this, speceficPostInfoList);
        layoutManager = new LinearLayoutManager(PostsActivity.this);
        pRecyclerView.setLayoutManager(layoutManager);
        pRecyclerView.setAdapter(postAdapter);

        loadPostData();
    }

    private void loadPostData() {
        String strUrl = RetrofitClient.BASE_URL + "posts";
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<List<UserPost>> call = api.getByPostUrl(strUrl);

        call.enqueue(new Callback<List<UserPost>>() {
            @Override
            public void onResponse(Call<List<UserPost>> call, Response<List<UserPost>> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.code());

                        postInfoList.addAll(response.body());
                       /* for(int i = 0; i<postInfoList.size();i++){
                            if(userInfo.getId().equals(postInfoList.get(i).getUserId())){
                                speceficPostInfoList.
                            }
                        }*/
                       for(UserPost userPost : postInfoList){
                           if(userInfo.getId().equals(userPost.getUserId()))
                           speceficPostInfoList.add(userPost);
                       }
                        postAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<UserPost>> call, Throwable t) {
                Toast.makeText(PostsActivity.this, "errorCode-" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void init() {
        pRecyclerView = findViewById(R.id.postInfoRecycleView);
        tvUserName = findViewById(R.id.userInfo);

    }
}
