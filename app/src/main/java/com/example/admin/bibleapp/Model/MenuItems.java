package com.example.admin.bibleapp.Model;

import com.google.gson.annotations.SerializedName;

public class MenuItems {
    @SerializedName("ID")
    public int menuid;

    @SerializedName("name")
    public String menuname;

    @SerializedName("iconUrl")
    public String iconUrl;

    @SerializedName("pageId")
    public int pageId;

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getLangId() {
        return LangId;
    }

    public void setLangId(int langId) {
        LangId = langId;
    }

    @SerializedName("LangId")
    public int LangId;

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }





}
