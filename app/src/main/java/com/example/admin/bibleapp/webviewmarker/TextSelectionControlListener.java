package com.example.admin.bibleapp.webviewmarker;

public interface TextSelectionControlListener {
    void endSelectionMode();

    void jsError(String str);

    void jsLog(String str);

    void selectionChanged(String str, String str2, String str3, boolean z);

    void selecttext();

    void setContentWidth(float f);

    void startSelectionMode();
}
