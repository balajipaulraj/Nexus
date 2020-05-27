package com.example.admin.bibleapp.webviewmarker;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class WebClient extends WebViewClient {
    public void onPageFinished(WebView view, String url) {
        view.loadUrl("javascript:AndroidFunction.resize(document.body.scrollHeight)");
    }
}
