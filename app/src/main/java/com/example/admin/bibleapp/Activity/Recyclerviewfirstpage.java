package com.example.admin.bibleapp.Activity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.Adapter.FirstAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.R;

public class Recyclerviewfirstpage extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    FirstAdapter Adapter;
    String bible;
    String data = "";
    String lastSeenBook;
    String lastSeenChap;
    RecyclerView.LayoutManager mLayoutManager;
    Dialog pop_search_word;
    RecyclerView rvSearch;
    SharedPreferences sharedPreferences;
    TextView tvNoItem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_recyclerviewfirstpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);
        TextView tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("NYI Nexus");
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        this.bible = this.sharedPreferences.getString("bible", null);
        this.lastSeenBook = this.sharedPreferences.getString("intentbookid", null);
        this.lastSeenChap = this.sharedPreferences.getString("intentchapid", null);
        DBhelper dBhelper = new DBhelper(this, getFilesDir().getAbsolutePath());
        this.rvSearch = (RecyclerView) findViewById(R.id.rv_recycle);
        this.mLayoutManager = new LinearLayoutManager(this);
        this.rvSearch.setLayoutManager(this.mLayoutManager);
        this.rvSearch.setVisibility(0);
        this.Adapter = new FirstAdapter(dBhelper.getFirstModel(this.bible, this.lastSeenBook, this.lastSeenChap), R.layout.single_search, this);
        this.rvSearch.setAdapter(this.Adapter);
    }

    public void onBackPressed() {
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
