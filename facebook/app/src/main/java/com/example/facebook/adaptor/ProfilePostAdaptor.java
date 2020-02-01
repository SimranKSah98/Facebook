package com.example.facebook.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.facebook.APIinterface;
import com.example.facebook.App;
import com.example.facebook.R;
import com.example.facebook.pojo.Action;
import com.example.facebook.pojo.BaseResponse;
import com.example.facebook.pojo.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePostAdaptor extends RecyclerView.Adapter<ProfilePostAdaptor.ViewHolder> {

    List<Post> posts;
    Action action = new Action();

    public ProfilePostAdaptor(List<Post> posts) {
        this.posts = posts;
    }


    @NonNull
    @Override
    public ProfilePostAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profilefeed, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePostAdaptor.ViewHolder holder, int position) {
        holder.username.setText(posts.get(position).getUserId());
        holder.time.setText(posts.get(position).getPostDate());
        holder.status.setText(posts.get(position).getPostDescription());
        holder.likecount.setText(String.valueOf(posts.get(position).getCounterOfLikes()));
        holder.dislikecount.setText(String.valueOf(posts.get(position).getCounterOfDislilkes()));
        holder.commentcount.setText(String.valueOf(posts.get(position).getCounterOfComments()));
        Glide.with(holder.statusimage.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(posts.get(holder.getAdapterPosition()).getPostImageUrl()).into(holder.statusimage);


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username, time, status, likecount, dislikecount, commentcount, emojocount;
        public ImageView profile, statusimage, like, dislike;
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


            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.setChannel("Facebook");
                    action.setTag("Coding");
                    action.setAction("like");

                    App.getApp().getActionRetrofit().create(APIinterface.class).addAction(action, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGlyYWcxMkBnbWFpbC5jb20iLCJ1c2VySWQiOiI2OTlmMzc5Zi02MGM3LTRkNTktYjFjMS1kNjVjYWRjMGZkOGQgIn0.TQ2I_x9CCd2R0wg-ru4ZhoB_8PtSMffg5jBYZ33Ueo-DzWDNfUkq01M1U5lSLwBgn0_sZwG5zYUvZu2ecsGtOA").enqueue(
                            new Callback<BaseResponse<Void>>() {
                                @Override
                                public void onResponse(Call<BaseResponse<Void>> call, Response<BaseResponse<Void>> response) {

                                }

                                @Override
                                public void onFailure(Call<BaseResponse<Void>> call, Throwable t) {

                                }
                            }
                    );
                }
            });
        }
    }
}
