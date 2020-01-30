package com.example.facebook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebook.APIinterface;
import com.example.facebook.App;
import com.example.facebook.R;
import com.example.facebook.adaptor.ProfilePostAdaptor;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.Post;
import com.example.facebook.pojo.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    Profile profile;
    private TextView username, useremail, userdob, userdesp, usergender, poststatus;
    private ProgressBar progressBar;
    private ProfilePostAdaptor profilePostAdaptor;
    private RecyclerView feedrecycleview;
    private List<Post> posts = new ArrayList();
    private Button frndList, follow, edit;
    Retrofit retrofit;
    ImageView postpic;
    VideoView postVideo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        initBottomNavigation();
        initToolbar();
        initRecycleview();
        initRetrofit();
        initFeedretrofit();
        initFriendList();
    }

    private void initFriendList() {
        frndList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, FriendListActivity.class);
                intent.putExtra("userId", "50d57520-0171-4756-a104-8fec92660959");
                startActivity(intent);
            }
        });
    }

    private void init() {
        username = findViewById(R.id.username);
        useremail = findViewById(R.id.useremail);
        userdob = findViewById(R.id.userdob);
        userdesp = findViewById(R.id.userdescription);
        usergender = findViewById(R.id.usergender);
        frndList = findViewById(R.id.friendlist);
        follow = findViewById(R.id.follow);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        edit = findViewById(R.id.edituser);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EdituserActivity.class);
                startActivity(intent);
            }
        });
        postpic = findViewById(R.id.postpic);
        postpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ImageuploadActivity.class);
                startActivity(intent);
            }
        });
        poststatus = findViewById(R.id.poststatus);
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

    private void initRecycleview() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProfileActivity.this, 1);
        feedrecycleview = findViewById(R.id.profile_recycler_view);
        profilePostAdaptor = new ProfilePostAdaptor(posts);
        feedrecycleview.setLayoutManager(layoutManager);
        feedrecycleview.setItemAnimator(new DefaultItemAnimator());
        feedrecycleview.setAdapter(profilePostAdaptor);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initRetrofit() {
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        App.getApp().getRetrofit().create(APIinterface.class).getUserdetails("50d57520-0171-4756-a104-8fec92660959").enqueue(
                new Callback<BaseResponse<Profile>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<Profile>> call, Response<BaseResponse<Profile>> response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        profile = response.body().getData();
                        username.setText(profile.getUserFirstName());
                        useremail.setText(profile.getUserEmailId());
                        userdob.setText(profile.getUserDateOfBirth());
                        userdesp.setText(profile.getPersonalDescription());
                        usergender.setText(profile.getUserGender());

                    }

                    @Override
                    public void onFailure(Call<BaseResponse<Profile>> call, Throwable t) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }


    private void initFeedretrofit() {
        App.getApp().getRetrofit2().create(APIinterface.class).getUserPost("user2", 0, 5).enqueue(
                new Callback<BaseResponse<List<Post>>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<List<Post>>> call, Response<BaseResponse<List<Post>>> response) {
                        posts.clear();
                        posts.addAll(response.body().getData());
                        profilePostAdaptor.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<List<Post>>> call, Throwable t) {

                    }
                }
        );
    }


}

