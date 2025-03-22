package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class ScreenServiceConnector implements ServiceConnection {

    private final Context context;
    private boolean isBoundToService = false;
    private BroadcastReceiver receiver;
    private ScreenRecordingService service;

    public ScreenServiceConnector(Context context) {
        this.context = context.getApplicationContext();
    }

    public void connect() {
        if (ScreenRecordingService.isServiceRunning()) {
            bindToService();
        } else {
            registerBroadcastReceiver();
            startService();
        }
    }

    public void disconnect() {
        unbindFromService();
    }

    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(ScreenRecordingService.MSG_SERVICE_STARTED);
        receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                bindToService();
                context.unregisterReceiver(receiver);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(receiver, intentFilter, Context.RECEIVER_EXPORTED);
        } else {
            context.registerReceiver(receiver, intentFilter);
        }
    }

    private void startService() {
        Intent intent = new Intent(context, ScreenRecordingService.class);
        try {
            context.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
            new Handler().postDelayed(() -> {
                try {
                    if (Build.VERSION.SDK_INT >= 26) {
                        context.startForegroundService(intent);
                    } else {
                        context.startService(intent);
                    }
                } catch (Exception ex) {
                    showToast();
                }
            }, 1000);
        }
    }

    private void showToast() {
        Toast.makeText(context, "Unable to start screen recording service", Toast.LENGTH_LONG).show();
    }

    public void stopService() {
        Intent intent = new Intent(context, ScreenRecordingService.class);
        if (isBoundToService) {
            unbindFromService();
            context.stopService(intent);
        } else if (ScreenRecordingService.isServiceRunning()) {
            context.stopService(intent);
        }
    }

    private void bindToService() {
        if (!isBoundToService) {
            context.bindService(new Intent(context, ScreenRecordingService.class), this, Context.BIND_AUTO_CREATE);
        }
    }

    private void unbindFromService() {
        if (isBoundToService) {
            context.unbindService(this);
            service = null;
            isBoundToService = false;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        service = ((ScreenRecordingService.ScreenRecordingBinder) binder).getService();
        isBoundToService = true;
        onServiceConnected(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isBoundToService = false;
        onServiceDisconnected();
    }

    // Hook methods for subclasses to override if needed
    protected void onServiceConnected(ScreenRecordingService service) {
    }

    protected void onServiceDisconnected() {
    }
}
