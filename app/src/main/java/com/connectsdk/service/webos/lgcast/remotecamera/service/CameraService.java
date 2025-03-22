package com.connectsdk.service.webos.lgcast.remotecamera.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Size;
import android.view.Surface;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.capability.RemoteCameraControl;
import com.connectsdk.service.webos.lgcast.common.connection.ConnectionManager;
import com.connectsdk.service.webos.lgcast.common.connection.ConnectionManagerError;
import com.connectsdk.service.webos.lgcast.common.connection.ConnectionManagerListener;
import com.connectsdk.service.webos.lgcast.common.connection.MobileDescription;
import com.connectsdk.service.webos.lgcast.common.streaming.RTPStreaming;
import com.connectsdk.service.webos.lgcast.common.transfer.RTPStreamerConfig;
import com.connectsdk.service.webos.lgcast.common.utils.AppUtil;
import com.connectsdk.service.webos.lgcast.common.utils.HandlerThreadEx;
import com.connectsdk.service.webos.lgcast.common.utils.JSONObjectEx;
import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.connectsdk.service.webos.lgcast.common.utils.StringUtil;
import com.connectsdk.service.webos.lgcast.common.utils.ThreadUtil;
import com.connectsdk.service.webos.lgcast.common.utils.TimerUtil;
import com.connectsdk.service.webos.lgcast.remotecamera.capability.CameraSinkCapability;
import com.connectsdk.service.webos.lgcast.remotecamera.capability.CameraSourceCapability;
import com.connectsdk.service.webos.lgcast.remotecamera.capture.CameraCapture;
import com.connectsdk.service.webos.lgcast.remotecamera.capture.MicCapture;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;


public class CameraService extends Service {
    private static final String AUDIO_PORT = "audioPort";
    private static final String CAMERA = "camera";
    public static final String ERROR_CAUSE = "errorCause";
    public static final String RESULT = "result";
    private static final String VIDEO_PORT = "videoPort";
    private CameraCapture mCameraCapture;
    private CameraProperty mCameraProperty;
    private CameraSinkCapability mCameraSinkCapability;
    private CameraSourceCapability mCameraSourceCapability;
    private ConnectionManager mConnectionManager;
    private AtomicBoolean mIsPlaying;
    private MicCapture mMicCapture;
    private RTPStreaming mRTPStreaming;
    private HandlerThreadEx mServiceHandler;

    public static void stopSelfService(CameraService cameraService) {
        cameraService.stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.showDebug(false);
        HandlerThreadEx handlerThreadEx = new HandlerThreadEx("CameraService Handler");
        this.mServiceHandler = handlerThreadEx;
        handlerThreadEx.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mServiceHandler.quit();
        this.mServiceHandler = null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Logger.print("onStartCommand: " + StringUtil.toString(intent));
        final String action = intent != null ? intent.getAction() : null;
        this.mServiceHandler.post(new Runnable() {
            @Override
            public void run() {
                CameraService.this.onStartCommandCameraService(action, intent);
            }
        });
        return START_STICKY;
    }

