package com.casttotv.screenmirroring.chromecast.smart.tv.Model;

import androidx.annotation.Keep;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Keep
@Entity(tableName = "channel")
public class ModelChannel implements Serializable {
    @PrimaryKey
    int id;
    String icon;
    String name;
    String url;
    boolean isFavorite;

    public ModelChannel(int id, String icon, String name, String url) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean favorite) {
        isFavorite = favorite;
    }

}
