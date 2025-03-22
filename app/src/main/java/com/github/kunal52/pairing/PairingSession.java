package com.github.kunal52.pairing;

import android.content.Context;
import android.util.Log;

import com.github.kunal52.AndroidRemoteContext;
import com.github.kunal52.exception.PairingException;
import com.github.kunal52.ssl.DummyTrustManager;
import com.github.kunal52.ssl.KeyStoreManager;
import com.github.kunal52.util.Utils;

import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;

public class PairingSession extends Thread {
    private final Condition condition;
    private final ReentrantLock lock;
    private final Context mContext;
    private BlockingQueue<Pairingmessage.PairingMessage> mMessagesQueue;
    private final PairingListener mPairingListener;
    private PairingMessageManager mPairingMessageManager;
    SecretProvider secretProvider;
    private String mPairingSecret;
    private SSLSocket mSslSocket;
    private PairingPacketParser pairingPacketParser;


    public PairingSession(Context context, PairingListener pairingListener) {
        this.mPairingListener = pairingListener;
        this.mContext = context;
        lock = new ReentrantLock();
        this.condition = lock.newCondition();
        mMessagesQueue = new LinkedBlockingDeque();
        mPairingMessageManager = new PairingMessageManager();
    }

    public void logReceivedMessage(String str) {
        Log.e("fatal2", "logReceivedMessage: " + str );
    }

    public void logSendMessage(String str) {
    }

    public void run() {
        try {
            this.lock.lock();
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(new KeyStoreManager(mContext).getKeyManagers(), new TrustManager[]{new DummyTrustManager()}, new SecureRandom());
            mSslSocket = (SSLSocket) instance.getSocketFactory().createSocket(AndroidRemoteContext.getInstance().getHost(), 6467);
            Log.e("fatal2", "run 111111111111111: " );
            mPairingListener.onSessionCreated();
            Log.e("fatal2", "run 222222222222222: " );
            pairingPacketParser = new PairingPacketParser(mSslSocket.getInputStream(), mMessagesQueue);
            pairingPacketParser.start();
            Log.e("fatal2", "run 33333333333333333: " );
            OutputStream outputStream = mSslSocket.getOutputStream();
            outputStream.write(mPairingMessageManager.createPairingMessage(AndroidRemoteContext.getInstance().getClientName(), AndroidRemoteContext.getInstance().getServiceName()));
            logReceivedMessage(waitForMessage().toString());
            Log.e("fatal2", "run 444444444444444444444: " );
            outputStream.write(new PairingMessageManager().createPairingOption());
            Log.e("fatal2", "run 5555555555555555555555: " );
            logReceivedMessage(waitForMessage().toString());
            Log.e("fatal2", "run 666666666666666666: " );
            outputStream.write(new PairingMessageManager().createConfigMessage());
            Log.e("fatal2", "run 7777777777777777: " );
            logReceivedMessage(waitForMessage().toString());
            Log.e("fatal2", "run 88888888888888888888: " );
            if (secretProvider != null) {
                secretProvider.requestSecret(this);
            }
            Log.e("fatal2", "run 99999999999999999: " );
            mPairingListener.onSecretRequested();
            Log.e("fatal2", "run aaaaaaaaaaaaaaaaaaa: " );
            condition.await();
            outputStream.write(mPairingMessageManager.createSecretMessage(createCodeSecret()));
            Log.e("fatal2", "run bbbbbbbbbbbbbbbbbb: " );
            logReceivedMessage(waitForMessage().toString());
            Log.e("fatal2", "run cccccccccccccc: " );
            mPairingListener.onPaired();
            Log.e("fatal2", "run ddddddddddddddddddddd: " );
            mPairingListener.onSessionEnded();
        } catch (Exception e) {
            Log.e("fatal2", "run 111: " + e.getMessage() );
            mPairingListener.onError(e.getMessage());
        } catch (Throwable th) {
            Log.e("fatal2", "run 222: " + th.getMessage() );
            lock.unlock();
            throw th;
        }
        lock.unlock();
    }


    public Pairingmessage.PairingMessage waitForMessage() throws InterruptedException, PairingException {
        Pairingmessage.PairingMessage take = mMessagesQueue.take();
        if (take.getStatus() == Pairingmessage.PairingMessage.Status.STATUS_OK) {
            return take;
        }
        throw new PairingException(take.toString());
    }

    public void provideSecret(String str) {
        this.lock.lock();
        this.mPairingSecret = str;
        this.condition.signal();
        this.lock.unlock();
    }

    private Pairingmessage.PairingMessage createCodeSecret() {
        mPairingSecret = mPairingSecret.substring(2);
        PairingChallengeResponse pairingChallengeResponse = new PairingChallengeResponse(Utils.getLocalCert(mSslSocket.getSession()), Utils.getPeerCert(mSslSocket.getSession()));
        byte[] bArr = new byte[0];
        if (Utils.isHexString(mPairingSecret)) {
            bArr = Utils.hexStringToBytes(mPairingSecret);
        }
        try {
            pairingChallengeResponse.checkGamma(bArr);
            try {
                return mPairingMessageManager.createSecretMessageProto(pairingChallengeResponse.getAlpha(bArr));
            } catch (PairingException e) {
                throw new RuntimeException(e);
            }
        } catch (PairingException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void abort() {
        if (pairingPacketParser != null) {
            pairingPacketParser.abort();
        }
    }
}
