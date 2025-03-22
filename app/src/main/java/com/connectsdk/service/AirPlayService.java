package com.connectsdk.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.connectsdk.core.ImageInfo;
import com.connectsdk.core.MediaInfo;
import com.connectsdk.core.Util;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.etc.helper.DeviceServiceReachability;
import com.connectsdk.service.airplay.AirPlayServiceSocketClient;
import com.connectsdk.service.airplay.PListParser;
import com.connectsdk.service.capability.CapabilityMethods;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommand;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.config.AirPlayServiceConfig;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.sessions.LaunchSession;
import com.dd.plist.BinaryPropertyListWriter;
import com.dd.plist.NSDictionary;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;


public class AirPlayService extends DeviceService implements MediaPlayer, MediaControl {
    private static final String CHARSET = "UTF-8";

    public static final String ID = "AirPlay";
    private static final long KEEP_ALIVE_PERIOD = 15000;
    public static final String X_APPLE_SESSION_ID = "X-Apple-Session-ID";
    private String mSessionId;
    private AirPlayServiceSocketClient.AirPlayServiceSocketClientListener mSocketListener;
    private AirPlayServiceSocketClient socketClient;
    private Timer timer;

    public interface PlaybackPositionListener {
        void onGetPlaybackPositionFailed(ServiceCommandError error);

        void onGetPlaybackPositionSuccess(long duration, long position);
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

    @Override
    public CapabilityPriorityLevel getPriorityLevel(Class<? extends CapabilityMethods> clazz) {
        if (clazz.equals(MediaPlayer.class)) {
            return getMediaPlayerCapabilityLevel();
        }
        if (clazz.equals(MediaControl.class)) {
            return getMediaControlCapabilityLevel();
        }
        return CapabilityPriorityLevel.NOT_SUPPORTED;
    }

    public AirPlayService(ServiceDescription serviceDescription, ServiceConfig serviceConf) {
        super(serviceDescription, serviceConf);
        this.mSocketListener = new AirPlayServiceSocketClient.AirPlayServiceSocketClientListener() {
            @Override
            public void onRegistrationFailed(final ServiceCommandError error) {
                AirPlayService.this.disconnect();
                Util.runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        if (AirPlayService.this.listener != null) {
                            AirPlayService.this.listener.onConnectionFailure(AirPlayService.this, error);
                        }
                    }
                });
            }

            @Override
            public Boolean onReceiveMessage(JSONObject message) {
                return true;
            }

