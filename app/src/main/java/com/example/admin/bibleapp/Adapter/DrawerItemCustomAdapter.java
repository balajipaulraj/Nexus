package com.example.admin.bibleapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bibleapp.Model.MenuItems;
import com.example.admin.bibleapp.R;

import java.util.List;

public class DrawerItemCustomAdapter extends ArrayAdapter<String> {
    Context mContext;
    int layoutResourceId;
    List<String> data;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, List<String> data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);
        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);
        String folder = data.get(position);
//        imageViewIcon.setImageResource(folder.getIcon());
        textViewName.setText(folder);

        return listItem;
    }

}
