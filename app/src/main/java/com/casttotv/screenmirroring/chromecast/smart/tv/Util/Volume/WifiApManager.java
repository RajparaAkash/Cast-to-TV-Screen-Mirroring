package com.casttotv.screenmirroring.chromecast.smart.tv.Util.Volume;

import android.content.Context;
import android.net.wifi.WifiManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Objects;

public class WifiApManager {

    public Context context;
    private final WifiManager mWifiManager;

    public WifiApManager(Context context2) {
        this.context = context2;
        this.mWifiManager = (WifiManager) context2.getSystemService(Context.WIFI_SERVICE);
    }

    public ConstantsWIFI_AP_STATE getWifiApState() {
        try {
            int intValue = ((Integer) this.mWifiManager.getClass().getMethod("getWifiApState", new Class[0]).invoke(this.mWifiManager, new Object[0])).intValue();
            if (intValue >= 10) {
                intValue -= 10;
            }
            return ((ConstantsWIFI_AP_STATE[]) Objects.requireNonNull(ConstantsWIFI_AP_STATE.class.getEnumConstants()))[intValue];
        } catch (Exception exception) {
            WifiApManager.class.toString();
            return ConstantsWIFI_AP_STATE.WIFI_AP_STATE_FAILED;
        }
    }

    public boolean isWifiApEnabled() {
        return getWifiApState() == ConstantsWIFI_AP_STATE.WIFI_AP_STATE_ENABLED;
    }

    public ArrayList<ClientScanResult> getClientList(boolean z, int i) {
        ArrayList<ClientScanResult> arrayList = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/net/arp"));
            while (true) {
                try {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            String[] split = readLine.split(" +");
                            if (split != null && split.length >= 4 && split[3].matches("..:..:..:..:..:..")) {
                                boolean isReachable = InetAddress.getByName(split[0]).isReachable(i);
                                if (!z || isReachable) {
                                    arrayList.add(new ClientScanResult(split[0], split[3], split[5], isReachable));
                                }
                            }
                        } else {
                            bufferedReader2.close();
                            return arrayList;
                        }
                    } catch (Exception e) {
                        WifiApManager.class.toString();
                        e.toString();
                        return arrayList;
                    } catch (Throwable th) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e2) {
                            WifiApManager.class.toString();
                            e2.getMessage();
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    bufferedReader = bufferedReader2;
                    WifiApManager.class.toString();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    return arrayList;
                }
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return arrayList;
    }
}
