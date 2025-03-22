package com.connectsdk.service;

import com.amazon.whisperplay.fling.media.controller.RemoteMediaPlayer;
import com.amazon.whisperplay.fling.media.service.CustomMediaPlayer;
import com.amazon.whisperplay.fling.media.service.MediaPlayerInfo;
import com.amazon.whisperplay.fling.media.service.MediaPlayerStatus;
import com.connectsdk.core.ImageInfo;
import com.connectsdk.core.MediaInfo;
import com.connectsdk.core.Util;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.service.capability.CapabilityMethods;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.FireTVServiceError;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.sessions.LaunchSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FireTVService extends DeviceService implements MediaPlayer, MediaControl {

    public static final String ID = "FireTV";
    private static final String META_ICON_IMAGE = "poster";
    private PlayStateSubscription playStateSubscription;
    private final RemoteMediaPlayer remoteMediaPlayer;
    
    public interface ConvertResult<Response, Result> {
        Response convert(Result data) throws Exception;
    }

    @Override 
    public MediaControl getMediaControl() {
        return this;
    }

    @Override 
    public MediaPlayer getMediaPlayer() {
        return this;
    }

    @Override 
    public boolean isConnectable() {
        return true;
    }

    public FireTVService(ServiceDescription serviceDescription, ServiceConfig serviceConfig) {
        super(serviceDescription, serviceConfig);
        if (serviceDescription != null && (serviceDescription.getDevice() instanceof RemoteMediaPlayer)) {
            this.remoteMediaPlayer = (RemoteMediaPlayer) serviceDescription.getDevice();
        } else {
            this.remoteMediaPlayer = null;
        }
    }

    public static DiscoveryFilter discoveryFilter() {
        return new DiscoveryFilter(ID, ID);
    }

    @Override 
    public void connect() {
        super.connect();
        if (this.remoteMediaPlayer != null) {
            this.connected = true;
            reportConnected(this.connected);
        }
    }

    @Override 
    public boolean isConnected() {
        return this.connected;
    }

    @Override 
    public void disconnect() {
        super.disconnect();
        PlayStateSubscription playStateSubscription = this.playStateSubscription;
        if (playStateSubscription != null) {
            playStateSubscription.unsubscribe();
            this.playStateSubscription = null;
        }
        this.connected = false;
    }

    @Override 
    protected void updateCapabilities() {
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(MediaPlayer.MediaInfo_Get);
        arrayList.add(MediaPlayer.Display_Image);
        arrayList.add("MediaPlayer.Play.Audio");
        arrayList.add("MediaPlayer.Play.Video");
        arrayList.add(MediaPlayer.Close);
        arrayList.add(MediaPlayer.MetaData_MimeType);
        arrayList.add(MediaPlayer.MetaData_Thumbnail);
        arrayList.add(MediaPlayer.MetaData_Title);
        arrayList.add(MediaPlayer.Subtitle_WebVTT);
        arrayList.add(MediaControl.Play);
        arrayList.add(MediaControl.Pause);
        arrayList.add(MediaControl.Stop);
        arrayList.add(MediaControl.Seek);
        arrayList.add(MediaControl.Duration);
        arrayList.add(MediaControl.Position);
        arrayList.add(MediaControl.PlayState);
        arrayList.add(MediaControl.PlayState_Subscribe);
        setCapabilities(arrayList);
    }

    @Override 
    public CapabilityPriorityLevel getPriorityLevel(Class<? extends CapabilityMethods> clazz) {
        if (clazz != null) {
            if (clazz.equals(MediaPlayer.class)) {
                return getMediaPlayerCapabilityLevel();
            }
            if (clazz.equals(MediaControl.class)) {
                return getMediaControlCapabilityLevel();
            }
        }
        return CapabilityPriorityLevel.NOT_SUPPORTED;
    }

    @Override 
    public CapabilityPriorityLevel getMediaPlayerCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override 
    public void getMediaInfo(final MediaInfoListener listener) {
        try {
            handleAsyncFutureWithConversion(listener, this.remoteMediaPlayer.getMediaInfo(), new ConvertResult<MediaInfo, MediaPlayerInfo>() {
                @Override
                public MediaInfo convert(MediaPlayerInfo data) throws JSONException {
                    ArrayList arrayList;
                    JSONObject jSONObject = new JSONObject(data.getMetadata());
                    if (jSONObject.has(FireTVService.META_ICON_IMAGE)) {
                        arrayList = new ArrayList<>();
                        arrayList.add(new ImageInfo(jSONObject.getString(FireTVService.META_ICON_IMAGE)));
                    } else {
                        arrayList = null;
                    }
                    return new MediaInfo(data.getSource(), jSONObject.getString("type"), jSONObject.getString("title"), jSONObject.getString("description"), arrayList);
                }
            }, "Error getting media info");
        } catch (Exception exception) {
            Util.postError(listener, new FireTVServiceError("Error getting media info"));
        }
    }

    @Override 
    public ServiceSubscription<MediaInfoListener> subscribeMediaInfo(MediaInfoListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return null;
    }

    @Override 
    public void displayImage(String url, String mimeType, String title, String description, String iconSrc, final LaunchListener listener) {
        setMediaSource(new MediaInfo.Builder(url, mimeType).setTitle(title).setDescription(description).setIcon(iconSrc).build(), listener);
    }

    @Override 
    public void playMedia(String url, String mimeType, String title, String description, String iconSrc, boolean shouldLoop, LaunchListener listener) {
        setMediaSource(new MediaInfo.Builder(url, mimeType).setTitle(title).setDescription(description).setIcon(iconSrc).build(), listener);
    }

    @Override 
    public void closeMedia(LaunchSession launchSession, final ResponseListener<Object> listener) {
        stop(listener);
    }

    @Override 
    public void displayImage(MediaInfo mediaInfo, LaunchListener listener) {
        setMediaSource(mediaInfo, listener);
    }

    @Override 
    public void playMedia(MediaInfo mediaInfo, boolean shouldLoop, LaunchListener listener) {
        setMediaSource(mediaInfo, listener);
    }

    @Override 
    public CapabilityPriorityLevel getMediaControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override 
    public void play(ResponseListener<Object> listener) {
        try {
            handleVoidAsyncFuture(listener, this.remoteMediaPlayer.play(), "Error playing");
        } catch (Exception e) {
            Util.postError(listener, new FireTVServiceError("Error playing", e));
        }
    }

    @Override 
    public void pause(ResponseListener<Object> listener) {
        try {
            handleVoidAsyncFuture(listener, this.remoteMediaPlayer.pause(), "Error pausing");
        } catch (Exception e) {
            Util.postError(listener, new FireTVServiceError("Error pausing", e));
        }
    }

    @Override 
    public void stop(ResponseListener<Object> listener) {
        try {
            handleVoidAsyncFuture(listener, this.remoteMediaPlayer.stop(), "Error stopping");
        } catch (Exception e) {
            Util.postError(listener, new FireTVServiceError("Error stopping", e));
        }
    }

    @Override 
    public void rewind(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override 
    public void fastForward(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override 
    public void previous(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override 
    public void next(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override 
    public void seek(long position, ResponseListener<Object> listener) {
        try {
            handleVoidAsyncFuture(listener, this.remoteMediaPlayer.seek(CustomMediaPlayer.PlayerSeekMode.Absolute, position), "Error seeking");
        } catch (Exception e) {
            Util.postError(listener, new FireTVServiceError("Error seeking", e));
        }
    }

    @Override 
    public void getDuration(final DurationListener listener) {
        try {
            handleAsyncFuture(listener, this.remoteMediaPlayer.getDuration(), "Error getting duration");
        } catch (Exception e) {
            Util.postError(listener, new FireTVServiceError("Error getting duration", e));
        }
    }

    @Override 
    public void getPosition(final PositionListener listener) {
        try {
            handleAsyncFuture(listener, this.remoteMediaPlayer.getPosition(), "Error getting position");
        } catch (Exception e) {
            Util.postError(listener, new FireTVServiceError("Error getting position", e));
        }
    }

    @Override 
    public void getPlayState(final PlayStateListener listener) {
        try {
            handleAsyncFutureWithConversion(listener, this.remoteMediaPlayer.getStatus(), new ConvertResult<PlayStateStatus, MediaPlayerStatus>() {
                @Override
                public PlayStateStatus convert(MediaPlayerStatus data) {
                    return FireTVService.this.createPlayStateStatusFromFireTVStatus(data);
                }
            }, "Error getting play state");
        } catch (Exception e) {
            Util.postError(listener, new FireTVServiceError("Error getting play state", e));
        }
    }

    @Override 
    public ServiceSubscription<PlayStateListener> subscribePlayState(final PlayStateListener listener) {
        PlayStateSubscription playStateSubscription = this.playStateSubscription;
        if (playStateSubscription == null) {
            PlayStateSubscription playStateSubscription2 = new PlayStateSubscription(listener);
            this.playStateSubscription = playStateSubscription2;
            this.remoteMediaPlayer.addStatusListener(playStateSubscription2);
        } else if (!playStateSubscription.getListeners().contains(listener)) {
            this.playStateSubscription.addListener(listener);
        }
        getPlayState(listener);
        return this.playStateSubscription;
    }

    PlayStateStatus createPlayStateStatusFromFireTVStatus(MediaPlayerStatus status) {
        PlayStateStatus playState = PlayStateStatus.Unknown;
        if (status != null) {
            switch (status.getState()) {
                case PreparingMedia:
                    playState = PlayStateStatus.Buffering;
                    break;
                case Playing:
                    playState = PlayStateStatus.Playing;
                    break;
                case Paused:
                    playState = PlayStateStatus.Paused;
                    break;
                case Finished:
                    playState = PlayStateStatus.Finished;
                    break;
                case NoSource:
                    playState = PlayStateStatus.Idle;
            }
        }
        return playState;
    }

    private String getMetadata(MediaInfo mediaInfo) throws JSONException {
        ImageInfo imageInfo;
        JSONObject jSONObject = new JSONObject();
        if (mediaInfo.getTitle() != null && !mediaInfo.getTitle().isEmpty()) {
            jSONObject.put("title", mediaInfo.getTitle());
        }
        if (mediaInfo.getDescription() != null && !mediaInfo.getDescription().isEmpty()) {
            jSONObject.put("description", mediaInfo.getDescription());
        }
        jSONObject.put("type", mediaInfo.getMimeType());
        if (mediaInfo.getImages() != null && mediaInfo.getImages().size() > 0 && (imageInfo = mediaInfo.getImages().get(0)) != null && imageInfo.getUrl() != null && !imageInfo.getUrl().isEmpty()) {
            jSONObject.put(META_ICON_IMAGE, imageInfo.getUrl());
        }
        jSONObject.put("noreplay", true);
        if (mediaInfo.getSubtitleInfo() != null) {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("kind", "subtitles");
            jSONObject2.put("src", mediaInfo.getSubtitleInfo().getUrl());
            String label = mediaInfo.getSubtitleInfo().getLabel();
            if (label == null) {
                label = "";
            }
            jSONObject2.put("label", label);
            String language = mediaInfo.getSubtitleInfo().getLanguage();
            jSONObject2.put("srclang", language != null ? language : "");
            jSONArray.put(jSONObject2);
            jSONObject.put("tracks", jSONArray);
        }
        return jSONObject.toString();
    }

    
    public MediaLaunchObject createMediaLaunchObject() {
        LaunchSession launchSession = new LaunchSession();
        launchSession.setService(this);
        launchSession.setSessionType(LaunchSession.LaunchSessionType.Media);
        launchSession.setAppId(this.remoteMediaPlayer.getUniqueIdentifier());
        launchSession.setAppName(this.remoteMediaPlayer.getName());
        return new MediaLaunchObject(launchSession, this);
    }

    private void setMediaSource(MediaInfo mediaInfo, final LaunchListener listener) {
        try {
            handleAsyncFutureWithConversion(listener, this.remoteMediaPlayer.setMediaSource(mediaInfo.getUrl(), getMetadata(mediaInfo), true, false), new ConvertResult<MediaLaunchObject, Void>() {
                @Override
                public MediaLaunchObject convert(Void data) {
                    return FireTVService.this.createMediaLaunchObject();
                }
            }, "Error setting media source");
        } catch (Exception e) {
            Util.postError(listener, new FireTVServiceError("Error setting media source", e));
        }
    }

    private void handleVoidAsyncFuture(final ResponseListener<Object> listener, final RemoteMediaPlayer.AsyncFuture<Void> asyncFuture, final String errorMessage) {
        handleAsyncFutureWithConversion(listener, asyncFuture, new ConvertResult<Object, Void>() {
            @Override
            public Object convert(Void data) {
                return data;
            }
        }, errorMessage);
    }

    private <T> void handleAsyncFuture(final ResponseListener<T> listener, final RemoteMediaPlayer.AsyncFuture<T> asyncFuture, final String errorMessage) {
        handleAsyncFutureWithConversion(listener, asyncFuture, new ConvertResult<T, T>() {
            @Override
            public T convert(T data) {
                return data;
            }
        }, errorMessage);
    }

    private <Response, Result> void handleAsyncFutureWithConversion(final ResponseListener<Response> listener, final RemoteMediaPlayer.AsyncFuture<Result> asyncFuture, final ConvertResult<Response, Result> conversion, final String errorMessage) {
        if (asyncFuture != null) {
            asyncFuture.getAsync(new RemoteMediaPlayer.FutureListener<Result>() {
                @Override
                public void futureIsNow(Future<Result> future) {
                    try {
                        Util.postSuccess(listener, conversion.convert(future.get()));
                    } catch (ExecutionException e) {
                        Util.postError(listener, new FireTVServiceError(errorMessage, e.getCause()));
                    } catch (Exception e2) {
                        Util.postError(listener, new FireTVServiceError(errorMessage, e2));
                    }
                }
            });
        } else {
            Util.postError(listener, new FireTVServiceError(errorMessage));
        }
    }

    
    private static abstract class Subscription<Status, Listener extends ResponseListener<Status>> implements ServiceSubscription<Listener> {
        List<Listener> listeners;
        Status prevStatus;
        public Subscription(Listener listener) {
            ArrayList arrayList = new ArrayList<>();
            this.listeners = arrayList;
            if (listener != null) {
                arrayList.add(listener);
            }
        }

        synchronized void notifyListeners(final Status status) {
            if (!status.equals(this.prevStatus)) {
                Util.runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        for (Listener listener : Subscription.this.listeners) {
                            listener.onSuccess(status);
                        }
                    }
                });
                this.prevStatus = status;
            }
        }

        public Listener addListener(Listener listener) {
            if (listener != null) {
                this.listeners.add(listener);
            }
            return listener;
        }

        public void removeListener(Listener listener) {
            this.listeners.remove(listener);
        }

        @Override
        public List<Listener> getListeners() {
            return this.listeners;
        }
    }

    
    class PlayStateSubscription extends Subscription<PlayStateStatus, PlayStateListener> implements CustomMediaPlayer.StatusListener {
        public PlayStateSubscription(PlayStateListener listener) {
            super(listener);
        }

        @Override
        public void onStatusChange(MediaPlayerStatus mediaPlayerStatus, long position) {
            notifyListeners(FireTVService.this.createPlayStateStatusFromFireTVStatus(mediaPlayerStatus));
        }

        @Override
        public void unsubscribe() {
            FireTVService.this.remoteMediaPlayer.removeStatusListener(this);
            FireTVService.this.playStateSubscription = null;
        }
    }
}
