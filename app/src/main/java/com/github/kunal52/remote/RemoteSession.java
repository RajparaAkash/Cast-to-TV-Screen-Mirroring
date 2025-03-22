package com.github.kunal52.remote;

import android.content.Context;
import android.util.Log;

import com.github.kunal52.AndroidRemoteContext;
import com.github.kunal52.exception.PairingException;
import com.github.kunal52.ssl.DummyTrustManager;
import com.github.kunal52.ssl.KeyStoreManager;
import com.github.kunal52.wire.PacketParser;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;

public class RemoteSession extends Thread {
    private static RemoteMessageManager mMessageManager;
    private final Context mContext;
    private final String mHost;
    private final BlockingQueue<Remotemessage.RemoteMessage> mMessageQueue;
    private final int mPort;
    private final RemoteSessionListener mRemoteSessionListener;
    private OutputStream outputStream;
    private PacketParser packetParser;

    public RemoteSession(Context context, String str, int i, RemoteSessionListener remoteSessionListener) {
        mMessageManager = new RemoteMessageManager();
        AndroidRemoteContext.getInstance().setHost(str);
        mMessageQueue = new LinkedBlockingQueue();
        this.mHost = str;
        this.mPort = i;
        this.mRemoteSessionListener = remoteSessionListener;
        this.mContext = context;
    }

