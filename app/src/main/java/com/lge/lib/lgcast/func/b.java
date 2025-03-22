package com.lge.lib.lgcast.func;

import android.media.AudioFormat;
import android.media.AudioPlaybackCaptureConfiguration;
import android.media.AudioRecord;
import android.media.projection.MediaProjection;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;

import com.lge.lib.lgcast.iface.CaptureErrorListener;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class b {

    public static final int f3054a = 2;
    public static final int b = 12;
    public static final int c = 1024;
    public static final int d = 131072;
    private int e;
    private int f;
    private AtomicInteger g = new AtomicInteger(0);
    private CaptureErrorListener h;
    private Handler i;
    private Handler j;
    private AudioRecord k;

    public class a extends Handler {

        public final com.lge.lib.lgcast.func.a f3055a;

        public a(Looper looper, com.lge.lib.lgcast.func.a aVar) {
            super(looper);
            this.f3055a = aVar;
        }

        @Override
        public void handleMessage(Message message) {
            com.lge.lib.lgcast.func.a.C0288a a2 = this.f3055a.a((byte[]) message.obj);
            if (a2 != null) {
                com.lge.lib.lgcast.common.b.a(b.this.i, a2.f3053a, a2.b);
            }
        }
    }

    public b(int i, int i2) {
        this.e = i;
        this.f = i2;
    }


    public void d(CountDownLatch countDownLatch) {
        com.lge.lib.lgcast.common.a.c("executeAudioCapture", new Object[0]);
        Process.setThreadPriority(-16);
        countDownLatch.countDown();
        short[] sArr = new short[1024];
        int i = 0;
        while (true) {
            if (this.g.get() == 2) {
                i = this.k.read(sArr, 0, 1024);
                if (i <= -1) {
                    break;
                }
                byte[] bArr = new byte[i * 2];
                ByteBuffer.wrap(bArr).order(ByteOrder.nativeOrder()).asShortBuffer().put(sArr, 0, i);
                com.lge.lib.lgcast.common.b.a(this.j, bArr);
            } else if (this.g.get() == 4) {
                com.lge.lib.lgcast.common.a.a("Audio capture stopped !!", new Object[0]);
                break;
            } else {
                com.lge.lib.lgcast.common.b.a(1L);
            }
        }
        if (i <= -1) {
            com.lge.lib.lgcast.common.a.b("AudioRecord read error", new Object[0]);
            CaptureErrorListener captureErrorListener = this.h;
            if (captureErrorListener != null) {
                captureErrorListener.onError();
            }
        }
        com.lge.lib.lgcast.common.a.a("executeAudioCapture completed", new Object[0]);
    }


    public void c(CountDownLatch countDownLatch) {
        com.lge.lib.lgcast.common.a.c("executeAudioEncoding", new Object[0]);
        Process.setThreadPriority(-16);
        countDownLatch.countDown();
        com.lge.lib.lgcast.func.a a2 = com.lge.lib.lgcast.func.a.a(131072, this.e, this.f);
        if (a2 == null) {
            return;
        }
        Looper.prepare();
        this.j = new a(Looper.myLooper(), a2);
        Looper.loop();
        a2.a();
    }

    public void a() {
        com.lge.lib.lgcast.common.a.c("stopCapture", new Object[0]);
        this.g.set(4);
        try {
            Handler handler = this.j;
            if (handler != null) {
                handler.getLooper().quit();
            }
            this.j = null;
            com.lge.lib.lgcast.common.a.a("audio capture close 1/4 ok", new Object[0]);
            if (this.i != null) {
                this.i = null;
            }
            com.lge.lib.lgcast.common.a.a("audio capture close 2/4 ok", new Object[0]);
            AudioRecord audioRecord = this.k;
            if (audioRecord != null) {
                audioRecord.stop();
            }
            com.lge.lib.lgcast.common.a.a("audio capture close 3/4 ok", new Object[0]);
            AudioRecord audioRecord2 = this.k;
            if (audioRecord2 != null) {
                audioRecord2.release();
            }
            this.k = null;
            com.lge.lib.lgcast.common.a.a("audio capture close 4/4 ok", new Object[0]);
        } catch (Exception e) {
            com.lge.lib.lgcast.common.a.a(e);
        }
    }

    public void a(MediaProjection mediaProjection, Handler handler) {
        com.lge.lib.lgcast.common.a.c("startCapture", new Object[0]);
        try {
            if (mediaProjection == null || handler == null) {
                throw new Exception("Invalid arguments");
            }
            this.i = handler;
            AudioRecord build = new AudioRecord.Builder().setBufferSizeInBytes(2048).setAudioPlaybackCaptureConfig(new AudioPlaybackCaptureConfiguration.Builder(mediaProjection).addMatchingUsage(1).build()).setAudioFormat(new AudioFormat.Builder().setEncoding(2).setChannelMask(12).setSampleRate(this.e).build()).build();
            this.k = build;
            build.startRecording();
            final CountDownLatch countDownLatch = new CountDownLatch(2);
            com.lge.lib.lgcast.common.b.a(new Runnable() {
                @Override
                public void run() {
                    b.this.c(countDownLatch);
                }
            });
            com.lge.lib.lgcast.common.b.a(new Runnable() {
                @Override
                public void run() {
                    b.this.d(countDownLatch);
                }
            });
            countDownLatch.await();
            this.g.set(2);
        } catch (Exception e) {
            com.lge.lib.lgcast.common.a.a(e);
            CaptureErrorListener captureErrorListener = this.h;
            if (captureErrorListener != null) {
                captureErrorListener.onError();
            }
            this.g.set(0);
        }
    }

    public void a(CaptureErrorListener captureErrorListener) {
        this.h = captureErrorListener;
    }
}
