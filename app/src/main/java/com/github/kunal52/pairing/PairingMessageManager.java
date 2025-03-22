package com.github.kunal52.pairing;

import android.util.Log;

import com.github.kunal52.wire.MessageManager;
import com.google.protobuf.ByteString;

public class PairingMessageManager extends MessageManager {
    public byte[] createPairingMessage(String str, String str2) {
        Log.e("fatal", "addLengthAndCreate: " + "aaaaaaaaaaaaaaaaaaaaaaaaa" );
        return addLengthAndCreate(Pairingmessage.PairingMessage.newBuilder().setPairingRequest(Pairingmessage.PairingRequest.newBuilder().setClientName(str).setServiceName(str2)).setStatus(Pairingmessage.PairingMessage.Status.STATUS_OK).setProtocolVersion(2).build().toByteArray());
    }

    public byte[] createPairingOption() {
        Log.e("fatal", "addLengthAndCreate: " + "bbbbbbbbbbbbbbbbbbbbbbbbb" );
        return addLengthAndCreate(Pairingmessage.PairingMessage.newBuilder().setPairingOption(Pairingmessage.PairingOption.newBuilder().setPreferredRole(Pairingmessage.RoleType.ROLE_TYPE_INPUT).addInputEncodings(Pairingmessage.PairingEncoding.newBuilder().setType(Pairingmessage.PairingEncoding.EncodingType.ENCODING_TYPE_HEXADECIMAL).setSymbolLength(6).build())).setStatus(Pairingmessage.PairingMessage.Status.STATUS_OK).setProtocolVersion(2).build().toByteArray());
    }

    public byte[] createConfigMessage() {
        Log.e("fatal", "addLengthAndCreate: " + "ccccccccccccccccccccccccc" );
        return addLengthAndCreate(Pairingmessage.PairingMessage.newBuilder().setPairingConfiguration(Pairingmessage.PairingConfiguration.newBuilder().setClientRole(Pairingmessage.RoleType.ROLE_TYPE_INPUT).setEncoding(Pairingmessage.PairingEncoding.newBuilder().setType(Pairingmessage.PairingEncoding.EncodingType.ENCODING_TYPE_HEXADECIMAL).setSymbolLength(6).build()).build()).setStatus(Pairingmessage.PairingMessage.Status.STATUS_OK).setProtocolVersion(2).build().toByteArray());
    }

    public byte[] createSecretMessage(Pairingmessage.PairingMessage pairingMessage) {
        Log.e("fatal", "addLengthAndCreate: " + "ddddddddddddddddddddddddd" );
        return addLengthAndCreate(pairingMessage.toByteArray());
    }

    public byte[] createSecretMessage(byte[] bArr) {
        return addLengthAndCreate(bArr);
    }

    public Pairingmessage.PairingMessage createSecretMessageProto(byte[] bArr) {
        return Pairingmessage.PairingMessage.newBuilder().setPairingSecret(Pairingmessage.PairingSecret.newBuilder().setSecret(ByteString.copyFrom(bArr)).build()).setStatus(Pairingmessage.PairingMessage.Status.STATUS_OK).setProtocolVersion(2).build();
    }
}
