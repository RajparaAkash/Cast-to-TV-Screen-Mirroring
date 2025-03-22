package com.connectsdk.service.sessions;

import com.connectsdk.core.MediaInfo;
import com.connectsdk.core.Util;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.PlaylistControl;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;

import org.json.JSONObject;


public class WebAppSession implements MediaControl, MediaPlayer, PlaylistControl {
    public LaunchSession launchSession;
    protected DeviceService service;
    private WebAppSessionListener webAppListener;

    
    public interface LaunchListener extends ResponseListener<WebAppSession> {
    }

    
    public interface MessageListener extends ResponseListener<Object> {
        void onMessage(Object message);
    }

    
    public interface StatusListener extends ResponseListener<WebAppStatus> {
    }

    
    public interface WebAppPinStatusListener extends ResponseListener<Boolean> {
    }

    
    public enum WebAppStatus {
        Unknown,
        Open,
        Background,
        Foreground,
        Closed
    }

    public void disconnectFromWebApp() {
    }

    @Override 
    public MediaControl getMediaControl() {
        return null;
    }

    public MediaPlayer getMediaPlayer() {
        return null;
    }

    @Override 
    public PlaylistControl getPlaylistControl() {
        return null;
    }

    protected void setService(DeviceService service) {
    }

    public WebAppSession(LaunchSession launchSession, DeviceService service) {
        this.launchSession = launchSession;
        this.service = service;
    }

