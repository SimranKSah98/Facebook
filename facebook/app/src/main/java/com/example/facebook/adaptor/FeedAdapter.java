package com.example.facebook.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.facebook.R;
import com.example.facebook.pojo.PaginationPost.DataItem;
import com.example.facebook.pojo.PaginationPost.PostResponse;
import com.example.facebook.pojo.Post;

import java.util.ArrayList;
import java.util.List;
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewAdapter> {
    List<DataItem> dataItems ;
    Context context;

    public FeedAdapter(List<DataItem> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_homefeed,parent,false);
        return new MyViewAdapter(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter holder, int position) {
        holder.likeCount.setText(dataItems.get(position).getCounterOfLikes());
        holder.commentCount.setText(dataItems.get(position).getCounterOfComments());
        Glide.with(holder.feedImage.getContext()).load(dataItems.get(position).getPostImageUrl()).into(holder.feedImage);
        //Glide.with(holder.feedVideo.getContext()).load(postList.get(position).getPostVideoUrl()).into(holder.feedVideo);
    }
    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder{
        ImageView feedImage;
        VideoView feedVideo;
        ImageView userFeed;
        TextView likeCount;
        TextView commentCount;

        public MyViewAdapter(@NonNull View itemView) {
            super(itemView);
            feedImage=itemView.findViewById(R.id.imagepost_hfv);
            feedVideo=itemView.findViewById(R.id.videopost_hfv);
            userFeed=itemView.findViewById(R.id.imgView_proPic_hfv);
        }
    }
}