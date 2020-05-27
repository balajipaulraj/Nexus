package com.example.admin.bibleapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.SearchModel;
import com.example.admin.bibleapp.ObservableWebView;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<SearchModel> SearchModel;
    private Context con;
    DBhelper dbhelper;
    private int itemLayout;
    private String searchword;
    SharedPreferences sharedPreferences;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ObservableWebView verse;
        TextView verseNo;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            this.verseNo = (TextView) itemView.findViewById(R.id.tv_verseno);
            this.verse = (ObservableWebView) itemView.findViewById(R.id.tv_verse);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_search, parent, false));
    }

    public SearchAdapter(List<SearchModel> SearchModel, int itemLayout, Context con, String search) {
        this.SearchModel = SearchModel;
        this.itemLayout = itemLayout;
        this.con = con;
        this.dbhelper = new DBhelper(con.getApplicationContext(), con.getFilesDir().getAbsolutePath());
        this.searchword = search;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchModel model = (SearchModel) this.SearchModel.get(position);
        holder.verseNo.setText(this.dbhelper.getBookName(model.getBookid()) + "\n\n" + model.getChapterid() + ":" + model.getVerseid());
        holder.verse.loadDataWithBaseURL("", model.getVerse().replaceAll("(?i)(" + this.searchword + ")", "<mark>$1</mark>"), "text/html", "utf-8", null);
    }

    public SearchModel getItem(int position) {
        return (SearchModel) this.SearchModel.get(position);
    }

    public int getItemCount() {
        return this.SearchModel.size();
    }
}
