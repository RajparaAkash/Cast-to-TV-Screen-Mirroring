package com.casttotv.screenmirroring.chromecast.smart.tv.AndroidTv;

import android.content.Context;
import android.util.Log;

import com.github.kunal52.AndroidRemoteTv;
import com.github.kunal52.remote.Remotemessage;

public class AndroidTvManager {

    public static final AndroidTvManager INSTANCE = new AndroidTvManager();
    private static AndroidRemoteTv androidRemoteTv;

    private AndroidTvManager() {
    }

    public AndroidRemoteTv getAndroidRemoteTv() {
        return androidRemoteTv;
    }

    public void setAndroidRemoteTv(AndroidRemoteTv androidRemoteTv2) {
        androidRemoteTv = androidRemoteTv2;
    }

    public void initAndroidRemote(Context context) {
        androidRemoteTv = new AndroidRemoteTv(context);
    }

    public void sendKey(Remotemessage.RemoteKeyCode remoteKeyCode) {
        try {
            AndroidRemoteTv androidRemoteTv = AndroidTvManager.INSTANCE.getAndroidRemoteTv();
            Log.e("fatal", "sendKey 111: " + androidRemoteTv + "           " + remoteKeyCode );
            if (androidRemoteTv != null) {
                androidRemoteTv.sendCommand(remoteKeyCode, Remotemessage.RemoteDirection.SHORT);
            }
        } catch (Exception e) {
            Log.e("fatal", "sendKey: " + e.getMessage() );
        }
    }

    public void openApp(String str) {
        AndroidRemoteTv androidRemoteTv = AndroidTvManager.INSTANCE.getAndroidRemoteTv();
        if (androidRemoteTv != null) {
            androidRemoteTv.sendAppLinkCommand(str);
        }
    }
}
