package com.connectsdk.service.webos.lgcast.common.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class ThreadWait<E> {
    private BlockingQueue<WaitResult> mBlockingQueue = new LinkedBlockingQueue();
    private AtomicBoolean mIsWaiting = new AtomicBoolean(false);

    
    
    public class WaitResult {
        public E result;

        public WaitResult(E result) {
            this.result = result;
        }
    }

    public boolean isWaiting() {
        return this.mIsWaiting.get();
    }

    public E waitFor(E fallback) {
        return waitFor(Long.MAX_VALUE, fallback);
    }

    public E waitFor(long timeout, E fallback) {
        try {
            try {
                this.mIsWaiting.set(true);
                this.mBlockingQueue.clear();
                WaitResult poll = this.mBlockingQueue.poll(timeout, TimeUnit.MILLISECONDS);
                if (poll != null) {
                    fallback = poll.result;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return fallback;
        } finally {
            this.mIsWaiting.set(false);
        }
    }

    public void wakeUp(E result) {
        try {
            this.mBlockingQueue.put(new WaitResult(result));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
