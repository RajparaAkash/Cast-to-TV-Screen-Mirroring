package com.connectsdk.service;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.connectsdk.core.ImageInfo;
import com.connectsdk.core.MediaInfo;
import com.connectsdk.core.SubtitleInfo;
import com.connectsdk.core.Util;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.capability.CapabilityMethods;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.VolumeControl;
import com.connectsdk.service.capability.WebAppLauncher;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.command.URLServiceSubscription;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.sessions.CastWebAppSession;
import com.connectsdk.service.sessions.LaunchSession;
import com.connectsdk.service.sessions.WebAppSession;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.images.WebImage;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

public class CastService extends DeviceService implements MediaPlayer, MediaControl, VolumeControl, WebAppLauncher {
    public static final String CAST_SERVICE_MUTE_SUBSCRIPTION_NAME = "mute";
    public static final String CAST_SERVICE_VOLUME_SUBSCRIPTION_NAME = "volume";
    public static final String ID = "AndroidTV2";
    //    public static final String ID = "Chromecast";
    public static final String PLAY_STATE = "PlayState";
    private static final long MEDIA_TRACK_ID = 1;
    static String applicationID = "CC1AD845";
    CastDevice castDevice;
    CopyOnWriteArraySet<ConnectionListener> commandQueue = new CopyOnWriteArraySet<>();
    String currentAppId;
    boolean currentMuteStatus;
    float currentVolumeLevel;
    String launchingAppId;
    GoogleApiClient mApiClient;
    CastClient mCastClient = new CastClient();
    CastListener mCastClientListener = new CastListener();
    ConnectionCallbacks mConnectionCallbacks = new ConnectionCallbacks();
    ConnectionFailedListener mConnectionFailedListener = new ConnectionFailedListener();
    RemoteMediaPlayer mMediaPlayer;
    boolean mWaitingForReconnect = false;
    Map<String, CastWebAppSession> sessions = new HashMap();
    List<URLServiceSubscription<?>> subscriptions = new ArrayList();

    public CastService(ServiceDescription serviceDescription, ServiceConfig serviceConfig) {
        super(serviceDescription, serviceConfig);
    }

    public static DiscoveryFilter discoveryFilter() {
        return new DiscoveryFilter(ID, "_androidtvremote2._tcp.local.");
//        return new DiscoveryFilter(ID, ID);
    }

    public static String getApplicationID() {
        return applicationID;
    }