            @Override
            public void onFailWithError(final ServiceCommandError error) {
                AirPlayService.this.socketClient.setListener(null);
                AirPlayService.this.socketClient.disconnect();
                AirPlayService.this.socketClient = null;
                Util.runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        if (AirPlayService.this.listener != null) {
                            AirPlayService.this.listener.onConnectionFailure(AirPlayService.this, error);
                        }
                    }
                });
            }

            @Override
            public void onConnect() {
                AirPlayService.this.reportConnected(true);
            }

            @Override
            public void onCloseWithError(final ServiceCommandError error) {
                AirPlayService.this.socketClient.setListener(null);
                AirPlayService.this.socketClient.disconnect();
                AirPlayService.this.socketClient = null;
                Util.runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        if (AirPlayService.this.listener != null) {
                            AirPlayService.this.listener.onDisconnect(AirPlayService.this, error);
                        }
                    }
                });
            }

            @Override
            public void onBeforeRegister(final PairingType pairingType) {
                if (DiscoveryManager.getInstance().getPairingLevel().compareTo(DiscoveryManager.PairingLevel.ON) >= 0) {
                    Util.runOnUI(new Runnable() {
                        @Override
                        public void run() {
                            if (AirPlayService.this.listener != null) {
                                AirPlayService.this.listener.onPairingRequired(AirPlayService.this, pairingType, null);
                            }
                        }
                    });
                }
            }
        };
        this.serviceConfig = new AirPlayServiceConfig(serviceConf.toJSONObject());
        this.pairingType = PairingType.PIN_CODE;
    }

    public static DiscoveryFilter discoveryFilter() {
        return new DiscoveryFilter(ID, "_airplay._tcp.local.");
    }

    @Override
    public CapabilityPriorityLevel getMediaControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void play(ResponseListener<Object> listener) {
        HashMap hashMap = new HashMap();
        hashMap.put("value", "1.000000");
        new ServiceCommand(this, getRequestURL("rate", hashMap), null, listener).send();
    }

    @Override
    public void pause(ResponseListener<Object> listener) {
        HashMap hashMap = new HashMap();
        hashMap.put("value", "0.000000");
        new ServiceCommand(this, getRequestURL("rate", hashMap), null, listener).send();
    }

    @Override
    public void stop(ResponseListener<Object> listener) {
        ServiceCommand serviceCommand = new ServiceCommand(this, getRequestURL("stop"), null, listener);
        serviceCommand.send();
        serviceCommand.send();
        stopTimer();
    }

    @Override
    public void rewind(ResponseListener<Object> listener) {
        HashMap hashMap = new HashMap();
        hashMap.put("value", "-2.000000");
        new ServiceCommand(this, getRequestURL("rate", hashMap), null, listener).send();
    }

    @Override
    public void fastForward(ResponseListener<Object> listener) {
        HashMap hashMap = new HashMap();
        hashMap.put("value", "2.000000");
        new ServiceCommand(this, getRequestURL("rate", hashMap), null, listener).send();
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
        HashMap hashMap = new HashMap();
        hashMap.put("position", String.valueOf(((float) position) / 1000.0f));
        new ServiceCommand(this, getRequestURL("scrub", hashMap), null, listener).send();
    }

    @Override
    public void getPosition(final PositionListener listener) {
        getPlaybackPosition(new PlaybackPositionListener() {
            @Override
            public void onGetPlaybackPositionSuccess(long duration, long position) {
                Util.postSuccess(listener, Long.valueOf(position));
            }

            @Override
            public void onGetPlaybackPositionFailed(ServiceCommandError error) {
                Util.postError(listener, new ServiceCommandError(0, "Unable to get position", null));
            }
        });
    }

    @Override
    public void getPlayState(final PlayStateListener listener) {
        getPlaybackInfo(new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object object) {
                PlayStateStatus playStateStatus = PlayStateStatus.Unknown;
                try {
                    JSONObject parse = new PListParser().parse(object.toString());
                    if (!parse.has("rate")) {
                        playStateStatus = PlayStateStatus.Finished;
                    } else {
                        int i = parse.getInt("rate");
                        if (i == 0) {
                            playStateStatus = PlayStateStatus.Paused;
                        } else if (i == 1) {
                            playStateStatus = PlayStateStatus.Playing;
                        }
                    }
                    Util.postSuccess(listener, playStateStatus);
                } catch (Exception e) {
                    Util.postError(listener, new ServiceCommandError(500, e.getMessage(), null));
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override
    public void getDuration(final DurationListener listener) {
        getPlaybackPosition(new PlaybackPositionListener() {
            @Override
            public void onGetPlaybackPositionSuccess(long duration, long position) {
                Util.postSuccess(listener, Long.valueOf(duration));
            }

            @Override
            public void onGetPlaybackPositionFailed(ServiceCommandError error) {
                Util.postError(listener, new ServiceCommandError(0, "Unable to get duration", null));
            }
        });
    }


    public void getPlaybackPosition(final PlaybackPositionListener listener) {
        ServiceCommand serviceCommand = new ServiceCommand(this, getRequestURL("scrub"), null, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                StringTokenizer stringTokenizer = new StringTokenizer((String) response);
                long j = 0;
                long j2 = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    String nextToken = stringTokenizer.nextToken();
                    if (nextToken.contains("duration")) {
                        j = AirPlayService.this.parseTimeValueFromString(stringTokenizer.nextToken());
                    } else if (nextToken.contains("position")) {
                        j2 = AirPlayService.this.parseTimeValueFromString(stringTokenizer.nextToken());
                    }
                }
                if (listener != null) {
                    listener.onGetPlaybackPositionSuccess(j, j2);
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                if (listener != null) {
                    listener.onGetPlaybackPositionFailed(error);
                }
            }
        });
        serviceCommand.setHttpMethod("GET");
        serviceCommand.send();
    }

    private long parseTimeValueFromString(String value) {
        long duration = 0L;
        try {
            float f = Float.parseFloat(value);
            duration = (long) f * 1000;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return duration;
    }

    private void getPlaybackInfo(ResponseListener<Object> listener) {
        ServiceCommand serviceCommand = new ServiceCommand(this, getRequestURL("playback-info"), null, listener);
        serviceCommand.setHttpMethod("GET");
        serviceCommand.send();
    }

    @Override
    public ServiceSubscription<PlayStateListener> subscribePlayState(PlayStateListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return null;
    }

    @Override
    public CapabilityPriorityLevel getMediaPlayerCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
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
    public void displayImage(final String url, String mimeType, String title, String description, String iconSrc, final LaunchListener listener) {
        Util.runInBackground(new Runnable() {
            @Override
            public void run() {
                ResponseListener<Object> responseListener = new ResponseListener<Object>() {

                    @Override
                    public void onSuccess(Object response) {
                        LaunchSession launchSession = new LaunchSession();
                        launchSession.setService(AirPlayService.this);
                        launchSession.setSessionType(LaunchSession.LaunchSessionType.Media);

                        Util.postSuccess(listener, new MediaLaunchObject(launchSession, AirPlayService.this));
                    }

                    @Override
                    public void onError(ServiceCommandError error) {
                        Util.postError(listener, error);
                    }
                };

                String uri = getRequestURL("photo");
                byte[] payload = null;

                try {
                    URL imagePath = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) imagePath.openConnection();
                    connection.setInstanceFollowRedirects(true);
                    connection.setDoInput(true);
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    boolean redirect = (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                            || responseCode == HttpURLConnection.HTTP_MOVED_PERM
                            || responseCode == HttpURLConnection.HTTP_SEE_OTHER);

                    if (redirect) {
                        String newPath = connection.getHeaderField("Location");
                        URL newImagePath = new URL(newPath);
                        connection = (HttpURLConnection) newImagePath.openConnection();
                        connection.setInstanceFollowRedirects(true);
                        connection.setDoInput(true);
                        connection.connect();
                    }

                    InputStream input = connection.getInputStream();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    payload = stream.toByteArray();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ServiceCommand<ResponseListener<Object>> request = new ServiceCommand<ResponseListener<Object>>(AirPlayService.this, uri, payload, responseListener);
                request.setHttpMethod(ServiceCommand.TYPE_PUT);
                request.send();
            }
        });
    }

    @Override
    public void displayImage(MediaInfo mediaInfo, LaunchListener listener) {
        String mediaUrl = null;
        String mimeType = null;
        String title = null;
        String desc = null;
        String iconSrc = null;

        if (mediaInfo != null) {
            mediaUrl = mediaInfo.getUrl();
            mimeType = mediaInfo.getMimeType();
            title = mediaInfo.getTitle();
            desc = mediaInfo.getDescription();

            if (mediaInfo.getImages() != null && mediaInfo.getImages().size() > 0) {
                ImageInfo imageInfo = mediaInfo.getImages().get(0);
                iconSrc = imageInfo.getUrl();
            }
        }

        displayImage(mediaUrl, mimeType, title, desc, iconSrc, listener);
    }

    public void playVideo(final String url, String mimeType, String title, String description, String iconSrc, boolean shouldLoop, final LaunchListener listener) {
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                LaunchSession launchSession = new LaunchSession();
                launchSession.setService(AirPlayService.this);
                launchSession.setSessionType(LaunchSession.LaunchSessionType.Media);
                Util.postSuccess(listener, new MediaLaunchObject(launchSession, AirPlayService.this));
                AirPlayService.this.startTimer();
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        String requestURL = getRequestURL("play");
        NSDictionary nSDictionary = new NSDictionary();
        nSDictionary.put("Content-Location", (Object) url);
        nSDictionary.put("Start-Position", (Object) Double.valueOf(0.0d));
        byte[] bArr = new byte[0];
        try {
            bArr = BinaryPropertyListWriter.writeToArray(nSDictionary);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ServiceCommand(this, requestURL, bArr, responseListener).send();
    }

    @Override
    public void playMedia(String url, String mimeType, String title, String description, String iconSrc, boolean shouldLoop, LaunchListener listener) {
        if (mimeType.contains("image")) {
            displayImage(url, mimeType, title, description, iconSrc, listener);
        } else {
            playVideo(url, mimeType, title, description, iconSrc, shouldLoop, listener);
        }
    }

    @Override
    public void playMedia(MediaInfo mediaInfo, boolean shouldLoop, LaunchListener listener) {
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
            str5 = str6;
            str = url;
            str2 = mimeType;
            str3 = title;
            str4 = description;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
        }
        playMedia(str, str2, str3, str4, str5, shouldLoop, listener);
    }

    @Override
    public void closeMedia(LaunchSession launchSession, ResponseListener<Object> listener) {
        stop(listener);
    }

    @Override
    public void sendCommand(final ServiceCommand<?> serviceCommand) {
        AirPlayServiceSocketClient airPlayServiceSocketClient = this.socketClient;
        if (airPlayServiceSocketClient != null) {
            airPlayServiceSocketClient.sendCommand(serviceCommand);
        }
    }

    @Override
    public void sendPairingKey(String pairingKey) {
        this.socketClient.pair(pairingKey);
    }

    @Override
    protected void updateCapabilities() {
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(MediaPlayer.Display_Image);
        arrayList.add("MediaPlayer.Play.Video");
        arrayList.add("MediaPlayer.Play.Audio");
        arrayList.add(MediaPlayer.Close);
        arrayList.add(MediaControl.Play);
        arrayList.add(MediaControl.Pause);
        arrayList.add(MediaControl.Stop);
        arrayList.add(MediaControl.Position);
        arrayList.add(MediaControl.Duration);
        arrayList.add(MediaControl.PlayState);
        arrayList.add(MediaControl.Seek);
        arrayList.add(MediaControl.Rewind);
        arrayList.add(MediaControl.FastForward);
        setCapabilities(arrayList);
    }

    public String getRequestURL(String command) {
        return getRequestURL(command, null);
    }

    private String getRequestURL(String command, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("/").append(command);
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(String.format("?%s=%s", entry.getKey(), entry.getValue()));
            }
        }
        return sb.toString();
    }

    @Override
    public boolean isConnected() {
        if (DiscoveryManager.getInstance().getPairingLevel().compareTo(DiscoveryManager.PairingLevel.PROTECTED) >= 0) {
            AirPlayServiceSocketClient airPlayServiceSocketClient = this.socketClient;
            return (airPlayServiceSocketClient == null || !airPlayServiceSocketClient.isConnected() || this.socketClient.getAuthToken() == "") ? false : true;
        }
        AirPlayServiceSocketClient airPlayServiceSocketClient2 = this.socketClient;
        return airPlayServiceSocketClient2 != null && airPlayServiceSocketClient2.isConnected();
    }

    public AirPlayServiceConfig getAirPlayServiceConfig() {
        return (AirPlayServiceConfig) this.serviceConfig;
    }

    @Override
    public void connect() {
        this.mSessionId = UUID.randomUUID().toString();
        AirPlayServiceSocketClient airPlayServiceSocketClient = new AirPlayServiceSocketClient(getAirPlayServiceConfig(), getPairingType(), getServiceDescription().getIpAddress());
        this.socketClient = airPlayServiceSocketClient;
        airPlayServiceSocketClient.setListener(this.mSocketListener);
        this.socketClient.connect();
    }

    @Override
    public void disconnect() {
        stopTimer();
        this.connected = false;
        if (this.mServiceReachability != null) {
            this.mServiceReachability.stop();
        }
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                if (AirPlayService.this.listener != null) {
                    AirPlayService.this.listener.onDisconnect(AirPlayService.this, null);
                }
            }
        });
        AirPlayServiceSocketClient airPlayServiceSocketClient = this.socketClient;
        if (airPlayServiceSocketClient != null) {
            airPlayServiceSocketClient.setListener(null);
            this.socketClient.disconnect();
            this.socketClient = null;
        }
    }

    @Override
    public void onLoseReachability(DeviceServiceReachability reachability) {
        if (this.connected) {
            disconnect();
        } else {
            this.mServiceReachability.stop();
        }
    }


    public void startTimer() {
        stopTimer();
        Timer timer = new Timer();
        this.timer = timer;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.d("Timer", "Timer");
                AirPlayService.this.getPlaybackPosition(new PlaybackPositionListener() {
                    @Override
                    public void onGetPlaybackPositionFailed(ServiceCommandError error) {
                    }

                    @Override
                    public void onGetPlaybackPositionSuccess(long duration, long position) {
                        if (position >= duration) {
                            AirPlayService.this.stopTimer();
                        }
                    }
                });
            }
        }, KEEP_ALIVE_PERIOD, KEEP_ALIVE_PERIOD);
    }


    public void stopTimer() {
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
        }
        this.timer = null;
    }
}
