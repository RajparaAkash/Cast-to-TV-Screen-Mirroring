package com.lge.lib.lgcast.func;

import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.Process;

import com.lge.lib.lgcast.iface.CaptureErrorListener;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class h {

    public static final String f3061a = "LGCastVirtualDisplay";
    private String b;
    private CaptureErrorListener d;
    private Handler e;
    private Handler f;
    private ParcelFileDescriptor g;
    private ParcelFileDescriptor h;
    private MediaRecorder i;
    private VirtualDisplay j;
    private HandlerThread k;
    private Handler l;
    private int m = -1;
    private int n = -1;
    private byte[] o = null;
    private ByteArrayOutputStream p = new ByteArrayOutputStream();
    private long q = 0;
    private int r = 0;
    private AtomicInteger c = new AtomicInteger(0);

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            h.this.a((byte[]) message.obj);
        }
    }

    public h(String str) {
        this.b = "(" + str + ") ";
    }


    public void d(CountDownLatch countDownLatch) {
        com.lge.lib.lgcast.common.a.c(this.b + "executeVideoCapture", new Object[0]);
        Process.setThreadPriority(-4);
        countDownLatch.countDown();
        byte[] bArr = new byte[188];
        FileInputStream fileInputStream = new FileInputStream(this.g.getFileDescriptor());
        int i = 0;
        int i2 = 0;
        while (true) {
            if (this.c.get() == 2) {
                i2 = com.lge.lib.lgcast.common.b.a(fileInputStream, bArr, i, 188 - i);
                if (i2 <= 0) {
                    break;
                }
                i += i2;
                if (i == 188) {
                    com.lge.lib.lgcast.common.b.a(this.f, com.lge.lib.lgcast.common.b.a(bArr));
                    i = 0;
                }
            } else if (this.c.get() == 4) {
                com.lge.lib.lgcast.common.a.a(this.b + "Video capture stopped !!", new Object[0]);
                break;
            } else {
                com.lge.lib.lgcast.common.b.a(10L);
            }
        }
        if (i2 <= -1) {
            com.lge.lib.lgcast.common.a.b(this.b + "MediaRecorder read error", new Object[0]);
        }
        com.lge.lib.lgcast.common.a.a(this.b + "executeVideoCapture completed", new Object[0]);
    }


    public void a(byte[] bArr) {
        g gVar = (bArr == null || bArr.length != 188) ? null : new g(bArr);
        if (gVar == null) {
            return;
        }
        int i = gVar.b;
        if (i != this.n) {
            if (i == 0) {
                this.m = new d(gVar.f3060a, bArr, gVar.e).f3057a;
                return;
            } else if (i == this.m) {
                this.n = new f(gVar.f3060a, bArr, gVar.e).f3059a;
                return;
            } else {
                return;
            }
        }
        e eVar = new e(gVar.f3060a, bArr, gVar.e);
        if (eVar.f3058a == 1) {
            if (this.p.size() > 0) {
                byte[] byteArray = this.p.toByteArray();
                this.p.reset();
                byte b = byteArray[4];
                if (b == 103) {
                    this.o = com.lge.lib.lgcast.common.b.a(byteArray);
                } else if (b == 101 && this.r < 3) {
                    com.lge.lib.lgcast.common.b.a(this.e, this.q, this.o);
                    this.r++;
                }
                com.lge.lib.lgcast.common.b.a(this.e, this.q, byteArray);
            }
            this.q = eVar.d;
        }
        this.p.write(bArr, eVar.e, eVar.f);
    }


    public void b() {
        try {
            com.lge.lib.lgcast.common.a.b(this.b + "try to stop media recorder...", new Object[0]);
            MediaRecorder mediaRecorder = this.i;
            if (mediaRecorder != null) {
                mediaRecorder.stop();
            }
            com.lge.lib.lgcast.common.a.b(this.b + "stop done...", new Object[0]);
            MediaRecorder mediaRecorder2 = this.i;
            if (mediaRecorder2 != null) {
                mediaRecorder2.reset();
            }
            com.lge.lib.lgcast.common.a.b(this.b + "reset done...", new Object[0]);
            MediaRecorder mediaRecorder3 = this.i;
            if (mediaRecorder3 != null) {
                mediaRecorder3.release();
            }
            com.lge.lib.lgcast.common.a.b(this.b + "release done...", new Object[0]);
            this.i = null;
            com.lge.lib.lgcast.common.a.b(this.b + "all things done...", new Object[0]);
        } catch (Exception e) {
            com.lge.lib.lgcast.common.a.a(e);
        }
    }

    public void c(CountDownLatch countDownLatch) {
        com.lge.lib.lgcast.common.a.c(this.b + "executeVideoExtract", new Object[0]);
        Process.setThreadPriority(-4);
        countDownLatch.countDown();
        Looper.prepare();
        this.f = new a(Looper.myLooper());
        Looper.loop();
    }

    public int a() {
        return this.c.get();
    }

    public void a(int i, int i2, int i3, MediaProjection mediaProjection, Handler handler) {
        com.lge.lib.lgcast.common.a.c(this.b + "prepare (%d x %d)", Integer.valueOf(i), Integer.valueOf(i2));
        try {
            if (mediaProjection == null || handler == null) {
                throw new IllegalArgumentException();
            }
            this.e = handler;
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            this.g = new ParcelFileDescriptor(createPipe[0]);
            this.h = new ParcelFileDescriptor(createPipe[1]);
            MediaRecorder mediaRecorder = new MediaRecorder();
            this.i = mediaRecorder;
            mediaRecorder.setVideoSource(2);
            this.i.setOutputFormat(8);
            this.i.setOutputFile(this.h.getFileDescriptor());
            this.i.setVideoSize(i, i2);
            this.i.setVideoEncoder(2);
            this.i.setVideoFrameRate(30);
            this.i.setVideoEncodingBitRate(i3);
            this.i.prepare();
            HandlerThread handlerThread = new HandlerThread("Display Handler");
            this.k = handlerThread;
            handlerThread.start();
            this.l = new Handler(this.k.getLooper());
            this.j = mediaProjection.createVirtualDisplay("LGCastVirtualDisplay", i, i2, 8, 16, this.i.getSurface(), null, this.l);
            final CountDownLatch countDownLatch = new CountDownLatch(2);
            com.lge.lib.lgcast.common.b.a(new Runnable() {
                @Override
                public void run() {
                    h.this.c(countDownLatch);
                }
            });
            com.lge.lib.lgcast.common.b.a(new Runnable() {
                @Override
                public void run() {
                    h.this.d(countDownLatch);
                }
            });
            countDownLatch.await();
            this.c.set(1);
        } catch (Exception e) {
            com.lge.lib.lgcast.common.a.a(e);
            CaptureErrorListener captureErrorListener = this.d;
            if (captureErrorListener != null) {
                captureErrorListener.onError();
            }
        }
    }

    public void a(CaptureErrorListener captureErrorListener) {
        this.d = captureErrorListener;
    }

    public void c() {
        com.lge.lib.lgcast.common.a.c(this.b + "pause (%d)", Integer.valueOf(this.c.get()));
        try {
            if (this.c.get() != 2) {
                throw new Exception(this.b + "Invalid status: " + this.c.get());
            }
            this.i.pause();
            this.c.set(3);
        } catch (Exception e) {
            com.lge.lib.lgcast.common.a.a(e);
        }
    }

    public void d() {
        com.lge.lib.lgcast.common.a.c(this.b + "start (%d)", Integer.valueOf(this.c.get()));
        try {
            if (this.c.get() != 1 && this.c.get() != 3) {
                throw new Exception(this.b + "Invalid status: " + this.c.get());
            }
            if (this.c.get() == 1) {
                this.i.start();
            } else {
                this.i.resume();
            }
            this.c.set(2);
        } catch (Exception e) {
            com.lge.lib.lgcast.common.a.a(e);
        }
    }

    public void e() {
        com.lge.lib.lgcast.common.a.c(this.b + "stop (%d)", Integer.valueOf(this.c.get()));
        try {
            if (this.c.get() != 2 && this.c.get() != 3) {
                throw new Exception(this.b + "Invalid status: " + this.c.get());
            }
            this.c.set(4);
            Handler handler = this.f;
            if (handler != null) {
                handler.getLooper().quit();
            }
            this.f = null;
            com.lge.lib.lgcast.common.a.a(this.b + "video capture close 1/6 ok", new Object[0]);
            if (this.e != null) {
                this.e = null;
            }
            com.lge.lib.lgcast.common.a.a(this.b + "video capture close 2/6 ok", new Object[0]);
            ParcelFileDescriptor parcelFileDescriptor = this.h;
            if (parcelFileDescriptor != null) {
                parcelFileDescriptor.detachFd();
            }
            ParcelFileDescriptor parcelFileDescriptor2 = this.h;
            if (parcelFileDescriptor2 != null) {
                parcelFileDescriptor2.close();
            }
            this.h = null;
            com.lge.lib.lgcast.common.a.a(this.b + "video capture close 3/6 ok", new Object[0]);
            ParcelFileDescriptor parcelFileDescriptor3 = this.g;
            if (parcelFileDescriptor3 != null) {
                parcelFileDescriptor3.detachFd();
            }
            ParcelFileDescriptor parcelFileDescriptor4 = this.g;
            if (parcelFileDescriptor4 != null) {
                parcelFileDescriptor4.close();
            }
            this.g = null;
            com.lge.lib.lgcast.common.a.a(this.b + "video capture close 4/6 ok", new Object[0]);
            com.lge.lib.lgcast.common.b.a(new Runnable() {
                @Override
                public void run() {
                    h.this.b();
                }
            }).join(2000L);
            com.lge.lib.lgcast.common.a.a(this.b + "video capture close 5/6 ok", new Object[0]);
            VirtualDisplay virtualDisplay = this.j;
            if (virtualDisplay != null) {
                virtualDisplay.release();
            }
            this.j = null;
            com.lge.lib.lgcast.common.a.a(this.b + "video capture close 6/6 ok", new Object[0]);
            Handler handler2 = this.l;
            if (handler2 != null) {
                handler2.getLooper().quit();
            }
            this.l = null;
            HandlerThread handlerThread = this.k;
            if (handlerThread != null) {
                handlerThread.quit();
            }
            this.k = null;
        } catch (Exception e) {
            com.lge.lib.lgcast.common.a.a(e);
        }
    }
}
