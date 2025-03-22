package com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio;

import android.os.Parcel;
import android.os.Parcelable;

public class AudioModel implements Parcelable {

    public static final Creator<AudioModel> CREATOR = new Creator<AudioModel>() {

        @Override
        public AudioModel createFromParcel(Parcel parcel) {
            return new AudioModel(parcel);
        }

        @Override
        public AudioModel[] newArray(int i) {
            return new AudioModel[i];
        }
    };
    private long duration;
    private String songAlbum;
    private String songArtist;
    private String songPath;
    private String songTitle;
    public boolean isSelected;

    public int describeContents() {
        return 0;
    }

    public AudioModel() {
    }

    public AudioModel(String path, String title, String artist, long duration) {
        this.songPath = path;
        this.songTitle = title;
        this.songArtist = artist;
        this.duration = duration;
    }

    protected AudioModel(Parcel parcel) {
        this.songTitle = parcel.readString();
        this.songPath = parcel.readString();
        this.songArtist = parcel.readString();
        this.songAlbum = parcel.readString();
        this.duration = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.songTitle);
        parcel.writeString(this.songPath);
        parcel.writeString(this.songArtist);
        parcel.writeString(this.songAlbum);
        parcel.writeLong(this.duration);
    }

    public String getSongAlbum() {
        return this.songAlbum;
    }

    public void setSongAlbum(String str) {
        this.songAlbum = str;
    }

    public String getSongTitle() {
        return this.songTitle;
    }

    public void setSongTitle(String str) {
        this.songTitle = str;
    }

    public String getSongPath() {
        return this.songPath;
    }

    public void setSongPath(String str) {
        this.songPath = str;
    }

    public String getSongArtist() {
        return this.songArtist;
    }

    public void setSongArtist(String str) {
        this.songArtist = str;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }
}
