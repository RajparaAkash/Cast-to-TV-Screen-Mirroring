package com.casttotv.screenmirroring.chromecast.smart.tv.Controllers;

import com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio.AudioModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MediaModel;

import java.util.ArrayList;
import java.util.List;

public class ManagerDataPlay {

    private static ManagerDataPlay managerDataPlay;
    public Long duration = 0L;
    private List<AudioModel> listAudio;
    private ArrayList<MediaModel> listPhoto;
    public String pathCast = "";
    private int posSelected = 0;
    public String thumbCast = "";
    public String titleCast = "";
    private int type = 0;

    public static ManagerDataPlay getInstance() {
        if (managerDataPlay == null) {
            managerDataPlay = new ManagerDataPlay();
        }
        return managerDataPlay;
    }

    public int getTypePlay() {
        return this.type;
    }

    public void setTypePlay(int i) {
        this.type = i;
    }

    public void setPosSelected(int i) {
        this.posSelected = i;
    }

    public int getPosSelected() {
        return this.posSelected;
    }

    public ArrayList<MediaModel> getListMedia() {
        return this.listPhoto;
    }

    public void setListMedia(ArrayList<MediaModel> arrayList) {
        ArrayList<MediaModel> arrayList2 = new ArrayList<>();
        this.listPhoto = arrayList2;
        arrayList2.addAll(arrayList);
    }

    public List<AudioModel> getListAudio() {
        return this.listAudio;
    }

    public void setListAudio(ArrayList<AudioModel> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        this.listAudio = arrayList2;
        arrayList2.addAll(arrayList);
    }
}
