package com.example.facebook.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.facebook.APIinterface;
import com.example.facebook.App;
import com.example.facebook.R;
import com.example.facebook.pojo.Action;
import com.example.facebook.pojo.ActionLogin;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.BaseResponseLogin;
import com.example.facebook.pojo.Login;
import com.example.facebook.pojo.Loginresponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView inputEmail,inputPassword;
    Button btnLogin,btnSignup;
    private Toolbar toolbar;
    Login login;
    String token;
    SharedPreferences sharedPreferences ;
    ActionLogin actionLogin = new ActionLogin();
    List<String> tags=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
//        initBottomNavigation();
        initFirebasetoken();
        initToolbar();
        initRetrofit();
        signup();
    }

    private void initView()
    {
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tags.add("Coding");
        tags.add("Non-Fiction");
        tags.add("Football");


    }

    private void initToolbar()
    {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Login");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void initFirebasetoken()
    {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Token", "getInstanceId failed", task.getException());
                            return;
                        }

                        token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("Token", msg);
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initBottomNavigation()
    {
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

    private void initRetrofit()
    {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login=new Login();
                login.setEmailAddress(inputEmail.getText().toString());
                login.setPassword(inputPassword.getText().toString());
                login.setFcmToken(token);
                login.setChannel("Facebook");
                App.getApp().getregisterretrofit().create(APIinterface.class).getLogin(login).enqueue(
                        new Callback<BaseResponseLogin<Loginresponse>>() {
                            @Override
                            public void onResponse(Call<BaseResponseLogin<Loginresponse>> call, Response<BaseResponseLogin<Loginresponse>> response) {
                                Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                                sharedPreferences=getSharedPreferences("com.example.facebook.activity",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                Loginresponse loginresponse = response.body().getData();
                                String profile=loginresponse.getProfile();
                                editor.putString("accessToken",loginresponse.getAccessToken());
                                editor.commit();
                                if(profile.equals("")) {
                                    Intent intent = new Intent(LoginActivity.this, PostSignupActivity.class);
                                    intent.putExtra("accessToken", response.body().getData());
                                    startActivity(intent);
                                }
                                else {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("accessToken", response.body().getData());
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this,"done",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseResponseLogin<Loginresponse>> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                );


                addLoginAction();
            }
        });
    }

    private void addLoginAction()
    {
        actionLogin.setChannel("Facebook");
        actionLogin.setTag(tags);
        actionLogin.setAction("like");
        SharedPreferences sharedPreferences=getSharedPreferences("com.example.facebook.activity",MODE_PRIVATE);
        String token=sharedPreferences.getString("accessToken","");
        App.getApp().getActionRetrofit().create(APIinterface.class).addActionLogin(actionLogin,token).enqueue(new Callback<BaseResponse<Void>>() {
            @Override
            public void onResponse(Call<BaseResponse<Void>> call, Response<BaseResponse<Void>> response) {

            }

            @Override
            public void onFailure(Call<BaseResponse<Void>> call, Throwable t) {

            }
        });
    }

    private void signup()
    {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
