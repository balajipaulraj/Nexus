package com.example.admin.bibleapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Model.IncompleteModel;
import java.util.List;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ViewHolder> {
    private List<IncompleteModel> IncompleteModel;
    String bible;
    private Context con;
    private int itemLayout;
    SharedPreferences sharedPreferences;
    String table_plan;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView portion1;
        TextView portion2;
        TextView portion3;
        TextView portion4;
        LinearLayout total;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            this.date = (TextView) itemView.findViewById(R.id.tv_note_time);
            this.portion1 = (TextView) itemView.findViewById(R.id.tv_portion1);
            this.portion2 = (TextView) itemView.findViewById(R.id.tv_portion2);
            this.portion3 = (TextView) itemView.findViewById(R.id.tv_portion3);
            this.portion4 = (TextView) itemView.findViewById(R.id.tv_portion4);
            this.total = (LinearLayout) itemView.findViewById(R.id.total_layout);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_incomplete, parent, false));
    }

    public ReadingAdapter(List<IncompleteModel> IncompleteModel, int itemLayout, Context con) {
        this.IncompleteModel = IncompleteModel;
        this.itemLayout = itemLayout;
        this.con = con;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        this.sharedPreferences = this.con.getSharedPreferences("MyPrefs", 0);
        this.bible = this.sharedPreferences.getString("bible", null);
        this.table_plan = this.sharedPreferences.getString("tablePlan", null);
        IncompleteModel model = (IncompleteModel) this.IncompleteModel.get(position);
        String date = model.getDate();
        int oldread = model.getPor1();
        int psalmread = model.getPor2();
        int newread = model.getPor3();
        int fourthread = model.getPor4();
        String str_date = model.getDate();
        String str_portion1 = model.getPortion1();
        String str_portion2 = model.getPortion2();
        String str_portion3 = model.getPortion3();
        String str_portion4 = model.getPortion4();
        if (oldread != 0 || str_portion1 == null) {
            holder.portion1.setVisibility(View.GONE);
        } else {
            holder.portion1.setText(model.getPortion1());
            holder.portion1.setVisibility(0);
        }
        if (psalmread != 0 || str_portion2 == null) {
            holder.portion2.setVisibility(View.GONE);
        } else {
            holder.portion2.setText(model.getPortion2());
            holder.portion2.setVisibility(0);
        }
        if (newread != 0 || str_portion3 == null) {
            holder.portion3.setVisibility(View.GONE);
        } else {
            holder.portion3.setText(model.getPortion3());
            holder.portion3.setVisibility(0);
        }
        if (fourthread != 0 || str_portion4 == null) {
            holder.portion4.setVisibility(View.GONE);
        } else {
            holder.portion4.setText(model.getPortion4());
            holder.portion4.setVisibility(0);
        }
        if (oldread != 1 || !str_portion2.equals("") || !str_portion3.equals("") || !str_portion4.equals("")) {
            holder.date.setText(model.getDate());
        } else if (oldread != 1 || psalmread != 1 || !str_portion3.equals("") || !str_portion4.equals("")) {
            holder.date.setText(model.getDate());
        } else if (oldread == 1 && newread == 1 && psalmread == 1 && fourthread == 1) {
            holder.date.setVisibility(View.GONE);
            holder.total.setVisibility(View.GONE);
        } else {
            holder.date.setText(model.getDate());
        }
    }

    public int getItemCount() {
        return this.IncompleteModel.size();
    }
}
