package com.casttotv.screenmirroring.chromecast.smart.tv.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MediaModel implements Parcelable {

    public static final Creator<MediaModel> CREATOR = new Creator<MediaModel>() {
        @Override
        public MediaModel createFromParcel(Parcel parcel) {
            return new MediaModel(parcel);
        }

        @Override
        public MediaModel[] newArray(int i) {
            return new MediaModel[i];
        }
    };

    private String albumName;
    private long duration;
    private int id;
    public boolean isSelected;
    private String photoUri;
    private String title;
    public boolean header;

    public int describeContents() {
        return 0;
    }

    public MediaModel() {
    }

    public MediaModel(String str, String str2, String str3) {
        this.title = str;
        this.albumName = str2;
        this.photoUri = str3;
    }

    public MediaModel(String str, String str2, String str3, long j) {
        this.title = str;
        this.albumName = str2;
        this.photoUri = str3;
        this.duration = j;
    }

    protected MediaModel(Parcel parcel) {
        this.id = parcel.readInt();
        this.albumName = parcel.readString();
        this.photoUri = parcel.readString();
        this.duration = parcel.readLong();
        this.title = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.albumName);
        parcel.writeString(this.photoUri);
        parcel.writeLong(this.duration);
        parcel.writeString(this.title);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setAlbumName(String str) {
        this.albumName = str;
    }

    public String getPhotoUri() {
        return this.photoUri;
    }

    public void setPhotoUri(String str) {
        this.photoUri = str;
    }

    public long getDuration() {
        return this.duration;
    }



    public void setDuration(long j) {
        this.duration = j;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAlbumName() {
        return albumName;
    }
}
