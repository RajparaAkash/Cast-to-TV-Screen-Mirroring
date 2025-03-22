package com.github.kunal52.pairing;

import com.github.kunal52.wire.PacketParser;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.InputStream;
import java.util.concurrent.BlockingQueue;

public class PairingPacketParser extends PacketParser {
    private BlockingQueue<Pairingmessage.PairingMessage> mMessagesQueue;

    public PairingPacketParser(InputStream inputStream, BlockingQueue<Pairingmessage.PairingMessage> blockingQueue) {
        super(inputStream);
        this.mMessagesQueue = blockingQueue;
    }

    @Override
    public void messageBufferReceived(byte[] bArr) {
        try {
            Pairingmessage.PairingMessage parseFrom = Pairingmessage.PairingMessage.parseFrom(bArr);
            if (parseFrom.getStatus() == Pairingmessage.PairingMessage.Status.STATUS_OK) {
                this.mMessagesQueue.put(parseFrom);
            }
        } catch (InvalidProtocolBufferException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
