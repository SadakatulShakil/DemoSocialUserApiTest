package com.example.demosocialuserapitest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demosocialuserapitest.Adapters.CommentsAdapter;
import com.example.demosocialuserapitest.Adapters.PostAdapter;
import com.example.demosocialuserapitest.Api.ApiInterface;
import com.example.demosocialuserapitest.Api.RetrofitClient;
import com.example.demosocialuserapitest.PostUtils.CommentsResponse;
import com.example.demosocialuserapitest.PostUtils.UserPost;
import com.example.demosocialuserapitest.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CheckCommentsActivity extends AppCompatActivity {

    private TextView TvUserName, TvPostCheck,titleTV, bodyTV;
    private LinearLayout originalPostLayout,demoInfoLayout;
    private RecyclerView cRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CommentsAdapter commentsAdapter;
    private ArrayList<CommentsResponse> commentsInfoList = new ArrayList<>();
    private ArrayList<CommentsResponse> specificCommentsInfoList = new ArrayList<>();
    private UserPost userPostInfo = new UserPost();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_comments);

        init();

        final Intent intent = getIntent();
        userPostInfo = (UserPost) intent.getSerializableExtra("userPostInfo");

        commentsAdapter = new CommentsAdapter(CheckCommentsActivity.this, specificCommentsInfoList);
        layoutManager = new LinearLayoutManager(CheckCommentsActivity.this);
        cRecyclerView.setLayoutManager(layoutManager);
        cRecyclerView.setAdapter(commentsAdapter);

        TvPostCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                originalPostLayout.setVisibility(View.VISIBLE);
                demoInfoLayout.setVisibility(View.GONE);
                if(userPostInfo!=null){

                    titleTV.setText(userPostInfo.getTitle());
                    bodyTV.setText(userPostInfo.getBody());
                }

            }
        });

        loadCommentsInfo();



    }

    private void loadCommentsInfo() {
        String strUrl = RetrofitClient.BASE_URL + "comments";
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<List<CommentsResponse>> call = api.getByCommentsUrl(strUrl);

        call.enqueue(new Callback<List<CommentsResponse>>() {
            @Override
            public void onResponse(Call<List<CommentsResponse>> call, Response<List<CommentsResponse>> response) {
                if(response.code() ==  200){
                    commentsInfoList.addAll(response.body());

                    for(CommentsResponse  commentsResponse : commentsInfoList){
                        if(userPostInfo.getId().equals(commentsResponse.getPostId()))
                            specificCommentsInfoList.add(commentsResponse);

                    }
                    commentsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<CommentsResponse>> call, Throwable t) {
                Toast.makeText(CheckCommentsActivity.this, "errorCode-" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    private void init() {
        TvUserName =  findViewById(R.id.userNameTV);
        TvPostCheck = findViewById(R.id.postCheckTV);
        cRecyclerView = findViewById(R.id.commentsInfoRecycleView);
        originalPostLayout = findViewById(R.id.demoPost);
        demoInfoLayout = findViewById(R.id.demoInfo);
        titleTV = findViewById(R.id.tvTitle);
        bodyTV = findViewById(R.id.tvBody);
    }
}
