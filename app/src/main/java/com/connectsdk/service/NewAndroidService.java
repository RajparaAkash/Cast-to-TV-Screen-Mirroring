package com.connectsdk.service;

import com.connectsdk.core.MediaInfo;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.PlaylistControl;
import com.connectsdk.service.capability.VolumeControl;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.sessions.LaunchSession;

import java.util.ArrayList;

public class NewAndroidService extends DeviceService implements MediaPlayer/*, MediaControl, VolumeControl, WebAppLauncher*/ {
    public static final String ID = "AndroidTV2";

    @Override
    public boolean isConnectable() {
        return true;
    }

    public NewAndroidService(ServiceDescription serviceDescription, ServiceConfig serviceConfig) {
        super(serviceDescription, serviceConfig);
    }

    public static DiscoveryFilter discoveryFilter() {
        return new DiscoveryFilter(ID, "_androidtvremote2._tcp.local.");
    }

    @Override
    public boolean isConnected() {
        return this.connected;
    }

    @Override
    public void connect() {
        this.connected = true;
        reportConnected(true);
    }

    @Override
    public void disconnect() {
        this.connected = false;
    }

    @Override
    public void updateCapabilities() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(MediaPlayer.Display_Image);
        arrayList.add("MediaPlayer.Play.Video");
        arrayList.add("MediaPlayer.Play.Audio");
        arrayList.add(MediaPlayer.Play_Playlist);
        arrayList.add(MediaPlayer.Close);
        arrayList.add(MediaPlayer.Subtitle_SRT);
        arrayList.add(MediaPlayer.MetaData_Title);
        arrayList.add(MediaPlayer.MetaData_MimeType);
        arrayList.add(MediaPlayer.MediaInfo_Get);
        arrayList.add(MediaPlayer.MediaInfo_Subscribe);
        arrayList.add(MediaControl.Play);
        arrayList.add(MediaControl.Pause);
        arrayList.add(MediaControl.Stop);
        arrayList.add(MediaControl.Seek);
        arrayList.add(MediaControl.Position);
        arrayList.add(MediaControl.Duration);
        arrayList.add(MediaControl.PlayState);
        arrayList.add(MediaControl.PlayState_Subscribe);
        arrayList.add(MediaControl.Next);
        arrayList.add(MediaControl.Previous);
        arrayList.add(PlaylistControl.Next);
        arrayList.add(PlaylistControl.Previous);
        arrayList.add(PlaylistControl.JumpToTrack);
        arrayList.add(PlaylistControl.SetPlayMode);
        arrayList.add(VolumeControl.Volume_Set);
        arrayList.add(VolumeControl.Volume_Get);
        arrayList.add(VolumeControl.Volume_Up_Down);
        arrayList.add(VolumeControl.Volume_Subscribe);
        arrayList.add(VolumeControl.Mute_Get);
        arrayList.add(VolumeControl.Mute_Set);
        arrayList.add(VolumeControl.Mute_Subscribe);
        setCapabilities(arrayList);
    }


    @Override
    public void closeMedia(LaunchSession launchSession, ResponseListener<Object> responseListener) {

    }

    @Override
    public void displayImage(MediaInfo mediaInfo, LaunchListener launchListener) {

    }

    @Override
    public void displayImage(String str, String str2, String str3, String str4, String str5, LaunchListener launchListener) {

    }

    @Override
    public void getMediaInfo(MediaInfoListener mediaInfoListener) {

    }

    @Override
    public MediaPlayer getMediaPlayer() {
        return null;
    }

    @Override
    public CapabilityPriorityLevel getMediaPlayerCapabilityLevel() {
        return null;
    }

    @Override
    public void playMedia(MediaInfo mediaInfo, boolean z, LaunchListener launchListener) {

    }

    @Override
    public void playMedia(String str, String str2, String str3, String str4, String str5, boolean z, LaunchListener launchListener) {

    }

    @Override
    public ServiceSubscription<MediaInfoListener> subscribeMediaInfo(MediaInfoListener mediaInfoListener) {
        return null;
    }

}
