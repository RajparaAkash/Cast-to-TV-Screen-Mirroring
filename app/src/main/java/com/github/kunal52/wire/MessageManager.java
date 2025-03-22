package com.github.kunal52.wire;

import android.util.Log;

import java.nio.ByteBuffer;

public abstract class MessageManager {
    public ByteBuffer mPacketBuffer = ByteBuffer.allocate(65539);

    public byte[] addLengthAndCreate1(byte[] bArr) {
        this.mPacketBuffer.put((byte) bArr.length).put(bArr);
        byte[] bArr2 = new byte[this.mPacketBuffer.position()];
        System.arraycopy(this.mPacketBuffer.array(), this.mPacketBuffer.arrayOffset(), bArr2, 0, this.mPacketBuffer.position());
        this.mPacketBuffer.clear();
        return bArr2;
    }

    public byte[] addLengthAndCreate(byte[] message) {
        Log.e("fatal", "addLengthAndCreate 111: " + message.length );
        int length = message.length;
        mPacketBuffer.put((byte) length).put(message);
        byte[] buf = new byte[mPacketBuffer.position()];
        System.arraycopy(mPacketBuffer.array(), mPacketBuffer.arrayOffset(), buf, 0, mPacketBuffer.position());
        mPacketBuffer.clear();
        Log.e("fatal", "addLengthAndCreate 222: " + buf.length );
        return buf;
    }

}
