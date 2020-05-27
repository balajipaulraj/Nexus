package com.example.admin.bibleapp.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class ScreenUtils {
    Context ctx;
    DisplayMetrics metrics = new DisplayMetrics();

    public ScreenUtils(Context ctx) {
        this.ctx = ctx;
        Display display = ((WindowManager) ctx.getSystemService("window")).getDefaultDisplay();
        display.getMetrics(this.metrics);
    }

    public int getHeight() {
        return this.metrics.heightPixels;
    }

    public int getWidth() {
        return this.metrics.widthPixels;
    }

    public int getRealHeight() {
        return this.metrics.heightPixels / this.metrics.densityDpi;
    }

    public int getRealWidth() {
        return this.metrics.widthPixels / this.metrics.densityDpi;
    }

    public int getDensity() {
        return this.metrics.densityDpi;
    }

    public int getScale(int picWidth) {
        return Double.valueOf(Double.valueOf(new Double((double) ((WindowManager) this.ctx.getSystemService("window")).getDefaultDisplay().getWidth()).doubleValue() / new Double((double) picWidth).doubleValue()).doubleValue() * 100.0d).intValue();
    }
}
