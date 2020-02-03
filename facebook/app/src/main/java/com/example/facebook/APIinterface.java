package com.example.facebook;

import com.example.facebook.pojo.Action;
import com.example.facebook.pojo.ActionLogin;
import com.example.facebook.pojo.Ads;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.BaseResponseLogin;
import com.example.facebook.pojo.DislikePost;
import com.example.facebook.pojo.Friend;
import com.example.facebook.pojo.FriendRequest;
import com.example.facebook.pojo.Login;
import com.example.facebook.pojo.Loginresponse;
import com.example.facebook.pojo.PaginationPost.PostResponse;
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

    @GET("user/getUserInfo")
    Call<BaseResponse<Profile>> getUserdetails(@Header("userId") String string);

    @GET("post/getUserPost/{pageNo}/{pageSize}")
    Call<BaseResponse<List<Post>>> getUserPost(@Header("token") String string, @Path("pageNo") int pageNo, @Path("pageSize") int pageSize);

    @GET("friends/getList")
    Call<BaseResponse<List<Friend>>> getFriendList(@Header("userId") String string);

    @POST("friends")
    Call<BaseResponse<FriendRequest>> sendRequest(@Body FriendRequest friendRequest);

    @GET("facebooksearch/search/{name}")
    Call<List<Profile>> searchbyName(@Path("name") String name);


    @POST("controller/register")
    Call<BaseResponseLogin<SignUp>> getregistered(@Body SignUp signUp);

    @GET("friends/accept/{whoacceptedId}/{whoseaccepted}}")
    Call<BaseResponse<List<FriendRequest>>> acceptRequest(@Path("whoacceptedId") String whoacceptedId, @Path("whoseaccepted") String whoseaccepted);


    @POST("controller/login")
    Call<BaseResponseLogin<Loginresponse>> getLogin(@Body Login login);

    @POST("roleController/userRole")
    Call<BaseResponseLogin<SignUp>> getRole(@Body SignUp signUp,@Header("Authorization") String accessToken);

    @GET("ads/getAds/{accessToken}")
    Call<List<Ads>> getAds(@Path("accessToken") String string);

    @POST("ads/onclick")
    Call<String> adsOnclick(@Body Ads ads);

    @POST("repo/add")
    Call<BaseResponse<Void>> addAction(@Body Action action, @Header("accessToken") String accessToken);

    @GET("/post/newsfeed/{pageNo}{pageSize}")
    Call<PostResponse> getnewsfeed(@Header("token") String string, @Path("pageNo")int pageNo, @Path("pageSize") int pageSize);

    @POST("/post/create")
    Call<BaseResponse<Post>> createPost(@Body Post post,@Header("token") String token);

    @POST("repo/addLogin")
    Call<BaseResponse<Void>> addActionLogin(@Body ActionLogin actionLogin,@Header("token") String s);

    @POST("supportAgent/createTicket")
    Call<String>disikeAction(@Body DislikePost dislikePost, @Header("token")String token);

}
