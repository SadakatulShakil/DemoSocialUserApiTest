package com.example.demosocialuserapitest.Api;

import com.example.demosocialuserapitest.PostUtils.UserPost;
import com.example.demosocialuserapitest.UserUtils.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET()
    Call<List<User>> getByUrlString(
            @Url String url
    );

    @GET()
    Call<List<UserPost>> getByPostUrl(
            @Url String url
    );
}

