package com.github.kunal52.remote;

import android.util.Log;

import com.github.kunal52.wire.MessageManager;

public class RemoteMessageManager extends MessageManager {
    public byte[] createRemoteConfigure(int i, String str, String str2, int i2, String str3) {
        return createRemoteConfigure(Remotemessage.RemoteConfigure.newBuilder().setCode1(i).setDeviceInfo(Remotemessage.RemoteDeviceInfo.newBuilder().setModel(str).setVendor(str2).setUnknown1(i2).setUnknown2(str3).setPackageName("androidtv-remote").setAppVersion("1.0.0").build()).build());
    }

    public byte[] createRemoteConfigure(Remotemessage.RemoteConfigure remoteConfigure) {
        Log.e("fatal", "addLengthAndCreate: " + "eeeeeeeeeeeeeeeeeeeeeeeee" );
        return addLengthAndCreate(Remotemessage.RemoteMessage.newBuilder().setRemoteConfigure(remoteConfigure).build().toByteArray());
    }

    public byte[] createRemoteActive(int i) {
        Log.e("fatal", "addLengthAndCreate: " + "fffffffffffffffffffffffff" );
        return addLengthAndCreate(Remotemessage.RemoteMessage.newBuilder().setRemoteSetActive(Remotemessage.RemoteSetActive.newBuilder().setActive(i).build()).build().toByteArray());
    }

    public byte[] createPingResponse(int i) {
        Log.e("fatal", "addLengthAndCreate: " + "ggggggggggggggggggggggggg" );
        return addLengthAndCreate(Remotemessage.RemoteMessage.newBuilder().setRemotePingResponse(Remotemessage.RemotePingResponse.newBuilder().setVal1(i).build()).build().toByteArray());
    }

    public byte[] createPower() {
        byte[] byteArray = Remotemessage.RemoteMessage.newBuilder().setRemoteKeyInject(Remotemessage.RemoteKeyInject.newBuilder().setDirection(Remotemessage.RemoteDirection.SHORT).setKeyCode(Remotemessage.RemoteKeyCode.KEYCODE_POWER).build()).build().toByteArray();
        this.mPacketBuffer.put((byte) byteArray.length).put(byteArray);
        byte[] bArr = new byte[this.mPacketBuffer.position()];
        System.arraycopy(this.mPacketBuffer.array(), this.mPacketBuffer.arrayOffset(), bArr, 0, this.mPacketBuffer.position());
        this.mPacketBuffer.clear();
        return bArr;
    }

    public byte[] createVolumeLevel(int i) {
        byte[] byteArray = Remotemessage.RemoteMessage.newBuilder().setRemoteAdjustVolumeLevel(Remotemessage.RemoteAdjustVolumeLevel.newBuilder().build()).build().toByteArray();
        this.mPacketBuffer.put((byte) byteArray.length).put(byteArray);
        byte[] bArr = new byte[this.mPacketBuffer.position()];
        System.arraycopy(this.mPacketBuffer.array(), this.mPacketBuffer.arrayOffset(), bArr, 0, this.mPacketBuffer.position());
        this.mPacketBuffer.clear();
        return bArr;
    }

    public byte[] createKeyCommand(Remotemessage.RemoteKeyCode remoteKeyCode, Remotemessage.RemoteDirection remoteDirection) {
        Log.e("fatal", "addLengthAndCreate: " + "hhhhhhhhhhhhhhhhhhhhhhhhh" );
//        Log.e("fatal", "createKeyCommand: " + addLengthAndCreate(Remotemessage.RemoteMessage.newBuilder().setRemoteKeyInject(Remotemessage.RemoteKeyInject.newBuilder().setKeyCode(remoteKeyCode).setDirection(remoteDirection).build()).build().toByteArray()) );
        return addLengthAndCreate(Remotemessage.RemoteMessage.newBuilder().setRemoteKeyInject(Remotemessage.RemoteKeyInject.newBuilder().setKeyCode(remoteKeyCode).setDirection(remoteDirection).build()).build().toByteArray());
    }

    public byte[] createAppLinkCommand(String str) {
        Log.e("fatal", "addLengthAndCreate: " + "iiiiiiiiiiiiiiiiiiiiiiiii" );
//        Log.e("fatal", "createAppLinkCommand: " + addLengthAndCreate(Remotemessage.RemoteMessage.newBuilder().setRemoteAppLinkLaunchRequest(Remotemessage.RemoteAppLinkLaunchRequest.newBuilder().setAppLink(str).build()).build().toByteArray()) );
        return addLengthAndCreate(Remotemessage.RemoteMessage.newBuilder().setRemoteAppLinkLaunchRequest(Remotemessage.RemoteAppLinkLaunchRequest.newBuilder().setAppLink(str).build()).build().toByteArray());
    }
}
