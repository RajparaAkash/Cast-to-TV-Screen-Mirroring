package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.graphics.Point;
import android.media.projection.MediaProjectionManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class ScreenRecordingService extends Service {

    public static final String MSG_SERVICE_STARTED = (ScreenRecordingService.class.getPackage().getName() + ".SERVICE_STARTED");
    private static final int NOTIFICATION_ID = 172;
    private static boolean isServiceRunning = false;

    private final ScreenRecordingBinder binder = new ScreenRecordingBinder();
    private boolean hasStreamingStarted;
    private ImageScreenRecorder imageScreenRecorder;
    private MotionJpegStreamer motionJpegStreamer;
    private BroadcastReceiver orientationChangeReceiver;
    private Settings settings;
    private PowerManager.WakeLock wakeLock;
    private WebServer webServer;

    public class ScreenRecordingBinder extends Binder {
        public ScreenRecordingService getService() {
            return ScreenRecordingService.this;
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        sendBroadcast(new Intent(MSG_SERVICE_STARTED));
        return Service.START_NOT_STICKY;
    }

    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        settings = SettingsManagerActivity.getInstance(this);
        isServiceRunning = true;
        hasStreamingStarted = false;

        registerOrientationChangeReceiver();
    }

    private void registerOrientationChangeReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        orientationChangeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (hasStreamingStarted()) {
                    motionJpegStreamer.pause();
                    resizeRecording();
                    motionJpegStreamer.resume();
                }
            }
        };
        registerReceiver(orientationChangeReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopScreenRecording();
        unregisterReceiver(orientationChangeReceiver);
        isServiceRunning = false;
        releaseWakeLock();
    }

    private void releaseWakeLock() {
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
        }
    }

    public void acquireWakeLock() {
        try {
            wakeLock = ((PowerManager) getSystemService(Context.POWER_SERVICE))
                    .newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "LiveScreen::ScreenMirroringLock");
            wakeLock.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void startScreenRecording(ScreenCapturePermission screenCapturePermission) {
        if (hasStreamingStarted) return;

        motionJpegStreamer = new MotionJpegStreamer(this);
        motionJpegStreamer.start();

        try {
            webServer = new WebServer(this, settings.getWebServerPort(), motionJpegStreamer);
            webServer.start();
        } catch (IOException e) {
            e.printStackTrace();
            stopSelf();
            return;
        }

        attachToForeground();
        startImageRecording(screenCapturePermission);
        hasStreamingStarted = true;
        acquireWakeLock();
    }

    private void attachToForeground() {
        Notification serviceNotification = AppNotifications.newServiceNotification(this);

        // Android 14+ (SDK 34) requires additional permission checks
        if (Build.VERSION.SDK_INT >= 34) {
            if (checkSelfPermission("android.permission.FOREGROUND_SERVICE_MEDIA_PROJECTION")
                    != PackageManager.PERMISSION_GRANTED) {
                Log.e("TAG", "Permission FOREGROUND_SERVICE_MEDIA_PROJECTION not granted!");
                return;
            }
        }

        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(NOTIFICATION_ID, serviceNotification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION);
        } else {
            startForeground(NOTIFICATION_ID, serviceNotification);
        }
    }

    /*private void attachToForeground() {
        Notification serviceNotification = AppNotifications.newServiceNotification(this);
        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(NOTIFICATION_ID, serviceNotification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION);
        } else {
            startForeground(NOTIFICATION_ID, serviceNotification);
        }
    }*/

    private void startImageRecording(ScreenCapturePermission screenCapturePermission) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);

        Point scaledSize = getScaledSize();
        imageScreenRecorder = new ImageScreenRecorder(scaledSize.x, scaledSize.y, displayMetrics.densityDpi, motionJpegStreamer);
        imageScreenRecorder.start(((MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE))
                .getMediaProjection(screenCapturePermission.getResultCode(), screenCapturePermission.getIntent()));
    }

    private Point getScaledSize() {
        Point displayResolution = DisplayUtil.getDisplayResolution(this);
        float scalingFactor = (float) SettingsManagerActivity.getInstance(getBaseContext()).getScalingFactor();
        displayResolution.x = (int) (displayResolution.x * scalingFactor);
        displayResolution.y = (int) (displayResolution.y * scalingFactor);
        return displayResolution;
    }

    public synchronized void stopScreenRecording() {
        if (!hasStreamingStarted) return;

        motionJpegStreamer.shutDown();
        if (webServer != null) {
            webServer.stop();
        }
        imageScreenRecorder.stop();

        webServer = null;
        imageScreenRecorder = null;
        motionJpegStreamer = null;
        hasStreamingStarted = false;
        stopForeground(true);
        releaseWakeLock();
    }

    public boolean hasStreamingStarted() {
        return hasStreamingStarted;
    }

    public void onQualityChanged() {
        if (hasStreamingStarted()) {
            motionJpegStreamer.pause();
            resizeRecording();
            motionJpegStreamer.resume();
        }
    }

    private void resizeRecording() {
        Point scaledSize = getScaledSize();
        imageScreenRecorder.resize(scaledSize.x, scaledSize.y);
    }

    public void onPortChanged() {
        if (hasStreamingStarted() && webServer.getPort() != settings.getWebServerPort()) {
            updateWebServerPort();
        }
    }

    public void onHttpAuthChanged() {
        if (!hasStreamingStarted()) return;

        if (settings.isHttpAuthEnabled()) {
            webServer.enableHttpAuthentication(settings.getHttpUsername(), settings.getHttpPassword());
        } else {
            webServer.disableHttpAuthentication();
        }
    }

    private void updateWebServerPort() {
        int webServerPort = settings.getWebServerPort();
        try {
            webServer.stop();
            motionJpegStreamer.shutDown();
            motionJpegStreamer.start();
            webServer = new WebServer(this, webServerPort, motionJpegStreamer);
            webServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<StreamingClient> getConnectedClients() {
        if (motionJpegStreamer == null) {
            return Collections.emptyList();
        }
        return motionJpegStreamer.getConnectedClients();
    }

    public static boolean isServiceRunning() {
        return isServiceRunning;
    }
}
