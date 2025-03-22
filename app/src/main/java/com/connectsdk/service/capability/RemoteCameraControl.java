package com.connectsdk.service.capability;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.view.Surface;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.webos.lgcast.common.utils.AppUtil;
import com.connectsdk.service.webos.lgcast.common.utils.IOUtil;
import com.connectsdk.service.webos.lgcast.common.utils.StringUtil;
import com.connectsdk.service.webos.lgcast.remotecamera.service.CameraService;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;

import java.util.ArrayList;

public interface RemoteCameraControl extends CapabilityMethods {

    public static final String Any = "RemoteCameraControl.Any";
    public static final int LENS_FACING_BACK = 1;
    public static final int LENS_FACING_FRONT = 0;
    public static final String RemoteCamera = "RemoteCameraControl.RemoteCamera";
    public static final String[] Capabilities = {RemoteCamera};

    public enum RemoteCameraError {
        ERROR_GENERIC,
        ERROR_CONNECTION_CLOSED,
        ERROR_DEVICE_SHUTDOWN,
        ERROR_RENDERER_TERMINATED,
        ERROR_STOPPED_BY_NOTIFICATION
    }

    
    public interface RemoteCameraErrorListener {
        void onError(RemoteCameraError error);
    }

    
    public interface RemoteCameraPlayingListener {
        void onPlaying();
    }

    
    public enum RemoteCameraProperty {
        UNKNOWN,
        RESOLUTION,
        LENS_FACING,
        BRIGHTNESS,
        WHITE_BALANCE,
        AUTO_WHITE_BALANCE,
        AUDIO
    }

    
    public interface RemoteCameraPropertyChangeListener {
        void onChange(RemoteCameraProperty property);
    }

    
    public interface RemoteCameraStartListener {
        void onPairing();

        void onStart(boolean result);
    }

    
    public interface RemoteCameraStopListener {
        void onStop(boolean result);
    }

    RemoteCameraControl getRemoteCameraControl();

    void setCameraPlayingListener(Context context, RemoteCameraPlayingListener playingListener);

    void setErrorListener(Context context, RemoteCameraErrorListener errorListener);

    void setLensFacing(Context context, int lensFacing);

    void setMicMute(Context context, boolean micMute);

    void setPropertyChangeListener(Context context, RemoteCameraPropertyChangeListener propertyChangeListener);

    void startRemoteCamera(Context context, Surface previewSurface, boolean micMute, int lensFacing, RemoteCameraStartListener startListener);

    void stopRemoteCamera(Context context, RemoteCameraStopListener stopListener);

    static int getSdkVersion(Context context) {
        return StringUtil.toInteger(IOUtil.readRawResourceText(context, R.raw.lgcast_version), -1);
    }

    static boolean isCompatibleOsVersion() {
        return Build.VERSION.SDK_INT >= 24;
    }

    static boolean isRunning(Context context) {
        ActivityManager.RunningServiceInfo serviceInfo = AppUtil.getServiceInfo(context, CameraService.class.getName());
        if (serviceInfo != null) {
            return serviceInfo.foreground;
        }
        return false;
    }

    static boolean isSupportRemoteCamera(String deviceId) {
        ConnectableDevice deviceById = DiscoveryManager.getInstance().getDeviceById(deviceId);
        return (deviceById != null ? deviceById.getCapabilities() : new ArrayList<>()).contains(RemoteCamera);
    }
}
