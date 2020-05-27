package com.example.admin.bibleapp.webviewmarker;

import android.webkit.JavascriptInterface;

public class TextSelectionController {
    public static final String INTERFACE_NAME = "TextSelection";
    public static final String TAG = "TextSelectionController";
    private TextSelectionControlListener mListener;

    public TextSelectionController(TextSelectionControlListener listener) {
        this.mListener = listener;
    }

    @JavascriptInterface
    public void jsError(String error) {
        if (this.mListener != null) {
            this.mListener.jsError(error);
        }
    }

    @JavascriptInterface
    public void jsLog(String message) {
        if (this.mListener != null) {
            this.mListener.jsLog(message);
        }
    }

    @JavascriptInterface
    public void startSelectionMode() {
        if (this.mListener != null) {
            this.mListener.startSelectionMode();
        }
    }

    @JavascriptInterface
    public void endSelectionMode() {
        if (this.mListener != null) {
            this.mListener.endSelectionMode();
        }
    }

    @JavascriptInterface
    public void selecttext() {
        if (this.mListener != null) {
            this.mListener.selecttext();
        }
    }

    @JavascriptInterface
    public void selectionChanged(String range, String text, String handleBounds, boolean isReallyChanged) {
        if (this.mListener != null) {
            this.mListener.selectionChanged(range, text, handleBounds, isReallyChanged);
        }
    }

    @JavascriptInterface
    public void setContentWidth(float contentWidth) {
        if (this.mListener != null) {
            this.mListener.setContentWidth(contentWidth);
        }
    }
}
