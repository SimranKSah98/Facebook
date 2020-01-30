package com.example.facebook.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.facebook.R;
import com.example.facebook.pojo.Friend;
import com.example.facebook.pojo.FriendRequest;

import java.util.List;

public class FriendRequestAdaptor extends RecyclerView.Adapter<FriendRequestAdaptor.ViewHolder> {

    List<Friend> friendRequestList;
    OnCardListener onCardListener;

    public FriendRequestAdaptor(List<Friend> friendRequestList, OnCardListener onCardListener) {
        this.friendRequestList = friendRequestList;
        this.onCardListener = onCardListener;
    }

    @NonNull
    @Override
    public FriendRequestAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FriendRequestAdaptor.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friendrequest, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestAdaptor.ViewHolder holder, int position) {
        holder.friendname.setText(friendRequestList.get(holder.getAdapterPosition()).getUserFirstName());
        holder.friendSurname.setText(friendRequestList.get(holder.getAdapterPosition()).getUserLastName());
        Glide.with(holder.imageView.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(friendRequestList.get(holder.getAdapterPosition()).getUserImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return friendRequestList.size();
    }

    public interface OnCardListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView friendname, friendSurname;
        public ImageView imageView;
        public Button btndelete,btnaccept;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friendname=itemView.findViewById(R.id.friend_name);
            friendSurname=itemView.findViewById(R.id.friend_lastname);
            imageView=itemView.findViewById(R.id.friend_image_view);
            btnaccept=itemView.findViewById(R.id.btnaccept);
            btndelete=itemView.findViewById(R.id.btndelete);

        }
    }

}
