package com.example.admin.bibleapp.Retrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.Media;
import com.example.admin.bibleapp.Model.MenuItems;
import com.example.admin.bibleapp.Retrofit.APIClient;
import com.example.admin.bibleapp.Retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SideMenu {

    public static ArrayList<MenuItems> getMenuItem(final Context con, int userid) {
        final DBhelper dbhelper = new DBhelper(con, con.getFilesDir().getAbsolutePath());
        APIInterface apiInterface = APIClient.getCacheEnabledRetrofit(con).create(APIInterface.class);
        final ArrayList<MenuItems> menuList = new ArrayList<MenuItems>();
        Call<ArrayList<MenuItems>> call = apiInterface.getSideMenu(userid);
        call.enqueue(new Callback<ArrayList<MenuItems>>() {
            @Override
            public void onResponse(Call<ArrayList<MenuItems>> call, Response<ArrayList<MenuItems>> response) {
                try {
                    if (response.isSuccessful()) {

                        if (response.body().size() > 0) {
                            for (int i = 0; i < response.body().size(); i++) {
                                MenuItems amenu = new MenuItems();
                                amenu.setIconUrl(response.body().get(i).getIconUrl());
                                if (dbhelper.getMenuId(response.body().get(i).getMenuid()) <= 0) {
                                    dbhelper.insertMenuItem(response.body().get(i).getMenuid(), response.body().get(i).getLangId(), response.body().get(i).getMenuname(), response.body().get(i).getPageId());
                                }
                            }
                        }
                    } else {
                        Toast.makeText(con, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<MenuItems>> call, Throwable t) {
                Log.e("invalis", t.getMessage());
                try {
                    call.cancel();
                } catch (Exception e) {

                }
            }
        });
        return menuList;
    }

    public static ArrayList<MenuItems> getFooterMenu(final Context con, int userid) {
        final DBhelper dbhelper = new DBhelper(con, con.getFilesDir().getAbsolutePath());
        APIInterface apiInterface = APIClient.getCacheEnabledRetrofit(con).create(APIInterface.class);
        final ArrayList<MenuItems> menuList = new ArrayList<MenuItems>();
        Call<ArrayList<MenuItems>> call = apiInterface.getFooterMenu(userid);
        call.enqueue(new Callback<ArrayList<MenuItems>>() {
            @Override
            public void onResponse(Call<ArrayList<MenuItems>> call, Response<ArrayList<MenuItems>> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().size() > 0) {
                            for (int i = 0; i < response.body().size(); i++) {
                                MenuItems amenu = new MenuItems();
                                amenu.setIconUrl(response.body().get(i).getIconUrl());
                                if (dbhelper.getMenuId(response.body().get(i).getMenuid()) <= 0) {
                                    dbhelper.insertMenuItem(response.body().get(i).getMenuid(), response.body().get(i).getLangId(), response.body().get(i).getMenuname(), response.body().get(i).getPageId());
                                }
                            }
                        }
                    } else {
                        Toast.makeText(con, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<MenuItems>> call, Throwable t) {
                Log.e("invalis", t.getMessage());
                try {
                    Toast.makeText(con, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                    call.cancel();
                } catch (Exception e) {

                }
            }
        });
        return menuList;
    }


}
