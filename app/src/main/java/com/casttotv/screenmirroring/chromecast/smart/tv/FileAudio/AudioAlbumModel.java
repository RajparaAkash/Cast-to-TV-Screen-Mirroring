package com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AudioAlbumModel implements Parcelable {

    private String nameAlbum;
    private String folderPath;
    private ArrayList<AudioModel> arrSong;

    public Boolean header = false;

    public AudioAlbumModel() {
    }

    public AudioAlbumModel(String str, ArrayList<AudioModel> arrayList) {
        this.nameAlbum = str;
        this.arrSong = arrayList;
    }

    protected AudioAlbumModel(Parcel in) {
        arrSong = in.createTypedArrayList(AudioModel.CREATOR);
        nameAlbum = in.readString();
        folderPath = in.readString();
        byte tmpHeader = in.readByte();
        header = tmpHeader == 0 ? null : tmpHeader == 1;
    }

    public static final Creator<AudioAlbumModel> CREATOR = new Creator<AudioAlbumModel>() {
        @Override
        public AudioAlbumModel createFromParcel(Parcel in) {
            return new AudioAlbumModel(in);
        }

        @Override
        public AudioAlbumModel[] newArray(int size) {
            return new AudioAlbumModel[size];
        }
    };

    public String getNameAlbum() {
        return this.nameAlbum;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public void setArrSong(ArrayList<AudioModel> arrSong) {
        this.arrSong = arrSong;
    }

    public ArrayList<AudioModel> getArrSong() {
        return arrSong;
    }

    /*public ArrayList<AudioModel> getArrSong() {
        ArrayList<AudioModel> arrayListTemp = new ArrayList<>();
        AudioModel mediaModel2 = new AudioModel();
        mediaModel2.header = true;
        arrayListTemp.add(mediaModel2);
        for (AudioModel mediaModel : arrSong) {
            arrayListTemp.add(mediaModel);
        }
        return arrayListTemp;
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeTypedList(arrSong);
        dest.writeString(nameAlbum);
        dest.writeString(folderPath);
        dest.writeByte((byte) (header == null ? 0 : header ? 1 : 2));
    }
}
