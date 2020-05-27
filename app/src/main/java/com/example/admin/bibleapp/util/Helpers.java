package com.example.admin.bibleapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Helpers {
    public static boolean NetworkCheck(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static String ManageText(String Input) {
        return Input.replace("'", "''");
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String GetCurrentDateTime() {
        return new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String GetCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        day.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        return day.format(cal.getTime());
    }
}
