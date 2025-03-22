package com.connectsdk.service.webos.lgcast.remotecamera.capture;

import android.media.AudioRecord;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.connectsdk.service.webos.lgcast.remotecamera.RemoteCameraConfig;
import com.lge.lib.lgcast.iface.MediaData;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;


public class MicCapture {
    private AudioPcmTask mAudioPcmTask;
    private boolean mMicMute = false;
    private AtomicReference<Handler> mAudioStreamHandler = new AtomicReference<>();

    public void startMicCapture(boolean micMute) {
        Logger.print("startMicCapture (micMute=%s)", Boolean.valueOf(micMute));
        this.mMicMute = micMute;
        this.mAudioStreamHandler.set(null);
        AudioPcmTask audioPcmTask = new AudioPcmTask();
        this.mAudioPcmTask = audioPcmTask;
        audioPcmTask.execute(new Void[0]);
    }

    public void stopMicCapture() {
        Logger.print("stopMicCapture", new Object[0]);
        AudioPcmTask audioPcmTask = this.mAudioPcmTask;
        if (audioPcmTask != null) {
            audioPcmTask.cancel(true);
        }
        this.mAudioPcmTask = null;
    }

    public boolean changeMicMute(boolean micMute) {
        Logger.print("changeMicMute (micMute=%s)", Boolean.valueOf(micMute));
        if (this.mMicMute == micMute) {
            return false;
        }
        this.mMicMute = micMute;
        return true;
    }

    public void startStreaming(Handler audioStreamHandler) {
        this.mAudioStreamHandler.set(audioStreamHandler);
    }

    public void stopStreaming() {
        this.mAudioStreamHandler.set(null);
    }


    private class AudioPcmTask extends AsyncTask<Void, Void, Void> {
        final int AUDIO_SOURCE;
        final int BUFFER_SIZE;
        final int CHANNEL_MASK;
        final int ENCODING_BIT;
        final int SAMPLE_RATE;

        private AudioPcmTask() {
            this.AUDIO_SOURCE = 1;
            this.SAMPLE_RATE = RemoteCameraConfig.Mic.SAMPLING_RATE;
            this.ENCODING_BIT = 2;
            this.CHANNEL_MASK = 16;
            this.BUFFER_SIZE = 512;
        }

        @Override 
        public Void doInBackground(Void... params) {
            Logger.print("Start audio recording (BUFFER_SIZE=%d)", 512);
            AudioRecord audioRecord = new AudioRecord(1, RemoteCameraConfig.Mic.SAMPLING_RATE, 16, 2, 512);
            audioRecord.startRecording();
            while (!isCancelled()) {
                byte[] bArr = new byte[512];
                int read = audioRecord.read(bArr, 0, 512);
                if (MicCapture.this.mMicMute) {
                    Arrays.fill(bArr, 0, read, (byte) 0);
                }
                MicCapture.this.sendAudioSample(bArr);
            }
            Logger.debug("stop audio recording", new Object[0]);
            audioRecord.stop();
            audioRecord.release();
            return null;
        }
    }

    
    public void sendAudioSample(byte[] data) {
        Message obtain = Message.obtain();
        obtain.obj = new MediaData(0L, data);
        if (this.mAudioStreamHandler.get() != null) {
            this.mAudioStreamHandler.get().sendMessage(obtain);
        }
    }
}
