package com.example.admin.bibleapp.ViewPager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.admin.bibleapp.Activity.EventActivity;
import com.example.admin.bibleapp.Activity.MonthlyCal;
import com.example.admin.bibleapp.Activity.UserProfile;
import com.example.admin.bibleapp.Adapter.BookAdapter;
import com.example.admin.bibleapp.Adapter.DrawerItemCustomAdapter;
import com.example.admin.bibleapp.Adapter.PrayerAdapter;
import com.example.admin.bibleapp.Adapter.VerseAdapter;
import com.example.admin.bibleapp.C0246R;
import com.example.admin.bibleapp.Model.MenuItems;
import com.example.admin.bibleapp.Model.user;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Activity.CalPage;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Activity.IncompleteReadings;
import com.example.admin.bibleapp.Model.IncompleteModel;
import com.example.admin.bibleapp.Activity.Notes;
import com.example.admin.bibleapp.Parser.Employee;
import com.example.admin.bibleapp.Parser.XMLPullParserHandler;
import com.example.admin.bibleapp.Activity.SearchList;
import com.example.admin.bibleapp.Activity.Settings;
import com.example.admin.bibleapp.Activity.bibleContentSelect;
import com.example.admin.bibleapp.popupWindow.popFlashcard;
import com.example.admin.bibleapp.popupWindow.popReadHistory;
import com.example.admin.bibleapp.popupWindow.pop_activity_about;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roughike.bottombar.BottomBar;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class CalContentView extends AppCompatActivity  {
    String Bookname;
    Typeface RobotoSlabLight;
    VerseAdapter adapt;
    BookAdapter adapter;
    TextView back;
    TextView backBook;
    TextView backChap;
    String bible;
    String bookid;
    LinearLayout bottomSelection;
    ScrollView chapScroll;
    BottomBar bottomBar;
    String content = "";
    String date = "";
    Cursor dateContent;
    DBhelper db;
    DBhelper dbhelper;
    DrawerLayout drawer;
    List<Employee> employees = null;
    public static final String MyPREFERENCES = "MyPrefs";
    Bundle extras;
    PrayerAdapter mAdapter;
    FloatingActionButton fab;
    Animation fabAntiClock;
    Animation fabClock;
    Animation fabClose;
    Animation fabOpen;
    boolean flag = true;
    float font_size;
    boolean fourread = false;
    String fourthread = "";
    ArrayList<String> frag1content = new ArrayList();
    ArrayList<String> frag2content = new ArrayList();
    ArrayList<String> frag3content = new ArrayList();
    ArrayList<String> frag4content = new ArrayList();
    TextView goBook;
    TextView goChap;
    GridView grid_chap;
    GridView grid_new_book;
    GridView grid_old_book;
    GridView grid_vers;
    TextView hamNASB;
    TextView hamabout;
    ImageView hamclose;
    TextView hamfont;
    TextView hamload;
    TextView hamnotes;
    TextView hamnotification;
    TextView hamreset;
    TextView hamsearch;
    TextView headerTitle;
    String initialdate;
    int int_fourthread;
    int int_newread;
    int int_oldread;
    int int_psalmread;
    int j;
    boolean isOpen = false;
    ImageView iv_bible;
    ImageView iv_monthlyCal;
    ImageView iv_nav;
    int f19j = 0;
    View layout;
    ImageButton leftNav;
    RecyclerView.LayoutManager mLayoutManager;
    ViewPager mViewPager;
    private int mWebViewScrollPosition;
    Typeface monRegular;
    boolean neread = false;
    TextView tvTitle;
    TextView newTestament;
    String newread = "";
    int noofchap;
    TextView oldTestament;
    String oldread = "";
    boolean olread = false;
    Animation optionClose;
    boolean optionIsOpen = false;
    Animation optionOpen;
    ProgressDialog pd;
    String planby;
    PopupWindow pop;
    TextView popBookNo;
    TextView popChapNo;
    TextView popVerseNo;
    Dialog pop_abt;
    Dialog pop_db;
    Dialog pop_font;
    Dialog pop_readbible;
    Dialog pop_search_word;
    LinearLayout poplay;
    int pos = 0;
    String psalmread = "";
    boolean psaread = false;
    ImageView readcontent;
    ImageButton rightNav;
    ScrollView scrollfull;
    SharedPreferences sharedPreferences;
    String state;
    String[] str_ver;
    List<String> stringArrayList = new ArrayList( );
    FloatingActionButton sub_fab1;
    FloatingActionButton sub_fab2;
    FloatingActionButton sub_fab3;
    String table_plan;
    StringBuffer textBuilder;
    String title1 = " ";
    String title2 = " ";
    String title3 = " ";
    String title4 = " ";
    String titlename1;
    String titlename2;
    String titlename3;
    String titlename4;
    Toolbar toolbar;
    ImageView tv_house;
    ImageView unreadcontent;
    String datediff;
    ScrollView verseScroll;
    private ListView mDrawerList;

    class C02542 implements OnClickListener {
        C02542() {
        }

        public void onClick(View v) {
            if (planby.equals("bydate")) {
                if (pos == 0) {
                    int int_total;
                    if (oldread.equals("0")) {
                        olread = true;
                        int_oldread = 1;
                        unreadcontent.setImageResource(R.drawable.appcheck);
                        int_total = ((int_oldread + int_psalmread) + int_newread) + int_fourthread;
                        db.addoldreaddate2(table_plan, date, title1, olread);
                        db.updatetotal2(table_plan, int_total, date);
                    } else {
                        olread = false;
                        int_oldread = 0;
                        unreadcontent.setImageResource(R.drawable.appuncheck);
                        int_total = ((int_oldread + int_psalmread) + int_newread) + int_fourthread;
                        db.addoldreaddate2(table_plan, date, title1, olread);
                        db.updatetotal2(table_plan, int_total, date);
                    }
                } else if (pos == 1) {
                    if (psalmread.equals("0")) {
                        unreadcontent.setImageResource(R.drawable.appcheck);
                        psaread = true;
                        int_psalmread = 1;
                        db.updatetotal2(table_plan, ((int_oldread + int_psalmread) + int_newread) + int_fourthread, date);
                        db.addpsalmreaddate2(table_plan, date, title2, psaread);
                    } else {
                        unreadcontent.setImageResource(R.drawable.appuncheck);
                        psaread = false;
                        int_psalmread = 0;
                        db.updatetotal2(table_plan, ((int_oldread + int_psalmread) + int_newread) + int_fourthread, date);
                        db.addpsalmreaddate2(table_plan, date, title2, psaread);
                    }
                } else if (pos == 2) {
                    if (newread.equals("0")) {
                        unreadcontent.setImageResource(R.drawable.appcheck);
                        neread = true;
                        int_newread = 1;
                        db.updatetotal2(table_plan, ((int_oldread + int_psalmread) + int_newread) + int_fourthread, date);
                        db.addnewreaddate2(table_plan, date, title3, neread);
                    } else {
                        unreadcontent.setImageResource(R.drawable.appuncheck);
                        neread = false;
                        int_newread = 0;
                        db.updatetotal2(table_plan, ((int_oldread + int_psalmread) + int_newread) + int_fourthread, date);
                        db.addnewreaddate2(table_plan, date, title3, neread);
                    }
                } else if (pos == 3) {
                    if (fourthread.equals("0")) {
                        unreadcontent.setImageResource(R.drawable.appcheck);
                        fourread = true;
                        int_fourthread = 1;
                        db.updatetotal2(table_plan, ((int_oldread + int_psalmread) + int_newread) + int_fourthread, date);
                        db.addfourthreaddate2(table_plan, date, title4, fourread);
                    } else {
                        unreadcontent.setImageResource(R.drawable.appuncheck);
                        fourread = false;
                        int_fourthread = 0;
                        db.updatetotal2(table_plan, ((int_oldread + int_psalmread) + int_newread) + int_fourthread, date);
                        db.addfourthreaddate2(table_plan, date, title4, fourread);
                    }
                }
            }
            else
            {
                datediff = sharedPreferences.getString("datediff",null);
                Log.e("datediff(id)",datediff);
                if (pos == 0) {
                    int int_total;
                    if (oldread.equals("0")) {
                        olread = true;
                        int_oldread = 1;
                        unreadcontent.setImageResource(R.drawable.appcheck);
                        int_total = ((int_oldread + int_psalmread) + int_newread) + int_fourthread;
                        db.addoldreaddate3(table_plan, datediff, title1, olread);
                        db.updatetotal3(table_plan, int_total, datediff);
                    } else {
                        olread = false;
                        int_oldread = 0;
                        unreadcontent.setImageResource(R.drawable.appuncheck);
                        int_total = ((int_oldread + int_psalmread) + int_newread) + int_fourthread;
                        db.addoldreaddate3(table_plan, datediff, title1, olread);
                        db.updatetotal3(table_plan, int_total, datediff);
                    }
                } else if (pos == 1) {
                    if (psalmread.equals("0")) {
                        unreadcontent.setImageResource(R.drawable.appcheck);
                        psaread = true;
                        int_psalmread = 1;
                        db.updatetotal3(table_plan, ((int_oldread + int_psalmread) + int_newread) + int_fourthread, datediff);
                        db.addpsalmreaddate3(table_plan, datediff, title2, psaread);
                    } else {
                        unreadcontent.setImageResource(R.drawable.appuncheck);
                        psaread = false;
                        int_psalmread = 0;
                        db.updatetotal3(table_plan, ((int_oldread + int_psalmread) + int_newread) + int_fourthread, datediff);
                        db.addpsalmreaddate3(table_plan, datediff, title2, psaread);
                    }
                } else if (pos == 2) {
                    if (newread.equals("0")) {
                        unreadcontent.setImageResource(R.drawable.appcheck);
                        neread = true;
                        int_newread = 1;
                        db.updatetotal3(table_plan, ((int_oldread + int_psalmread) + int_newread) + int_fourthread, datediff);
                        db.addnewreaddate3(table_plan, datediff, title3, neread);
                    } else {
                        unreadcontent.setImageResource(R.drawable.appuncheck);
                        neread = false;
                        int_newread = 0;
                        db.updatetotal3(table_plan, ((int_oldread + int_psalmread) + int_newread) + int_fourthread, datediff);
                        db.addnewreaddate3(table_plan, datediff, title3, neread);
                    }
                } else if (pos == 3) {
                    if (fourthread.equals("0")) {
                        unreadcontent.setImageResource(R.drawable.appcheck);
                        fourread = true;
                        int_fourthread = 1;
                        db.updatetotal3(table_plan, ((int_oldread + int_psalmread) + int_newread) + int_fourthread, datediff);
                        db.addfourthreaddate3(table_plan, datediff, title4, fourread);
                    } else {
                        unreadcontent.setImageResource(R.drawable.appuncheck);
                        fourread = false;
                        int_fourthread = 0;
                        db.updatetotal3(table_plan, ((int_oldread + int_psalmread) + int_newread) + int_fourthread, datediff);
                        db.addfourthreaddate3(table_plan, datediff, title4, fourread);
                    }
                }
            }
            if (planby.equals("bydate")) {
                dateContent = db.getDateContent1(table_plan, date);
                oldread = dateContent.getString(dateContent.getColumnIndex("old_read"));
                Log.e("old", oldread);
                psalmread = dateContent.getString(dateContent.getColumnIndex("PSALM_read"));
                Log.e("psalm", psalmread);
                newread = dateContent.getString(dateContent.getColumnIndex("new_read"));
                Log.e("newread", newread);
                fourthread = dateContent.getString(dateContent.getColumnIndex("fourth_read"));
                Log.e("fourthread", fourthread);
            } else {
                dateContent = db.getDateContent2(table_plan, datediff);
                oldread = dateContent.getString(dateContent.getColumnIndex("old_read"));
                Log.e("old", oldread);
                psalmread = dateContent.getString(dateContent.getColumnIndex("PSALM_read"));
                Log.e("psalm", psalmread);
                newread = dateContent.getString(dateContent.getColumnIndex("new_read"));
                Log.e("newread", newread);
                fourthread = dateContent.getString(dateContent.getColumnIndex("fourth_read"));
                Log.e("fourthread", fourthread);
            }
            if (oldread.equals("0") || oldread.equals("")) {
                int_oldread = 0;
            } else {
                int_oldread = Integer.parseInt(oldread);
            }
            if (psalmread.equals("0") || psalmread.equals("")) {
                int_psalmread = 0;
            } else {
                int_psalmread = Integer.parseInt(psalmread);
            }
            if (newread.equals("0") || newread.equals("")) {
                int_newread = 0;
            } else {
                int_newread = Integer.parseInt(newread);
            }
            if (fourthread.equals("0") || fourthread.equals("")) {
                int_fourthread = 0;
            } else {
                int_fourthread = Integer.parseInt(fourthread);
            }
            if (psalmread.equals("") && newread.equals("") && fourthread.equals("") && int_oldread == 1) {
                finish( );
            } else if (newread.equals("") && fourthread.equals("") && int_oldread == 1 && int_psalmread == 1) {
                finish( );
            } else if (fourthread.equals("") && int_oldread == 1 && int_psalmread == 1 && int_newread == 1) {
                finish( );
            } else if (int_oldread == 1 && int_psalmread == 1 && int_newread == 1 && int_fourthread == 1) {
                finish( );
            }
        }
    }

    class C02553 implements OnClickListener {
        C02553() {
        }

        public void onClick(View v) {
            int tab = mViewPager.getCurrentItem( );
            if (psalmread.equals("") && newread.equals("") && fourthread.equals("")) {
                if (tab > 0) {
                    tab--;
                    leftNav.setVisibility(View.VISIBLE);
                    rightNav.setVisibility(View.VISIBLE);
                    if (tab == 0) {
                        tvTitle.setText(titlename1);
                    }
                    mViewPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    rightNav.setVisibility(View.INVISIBLE);
                    leftNav.setVisibility(View.INVISIBLE);
                    mViewPager.setCurrentItem(tab);
                }
            } else if (newread.equals("") && fourthread.equals("")) {
                if (tab > 0) {
                    tab--;
                    leftNav.setVisibility(View.VISIBLE);
                    rightNav.setVisibility(View.VISIBLE);
                    if (tab == 0) {
                        tvTitle.setText(titlename1);
                    } else if (tab == 1) {
                        tvTitle.setText(title2);
                    }
                    mViewPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    rightNav.setVisibility(View.VISIBLE);
                    mViewPager.setCurrentItem(tab);
                }
            } else if (fourthread.equals("")) {
                if (tab > 0) {
                    tab--;
                    leftNav.setVisibility(View.VISIBLE);
                    rightNav.setVisibility(View.VISIBLE);
                    if (tab == 0) {
                        tvTitle.setText(titlename1);
                    } else if (tab == 1) {
                        tvTitle.setText(title2);
                    } else if (tab == 2) {
                        tvTitle.setText(title3);
                    }
                    mViewPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    rightNav.setVisibility(View.VISIBLE);
                    mViewPager.setCurrentItem(tab);
                }
            } else if (tab > 0) {
                tab--;
                leftNav.setVisibility(View.VISIBLE);
                rightNav.setVisibility(View.VISIBLE);
                if (tab == 0) {
                    tvTitle.setText(titlename1);
                } else if (tab == 1) {
                    tvTitle.setText(title2);
                } else if (tab == 2) {
                    tvTitle.setText(title3);
                } else if (tab == 3) {
                    tvTitle.setText(title4);
                }
                mViewPager.setCurrentItem(tab);
            } else if (tab == 0) {
                rightNav.setVisibility(View.INVISIBLE);
                mViewPager.setCurrentItem(tab);
            }
        }
    }

    class C02564 implements OnClickListener {
        C02564() {
        }

        public void onClick(View v) {
            int tab = mViewPager.getCurrentItem( );
            if (psalmread.equals("") && newread.equals("") && fourthread.equals("")) {
                rightNav.setVisibility(View.INVISIBLE);
                if (tab < 0) {
                    tab++;
                    if (tab == 0) {
                        tvTitle.setText(titlename1);
                        rightNav.setVisibility(View.INVISIBLE);
                    }
                    mViewPager.setCurrentItem(tab);
                }
            } else if (newread.equals("") && fourthread.equals("")) {
                rightNav.setVisibility(View.INVISIBLE);
                if (tab < 1) {
                    tab++;
                    if (tab == 0) {
                        tvTitle.setText(titlename1);
                        rightNav.setVisibility(View.INVISIBLE);
                    } else if (tab == 1) {
                        tvTitle.setText(title2);
                        rightNav.setVisibility(View.INVISIBLE);
                    }
                    mViewPager.setCurrentItem(tab);
                }
            } else if (fourthread.equals("")) {
                rightNav.setVisibility(View.INVISIBLE);
                if (tab < 2) {
                    tab++;
                    if (tab == 0) {
                        tvTitle.setText(titlename1);
                        rightNav.setVisibility(View.VISIBLE);
                        leftNav.setVisibility(View.INVISIBLE);
                    } else if (tab == 1) {
                        tvTitle.setText(title2);
                        rightNav.setVisibility(View.VISIBLE);
                        leftNav.setVisibility(View.VISIBLE);
                    } else if (tab == 2) {
                        tvTitle.setText(title3);
                        rightNav.setVisibility(View.INVISIBLE);
                        leftNav.setVisibility(View.VISIBLE);
                    }
                    mViewPager.setCurrentItem(tab);
                }
            } else if (tab < 3) {
                tab++;
                if (tab == 0) {
                    tvTitle.setText(titlename1);
                } else if (tab == 1) {
                    tvTitle.setText(title2);
                } else if (tab == 2) {
                    tvTitle.setText(title3);
                } else if (tab == 3) {
                    tvTitle.setText(title4);
                }
                mViewPager.setCurrentItem(tab);
            }
        }
    }

    class C04971 implements ViewPager.OnPageChangeListener {
        C04971() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mViewPager.getBottom( ) - mViewPager.getHeight( ) != 0) {
            }
        }

        public void onPageSelected(int position) {
            pos = position;
            if (pos == 0) {
                tvTitle.setText(title1);
                leftNav.setVisibility(View.INVISIBLE);
                rightNav.setVisibility(View.VISIBLE);
                if (oldread.equals("1")) {
                    olread = true;
                    unreadcontent.setImageResource(R.drawable.appcheck);
                    return;
                }
                unreadcontent.setImageResource(R.drawable.appuncheck);
            } else if (pos == 1) {
                tvTitle.setText(title2);
                leftNav.setVisibility(View.VISIBLE);
                rightNav.setVisibility(View.VISIBLE);
                if (psalmread.equals("1")) {
                    psaread = true;
                    unreadcontent.setImageResource(R.drawable.appcheck);
                    return;
                }
                unreadcontent.setImageResource(R.drawable.appuncheck);
            } else if (pos == 2) {
                tvTitle.setText(title3);
                leftNav.setVisibility(View.VISIBLE);
                rightNav.setVisibility(View.VISIBLE);
                if (newread.equals("1")) {
                    neread = true;
                    unreadcontent.setImageResource(R.drawable.appcheck);
                    return;
                }
                unreadcontent.setImageResource(R.drawable.appuncheck);
            } else if (pos == 3) {
                tvTitle.setText(title4);
                leftNav.setVisibility(View.VISIBLE);
                rightNav.setVisibility(View.INVISIBLE);
                if (fourthread.equals("1")) {
                    fourread = true;
                    unreadcontent.setImageResource(R.drawable.appcheck);
                    return;
                }
                unreadcontent.setImageResource(R.drawable.appuncheck);
            }
        }

        public void onPageScrollStateChanged(int state) {
            int diff = mViewPager.getBottom( ) - mViewPager.getHeight( );
            Log.e("Diffee", diff + "");
            if (diff != 0) {
            }
        }
    }



    public class SamplePagerAdapter extends FragmentPagerAdapter {
        Fragment fragment = null;

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            Bundle bundle;
            if (position == 0) {
                this.fragment = new SampleFragment( );
                bundle = new Bundle( );
                bundle.putStringArrayList("frag1", frag1content);
                bundle.putStringArrayList("frag2", frag2content);
                bundle.putStringArrayList("frag3", frag3content);
                bundle.putString("title1", title1);
                bundle.putString("title2", title2);
                bundle.putString("title3", title3);
                bundle.putString("title4", title4);
                bundle.putString("date", date);
                bundle.putString("oldread", oldread);
                bundle.putString("newread", newread);
                bundle.putString("psalmread", psalmread);
                this.fragment.setArguments(bundle);
            } else if (position == 1) {
                this.fragment = new SampleFragment2( );
                bundle = new Bundle( );
                bundle.putStringArrayList("frag1", frag1content);
                bundle.putStringArrayList("frag2", frag2content);
                bundle.putStringArrayList("frag3", frag3content);
                bundle.putStringArrayList("frag4", frag4content);
                bundle.putString("title1", title1);
                bundle.putString("title2", title2);
                bundle.putString("title3", title3);
                bundle.putString("title4", title4);
                bundle.putString("date", date);
                bundle.putString("oldread", oldread);
                bundle.putString("newread", newread);
                bundle.putString("psalmread", psalmread);
                bundle.putString("fourthread", fourthread);
                this.fragment.setArguments(bundle);
            } else if (position == 2) {
                this.fragment = new SampleFragment3( );
                bundle = new Bundle( );
                bundle.putStringArrayList("frag1", frag1content);
                bundle.putStringArrayList("frag2", frag2content);
                bundle.putStringArrayList("frag3", frag3content);
                bundle.putStringArrayList("frag4", frag4content);
                bundle.putString("title1", title1);
                bundle.putString("title2", title2);
                bundle.putString("title3", title3);
                bundle.putString("title4", title4);
                bundle.putString("date", date);
                bundle.putString("oldread", oldread);
                bundle.putString("newread", newread);
                bundle.putString("psalmread", psalmread);
                bundle.putString("fourthread", fourthread);
                this.fragment.setArguments(bundle);
            } else if (position == 3) {
                this.fragment = new SampleFragment4( );
                bundle = new Bundle( );
                bundle.putStringArrayList("frag1", frag1content);
                bundle.putStringArrayList("frag2", frag2content);
                bundle.putStringArrayList("frag3", frag3content);
                bundle.putStringArrayList("frag4", frag4content);
                bundle.putString("title1", title1);
                bundle.putString("title2", title2);
                bundle.putString("title3", title3);
                bundle.putString("title4", title4);
                bundle.putString("date", date);
                bundle.putString("oldread", oldread);
                bundle.putString("newread", newread);
                bundle.putString("psalmread", psalmread);
                bundle.putString("fourthread", fourthread);
                this.fragment.setArguments(bundle);
            }
            return this.fragment;
        }

        public int getCount() {
            return 4;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_cal_content_view);
        toolbar = (Toolbar) findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);
        tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("Connect Detail");

        this.dbhelper = new DBhelper(this, getFilesDir( ).getAbsolutePath( ));
        this.state = Environment.getExternalStorageState( );
        //this.pd = new ProgressDialog(this, R.style.MyTheme);
        //      this.pd.setCancelable(false);
        //    this.pd.setProgressStyle(16973854);
        this.bottomSelection = (LinearLayout) findViewById(R.id.bottomlay);
        this.bottomSelection.setVisibility(View.VISIBLE);
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        this.bible = this.sharedPreferences.getString("bible", null);
        this.table_plan = this.sharedPreferences.getString("tablePlan", null);
        this.planby = this.sharedPreferences.getString("planby", null);
        this.initialdate = this.sharedPreferences.getString("initialdate", null);
        this.db = new DBhelper(this, getFilesDir( ).getAbsolutePath( ));
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawer, this.toolbar, C0246R.string.navigation_drawer_open, C0246R.string.navigation_drawer_close);
        this.drawer.setDrawerListener(toggle);
        toggle.syncState( );
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
//        this.optionOpen = AnimationUtils.loadAnimation(getApplicationContext(), C0246R.anim.grow_from_topleft_to_bottomright);
        //      this.optionClose = AnimationUtils.loadAnimation(getApplicationContext(), C0246R.anim.shrink_from_bottomright_to_topleft);
        this.unreadcontent = (ImageView) findViewById(R.id.ib_unread_content);
        this.readcontent = (ImageView) findViewById(R.id.ib_read_content);
        this.leftNav = (ImageButton) findViewById(R.id.left_nav);
        this.rightNav = (ImageButton) findViewById(R.id.right_nav);
