package com.example.admin.bibleapp.quickaction;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class ActionItem {
    private int mActionId;
    private Drawable mIcon;
    private boolean mSelected;
    private boolean mSticky;
    private Bitmap mThumb;
    private String mTitle;

    public ActionItem(int mActionId, String mTitle, Drawable mIcon) {
        this.mActionId = -1;
        this.mTitle = mTitle;
        this.mIcon = mIcon;
        this.mActionId = mActionId;
    }

    public ActionItem() {
        this(-1, null, null);
    }

    public ActionItem(int mActionId, String mTitle) {
        this(mActionId, mTitle, null);
    }

    public ActionItem(Drawable mIcon) {
        this(-1, null, mIcon);
    }

    public ActionItem(int mActionId, Drawable mIcon) {
        this(mActionId, null, mIcon);
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setIcon(Drawable mIcon) {
        this.mIcon = mIcon;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public void setActionId(int mActionId) {
        this.mActionId = mActionId;
    }

    public int getActionId() {
        return this.mActionId;
    }

    public void setSticky(boolean mSticky) {
        this.mSticky = mSticky;
    }

    public boolean isSticky() {
        return this.mSticky;
    }

    public void setSelected(boolean mSelected) {
        this.mSelected = mSelected;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public void setThumb(Bitmap mThumb) {
        this.mThumb = mThumb;
    }

    public Bitmap getThumb() {
        return this.mThumb;
    }
}
