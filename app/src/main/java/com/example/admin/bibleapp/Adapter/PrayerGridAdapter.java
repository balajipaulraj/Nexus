package com.example.admin.bibleapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Activity.myprayergroup;

public class PrayerGridAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] gridViewString;
    private final int[] gridViewImageId;

    public PrayerGridAdapter(Context context, String[] gridViewString, int[] gridViewImageId) {
        mContext = context;
        this.gridViewImageId = gridViewImageId;
        this.gridViewString = gridViewString;
    }

    @Override
    public int getCount() {
        return gridViewString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        gridViewAndroid = new View(mContext);
        gridViewAndroid = inflater.inflate(R.layout.single_prayer_group, null);
        final TextView category = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
        ImageView image = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image);
        if (convertView == null) {
            category.setText(gridViewString[position]);
            image.setImageResource(gridViewImageId[position]);
        } else {
            gridViewAndroid = (View) convertView;
        }
        image.setOnClickListener(new OnClickListener( ) {
            @Override
            public void onClick(View view) {
               String n = gridViewString[position];
                ((myprayergroup) mContext).shift(n,position);
            }
        });
        return gridViewAndroid;
    }
}