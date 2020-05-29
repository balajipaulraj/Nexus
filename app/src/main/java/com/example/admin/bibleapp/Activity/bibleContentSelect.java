package com.example.admin.bibleapp.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.admin.bibleapp.Adapter.BookAdapter;
import com.example.admin.bibleapp.Adapter.ChapterAdapter;
import com.example.admin.bibleapp.Adapter.DrawerItemCustomAdapter;
import com.example.admin.bibleapp.Adapter.PrayerAdapter;
import com.example.admin.bibleapp.Adapter.VerseAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.user;
import com.example.admin.bibleapp.Views.MyGridView;
import com.example.admin.bibleapp.Views.OnBlurCompleteListener;
import com.example.admin.bibleapp.Views.OnSwipeTouchListener2;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.popupWindow.popFlashcard;
import com.example.admin.bibleapp.popupWindow.popReadHistory;
import com.example.admin.bibleapp.popupWindow.popSearch;
import com.example.admin.bibleapp.popupWindow.pop_activity_about;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class bibleContentSelect extends AppCompatActivity implements GestureDetector.OnGestureListener {
    public static final String MyPREFERENCES = "MyPrefs";
    private GestureDetector productGestureDetector;
    VerseAdapter adapt;
    BookAdapter adapter;
    TextView backk;
    TextView backBook;
    TextView backChap;
    String bible;
    String bookid;
    ScrollView chapScroll;
    float downX, upX;
    Button test;
    String content = "";
    Cursor dateContent;
    DBhelper dbhelper;
    TabLayout tab_lay;
    boolean exit = false;
    boolean flag = true;
    TextView goBook;
    TextView goChap;
    MyGridView grid_chap;
    GridView grid_new_book;
    float x1, x2;
    GridView grid_old_book;
    Toolbar toolbar;
    private ListView mDrawerList;

    LinearLayout swipe;
    MyGridView grid_vers;
    TextView newTestament;
    int noofchap;
    TextView oldTestament;
    String planby;
    TextView popBookNo;
    boolean swapped;
    TextView popChapNo;
    TextView popVerseNo;
    RelativeLayout swipe_new;
    LinearLayout oldtest;
    BottomBar bottomBar;
    ScrollView scrollfull;
    SharedPreferences sharedPreferences;
    String[] str_ver;
    List<String> stringArrayList = new ArrayList();
    String table_plan;
    ScrollView verseScroll;
    PrayerAdapter mAdapter;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getRawY() - e1.getRawY();
            float diffX = e2.getRawX() - e1.getRawX();
            if ((Math.abs(diffX) - Math.abs(diffY)) > SWIPE_THRESHOLD) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        swipeToLeft();
                    } else {
                        swipeToRight();
                    }
                }
            }
        } catch (Exception e) {

        }
        return result;
    }

    class C02921 implements OnClickListener {
        C02921() {
        }

        public void onClick(View v) {
            oldTestament.setVisibility(View.VISIBLE);
            newTestament.setVisibility(View.VISIBLE);
            grid_old_book.setVisibility(View.VISIBLE);
            grid_new_book.setVisibility(View.VISIBLE);
            grid_chap.setVisibility(View.GONE);
            backk.setVisibility(View.VISIBLE);
            goBook.setVisibility(View.GONE);
            goChap.setVisibility(View.GONE);
            backBook.setVisibility(View.GONE);
            backChap.setVisibility(View.GONE);
            grid_vers.setVisibility(View.GONE);
            popBookNo.setText("Book\n Gen");
            popChapNo.setText("Chapter\n01");

        }
    }

    class C02932 implements OnClickListener {
        C02932() {
        }

        public void onClick(View v) {
            oldTestament.setVisibility(View.GONE);
            newTestament.setVisibility(View.GONE);
            grid_old_book.setVisibility(View.GONE);
            grid_new_book.setVisibility(View.GONE);
            grid_chap.setVisibility(View.VISIBLE);
            backk.setVisibility(View.GONE);
            goBook.setVisibility(View.VISIBLE);
            goChap.setVisibility(View.GONE);
            backBook.setVisibility(View.VISIBLE);
            backChap.setVisibility(View.GONE);
            grid_vers.setVisibility(View.GONE);
            popChapNo.setText("Chapter\n01");
        }
    }

    class C02943 implements OnClickListener {
        C02943() {
        }

        public void onClick(View v) {
            finish();
        }
    }

    class C02965 implements OnClickListener {
        C02965() {
        }

        public void onClick(View v) {
            oldTestament.setVisibility(View.GONE);
            newTestament.setVisibility(View.GONE);
            grid_old_book.setVisibility(View.GONE);
            grid_new_book.setVisibility(View.GONE);
            grid_chap.setVisibility(View.VISIBLE);
            backk.setVisibility(View.GONE);
            goBook.setVisibility(View.VISIBLE);
            goChap.setVisibility(View.GONE);
            backBook.setVisibility(View.VISIBLE);
            backChap.setVisibility(View.GONE);
            grid_vers.setVisibility(View.GONE);
            popChapNo.setText("Chapter\n01");
        }
    }

    class C02976 implements OnClickListener {
        C02976() {
        }

        public void onClick(View v) {
            String con = "";
            Cursor value = dbhelper.getBookContent(bible, bookid);
            if (value != null) {
                if (value.moveToFirst()) {
                    do {
                        con = con + value.getString(value.getColumnIndex("verse"));
                        Log.e("content by id", content);
                    } while (value.moveToNext());
                }
                value.close();
                Intent i = new Intent(getApplicationContext(), FirstPage.class);
                sharedPreferences = getSharedPreferences("MyPrefs", 0);
                Editor edit_gen = sharedPreferences.edit();
                edit_gen.putString("intentbookid", bookid);
                edit_gen.putString("intentchapid", "");
                edit_gen.putString("intentverseid", "");
                edit_gen.commit();
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        }
    }

    class C02987 implements OnClickListener {
        C02987() {
        }

        public void onClick(View v) {
            oldTestament.setVisibility(View.VISIBLE);
            newTestament.setVisibility(View.VISIBLE);
            grid_old_book.setVisibility(View.VISIBLE);
            grid_new_book.setVisibility(View.VISIBLE);
            grid_chap.setVisibility(View.GONE);
            backk.setVisibility(View.VISIBLE);
            goBook.setVisibility(View.GONE);
            goChap.setVisibility(View.GONE);
            backBook.setVisibility(View.GONE);
            backChap.setVisibility(View.GONE);
            grid_vers.setVisibility(View.GONE);
            popBookNo.setText("Book\nGen");
            popChapNo.setText("Chapter\n01");
        }
    }

    class C05078 implements OnBlurCompleteListener {
        C05078() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(getApplicationContext(), bibleContentSelect.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    class C05089 implements OnBlurCompleteListener {
        C05089() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(getApplicationContext(), MonthlyCal.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    class C04959 implements OnBlurCompleteListener {
        C04959() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(bibleContentSelect.this, IncompleteReadings.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            bibleContentSelect.this.startActivity(intent);
        }
    }

    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_select);
        dbhelper = new DBhelper(this, getFilesDir().getAbsolutePath());
        try {
            dbhelper.prepareDatabase();
        } catch (IOException e) {
        }
        toolbar = (Toolbar) findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);
        TextView tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("NYI Nexus");
        sharedPreferences = getSharedPreferences("MyPrefs", 0);
        int userId = sharedPreferences.getInt("userid", 0);
        bible = sharedPreferences.getString("bible", null);
        table_plan = sharedPreferences.getString("tablePlan", null);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        oldtest = (LinearLayout) findViewById(R.id.oldtest);
        planby = sharedPreferences.getString("planby", null);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        List<String> menuItems = dbhelper.getMenu();
        DrawerItemCustomAdapter dradapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, menuItems);
        mDrawerList.setAdapter(dradapter);
        final ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false); //disable "hamburger to arrow" drawable
        toggle.setHomeAsUpIndicator(R.drawable.ham6);
        toggle.setToolbarNavigationClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        productGestureDetector = new GestureDetector(bibleContentSelect.this);

        swipe_new = (RelativeLayout) findViewById(R.id.swipe_test);
        grid_old_book = (GridView) findViewById(R.id.grid_old_book);
        grid_new_book = (GridView) findViewById(R.id.grid_new_book);
        chapScroll = (ScrollView) findViewById(R.id.chap_scroll);
        scrollfull = (ScrollView) findViewById(R.id.scrollfull);
        verseScroll = (ScrollView) findViewById(R.id.verse_scroll);
        adapter = new BookAdapter(dbhelper.getOldBook(), R.layout.single_chapter_row, this);
        grid_old_book.setAdapter(adapter);
        adapter = new BookAdapter(dbhelper.getNewBook(), R.layout.single_chapter_row, this);
        grid_new_book.setAdapter(adapter);
        grid_old_book.setVisibility(View.VISIBLE);
        grid_new_book.setVisibility(View.VISIBLE);
        popBookNo = (TextView) findViewById(R.id.tvBook);
        oldTestament = (TextView) findViewById(R.id.tv_old_testament);
        backk = (TextView) findViewById(R.id.back);
        goBook = (TextView) findViewById(R.id.bookgo);
        backBook = (TextView) findViewById(R.id.bookback);
        tab_lay = (TabLayout) findViewById(R.id.tab_book);
        tab_lay.addTab(tab_lay.newTab().setText("Book"));
        tab_lay.addTab(tab_lay.newTab().setText("Chapter"));
        tab_lay.addTab(tab_lay.newTab().setText("Verse"));
        tab_lay.setTabGravity(TabLayout.GRAVITY_FILL);
        tab_lay.setTabMode(TabLayout.MODE_FIXED);
        backChap = (TextView) findViewById(R.id.chapback);
        goChap = (TextView) findViewById(R.id.chapgo);
        newTestament = (TextView) findViewById(R.id.tv_newtestament);
        oldTestament.setVisibility(View.VISIBLE);
        newTestament.setVisibility(View.VISIBLE);
        backk.setVisibility(View.VISIBLE);
        backBook.setVisibility(View.GONE);
        backChap.setVisibility(View.GONE);
        goBook.setVisibility(View.VISIBLE);
        goChap.setVisibility(View.GONE);
//      scrollfull.pageScroll(View.VISIBLE);
        popChapNo = (TextView) findViewById(R.id.tvChapter);
        popVerseNo = (TextView) findViewById(R.id.tvVerse);
        grid_chap = (MyGridView) findViewById(R.id.grid_chap);
        grid_vers = (MyGridView) findViewById(R.id.grid_vers);
        swipe = (LinearLayout) findViewById(R.id.swipe);
        grid_chap.setNumColumns(6);
        grid_vers.setNumColumns(6);
        grid_new_book.setNumColumns(6);
        grid_old_book.setNumColumns(6);
        grid_old_book.setVisibility(View.VISIBLE);
        grid_new_book.setVisibility(View.VISIBLE);
        grid_vers.setVisibility(View.GONE);
        grid_chap.setVisibility(View.GONE);



        bottomBar.selectTabWithId(R.id.tab_read);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                if (tabId == R.id.tab_home) {
                    Intent i = new Intent(bibleContentSelect.this, CalPage.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();
                } else if (tabId == R.id.tab_plans) {
                    Intent i = new Intent(bibleContentSelect.this, MonthlyCal.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();
                }
                else if (tabId == R.id.tab_media) {
                    Intent i = new Intent(bibleContentSelect.this, MediaActivity.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();
                }else if (tabId == R.id.tab_pray) {
                    Intent i = new Intent(bibleContentSelect.this, myprayergroup.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();
                }  else if (tabId == R.id.tab_page) {
                    Intent i = new Intent(bibleContentSelect.this, PageActivity.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();

                }
            }
        });
        tab_lay.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabpos = tab_lay.getSelectedTabPosition();
                if (tabpos == 0) {
                    oldTestament.setVisibility(View.VISIBLE);
                    newTestament.setVisibility(View.VISIBLE);
                    grid_old_book.setVisibility(View.VISIBLE);
                    grid_new_book.setVisibility(View.VISIBLE);
                    grid_chap.setVisibility(View.GONE);
                    backk.setVisibility(View.VISIBLE);
                    goBook.setVisibility(View.GONE);
                    goChap.setVisibility(View.GONE);
                    backBook.setVisibility(View.GONE);
                    backChap.setVisibility(View.GONE);
                    grid_vers.setVisibility(View.GONE);
                    popBookNo.setText("Book\nGen");
                    popChapNo.setText("Chapter\n01");
                } else if (tabpos == 1) {
                    if (tab_lay.getTabAt(0).getText().equals("Book")) {
                        tab_lay.getTabAt(0).setText("Gen");
                        Chapter("Gen");
                    }
                    oldTestament.setVisibility(View.GONE);
                    newTestament.setVisibility(View.GONE);
                    grid_old_book.setVisibility(View.GONE);
                    grid_new_book.setVisibility(View.GONE);
                    grid_chap.setVisibility(View.VISIBLE);
                    backk.setVisibility(View.GONE);
                    goBook.setVisibility(View.VISIBLE);
                    goChap.setVisibility(View.GONE);
                    backBook.setVisibility(View.VISIBLE);
                    backChap.setVisibility(View.GONE);
                    grid_vers.setVisibility(View.GONE);
                    popChapNo.setText("Chapter\n01");
                } else {
//                    if(tab_lay.getTabAt(1).getText().equals("Chapter") && tab_lay.getTabAt(0).getText().equals("Book"))
//                    {
//                        tab_lay.getTabAt(0).setText("Gen");
//                        tab_lay.getTabAt(1).setText("1");
//                        Verse("01","1");
//                    }
                    if (tab_lay.getTabAt(1).getText().equals("Chapter")) {
                        tab_lay.getTabAt(1).setText("1");
                        Verse("01", "1");
                    }
                    oldTestament.setVisibility(View.GONE);
                    newTestament.setVisibility(View.GONE);
                    grid_old_book.setVisibility(View.GONE);
                    grid_new_book.setVisibility(View.GONE);
                    grid_chap.setVisibility(View.GONE);
                    backk.setVisibility(View.GONE);
                    goBook.setVisibility(View.VISIBLE);
                    goChap.setVisibility(View.GONE);
                    backBook.setVisibility(View.VISIBLE);
                    backChap.setVisibility(View.GONE);
                    grid_vers.setVisibility(View.VISIBLE);
                    popChapNo.setText("Chapter\n01");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        swipe.setOnTouchListener(new OnSwipeTouchListener2(getApplicationContext()) {


            public void onSwipeLeft() {
                int tabpos;
                tabpos = tab_lay.getSelectedTabPosition();
                if (tabpos == 2) {
                    oldTestament.setVisibility(View.GONE);
                    newTestament.setVisibility(View.GONE);
                    grid_old_book.setVisibility(View.GONE);
                    grid_new_book.setVisibility(View.GONE);
                    grid_chap.setVisibility(View.GONE);
                    backk.setVisibility(View.GONE);
                    goBook.setVisibility(View.VISIBLE);
                    goChap.setVisibility(View.GONE);
                    backBook.setVisibility(View.VISIBLE);
                    backChap.setVisibility(View.GONE);
                    grid_vers.setVisibility(View.VISIBLE);
                    popChapNo.setText("Chapter\n01");
                    tab_lay.getTabAt(1).select();
                } else if (tabpos == 1) {
                    oldTestament.setVisibility(View.VISIBLE);
                    newTestament.setVisibility(View.VISIBLE);
                    grid_old_book.setVisibility(View.VISIBLE);
                    grid_new_book.setVisibility(View.VISIBLE);
                    grid_chap.setVisibility(View.GONE);
                    backk.setVisibility(View.VISIBLE);
                    goBook.setVisibility(View.GONE);
                    goChap.setVisibility(View.GONE);
                    backBook.setVisibility(View.GONE);
                    backChap.setVisibility(View.GONE);
                    grid_vers.setVisibility(View.GONE);
                    popBookNo.setText("Book\nGen");
                    popChapNo.setText("Chapter\n01");
                    tab_lay.getTabAt(0).select();
                }
            }

            public void onSwipeRight() {

                int tabpos;
                tabpos = tab_lay.getSelectedTabPosition();
                if (tabpos == 0) {
                    oldTestament.setVisibility(View.GONE);
                    newTestament.setVisibility(View.GONE);
                    grid_old_book.setVisibility(View.GONE);
                    grid_new_book.setVisibility(View.GONE);
                    grid_chap.setVisibility(View.VISIBLE);
                    backk.setVisibility(View.GONE);
                    goBook.setVisibility(View.VISIBLE);
                    goChap.setVisibility(View.GONE);
                    backBook.setVisibility(View.VISIBLE);
                    backChap.setVisibility(View.GONE);
                    grid_vers.setVisibility(View.GONE);
                    popChapNo.setText("Chapter\n01");
                    tab_lay.getTabAt(1).select();
                } else if (tabpos == 1) {
                    oldTestament.setVisibility(View.GONE);
                    newTestament.setVisibility(View.GONE);
                    grid_old_book.setVisibility(View.GONE);
                    grid_new_book.setVisibility(View.GONE);
                    grid_chap.setVisibility(View.GONE);
                    backk.setVisibility(View.GONE);
                    goBook.setVisibility(View.VISIBLE);
                    goChap.setVisibility(View.GONE);
                    backBook.setVisibility(View.VISIBLE);
                    backChap.setVisibility(View.GONE);
                    grid_vers.setVisibility(View.VISIBLE);
                    popChapNo.setText("Chapter\n01");
                    tab_lay.getTabAt(2).select();
                }

            }
        });


        swipe.setOnTouchListener(new OnSwipeTouchListener2(getApplicationContext()) {
            public void onSwipeRight() {

                int tabpos;
                tabpos = tab_lay.getSelectedTabPosition();
                if (tabpos == 0) {
                    oldTestament.setVisibility(View.GONE);
                    newTestament.setVisibility(View.GONE);
                    grid_old_book.setVisibility(View.GONE);
                    grid_new_book.setVisibility(View.GONE);
                    grid_chap.setVisibility(View.VISIBLE);
                    backk.setVisibility(View.GONE);
                    goBook.setVisibility(View.VISIBLE);
                    goChap.setVisibility(View.GONE);
                    backBook.setVisibility(View.VISIBLE);
                    backChap.setVisibility(View.GONE);
                    grid_vers.setVisibility(View.GONE);
                    popChapNo.setText("Chapter\n01");
                    tab_lay.getTabAt(1).select();
                } else if (tabpos == 1) {
                    oldTestament.setVisibility(View.GONE);
                    newTestament.setVisibility(View.GONE);
                    grid_old_book.setVisibility(View.GONE);
                    grid_new_book.setVisibility(View.GONE);
                    grid_chap.setVisibility(View.GONE);
                    backk.setVisibility(View.GONE);
                    goBook.setVisibility(View.VISIBLE);
                    goChap.setVisibility(View.GONE);
                    backBook.setVisibility(View.VISIBLE);
                    backChap.setVisibility(View.GONE);
                    grid_vers.setVisibility(View.VISIBLE);
                    popChapNo.setText("Chapter\n01");
                    tab_lay.getTabAt(2).select();
                }

            }

            public void onSwipeLeft() {
                int tabpos;
                tabpos = tab_lay.getSelectedTabPosition();
                if (tabpos == 2) {
                    oldTestament.setVisibility(View.GONE);
                    newTestament.setVisibility(View.GONE);
                    grid_old_book.setVisibility(View.GONE);
                    grid_new_book.setVisibility(View.GONE);
                    grid_chap.setVisibility(View.GONE);
                    backk.setVisibility(View.GONE);
                    goBook.setVisibility(View.VISIBLE);
                    goChap.setVisibility(View.GONE);
                    backBook.setVisibility(View.VISIBLE);
                    backChap.setVisibility(View.GONE);
                    grid_vers.setVisibility(View.VISIBLE);
                    popChapNo.setText("Chapter\n01");
                    tab_lay.getTabAt(1).select();
                } else if (tabpos == 1) {
                    oldTestament.setVisibility(View.VISIBLE);
                    newTestament.setVisibility(View.VISIBLE);
                    grid_old_book.setVisibility(View.VISIBLE);
                    grid_new_book.setVisibility(View.VISIBLE);
                    grid_chap.setVisibility(View.GONE);
                    backk.setVisibility(View.VISIBLE);
                    goBook.setVisibility(View.GONE);
                    goChap.setVisibility(View.GONE);
                    backBook.setVisibility(View.GONE);
                    backChap.setVisibility(View.GONE);
                    grid_vers.setVisibility(View.GONE);
                    popBookNo.setText("Book\nGen");
                    popChapNo.setText("Chapter\n01");
                    tab_lay.getTabAt(0).select();
                }

            }
        });

        popBookNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldTestament.setVisibility(View.VISIBLE);
                newTestament.setVisibility(View.VISIBLE);
                grid_old_book.setVisibility(View.VISIBLE);
                grid_new_book.setVisibility(View.VISIBLE);
                grid_chap.setVisibility(View.GONE);
                backk.setVisibility(View.VISIBLE);
                goBook.setVisibility(View.GONE);
                goChap.setVisibility(View.GONE);
                backBook.setVisibility(View.GONE);
                backChap.setVisibility(View.GONE);
                grid_vers.setVisibility(View.GONE);
                popBookNo.setText("Book\nGen");
                popChapNo.setText("Chapter\n01");
            }
        });
        popChapNo.setOnClickListener(new C02932());
        backk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        scrollfull.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch(event.getAction()){
//                    case MotionEvent.ACTION_DOWN:{
//                        downX = event.getX();}
//                    case MotionEvent.ACTION_UP:{
//                        upX = event.getX();
//
//                        float deltaX = downX - upX;
//
//                        if(Math.abs(deltaX)>0){
//                            if(deltaX>=0){
//                                swipeToRight();
//                                return true;
//                            }else{
//                                swipeToLeft();
//                                return  true;
//                            }
//                        }
//                    }
//                }
//
//                return false;
//            }
//        });

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
//                    case 2:
//                        Intent intent1 = new Intent(CalPage.this, MyPage.class);
//                        intent1.setFlags(intent1.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(intent1);
//                        break;
                    case 3:
                        Intent intent = new Intent(bibleContentSelect.this, Notes.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;

                    case 8:
                        Intent intent8 = new Intent(bibleContentSelect.this, pop_activity_about.class);
                        intent8.setFlags(intent8.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent8);
                        break;
                    case 10:
                        Intent intent10 = new Intent(bibleContentSelect.this, EventActivity.class);
                        intent10.setFlags(intent10.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(bibleContentSelect.this, UserProfile.class);
                        intent11.setFlags(intent11.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent11);
                        break;
                    case 2:
                        Intent intent2 = new Intent(bibleContentSelect.this, popFlashcard.class);
                        intent2.setFlags(intent2.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case 1:
                        search();
                        break;

                    case 4:
                        Intent intent4 = new Intent(bibleContentSelect.this, popReadHistory.class);
                        intent4.setFlags(intent4.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                    case 0:
                        Intent intent0 = new Intent(bibleContentSelect.this, Settings.class);
                        intent0.setFlags(intent0.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent0);
                        break;

                }
            }
        });
        try {
            TextView tvProfile = (TextView) findViewById(R.id.profile_name);
            TextView tvaddress = (TextView) findViewById(R.id.profile_address);
            sharedPreferences = getSharedPreferences("MyPrefs", 0);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("userdata", "");
            Type collectionType = new TypeToken<Collection<user>>() {
            }.getType();
            List<user> userSites = gson.fromJson(json, collectionType);
            tvProfile.setText(userSites.get(0).getFirstname() + " " + userSites.get(0).getLastname());
            tvaddress.setText(userSites.get(0).getAddress() + " " + userSites.get(0).getCity());
        } catch (Exception e) {

        }

    }
    private void search() {
        Dialog pop_search_word=null;
        if (pop_search_word == null) {
            pop_search_word = new Dialog(bibleContentSelect.this);
            pop_search_word.requestWindowFeature(1);
            pop_search_word.setTitle("Search Option");
            pop_search_word.setCanceledOnTouchOutside(true);
            pop_search_word.setCancelable(true);
            pop_search_word.setContentView(R.layout.popup_search);
            final EditText edtSearchWord = (EditText) pop_search_word.findViewById(R.id.edt_search_word);
            Button btnSumbit = (Button) pop_search_word.findViewById(R.id.btn_submit);
            Button btnCancel = (Button) pop_search_word.findViewById(R.id.btn_cancel);
            pop_search_word.show();
            // ImageView ivclose = (ImageView) pop_search_word.findViewById(R.id.iv_close);

//            ivclose.setOnClickListener(new SampleFragment.C02636( ));
            btnSumbit.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    String searchedWord = edtSearchWord.getText().toString();
                    if (searchedWord == " ") {
                        edtSearchWord.setError("Enter a word to search");
                        return;
                    }
                    Intent i = new Intent(getApplicationContext(), SearchList.class);
                    i.putExtra("searchword", searchedWord);
                    startActivity(i);
                    overridePendingTransition(0, 0);

                }
            });
            edtSearchWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (event.getKeyCode() == 66) {
                        String searchedWord = edtSearchWord.getText().toString();
                        if (searchedWord == " ") {
                            edtSearchWord.setError("Enter a word to search");
                        } else {
                            Intent i = new Intent(getApplicationContext(), SearchList.class);
                            i.putExtra("searchword", searchedWord);
                            startActivity(i);
                            overridePendingTransition(0, 0);

                        }
                    }
                    return true;
                }
            });
            final Dialog finalPop_search_word = pop_search_word;
            btnCancel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalPop_search_word.dismiss();
                }
            });

        } else if (pop_search_word.isShowing()) {
            pop_search_word.dismiss();
            pop_search_word = null;
            search();
        } else {
            pop_search_word = null;
            search();
        }
    }
    public void swipeToLeft() {
        int tabpos;
        tabpos = tab_lay.getSelectedTabPosition();
        if (tabpos == 2) {
            oldTestament.setVisibility(View.GONE);
            newTestament.setVisibility(View.GONE);
            grid_old_book.setVisibility(View.GONE);
            grid_new_book.setVisibility(View.GONE);
            grid_chap.setVisibility(View.GONE);
            backk.setVisibility(View.GONE);
            goBook.setVisibility(View.VISIBLE);
            goChap.setVisibility(View.GONE);
            backBook.setVisibility(View.VISIBLE);
            backChap.setVisibility(View.GONE);
            grid_vers.setVisibility(View.VISIBLE);
            popChapNo.setText("Chapter\n01");
            tab_lay.getTabAt(1).select();
        } else if (tabpos == 1) {
            oldTestament.setVisibility(View.VISIBLE);
            newTestament.setVisibility(View.VISIBLE);
            grid_old_book.setVisibility(View.VISIBLE);
            grid_new_book.setVisibility(View.VISIBLE);
            grid_chap.setVisibility(View.GONE);
            backk.setVisibility(View.VISIBLE);
            goBook.setVisibility(View.GONE);
            goChap.setVisibility(View.GONE);
            backBook.setVisibility(View.GONE);
            backChap.setVisibility(View.GONE);
            grid_vers.setVisibility(View.GONE);
            popBookNo.setText("Book\nGen");
            popChapNo.setText("Chapter\n01");
            tab_lay.getTabAt(0).select();
        }

    }

    public void swipeToRight() {

        int tabpos;
        tabpos = tab_lay.getSelectedTabPosition();
        if (tabpos == 0) {
            if (tab_lay.getTabAt(0).getText().equals("Book")) {
                tab_lay.getTabAt(0).setText("Gen");
                Chapter("Gen");
            }
            oldTestament.setVisibility(View.GONE);
            newTestament.setVisibility(View.GONE);
            grid_old_book.setVisibility(View.GONE);
            grid_new_book.setVisibility(View.GONE);
            grid_chap.setVisibility(View.VISIBLE);
            backk.setVisibility(View.GONE);
            goBook.setVisibility(View.VISIBLE);
            goChap.setVisibility(View.GONE);
            backBook.setVisibility(View.VISIBLE);
            backChap.setVisibility(View.GONE);
            grid_vers.setVisibility(View.GONE);
            popChapNo.setText("Chapter\n01");
            tab_lay.getTabAt(1).select();
        } else if (tabpos == 1) {
            if (tab_lay.getTabAt(1).getText().equals("Chapter")) {
                tab_lay.getTabAt(1).setText("1");
                Verse("01", "1");
            }
            oldTestament.setVisibility(View.GONE);
            newTestament.setVisibility(View.GONE);
            grid_old_book.setVisibility(View.GONE);
            grid_new_book.setVisibility(View.GONE);
            grid_chap.setVisibility(View.GONE);
            backk.setVisibility(View.GONE);
            goBook.setVisibility(View.VISIBLE);
            goChap.setVisibility(View.GONE);
            backBook.setVisibility(View.VISIBLE);
            backChap.setVisibility(View.GONE);
            grid_vers.setVisibility(View.VISIBLE);
            popChapNo.setText("Chapter\n01");
            tab_lay.getTabAt(2).select();
        }

    }

    public void Verse(String s, String tabchapid) {
        final String bookid = sharedPreferences.getString("bookid", null);
        tab_lay.getTabAt(2).select();
        tab_lay.getTabAt(1).setText(tabchapid);
        Editor editor = sharedPreferences.edit();
        editor.putString("chapterid", s);
        editor.putString("TabChapid", tabchapid);
        backk.setVisibility(View.GONE);
        backBook.setVisibility(View.GONE);
        backChap.setVisibility(View.VISIBLE);
        goBook.setVisibility(View.GONE);
        goChap.setVisibility(View.VISIBLE);
        editor.commit();
        scrollfull.pageScroll(33);
        ArrayList<String> stringArrayList = new ArrayList();
        final String chapno = s;
        goChap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String con = "";
                Cursor value = dbhelper.getChapterContent(bible, bookid, chapno);
                if (value != null) {
                    if (value.moveToFirst()) {
                        do {
                            con = con + value.getString(value.getColumnIndex("verse"));
                            Log.e("content by id", content);
                        } while (value.moveToNext());
                    }
                    value.close();
                    Intent i = new Intent(getApplicationContext(), FirstPage.class);
                    i.putExtra("intent_content", con);
                    i.putExtra("intentbookid", bookid);
                    i.putExtra("intentchapid", chapno);
                    Editor edit_gen = sharedPreferences.edit();
                    edit_gen.putString("intentbookid", bookid);
                    edit_gen.putString("intentchapid", chapno);
                    edit_gen.putString("intentverseid", "");
                    edit_gen.commit();
                    startActivity(i);
                    overridePendingTransition(0, 0);

                }
            }
        });
        backChap.setOnClickListener(new C02965());
        popChapNo.setText("Chapter\n" + s);
        int value = dbhelper.getNoofVerse(bible, bookid, chapno);
        Log.e("no:of verse", "got count");
        grid_chap.setVisibility(View.GONE);
        grid_vers.setVisibility(View.VISIBLE);
        for (int i = 1; i <= value; i++) {
            stringArrayList.add(String.valueOf(i));
        }
        str_ver = (String[]) stringArrayList.toArray(new String[stringArrayList.size()]);
        adapt = new VerseAdapter(this, str_ver);
        grid_vers.setAdapter(adapt);
        Log.e("verse set", "verse set");

    }

    public void content(String book, String chap, String verse) {
        String con = "";
        Cursor value = dbhelper.getContent(bible, book, chap, verse);
        if (value != null) {
            if (value.moveToFirst()) {
                do {
                    con = con + value.getString(value.getColumnIndex("verse"));
                    Log.e("content by id", content);
                } while (value.moveToNext());
            }
            value.close();
            Intent i = new Intent(getApplicationContext(), FirstPage.class);
            Editor edit_gen = sharedPreferences.edit();
            edit_gen.putString("intentbookid", bookid);
            edit_gen.putString("intentchapid", chap);
            edit_gen.putString("intentverseid", verse);
            edit_gen.commit();
            startActivity(i);
            overridePendingTransition(0, 0);

        }
    }

    public void Chapter(String s) {
        bookid = dbhelper.getId(s);
        popBookNo.setText("Book\n" + s);
        tab_lay.getTabAt(0).setText(s);
        tab_lay.getTabAt(1).select();
        oldTestament.setVisibility(View.GONE);
        newTestament.setVisibility(View.GONE);
        grid_old_book.setVisibility(View.GONE);
        grid_new_book.setVisibility(View.GONE);
        backk.setVisibility(View.GONE);
        backBook.setVisibility(View.VISIBLE);
        backChap.setVisibility(View.GONE);
        goBook.setVisibility(View.VISIBLE);
        goChap.setVisibility(View.GONE);
        grid_vers.setVisibility(View.GONE);
        grid_chap.setVisibility(View.VISIBLE);
        noofchap = dbhelper.getNoofChap(bible, bookid);
        scrollfull.pageScroll(33);
        goBook.setOnClickListener(new C02976());
        backBook.setOnClickListener(new C02987());
        stringArrayList.clear();
        for (int i = 1; i <= noofchap; i++) {
            stringArrayList.add(String.valueOf(i));
        }
        String[] str = (String[]) stringArrayList.toArray(new String[stringArrayList.size()]);
        Editor edit_gen = sharedPreferences.edit();
        edit_gen.putString("bookid", bookid);
        edit_gen.commit();
        grid_chap.setAdapter(new ChapterAdapter(this, str));
    }


    @SuppressLint("WrongConstant")
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_about) {
            Intent intent = new Intent(bibleContentSelect.this, pop_activity_about.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.nav_mynotes) {
            Intent intent = new Intent(this, Notes.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.nav_flashcard) {

            Intent intent = new Intent(this, popFlashcard.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            this.startActivity(intent);

        } else if (id == R.id.nav_search) {
            Intent intent = new Intent(bibleContentSelect.this, popSearch.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);

        } else if (id == R.id.nav_readhistory) {

            Intent intent = new Intent(this, popReadHistory.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            this.startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(bibleContentSelect.this, Settings.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return productGestureDetector.onTouchEvent(ev);
    }//    @Override

    //    public boolean onTouchEvent(MotionEvent event) {
//        if (swapped) {
//     /*Make sure you don't swap twice,
//since the dispatchTouchEvent might dispatch your touch event to this function again!*/
//            swapped = false;
//            return super.onTouchEvent(event);
//        }
//        switch (event.getAction( )) {
//            case MotionEvent.ACTION_DOWN:
//                x1 = event.getX( );
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = event.getX( );
//                float deltaX = x2 - x1;
//                if (Math.abs(deltaX) > MIN_DISTANCE) {
//                    swipeToRight();
//                    //you already swapped, set flag swapped = true
//                    swapped = true;
//                } else {
//
//                    // not swapping
//                }
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
    public void onResume() {
        super.onResume();
        bottomBar.selectTabWithId(R.id.tab_read);
    }
}

