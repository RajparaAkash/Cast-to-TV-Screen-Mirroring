package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.model.ClientScanResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.ArrayList;

public class WifiApManager {
    private Context context;
    private final WifiManager mWifiManager;

    public WifiApManager(Context context2) {
        this.context = context2;
        this.mWifiManager = (WifiManager) context2.getSystemService(Context.WIFI_SERVICE);
    }

    public void showWritePermissionSettings(boolean z) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        if (z || !Settings.System.canWrite(this.context)) {
            Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
            intent.setData(Uri.parse("package:" + this.context.getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(intent);
        }
    }

    public boolean setWifiApEnabled(WifiConfiguration wifiConfiguration, boolean z) {
        if (z) {
            try {
                this.mWifiManager.setWifiEnabled(false);
            } catch (Exception e) {
                Log.e(getClass().toString(), "", e);
                return false;
            }
        }
        try {
            return ((Boolean) this.mWifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE).invoke(this.mWifiManager, wifiConfiguration, Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Constants.WIFI_AP_STATE getWifiApState() {
        try {
            int intValue = ((Integer) this.mWifiManager.getClass().getMethod("getWifiApState", new Class[0]).invoke(this.mWifiManager, new Object[0])).intValue();
            if (intValue >= 10) {
                intValue -= 10;
            }
            return ((Constants.WIFI_AP_STATE[]) Constants.WIFI_AP_STATE.class.getEnumConstants())[intValue];
        } catch (Exception e) {
            Log.e(getClass().toString(), "", e);
            return Constants.WIFI_AP_STATE.WIFI_AP_STATE_FAILED;
        }
    }

    public boolean isWifiApEnabled() {
        return getWifiApState() == Constants.WIFI_AP_STATE.WIFI_AP_STATE_ENABLED;
    }

    public WifiConfiguration getWifiApConfiguration() {
        try {
            return (WifiConfiguration) this.mWifiManager.getClass().getMethod("getWifiApConfiguration", new Class[0]).invoke(this.mWifiManager, new Object[0]);
        } catch (Exception e) {
            Log.e(getClass().toString(), "", e);
            return null;
        }
    }

    public boolean setWifiApConfiguration(WifiConfiguration wifiConfiguration) {
        try {
            return ((Boolean) this.mWifiManager.getClass().getMethod("setWifiApConfiguration", WifiConfiguration.class).invoke(this.mWifiManager, wifiConfiguration)).booleanValue();
        } catch (Exception e) {
            Log.e(getClass().toString(), "", e);
            return false;
        }
    }

    public ArrayList<ClientScanResult> getClientList(boolean z) {
        return getClientList(z, 300);
    }

    /* CODE */
    public ArrayList<ClientScanResult> getClientList(boolean z, int i) {
        Exception e;
        ArrayList<ClientScanResult> arrayList = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        String[] split = readLine.split(" +");
                        if (split != null && split.length >= 4 && split[3].matches("..:..:..:..:..:..")) {
                            boolean isReachable = InetAddress.getByName(split[0]).isReachable(i);
                            if (!z || isReachable) {
                                arrayList.add(new ClientScanResult(split[0], split[3], split[5], isReachable));
                            }
                        }
                    } else {
                        break;
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        Log.e(getClass().toString(), e.toString());
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                Log.e(getClass().toString(), e4.getMessage());
                            }
                        }
                        throw th2;
                    }
                } catch (Throwable th3) {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th3;
                }
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (Exception e5) {
            e = e5;
            Log.e(getClass().toString(), e.toString());
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return arrayList;
        }
        return arrayList;
    }
}
