package com.example.admin.bibleapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Activity.bibleContentSelect;

public class VerseAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private String Verseid;
    private String bookid;
    private int chap;
    private Context mContext;
    private final String[] value;

    public VerseAdapter(Context mContext, String[] value) {
        this.mContext = mContext;
        this.value = value;
        inflater = (LayoutInflater) mContext.getSystemService("layout_inflater");
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
                SharedPreferences sharedPreferences = VerseAdapter.this.mContext.getSharedPreferences("MyPrefs", 0);
                String bookid = sharedPreferences.getString("bookid", null);
                String chapid = sharedPreferences.getString("chapterid", null);
                String tableChapid = sharedPreferences.getString("TabChapid", null);
                String activi = sharedPreferences.getString("activity", null);
                VerseAdapter.this.Verseid = VerseAdapter.this.value[position];
                if (VerseAdapter.this.value[position].equals("1") || VerseAdapter.this.value[position].equals("2") || VerseAdapter.this.value[position].equals("3") || VerseAdapter.this.value[position].equals("4") || VerseAdapter.this.value[position].equals("5") || VerseAdapter.this.value[position].equals("6") || VerseAdapter.this.value[position].equals("7") || VerseAdapter.this.value[position].equals("8") || VerseAdapter.this.value[position].equals("9") || VerseAdapter.this.value[position].equals("0")) {
                    VerseAdapter.this.Verseid = "0" + VerseAdapter.this.Verseid;
                    ((bibleContentSelect) VerseAdapter.this.mContext).content(bookid, chapid, VerseAdapter.this.Verseid);
                    return;
                }
                ((bibleContentSelect) VerseAdapter.this.mContext).content(bookid, chapid, VerseAdapter.this.Verseid);
            }
        });
        return grid;
    }
}