    public void onStartCommandCameraService(String str, Intent intent) {
        if (CameraServiceIF.ACTION_START_REQUEST.equals(str)) {
            executeStart(intent);
        } else if (CameraServiceIF.ACTION_STOP_REQUEST.equals(str)) {
            executeStop();
        } else if (CameraServiceIF.ACTION_STOP_BY_NOTIFICATION.equals(str)) {
            executeStopByNotification();
        } else if (CameraServiceIF.ACTION_SET_MIC_MUTE.equals(str)) {
            executeSetMicMute(intent);
        } else if (CameraServiceIF.ACTION_SET_LENS_FACING.equals(str)) {
            executeSetLensFacing(intent);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        CameraProperty cameraProperty = this.mCameraProperty;
        if (cameraProperty == null || this.mConnectionManager == null) {
            return;
        }
        cameraProperty.rotation = AppUtil.getRotationDegree(getBaseContext());
        this.mConnectionManager.updateSourceDeviceCapability(new JSONObjectEx().put(CameraProperty.ROTATION, this.mCameraProperty.rotation).toJSONObject());
    }

    public class C21011 implements ConnectionManagerListener {
        C21011() {
        }

        @Override
        public void onPairingRequested() {
            Logger.debug("onPairingRequested");
            CameraServiceIF.notifyPairing(CameraService.this.getBaseContext());
        }

        @Override
        public void onPairingRejected() {
            Logger.debug("onPairingRejected");
            CameraServiceIF.respondStart(CameraService.this.getBaseContext(), false);
            CameraService.this.stop();
        }

        @Override
        public void onConnectionFailed(String message) {
            Logger.debug("onConnectionFailed (%s)", message);
            CameraServiceIF.respondStart(CameraService.this.getBaseContext(), false);
            CameraService.this.stop();
        }

        @Override
        public void onConnectionCompleted(JSONObject jsonObj) {
            Logger.debug("onConnectionCompleted");
            CameraService.this.mCameraSinkCapability = new CameraSinkCapability(jsonObj);
            CameraService.this.mCameraSinkCapability.debug();
            CameraService cameraService = CameraService.this;
            cameraService.mCameraSourceCapability = CameraSourceCapability.create(cameraService.getBaseContext(), CameraService.this.mCameraSinkCapability.publicKey);
            CameraService.this.mCameraSourceCapability.debug();
            MobileDescription mobileDescription = new MobileDescription(CameraService.this.getBaseContext());
            mobileDescription.debug();
            CameraService.this.mConnectionManager.setSourceDeviceCapability(CameraService.this.mCameraSourceCapability.toJSONObject(), mobileDescription.toJSONObject());
            CameraServiceIF.respondStart(CameraService.this.getBaseContext(), true);
        }

        @Override
        public void onReceivePlayCommand(JSONObject jsonObj) {
            Logger.debug("onReceivePlayCommand");
            JSONObject optJSONObject = jsonObj.optJSONObject(CameraService.CAMERA);
            if (optJSONObject == null) {
                return;
            }
            int optInt = optJSONObject.optInt("width", CameraService.this.mCameraProperty.width);
            int optInt2 = optJSONObject.optInt("height", CameraService.this.mCameraProperty.height);
            int optInt3 = optJSONObject.optInt(CameraProperty.FACING, CameraService.this.mCameraProperty.facing);
            Logger.debug("Before: width=%d, height=%d", optInt, optInt2);
            Iterator<Size> it = CameraService.this.mCameraSourceCapability.mSupportedPreviewSizes.iterator();
            boolean z = false;
            while (it.hasNext()) {
                Size next = it.next();
                if (optInt == next.getWidth() && optInt2 == next.getHeight()) {
                    z = true;
                }
            }
            if (!z) {
                optInt = 1280;
            }
            if (!z) {
                optInt2 = 720;
            }
            Logger.debug("After: width=%d, height=%d", optInt, optInt2);
            if (optInt != CameraService.this.mCameraProperty.width || optInt2 != CameraService.this.mCameraProperty.height || optInt3 != CameraService.this.mCameraProperty.facing) {
                CameraService.this.mCameraProperty.width = optInt;
                CameraService.this.mCameraProperty.height = optInt2;
                CameraService.this.mCameraProperty.facing = optInt3;
                Logger.debug("Restarting with width=%d, height=%d, facing=%d", CameraService.this.mCameraProperty.width, CameraService.this.mCameraProperty.height, CameraService.this.mCameraProperty.facing);
                CameraService.this.mCameraCapture.restartPreview(CameraService.this.mCameraProperty);
                ThreadUtil.sleep(10L);
            } else {
                Logger.debug("No changes in current preview");
            }
            if (optJSONObject.has(CameraService.VIDEO_PORT) && optJSONObject.has(CameraService.AUDIO_PORT)) {
                CameraService.this.startStreaming(optJSONObject.optInt(CameraService.VIDEO_PORT), optJSONObject.optInt(CameraService.AUDIO_PORT));
                CameraServiceIF.notifyPlaying(CameraService.this.getBaseContext());
                return;
            }
            CameraServiceIF.notifyError(CameraService.this.getBaseContext(), CameraServiceError.ERROR_GENERIC);
        }

        @Override
        public void onReceiveStopCommand(JSONObject jsonObj) {
            Logger.debug("onReceiveStopCommand");
            CameraService.this.stopStreaming();
        }

        @Override
        public void onReceiveGetParameter(JSONObject jsonObj) {
            Logger.debug("onReceiveGetParameter");
            CameraService.this.mCameraProperty.rotation = AppUtil.getRotationDegree(CameraService.this.getBaseContext());
            CameraService.this.mConnectionManager.sendGetParameterResponse(CameraService.this.mCameraProperty.toJSONObject());
        }

        @Override
        public void onReceiveSetParameter(JSONObject jsonObj) {
            Logger.debug("onReceiveSetParameter - " + jsonObj);
            JSONObject optJSONObject = jsonObj.optJSONObject(CameraService.CAMERA);
            if (optJSONObject == null) {
                return;
            }
            if (optJSONObject.has(CameraProperty.BRIGHTNESS)) {
                onChangeBrightness(optJSONObject);
            } else if (optJSONObject.has(CameraProperty.WHITE_BALANCE)) {
                onChangeWhiteBalance(optJSONObject);
            } else if (optJSONObject.has(CameraProperty.AUTO_WHITE_BALANCE)) {
                onChangeAutoWhiteBalance(optJSONObject);
            } else if (optJSONObject.has(CameraProperty.AUDIO)) {
                onChangeAudio(optJSONObject);
            } else if (optJSONObject.has(CameraProperty.FACING)) {
                onChangeFacing(optJSONObject);
            } else {
                sendSetParameterResponse(false, optJSONObject, RemoteCameraControl.RemoteCameraProperty.UNKNOWN);
            }
        }

        @Override
        public void onError(final ConnectionManagerError connectionError, String errorMessage) {
            Logger.error("onError: connectionError=%s, errorMessage=%s", connectionError, errorMessage);
            TimerUtil.schedule(new TimerUtil.TimerListener() {
                @Override
                public void onTime() {
                    C21011.this.onErrorOnTime(connectionError);
                }
            }, 100L);
            CameraService.this.stop();
        }

        public void onErrorOnTime(ConnectionManagerError connectionManagerError) {
            CameraServiceIF.notifyError(CameraService.this.getBaseContext(), CameraServiceIF.toCameraError(connectionManagerError));
        }

        private void onChangeBrightness(JSONObject cameraObj) {
            int optInt = cameraObj.optInt(CameraProperty.BRIGHTNESS);
            boolean changeBrightness = CameraService.this.mCameraCapture.changeBrightness(optInt);
            CameraProperty cameraProperty = CameraService.this.mCameraProperty;
            if (!changeBrightness) {
                optInt = CameraService.this.mCameraProperty.brightness;
            }
            cameraProperty.brightness = optInt;
            sendSetParameterResponse(changeBrightness, cameraObj, RemoteCameraControl.RemoteCameraProperty.BRIGHTNESS);
        }

        private void onChangeWhiteBalance(JSONObject cameraObj) {
            int optInt = cameraObj.optInt(CameraProperty.WHITE_BALANCE);
            boolean changeWhiteBalance = CameraService.this.mCameraCapture.changeWhiteBalance(optInt);
            if (changeWhiteBalance) {
                CameraService.this.mCameraCapture.changeAutoWhiteBalance(false);
                CameraService.this.mCameraProperty.autoWhiteBalance = false;
                CameraService.this.mCameraProperty.whiteBalance = optInt;
            }
            sendSetParameterResponse(changeWhiteBalance, cameraObj, RemoteCameraControl.RemoteCameraProperty.WHITE_BALANCE);
        }

        private void onChangeAutoWhiteBalance(JSONObject cameraObj) {
            boolean optBoolean = cameraObj.optBoolean(CameraProperty.AUTO_WHITE_BALANCE);
            boolean changeAutoWhiteBalance = CameraService.this.mCameraCapture.changeAutoWhiteBalance(optBoolean);
            CameraProperty cameraProperty = CameraService.this.mCameraProperty;
            if (!changeAutoWhiteBalance) {
                optBoolean = CameraService.this.mCameraProperty.autoWhiteBalance;
            }
            cameraProperty.autoWhiteBalance = optBoolean;
            sendSetParameterResponse(changeAutoWhiteBalance, cameraObj, RemoteCameraControl.RemoteCameraProperty.AUTO_WHITE_BALANCE);
        }

        private void onChangeAudio(JSONObject cameraObj) {
            boolean optBoolean = cameraObj.optBoolean(CameraProperty.AUDIO);
            boolean changeMicMute = CameraService.this.mMicCapture.changeMicMute(!optBoolean);
            CameraProperty cameraProperty = CameraService.this.mCameraProperty;
            if (!changeMicMute) {
                optBoolean = CameraService.this.mCameraProperty.audio;
            }
            cameraProperty.audio = optBoolean;
            sendSetParameterResponse(changeMicMute, cameraObj, RemoteCameraControl.RemoteCameraProperty.AUDIO);
        }

        private void onChangeFacing(JSONObject cameraObj) {
            CameraService.this.mCameraProperty.facing = cameraObj.optInt(CameraProperty.FACING);
            sendSetParameterResponse(CameraService.this.mCameraCapture.restartPreview(CameraService.this.mCameraProperty), cameraObj, RemoteCameraControl.RemoteCameraProperty.LENS_FACING);
        }

        private void sendSetParameterResponse(boolean result, JSONObject cameraObj, RemoteCameraControl.RemoteCameraProperty property) {
            JSONObjectEx jSONObjectEx = new JSONObjectEx(cameraObj);
            jSONObjectEx.put("result", result);
            if (result) {
                if (property == RemoteCameraControl.RemoteCameraProperty.WHITE_BALANCE) {
                    jSONObjectEx.put(CameraProperty.AUTO_WHITE_BALANCE, false);
                }
                CameraService.this.mConnectionManager.sendSetParameterResponse(jSONObjectEx);
                CameraServiceIF.notifyPropertyChange(CameraService.this.getBaseContext(), property);
                return;
            }
            Logger.error("Failed to change: " + property);
            jSONObjectEx.put(CameraService.ERROR_CAUSE, "Failed to change: " + property);
            CameraService.this.mConnectionManager.sendSetParameterResponse(jSONObjectEx);
        }
    }

    private void executeStart(Intent intent) {
        C21011 c21011 = new C21011();
        Logger.print("executeStart");
        start(intent, c21011);
    }

    private void executeStop() {
        Logger.print("executeStop");
        stop();
        CameraServiceIF.respondStop(this, true);
    }

    private void executeStopByNotification() {
        Logger.print("executeStopByNotification");
        stop();
        CameraServiceIF.notifyError(this, CameraServiceError.ERROR_STOPPED_BY_NOTIFICATION);
    }

    private void executeSetMicMute(Intent intent) {
        Logger.print("executeSetMicMute");
        try {
            if (this.mMicCapture == null) {
                throw new Exception("Invalid mic status");
            }
            this.mCameraProperty.audio = !intent.getBooleanExtra(CameraServiceIF.EXTRA_MIC_MUTE, false);
            this.mMicCapture.changeMicMute(!this.mCameraProperty.audio);
            this.mConnectionManager.updateSourceDeviceCapability(new JSONObjectEx().put(CameraProperty.AUDIO, this.mCameraProperty.audio).toJSONObject());
            CameraServiceIF.notifyPropertyChange(getBaseContext(), RemoteCameraControl.RemoteCameraProperty.AUDIO);
        } catch (Exception e) {
            Logger.error(e);
            CameraServiceIF.notifyError(this, CameraServiceError.ERROR_GENERIC);
            stop();
        }
    }

    private void executeSetLensFacing(Intent intent) {
        Logger.print("executeSetLensFacing");
        try {
            if (this.mCameraCapture == null) {
                throw new Exception("Invalid camera status");
            }
            this.mCameraProperty.facing = intent.getIntExtra(CameraServiceIF.EXTRA_LENS_FACING, 0);
            this.mCameraCapture.restartPreview(this.mCameraProperty);
            this.mConnectionManager.updateSourceDeviceCapability(new JSONObjectEx().put(CameraProperty.FACING, this.mCameraProperty.facing).toJSONObject());
            CameraServiceIF.notifyPropertyChange(getBaseContext(), RemoteCameraControl.RemoteCameraProperty.LENS_FACING);
        } catch (Exception e) {
            Logger.error(e);
            CameraServiceIF.notifyError(this, CameraServiceError.ERROR_GENERIC);
            stop();
        }
    }

    private void start(Intent intent, ConnectionManagerListener connectionListener) {
        Logger.print("stop");
        try {
            int intExtra = intent.getIntExtra(CameraServiceIF.EXTRA_LENS_FACING, 0);
            boolean booleanExtra = intent.getBooleanExtra(CameraServiceIF.EXTRA_MIC_MUTE, false);
            CameraProperty cameraProperty = new CameraProperty();
            this.mCameraProperty = cameraProperty;
            cameraProperty.facing = intExtra;
            this.mCameraProperty.audio = !booleanExtra;
            this.mCameraProperty.debug();
            AtomicBoolean atomicBoolean = new AtomicBoolean();
            this.mIsPlaying = atomicBoolean;
            atomicBoolean.set(false);
            initializeService(intent);
            startCameraPreview(intent);
            startMicCapture(intent);
            openTvConnection(intent, connectionListener);
        } catch (Exception e) {
            Logger.error(e);
            CameraServiceIF.respondStart(this, false);
        }
    }

    public void stop() {
        Logger.print("stop");
        stopStreaming();
        closeTvConnection();
        stopMicCapture();
        stopCameraPreview();
        terminateService();
    }

    private void initializeService(Intent intent) {
        startForeground(8192, CameraServiceFunc.createNotification(this));
    }

    private void terminateService() {
        Logger.print("terminateService");
        stopForeground(true);
        ThreadUtil.runOnMainLooper(new Runnable() {
            @Override
            public void run() {
                CameraService.stopSelfService(CameraService.this);
            }
        }, 150L);
    }

    private void startCameraPreview(Intent intent) throws Exception {
        Logger.print("startCameraPreview");
        CameraCapture cameraCapture = new CameraCapture(this, (Surface) intent.getParcelableExtra(CameraServiceIF.EXTRA_PREVIEW_SURFACE), new CameraCapture.ErrorCallback() {
            @Override
            public void onError(String str) {
                CameraServiceIF.notifyError(CameraService.this, CameraServiceError.ERROR_GENERIC);
                stop();
            }
        });
        this.mCameraCapture = cameraCapture;
        cameraCapture.start(this.mCameraProperty);
    }

    private void stopCameraPreview() {
        Logger.print("stopCameraPreview");
        CameraCapture cameraCapture = this.mCameraCapture;
        if (cameraCapture != null) {
            cameraCapture.stop();
        }
        this.mCameraCapture = null;
    }

    private void startMicCapture(Intent intent) {
        Logger.print("startMicCapture");
        MicCapture micCapture = new MicCapture();
        this.mMicCapture = micCapture;
        micCapture.startMicCapture(!this.mCameraProperty.audio);
    }

    private void stopMicCapture() {
        Logger.print("stopMicCapture");
        MicCapture micCapture = this.mMicCapture;
        if (micCapture != null) {
            micCapture.stopMicCapture();
        }
        this.mMicCapture = null;
    }

    private void openTvConnection(Intent intent, ConnectionManagerListener connectionListener) {
        Logger.print("openTvConnection");
        ConnectableDevice deviceByIpAddress = DiscoveryManager.getInstance().getDeviceByIpAddress(CameraServiceFunc.getDeviceIpAddress(intent));
        ConnectionManager connectionManager = new ConnectionManager(CAMERA);
        this.mConnectionManager = connectionManager;
        connectionManager.openConnection(deviceByIpAddress, connectionListener);
    }

    private void closeTvConnection() {
        Logger.print("closeTvConnection");
        ConnectionManager connectionManager = this.mConnectionManager;
        if (connectionManager != null) {
            connectionManager.closeConnection();
        }
        this.mConnectionManager = null;
    }

    public void startStreaming(int videoPort, int audioPort) {
        Logger.print("startStreaming");
        this.mIsPlaying.set(true);
        try {
            RTPStreamerConfig.VideoConfig createRtpVideoConfig = CameraServiceFunc.createRtpVideoConfig(this.mCameraProperty.width, this.mCameraProperty.height);
            RTPStreamerConfig.AudioConfig createRtpAudioConfig = CameraServiceFunc.createRtpAudioConfig();
            RTPStreamerConfig.SecurityConfig createRtpSecurityConfig = CameraServiceFunc.createRtpSecurityConfig(this.mCameraSourceCapability.masterKeys);
            this.mRTPStreaming = new RTPStreaming();
            this.mRTPStreaming.setStreamingConfig(createRtpVideoConfig, createRtpAudioConfig, createRtpSecurityConfig);
            this.mRTPStreaming.open(this, 1356955624L, this.mCameraSinkCapability.ipAddress, videoPort, audioPort);
            this.mMicCapture.startStreaming(this.mRTPStreaming.getAudioStreamHandler());
            this.mCameraCapture.startStreaming(this.mRTPStreaming.getVideoStreamHandler());
        } catch (Exception e) {
            Logger.error(e);
            this.mIsPlaying.set(false);
        }
    }

    public void stopStreaming() {
        Logger.print("stopStreaming");
        this.mIsPlaying.set(false);
        CameraCapture cameraCapture = this.mCameraCapture;
        if (cameraCapture != null) {
            cameraCapture.stopStreaming();
        }
        MicCapture micCapture = this.mMicCapture;
        if (micCapture != null) {
            micCapture.stopStreaming();
        }
        if (this.mRTPStreaming != null) {
            this.mRTPStreaming.close();
        }
        this.mRTPStreaming = null;
    }
}
