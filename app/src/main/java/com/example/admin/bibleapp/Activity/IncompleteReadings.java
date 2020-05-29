package com.example.admin.bibleapp.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.Adapter.PrayerAdapter;
import com.example.admin.bibleapp.Adapter.ReadingAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Views.OnBlurCompleteListener;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.popupWindow.PlanSelection1;
import com.example.admin.bibleapp.popupWindow.bibleselection1;
import com.example.admin.bibleapp.popupWindow.popFlashcard;
import com.example.admin.bibleapp.popupWindow.popFontSize;
import com.example.admin.bibleapp.popupWindow.popReadHistory;
import com.example.admin.bibleapp.popupWindow.popReset;
import com.example.admin.bibleapp.popupWindow.popSearch;
import com.example.admin.bibleapp.popupWindow.pop_activity_about;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IncompleteReadings extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    ReadingAdapter Adapter;
    String Bookname;
    String bible;
    DBhelper dbhelper;
    DrawerLayout drawer;
    Dialog pop_search_word;
    String initialdate;
    RecyclerView.LayoutManager mLayoutManager;
    ProgressDialog pd;
    PrayerAdapter mAdapter;
    RecyclerView rvIncomplete;
    SharedPreferences sharedPreferences;
    String state;
    String table_plan;

    class C04861 implements OnBlurCompleteListener {
        C04861() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(IncompleteReadings.this, pop_activity_about.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            IncompleteReadings.this.startActivity(intent);
        }
    }

    class C04872 implements OnBlurCompleteListener {
        C04872() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(IncompleteReadings.this, popReset.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            IncompleteReadings.this.startActivity(intent);
        }
    }

    class C04883 implements OnBlurCompleteListener {
        C04883() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(IncompleteReadings.this, popSearch.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            IncompleteReadings.this.startActivity(intent);
        }
    }

    class C04894 implements OnBlurCompleteListener {
        C04894() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(IncompleteReadings.this, popFontSize.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            IncompleteReadings.this.startActivity(intent);
        }
    }

    class C04905 implements OnBlurCompleteListener {
        C04905() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(IncompleteReadings.this, bibleselection1.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            IncompleteReadings.this.startActivity(intent);
        }
    }

    class C04916 implements OnBlurCompleteListener {
        C04916() {
        }

        public void onBlurComplete() {
            IncompleteReadings.this.sharedPreferences = IncompleteReadings.this.getSharedPreferences("MyPrefs", 0);
            IncompleteReadings.this.bible = IncompleteReadings.this.sharedPreferences.getString("bible", null);
            Intent intent = new Intent(IncompleteReadings.this, PlanSelection1.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            IncompleteReadings.this.startActivity(intent);
        }
    }

    class C04948 implements OnBlurCompleteListener {
        C04948() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(IncompleteReadings.this, MonthlyCal.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            IncompleteReadings.this.startActivity(intent);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calendar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.monthlydate) {
            Intent intent = new Intent(IncompleteReadings.this, MonthlyCal.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            IncompleteReadings.this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_incomplete_readings);
        this.dbhelper = new DBhelper(this);
        this.state = Environment.getExternalStorageState();
       // this.pd = new ProgressDialog(this);
        //this.pd.setCancelable(false);
        //this.pd.setProgressStyle(16973854);
        this.dbhelper = new DBhelper(this, getFilesDir().getAbsolutePath());
      Toolbar toolbar = (Toolbar) findViewById(R.id.settoolbar);
      setSupportActionBar(toolbar);
        TextView tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("NYI Nexus");
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        this.bible = this.sharedPreferences.getString("bible", null);
        this.table_plan = this.sharedPreferences.getString("tablePlan", null);
        this.initialdate = this.dbhelper.getInitialdate(this.table_plan);
        this.rvIncomplete = (RecyclerView) findViewById(R.id.rv_incomplete);
        this.mLayoutManager = new LinearLayoutManager(this);
        this.rvIncomplete.setLayoutManager(this.mLayoutManager);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//       ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false); //disable "hamburger to arrow" drawable
        toggle.setHomeAsUpIndicator(R.drawable.ham6);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("dd MMM");
        SimpleDateFormat month = new SimpleDateFormat("MMM");
        String CurrentDate = date.format(cal.getTime());
        String CurrentMonth = month.format(cal.getTime());
        this.Adapter = new ReadingAdapter(this.dbhelper.getReadings(this.table_plan, this.dbhelper.getInitialdateId(this.table_plan, this.initialdate), this.dbhelper.getInitialdateId(this.table_plan, CurrentDate)), R.layout.row_incomplete, this);
        this.rvIncomplete.setAdapter(this.Adapter);
        this.Adapter.notifyDataSetChanged();
        this.rvIncomplete.setVisibility(0);
    }

    @SuppressLint("WrongConstant")
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_about) {
            Intent intent = new Intent(IncompleteReadings.this, pop_activity_about.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        else if (id == R.id.nav_mynotes) {
            Intent intent = new Intent(this, Notes.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.nav_flashcard) {

            Intent intent = new Intent(this, popFlashcard.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            this.startActivity(intent);

        } else if (id == R.id.nav_search) {
           search_pop();
        } else if (id == R.id.nav_readhistory) {

            Intent intent = new Intent(this, popReadHistory.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            this.startActivity(intent);
        }
        else if (id == R.id.nav_settings) {
            Intent intent = new Intent(IncompleteReadings.this, Settings.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    public void onResume() {
        super.onResume();
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        this.bible = this.sharedPreferences.getString("bible", null);
        this.table_plan = this.sharedPreferences.getString("tablePlan", null);
        this.initialdate = this.sharedPreferences.getString("initialdate", null);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("dd MMM");
        SimpleDateFormat month = new SimpleDateFormat("MMM");
        String CurrentDate = date.format(cal.getTime());
        String CurrentMonth = month.format(cal.getTime());
        this.Adapter = new ReadingAdapter(this.dbhelper.getReadings(this.table_plan, this.dbhelper.getInitialdateId(this.table_plan, this.initialdate), this.dbhelper.getInitialdateId(this.table_plan, CurrentDate)), R.layout.row_incomplete, this);
        this.rvIncomplete.setAdapter(this.Adapter);
        this.Adapter.notifyDataSetChanged();
        this.rvIncomplete.setVisibility(0);
    }


    public String getdata(String table, String date, String col) {
        return this.dbhelper.getContentbydate(table, date, col);
    }

    public Integer getread(String table, String date, String col) {
        return Integer.valueOf(this.dbhelper.getContentbyread(table, date, col).intValue());
    }
    private void search_pop() {
        if (this.pop_search_word == null) {
            this.pop_search_word = new Dialog(IncompleteReadings.this);
            this.pop_search_word.requestWindowFeature(1);
            this.pop_search_word.setTitle("Search Option");
            this.pop_search_word.setCanceledOnTouchOutside(true);
            this.pop_search_word.setCancelable(true);
            this.pop_search_word.setContentView(R.layout.popup_search);
            final EditText edtSearchWord = (EditText) this.pop_search_word.findViewById(R.id.edt_search_word);
            Button btnSumbit = (Button) this.pop_search_word.findViewById(R.id.btn_submit);
            Button btnCancel = (Button) this.pop_search_word.findViewById(R.id.btn_cancel);
            pop_search_word.show( );
            // ImageView ivclose = (ImageView) this.pop_search_word.findViewById(R.id.iv_close);

//            ivclose.setOnClickListener(new SampleFragment.C02636( ));
            btnSumbit.setOnClickListener(new View.OnClickListener( ) {
                public void onClick(View v) {
                    String searchedWord = edtSearchWord.getText( ).toString( );
                    if (searchedWord == " ") {
                        edtSearchWord.setError("Enter a word to search");
                        return;
                    }
                    Intent i = new Intent(getApplicationContext( ), SearchList.class);
                    i.putExtra("searchword", searchedWord);
                    startActivity(i);
                }
            });
            edtSearchWord.setOnEditorActionListener(new TextView.OnEditorActionListener( ) {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (event.getKeyCode( ) == 66) {
                        String searchedWord = edtSearchWord.getText( ).toString( );
                        if (searchedWord == " ") {
                            edtSearchWord.setError("Enter a word to search");
                        } else {
                            Intent i = new Intent(getApplicationContext( ), SearchList.class);
                            i.putExtra("searchword", searchedWord);
                            startActivity(i);
                        }
                    }
                    return true;
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View view) {
                    pop_search_word.dismiss( );
                }
            });

        } else if (this.pop_search_word.isShowing( )) {
            this.pop_search_word.dismiss( );
            this.pop_search_word = null;
            search_pop();
        } else {
            this.pop_search_word = null;
            search_pop();
        }
    }
}