    public static void setApplicationID(String str) {
        applicationID = str;
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
    public String getServiceName() {
        return ID;
    }

    @Override
    public VolumeControl getVolumeControl() {
        return this;
    }

    @Override
    public WebAppLauncher getWebAppLauncher() {
        return this;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public CapabilityPriorityLevel getPriorityLevel(Class<? extends CapabilityMethods> cls) {
        if (cls.equals(MediaPlayer.class)) {
            return getMediaPlayerCapabilityLevel();
        }
        if (cls.equals(MediaControl.class)) {
            return getMediaControlCapabilityLevel();
        }
        if (cls.equals(VolumeControl.class)) {
            return getVolumeControlCapabilityLevel();
        }
        if (cls.equals(WebAppLauncher.class)) {
            return getWebAppLauncherCapabilityLevel();
        }
        return CapabilityPriorityLevel.NOT_SUPPORTED;
    }

    @Override
    public void connect() {
        if (castDevice == null) {
            castDevice = (CastDevice) getServiceDescription().getDevice();
        }
        if (mApiClient == null) {
            mApiClient = createApiClient();
        }
        if (!mApiClient.isConnecting() && !mApiClient.isConnected()) {
            mApiClient.connect();
        }
    }


    public GoogleApiClient createApiClient() {
        return new GoogleApiClient.Builder(DiscoveryManager.getInstance().getContext())
                .addApi(Cast.API, Cast.CastOptions.builder(castDevice, mCastClientListener).build())
                .addConnectionCallbacks(mConnectionCallbacks).addOnConnectionFailedListener(mConnectionFailedListener).build();
    }

    @Override
    public void disconnect() {
        mWaitingForReconnect = false;
        detachMediaPlayer();
        if (!commandQueue.isEmpty()) {
            commandQueue.clear();
        }
        if (mApiClient != null && mApiClient.isConnected()) {
            try {
                mCastClient.leaveApplication(mApiClient);
            } catch (CastClientException e) {
                Log.e(Util.T, "Closing application error", e);
            }
            mApiClient.disconnect();
        }
        if (connected) {
            Util.runOnUI(new Runnable() {

                public void run() {
                    if (getListener() != null) {
                        getListener().onDisconnect(CastService.this, null);
                    }
                }
            });
        }
        this.connected = false;
        this.mApiClient = null;
    }

    @Override
    public CapabilityPriorityLevel getMediaControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void play(final ResponseListener<Object> responseListener) {
        runCommand(new ConnectionListener() {

            @Override
            public void onConnected() {
                try {
                    mMediaPlayer.play(mApiClient);
                    Util.postSuccess(responseListener, null);
                } catch (Exception unused) {
                    Util.postError(responseListener, new ServiceCommandError(0, "Unable to play", null));
                }
            }
        });
    }

    @Override
    public void pause(final ResponseListener<Object> responseListener) {
        runCommand(new ConnectionListener() {

            @Override
            public void onConnected() {
                try {
                    mMediaPlayer.pause(mApiClient);
                    Util.postSuccess(responseListener, null);
                } catch (Exception unused) {
                    Util.postError(responseListener, new ServiceCommandError(0, "Unable to pause", null));
                }
            }
        });
    }

    @Override
    public void stop(final ResponseListener<Object> responseListener) {
        runCommand(new ConnectionListener() {

            @Override
            public void onConnected() {
                try {
                    mMediaPlayer.stop(mApiClient);
                    Util.postSuccess(responseListener, null);
                } catch (Exception unused) {
                    Util.postError(responseListener, new ServiceCommandError(0, "Unable to stop", null));
                }
            }
        });
    }

    @Override
    public void rewind(ResponseListener<Object> responseListener) {
        Util.postError(responseListener, ServiceCommandError.notSupported());
    }

    @Override
    public void fastForward(ResponseListener<Object> responseListener) {
        Util.postError(responseListener, ServiceCommandError.notSupported());
    }

    @Override
    public void previous(ResponseListener<Object> responseListener) {
        Util.postError(responseListener, ServiceCommandError.notSupported());
    }

    @Override
    public void next(ResponseListener<Object> responseListener) {
        Util.postError(responseListener, ServiceCommandError.notSupported());
    }

    @Override
    public void seek(final long j, final ResponseListener<Object> responseListener) {
        if (mMediaPlayer == null || mMediaPlayer.getMediaStatus() == null) {
            Util.postError(responseListener, new ServiceCommandError(0, "There is no media currently available", null));
        } else {
            runCommand(new ConnectionListener() {

                @Override
                public void onConnected() {
                    try {
                        mMediaPlayer.seek(mApiClient, j, 0).setResultCallback(new ResultCallback<RemoteMediaPlayer.MediaChannelResult>() {

                            public void onResult(RemoteMediaPlayer.MediaChannelResult mediaChannelResult) {
                                Status status = mediaChannelResult.getStatus();
                                if (status.isSuccess()) {
                                    Util.postSuccess(responseListener, null);
                                } else {
                                    Util.postError(responseListener, new ServiceCommandError(status.getStatusCode(), status.getStatusMessage(), status));
                                }
                            }
                        });
                    } catch (Exception unused) {
                        Util.postError(responseListener, new ServiceCommandError(0, "Unable to seek", null));
                    }
                }
            });
        }
    }

    @Override
    public void getDuration(DurationListener durationListener) {
        if (mMediaPlayer == null || mMediaPlayer.getMediaStatus() == null) {
            Util.postError(durationListener, new ServiceCommandError(0, "There is no media currently available", null));
        } else {
            Util.postSuccess(durationListener, Long.valueOf(mMediaPlayer.getStreamDuration()));
        }
    }

    @Override
    public void getPosition(PositionListener positionListener) {
        if (mMediaPlayer == null || mMediaPlayer.getMediaStatus() == null) {
            Util.postError(positionListener, new ServiceCommandError(0, "There is no media currently available", null));
        } else {
            Util.postSuccess(positionListener, Long.valueOf(mMediaPlayer.getApproximateStreamPosition()));
        }
    }

    @Override
    public CapabilityPriorityLevel getMediaPlayerCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void getMediaInfo(MediaInfoListener mediaInfoListener) {
        ArrayList arrayList;
        String str;
        String str2;
        if (mMediaPlayer != null) {
            ArrayList arrayList2 = null;
            if (mMediaPlayer.getMediaInfo() != null) {
                String contentId = mMediaPlayer.getMediaInfo().getContentId();
                String contentType = mMediaPlayer.getMediaInfo().getContentType();
                MediaMetadata metadata = mMediaPlayer.getMediaInfo().getMetadata();
                if (metadata != null) {
                    String string = metadata.getString(MediaMetadata.KEY_TITLE);
                    String string2 = metadata.getString(MediaMetadata.KEY_SUBTITLE);
                    if (metadata.getImages() != null && metadata.getImages().size() > 0) {
                        String uri = metadata.getImages().get(0).getUrl().toString();
                        arrayList2 = new ArrayList();
                        arrayList2.add(new ImageInfo(uri));
                    }
                    arrayList = arrayList2;
                    str = string2;
                    str2 = string;
                } else {
                    str2 = null;
                    str = null;
                    arrayList = null;
                }
                Util.postSuccess(mediaInfoListener, new MediaInfo(contentId, contentType, str2, str, arrayList));
                return;
            }
            Util.postError(mediaInfoListener, new ServiceCommandError(0, "Media Info is null", null));
        }
    }

    @Override
    public ServiceSubscription<MediaInfoListener> subscribeMediaInfo(MediaInfoListener mediaInfoListener) {
        URLServiceSubscription uRLServiceSubscription = new URLServiceSubscription<>(this, "info", null, null);
        uRLServiceSubscription.addListener(mediaInfoListener);
        addSubscription(uRLServiceSubscription);
        return uRLServiceSubscription;
    }


    private void attachMediaPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = createMediaPlayer();
            mMediaPlayer.setOnStatusUpdatedListener(new RemoteMediaPlayer.OnStatusUpdatedListener() {

                @Override
                public void onStatusUpdated() {
                    if (subscriptions.size() > 0) {
                        for (URLServiceSubscription<?> uRLServiceSubscription : subscriptions) {
                            if (uRLServiceSubscription.getTarget().equalsIgnoreCase(CastService.PLAY_STATE)) {
                                for (int i = 0; i < uRLServiceSubscription.getListeners().size(); i++) {
                                    ResponseListener responseListener = (ResponseListener) uRLServiceSubscription.getListeners().get(i);
                                    if (!(mMediaPlayer == null || mMediaPlayer.getMediaStatus() == null)) {
                                        Util.postSuccess(responseListener, PlayStateStatus.convertPlayerStateToPlayStateStatus(mMediaPlayer.getMediaStatus().getPlayerState()));
                                    }
                                }
                            }
                        }
                    }
                }
            });
            mMediaPlayer.setOnMetadataUpdatedListener(new RemoteMediaPlayer.OnMetadataUpdatedListener() {

                @Override
                public void onMetadataUpdated() {
                    if (subscriptions.size() > 0) {
                        for (URLServiceSubscription<?> uRLServiceSubscription : subscriptions) {
                            if (uRLServiceSubscription.getTarget().equalsIgnoreCase("info")) {
                                for (int i = 0; i < uRLServiceSubscription.getListeners().size(); i++) {
                                    getMediaInfo((MediaInfoListener) uRLServiceSubscription.getListeners().get(i));
                                }
                            }
                        }
                    }
                }
            });
            if (mApiClient != null) {
                try {
                    mCastClient.setMessageReceivedCallbacks(mApiClient, mMediaPlayer.getNamespace(), mMediaPlayer);
                } catch (Exception e) {
                    Log.w(Util.T, "Exception while creating media channel", e);
                }
            }
        }
    }


    public RemoteMediaPlayer createMediaPlayer() {
        return new RemoteMediaPlayer();
    }


