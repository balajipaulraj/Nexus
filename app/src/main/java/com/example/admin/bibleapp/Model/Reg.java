package com.example.admin.bibleapp.Model;

import com.google.gson.annotations.SerializedName;

public class Reg {

    @SerializedName("userId")
    int userId;
    @SerializedName("msg")
    String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @SerializedName("expires_in")
    int expires_in;

}
