package com.connectsdk.service.webos.lgcast.remotecamera.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.connectsdk.service.webos.lgcast.common.transfer.RTPStreamerConfig;
import com.connectsdk.service.webos.lgcast.common.transfer.RTPStreamerData;
import com.connectsdk.service.webos.lgcast.remotecamera.RemoteCameraConfig;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.lge.lib.lgcast.iface.MasterKey;

import java.util.ArrayList;
import java.util.Iterator;

public class CameraServiceFunc {

    private static final String NOTI_CHANNEL_ID = "LG_CAST_REMOTE_CAMERA";
    private static final String NOTI_CHANNEL_NAME = "LG Cast Remote Camera";

    public static Notification createNotification(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(new NotificationChannel(NOTI_CHANNEL_ID, NOTI_CHANNEL_NAME, 2));
        }
        PendingIntent service = PendingIntent.getService(context, 0, new Intent(context, CameraService.class).setAction(CameraServiceIF.ACTION_STOP_BY_NOTIFICATION), 1140850688);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTI_CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(context.getString(R.string.notification_remote_camera_title));
        builder.setContentText(context.getString(R.string.notification_remote_camera_desc));
        builder.addAction(R.mipmap.ic_launcher, context.getString(R.string.notification_disconnect_action), service);
        return builder.build();
    }

    public static String getDeviceIpAddress(Intent intent) {
        if (intent != null) {
            return intent.getStringExtra(CameraServiceIF.EXTRA_DEVICE_IP_ADDRESS);
        }
        return null;
    }

    public static RTPStreamerConfig.VideoConfig createRtpVideoConfig(int width, int height) {
        RTPStreamerConfig.VideoConfig videoConfig = new RTPStreamerConfig.VideoConfig();
        videoConfig.setType(RTPStreamerData.VideoType.MJPEG);
        videoConfig.setEnableMP(false);
        videoConfig.setMpUnitSize((int) (width * height * 1.5d));
        videoConfig.setWidth(width);
        videoConfig.setHeight(height);
        videoConfig.setFramerate(30);
        videoConfig.setBitrate(4194304);
        return videoConfig;
    }

    public static RTPStreamerConfig.AudioConfig createRtpAudioConfig() {
        RTPStreamerConfig.AudioConfig audioConfig = new RTPStreamerConfig.AudioConfig();
        audioConfig.setType(RTPStreamerData.AudioType.PCM);
        audioConfig.setSamplingRate(RemoteCameraConfig.Mic.SAMPLING_RATE);
        audioConfig.setChannelCnt(1);
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
}
