package com.connectsdk.service.webos.lgcast.remotecamera.api;

import android.content.Context;
import android.content.Intent;
import android.view.Surface;

import androidx.core.app.ActivityCompat;

import com.connectsdk.service.capability.RemoteCameraControl;
import com.connectsdk.service.webos.lgcast.common.utils.LocalBroadcastEx;
import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.connectsdk.service.webos.lgcast.remotecamera.service.CameraServiceError;
import com.connectsdk.service.webos.lgcast.remotecamera.service.CameraServiceIF;

public class RemoteCameraApi {

    private final LocalBroadcastEx mLocalBroadcastEx;

    private RemoteCameraApi() {
        this.mLocalBroadcastEx = new LocalBroadcastEx();
    }

    public static class LazyHolder {
        private static final RemoteCameraApi INSTANCE = new RemoteCameraApi();

        private LazyHolder() {
        }
    }

    public static RemoteCameraApi getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void startRemoteCamera(Context context, Surface previewSurface, String deviceIpAddress, boolean micMute, int lensFacing, final RemoteCameraControl.RemoteCameraStartListener startListener) {
        Logger.print("startRemoteCamera", new Object[0]);
        try {
            if (context == null || deviceIpAddress == null) {
                throw new Exception("Invalid arguments");
            }
            if (!RemoteCameraControl.isCompatibleOsVersion()) {
                throw new Exception("Incompatible OS version");
            }
            boolean z = true;
            if (RemoteCameraControl.isRunning(context)) {
                throw new Exception("Remote Camera is ALREADY running");
            }
            if (!(ActivityCompat.checkSelfPermission(context, "android.permission.CAMERA") == 0)) {
                throw new Exception("Invalid camera permission");
            }
            if (ActivityCompat.checkSelfPermission(context, "android.permission.RECORD_AUDIO") != 0) {
                z = false;
            }
            if (!z) {
                throw new Exception("Invalid audio permission");
            }
            this.mLocalBroadcastEx.registerOnce(context, CameraServiceIF.ACTION_NOTIFY_PAIRING, new LocalBroadcastEx.BroadcastListener() {
                @Override
                public void onReceive(Intent intent) {
                    RemoteCameraApi.startRemoteCamera0(startListener, intent);
                }
            });
            this.mLocalBroadcastEx.registerOnce(context, CameraServiceIF.ACTION_START_RESPONSE, new LocalBroadcastEx.BroadcastListener() {
                @Override
                public void onReceive(Intent intent) {
                    RemoteCameraApi.startRemoteCamera1(startListener, intent);
                }
            });
            Logger.debug("Request start", new Object[0]);
            CameraServiceIF.requestStart(context, previewSurface, deviceIpAddress, micMute, lensFacing);
        } catch (Exception exception) {
            if (startListener != null) {
                startListener.onStart(false);
            }
        }
    }

    public static void startRemoteCamera0(RemoteCameraControl.RemoteCameraStartListener remoteCameraStartListener, Intent intent) {
        if (remoteCameraStartListener != null) {
            remoteCameraStartListener.onPairing();
        }
    }

    public static void startRemoteCamera1(RemoteCameraControl.RemoteCameraStartListener remoteCameraStartListener, Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra(CameraServiceIF.EXTRA_RESULT, false);
        if (remoteCameraStartListener != null) {
            remoteCameraStartListener.onStart(booleanExtra);
        }
    }

    public void stopRemoteCamera(final Context context, final RemoteCameraControl.RemoteCameraStopListener stopListener) {
        Logger.print("stopRemoteCamera", new Object[0]);
        try {
            if (context == null) {
                throw new Exception("Invalid arguments");
            }
            if (!RemoteCameraControl.isRunning(context)) {
                throw new Exception("Remote Camera is NOT running");
            }
            this.mLocalBroadcastEx.registerOnce(context, CameraServiceIF.ACTION_STOP_RESPONSE, new LocalBroadcastEx.BroadcastListener() {
                @Override
                public void onReceive(Intent intent) {
                    RemoteCameraApi.this.onReceiveLocalBroadcast(stopListener, context, intent);
                }
            });
            Logger.debug("Request stop", new Object[0]);
            CameraServiceIF.requestStop(context);
        } catch (Exception exception) {
            if (stopListener != null) {
                stopListener.onStop(false);
            }
        }
    }

