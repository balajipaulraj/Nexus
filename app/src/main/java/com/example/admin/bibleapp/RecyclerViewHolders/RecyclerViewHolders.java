package com.example.admin.bibleapp.RecyclerViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.R;

public class RecyclerViewHolders extends RecyclerView.ViewHolder {
    public TextView tv;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        this.tv = (TextView) itemView.findViewById(R.id.web);
    }
}
