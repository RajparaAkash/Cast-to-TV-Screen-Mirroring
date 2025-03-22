package com.casttotv.screenmirroring.chromecast.smart.tv.Model;

import androidx.annotation.Keep;

import kotlin.jvm.internal.Intrinsics;

@Keep
public class Remote {
    private final String name;
    private final String tvType;

    public Remote(String str, String str2) {
        this.name = str;
        this.tvType = str2;
    }

    public Remote copy(String str, String str2) {
        return new Remote(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Remote)) {
            return false;
        }
        Remote remote = (Remote) obj;
        return Intrinsics.areEqual(this.name, remote.name) && Intrinsics.areEqual(this.tvType, remote.tvType);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.tvType.hashCode();
    }

    public String toString() {
        return "Remote(name=" + this.name + ", tvType=" + this.tvType + ')';
    }

    public String getName() {
        return this.name;
    }

    public String getTvType() {
        return this.tvType;
    }
}
