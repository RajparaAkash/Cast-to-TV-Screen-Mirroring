package com.connectsdk.service.webos.lgcast.screenmirroring.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

import com.connectsdk.service.webos.lgcast.common.transfer.RTPStreamerConfig;
import com.connectsdk.service.webos.lgcast.common.transfer.RTPStreamerData;
import com.connectsdk.service.webos.lgcast.common.utils.AppUtil;
import com.connectsdk.service.webos.lgcast.common.utils.DeviceUtil;
import com.connectsdk.service.webos.lgcast.common.utils.JSONObjectEx;
import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.connectsdk.service.webos.lgcast.screenmirroring.ScreenMirroringConfig;
import com.connectsdk.service.webos.lgcast.screenmirroring.capability.MirroringSinkCapability;
import com.connectsdk.service.webos.lgcast.screenmirroring.capability.MirroringSourceCapability;
import com.connectsdk.service.webos.lgcast.screenmirroring.uibc.UibcAccessibilityService;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.lge.lib.lgcast.iface.MasterKey;
import com.lge.lib.lgcast.iface.MasterKeyFactoryIF;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MirroringServiceFunc {

    private static final String ACTIVE_HEIGHT = "activeHeight";
    private static final String ACTIVE_WIDTH = "activeWidth";
    private static final String HEIGHT = "height";
    private static final String LANDSCAPE = "landscape";
    private static final String LANDSCAPE_OR_PORTRAIT = "landscape|portrait";
    private static final String NOTI_CHANNEL_ID = "LG_CAST_SCREEN_MIRRORING";
    private static final String NOTI_CHANNEL_NAME = "LG Cast Screen Mirroring";
    private static final String ORIENTATION = "orientation";
    private static final String PORTRAIT = "portrait";
    private static final String UIBC_ENABLED = "uibcEnabled";
    private static final String VIDEO = "video";
    private static final String WIDTH = "width";

    public static Notification createNotification(Context context) {
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(new NotificationChannel(NOTI_CHANNEL_ID, NOTI_CHANNEL_NAME, 2));
        PendingIntent service = PendingIntent.getService(context, 0, new Intent(context, MirroringService.class).setAction(MirroringServiceIF.ACTION_STOP_BY_NOTIFICATION), 1140850688);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTI_CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(context.getString(R.string.notification_screen_sharing_title));
        builder.setContentText(context.getString(R.string.notification_screen_sharing_desc));
        builder.addAction(R.mipmap.ic_launcher, context.getString(R.string.notification_disconnect_action), service);
        return builder.build();
    }

    public static MediaProjection getMediaProjection(Context context, Intent intent) {
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) context.getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        if (intent != null) {
            return mediaProjectionManager.getMediaProjection(-1, (Intent) intent.getParcelableExtra(MirroringServiceIF.EXTRA_PROJECTION_DATA));
        }
        return null;
    }

    public static String getDeviceIpAddress(Intent intent) {
        if (intent != null) {
            return intent.getStringExtra(MirroringServiceIF.EXTRA_DEVICE_IP_ADDRESS);
        }
        return null;
    }

    public static String getPackageName(Intent intent) {
        if (intent != null) {
            return intent.getStringExtra(MirroringServiceIF.EXTRA_PACKAGE_NAME);
        }
        return null;
    }

    public static boolean isDualScreen(Intent intent) {
        if (intent != null) {
            return intent.getBooleanExtra(MirroringServiceIF.EXTRA_IS_DUAL_SCREEN, false);
        }
        return false;
    }

    public static boolean isCaptureByDisplaySize(Context context) {
        return DeviceUtil.getTotalMemorySpace(context) <= 3221225472L;
    }

    public static Point getCaptureSizeInLandscape(Context context) {
        if (isCaptureByDisplaySize(context)) {
            return AppUtil.getDisplaySizeInLandscape(context);
        }
        return new Point(1920, 1080);
    }

    public static MirroringSinkCapability createPcMirroringSinkCapa() {
        MirroringSinkCapability mirroringSinkCapability = new MirroringSinkCapability();
        mirroringSinkCapability.ipAddress = ScreenMirroringConfig.Test.pcIpAddress;
        mirroringSinkCapability.videoUdpPort = 5000;
        mirroringSinkCapability.audioUdpPort = 5002;
        mirroringSinkCapability.videoLandscapeMaxWidth = 1920;
        mirroringSinkCapability.videoLandscapeMaxHeight = 1080;
        mirroringSinkCapability.videoPortraitMaxWidth = 1080;
        mirroringSinkCapability.videoPortraitMaxHeight = 1920;
        return mirroringSinkCapability;
    }

    public static MirroringSourceCapability createMirroringSourceCapa(Context context, Intent intent, MirroringSinkCapability mirroringSinkCapability) {
        Logger.debug("captureByDisplaySize=%s, isDisplayLandscape=%s", Boolean.valueOf(isCaptureByDisplaySize(context)), Boolean.valueOf(mirroringSinkCapability.isDisplayLandscape()));
        Point calculateVideoCaptureSize = calculateVideoCaptureSize(context, mirroringSinkCapability);
        Point calculateVideoActiveSize = calculateVideoActiveSize(context, calculateVideoCaptureSize);
        Logger.error("##### MIRRORING SOURCE CAPABILITY (onConnectionPrepared) #####", new Object[0]);
        Logger.error("display orientation=" + mirroringSinkCapability.displayOrientation, new Object[0]);
        Logger.error("phone orientation=" + (AppUtil.isLandscape(context) ? "landscape" : "portrait"), new Object[0]);
        Logger.error("capture width=" + calculateVideoCaptureSize.x, new Object[0]);
        Logger.error("capture height=" + calculateVideoCaptureSize.y, new Object[0]);
        Logger.error("active width=" + calculateVideoActiveSize.x, new Object[0]);
        Logger.error("active height=" + calculateVideoActiveSize.y, new Object[0]);
        Logger.error("--------------------------------------------------------------", new Object[0]);
        MirroringSourceCapability mirroringSourceCapability = new MirroringSourceCapability();
        mirroringSourceCapability.videoCodec = ScreenMirroringConfig.Video.CODEC;
        mirroringSourceCapability.videoClockRate = ScreenMirroringConfig.Video.CLOCK_RATE;
        mirroringSourceCapability.videoFramerate = 60;
        mirroringSourceCapability.videoWidth = calculateVideoCaptureSize.x;
        mirroringSourceCapability.videoHeight = calculateVideoCaptureSize.y;
        mirroringSourceCapability.videoActiveWidth = calculateVideoActiveSize.x;
        mirroringSourceCapability.videoActiveHeight = calculateVideoActiveSize.y;
        mirroringSourceCapability.videoOrientation = !AppUtil.isLandscape(context) ? "portrait" : "landscape";
        mirroringSourceCapability.audioCodec = ScreenMirroringConfig.Audio.CODEC;
        mirroringSourceCapability.audioClockRate = ScreenMirroringConfig.Audio.SAMPLING_RATE;
        mirroringSourceCapability.audioFrequency = ScreenMirroringConfig.Audio.SAMPLING_RATE;
        mirroringSourceCapability.audioStreamMuxConfig = ScreenMirroringConfig.Audio.STREAM_MUX_CONFIG;
        mirroringSourceCapability.audioChannels = 2;
        mirroringSourceCapability.masterKeys = new MasterKeyFactoryIF().createKeys(mirroringSinkCapability.publicKey);
        mirroringSourceCapability.uibcEnabled = isUibcEnabled(context);
        mirroringSourceCapability.screenOrientation = LANDSCAPE_OR_PORTRAIT;
        return mirroringSourceCapability;
    }

    public static JSONObject createVideoSizeInfo(Context context, MirroringSinkCapability mirroringSinkCapability) {
        Logger.debug("captureByDisplaySize=%s, isDisplayLandscape=%s", Boolean.valueOf(isCaptureByDisplaySize(context)), Boolean.valueOf(mirroringSinkCapability.isDisplayLandscape()));
        Point calculateVideoCaptureSize = calculateVideoCaptureSize(context, mirroringSinkCapability);
        Point calculateVideoActiveSize = calculateVideoActiveSize(context, calculateVideoCaptureSize);
        Logger.error("##### MIRRORING SOURCE CAPABILITY (onDisplayRotated) #####", new Object[0]);
        Logger.error("display orientation=" + mirroringSinkCapability.displayOrientation, new Object[0]);
        Logger.error("phone orientation=" + (AppUtil.isLandscape(context) ? "landscape" : "portrait"), new Object[0]);
        Logger.error("capture width=" + calculateVideoCaptureSize.x, new Object[0]);
        Logger.error("capture height=" + calculateVideoCaptureSize.y, new Object[0]);
        Logger.error("active width=" + calculateVideoActiveSize.x, new Object[0]);
        Logger.error("active height=" + calculateVideoActiveSize.y, new Object[0]);
        Logger.error("--------------------------------------------------------------", new Object[0]);
        JSONObjectEx jSONObjectEx = new JSONObjectEx();
        jSONObjectEx.put("width", calculateVideoCaptureSize.x);
        jSONObjectEx.put("height", calculateVideoCaptureSize.y);
        jSONObjectEx.put(ACTIVE_WIDTH, calculateVideoActiveSize.x);
        jSONObjectEx.put(ACTIVE_HEIGHT, calculateVideoActiveSize.y);
        jSONObjectEx.put("orientation", !AppUtil.isLandscape(context) ? "portrait" : "landscape");
        JSONObjectEx jSONObjectEx2 = new JSONObjectEx();
        jSONObjectEx2.put(VIDEO, jSONObjectEx);
        return jSONObjectEx2.toJSONObject();
    }

    public static RTPStreamerConfig.VideoConfig createRtpVideoConfig(int videoBitRate) {
        RTPStreamerConfig.VideoConfig videoConfig = new RTPStreamerConfig.VideoConfig();
        videoConfig.setType(RTPStreamerData.VideoType.H264);
        videoConfig.setEnableMP(false);
        videoConfig.setFramerate(60);
        videoConfig.setBitrate(videoBitRate);
        return videoConfig;
    }

    public static RTPStreamerConfig.AudioConfig createRtpAudioConfig() {
        RTPStreamerConfig.AudioConfig audioConfig = new RTPStreamerConfig.AudioConfig();
        audioConfig.setType(RTPStreamerData.AudioType.AAC);
        audioConfig.setCodecData(new byte[]{17, -112});
        audioConfig.setSamplingRate(ScreenMirroringConfig.Audio.SAMPLING_RATE);
        audioConfig.setChannelCnt(2);
        audioConfig.setEnableMP(false);
        return audioConfig;
    }

    public static RTPStreamerConfig.SecurityConfig createRtpSecurityConfig(ArrayList<MasterKey> masterKeys) {
        ArrayList<RTPStreamerConfig.SecurityKey> arrayList = new ArrayList<>();
        Iterator<MasterKey> it = masterKeys.iterator();
        while (it.hasNext()) {
            MasterKey next = it.next();
            RTPStreamerConfig.SecurityKey securityKey = new RTPStreamerConfig.SecurityKey();
            securityKey.masterKey = next.key;
            securityKey.mki = next.mki;
            arrayList.add(securityKey);
        }
        RTPStreamerConfig.SecurityConfig securityConfig = new RTPStreamerConfig.SecurityConfig();
        securityConfig.setEnableSecurity(true);
        securityConfig.setCipherType(RTPStreamerData.SRTPCipherType.AES_128_ICM);
        securityConfig.setAuthType(RTPStreamerData.SRTPAuthType.HMAC_SHA1_80);
        securityConfig.setKeys(arrayList);
        return securityConfig;
    }

    public static JSONObject createUibcInfo(Context context) {
        return new JSONObjectEx().put(UIBC_ENABLED, isUibcEnabled(context)).toJSONObject();
    }

    public static boolean isUibcEnabled(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        return string != null && string.contains(new StringBuilder().append(context.getPackageName()).append("/").append(UibcAccessibilityService.class.getName()).toString());
    }

    private static Point calculateVideoCaptureSize(Context context, MirroringSinkCapability mirroringSinkCapability) {
        boolean isCaptureByDisplaySize = isCaptureByDisplaySize(context);
        boolean isDisplayLandscape = mirroringSinkCapability.isDisplayLandscape();
        Point displaySizeInLandscape = AppUtil.getDisplaySizeInLandscape(context);
        Logger.debug("captureByDisplaySize=%s, isDisplayLandscape=%s", Boolean.valueOf(isCaptureByDisplaySize), Boolean.valueOf(isDisplayLandscape));
        int i = isCaptureByDisplaySize ? displaySizeInLandscape.x : 1920;
        int i2 = isCaptureByDisplaySize ? displaySizeInLandscape.y : 1080;
        return isDisplayLandscape ? new Point(i, i2) : new Point(i2, i);
    }

    private static Point calculateVideoActiveSize(Context context, Point captureSize) {
        Point point = new Point();
        Point displaySize = AppUtil.getDisplaySize(context);
        if (AppUtil.isLandscape(context)) {
            point.x = captureSize.x;
            point.y = (int) Math.round((displaySize.y * point.x) / displaySize.x);
        } else {
            point.y = captureSize.y;
            point.x = (int) Math.round((displaySize.x * point.y) / displaySize.y);
        }
        return point;
    }
}
