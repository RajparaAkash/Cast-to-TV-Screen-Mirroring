package com.connectsdk.core;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;

import com.connectsdk.service.capability.listeners.ErrorListener;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public final class Util {
    private static final int NUM_OF_THREADS = 20;
    public static String T = "Connect SDK";
    private static Executor executor;
    private static Handler handler;

    public static byte[] convertIpAddress(int ip) {
        return new byte[]{(byte) (ip & 255), (byte) ((ip >> 8) & 255), (byte) ((ip >> 16) & 255), (byte) ((ip >> 24) & 255)};
    }

    static {
        createExecutor();
    }

    static void createExecutor() {
        executor = Executors.newFixedThreadPool(20, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("2nd Screen BG");
                return thread;
            }
        });
    }

    public static void runOnUI(Runnable runnable) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(runnable);
    }

    public static void runInBackground(Runnable runnable, boolean forceNewThread) {
        if (forceNewThread || isMain()) {
            executor.execute(runnable);
        } else {
            runnable.run();
        }
    }

    public static void runInBackground(Runnable runnable) {
        runInBackground(runnable, false);
    }

    public static Executor getExecutor() {
        return executor;
    }

    public static boolean isMain() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static <T> void postSuccess(final ResponseListener<T> listener, final T object) {
        if (listener == null) {
            return;
        }
        runOnUI(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess(object);
            }
        });
    }

    public static void postError(final ErrorListener listener, final ServiceCommandError error) {
        if (listener == null) {
            return;
        }
        runOnUI(new Runnable() {
            @Override
            public void run() {
                listener.onError(error);
            }
        });
    }

    public static long getTime() {
        return TimeUnit.MILLISECONDS.toSeconds(new Date().getTime());
    }

    public static InetAddress getIpAddress(Context context) throws UnknownHostException {
        int ipAddress = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getIpAddress();
        if (ipAddress == 0) {
            return null;
        }
        return InetAddress.getByAddress(convertIpAddress(ipAddress));
    }

}
