package com.example.demosocialuserapitest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.demosocialuserapitest.Adapters.UserAdapter;
import com.example.demosocialuserapitest.Api.ApiInterface;
import com.example.demosocialuserapitest.Api.RetrofitClient;
import com.example.demosocialuserapitest.R;
import com.example.demosocialuserapitest.UserUtils.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private UserAdapter userAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> userInfoList = new ArrayList<>();
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.userInfoRecycleView);
        userAdapter = new UserAdapter(MainActivity.this, userInfoList);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(userAdapter);

        loadUserList();
    }


    private void loadUserList() {
        String strUrl = RetrofitClient.BASE_URL + "users";
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<List<User>> call = api.getByUrlString(strUrl);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: " +response.code());
                    userInfoList.addAll(response.body());
                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "errorCode-" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
