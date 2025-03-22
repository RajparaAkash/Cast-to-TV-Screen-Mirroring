package com.connectsdk.service.capability;

import com.connectsdk.core.MediaInfo;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.sessions.LaunchSession;

public interface MediaPlayer extends CapabilityMethods {

    public static final String Any = "MediaPlayer.Any";
    @Deprecated
    public static final String Display_Audio = "MediaPlayer.Play.Audio";
    @Deprecated
    public static final String Display_Video = "MediaPlayer.Play.Video";
    public static final String Loop = "MediaPlayer.Loop";
    public static final String Play_Audio = "MediaPlayer.Play.Audio";
    public static final String Play_Playlist = "MediaPlayer.Play.Playlist";
    public static final String Play_Video = "MediaPlayer.Play.Video";
    public static final String Subtitle_SRT = "MediaPlayer.Subtitle.SRT";
    public static final String Subtitle_WebVTT = "MediaPlayer.Subtitle.WebVTT";
    public static final String Display_Image = "MediaPlayer.Display.Image";
    public static final String Close = "MediaPlayer.Close";
    public static final String MetaData_Title = "MediaPlayer.MetaData.Title";
    public static final String MetaData_Description = "MediaPlayer.MetaData.Description";
    public static final String MetaData_Thumbnail = "MediaPlayer.MetaData.Thumbnail";
    public static final String MetaData_MimeType = "MediaPlayer.MetaData.MimeType";
    public static final String MediaInfo_Get = "MediaPlayer.MediaInfo.Get";
    public static final String MediaInfo_Subscribe = "MediaPlayer.MediaInfo.Subscribe";
    public static final String[] Capabilities = {Display_Image, "MediaPlayer.Play.Video", "MediaPlayer.Play.Audio", Close, MetaData_Title, MetaData_Description, MetaData_Thumbnail, MetaData_MimeType, MediaInfo_Get, MediaInfo_Subscribe};


    public interface LaunchListener extends ResponseListener<MediaLaunchObject> {
    }


    public interface MediaInfoListener extends ResponseListener<MediaInfo> {
    }

    void closeMedia(LaunchSession launchSession, ResponseListener<Object> listener);

    void displayImage(MediaInfo mediaInfo, LaunchListener listener);

    @Deprecated
    void displayImage(String url, String mimeType, String title, String description, String iconSrc, LaunchListener listener);

    void getMediaInfo(MediaInfoListener listener);

    MediaPlayer getMediaPlayer();

    CapabilityPriorityLevel getMediaPlayerCapabilityLevel();

    void playMedia(MediaInfo mediaInfo, boolean shouldLoop, LaunchListener listener);

    @Deprecated
    void playMedia(String url, String mimeType, String title, String description, String iconSrc, boolean shouldLoop, LaunchListener listener);

    ServiceSubscription<MediaInfoListener> subscribeMediaInfo(MediaInfoListener listener);


    public static class MediaLaunchObject {
        public LaunchSession launchSession;
        public MediaControl mediaControl;
        public PlaylistControl playlistControl;

        public MediaLaunchObject(LaunchSession launchSession, MediaControl mediaControl) {
            this.launchSession = launchSession;
            this.mediaControl = mediaControl;
        }

        public MediaLaunchObject(LaunchSession launchSession, MediaControl mediaControl, PlaylistControl playlistControl) {
            this.launchSession = launchSession;
            this.mediaControl = mediaControl;
            this.playlistControl = playlistControl;
        }
    }
}
