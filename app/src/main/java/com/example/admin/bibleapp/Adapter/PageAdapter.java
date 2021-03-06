package com.example.admin.bibleapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.Activity.EventDetail;
import com.example.admin.bibleapp.Activity.PageDetail;
import com.example.admin.bibleapp.Model.Event;
import com.example.admin.bibleapp.Model.Page;
import com.example.admin.bibleapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.ViewHolder> {

    private Context context;
    private List<Page> activityListModels;
    public static String BASE_URL = "http://ec2-15-206-75-177.ap-south-1.compute.amazonaws.com";


    public PageAdapter(Context context, List<Page> activityListModels) {
        this.context = context;
        this.activityListModels = activityListModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.brandname.setText(activityListModels.get(position).getTitle());
        String url = activityListModels.get(position).getBannerImageUrl();
        Log.e("Base url",BASE_URL + url);
        Picasso.with(context).load(BASE_URL + url).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.brandimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, PageDetail.class);
                i.putExtra("id",activityListModels.get(position).getID());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activityListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView brandname;
        ImageView brandimage;

        public ViewHolder(View itemView) {
            super(itemView);

            brandname = itemView.findViewById(R.id.brandname);
            brandimage = itemView.findViewById(R.id.brandimage);


        }
    }
}
