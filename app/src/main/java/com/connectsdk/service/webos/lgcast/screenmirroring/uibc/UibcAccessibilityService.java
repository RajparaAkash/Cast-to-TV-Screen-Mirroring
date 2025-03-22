package com.connectsdk.service.webos.lgcast.screenmirroring.uibc;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Path;
import android.os.Message;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.connectsdk.service.webos.lgcast.common.utils.HandlerThreadEx;
import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.connectsdk.service.webos.lgcast.screenmirroring.service.MirroringServiceFunc;

import org.json.JSONObject;


public class UibcAccessibilityService extends AccessibilityService {
    public static final String START_SERVICE = "AccessibilityService:START_SERVICE";
    public static final String STOP_SERVICE = "AccessibilityService:STOP_SERVICE";
    private static HandlerThreadEx mRotationHandler;
    private static HandlerThreadEx mServiceHandler;
    private int mCurrentOrientation;
    private int mCurrentScreenWidth;
    private String mCurrentTVOrientation;
    private boolean mIsMouseClicked;
    private long mMouseDownTime;
    private Path mMousePointPath;
    private PointConverter mPointConverter;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }

    @Override
    public void onInterrupt() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Configuration configuration = getResources().getConfiguration();
        this.mCurrentOrientation = configuration.orientation;
        this.mCurrentScreenWidth = configuration.smallestScreenWidthDp;
        this.mCurrentTVOrientation = "landscape";
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent != null ? intent.getAction() : null;
        if (START_SERVICE.equals(action)) {
            startService();
        }
        if (STOP_SERVICE.equals(action)) {
            stopService();
        }
        return Service.START_STICKY;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mCurrentOrientation == newConfig.orientation && this.mCurrentScreenWidth == newConfig.smallestScreenWidthDp) {
            return;
        }
        this.mCurrentOrientation = newConfig.orientation;
        this.mCurrentScreenWidth = newConfig.smallestScreenWidthDp;
        if (this.mPointConverter != null) {
            if ("portrait".equals(this.mCurrentTVOrientation)) {
                this.mPointConverter.update(1080, 1920);
            } else {
                this.mPointConverter.update(1920, 1080);
            }
        }
    }

    public static void sendUibcInfo(JSONObject uibcInfo) {
        if (uibcInfo == null) {
            return;
        }
        HandlerThreadEx handlerThreadEx = mServiceHandler;
        if (handlerThreadEx != null) {
            handlerThreadEx.sendMessage(uibcInfo);
        } else {
            Logger.error("Invalid handler", new Object[0]);
        }
    }

    public static void onDisplayRotated(String displayOrientation) {
        HandlerThreadEx handlerThreadEx = mRotationHandler;
        if (handlerThreadEx != null) {
            handlerThreadEx.sendMessage(displayOrientation);
        } else {
            Logger.error("Invalid handler", new Object[0]);
        }
    }

    private void startService() {
        Logger.print("start TvInputService", new Object[0]);
        startForeground(4096, MirroringServiceFunc.createNotification(this));
        HandlerThreadEx handlerThreadEx = new HandlerThreadEx("UibcAccessibilityService Handler");
        mServiceHandler = handlerThreadEx;
        handlerThreadEx.start(new HandlerThreadEx.HandlerCallback() {
            @Override
            public void handleMessage(Message message) {
                UibcAccessibilityService.this.bcAccessibilityService1(message);
            }
        });
        HandlerThreadEx handlerThreadEx2 = new HandlerThreadEx("UibcAccessibilityService Screen Rotation Handler");
        mRotationHandler = handlerThreadEx2;
        handlerThreadEx2.start(new HandlerThreadEx.HandlerCallback() {
            @Override
            public void handleMessage(Message message) {
                UibcAccessibilityService.this.bcAccessibilityService2(message);
            }
        });
        this.mPointConverter = new PointConverter(this, 1920, 1080);
        this.mMousePointPath = new Path();
        this.mIsMouseClicked = false;
        this.mMouseDownTime = 0L;
    }

    public void bcAccessibilityService1(Message message) {
        handleUibcInfo((JSONObject) message.obj);
    }

    public void bcAccessibilityService2(Message message) {
        handleDisplayRotation((String) message.obj);
    }

    private void stopService() {
        Logger.print("stop TvInputService", new Object[0]);
        HandlerThreadEx handlerThreadEx = mServiceHandler;
        if (handlerThreadEx != null) {
            handlerThreadEx.quit();
        }
        mServiceHandler = null;
        HandlerThreadEx handlerThreadEx2 = mRotationHandler;
        if (handlerThreadEx2 != null) {
            handlerThreadEx2.quit();
        }
        mRotationHandler = null;
        stopForeground(true);
        stopSelf();
    }

    private void handleUibcInfo(JSONObject uibcInfo) {
        String optString = uibcInfo != null ? uibcInfo.optString("type", "none") : "none";
        optString.hashCode();
        char c = 65535;
        switch (optString.hashCode()) {
            case -814974079:
                if (optString.equals("keydown")) {
                    c = 0;
                    break;
                }
                break;
            case 101945658:
                if (optString.equals("keyup")) {
                    c = 1;
                    break;
                }
                break;
            case 113097563:
                if (optString.equals("wheel")) {
                    c = 2;
                    break;
                }
                break;
            case 586843847:
                if (optString.equals("mousedown")) {
                    c = 3;
                    break;
                }
                break;
            case 587111926:
                if (optString.equals("mousemove")) {
                    c = 4;
                    break;
                }
                break;
            case 1243067904:
                if (optString.equals("mouseup")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
                handleKeyEvent(optString, uibcInfo.optInt("keyCode", 0), uibcInfo.optBoolean("shiftKey", false));
                return;
            case 2:
                handleWheelEvent(optString, uibcInfo.optInt("deltaY", 0), uibcInfo.optInt("screenX", 0), uibcInfo.optInt("screenY", 0));
                return;
            case 3:
            case 4:
            case 5:
                handleMouseEvent(optString, uibcInfo.optInt("button", 0), uibcInfo.optInt("screenX", 0), uibcInfo.optInt("screenY", 0));
                return;
            default:
                return;
        }
    }

    private void handleMouseEvent(String type, int button, int screenX, int screenY) {
        if (button == 0) {
            if (type.equals("mousedown")) {
                onRemoteMouseDown(screenX, screenY);
            } else if (type.equals("mousemove")) {
                onRemoteMouseMove(screenX, screenY);
            } else if (type.equals("mouseup")) {
                onRemoteMouseUp(screenX, screenY);
            }
        }
    }

    private void handleKeyEvent(String type, int keyCode, boolean shiftKey) {
        if (keyCode == 461 && type.equals("keyup")) {
            onRemoteBackButton();
        }
    }

    private void handleWheelEvent(String type, int deltaY, int screenX, int screenY) {
        Logger.debug("Dispatch result = " + scrollView(getRootInActiveWindow(), deltaY), new Object[0]);
    }

    private void onRemoteMouseDown(float x, float y) {
        PointConverter.POINT convert = this.mPointConverter.convert(x, y);
        if (convert.screenX == -1.0f || convert.screenY == -1.0f) {
            return;
        }
        this.mMousePointPath.reset();
        this.mMousePointPath.moveTo(convert.screenX, convert.screenY);
        this.mMouseDownTime = System.currentTimeMillis();
        this.mIsMouseClicked = true;
    }

    private void onRemoteMouseMove(float x, float y) {
        if (this.mIsMouseClicked) {
            PointConverter.POINT convert = this.mPointConverter.convert(x, y);
            if (convert.screenX == -1.0f || convert.screenY == -1.0f) {
                return;
            }
            this.mMousePointPath.lineTo(convert.screenX, convert.screenY);
        }
    }

    private void onRemoteMouseUp(float x, float y) {
        if (!this.mIsMouseClicked) {
            Logger.error("Not mouse clicked", new Object[0]);
        } else if (this.mMousePointPath.isEmpty()) {
            Logger.error("Empty mouse path", new Object[0]);
        } else {
            long currentTimeMillis = System.currentTimeMillis() - this.mMouseDownTime;
            if (currentTimeMillis < ViewConfiguration.getLongPressTimeout()) {
                currentTimeMillis /= 2;
            }
            long min = Math.min(currentTimeMillis, ViewConfiguration.getLongPressTimeout());
            PointConverter.POINT convert = this.mPointConverter.convert(x, y);
            if (convert.screenX != -1.0f && convert.screenY != -1.0f) {
                this.mMousePointPath.lineTo(convert.screenX, convert.screenY);
            }
            Logger.debug("Dispatch result = " + dispatchSwipeEvent(this.mMousePointPath, min), new Object[0]);
            this.mMousePointPath.reset();
            this.mIsMouseClicked = false;
        }
    }

    private void onRemoteBackButton() {
        Logger.debug("Dispatch result = " + performGlobalAction(1), new Object[0]);
    }

    private boolean dispatchSwipeEvent(Path mPath, long duration) {
        if (duration <= 0) return false;
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(mPath, 10L, duration));
        GestureDescription gestureDescription = builder.build();

        return dispatchGesture(gestureDescription, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                Logger.error("Dispatch cancelled!!");
                super.onCancelled(gestureDescription);
            }
        }, null);
    }

    private boolean scrollView(AccessibilityNodeInfo nodeInfo, int deltaY) {
        if (nodeInfo != null && deltaY != 0) {
            if (nodeInfo.isScrollable()) {
                return nodeInfo.performAction(deltaY > 0 ? 4096 : 8192);
            }
            for (int i = 0; i < nodeInfo.getChildCount(); i++) {
                if (scrollView(nodeInfo.getChild(i), deltaY)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void handleDisplayRotation(String displayOrientation) {
        this.mCurrentTVOrientation = displayOrientation;
        Logger.debug("handleDisplayRotation (displayOrientation=%s)", displayOrientation);
        if (this.mPointConverter != null) {
            if ("portrait".equals(this.mCurrentTVOrientation)) {
                this.mPointConverter.update(1080, 1920);
            } else {
                this.mPointConverter.update(1920, 1080);
            }
        }
    }
}
