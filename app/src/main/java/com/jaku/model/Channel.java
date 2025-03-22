package com.jaku.model;

public class Channel {
    private String mId;
    private String mImageUrl;
    private String mTitle;
    private String mType;
    private String mVersion;

    public Channel() {
    }

    public Channel(String str, String str2, String str3, String str4, String str5) {
        this.mId = str;
        this.mTitle = str2;
        this.mType = str3;
        this.mVersion = str4;
        this.mImageUrl = str5;
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public String getVerion() {
        return this.mVersion;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void setImageUrl(String str) {
        this.mImageUrl = str;
    }

    public String toString() {
        return this.mId + " " + this.mTitle + " " + this.mType + " " + this.mVersion + " " + this.mImageUrl;
    }
}
