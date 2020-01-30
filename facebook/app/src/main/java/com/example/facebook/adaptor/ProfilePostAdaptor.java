package com.example.facebook.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.facebook.R;
import com.example.facebook.activity.ProfileActivity;
import com.example.facebook.pojo.Post;
import com.example.facebook.pojo.Profile;

import java.util.List;

public class ProfilePostAdaptor extends RecyclerView.Adapter<ProfilePostAdaptor.ViewHolder> {

    List<Post> posts;

    public ProfilePostAdaptor(List<Post> posts)
    {
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
        public TextView username,time,status,likecount,dislikecount,commentcount,emojocount;
        public ImageView profile, statusimage;
        public VideoView statusvideo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            time=itemView.findViewById(R.id.time);
            status=itemView.findViewById(R.id.status);
            statusimage=itemView.findViewById(R.id.imagepost);
            likecount=itemView.findViewById(R.id.liketext);
            dislikecount=itemView.findViewById(R.id.disliketext);
            commentcount=itemView.findViewById(R.id.commenttext);
            emojocount=itemView.findViewById(R.id.emojitext);
        }
    }
}