    /* CODE */
    public void run1() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(new KeyStoreManager(mContext).getKeyManagers(), new TrustManager[]{new DummyTrustManager()}, new SecureRandom());
            SSLSocket sSLSocket = (SSLSocket) instance.getSocketFactory().createSocket(mHost, mPort);
            sSLSocket.setNeedClientAuth(true);
            sSLSocket.setUseClientMode(true);
            sSLSocket.setKeepAlive(true);
            sSLSocket.setTcpNoDelay(true);
            sSLSocket.startHandshake();
            outputStream = sSLSocket.getOutputStream();
            packetParser = new RemotePacketParser(sSLSocket.getInputStream(), outputStream, mMessageQueue, new RemoteListener() {

                @Override
                public void onDisconnected() {
                    Log.e("fatal1", "RemoteSession onDisconnected: ");
                }

                @Override
                public void onError(String str) {
                    Log.e("fatal1", "RemoteSession onError: " + str);
                }

                @Override
                public void onLog(String str) {
                    Log.e("fatal1", "RemoteSession onLog: " + str);
                }

                @Override
                public void onPerformInputDeviceRole() throws PairingException {
                    Log.e("fatal1", "RemoteSession onPerformInputDeviceRole: ");
                }

                @Override
                public void onPerformOutputDeviceRole(byte[] bArr) throws PairingException {
                    Log.e("fatal1", "RemoteSession onPerformOutputDeviceRole: ");
                }

                @Override
                public void onSessionEnded() {
                    Log.e("fatal1", "RemoteSession onSessionEnded: ");
                }

                @Override
                public void onVolume() {
                    Log.e("fatal1", "RemoteSession onVolume: ");
                }

                @Override
                public void sSLException() {
                    Log.e("fatal1", "RemoteSession sSLException: ");
                }

                @Override
                public void onConnected() {
                    Log.e("fatal1", "RemoteSession onConnected: ");
                    mRemoteSessionListener.onConnected();
                }
            });
            packetParser.start();
            waitForMessage();
            outputStream.write(mMessageManager.createRemoteConfigure(622, AndroidRemoteContext.getInstance().getModel(), AndroidRemoteContext.getInstance().getVendor(), 1, "1"));
            waitForMessage();
            outputStream.write(mMessageManager.createRemoteActive(622));
        } catch (SSLException unknown) {
            Log.e("fatal", "RemoteSession exce 111: " + unknown.getMessage());
            try {
                mRemoteSessionListener.onSslError();
            } catch (GeneralSecurityException e) {
                Log.e("fatal", "RemoteSession exce 222: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("fatal", "RemoteSession exce 333: " + e.getMessage());
                e.printStackTrace();
            } catch (InterruptedException e) {
                Log.e("fatal", "RemoteSession exce 444: " + e.getMessage());
                e.printStackTrace();
            } catch (PairingException e) {
                Log.e("fatal", "RemoteSession exce 555: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.e("fatal", "RemoteSession exce 666: " + e.getMessage());
            e.printStackTrace();
            mRemoteSessionListener.onError(e.getMessage());
            return;
        }
//        try {
//            mRemoteSessionListener.onSslError();
//        } catch (PairingException | IOException | InterruptedException |
//                 GeneralSecurityException e2) {
//            Log.e("fatal", "RemoteSession 777: " + e2.getMessage() );
//            throw new RuntimeException(e2);
//        }
    }

    public void run() {
        try {
            final SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(new KeyStoreManager(this.mContext).getKeyManagers(), new TrustManager[]{(TrustManager) new DummyTrustManager()}, new SecureRandom());
            final SSLSocket sslSocket = (SSLSocket) instance.getSocketFactory().createSocket(this.mHost, this.mPort);
            sslSocket.setNeedClientAuth(true);
            sslSocket.setUseClientMode(true);
            sslSocket.setKeepAlive(true);
            sslSocket.setTcpNoDelay(true);
            sslSocket.startHandshake();
            outputStream = sslSocket.getOutputStream();
            (packetParser = (PacketParser) new RemotePacketParser(sslSocket.getInputStream(), outputStream, (BlockingQueue) mMessageQueue, (RemoteListener) new RemoteListener() {

                @Override
                public void onDisconnected() {
                    Log.e("fatal1", "RemoteSession onDisconnected: ");
                }

                @Override
                public void onError(String str) {
                    Log.e("fatal1", "RemoteSession onError: " + str);
                }

                @Override
                public void onLog(String str) {
                    Log.e("fatal1", "RemoteSession onLog: " + str);
                }

                @Override
                public void onPerformInputDeviceRole() throws PairingException {
                    Log.e("fatal1", "RemoteSession onPerformInputDeviceRole: ");
                }

                @Override
                public void onPerformOutputDeviceRole(byte[] bArr) throws PairingException {
                    Log.e("fatal1", "RemoteSession onPerformOutputDeviceRole: ");
                }

                @Override
                public void onSessionEnded() {
                    Log.e("fatal1", "RemoteSession onSessionEnded: ");
                }

                @Override
                public void onVolume() {
                    Log.e("fatal1", "RemoteSession onVolume: ");
                }

                @Override
                public void sSLException() {
                    Log.e("fatal1", "RemoteSession sSLException: ");
                }

                @Override
                public void onConnected() {
                    Log.e("fatal1", "RemoteSession onConnected: ");
                    mRemoteSessionListener.onConnected();
                }
            })).start();
            this.waitForMessage();
            this.outputStream.write(RemoteSession.mMessageManager.createRemoteConfigure(622, AndroidRemoteContext.getInstance().getModel(), AndroidRemoteContext.getInstance().getVendor(), 1, "1"));
            this.waitForMessage();
            final byte[] remoteActive = RemoteSession.mMessageManager.createRemoteActive(622);
            this.outputStream.write(remoteActive);
        } catch (final Exception remoteActive) {
            ((Throwable) (Object) remoteActive).printStackTrace();
            this.mRemoteSessionListener.onError(((Throwable) (Object) remoteActive).getMessage());
        }
    }

    public Remotemessage.RemoteMessage waitForMessage() throws InterruptedException {
        return mMessageQueue.take();
    }

    public void sendCommand(Remotemessage.RemoteKeyCode remoteKeyCode, Remotemessage.RemoteDirection remoteDirection) {
        try {
            Log.e("fatal", "sendCommand 111: " + mMessageManager );
            outputStream.write(mMessageManager.createKeyCommand(remoteKeyCode, remoteDirection));
        } catch (IOException e) {
            Log.e("fatal", "sendCommand exce: " + e.getMessage() );
            throw new RuntimeException(e);
        }
    }

    public void openApp(String str) {
        try {
            outputStream.write(mMessageManager.createAppLinkCommand(str));
        } catch (IOException e) {
            Log.e("fatal", "openApp: " + e.getMessage() );
            throw new RuntimeException(e);
        }
    }

    public void abort() {
        packetParser.abort();
    }

    public interface RemoteSessionListener {
        void onConnected();

        void onDisconnected();

        void onError(String str);

        void onSslError() throws GeneralSecurityException, IOException, InterruptedException, PairingException;
    }

}