    private void detachMediaPlayer() {
        if (!(mMediaPlayer == null || mApiClient == null)) {
            try {
                mCastClient.removeMessageReceivedCallbacks(mApiClient, mMediaPlayer.getNamespace());
            } catch (CastClientException e) {
                Log.w(Util.T, "Exception while launching application", e);
            }
        }
        mMediaPlayer = null;
    }

    @Override
    public void displayImage(String str, String str2, String str3, String str4, String str5, LaunchListener launchListener) {
        MediaMetadata mediaMetadata = new MediaMetadata(4);
        mediaMetadata.putString(MediaMetadata.KEY_TITLE, str3);
        mediaMetadata.putString(MediaMetadata.KEY_SUBTITLE, str4);
        if (str5 != null) {
            mediaMetadata.addImage(new WebImage(Uri.parse(str5), 100, 100));
        }
        playMedia(new com.google.android.gms.cast.MediaInfo.Builder(str).setContentType(str2).setStreamType(0).setMetadata(mediaMetadata).setStreamDuration(0).setCustomData(null).build(), applicationID, launchListener);
    }

    @Override
    public void displayImage(MediaInfo mediaInfo, LaunchListener launchListener) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = null;
        if (mediaInfo != null) {
            String url = mediaInfo.getUrl();
            String mimeType = mediaInfo.getMimeType();
            String title = mediaInfo.getTitle();
            String description = mediaInfo.getDescription();
            if (mediaInfo.getImages() != null && mediaInfo.getImages().size() > 0) {
                str6 = mediaInfo.getImages().get(0).getUrl();
            }
            str = str6;
            str5 = url;
            str4 = mimeType;
            str3 = title;
            str2 = description;
        } else {
            str5 = null;
            str4 = null;
            str3 = null;
            str2 = null;
            str = null;
        }
        displayImage(str5, str4, str3, str2, str, launchListener);
    }

    private void playMedia(String str, SubtitleInfo subtitleInfo, String str2, String str3, String str4, String str5, boolean z, LaunchListener launchListener) {
        MediaMetadata mediaMetadata = new MediaMetadata(1);
        mediaMetadata.putString(MediaMetadata.KEY_TITLE, str3);
        mediaMetadata.putString(MediaMetadata.KEY_SUBTITLE, str4);
        if (str5 != null) {
            mediaMetadata.addImage(new WebImage(Uri.parse(str5), 100, 100));
        }
        ArrayList arrayList = new ArrayList();
        if (subtitleInfo != null) {
            arrayList.add(new MediaTrack.Builder(1, 1).setName(subtitleInfo.getLabel()).setSubtype(1).setContentId(subtitleInfo.getUrl()).setContentType(subtitleInfo.getMimeType()).setLanguage(subtitleInfo.getLanguage()).build());
        }
        playMedia(new com.google.android.gms.cast.MediaInfo.Builder(str).setContentType(str2).setStreamType(1).setMetadata(mediaMetadata).setStreamDuration(1000).setCustomData(null).setMediaTracks(arrayList).build(), applicationID, launchListener);
    }

    @Override
    public void playMedia(String str, String str2, String str3, String str4, String str5, boolean z, LaunchListener launchListener) {
        playMedia(str, null, str2, str3, str4, str5, z, launchListener);
    }

    @Override
    public void playMedia(MediaInfo mediaInfo, boolean z, LaunchListener launchListener) {
        String str;
        String str2;
        String str3;
        String str4;
        SubtitleInfo subtitleInfo;
        String str5;
        try {
            mCastClient.getApplicationStatus(mApiClient);
        } catch (CastClientException e) {
            Log.e("fatal", "CastService playMedia 111: " + e.getMessage() );
            Util.postError(launchListener, new ServiceCommandError(e.getMessage()));
        }
        String str6 = null;
        if (mediaInfo != null) {
            String url = mediaInfo.getUrl();
            SubtitleInfo subtitleInfo2 = mediaInfo.getSubtitleInfo();
            String mimeType = mediaInfo.getMimeType();
            String title = mediaInfo.getTitle();
            String description = mediaInfo.getDescription();
            if (mediaInfo.getImages() != null && mediaInfo.getImages().size() > 0) {
                str6 = mediaInfo.getImages().get(0).getUrl();
            }
            str = str6;
            str2 = description;
            str3 = title;
            str4 = mimeType;
            subtitleInfo = subtitleInfo2;
            str5 = url;
        } else {
            str5 = null;
            subtitleInfo = null;
            str4 = null;
            str3 = null;
            str2 = null;
            str = null;
        }
        playMedia(str5, subtitleInfo, str4, str3, str2, str, z, launchListener);
    }

    private void playMedia(final com.google.android.gms.cast.MediaInfo mediaInfo, final String str, final LaunchListener launchListener) {
        final ApplicationConnectionResultCallback applicationConnectionResultCallback = new ApplicationConnectionResultCallback(new LaunchWebAppListener() {

            @Override
            public void onSuccess(final WebAppSession webAppSession) {
                CastService.this.runCommand(new ConnectionListener() {

                    @Override
                    public void onConnected() {
                        Log.e("fatal", "CastService onConnected: " );
                        CastService.this.loadMedia(mediaInfo, webAppSession, launchListener);
                    }
                });
            }

            @Override
            public void onFailure(ServiceCommandError serviceCommandError) {
                Log.e("fatal", "CastService playMedia 222: " + serviceCommandError.getMessage() );
                Util.postError(launchListener, serviceCommandError);
            }
        });
        this.launchingAppId = str;
        runCommand(new ConnectionListener() {

            @Override
            public void onConnected() {
                boolean z;
//                try {
//                    if (CastService.this.mCastClient.getApplicationStatus(CastService.this.mApiClient) != null) {
//                        if (str.equals(CastService.this.currentAppId)) {
//                            Log.e("fatal", "playMedia: " + "aaaa" );
//                            z = false;
//                            LaunchOptions launchOptions = new LaunchOptions();
//                            launchOptions.setRelaunchIfRunning(z);
//                            CastService.this.mCastClient.launchApplication(CastService.this.mApiClient, str, launchOptions).setResultCallback(applicationConnectionResultCallback);
//                        }
//                    }
//                    z = true;
//                    LaunchOptions launchOptions2 = new LaunchOptions();
//                    launchOptions2.setRelaunchIfRunning(z);
//                    CastService.this.mCastClient.launchApplication(CastService.this.mApiClient, str, launchOptions2).setResultCallback(applicationConnectionResultCallback);
//                } catch (Exception unused) {
//                    Util.postError(launchListener, new ServiceCommandError(0, "Unable to launch", null));
//                }

                try {
                    final boolean relaunchIfRunning = mCastClient.getApplicationStatus(mApiClient) == null || !str.equals(currentAppId);
                    final LaunchOptions launchOptions = new LaunchOptions();
                    launchOptions.setRelaunchIfRunning(relaunchIfRunning);
                    mCastClient.launchApplication(mApiClient, str, launchOptions).setResultCallback(applicationConnectionResultCallback);
                } catch (final Exception ex) {
                    Util.postError(launchListener, new ServiceCommandError(0, "Unable to launch", (Object)null));
                }

            }
        });
    }


    private void loadMedia(com.google.android.gms.cast.MediaInfo mediaInfo, final WebAppSession webAppSession, final LaunchListener launchListener) {
        try {
            Log.e("fatal", "loadMedia 111: " + mApiClient );
            Log.e("fatal", "loadMedia 222: " + mediaInfo );
            mMediaPlayer.load(mApiClient, mediaInfo, true).setResultCallback(new ResultCallback<RemoteMediaPlayer.MediaChannelResult>() {

                public void onResult(RemoteMediaPlayer.MediaChannelResult mediaChannelResult) {
                    Status status = mediaChannelResult.getStatus();
                    Log.e("fatal", "loadMedia onResult: " + status + "                " + status.isSuccess() );
                    if (status.isSuccess()) {
                        webAppSession.launchSession.setSessionType(LaunchSession.LaunchSessionType.Media);
                        mMediaPlayer.setActiveMediaTracks(mApiClient, new long[]{1});
                        Util.postSuccess(launchListener, new MediaLaunchObject(webAppSession.launchSession, CastService.this));
                        return;
                    }
                    Util.postError(launchListener, new ServiceCommandError(status.getStatusCode(), status.getStatusMessage(), status));
                }
            });
        } catch (Exception unused) {
            Log.e("fatal", "loadMedia exce: " + unused.getMessage() );
            Util.postError(launchListener, new ServiceCommandError(0, "Unable to load", null));
        }
    }

    @Override
    public void closeMedia(final LaunchSession launchSession, final ResponseListener<Object> responseListener) {
        runCommand(new ConnectionListener() {

            @Override
            public void onConnected() {
                try {
                    mCastClient.stopApplication(mApiClient, launchSession.getSessionId()).setResultCallback(new ResultCallback<Status>() {

                        public void onResult(Status status) {
                            if (status.isSuccess()) {
                                Util.postSuccess(responseListener, status);
                            } else {
                                Util.postError(responseListener, new ServiceCommandError(status.getStatusCode(), status.getStatusMessage(), status));
                            }
                        }
                    });
                } catch (Exception unused) {
                    Util.postError(responseListener, new ServiceCommandError(0, "Unable to stop", null));
                }
            }
        });
    }

    @Override
    public CapabilityPriorityLevel getWebAppLauncherCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void launchWebApp(String str, WebAppSession.LaunchListener launchListener) {
        launchWebApp(str, true, launchListener);
    }

    @Override
    public void launchWebApp(final String str, final boolean z, final WebAppSession.LaunchListener launchListener) {
        this.launchingAppId = str;
        final LaunchWebAppListener r5 = new LaunchWebAppListener() {

            @Override
            public void onSuccess(WebAppSession webAppSession) {
                Util.postSuccess(launchListener, webAppSession);
            }

            @Override
            public void onFailure(ServiceCommandError serviceCommandError) {
                Log.e("fatal", "CastService playMedia 333: " + serviceCommandError.getMessage() );
                Util.postError(launchListener, serviceCommandError);
            }
        };
        runCommand(new ConnectionListener() {

            @Override
            public void onConnected() {
                try {
                    if (!z) {
                        mCastClient.joinApplication(mApiClient).setResultCallback(new ResultCallback<Cast.ApplicationConnectionResult>() {

                            public void onResult(Cast.ApplicationConnectionResult applicationConnectionResult) {
                                if (!applicationConnectionResult.getStatus().isSuccess() || applicationConnectionResult.getApplicationMetadata() == null || applicationConnectionResult.getApplicationMetadata().getName() == null || !applicationConnectionResult.getApplicationMetadata().getApplicationId().equals(str)) {
                                    LaunchOptions launchOptions = new LaunchOptions();
                                    launchOptions.setRelaunchIfRunning(true);
                                    try {
                                        mCastClient.launchApplication(mApiClient, str, launchOptions).setResultCallback(new ApplicationConnectionResultCallback(r5));
                                    } catch (Exception unused) {
                                        Util.postError(launchListener, new ServiceCommandError(0, "Unable to launch", null));
                                    }
                                } else {
                                    ApplicationMetadata applicationMetadata = applicationConnectionResult.getApplicationMetadata();
                                    CastService.this.currentAppId = applicationMetadata.getApplicationId();
                                    LaunchSession launchSessionForAppId = LaunchSession.launchSessionForAppId(applicationMetadata.getApplicationId());
                                    launchSessionForAppId.setAppName(applicationMetadata.getName());
                                    launchSessionForAppId.setSessionId(applicationConnectionResult.getSessionId());
                                    launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.WebApp);
                                    launchSessionForAppId.setService(CastService.this);
                                    CastWebAppSession castWebAppSession = new CastWebAppSession(launchSessionForAppId, CastService.this);
                                    castWebAppSession.setMetadata(applicationMetadata);
                                    CastService.this.sessions.put(applicationMetadata.getApplicationId(), castWebAppSession);
                                    Util.postSuccess(launchListener, castWebAppSession);
                                }
                            }
                        });
                        return;
                    }
                    LaunchOptions launchOptions = new LaunchOptions();
                    launchOptions.setRelaunchIfRunning(z);
                    mCastClient.launchApplication(mApiClient, str, launchOptions).setResultCallback(new ApplicationConnectionResultCallback(r5));
                } catch (Exception unused) {
                    Util.postError(launchListener, new ServiceCommandError(0, "Unable to launch", null));
                }
            }
        });
    }

    @Override
    public void launchWebApp(String str, JSONObject jSONObject, WebAppSession.LaunchListener launchListener) {
        Util.postError(launchListener, ServiceCommandError.notSupported());
    }

    @Override
    public void launchWebApp(String str, JSONObject jSONObject, boolean z, WebAppSession.LaunchListener launchListener) {
        Util.postError(launchListener, ServiceCommandError.notSupported());
    }

    public void requestStatus(final ResponseListener<Object> responseListener) {
        try {
            mMediaPlayer.requestStatus(mApiClient).setResultCallback(new ResultCallback<RemoteMediaPlayer.MediaChannelResult>() {

                public void onResult(RemoteMediaPlayer.MediaChannelResult mediaChannelResult) {
                    if (mediaChannelResult.getStatus().isSuccess()) {
                        Util.postSuccess(responseListener, mediaChannelResult);
                    } else {
                        Util.postError(responseListener, new ServiceCommandError(0, "Failed to request status", mediaChannelResult));
                    }
                }
            });
        } catch (Exception unused) {
            Util.postError(responseListener, new ServiceCommandError(0, "There is no media currently available", null));
        }
    }

    public void joinApplication(final ResponseListener<Object> responseListener) {
        runCommand(new ConnectionListener() {

            @Override
            public void onConnected() {
                try {
                    mCastClient.joinApplication(mApiClient).setResultCallback(new ResultCallback<Cast.ApplicationConnectionResult>() {

                        public void onResult(Cast.ApplicationConnectionResult applicationConnectionResult) {
                            if (!applicationConnectionResult.getStatus().isSuccess()) {
                                Util.postError(responseListener, new ServiceCommandError(0, "Failed to join application", applicationConnectionResult));
                            } else if (applicationConnectionResult.getApplicationMetadata() == null || applicationConnectionResult.getApplicationMetadata().getName() == null || applicationConnectionResult.getApplicationMetadata().getName().equals("Backdrop") || mMediaPlayer == null || mApiClient == null) {
                                Util.postSuccess(responseListener, applicationConnectionResult);
                            } else {
                                mMediaPlayer.requestStatus(mApiClient).setResultCallback(new ResultCallback<RemoteMediaPlayer.MediaChannelResult>() {

                                    public void onResult(RemoteMediaPlayer.MediaChannelResult mediaChannelResult) {
                                        Util.postSuccess(responseListener, mediaChannelResult);
                                    }
                                });
                            }
                        }
                    });
                } catch (Exception unused) {
                    Util.postError(responseListener, new ServiceCommandError(0, "Unable to join", null));
                }
            }
        });
    }

    @Override
    public void joinWebApp(final LaunchSession launchSession, final WebAppSession.LaunchListener launchListener) {
        final ApplicationConnectionResultCallback applicationConnectionResultCallback = new ApplicationConnectionResultCallback(new LaunchWebAppListener() {

            @Override
            public void onSuccess(final WebAppSession webAppSession) {
                webAppSession.connect(new ResponseListener<Object>() {

                    @Override
                    public void onSuccess(Object obj) {
                        CastService.this.requestStatus(new ResponseListener<Object>() {

                            @Override
                            public void onSuccess(Object obj) {
                                Util.postSuccess(launchListener, webAppSession);
                            }

                            @Override
                            public void onError(ServiceCommandError serviceCommandError) {
                                Util.postSuccess(launchListener, webAppSession);
                            }
                        });
                    }

                    @Override
                    public void onError(ServiceCommandError serviceCommandError) {
                        Log.e("fatal", "CastService playMedia 444: " + serviceCommandError.getMessage() );
                        Util.postError(launchListener, serviceCommandError);
                    }
                });
            }

            @Override
            public void onFailure(ServiceCommandError serviceCommandError) {
                Log.e("fatal", "CastService playMedia 555: " + serviceCommandError.getMessage() );
                Util.postError(launchListener, serviceCommandError);
            }
        });
        this.launchingAppId = launchSession.getAppId();
        runCommand(new ConnectionListener() {

            @Override
            public void onConnected() {
                try {
                    mCastClient.joinApplication(mApiClient, launchSession.getAppId()).setResultCallback(applicationConnectionResultCallback);
                } catch (Exception unused) {
                    Util.postError(launchListener, new ServiceCommandError(0, "Unable to join", null));
                }
            }
        });
    }

    @Override
    public void joinWebApp(String str, WebAppSession.LaunchListener launchListener) {
        LaunchSession launchSessionForAppId = LaunchSession.launchSessionForAppId(str);
        launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.WebApp);
        launchSessionForAppId.setService(this);
        joinWebApp(launchSessionForAppId, launchListener);
    }

    @Override
    public void closeWebApp(LaunchSession launchSession, final ResponseListener<Object> responseListener) {
        runCommand(new ConnectionListener() {

            @Override
            public void onConnected() {
                try {
                    mCastClient.stopApplication(mApiClient).setResultCallback(new ResultCallback<Status>() {

                        public void onResult(Status status) {
                            if (status.isSuccess()) {
                                Util.postSuccess(responseListener, null);
                            } else {
                                Util.postError(responseListener, new ServiceCommandError(status.getStatusCode(), status.getStatusMessage(), status));
                            }
                        }
                    });
                } catch (Exception unused) {
                    Util.postError(responseListener, new ServiceCommandError(0, "Unable to stop", null));
                }
            }
        });
    }

    @Override
    public void pinWebApp(String str, ResponseListener<Object> responseListener) {
        Util.postError(responseListener, ServiceCommandError.notSupported());
    }

    @Override
    public void unPinWebApp(String str, ResponseListener<Object> responseListener) {
        Util.postError(responseListener, ServiceCommandError.notSupported());
    }

    @Override
    public void isWebAppPinned(String str, WebAppSession.WebAppPinStatusListener webAppPinStatusListener) {
        Util.postError(webAppPinStatusListener, ServiceCommandError.notSupported());
    }

    @Override
    public ServiceSubscription<WebAppSession.WebAppPinStatusListener> subscribeIsWebAppPinned(String str, WebAppSession.WebAppPinStatusListener webAppPinStatusListener) {
        Util.postError(webAppPinStatusListener, ServiceCommandError.notSupported());
        return null;
    }

    @Override
    public CapabilityPriorityLevel getVolumeControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void volumeUp(final ResponseListener<Object> responseListener) {
        getVolume(new VolumeListener() {

            public void onSuccess(Float f) {
                if (((double) f.floatValue()) >= 1.0d) {
                    Util.postSuccess(responseListener, null);
                    return;
                }
                float floatValue = (float) (((double) f.floatValue()) + 0.01d);
                if (((double) floatValue) > 1.0d) {
                    floatValue = 1.0f;
                }
                CastService.this.setVolume(floatValue, responseListener);
                Util.postSuccess(responseListener, null);
            }

            @Override
            public void onError(ServiceCommandError serviceCommandError) {
                Log.e("fatal", "CastService playMedia 666: " + serviceCommandError.getMessage() );
                Util.postError(responseListener, serviceCommandError);
            }
        });
    }

    @Override
    public void volumeDown(final ResponseListener<Object> responseListener) {
        getVolume(new VolumeListener() {

            public void onSuccess(Float f) {
                if (((double) f.floatValue()) <= 0.0d) {
                    Util.postSuccess(responseListener, null);
                    return;
                }
                float floatValue = (float) (((double) f.floatValue()) - 0.01d);
                if (((double) floatValue) < 0.0d) {
                    floatValue = 0.0f;
                }
                CastService.this.setVolume(floatValue, responseListener);
                Util.postSuccess(responseListener, null);
            }

            @Override
            public void onError(ServiceCommandError serviceCommandError) {
                Log.e("fatal", "CastService playMedia 777: " + serviceCommandError.getMessage() );
                Util.postError(responseListener, serviceCommandError);
            }
        });
    }

    @Override
    public void setVolume(final float f, final ResponseListener<Object> responseListener) {
        runCommand(new ConnectionListener() {

            @Override
            public void onConnected() {
                try {
                    mCastClient.setVolume(mApiClient, f);
                    Util.postSuccess(responseListener, null);
                } catch (Exception unused) {
                    Util.postError(responseListener, new ServiceCommandError(0, "setting volume level failed", null));
                }
            }
        });
    }

    @Override
    public void getVolume(VolumeListener volumeListener) {
        Util.postSuccess(volumeListener, Float.valueOf(currentVolumeLevel));
    }

    @Override
    public void setMute(final boolean z, final ResponseListener<Object> responseListener) {
        runCommand(new ConnectionListener() {

            @Override
            public void onConnected() {
                try {
                    mCastClient.setMute(mApiClient, z);
                    Util.postSuccess(responseListener, null);
                } catch (Exception unused) {
                    Util.postError(responseListener, new ServiceCommandError(0, "setting mute status failed", null));
                }
            }
        });
    }

    @Override
    public void getMute(MuteListener muteListener) {
        Util.postSuccess(muteListener, Boolean.valueOf(currentMuteStatus));
    }

    @Override
    public ServiceSubscription<VolumeListener> subscribeVolume(VolumeListener volumeListener) {
        URLServiceSubscription uRLServiceSubscription = new URLServiceSubscription<>(this, CAST_SERVICE_VOLUME_SUBSCRIPTION_NAME, null, null);
        uRLServiceSubscription.addListener(volumeListener);
        addSubscription(uRLServiceSubscription);
        return uRLServiceSubscription;
    }

    @Override
    public ServiceSubscription<MuteListener> subscribeMute(MuteListener muteListener) {
        URLServiceSubscription uRLServiceSubscription = new URLServiceSubscription<>(this, CAST_SERVICE_MUTE_SUBSCRIPTION_NAME, null, null);
        uRLServiceSubscription.addListener(muteListener);
        addSubscription(uRLServiceSubscription);
        return uRLServiceSubscription;
    }


    @Override
    public void updateCapabilities() {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, MediaPlayer.Capabilities);
        arrayList.add(MediaPlayer.Subtitle_WebVTT);
        Collections.addAll(arrayList, VolumeControl.Capabilities);
        arrayList.add(MediaControl.Play);
        arrayList.add(MediaControl.Pause);
        arrayList.add(MediaControl.Stop);
        arrayList.add(MediaControl.Duration);
        arrayList.add(MediaControl.Seek);
        arrayList.add(MediaControl.Position);
        arrayList.add(MediaControl.PlayState);
        arrayList.add(MediaControl.PlayState_Subscribe);
        arrayList.add(WebAppLauncher.Launch);
        arrayList.add(WebAppLauncher.Message_Send);
        arrayList.add(WebAppLauncher.Message_Receive);
        arrayList.add(WebAppLauncher.Message_Send_JSON);
        arrayList.add(WebAppLauncher.Message_Receive_JSON);
        arrayList.add(WebAppLauncher.Connect);
        arrayList.add(WebAppLauncher.Disconnect);
        arrayList.add(WebAppLauncher.Join);
        arrayList.add(WebAppLauncher.Close);
        setCapabilities(arrayList);
    }

    @Override
    public void getPlayState(PlayStateListener playStateListener) {
        if (mMediaPlayer == null || mMediaPlayer.getMediaStatus() == null) {
            Util.postError(playStateListener, new ServiceCommandError(0, "There is no media currently available", null));
        } else {
            Util.postSuccess(playStateListener, PlayStateStatus.convertPlayerStateToPlayStateStatus(mMediaPlayer.getMediaStatus().getPlayerState()));
        }
    }

    public GoogleApiClient getApiClient() {
        return this.mApiClient;
    }

    @Override
    public boolean isConnected() {
        return this.connected;
    }

    @Override
    public ServiceSubscription<PlayStateListener> subscribePlayState(PlayStateListener playStateListener) {
        URLServiceSubscription uRLServiceSubscription = new URLServiceSubscription<>(this, PLAY_STATE, null, null);
        uRLServiceSubscription.addListener(playStateListener);
        addSubscription(uRLServiceSubscription);
        return uRLServiceSubscription;
    }

    private void addSubscription(URLServiceSubscription<?> uRLServiceSubscription) {
        this.subscriptions.add(uRLServiceSubscription);
    }

    @Override
    public void unsubscribe(URLServiceSubscription<?> uRLServiceSubscription) {
        this.subscriptions.remove(uRLServiceSubscription);
    }

    public List<URLServiceSubscription<?>> getSubscriptions() {
        return this.subscriptions;
    }

    public void setSubscriptions(List<URLServiceSubscription<?>> list) {
        this.subscriptions = list;
    }


    private void runCommand(ConnectionListener connectionListener) {
        if (mApiClient == null || !mApiClient.isConnected()) {
            connect();
            commandQueue.add(connectionListener);
            return;
        }
        connectionListener.onConnected();
    }


    public interface ConnectionListener {
        void onConnected();
    }

    public interface LaunchWebAppListener {
        void onFailure(ServiceCommandError serviceCommandError);

        void onSuccess(WebAppSession webAppSession);
    }


    public static class CastClientException extends Exception {
        CastClientException(String str, Exception exc) {
            super(str, exc);
        }
    }


    public static class CastClient {
        CastClient() {
        }

        public void leaveApplication(GoogleApiClient googleApiClient) throws CastClientException {
            try {
                Cast.CastApi.leaveApplication(googleApiClient);
            } catch (RuntimeException e) {
                throw createCastClientException(e);
            }
        }

        public void setMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str, RemoteMediaPlayer remoteMediaPlayer) throws CastClientException {
            try {
                Cast.CastApi.setMessageReceivedCallbacks(googleApiClient, remoteMediaPlayer.getNamespace(), remoteMediaPlayer);
            } catch (RuntimeException e) {
                throw createCastClientException(e);
            } catch (IOException e2) {
                throw createCastClientException(e2);
            }
        }

        public void removeMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str) throws CastClientException {
            try {
                Cast.CastApi.removeMessageReceivedCallbacks(googleApiClient, str);
            } catch (IOException e) {
                throw createCastClientException(e);
            } catch (RuntimeException e2) {
                throw createCastClientException(e2);
            }
        }

        public Object getApplicationStatus(GoogleApiClient googleApiClient) throws CastClientException {
            try {
                return Cast.CastApi.getApplicationStatus(googleApiClient);
            } catch (RuntimeException e) {
                Log.e("fatal", "getApplicationStatus: " + e.getMessage() );
                e.printStackTrace();
                throw createCastClientException(e);
            }
        }

        public PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions) throws CastClientException {
            try {
                return Cast.CastApi.launchApplication(googleApiClient, str, launchOptions);
            } catch (RuntimeException e) {
                Log.e("fatal", "launchApplication: " + e.getMessage() );
                e.printStackTrace();
                throw createCastClientException(e);
            }
        }

        public PendingResult<Status> stopApplication(GoogleApiClient googleApiClient, String str) throws CastClientException {
            try {
                return Cast.CastApi.stopApplication(googleApiClient, str);
            } catch (RuntimeException e) {
                throw createCastClientException(e);
            }
        }

        public PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient) throws CastClientException {
            try {
                return Cast.CastApi.joinApplication(googleApiClient);
            } catch (RuntimeException e) {
                throw createCastClientException(e);
            }
        }

        public PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str) throws CastClientException {
            try {
                return Cast.CastApi.joinApplication(googleApiClient, str);
            } catch (RuntimeException e) {
                throw createCastClientException(e);
            }
        }

        public PendingResult<Status> stopApplication(GoogleApiClient googleApiClient) throws CastClientException {
            try {
                return Cast.CastApi.stopApplication(googleApiClient);
            } catch (RuntimeException e) {
                throw createCastClientException(e);
            }
        }

        public void setVolume(GoogleApiClient googleApiClient, float f) throws IOException, CastClientException {
            try {
                Cast.CastApi.setVolume(googleApiClient, (double) f);
            } catch (RuntimeException e) {
                throw createCastClientException(e);
            } catch (IOException e2) {
                throw createCastClientException(e2);
            }
        }

        public void setMute(GoogleApiClient googleApiClient, boolean z) throws IOException, CastClientException {
            try {
                Cast.CastApi.setMute(googleApiClient, z);
            } catch (RuntimeException e) {
                throw createCastClientException(e);
            } catch (IOException e2) {
                throw createCastClientException(e2);
            }
        }

        public ApplicationMetadata getApplicationMetadata(GoogleApiClient googleApiClient) throws CastClientException {
            try {
                return Cast.CastApi.getApplicationMetadata(googleApiClient);
            } catch (RuntimeException e) {
                throw createCastClientException(e);
            }
        }

        public double getVolume(GoogleApiClient googleApiClient) throws CastClientException {
            try {
                return Cast.CastApi.getVolume(googleApiClient);
            } catch (RuntimeException e) {
                throw createCastClientException(e);
            }
        }

        public boolean isMute(GoogleApiClient googleApiClient) throws CastClientException {
            try {
                return Cast.CastApi.isMute(googleApiClient);
            } catch (RuntimeException e) {
                throw createCastClientException(e);
            }
        }

        private CastClientException createCastClientException(Exception exc) {
            return new CastClientException("CastClient error", exc);
        }
    }


    public class CastListener extends Cast.Listener {
        private CastListener() {
        }

        @Override
        public void onApplicationDisconnected(int i) {
            CastWebAppSession castWebAppSession;
            Log.d(Util.T, "Cast.Listener.onApplicationDisconnected: " + i);
            if (CastService.this.currentAppId != null && (castWebAppSession = CastService.this.sessions.get(CastService.this.currentAppId)) != null) {
                castWebAppSession.handleAppClose();
                CastService.this.currentAppId = null;
            }
        }

        @Override
        public void onApplicationStatusChanged() {
            CastService.this.runCommand(new ConnectionListener() {

                @Override
                public void onConnected() {
                    if (CastService.this.mApiClient != null) {
                        try {
                            ApplicationMetadata applicationMetadata = CastService.this.mCastClient.getApplicationMetadata(CastService.this.mApiClient);
                            if (applicationMetadata != null) {
                                CastService.this.currentAppId = applicationMetadata.getApplicationId();
                            }
                        } catch (CastClientException e) {
                            Log.e(Util.T, "Error in onApplicationStatusChanged", e);
                        }
                    }
                }
            });
        }

        @Override
        public void onVolumeChanged() {
            CastService.this.runCommand(new ConnectionListener() {

                @Override
                public void onConnected() {
                    try {
                        currentVolumeLevel = (float) mCastClient.getVolume(mApiClient);
                        currentMuteStatus = mCastClient.isMute(mApiClient);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (subscriptions.size() > 0) {
                        for (URLServiceSubscription<?> uRLServiceSubscription : subscriptions) {
                            int i = 0;
                            if (uRLServiceSubscription.getTarget().equals(CAST_SERVICE_VOLUME_SUBSCRIPTION_NAME)) {
                                while (i < uRLServiceSubscription.getListeners().size()) {
                                    Util.postSuccess((ResponseListener) uRLServiceSubscription.getListeners().get(i), Float.valueOf(currentVolumeLevel));
                                    i++;
                                }
                            } else if (uRLServiceSubscription.getTarget().equals(CastService.CAST_SERVICE_MUTE_SUBSCRIPTION_NAME)) {
                                while (i < uRLServiceSubscription.getListeners().size()) {
                                    Util.postSuccess((ResponseListener) uRLServiceSubscription.getListeners().get(i), Boolean.valueOf(currentMuteStatus));
                                    i++;
                                }
                            }
                        }
                    }
                }
            });
        }
    }


    public class ConnectionCallbacks implements GoogleApiClient.ConnectionCallbacks {
        private ConnectionCallbacks() {
        }

        @Override
        public void onConnectionSuspended(int i) {
            Log.d(Util.T, "ConnectionCallbacks.onConnectionSuspended");
            CastService.this.mWaitingForReconnect = true;
            CastService.this.detachMediaPlayer();
        }

        @Override
        public void onConnected(Bundle bundle) {
            Log.d(Util.T, "ConnectionCallbacks.onConnected, wasWaitingForReconnect: " + mWaitingForReconnect);
            attachMediaPlayer();
            if (mApiClient != null && mApiClient.isConnected()) {
                try {
                    mCastClient.joinApplication(mApiClient).setResultCallback(new ResultCallback<Cast.ApplicationConnectionResult>() {

                        public void onResult(Cast.ApplicationConnectionResult applicationConnectionResult) {
                            onJoinApplicationResult(applicationConnectionResult);
                        }
                    });
                } catch (CastClientException e) {
                    Log.e(Util.T, "join application error", e);
                }
            }
        }


        private void onJoinApplicationResult(Cast.ApplicationConnectionResult applicationConnectionResult) {
            if (!applicationConnectionResult.getStatus().isSuccess()) {
                joinFinished();
            } else if (applicationConnectionResult.getApplicationMetadata() == null || applicationConnectionResult.getApplicationMetadata().getName() == null || applicationConnectionResult.getApplicationMetadata().getName().equals("Backdrop") || mMediaPlayer == null || mApiClient == null) {
                joinFinished();
            } else {
                mMediaPlayer.requestStatus(mApiClient).setResultCallback(new ResultCallback<RemoteMediaPlayer.MediaChannelResult>() {

                    public void onResult(RemoteMediaPlayer.MediaChannelResult mediaChannelResult) {
                        joinFinished();
                    }
                });
            }
        }


        private void joinFinished() {
            if (mWaitingForReconnect) {
                mWaitingForReconnect = false;
            } else {
                connected = true;
                reportConnected(true);
            }
            if (!commandQueue.isEmpty()) {
                Iterator<ConnectionListener> it = commandQueue.iterator();
                while (it.hasNext()) {
                    ConnectionListener next = it.next();
                    next.onConnected();
                    commandQueue.remove(next);
                }
            }
        }
    }


    public class ConnectionFailedListener implements GoogleApiClient.OnConnectionFailedListener {
        private ConnectionFailedListener() {
        }

        @Override
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            Log.d(Util.T, "ConnectionFailedListener.onConnectionFailed " + (connectionResult != null ? connectionResult : ""));
            CastService.this.detachMediaPlayer();
            CastService.this.connected = false;
            CastService.this.mWaitingForReconnect = false;
            CastService.this.mApiClient = null;
            Util.runOnUI(new Runnable() {

                public void run() {
                    if (listener != null) {
                        listener.onConnectionFailure(CastService.this, new ServiceCommandError(connectionResult.getErrorCode(), "Failed to connect to Google Cast device", connectionResult));
                    }
                }
            });
        }
    }


    public class ApplicationConnectionResultCallback implements ResultCallback<Cast.ApplicationConnectionResult> {
        LaunchWebAppListener listener;

        public ApplicationConnectionResultCallback(LaunchWebAppListener launchWebAppListener) {
            this.listener = launchWebAppListener;
        }

        public void onResult(Cast.ApplicationConnectionResult applicationConnectionResult) {
            Status status = applicationConnectionResult.getStatus();
            if (status.isSuccess()) {
                ApplicationMetadata applicationMetadata = applicationConnectionResult.getApplicationMetadata();
                CastService.this.currentAppId = applicationMetadata.getApplicationId();
                LaunchSession launchSessionForAppId = LaunchSession.launchSessionForAppId(applicationMetadata.getApplicationId());
                launchSessionForAppId.setAppName(applicationMetadata.getName());
                launchSessionForAppId.setSessionId(applicationConnectionResult.getSessionId());
                launchSessionForAppId.setSessionType(LaunchSession.LaunchSessionType.WebApp);
                launchSessionForAppId.setService(CastService.this);
                CastWebAppSession castWebAppSession = new CastWebAppSession(launchSessionForAppId, CastService.this);
                castWebAppSession.setMetadata(applicationMetadata);
                CastService.this.sessions.put(applicationMetadata.getApplicationId(), castWebAppSession);
                if (listener != null) {
                    listener.onSuccess(castWebAppSession);
                }
                launchingAppId = null;
                return;
            }
            if (listener != null) {
                listener.onFailure(new ServiceCommandError(status.getStatusCode(), status.getStatusMessage(), status));
            }
        }
    }

}
