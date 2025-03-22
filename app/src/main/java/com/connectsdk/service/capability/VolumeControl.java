package com.connectsdk.service.capability;

import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceSubscription;


public interface VolumeControl extends CapabilityMethods {
    public static final String Any = "VolumeControl.Any";
    public static final String Volume_Get = "VolumeControl.Get";
    public static final String Volume_Set = "VolumeControl.Set";
    public static final String Volume_Up_Down = "VolumeControl.UpDown";
    public static final String Volume_Subscribe = "VolumeControl.Subscribe";
    public static final String Mute_Get = "VolumeControl.Mute.Get";
    public static final String Mute_Set = "VolumeControl.Mute.Set";
    public static final String Mute_Subscribe = "VolumeControl.Mute.Subscribe";
    public static final String[] Capabilities = {Volume_Get, Volume_Set, Volume_Up_Down, Volume_Subscribe, Mute_Get, Mute_Set, Mute_Subscribe};


    public interface MuteListener extends ResponseListener<Boolean> {
    }


    public interface VolumeListener extends ResponseListener<Float> {
    }


    public interface VolumeStatusListener extends ResponseListener<VolumeStatus> {
    }

    void getMute(MuteListener listener);

    void getVolume(VolumeListener listener);

    VolumeControl getVolumeControl();

    CapabilityPriorityLevel getVolumeControlCapabilityLevel();

    void setMute(boolean isMute, ResponseListener<Object> listener);

    void setVolume(float volume, ResponseListener<Object> listener);

    ServiceSubscription<MuteListener> subscribeMute(MuteListener listener);

    ServiceSubscription<VolumeListener> subscribeVolume(VolumeListener listener);

    void volumeDown(ResponseListener<Object> listener);

    void volumeUp(ResponseListener<Object> listener);


    public static class VolumeStatus {
        public boolean isMute;
        public float volume;

        public VolumeStatus(boolean isMute, float volume) {
            this.isMute = isMute;
            this.volume = volume;
        }
    }
}