    public ServiceSubscription<MessageListener> subscribeWebAppStatus(MessageListener listener) {
        if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
            return null;
        }
        return null;
    }

    public void connect(ResponseListener<Object> connectionListener) {
        Util.postError(connectionListener, ServiceCommandError.notSupported());
    }

    public void join(ResponseListener<Object> connectionListener) {
        Util.postError(connectionListener, ServiceCommandError.notSupported());
    }

    public void pinWebApp(String webAppId, ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    public void unPinWebApp(String webAppId, ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    public void isWebAppPinned(String webAppId, WebAppPinStatusListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    public ServiceSubscription<WebAppPinStatusListener> subscribeIsWebAppPinned(String webAppId, WebAppPinStatusListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return null;
    }

    public void close(ResponseListener<Object> listener) {
        if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    public void sendMessage(String message, ResponseListener<Object> listener) {
        if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    public void sendMessage(JSONObject message, ResponseListener<Object> listener) {
        if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    @Override 
    public CapabilityPriorityLevel getMediaControlCapabilityLevel() {
        return CapabilityPriorityLevel.VERY_LOW;
    }

    @Override 
    public void getMediaInfo(MediaInfoListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override 
    public ServiceSubscription<MediaInfoListener> subscribeMediaInfo(MediaInfoListener listener) {
        listener.onError(ServiceCommandError.notSupported());
        return null;
    }

    @Override 
    public void play(ResponseListener<Object> listener) {
        DeviceService deviceService = this.service;
        MediaControl mediaControl = deviceService != null ? (MediaControl) deviceService.getAPI(MediaControl.class) : null;
        if (mediaControl != null) {
            mediaControl.play(listener);
        } else if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    @Override 
    public void pause(ResponseListener<Object> listener) {
        DeviceService deviceService = this.service;
        MediaControl mediaControl = deviceService != null ? (MediaControl) deviceService.getAPI(MediaControl.class) : null;
        if (mediaControl != null) {
            mediaControl.pause(listener);
        } else if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    @Override 
    public void stop(ResponseListener<Object> listener) {
        DeviceService deviceService = this.service;
        MediaControl mediaControl = deviceService != null ? (MediaControl) deviceService.getAPI(MediaControl.class) : null;
        if (mediaControl != null) {
            mediaControl.stop(listener);
        } else if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    @Override 
    public void rewind(ResponseListener<Object> listener) {
        DeviceService deviceService = this.service;
        MediaControl mediaControl = deviceService != null ? (MediaControl) deviceService.getAPI(MediaControl.class) : null;
        if (mediaControl != null) {
            mediaControl.rewind(listener);
        } else if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    @Override 
    public void fastForward(ResponseListener<Object> listener) {
        DeviceService deviceService = this.service;
        MediaControl mediaControl = deviceService != null ? (MediaControl) deviceService.getAPI(MediaControl.class) : null;
        if (mediaControl != null) {
            mediaControl.fastForward(listener);
        } else if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    @Override 
    public void previous(ResponseListener<Object> listener) {
        DeviceService deviceService = this.service;
        MediaControl mediaControl = deviceService != null ? (MediaControl) deviceService.getAPI(MediaControl.class) : null;
        if (mediaControl != null) {
            mediaControl.previous(listener);
        } else if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    @Override 
    public void next(ResponseListener<Object> listener) {
        DeviceService deviceService = this.service;
        MediaControl mediaControl = deviceService != null ? (MediaControl) deviceService.getAPI(MediaControl.class) : null;
        if (mediaControl != null) {
            mediaControl.next(listener);
        } else if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    @Override 
    public void seek(long position, ResponseListener<Object> listener) {
        DeviceService deviceService = this.service;
        MediaControl mediaControl = deviceService != null ? (MediaControl) deviceService.getAPI(MediaControl.class) : null;
        if (mediaControl != null) {
            mediaControl.seek(position, listener);
        } else if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    @Override 
    public void getDuration(DurationListener listener) {
        DeviceService deviceService = this.service;
        MediaControl mediaControl = deviceService != null ? (MediaControl) deviceService.getAPI(MediaControl.class) : null;
        if (mediaControl != null) {
            mediaControl.getDuration(listener);
        } else if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    @Override 
    public void getPosition(PositionListener listener) {
        DeviceService deviceService = this.service;
        MediaControl mediaControl = deviceService != null ? (MediaControl) deviceService.getAPI(MediaControl.class) : null;
        if (mediaControl != null) {
            mediaControl.getPosition(listener);
        } else if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    @Override 
    public void getPlayState(PlayStateListener listener) {
        DeviceService deviceService = this.service;
        MediaControl mediaControl = deviceService != null ? (MediaControl) deviceService.getAPI(MediaControl.class) : null;
        if (mediaControl != null) {
            mediaControl.getPlayState(listener);
        } else if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
    }

    @Override 
    public ServiceSubscription<PlayStateListener> subscribePlayState(PlayStateListener listener) {
        DeviceService deviceService = this.service;
        MediaControl mediaControl = deviceService != null ? (MediaControl) deviceService.getAPI(MediaControl.class) : null;
        if (mediaControl != null) {
            return mediaControl.subscribePlayState(listener);
        }
        if (listener != null) {
            listener.onError(ServiceCommandError.notSupported());
        }
        return null;
    }

    public void closeMedia(LaunchSession launchSession, ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override 
    public void displayImage(String url, String mimeType, String title, String description, String iconSrc, MediaPlayer.LaunchListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override 
    public void displayImage(MediaInfo mediaInfo, MediaPlayer.LaunchListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    public void playMedia(String url, String mimeType, String title, String description, String iconSrc, boolean shouldLoop, MediaPlayer.LaunchListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override 
    public void playMedia(MediaInfo mediaInfo, boolean shouldLoop, MediaPlayer.LaunchListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    public CapabilityPriorityLevel getMediaPlayerCapabilityLevel() {
        return CapabilityPriorityLevel.VERY_LOW;
    }

    @Override 
    public CapabilityPriorityLevel getPlaylistControlCapabilityLevel() {
        return CapabilityPriorityLevel.VERY_LOW;
    }

    @Override 
    public void jumpToTrack(long index, ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override 
    public void setPlayMode(PlayMode playMode, ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    public WebAppSessionListener getWebAppSessionListener() {
        return this.webAppListener;
    }

    public void setWebAppSessionListener(WebAppSessionListener listener) {
        this.webAppListener = listener;
    }
}
