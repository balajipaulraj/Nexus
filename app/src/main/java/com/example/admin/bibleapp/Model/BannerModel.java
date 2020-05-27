package com.example.admin.bibleapp.Model;

import com.google.gson.annotations.SerializedName;

public class BannerModel {

    public String getBannerimage() {
        return bannerimage;
    }

    public void setBannerimage(String bannerimage) {
        this.bannerimage = bannerimage;
    }


    @SerializedName("banner_sm_image")
    public String bannerimage;

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }

    @SerializedName("banner_id")
    public int banner;


}
