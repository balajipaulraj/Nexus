package com.example.admin.bibleapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Model.NewBookModel;
import com.example.admin.bibleapp.Activity.bibleContentSelect;
import java.util.List;


public class BookAdapter extends BaseAdapter {
    private Context con;
    String content;
    private int itemLayout;
    private List<NewBookModel> newBook;
    String[] str;

    public BookAdapter(List<NewBookModel> newBook, int itemLayout, Context con) {
        this.newBook = newBook;
        this.itemLayout = itemLayout;
        this.con = con;
    }

    public int getCount() {
        Log.e("Book size", this.newBook.size() + "");
        return this.newBook.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View grid;
        String activi = this.con.getSharedPreferences("MyPrefs", 0).getString("activity", null);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.con.getSystemService("layout_inflater");
            grid = new View(this.con);
            grid = inflater.inflate(R.layout.single_chapter_row, null);
        } else {
            grid = convertView;
        }
        final TextView textView = (TextView) grid.findViewById(R.id.tv_chap);
        textView.setText(((NewBookModel) this.newBook.get(position)).getBook());
        Log.e("newBook", ((NewBookModel) this.newBook.get(position)).getBook());
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                ((bibleContentSelect) con).Chapter(((NewBookModel) newBook.get(position)).getBook());

            }
        });
        return grid;
    }
}
