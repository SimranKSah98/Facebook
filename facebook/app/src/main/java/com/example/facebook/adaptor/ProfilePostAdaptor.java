package com.example.facebook.adaptor;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.facebook.APIinterface;
import com.example.facebook.App;
import com.example.facebook.R;
import com.example.facebook.activity.MainActivity;
import com.example.facebook.activity.PostActivity;
import com.example.facebook.activity.ProfileActivity;
import com.example.facebook.pojo.Action;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.DislikePost;
import com.example.facebook.pojo.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ProfilePostAdaptor extends RecyclerView.Adapter<ProfilePostAdaptor.ViewHolder> {

    List<Post> posts = new ArrayList<>();
    Context context;
    SharedPreferences sharedPreferences;
    DislikePost dislikePost = new DislikePost();
    Action action = new Action();
    int disCount;


    public ProfilePostAdaptor(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }


    @NonNull
    @Override
    public ProfilePostAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profilefeed, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfilePostAdaptor.ViewHolder holder, final int position) {
        holder.username.setText(posts.get(position).getUserId());
        holder.time.setText(posts.get(position).getPostDate());
        holder.status.setText(posts.get(position).getPostDescription());
        holder.likecount.setText(String.valueOf(posts.get(position).getCounterOfLikes()));
        holder.dislikecount.setText(String.valueOf(posts.get(position).getCounterOfDislilkes()));
        holder.commentcount.setText(String.valueOf(posts.get(position).getCounterOfComments()));
        Glide.with(holder.statusimage.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(posts.get(holder.getAdapterPosition()).getPostImageUrl()).into(holder.statusimage);

        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postId = posts.get(position).getPostId();
                String source = "facebook";
                String userId = posts.get(position).getUserId();
                sharedPreferences = context.getSharedPreferences("com.example.facebook.activity", MODE_PRIVATE);
                String token = sharedPreferences.getString("accessToken", "");

                dislikePost.setPostId(postId);
                dislikePost.setSource(source);
                dislikePost.setUserId(userId);
                dislikePost.setPostDescription(posts.get(position).getPostDescription());
                dislikePost.setPostImageUrl("");
                dislikePost.setPostVideoUrl("");
                disCount = posts.get(position).getCounterOfDislilkes() + 1;
                dislikePost.setCounterOfDislikes(disCount);
                String str2 = String.valueOf(disCount);
                holder.dislikecount.setText(str2);

                action.setChannel("Facebook");
                action.setTag("Coding");
                action.setAction("dislike");

                App.getApp().getTicket().create(APIinterface.class).disikeAction(dislikePost, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsa2poQGdtYWlsLmNvbSIsInVzZXJJZCI6ImZmNTQyY2IzLTcxYWEtNGY4Mi1iMzc2LTMwOWJmMTlhYjA1ZSAifQ.4R0IkIYlbI_e94OQXA2Eg_ui_dIzaH6BDhu8BzCS6GFGJqEiaPt5gSpeYaF5hLT7JUahwMcd1fFdP4Qbib90LA")
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (null != response.body()) {
                                    String val = response.body();
                                    System.out.println("=====>" + val);
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(context.getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                App.getApp().getActionRetrofit().create(APIinterface.class).addAction(action, token).enqueue(new Callback<BaseResponse<Void>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<Void>> call, Response<BaseResponse<Void>> response) {
                        Toast.makeText(context.getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<Void>> call, Throwable t) {
//                        Toast.makeText(context.getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.setChannel("Facebook");
                action.setTag("Coding");
                action.setAction("like");
                sharedPreferences = context.getSharedPreferences("com.example.facebook.activity", MODE_PRIVATE);
                String accessToken = sharedPreferences.getString("accessToken", "");
                App.getApp().getActionRetrofit().create(APIinterface.class).addAction(action, accessToken).enqueue(
                        new Callback<BaseResponse<Void>>() {
                            @Override
                            public void onResponse(Call<BaseResponse<Void>> call, Response<BaseResponse<Void>> response) {
                                Toast.makeText(context.getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<BaseResponse<Void>> call, Throwable t) {
//                                Toast.makeText(context.getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                String str = posts.get(position).getCounterOfLikes().toString();
                int disCount = Integer.parseInt(str) + 1;
                String str2 = String.valueOf(disCount);
                holder.likecount.setText(str2);

            }
        });


        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.setChannel("Facebook");
                action.setAction("comment");
                action.setTag("Coding");
                sharedPreferences = context.getSharedPreferences("com.example.facebook.activity", MODE_PRIVATE);
                String accessToken = sharedPreferences.getString("accessToken", "");
                App.getApp().getActionRetrofit().create(APIinterface.class).addAction(action, accessToken).enqueue(
                        new Callback<BaseResponse<Void>>() {
                            @Override
                            public void onResponse(Call<BaseResponse<Void>> call, Response<BaseResponse<Void>> response) {
                                Toast.makeText(context.getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<BaseResponse<Void>> call, Throwable t) {
//                                Toast.makeText(context.getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username, time, status, likecount, dislikecount, commentcount, emojocount;
        public ImageView profile, statusimage, like, dislike, comment;
        public VideoView statusvideo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);
            statusimage = itemView.findViewById(R.id.imagepost);
            likecount = itemView.findViewById(R.id.liketext);
            dislikecount = itemView.findViewById(R.id.disliketext);
            commentcount = itemView.findViewById(R.id.commenttext);
            emojocount = itemView.findViewById(R.id.emojitext);
            like = itemView.findViewById(R.id.like);
            dislike = itemView.findViewById(R.id.dislike);
            comment = itemView.findViewById(R.id.comment);

        }
    }
}
