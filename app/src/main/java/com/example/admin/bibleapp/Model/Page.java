package com.example.admin.bibleapp.Model;

import com.google.gson.annotations.SerializedName;

public class Page {
    @SerializedName("title")
    public String title;
    @SerializedName("content")
    public String content;
    @SerializedName("excerpt")
    public String excerpt;
    @SerializedName("parentId")
    public String parentId;
    @SerializedName("bannerImageUrl")
    public String bannerImageUrl;
    @SerializedName("status")
    public String status;
    @SerializedName("language")
    public String language;
    @SerializedName("editedBy")
    public String editedBy;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(String editedAt) {
        this.editedAt = editedAt;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @SerializedName("createdAt")
    public String createdAt;
    @SerializedName("addedBy")
    public String addedBy;
    @SerializedName("editedAt")
    public String editedAt;
    @SerializedName("expire")
    public String expire;
    @SerializedName("ID")
    public int ID;
}
