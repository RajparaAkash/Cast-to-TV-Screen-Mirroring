package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import android.annotation.SuppressLint;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class ImageScreenRecorder {
    private static final String TAG = "ImageScreenRecorder";
    private int displayHeight;
    private int displayWidth;
    private final ImageReader.OnImageAvailableListener imageAvailableListener;
    private ImageReader imageReader;
    private MediaProjection mediaProjection;
    private final int screenDensity;
    private VirtualDisplay virtualDisplay;

    public ImageScreenRecorder(int i, int i2, int i3, ImageReader.OnImageAvailableListener onImageAvailableListener) {
        Log.d(TAG, "ImageScreenRecorder created");
        this.displayWidth = i;
        this.displayHeight = i2;
        this.screenDensity = i3;
        this.imageAvailableListener = onImageAvailableListener;
    }

    public void start(MediaProjection mediaProjection2) {
        Log.d(TAG, "screen recording started");
        this.mediaProjection = mediaProjection2;
        createImageReader();
        createVirtualDisplay();
    }

    public void stop() {
        Log.d(TAG, "stopping screen recording");
        VirtualDisplay virtualDisplay2 = this.virtualDisplay;
        if (virtualDisplay2 != null) {
            virtualDisplay2.release();
        }
        ImageReader imageReader2 = this.imageReader;
        if (imageReader2 != null) {
            imageReader2.close();
        }
        MediaProjection mediaProjection2 = this.mediaProjection;
        if (mediaProjection2 != null) {
            mediaProjection2.stop();
            this.mediaProjection = null;
        }
    }

    private void createImageReader() {
        int i = this.displayWidth;
        int i2 = this.displayHeight;
        Log.d(TAG, String.format("creating image reader of size %dx%d", Integer.valueOf(i), Integer.valueOf(i2)));
        @SuppressLint("WrongConstant") ImageReader newInstance = ImageReader.newInstance(i, i2, 1, 5);
        this.imageReader = newInstance;
        newInstance.setOnImageAvailableListener(this.imageAvailableListener, null);
    }

    /*private void createVirtualDisplay() {
        int i = this.displayWidth;
        int i2 = this.displayHeight;
        Log.d(TAG, String.format("creating virtual display of size %dx%d", Integer.valueOf(i), Integer.valueOf(i2)));
        this.virtualDisplay = this.mediaProjection.createVirtualDisplay("LiveScreen", i, i2, this.screenDensity, 16, this.imageReader.getSurface(), null, null);
    }*/

    private void createVirtualDisplay() {
        int i = this.displayWidth;
        int i2 = this.displayHeight;

        Log.d(TAG, String.format("creating virtual display of size %dx%d", i, i2));

        // Register a callback to manage MediaProjection state
        mediaProjection.registerCallback(new MediaProjection.Callback() {
            @Override
            public void onStop() {
                super.onStop();
                Log.d(TAG, "MediaProjection stopped");
                if (virtualDisplay != null) {
                    virtualDisplay.release();
                    virtualDisplay = null;
                }
            }
        }, new Handler(Looper.getMainLooper())); // Pass a handler to the callback

        // Now create the virtual display
        this.virtualDisplay = this.mediaProjection.createVirtualDisplay(
                "LiveScreen",
                i, i2,
                this.screenDensity,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC, // Correct flag
                this.imageReader.getSurface(),
                null, null
        );
    }

    public void resize(int i, int i2) {
        if (this.virtualDisplay == null) {
            Log.e(TAG, "virtualDisplay is null, cannot resize");
            return;
        }
        if (this.imageReader != null) {
            this.imageReader.close();
        }

        this.displayWidth = i;
        this.displayHeight = i2;
        Log.d(TAG, String.format("Resizing to %dx%d", i, i2));

        createImageReader();
        if (this.imageReader == null || this.imageReader.getSurface() == null) {
            Log.e(TAG, "ImageReader or its Surface is null, cannot update VirtualDisplay");
            return;
        }
        this.virtualDisplay.setSurface(this.imageReader.getSurface());
        this.virtualDisplay.resize(i, i2, this.screenDensity);
    }
}
