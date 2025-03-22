package com.connectsdk.service.webos.lgcast.screenmirroring.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.os.IBinder;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.webos.lgcast.common.connection.ConnectionManager;
import com.connectsdk.service.webos.lgcast.common.connection.ConnectionManagerError;
import com.connectsdk.service.webos.lgcast.common.connection.ConnectionManagerListener;
import com.connectsdk.service.webos.lgcast.common.connection.MobileDescription;
import com.connectsdk.service.webos.lgcast.common.streaming.RTPStreaming;
import com.connectsdk.service.webos.lgcast.common.utils.AppUtil;
import com.connectsdk.service.webos.lgcast.common.utils.DeviceUtil;
import com.connectsdk.service.webos.lgcast.common.utils.HandlerThreadEx;
import com.connectsdk.service.webos.lgcast.common.utils.IOUtil;
import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.connectsdk.service.webos.lgcast.common.utils.StringUtil;
import com.connectsdk.service.webos.lgcast.common.utils.ThreadUtil;
import com.connectsdk.service.webos.lgcast.common.utils.TimerUtil;
import com.connectsdk.service.webos.lgcast.screenmirroring.ScreenMirroringConfig;
import com.connectsdk.service.webos.lgcast.screenmirroring.capability.MirroringSinkCapability;
import com.connectsdk.service.webos.lgcast.screenmirroring.capability.MirroringSourceCapability;
import com.connectsdk.service.webos.lgcast.screenmirroring.uibc.UibcAccessibilityService;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.lge.lib.lgcast.iface.AudioCaptureIF;
import com.lge.lib.lgcast.iface.CaptureErrorListener;
import com.lge.lib.lgcast.iface.VideoCaptureIF;

import org.json.JSONObject;

public class MirroringService extends Service {

    private AudioCaptureIF mAudioCapture;
    private ConnectionManager mConnectionManager;
    private int mCurrentOrientation;
    private int mCurrentScreenWidth;
    private VideoCaptureIF mLandscapeVideoCapture;
    private MediaProjection mMediaProjection;
    private MirroringServiceEvent mMirroringServiceEvent;
    private MirroringSinkCapability mMirroringSinkCapability;
    private MirroringSourceCapability mMirroringSourceCapability;
    private MirroringVolume mMirroringVolume;
    private VideoCaptureIF mPortraitVideoCapture;
    private RTPStreaming mRTPStreaming;
    private HandlerThreadEx mServiceHandler;

    public static void stopSelfService(MirroringService mirroringService) {
        mirroringService.stopSelf();
    }

