package com.example.admin.bibleapp.Model;

import com.google.gson.annotations.SerializedName;

public class LanguageLabel {

    @SerializedName("engId")
    public int engId;
    @SerializedName("labelId")
    public int labelId;
    @SerializedName("langId")
    public int langId;
    @SerializedName("name")
    public String name;
    @SerializedName("labelName")
    public String labelName;
    @SerializedName("labelDesc")
    public String labelDesc;
    @SerializedName("status")
    public int status;
}
