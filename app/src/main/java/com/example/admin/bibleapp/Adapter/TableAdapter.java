package com.example.admin.bibleapp.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Model.Table;
import com.example.admin.bibleapp.Views.OnLoadMoreListener;
import com.example.admin.bibleapp.ProgressViewHolder.ProgressViewHolder;
import com.example.admin.bibleapp.RecyclerViewHolders.RecyclerViewHolders;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    private List<Table> Table;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private Context con;
    boolean isLoading = false;
    boolean isMoreDataAvailable = true;
    private int itemLayout;
    private int lastVisibleItem;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;
    RecyclerView recyclerView;
    private int totalItemCount;
    private int visibleThreshold = 5;

    public TableAdapter(List<Table> Table, RecyclerView recyclerView, Context con) {
        this.Table = Table;
        this.recyclerView = recyclerView;
        this.itemLayout = this.itemLayout;
        this.con = con;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    TableAdapter.this.totalItemCount = linearLayoutManager.getItemCount();
                    Log.e("Item", TableAdapter.this.totalItemCount + "");
                    TableAdapter.this.lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    Log.e("LastVisible", TableAdapter.this.lastVisibleItem + "");
                    if (TableAdapter.this.loading || TableAdapter.this.totalItemCount > TableAdapter.this.lastVisibleItem + TableAdapter.this.visibleThreshold) {
                        Log.e("Vis+vis+else", (TableAdapter.this.lastVisibleItem + TableAdapter.this.visibleThreshold) + " " + TableAdapter.this.totalItemCount);
                        return;
                    }
                    Log.e("Vis+vis+if", (TableAdapter.this.lastVisibleItem + TableAdapter.this.visibleThreshold) + " " + TableAdapter.this.totalItemCount);
                    if (TableAdapter.this.onLoadMoreListener != null) {
                        TableAdapter.this.onLoadMoreListener.onLoadMore();
                        Log.e("onload", "load");
                    }
                    TableAdapter.this.loading = true;
                }
            });
        }
    }

    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new RecyclerViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_table, parent, false));
        }
        return new ProgressViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_progress, parent, false));
    }

    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        Table model = (Table) this.Table.get(position);
        Typeface tf = Typeface.createFromAsset(this.con.getAssets(), "fonts/Mon.ttf");
        if (holder instanceof RecyclerViewHolders) {
            holder.tv.setText(Html.fromHtml(model.getTable()));
            holder.tv.setTypeface(tf);
            return;
        }
        ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoaded() {
        this.loading = false;
    }

    public int getItemViewType(int position) {
        return this.Table.get(position) != null ? 1 : 0;
    }

    public int getItemCount() {
        return this.Table.size();
    }

    public Table getItem(int position) {
        return (Table) this.Table.get(position);
    }
}
