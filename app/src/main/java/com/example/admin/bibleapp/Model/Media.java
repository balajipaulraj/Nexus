package com.example.admin.bibleapp.Model;

import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("title")
    public String title;


    @SerializedName("bannerImageUrl")
    public String bannerImageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @SerializedName("content")
    public String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getLanguage() {
//        return language;
//    }
//
//    public void setLanguage(String language) {
//        this.language = language;
//    }
//
    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }
//
//    public String getEditedBy() {
//        return editedBy;
//    }
//
//    public void setEditedBy(String editedBy) {
//        this.editedBy = editedBy;
//    }
//
//    public String getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getAddedBy() {
//        return addedBy;
//    }
//
//    public void setAddedBy(String addedBy) {
//        this.addedBy = addedBy;
//    }
//
//    public String getEditedAt() {
//        return editedAt;
//    }
//
//    public void setEditedAt(String editedAt) {
//        this.editedAt = editedAt;
//    }
//
//    public String getExpire() {
//        return expire;
//    }
//
//    public void setExpire(String expire) {
//        this.expire = expire;
//    }
//
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @SerializedName("audioUrl")
    public String audioUrl;
    @SerializedName("videoUrl")
    public String videoUrl;
    @SerializedName("pdfUrl")
    public String pdfUrl;
    @SerializedName("docUrl")
    public String docUrl;

    @SerializedName("ID")
    public int ID;

}
