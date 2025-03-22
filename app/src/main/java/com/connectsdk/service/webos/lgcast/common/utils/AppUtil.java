package com.connectsdk.service.webos.lgcast.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Map;

public class AppUtil {

    public static boolean openActivity(final Activity activity, final Intent intent, final int flag) {
        if (activity != null && intent != null) {
            if (flag > 0) {
                intent.addFlags(flag);
            }
            activity.startActivity(intent);
            activity.finish();
        }
        return activity != null;
    }

    public static void closeActivity(final Activity activity, final int resultCode, final Intent data) {
        if (activity != null) {
            activity.setResult(resultCode, data);
            activity.finish();
        }
    }

    public static void closeActivity(final Activity activity, final int resultCode, final Intent data, final long delayMillis) {
        ThreadUtil.runOnMainLooper(new Runnable() {
            @Override
            public void run() {
                AppUtil.closeActivity(activity, resultCode, data);
            }
        }, delayMillis);
    }

    public static String getPackageName(final Context context) {
        if (context != null) {
            return context.getPackageName();
        }
        return null;
    }

    public static void showToast(final Context context, final int duration, final String format, final Object... args) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context, StringUtil.format(format, args), duration).show();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    AppUtil.showToast(context, format, args, duration);
                }
            });
        }
    }

    public static void showToast(Context context, String str, Object[] objArr, int i) {
        Toast.makeText(context, StringUtil.format(str, objArr), i).show();
    }

    public static void showToast(final Context context, final String format, final Object... args) {
        showToast(context, 0, format, args);
    }

    public static void showToastLong(final Context context, final String format, final Object... args) {
        showToast(context, 1, format, args);
    }

    public static boolean isAvailable(final Context context, final String packageName) {
        try {
            if (context == null || packageName == null) {
                throw new NullPointerException("context is null");
            }
            int applicationEnabledSetting = context.getPackageManager().getApplicationEnabledSetting(packageName);
            return applicationEnabledSetting == 0 || applicationEnabledSetting == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getVersionName(final Context context, final String packageName) {
        try {
            if (context == null || packageName == null) {
                throw new NullPointerException("context is null");
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo != null ? packageInfo.versionName : "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    public static int getVersionCode(final Context context, final String packageName) {
        try {
            if (context == null || packageName == null) {
                throw new NullPointerException("context is null");
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static byte[] getAppSignature(final PackageManager packageManager, final String packageName, final String algorithm) {
        try {
            if (packageManager == null || packageName == null)
                throw new Exception("Invalid arguments");

            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            Signature[] signatures = (packageInfo != null) ? packageInfo.signatures : new Signature[0];

            for (Signature signature : signatures) {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance((algorithm == null) ? "SHA-1" : algorithm);
                md.update(signature.toByteArray());
                return md.digest();
            }

            throw new Exception("No signature found");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isEnabled(final Context context, final String packageName, final String className) {
        try {
            if (context == null || packageName == null || className == null) {
                throw new Exception("Invalid arguments");
            }
            int componentEnabledSetting = context.getPackageManager().getComponentEnabledSetting(new ComponentName(packageName, className));
            return componentEnabledSetting == 0 || componentEnabledSetting == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean setEnabled(final Context context, final String packageName, final String className, final boolean enabled) {
        try {
            if (context == null || packageName == null || className == null)
                throw new Exception("Invalid arguments");

            ComponentName component = new ComponentName(packageName, className);
            context.getPackageManager().setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getUserId() {
        try {
            Integer num = (Integer) UserHandle.class.getMethod("myUserId", new Class[0]).invoke(UserHandle.class, new Object[0]);
            if (num != null) {
                return num.intValue();
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getUID(final Context context, String packageName) {
        try {
            PackageManager packageManager = (context != null) ? context.getPackageManager() : null;
            ApplicationInfo aInfo = (packageManager != null && packageName != null) ? packageManager.getApplicationInfo(packageName, 0) : null;
            return (aInfo != null) ? aInfo.uid : -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getBinderCallerId(final Context context) {
        return Binder.getCallingPid();
    }

    public static String getBinderCallerName(final Context context) {
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid == null || packagesForUid.length <= 0) {
            return null;
        }
        return packagesForUid[0];
    }

    public static String getTopActivity(final Context context) {
        return ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1).get(0).topActivity.getClassName();
    }

    public static boolean isServiceRunning(final Context context, final Class cls) {
        if (context != null && cls != null) {
            for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE)) {
                if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void sendPendingIntentToActivity(final Context context, final Intent intent) {
        try {
            PendingIntent pi = PendingIntent.getActivity(
                    context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );
            pi.send();
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    public static void moveToDetailSetting(final Context context) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }

    public static void moveToAccessibilitySetting(final Context context) {
        context.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
    }

    public static ActivityManager.RunningServiceInfo getServiceInfo(Context context, String className) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(100)) {
            if (runningServiceInfo.service.getClassName().equals(className)) {
                return runningServiceInfo;
            }
        }
        return null;
    }

    public static boolean isForegroundService(Context context, String className) {
        ActivityManager.RunningServiceInfo serviceInfo = getServiceInfo(context, className);
        if (serviceInfo != null) {
            return serviceInfo.foreground;
        }
        return false;
    }

    public static Activity getCurrentActivity() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            Map map = (Map) declaredField.get(invoke);
            if (map == null) {
                return null;
            }
            for (Object obj : map.values()) {
                Class<?> cls2 = obj.getClass();
                Field declaredField2 = cls2.getDeclaredField("paused");
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(obj)) {
                    Field declaredField3 = cls2.getDeclaredField("activity");
                    declaredField3.setAccessible(true);
                    return (Activity) declaredField3.get(obj);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics;
    }

    public static Point getDisplaySize(Context context) {
        if (context == null) {
            throw new IllegalArgumentException();
        }
        Point point = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealSize(point);
        return point;
    }

    public static Point getDisplaySizeInLandscape(Context context) {
        Point displaySize = getDisplaySize(context);
        return isLandscape(context) ? new Point(displaySize.x, displaySize.y) : new Point(displaySize.y, displaySize.x);
    }

    public static int getRotationDegree(Context context) {
        if (context == null) {
            throw new IllegalArgumentException();
        }
        int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        if (rotation == 0) {
            return 0;
        }
        if (1 == rotation) {
            return 90;
        }
        if (2 == rotation) {
            return 180;
        }
        return 3 == rotation ? 270 : -1;
    }

    public static int getOrientation(Context context) {
        if (context == null) {
            throw new IllegalArgumentException();
        }
        int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        if (rotation == 0 || 2 == rotation) {
            return 1;
        }
        return (1 == rotation || 3 == rotation) ? 2 : 0;
    }

    public static boolean isLandscape(Context context) {
        return getOrientation(context) == 2;
    }

    public static boolean isPortrait(Context context) {
        return getOrientation(context) == 1;
    }

    public static int getActivityOrientation(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException();
        }
        return activity.getResources().getConfiguration().orientation;
    }

    public static Rect getWindowVisibleDisplayFrame(Activity activity) {
        if (activity != null) {
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            return rect;
        }
        return null;
    }
}
