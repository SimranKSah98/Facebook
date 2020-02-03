package com.example.facebook.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
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
import com.example.facebook.adaptor.ListViewAdaptor;
import com.example.facebook.adaptor.ProfilePostAdaptor;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.FriendRequest;
import com.example.facebook.pojo.Post;
import com.example.facebook.pojo.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    String s;
    Profile profile;
    private TextView username, useremail, userdob, userdesp, usergender;
    private EditText poststatus;
    private ProgressBar progressBar;
    private ProfilePostAdaptor profilePostAdaptor;
    private RecyclerView feedrecycleview;
    private List<Post> posts = new ArrayList();
    private Button frndList, follow, edit;
    Retrofit retrofit;
    ImageView postpic;
    VideoView postVideo;
    ListView listView;
    ListViewAdaptor adapter;
    List<Profile> profiles = new ArrayList<Profile>();
    SearchView searchView;
    String touser, fromuser;
    FriendRequest friendRequest = new FriendRequest();
    String accessToken;
    ImageView postButton;
    Post post = new Post();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        postButton = findViewById(R.id.postButton);
        poststatus = findViewById(R.id.poststatus);
        init();
        initBottomNavigation();
        initToolbar();
        initRecycleview();
        initRetrofit();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.facebook.activity", MODE_PRIVATE);
        accessToken = sharedPreferences.getString("accessToken", "");
        initFeedretrofit();
        initFriendList();
        initfollowrequest();
        setPoststatus();


    }


    private void setPoststatus() {

        post.setPostDescription(s);
        post.setBusinessProfilePost(false);
        post.setPostImageUrl("https://api.androidhive.info/json/movies/2.jpg");
        post.setPostVideoUrl("https://api.androidhive.info/json/movies/2.jpg");

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                App.getApp().getRetrofit2().create(APIinterface.class).createPost(post, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGlyYWcxMkBnbWFpbC5jb20iLCJ1c2VySWQiOiI2OTlmMzc5Zi02MGM3LTRkNTktYjFjMS1kNjVjYWRjMGZkOGQgIn0.TQ2I_x9CCd2R0wg-ru4ZhoB_8PtSMffg5jBYZ33Ueo-DzWDNfUkq01M1U5lSLwBgn0_sZwG5zYUvZu2ecsGtOA").enqueue(new Callback<BaseResponse<Post>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<Post>> call, Response<BaseResponse<Post>> response) {

                        if (null != response.body()) {
                            post = response.body().getData();
                            Toast.makeText(ProfileActivity.this, "successful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<Post>> call, Throwable t) {
                        Toast.makeText(ProfileActivity.this, "failure", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
        s = poststatus.getText().toString();
        touser = getIntent().getStringExtra("ToUserId");
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.facebook.activity", MODE_PRIVATE);
        fromuser = sharedPreferences.getString("accessToken", "");
        username = findViewById(R.id.username);
        useremail = findViewById(R.id.useremail);
        userdob = findViewById(R.id.userdob);
        userdesp = findViewById(R.id.userdescription);
        usergender = findViewById(R.id.usergender);
        frndList = findViewById(R.id.friendlist);
        searchView = (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(ProfileActivity.this);
        follow = findViewById(R.id.follow);
        postpic = findViewById(R.id.postpic);
        edit = findViewById(R.id.edituser);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EdituserActivity.class);
                startActivity(intent);
            }
        });

        postpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ImageuploadActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initfollowrequest() {
        friendRequest.setFromRequestId(fromuser);
        friendRequest.setToRequestId(touser);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getApp().getfriendrequest().create(APIinterface.class).sendRequest(friendRequest).enqueue(
                        new Callback<BaseResponse<FriendRequest>>() {
                            @Override
                            public void onResponse(Call<BaseResponse<FriendRequest>> call, Response<BaseResponse<FriendRequest>> response) {
                                Toast.makeText(ProfileActivity.this, "Sent Request", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<BaseResponse<FriendRequest>> call, Throwable t) {
                                Toast.makeText(ProfileActivity.this, "Sent Request", Toast.LENGTH_SHORT).show();

                            }
                        }
                );

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

    private void initRecycleview() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProfileActivity.this, 1);
        feedrecycleview = findViewById(R.id.profile_recycler_view);
        profilePostAdaptor = new ProfilePostAdaptor(posts,ProfileActivity.this);
        feedrecycleview.setLayoutManager(layoutManager);
        feedrecycleview.setItemAnimator(new DefaultItemAnimator());
        feedrecycleview.setAdapter(profilePostAdaptor);
        listView = (ListView) findViewById(R.id.searchlistview);
        listView.bringToFront();
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

        App.getApp().getRetrofit2().create(APIinterface.class).getUserPost("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsa2poQGdtYWlsLmNvbSIsInVzZXJJZCI6ImZmNTQyY2IzLTcxYWEtNGY4Mi1iMzc2LTMwOWJmMTlhYjA1ZSAifQ.4R0IkIYlbI_e94OQXA2Eg_ui_dIzaH6BDhu8BzCS6GFGJqEiaPt5gSpeYaF5hLT7JUahwMcd1fFdP4Qbib90LA", 0, 5).enqueue(
                new Callback<BaseResponse<List<Post>>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<List<Post>>> call, Response<BaseResponse<List<Post>>> response) {
//                        posts.clear();
                        posts.addAll(response.body().getData());
                        profilePostAdaptor.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<BaseResponse<List<Post>>> call, Throwable t) {
                        Toast.makeText(ProfileActivity.this, "failed to call profilepost", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        App.getApp().search().create(APIinterface.class).searchbyName(query).enqueue(
                new Callback<List<Profile>>() {
                    @Override
                    public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                        if (!response.body().isEmpty()) {
                            profiles.clear();
                            profiles.addAll(response.body());
                            adapter = new ListViewAdaptor(ProfileActivity.this, profiles);
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                                    int i = listView.getSelectedItemPosition();
                                    intent.putExtra("ToUserId", profiles.get(i).getUserId());
                                    startActivity(intent);

                                }
                            });
                        } else {
                            profiles.clear();
                            Profile profile = new Profile();
                            profile.setUserFirstName("No Search result");
                            profiles.add(profile);
                            adapter = new ListViewAdaptor(ProfileActivity.this, profiles);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(null);
                        }


                    }

                    @Override
                    public void onFailure(Call<List<Profile>> call, Throwable t) {
                        Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }
        );
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        listView.clearChoices();
        listView.setAdapter(null);
        return false;
    }
}

