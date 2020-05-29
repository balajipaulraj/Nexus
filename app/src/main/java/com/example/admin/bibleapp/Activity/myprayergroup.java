package com.example.admin.bibleapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.admin.bibleapp.Adapter.DrawerItemCustomAdapter;
import com.example.admin.bibleapp.Adapter.PrayerGridAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.user;
import com.example.admin.bibleapp.Views.MyGridView;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.popupWindow.ListActivity;
import com.example.admin.bibleapp.popupWindow.popFlashcard;
import com.example.admin.bibleapp.popupWindow.popReadHistory;
import com.example.admin.bibleapp.popupWindow.pop_activity_about;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class myprayergroup extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    MyGridView prayergroup;
    ArrayList<String> PrayCategory = new ArrayList();
    ArrayList<String> PrayCategory2 = new ArrayList();
    ArrayList<Integer> PrayImage = new ArrayList();
    String[] category;
    BottomBar bottomBar;
    private ListView mDrawerList;
    String l_prayer;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    int[] image;
    int i = 0;
    PrayerGridAdapter adapter;
    DBhelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_group);
        sharedPreferences = getSharedPreferences("MyPrefs", 0);
        int userId = sharedPreferences.getInt("userid", 0);
        toolbar = (Toolbar) findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);
        TextView tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("NYI Nexus");
        prayergroup = (MyGridView) findViewById(R.id.grid_prayer);
        dbHelper = new DBhelper(getApplicationContext());
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        List<String> menuItems = dbHelper.getMenu();
        DrawerItemCustomAdapter dradapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, menuItems);
        mDrawerList.setAdapter(dradapter);
        final ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.selectTabWithId(R.id.tab_pray);
        l_prayer = "“‘Our Father in heaven,\n" +
                "hallowed be your name,\n" +
                "your kingdom come,\n" +
                "your will be done,\n" +
                "on earth as it is in heaven.\n" +
                "Give us today our daily bread.\n" +
                "And forgive us our debts,\n" +
                "as we also have forgiven our debtors.\n" +
                "And lead us not into temptation,\n" +
                "but deliver us from the evil one.’";
        int check = dbHelper.getPrayerId("The Lords Prayer");
        if (check == 0) {
            dbHelper.insertPrayer("The Lords Prayer", "The Lords Prayer");
            int id = dbHelper.getPrayerId("The Lords Prayer");
            dbHelper.insertDescr(id, l_prayer);
        }

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {

                if (tabId == R.id.tab_read) {
                    Intent i = new Intent(myprayergroup.this, bibleContentSelect.class);

                    startActivity(i);
                    finish();
                } else if (tabId == R.id.tab_plans) {
                    Intent i = new Intent(myprayergroup.this, MonthlyCal.class);

                    startActivity(i);
                    finish();
                } else if (tabId == R.id.tab_home) {
                    Intent i = new Intent(myprayergroup.this, CalPage.class);

                    startActivity(i);
                    finish();
                } else if (tabId == R.id.tab_page) {
                    Intent i = new Intent(myprayergroup.this, PageActivity.class);

                    startActivity(i);
                    finish();

                } else if (tabId == R.id.tab_media) {
                    Intent i = new Intent(myprayergroup.this, MediaActivity.class);

                    startActivity(i);
                    finish();
                }

            }

        });
        PrayImage.add(R.drawable.selct);
        PrayImage.add(R.drawable.pray64);
        PrayImage.add(R.drawable.cross);
        PrayImage.add(R.drawable.family512);
        PrayImage.add(R.drawable.circlefriends);
        PrayImage.add(R.drawable.chruch);
        PrayCategory.add("All");
        PrayCategory.add("I'm Thankful For");
        PrayCategory.add("The Lord's Prayer");
        PrayCategory.add("My Family");
        PrayCategory.add("My Friends");
        PrayCategory.add("My Church");
        PrayCategory2 = dbHelper.getCategory();
        if (!PrayCategory2.get(0).equals("")) {
            for (i = 0; i < PrayCategory2.size(); i++) {
                PrayCategory.add(PrayCategory2.get(i));
            }
        }
        PrayCategory.add("Add new");
        category = new String[PrayCategory.size()];
        image = new int[100];
        int size = PrayCategory.size();

        for (i = 0; i < size; i++) {
            if (i == size - 1) {
                category[i] = PrayCategory.get(i);
                image[i] = R.drawable.baseline_add_circle_black_48;
            } else {
                category[i] = PrayCategory.get(i);
                if (i > 5) {
                    image[i] = R.drawable.pray64;
                } else {
                    image[i] = PrayImage.get(i);

                }
            }
        }

        adapter = new PrayerGridAdapter(myprayergroup.this, category, image);
        prayergroup.setAdapter(adapter);
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
                        Intent intent = new Intent(myprayergroup.this, Notes.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;

                    case 8:
                        Intent intent8 = new Intent(myprayergroup.this, pop_activity_about.class);
                        intent8.setFlags(intent8.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent8);
                        break;
                    case 10:
                        Intent intent10 = new Intent(myprayergroup.this, EventActivity.class);
                        intent10.setFlags(intent10.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(myprayergroup.this, UserProfile.class);
                        intent11.setFlags(intent11.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent11);
                        break;
                    case 2:
                        Intent intent2 = new Intent(myprayergroup.this, popFlashcard.class);
                        intent2.setFlags(intent2.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case 1:
                        search();
                        break;

                    case 4:
                        Intent intent4 = new Intent(myprayergroup.this, popReadHistory.class);
                        intent4.setFlags(intent4.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                    case 0:
                        Intent intent0 = new Intent(myprayergroup.this, Settings.class);
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

    public void shift(String name, int i) {
        dbHelper = new DBhelper(getApplicationContext());

        int j = 0;
        if (i == PrayCategory.size() - 1) {
            Dialog dialog1 = new Dialog(myprayergroup.this);
            dialog1.setContentView(R.layout.popup_add_prayer);
            final EditText edtSubject = (EditText) dialog1.findViewById(R.id.new_subject_name_edit);
            final EditText edtCategory = (EditText) dialog1.findViewById(R.id.new_category_name_edit);
            edtCategory.setVisibility(View.VISIBLE);
            ((TextView) dialog1.findViewById(R.id.new_subject_confirm_button)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String subjectname = edtSubject.getText().toString();
                    String categoryname = edtCategory.getText().toString();
                    if (subjectname.equals("")) {
                        Toast.makeText(getApplicationContext(), " Subject name cannot be Empty", Toast.LENGTH_LONG).show();
                    } else {
                        dbHelper.insertPrayer(subjectname, categoryname);
                        dbHelper.insertCategory(subjectname, categoryname);
                        int prayerid = dbHelper.getPrayerId(subjectname);
                        Intent i = new Intent(getApplicationContext(), AddSubjectDescription.class);
                        i.putExtra("prayertitle", subjectname);
                        i.putExtra("prayerid", prayerid);
                        startActivity(i);
                        finish();
                    }
                }
            });
            dialog1.show();

        } else if (i == 1) {
            Intent intent = new Intent(myprayergroup.this, ThankfulPrayers.class);
            startActivity(intent);

        } else if (i == 2) {

            Intent intent = new Intent(myprayergroup.this, ListActivity.class);
            intent.putExtra("category", "The Lords Prayer");
            intent.putStringArrayListExtra("category array", PrayCategory);
            startActivity(intent);


        } else {
            Intent intent = new Intent(myprayergroup.this, ListActivity.class);
            intent.putExtra("category", PrayCategory.get(i));
            intent.putStringArrayListExtra("category array", PrayCategory);
            startActivity(intent);

        }


    }

    private void search() {
        Dialog pop_search_word = null;
        if (pop_search_word == null) {
            pop_search_word = new Dialog(myprayergroup.this);
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

}


