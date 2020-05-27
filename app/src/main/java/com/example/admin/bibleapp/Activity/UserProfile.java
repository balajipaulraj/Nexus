package com.example.admin.bibleapp.Activity;

import android.app.Dialog;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.admin.bibleapp.Adapter.DrawerItemCustomAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.UserLogin;
import com.example.admin.bibleapp.Model.user;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Retrofit.APIInterface;
import com.example.admin.bibleapp.popupWindow.popFlashcard;
import com.example.admin.bibleapp.popupWindow.popReadHistory;
import com.example.admin.bibleapp.popupWindow.pop_activity_about;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Admin on 6/27/2018.
 */

public class UserProfile extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawer;
    EditText tvFirstName, tvLastName, tvLocation, tvUserId, tvGender, tvDob, tvRegister, tvEmail, tvStatus, tvMobile;
    BottomBar bottomBar;
    private ListView mDrawerList;
    DBhelper dbHelper;
    ImageView ivEdit;
    SharedPreferences sharedPreferences;
    APIInterface apiInterface;
    List<user> userSites = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        toolbar = (Toolbar) findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);
        TextView tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("Profile");
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        sharedPreferences = getSharedPreferences("MyPrefs", 0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userdata", "");
//        use = gson.fromJson(json, user.class);

        Type collectionType = new TypeToken<Collection<user>>() {
        }.getType();
        userSites = gson.fromJson(json, collectionType);
        Log.e("User data", json);
        tvFirstName = (EditText) findViewById(R.id.tv_firstname);
        tvLastName = (EditText) findViewById(R.id.tv_lastname);
        ivEdit = (ImageView) findViewById(R.id.iv_edit);
        tvLocation = (EditText) findViewById(R.id.tv_location);
        tvUserId = (EditText) findViewById(R.id.tv_userid);
        tvGender = (EditText) findViewById(R.id.tv_gender);
        tvDob = (EditText) findViewById(R.id.tv_dob);
        tvRegister = (EditText) findViewById(R.id.tv_reg);
        tvEmail = (EditText) findViewById(R.id.tv_email);
        tvMobile = (EditText) findViewById(R.id.tv_mobile);
        tvStatus = (EditText) findViewById(R.id.tv_status);
        try {

            tvFirstName.setText(userSites.get(0).getFirstname());
            tvLastName.setText(userSites.get(0).getLastname());
            tvLocation.setText(userSites.get(0).getCity() + "," + userSites.get(0).getState());
            tvUserId.setText(String.valueOf(userSites.get(0).getUserId()));
            tvGender.setText(userSites.get(0).getGender());
            tvDob.setText(userSites.get(0).getDob());
            tvRegister.setText(userSites.get(0).getRegisterDate());
            tvMobile.setText(userSites.get(0).getPhone());
            tvEmail.setText(userSites.get(0).getEmail());
            tvStatus.setText(userSites.get(0).getStatus());
        } catch (Exception e) {
            Log.e("Exception ", e.getMessage());
        }
//
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvEmail.isInEditMode()) {
//                   ivEdit.setImageResource(getResources().getDrawable(R.id.ic_menu_edit));
                    tvFirstName.setEnabled(false);
                    tvLastName.setEnabled(false);
                    tvLocation.setEnabled(false);
                    tvMobile.setEnabled(false);
                    tvEmail.setEnabled(false);
                    tvStatus.setEnabled(false);
//                    upload();
                } else {
                    tvFirstName.setEnabled(true);
                    tvLastName.setEnabled(true);
                    tvLocation.setEnabled(true);
                    tvMobile.setEnabled(true);
                    tvEmail.setEnabled(true);
                    tvStatus.setEnabled(true);
                }
            }
        });

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


        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_read) {
                    Intent i = new Intent(UserProfile.this, bibleContentSelect.class);

                    startActivity(i);
                    finish();
                } else if (tabId == R.id.tab_media) {
                    Intent i = new Intent(UserProfile.this, MediaActivity.class);

                    startActivity(i);
                    finish();
                } else if (tabId == R.id.tab_plans) {
                    Intent i = new Intent(UserProfile.this, MonthlyCal.class);

                    startActivity(i);
                    finish();
                } else if (tabId == R.id.tab_pray) {
                    Intent i = new Intent(UserProfile.this, myprayergroup.class);

                    startActivity(i);
                    finish();
                } else if (tabId == R.id.tab_page) {
                    Intent i = new Intent(UserProfile.this, PageActivity.class);

                    startActivity(i);
                    finish();

                }
            }
        });


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
                        Intent intent = new Intent(UserProfile.this, Notes.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;

                    case 8:
                        Intent intent8 = new Intent(UserProfile.this, pop_activity_about.class);
                        intent8.setFlags(intent8.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent8);
                        break;
                    case 10:
                        Intent intent10 = new Intent(UserProfile.this, EventActivity.class);
                        intent10.setFlags(intent10.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent10);
                        break;
                    case 11:
                        break;
                    case 2:
                        Intent intent2 = new Intent(UserProfile.this, popFlashcard.class);
                        intent2.setFlags(intent2.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case 1:
                        search();
                        break;

                    case 4:
                        Intent intent4 = new Intent(UserProfile.this, popReadHistory.class);
                        intent4.setFlags(intent4.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                    case 0:
                        Intent intent0 = new Intent(UserProfile.this, Settings.class);
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
            List<user> userSites = gson.fromJson(json, collectionType);
            tvProfile.setText(userSites.get(0).getFirstname() + " " + userSites.get(0).getLastname());
            tvaddress.setText(userSites.get(0).getAddress() + " " + userSites.get(0).getCity());
        } catch (Exception e) {
            Log.e("Second Exception", e.getMessage());
        }
    }

    //
//
    public void upload() {
        Call<UserLogin> call = apiInterface.editprofile(tvEmail.getText().toString(), userSites.get(0).getPassword(), tvFirstName.getText().toString(),
                tvLastName.getText().toString(), tvMobile.getText().toString());
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                if (response.isSuccessful()) {

                } else {

                    Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }


    private void search() {
        Dialog pop_search_word = null;
        if (pop_search_word == null) {
            pop_search_word = new Dialog(UserProfile.this);
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
