package com.casttotv.screenmirroring.chromecast.smart.tv.Model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Keep
@Entity(tableName = "web_history")
public class WebHistory implements Serializable {
    public String icon;
    public String name;
    public long timeStamp;
    @NonNull
    @PrimaryKey
    public String url;


    public WebHistory(String str, String str2, String str3, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) == 0 ? str3 : "", (i & 8) != 0 ? System.currentTimeMillis() : j);
    }

    public WebHistory(String name, String url, String icon, long timeStamp) {
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.timeStamp = timeStamp;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        WebHistory webHistory = (WebHistory) obj;
        return Intrinsics.areEqual(getName(), webHistory.getName()) && Intrinsics.areEqual(getUrl(), webHistory.getUrl()) && Intrinsics.areEqual(getIcon(), webHistory.getIcon()) && getTimeStamp() == webHistory.getTimeStamp();
    }

    public int hashCode() {
        return (((((getName().hashCode() * 31) + getUrl().hashCode()) * 31) + getIcon().hashCode()) * 31) + Long.hashCode(getTimeStamp());
    }

}
