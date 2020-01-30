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

import java.util.List;

public class FriendListAdaptor extends RecyclerView.Adapter<FriendListAdaptor.ViewHolder> {

    List<Friend> friendList;
    OnCardListener onCardListener;

    public FriendListAdaptor(List<Friend> friendList, OnCardListener onCardListener) {
        this.friendList = friendList;
        this.onCardListener = onCardListener;
    }

    @NonNull
    @Override
    public FriendListAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FriendListAdaptor.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friendlist, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull final FriendListAdaptor.ViewHolder holder, int position) {
        holder.friendname.setText(friendList.get(holder.getAdapterPosition()).getUserFirstName());
        holder.friendSurname.setText(friendList.get(holder.getAdapterPosition()).getUserLastName());
        Glide.with(holder.imageView.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(friendList.get(holder.getAdapterPosition()).getUserImage()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView friendname, friendSurname;
        public ImageView imageView;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friendname = itemView.findViewById(R.id.friend_name);
            friendSurname = itemView.findViewById(R.id.friend_lastname);
            imageView = itemView.findViewById(R.id.friend_image_view);
            cardView = itemView.findViewById(R.id.friendlistcard);
        }
    }

    public interface OnCardListener {
        void onCardClick(String id);
    }
}
