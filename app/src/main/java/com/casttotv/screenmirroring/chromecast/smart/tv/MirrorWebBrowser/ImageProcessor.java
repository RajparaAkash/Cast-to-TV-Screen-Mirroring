package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.media.Image;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicReference;

public abstract class ImageProcessor extends Thread {

    private final Paint grayScalePaint;
    private final AtomicReference<Image> imageBuffer;
    private final Settings settings;
    private volatile State state = State.RUNNING;

    public enum State {
        RUNNING,
        PAUSING,
        PAUSED,
        FINISHED
    }

    public abstract void onImageProcessed(byte[] bArr);

    public abstract void onShutDown();

    public ImageProcessor(Context context) {
        this.grayScalePaint = new Paint();
        this.settings = SettingsManagerActivity.getInstance(context);
        this.imageBuffer = new AtomicReference<>();

        // Apply grayscale color filter to the paint object
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        this.grayScalePaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    public synchronized void pauseProcessing() {
        this.state = State.PAUSING;
        while (this.state != State.PAUSED) {
            ThreadUtil.sleepMillis(); // Ensure this is implemented in your project
        }
    }

    public synchronized void resumeProcessing() {
        this.state = State.RUNNING;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            switch (state) {
                case RUNNING:
                    processImage();
                    break;
                case PAUSING:
                    pauseThread();
                    break;
                case PAUSED:
                    // Wait until resumed
                    break;
                case FINISHED:
                    break;
            }
        }
        onShutDown();
        this.state = State.FINISHED;
    }

    private void processImage() {
        Image image = imageBuffer.getAndSet(null);
        if (image != null) {
            try {
                byte[] imageBytes = PrepareImage(image);
                onImageProcessed(imageBytes);
            } catch (Exception e) {
                Log.e("ImageProcessor", "Error processing image", e);
            }
        }
    }

    private void pauseThread() {
        this.state = State.PAUSED;
        ThreadUtil.sleepMillis(); // Allow time for the thread to pause
    }

    public boolean isInStateFinished() {
        return this.state == State.FINISHED;
    }

    public void registerNewImage(Image image) {
        if (this.state == State.RUNNING && image != null) {
            Image oldImage = imageBuffer.getAndSet(image);
            if (oldImage != null && oldImage.getPlanes() != null) {
                oldImage.close();
            }
        } else if (image != null) {
            image.close();
        }
    }

    private synchronized byte[] PrepareImage(Image image) {
        try {
            if (image == null) {
                throw new IllegalArgumentException("Image is null");
            }

            if (image.getPlanes() == null) {
                throw new IllegalStateException("Image planes are null, possibly already closed");
            }

            Log.d("ImageProcessor", "Creating bitmap from image...");
            Bitmap mBitmap = bitmapImageCreateFrom(image);
            Log.d("ImageProcessor", "Bitmap created successfully");

            if (!settings.hasColor()) {
                Log.d("ImageProcessor", "Converting to grayscale...");
                mBitmap = toGrayScale(mBitmap);
            }

            return toByteArray(mBitmap);

        } catch (Exception e) {
            Log.e("ImageProcessor", "Error processing image", e);
            throw new RuntimeException("Failed to process", e);

        } finally {
            if (image != null) {
                image.close(); // Ensure the image is properly closed after processing
            }
        }
    }

    private Bitmap bitmapImageCreateFrom(Image mImage) {
        if (mImage == null) {
            throw new IllegalStateException("Image is null");
        }

        Image.Plane[] planes = mImage.getPlanes();
        if (planes == null || planes.length == 0) {
            throw new IllegalStateException("Image planes are not available");
        }

        ByteBuffer buffer = planes[0].getBuffer();
        buffer.rewind();  // Reset buffer position

        int rowStride = planes[0].getRowStride() / planes[0].getPixelStride();
        Bitmap bitmap = Bitmap.createBitmap(rowStride, mImage.getHeight(), Bitmap.Config.ARGB_8888);
        bitmap.copyPixelsFromBuffer(buffer);

        return rowStride > mImage.getWidth() ? Bitmap.createBitmap(bitmap, 0, 0, mImage.getWidth(), mImage.getHeight()) : bitmap;
    }

    private Bitmap toGrayScale(Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bitmap, 0, 0, grayScalePaint);
        return bitmap;
    }

    private byte[] toByteArray(Bitmap bitmap) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            int jpegQuality = settings.getJpegQuality();
            if (jpegQuality == 100) {
                drawWatermark(bitmap);
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, jpegQuality, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not create JPEG");
        }
    }

    private void drawWatermark(Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(-1);
        paint.setTextSize((float) (bitmap.getWidth() / 16));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);

        String watermarkText = "Native (Pro Feature)";
        float width = (float) bitmap.getWidth() / 2;
        float height = ((float) bitmap.getHeight() / 2) - (paint.descent() + paint.ascent()) / 2;
        canvas.drawText(watermarkText, width, height, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xFF000000); // Black stroke for text
        canvas.drawText(watermarkText, width, height, paint);
    }

    // ThreadUtil class should be implemented elsewhere in the project
    static class ThreadUtil {
        static void sleepMillis() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}