    public void onReceiveLocalBroadcast(RemoteCameraControl.RemoteCameraStopListener remoteCameraStopListener, Context context, Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra(CameraServiceIF.EXTRA_RESULT, false);
        if (remoteCameraStopListener != null) {
            remoteCameraStopListener.onStop(booleanExtra);
        }
        this.mLocalBroadcastEx.unregisterAll(context);
    }

    public void setMicMute(Context context, boolean micMute) {
        Logger.print("setMicMute (micMute=%s)", Boolean.valueOf(micMute));
        if (RemoteCameraControl.isRunning(context)) {
            CameraServiceIF.setMicMute(context, micMute);
        } else {
            Logger.error("Remote camera is NOT running", new Object[0]);
        }
    }

    public void setLensFacing(Context context, int lensFacing) {
        Logger.print("setLensFacing (lensFacing=%d)", Integer.valueOf(lensFacing));
        if (RemoteCameraControl.isRunning(context)) {
            CameraServiceIF.setLensFacing(context, lensFacing);
        } else {
            Logger.error("Remote camera is NOT running", new Object[0]);
        }
    }

    public void setCameraPlayingListener(Context context, final RemoteCameraControl.RemoteCameraPlayingListener playingListener) {
        this.mLocalBroadcastEx.registerOnce(context, CameraServiceIF.ACTION_NOTIFY_PLAYING, new LocalBroadcastEx.BroadcastListener() {
            @Override
            public void onReceive(Intent intent) {
                if (playingListener != null) {
                    playingListener.onPlaying();
                }
            }
        });
    }

    public void setPropertyChangeListener(Context context, final RemoteCameraControl.RemoteCameraPropertyChangeListener propertyChangeListener) {
        this.mLocalBroadcastEx.register(context, CameraServiceIF.ACTION_NOTIFY_PROPERTY_CHANGE, new LocalBroadcastEx.BroadcastListener() {
            @Override
            public void onReceive(Intent intent) {
                RemoteCameraControl.RemoteCameraProperty remoteCameraProperty = (RemoteCameraControl.RemoteCameraProperty) intent.getSerializableExtra(CameraServiceIF.EXTRA_PROPERTY);
                if (propertyChangeListener != null) {
                    propertyChangeListener.onChange(remoteCameraProperty);
                }
            }
        });
    }

    public void setErrorListener(Context context, final RemoteCameraControl.RemoteCameraErrorListener errorListener) {
        this.mLocalBroadcastEx.register(context, CameraServiceIF.ACTION_NOTIFY_ERROR, new LocalBroadcastEx.BroadcastListener() {
            @Override
            public void onReceive(Intent intent) {
                CameraServiceError cameraServiceError = (CameraServiceError) intent.getSerializableExtra(CameraServiceIF.EXTRA_ERROR);
                if (errorListener != null) {
                    errorListener.onError(toRemoteCameraError(cameraServiceError));
                }
            }
        });
    }

    private RemoteCameraControl.RemoteCameraError toRemoteCameraError(CameraServiceError serviceError) {
        return serviceError == CameraServiceError.ERROR_CONNECTION_CLOSED ? RemoteCameraControl.RemoteCameraError.ERROR_CONNECTION_CLOSED : serviceError == CameraServiceError.ERROR_DEVICE_SHUTDOWN ? RemoteCameraControl.RemoteCameraError.ERROR_DEVICE_SHUTDOWN : serviceError == CameraServiceError.ERROR_RENDERER_TERMINATED ? RemoteCameraControl.RemoteCameraError.ERROR_RENDERER_TERMINATED : serviceError == CameraServiceError.ERROR_STOPPED_BY_NOTIFICATION ? RemoteCameraControl.RemoteCameraError.ERROR_STOPPED_BY_NOTIFICATION : RemoteCameraControl.RemoteCameraError.ERROR_GENERIC;
    }
}
