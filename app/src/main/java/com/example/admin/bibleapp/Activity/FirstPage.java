package com.example.admin.bibleapp.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.Adapter.BookAdapter;
import com.example.admin.bibleapp.Adapter.ChapterAdapter;
import com.example.admin.bibleapp.Adapter.FirstPageListview;
import com.example.admin.bibleapp.Adapter.VerseAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Views.OnBlurCompleteListener;
import com.example.admin.bibleapp.Views.OnSwipeTouchListener2;
import com.example.admin.bibleapp.Parser.Employee;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Views.customwebview;
import com.example.admin.bibleapp.popupWindow.popFlashcard;
import com.example.admin.bibleapp.popupWindow.popFontSize;
import com.example.admin.bibleapp.popupWindow.popReadHistory;
import com.example.admin.bibleapp.popupWindow.popReset;
import com.example.admin.bibleapp.popupWindow.popSearch;
import com.example.admin.bibleapp.popupWindow.pop_activity_about;
import com.example.admin.bibleapp.util.UiUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.appindexing.Thing;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.GoogleApiClient.Builder;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.admin.bibleapp.Utility.setListViewHeightBasedOnChildren;

public class FirstPage extends AppCompatActivity implements GestureDetector.OnGestureListener {
    public static final String MyPREFERENCES = "MyPrefs";
    private static final String TAG = FirstPage.class.getName( );
    Typeface RobotoSlabLight;
    Typeface RobotoSlabThin;
    private GestureDetector productGestureDetector;
    String Tag1;
    VerseAdapter adapt;
    BookAdapter adapter;
    TextView back;
    TextView bb_bookchap;
    TextView backBook;
    TextView backChap;
    Toolbar toolbar;
    // String bible;
    String bookid;
    String bookno = "43";
    String chapno = "01";
    //    private GoogleApiClient client;
    String content = "";
    DBhelper dbhelper;
    DrawerLayout drawer;
    List<Employee> employees = null;
    LinearLayout swipe;
    NestedScrollView Scroll_new;
    FloatingActionButton fab;
    FirstPageListview adapter1;
    Animation fabAntiClock;
    Animation fabClock;
    Animation fabClose;
    // Animation fabOpen;
    boolean flag = true;
    View row;
    //float fontSize;
    TextView goBook;
    TextView goChap;
    GridView grid_chap;
    RelativeLayout first_page;
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
    String intent_bookid;
    String intent_chapid;
    String intent_verseid;
    boolean isOpen = false;
    ArrayList<Integer> pos = new ArrayList<>( );
    int count = 0;
    ImageView ivLeftNav;
    ImageView ivRightNav;
    ImageView iv_bible;
    ImageView iv_monthlyCal;
    ImageView iv_nav;
    String lastSeenBook;
    String lastSeenChap;
    LinearLayout layNav;
    int sflag = 0;
    LinearLayout layOption;
    View layout;
    RecyclerView.LayoutManager mLayoutManager;
    private int mScrollY;
    Typeface monBold;
    Typeface monLight;
    Typeface monRegular;
    TextView newTestament;
    int noofchap;
    TextView oldTestament;
    TextView tvTitle;
    Animation optionClose;
    LinearLayout optionLay;
    Animation optionOpen;
    ProgressDialog pd;
    String planId;
    PopupWindow pop;
    TextView popBookNo;
    TextView popChapNo;
    TextView popVerseNo;
    LinearLayout poplay;
    String sample = "";
    String[] lv_sample;
    Integer[] lv_verseid;
    ArrayList<String> lvsample = new ArrayList( );
    ArrayList<Integer> vid = new ArrayList( );
    String sample1 = " ";
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
    Typeface tribbon;
    ImageView tv_house;
    ListView lv_content;
    customwebview wv_Content;
    customwebview wv_Content1;
    String bible;
    Bundle bundle;
    String date = " ";
    DBhelper db;
    Animation fabOpen;
    BottomBar bottomBar;
    float fontSize;
    private int mScrollYY;
    private String mSelectedText = "";
    String oldread = "";
    LinearLayout option;
    Dialog pop_search_word;
    boolean read = false;
    String title = " ";
    TextView tvcopy;
    TextView tvnotes;
    TextView tvcompare;
    TextView tvflashcard;
    int onclick_flag = 0;
    LinearLayout pop_lay;
    ArrayList<String> onclick_value = new ArrayList( );
    ArrayList<String> c_vid = new ArrayList();
    WebSettings webSettings;
    WebView wvContent;
    private static final int SWIPE_THRESHOLD = 150;
    private static final int SWIPE_VELOCITY_THRESHOLD = 150;

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


    class C02251 extends WebChromeClient {

        class C02241 implements Runnable {
            C02241() {
            }

            public void run() {
                Log.d("scroll y", "Scrolly" + FirstPage.this.mScrollY);
                FirstPage.this.wv_Content.scrollTo(0, FirstPage.this.mScrollY);
            }
        }

        C02251() {
        }

        public void onProgressChanged(WebView view, int progress) {
            if (view.getProgress( ) == 100) {
                FirstPage.this.wv_Content.postDelayed(new C02241( ), 100);
                Log.e("String out urllllll", view + "," + 16908299);
            }
        }
    }

    class WebClient extends WebViewClient {
        WebClient() {
        }

        public void onPageFinished(WebView view, String url) {
            // FirstPage.this.wv_Content.loadUrl("javascript:AndroidFunction.resize(document.body.scrollHeight)");

        }
    }

    class C04804 implements OnBlurCompleteListener {
        C04804() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(FirstPage.this, bibleContentSelect.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            FirstPage.this.startActivity(intent);
        }
    }

    class C04815 implements OnBlurCompleteListener {
        C04815() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(FirstPage.this, MonthlyCal.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            FirstPage.this.startActivity(intent);
        }
    }

    class C04826 implements OnBlurCompleteListener {
        C04826() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(FirstPage.this, pop_activity_about.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            FirstPage.this.startActivity(intent);
        }
    }

    class C04837 implements OnBlurCompleteListener {
        C04837() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(FirstPage.this, popReset.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            FirstPage.this.startActivity(intent);
        }
    }

    class C04848 implements OnBlurCompleteListener {
        C04848() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(FirstPage.this, popSearch.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            FirstPage.this.startActivity(intent);
        }
    }

    class C04859 implements OnBlurCompleteListener {
        C04859() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(FirstPage.this, popFontSize.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            FirstPage.this.startActivity(intent);
        }
    }

    int currentFirst = 0;
    int currentLast = 0;

