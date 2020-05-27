package com.example.admin.bibleapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.Adapter.SearchAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.popupWindow.popSearch;

public class SearchList extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    SearchAdapter Adapter;
    String bible;
    String data = "";
    RecyclerView.LayoutManager mLayoutManager;
    Dialog pop_search_word;
    RecyclerView rvSearch;
    String searchword;
    SharedPreferences sharedPreferences;
    TextView tvNoItem;

    class C02471 implements OnClickListener {
        C02471() {
        }

        public void onClick(View v) {
            SearchList.this.pop_search_word.dismiss();
        }
    }

    class C02504 implements OnClickListener {
        C02504() {
        }

        public void onClick(View v) {
            SearchList.this.pop_search_word.dismiss();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_search_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.settoolbar);
       setSupportActionBar(toolbar);
        TextView tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("Search");
        this.searchword = getIntent().getExtras().getString("searchword", null);
        DBhelper dBhelper = new DBhelper(this, getFilesDir().getAbsolutePath());
        this.rvSearch = (RecyclerView) findViewById(R.id.rv_search);
        this.tvNoItem = (TextView) findViewById(R.id.tv_no_items);
        this.mLayoutManager = new LinearLayoutManager(this);
        this.rvSearch.setLayoutManager(this.mLayoutManager);
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        this.bible = this.sharedPreferences.getString("bible", null);
        this.data += dBhelper.getSearch(this.bible, this.searchword);
        if (this.data.equals("[]")) {
            this.tvNoItem.setVisibility(0);
            this.rvSearch.setVisibility(View.GONE);
            return;
        }
        this.tvNoItem.setVisibility(View.GONE);
        this.rvSearch.setVisibility(0);
        this.Adapter = new SearchAdapter(dBhelper.getSearch(this.bible, this.searchword), R.layout.single_search, this, this.searchword);
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
        if (item.getItemId() == R.id.searchword) {
            startActivity(new Intent(this, popSearch.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSearch() {
        if (this.pop_search_word == null) {
            this.pop_search_word = new Dialog(this);
            this.pop_search_word.requestWindowFeature(1);
            this.pop_search_word.setTitle("Search Option");
            this.pop_search_word.setCanceledOnTouchOutside(true);
            this.pop_search_word.setCancelable(true);
            this.pop_search_word.setContentView(R.layout.popup_search);
            final EditText edtSearchWord = (EditText) this.pop_search_word.findViewById(R.id.edt_search_word);
            Button btnSumbit = (Button) this.pop_search_word.findViewById(R.id.btn_submit);
            Button btnCancel = (Button) this.pop_search_word.findViewById(R.id.btn_cancel);
            ((ImageView) this.pop_search_word.findViewById(R.id.iv_close)).setOnClickListener(new C02471());
            btnSumbit.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    String searchedWord = edtSearchWord.getText().toString();
                    if (searchedWord == " ") {
                        edtSearchWord.setError("Enter your Search word");
                        return;
                    }
                    Intent i = new Intent(SearchList.this.getApplicationContext(), SearchList.class);
                    i.putExtra("searchword", searchedWord);
                    SearchList.this.startActivity(i);
                }
            });
            edtSearchWord.setOnEditorActionListener(new OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (event.getKeyCode() == 66) {
                        String searchedWord = edtSearchWord.getText().toString();
                        if (searchedWord == " ") {
                            edtSearchWord.setError("Enter your Search word");
                        } else {
                            Intent i = new Intent(SearchList.this.getApplicationContext(), SearchList.class);
                            i.putExtra("searchword", searchedWord);
                            SearchList.this.startActivity(i);
                        }
                    }
                    return true;
                }
            });
            btnCancel.setOnClickListener(new C02504());
            this.pop_search_word.show();
        } else if (this.pop_search_word.isShowing()) {
            this.pop_search_word.dismiss();
            this.pop_search_word = null;
            showSearch();
        } else {
            this.pop_search_word = null;
            showSearch();
        }
    }
}
