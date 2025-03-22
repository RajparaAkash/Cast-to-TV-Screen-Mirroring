package com.connectsdk.etc.helper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class DeviceServiceReachability {
    private static final int TIMEOUT = 10000;
    private InetAddress ipAddress;
    private DeviceServiceReachabilityListener listener;
    private Runnable testReachability = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    if (!DeviceServiceReachability.this.ipAddress.isReachable(10000)) {
                        DeviceServiceReachability.this.unreachable();
                    }
                    Thread.sleep(10000L);
                } catch (IOException exception) {
                    DeviceServiceReachability.this.unreachable();
                    return;
                } catch (InterruptedException unused2) {
                    return;
                }
            }
        }
    };
    private Thread testThread;


    public interface DeviceServiceReachabilityListener {
        void onLoseReachability(DeviceServiceReachability reachability);
    }

    public DeviceServiceReachability() {
    }

    public DeviceServiceReachability(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public DeviceServiceReachability(InetAddress ipAddress, DeviceServiceReachabilityListener listener) {
        this.ipAddress = ipAddress;
        this.listener = listener;
    }

    public static DeviceServiceReachability getReachability(InetAddress ipAddress, DeviceServiceReachabilityListener listener) {
        return new DeviceServiceReachability(ipAddress, listener);
    }

    public static DeviceServiceReachability getReachability(final String ipAddress, DeviceServiceReachabilityListener listener) {
        try {
            return getReachability(InetAddress.getByName(ipAddress), listener);
        } catch (UnknownHostException exception) {
            return null;
        }
    }

    public InetAddress getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isRunning() {
        Thread thread = this.testThread;
        return thread != null && thread.isAlive();
    }

    public DeviceServiceReachabilityListener getListener() {
        return this.listener;
    }

    public void setListener(DeviceServiceReachabilityListener listener) {
        this.listener = listener;
    }

    public void start() {
        if (isRunning()) {
            return;
        }
        Thread thread = new Thread(this.testReachability);
        this.testThread = thread;
        thread.start();
    }

    public void stop() {
        if (isRunning()) {
            this.testThread.interrupt();
            this.testThread = null;
        }
    }

    
    public void unreachable() {
        stop();
        DeviceServiceReachabilityListener deviceServiceReachabilityListener = this.listener;
        if (deviceServiceReachabilityListener != null) {
            deviceServiceReachabilityListener.onLoseReachability(this);
        }
    }
}
