package com.example.admin.bibleapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.admin.bibleapp.Model.BannerModel;
import com.example.admin.bibleapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    List<BannerModel> image;
    Context context;
    public static String BASE_URL = "http://ec2-15-206-75-177.ap-south-1.compute.amazonaws.com";

    public BannerAdapter(Context con, List<BannerModel> image) {
        this.context = con;
        this.image = image;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.item_banner, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.iv_banner);
        String url = image.get(position).getBannerimage();
        Log.e("data image",url);
        Picasso.with(context).load(BASE_URL +image.get(position).getBannerimage()).memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);

        ((ViewPager) container).addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View) object);
    }
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}