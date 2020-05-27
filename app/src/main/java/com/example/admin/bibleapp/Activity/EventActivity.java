package com.example.admin.bibleapp.Activity;

import androidx.annotation.IdRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.bibleapp.Adapter.BannerAdapter;
import com.example.admin.bibleapp.Adapter.DrawerItemCustomAdapter;
import com.example.admin.bibleapp.Adapter.EventAdapter;
import com.example.admin.bibleapp.Adapter.MediaAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.BannerModel;
import com.example.admin.bibleapp.Model.Event;
import com.example.admin.bibleapp.Model.Media;
import com.example.admin.bibleapp.Model.user;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Retrofit.APIClient;
import com.example.admin.bibleapp.Retrofit.APIInterface;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawer;
    BottomBar bottomBar;
    private ListView mDrawerList;
    SharedPreferences sharedPreferences;
    DBhelper dbhelper;
    static Context ctx;
    public static EventAdapter mediaAdapter;
    static RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        toolbar = (Toolbar) findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);
        TextView tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("Event");
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        sharedPreferences = getSharedPreferences("MyPrefs", 0);
        int userId = sharedPreferences.getInt("userid", 0);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        dbhelper = new DBhelper(this, getFilesDir().getAbsolutePath());
        List<String> menuItems = dbhelper.getMenu();
        ctx = EventActivity.this;
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
        Log.e("event", "tes");
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_read) {
                    Intent i = new Intent(EventActivity.this, bibleContentSelect.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();
                }  else if (tabId == R.id.tab_media) {
                    Intent i = new Intent(EventActivity.this, MediaActivity.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();
                } else if (tabId == R.id.tab_plans) {
                    Intent i = new Intent(EventActivity.this, MonthlyCal.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();
                } else if (tabId == R.id.tab_pray) {
                    Intent i = new Intent(EventActivity.this, myprayergroup.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();
                } else if (tabId == R.id.tab_page) {
                    Intent i = new Intent(EventActivity.this, PageActivity.class);

                    startActivity(i);
                    overridePendingTransition(0, 0);

                    finish();

                }
            }
        });
        rvList = findViewById(R.id.rv_menu);
        RecyclerView.LayoutManager layoutManager5 = new GridLayoutManager(EventActivity.this, 2);
        rvList.setLayoutManager(layoutManager5);
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setLayoutManager(layoutManager5);
        Log.e("event", "tes1");
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
            Log.e("Exception", e.getMessage());
        }

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
                        Intent intent = new Intent(EventActivity.this, Notes.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;

                    case 8:
                        Intent intent8 = new Intent(EventActivity.this, pop_activity_about.class);
                        intent8.setFlags(intent8.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent8);
                        break;
                    case 10:
                        break;
                    case 11:
                        Intent intent11 = new Intent(EventActivity.this, UserProfile.class);
                        intent11.setFlags(intent11.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent11);
                        break;
                    case 2:
                        Intent intent2 = new Intent(EventActivity.this, popFlashcard.class);
                        intent2.setFlags(intent2.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case 1:
                        search();
                        break;

                    case 4:
                        Intent intent4 = new Intent(EventActivity.this, popReadHistory.class);
                        intent4.setFlags(intent4.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                    case 0:
                        Intent intent0 = new Intent(EventActivity.this, Settings.class);
                        intent0.setFlags(intent0.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent0);
                        break;

                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getEvent();
    }

    private void search() {
        Dialog pop_search_word = null;
        if (pop_search_word == null) {
            pop_search_word = new Dialog(EventActivity.this);
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

    private static ArrayList<Event> getEvent() {
        APIInterface apiInterface = APIClient.getCacheEnabledRetrofit(ctx).create(APIInterface.class);
        final ArrayList<Event> Medias = new ArrayList<Event>();
        Call<ArrayList<Event>> call = apiInterface.getEvent(1);
        call.enqueue(new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                try {
                    List<Event> call1 = response.body();
                    Log.e("Model", call1.toString());
                    for (int i = 0; i < call1.size(); i++) {
                        String titlename = call1.get(i).getTitle();
                        String image = call1.get(i).getBannerImageUrl();
                        int id = call1.get(i).getID();
                        Event media = new Event();
                        media.setTitle(titlename);
                        media.setBannerImageUrl(image);
                        media.setID(id);
                        Medias.add(media);
                    }
                    mediaAdapter = new EventAdapter(ctx, Medias);
                    rvList.setAdapter(mediaAdapter);
                    mediaAdapter.notifyDataSetChanged();

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }

        });
        return Medias;
    }
}
