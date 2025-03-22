package com.connectsdk.service.capability;

import android.app.ActivityManager;
import android.app.Presentation;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.webos.lgcast.common.utils.AppUtil;
import com.connectsdk.service.webos.lgcast.common.utils.IOUtil;
import com.connectsdk.service.webos.lgcast.common.utils.StringUtil;
import com.connectsdk.service.webos.lgcast.screenmirroring.service.MirroringService;
import com.connectsdk.service.webos.lgcast.screenmirroring.uibc.UibcAccessibilityService;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;

import java.util.ArrayList;

public interface ScreenMirroringControl extends CapabilityMethods {

    public static final String Any = "ScreenMirroringControl.Any";
    public static final String ScreenMirroring = "ScreenMirroringControl.ScreenMirroring";
    public static final String[] Capabilities = {ScreenMirroring};

    public enum ScreenMirroringError {
        ERROR_GENERIC,
        ERROR_CONNECTION_CLOSED,
        ERROR_DEVICE_SHUTDOWN,
        ERROR_RENDERER_TERMINATED,
        ERROR_STOPPED_BY_NOTIFICATION
    }


    public interface ScreenMirroringErrorListener {
        void onError(ScreenMirroringError screenMirroringError);
    }


    public interface ScreenMirroringStartListener {
        void onPairing();

        void onStart(boolean result, Presentation secondScreen);
    }


    public interface ScreenMirroringStopListener {
        void onStop(boolean result);
    }

    ScreenMirroringControl getScreenMirroringControl();

    void setErrorListener(Context context, ScreenMirroringErrorListener errorListener);

    void startScreenMirroring(Context context, Intent projectionData, ScreenMirroringStartListener onStartListener);

    void startScreenMirroring(Context context, Intent projectionData, Class secondScreenClass, ScreenMirroringStartListener onStartListener);

    void stopScreenMirroring(Context context, ScreenMirroringStopListener stopListener);

    static int getSdkVersion(Context context) {
        return StringUtil.toInteger(IOUtil.readRawResourceText(context, R.raw.lgcast_version), -1);
    }

    static boolean isCompatibleOsVersion() {
        return Build.VERSION.SDK_INT >= 29;
    }

    static boolean isRunning(Context context) {
        ActivityManager.RunningServiceInfo serviceInfo = AppUtil.getServiceInfo(context, MirroringService.class.getName());
        if (serviceInfo != null) {
            return serviceInfo.foreground;
        }
        return false;
    }

    static boolean isSupportScreenMirroring(String deviceId) {
        ConnectableDevice deviceById = DiscoveryManager.getInstance().getDeviceById(deviceId);
        return (deviceById != null ? deviceById.getCapabilities() : new ArrayList<>()).contains(ScreenMirroring);
    }

    static boolean isUibcEnabled(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        return string != null && string.contains(new StringBuilder().append(context.getPackageName()).append("/").append(UibcAccessibilityService.class.getName()).toString());
    }
}
