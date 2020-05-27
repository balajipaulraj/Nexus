package com.example.admin.bibleapp.Model;

import com.google.gson.annotations.SerializedName;

public class Language {
    @SerializedName("langId")
    public int langId;
    @SerializedName("langType")
    public String langType;
    @SerializedName("OriginalLangtext")
    public String OriginalLangtext;
    @SerializedName("status")
    public String status;
}
