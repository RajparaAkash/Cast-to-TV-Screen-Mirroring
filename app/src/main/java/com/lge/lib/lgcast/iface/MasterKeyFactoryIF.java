package com.lge.lib.lgcast.iface;

import com.lge.lib.lgcast.func.c;

import java.util.ArrayList;

public class MasterKeyFactoryIF {

    private c f3064a = new c();

    public ArrayList<MasterKey> createFixedKeys(byte[] bArr) {
        return this.f3064a.a(bArr);
    }

    public ArrayList<MasterKey> createKeys(String str) {
        return this.f3064a.a(str);
    }
}
