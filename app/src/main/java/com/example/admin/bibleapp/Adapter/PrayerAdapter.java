package com.example.admin.bibleapp.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.Model.Prayer;
import com.example.admin.bibleapp.Activity.PrayerActivity;
import com.example.admin.bibleapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10-Apr-18.
 */

public class PrayerAdapter extends RecyclerView.Adapter<PrayerAdapter.ViewHolder> {
    Context context;
    List<Prayer> data = new ArrayList<Prayer>();
    String category;
    public PrayerAdapter(Context context, List<Prayer> data,String cat) {
        this.context = context;
        this.data = data;
        category=cat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Prayer prayer = (Prayer) this.data.get(position);
        holder.ivSingleText.setText(prayer.getTitle());
        if(prayer.getTitle().equals("The Lords Prayer"))
        {
            Intent i=new Intent(context, PrayerActivity.class);
            i.putExtra("fragno",position);
            i.putExtra("category",category);
            context.startActivity(i);
        }
        holder.ivSingleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog pd=new ProgressDialog(context);
                pd.show();
                Intent i=new Intent(context, PrayerActivity.class);
                i.putExtra("fragno",position);
                i.putExtra("category",category);
                context.startActivity(i);
                pd.dismiss();
            }
        });
    }

    public int getItemCount() {
        return this.data.size();
    }

    public Prayer getItem(int position) {
        return (Prayer) this.data.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ivSingleText;
        RelativeLayout lay_single;

        public ViewHolder(View itemView) {
            super(itemView);
            ivSingleText=(TextView)itemView.findViewById(R.id.tv_single_prayer);
            lay_single=(RelativeLayout) itemView.findViewById(R.id.lay_single);
        }
    }



}
