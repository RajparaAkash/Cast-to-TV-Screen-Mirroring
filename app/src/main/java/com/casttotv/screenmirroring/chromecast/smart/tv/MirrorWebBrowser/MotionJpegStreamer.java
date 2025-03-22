package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.ImageReader;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class MotionJpegStreamer implements ImageReader.OnImageAvailableListener {

    private final Queue<StreamingClient> clients = new ConcurrentLinkedQueue<>();
    private final Context context;
    private ImageProcessor imageProcessor;
    private ImageServer imageServer;
    private byte[] lastImage;

    public MotionJpegStreamer(Context context) {
        this.context = context.getApplicationContext();
    }

    public Collection<StreamingClient> getConnectedClients() {
        return Collections.unmodifiableCollection(clients);
    }

    public void start() {
        this.imageServer = new ImageServer();
        this.imageProcessor = new ImageProcessor(context) {

            @Override
            public void onImageProcessed(byte[] bArr) {
                lastImage = bArr;
                imageServer.registerNewImage(bArr);
            }

            @Override
            public void onShutDown() {
                shutdownClients();
            }
        };
        imageProcessor.start();
    }

    public void shutDown() {
        imageProcessor.interrupt();
        waitForImageProcessorToFinish();
        imageServer.shutDown();
    }

    private void shutdownClients() {
        for (StreamingClient client : clients) {
            try {
                client.getSocket().close();
                clients.remove(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void waitForImageProcessorToFinish() {
        while (!imageProcessor.isInStateFinished()) {
            ThreadUtil.sleepMillis(5);
        }
    }

    public synchronized void add(Socket socket) throws IOException {
        StreamingClient client = new StreamingClient(socket);
        client.sendHeader();
        notifyConnectionEstablished();
        if (lastImage != null) {
            client.streamImage(lastImage);
        }
        clients.add(client);
        imageServer.updateImage();
    }

    private void notifyConnectionEstablished() {
        try {
            Intent intent = new Intent(LiveScreenIntent.CONNECTION_ESTABLISHED);
            intent.putExtra(NotificationCompat.CATEGORY_STATUS, 1);
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getLastImage() {
        return this.lastImage;
    }

    public synchronized void remove(StreamingClient streamingClient) {
        try {
            this.clients.remove(streamingClient);
            streamingClient.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onImageAvailable(ImageReader imageReader) {
        try {
            Image acquireLatestImage = imageReader.acquireLatestImage();
            if (acquireLatestImage != null) {
                this.imageProcessor.registerNewImage(acquireLatestImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.context, "Streaming interrupted or not supported for your device.", Toast.LENGTH_LONG).show();
        }
    }

    public void pause() {
        this.imageProcessor.pauseProcessing();
    }

    public void resume() {
        this.imageProcessor.resumeProcessing();
    }

    public class ImageServer {
        private final ExecutorService executorService = Executors.newFixedThreadPool(3);
        private final AtomicReference<byte[]> imageBuffer = new AtomicReference<>();
        private final AtomicReference<byte[]> imageBufferLast = new AtomicReference<>();
        private final Thread serverThread;

        public ImageServer() {
            serverThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    byte[] image = imageBuffer.getAndSet(null);
                    if (image != null) {
                        serve(image);
                    }
                    ThreadUtil.sleepMillis(10);
                }
                executorService.shutdownNow();
            });
            serverThread.start();
        }

        public void updateImage() {
            imageBuffer.set(imageBufferLast.get());
        }

        public void registerNewImage(byte[] image) {
            imageBuffer.set(image);
            if (image != null) {
                imageBufferLast.set(image);
            }
        }

        private void serve(byte[] image) {
            submitTasks(createParallelStreamingTasks(image));
        }

        private List<Callable<Void>> createParallelStreamingTasks(final byte[] image) {
            List<Callable<Void>> tasks = new ArrayList<>(clients.size());
            for (final StreamingClient client : clients) {
                tasks.add(() -> {
                    try {
                        client.streamImage(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                        remove(client);
                    }
                    return null;
                });
            }
            return tasks;
        }

        private void submitTasks(List<Callable<Void>> tasks) {
            try {
                executorService.invokeAll(tasks);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public void shutDown() {
            serverThread.interrupt();
        }
    }
}
