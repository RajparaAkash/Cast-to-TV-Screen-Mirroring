package com.lge.lib.lgcast.func;

public class f {

    public int f3059a;

    public f(int i, byte[] bArr, int i2) {
        this.f3059a = 0;
        if (bArr == null) {
            throw new IllegalArgumentException();
        }
        int i3 = (i > 0 ? i2 + 1 : i2) + 1 + 2 + 2 + 1 + 1 + 1 + 2 + 2 + 1;
        this.f3059a = ((bArr[i3] & 31) << 8) | ((bArr[i3 + 1] & 255) << 0);
    }

    public void a() {
    }
}
