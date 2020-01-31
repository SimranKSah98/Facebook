package com.example.facebook.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.facebook.R;
import com.example.facebook.pojo.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdaptor extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<Profile> profiles = null;
    private ArrayList<Profile> arraylist;

    public ListViewAdaptor(Context context, List<Profile> profiles) {
        mContext = context;
        this.profiles = profiles;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Profile>();
        this.arraylist.addAll(this.profiles);
    }

    @Override
    public int getCount() {
        return profiles.size();
    }

    @Override
    public Object getItem(int position) {
        return profiles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(profiles.get(position).getUserFirstName());
        return view;
    }

    public void filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        profiles.clear();
        if (charText.length() == 0) {
            profiles.addAll(arraylist);
        } else {
            for (Profile wp : arraylist) {
                if (wp.getUserFirstName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    profiles.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder
    {
        TextView name;
    }
}
