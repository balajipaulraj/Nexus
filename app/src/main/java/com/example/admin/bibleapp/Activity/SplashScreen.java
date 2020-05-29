package com.example.admin.bibleapp.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.Sync;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Retrofit.APIClient;
import com.example.admin.bibleapp.Retrofit.APIInterface;
import com.example.admin.bibleapp.Retrofit.SideMenu;
import com.example.admin.bibleapp.popupWindow.bibleselection;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    public static int TIME_OUT = 5000;
    private Activity activity;
    ProgressDialog pd;
    Dialog pop_db;
    int userId;
    DBhelper dbhelper;
    SharedPreferences sharedPreferences;

    class C02511 implements Runnable {
        C02511() {
        }

        public void run() {
//            SplashScreen.this.startActivity(new Intent(SplashScreen.this.getApplicationContext(), LoginActivity.class));
//            SplashScreen.this.finish();
            sharedPreferences = getSharedPreferences("MyPrefs", 0);
            SharedPreferences.Editor date_store = sharedPreferences.edit();
//            List<user> user="";
            date_store.putInt("userid", 1);
            Gson gson = new Gson();
//            String json = gson.toJson(user);
//            date_store.putString("userdata", json);
            date_store.putBoolean("firsttime", false);
            date_store.apply();

            startActivity(new Intent(SplashScreen.this, bibleselection.class));
            finish();
//            SplashScreen.this.pd.dismiss();
        }
    }

    class C02522 implements Runnable {
        C02522() {
        }

        public void run() {
            Intent intent = new Intent(SplashScreen.this, CalPage.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
            SplashScreen.this.startActivity(intent);
            finish();
//            SplashScreen.this.pd.dismiss();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_splash_screen);
        this.dbhelper = new DBhelper(this, getFilesDir().getAbsolutePath());
        try {
            this.dbhelper.prepareDatabase();
        } catch (IOException e) {
            Log.e("Splash", e.getMessage());
        }
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        init();
        String path = getApplicationContext().getFilesDir().getAbsolutePath() + "/catc plan";
        File Image_Path = new File(Environment.getExternalStorageDirectory(), "/catc plan");
        if (!Image_Path.exists()) {
            Image_Path.mkdirs();
        }
        userId = this.sharedPreferences.getInt("userid", 0);
        SideMenu sideMenu = new SideMenu();
        sideMenu.getMenuItem(SplashScreen.this, 1);
        APIInterface apiInterface = APIClient.getCacheEnabledRetrofit(this).create(APIInterface.class);
        final ArrayList<Sync> syncList = new ArrayList<Sync>();
        Call<ArrayList<Sync>> call = apiInterface.getSync(userId);
        call.enqueue(new Callback<ArrayList<Sync>>() {
            @Override
            public void onResponse(Call<ArrayList<Sync>> call, Response<ArrayList<Sync>> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().size() > 0) {
                            for (int i = 0; i < response.body().size(); i++) {
                                Sync amenu = new Sync();
                                amenu.setId(response.body().get(i).getId());
                                amenu.setName(response.body().get(i).getName());
                                amenu.setLastUpdateDate(response.body().get(i).getLastUpdateDate());
                                syncList.add(amenu);
                            }
                            checkdb(syncList);
                        }
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sync>> call, Throwable t) {
                Log.e("invalis", t.getMessage());
                try {
                call.cancel();
                } catch (Exception e) {

                }
            }
        });


//        SideMenu sideMenu = new SideMenu();
//        sideMenu.getMenuItem(SplashScreen.this, userId);
    }

    private void checkdb(ArrayList<Sync> syncList) {

        for (int i = 0; i < syncList.size(); i++) {
            if (dbhelper.getSyncId(syncList.get(i).getId())) {
                boolean status = dbhelper.getSyncData(syncList.get(i).getId(), syncList.get(i).getLastUpdateDate());
                if (!status) {
                    dbhelper.updateSyncdata(syncList.get(i).getId(), syncList.get(i).getName(), syncList.get(i).getLastUpdateDate());

                }

            } else {
                dbhelper.insertSyncdata(syncList.get(i).getId(), syncList.get(i).getName(), syncList.get(i).getLastUpdateDate());
            }
        }
    }

    private void init() {
        try {
            if (this.sharedPreferences.getBoolean("firsttime", true)) {
                new Handler().postDelayed(new C02511(), 5000);
            } else {
                new Handler().postDelayed(new C02522(), 5000);
            }
        } catch (Exception e) {
            new Handler().postDelayed(new C02511(), 5000);
        }
    }

    private void showDBtolaod() {
        if (this.pop_db == null) {
            this.pop_db = new Dialog(this);
            this.pop_db.requestWindowFeature(1);
            this.pop_db.setTitle("FontSize");
            this.pop_db.setCanceledOnTouchOutside(false);
            this.pop_db.setCancelable(true);
            this.pop_db.setContentView(R.layout.popup_db);
            RadioGroup rgSelectPlan = (RadioGroup) this.pop_db.findViewById(R.id.rg_year);
            final RadioButton rbPlan1 = (RadioButton) this.pop_db.findViewById(R.id.rb_part1);
            final RadioButton rbPlan2 = (RadioButton) this.pop_db.findViewById(R.id.rb_part2);
            ((Button) this.pop_db.findViewById(R.id.btn_select)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent i;
                    Editor edit_gen;
                    if (rbPlan1.isChecked()) {
                        i = new Intent(SplashScreen.this.getApplication(), CalPage.class);
                        SplashScreen.this.sharedPreferences = SplashScreen.this.getSharedPreferences("MyPrefs", 0);
                        edit_gen = SplashScreen.this.sharedPreferences.edit();
                        edit_gen.putString("plan", "planI");
                        edit_gen.commit();
                        SplashScreen.this.startActivity(i);
                        SplashScreen.this.finish();
                    } else if (rbPlan2.isChecked()) {
                        i = new Intent(SplashScreen.this.getApplicationContext(), CalPage.class);
                        SplashScreen.this.sharedPreferences = SplashScreen.this.getSharedPreferences("MyPrefs", 0);
                        edit_gen = SplashScreen.this.sharedPreferences.edit();
                        edit_gen.putString("plan", "planII");
                        edit_gen.commit();
                        SplashScreen.this.startActivity(i);
                        SplashScreen.this.finish();
                    }
                }
            });
            this.pop_db.show();
        } else if (this.pop_db.isShowing()) {
            this.pop_db.dismiss();
            this.pop_db = null;
            showDBtolaod();
        } else {
            this.pop_db = null;
            showDBtolaod();
        }
    }
}
