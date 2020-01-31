package com.example.facebook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebook.APIinterface;
import com.example.facebook.App;
import com.example.facebook.R;
import com.example.facebook.adaptor.FriendListAdaptor;
import com.example.facebook.adaptor.FriendRequestAdaptor;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.Friend;
import com.example.facebook.pojo.FriendRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendRequestActivity extends AppCompatActivity implements FriendRequestAdaptor.OnCardListener, FriendListAdaptor.OnCardListener {

    RecyclerView recyclerView;
    FriendRequestAdaptor friendRequestAdaptor;
    List<FriendRequest> friendRequestList=new ArrayList();
    List<Friend> friendList=new ArrayList();
    Toolbar toolbar;
    FriendRequest friendRequest=new FriendRequest();
    String fromuser,touser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendrequest);
        initView();
        initRecyclerView();
        initBottomNavigation();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.friend_recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(FriendRequestActivity.this, 1);
        recyclerView.setLayoutManager(layoutManager);
        friendRequestAdaptor = new FriendRequestAdaptor(friendList, this);
        recyclerView.setAdapter(friendRequestAdaptor);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initView() {

        touser=getIntent().getStringExtra("ToUserId");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Request");
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
                        return true;

                    case R.id.notification:
                        startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    private void initRetrofit()
    {
        App.getApp().getRetrofit().create(APIinterface.class).sendrequest(friendRequest).enqueue(
                new Callback<BaseResponse<FriendRequest>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<FriendRequest>> call, Response<BaseResponse<FriendRequest>> response) {
                        friendRequest=response.body().getData();



                    }

                    @Override
                    public void onFailure(Call<BaseResponse<FriendRequest>> call, Throwable t) {

                    }
                }
        );
    }

    @Override
    public void onCardClick(String id) {


    }
}
