package com.connectsdk.service.webos.lgcast.screenmirroring.service;

import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.connectsdk.service.webos.lgcast.common.connection.ConnectionManagerError;
import com.connectsdk.service.webos.lgcast.common.utils.AudioUtil;


public class MirroringServiceIF {
    public static final String ACTION_NOTIFY_ERROR = "MirroringServiceIF:ACTION_NOTIFY_ERROR";
    public static final String ACTION_NOTIFY_PAIRING = "MirroringServiceIF:ACTION_NOTIFY_PAIRING";
    public static final String ACTION_START_REQUEST = "MirroringServiceIF:ACTION_START_REQUEST";
    public static final String ACTION_START_RESPONSE = "MirroringServiceIF:ACTION_START_RESPONSE";
    public static final String ACTION_STOP_BY_NOTIFICATION = "MirroringServiceIF:ACTION_STOP_BY_NOTIFICATION";
    public static final String ACTION_STOP_REQUEST = "MirroringServiceIF:ACTION_STOP_REQUEST";
    public static final String ACTION_STOP_RESPONSE = "MirroringServiceIF:ACTION_STOP_RESPONSE";
    public static final String EXTRA_DEVICE_IP_ADDRESS = "MirroringServiceIF:EXTRA_DEVICE_IP_ADDRESS";
    public static final String EXTRA_ERROR = "MirroringServiceIF:EXTRA_ERROR";
    public static final String EXTRA_IS_DUAL_SCREEN = "MirroringServiceIF:EXTRA_IS_DUAL_SCREEN";
    public static final String EXTRA_PACKAGE_NAME = "MirroringServiceIF:EXTRA_PACKAGE_NAME";
    public static final String EXTRA_PROJECTION_DATA = "MirroringServiceIF:EXTRA_PROJECTION_DATA";
    public static final String EXTRA_RESULT = "MirroringServiceIF:EXTRA_RESULT";

    public static void requestStart(Context context, Intent projectionData, String deviceIpAddress, boolean isDualScreen) {
        Intent intent = new Intent(context, MirroringService.class);
        intent.setAction(ACTION_START_REQUEST);
        intent.putExtra(EXTRA_PROJECTION_DATA, projectionData);
        intent.putExtra(EXTRA_DEVICE_IP_ADDRESS, deviceIpAddress);
        intent.putExtra(EXTRA_IS_DUAL_SCREEN, isDualScreen);
        intent.putExtra(EXTRA_PACKAGE_NAME, context.getPackageName());
        context.startForegroundService(intent);
    }

    public static void respondStart(Context context, boolean result, boolean isSecondScreen) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_START_RESPONSE).putExtra(EXTRA_RESULT, result));
    }

    public static void requestStop(Context context) {
        Intent intent = new Intent(context, MirroringService.class);
        intent.setAction(ACTION_STOP_REQUEST);
        context.startService(intent);
    }

    public static void respondStop(Context context, boolean result) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_STOP_RESPONSE).putExtra(EXTRA_RESULT, true));
    }

    public static void notifyPairing(Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_NOTIFY_PAIRING));
    }

    public static MirroringServiceError toMirroringError(ConnectionManagerError connectionError) {
        return connectionError == ConnectionManagerError.CONNECTION_CLOSED ? MirroringServiceError.ERROR_CONNECTION_CLOSED : connectionError == ConnectionManagerError.DEVICE_SHUTDOWN ? MirroringServiceError.ERROR_DEVICE_SHUTDOWN : connectionError == ConnectionManagerError.RENDERER_TERMINATED ? MirroringServiceError.ERROR_RENDERER_TERMINATED : MirroringServiceError.ERROR_GENERIC;
    }

    public static void notifyError(Context context, MirroringServiceError mirroringError) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_NOTIFY_ERROR).putExtra(EXTRA_ERROR, mirroringError));
        AudioUtil.setUnmute(context);
    }
}