//        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        this.mViewPager = (ViewPager) findViewById(R.id.pager);
        this.mViewPager.addOnPageChangeListener(new C04971( ));
        this.unreadcontent.setOnClickListener(new C02542( ));
        this.leftNav.setOnClickListener(new C02553( ));
        this.rightNav.setOnClickListener(new C02564( ));
        Intentdatas( );
        /*olread = true;
        int_oldread = 1;
        unreadcontent.setImageResource(R.drawable.appcheck);
        db.addoldreaddate2(table_plan, date, title1, olread);
        db.updatetotal2(table_plan, 0, date);
*/
//        bottomBar.selectTabWithId(R.id.tab_menu);

        ArrayList<MenuItems> menu = new ArrayList<MenuItems>();
        List<String> menuItems = dbhelper.getMenu();
        if(menuItems.size()<=0) {
            for (int j = 0; j < menu.size(); j++) {
                dbhelper.insertMenuItem(menu.get(j).getMenuid(), menu.get(j).getLangId(), menu.get(j).getMenuname(), menu.get(j).getPageId());
                menuItems = dbhelper.getMenu();
            }
        }
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, menuItems);
        mDrawerList.setAdapter(adapter);
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
                        Intent intent = new Intent(CalContentView.this, Notes.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;

                    case 8:
                        Intent intent8 = new Intent(CalContentView.this, pop_activity_about.class);
                        intent8.setFlags(intent8.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent8);
                        break;
                    case 10:
                        Intent intent10 = new Intent(CalContentView.this, EventActivity.class);
                        intent10.setFlags(intent10.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(CalContentView.this, UserProfile.class);
                        intent11.setFlags(intent11.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent11);
                        break;
                    case 2:
                        Intent intent2 = new Intent(CalContentView.this, popFlashcard.class);
                        intent2.setFlags(intent2.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case 1:
                        search();
                        break;

                    case 4:
                        Intent intent4 = new Intent(CalContentView.this, popReadHistory.class);
                        intent4.setFlags(intent4.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                    case 0:
                        Intent intent0 = new Intent(CalContentView.this, Settings.class);
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

    private void Intentdatas() {
        this.extras = getIntent( ).getExtras( );
        if (this.extras != null) {
            this.pos = Integer.parseInt(this.extras.getString("viewpager_position"));
            this.date = this.extras.getString("date");
            this.frag1content = this.extras.getStringArrayList("frag1value");
            this.frag2content = this.extras.getStringArrayList("frag2value");
            this.frag3content = this.extras.getStringArrayList("frag3value");
            this.frag4content = this.extras.getStringArrayList("frag4value");
            this.title1 = this.extras.getString("title1");
            this.titlename1 = this.dbhelper.getBookName(this.title1);
            this.title2 = this.extras.getString("title2");
            this.titlename2 = this.dbhelper.getBookName(this.title2);
            this.title3 = this.extras.getString("title3");
            this.titlename3 = this.dbhelper.getBookName(this.title3);
            this.title4 = this.extras.getString("title4");
            this.titlename4 = this.dbhelper.getBookName(this.title4);
            this.oldread = this.extras.getString("oldread");
            this.newread = this.extras.getString("newread");
            this.psalmread = this.extras.getString("psalmread");
            this.fourthread = this.extras.getString("fourthread");
            if (!this.oldread.equals("")) {
                this.int_oldread = Integer.parseInt(this.oldread);
            }
            if (!this.psalmread.equals("")) {
                this.int_psalmread = Integer.parseInt(this.psalmread);
            }
            if (!this.newread.equals("")) {
                this.int_newread = Integer.parseInt(this.newread);
            }
            if (!this.fourthread.equals("")) {
                this.int_fourthread = Integer.parseInt(this.fourthread);
            }
        }
        SetFirstTitle( );
        this.mViewPager.setAdapter(new SamplePagerAdapter(getSupportFragmentManager( )));
        this.mViewPager.setCurrentItem(this.pos);
    }


    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem register = menu.findItem(R.id.incomplete);
        Calendar cal = Calendar.getInstance( );
        SimpleDateFormat date = new SimpleDateFormat("dd MMM");
        SimpleDateFormat month = new SimpleDateFormat("MMM");
        String CurrentDate = date.format(cal.getTime( ));
        String CurrentMonth = month.format(cal.getTime( ));
        List valu = this.dbhelper.getReadings(this.table_plan, this.dbhelper.getInitialdateId(this.table_plan, this.initialdate), this.dbhelper.getInitialdateId(this.table_plan, CurrentDate));
        IncompleteModel model = new IncompleteModel( );
        if (valu.size( ) == 0) {
            register.setVisible(false);
        } else {
            register.setVisible(true);
        }
        return true;
    }

    public void SetFirstTitle() {
        if (this.pos == 0) {
            Log.e(title1, "title1");
            this.tvTitle.setText(title1);
            this.leftNav.setVisibility(View.INVISIBLE);
            if(psalmread.equals(""))
            this.rightNav.setVisibility(View.INVISIBLE);
            else
            this.rightNav.setVisibility(View.VISIBLE);
            if (this.oldread.equals("1")) {
                this.olread = true;
                this.unreadcontent.setImageResource(R.drawable.appcheck);
                return;
            }
            this.unreadcontent.setImageResource(R.drawable.appuncheck);
        } else if (this.pos == 1) {
            this.tvTitle.setText(this.title2);
            this.leftNav.setVisibility(View.VISIBLE);
            if(newread.equals(""))
                this.rightNav.setVisibility(View.INVISIBLE);
            else
                this.rightNav.setVisibility(View.VISIBLE);
            if (this.psalmread.equals("1")) {
                this.psaread = true;
                this.unreadcontent.setImageResource(R.drawable.appcheck);
                return;
            }
            this.unreadcontent.setImageResource(R.drawable.appuncheck);
        } else if (this.pos == 2) {
            this.tvTitle.setText(this.title3);
            this.leftNav.setVisibility(View.VISIBLE);
            if(fourthread.equals(""))
                this.rightNav.setVisibility(View.INVISIBLE);
            else
                this.rightNav.setVisibility(View.VISIBLE);
            if (this.newread.equals("1")) {
                this.neread = true;
                this.unreadcontent.setImageResource(R.drawable.appcheck);
                return;
            }
            this.unreadcontent.setImageResource(R.drawable.appuncheck);
        } else if (this.pos == 3) {
            this.tvTitle.setText(this.title4);
            this.leftNav.setVisibility(View.VISIBLE);
            this.rightNav.setVisibility(View.INVISIBLE);
            if (this.fourthread.equals("1")) {
                this.fourread = true;
                this.unreadcontent.setImageResource(R.drawable.appcheck);
                return;
            }
            this.unreadcontent.setImageResource(R.drawable.appuncheck);
        }
    }

    public void setActionBarTitle(String title) {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater( ).inflate(R.menu.menu_cal_con, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId( );
        if (id == R.id.search) {
            Intent intent = new Intent(CalContentView.this, bibleContentSelect.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            return true;
        }
        if (id == R.id.monthlydate) {
            Intent intent = new Intent(CalContentView.this, CalPage.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.incomplete) {
            Intent intent = new Intent(CalContentView.this, IncompleteReadings.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId( );
        if (id == R.id.nav_about) {
            Intent intent = new Intent(CalContentView.this, pop_activity_about.class);
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
            search();

        } else if (id == R.id.nav_readhistory) {

            Intent intent = new Intent(this, popReadHistory.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            this.startActivity(intent);
        }
        else if (id == R.id.nav_settings) {
            Intent intent = new Intent(CalContentView.this, Settings.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    private void loaddata() {
        BufferedReader bufferedReader;
        FileNotFoundException e;
        IOException e2;
        if (this.state.equals("mounted")) {
            try {
                File textFile = new File(Environment.getExternalStorageDirectory( ) + "/catc plan/plandata.xml");
                if (textFile.exists( )) {
                    this.dbhelper.resetdata2(this.table_plan);
                    BufferedReader reader = new BufferedReader(new FileReader(textFile));
                    try {
                        this.textBuilder = new StringBuffer( );
                        while (true) {
                            String line = reader.readLine( );
                            if (line != null) {
                                this.textBuilder.append(line);
                                this.textBuilder.append("\n");
                            } else {
                                Log.e("Text build", this.textBuilder + "");
                                InputStream inputStream = new ByteArrayInputStream(this.textBuilder.toString( ).getBytes( ));
                                this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
                                Editor edit = this.sharedPreferences.edit( );
                                edit.putString("activit", "calPage");
                                edit.commit( );
                                this.employees = new XMLPullParserHandler(this).parse(inputStream);
                                this.pd.dismiss( );
                                Toast.makeText(getApplicationContext( ), "Data Loaded", Toast.LENGTH_LONG).show( );
                                bufferedReader = reader;
                                return;
                            }
                        }
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        bufferedReader = reader;
                        e.printStackTrace( );
                        return;
                    } catch (IOException e4) {
                        e2 = e4;
                        bufferedReader = reader;
                        e2.printStackTrace( );
                        return;
                    }
                }
                this.pd.dismiss( );
                Toast.makeText(this, "Data not exists", Toast.LENGTH_LONG).show( );
                return;

            } catch (IOException e6) {
                e2 = e6;
                e2.printStackTrace( );
                return;
            }
        }
        Toast.makeText(getApplicationContext( ), "There is no any sd card", Toast.LENGTH_LONG).show( );
    }

    public void onBackPressed() {
        this.flag = false;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen((int) GravityCompat.START)) {
            drawer.closeDrawer((int) GravityCompat.START);
        } else if (this.poplay == null || this.poplay.getVisibility( ) != View.VISIBLE) {
            finish( );
        } else {
            this.poplay.setVisibility(View.GONE);
            this.pop.dismiss( );
        }
    }

    public void load(String id, String i1, String i2, String i3, String i4) {
        this.dbhelper.insertdata(this.table_plan, id, i1, i2, i3, i4);
    }

    public ArrayList<String> newSeparator(String inputText) {
        ArrayList<String> FinalContent = new ArrayList();
        int spaceCounter = 0, colonCounter = 0, hypenCounter = 0;
        if (inputText.length() > 0) {
            for (int i = 0; i < inputText.length(); i++) {
                if (inputText.charAt(i) == ' ') {
                    spaceCounter++;
                } else if (inputText.charAt(i) == ':') {
                    colonCounter++;
                }
                if (inputText.charAt(i) == '-') {
                    hypenCounter++;
                }
            }
            Log.e("input", inputText + " " + spaceCounter + " " + colonCounter + " " + hypenCounter);
            if (spaceCounter == 0 && colonCounter == 0 && hypenCounter == 0) {
                for (int i = j; i < inputText.length(); i++) {
                    int startpos = i;
                    Bookname = inputText.substring(0, inputText.length());
                    Log.e("BookName:", Bookname);
                }
                String bookid = dbhelper.getMonBookId(Bookname);
                FinalContent = dbhelper.getMonBook(bible, bookid);
                Log.e("Content", FinalContent.get(0));
            } else if (spaceCounter == 1 && colonCounter == 0 && hypenCounter == 0) {
                String chapname = "";
                for (int i = j; i < inputText.length(); i++) {
                    if (i == 0) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        Bookname = inputText.substring(startpos, endpos);
                        Log.e("BookName:", Bookname);
                    } else if (inputText.charAt(i) == ' ') {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        chapname = inputText.substring(startpos + 1, inputText.length());

                    }
                }
                String bookid = dbhelper.getMonBookId(Bookname);
                if (chapname.equals("1") || chapname.equals("2") || chapname.equals("3") || chapname.equals("4") || chapname.equals("5") || chapname.equals("6") || chapname.equals("7") || chapname.equals("8") || chapname.equals("9")) {
                    chapname = "0" + chapname;
                }
                Log.e("newchap", chapname);
                FinalContent = dbhelper.getMonBookChap(bible, bookid, chapname);
                Log.e("Content", FinalContent.get(0));

            } else if (spaceCounter == 1 && colonCounter == 0 && hypenCounter == 1) {
                String chapstart = "", chapend = "";
                for (int i = j; i < inputText.length(); i++) {
                    if (i == 0) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        Bookname = inputText.substring(startpos, endpos);
                        Log.e("BookName:", Bookname);
                    } else if (inputText.charAt(i) == ' ') {
                        int startpos = i;
                        int endpos = inputText.indexOf("-", startpos);
                        chapstart = inputText.substring(startpos + 1, endpos);
                        chapend = inputText.substring(endpos + 1, inputText.length());

                    }
                }
                String bookid = dbhelper.getMonBookId(Bookname);
                Log.e("Input Text", inputText + "Chapstart: " + chapstart + "Chapend: " + chapend);
                if (chapstart.equals("1") || chapstart.equals("2") || chapstart.equals("3") || chapstart.equals("4") || chapstart.equals("5") || chapstart.equals("6") || chapstart.equals("7") || chapstart.equals("8") || chapstart.equals("9")) {
                    chapstart = "0" + chapstart;
                    Log.e("newchap", chapstart);
                }
                if (chapend.equals("1") || chapend.equals("2") || chapend.equals("3") || chapend.equals("4") || chapend.equals("5") || chapend.equals("6") || chapend.equals("7") || chapend.equals("8") || chapend.equals("9")) {
                    chapend = "0" + chapend;
                    Log.e("newchap", chapend);
                }
                FinalContent = dbhelper.getMonBookChapstartend(bible, bookid, chapstart, chapend);
                Log.e("Content", FinalContent.get(0));
            } else if (spaceCounter == 1 && colonCounter == 1 && hypenCounter == 0) {
                String chapname = "", versename = "";
                int i;
                for (i = j; i < inputText.length(); i++) {
                    if (i == 0) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        Bookname = inputText.substring(startpos, endpos);
                        Log.e("BookName:", Bookname);
                    } else if (inputText.charAt(i) == ' ') {
                        int startpos = i;
                        int endpos = inputText.indexOf(":", startpos);
                        chapname = inputText.substring(startpos + 1, endpos);
                        versename = inputText.substring(endpos + 1, inputText.length());
                        Log.e("ChapterName:", chapname);
                    }
                }
                String bookid = dbhelper.getMonBookId(Bookname);
                if (chapname.equals("1") || chapname.equals("2") || chapname.equals("3") || chapname.equals("4") || chapname.equals("5") || chapname.equals("6") || chapname.equals("7") || chapname.equals("8") || chapname.equals("9")) {
                    chapname = "0" + chapname;
                    Log.e("newchap", chapname);
                }
                if (versename.equals("1") || versename.equals("2") || versename.equals("3") || versename.equals("4") || versename.equals("5") || versename.equals("6") || versename.equals("7") || versename.equals("8") || versename.equals("9")) {
                    versename = "0" + versename;
                    Log.e("newverse", versename);
                }
                FinalContent = dbhelper.getMonBookChapverse(bible, bookid, chapname, versename);
                Log.e("Content", FinalContent.get(0));
            } else if (spaceCounter == 1 && colonCounter == 1 && hypenCounter == 1) {
                String chapstart = "", versestart = "", chapend = "", verseend = "";
                for (int i = j; i < inputText.length(); i++) {
                    if (i == 0) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        Bookname = inputText.substring(startpos, endpos);
                        Log.e("BookName:", Bookname);
                    } else if (inputText.charAt(i) == ' ') {
                        int startpos = i;
                        int endpos = inputText.indexOf(":", startpos);
                        chapstart = inputText.substring(startpos + 1, endpos);
                        Log.e("Chapstart: ", chapstart);
                    } else if (inputText.charAt(i) == ':') {
                        int startpos = i;
                        int endpos = inputText.indexOf("-", startpos);
                        versestart = inputText.substring(startpos + 1, endpos);
                        verseend = inputText.substring(endpos + 1, inputText.length());
                        Log.e("verseStart: ", chapstart + " " + verseend);
                    }
                }
                String bookid = dbhelper.getMonBookId(Bookname);
                if (chapstart.equals("1") || chapstart.equals("2") || chapstart.equals("3") || chapstart.equals("4") || chapstart.equals("5") || chapstart.equals("6") || chapstart.equals("7") || chapstart.equals("8") || chapstart.equals("9")) {
                    chapstart = "0" + chapstart;
                    Log.e("newchap", chapstart);
                }
                if (chapend.equals("1") || chapend.equals("2") || chapend.equals("3") || chapend.equals("4") || chapend.equals("5") || chapend.equals("6") || chapend.equals("7") || chapend.equals("8") || chapend.equals("9")) {
                    chapend = "0" + chapend;
                    Log.e("newchap", chapend);
                }
                if (versestart.equals("1") || versestart.equals("2") || versestart.equals("3") || versestart.equals("4") || versestart.equals("5") || versestart.equals("6") || versestart.equals("7") || versestart.equals("8") || versestart.equals("9")) {
                    versestart = "0" + versestart;
                    Log.e("versestart", chapstart);
                }
                if (verseend.equals("1") || verseend.equals("2") || verseend.equals("3") || verseend.equals("4") || verseend.equals("5") || verseend.equals("6") || verseend.equals("7") || verseend.equals("8") || verseend.equals("9")) {
                    verseend = "0" + verseend;
                    Log.e("verseend", verseend);
                }
                int start_id = dbhelper.getMonChapVerseStartId(bible, bookid, chapstart, versestart);
                Log.e("startid", start_id + "");
                int end_id = dbhelper.getMonChapVerseEndId(bible, bookid, chapstart, verseend);
                Log.e("endid", end_id + "");
                FinalContent = dbhelper.getContentbyrange(bible, start_id, end_id);
            } else if (spaceCounter == 1 && colonCounter == 2 && hypenCounter == 1) {
                String chapstart = "", versestart = "", chapend = "", verseend = "";
                for (int i = j; i < inputText.length(); i++) {
                    if (i == 0) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        Bookname = inputText.substring(startpos, endpos);
                        Log.e("BookName:", Bookname);
                    } else if (inputText.charAt(i) == ' ') {
                        int startpos = i;
                        int endpos = inputText.indexOf(":", startpos);
                        chapstart = inputText.substring(startpos + 1, endpos);
                        Log.e("Input Text", inputText + "Chapstart: " + chapstart);
                    } else if (inputText.charAt(i) == ':') {
                        int startpos = i;
                        int endpos = inputText.indexOf("-", startpos);
                        versestart = inputText.substring(startpos + 1, endpos);
                        Log.e("verseStart: ", versestart);
                    } else if (inputText.charAt(i) == '-') {
                        int startpos = i;
                        int endpos = inputText.indexOf(":", startpos);
                        chapend = inputText.substring(startpos + 1, endpos);
                        verseend = inputText.substring(endpos + 1, inputText.length());
                        i = inputText.length() - 1;
                        Log.e("chapend: ", chapend);
                        Log.e("verseend: ", verseend);
                    }
                }
                String bookid = dbhelper.getMonBookId(Bookname);
                if (chapstart.equals("1") || chapstart.equals("2") || chapstart.equals("3") || chapstart.equals("4") || chapstart.equals("5") || chapstart.equals("6") || chapstart.equals("7") || chapstart.equals("8") || chapstart.equals("9")) {
                    chapstart = "0" + chapstart;
                    Log.e("newchap", chapstart);
                }
                if (chapend.equals("1") || chapend.equals("2") || chapend.equals("3") || chapend.equals("4") || chapend.equals("5") || chapend.equals("6") || chapend.equals("7") || chapend.equals("8") || chapend.equals("9")) {
                    chapend = "0" + chapend;
                    Log.e("newchap", chapend);
                }
                if (versestart.equals("1") || versestart.equals("2") || versestart.equals("3") || versestart.equals("4") || versestart.equals("5") || versestart.equals("6") || versestart.equals("7") || versestart.equals("8") || versestart.equals("9")) {
                    versestart = "0" + versestart;
                    Log.e("versestart", chapstart);
                }
                if (verseend.equals("1") || verseend.equals("2") || verseend.equals("3") || verseend.equals("4") || verseend.equals("5") || verseend.equals("6") || verseend.equals("7") || verseend.equals("8") || verseend.equals("9")) {
                    verseend = "0" + verseend;
                    Log.e("verseend", verseend);
                }
                int start_id = dbhelper.getMonChapVerseStartId(bible, bookid, chapstart, versestart);
                int end_id = dbhelper.getMonChapVerseEndId(bible, bookid, chapend, verseend);
                FinalContent = dbhelper.getContentbyrange(bible, start_id, end_id);

                sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor edit_gen = sharedPreferences.edit();
                //edit_gen.putString("FinalContent", FinalContent.get(0));
                edit_gen.commit();
                Log.e("Content", FinalContent.get(0));
            } else if (spaceCounter == 2 && colonCounter == 0 && hypenCounter == 0) {
                String chapname = "";
                int i;
                for (i = j; i < inputText.length(); i++) {
                    if (i == 0) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        int nxtstartpos = endpos + 1;
                        int nxtendpos = inputText.indexOf(" ", nxtstartpos);
                        Bookname = inputText.substring(startpos, nxtendpos);
                        Log.e("BookName:", Bookname);
                        i = nxtendpos - 1;
                    } else if (inputText.charAt(i) == ' ') {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        chapname = inputText.substring(startpos + 1, inputText.length());
                        Log.e("ChapterName:", chapname);
                    }
                }
                String bookid = dbhelper.getMonBookId(Bookname);
                if (chapname.equals("1") || chapname.equals("2") || chapname.equals("3") || chapname.equals("4") || chapname.equals("5") || chapname.equals("6") || chapname.equals("7") || chapname.equals("8") || chapname.equals("9")) {
                    chapname = "0" + chapname;
                    Log.e("newchap", chapname);
                }
                FinalContent = dbhelper.getMonBookChap(bible, bookid, chapname);
                Log.e("Content", FinalContent.get(0));
            } else if (spaceCounter == 2 && colonCounter == 0 && hypenCounter == 1) {
                String chapstart = "", chapend = "";
                for (int i = j; i < inputText.length(); i++) {
                    if (i == 0) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        int nxtstartpos = endpos + 1;
                        int nxtendpos = inputText.indexOf(" ", nxtstartpos);
                        Bookname = inputText.substring(startpos, nxtendpos);
                        Log.e("BookName:", Bookname);
                        i = nxtendpos - 1;
                    } else if (inputText.charAt(i) == ' ') {
                        int startpos = i;
                        int endpos = inputText.indexOf("-", startpos);
                        chapstart = inputText.substring(startpos + 1, endpos);
                        chapend = inputText.substring(endpos + 1, inputText.length());
                        Log.e("Input Text", inputText + "Chapstart: " + chapstart + "Chapend: " + chapend);
                    }
                }
                String bookid = dbhelper.getMonBookId(Bookname);
                if (chapstart.equals("1") || chapstart.equals("2") || chapstart.equals("3") || chapstart.equals("4") || chapstart.equals("5") || chapstart.equals("6") || chapstart.equals("7") || chapstart.equals("8") || chapstart.equals("9")) {
                    chapstart = "0" + chapstart;
                    Log.e("newchap", chapstart);
                }
                if (chapend.equals("1") || chapend.equals("2") || chapend.equals("3") || chapend.equals("4") || chapend.equals("5") || chapend.equals("6") || chapend.equals("7") || chapend.equals("8") || chapend.equals("9")) {
                    chapend = "0" + chapend;
                    Log.e("newchap", chapend);
                }
                FinalContent = dbhelper.getMonBookChapstartend(bible, bookid, chapstart, chapend);
                Log.e("Content", FinalContent.get(0));
            } else if (spaceCounter == 2 && colonCounter == 1 && hypenCounter == 0) {
                String chapstart = "", chapend = "";
                for (int i = j; i < inputText.length(); i++) {
                    if (i == 0) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        int nxtstartpos = endpos + 1;
                        int nxtendpos = inputText.indexOf(" ", nxtstartpos);
                        Bookname = inputText.substring(startpos, nxtendpos);
                        Log.e("BookName:", Bookname);
                        i = nxtendpos - 1;
                    } else if (inputText.charAt(i) == ' ') {
                        int startpos = i;
                        int endpos = inputText.indexOf(":", startpos);
                        chapstart = inputText.substring(startpos + 1, endpos);
                        chapend = inputText.substring(endpos + 1, inputText.length());
                        Log.e("Input Text", inputText + "Chapstart: " + chapstart + "Chapend: " + chapend);
                    }
                }
                String bookid = dbhelper.getMonBookId(Bookname);
                if (chapstart.equals("1") || chapstart.equals("2") || chapstart.equals("3") || chapstart.equals("4") || chapstart.equals("5") || chapstart.equals("6") || chapstart.equals("7") || chapstart.equals("8") || chapstart.equals("9")) {
                    chapstart = "0" + chapstart;
                    Log.e("newchap", chapstart);
                }
                if (chapend.equals("1") || chapend.equals("2") || chapend.equals("3") || chapend.equals("4") || chapend.equals("5") || chapend.equals("6") || chapend.equals("7") || chapend.equals("8") || chapend.equals("9")) {
                    chapend = "0" + chapend;
                    Log.e("newchap", chapend);
                }
                FinalContent = dbhelper.getMonBookChapstartend(bible, bookid, chapstart, chapend);
                Log.e("Content", FinalContent.get(0));
            } else if (spaceCounter == 2 && colonCounter == 1 && hypenCounter == 1) {
                String chapstart = "", chapend = "";
                String versestart = "", verseend = "";
                for (int i = j; i < inputText.length(); i++) {
                    if (i == 0) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        int nxtstartpos = endpos + 1;
                        int nxtendpos = inputText.indexOf(" ", nxtstartpos);
                        Bookname = inputText.substring(startpos, nxtendpos);
                        Log.e("BookName:", Bookname);
                        //i = nxtendpos - 1;
                    } else if (inputText.charAt(i) == ' ') {
                        int startpos = i;
                        int endpos = inputText.indexOf(":", startpos);
                        chapstart = inputText.substring(startpos + 1, endpos);
                    } else if (inputText.charAt(i) == ':') {
                        int startpos = i;
                        int endpos = inputText.indexOf("-", startpos);
                        versestart = inputText.substring(startpos + 1, endpos);
                        verseend = inputText.substring(endpos + 1, inputText.length());
                        Log.e("verseStart: ", chapstart + " " + verseend);
                    }
                }
                String bookid = dbhelper.getMonBookId(Bookname);
                if (chapstart.equals("1") || chapstart.equals("2") || chapstart.equals("3") || chapstart.equals("4") || chapstart.equals("5") || chapstart.equals("6") || chapstart.equals("7") || chapstart.equals("8") || chapstart.equals("9")) {
                    chapstart = "0" + chapstart;
                    Log.e("newchap", chapstart);
                }

                if (versestart.equals("1") || versestart.equals("2") || versestart.equals("3") || versestart.equals("4") || versestart.equals("5") || versestart.equals("6") || versestart.equals("7") || versestart.equals("8") || versestart.equals("9")) {
                    versestart = "0" + versestart;
                    Log.e("versestart", chapstart);
                }
                if (verseend.equals("1") || verseend.equals("2") || verseend.equals("3") || verseend.equals("4") || verseend.equals("5") || verseend.equals("6") || verseend.equals("7") || verseend.equals("8") || verseend.equals("9")) {
                    verseend = "0" + verseend;
                    Log.e("verseend", verseend);
                }
                FinalContent = dbhelper.getMonChapVerse(bible, bookid, chapstart, versestart, verseend);
                Log.e("Content", FinalContent.get(0));
            } else if (spaceCounter == 2 && colonCounter == 2 && hypenCounter == 1) {
                String chapstart = "", chapend = "";
                String versestart = "", verseend = "";
                for (int i = j; i < inputText.length(); i++) {
                    if (i == 0) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        int nxtstartpos = endpos + 1;
                        int nxtendpos = inputText.indexOf(" ", nxtstartpos);
                        Bookname = inputText.substring(startpos, nxtendpos);
                        Log.e("BookName:", Bookname);
                        i = nxtendpos - 1;
                    } else if (inputText.charAt(i) == ' ') {
                        int startpos = i;
                        int endpos = inputText.indexOf(":", startpos);
                        chapstart = inputText.substring(startpos + 1, endpos);
                        Log.e("Input Text", inputText + "Chapstart: " + chapstart);
                    } else if (inputText.charAt(i) == ':') {
                        int startpos = i;
                        int endpos = inputText.indexOf("-", startpos);
                        versestart = inputText.substring(startpos + 1, endpos);
                        Log.e("verseStart: ", chapstart + " " + verseend);
                    } else if (inputText.charAt(i) == '-') {
                        int startpos = i;
                        int endpos = inputText.indexOf(":", startpos);
                        chapend = inputText.substring(startpos + 1, endpos);
                        verseend = inputText.substring(endpos + 1, inputText.length());
                        i = inputText.length() - 1;
                        Log.e("chapend: ", chapend);
                        Log.e("verseend: ", verseend);
                    }
                }
                String bookid = dbhelper.getMonBookId(Bookname);
                if (chapstart.equals("1") || chapstart.equals("2") || chapstart.equals("3") || chapstart.equals("4") || chapstart.equals("5") || chapstart.equals("6") || chapstart.equals("7") || chapstart.equals("8") || chapstart.equals("9")) {
                    chapstart = "0" + chapstart;
                    Log.e("newchap", chapstart);
                }
                if (chapend.equals("1") || chapend.equals("2") || chapend.equals("3") || chapend.equals("4") || chapend.equals("5") || chapend.equals("6") || chapend.equals("7") || chapend.equals("8") || chapend.equals("9")) {
                    chapend = "0" + chapend;
                    Log.e("newchap", chapend);
                }
                if (versestart.equals("1") || versestart.equals("2") || versestart.equals("3") || versestart.equals("4") || versestart.equals("5") || versestart.equals("6") || versestart.equals("7") || versestart.equals("8") || versestart.equals("9")) {
                    versestart = "0" + versestart;
                    Log.e("versestart", chapstart);
                }
                if (verseend.equals("1") || verseend.equals("2") || verseend.equals("3") || verseend.equals("4") || verseend.equals("5") || verseend.equals("6") || verseend.equals("7") || verseend.equals("8") || verseend.equals("9")) {
                    verseend = "0" + verseend;
                    Log.e("verseend", verseend);
                }
                int start_id = dbhelper.getMonChapVerseStartId(bible, bookid, chapstart, versestart);
                int end_id = dbhelper.getMonChapVerseEndId(bible, bookid, chapend, verseend);
                FinalContent = dbhelper.getContentbyrange(bible, start_id, end_id);

                Log.e("Content", FinalContent.get(0));

            } else if (spaceCounter == 4 && colonCounter == 0 && hypenCounter == 0) {
                String bookname1 = "", bookname2 = "";
                String versestart = "", verseend = "";
                for (int i = j; i < inputText.length(); i++) {
                    if (i == 0) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        Bookname = inputText.substring(startpos, endpos);
                        Log.e("BookName:", Bookname);
                        i = endpos - 1;
                    } else if (inputText.charAt(i) == ' ') {
                        if (i == 3) {
                            int startpos = i;
                            int endpos = inputText.indexOf(" ", startpos);
                            int nxtstartpos = endpos + 1;
                            int nxtendpos = inputText.indexOf(" ", nxtstartpos);
                            int nxtstartposs = nxtendpos + 1;
                            int nxtendposs = inputText.indexOf(" ", nxtstartposs);
                            bookname1 = inputText.substring(startpos + 1, nxtendposs);
                            Log.e("BookName1:", bookname1);
                        } else if (i == 8) {
                            int startpos = i;
                            int endpos = inputText.indexOf(" ", startpos);
//                            int nxtstartpos = endpos + 1;
//                            int nxtendpos = inputText.indexOf(" ", nxtstartpos);
//                            int nxtstartposs = nxtendpos + 1;
                            //int nxtendposs = inputText.indexOf(" ", nxtstartposs);
                            bookname2 = inputText.substring(startpos + 1, inputText.length());
                            Log.e("BookName2:", bookname2);
                        }

                    }
                }
                String bookid = dbhelper.getMonBookId(Bookname);
                String bookid1 = dbhelper.getMonBookId(bookname1);
                String bookid2 = dbhelper.getMonBookId(bookname2);
                ArrayList<String> FC=new ArrayList();
                FinalContent = dbhelper.getMonBook(bible, bookid);
                for(int i=0;i<FinalContent.size();i++)
                {
                    FC.add(FinalContent.get(i));
                }
                FinalContent =  dbhelper.getMonBook(bible, bookid1);
                for(int i=0;i<FinalContent.size();i++)
                {
                    FC.add(FinalContent.get(i));
                }
                FinalContent = dbhelper.getMonBook(bible, bookid2);
                for(int i=0;i<FinalContent.size();i++)
                {
                    FC.add(FinalContent.get(i));
                }
                FinalContent=FC;
                Log.e("Content", FinalContent.get(0));
            } else if (spaceCounter == 3 && colonCounter == 0 && hypenCounter == 1) {
                String chapstart = "", chapend = "";
                String Bookname1 = "", Bookname2 = "";
                for (int i = j; i < inputText.length(); i++) {
                    if (i == 0) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        Bookname1 = inputText.substring(startpos, endpos);
                        Log.e("BookName:", Bookname1);
                    } else if (i == 5) {
                        int startpos = i;
                        int endpos = inputText.indexOf(" ", startpos);
                        int nxtstartpos = endpos + 1;
                        int nxtendpos = inputText.indexOf(" ", nxtstartpos);
                        Bookname2 = inputText.substring(startpos, nxtendpos);
                        Log.e("BookName:", Bookname2);
                        i = nxtendpos - 1;
                    } else if (i != 4) {
                        if (inputText.charAt(i) == ' ') {
                            int startpos = i;
                            int endpos = inputText.indexOf("-", startpos);
                            chapstart = inputText.substring(startpos + 1, endpos);
                            chapend = inputText.substring(endpos + 1, inputText.length());
                            Log.e("Input Text", inputText + "Chapstart: " + chapstart + "Chapend: " + chapend);
                        }
                    }
                }
                String bookid1 = dbhelper.getMonBookId(Bookname1);
                String bookid2 = dbhelper.getMonBookId(Bookname2);
                if (chapstart.equals("1") || chapstart.equals("2") || chapstart.equals("3") || chapstart.equals("4") || chapstart.equals("5") || chapstart.equals("6") || chapstart.equals("7") || chapstart.equals("8") || chapstart.equals("9")) {
                    chapstart = "0" + chapstart;
                    Log.e("newchap", chapstart);
                }
                if (chapend.equals("1") || chapend.equals("2") || chapend.equals("3") || chapend.equals("4") || chapend.equals("5") || chapend.equals("6") || chapend.equals("7") || chapend.equals("8") || chapend.equals("9")) {
                    chapend = "0" + chapend;
                    Log.e("newchap", chapend);
                }
                ArrayList<String> FC = new ArrayList();
                FinalContent = dbhelper.getMonBook(bible, bookid1);
                for(int i=0;i<FinalContent.size();i++)
                {
                    FC.add(FinalContent.get(i));
                }
                FinalContent = dbhelper.getMonBookChapstartend(bible, bookid2, chapstart, chapend);
                for(int i=0;i<FinalContent.size();i++)
                {
                    FC.add(FinalContent.get(i));
                }
                FinalContent=FC;
//                FinalContent += dbhelper.getMonBookChapstartend(bookid2, chapstart, chapend);
                //int id1=dbhelper.getMonBookChapstartId(bookid2, chapstart);
                //int id2=dbhelper.getMonBookChapendId(bookid2, chapend);

                // FinalContent +=dbhelper.getContentbyrange(id1,id2);
                Log.e("Content", FinalContent.get(0));
            }
        }
        return FinalContent;
    }
    private void search() {
        if (this.pop_search_word == null) {
            this.pop_search_word = new Dialog(CalContentView.this);
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
            btnSumbit.setOnClickListener(new OnClickListener( ) {
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
            btnCancel.setOnClickListener(new OnClickListener( ) {
                @Override
                public void onClick(View view) {
                    pop_search_word.dismiss( );
                }
            });

        } else if (this.pop_search_word.isShowing( )) {
            this.pop_search_word.dismiss( );
            this.pop_search_word = null;
            search();
        } else {
            this.pop_search_word = null;
            search();
        }
    }
    public void onResume() {
        super.onResume( );
        SetFirstTitle( );
    }


//    private void search() {
//        Dialog pop_search_word=null;
//        if (pop_search_word == null) {
//            pop_search_word = new Dialog(CalContentView.this);
//            pop_search_word.requestWindowFeature(1);
//            pop_search_word.setTitle("Search Option");
//            pop_search_word.setCanceledOnTouchOutside(true);
//            pop_search_word.setCancelable(true);
//            pop_search_word.setContentView(R.layout.popup_search);
//            final EditText edtSearchWord = (EditText) pop_search_word.findViewById(R.id.edt_search_word);
//            Button btnSumbit = (Button) pop_search_word.findViewById(R.id.btn_submit);
//            Button btnCancel = (Button) pop_search_word.findViewById(R.id.btn_cancel);
//            pop_search_word.show();
//            // ImageView ivclose = (ImageView) pop_search_word.findViewById(R.id.iv_close);
//
////            ivclose.setOnClickListener(new SampleFragment.C02636( ));
//            btnSumbit.setOnClickListener(new OnClickListener() {
//                public void onClick(View v) {
//                    String searchedWord = edtSearchWord.getText().toString();
//                    if (searchedWord == " ") {
//                        edtSearchWord.setError("Enter a word to search");
//                        return;
//                    }
//                    Intent i = new Intent(getApplicationContext(), SearchList.class);
//                    i.putExtra("searchword", searchedWord);
//                    startActivity(i);
//                }
//            });
//            edtSearchWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                    if (event.getKeyCode() == 66) {
//                        String searchedWord = edtSearchWord.getText().toString();
//                        if (searchedWord == " ") {
//                            edtSearchWord.setError("Enter a word to search");
//                        } else {
//                            Intent i = new Intent(getApplicationContext(), SearchList.class);
//                            i.putExtra("searchword", searchedWord);
//                            startActivity(i);
//                        }
//                    }
//                    return true;
//                }
//            });
//            final Dialog finalPop_search_word = pop_search_word;
//            btnCancel.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    finalPop_search_word.dismiss();
//                }
//            });
//
//        } else if (pop_search_word.isShowing()) {
//            pop_search_word.dismiss();
//            pop_search_word = null;
//            search();
//        } else {
//            pop_search_word = null;
//            search();
//        }
//    }

}
