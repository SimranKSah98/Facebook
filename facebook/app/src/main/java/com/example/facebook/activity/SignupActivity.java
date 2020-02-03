package com.example.facebook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.facebook.APIinterface;
import com.example.facebook.App;
import com.example.facebook.R;
import com.example.facebook.pojo.BaseResponseLogin;
import com.example.facebook.pojo.SignUp;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText inputEmail, inputPassword,inputName,confPassword;
    Button btnSignup,btnLogin;
    String email,name,password;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    //                    "(?=.*[a-zA-Z])" +      //any letter
                    //                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    //                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();
        initRegister();
//        initBottomNavigation();
        initToolbar();

    }

    private void initView() {
        inputEmail = (EditText) findViewById(R.id.email);
        inputName = findViewById(R.id.username);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        confPassword=findViewById(R.id.passwordConf);

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

                if (!validateEmail() | !validateUsername() | !validatePassword()) {
                    return;
                }
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

    private boolean validateEmail() {
        String emailInput = inputEmail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            inputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            inputEmail.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            inputEmail.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = inputName.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            inputName.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            inputName.setError("Username too long");
            return false;
        } else {
            inputName.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = inputPassword.getText().toString().trim();
        String passwordConf = confPassword.getText().toString();


        if (passwordInput.isEmpty()) {
            inputPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            inputPassword.setError("Password too weak");
            return false;
        } else if (passwordInput.equals(passwordConf)) {
            inputPassword.setError(null);
            return true;
        } else {
            inputPassword.setError(null);
            return true;
        }
    }

}
