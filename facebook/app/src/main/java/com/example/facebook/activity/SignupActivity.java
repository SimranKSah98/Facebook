package com.example.facebook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.facebook.APIinterface;
import com.example.facebook.App;
import com.example.facebook.R;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.BaseResponseLogin;
import com.example.facebook.pojo.SignUp;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText inputEmail, inputPassword,inputName;
    Button btnSignup,btnLogin;
    String email,name,password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();
        initRegister();
        initBottomNavigation();
        initToolbar();

    }

    private void initView() {
        inputEmail = (EditText) findViewById(R.id.email);
        inputName = findViewById(R.id.username);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);

    }

    private void initRegister()
    {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = inputEmail.getText().toString();
                name = inputName.getText().toString();
                password = inputPassword.getText().toString();
                SignUp signUp=new SignUp();
                signUp.setName(name);
                signUp.setPassword(password);
                signUp.setEmailAddress(email);
                App.getApp().getregisterretrofit().create(APIinterface.class).getregistered(signUp).enqueue(
                        new Callback<BaseResponseLogin<SignUp>>() {
                            @Override
                            public void onResponse(Call<BaseResponseLogin<SignUp>> call, Response<BaseResponseLogin<SignUp>> response) {
                                Toast.makeText(SignupActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                                intent.putExtra("userId",response.body().getData().getUserId());
                                startActivity(intent);

                            }

                            @Override
                            public void onFailure(Call<BaseResponseLogin<SignUp>> call, Throwable t) {
                                Toast.makeText(SignupActivity.this, "failed", Toast.LENGTH_SHORT).show();


                            }
                        }
                );
            }
        });
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Sign Up");
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
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
    }
}
