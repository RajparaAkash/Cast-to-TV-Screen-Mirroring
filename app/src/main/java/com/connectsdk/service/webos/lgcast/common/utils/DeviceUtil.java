package com.connectsdk.service.webos.lgcast.common.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;


public class DeviceUtil {
    public static int getProcessorBits() {
        if (Build.SUPPORTED_64_BIT_ABIS == null || Build.SUPPORTED_64_BIT_ABIS.length <= 0) {
            return (Build.SUPPORTED_32_BIT_ABIS == null || Build.SUPPORTED_32_BIT_ABIS.length <= 0) ? 0 : 32;
        }
        return 64;
    }

    public static long getTotalMemorySpace(Context context) {
        if (context == null) {
            return 0L;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem;
    }

    public static long getFreeMemorySpace(Context context) {
        if (context == null) {
            return 0L;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }
}
