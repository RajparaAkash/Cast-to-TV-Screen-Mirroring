package com.connectsdk.service.capability;

import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceSubscription;


public interface MediaControl extends CapabilityMethods {
    String Any = "MediaControl.Any";
    int PLAYER_STATE_BUFFERING = 4;
    String Play = "MediaControl.Play";
    String Pause = "MediaControl.Pause";
    String Stop = "MediaControl.Stop";
    String Rewind = "MediaControl.Rewind";
    String FastForward = "MediaControl.FastForward";
    String Seek = "MediaControl.Seek";
    @Deprecated
    String Previous = "MediaControl.Previous";
    @Deprecated
    String Next = "MediaControl.Next";
    String Duration = "MediaControl.Duration";
    String PlayState = "MediaControl.PlayState";
    String PlayState_Subscribe = "MediaControl.PlayState.Subscribe";
    String Position = "MediaControl.Position";
    String[] Capabilities = {Play, Pause, Stop, Rewind, FastForward, Seek, Previous, Next, Duration, PlayState, PlayState_Subscribe, Position};


    interface DurationListener extends ResponseListener<Long> {
    }


    interface PlayStateListener extends ResponseListener<PlayStateStatus> {
    }


    interface PositionListener extends ResponseListener<Long> {
    }

    void fastForward(ResponseListener<Object> listener);

    void getDuration(DurationListener listener);

    MediaControl getMediaControl();

    CapabilityPriorityLevel getMediaControlCapabilityLevel();

    void getPlayState(PlayStateListener listener);

    void getPosition(PositionListener listener);

    @Deprecated
    void next(ResponseListener<Object> listener);

    void pause(ResponseListener<Object> listener);

    void play(ResponseListener<Object> listener);

    @Deprecated
    void previous(ResponseListener<Object> listener);

    void rewind(ResponseListener<Object> listener);

    void seek(long position, ResponseListener<Object> listener);

    void stop(ResponseListener<Object> listener);

    ServiceSubscription<PlayStateListener> subscribePlayState(PlayStateListener listener);


    enum PlayStateStatus {
        Unknown,
        Idle,
        Playing,
        Paused,
        Buffering,
        Finished;

        public static PlayStateStatus convertPlayerStateToPlayStateStatus(int playerState) {
            if (playerState != 1) {
                if (playerState != 2) {
                    if (playerState != 3) {
                        return playerState != 4 ? Unknown : Buffering;
                    }
                    return Paused;
                }
                return Playing;
            }
            return Finished;
        }

        public static PlayStateStatus convertTransportStateToPlayStateStatus(String transportState) {
            PlayStateStatus playStateStatus = Unknown;
            if (transportState.equals("STOPPED")) {
                return Finished;
            }
            if (transportState.equals("PLAYING")) {
                return Playing;
            }
            if (transportState.equals("TRANSITIONING")) {
                return Buffering;
            }
            if (transportState.equals("PAUSED_PLAYBACK")) {
                return Paused;
            }
            if (transportState.equals("PAUSED_RECORDING") || transportState.equals("RECORDING")) {
                return playStateStatus;
            }
            transportState.equals("NO_MEDIA_PRESENT");
            return playStateStatus;
        }
    }
}
