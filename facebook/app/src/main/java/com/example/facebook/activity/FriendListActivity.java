package com.example.facebook.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.facebook.APIinterface;
import com.example.facebook.App;
import com.example.facebook.R;
import com.example.facebook.adaptor.FriendListAdaptor;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.Friend;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendListActivity extends AppCompatActivity implements FriendListAdaptor.OnCardListener {

    Toolbar toolbar;
    List<Friend> friendList = new ArrayList();
    FriendListAdaptor friendListAdaptor;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    String authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        initView();
        initRetrofit();
        initRecyclerView();
        initBottomNavigation();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.friend_recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(FriendListActivity.this, 1);
        recyclerView.setLayoutManager(layoutManager);
        friendListAdaptor = new FriendListAdaptor(friendList, this);
        recyclerView.setAdapter(friendListAdaptor);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initView() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Friend List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.request:
                        startActivity(new Intent(getApplicationContext(), FriendRequestActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.notification:
                        startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });
    }

    private void initRetrofit() {
        App.getApp().getFrienfListRetrofit().create(APIinterface.class).getFriendList(getIntent().getStringExtra("userId")).enqueue(
                new Callback<BaseResponse<List<Friend>>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<List<Friend>>> call, Response<BaseResponse<List<Friend>>> response) {
                        friendList.clear();
                        friendList.addAll(response.body().getData());
                        friendListAdaptor.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<List<Friend>>> call, Throwable t) {
                        Toast.makeText(FriendListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    @Override
    public void onCardClick(String id) {

    }
}
