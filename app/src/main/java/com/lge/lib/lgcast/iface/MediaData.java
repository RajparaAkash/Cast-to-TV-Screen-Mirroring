package com.lge.lib.lgcast.iface;

public class MediaData {
    public byte[] data;
    public long pts;

    public MediaData(long j, byte[] bArr) {
        this.pts = j;
        this.data = bArr;
    }
}
