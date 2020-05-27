package com.example.admin.bibleapp.ProgressViewHolder;

import android.view.View;
import android.widget.ProgressBar;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.RecyclerViewHolders.RecyclerViewHolders;

public class ProgressViewHolder extends RecyclerViewHolders {
    public ProgressBar progressBar;

    public ProgressViewHolder(View itemView) {
        super(itemView);
        this.progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
    }
}
