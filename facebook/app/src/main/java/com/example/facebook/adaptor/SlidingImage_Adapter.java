package com.example.facebook.adaptor;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import com.example.facebook.R;
import com.example.facebook.pojo.Ads;
import java.util.List;
public class SlidingImage_Adapter extends PagerAdapter {
    private String[] urls;
    List<Ads> list;
    private LayoutInflater inflater;
    private Context context;
    public SlidingImage_Adapter(Context context, List<Ads>movies) {
        this.context = context;
        this.list=movies;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);
        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image1);
        Glide.with(imageView.getContext()).load(list.get(position).getImageUrl()).into(imageView);

        view.addView(imageLayout, 0);
        return imageLayout;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }
    @Override
    public Parcelable saveState() {
        return null;
    }
}
