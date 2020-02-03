package com.example.facebook.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.facebook.APIinterface;
import com.example.facebook.App;
import com.example.facebook.R;
import com.example.facebook.pojo.Action;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.Post;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView postLike;
    ImageView postDislike;
    Action action = new Action();
    Context context;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initView();
        initBottomNavigation();
        initRetrofit();

    }

    private void initView() {

        toolbar = findViewById(R.id.posttoolbar);
        toolbar.setTitle("Login");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        postLike=findViewById(R.id.postlike);
        postDislike=findViewById(R.id.postdislike);
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

    private void initRetrofit()
    {
        postLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = getResources().getDrawable(R.drawable.ic_thumb_up_black_24dp);
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, Color.BLUE);
                action.setChannel("Facebook");
                action.setTag("Coding");
                action.setAction("like");

                App.getApp().getActionRetrofit().create(APIinterface.class).addAction(action, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGlyYWcxMkBnbWFpbC5jb20iLCJ1c2VySWQiOiI2OTlmMzc5Zi02MGM3LTRkNTktYjFjMS1kNjVjYWRjMGZkOGQgIn0.TQ2I_x9CCd2R0wg-ru4ZhoB_8PtSMffg5jBYZ33Ueo-DzWDNfUkq01M1U5lSLwBgn0_sZwG5zYUvZu2ecsGtOA").enqueue(
                        new Callback<BaseResponse<Void>>() {
                            @Override
                            public void onResponse(Call<BaseResponse<Void>> call, Response<BaseResponse<Void>> response) {

                            }

                            @Override
                            public void onFailure(Call<BaseResponse<Void>> call, Throwable t) {

                            }
                        }
                );
            }
        });


    }
}
