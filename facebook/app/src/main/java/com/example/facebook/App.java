package com.example.facebook;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static Retrofit retrofit;
    private static App app;
    public static String BASE_URL = "http://172.16.20.126:8082/";

    @Override
    public void onCreate() {
        super.onCreate();
        setApp(this);
    }

    public Retrofit getRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client).build();

        return retrofit;
    }

    public Retrofit getfriendrequest() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.177.69.57:7007/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client).build();

        return retrofit;
    }

    public Retrofit getRetrofit2() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.177.68.116:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client).build();

        return retrofit;
    }

    public Retrofit getActionRetrofit()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.20.160:8102/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client).build();

        return retrofit;
    }

    public Retrofit getregisterretrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.20.121:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client).build();

        return retrofit;
    }

    public Retrofit search() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.20.202:7007/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client).build();

        return retrofit;
    }

    public Retrofit getadsRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.20.83:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client).build();

        return retrofit;
    }

    public Retrofit getFrienfListRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client).build();

        return retrofit;
    }


    public static App getApp() {
        return app;
    }

    public static void setApp(App app) {
        App.app = app;
    }
}
