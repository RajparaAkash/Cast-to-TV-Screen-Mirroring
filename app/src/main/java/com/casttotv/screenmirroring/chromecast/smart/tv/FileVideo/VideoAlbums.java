package com.casttotv.screenmirroring.chromecast.smart.tv.FileVideo;

public class VideoAlbums {

    public String name;
    public String firstVideoPath;
    public int videoCount;

    public VideoAlbums(String str, String str2, int i) {
        this.name = str;
        this.firstVideoPath = str2;
        this.videoCount = i;
    }

    public String getName() {
        return this.name;
    }

    public String getFirstVideoPath() {
        return this.firstVideoPath;
    }

    public int getVideoCount() {
        return this.videoCount;
    }
}