    public void onScroll(AbsListView view, int firstVisible,
                         int visibleCount, int totalItems) {
        int lastVisible = firstVisible + visibleCount - 1;
        if (currentFirst != firstVisible && currentLast != lastVisible) {
            Toast.makeText(getApplicationContext( ), "first visible position = " + firstVisible
                    + ", last visible position = " + lastVisible, Toast.LENGTH_SHORT).show( );
            currentFirst = firstVisible;
            currentLast = lastVisible;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_first_page);
        toolbar = (Toolbar) findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);
        tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("Bible");
        this.state = Environment.getExternalStorageState( );
        option = (LinearLayout) findViewById(R.id.pop_lay_options);
        tvcopy = (TextView) findViewById(R.id.tv_copy);
        tvcompare = (TextView) findViewById(R.id.tv_compare);
        tvnotes = (TextView) findViewById(R.id.tv_mynote);
        productGestureDetector = new GestureDetector(FirstPage.this);
        bottomBar= (BottomBar) findViewById(R.id.bottomBar);
        lv_content = (ListView) findViewById(R.id.lv_content);
        option.setVisibility(View.GONE);
        //this.pd = new ProgressDialog(this, R.style.MyTheme);
//        this.pd.setCancelable(false);
        //      this.pd.setProgressStyle(16973854);
        //    this.pd.show();
        this.monBold = Typeface.createFromAsset(getAssets( ), "fonts/monBold.ttf");
        this.monRegular = Typeface.createFromAsset(getAssets( ), "fonts/Mon.ttf");
        this.monLight = Typeface.createFromAsset(getAssets( ), "fonts/monLight.ttf");
        this.RobotoSlabLight = Typeface.createFromAsset(getAssets( ), "fonts/RobotoSlabLight.ttf");
        this.RobotoSlabThin = Typeface.createFromAsset(getAssets( ), "fonts/RobotoSlabThin.ttf");
        this.tribbon = Typeface.createFromAsset(getAssets( ), "fonts/tribbon.ttf");
        swipe= (LinearLayout)findViewById(R.id.swipe);
//        this.wv_Content = (customwebview) findViewById(R.id.wv_content);
//        this.wv_Content1 = (customwebview) findViewById(R.id.wv_content);
        this.layNav = (LinearLayout) findViewById(R.id.lay_nav);
        first_page = (RelativeLayout)findViewById(R.id.content_first_page);
//        first_page.getForeground().setAlpha(0);
        this.ivLeftNav = (ImageView) findViewById(R.id.left_nav);
        this.ivRightNav = (ImageView) findViewById(R.id.right_nav);
        tvflashcard = (TextView) findViewById(R.id.tv_flashcard);
        Scroll_new = (NestedScrollView) findViewById(R.id.scroll_new);
//        lv_content.setOnTouchListener(new View.OnTouchListener() {
//            // Setting on Touch Listener for handling the touch inside ScrollView
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // Disallow the touch request for parent scroll on touch of child view
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
//        this.optionOpen = AnimationUtils.loadAnimation(getApplicationContext(), C0246R.anim.grow_from_topleft_to_bottomright);
        //      this.optionClose = AnimationUtils.loadAnimation(getApplicationContext(), C0246R.anim.shrink_from_bottomright_to_topleft);
//        this.fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), C0246R.anim.fab_open);
//        this.fabClose = AnimationUtils.loadAnimation(getApplicationContext(), C0246R.anim.fab_close);
//        this.fabClock = AnimationUtils.loadAnimation(getApplicationContext(), C0246R.anim.rotate_clock);
//        this.fabAntiClock = AnimationUtils.loadAnimation(getApplicationContext(), C0246R.anim.rotate_anticlock);
        this.dbhelper = new DBhelper(this, getFilesDir( ).getAbsolutePath( ));
        try {
            this.dbhelper.prepareDatabase( );
        } catch (IOException e) {
            Log.e(TAG, e.getMessage( ));
        }
//        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        this.mLayoutManager = new LinearLayoutManager(this);
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        this.planId = this.sharedPreferences.getString("plan", null);
        bb_bookchap = (TextView)findViewById(R.id.tv_bookchap);
        this.bible = this.sharedPreferences.getString("bible", null);
        this.table_plan = this.sharedPreferences.getString("tablePlan", null);
        String flot_size = this.sharedPreferences.getString("fontsize", null);
        this.lastSeenBook = this.sharedPreferences.getString("lastSeenBook", null);
        this.lastSeenChap = this.sharedPreferences.getString("lastSeenChap", null);
        if (flot_size == null) {
            this.fontSize = 15.0f;
        } else {
            this.fontSize = Float.parseFloat(flot_size);
        }
        Editor edit = this.sharedPreferences.edit( );
        edit.putString("firsttime", "true");
        edit.commit( );
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawer.setDrawerListener(toggle);
        toggle.syncState( );
//       this.wv_Content.setWebChromeClient(new C02251());
//        this.wv_Content.getSettings().setDefaultFontSize((int) this.fontSize);
//        this.wv_Content.getSettings().setDefaultTextEncodingName("utf-8");
//        this.wv_Content.setWebViewClient(new WebClient());
//        this.wv_Content.getSettings().setLoadWithOverviewMode(true);
//        this.wv_Content.setWebChromeClient(new WebChromeClient());
        if (this.lastSeenBook == null || this.lastSeenChap == null) {
            this.lastSeenBook = this.bookno;
            this.lastSeenChap = this.chapno;
//            this.sample = this.dbhelper.getMonBookChap(this.bible, this.lastSeenBook, this.lastSeenChap);
        } else {
//            this.sample = this.dbhelper.getMonBookChap(this.bible, this.lastSeenBook, this.lastSeenChap);
        }
        bottomBar.selectTabWithId(R.id.tab_read);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                if(tabId==R.id.tab_home)
                {
                    Intent i = new Intent(FirstPage.this, CalPage.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }
                else if(tabId==R.id.tab_media)
                {
                    Intent i = new Intent(FirstPage.this, MediaActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }
                else if(tabId==R.id.tab_plans)
                {
                    Intent i = new Intent(FirstPage.this,MonthlyCal.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }
                else if(tabId==R.id.tab_pray)
                {
                    Intent i = new Intent(FirstPage.this,myprayergroup.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }
                else if (tabId == R.id.tab_page) {
                    Intent i = new Intent(FirstPage.this, PageActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);

                }
            }
        });
        this.content = this.sample;
        this.ivRightNav.setOnClickListener(new OnClickListener( ) {
            public void onClick(View v) {
                onclick_flag = 0;
                onclick_value.clear( );
                c_vid.clear();
                pos.clear( );
                count = 0;
                if (count == 0 && pos.size( ) == 0) {
                    option.setVisibility(View.GONE);
                }
                int lastchapno = FirstPage.this.dbhelper.getNoofChap(FirstPage.this.bible, FirstPage.this.lastSeenBook);
                int int_chapno = Integer.parseInt(FirstPage.this.lastSeenChap);
                int int_bookno = Integer.parseInt(FirstPage.this.lastSeenBook);
                if (int_chapno < lastchapno) {
                    int_chapno++;
                    FirstPage.this.lastSeenChap = String.valueOf(int_chapno);
                    if (FirstPage.this.lastSeenChap.equals("1") || FirstPage.this.lastSeenChap.equals("2") || FirstPage.this.lastSeenChap.equals("3") || FirstPage.this.lastSeenChap.equals("4") || FirstPage.this.lastSeenChap.equals("5") || FirstPage.this.lastSeenChap.equals("6") || FirstPage.this.lastSeenChap.equals("7") || FirstPage.this.lastSeenChap.equals("8") || FirstPage.this.lastSeenChap.equals("9") || FirstPage.this.lastSeenChap.equals("0")) {
                        FirstPage.this.lastSeenChap = "0" + FirstPage.this.lastSeenChap;
                    }
                } else {
                    int_bookno++;
                    FirstPage.this.lastSeenBook = String.valueOf(int_bookno);
                    if (FirstPage.this.lastSeenBook.equals("1") || FirstPage.this.lastSeenBook.equals("2") || FirstPage.this.lastSeenBook.equals("3") || FirstPage.this.lastSeenBook.equals("4") || FirstPage.this.lastSeenBook.equals("5") || FirstPage.this.lastSeenBook.equals("6") || FirstPage.this.lastSeenBook.equals("7") || FirstPage.this.lastSeenBook.equals("8") || FirstPage.this.lastSeenBook.equals("9") || FirstPage.this.lastSeenBook.equals("0")) {
                        FirstPage.this.lastSeenBook = "0" + FirstPage.this.lastSeenBook;
                    }
                    FirstPage.this.lastSeenChap = "01";
                }

//                FirstPage.this.sample = FirstPage.this.dbhelper.getMonBookChap(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
//                FirstPage.this.sample = "<div style='line-height:150%;text-align: justify;'>" + FirstPage.this.sample + "</font></div>";
                vid = FirstPage.this.dbhelper.getMonVerid(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
                lvsample = FirstPage.this.dbhelper.getMonBookChapVer(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
                lv_verseid = new Integer[lvsample.size( )];
                for (int i = 0; i < lvsample.size( ); i++) {
                    lv_verseid[i] = vid.get(i);
                }
                lv_sample = new String[lvsample.size( )];
                for (int i = 0; i < lvsample.size( ); i++) {
                    lv_sample[i] = lvsample.get(i);
                }
                adapter1 = new FirstPageListview(FirstPage.this, lv_sample, lv_verseid, fontSize, pos);
                lv_content.setAdapter(adapter1);
                setListViewHeightBasedOnChildren(lv_content);
                Scroll_new.scrollTo(0, 0);
                title = FirstPage.this.dbhelper.getBookName(FirstPage.this.lastSeenBook) + " " + FirstPage.this.lastSeenChap;
                tvTitle.setText(FirstPage.this.dbhelper.getBookName(FirstPage.this.lastSeenBook) + " " + FirstPage.this.lastSeenChap);
                bb_bookchap.setText(title);
                //FirstPage.this.wv_Content.loadDataWithBaseURL("", FirstPage.this.sample, "text/html", "utf-8", null);
            }
        });
        this.ivLeftNav.setOnClickListener(new OnClickListener( ) {
            public void onClick(View v) {
                onclick_flag = 0;
                onclick_value.clear( );
                c_vid.clear();
                count = 0;
                pos.clear( );
                if (count == 0 && pos.size( ) == 0) {
                    option.setVisibility(View.GONE);
                }
                int lastchapno = FirstPage.this.dbhelper.getNoofChap(FirstPage.this.bible, FirstPage.this.lastSeenBook);
                int int_chapno = Integer.parseInt(FirstPage.this.lastSeenChap);
                int int_bookno = Integer.parseInt(FirstPage.this.lastSeenBook);
                int_chapno--;
                if (int_chapno > 0) {
                    FirstPage.this.lastSeenChap = String.valueOf(int_chapno);
                    if (FirstPage.this.lastSeenChap.equals("1") || FirstPage.this.lastSeenChap.equals("2") || FirstPage.this.lastSeenChap.equals("3") || FirstPage.this.lastSeenChap.equals("4") || FirstPage.this.lastSeenChap.equals("5") || FirstPage.this.lastSeenChap.equals("6") || FirstPage.this.lastSeenChap.equals("7") || FirstPage.this.lastSeenChap.equals("8") || FirstPage.this.lastSeenChap.equals("9") || FirstPage.this.lastSeenChap.equals("0")) {
                        FirstPage.this.lastSeenChap = "0" + FirstPage.this.lastSeenChap;
                    }
                } else {
                    int_bookno--;
                    if (int_bookno > 0) {
                        FirstPage.this.lastSeenBook = String.valueOf(int_bookno);
                        if (FirstPage.this.lastSeenBook.equals("1") || FirstPage.this.lastSeenBook.equals("2") || FirstPage.this.lastSeenBook.equals("3") || FirstPage.this.lastSeenBook.equals("4") || FirstPage.this.lastSeenBook.equals("5") || FirstPage.this.lastSeenBook.equals("6") || FirstPage.this.lastSeenBook.equals("7") || FirstPage.this.lastSeenBook.equals("8") || FirstPage.this.lastSeenBook.equals("9") || FirstPage.this.lastSeenBook.equals("0")) {
                            FirstPage.this.lastSeenBook = "0" + FirstPage.this.lastSeenBook;
                        }
                        int laschapno = FirstPage.this.dbhelper.getNoofChap(FirstPage.this.bible, FirstPage.this.lastSeenBook);
                        FirstPage.this.lastSeenChap = String.valueOf(laschapno);
                        if (FirstPage.this.lastSeenChap.equals("1") || FirstPage.this.lastSeenChap.equals("2") || FirstPage.this.lastSeenChap.equals("3") || FirstPage.this.lastSeenChap.equals("4") || FirstPage.this.lastSeenChap.equals("5") || FirstPage.this.lastSeenChap.equals("6") || FirstPage.this.lastSeenChap.equals("7") || FirstPage.this.lastSeenChap.equals("8") || FirstPage.this.lastSeenChap.equals("9") || FirstPage.this.lastSeenChap.equals("0")) {
                            FirstPage.this.lastSeenChap = "0" + FirstPage.this.lastSeenChap;
                        }
                    }
                }
//                FirstPage.this.sample = FirstPage.this.dbhelper.getMonBookChap(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
                FirstPage.this.sample = "<div style='line-height:150%;text-align: justify;'>" + FirstPage.this.sample + "</font></div>";
                title = FirstPage.this.dbhelper.getBookName(FirstPage.this.lastSeenBook) + " " + FirstPage.this.lastSeenChap;
                tvTitle.setText(FirstPage.this.dbhelper.getBookName(FirstPage.this.lastSeenBook) + " " + FirstPage.this.lastSeenChap);
                bb_bookchap.setText(title);
                vid = FirstPage.this.dbhelper.getMonVerid(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
                lvsample = FirstPage.this.dbhelper.getMonBookChapVer(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
                lv_verseid = new Integer[lvsample.size( )];
                for (int i = 0; i < lvsample.size( ); i++) {
                    lv_verseid[i] = vid.get(i);
                }
                lv_sample = new String[lvsample.size( )];
                for (int i = 0; i < lvsample.size( ); i++) {
                    lv_sample[i] = lvsample.get(i);
                }
                adapter1 = new FirstPageListview(FirstPage.this, lv_sample, lv_verseid, fontSize, pos);
                lv_content.setAdapter(adapter1);
                setListViewHeightBasedOnChildren(lv_content);
                Scroll_new.scrollTo(0, 0);
                Scroll_new.smoothScrollTo(0, 0);

                // lv_content.setAdapter(adapter);

                //FirstPage.this.wv_Content.loadDataWithBaseURL("", FirstPage.this.sample, "text/html", "utf-8", null);
            }
        });
        this.swipe.setOnTouchListener(new OnSwipeTouchListener2(getApplicationContext()) {
            @Override
            public void onSwipeLeft() {
                    onclick_flag = 0;
                    onclick_value.clear( );
                    c_vid.clear();
                    pos.clear( );
                    count = 0;
                    if (count == 0 && pos.size( ) == 0) {
                        option.setVisibility(View.GONE);
                    }
                    int lastchapno = FirstPage.this.dbhelper.getNoofChap(FirstPage.this.bible, FirstPage.this.lastSeenBook);
                    int int_chapno = Integer.parseInt(FirstPage.this.lastSeenChap);
                    int int_bookno = Integer.parseInt(FirstPage.this.lastSeenBook);
                    if (int_chapno < lastchapno) {
                        int_chapno++;
                        FirstPage.this.lastSeenChap = String.valueOf(int_chapno);
                        if (FirstPage.this.lastSeenChap.equals("1") || FirstPage.this.lastSeenChap.equals("2") || FirstPage.this.lastSeenChap.equals("3") || FirstPage.this.lastSeenChap.equals("4") || FirstPage.this.lastSeenChap.equals("5") || FirstPage.this.lastSeenChap.equals("6") || FirstPage.this.lastSeenChap.equals("7") || FirstPage.this.lastSeenChap.equals("8") || FirstPage.this.lastSeenChap.equals("9") || FirstPage.this.lastSeenChap.equals("0")) {
                            FirstPage.this.lastSeenChap = "0" + FirstPage.this.lastSeenChap;
                        }
                    } else {
                        int_bookno++;
                        FirstPage.this.lastSeenBook = String.valueOf(int_bookno);
                        if (FirstPage.this.lastSeenBook.equals("1") || FirstPage.this.lastSeenBook.equals("2") || FirstPage.this.lastSeenBook.equals("3") || FirstPage.this.lastSeenBook.equals("4") || FirstPage.this.lastSeenBook.equals("5") || FirstPage.this.lastSeenBook.equals("6") || FirstPage.this.lastSeenBook.equals("7") || FirstPage.this.lastSeenBook.equals("8") || FirstPage.this.lastSeenBook.equals("9") || FirstPage.this.lastSeenBook.equals("0")) {
                            FirstPage.this.lastSeenBook = "0" + FirstPage.this.lastSeenBook;
                        }
                        FirstPage.this.lastSeenChap = "01";
                    }

//                FirstPage.this.sample = FirstPage.this.dbhelper.getMonBookChap(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
//                FirstPage.this.sample = "<div style='line-height:150%;text-align: justify;'>" + FirstPage.this.sample + "</font></div>";
                    vid = FirstPage.this.dbhelper.getMonVerid(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
                    lvsample = FirstPage.this.dbhelper.getMonBookChapVer(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
                    lv_verseid = new Integer[lvsample.size( )];
                    for (int i = 0; i < lvsample.size( ); i++) {
                        lv_verseid[i] = vid.get(i);
                    }
                    lv_sample = new String[lvsample.size( )];
                    for (int i = 0; i < lvsample.size( ); i++) {
                        lv_sample[i] = lvsample.get(i);
                    }
                    adapter1 = new FirstPageListview(FirstPage.this, lv_sample, lv_verseid, fontSize, pos);
                    lv_content.setAdapter(adapter1);
                    setListViewHeightBasedOnChildren(lv_content);
                    Scroll_new.scrollTo(0, 0);
                    title = FirstPage.this.dbhelper.getBookName(FirstPage.this.lastSeenBook) + " " + FirstPage.this.lastSeenChap;
                tvTitle.setText(FirstPage.this.dbhelper.getBookName(FirstPage.this.lastSeenBook) + " " + FirstPage.this.lastSeenChap);

                bb_bookchap.setText(title);
                //FirstPage.this.wv_Content.loadDataWithBaseURL("", FirstPage.this.sample, "text/html", "utf-8", null);
            }
            public void onSwipeRight() {
                onclick_flag = 0;
                onclick_value.clear( );
                c_vid.clear();
                count = 0;
                pos.clear( );
                if (count == 0 && pos.size( ) == 0) {
                    option.setVisibility(View.GONE);
                }
                int lastchapno = FirstPage.this.dbhelper.getNoofChap(FirstPage.this.bible, FirstPage.this.lastSeenBook);
                int int_chapno = Integer.parseInt(FirstPage.this.lastSeenChap);
                int int_bookno = Integer.parseInt(FirstPage.this.lastSeenBook);
                int_chapno--;
                if (int_chapno > 0) {
                    FirstPage.this.lastSeenChap = String.valueOf(int_chapno);
                    if (FirstPage.this.lastSeenChap.equals("1") || FirstPage.this.lastSeenChap.equals("2") || FirstPage.this.lastSeenChap.equals("3") || FirstPage.this.lastSeenChap.equals("4") || FirstPage.this.lastSeenChap.equals("5") || FirstPage.this.lastSeenChap.equals("6") || FirstPage.this.lastSeenChap.equals("7") || FirstPage.this.lastSeenChap.equals("8") || FirstPage.this.lastSeenChap.equals("9") || FirstPage.this.lastSeenChap.equals("0")) {
                        FirstPage.this.lastSeenChap = "0" + FirstPage.this.lastSeenChap;
                    }
                } else {
                    int_bookno--;
                    if (int_bookno > 0) {
                        FirstPage.this.lastSeenBook = String.valueOf(int_bookno);
                        if (FirstPage.this.lastSeenBook.equals("1") || FirstPage.this.lastSeenBook.equals("2") || FirstPage.this.lastSeenBook.equals("3") || FirstPage.this.lastSeenBook.equals("4") || FirstPage.this.lastSeenBook.equals("5") || FirstPage.this.lastSeenBook.equals("6") || FirstPage.this.lastSeenBook.equals("7") || FirstPage.this.lastSeenBook.equals("8") || FirstPage.this.lastSeenBook.equals("9") || FirstPage.this.lastSeenBook.equals("0")) {
                            FirstPage.this.lastSeenBook = "0" + FirstPage.this.lastSeenBook;
                        }
                        int laschapno = FirstPage.this.dbhelper.getNoofChap(FirstPage.this.bible, FirstPage.this.lastSeenBook);
                        FirstPage.this.lastSeenChap = String.valueOf(laschapno);
                        if (FirstPage.this.lastSeenChap.equals("1") || FirstPage.this.lastSeenChap.equals("2") || FirstPage.this.lastSeenChap.equals("3") || FirstPage.this.lastSeenChap.equals("4") || FirstPage.this.lastSeenChap.equals("5") || FirstPage.this.lastSeenChap.equals("6") || FirstPage.this.lastSeenChap.equals("7") || FirstPage.this.lastSeenChap.equals("8") || FirstPage.this.lastSeenChap.equals("9") || FirstPage.this.lastSeenChap.equals("0")) {
                            FirstPage.this.lastSeenChap = "0" + FirstPage.this.lastSeenChap;
                        }
                    }
                }
//                FirstPage.this.sample = FirstPage.this.dbhelper.getMonBookChap(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
                FirstPage.this.sample = "<div style='line-height:150%;text-align: justify;'>" + FirstPage.this.sample + "</font></div>";
                title = FirstPage.this.dbhelper.getBookName(FirstPage.this.lastSeenBook) + " " + FirstPage.this.lastSeenChap;
                tvTitle.setText(FirstPage.this.dbhelper.getBookName(FirstPage.this.lastSeenBook) + " " + FirstPage.this.lastSeenChap);
                bb_bookchap.setText(title);
                vid = FirstPage.this.dbhelper.getMonVerid(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
                lvsample = FirstPage.this.dbhelper.getMonBookChapVer(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
                lv_verseid = new Integer[lvsample.size( )];
                for (int i = 0; i < lvsample.size( ); i++) {
                    lv_verseid[i] = vid.get(i);
                }
                lv_sample = new String[lvsample.size( )];
                for (int i = 0; i < lvsample.size( ); i++) {
                    lv_sample[i] = lvsample.get(i);
                }
                adapter1 = new FirstPageListview(FirstPage.this, lv_sample, lv_verseid, fontSize, pos);
                lv_content.setAdapter(adapter1);
                setListViewHeightBasedOnChildren(lv_content);
                Scroll_new.scrollTo(0, 0);
                Scroll_new.smoothScrollTo(0, 0);

            }
        });
        this.intent_bookid = this.sharedPreferences.getString("intentbookid", null);
        this.intent_chapid = this.sharedPreferences.getString("intentchapid", null);
        this.intent_verseid = this.sharedPreferences.getString("intentverseid", null);
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance( ).getTime( ));
        String time = new SimpleDateFormat("h:mm a").format(Calendar.getInstance( ).getTime( ));
        DBhelper db;
        db = new DBhelper(getApplicationContext( ));
        db.getReadableDatabase( );
        String fullbookname = dbhelper.getBookName(intent_bookid);
        db.getWritableDatabase( );
        db.insertreadhistory(fullbookname, intent_chapid, intent_verseid, date, time);
        if (this.intent_bookid == null) {
            this.sample = "<div style='line-height:150%;text-align: justify;'>" + this.sample + "</font></div>";
        } else if (this.intent_bookid == null || !this.intent_chapid.equals("")) {
            this.lastSeenBook = this.intent_bookid;
            this.lastSeenChap = this.intent_chapid;
            //this.sample = this.dbhelper.getMonBookChap(this.bible, this.intent_bookid, this.intent_chapid);
            this.sample = "<div style='line-height:150%;text-align: justify;'>" + this.sample + "</font></div>";
            lvsample = FirstPage.this.dbhelper.getMonBookChapVer(FirstPage.this.bible, FirstPage.this.intent_bookid, FirstPage.this.intent_chapid);
            vid = FirstPage.this.dbhelper.getMonVerid(FirstPage.this.bible, FirstPage.this.intent_bookid, FirstPage.this.intent_chapid);

        } else {
            this.lastSeenBook = this.intent_bookid;
            this.lastSeenChap = "01";
            vid = FirstPage.this.dbhelper.getMonVerid(FirstPage.this.bible, FirstPage.this.intent_bookid, FirstPage.this.lastSeenChap);
            lvsample = FirstPage.this.dbhelper.getMonBookChapVer(FirstPage.this.bible, FirstPage.this.intent_bookid, FirstPage.this.lastSeenChap);
            //this.sample = this.dbhelper.getMonBookChap(this.bible, this.intent_bookid, this.lastSeenChap);
            this.sample = "<div style='line-height:150%;text-align: justify;'>" + this.sample + "</font></div>";
        }
        String bookname = this.dbhelper.getBookName(this.lastSeenBook);
        if (this.intent_chapid.equals("")) {
            title = bookname + "  01";
            tvTitle.setText(bookname + "  01");
            bb_bookchap.setText(title);

        } else {
            title = bookname + " " + this.lastSeenChap;
            tvTitle.setText(bookname + " " + this.lastSeenChap);
            bb_bookchap.setText(title);

        }
        lv_verseid = new Integer[vid.size( )];
        for (int i = 0; i < vid.size( ); i++) {
            lv_verseid[i] = vid.get(i);
        }
        lv_sample = new String[lvsample.size( )];
        for (int i = 0; i < lvsample.size( ); i++) {
            lv_sample[i] = lvsample.get(i);
        }
        adapter1 = new FirstPageListview(FirstPage.this, lv_sample, lv_verseid, fontSize, pos);
        lv_content.setAdapter(adapter);
        lv_content.setSelection(10);
        setListViewHeightBasedOnChildren(lv_content);
        lv_content.setAdapter(adapter);
        if (intent_verseid == "") {
            lv_content.setSelection(0);
            lv_content.smoothScrollToPosition(0);

        } else {
            lv_content.setSelection(Integer.parseInt(intent_verseid) - 1);
            lv_content.smoothScrollToPosition(Integer.parseInt(intent_verseid));
            Scroll_new.scrollTo(0, (Integer.parseInt(intent_verseid) - 1));
        }

        // this.wv_Content.loadDataWithBaseURL("", this.sample, "text/html", "utf-8", null);
//        this.pd.dismiss();
//        this.client = new Builder(this).addApi(AppIndex.API).build();
//        this.mTextSelectionSupport = TextSelectionSupportCW.support(this, this.wv_Content);
        //      this.mTextSelectionSupport.setSelectionListener(new C05032( ));
        this.tvcopy.setOnClickListener(new C02603( ));
        this.tvcompare.setOnClickListener(new C02614( ));
        this.tvnotes.setOnClickListener(new C02625( ));
        this.tvflashcard.setOnClickListener(new C02624( ));
        int currentFirst = 0;
        int currentLast = 0;
        lv_content.setOnScrollListener(new AbsListView.OnScrollListener( ) {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisible,
                                 int visibleCount, int totalItems) {

            }

        });

        lv_content.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener( ) {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long arg3) {
                if (deselect(position) == true) {
                    lv_content.getChildAt(position - lv_content.getFirstVisiblePosition( )).setBackgroundColor(getResources( ).getColor(R.color.white));
                    Log.e("pos", position - lv_content.getFirstVisiblePosition( ) + "");
                    adapter1.notifyDataSetChanged( );
                    count = count - 1;
                    if (count == 0) {
                        option.setVisibility(View.GONE);
                        onclick_flag = 0;

                    }
                    //lv_content.getChildAt(position+lv_content.getLastVisiblePosition()).setBackgroundColor(getResources().getColor(R.color.white));


                } else {
                    if (position + 1 == 1 || position + 1 == 2 || position + 1 == 3 || position + 1 == 4 || position + 1 == 5 || position + 1 == 6 || position + 1 == 7 || position + 1 == 8 || position + 1 == 9 || position + 1 == 0)
                    {
                        c_vid.add("0"+(position+1));
                    }
                    else
                    {
                        c_vid.add(""+(position+1));
                    }
                    String sverse = "" + (position + 1) + "." + adapter.getItemAtPosition(position).toString( );
                    onclick_value.add(sverse);
                    row = view;
                    onclick_flag = 1;
                    pos.add(position);
                    count = count + 1;
                    option.setVisibility(View.VISIBLE);
                    lv_content.getChildAt(position - lv_content.getFirstVisiblePosition( )).setBackgroundColor(getResources( ).getColor(R.color.selectionblue));
                    adapter1.notifyDataSetChanged( );

                }
                return true;
            }
        });

        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long l) {
                // view=lv_content.getChildAt(position-lv_content.getFirstVisiblePosition());
                //      wv_layout.setVisibility(View.GONE);
                if (onclick_flag == 1 && deselect(position) == false) {
                    if (position + 1 == 1 || position + 1 == 2 || position + 1 == 3 || position + 1 == 4 || position + 1 == 5 || position + 1 == 6 || position + 1 == 7 || position + 1 == 8 || position + 1 == 9 || position + 1 == 0)
                    {
                        c_vid.add("0"+(position+1));
                    }
                    else
                    {
                        c_vid.add(""+(position+1));
                    }                    String sverse = "" + (position + 1) + "." + adapter.getItemAtPosition(position).toString( );
                    onclick_value.add(sverse);
                    pos.add(position);
                    Log.e("Position", position + "");
                    count = count + 1;
                    option.setVisibility(View.VISIBLE);
                    int j = lv_content.getDisplay( ).getDisplayId( );
                    Log.e("content position", lv_content.getChildAt(position) + "");
////
//                     FirstPageListview adapter1 = new FirstPageListview(FirstPage.this, lv_sample, lv_verseid, fontSize,pos);
                    lv_content.getChildAt(position - lv_content.getFirstVisiblePosition( )).setBackgroundColor(getResources( ).getColor(R.color.selectionblue));
                    adapter1.notifyDataSetChanged( );
//                    lv_content.setAdapter(adapter1);
//                    lv_content.setSelection(view.getId());
//                    lv_content.getPositionForView(view);
                } else if (count == 0) {
                  //  Toast.makeText(FirstPage.this, "Long press to select verse", Toast.LENGTH_LONG).show( );
                } else {
                    Log.e("pos", position - lv_content.getFirstVisiblePosition( ) + "");
                    lv_content.getChildAt(position - lv_content.getFirstVisiblePosition( )).setBackgroundColor(getResources( ).getColor(R.color.white));
                    adapter1.notifyDataSetChanged( );
                    //view.setBackgroundColor(getResources( ).getColor(R.color.white));
                    count = count - 1;
                    if (count == 0) {
                        option.setVisibility(View.GONE);
                        onclick_flag = 0;
                    }
                }
            }
        });
    }
    private void loaddata() {
        BufferedReader bufferedReader;
        FileNotFoundException e;
        IOException e2;
        if (this.state.equals("mounted")) {
            try {
                File textFile = new File(Environment.getExternalStorageDirectory( ) + "/catc plan/plandata.xml");
                if (textFile.exists( )) {
                    BufferedReader reader = new BufferedReader(new FileReader(textFile));
                    try {
                        this.textBuilder = new StringBuffer( );
                        while (true) {
                            String line = reader.readLine( );
                            if (line != null) {
                                this.textBuilder.append(line);
                                this.textBuilder.append("\n");
                            } else {
                                InputStream inputStream = new ByteArrayInputStream(this.textBuilder.toString( ).getBytes( ));
                                this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
                                Editor edit = this.sharedPreferences.edit( );
                                edit.putString("activit", "First");
                                edit.commit( );
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater( ).inflate(R.menu.first_page, menu);
//        MenuItem mSearch = menu.findItem(R.id.search);
//        SearchView mSearchView = (SearchView) mSearch.getActionView();
//        mSearchView.setQueryHint("Search");
//
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//
//
//    });
      return true;
    }

    @JavascriptInterface
    public void resize(float height) {
        float webViewHeight = height * getResources( ).getDisplayMetrics( ).density;
        Log.e("test", "test");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId( );
        if (id == R.id.search) {
           search_pop();
        }
        if (id == R.id.date) {
            Intent intent = new Intent(FirstPage.this, MonthlyCal.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            FirstPage.this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId( );
        if (id == R.id.nav_about) {
            Intent intent = new Intent(FirstPage.this, pop_activity_about.class);
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
            Intent intent = new Intent(FirstPage.this, Settings.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    public void load(String plan, String id, String i1, String i2, String i3, String i4) {
        this.dbhelper.insertdata(plan, id, i1, i2, i3, i4);
    }

    public void Chapter(String s) {
        this.bookid = this.dbhelper.getId(s);
        this.popBookNo.setText("Book\n" + s);
        this.oldTestament.setVisibility(View.GONE);
        this.newTestament.setVisibility(View.GONE);
        this.grid_old_book.setVisibility(View.GONE);
        this.grid_new_book.setVisibility(View.GONE);
        this.back.setVisibility(View.GONE);
        this.backBook.setVisibility(View.VISIBLE);
        this.backChap.setVisibility(View.GONE);
        this.goBook.setVisibility(View.VISIBLE);
        this.goChap.setVisibility(View.GONE);
        this.grid_vers.setVisibility(View.GONE);
        this.grid_chap.setVisibility(View.VISIBLE);
        this.noofchap = this.dbhelper.getNoofChap(this.bible, this.bookid);
        this.scrollfull.pageScroll(33);
        this.goBook.setOnClickListener(new OnClickListener( ) {
            public void onClick(View v) {
                String con = "";
                Cursor value = FirstPage.this.dbhelper.getBookContent(FirstPage.this.bible, FirstPage.this.bookid);
                if (value != null) {
                    if (value.moveToFirst( )) {
                        do {
                            con = con + value.getString(value.getColumnIndex("verse"));
                            Log.e("content by id", FirstPage.this.content);
                        } while (value.moveToNext( ));
                    }
                    value.close( );
                    FirstPage.this.poplay.setVisibility(View.GONE);
                    FirstPage.this.pop.dismiss( );
                    Intent i = new Intent(FirstPage.this.getApplicationContext( ), FirstPage.class);
                    FirstPage.this.sharedPreferences = FirstPage.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen = FirstPage.this.sharedPreferences.edit( );
                    edit_gen.putString("intentbookid", FirstPage.this.bookid);
                    edit_gen.commit( );
                    FirstPage.this.startActivity(i);
                    overridePendingTransition(0, 0);
                }
            }
        });
        this.backBook.setOnClickListener(new OnClickListener( ) {
            public void onClick(View v) {
                FirstPage.this.oldTestament.setVisibility(View.VISIBLE);
                FirstPage.this.newTestament.setVisibility(View.VISIBLE);
                FirstPage.this.grid_old_book.setVisibility(View.VISIBLE);
                FirstPage.this.grid_new_book.setVisibility(View.VISIBLE);
                FirstPage.this.grid_chap.setVisibility(View.GONE);
                FirstPage.this.back.setVisibility(View.VISIBLE);
                FirstPage.this.goBook.setVisibility(View.GONE);
                FirstPage.this.goChap.setVisibility(View.GONE);
                FirstPage.this.backBook.setVisibility(View.GONE);
                FirstPage.this.backChap.setVisibility(View.GONE);
                FirstPage.this.grid_vers.setVisibility(View.GONE);
                FirstPage.this.popBookNo.setText("Book\nGen");
                FirstPage.this.popChapNo.setText("Chapter\n01");
            }
        });
        this.stringArrayList.clear( );
        for (int i = 1; i <= this.noofchap; i++) {
            this.stringArrayList.add(String.valueOf(i));
        }
        String[] str = (String[]) this.stringArrayList.toArray(new String[this.stringArrayList.size( )]);
        Editor edit_gen = this.sharedPreferences.edit( );
        edit_gen.putString("bookid", this.bookid);
        edit_gen.commit( );
        this.grid_chap.setAdapter(new ChapterAdapter(this, str));
    }

    public void Verse(String s, String tabchapid) {
        final String bookid = this.sharedPreferences.getString("bookid", null);
        Editor editor = this.sharedPreferences.edit( );
        editor.putString("chapterid", s);
        editor.putString("TabChapid", tabchapid);
        this.back.setVisibility(View.GONE);
        this.backBook.setVisibility(View.GONE);
        this.backChap.setVisibility(View.VISIBLE);
        this.goBook.setVisibility(View.GONE);
        this.goChap.setVisibility(View.VISIBLE);
        editor.commit( );
        this.scrollfull.pageScroll(33);
        ArrayList<String> stringArrayList = new ArrayList( );
        final String chapno = s;
        this.goChap.setOnClickListener(new OnClickListener( ) {
            public void onClick(View v) {
                String con = "";
                Cursor value = FirstPage.this.dbhelper.getChapterContent(FirstPage.this.bible, bookid, chapno);
                if (value != null) {
                    if (value.moveToFirst( )) {
                        do {
                            con = con + value.getString(value.getColumnIndex("verse"));
                            Log.e("content by id", FirstPage.this.content);
                        } while (value.moveToNext( ));
                    }
                    value.close( );
                    FirstPage.this.poplay.setVisibility(View.GONE);
                    FirstPage.this.pop.dismiss( );
                    Intent i = new Intent(FirstPage.this.getApplicationContext( ), FirstPage.class);
                    i.putExtra("intent_content", con);
                    Editor edit_gen = FirstPage.this.sharedPreferences.edit( );
                    edit_gen.putString("intentbookid", bookid);
                    edit_gen.putString("intentchapid", chapno);
                    edit_gen.commit( );
                    FirstPage.this.startActivity(i);
                    overridePendingTransition(0, 0);

                }
            }
        });
        this.backChap.setOnClickListener(new OnClickListener( ) {
            public void onClick(View v) {
                FirstPage.this.oldTestament.setVisibility(View.GONE);
                FirstPage.this.newTestament.setVisibility(View.GONE);
                FirstPage.this.grid_old_book.setVisibility(View.GONE);
                FirstPage.this.grid_new_book.setVisibility(View.GONE);
                FirstPage.this.grid_chap.setVisibility(View.VISIBLE);
                FirstPage.this.back.setVisibility(View.GONE);
                FirstPage.this.goBook.setVisibility(View.VISIBLE);
                FirstPage.this.goChap.setVisibility(View.GONE);
                FirstPage.this.backBook.setVisibility(View.VISIBLE);
                FirstPage.this.backChap.setVisibility(View.GONE);
                FirstPage.this.grid_vers.setVisibility(View.GONE);
                FirstPage.this.popChapNo.setText("Chapter\n01");
            }
        });
        this.popChapNo.setText("Chapter\n" + s);
        int value = this.dbhelper.getNoofVerse(this.bible, bookid, chapno);
        this.grid_chap.setVisibility(View.GONE);
        this.grid_vers.setVisibility(View.VISIBLE);
        for (int i = 1; i <= value; i++) {
            stringArrayList.add(String.valueOf(i));
        }
        this.str_ver = (String[]) stringArrayList.toArray(new String[stringArrayList.size( )]);
        this.adapt = new VerseAdapter(this, this.str_ver);
        this.grid_vers.setAdapter(this.adapt);
    }

    public void content(String book, String chap, String verse) {
        String con = "";
        Cursor value = this.dbhelper.getContent(this.bible, book, chap, verse);
        if (value != null) {
            if (value.moveToFirst( )) {
                do {
                    con = con + value.getString(value.getColumnIndex("verse"));
                    Log.e("content by id", this.content);
                } while (value.moveToNext( ));
            }
            value.close( );
            this.poplay.setVisibility(View.GONE);
            this.pop.dismiss( );
            Intent i = new Intent(getApplicationContext( ), FirstPage.class);
            Editor edit_gen = this.sharedPreferences.edit( );
            edit_gen.putString("intentbookid", this.bookid);
            edit_gen.putString("intentchapid", chap);
            edit_gen.commit( );
            startActivity(i);
            overridePendingTransition(0, 0);

        }
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen((int) GravityCompat.START)) {
            drawer.closeDrawer((int) GravityCompat.START);
        } else if (this.poplay == null || this.poplay.getVisibility( ) != View.VISIBLE) {
            startActivity(new Intent(getApplicationContext( ), bibleContentSelect.class));
            finish( );
        } else {
            this.poplay.setVisibility(View.GONE);
            this.pop.dismiss( );
            fab.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onResume() {
        super.onResume( );
        bottomBar.selectTabWithId(R.id.tab_read);
       // first_page.getForeground().setAlpha(0);
        onclick_flag = 0;
        count = 0;
        bb_bookchap.setText(title);
        onclick_value.clear( );
        c_vid.clear();
        pos.clear( );
        if (count == 0 && pos.size( ) == 0) {
            option.setVisibility(View.GONE);
        }
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", 0);
        String flot_size = sharedPreferences.getString("fontsize", null);
        this.bible = sharedPreferences.getString("bible", null);
        if (flot_size == null) {
            this.fontSize = 15.0f;
        } else {
            this.fontSize = Float.parseFloat(flot_size);
        }
        vid = FirstPage.this.dbhelper.getMonVerid(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
        lvsample = FirstPage.this.dbhelper.getMonBookChapVer(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
        lv_verseid = new Integer[vid.size( )];
        for (int i = 0; i < lvsample.size( ); i++) {
            lv_verseid[i] = vid.get(i);
        }
        lv_sample = new String[lvsample.size( )];
        for (int i = 0; i < lvsample.size( ); i++) {
            lv_sample[i] = lvsample.get(i);
        }
        adapter1 = new FirstPageListview(FirstPage.this, lv_sample, lv_verseid, fontSize, pos);
        lv_content.setAdapter(adapter1);


        ViewCompat.setNestedScrollingEnabled(lv_content, true);
        if (intent_verseid == "") {
            lv_content.setSelection(0);
        } else {

            lv_content.setSelection(Integer.parseInt(intent_verseid) - 1);

        }
    }
        class C02603 implements OnClickListener {
            C02603() {
            }

            public void onClick(View v) {

                String fverse = "";
                for (int s = 0; s < onclick_value.size( ); s++) {
                    fverse = fverse + onclick_value.get(s);
                }
                UiUtil.copyToClipboard(getApplicationContext( ), fverse);
                Toast.makeText(getApplicationContext( ), "Copied", Toast.LENGTH_LONG).show( );
                //lv_content.setBackgroundColor(getResources( ).getColor(R.color.white));
            }
        }

        class C02614 implements OnClickListener {
            C02614() {
            }

            public void onClick(View v) {

                String fverse = "";
                for (int s = 0; s < onclick_value.size( ); s++) {
                    fverse = fverse + onclick_value.get(s);
                }
               // search(fverse);
//            lv_content.setBackgroundColor();
                Intent intent =  new Intent(FirstPage.this, CompareVerse.class);
                intent.putExtra("book_id",lastSeenBook);
                intent.putExtra("chap_id",lastSeenChap);
                intent.putIntegerArrayListExtra("vid",vid);
                intent.putStringArrayListExtra("s_vid",c_vid);
                startActivity(intent);
                pos.clear( );
                onclick_value.clear( );
                c_vid.clear();
                onclick_flag = 0;
                count = 0;
            }
        }

        class C02636 implements OnClickListener {
            C02636() {
            }

            public void onClick(View v) {
                FirstPage.this.pop_search_word.dismiss( );
            }
        }

        class C02669 implements OnClickListener {
            C02669() {
            }

            public void onClick(View v) {
                FirstPage.this.pop_search_word.dismiss( );
            }
        }

        class C02625 implements OnClickListener {
            C02625() {
            }

            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext( ), Notes.class));
                pos.clear( );
                onclick_value.clear( );
                c_vid.clear();
                onclick_flag = 0;
                count = 0;
            }
        }


        class C02624 implements OnClickListener {
            private DBhelper db;

            C02624() {
            }

            public void onClick(View v) {

                String fverse = "";
                this.db = new DBhelper(getApplicationContext( ));
                db.getWritableDatabase( );
                String v_title = title;
                String date = new SimpleDateFormat("dd-MM-yy").format(Calendar.getInstance( ).getTime( ));

                for (int s = 0; s < onclick_value.size( ); s++) {
                    fverse = fverse + onclick_value.get(s).toString( );
                }
                long i = db.insertflashcard(fverse, date, v_title);
                if (i != -1) {
                    Toast.makeText(getApplicationContext( ), "Added to Memory verse", Toast.LENGTH_LONG).show( );
                    pos.clear( );
                    onclick_value.clear( );
                    c_vid.clear();
                    onclick_flag = 0;
                    count = 0;
                    option.setVisibility(View.GONE);
                }

            }
        }

    private boolean deselect(int position) {
        boolean b = false;

        for (int i = 0; i < count; i++) {
            if (pos.get(i) == position) {
                pos.remove(i);
                onclick_value.remove(i);
                c_vid.remove(i);
                adapter1.notifyDataSetChanged();
                b = true;
                return b;
            }
        }
        return b;
    }


    private void search(String mSelectedText) {
        if (this.pop_search_word == null) {
            this.pop_search_word = new Dialog(FirstPage.this);
            this.pop_search_word.requestWindowFeature(1);
            this.pop_search_word.setTitle("Search Option");
            this.pop_search_word.setCanceledOnTouchOutside(true);
            this.pop_search_word.setCancelable(true);
            this.pop_search_word.setContentView(R.layout.popup_search);
            final EditText edtSearchWord = (EditText) this.pop_search_word.findViewById(R.id.edt_search_word);
            Button btnSumbit = (Button) this.pop_search_word.findViewById(R.id.btn_submit);
            Button btnCancel = (Button) this.pop_search_word.findViewById(R.id.btn_cancel);
            ImageView ivclose = (ImageView) this.pop_search_word.findViewById(R.id.iv_close);
            edtSearchWord.setText(mSelectedText);
            ivclose.setOnClickListener(new C02636( ));
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
                    overridePendingTransition(0, 0);

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
                            overridePendingTransition(0, 0);

                        }
                    }
                    return true;
                }
            });
            btnCancel.setOnClickListener(new C02669( ));
            pop_search_word.show( );
        } else if (this.pop_search_word.isShowing( )) {
            this.pop_search_word.dismiss( );
            this.pop_search_word = null;
            search("");
        } else {
            this.pop_search_word = null;
            search("");
        }
    }


//    public Action getIndexApiAction() {
//        return new Action.Builder(Action.TYPE_VIEW).setObject(new Thing.Builder().setName("FirstPage Page").setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]")).build()).setActionStatus(Action.STATUS_TYPE_COMPLETED).build();
//    }
//
//    public void onStart() {
//        super.onStart();
//        this.client.connect();
//        AppIndex.AppIndexApi.start(this.client, getIndexApiAction());
//    }
//
//    public void onStop() {
//        super.onStop();
//        AppIndex.AppIndexApi.end(this.client, getIndexApiAction());
//        this.client.disconnect();
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        super.dispatchTouchEvent(ev);
        return productGestureDetector.onTouchEvent(ev);
    }

    public void swipeToLeft()
    {

        onclick_flag = 0;
        onclick_value.clear( );
        c_vid.clear();
        count = 0;
        pos.clear( );
        if (count == 0 && pos.size( ) == 0) {
            option.setVisibility(View.GONE);
        }
        int lastchapno = FirstPage.this.dbhelper.getNoofChap(FirstPage.this.bible, FirstPage.this.lastSeenBook);
        int int_chapno = Integer.parseInt(FirstPage.this.lastSeenChap);
        int int_bookno = Integer.parseInt(FirstPage.this.lastSeenBook);
        int_chapno--;
        if (int_chapno > 0) {
            FirstPage.this.lastSeenChap = String.valueOf(int_chapno);
            if (FirstPage.this.lastSeenChap.equals("1") || FirstPage.this.lastSeenChap.equals("2") || FirstPage.this.lastSeenChap.equals("3") || FirstPage.this.lastSeenChap.equals("4") || FirstPage.this.lastSeenChap.equals("5") || FirstPage.this.lastSeenChap.equals("6") || FirstPage.this.lastSeenChap.equals("7") || FirstPage.this.lastSeenChap.equals("8") || FirstPage.this.lastSeenChap.equals("9") || FirstPage.this.lastSeenChap.equals("0")) {
                FirstPage.this.lastSeenChap = "0" + FirstPage.this.lastSeenChap;
            }
        } else {
            int_bookno--;
            if (int_bookno > 0) {
                FirstPage.this.lastSeenBook = String.valueOf(int_bookno);
                if (FirstPage.this.lastSeenBook.equals("1") || FirstPage.this.lastSeenBook.equals("2") || FirstPage.this.lastSeenBook.equals("3") || FirstPage.this.lastSeenBook.equals("4") || FirstPage.this.lastSeenBook.equals("5") || FirstPage.this.lastSeenBook.equals("6") || FirstPage.this.lastSeenBook.equals("7") || FirstPage.this.lastSeenBook.equals("8") || FirstPage.this.lastSeenBook.equals("9") || FirstPage.this.lastSeenBook.equals("0")) {
                    FirstPage.this.lastSeenBook = "0" + FirstPage.this.lastSeenBook;
                }
                int laschapno = FirstPage.this.dbhelper.getNoofChap(FirstPage.this.bible, FirstPage.this.lastSeenBook);
                FirstPage.this.lastSeenChap = String.valueOf(laschapno);
                if (FirstPage.this.lastSeenChap.equals("1") || FirstPage.this.lastSeenChap.equals("2") || FirstPage.this.lastSeenChap.equals("3") || FirstPage.this.lastSeenChap.equals("4") || FirstPage.this.lastSeenChap.equals("5") || FirstPage.this.lastSeenChap.equals("6") || FirstPage.this.lastSeenChap.equals("7") || FirstPage.this.lastSeenChap.equals("8") || FirstPage.this.lastSeenChap.equals("9") || FirstPage.this.lastSeenChap.equals("0")) {
                    FirstPage.this.lastSeenChap = "0" + FirstPage.this.lastSeenChap;
                }
            }
        }
//                FirstPage.this.sample = FirstPage.this.dbhelper.getMonBookChap(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
        FirstPage.this.sample = "<div style='line-height:150%;text-align: justify;'>" + FirstPage.this.sample + "</font></div>";
        title = FirstPage.this.dbhelper.getBookName(FirstPage.this.lastSeenBook) + " " + FirstPage.this.lastSeenChap;
        tvTitle.setText(FirstPage.this.dbhelper.getBookName(FirstPage.this.lastSeenBook) + " " + FirstPage.this.lastSeenChap);
        bb_bookchap.setText(title);
        vid = FirstPage.this.dbhelper.getMonVerid(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
        lvsample = FirstPage.this.dbhelper.getMonBookChapVer(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
        lv_verseid = new Integer[lvsample.size( )];
        for (int i = 0; i < lvsample.size( ); i++) {
            lv_verseid[i] = vid.get(i);
        }
        lv_sample = new String[lvsample.size( )];
        for (int i = 0; i < lvsample.size( ); i++) {
            lv_sample[i] = lvsample.get(i);
        }
        adapter1 = new FirstPageListview(FirstPage.this, lv_sample, lv_verseid, fontSize, pos);
        lv_content.setAdapter(adapter1);
        setListViewHeightBasedOnChildren(lv_content);
        Scroll_new.scrollTo(0, 0);
        Scroll_new.smoothScrollTo(0, 0);

        // lv_content.setAdapter(adapter);

        //FirstPage.this.wv_Content.loadDataWithBaseURL("", FirstPage.this.sample, "text/html", "utf-8", null);
    }


    public void swipeToRight()
    {
        onclick_flag = 0;
        onclick_value.clear( );
        c_vid.clear();
        pos.clear( );
        count = 0;
        if (count == 0 && pos.size( ) == 0) {
            option.setVisibility(View.GONE);
        }
        int lastchapno = FirstPage.this.dbhelper.getNoofChap(FirstPage.this.bible, FirstPage.this.lastSeenBook);
        int int_chapno = Integer.parseInt(FirstPage.this.lastSeenChap);
        int int_bookno = Integer.parseInt(FirstPage.this.lastSeenBook);
        if (int_chapno < lastchapno) {
            int_chapno++;
            FirstPage.this.lastSeenChap = String.valueOf(int_chapno);
            if (FirstPage.this.lastSeenChap.equals("1") || FirstPage.this.lastSeenChap.equals("2") || FirstPage.this.lastSeenChap.equals("3") || FirstPage.this.lastSeenChap.equals("4") || FirstPage.this.lastSeenChap.equals("5") || FirstPage.this.lastSeenChap.equals("6") || FirstPage.this.lastSeenChap.equals("7") || FirstPage.this.lastSeenChap.equals("8") || FirstPage.this.lastSeenChap.equals("9") || FirstPage.this.lastSeenChap.equals("0")) {
                FirstPage.this.lastSeenChap = "0" + FirstPage.this.lastSeenChap;
            }
        } else {
            int_bookno++;
            FirstPage.this.lastSeenBook = String.valueOf(int_bookno);
            if (FirstPage.this.lastSeenBook.equals("1") || FirstPage.this.lastSeenBook.equals("2") || FirstPage.this.lastSeenBook.equals("3") || FirstPage.this.lastSeenBook.equals("4") || FirstPage.this.lastSeenBook.equals("5") || FirstPage.this.lastSeenBook.equals("6") || FirstPage.this.lastSeenBook.equals("7") || FirstPage.this.lastSeenBook.equals("8") || FirstPage.this.lastSeenBook.equals("9") || FirstPage.this.lastSeenBook.equals("0")) {
                FirstPage.this.lastSeenBook = "0" + FirstPage.this.lastSeenBook;
            }
            FirstPage.this.lastSeenChap = "01";
        }

//                FirstPage.this.sample = FirstPage.this.dbhelper.getMonBookChap(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
//                FirstPage.this.sample = "<div style='line-height:150%;text-align: justify;'>" + FirstPage.this.sample + "</font></div>";
        vid = FirstPage.this.dbhelper.getMonVerid(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
        lvsample = FirstPage.this.dbhelper.getMonBookChapVer(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
        lv_verseid = new Integer[lvsample.size( )];
        for (int i = 0; i < lvsample.size( ); i++) {
            lv_verseid[i] = vid.get(i);
        }
        lv_sample = new String[lvsample.size( )];
        for (int i = 0; i < lvsample.size( ); i++) {
            lv_sample[i] = lvsample.get(i);
        }
        adapter1 = new FirstPageListview(FirstPage.this, lv_sample, lv_verseid, fontSize, pos);
        lv_content.setAdapter(adapter1);
        setListViewHeightBasedOnChildren(lv_content);
        Scroll_new.scrollTo(0, 0);
        title = FirstPage.this.dbhelper.getBookName(FirstPage.this.lastSeenBook) + " " + FirstPage.this.lastSeenChap;
        tvTitle.setText(FirstPage.this.dbhelper.getBookName(FirstPage.this.lastSeenBook) + " " + FirstPage.this.lastSeenChap);
        bb_bookchap.setText(title);
        //FirstPage.this.wv_Content.loadDataWithBaseURL("", FirstPage.this.sample, "text/html", "utf-8", null);
    }
    private void search_pop() {
        if (this.pop_search_word == null) {
            this.pop_search_word = new Dialog(FirstPage.this);
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
                    overridePendingTransition(0, 0);

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
                            overridePendingTransition(0, 0);

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
            search_pop();
        } else {
            this.pop_search_word = null;
            search_pop();
        }
    }
}

