package com.example.admin.bibleapp.util;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.admin.bibleapp.C0246R;
import com.example.admin.bibleapp.R;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

public class UiUtil {
    private static final Hashtable<String, SoftReference<Typeface>> fontCache = new Hashtable();

    public static void setCustomFont(View view, Context ctx, AttributeSet attrs, int[] attributeSet, int fontId) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, attributeSet);
        setCustomFont(view, ctx, a.getString(fontId));
        a.recycle();
    }

    private static boolean setCustomFont(View view, Context ctx, String asset) {
        if (TextUtils.isEmpty(asset)) {
            return false;
        }
        try {
            Typeface tf = getFont(ctx, asset);
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(tf);
            } else {
                ((Button) view).setTypeface(tf);
            }
            return true;
        } catch (Exception e) {
            Log.e("AppUtil", "Could not get typface  " + asset);
            return false;
        }
    }

    public static Typeface getFont(Context c, String name) {
        synchronized (fontCache) {
            if (fontCache.get(name) != null) {
                SoftReference<Typeface> ref = (SoftReference) fontCache.get(name);
                if (ref.get() != null) {
                    Typeface typeface = (Typeface) ref.get();
                    return typeface;
                }
            }
            Typeface typeface2 = Typeface.createFromAsset(c.getAssets(), name);
            fontCache.put(name, new SoftReference(typeface2));
            return typeface2;
        }
    }

    public static ColorStateList getColorList(Context context, int selectedColor, int unselectedColor) {
        int[][] states = new int[3][];
        states[0] = new int[]{16842919};
        states[1] = new int[]{16842913};
        states[2] = new int[0];
        return new ColorStateList(states, new int[]{ContextCompat.getColor(context, selectedColor), ContextCompat.getColor(context, selectedColor), ContextCompat.getColor(context, unselectedColor)});
    }

    public static void keepScreenAwake(boolean enable, Context context) {
        if (enable) {
            ((Activity) context).getWindow().addFlags(128);
        } else {
            ((Activity) context).getWindow().clearFlags(128);
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    public static void copyToClipboard(Context context, String text) {
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("copy", text));
    }

    public static void share(Context context, String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction("android.intent.action.SEND");
        sendIntent.putExtra("android.intent.extra.TEXT", text);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, context.getResources().getText(C0246R.string.send_to)));
    }
}
