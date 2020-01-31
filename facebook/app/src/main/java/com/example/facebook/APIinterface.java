package com.example.facebook;

import androidx.annotation.CallSuper;
import androidx.cardview.widget.CardView;

import com.example.facebook.pojo.Action;
import com.example.facebook.pojo.Ads;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.BaseResponseLogin;
import com.example.facebook.pojo.Friend;
import com.example.facebook.pojo.FriendRequest;
import com.example.facebook.pojo.Login;
import com.example.facebook.pojo.Post;
import com.example.facebook.pojo.Profile;
import com.example.facebook.pojo.SignUp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIinterface {

    @GET("user/{userId}")
    Call<BaseResponse<Profile>> getUserdetails(@Path("userId") String string);

    @GET("post/getUserPost/{userId}/{pageNo}/{pageSize}")
    Call<BaseResponse<List<Post>>> getUserPost(@Path("userId") String string, @Path("pageNo") int pageNo, @Path("pageSize") int pageSize);

    @GET("friends/getList/{userId}")
    Call<BaseResponse<List<Friend>>> getFriendList(@Header("userId") String string);

    @POST
    Call<BaseResponse<FriendRequest>> sendRequest();

    @GET("facebooksearch/search/{name}")
    Call<List<Profile>> searchbyName(@Path("name") String name);

    @POST("controller/register")
    Call<BaseResponseLogin<SignUp>> getregistered(@Body SignUp signUp);

    @GET("friends/accept/{whoacceptedId}/{whoseaccepted}}")
    Call<BaseResponse<List<FriendRequest>>> acceptRequest(@Path("whoacceptedId") String whoacceptedId, @Path("whoseaccepted") String whoseaccepted);

    @POST("friends/sendFriend")
    Call<BaseResponse<FriendRequest>> sendrequest(@Body FriendRequest friendRequest);

    @POST("controller/login")
    Call<BaseResponseLogin<String>> getLogin(@Body Login login);

    @POST("roleController/userRole")
    Call<BaseResponseLogin<SignUp>> getRole(@Body SignUp signUp,@Header("Authorization") String accessToken);

    @GET("getAds/{accessToken}")
    Call<List<Ads>> getAds(@Path("accessToken") String string);

    @POST("/add")
    Call<BaseResponse<Void>> addAction(@Body Action action, @Header("Authorization") String accessToken);
}
