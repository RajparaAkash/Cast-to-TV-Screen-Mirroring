package com.connectsdk.service.webos.lgcast.remotecamera.capture;

import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;


public class JPEGEncoder {
    public static byte[] getJpegStream(Image image, int quality) {
        if (image == null || image.getFormat() != 35) {
            if (image == null || image.getFormat() != 256) {
                return null;
            }
            return imageToJpeg(image);
        }
        return yuv420ToJpeg(image, quality);
    }

    private static byte[] yuv420ToJpeg(Image image, int quality) {
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        ByteBuffer buffer2 = image.getPlanes()[2].getBuffer();
        int remaining = buffer.remaining();
        int remaining2 = buffer2.remaining();
        byte[] bArr = new byte[remaining + remaining2];
        buffer.get(bArr, 0, remaining);
        buffer2.get(bArr, remaining, remaining2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new YuvImage(bArr, 17, image.getWidth(), image.getHeight(), null).compressToJpeg(new Rect(0, 0, image.getWidth(), image.getHeight()), quality, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private static byte[] imageToJpeg(Image image) {
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] bArr = new byte[buffer.capacity()];
        buffer.get(bArr);
        return bArr;
    }
}
