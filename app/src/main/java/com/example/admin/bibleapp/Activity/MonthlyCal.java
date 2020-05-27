package com.example.admin.bibleapp.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.admin.bibleapp.Adapter.DrawerItemCustomAdapter;
import com.example.admin.bibleapp.CalendarView;
import com.example.admin.bibleapp.CalendarView.EventHandler;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.MenuItems;
import com.example.admin.bibleapp.Model.user;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.ViewPager.CalContentView;
import com.example.admin.bibleapp.popupWindow.popFlashcard;
import com.example.admin.bibleapp.popupWindow.popReadHistory;
import com.example.admin.bibleapp.popupWindow.popSearch;
import com.example.admin.bibleapp.popupWindow.pop_activity_about;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class MonthlyCal extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    private static final String TAG = MonthlyCal.class.getName();
    String Bookname;
    String Tag1;
    String bible;
    String datediff;
    String bookid;
    CalContentView cal;
    TextView contentComplete;
    Date curredate;
    TextView currentdate;
    CalendarView cv;
    Toolbar toolbar;
    Cursor dateContent;
    ArrayList<String> dateval1;
    ArrayList<String> dateval2;
    ArrayList<String> dateval3;
    DBhelper dbhelper;
    DrawerLayout drawer;
    private ListView mDrawerList;
    boolean flag = true;
    float font_size;
    LinearLayout fourthLayout;
    String fourthread;
    TextView fourthtest;
    String fragContent;
    SimpleDateFormat getdetail;
    String getformattedDate;

    HashSet<Date> hash_makeupdays = new HashSet();
    TextView incomplete;
    Date initdate;
    String initialdate;
    int int_fourthread;
    int int_newread;
    int int_oldread;
    int int_psalmread;
    int j;
    ImageView ivFourthRead;
    ImageView ivNewRead;
    ImageView ivOldRead;
    ImageView ivPsalmRead;
    LinearLayout layContent;
    View layout;
    ArrayList<String> list = new ArrayList();
    ArrayList<String> list1 = new ArrayList();
    ArrayList<String> list2 = new ArrayList();
    ArrayList<String> list3 = new ArrayList();
    TextView makeupday;
    LinearLayout newLayout;
    TextView newTestament;
    String newread;
    int total1_day;
    TextView newtest;
    LinearLayout oldLayout;
    String oldread;
    TextView oldtest;
    ProgressDialog pd;
    String planby;
    PopupWindow pop;
    LinearLayout poplay;
    TextView psalm;
    LinearLayout psalmLayout;
    String psalmread;
    SharedPreferences sharedPreferences;
    String state;
    LinearLayout swipe;
    String table_plan;
    ArrayList<String> val;
    String year70;
    BottomBar bottomBar;


    class C04926 implements EventHandler {
        C04926() {
        }

        public void onDayPress(Date date) {
            Cursor dateContent;
            DateFormat df = new SimpleDateFormat("dd MMM");
            DateFormat df1 = new SimpleDateFormat("yyyy");
            getformattedDate = df.format(date);
            year70 = df1.format(date);
            sharedPreferences = getSharedPreferences("MyPrefs", 0);
            Editor date_store = sharedPreferences.edit();
            date_store.putString("date", getformattedDate);
            date_store.commit();
            try {
                curredate = getdetail.parse(getformattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (planby.equals("bydate")) {
                dateContent = dbhelper.getDateContent1(table_plan, getformattedDate);
            } else {
                try {
                    SimpleDateFormat getd = new SimpleDateFormat("MMM dd");
                    initdate = getd.parse(initialdate);
                    curredate = getdetail.parse(getformattedDate);
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
                dateContent = dbhelper.getDateContent2(table_plan, String.valueOf(getDifferenceDays(initdate, curredate)));
                datediff=String.valueOf(getDifferenceDays(initdate, curredate));
                Editor edit = sharedPreferences.edit();
                edit.putString("datediff",datediff);
                edit.commit();
            }
            if (dateContent.getCount() > 0) {
                layContent.setVisibility(View.VISIBLE);
                makeupday.setVisibility(View.GONE);
                incomplete.setVisibility(View.VISIBLE);
                psalm.setText(dateContent.getString(dateContent.getColumnIndex("PSALM")));
                oldtest.setText(dateContent.getString(dateContent.getColumnIndex("old_testament")));
                newtest.setText(dateContent.getString(dateContent.getColumnIndex("new_testament")));
                fourthtest.setText(dateContent.getString(dateContent.getColumnIndex("fourth_testament")));
                oldread = dateContent.getString(dateContent.getColumnIndex("old_read"));
                psalmread = dateContent.getString(dateContent.getColumnIndex("PSALM_read"));
                newread = dateContent.getString(dateContent.getColumnIndex("new_read"));
                fourthread = dateContent.getString(dateContent.getColumnIndex("fourth_read"));
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
                int totalcontentread = ((int_newread + int_psalmread) + int_oldread) + int_fourthread;
                if (totalcontentread == 0) {
                    contentComplete.setText("You've read 0% of the daily reading");
                } else if (psalmread.equals("") && newread.equals("") && fourthread.equals("")) {
                    contentComplete.setText("You've read " + ((totalcontentread * 100) / 1) + "% of the daily reading");
                } else if (newread.equals("") && fourthread.equals("")) {
                    contentComplete.setText("You've read " + ((totalcontentread * 100) / 2) + "% of the daily reading");
                } else if (fourthread.equals("")) {
                    contentComplete.setText("You've read " + ((totalcontentread * 100) / 3) + "% of the daily reading");
                } else {
                    contentComplete.setText("You've read " + ((totalcontentread * 100) / 4) + "% of the daily reading");
                }
                if (oldread == null || !oldread.equals("1")) {
                    ivOldRead.setVisibility(View.INVISIBLE);
                } else {
                    ivOldRead.setVisibility(View.VISIBLE);
                    ivOldRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
                if (newread.equals("")) {
                    newLayout.setVisibility(View.GONE);
                } else if (newread == null || !newread.equals("1")) {
                    ivNewRead.setVisibility(View.VISIBLE);
                    ivNewRead.setVisibility(View.INVISIBLE);
                } else {
                    ivNewRead.setVisibility(View.VISIBLE);
                    ivNewRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                    newLayout.setVisibility(View.VISIBLE);
                }
                if (psalmread.equals("")) {
                    psalmLayout.setVisibility(View.GONE);
                } else if (psalmread == null || !psalmread.equals("1")) {
                    psalmLayout.setVisibility(View.VISIBLE);
                    ivPsalmRead.setVisibility(View.INVISIBLE);
                } else {
                    psalmLayout.setVisibility(View.VISIBLE);
                    ivPsalmRead.setVisibility(View.VISIBLE);
                    ivPsalmRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
                if (fourthread.equals("")) {
                    fourthLayout.setVisibility(View.GONE);
                } else if (fourthread == null || !fourthread.equals("1")) {
                    fourthLayout.setVisibility(View.VISIBLE);
                    ivFourthRead.setVisibility(View.INVISIBLE);
                } else {
                    fourthLayout.setVisibility(View.VISIBLE);
                    ivFourthRead.setVisibility(View.VISIBLE);
                    ivFourthRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
                if ((psalmread.equals("") || newread.equals("") || fourthread.equals("")) && int_oldread == 1) {
                    incomplete.setText("COMPLETE");
                    incomplete.setBackgroundResource(R.color.colorPrimary);
                    return;
                } else if ((newread.equals("") || fourthread.equals("")) && int_oldread == 1 && int_psalmread == 1) {
                    incomplete.setText("COMPLETE");
                    incomplete.setBackgroundResource(R.color.colorPrimary);
                    return;
                } else if (fourthread.equals("") && int_oldread == 1 && int_psalmread == 1 && int_newread == 1) {
                    incomplete.setText("COMPLETE");
                    incomplete.setBackgroundResource(R.color.colorPrimary);
                    return;
                } else if (int_oldread == 1 && int_psalmread == 1 && int_newread == 1 && int_fourthread == 1) {
                    incomplete.setText("COMPLETE");
                    incomplete.setBackgroundResource(R.color.colorPrimary);
                    return;
                } else {
                    incomplete.setText("INCOMPLETE");
                    incomplete.setBackgroundResource(R.color.ash);
                    return;
                }
            }
            Toast.makeText(getApplicationContext(), "Data not available", Toast.LENGTH_LONG).show();
            layContent.setVisibility(View.GONE);
            makeupday.setVisibility(View.GONE);
            incomplete.setVisibility(View.INVISIBLE);
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        int i;
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_monthly_cal);
        sharedPreferences = getSharedPreferences("MyPrefs", 0);
        bible = sharedPreferences.getString("bible", null);
        table_plan = sharedPreferences.getString("tablePlan", null);
        initialdate = sharedPreferences.getString("initialdate", null);
        planby = sharedPreferences.getString("planby", null);
       toolbar = (Toolbar) findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        TextView tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("NYI Nexus");
        state = Environment.getExternalStorageState();
        contentComplete = (TextView) findViewById(R.id.dailyreadingpercent);
        currentdate = (TextView) findViewById(R.id.tv_current_date);
        oldtest = (TextView) findViewById(R.id.tv_old_test);
        bottomBar= (BottomBar)findViewById(R.id.bottomBar);
        cal = new CalContentView();
        ivOldRead = (ImageView) findViewById(R.id.iv_old_read);
        psalm = (TextView) findViewById(R.id.tv_psalm);
        ivPsalmRead = (ImageView) findViewById(R.id.iv_psalm_read);
        newtest = (TextView) findViewById(R.id.tv_new_test);
        swipe = (LinearLayout) findViewById(R.id.swipe);
        ivNewRead = (ImageView) findViewById(R.id.iv_new_read);
        makeupday = (TextView) findViewById(R.id.tv_makeup_day);
        incomplete = (TextView) findViewById(R.id.tv_incomplete);
        layContent = (LinearLayout) findViewById(R.id.layout_daily_content);
        dbhelper = new DBhelper(this, getFilesDir().getAbsolutePath());
        Log.e("Directory1", getFilesDir().getAbsolutePath());
        oldLayout = (LinearLayout) findViewById(R.id.old_layout);
        psalmLayout = (LinearLayout) findViewById(R.id.psalm_layout);
        newLayout = (LinearLayout) findViewById(R.id.new_layout);
        fourthLayout = (LinearLayout) findViewById(R.id.fourth_layout);
        fourthtest = (TextView) findViewById(R.id.tv_fourth_test);
        ivFourthRead = (ImageView) findViewById(R.id.iv_fourth_read);
//        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd");
        getdetail = new SimpleDateFormat("dd MMM");
        String formattedDate = df.format(c.getTime());
        getformattedDate = getdetail.format(c.getTime());
        if (planby.equals("bydate")) {
            dateContent = dbhelper.getDateContent1(table_plan, getformattedDate);
        } else {
            try {
                initdate = new SimpleDateFormat("MMM dd").parse(initialdate);
                curredate = getdetail.parse(getformattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dateContent = dbhelper.getDateContent2(table_plan, String.valueOf(getDifferenceDays(initdate, curredate)));
            Editor edit = sharedPreferences.edit();
            edit.putString("datediff",datediff);
            edit.commit();
            Log.e("Con Date Content", dateContent + "," + dateContent.getCount());
        }
        if (dateContent == null) {
            Toast.makeText(getApplicationContext(), "Data not available", Toast.LENGTH_LONG).show();
            layContent.setVisibility(View.GONE);
            makeupday.setVisibility(View.GONE);
            incomplete.setVisibility(View.VISIBLE);
        } else {
            cv = (CalendarView) findViewById(R.id.calendar_view);
            cv.updateCalendar();
            if (dateContent.getCount() > 0) {
                layContent.setVisibility(View.VISIBLE);
                makeupday.setVisibility(View.GONE);
                incomplete.setVisibility(View.VISIBLE);
                psalm.setText(dateContent.getString(dateContent.getColumnIndex("PSALM")));
                oldtest.setText(dateContent.getString(dateContent.getColumnIndex("old_testament")));
                newtest.setText(dateContent.getString(dateContent.getColumnIndex("new_testament")));
                fourthtest.setText(dateContent.getString(dateContent.getColumnIndex("fourth_testament")));
                oldread = dateContent.getString(dateContent.getColumnIndex("old_read"));
                psalmread = dateContent.getString(dateContent.getColumnIndex("PSALM_read"));
                newread = dateContent.getString(dateContent.getColumnIndex("new_read"));
                fourthread = dateContent.getString(dateContent.getColumnIndex("fourth_read"));
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
                int totalcontentread = ((int_newread + int_psalmread) + int_oldread) + int_fourthread;
                if (totalcontentread == 0) {
                    contentComplete.setText("You've read 0% of the daily reading");
                } else if (psalmread.equals("") && newread.equals("") && fourthread.equals("")) {
                    contentComplete.setText("You've read " + ((totalcontentread * 100) / 1) + "% of the daily reading");
                } else if (newread.equals("") && fourthread.equals("")) {
                    contentComplete.setText("You've read " + ((totalcontentread * 100) / 2) + "% of the daily reading");
                } else if (fourthread.equals("")) {
                    contentComplete.setText("You've read " + ((totalcontentread * 100) / 3) + "% of the daily reading");
                } else {
                    contentComplete.setText("You've read " + ((totalcontentread * 100) / 4) + "% of the daily reading");
                }
                if (oldread == null || !oldread.equals("1")) {
                    ivOldRead.setVisibility(View.INVISIBLE);
                } else {
                    ivOldRead.setVisibility(View.VISIBLE);
                    ivOldRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
                if (newread.equals("")) {
                    newLayout.setVisibility(View.GONE);
                } else if (newread == null || !newread.equals("1")) {
                    ivNewRead.setVisibility(View.VISIBLE);
                    ivNewRead.setVisibility(View.INVISIBLE);
                } else {
                    ivNewRead.setVisibility(View.VISIBLE);
                    ivNewRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                    newLayout.setVisibility(View.VISIBLE);
                }
                if (psalmread.equals("")) {
                    psalmLayout.setVisibility(View.GONE);
                } else if (psalmread == null || !psalmread.equals("1")) {
                    psalmLayout.setVisibility(View.VISIBLE);
                    ivPsalmRead.setVisibility(View.INVISIBLE);
                } else {
                    psalmLayout.setVisibility(View.VISIBLE);
                    ivPsalmRead.setVisibility(View.VISIBLE);
                    ivPsalmRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
                if (fourthread.equals("")) {
                    fourthLayout.setVisibility(View.GONE);
                } else if (fourthread == null || !fourthread.equals("1")) {
                    fourthLayout.setVisibility(View.VISIBLE);
                    ivFourthRead.setVisibility(View.INVISIBLE);
                } else {
                    fourthLayout.setVisibility(View.VISIBLE);
                    ivFourthRead.setVisibility(View.VISIBLE);
                    ivFourthRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
                if ((psalmread.equals("") || newread.equals("") || fourthread.equals("")) && int_oldread == 1) {
                    incomplete.setText("COMPLETE");
                    incomplete.setBackgroundResource(R.color.colorPrimary);
                } else if ((newread.equals("") || fourthread.equals("")) && int_oldread == 1 && int_psalmread == 1) {
                    incomplete.setText("COMPLETE");
                    incomplete.setBackgroundResource(R.color.colorPrimary);
                } else if (fourthread.equals("") && int_oldread == 1 && int_psalmread == 1 && int_newread == 1) {
                    incomplete.setText("COMPLETE");
                    incomplete.setBackgroundResource(R.color.colorPrimary);
                } else if (int_oldread == 1 && int_psalmread == 1 && int_newread == 1 && int_fourthread == 1) {
                    incomplete.setText("COMPLETE");
                    incomplete.setBackgroundResource(R.color.colorPrimary);
                } else {
                    incomplete.setText("INCOMPLETE");
                    incomplete.setBackgroundResource(R.color.ash);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Data not available", Toast.LENGTH_LONG).show();
                layContent.setVisibility(View.GONE);
                makeupday.setVisibility(View.GONE);
                incomplete.setVisibility(View.INVISIBLE);
            }
        }
        bottomBar.selectTabWithId(R.id.tab_plans);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                if(tabId==R.id.tab_read)
                {
                    Intent i = new Intent(MonthlyCal.this, bibleContentSelect.class);

                    startActivity(i);
                    finish();
                }
                else if(tabId==R.id.tab_home)
                {
                    Intent i = new Intent(MonthlyCal.this, CalPage.class);

                    startActivity(i);
                    finish();
                } else if(tabId==R.id.tab_media)
                {
                    Intent i = new Intent(MonthlyCal.this, MediaActivity.class);

                    startActivity(i);
                    finish();
                }
                else if(tabId==R.id.tab_pray)
                {
                    Intent i = new Intent(MonthlyCal.this,myprayergroup.class);

                    startActivity(i);
                    finish();
                }
                else if (tabId == R.id.tab_page) {
                    Intent i = new Intent(MonthlyCal.this, PageActivity.class);

                    startActivity(i);
                    finish();

                }
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
        val = dbhelper.getdateforMakeUp2(table_plan);
        if (val != null) {
            for (i = 0; i < val.size(); i++) {
                try {
                    hash_makeupdays.add(new SimpleDateFormat("dd MMM").parse((String) val.get(i)));
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
            }
            for (i = 0; i < val.size(); i++) {
                try {
                    cv.updateCalendar(new SimpleDateFormat("dd MMM").parse((String) val.get(i)));
                } catch (ParseException e22) {
                    e22.printStackTrace();
                }
            }
        }
        val = dbhelper.getdateforMakeUp2(table_plan);
        if (list.size() == 0 && val != null) {
            for (i = 0; i < val.size(); i++) {
                try {
                    Date makeupdays = new SimpleDateFormat("dd MMM").parse((String) val.get(i));
                    int makeday = makeupdays.getDate();
                    list.add(makeday + " " + makeupdays.getMonth());
                } catch (ParseException e222) {
                    e222.printStackTrace();
                }
            }
            cv.updateCalendar1(list);
        }
        dateval1 = dbhelper.getTotalOne2(table_plan);
        if (list1.size() == 0 && dateval1 != null) {
            for (i = 0; i < dateval1.size(); i++) {
                try {
                    Date date1 = new SimpleDateFormat("dd MMM").parse((String) dateval1.get(i));
                    int total1_day = date1.getDate();
                    list1.add(total1_day + " " + date1.getMonth());
                } catch (ParseException e2222) {
                    e2222.printStackTrace();
                }
            }
            cv.updateCalendar2(list1);
            Log.e("list1 in monthly", list1 + "");
        }
        dateval2 = dbhelper.getTotaltwo2(table_plan);
        if (list2.size() == 0 && dateval2 != null) {
            for (i = 0; i < dateval2.size(); i++) {
                try {
                    Date date2 = new SimpleDateFormat("dd MMM").parse((String) dateval2.get(i));
                    total1_day = date2.getDate();
                    list2.add(total1_day + " " + date2.getMonth());
                } catch (ParseException e22222) {
                    e22222.printStackTrace();
                }
            }
            cv.updateCalendar3(list2);
        }
        dateval3 = dbhelper.getTotalthree2(table_plan);
        if (list3.size() == 0 && dateval3 != null) {
            for (i = 0; i < dateval3.size(); i++) {
                try {
                    Date date3 = new SimpleDateFormat("dd MMM").parse((String) dateval3.get(i));
                    total1_day = date3.getDate();
                    list3.add(total1_day + " " + date3.getMonth());
                } catch (ParseException e222222) {
                    e222222.printStackTrace();
                }
            }
            cv.updateCalendar4(list3);
        }
        cv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat df = SimpleDateFormat.getDateInstance();
            }
        });
        oldtest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldtst = oldtest.getText().toString();
                if (!oldtst.equals("Catch-up day") && !oldtst.equals("REFLECTION | MAKE-UP")) {
                    String psa = psalm.getText().toString();
                    String newtst = newtest.getText().toString();
                    String fourthtst = fourthtest.getText().toString();
                    ArrayList<String> frag1_content = newSeparator(oldtst);
                    ArrayList<String> frag2_content = newSeparator(psa);
                    ArrayList<String> frag3_content = newSeparator(newtst);
                    ArrayList<String> frag4_content = newSeparator(fourthtst);
                    Intent i = new Intent(MonthlyCal.this, CalContentView.class);
                    i.putExtra("viewpager_position", "0");
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
                }
            }
        });
        psalm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> frag1_content = new ArrayList();
                String oldtst = oldtest.getText().toString();
                String psa = psalm.getText().toString();
                String newtst = newtest.getText().toString();
                String fourthtst = fourthtest.getText().toString();
                if (!(oldtst.equals("Catch-up day") || oldtst.equals("REFLECTION | MAKE-UP"))) {
                    frag1_content = newSeparator(oldtst);
                }
                ArrayList<String> frag2_content = newSeparator(psa);
                ArrayList<String> frag3_content = newSeparator(newtst);
                ArrayList<String> frag4_content = newSeparator(fourthtst);
                Intent i = new Intent(MonthlyCal.this, CalContentView.class);
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
            }
        });
        newtest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> frag1_content = new ArrayList<>();
                String oldtst = oldtest.getText().toString();
                String psa = psalm.getText().toString();
                String newtst = newtest.getText().toString();
                String fourthtst = fourthtest.getText().toString();
                if (!(oldtst.equals("Catch-up day") || oldtst.equals("REFLECTION | MAKE-UP"))) {
                    frag1_content = newSeparator(oldtst);
                }
                ArrayList<String> frag2_content = newSeparator(psa);
                ArrayList<String> frag3_content = newSeparator(newtst);
                ArrayList<String> frag4_content = newSeparator(fourthtst);
                Intent i = new Intent(MonthlyCal.this, CalContentView.class);
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
            }
        });
        fourthtest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> frag1_content = new ArrayList();
                String oldtst = oldtest.getText().toString();
                String psa = psalm.getText().toString();
                String newtst = newtest.getText().toString();
                String fourthtst = fourthtest.getText().toString();
                if (!(oldtst.equals("Catch-up day") || oldtst.equals("REFLECTION | MAKE-UP"))) {
                    frag1_content = newSeparator(oldtst);
                }
                ArrayList<String> frag2_content = newSeparator(psa);
                ArrayList<String> frag3_content = newSeparator(newtst);
                ArrayList<String> frag4_content = newSeparator(fourthtst);
                Intent i = new Intent(MonthlyCal.this, CalContentView.class);
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
            }
        });
        cv.setEventHandler(new C04926());

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
                        Intent intent = new Intent(MonthlyCal.this, Notes.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;

                    case 8:
                        Intent intent8 = new Intent(MonthlyCal.this, pop_activity_about.class);
                        intent8.setFlags(intent8.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent8);
                        break;
                    case 10:
                        Intent intent10 = new Intent(MonthlyCal.this, EventActivity.class);
                        intent10.setFlags(intent10.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(MonthlyCal.this, UserProfile.class);
                        intent11.setFlags(intent11.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent11);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MonthlyCal.this, popFlashcard.class);
                        intent2.setFlags(intent2.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case 1:
                        search();
                        break;

                    case 4:
                        Intent intent4 = new Intent(MonthlyCal.this, popReadHistory.class);
                        intent4.setFlags(intent4.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                    case 0:
                        Intent intent0 = new Intent(MonthlyCal.this, Settings.class);
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
            pop_search_word = new Dialog(MonthlyCal.this);
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

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), CalPage.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.monthly_cal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.search) {
//            Intent intent = new Intent(MonthlyCal.this, bibleContentSelect.class);
//            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
//            startActivity(intent);
//            return true;
//        }
//        if (id == R.id.date) {
//            Intent intent = new Intent(MonthlyCal.this, CalPage.class);
//            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
//            startActivity(intent);
        if (id == R.id.incomplete) {
            Intent intent = new Intent(MonthlyCal.this, IncompleteReadings.class);
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

    public void content(String book, String chap, String verse) {
        String con = "";
        Cursor value = dbhelper.getContent(bible, book, chap, verse);
        if (value != null) {
            if (value.moveToFirst()) {
                do {
                    con = con + value.getString(value.getColumnIndex("verse"));
                } while (value.moveToNext());
            }
            value.close();
            poplay.setVisibility(View.GONE);
            pop.dismiss();
            Intent i = new Intent(getApplicationContext(), FirstPage.class);
            Editor edit_gen = sharedPreferences.edit();
            edit_gen.putString("intentbookid", bookid);
            edit_gen.putString("intentchapid", chap);
            edit_gen.putString("intentverseid", verse);
            edit_gen.commit();
            startActivity(i);
        }
    }

    public void onResume() {
        int i;
        super.onResume();
        bottomBar.selectTabWithId(R.id.tab_plans);
        invalidateOptionsMenu();
        sharedPreferences = getSharedPreferences("MyPrefs", 0);
        bible = sharedPreferences.getString("bible", null);
        table_plan = sharedPreferences.getString("tablePlan", null);
        initialdate = sharedPreferences.getString("initialdate", null);
        planby = sharedPreferences.getString("planby", null);
        SimpleDateFormat getdetail = new SimpleDateFormat("dd MMM");
        val = dbhelper.getdateforMakeUp2(table_plan);
        list1.clear();
        list2.clear();
        list3.clear();
        if (val != null) {
            for (i = 0; i < val.size(); i++) {
                try {
                    hash_makeupdays.add(new SimpleDateFormat("dd MMM").parse((String) val.get(i)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            for (i = 0; i < val.size(); i++) {
                try {
                    cv.updateCalendar(new SimpleDateFormat("dd MMM").parse((String) val.get(i)));
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (planby.equals("bydate")) {
            dateContent = dbhelper.getDateContent1(table_plan, getformattedDate);
            Log.e("dateContent", dateContent + "");
        } else {
            try {
                initdate = new SimpleDateFormat("MMM dd").parse(initialdate);
                curredate = getdetail.parse(getformattedDate);
            } catch (ParseException e22) {
                e22.printStackTrace();
            }
            dateContent = dbhelper.getDateContent2(table_plan, String.valueOf(getDifferenceDays(initdate, curredate)));
            Editor edit = sharedPreferences.edit();
            edit.putString("datediff",datediff);
            edit.commit();
            Log.e("Con Date Content", dateContent + "," + dateContent.getCount());
        }
        if (dateContent == null) {
            Toast.makeText(getApplicationContext(), "Data not available", Toast.LENGTH_LONG).show();
            layContent.setVisibility(View.GONE);
            makeupday.setVisibility(View.GONE);
            incomplete.setVisibility(View.INVISIBLE);
        } else {
            cv.updateCalendar();
            if (dateContent.getCount() > 0) {
                layContent.setVisibility(View.VISIBLE);
                makeupday.setVisibility(View.GONE);
                incomplete.setVisibility(View.VISIBLE);
                psalm.setText(dateContent.getString(dateContent.getColumnIndex("PSALM")));
                oldtest.setText(dateContent.getString(dateContent.getColumnIndex("old_testament")));
                newtest.setText(dateContent.getString(dateContent.getColumnIndex("new_testament")));
                fourthtest.setText(dateContent.getString(dateContent.getColumnIndex("fourth_testament")));
                oldread = dateContent.getString(dateContent.getColumnIndex("old_read"));
                psalmread = dateContent.getString(dateContent.getColumnIndex("PSALM_read"));
                newread = dateContent.getString(dateContent.getColumnIndex("new_read"));
                fourthread = dateContent.getString(dateContent.getColumnIndex("fourth_read"));
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
                int totalcontentread = ((int_newread + int_psalmread) + int_oldread) + int_fourthread;
                if (totalcontentread == 0) {
                    contentComplete.setText("You've read 0% of the daily reading");
                } else if (psalmread.equals("") && newread.equals("") && fourthread.equals("")) {
                    contentComplete.setText("You've read " + ((totalcontentread * 100) / 1) + "% of the daily reading");
                } else if (newread.equals("") && fourthread.equals("")) {
                    contentComplete.setText("You've read " + ((totalcontentread * 100) / 2) + "% of the daily reading");
                } else if (fourthread.equals("")) {
                    contentComplete.setText("You've read " + ((totalcontentread * 100) / 3) + "% of the daily reading");
                } else {
                    contentComplete.setText("You've read " + ((totalcontentread * 100) / 4) + "% of the daily reading");
                }
                if (oldread == null || !oldread.equals("1")) {
                    ivOldRead.setVisibility(View.INVISIBLE);
                } else {
                    ivOldRead.setVisibility(View.VISIBLE);
                    ivOldRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
                if (newread.equals("")) {
                    newLayout.setVisibility(View.GONE);
                } else if (newread == null || !newread.equals("1")) {
                    ivNewRead.setVisibility(View.VISIBLE);
                    ivNewRead.setVisibility(View.INVISIBLE);
                } else {
                    ivNewRead.setVisibility(View.VISIBLE);
                    ivNewRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                    newLayout.setVisibility(View.VISIBLE);
                }
                if (psalmread.equals("")) {
                    psalmLayout.setVisibility(View.GONE);
                } else if (psalmread == null || !psalmread.equals("1")) {
                    psalmLayout.setVisibility(View.VISIBLE);
                    ivPsalmRead.setVisibility(View.INVISIBLE);
                } else {
                    psalmLayout.setVisibility(View.VISIBLE);
                    ivPsalmRead.setVisibility(View.VISIBLE);
                    ivPsalmRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
                if (fourthread.equals("")) {
                    fourthLayout.setVisibility(View.GONE);
                } else if (fourthread == null || !fourthread.equals("1")) {
                    fourthLayout.setVisibility(View.VISIBLE);
                    ivFourthRead.setVisibility(View.INVISIBLE);
                } else {
                    fourthLayout.setVisibility(View.VISIBLE);
                    ivFourthRead.setVisibility(View.VISIBLE);
                    ivFourthRead.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                }
                if ((psalmread.equals("") || newread.equals("") || fourthread.equals("")) && int_oldread == 1) {
                    incomplete.setText("COMPLETE");
                    incomplete.setBackgroundResource(R.color.colorPrimary);
                } else if ((newread.equals("") || fourthread.equals("")) && int_oldread == 1 && int_psalmread == 1) {
                    incomplete.setText("COMPLETE");
                    incomplete.setBackgroundResource(R.color.colorPrimary);
                } else if (fourthread.equals("") && int_oldread == 1 && int_psalmread == 1 && int_newread == 1) {
                    incomplete.setText("COMPLETE");
                    incomplete.setBackgroundResource(R.color.colorPrimary);
                } else if (int_oldread == 1 && int_psalmread == 1 && int_newread == 1 && int_fourthread == 1) {
                    incomplete.setText("COMPLETE");
                    incomplete.setBackgroundResource(R.color.colorPrimary);
                } else {
                    incomplete.setText("INCOMPLETE");
                    incomplete.setBackgroundResource(R.color.ash);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Data not available", Toast.LENGTH_LONG).show();
                layContent.setVisibility(View.GONE);
                makeupday.setVisibility(View.GONE);
                incomplete.setVisibility(View.INVISIBLE);
            }
        }
        val = dbhelper.getdateforMakeUp2(table_plan);
        if (!(list.size() == 0 || val == null)) {
            for (i = 0; i < val.size(); i++) {
                try {
                    Date makeupdays = new SimpleDateFormat("dd MMM").parse((String) val.get(i));
                    int makeday = makeupdays.getDate();
                    list.add(makeday + " " + makeupdays.getMonth());
                } catch (ParseException e222) {
                    e222.printStackTrace();
                }
            }
            cv.updateCalendar1(list);
            Log.e("list in resume", list + "");
        }
        dateval1 = dbhelper.getTotalOne2(table_plan);
        if ((list1.size() == 0 && dateval1 != null) || !(list1.size() == 0 || dateval1 == null)) {
            for (i = 0; i < dateval1.size(); i++) {
                String str_date = (String) dateval1.get(i);
                Log.e("dat", str_date);
                try {
                    Date date1 = new SimpleDateFormat("dd MMM").parse(str_date);
                    int total1_day = date1.getDate();
                    list1.add(total1_day + " " + date1.getMonth());
                } catch (ParseException e2222) {
                    e2222.printStackTrace();
                }
            }
            cv.updateCalendar2(list1);
            Log.e("list1 in resume", list1 + "");
        }
        dateval2 = dbhelper.getTotaltwo2(table_plan);
        if ((list2.size() == 0 && dateval2 != null) || !(list2.size() == 0 || dateval2 == null)) {
            for (i = 0; i < dateval2.size(); i++) {
                try {
                    Date date2 = new SimpleDateFormat("dd MMM").parse((String) dateval2.get(i));
                    total1_day = date2.getDate();
                    list2.add(total1_day + " " + date2.getMonth());
                } catch (ParseException e22222) {
                    e22222.printStackTrace();
                }
            }
            cv.updateCalendar3(list2);
        }
        dateval3 = dbhelper.getTotalthree2(table_plan);
        if ((list3.size() == 0 && dateval3 != null) || (list3.size() != 0 && dateval3 != null)) {
            for (i = 0; i < dateval3.size(); i++) {
                try {
                    Date date3 = new SimpleDateFormat("dd MMM").parse((String) dateval3.get(i));
                    total1_day = date3.getDate();
                    list3.add(total1_day + " " + date3.getMonth());
                } catch (ParseException e222222) {
                    e222222.printStackTrace();
                }
            }
            cv.updateCalendar4(list3);
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_about) {
            Intent intent = new Intent(MonthlyCal.this, pop_activity_about.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        else if (id == R.id.nav_mynotes) {
            Intent intent = new Intent(this, Notes.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }  else if (id == R.id.nav_flashcard) {

            Intent intent = new Intent(this, popFlashcard.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            this.startActivity(intent);

        } else if (id == R.id.nav_search) {
            Intent intent = new Intent(MonthlyCal.this, popSearch.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);

        } else if (id == R.id.nav_readhistory) {

            Intent intent = new Intent(this, popReadHistory.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            this.startActivity(intent);
        }
        else if (id == R.id.nav_settings) {
            Intent intent = new Intent(MonthlyCal.this, Settings.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    public boolean checkMonth(String currdate, String month) {
        String strdate = " ";
        try {
            DateFormat df = new SimpleDateFormat("MMM");
            strdate = df.format(df.parse(currdate));
        } catch (Exception e) {
        }
        if (month.equals(strdate)) {
            return true;
        }
        return false;
    }

    public int getDifferenceDays(Date d1, Date d2) {
        Log.e("Dates", d1 + "," + d2);
        return (int) (((d2.getTime() - d1.getTime()) / 86400000) + 1);
    }
}
