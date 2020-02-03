package com.example.facebook.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.facebook.APIinterface;
import com.example.facebook.App;
import com.example.facebook.R;
import com.example.facebook.pojo.BaseResponseLogin;
import com.example.facebook.pojo.SignUp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostSignupActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button radioButton;
    RadioButton publict, privatet, businesst;
    String getProfile, role;
    SignUp signUp=new SignUp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_signup);
        initView();
    }

    private void initView() {
        radioGroup = findViewById(R.id.radioGroup);
        publict = findViewById(R.id.publictext);
        privatet = findViewById(R.id.privatetext);
        businesst = findViewById(R.id.business);
        radioButton = findViewById(R.id.buttonradio);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                getProfile = (String) radioButton.getText();
                if (getProfile.equals("Public") || getProfile.equals("Private")) {
                    role = "Non Moderator";
                } else {
                    role = "Moderator";
                }
                signUp.setChannel("Facebook");
                signUp.setRole(role);
                signUp.setProfile(getProfile);

                App.getApp().getregisterretrofit().create(APIinterface.class).getRole(signUp,"Bearer "+getIntent().getStringExtra("accessToken")).enqueue(
                        new Callback<BaseResponseLogin<SignUp>>() {
                            @Override
                            public void onResponse(Call<BaseResponseLogin<SignUp>> call, Response<BaseResponseLogin<SignUp>> response) {
                                Toast.makeText(PostSignupActivity.this,"Sucess",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(PostSignupActivity.this,MainActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<BaseResponseLogin<SignUp>> call, Throwable t) {
                                Toast.makeText(PostSignupActivity.this,"Fail",Toast.LENGTH_SHORT).show();


                            }
                        }
                );

            }
        });


    }
}
