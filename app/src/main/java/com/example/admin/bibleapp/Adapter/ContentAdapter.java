package com.example.admin.bibleapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Activity.FirstPage;
import com.example.admin.bibleapp.Model.ContentModel;
import com.example.admin.bibleapp.ObservableWebView;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    private List<ContentModel> ContentModel;
    private Context con;
    private int itemLayout;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ObservableWebView verse;
        TextView verseNo;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            this.verse = (ObservableWebView) itemView.findViewById(R.id.tv_verse);
           itemView.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (ContentAdapter.this.ContentModel.size() != 0) {
                       ContentModel mode = (ContentModel) ContentAdapter.this.ContentModel.get(ViewHolder.this.getAdapterPosition());
                       ContentAdapter.this.con.startActivity(new Intent(ContentAdapter.this.con, FirstPage.class));
                   }
               }
           });
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_content, parent, false));
    }

    public ContentAdapter(List<ContentModel> ContentModel, int itemLayout, Context con) {
        this.ContentModel = ContentModel;
        this.itemLayout = itemLayout;
        this.con = con;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.verse.loadDataWithBaseURL("", ((ContentModel) this.ContentModel.get(position)).getVerse(), "text/html", "utf-8", null);
    }

    public ContentModel getItem(int position) {
        return (ContentModel) this.ContentModel.get(position);
    }

    public int getItemCount() {
        return this.ContentModel.size();
    }
}
