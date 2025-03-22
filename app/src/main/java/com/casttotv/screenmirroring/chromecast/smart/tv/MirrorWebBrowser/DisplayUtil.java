package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

public class DisplayUtil {

    public static Point getDisplayResolution(Context context) {
        Point point = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealSize(point);
        return point;
    }
}
