package com.example.admin.bibleapp.Model;

import com.google.gson.annotations.SerializedName;

public class EnglishLabel {

    @SerializedName("id")
    public int id;
    @SerializedName("langId")
    public int langId;
    @SerializedName("name")
    public String name;
    @SerializedName("labelDesc")
    public String labelDesc;
    @SerializedName("status")
    public int status;
}
