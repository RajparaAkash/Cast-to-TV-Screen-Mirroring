package com.lge.lib.lgcast.func;

public class e {

    public int f3058a;
    public int b;
    public int c;
    public long d;
    public int e;
    public int f;

    public e(int i, byte[] bArr, int i2) {
        this.f3058a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0L;
        this.e = 0;
        this.f = 0;
        if (bArr == null) {
            throw new IllegalArgumentException();
        }
        if (i == 1) {
            this.f3058a = ((bArr[i2 + 0] & 255) << 16) + 0 + ((bArr[i2 + 1] & 255) << 8) + ((bArr[i2 + 2] & 255) << 0);
            int i3 = i2 + 3 + 1 + 2 + 1;
            int i4 = (bArr[i3] & 128) >> 7;
            this.b = i4;
            int i5 = i3 + 1;
            int i6 = bArr[i5] & 255;
            this.c = i6;
            int i7 = i5 + 1;
            if (i4 == 1) {
                int i8 = i7 + 1;
                int i9 = i8 + 1;
                int i10 = i9 + 1;
                this.d = (((bArr[i7] & 14) >> 1) << 30) | (((bArr[i8] & 255) >> 0) << 22) | (((bArr[i9] & 254) >> 1) << 15) | (((bArr[i10] & 255) >> 0) << 7) | ((bArr[i10 + 1] & 254) >> 1);
            }
            i2 = i7 + i6;
        }
        this.e = i2;
        this.f = 188 - i2;
    }

    public void a() {
    }
}
