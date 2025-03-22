package com.connectsdk.service.webos.lgcast.screenmirroring.api;

import android.app.Presentation;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.view.Display;

import com.connectsdk.service.capability.ScreenMirroringControl;
import com.connectsdk.service.webos.lgcast.common.utils.DeviceUtil;
import com.connectsdk.service.webos.lgcast.common.utils.LocalBroadcastEx;
import com.connectsdk.service.webos.lgcast.common.utils.Logger;
import com.connectsdk.service.webos.lgcast.screenmirroring.service.MirroringServiceError;
import com.connectsdk.service.webos.lgcast.screenmirroring.service.MirroringServiceIF;

public class ScreenMirroringApi {

    private DisplayManager.DisplayListener mDisplayListener;
    private LocalBroadcastEx mLocalBroadcastEx;
    private Presentation mSecondScreen;

    private ScreenMirroringApi() {
        this.mLocalBroadcastEx = new LocalBroadcastEx();
    }


    public static class LazyHolder {
        private static final ScreenMirroringApi INSTANCE = new ScreenMirroringApi();

        private LazyHolder() {
        }
    }

    public static ScreenMirroringApi getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void startMirroring(Context context, Intent projectionData, String deviceIpAddress, Class secondScreenClass, final ScreenMirroringControl.ScreenMirroringStartListener startListener) {
        Logger.print("startMirroring", new Object[0]);
        try {
            if (context == null || projectionData == null || deviceIpAddress == null) {
                throw new Exception("Invalid arguments");
            }
            if (!ScreenMirroringControl.isCompatibleOsVersion()) {
                throw new Exception("Incompatible OS version");
            }
            boolean z = true;
            if (ScreenMirroringControl.isRunning(context)) {
                throw new Exception("Cast to TV is ALREADY running");
            }
            if (DeviceUtil.getProcessorBits() != 64) {
                throw new Exception("Invalid Application Binary Interface");
            }
            this.mLocalBroadcastEx.registerOnce(context, MirroringServiceIF.ACTION_NOTIFY_PAIRING, new LocalBroadcastEx.BroadcastListener() {
                @Override
                public   void onReceive(Intent intent) {
                    if (startListener != null) {
                        startListener.onPairing();
                    }
                }
            });
            this.mLocalBroadcastEx.registerOnce(context, MirroringServiceIF.ACTION_START_RESPONSE, new LocalBroadcastEx.BroadcastListener() {
                @Override
                public   void onReceive(Intent intent) {
                    boolean booleanExtra = intent.getBooleanExtra(MirroringServiceIF.EXTRA_RESULT, false);
                    if (startListener != null) {
                        startListener.onStart(booleanExtra, mSecondScreen);
                    }
                }
            });
            if (secondScreenClass != null) {
                this.mDisplayListener = createDisplayListener(context, secondScreenClass);
                getDisplayManager(context).registerDisplayListener(this.mDisplayListener, null);
            }
            Logger.debug("Request start");
            if (secondScreenClass == null) {
                z = false;
            }
            MirroringServiceIF.requestStart(context, projectionData, deviceIpAddress, z);
        } catch (Exception e) {
            Logger.error(e);
            if (startListener != null) {
                startListener.onStart(false, null);
            }
        }
    }

    public void stopMirroring(final Context context, final ScreenMirroringControl.ScreenMirroringStopListener stopListener) {
        Logger.print("stopMirroring");
        try {
            if (context == null) {
                throw new Exception("Invalid arguments");
            }
            if (!ScreenMirroringControl.isRunning(context)) {
                throw new Exception("Cast to TV is NOT running");
            }
            this.mLocalBroadcastEx.registerOnce(context, MirroringServiceIF.ACTION_STOP_RESPONSE, new LocalBroadcastEx.BroadcastListener() {
                @Override
                public   void onReceive(Intent intent) {
                    ScreenMirroringApi.this.stopMirroringApi(stopListener, context, intent);
                }
            });
            if (this.mDisplayListener != null) {
                getDisplayManager(context).unregisterDisplayListener(this.mDisplayListener);
            }
            this.mDisplayListener = null;
            if (mSecondScreen != null && mSecondScreen.isShowing()) {
                mSecondScreen.dismiss();
            }
            this.mSecondScreen = null;
            Logger.debug("Request stop");
            MirroringServiceIF.requestStop(context);
        } catch (Exception e) {
            Logger.error(e);
            if (stopListener != null) {
                stopListener.onStop(false);
            }
        }
    }

