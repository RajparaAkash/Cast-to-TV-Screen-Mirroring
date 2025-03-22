package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

public interface Settings {
    String getFormat();

    String getHttpPassword();

    String getHttpUsername();

    int getJpegQuality();

    String getQuality();

    double getScalingFactor();

    int getWebServerPort();

    boolean hasColor();

    boolean isHttpAuthEnabled();
}
