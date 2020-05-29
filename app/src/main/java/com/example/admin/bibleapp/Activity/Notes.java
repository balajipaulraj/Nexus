package com.example.admin.bibleapp.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;

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
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.Adapter.DrawerItemCustomAdapter;
import com.example.admin.bibleapp.Adapter.NotesAdapter;
import com.example.admin.bibleapp.Adapter.PrayerAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.NotesModel;
import com.example.admin.bibleapp.Model.user;
import com.example.admin.bibleapp.Views.OnBlurCompleteListener;
import com.example.admin.bibleapp.Parser.Employee;
import com.example.admin.bibleapp.Parser.XMLPullParserHandler;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.popupWindow.popFlashcard;
import com.example.admin.bibleapp.popupWindow.popReadHistory;
import com.example.admin.bibleapp.popupWindow.pop_activity_about;
import com.example.admin.bibleapp.util.Helpers;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import static android.view.View.GONE;

public class Notes extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    NotesAdapter Adapter;
    Typeface RobotoSlabLight;
    Typeface RobotoSlabThin;
    String bible;
    String content;
    List<NotesModel> data;
    DBhelper dbhelper;
    LinearLayout linear;
    DrawerLayout drawer;
    private ListView mDrawerList;
    EditText edtContainNotes;
    EditText edtNewnotes;
    List<Employee> employees = null;
    boolean flag = true;
    PrayerAdapter mAdapter;
    float fontSize;
    float font_size;
    TextView hamNASB;
    TextView hamabout;
    ImageView hamclose;
    TextView hamfont;
    TextView hamload;
    NestedScrollView nested_scroll;
    TextView hamnotes;
    TextView hamnotification;
    TextView hamreset;
    TextView hamsearch;
    LinearLayout lay_title;
    RecyclerView.LayoutManager mLayoutManager;
    Typeface monBold;
    Typeface monLight;
    Typeface monRegular;
    String mynotes;
    String mytime;
    String mytitle;
    String notestime;
    ProgressDialog pd;
    Dialog pop_abt;
    Dialog pop_db;
    Dialog pop_font;
    Dialog pop_readbible;
    Dialog pop_search_word;
    Dialog pop_title;
    LinearLayout poplay;
    LinearLayout edtContainNoteslayout;
    RecyclerView rv_notes;
    SharedPreferences sharedPreferences;
    String state;
    String table_plan;
    NavigationView nav_view;
    StringBuffer textBuilder;
    String title = "";
    TextView tvEmptyNotes;
    ImageView tv_house;
    TextView tvaddnotes;
    TextView tvnotetime;
    TextView tvsettitle;
    DBhelper dbHelper;

    class C02371 implements OnClickListener {
        C02371() {
        }

        public void onClick(View v) {
            Notes.this.tvEmptyNotes.setVisibility(View.GONE);
            Notes.this.edtContainNotes.setVisibility(View.VISIBLE);
            if (Notes.this.edtContainNotes.getText().toString().equals("")) {
                Notes.this.showTitle();
                Notes.this.tvsettitle.setText(Notes.this.title);
                Notes.this.tvnotetime.setText(Notes.this.notestime);
                Notes.this.tvsettitle.setTextSize(Notes.this.fontSize);
                Notes.this.tvsettitle.setVisibility(View.VISIBLE);
                Notes.this.lay_title.setVisibility(View.VISIBLE);
                Notes.this.edtContainNotes.setHint("\tPaste the Text Here");
                Notes.this.edtContainNotes.setTextSize(Notes.this.fontSize);
                Notes.this.tvaddnotes.setVisibility(View.GONE);
            }
        }
    }

    class C02382 implements OnClickListener {
        C02382() {
        }

        public void onClick(View v) {
            Notes.this.lay_title.setVisibility(View.GONE);
            Notes.this.edtContainNotes.setVisibility(View.GONE);
            Notes.this.tvaddnotes.setVisibility(View.VISIBLE);
            Notes.this.pop_title.dismiss();
        }
    }

    class C02415 implements OnClickListener {
        C02415() {
        }

        public void onClick(View v) {
            Notes.this.pop_search_word.dismiss();
        }
    }

    class C02448 implements OnClickListener {
        C02448() {
        }

        public void onClick(View v) {
            Notes.this.pop_search_word.dismiss();
        }
    }

    class C04969 implements OnBlurCompleteListener {
        C04969() {
        }

        public void onBlurComplete() {
            Intent intent = new Intent(Notes.this, pop_activity_about.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            Notes.this.startActivity(intent);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        this.dbhelper = new DBhelper(this);
        this.state = Environment.getExternalStorageState();
        //this.pd = new ProgressDialog(this, R.style.MyTheme);
//        this.pd.setCancelable(false);
        //      this.pd.setProgressStyle(16973854);
        this.notestime = Helpers.GetCurrentDateTime();
        this.dbhelper = new DBhelper(this, getFilesDir().getAbsolutePath());
        Toolbar toolbar = (Toolbar) findViewById(R.id.settoolbar);
        TextView tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("Notes");
        this.tvEmptyNotes = (TextView) findViewById(R.id.tv_notes_empty);
        this.edtContainNotes = (EditText) findViewById(R.id.tv_notes_full);
        // this.edtContainNoteslayout = (LinearLayout)findViewById(R.id.tv_notes_full_layout) ;
        this.tvaddnotes = (TextView) findViewById(R.id.add_new_notes);
        this.tvsettitle = (TextView) findViewById(R.id.tv_set_title);
        this.tvnotetime = (TextView) findViewById(R.id.tv_note_time);
        this.lay_title = (LinearLayout) findViewById(R.id.lay_title);
        setSupportActionBar(toolbar);
        dbHelper = new DBhelper(getApplicationContext());
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        List<String> menuItems = dbHelper.getMenu();
        DrawerItemCustomAdapter dradapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, menuItems);
        mDrawerList.setAdapter(dradapter);
//        final ActionBarDrawerToggle toggle;
//        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
        //  nested_scroll = (NestedScrollView)findViewById(R.id.nested_scroll);
        this.monRegular = Typeface.createFromAsset(getAssets(), "fonts/Mon.ttf");
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        this.table_plan = this.sharedPreferences.getString("tablePlan", null);
        this.mynotes = this.sharedPreferences.getString("mynotes", null);
        this.mytitle = this.sharedPreferences.getString("mytitle", null);
        this.mytime = this.sharedPreferences.getString("mytime", null);
        this.bible = this.sharedPreferences.getString("bible", null);
//       nav_view = (NavigationView) findViewById(R.id.nav_view);
//       nav_view.setNavigationItemSelectedListener(this);
        this.rv_notes = (RecyclerView) findViewById(R.id.rv_notes);
        this.mLayoutManager = new LinearLayoutManager(this);
        this.rv_notes.setLayoutManager(this.mLayoutManager);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_Layout);
        String flot_size = getSharedPreferences("MyPrefs", 0).getString("fontsize", null);
        if (flot_size == null) {
            this.fontSize = 17.0f;
        } else {
            this.fontSize = Float.parseFloat(flot_size);
        }
        new ActionBarDrawerToggle(this, this.drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close).syncState();
        this.Adapter = new NotesAdapter(this.dbhelper.getNotes(), R.layout.row_notes, this);
        this.rv_notes.setAdapter(this.Adapter);
        this.Adapter.notifyDataSetChanged();
        this.rv_notes.setVisibility(View.VISIBLE);
        if (!new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()).equals(this.mytime)) {
            this.lay_title.setVisibility(View.GONE);
            // this.edtContainNoteslayout.setVisibility(View.GONE);
            this.tvaddnotes.setVisibility(View.VISIBLE);
            this.edtContainNotes.setVisibility(View.GONE);
            this.rv_notes.setVisibility(View.VISIBLE);
            if (this.mynotes == null || this.mynotes == "") {
                this.tvEmptyNotes.setVisibility(View.VISIBLE);
            } else {
                this.tvEmptyNotes.setVisibility(View.GONE);
            }
        } else if (this.mytitle == null || this.mytitle == "") {
            this.tvEmptyNotes.setVisibility(View.VISIBLE);
            this.edtContainNotes.setVisibility(View.GONE);
            // this.edtContainNoteslayout.setVisibility(View.GONE);
            this.tvaddnotes.setVisibility(View.VISIBLE);
            this.tvsettitle.setVisibility(View.GONE);
            this.lay_title.setVisibility(View.GONE);
            this.rv_notes.setVisibility(View.GONE);
        } else {
            this.tvEmptyNotes.setVisibility(View.GONE);
            this.edtContainNotes.setVisibility(View.VISIBLE);
            this.tvsettitle.setVisibility(View.VISIBLE);
            // this.edtContainNoteslayout.setVisibility(View.VISIBLE);
            this.lay_title.setVisibility(View.VISIBLE);
            this.tvsettitle.setText(this.mytitle);
            this.tvsettitle.setTextSize(this.fontSize);
            this.tvnotetime.setText(this.mytime);
            this.tvaddnotes.setVisibility(View.GONE);
            this.edtContainNotes.setText(this.mynotes);
            this.edtContainNotes.setTextSize(this.fontSize);
            this.rv_notes.setVisibility(View.VISIBLE);
        }
        this.tvaddnotes.setOnClickListener(new C02371());
        this.Adapter = new NotesAdapter(this.dbhelper.getNotes(), R.layout.row_notes, this);
        this.rv_notes.setAdapter(this.Adapter);
        this.Adapter.notifyDataSetChanged();
        //  ViewCompat.setNestedScrollingEnabled(rv_notes,true);

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
                        Intent intent = new Intent(Notes.this, Notes.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;

                    case 8:
                        Intent intent8 = new Intent(Notes.this, pop_activity_about.class);
                        intent8.setFlags(intent8.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent8);
                        break;
                    case 10:
                        Intent intent10 = new Intent(Notes.this, EventActivity.class);
                        intent10.setFlags(intent10.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(Notes.this, UserProfile.class);
                        intent11.setFlags(intent11.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent11);
                        break;
                    case 2:
                        Intent intent2 = new Intent(Notes.this, popFlashcard.class);
                        intent2.setFlags(intent2.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case 1:
                        search();
                        break;

                    case 4:
                        Intent intent4 = new Intent(Notes.this, popReadHistory.class);
                        intent4.setFlags(intent4.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                    case 0:
                        Intent intent0 = new Intent(Notes.this, Settings.class);
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
        private void search () {
            Dialog pop_search_word = null;
            if (pop_search_word == null) {
                pop_search_word = new Dialog(Notes.this);
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
                btnSumbit.setOnClickListener(new View.OnClickListener() {
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
                btnCancel.setOnClickListener(new View.OnClickListener() {
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
        private void loaddata () {
            FileNotFoundException e;
            IOException e2;
            if (this.state.equals("mounted")) {
                try {
                    File textFile = new File(Environment.getExternalStorageDirectory() + "/catc plan/plandata.xml");
                    if (textFile.exists()) {
                        this.dbhelper.resetdata2("");
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
                                    InputStream inputStream = new ByteArrayInputStream(this.textBuilder.toString().getBytes());
                                    this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
                                    Editor edit = this.sharedPreferences.edit();
                                    edit.putString("activit", "notes");
                                    edit.commit();
                                    this.employees = new XMLPullParserHandler(this).parse(inputStream);
                                    this.pd.dismiss();
                                    Toast.makeText(getApplicationContext(), "Data Loaded", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(this, "Data not exists", Toast.LENGTH_LONG).show();
                    return;
                } catch (IOException e6) {
                    e2 = e6;
                    e2.printStackTrace();
                    return;
                }
            }
            Toast.makeText(getApplicationContext(), "There is no any sd card", Toast.LENGTH_LONG).show();
            this.pd.dismiss();
        }

        private void showTitle () {
            if (this.pop_title == null) {
                this.pop_title = new Dialog(this);
                this.pop_title.requestWindowFeature(1);
                this.pop_title.setCanceledOnTouchOutside(false);
                this.pop_title.setCancelable(true);
                this.pop_title.setContentView(R.layout.popup_add_title);
                final EditText edtAddTitle = (EditText) this.pop_title.findViewById(R.id.edt_title);
                TextView btnSubmit = (TextView) this.pop_title.findViewById(R.id.tv_submit);
                ((ImageView) this.pop_title.findViewById(R.id.iv_close)).setOnClickListener(new C02382());
                btnSubmit.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        String tit = edtAddTitle.getText().toString();
                        if (tit.equals("")) {
                            edtAddTitle.setError("Please mention the title");
                            return;
                        }
                        Notes.this.title = tit;
                        Notes.this.tvnotetime.setText(Notes.this.notestime);
                        Notes.this.tvsettitle.setText(Notes.this.title);
                        Notes.this.tvsettitle.setTextSize(Notes.this.fontSize);
                        Notes.this.edtContainNotes.setTextSize(Notes.this.fontSize);
                        Notes.this.edtContainNotes.setHint("\tPaste the Text Here");
                        Notes.this.tvaddnotes.setVisibility(View.GONE);
                        Notes.this.pop_title.dismiss();
                    }
                });
                edtAddTitle.setOnEditorActionListener(new OnEditorActionListener() {
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (event.getKeyCode() == 66) {
                            String tit = edtAddTitle.getText().toString();
                            if (tit.equals("")) {
                                edtAddTitle.setError("Please mention the title");
                            } else {
                                Notes.this.title = tit;
                                Notes.this.tvnotetime.setText(Notes.this.notestime);
                                Notes.this.tvsettitle.setText(Notes.this.title);
                                Notes.this.tvsettitle.setTextSize(Notes.this.fontSize);
                                Notes.this.edtContainNotes.setTextSize(Notes.this.fontSize);
                                Notes.this.edtContainNotes.setHint("\tPaste the Text Here");
                                Notes.this.tvaddnotes.setVisibility(View.GONE);
                                Notes.this.pop_title.dismiss();
                            }
                        }
                        return true;
                    }
                });
                this.pop_title.show();
            } else if (this.pop_title.isShowing()) {
                this.pop_title.dismiss();
                this.pop_title = null;
                showTitle();
            } else {
                this.pop_title = null;
                showTitle();
            }
        }

        public void onBackPressed () {
            this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
            Editor edit_gen = this.sharedPreferences.edit();
            String data = this.edtContainNotes.getText().toString();
            String sharedtitle = this.tvsettitle.getText().toString();
            String sharedtime = this.tvnotetime.getText().toString();
            edit_gen.putString("mytitle", sharedtitle);
            edit_gen.putString("mynotes", data);
            edit_gen.putString("mytime", sharedtime);
            edit_gen.commit();
            this.flag = false;
            if (drawer.isDrawerOpen((int) GravityCompat.START)) {
                drawer.closeDrawer((int) GravityCompat.START);
            } else if (this.poplay == null || this.poplay.getVisibility() != View.VISIBLE) {
                finish();
            } else {
                this.poplay.setVisibility(GONE);
            }
        }

        public void updatenotes (String date, String title, String content){
            String CurrentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
            this.dbhelper.updatenote(date, title, content);
        }

        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.menu_save, menu);
            return true;
        }

        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();
            if (id == R.id.date) {
                startActivity(new Intent(this, CalPage.class));
            } else if (id == R.id.menusave) {
                this.rv_notes.setVisibility(View.VISIBLE);
                String CurrentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                String data = this.edtContainNotes.getText().toString();
                String sharedtitle = this.tvsettitle.getText().toString();
                this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
                Editor edit_gen = this.sharedPreferences.edit();
                edit_gen.putString("mytitle", sharedtitle);
                edit_gen.putString("mynotes", data);
                edit_gen.putString("mytime", CurrentDate);
                edit_gen.commit();
                if (sharedtitle.equals("Title") || data.equals("")) {
                    Toast.makeText(getApplicationContext(), "Add title and Content to save", Toast.LENGTH_LONG).show();
                } else {
                    if (this.dbhelper.getdate(CurrentDate)) {
                        this.dbhelper.updatenote(CurrentDate, sharedtitle, data);
                    } else {
                        this.dbhelper.addNotes(CurrentDate, sharedtitle, data);
                    }
                    this.Adapter.notifyDataSetChanged();
                    startActivity(new Intent(getApplicationContext(), Notes.class));
                    finish();
                    Toast.makeText(getApplicationContext(), "Successfully saved", Toast.LENGTH_LONG).show();
                }
            }
            return super.onOptionsItemSelected(item);
        }

        private void showSearch () {
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
                ((ImageView) this.pop_search_word.findViewById(R.id.iv_close)).setOnClickListener(new C02415());
                btnSumbit.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        String searchedWord = edtSearchWord.getText().toString();
                        if (searchedWord == " ") {
                            edtSearchWord.setError("Enter your Search word");
                            return;
                        }
                        Intent i = new Intent(Notes.this.getApplicationContext(), SearchList.class);
                        i.putExtra("searchword", searchedWord);
                        Notes.this.startActivity(i);
                    }
                });
                edtSearchWord.setOnEditorActionListener(new OnEditorActionListener() {
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (event.getKeyCode() == 66) {
                            String searchedWord = edtSearchWord.getText().toString();
                            if (searchedWord == " ") {
                                edtSearchWord.setError("Enter your Search word");
                            } else {
                                Intent i = new Intent(Notes.this.getApplicationContext(), SearchList.class);
                                i.putExtra("searchword", searchedWord);
                                Notes.this.startActivity(i);
                            }
                        }
                        return true;
                    }
                });
                btnCancel.setOnClickListener(new C02448());
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
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            int id = item.getItemId();
            if (id == R.id.nav_about) {
                Intent intent = new Intent(Notes.this, pop_activity_about.class);
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
                search_pop();

            } else if (id == R.id.nav_readhistory) {

                Intent intent = new Intent(this, popReadHistory.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                this.startActivity(intent);
            } else if (id == R.id.nav_settings) {
                Intent intent = new Intent(Notes.this, Settings.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
            this.drawer.closeDrawer((int) GravityCompat.START);
            return true;
        }

        public void onResume () {
            super.onResume();
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", 0);
            String flot_size = sharedPreferences.getString("fontsize", null);
            this.bible = sharedPreferences.getString("bible", null);
            if (flot_size == null) {
                this.fontSize = 17.0f;
            } else {
                this.fontSize = Float.parseFloat(flot_size);
            }
            String notesdata = edtContainNotes.getText().toString();
            int position = notesdata.length();
            edtContainNotes.setSelection(position);
        }

        public void load (String plan, String id, String i1, String i2, String i3, String i4){
            this.dbhelper.insertdata(plan, id, i1, i2, i3, i4);
        }

        private void search_pop () {
            if (this.pop_search_word == null) {
                this.pop_search_word = new Dialog(Notes.this);
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
                search_pop();
            } else {
                this.pop_search_word = null;
                search_pop();
            }
        }
    }
