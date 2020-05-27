package com.example.admin.bibleapp;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class customwebview extends WebView {
    public customwebview(Context context) {
        super(context);
    }

    public customwebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public customwebview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getContentHeight() {
        return computeVerticalScrollRange();
    }
}
