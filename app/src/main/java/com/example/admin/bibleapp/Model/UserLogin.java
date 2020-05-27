package com.example.admin.bibleapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserLogin {

    @SerializedName("user")
    List<user> user;
    @SerializedName("expires_in")
    int expires_in;

    public List<com.example.admin.bibleapp.Model.user> getUser() {
        return user;
    }

    public void setUser(List<com.example.admin.bibleapp.Model.user> user) {
        this.user = user;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @SerializedName("type")
    String type;

}

