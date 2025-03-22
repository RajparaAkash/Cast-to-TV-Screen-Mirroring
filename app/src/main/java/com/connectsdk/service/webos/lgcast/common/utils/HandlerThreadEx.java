package com.connectsdk.service.webos.lgcast.common.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;


public class HandlerThreadEx {
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private String mThreadName;
    private int mThreadPriority = 0;


    public interface HandlerCallback {
        void handleMessage(Message msg);
    }

    public HandlerThreadEx(String name) {
        this.mThreadName = name;
    }

    public void start() {
        HandlerThread handlerThread = new HandlerThread(this.mThreadName, this.mThreadPriority);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
    }

    public void start(final HandlerCallback callback) {
        if (callback == null) {
            throw new IllegalArgumentException();
        }
        HandlerThread handlerThread = new HandlerThread(this.mThreadName, this.mThreadPriority);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                callback.handleMessage(msg);
            }
        };
    }

    public void quit() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.getLooper().quit();
        }
        this.mHandler = null;
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        this.mHandlerThread = null;
    }

    public void post(Runnable r) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(r);
        }
    }

    public void post(Runnable r, long delayMillis) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.postDelayed(r, delayMillis);
        }
    }

    public boolean sendMessage(Object obj) {
        return sendMessage(0, obj);
    }

    public boolean sendMessage(int what, Object obj) {
        Message obtain = Message.obtain();
        obtain.what = what;
        obtain.obj = obj;
        Handler handler = this.mHandler;
        if (handler != null) {
            return handler.sendMessage(obtain);
        }
        return false;
    }

    public Handler getHandler() {
        return this.mHandler;
    }
}
