package com.lge.lib.lgcast.func;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class a {

    private static final String f3052a = "audio/mp4a-latm";
    private static final int b = 1000;
    private int c;
    private int d;
    private int e;
    private MediaCodec f;
    private int g;
    private ByteBuffer h;
    private int i;
    private ByteBuffer j;
    private MediaCodec.BufferInfo k;

    public static class C0288a {
        public long f3053a;
        public byte[] b;
    }

    public a(int i, int i2, int i3) {
        this.c = i;
        this.d = i2;
        this.e = i3;
    }

    public static a a(int i, int i2, int i3) {
        com.lge.lib.lgcast.common.a.c("open (bitrate=%d, sampleRate=%d, channelCount=%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        try {
            a aVar = new a(i, i2, i3);
            aVar.b();
            return aVar;
        } catch (Exception e) {
            com.lge.lib.lgcast.common.a.a(e);
            return null;
        }
    }

    public C0288a a(byte[] bArr) {
        if (bArr == null) {
            com.lge.lib.lgcast.common.a.b("Invalid input stream", new Object[0]);
            return null;
        }
        int dequeueInputBuffer = this.f.dequeueInputBuffer(1000L);
        this.g = dequeueInputBuffer;
        if (dequeueInputBuffer >= 0) {
            ByteBuffer inputBuffer = this.f.getInputBuffer(dequeueInputBuffer);
            this.h = inputBuffer;
            inputBuffer.clear();
            this.h.put(bArr);
            this.h.limit(bArr.length);
            this.f.queueInputBuffer(this.g, 0, bArr.length, 0L, 0);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.k = new MediaCodec.BufferInfo();
        while (true) {
            int dequeueOutputBuffer = this.f.dequeueOutputBuffer(this.k, 1000L);
            this.i = dequeueOutputBuffer;
            if (dequeueOutputBuffer < 0) {
                break;
            }
            int i = this.k.size;
            if (i > 0) {
                byte[] bArr2 = new byte[i];
                ByteBuffer outputBuffer = this.f.getOutputBuffer(dequeueOutputBuffer);
                this.j = outputBuffer;
                MediaCodec.BufferInfo bufferInfo = this.k;
                outputBuffer.get(bArr2, bufferInfo.offset, bufferInfo.size);
                byteArrayOutputStream.write(bArr2, 0, i);
                this.f.releaseOutputBuffer(this.i, false);
            }
        }
        C0288a c0288a = new C0288a();
        c0288a.f3053a = 0L;
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        c0288a.b = byteArray;
        if (byteArray.length > 0) {
            return c0288a;
        }
        return null;
    }

    public void a() {
        com.lge.lib.lgcast.common.a.c("close", new Object[0]);
        MediaCodec mediaCodec = this.f;
        if (mediaCodec != null) {
            mediaCodec.stop();
        }
        MediaCodec mediaCodec2 = this.f;
        if (mediaCodec2 != null) {
            mediaCodec2.release();
        }
        this.f = null;
    }

    public void b() throws Exception {
        com.lge.lib.lgcast.common.a.c("open", new Object[0]);
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", this.d, this.e);
        createAudioFormat.setInteger("aac-profile", 2);
        createAudioFormat.setInteger("bitrate", this.c);
        MediaCodec createEncoderByType = MediaCodec.createEncoderByType("audio/mp4a-latm");
        this.f = createEncoderByType;
        createEncoderByType.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 1);
        this.f.start();
    }
}
