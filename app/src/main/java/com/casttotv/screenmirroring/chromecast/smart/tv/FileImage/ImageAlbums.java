package com.casttotv.screenmirroring.chromecast.smart.tv.FileImage;

import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MediaModel;

import java.util.ArrayList;

public class ImageAlbums {

    private ArrayList<MediaModel> albumPhotos;
    private String coverUri;
    private String name;

    public void setId(int i) {
    }

    public ImageAlbums() {
    }

    public ImageAlbums(int i, String str, String str2, ArrayList<MediaModel> arrayList) {
        this.name = str;
        this.coverUri = str2;
        this.albumPhotos = arrayList;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCoverUri() {
        return this.coverUri;
    }

    public void setCoverUri(String str) {
        this.coverUri = str;
    }

    public ArrayList<MediaModel> getAlbumPhotos() {
        if (this.albumPhotos == null) {
            this.albumPhotos = new ArrayList<>();
        }
        return this.albumPhotos;
    }
}
