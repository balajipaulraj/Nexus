package com.example.admin.bibleapp.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.admin.bibleapp.Adapter.BookAdapter;
import com.example.admin.bibleapp.Adapter.DrawerItemCustomAdapter;
import com.example.admin.bibleapp.Adapter.VerseAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.DoubleClickListener;

import com.example.admin.bibleapp.Model.MenuItems;
import com.example.admin.bibleapp.Model.user;
import com.example.admin.bibleapp.Views.OnSwipeTouchListener2;
import com.example.admin.bibleapp.Parser.Employee;
import com.example.admin.bibleapp.Parser.XMLPullParserHandler;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.ViewPager.CalContentView;
import com.example.admin.bibleapp.popupWindow.popFlashcard;
import com.example.admin.bibleapp.popupWindow.popReadHistory;
import com.example.admin.bibleapp.popupWindow.pop_activity_about;
import com.example.admin.bibleapp.util.Helpers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class CalPage extends AppCompatActivity implements GestureDetector.OnGestureListener {
    public static final String MyPREFERENCES = "MyPrefs";
    private static final String TAG = CalPage.class.getName();
    private GestureDetector productGestureDetector;
    private ProgressBar pb = null;
    String Bookname;
    Typeface RobotoSlabLight;
    Typeface RobotoSlabThin;
    VerseAdapter adapt;
    BookAdapter adapter;
    TextView back;
    TextView backBook;
    TextView tvTitle;
    String verse, title, power, date;
    TextView backChap;
    String bible;
    private ListView mDrawerList;
    String bookid;
    Calendar f16c;
    int completed;
    int dr1, dr2, dr3, dr4;
    TextView daily_read;
    ProgressBar d_read;
    RelativeLayout cal_page;
    String content = "";
    Date curredate;
    TextView currentdate;
    TextView dailreadings;
    int j;
    String datediff;
    Cursor dateContent;
    DBhelper dbhelper;
    List<Employee> employees = null;
    LinearLayout fourthLayout;
    String fourthread = "";
    TextView fourthtest;
    String getformattedDate;
    TextView goBook;
    TextView goChap;
    TextView titlevod;
    TextView power_vod;
    TextView versevod;
    TextView incomplete;
    Date initdate;
    String initialdate;
    private static String url = "https://www.biblegateway.com/usage/votd/rss/votd.rdf";
    String cdata = "", verseTitle = "";
    int int_fourthread;
    int int_newread;
    int int_oldread;
    int int_psalmread;
    ImageView ivFourthRead;
    ImageView ivNewRead;
    ImageView ivOldRead;
    Toolbar toolbar;
    ImageView ivPsalmRead;
    ImageView iv_bible;
    ArrayList<String> vod = new ArrayList();
    ImageView iv_monthlyCal;
    ImageView iv_nav;
    TextView ivcurrentdate;
    int f17j = 0;
    LinearLayout layContent;
    View layout;
    String makeup;
    TextView makeupday;
    Typeface monBold;
    Typeface monLight;
    Typeface monRegular;
    LinearLayout newLayout;
    TextView newTestament;
    String newformattedDate;
    String newread = "";
    TextView newtest;
    int noofchap;
    LinearLayout oldLayout;
    TextView oldTestament;
    String oldread = "";
    TextView oldtest;
    LinearLayout vodlay;
    ProgressDialog pd;
    String planby;
    PopupWindow pop;
    TextView popBookNo;
    TextView popChapNo;
    TextView popVerseNo;
    Dialog pop_search_word;
    LinearLayout poplay;
    TextView psalm;
    LinearLayout psalmLayout;
    String psalmread = "";
    SharedPreferences sharedPreferences;
    String state;
    String[] str_ver;
    int userId;
    List<String> stringArrayList = new ArrayList();
    LinearLayout swipe;
    String table_plan;
    StringBuffer textBuilder;
    TextView totalComplete;
    int totalcount = 4;
    BottomBar bottomBar;
    Typeface tribbon;
    ImageView tv_house;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    public static Context ctx;

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
                        swipeToRight();
                    } else {

                        swipeToLeft();
                    }
                }
            }
        } catch (Exception e) {

        }
        return result;
    }


    class C02172 implements OnClickListener {
        C02172() {
        }

        public void onClick(View v) {
            String oldtst = oldtest.getText().toString();
            if (!oldtst.equals("Catch-up day") && !oldtst.equals("REFLECTION | MAKE-UP")) {
                String psa = psalm.getText().toString();
                String newtst = newtest.getText().toString();
                String fourthtst = fourthtest.getText().toString();
                sharedPreferences = getSharedPreferences("MyPrefs", 0);
                Editor edit = sharedPreferences.edit();
                edit.putString("oldtst", oldtst);
                edit.putString("psa", psa);
                edit.putString("newtst", newtst);
                edit.putString("fourthtst", fourthtst);
                edit.commit();
                ArrayList<String> frag1_content = newSeparator(oldtst);
                ArrayList<String> frag2_content = newSeparator(psa);
                ArrayList<String> frag3_content = newSeparator(newtst);
                ArrayList<String> frag4_content = newSeparator(fourthtst);
                Intent i = new Intent(CalPage.this, CalContentView.class);
                i.putExtra("viewpager_position", "0");
                i.putExtra("date", getformattedDate);
                i.putStringArrayListExtra("frag1value", frag1_content);
                i.putStringArrayListExtra("frag2value", frag2_content);
                i.putStringArrayListExtra("frag3value", frag3_content);
                i.putStringArrayListExtra("frag4value", frag4_content);
                i.putExtra("title1", oldtst);
                i.putExtra("title2", psa);
                i.putExtra("title3", newtst);
                i.putExtra("title4", fourthtst);
                i.putExtra("oldread", oldread);
                i.putExtra("newread", newread);
                i.putExtra("psalmread", psalmread);
                i.putExtra("fourthread", fourthread);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        }
    }

    class C02183 implements OnClickListener {
        C02183() {
        }

        public void onClick(View v) {
            ArrayList<String> frag1_content = new ArrayList();
            String oldtst = oldtest.getText().toString();
            String psa = psalm.getText().toString();
            String newtst = newtest.getText().toString();
            String fourthtst = fourthtest.getText().toString();
            sharedPreferences = getSharedPreferences("MyPrefs", 0);
            Editor edit = sharedPreferences.edit();
            edit.putString("oldtst", oldtst);
            edit.putString("psa", psa);
            edit.putString("newtst", newtst);
            edit.putString("fourthtst", fourthtst);
            edit.commit();
            if (!(oldtst.equals("Catch-up day") || oldtst.equals("REFLECTION | MAKE-UP"))) {
                frag1_content = newSeparator(oldtst);
            }
            ArrayList<String> frag2_content = newSeparator(psa);
            ArrayList<String> frag3_content = newSeparator(newtst);
            ArrayList<String> frag4_content = newSeparator(fourthtst);
            Intent i = new Intent(CalPage.this, CalContentView.class);
            i.putExtra("viewpager_position", "1");
            i.putExtra("date", getformattedDate);
            i.putExtra("frag1value", frag1_content);
            i.putExtra("frag2value", frag2_content);
            i.putExtra("frag3value", frag3_content);
            i.putExtra("frag4value", frag4_content);
            i.putExtra("title1", oldtst);
            i.putExtra("title2", psa);
            i.putExtra("title3", newtst);
            i.putExtra("title4", fourthtst);
            i.putExtra("oldread", oldread);
            i.putExtra("newread", newread);
            i.putExtra("psalmread", psalmread);
            i.putExtra("fourthread", fourthread);
            startActivity(i);
            overridePendingTransition(0, 0);

        }
    }

    class C02194 implements OnClickListener {
        C02194() {
        }

        public void onClick(View v) {
            String oldtst = oldtest.getText().toString();
            String psa = psalm.getText().toString();
            String newtst = newtest.getText().toString();
            String fourthtst = fourthtest.getText().toString();
            sharedPreferences = getSharedPreferences("MyPrefs", 0);
            Editor edit = sharedPreferences.edit();
            edit.putString("oldtst", oldtst);
            edit.putString("psa", psa);
            edit.putString("newtst", newtst);
            edit.putString("fourthtst", fourthtst);
            edit.commit();
            ArrayList<String> frag1_content = new ArrayList();
            if (!(oldtst.equals("Catch-up day") || oldtst.equals("REFLECTION | MAKE-UP"))) {
                frag1_content = newSeparator(oldtst);
            }
            ArrayList<String> frag2_content = newSeparator(psa);
            ArrayList<String> frag3_content = newSeparator(newtst);
            ArrayList<String> frag4_content = newSeparator(fourthtst);
            Intent i = new Intent(CalPage.this, CalContentView.class);
            i.putExtra("viewpager_position", "2");
            i.putExtra("date", getformattedDate);
            i.putExtra("frag1value", frag1_content);
            i.putExtra("frag2value", frag2_content);
            i.putExtra("frag3value", frag3_content);
            i.putExtra("frag4value", frag4_content);
            i.putExtra("title1", oldtst);
            i.putExtra("title2", psa);
            i.putExtra("title3", newtst);
            i.putExtra("title4", fourthtst);
            i.putExtra("oldread", oldread);
            i.putExtra("newread", newread);
            i.putExtra("psalmread", psalmread);
            i.putExtra("fourthread", fourthread);
            startActivity(i);
            overridePendingTransition(0, 0);

        }
    }

    class C02205 implements OnClickListener {
        C02205() {
        }

        public void onClick(View v) {
            String oldtst = oldtest.getText().toString();
            String psa = psalm.getText().toString();
            String newtst = newtest.getText().toString();
            String fourthtst = fourthtest.getText().toString();
            sharedPreferences = getSharedPreferences("MyPrefs", 0);
            Editor edit = sharedPreferences.edit();
            edit.putString("oldtst", oldtst);
            edit.putString("psa", psa);
            edit.putString("newtst", newtst);
            edit.putString("fourthtst", fourthtst);
            edit.commit();
            ArrayList<String> frag1_content = new ArrayList();
            if (!(oldtst.equals("Catch-up day") || oldtst.equals("REFLECTION | MAKE-UP"))) {
                frag1_content = newSeparator(oldtst);
            }
            ArrayList<String> frag2_content = newSeparator(psa);
            ArrayList<String> frag3_content = newSeparator(newtst);
            ArrayList<String> frag4_content = newSeparator(fourthtst);
            Intent i = new Intent(CalPage.this, CalContentView.class);
            i.putExtra("viewpager_position", "3");
            i.putExtra("date", getformattedDate);
            i.putExtra("frag1value", frag1_content);
            i.putExtra("frag2value", frag2_content);
            i.putExtra("frag3value", frag3_content);
            i.putExtra("frag4value", frag4_content);
            i.putExtra("title1", oldtst);
            i.putExtra("title2", psa);
            i.putExtra("title3", newtst);
            i.putExtra("title4", fourthtst);
            i.putExtra("oldread", oldread);
            i.putExtra("newread", newread);
            i.putExtra("psalmread", psalmread);
            i.putExtra("fourthread", fourthread);
            startActivity(i);
            overridePendingTransition(0, 0);

        }
    }

    private class DownloadXML extends AsyncTask<String, Void, Void> {
        NodeList nodelist = null, nodelist1 = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... Url) {
            try {
                URL url1 = new URL(Url[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                // Download the XML file
                Document doc = db.parse(new InputSource(url1.openStream()));
                traverse(doc);
                doc.getDocumentElement().normalize();
                // Locate the Tag Name
                nodelist = doc.getElementsByTagName("channel");
                Log.e("Nodelist", nodelist + "");
                Node nNode = nodelist.item(0);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Log.e("data", getNode("title", eElement) + "");
                }

                nodelist1 = doc.getElementsByTagName("item");
                for (int i = 0; i < nodelist1.getLength(); i++) {
                    Element eleBook = (Element) nodelist1.item(i);
                    Log.i("Book Node", getNode("title", eleBook) + "");
                    verseTitle = getNode("title", eleBook);
                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void args) {
            initiatePopupWindow();
            verseoftheday();
            date = ivcurrentdate.getText().toString() + " " + currentdate.getText().toString();
            vod = dbhelper.getvod(date);
            if (vod.get(0).equals("")) {
                titlevod.setText("");
                versevod.setText("Unavailable check internet connection");
                power_vod.setText("");
            } else {
                String[] vod1 = vod.get(0).split(",,", 3);
                String s1 = title;
                String s2 = verse;
                titlevod.setText(vod1[0]);
                versevod.setText(vod1[1]);
                power_vod.setText(vod1[2]);

            }
        }
    }

    public void verseoftheday() {
        date = ivcurrentdate.getText().toString() + " " + currentdate.getText().toString();
        dbhelper.getWritableDatabase();
        ArrayList<String> titlevod = new ArrayList();
        ArrayList<String> datevod = new ArrayList();
        titlevod = dbhelper.getvodtitle();
        datevod = dbhelper.getvoddate();
        long id = 0;
        for (int i = 0; i < datevod.size(); i++) {
            if (datevod.get(i).equals(date)) {
                id = id + 1;
            } else {
                id = id;
            }
        }
        for (int i = 0; i < titlevod.size(); i++) {
            if (titlevod.get(i).equals(title)) {
                id = id + 1;
            } else {
                id = id;
            }
        }
        if (id == 0) {
            long idd = dbhelper.insertvod(title, verse, power, date);

        }

    }

    private void initiatePopupWindow() {
        final Dialog dialog1 = new Dialog(CalPage.this, R.style.MyDialogTheme);
        dialog1.setContentView(R.layout.popup_dailyverse);
        final TextView tvVerse = (TextView) dialog1.findViewById(R.id.tv_verse_of_day);
        final TextView tvTitle = (TextView) dialog1.findViewById(R.id.tv_title);
        final TextView tvPower = (TextView) dialog1.findViewById(R.id.tv_power);
        Log.e("cdata", cdata);
        if (cdata != "") {
            tvTitle.setText(verseTitle);
            String s1 = cdata;
            String replaceString = s1.replace("Brought to you by <a href=\"https://www.biblegateway.com\">BibleGateway.com</a>. Copyright (C) . All Rights Reserved.", "");
            tvVerse.setText(Html.fromHtml(replaceString));
            tvPower.setText(Html.fromHtml("Powered by BibleGateway. <a href=\"https://www.biblegateway.com\"> www.BibleGateway.com</a>\nCopyright (C). All Rights Reserved."));
            verse = tvVerse.getText().toString();
            power = tvPower.getText().toString();
            title = tvTitle.getText().toString();
        } else {
            tvTitle.setText("");
            tvVerse.setText("Unable to load content. Check your network connection");
            tvPower.setText("");

        }
        ((Button) dialog1.findViewById(R.id.tv_ok)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        //dialog1.show();

    }


    private void traverse(Node node) {
        // is there anything to do?
        if (node == null) {
            return;
        }
        int type = node.getNodeType();
        switch (type) {
            // print document
            case Node.DOCUMENT_NODE: {
                traverse(((Document) node).getDocumentElement());
                break;
            }
            case Node.ELEMENT_NODE: {
                NodeList children = node.getChildNodes();
                if (children != null) {
                    int len = children.getLength();
                    for (int i = 0; i < len; i++) {
                        traverse(children.item(i));
                    }
                }
                break;
            }
            // handle entity reference nodes
            case Node.ENTITY_REFERENCE_NODE: {
                NodeList children = node.getChildNodes();
                if (children != null) {
                    int len = children.getLength();
                    for (int i = 0; i < len; i++) {
                        traverse(children.item(i));
                    }
                }
                break;
            }
            case Node.CDATA_SECTION_NODE: {
                cdata = node.getNodeValue();
                break;
            }
            case Node.TEXT_NODE: {
                Node pn = node.getParentNode();
                String pnn = pn.getNodeName();
                break;
            }

        }
    }

    private static String getNode(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_cal_page);
        toolbar = (Toolbar) findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("NYI Nexus");
        productGestureDetector = new GestureDetector(CalPage.this);
        if (Helpers.NetworkCheck(getApplicationContext())) {
            new DownloadXML().execute(url);
        }
        this.state = Environment.getExternalStorageState();
        this.pd = new ProgressDialog(this);
        this.pd.setCancelable(false);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setMax(100);
        pb.setVisibility(View.GONE);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
//        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        this.pd.setProgressStyle(16973854);
        this.swipe = (LinearLayout) findViewById(R.id.swipe);
        this.layContent = (LinearLayout) findViewById(R.id.layout_daily_content);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

//        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        this.dbhelper = new DBhelper(this, getFilesDir().getAbsolutePath());
        try {
            this.dbhelper.prepareDatabase();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        this.bible = this.sharedPreferences.getString("bible", null);
        this.userId = this.sharedPreferences.getInt("userid", 0);
        this.table_plan = this.sharedPreferences.getString("tablePlan", null);
        this.planby = this.sharedPreferences.getString("planby", null);
        this.initialdate = this.sharedPreferences.getString("initialdate", null);
        titlevod = (TextView) findViewById(R.id.titlevod);
        versevod = (TextView) findViewById(R.id.verseoftheday);
        power_vod = (TextView) findViewById(R.id.power);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

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
        if (!this.dbhelper.getbookpresent(this.table_plan)) {
            this.dbhelper.addinitialdate(this.table_plan, this.initialdate);
        }

        ctx = this;

        Editor edit = this.sharedPreferences.edit();
        this.currentdate = (TextView) findViewById(R.id.tv_current_date);
        this.ivcurrentdate = (TextView) findViewById(R.id.iv_current_date);
        this.totalComplete = (TextView) findViewById(R.id.tv_content_completed);
        this.oldtest = (TextView) findViewById(R.id.tv_old_testa);
        this.ivOldRead = (ImageView) findViewById(R.id.iv_old_read);
        this.psalm = (TextView) findViewById(R.id.tv_psalm);
        daily_read = (TextView) findViewById(R.id.tv_content_completed_d_r);
        d_read = (ProgressBar) findViewById(R.id.progressBar_dr);
        cal_page = (RelativeLayout) findViewById(R.id.content_cal_page);
        // cal_page.getForeground().setAlpha(0);
        this.dailreadings = (TextView) findViewById(R.id.daily_readings);
        this.ivPsalmRead = (ImageView) findViewById(R.id.iv_psalm_read);
        this.newtest = (TextView) findViewById(R.id.tv_new_testa);
        this.ivNewRead = (ImageView) findViewById(R.id.iv_new_read);
        this.fourthtest = (TextView) findViewById(R.id.tv_fourth_testa);
        this.ivFourthRead = (ImageView) findViewById(R.id.iv_fourth_read);
        this.makeupday = (TextView) findViewById(R.id.tv_makeup_day);
        this.incomplete = (TextView) findViewById(R.id.tv_incomplete);
        this.oldLayout = (LinearLayout) findViewById(R.id.old_layout);
        this.psalmLayout = (LinearLayout) findViewById(R.id.psalm_layout);
        this.newLayout = (LinearLayout) findViewById(R.id.new_layout);
        this.fourthLayout = (LinearLayout) findViewById(R.id.fourth_layout);
//        NavigationView nav = ((NavigationView) findViewById(R.id.nav_view));
//        nav.setNavigationItemSelectedListener(this);
        this.f16c = Calendar.getInstance();
        vodlay = (LinearLayout) findViewById(R.id.lay_vod);
        SimpleDateFormat df = new SimpleDateFormat("MMM dd");
        SimpleDateFormat imagemonth = new SimpleDateFormat("MMM");
        SimpleDateFormat imagedate = new SimpleDateFormat("dd");
        SimpleDateFormat getdetail = new SimpleDateFormat("dd MMM");
        this.getformattedDate = getdetail.format(this.f16c.getTime());
        String str_image_date = imagedate.format(this.f16c.getTime());
        displaymonth(imagemonth.format(this.f16c.getTime()));
        this.currentdate.setText(str_image_date);
        this.currentdate.setTypeface(this.monBold);
        date = ivcurrentdate.getText().toString() + " " + currentdate.getText().toString();
        vod = dbhelper.getvod(date);
        if (vod.get(0).equals("")) {
            titlevod.setText("");
            power_vod.setText("");
            versevod.setText("Unavailable check internet connection");
        } else {
            String[] vod1 = vod.get(0).split(",,", 3);
            String s1 = title;
            String s2 = verse;
            titlevod.setText(vod1[0]);
            versevod.setText(vod1[1]);
            power_vod.setText(vod1[2]);
        }
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_read) {
                    Intent i = new Intent(CalPage.this, bibleContentSelect.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                    finish();
                } else if (tabId == R.id.tab_media) {
                    Intent i = new Intent(CalPage.this, MediaActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();
                } else if (tabId == R.id.tab_plans) {
                    Intent i = new Intent(CalPage.this, MonthlyCal.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();
                } else if (tabId == R.id.tab_pray) {
                    Intent i = new Intent(CalPage.this, myprayergroup.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();
                } else if (tabId == R.id.tab_page) {
                    Intent i = new Intent(CalPage.this, PageActivity.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();

                }
            }
        });
        vodlay.setOnClickListener(new DoubleClickListener() {

            @Override
            public void onDoubleClick() {
                Intent i = new Intent(CalPage.this, Verseoftheday.class);
                startActivity(i);
            }
        });
        ArrayList<MenuItems> menu = new ArrayList<MenuItems>();
        List<String> menuItems = dbhelper.getMenu();
        if (menuItems.size() <= 0) {
            for (int i = 0; i < menu.size(); i++) {
                dbhelper.insertMenuItem(menu.get(i).getMenuid(), menu.get(i).getLangId(), menu.get(i).getMenuname(), menu.get(i).getPageId());
                menuItems = dbhelper.getMenu();
            }
        }
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, menuItems);
        mDrawerList.setAdapter(adapter);

        TextView tvProfile = (TextView) findViewById(R.id.profile_name);
        TextView tvaddress = (TextView) findViewById(R.id.profile_address);
        sharedPreferences = getSharedPreferences("MyPrefs", 0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userdata", "");
        Type collectionType = new TypeToken<Collection<user>>() {
        }.getType();
        List<user> userSites = gson.fromJson(json, collectionType);
//        tvProfile.setText(userSites.get(0).getFirstname()+" "+userSites.get(0).getLastname());
//        tvaddress.setText(userSites.get(0).getAddress()+" "+userSites.get(0).getCity());
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
                        Intent intent = new Intent(CalPage.this, Notes.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;

                    case 8:
                        Intent intent8 = new Intent(CalPage.this, pop_activity_about.class);
                        intent8.setFlags(intent8.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent8);
                        break;
                    case 10:
                        Intent intent10 = new Intent(CalPage.this, EventActivity.class);
                        intent10.setFlags(intent10.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(CalPage.this, UserProfile.class);
                        intent11.setFlags(intent11.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent11);
                        break;
                    case 2:
                        Intent intent2 = new Intent(CalPage.this, popFlashcard.class);
                        intent2.setFlags(intent2.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case 1:
                        search();
                        break;

                    case 4:
                        Intent intent4 = new Intent(CalPage.this, popReadHistory.class);
                        intent4.setFlags(intent4.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                    case 0:
                        Intent intent0 = new Intent(CalPage.this, Settings.class);
                        intent0.setFlags(intent0.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent0);
                        break;

                }
            }
        });

//        for(int i=0;i<menuView.getChildCount();i++ ) {
//            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
//            //   final View titleView = menuView.getChildAt(i).findViewById(android.support.design.R.id.title);
//            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams( );
//            final DisplayMetrics displayMetrics = getResources( ).getDisplayMetrics( );
//            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
//            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
//            iconView.setLayoutParams(layoutParams);
//        }
//            final ViewGroup.LayoutParams layoutParamstitle = titleView.getLayoutParams();
//            final DisplayMetrics displayMetricstitle = getResources().getDisplayMetrics();
//            layoutParamstitle.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, displayMetricstitle);
//            layoutParamstitle.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, displayMetricstitle);
//            titleView.setLayoutParams(layoutParams);

        if (this.planby.equals("bydate")) {
            this.dateContent = this.dbhelper.getDateContent1(this.table_plan, this.getformattedDate);
            this.completed = this.dbhelper.getTotalComplete1(this.table_plan);
            this.totalcount = this.dbhelper.getTotalCount1(this.table_plan);
        } else {
            try {
                this.initdate = new SimpleDateFormat("MMM dd").parse(this.initialdate);
                Log.e("I_Date", initdate.toString());
                this.curredate = getdetail.parse(this.getformattedDate);
                Log.e("C_Date", curredate.toString());
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
            this.dateContent = this.dbhelper.getDateContent2(this.table_plan, String.valueOf(getDifferenceDays(this.initdate, this.curredate)));
            datediff = String.valueOf(getDifferenceDays(this.initdate, this.curredate));
            edit.putString("datediff", datediff);
            edit.commit();
            Log.e("Con Date Content", this.dateContent + "," + this.dateContent.getCount());
            this.completed = this.dbhelper.getTotalComplete1(this.table_plan);
            this.totalcount = this.dbhelper.getTotalCount1(this.table_plan);
        }
        this.totalComplete.setText("You've read " + ((this.completed * 100) / this.totalcount) + "% of the Bible");
        pb.setProgress(((this.completed * 100) / this.totalcount));
        this.totalComplete.setTypeface(this.RobotoSlabLight);
        this.swipe.setOnTouchListener(new OnSwipeTouchListener2(getApplicationContext()) {
            public void onSwipeRight() {
                Calendar calendar = f16c;
                Calendar calendar2 = f16c;
                calendar.add(5, -1);
                currentdate.setText(new SimpleDateFormat("dd").format(f16c.getTime()));
                displaymonth(new SimpleDateFormat("MMM").format(f16c.getTime()));
                SimpleDateFormat getdetail = new SimpleDateFormat("dd MMM");
                getformattedDate = getdetail.format(f16c.getTime());
                Log.e("date", getformattedDate);
                if (planby.equals("bydate")) {
                    dateContent = dbhelper.getDateContent1(table_plan, getformattedDate);
                    completed = dbhelper.getTotalComplete1(table_plan);
                    totalcount = dbhelper.getTotalCount1(table_plan);
                } else {
                    try {
                        SimpleDateFormat getd = new SimpleDateFormat("MMM dd");
                        initdate = getd.parse(initialdate);
                        curredate = getdetail.parse(getformattedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    int id = getDifferenceDays(initdate, curredate);
                    dateContent = dbhelper.getDateContent2(table_plan, String.valueOf(id));
                    completed = dbhelper.getTotalComplete2(table_plan);
                    totalcount = dbhelper.getTotalCount2(table_plan);
                }
                totalComplete.setText("You've read " + ((completed * 100) / totalcount) + "% of the Bible");
                pb.setProgress(((completed * 100) / totalcount));
                totalComplete.setTypeface(RobotoSlabLight);
                makeup = dateContent.getString(dateContent.getColumnIndex("old_testament"));
                if (dateContent.getCount() > 0) {
                    if (makeup.equals("REFLECTION | MAKE-UP")) {
                        layContent.setVisibility(View.GONE);
                        makeupday.setVisibility(0);
                        incomplete.setVisibility(View.GONE);
                    } else {
                        layContent.setVisibility(0);
                        makeupday.setVisibility(View.GONE);
                        // incomplete.setVisibility(0);
                        psalm.setText(dateContent.getString(dateContent.getColumnIndex("PSALM")));
                        oldtest.setText(dateContent.getString(dateContent.getColumnIndex("old_testament")));
                        newtest.setText(dateContent.getString(dateContent.getColumnIndex("new_testament")));
                        fourthtest.setText(dateContent.getString(dateContent.getColumnIndex("fourth_testament")));
                        oldread = dateContent.getString(dateContent.getColumnIndex("old_read"));
                        psalmread = dateContent.getString(dateContent.getColumnIndex("PSALM_read"));
                        newread = dateContent.getString(dateContent.getColumnIndex("new_read"));
                        newread = dateContent.getString(dateContent.getColumnIndex("new_read"));
                        fourthread = dateContent.getString(dateContent.getColumnIndex("fourth_read"));
                        Log.e("fourthrea", fourthread);
                        if (oldread.equals("0")) {
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
                    }
                }

                if (oldread.equals("")) {
                    oldLayout.setVisibility(View.GONE);
                } else if (oldread == null || !oldread.equals("1")) {
                    oldLayout.setVisibility(View.VISIBLE);
                    ivOldRead.setVisibility(View.INVISIBLE);
                } else {
                    ivOldRead.setVisibility(View.VISIBLE);
                    ivOldRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                    oldLayout.setVisibility(View.VISIBLE);

                }
                if (newread.equals("")) {
                    newLayout.setVisibility(View.GONE);
                } else if (newread == null || !newread.equals("1")) {
                    newLayout.setVisibility(View.VISIBLE);
                    ivNewRead.setVisibility(4);
                } else {
                    ivNewRead.setVisibility(0);
                    ivNewRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                    newLayout.setVisibility(0);
                }
                if (psalmread.equals("")) {
                    psalmLayout.setVisibility(View.GONE);
                } else if (psalmread == null || !psalmread.equals("1")) {
                    psalmLayout.setVisibility(0);
                    ivPsalmRead.setVisibility(4);
                } else {
                    psalmLayout.setVisibility(0);
                    ivPsalmRead.setVisibility(0);
                    ivPsalmRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
                if (fourthread.equals("")) {
                    fourthLayout.setVisibility(View.GONE);
                } else if (fourthread == null || !fourthread.equals("1")) {
                    fourthLayout.setVisibility(0);
                    ivFourthRead.setVisibility(4);
                } else {
                    fourthLayout.setVisibility(0);
                    ivFourthRead.setVisibility(0);
                    ivFourthRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
//                if (psalmread.equals("") && newread.equals("") && fourthread.equals("") && int_oldread == 1) {
//                    incomplete.setText("COMPLETE");
//                    incomplete.setTypeface(RobotoSlabThin);
//                    incomplete.setBackgroundResource(R.color.colorPrimary);
//                } else if (newread.equals("") && fourthread.equals("") && int_oldread == 1 && int_psalmread == 1) {
//                    incomplete.setText("COMPLETE");
//                    incomplete.setTypeface(RobotoSlabThin);
//                    incomplete.setBackgroundResource(R.color.colorPrimary);
//                } else if (fourthread.equals("") && int_oldread == 1 && int_psalmread == 1 && int_newread == 1) {
//                    incomplete.setText("COMPLETE");
//                    incomplete.setTypeface(RobotoSlabThin);
//                    incomplete.setBackgroundResource(R.color.colorPrimary);
//                } else if (int_oldread == 1 && int_psalmread == 1 && int_newread == 1 && int_fourthread == 1) {
//                    incomplete.setText("COMPLETE");
//                    incomplete.setTypeface(RobotoSlabThin);
//                    incomplete.setBackgroundResource(R.color.colorPrimary);
//                } else {
//                    incomplete.setText("INCOMPLETE");
//                    incomplete.setTypeface(RobotoSlabThin);
//                    incomplete.setBackgroundResource(R.color.ash);
//                }
                date = ivcurrentdate.getText().toString() + " " + currentdate.getText().toString();
                vod = dbhelper.getvod(date);
                if (vod.get(0).equals("")) {
                    titlevod.setText("");
                    power_vod.setText("");
                    versevod.setText("Unavailable");
                    versevod.setTextSize(30);
                } else {
                    String[] vod1 = vod.get(0).split(",,", 3);
                    String s1 = title;
                    String s2 = verse;
                    titlevod.setText(vod1[0]);
                    versevod.setText(vod1[1]);
                    versevod.setTextSize(15);
                    power_vod.setText(vod1[2]);

                }
            }

            public void onSwipeLeft() {
                Calendar calendar = f16c;
                Calendar calendar2 = f16c;
                calendar.add(5, 1);
                currentdate.setText(new SimpleDateFormat("dd").format(f16c.getTime()));
                displaymonth(new SimpleDateFormat("MMM").format(f16c.getTime()));
                SimpleDateFormat getdetail = new SimpleDateFormat("dd MMM");
                getformattedDate = getdetail.format(f16c.getTime());
                Log.e("date", getformattedDate);
                if (planby.equals("bydate")) {
                    dateContent = dbhelper.getDateContent1(table_plan, getformattedDate);
                    completed = dbhelper.getTotalComplete1(table_plan);
                    totalcount = dbhelper.getTotalCount1(table_plan);
                } else {
                    try {
                        SimpleDateFormat getd = new SimpleDateFormat("dd MMM");
                        initdate = getd.parse(initialdate);
                        curredate = getdetail.parse(getformattedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    int id = getDifferenceDays(initdate, curredate);
                    dateContent = dbhelper.getDateContent2(table_plan, String.valueOf(id));
                    completed = dbhelper.getTotalComplete2(table_plan);
                    totalcount = dbhelper.getTotalCount2(table_plan);
                }
                totalComplete.setText("You've read " + ((completed * 100) / totalcount) + "% of the Bible");
                pb.setProgress(((completed * 100) / totalcount));
                totalComplete.setTypeface(RobotoSlabLight);
                makeup = dateContent.getString(dateContent.getColumnIndex("old_testament"));
                if (dateContent.getCount() > 0) {
                    if (makeup.equals("REFLECTION | MAKE-UP")) {
                        layContent.setVisibility(View.GONE);
                        makeupday.setVisibility(0);
                        incomplete.setVisibility(View.GONE);
                    } else {
                        layContent.setVisibility(0);
                        makeupday.setVisibility(View.GONE);
                        //  incomplete.setVisibility(0);
                        psalm.setText(dateContent.getString(dateContent.getColumnIndex("PSALM")));
                        oldtest.setText(dateContent.getString(dateContent.getColumnIndex("old_testament")));
                        newtest.setText(dateContent.getString(dateContent.getColumnIndex("new_testament")));
                        fourthtest.setText(dateContent.getString(dateContent.getColumnIndex("fourth_testament")));
                        oldread = dateContent.getString(dateContent.getColumnIndex("old_read"));
                        psalmread = dateContent.getString(dateContent.getColumnIndex("PSALM_read"));
                        newread = dateContent.getString(dateContent.getColumnIndex("new_read"));
                        fourthread = dateContent.getString(dateContent.getColumnIndex("fourth_read"));
                        if (oldread.equals("0")) {
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
                    }
                }
                if (oldread.equals("")) {
                    oldLayout.setVisibility(View.GONE);
                } else if (oldread == null || !oldread.equals("1")) {
                    oldLayout.setVisibility(0);
                    ivOldRead.setVisibility(4);
                } else {
                    ivOldRead.setVisibility(0);
                    ivOldRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                    oldLayout.setVisibility(0);
                }
                if (newread.equals("")) {
                    newLayout.setVisibility(View.GONE);
                } else if (newread == null || !newread.equals("1")) {
                    newLayout.setVisibility(0);
                    ivNewRead.setVisibility(4);
                } else {
                    ivNewRead.setVisibility(0);
                    ivNewRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                    newLayout.setVisibility(0);
                }
                if (psalmread.equals("")) {
                    psalmLayout.setVisibility(View.GONE);
                } else if (psalmread == null || !psalmread.equals("1")) {
                    psalmLayout.setVisibility(0);
                    ivPsalmRead.setVisibility(4);
                } else {
                    psalmLayout.setVisibility(0);
                    ivPsalmRead.setVisibility(0);
                    ivPsalmRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
                if (fourthread.equals("")) {
                    fourthLayout.setVisibility(View.GONE);
                } else if (fourthread == null || !fourthread.equals("1")) {
                    fourthLayout.setVisibility(0);
                    ivFourthRead.setVisibility(4);
                } else {
                    fourthLayout.setVisibility(0);
                    ivFourthRead.setVisibility(0);
                    ivFourthRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
//                if (psalmread.equals("") && newread.equals("") && fourthread.equals("") && int_oldread == 1) {
//                    incomplete.setText("COMPLETE");
//                    incomplete.setTypeface(RobotoSlabThin);
//                    incomplete.setBackgroundResource(R.color.colorPrimary);
//
//                } else if (newread.equals("") && fourthread.equals("") && int_oldread == 1 && int_psalmread == 1) {
//                    incomplete.setText("COMPLETE");
//                    incomplete.setTypeface(RobotoSlabThin);
//                    incomplete.setBackgroundResource(R.color.colorPrimary);
//                } else if (fourthread.equals("") && int_oldread == 1 && int_psalmread == 1 && int_newread == 1) {
//                    incomplete.setText("COMPLETE");
//                    incomplete.setTypeface(RobotoSlabThin);
//                    incomplete.setBackgroundResource(R.color.colorPrimary);
//                } else if (int_oldread == 1 && int_psalmread == 1 && int_newread == 1 && int_fourthread == 1) {
//                    incomplete.setText("COMPLETE");
//                    incomplete.setTypeface(RobotoSlabThin);
//                    incomplete.setBackgroundResource(R.color.colorPrimary);
//                } else {
//                    incomplete.setText("INCOMPLETE");
//                    incomplete.setTypeface(RobotoSlabThin);
//                    incomplete.setBackgroundResource(R.color.ash);
//                }
                date = ivcurrentdate.getText().toString() + " " + currentdate.getText().toString();
                vod = dbhelper.getvod(date);
                if (vod.get(0).equals("")) {
                    titlevod.setText("");
                    power_vod.setText("");
                    versevod.setText("Unavailable");
                    versevod.setTextSize(30);
                } else {
                    String[] vod1 = vod.get(0).split(",,", 3);
                    String s1 = title;
                    String s2 = verse;
                    titlevod.setText(vod1[0]);
                    versevod.setText(vod1[1]);
                    versevod.setTextSize(15);
                    power_vod.setText(vod1[2]);

                }
            }
        });
        this.oldtest.setOnClickListener(new C02172());
        this.psalm.setOnClickListener(new C02183());
        this.newtest.setOnClickListener(new C02194());
        this.fourthtest.setOnClickListener(new C02205());

        d_read.setVisibility(View.GONE);
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen((int) GravityCompat.START)) {
            drawer.closeDrawer((int) GravityCompat.START);
            return;
        }
        Intent in = new Intent("android.intent.action.MAIN");
        in.addCategory("android.intent.category.HOME");
        in.setFlags(268468224);
        startActivity(in);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cal_con, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search) {
            Intent intent = new Intent(CalPage.this, bibleContentSelect.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            return true;
        }
        if (id == R.id.monthlydate) {
            Intent intent = new Intent(CalPage.this, MonthlyCal.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.incomplete) {
            Intent intent = new Intent(CalPage.this, IncompleteReadings.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem register = menu.findItem(R.id.incomplete);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("dd MMM");
        SimpleDateFormat month = new SimpleDateFormat("MMM");
        String CurrentDate = date.format(cal.getTime());
        String CurrentMonth = month.format(cal.getTime());
        if (dbhelper.getReadings(table_plan, dbhelper.getInitialdateId(table_plan, initialdate), dbhelper.getInitialdateId(table_plan, CurrentDate)).size() == 0) {
            register.setVisible(false);
        } else {
            register.setVisible(true);
        }
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_about) {
            Intent intent = new Intent(CalPage.this, pop_activity_about.class);
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
            search();

        } else if (id == R.id.nav_readhistory) {

            Intent intent = new Intent(this, popReadHistory.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            this.startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(CalPage.this, Settings.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        if (id == R.id.nav_about) {
            Intent intent = new Intent(CalPage.this, pop_activity_about.class);
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
            search();

        } else if (id == R.id.nav_readhistory) {

            Intent intent = new Intent(this, popReadHistory.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            this.startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(CalPage.this, Settings.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.nav_sync) {
            Intent intent = new Intent(CalPage.this, Sync.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

//    public boolean onPrepareOptionsMenu(Menu menu) {
//        MenuItem register = menu.findItem(R.id.incomplete);
//        Calendar cal = Calendar.getInstance( );
//        SimpleDateFormat date = new SimpleDateFormat("dd MMM");
//        SimpleDateFormat month = new SimpleDateFormat("MMM");
//        String CurrentDate = date.format(cal.getTime( ));
//        String CurrentMonth = month.format(cal.getTime( ));
//        List valu = this.dbhelper.getReadings(this.table_plan, this.dbhelper.getInitialdateId(this.table_plan, this.initialdate), this.dbhelper.getInitialdateId(this.table_plan, CurrentDate));
//        IncompleteModel model = new IncompleteModel( );
//        if (valu.size( ) == 0) {
//            register.setVisible(false);
//        } else {
//            register.setVisible(true);
//        }
//        return true;
//    }

    private void loaddata() {
        FileNotFoundException e;
        IOException e2;
        if (this.state.equals("mounted")) {
            try {
                File textFile = new File(Environment.getExternalStorageDirectory() + "/catc plan/plandata.xml");
                if (textFile.exists()) {
                    this.dbhelper.resetdata2(this.table_plan);
                    BufferedReader reader = new BufferedReader(new FileReader(textFile));
                    BufferedReader bufferedReader;
                    try {
                        this.textBuilder = new StringBuffer();
                        while (true) {
                            String line = reader.readLine();
                            if (line != null) {
                                this.textBuilder.append(line);
                                this.textBuilder.append("\n");
                            } else {
                                Log.e("Text build", this.textBuilder + "");
                                InputStream inputStream = new ByteArrayInputStream(this.textBuilder.toString().getBytes());
                                this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
                                Editor edit = this.sharedPreferences.edit();
                                edit.putString("activit", "calPage");
                                edit.commit();
                                this.employees = new XMLPullParserHandler(this).parse(inputStream);
                                this.pd.dismiss();
                                Toast.makeText(getApplicationContext(), "Data Loaded", 1).show();
                                bufferedReader = reader;
                                return;
                            }
                        }
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        bufferedReader = reader;
                        e.printStackTrace();
                        return;
                    } catch (IOException e4) {
                        e2 = e4;
                        bufferedReader = reader;
                        e2.printStackTrace();
                        return;
                    }
                }
                this.pd.dismiss();
                Toast.makeText(this, "Data not exists", 1).show();
                return;

            } catch (IOException e6) {
                e2 = e6;
                e2.printStackTrace();
                return;
            }
        }
        Toast.makeText(getApplicationContext(), "There is no any sd card", 1).show();
    }

    private void displaymonth(String str_image_month) {
        if (str_image_month.equals("Jan")) {
            this.ivcurrentdate.setText("JAN");
            this.ivcurrentdate.setTypeface(this.tribbon);
        } else if (str_image_month.equals("Feb")) {
            this.ivcurrentdate.setText("FEB");
            this.ivcurrentdate.setTypeface(this.tribbon);
        } else if (str_image_month.equals("Mar")) {
            this.ivcurrentdate.setText("MAR");
            this.ivcurrentdate.setTypeface(this.tribbon);
        } else if (str_image_month.equals("Apr")) {
            this.ivcurrentdate.setText("APR");
            this.ivcurrentdate.setTypeface(this.tribbon);
        } else if (str_image_month.equals("May")) {
            this.ivcurrentdate.setText("MAY");
            this.ivcurrentdate.setTypeface(this.tribbon);
        } else if (str_image_month.equals("Jun")) {
            this.ivcurrentdate.setText("JUN");
            this.ivcurrentdate.setTypeface(this.tribbon);
        } else if (str_image_month.equals("Jul")) {
            this.ivcurrentdate.setText("JUL");
            this.ivcurrentdate.setTypeface(this.tribbon);
        } else if (str_image_month.equals("Aug")) {
            this.ivcurrentdate.setText("AUG");
            this.ivcurrentdate.setTypeface(this.tribbon);
        } else if (str_image_month.equals("Sep")) {
            this.ivcurrentdate.setText("SEP");
            this.ivcurrentdate.setTypeface(this.tribbon);
        } else if (str_image_month.equals("Oct")) {
            this.ivcurrentdate.setText("OCT");
            this.ivcurrentdate.setTypeface(this.tribbon);
        } else if (str_image_month.equals("Nov")) {
            this.ivcurrentdate.setText("NOV");
            this.ivcurrentdate.setTypeface(this.tribbon);
        } else if (str_image_month.equals("Dec")) {
            this.ivcurrentdate.setText("DEC");
            this.ivcurrentdate.setTypeface(this.tribbon);
        }
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
                //edit_gen.putString("FinalContent", FinalContent);
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
                ArrayList<String> FC = new ArrayList();
                FinalContent = dbhelper.getMonBook(bible, bookid);
                for (int i = 0; i < FinalContent.size(); i++) {
                    FC.add(FinalContent.get(i));
                }
                FinalContent = dbhelper.getMonBook(bible, bookid1);
                for (int i = 0; i < FinalContent.size(); i++) {
                    FC.add(FinalContent.get(i));
                }
                FinalContent = dbhelper.getMonBook(bible, bookid2);
                for (int i = 0; i < FinalContent.size(); i++) {
                    FC.add(FinalContent.get(i));
                }
                FinalContent = FC;
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
                for (int i = 0; i < FinalContent.size(); i++) {
                    FC.add(FinalContent.get(i));
                }
                FinalContent = dbhelper.getMonBookChapstartend(bible, bookid2, chapstart, chapend);
                for (int i = 0; i < FinalContent.size(); i++) {
                    FC.add(FinalContent.get(i));
                }
                FinalContent = FC;
//                FinalContent += dbhelper.getMonBookChapstartend(bookid2, chapstart, chapend);
                //int id1=dbhelper.getMonBookChapstartId(bookid2, chapstart);
                //int id2=dbhelper.getMonBookChapendId(bookid2, chapend);

                // FinalContent +=dbhelper.getContentbyrange(id1,id2);
                Log.e("Content", FinalContent.get(0));
            }
        }
        return FinalContent;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onResume() {
        super.onResume();
        tvTitle.setText("NYI Nexus");
//        cal_page.getForeground().setAlpha(0);
        date = ivcurrentdate.getText().toString() + " " + currentdate.getText().toString();
        vod = dbhelper.getvod(date);
        if (vod.get(0).equals("")) {
            titlevod.setText("");
            power_vod.setText("");
            versevod.setText("Unavailable check internet connection");
        } else {
            String[] vod1 = vod.get(0).split(",,", 3);
            String s1 = title;
            String s2 = verse;
            titlevod.setText(vod1[0]);
            versevod.setText(vod1[1]);
            power_vod.setText(vod1[2]);
        }
        bottomBar.selectTabWithId(R.id.tab_home);
        invalidateOptionsMenu();
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        this.bible = this.sharedPreferences.getString("bible", null);
        this.table_plan = this.sharedPreferences.getString("tablePlan", null);
        this.planby = this.sharedPreferences.getString("planby", null);
        SimpleDateFormat getdetail = new SimpleDateFormat("dd MMM");
        if (!this.dbhelper.getbookpresent(this.table_plan)) {
            this.dbhelper.addinitialdate(this.table_plan, this.initialdate);
        }
        if (this.planby.equals("bydate")) {
            this.dateContent = this.dbhelper.getDateContent1(this.table_plan, this.getformattedDate);
            this.completed = this.dbhelper.getTotalComplete1(this.table_plan);
            this.totalcount = this.dbhelper.getTotalCount1(this.table_plan);
        } else {
            try {
                this.initdate = new SimpleDateFormat("MMM dd").parse(this.initialdate);
                this.curredate = getdetail.parse(this.getformattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.dateContent = this.dbhelper.getDateContent2(this.table_plan, String.valueOf(getDifferenceDays(this.initdate, this.curredate)));
            datediff = String.valueOf(getDifferenceDays(this.initdate, this.curredate));
            Editor edit = sharedPreferences.edit();
            edit.putString("datediff", datediff);
            edit.commit();
            Log.e("Con Date Content", this.dateContent + "," + this.dateContent.getCount());
            this.completed = this.dbhelper.getTotalComplete2(this.table_plan);
            this.totalcount = this.dbhelper.getTotalCount2(this.table_plan);
        }
        if (this.dateContent == null) {
            Toast.makeText(getApplicationContext(), "Data not available", 1).show();
            this.layContent.setVisibility(View.GONE);
            this.makeupday.setVisibility(View.GONE);
            // this.incomplete.setVisibility(4);
            return;
        }
        this.completed = this.dbhelper.getTotalComplete1(this.table_plan);
        this.totalcount = this.dbhelper.getTotalCount1(this.table_plan);
        this.totalComplete.setText("You've read " + ((this.completed * 100) / this.totalcount) + "% of the Bible");
        pb.setProgress(((this.completed * 100) / this.totalcount));
        this.totalComplete.setTypeface(this.RobotoSlabLight);
        if (this.dateContent.getCount() > 0) {
            this.layContent.setVisibility(0);
            this.makeupday.setVisibility(View.GONE);
            //this.incomplete.setVisibility(0);
            this.psalm.setText(this.dateContent.getString(this.dateContent.getColumnIndex("PSALM")));
            this.psalm.setTypeface(this.monLight);
            this.oldtest.setText(this.dateContent.getString(this.dateContent.getColumnIndex("old_testament")));
            this.oldtest.setTypeface(this.monLight);
            this.newtest.setText(this.dateContent.getString(this.dateContent.getColumnIndex("new_testament")));
            this.newtest.setTypeface(this.monLight);
            this.fourthtest.setText(this.dateContent.getString(this.dateContent.getColumnIndex("fourth_testament")));
            this.fourthtest.setTypeface(this.monLight);
            this.oldread = this.dateContent.getString(this.dateContent.getColumnIndex("old_read"));
            this.psalmread = this.dateContent.getString(this.dateContent.getColumnIndex("PSALM_read"));
            this.newread = this.dateContent.getString(this.dateContent.getColumnIndex("new_read"));
            this.fourthread = this.dateContent.getString(this.dateContent.getColumnIndex("fourth_read"));
            if (this.oldread.equals("0") || this.oldread.equals("")) {
                this.int_oldread = 0;
            } else {
                this.int_oldread = Integer.parseInt(this.oldread);
            }
            if (this.psalmread.equals("0") || this.psalmread.equals("")) {
                this.int_psalmread = 0;
            } else {
                this.int_psalmread = Integer.parseInt(this.psalmread);
            }
            if (this.newread.equals("0") || this.newread.equals("")) {
                this.int_newread = 0;
            } else {
                this.int_newread = Integer.parseInt(this.newread);
            }
            if (this.fourthread.equals("0") || this.fourthread.equals("")) {
                this.int_fourthread = 0;
            } else {
                this.int_fourthread = Integer.parseInt(this.fourthread);
            }
        }
        int totalcontentread = ((int_newread + int_psalmread) + int_oldread) + int_fourthread;
        if (totalcontentread == 0) {
            daily_read.setText("You've read 0% of the daily reading");
            d_read.setProgress(0);
        } else if (psalmread.equals("") && newread.equals("") && fourthread.equals("")) {
            daily_read.setText("You've read " + ((totalcontentread * 100) / 1) + "% of the daily reading");
            d_read.setProgress(((totalcontentread * 100) / 1));
        } else if (newread.equals("") && fourthread.equals("")) {
            daily_read.setText("You've read " + ((totalcontentread * 100) / 2) + "% of the daily reading");
            d_read.setProgress(((totalcontentread * 100) / 2));
        } else if (fourthread.equals("")) {
            daily_read.setText("You've read " + ((totalcontentread * 100) / 3) + "% of the daily reading");
            d_read.setProgress(((totalcontentread * 100) / 3));
        } else {
            daily_read.setText("You've read " + ((totalcontentread * 100) / 4) + "% of the daily reading");
            d_read.setProgress(((totalcontentread * 100) / 4));

        }
        if (this.oldread.equals("")) {
            this.oldLayout.setVisibility(View.GONE);
        } else if (this.oldread == null || !this.oldread.equals("1")) {
            this.oldLayout.setVisibility(0);
            this.ivOldRead.setVisibility(4);
        } else {
            this.oldLayout.setVisibility(0);
            this.ivOldRead.setVisibility(0);
            this.ivOldRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
        }
        if (this.newread.equals("")) {
            this.newLayout.setVisibility(View.GONE);
        } else if (this.newread == null || !this.newread.equals("1")) {
            this.ivNewRead.setVisibility(0);
            this.ivNewRead.setVisibility(4);
        } else {
            this.ivNewRead.setVisibility(0);
            this.ivNewRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
            this.newLayout.setVisibility(0);
        }
        if (this.psalmread.equals("")) {
            this.psalmLayout.setVisibility(View.GONE);
        } else if (this.psalmread == null || !this.psalmread.equals("1")) {
            this.psalmLayout.setVisibility(0);
            this.ivPsalmRead.setVisibility(4);
        } else {
            this.psalmLayout.setVisibility(0);
            this.ivPsalmRead.setVisibility(0);
            this.ivPsalmRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
        }
        if (this.fourthread.equals("")) {
            this.fourthLayout.setVisibility(View.GONE);
        } else if (this.fourthread == null || !this.fourthread.equals("1")) {
            this.fourthLayout.setVisibility(0);
            this.ivFourthRead.setVisibility(4);
        } else {
            this.fourthLayout.setVisibility(0);
            this.ivFourthRead.setVisibility(0);
            this.ivFourthRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
        }
//        if ((this.psalmread.equals("") || this.newread.equals("") || this.fourthread.equals("")) && this.int_oldread == 1) {
//            this.incomplete.setText("COMPLETE");
//            this.incomplete.setTypeface(this.RobotoSlabThin);
//            this.incomplete.setBackgroundResource(R.color.colorPrimary);
//            d_read.setProgress(100);
//            daily_read.setText("You've read 100% of the Daily Readings");
//        } else if ((this.newread.equals("") || this.fourthread.equals("")) && this.int_oldread == 1 && this.int_psalmread == 1) {
//            this.incomplete.setText("COMPLETE");
//            this.incomplete.setTypeface(this.RobotoSlabThin);
//            this.incomplete.setBackgroundResource(R.color.colorPrimary);
//            d_read.setProgress(100);
//            daily_read.setText("You've read 100% of the Daily Readings");
//        } else if (this.fourthread.equals("") && this.int_oldread == 1 && this.int_psalmread == 1 && this.int_newread == 1) {
//            this.incomplete.setText("COMPLETE");
//            this.incomplete.setTypeface(this.RobotoSlabThin);
//            this.incomplete.setBackgroundResource(R.color.colorPrimary);
//            d_read.setProgress(100);
//            daily_read.setText("You've read 100% of the Daily Readings");
//        } else if (this.int_oldread == 1 && this.int_psalmread == 1 && this.int_newread == 1 && this.int_fourthread == 1) {
//            this.incomplete.setText("COMPLETE");
//            this.incomplete.setTypeface(this.RobotoSlabThin);
//            this.incomplete.setBackgroundResource(R.color.colorPrimary);
//            d_read.setProgress(100);
//            daily_read.setText("You've read 100% of the Daily Readings");
//        } else {
//            this.incomplete.setText("INCOMPLETE");
//            this.incomplete.setTypeface(this.RobotoSlabThin);
//            this.incomplete.setBackgroundResource(R.color.ash);
//        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return productGestureDetector.onTouchEvent(ev);
    }

    public void swipeToLeft() {

        Calendar calendar = f16c;
        Calendar calendar2 = f16c;
        calendar.add(5, 1);
        currentdate.setText(new SimpleDateFormat("dd").format(f16c.getTime()));
        displaymonth(new SimpleDateFormat("MMM").format(f16c.getTime()));
        SimpleDateFormat getdetail = new SimpleDateFormat("dd MMM");
        getformattedDate = getdetail.format(f16c.getTime());
        Log.e("date", getformattedDate);
        date = ivcurrentdate.getText().toString() + " " + currentdate.getText().toString();
        vod = dbhelper.getvod(date);
        if (vod.get(0).equals("")) {
            titlevod.setText("");
            power_vod.setText("");
            versevod.setText("Unavailable");
            versevod.setTextSize(30);
        } else {
            String[] vod1 = vod.get(0).split(",,", 3);
            String s1 = title;
            String s2 = verse;
            titlevod.setText(vod1[0]);
            versevod.setText(vod1[1]);
            versevod.setTextSize(15);
            power_vod.setText(vod1[2]);

        }
        if (planby.equals("bydate")) {
            dateContent = dbhelper.getDateContent1(table_plan, getformattedDate);
            completed = dbhelper.getTotalComplete1(table_plan);
            totalcount = dbhelper.getTotalCount1(table_plan);
        } else {
            try {
                SimpleDateFormat getd = new SimpleDateFormat("MMM dd");
                initdate = getd.parse(initialdate);
                curredate = getdetail.parse(getformattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int id = getDifferenceDays(initdate, curredate);
            datediff = String.valueOf(id);
            Editor edit = this.sharedPreferences.edit();
            edit.putString("datediff", datediff);
            edit.commit();
            dateContent = dbhelper.getDateContent2(table_plan, String.valueOf(id));
            completed = dbhelper.getTotalComplete2(table_plan);
            totalcount = dbhelper.getTotalCount2(table_plan);
        }
        totalComplete.setText("You've read " + ((completed * 100) / totalcount) + "% of the Bible");
        pb.setProgress(((completed * 100) / totalcount));
        totalComplete.setTypeface(RobotoSlabLight);
        makeup = dateContent.getString(dateContent.getColumnIndex("old_testament"));
        if (dateContent.getCount() > 0) {
            if (makeup.equals("REFLECTION | MAKE-UP")) {
                layContent.setVisibility(View.GONE);
                makeupday.setVisibility(0);
                //incomplete.setVisibility(View.GONE);
            } else {
                layContent.setVisibility(0);
                makeupday.setVisibility(View.GONE);
                // incomplete.setVisibility(0);
                psalm.setText(dateContent.getString(dateContent.getColumnIndex("PSALM")));
                oldtest.setText(dateContent.getString(dateContent.getColumnIndex("old_testament")));
                newtest.setText(dateContent.getString(dateContent.getColumnIndex("new_testament")));
                fourthtest.setText(dateContent.getString(dateContent.getColumnIndex("fourth_testament")));
                oldread = dateContent.getString(dateContent.getColumnIndex("old_read"));
                psalmread = dateContent.getString(dateContent.getColumnIndex("PSALM_read"));
                newread = dateContent.getString(dateContent.getColumnIndex("new_read"));
                fourthread = dateContent.getString(dateContent.getColumnIndex("fourth_read"));
                if (oldread.equals("0")) {
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
            }
        }
        int totalcontentread = ((int_newread + int_psalmread) + int_oldread) + int_fourthread;
        if (totalcontentread == 0) {
            daily_read.setText("You've read 0% of the daily reading");
            d_read.setProgress(0);
        } else if (psalmread.equals("") && newread.equals("") && fourthread.equals("")) {
            daily_read.setText("You've read " + ((totalcontentread * 100) / 1) + "% of the daily reading");
            d_read.setProgress(((totalcontentread * 100) / 1));
        } else if (newread.equals("") && fourthread.equals("")) {
            daily_read.setText("You've read " + ((totalcontentread * 100) / 2) + "% of the daily reading");
            d_read.setProgress(((totalcontentread * 100) / 2));
        } else if (fourthread.equals("")) {
            daily_read.setText("You've read " + ((totalcontentread * 100) / 3) + "% of the daily reading");
            d_read.setProgress(((totalcontentread * 100) / 3));
        } else {
            daily_read.setText("You've read " + ((totalcontentread * 100) / 4) + "% of the daily reading");
            d_read.setProgress(((totalcontentread * 100) / 4));

        }
        if (oldread.equals("")) {
            oldLayout.setVisibility(View.GONE);
        } else if (oldread == null || !oldread.equals("1")) {
            oldLayout.setVisibility(0);
            ivOldRead.setVisibility(4);
        } else {
            ivOldRead.setVisibility(0);
            ivOldRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
            oldLayout.setVisibility(0);
        }
        if (newread.equals("")) {
            newLayout.setVisibility(View.GONE);
        } else if (newread == null || !newread.equals("1")) {
            newLayout.setVisibility(0);
            ivNewRead.setVisibility(4);
        } else {
            ivNewRead.setVisibility(0);
            ivNewRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
            newLayout.setVisibility(0);
        }
        if (psalmread.equals("")) {
            psalmLayout.setVisibility(View.GONE);
        } else if (psalmread == null || !psalmread.equals("1")) {
            psalmLayout.setVisibility(0);
            ivPsalmRead.setVisibility(4);
        } else {
            psalmLayout.setVisibility(0);
            ivPsalmRead.setVisibility(0);
            ivPsalmRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
        }
        if (fourthread.equals("")) {
            fourthLayout.setVisibility(View.GONE);
        } else if (fourthread == null || !fourthread.equals("1")) {
            fourthLayout.setVisibility(0);
            ivFourthRead.setVisibility(4);
        } else {
            fourthLayout.setVisibility(0);
            ivFourthRead.setVisibility(0);
            ivFourthRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
        }
//        if (psalmread.equals("") && newread.equals("") && fourthread.equals("") && int_oldread == 1) {
//            incomplete.setText("COMPLETE");
//            incomplete.setTypeface(RobotoSlabThin);
//            incomplete.setBackgroundResource(R.color.colorPrimary);
//        } else if (newread.equals("") && fourthread.equals("") && int_oldread == 1 && int_psalmread == 1) {
//            incomplete.setText("COMPLETE");
//            incomplete.setTypeface(RobotoSlabThin);
//            incomplete.setBackgroundResource(R.color.colorPrimary);
//        } else if (fourthread.equals("") && int_oldread == 1 && int_psalmread == 1 && int_newread == 1) {
//            incomplete.setText("COMPLETE");
//            incomplete.setTypeface(RobotoSlabThin);
//            incomplete.setBackgroundResource(R.color.colorPrimary);
//        } else if (int_oldread == 1 && int_psalmread == 1 && int_newread == 1 && int_fourthread == 1) {
//            incomplete.setText("COMPLETE");
//            incomplete.setTypeface(RobotoSlabThin);
//            incomplete.setBackgroundResource(R.color.colorPrimary);
//        } else {
//            incomplete.setText("INCOMPLETE");
//            incomplete.setTypeface(RobotoSlabThin);
//            incomplete.setBackgroundResource(R.color.ash);
//        }


    }

    public void swipeToRight() {

        Calendar calendar = f16c;
        Calendar calendar2 = f16c;
        calendar.add(5, -1);
        currentdate.setText(new SimpleDateFormat("dd").format(f16c.getTime()));
        displaymonth(new SimpleDateFormat("MMM").format(f16c.getTime()));
        SimpleDateFormat getdetail = new SimpleDateFormat("dd MMM");
        getformattedDate = getdetail.format(f16c.getTime());
        Log.e("date", getformattedDate);
        date = ivcurrentdate.getText().toString() + " " + currentdate.getText().toString();
        vod = dbhelper.getvod(date);
        if (vod.get(0).equals("")) {
            titlevod.setText("");
            power_vod.setText("");
            versevod.setText("Unavailable");
            versevod.setTextSize(30);
        } else {
            String[] vod1 = vod.get(0).split(",,", 3);
            String s1 = title;
            String s2 = verse;
            titlevod.setText(vod1[0]);
            versevod.setText(vod1[1]);
            versevod.setTextSize(15);
            power_vod.setText(vod1[2]);

        }
        if (planby.equals("bydate")) {
            dateContent = dbhelper.getDateContent1(table_plan, getformattedDate);
            completed = dbhelper.getTotalComplete1(table_plan);
            totalcount = dbhelper.getTotalCount1(table_plan);
        } else {
            try {
                SimpleDateFormat getd = new SimpleDateFormat("MMM dd");
                initdate = getd.parse(initialdate);
                curredate = getdetail.parse(getformattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int id = getDifferenceDays(initdate, curredate);
            Editor edit = sharedPreferences.edit();
            edit.putString("datediff", datediff);
            datediff = String.valueOf(id);
            edit.commit();
            dateContent = dbhelper.getDateContent2(table_plan, String.valueOf(id));
            completed = dbhelper.getTotalComplete2(table_plan);
            totalcount = dbhelper.getTotalCount2(table_plan);
        }
        totalComplete.setText("You've read " + ((completed * 100) / totalcount) + "% of the Bible");
        pb.setProgress(((completed * 100) / totalcount));
        totalComplete.setTypeface(RobotoSlabLight);
        makeup = dateContent.getString(dateContent.getColumnIndex("old_testament"));
        if (dateContent.getCount() > 0) {
            if (makeup.equals("REFLECTION | MAKE-UP")) {
                layContent.setVisibility(View.GONE);
                makeupday.setVisibility(0);
                //   incomplete.setVisibility(View.GONE);
            } else {
                layContent.setVisibility(0);
                makeupday.setVisibility(View.GONE);
                // incomplete.setVisibility(0);
                psalm.setText(dateContent.getString(dateContent.getColumnIndex("PSALM")));
                oldtest.setText(dateContent.getString(dateContent.getColumnIndex("old_testament")));
                newtest.setText(dateContent.getString(dateContent.getColumnIndex("new_testament")));
                fourthtest.setText(dateContent.getString(dateContent.getColumnIndex("fourth_testament")));
                oldread = dateContent.getString(dateContent.getColumnIndex("old_read"));
                psalmread = dateContent.getString(dateContent.getColumnIndex("PSALM_read"));
                newread = dateContent.getString(dateContent.getColumnIndex("new_read"));
                newread = dateContent.getString(dateContent.getColumnIndex("new_read"));
                fourthread = dateContent.getString(dateContent.getColumnIndex("fourth_read"));
                Log.e("fourthrea", fourthread);
                if (oldread.equals("0")) {
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
            }
        }
        int totalcontentread = ((int_newread + int_psalmread) + int_oldread) + int_fourthread;
        if (totalcontentread == 0) {
            daily_read.setText("You've read 0% of the daily reading");
            d_read.setProgress(0);
        } else if (psalmread.equals("") && newread.equals("") && fourthread.equals("")) {
            daily_read.setText("You've read " + ((totalcontentread * 100) / 1) + "% of the daily reading");
            d_read.setProgress(((totalcontentread * 100) / 1));
        } else if (newread.equals("") && fourthread.equals("")) {
            daily_read.setText("You've read " + ((totalcontentread * 100) / 2) + "% of the daily reading");
            d_read.setProgress(((totalcontentread * 100) / 2));
        } else if (fourthread.equals("")) {
            daily_read.setText("You've read " + ((totalcontentread * 100) / 3) + "% of the daily reading");
            d_read.setProgress(((totalcontentread * 100) / 3));
        } else {
            daily_read.setText("You've read " + ((totalcontentread * 100) / 4) + "% of the daily reading");
            d_read.setProgress(((totalcontentread * 100) / 4));

        }
        if (oldread.equals("")) {
            oldLayout.setVisibility(View.GONE);
        } else if (oldread == null || !oldread.equals("1")) {
            oldLayout.setVisibility(View.VISIBLE);
            ivOldRead.setVisibility(View.INVISIBLE);
        } else {
            ivOldRead.setVisibility(View.VISIBLE);
            ivOldRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
            oldLayout.setVisibility(View.VISIBLE);
            dr1 = 25;
        }
        if (newread.equals("")) {
            newLayout.setVisibility(View.GONE);
        } else if (newread == null || !newread.equals("1")) {
            newLayout.setVisibility(View.VISIBLE);
            ivNewRead.setVisibility(4);
        } else {
            ivNewRead.setVisibility(0);
            ivNewRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
            newLayout.setVisibility(0);
            dr2 = dr1 + 25;
        }
        if (psalmread.equals("")) {
            psalmLayout.setVisibility(View.GONE);
        } else if (psalmread == null || !psalmread.equals("1")) {
            psalmLayout.setVisibility(0);
            ivPsalmRead.setVisibility(4);

        } else {
            psalmLayout.setVisibility(0);
            ivPsalmRead.setVisibility(0);
            ivPsalmRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
            dr3 = dr2 + 25;
        }
        if (fourthread.equals("")) {
            fourthLayout.setVisibility(View.GONE);
        } else if (fourthread == null || !fourthread.equals("1")) {
            fourthLayout.setVisibility(0);
            ivFourthRead.setVisibility(4);
        } else {
            fourthLayout.setVisibility(0);
            ivFourthRead.setVisibility(0);
            ivFourthRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
            dr4 = dr3 + 25;
        }
//        if (psalmread.equals("") && newread.equals("") && fourthread.equals("") && int_oldread == 1) {
//            incomplete.setText("COMPLETE");
//            incomplete.setTypeface(RobotoSlabThin);
//            incomplete.setBackgroundResource(R.color.colorPrimary);
//            d_read.setProgress(100);
//            daily_read.setText("You've read 100% of the Daily Readings");
//        } else if (newread.equals("") && fourthread.equals("") && int_oldread == 1 && int_psalmread == 1) {
//            incomplete.setText("COMPLETE");
//            incomplete.setTypeface(RobotoSlabThin);
//            incomplete.setBackgroundResource(R.color.colorPrimary);
//            d_read.setProgress(100);
//            daily_read.setText("You've read 100% of the Daily Readings");
//        } else if (fourthread.equals("") && int_oldread == 1 && int_psalmread == 1 && int_newread == 1) {
//            incomplete.setText("COMPLETE");
//            incomplete.setTypeface(RobotoSlabThin);
//            daily_read.setText("You've read 100% of the Daily Readings");
//            incomplete.setBackgroundResource(R.color.colorPrimary);
//            d_read.setProgress(100);
//        } else if (int_oldread == 1 && int_psalmread == 1 && int_newread == 1 && int_fourthread == 1) {
//            incomplete.setText("COMPLETE");
//            incomplete.setTypeface(RobotoSlabThin);
//            incomplete.setBackgroundResource(R.color.colorPrimary);
//            daily_read.setText("You've read 100% of the Daily Readings");
//
//            d_read.setProgress(100);
//        } else {
//            incomplete.setText("INCOMPLETE");
//            incomplete.setTypeface(RobotoSlabThin);
//            incomplete.setBackgroundResource(R.color.ash);
//        }

    }

    public int getDifferenceDays(Date d1, Date d2) {
        Log.e("Dates", d1 + "," + d2);
        return (int) (((d2.getTime() - d1.getTime()) / 86400000) + 1);
    }

    private void search() {
        if (this.pop_search_word == null) {
            this.pop_search_word = new Dialog(CalPage.this);
            this.pop_search_word.requestWindowFeature(1);
            this.pop_search_word.setTitle("Search Option");
            this.pop_search_word.setCanceledOnTouchOutside(true);
            this.pop_search_word.setCancelable(true);
            this.pop_search_word.setContentView(R.layout.popup_search);
            final EditText edtSearchWord = (EditText) this.pop_search_word.findViewById(R.id.edt_search_word);
            Button btnSumbit = (Button) this.pop_search_word.findViewById(R.id.btn_submit);
            Button btnCancel = (Button) this.pop_search_word.findViewById(R.id.btn_cancel);
            pop_search_word.show();
            // ImageView ivclose = (ImageView) this.pop_search_word.findViewById(R.id.iv_close);

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
                        }
                    }
                    return true;
                }
            });
            btnCancel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    pop_search_word.dismiss();
                }
            });

        } else if (this.pop_search_word.isShowing()) {
            this.pop_search_word.dismiss();
            this.pop_search_word = null;
            search();
        } else {
            this.pop_search_word = null;
            search();
        }
    }


    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

