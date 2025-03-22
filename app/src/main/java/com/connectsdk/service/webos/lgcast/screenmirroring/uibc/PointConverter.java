package com.connectsdk.service.webos.lgcast.screenmirroring.uibc;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.connectsdk.service.webos.lgcast.common.utils.StringUtil;


public class PointConverter {
    private final Display mDisplay;
    private Property mScreenProperty;
    private int mVideoMaxHeight;
    private int mVideoMaxWidth;


    public static class Property {
        public boolean isWiderThanTV;
        public float mobileScreenHeight;
        public float mobileScreenWidth;
        public float tvVideoHeight;
        public float tvVideoWidth;

        public void debug() {
            Logger.debug("mobileScreenWidth=" + this.mobileScreenWidth, new Object[0]);
            Logger.debug("mobileScreenHeight=" + this.mobileScreenHeight, new Object[0]);
            Logger.debug("tvVideoWidth=" + this.tvVideoWidth, new Object[0]);
            Logger.debug("tvVideoHeight=" + this.tvVideoHeight, new Object[0]);
            Logger.debug(StringUtil.LF, new Object[0]);
        }
    }

    public static class POINT {
        public float videoX;
        public float videoY;
        public float screenX;
        public float screenY;

        public void debug() {
            Logger.debug("videoX=" + videoX);
            Logger.debug("videoY=" + videoY);
            Logger.debug("screenX=" + screenX);
            Logger.debug("screenY=" + screenY);
            Logger.debug("\n");
        }
    }

    public PointConverter(Context context, int videoMaxWidth, int videoMaxHeight) {
        this.mDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        update(videoMaxWidth, videoMaxHeight);
    }

    public void update(int videoMaxWidth, int videoMaxHeight) {
        this.mVideoMaxWidth = videoMaxWidth;
        this.mVideoMaxHeight = videoMaxHeight;
        updateProperty();
    }

    public POINT convert(float tvX, float tvY) {
        return this.mScreenProperty.isWiderThanTV ? convertToLandscape(this.mScreenProperty, tvX, tvY) : convertToPortrait(this.mScreenProperty, tvX, tvY);
    }

    private POINT convertToPortrait(Property portraitProperty, float tvX, float tvY) {
        POINT point = new POINT();
        point.videoX = tvX - ((this.mVideoMaxWidth - portraitProperty.tvVideoWidth) / 2.0f);
        point.videoY = tvY;
        if (point.videoX < 0.0f || point.videoX > portraitProperty.tvVideoWidth) {
            point.videoX = -1.0f;
        }
        if (point.videoY < 0.0f || point.videoY > portraitProperty.tvVideoHeight) {
            point.videoY = -1.0f;
        }
        point.screenX = point.videoX != -1.0f ? (point.videoX * portraitProperty.mobileScreenWidth) / portraitProperty.tvVideoWidth : -1.0f;
        point.screenY = point.videoY != -1.0f ? (point.videoY * portraitProperty.mobileScreenHeight) / portraitProperty.tvVideoHeight : -1.0f;
        return point;
    }

    private POINT convertToLandscape(Property landscapeProperty, float tvX, float tvY) {
        POINT point = new POINT();
        point.videoX = tvX;
        point.videoY = tvY - ((this.mVideoMaxHeight - landscapeProperty.tvVideoHeight) / 2.0f);
        if (point.videoX < 0.0f || point.videoX > landscapeProperty.tvVideoWidth) {
            point.videoX = -1.0f;
        }
        if (point.videoY < 0.0f || point.videoY > landscapeProperty.tvVideoHeight) {
            point.videoY = -1.0f;
        }
        point.screenX = point.videoX != -1.0f ? (point.videoX * landscapeProperty.mobileScreenWidth) / landscapeProperty.tvVideoWidth : -1.0f;
        point.screenY = point.videoY != -1.0f ? (point.videoY * landscapeProperty.mobileScreenHeight) / landscapeProperty.tvVideoHeight : -1.0f;
        return point;
    }

    private void updateProperty() {
        mScreenProperty = new Property();

        Point displaySize = new Point();
        mDisplay.getRealSize(displaySize);
        mScreenProperty.mobileScreenWidth = (float) displaySize.x;
        mScreenProperty.mobileScreenHeight = (float) displaySize.y;

        float screenRatio = mScreenProperty.mobileScreenWidth / mScreenProperty.mobileScreenHeight;
        float tvRatio = (float) mVideoMaxWidth / mVideoMaxHeight;
        mScreenProperty.isWiderThanTV = screenRatio > tvRatio;

        float scale;
        if (mScreenProperty.isWiderThanTV) scale = mVideoMaxWidth / mScreenProperty.mobileScreenWidth;
        else scale = mVideoMaxHeight / mScreenProperty.mobileScreenHeight;

        mScreenProperty.tvVideoWidth = Math.round(scale * mScreenProperty.mobileScreenWidth);
        mScreenProperty.tvVideoHeight = Math.round(scale * mScreenProperty.mobileScreenHeight);
    }
}
