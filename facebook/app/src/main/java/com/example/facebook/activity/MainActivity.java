package com.example.facebook.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.example.facebook.APIinterface;
import com.example.facebook.App;
import com.example.facebook.R;
import com.example.facebook.adaptor.FeedAdapter;
import com.example.facebook.adaptor.SlidingImage_Adapter;
import com.example.facebook.pojo.Ads;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.Post;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    List<Ads> adsList;
    List<Post> postList;
    RecyclerView recyclerView;
    FeedAdapter feedAdaptor;
    LinearLayoutManager manager;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private CirclePageIndicator indicator;
    SlidingImage_Adapter slidingImage_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showAds();
        //showPost();
    }
    private void showAds() {
        mPager = (ViewPager) findViewById(R.id.pager);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.facebook.activity",MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken","");
        App.getApp().getadsRetrofit().create(APIinterface.class).getAds(accessToken).enqueue(new Callback<List<Ads>>() {
            @Override
            public void onResponse(Call<List<Ads>> call, Response<List<Ads>> response) {
                if (null != response.body()) {
                    List<Ads> movies = response.body();
                    slidingImage_adapter = new SlidingImage_Adapter(MainActivity.this, movies);
                    mPager.setAdapter(slidingImage_adapter);
                    indicator = (CirclePageIndicator)
                            findViewById(R.id.indicator);
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
    }
//    private void showPost()
//    {
//        App app=new App();
//        postList=new ArrayList<Post>();
//        Retrofit retrofit=app.getRetrofit();//TODO:posts api and post retrofit
//        retrofit.create(APIinterface.class).getNewsFeed("user2",0,2).enqueue(new Callback<BaseResponse<List<Post>>>() {
//            @Override
//            public void onResponse(Call<BaseResponse<List<Post>>> call, Response<BaseResponse<List<Post>>> response) {
//                if(!response.isSuccessful())
//                {
//                }
//                else if(null!=response.body())
//                {
//                    recyclerView=findViewById(R.id.feed_recycler);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                    recyclerView.setAdapter(new FeedAdapter());
//                }
//            }
//            @Override
//            public void onFailure(Call<BaseResponse<List<Post>>> call, Throwable t) {
//            }
//        });
//    }
}