package com.example.facebook.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.facebook.APIinterface;
import com.example.facebook.App;
import com.example.facebook.R;
import com.example.facebook.adaptor.FeedAdapter;
import com.example.facebook.adaptor.ProfilePostAdaptor;
import com.example.facebook.adaptor.SlidingImage_Adapter;
import com.example.facebook.pojo.Ads;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.PaginationPost.DataItem;
import com.example.facebook.pojo.PaginationPost.PostResponse;
import com.example.facebook.pojo.Post;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    List<Ads> adsList;
    List<Post> postList=new ArrayList<Post>();
    RecyclerView recyclerView;
    FeedAdapter feedAdaptor;
    LinearLayoutManager manager;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private int page=0;
    private int totalPages;
    private int totalElement;
    private CirclePageIndicator indicator;
    SlidingImage_Adapter slidingImage_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBottomNavigation();
//        initRecycleview();
        showAds();
        showPost();
    }
    private void showAds() {
        mPager = (ViewPager) findViewById(R.id.pager);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.facebook.activity",MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken","");
        App.getApp().getadsRetrofit().create(APIinterface.class).getAds("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGlyYWcxMkBnbWFpbC5jb20iLCJ1c2VySWQiOiI2OTlmMzc5Zi02MGM3LTRkNTktYjFjMS1kNjVjYWRjMGZkOGQgIn0.TQ2I_x9CCd2R0wg-ru4ZhoB_8PtSMffg5jBYZ33Ueo-DzWDNfUkq01M1U5lSLwBgn0_sZwG5zYUvZu2ecsGtOA").enqueue(new Callback<List<Ads>>() {
            @Override
            public void onResponse(Call<List<Ads>> call, Response<List<Ads>> response) {
                if (null != response.body()) {
                    List<Ads> movies = response.body();
                    slidingImage_adapter = new SlidingImage_Adapter(MainActivity.this, movies);
                    mPager.setAdapter(slidingImage_adapter);
                    indicator = (CirclePageIndicator) findViewById(R.id.indicator);
                    indicator.setViewPager(mPager);
                    final float density = getResources().getDisplayMetrics().density;
                    indicator.setRadius(5 * density);
                    NUM_PAGES = movies.size();
                    System.out.println("=====>" + NUM_PAGES);
                }
            }
            @Override
            public void onFailure(Call<List<Ads>> call, Throwable t) {
                Log.d("failed", "on failure");
            }
        });
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
        mPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AdsActivity.class);
                startActivity(intent);
            }
        });
    }

//    private void initRecycleview() {
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 1);
//        manager=new LinearLayoutManager(MainActivity.this);
//        recyclerView = findViewById(R.id.feed_recycler);
//        feedAdaptor = new FeedAdapter(postList,MainActivity.this);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(feedAdaptor);
//    }


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
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    private void showPost()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.facebook.activity",MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken","");
        App.getApp().getRetrofit2().create(APIinterface.class).getnewsfeed(accessToken,0,3).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
             if(null!=response.body())
             {
                 //int length = postList.size();
                 //List<DataItem> dataItem = response.body().getData();
                 manager=new LinearLayoutManager(MainActivity.this);
                 recyclerView = findViewById(R.id.feed_recycler);
                 feedAdaptor = new FeedAdapter(response.body().getData(),MainActivity.this);
                 recyclerView.setLayoutManager(manager);
                 recyclerView.setAdapter(feedAdaptor);
             }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });

    }
}