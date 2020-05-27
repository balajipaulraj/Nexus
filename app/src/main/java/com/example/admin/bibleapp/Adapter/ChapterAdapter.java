package com.example.admin.bibleapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Activity.bibleContentSelect;

public class ChapterAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    VerseAdapter adapter;
    private Context mContext;
    private final String[] value;

    public ChapterAdapter(Context c, String[] value) {
        this.mContext = c;
        this.value = value;
        inflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.value.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View grid;
        String activi = this.mContext.getSharedPreferences("MyPrefs", 0).getString("activity", null);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
            grid = new View(this.mContext);
            grid = inflater.inflate(R.layout.single_chapter_row, null);
        } else {
            grid = convertView;
        }
        TextView textView = (TextView) grid.findViewById(R.id.tv_chap);
        textView.setText(this.value[position]);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (ChapterAdapter.this.value[position].equals("1") || ChapterAdapter.this.value[position].equals("2") || ChapterAdapter.this.value[position].equals("3") || ChapterAdapter.this.value[position].equals("4") || ChapterAdapter.this.value[position].equals("5") || ChapterAdapter.this.value[position].equals("6") || ChapterAdapter.this.value[position].equals("7") || ChapterAdapter.this.value[position].equals("8") || ChapterAdapter.this.value[position].equals("9") || ChapterAdapter.this.value[position].equals("0")) {
                    ((bibleContentSelect) ChapterAdapter.this.mContext).Verse("0" + ChapterAdapter.this.value[position], ChapterAdapter.this.value[position]);
                } else {
                    ((bibleContentSelect) ChapterAdapter.this.mContext).Verse(ChapterAdapter.this.value[position], ChapterAdapter.this.value[position]);
                }
            }
        });
        return grid;
    }
}
