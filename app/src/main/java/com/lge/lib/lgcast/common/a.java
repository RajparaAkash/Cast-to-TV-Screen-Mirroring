package com.lge.lib.lgcast.common;

import android.util.Log;

public class a {

    private static final String f3051a = "LGCAST (capture)";

    public static void a(String str, Object... objArr) {
        Log.d(f3051a, String.format(str, objArr));
    }

    public static void a(Throwable th) {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        Log.e(f3051a, "Exception: " + th.getMessage() + " (" + stackTrace[1].getFileName() + ":" + stackTrace[1].getLineNumber() + ")");
    }

    public static void b(String str, Object... objArr) {
        Log.e(f3051a, String.format(str, objArr));
    }

    public static void c(String str, Object... objArr) {
        Log.v(f3051a, String.format(str, objArr));
    }
}
