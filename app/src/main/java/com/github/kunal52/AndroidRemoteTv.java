package com.github.kunal52;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.github.kunal52.pairing.PairingListener;
import com.github.kunal52.pairing.PairingSession;
import com.github.kunal52.remote.RemoteSession;
import com.github.kunal52.remote.Remotemessage;

public class AndroidRemoteTv extends BaseAndroidRemoteTv {
    private final Context mContext;
    private PairingSession mPairingSession;
    private RemoteSession mRemoteSession;

    @Override
    public String getClientName() {
        return super.getClientName();
    }

    @Override
    public char[] getKeyStorePass() {
        return super.getKeyStorePass();
    }

    @Override
    public String getServiceName() {
        return super.getServiceName();
    }

    @Override
    public void setClientName(String str) {
        super.setClientName(str);
    }

    @Override
    public void setKeyStorePass(String str) {
        super.setKeyStorePass(str);
    }

    @Override
    public void setServiceName(String str) {
        super.setServiceName(str);
    }

    public AndroidRemoteTv(Context context) {
        this.mContext = context;
    }

    public void connect(String host, final AndroidTvListener androidTvListener) {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mRemoteSession = new RemoteSession(mContext, host, 6466, new RemoteSession.RemoteSessionListener() {

            @Override
            public void onDisconnected() {
                Log.e("fatal1", "AndroidRemoteTv onDisconnected: "  );
            }

            @Override
            public void onError(String str) {
                Log.e("fatal1", "AndroidRemoteTv onError: " + str  );
            }

            @Override
            public void onConnected() {
                Log.e("fatal1", "AndroidRemoteTv onConnected: "  );
                androidTvListener.onConnected();
            }

            @Override
            public void onSslError() {
                Log.e("fatal1", "AndroidRemoteTv onSslError: "  );
                defaultSharedPreferences.edit().putBoolean("isPartied", false).apply();
            }
        });
        boolean z = defaultSharedPreferences.getBoolean("isPartied", false);
        if (z) {
            Log.e("fatal1", "AndroidRemoteTv connect: " + "111" );
            mRemoteSession.start();
            return;
        }
        Log.e("fatal1", "AndroidRemoteTv connect: " + "222"  );
        mPairingSession = new PairingSession(mContext, new PairingListener() {

            @Override
            public void onError(String str) {
                Log.e("fatal1", "AndroidRemoteTv onError: " + str  );
            }

            @Override
            public void onLog(String str) {
                Log.e("fatal1", "AndroidRemoteTv onLog: " + str  );
            }

            @Override
            public void onPerformInputDeviceRole() {
                Log.e("fatal1", "AndroidRemoteTv onPerformInputDeviceRole: "  );
            }

            @Override
            public void onPerformOutputDeviceRole(byte[] bArr) {
                Log.e("fatal1", "AndroidRemoteTv onPerformOutputDeviceRole: " + bArr  );
            }

            @Override
            public void onSessionCreated() {
                Log.e("fatal1", "AndroidRemoteTv onSessionCreated: "  );
            }

            @Override
            public void onSessionEnded() {
                Log.e("fatal1", "AndroidRemoteTv onSessionEnded: "  );
            }

            @Override
            public void onSecretRequested() {
                Log.e("fatal1", "AndroidRemoteTv onSecretRequested: "  );
                androidTvListener.onSecretRequested();
            }

            @Override
            public void onPaired() {
                Log.e("fatal1", "AndroidRemoteTv onPaired: "   );
                defaultSharedPreferences.edit().putBoolean("isPartied", true).apply();
                mRemoteSession.start();
            }
        });
        mPairingSession.start();
    }

    public void sendCommand(Remotemessage.RemoteKeyCode remoteKeyCode, Remotemessage.RemoteDirection remoteDirection) {
        Log.e("fatal", "sendCommand : " + mRemoteSession );
        mRemoteSession.sendCommand(remoteKeyCode, remoteDirection);
    }

    public void sendAppLinkCommand(String str) {
        try {
            Log.e("fatal", "sendAppLinkCommand 111: " + str );
            mRemoteSession.openApp(str);
        } catch (Exception unused) {
            Log.e("fatal", "sendAppLinkCommand 222: " + unused.getMessage() );
        }
    }

    public void abort() {
        if (mRemoteSession != null) {
            mRemoteSession.abort();
        }
        if (mPairingSession != null) {
            mPairingSession.abort();
        }
    }

    public void sendSecret(String str) {
        mPairingSession.provideSecret(str);
    }

}
