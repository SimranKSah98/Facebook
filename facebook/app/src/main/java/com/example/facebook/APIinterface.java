package com.example.facebook;

import androidx.cardview.widget.CardView;

import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.Friend;
import com.example.facebook.pojo.FriendRequest;
import com.example.facebook.pojo.Post;
import com.example.facebook.pojo.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIinterface {

    @GET("user/{userId}")
    Call<BaseResponse<Profile>> getUserdetails(@Path("userId") String string);

    @GET("post/getUserPost/{userId}/{pageNo}/{pageSize}")
    Call<BaseResponse<List<Post>>> getUserPost(@Path("userId") String string, @Path("pageNo") int pageNo, @Path("pageSize") int pageSize);

    @GET("friends/getList/{userId}")
    Call<BaseResponse<List<Friend>>> getFriendList(@Path("userId") String string);

    @GET("friends/accept/{whoacceptedId}/{whoseaccepted}}")
    Call<BaseResponse<List<FriendRequest>>> acceptRequest(@Path("whoacceptedId") String whoacceptedId, @Path("whoseaccepted") String whoseaccepted);
}
