package com.github.kunal52.remote;

import android.util.Log;

import com.github.kunal52.wire.PacketParser;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;

public class RemotePacketParser extends PacketParser {
    private final OutputStream mOutputStream;
    private final RemoteListener mRemoteListener;
    private final RemoteMessageManager remoteMessageManager;
    BlockingQueue<Remotemessage.RemoteMessage> mMessageQueue;
    private boolean isConnected = false;

    public RemotePacketParser(InputStream inputStream, OutputStream outputStream, BlockingQueue<Remotemessage.RemoteMessage> blockingQueue, RemoteListener remoteListener) {
        super(inputStream);
        this.mOutputStream = outputStream;
        this.remoteMessageManager = new RemoteMessageManager();
        this.mRemoteListener = remoteListener;
        this.mMessageQueue = blockingQueue;
    }

    //    @Override
    public void messageBufferReceived1(byte[] bArr) {
        try {
            Remotemessage.RemoteMessage parseFrom = Remotemessage.RemoteMessage.parseFrom(bArr);
            if (parseFrom.hasRemotePingRequest()) {
                try {
                    mOutputStream.write(remoteMessageManager.createPingResponse(parseFrom.getRemotePingRequest().getVal1()));
                } catch (IOException unused) {
                }
            } else if (parseFrom.hasRemoteStart()) {
                if (!this.isConnected) {
                    this.mRemoteListener.onConnected();
                }
                this.isConnected = true;
            } else {
                this.mMessageQueue.put(parseFrom);
            }
            System.out.println(parseFrom);
        } catch (InvalidProtocolBufferException unused2) {
            unused2.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void messageBufferReceived(byte[] buf) {
        Remotemessage.RemoteMessage remoteMessage;
        try {
            remoteMessage = Remotemessage.RemoteMessage.parseFrom(buf);
        } catch (InvalidProtocolBufferException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            remoteMessage = null;
        }
        //Send Ping Response
        if (remoteMessage != null) {
            if (remoteMessage.hasRemotePingRequest()) {
                try {
                    Log.e("fatal", "messageBufferReceived: " + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                    mOutputStream.write(remoteMessageManager.createPingResponse(remoteMessage.getRemotePingRequest().getVal1()));
                } catch (IOException e) {
                    //                throw new RuntimeException(e);
                    e.printStackTrace();
                }
            } else if (remoteMessage.hasRemoteStart()) {
                if (!isConnected)
                    mRemoteListener.onConnected();
                isConnected = true;
            } else {
                try {
                    mMessageQueue.put(remoteMessage);
                } catch (InterruptedException e) {
//                  throw new RuntimeException(e);
                    e.printStackTrace();
                }
            }

            System.out.println(remoteMessage);
        }

    }

    public interface RemotePacketParserListener {
        void onConnected();
    }

}
