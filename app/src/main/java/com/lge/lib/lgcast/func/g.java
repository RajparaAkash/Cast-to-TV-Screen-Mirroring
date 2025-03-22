package com.lge.lib.lgcast.func;

public class g {

    public int f3060a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public g(byte[] bArr) {
        this.f3060a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        if (bArr == null) {
            throw new IllegalArgumentException();
        }
        byte b = bArr[1];
        this.f3060a = (b & 64) >> 6;
        this.b = ((bArr[2] & 255) << 0) | ((b & 31) << 8);
        int i = (bArr[3] & 48) >> 4;
        this.c = i;
        this.e = 4;
        if (i == 2 || i == 3) {
            this.d = bArr[4] & 255;
            this.e = 5;
        }
        int i2 = this.e + this.d;
        this.e = i2;
        this.f = 188 - i2;
    }

    public void a() {
    }
}
