package com.lge.lib.lgcast.common;

import android.os.Handler;
import android.os.Message;

import com.lge.lib.lgcast.iface.MediaData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Locale;

public class b {
    public static int a(InputStream inputStream, byte[] bArr, int i, int i2) {
        try {
            if (inputStream != null) {
                if (bArr != null) {
                    return inputStream.read(bArr, i, i2);
                }
                throw new IOException();
            }
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Thread a(Runnable runnable) {
        if (runnable == null) {
            return null;
        }
        Thread thread = new Thread(runnable);
        thread.start();
        return thread;
    }

    public static void a(long j) {
        try {
            Thread.sleep(j);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Handler handler, long j, byte[] bArr) {
        Message obtain = Message.obtain();
        obtain.obj = new MediaData(j, bArr);
        if (handler != null) {
            handler.sendMessage(obtain);
        }
    }

    public static void a(Handler handler, byte[] bArr) {
        Message obtain = Message.obtain();
        obtain.obj = bArr;
        if (handler != null) {
            handler.sendMessage(obtain);
        }
    }

    public static byte[] a(byte[] bArr) {
        if (bArr != null) {
            return Arrays.copyOf(bArr, bArr.length);
        }
        return null;
    }

    public static String b(long j) {
        float f = (float) j;
        float f2 = f / 1024.0f;
        float f3 = f / 1048576.0f;
        float f4 = f / 1.0737418E9f;
        return ((int) f4) > 0 ? String.format(Locale.US, "%.02fG", Float.valueOf(f4)) : ((int) f3) > 0 ? String.format(Locale.US, "%.02fM", Float.valueOf(f3)) : ((int) f2) > 0 ? String.format(Locale.US, "%.02fK", Float.valueOf(f2)) : String.format(Locale.US, "%d", Long.valueOf(j));
    }
}
