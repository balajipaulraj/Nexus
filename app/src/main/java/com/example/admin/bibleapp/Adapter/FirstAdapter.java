package com.example.admin.bibleapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Model.FirstModel;
import java.util.List;

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.ViewHolder> {
    private List<FirstModel> FirstModel;
    private Context con;
    float fontSize;
    private int itemLayout;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView verse;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            this.verse = (TextView) itemView.findViewById(R.id.verse);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_firstpage, parent, false));
    }

    public FirstAdapter(List<FirstModel> FirstModel, int itemLayout, Context con) {
        this.FirstModel = FirstModel;
        this.itemLayout = itemLayout;
        this.con = con;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        FirstModel model = (FirstModel) this.FirstModel.get(position);
        holder.verse.setText(model.getVerse());
        Log.e("Dtata", model.getVerse());
    }

    public FirstModel getItem(int position) {
        return (FirstModel) this.FirstModel.get(position);
    }

    public int getItemCount() {
        return this.FirstModel.size();
    }
}
