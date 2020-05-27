package com.example.admin.bibleapp.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.bibleapp.Adapter.DrawerItemCustomAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.Event;
import com.example.admin.bibleapp.Model.Page;
import com.example.admin.bibleapp.Model.user;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Retrofit.APIClient;
import com.example.admin.bibleapp.Retrofit.APIInterface;
import com.example.admin.bibleapp.popupWindow.popFlashcard;
import com.example.admin.bibleapp.popupWindow.popReadHistory;
import com.example.admin.bibleapp.popupWindow.pop_activity_about;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageDetail extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawer;
    SharedPreferences sharedPreferences;
    DBhelper dbhelper;
    private ListView mDrawerList;
    static int mediaId;
    static TextView tvTitle, tvContent, tvLocation;
    static Context ctx;
    static ImageView ivBanner;
    public static String BASE_URL = "http://ec2-15-206-75-177.ap-south-1.compute.amazonaws.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detail);
        toolbar = (Toolbar) findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);
        TextView tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("Connect Detail");
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        Bundle extras = getIntent().getExtras();
        mediaId = extras.getInt("id", 1);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        dbhelper = new DBhelper(this, getFilesDir().getAbsolutePath());
        List<String> menuItems = dbhelper.getMenu();
        tvTitle=(TextView) findViewById(R.id.tv_title);
        ctx=PageDetail.this;
        tvContent=(TextView) findViewById(R.id.tv_content);
        tvLocation=(TextView) findViewById(R.id.tv_location);
        ivBanner=(ImageView )findViewById(R.id.banner);
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
                        Intent intent = new Intent(PageDetail.this, Notes.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;

                    case 8:
                        Intent intent8 = new Intent(PageDetail.this, pop_activity_about.class);
                        intent8.setFlags(intent8.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent8);
                        break;
                    case 10:
                        Intent intent10 = new Intent(PageDetail.this, EventActivity.class);
                        intent10.setFlags(intent10.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(PageDetail.this, UserProfile.class);
                        intent11.setFlags(intent11.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent11);
                        break;
                    case 2:
                        Intent intent2 = new Intent(PageDetail.this, popFlashcard.class);
                        intent2.setFlags(intent2.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case 1:
                        search();
                        break;

                    case 4:
                        Intent intent4 = new Intent(PageDetail.this, popReadHistory.class);
                        intent4.setFlags(intent4.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                    case 0:
                        Intent intent0 = new Intent(PageDetail.this, Settings.class);
                        intent0.setFlags(intent0.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent0);
                        break;

                }
            }
        });
        getPageDetail();

    }

    private static ArrayList<Page> getPageDetail() {
        APIInterface apiInterface = APIClient.getCacheEnabledRetrofit(ctx).create(APIInterface.class);
        final ArrayList<Page> Medias = new ArrayList<Page>();
        Call<ArrayList<Page>> call = apiInterface.getIdPage(mediaId,1);
        call.enqueue(new Callback<ArrayList<Page>>() {
            @Override
            public void onResponse(Call<ArrayList<Page>> call, Response<ArrayList<Page>> response) {
                try {
                    List<Page> call1 = response.body();
                    Log.e("Model", call1.toString());
                    for (int i = 0; i < call1.size(); i++) {
                        String titlename = call1.get(i).getTitle();
                        String image = call1.get(i).getBannerImageUrl();
                        String content = call1.get(i).getContent();
                        int id = call1.get(i).getID();
                        Page media = new Page();
                        media.setTitle(titlename);
                        media.setBannerImageUrl(image);
                        media.setContent(content);
                        media.setID(id);
                        Medias.add(media);
                    }

                    tvTitle.setText(Medias.get(0).getTitle());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        tvContent.setText(Html.fromHtml(Medias.get(0).getContent(), Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        tvContent.setText(Html.fromHtml(Medias.get(0).getContent()));
                    }
                    Picasso.with(ctx).load(BASE_URL + Medias.get(0).getBannerImageUrl()).memoryPolicy(MemoryPolicy.NO_CACHE).into(ivBanner);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Page>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }

        });
        return Medias;
    }


    private void search() {
        Dialog pop_search_word=null;
        if (pop_search_word == null) {
            pop_search_word = new Dialog(PageDetail.this);
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

