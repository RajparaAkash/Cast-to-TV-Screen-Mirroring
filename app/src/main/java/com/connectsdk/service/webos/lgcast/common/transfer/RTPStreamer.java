package com.connectsdk.service.webos.lgcast.common.transfer;

import android.content.Context;

import com.amazon.whisperlink.service.fling.media.SimplePlayerConstants;
import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.connectsdk.service.webos.lgcast.common.utils.ThreadWait;

import java.util.concurrent.atomic.AtomicBoolean;
//import org.freedesktop.gstreamer.GStreamer;


public class RTPStreamer {
    private static RTPStreamer instance = new RTPStreamer();
    private RTPStreamerSetting.StreamSetting mStreamSetting;
    private long native_custom_data;
    private AtomicBoolean mIsInitialized = new AtomicBoolean(false);
    private AtomicBoolean mIsStarted = new AtomicBoolean(false);
    private ThreadWait<Boolean> mStartWait = new ThreadWait<>();
    private AtomicBoolean mIsIFrameSent = new AtomicBoolean(false);

    private static native boolean nativeClassInit();

    private native void nativeFinalize();

    private native void nativeInit();

    private native boolean nativeSendRawData(int mediaType, long pts, byte[] data);

    private native void nativeSetStreamInfo(RTPStreamerSetting.StreamSetting info);

    private native boolean nativeStart();

    private native boolean nativeStop();

    private native void nativeUpdateMasterKey();

    static {
        System.loadLibrary("gstreamer_android");
        System.loadLibrary("gstreamer-appcast");
        nativeClassInit();
    }

    private RTPStreamer() {
    }

    public static RTPStreamer getInstance() {
        return instance;
    }

    public void initialize(Context context) {
        Logger.print("initialize", new Object[0]);
        try {
            if (this.mIsInitialized.get()) {
                Logger.error("ALREADY initialized...", new Object[0]);
                return;
            }
//            GStreamer.init(context);
            nativeInit();
            this.mIsInitialized.set(true);
        } catch (Exception e) {
            Logger.error(e);
            this.mIsInitialized.set(false);
        }
    }

    public void terminate() {
        Logger.print("terminate", new Object[0]);
        if (!this.mIsInitialized.get()) {
            Logger.error("NOT initialized...", new Object[0]);
            return;
        }
        nativeFinalize();
        this.mIsInitialized.set(false);
    }

    public void setStreamMode(RTPStreamerData.ProtocolType protocolType, RTPStreamerData.MediaType mediaType, long ssrc) {
        this.mStreamSetting = new RTPStreamerSetting.StreamSetting(protocolType, mediaType, ssrc);
    }

    public void setVideoConfig(RTPStreamerConfig.VideoConfig config) {
        try {
            if (config == null || config.bitrate <= 0 || config.framerate <= 0) throw new Exception("Invalid arguments");
            if (mStreamSetting == null || mStreamSetting.videoStreamInfo == null) throw new Exception("Invalid stream setting");
            mStreamSetting.videoStreamInfo.setVideoConfig(config);
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    public void setAudioConfig(RTPStreamerConfig.AudioConfig config) {
        try {
            if (config == null) {
                throw new Exception("Invalid arguments");
            }
            RTPStreamerSetting.StreamSetting streamSetting = this.mStreamSetting;
            if (streamSetting == null || streamSetting.audioStreamInfo == null) {
                throw new Exception("Invalid stream setting");
            }
            this.mStreamSetting.audioStreamInfo.setAudioConfig(config);
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    public void setSecurityConfig(RTPStreamerConfig.SecurityConfig config) {
        try {
            if (config == null) {
                throw new Exception("Invalid arguments");
            }
            RTPStreamerSetting.StreamSetting streamSetting = this.mStreamSetting;
            if (streamSetting == null || streamSetting.securityInfo == null) {
                throw new Exception("Invalid stream setting");
            }
            this.mStreamSetting.securityInfo.setSecurityConfig(config);
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    public void setVideoDstToUri(String host, int port) {
        try {
            if (host == null) {
                throw new Exception("Invalid arguments");
            }
            RTPStreamerSetting.StreamSetting streamSetting = this.mStreamSetting;
            if (streamSetting == null) {
                throw new Exception("Invalid stream setting");
            }
            streamSetting.setDestinationStream(RTPStreamerData.MediaType.VIDEO, host, port);
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    public void setAudioDstToUri(String host, int port) {
        try {
            if (host == null) {
                throw new Exception("Invalid arguments");
            }
            RTPStreamerSetting.StreamSetting streamSetting = this.mStreamSetting;
            if (streamSetting == null) {
                throw new Exception("Invalid stream setting");
            }
            streamSetting.setDestinationStream(RTPStreamerData.MediaType.AUDIO, host, port);
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    public boolean start() {
        Logger.print("start", new Object[0]);
        try {
            if (this.mIsStarted.get()) {
                Logger.error("ALREADY started...", new Object[0]);
                return true;
            }
            if (this.mStreamSetting.audioStreamInfo != null && !this.mStreamSetting.audioStreamInfo.isCompleted()) {
                throw new Exception("Need to init audio info!!");
            }
            if (this.mStreamSetting.videoStreamInfo != null && !this.mStreamSetting.videoStreamInfo.isCompleted()) {
                throw new Exception("Need to init video info!!");
            }
            if (this.mStreamSetting.securityInfo != null && !this.mStreamSetting.securityInfo.isCompleted()) {
                throw new Exception("Need to init security info!!");
            }
            nativeSetStreamInfo(this.mStreamSetting);
            nativeStart();
            if (!this.mStartWait.waitFor(SimplePlayerConstants.DEFAULT_POSITION_UPDATE_INTERVAL_MS, false).booleanValue()) {
                throw new Exception("Failed to start RTP streamer");
            }
            this.mIsStarted.set(true);
            return true;
        } catch (Exception e) {
            Logger.error(e);
            this.mIsStarted.set(false);
            return false;
        }
    }

    public void stop() {
        Logger.print("stop", new Object[0]);
        if (!this.mIsStarted.get()) {
            Logger.error("NOT started...", new Object[0]);
            return;
        }
        nativeStop();
        this.mIsStarted.set(false);
    }

    public void sendData(RTPStreamerData.MediaType mediaType, long nanosec, byte[] data) {
        if (data == null) {
            Logger.error("Invalid data", new Object[0]);
        } else {
            nativeSendRawData(mediaType.getType(), nanosec, data);
        }
    }

    public void updateMasterKey() {
        Logger.print("updateMasterKey..", new Object[0]);
        nativeUpdateMasterKey();
    }

    private void setMessage(final String message) {
        Logger.print("setMessage() called: ", message);
    }

    private void onGStreamerInitialized() {
        Logger.print("onGStreamerInitialized()", new Object[0]);
        this.mStartWait.wakeUp(true);
    }
}