    public static void stopService(MirroringService mirroringService) {
        mirroringService.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.showDebug(false);
        HandlerThreadEx handlerThreadEx = new HandlerThreadEx("MirroringService Handler");
        this.mServiceHandler = handlerThreadEx;
        handlerThreadEx.start();
        this.mCurrentOrientation = AppUtil.getOrientation(this);
        this.mCurrentScreenWidth = getResources().getConfiguration().smallestScreenWidthDp;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mServiceHandler.quit();
        this.mServiceHandler = null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Logger.print("onStartCommand: " + StringUtil.toString(intent), new Object[0]);
        final String action = intent != null ? intent.getAction() : null;
        this.mServiceHandler.post(() -> {
            if (MirroringServiceIF.ACTION_START_REQUEST.equals(action)) {
                executeStart(intent);
            } else if (MirroringServiceIF.ACTION_STOP_REQUEST.equals(action)) {
                executeStop();
            } else if (MirroringServiceIF.ACTION_STOP_BY_NOTIFICATION.equals(action)) {
                executeStopByNotification();
            }
        });
        return START_STICKY;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mConnectionManager == null || this.mMirroringSourceCapability == null) {
            return;
        }
        if (this.mCurrentOrientation != newConfig.orientation) {
            Logger.debug("Orientation changed: old=%d, new=%d", Integer.valueOf(this.mCurrentOrientation), Integer.valueOf(newConfig.orientation));
            this.mCurrentOrientation = newConfig.orientation;
            if ("landscape|portrait".equals(this.mMirroringSourceCapability.screenOrientation)) {
                this.mConnectionManager.updateSourceDeviceCapability(MirroringServiceFunc.createVideoSizeInfo(getBaseContext(), this.mMirroringSinkCapability));
            }
        }
        if (this.mCurrentScreenWidth != newConfig.smallestScreenWidthDp) {
            Logger.debug("Screen width changed: old=%d, new=%d", Integer.valueOf(this.mCurrentScreenWidth), Integer.valueOf(newConfig.smallestScreenWidthDp));
            this.mCurrentScreenWidth = newConfig.smallestScreenWidthDp;
            if ("landscape|portrait".equals(this.mMirroringSourceCapability.screenOrientation)) {
                this.mConnectionManager.updateSourceDeviceCapability(MirroringServiceFunc.createVideoSizeInfo(getBaseContext(), this.mMirroringSinkCapability));
            }
        }
    }

    public class ConnectionManag implements ConnectionManagerListener {
        final Intent intent;

        ConnectionManag(final Intent intent) {
            this.intent = intent;
        }

        @Override
        public void onPairingRequested() {
            Logger.debug("onPairingRequested", new Object[0]);
            MirroringServiceIF.notifyPairing(MirroringService.this.getBaseContext());
        }

        @Override
        public void onPairingRejected() {
            Logger.error("onPairingRejected", new Object[0]);
            MirroringServiceIF.respondStart(MirroringService.this.getBaseContext(), false, false);
            MirroringService.this.stop();
        }

        @Override
        public void onConnectionFailed(String message) {
            Logger.error("onConnectionFailed (%s)", message);
            MirroringServiceIF.respondStart(MirroringService.this.getBaseContext(), false, false);
            MirroringService.this.stop();
        }

        @Override
        public void onConnectionCompleted(JSONObject jsonObj) {
            Logger.debug("onConnectionCompleted", new Object[0]);
            MirroringService.this.mMirroringSinkCapability = new MirroringSinkCapability(jsonObj);
            MirroringService.this.mMirroringSinkCapability.displayOrientation = ScreenMirroringConfig.Test.displayOrientation != null ? ScreenMirroringConfig.Test.displayOrientation : MirroringService.this.mMirroringSinkCapability.displayOrientation;
            MirroringService.this.mMirroringSinkCapability.debug();
            MirroringService mirroringService = MirroringService.this;
            mirroringService.mMirroringSourceCapability = MirroringServiceFunc.createMirroringSourceCapa(mirroringService.getBaseContext(), this.intent, MirroringService.this.mMirroringSinkCapability);
            MirroringService.this.mMirroringSourceCapability.debug();
            MobileDescription mobileDescription = new MobileDescription(MirroringService.this.getBaseContext());
            mobileDescription.debug();
            MirroringService.this.mConnectionManager.setSourceDeviceCapability(MirroringService.this.mMirroringSourceCapability.toJSONObject(), mobileDescription.toJSONObject());
            UibcAccessibilityService.onDisplayRotated(MirroringService.this.mMirroringSinkCapability.displayOrientation);
        }

        @Override
        public void onReceivePlayCommand(JSONObject jsonObj) {
            Logger.debug("onReceivePlayCommand", new Object[0]);
            boolean startCaptureAndStreaming = MirroringService.this.startCaptureAndStreaming(this.intent);
            MirroringServiceIF.respondStart(MirroringService.this.getBaseContext(), startCaptureAndStreaming, MirroringServiceFunc.isDualScreen(this.intent));
            if (startCaptureAndStreaming) {
                return;
            }
            MirroringService.this.stop();
        }

        @Override
        public void onReceiveStopCommand(JSONObject jsonObj) {
            Logger.error("onReceiveStopCommand (noop)", new Object[0]);
        }

        @Override
        public void onReceiveGetParameter(JSONObject jsonObj) {
            Logger.error("onReceiveGetParameter (noop)", new Object[0]);
        }

        @Override
        public void onReceiveSetParameter(JSONObject jsonObj) {
            Logger.debug("onReceiveSetParameter");
            JSONObject optJSONObject = jsonObj != null ? jsonObj.optJSONObject("mirroring") : null;
            String optString = optJSONObject != null ? optJSONObject.optString("displayOrientation") : null;
            if (optString == null) {
                return;
            }
            Logger.debug("Display rotated");
            UibcAccessibilityService.onDisplayRotated(optString);
            if (MirroringService.this.mMirroringSinkCapability != null && MirroringService.this.mMirroringSinkCapability.isSupportPortraitMode()) {
                Logger.debug("onDisplayRotated (displayOrientation=%s, phoneOrientation=%s)", optString, Integer.valueOf(AppUtil.getOrientation(MirroringService.this.getBaseContext())));
                MirroringService.this.mMirroringSinkCapability.displayOrientation = optString;
                if (!MirroringService.this.mMirroringSinkCapability.isDisplayLandscape() || MirroringService.this.mLandscapeVideoCapture.getStatus() != 2) {
                    if (!MirroringService.this.mMirroringSinkCapability.isDisplayPortrait() || MirroringService.this.mPortraitVideoCapture.getStatus() != 2) {
                        if (MirroringService.this.mMirroringSinkCapability.isDisplayLandscape()) {
                            MirroringService.this.mPortraitVideoCapture.pause();
                            MirroringService.this.mLandscapeVideoCapture.start();
                        } else {
                            MirroringService.this.mLandscapeVideoCapture.pause();
                            MirroringService.this.mPortraitVideoCapture.start();
                        }
                        JSONObject createVideoSizeInfo = MirroringServiceFunc.createVideoSizeInfo(MirroringService.this.getBaseContext(), MirroringService.this.mMirroringSinkCapability);
                        if (MirroringService.this.mConnectionManager != null) {
                            MirroringService.this.mConnectionManager.updateSourceDeviceCapability(createVideoSizeInfo);
                            return;
                        }
                        return;
                    }
                    Logger.error("Phone is already PORTRAIT");
                    return;
                }
                Logger.error("Phone is already LANDSCAPE");
                return;
            }
            Logger.error("TV does not support PORTRAIT mode");
        }

        @Override
        public void onError(ConnectionManagerError connectionError, String errorMessage) {
            Logger.error("onError: connectionError=%s, errorMessage=%s", connectionError, errorMessage);
            TimerUtil.schedule(() -> MirroringServiceIF.notifyError(getBaseContext(), MirroringServiceIF.toMirroringError(connectionError)), 150);
            stop();
        }
    }

    private void executeStart(Intent intent) {
        ConnectionManag manag = new ConnectionManag(intent);
        Logger.print("executeStart", new Object[0]);
        start(intent, manag);
    }

    private void executeStop() {
        Logger.print("executeStop", new Object[0]);
        stop();
        MirroringServiceIF.respondStop(this, true);
    }

    private void executeStopByNotification() {
        Logger.print("executeStopByNotification", new Object[0]);
        stop();
        MirroringServiceIF.notifyError(this, MirroringServiceError.ERROR_STOPPED_BY_NOTIFICATION);
    }

    private void start(Intent intent, ConnectionManagerListener connectionListener) {
        Logger.print("stop", new Object[0]);
        initializeService();
        openTvConnection(intent, connectionListener);
    }

    public void stop() {
        Logger.print("stop");
        stopCaptureAndStreaming();
        closeTvConnection();
        terminateService();
    }

    private void initializeService() {
        Logger.print("initializeService (SDK version=%s)", IOUtil.readRawResourceText(this, R.raw.lgcast_version));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(4096, MirroringServiceFunc.createNotification(this), ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION);
        }
        startService(new Intent(this, UibcAccessibilityService.class).setAction(UibcAccessibilityService.START_SERVICE));
        MirroringServiceEvent mirroringServiceEvent = new MirroringServiceEvent(this);
        this.mMirroringServiceEvent = mirroringServiceEvent;
        mirroringServiceEvent.startScreenOnOffReceiver(new MirroringServiceEvent.ScreenOnOffListener() {
            @Override
            public void onScreenOnOffChanged(boolean z) {
                ConnectionManager connectionManager = mConnectionManager;
                if (connectionManager != null) {
                    connectionManager.notifyScreenOnOff(z);
                }
            }
        });
        this.mMirroringServiceEvent.startAccessibilitySettingObserver(new MirroringServiceEvent.AccessibilitySettingListener() {
            @Override
            public void onAccessibilitySettingChanged(boolean z) {
                JSONObject createUibcInfo = MirroringServiceFunc.createUibcInfo(getBaseContext());
                ConnectionManager connectionManager = mConnectionManager;
                if (connectionManager != null) {
                    connectionManager.updateSourceDeviceCapability(createUibcInfo);
                }
            }
        });
        MirroringVolume mirroringVolume = new MirroringVolume(this);
        this.mMirroringVolume = mirroringVolume;
        mirroringVolume.startMute();
    }

    private void terminateService() {
        Logger.print("terminateService", new Object[0]);
        MirroringVolume mirroringVolume = this.mMirroringVolume;
        if (mirroringVolume != null) {
            mirroringVolume.stopMute();
        }
        this.mMirroringVolume = null;
        MirroringServiceEvent mirroringServiceEvent = this.mMirroringServiceEvent;
        if (mirroringServiceEvent != null) {
            mirroringServiceEvent.quit();
        }
        this.mMirroringServiceEvent = null;
        startService(new Intent(this, UibcAccessibilityService.class).setAction(UibcAccessibilityService.STOP_SERVICE));
        stopForeground(true);
        ThreadUtil.runOnMainLooper(new Runnable() {
            @Override
            public void run() {
                MirroringService.stopSelfService(MirroringService.this);
            }
        }, 150L);
    }

    private void openTvConnection(Intent intent, ConnectionManagerListener connectionListener) {
        Logger.print("openTvConnection", new Object[0]);
        ConnectableDevice deviceByIpAddress = DiscoveryManager.getInstance().getDeviceByIpAddress(MirroringServiceFunc.getDeviceIpAddress(intent));
        ConnectionManager connectionManager = new ConnectionManager("mirroring");
        this.mConnectionManager = connectionManager;
        connectionManager.openConnection(deviceByIpAddress, connectionListener);
    }

    private void closeTvConnection() {
        Logger.print("closeTvConnection", new Object[0]);
        ConnectionManager connectionManager = this.mConnectionManager;
        if (connectionManager != null) {
            connectionManager.closeConnection();
        }
        this.mConnectionManager = null;
    }

    public boolean startCaptureAndStreaming(Intent intent) {
        Logger.print("startCaptureAndStreaming", new Object[0]);
        try {
            Point captureSizeInLandscape = MirroringServiceFunc.getCaptureSizeInLandscape(this);
            int i = captureSizeInLandscape.x;
            int i2 = captureSizeInLandscape.y;
            int i3 = 6000000;
            float totalMemorySpace = ((((float) DeviceUtil.getTotalMemorySpace(this)) / 1024.0f) / 1024.0f) / 1024.0f;
            double d = totalMemorySpace;
            if (d <= 3.0d) {
                i3 = ScreenMirroringConfig.Video.BITRATE_1MB;
            } else if (d <= 4.0d) {
                i3 = ScreenMirroringConfig.Video.BITRATE_4MB;
            }
            int i4 = i3;
            Logger.error("### width=%d, height=%d, totalMemory=%f, bitrate=%d ###", Integer.valueOf(i), Integer.valueOf(i2), Float.valueOf(totalMemorySpace), Integer.valueOf(i4));
            MediaProjection mediaProjection = MirroringServiceFunc.getMediaProjection(this, intent);
            this.mMediaProjection = mediaProjection;
            if (mediaProjection == null) {
                throw new Exception("Invalid projection");
            }
            RTPStreaming rTPStreaming = new RTPStreaming();
            this.mRTPStreaming = rTPStreaming;
            rTPStreaming.setStreamingConfig(MirroringServiceFunc.createRtpVideoConfig(i4), MirroringServiceFunc.createRtpAudioConfig(), MirroringServiceFunc.createRtpSecurityConfig(this.mMirroringSourceCapability.masterKeys));
            this.mRTPStreaming.open(this, 1356955624L, this.mMirroringSinkCapability.ipAddress, this.mMirroringSinkCapability.videoUdpPort, this.mMirroringSinkCapability.audioUdpPort);
            AudioCaptureIF audioCaptureIF = new AudioCaptureIF(ScreenMirroringConfig.Audio.SAMPLING_RATE, 2);
            this.mAudioCapture = audioCaptureIF;
            audioCaptureIF.setErrorListener(new CaptureErrorListener() {
                @Override
                public void onError() {
                    MirroringService.stopService(MirroringService.this);
                }
            });
            this.mAudioCapture.startCapture(this.mMediaProjection, this.mRTPStreaming.getAudioStreamHandler());
            VideoCaptureIF videoCaptureIF = new VideoCaptureIF("land");
            this.mLandscapeVideoCapture = videoCaptureIF;
            videoCaptureIF.setErrorListener(new CaptureErrorListener() {
                @Override
                public void onError() {
                    MirroringService.stopService(MirroringService.this);
                }
            });
            this.mLandscapeVideoCapture.prepare(i, i2, i4, this.mMediaProjection, this.mRTPStreaming.getVideoStreamHandler());
            VideoCaptureIF videoCaptureIF2 = new VideoCaptureIF("port");
            this.mPortraitVideoCapture = videoCaptureIF2;
            videoCaptureIF2.setErrorListener(new CaptureErrorListener() {
                @Override
                public void onError() {
                    MirroringService.stopService(MirroringService.this);
                }
            });
            if (!MirroringServiceFunc.isDualScreen(intent)) {
                Logger.debug("Prepare portrait capture", new Object[0]);
                this.mPortraitVideoCapture.prepare(i2, i, i4, this.mMediaProjection, this.mRTPStreaming.getVideoStreamHandler());
            }
            if (this.mMirroringSinkCapability.isDisplayPortrait()) {
                this.mPortraitVideoCapture.start();
            } else {
                this.mLandscapeVideoCapture.start();
            }
            return true;
        } catch (Exception e) {
            Logger.error(e);
            return false;
        }
    }

    private void startCaptureAndStreaming3() {
        Logger.debug("Test master key update", new Object[0]);
        RTPStreaming rTPStreaming = this.mRTPStreaming;
        if (rTPStreaming != null) {
            rTPStreaming.updateMasterKey();
        }
    }

    private void stopCaptureAndStreaming() {
        Logger.print("stopCaptureAndStreaming", new Object[0]);
        VideoCaptureIF videoCaptureIF = this.mPortraitVideoCapture;
        if (videoCaptureIF != null) {
            videoCaptureIF.stop();
        }
        this.mPortraitVideoCapture = null;
        VideoCaptureIF videoCaptureIF2 = this.mLandscapeVideoCapture;
        if (videoCaptureIF2 != null) {
            videoCaptureIF2.stop();
        }
        this.mLandscapeVideoCapture = null;
        AudioCaptureIF audioCaptureIF = this.mAudioCapture;
        if (audioCaptureIF != null) {
            audioCaptureIF.stopCapture();
        }
        this.mAudioCapture = null;
        RTPStreaming rTPStreaming = this.mRTPStreaming;
        if (rTPStreaming != null) {
            rTPStreaming.close();
        }
        this.mRTPStreaming = null;
        MediaProjection mediaProjection = this.mMediaProjection;
        if (mediaProjection != null) {
            mediaProjection.stop();
        }
        this.mMediaProjection = null;
    }
}
