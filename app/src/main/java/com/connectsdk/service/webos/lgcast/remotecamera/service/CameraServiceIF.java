package com.connectsdk.service.webos.lgcast.remotecamera.service;

import android.content.Context;
import android.content.Intent;
import android.view.Surface;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.connectsdk.service.capability.RemoteCameraControl;
import com.connectsdk.service.webos.lgcast.common.connection.ConnectionManagerError;


public class CameraServiceIF {
    public static final String ACTION_NOTIFY_ERROR = "CameraServiceIF:ACTION_NOTIFY_ERROR";
    public static final String ACTION_NOTIFY_PAIRING = "CameraServiceIF:ACTION_NOTIFY_PAIRING";
    public static final String ACTION_NOTIFY_PLAYING = "CameraServiceIF:ACTION_NOTIFY_PLAYING";
    public static final String ACTION_NOTIFY_PROPERTY_CHANGE = "CameraServiceIF:ACTION_NOTIFY_PROPERTY_CHANGE";
    public static final String ACTION_SET_LENS_FACING = "CameraServiceIF:ACTION_SET_LENS_FACING";
    public static final String ACTION_SET_MIC_MUTE = "CameraServiceIF:ACTION_SET_MIC_MUTE";
    public static final String ACTION_START_REQUEST = "CameraServiceIF:ACTION_START_REQUEST";
    public static final String ACTION_START_RESPONSE = "CameraServiceIF:ACTION_START_RESPONSE";
    public static final String ACTION_STOP_BY_NOTIFICATION = "CameraServiceIF:ACTION_STOP_BY_NOTIFICATION";
    public static final String ACTION_STOP_REQUEST = "CameraServiceIF:ACTION_STOP_REQUEST";
    public static final String ACTION_STOP_RESPONSE = "CameraServiceIF:ACTION_STOP_RESPONSE";
    public static final String EXTRA_DEVICE_IP_ADDRESS = "CameraServiceIF:EXTRA_DEVICE_IP_ADDRESS";
    public static final String EXTRA_ERROR = "CameraServiceIF:EXTRA_ERROR";
    public static final String EXTRA_LENS_FACING = "CameraServiceIF:EXTRA_LENS_FACING";
    public static final String EXTRA_MIC_MUTE = "CameraServiceIF:EXTRA_MIC_MUTE";
    public static final String EXTRA_PREVIEW_SURFACE = "CameraServiceIF:EXTRA_PREVIEW_SURFACE";
    public static final String EXTRA_PROPERTY = "CameraServiceIF:EXTRA_PROPERTY";
    public static final String EXTRA_RESULT = "CameraServiceIF:EXTRA_RESULT";
    public static final int LENS_FACING_BACK = 1;
    public static final int LENS_FACING_FRONT = 0;

    public static void requestStart(Context context, Surface previewSurface, String deviceIpAddress, boolean micMute, int lensFacing) {
        Intent intent = new Intent(context, CameraService.class);
        intent.setAction(ACTION_START_REQUEST);
        intent.putExtra(EXTRA_PREVIEW_SURFACE, previewSurface);
        intent.putExtra(EXTRA_DEVICE_IP_ADDRESS, deviceIpAddress);
        intent.putExtra(EXTRA_MIC_MUTE, micMute);
        intent.putExtra(EXTRA_LENS_FACING, lensFacing);
        context.startForegroundService(intent);
    }

    public static void respondStart(Context context, boolean isSuccess) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_START_RESPONSE).putExtra(EXTRA_RESULT, isSuccess));
    }

    public static void requestStop(Context context) {
        Intent intent = new Intent(context, CameraService.class);
        intent.setAction(ACTION_STOP_REQUEST);
        context.startService(intent);
    }

    public static void respondStop(Context context, boolean isSuccess) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_START_RESPONSE).putExtra(EXTRA_RESULT, isSuccess));
    }

    public static void setMicMute(Context context, boolean micMute) {
        Intent intent = new Intent(context, CameraService.class);
        intent.setAction(ACTION_SET_MIC_MUTE);
        intent.putExtra(EXTRA_MIC_MUTE, micMute);
        context.startForegroundService(intent);
    }

    public static void setLensFacing(Context context, int lensFacing) {
        Intent intent = new Intent(context, CameraService.class);
        intent.setAction(ACTION_SET_LENS_FACING);
        intent.putExtra(EXTRA_LENS_FACING, lensFacing);
        context.startForegroundService(intent);
    }

    public static void notifyPairing(Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_NOTIFY_PAIRING));
    }

    public static void notifyPlaying(Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_NOTIFY_PLAYING));
    }

    public static void notifyPropertyChange(Context context, RemoteCameraControl.RemoteCameraProperty property) {
        Intent intent = new Intent(ACTION_NOTIFY_PROPERTY_CHANGE);
        intent.putExtra(EXTRA_PROPERTY, property);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static CameraServiceError toCameraError(ConnectionManagerError connectionError) {
        return connectionError == ConnectionManagerError.CONNECTION_CLOSED ? CameraServiceError.ERROR_CONNECTION_CLOSED : connectionError == ConnectionManagerError.DEVICE_SHUTDOWN ? CameraServiceError.ERROR_DEVICE_SHUTDOWN : connectionError == ConnectionManagerError.RENDERER_TERMINATED ? CameraServiceError.ERROR_RENDERER_TERMINATED : CameraServiceError.ERROR_GENERIC;
    }

    public static void notifyError(Context context, CameraServiceError cameraError) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_NOTIFY_ERROR).putExtra(EXTRA_ERROR, cameraError));
    }
}
