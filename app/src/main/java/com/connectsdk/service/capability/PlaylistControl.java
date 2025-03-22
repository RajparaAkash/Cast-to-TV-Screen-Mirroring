package com.connectsdk.service.capability;

import com.connectsdk.service.capability.listeners.ResponseListener;


public interface PlaylistControl extends CapabilityMethods {
    public static final String Any = "PlaylistControl.Any";
    public static final String Previous = "PlaylistControl.Previous";
    public static final String Next = "PlaylistControl.Next";
    public static final String JumpToTrack = "PlaylistControl.JumpToTrack";
    public static final String SetPlayMode = "PlaylistControl.SetPlayMode";
    public static final String[] Capabilities = {Previous, Next, JumpToTrack, SetPlayMode, JumpToTrack};


    public enum PlayMode {
        Normal,
        Shuffle,
        RepeatOne,
        RepeatAll
    }

    PlaylistControl getPlaylistControl();

    CapabilityPriorityLevel getPlaylistControlCapabilityLevel();

    void jumpToTrack(long index, ResponseListener<Object> listener);

    void next(ResponseListener<Object> listener);

    void previous(ResponseListener<Object> listener);

    void setPlayMode(PlayMode playMode, ResponseListener<Object> listener);
}