    public void stopMirroringApi(ScreenMirroringControl.ScreenMirroringStopListener screenMirroringStopListener, Context context, Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra(MirroringServiceIF.EXTRA_RESULT, false);
        if (screenMirroringStopListener != null) {
            screenMirroringStopListener.onStop(booleanExtra);
        }
        this.mLocalBroadcastEx.unregisterAll(context);
    }

    public void setErrorListener(Context context, final ScreenMirroringControl.ScreenMirroringErrorListener errorListener) {
        this.mLocalBroadcastEx.registerOnce(context, MirroringServiceIF.ACTION_NOTIFY_ERROR, new LocalBroadcastEx.BroadcastListener() {
            @Override
            public   void onReceive(Intent intent) {
                ScreenMirroringApi.this.setErrorListenerApi(errorListener, intent);
            }
        });
    }

    public void setErrorListenerApi(ScreenMirroringControl.ScreenMirroringErrorListener screenMirroringErrorListener, Intent intent) {
        MirroringServiceError mirroringServiceError = (MirroringServiceError) intent.getSerializableExtra(MirroringServiceIF.EXTRA_ERROR);
        if (screenMirroringErrorListener != null) {
            screenMirroringErrorListener.onError(toScreenMirroringError(mirroringServiceError));
        }
    }

    private DisplayManager getDisplayManager(Context context) {
        return (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
    }

    private DisplayManager.DisplayListener createDisplayListener(final Context context, final Class secondScreenClass) {
        return new DisplayManager.DisplayListener() {
            @Override
            public void onDisplayAdded(int id) {
                Logger.debug("onDisplayAdded (id=%d)", id);
                Display display = ScreenMirroringApi.this.getDisplayManager(context).getDisplay(id);
                if (display == null || !display.getName().equals("LGCastVirtualDisplay")) {
                    Logger.error("Unknown display");
                    return;
                }
                Class cls = secondScreenClass;
                if (cls != null) {
                    ScreenMirroringApi screenMirroringApi = ScreenMirroringApi.this;
                    screenMirroringApi.mSecondScreen = screenMirroringApi.createSecondScreenInstance(context, cls, screenMirroringApi.getDisplayManager(context).getDisplay(id));
                    if (ScreenMirroringApi.this.mSecondScreen != null) {
                        ScreenMirroringApi.this.mSecondScreen.show();
                    }
                }
                if (ScreenMirroringApi.this.mDisplayListener != null) {
                    ScreenMirroringApi.this.getDisplayManager(context).unregisterDisplayListener(ScreenMirroringApi.this.mDisplayListener);
                }
                ScreenMirroringApi.this.mDisplayListener = null;
            }

            @Override
            public void onDisplayRemoved(int id) {
                Logger.debug("onDisplayRemoved (id=%d)", id);
            }

            @Override
            public void onDisplayChanged(int id) {
                Logger.debug("onDisplayChanged (id=%d)", id);
            }
        };
    }

    public Presentation createSecondScreenInstance(Context context, Class secondScreenClass, Display display) {
        try {
            if (secondScreenClass != null) {
                if (display == null) {
                    throw new Exception("Invalid display");
                }
                return (Presentation) secondScreenClass.getConstructor(Context.class, Display.class).newInstance(context, display);
            }
            throw new Exception("Invalid class");
        } catch (Exception e) {
            Logger.error(e);
            return null;
        }
    }

    private ScreenMirroringControl.ScreenMirroringError toScreenMirroringError(MirroringServiceError serviceError) {
        return serviceError == MirroringServiceError.ERROR_CONNECTION_CLOSED ? ScreenMirroringControl.ScreenMirroringError.ERROR_CONNECTION_CLOSED : serviceError == MirroringServiceError.ERROR_DEVICE_SHUTDOWN ? ScreenMirroringControl.ScreenMirroringError.ERROR_DEVICE_SHUTDOWN : serviceError == MirroringServiceError.ERROR_RENDERER_TERMINATED ? ScreenMirroringControl.ScreenMirroringError.ERROR_RENDERER_TERMINATED : serviceError == MirroringServiceError.ERROR_STOPPED_BY_NOTIFICATION ? ScreenMirroringControl.ScreenMirroringError.ERROR_STOPPED_BY_NOTIFICATION : ScreenMirroringControl.ScreenMirroringError.ERROR_GENERIC;
    }
}
