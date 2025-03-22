package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.tasks;

import android.content.Context;
import android.util.Log;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;

import com.jaku.api.DeviceRequests;
import com.jaku.api.QueryRequests;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.model.ClientScanResult;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.model.Device;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils.WifiApManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class AvailableDevicesTask implements Callable {

    private static final String TAG = "com.universal.remotecontrol.forall.smarttv.roku.tasks.AvailableDevicesTask";
    private Context context;
    private boolean filterPairedDevices;

    public AvailableDevicesTask(Context context2) {
        this(context2, true);
    }

    public AvailableDevicesTask(Context context2, boolean z) {
        this.context = context2;
        this.filterPairedDevices = z;
    }

    @Override
    public List<Device> call() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            ArrayList<Device> arrayList3 = new ArrayList();
            if (new WifiApManager(context).isWifiApEnabled()) {
                arrayList3.addAll(scanAccessPointForDevices());
            } else {
                for (com.jaku.model.Device device : DeviceRequests.discoverDevices()) {
                    arrayList3.add(Device.Companion.fromDevice(device));
                }
            }
            for (Device device2 : arrayList3) {
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= arrayList.size()) {
                        break;
                    } else if (((Device) arrayList.get(i)).getSerialNumber().equals(device2.getSerialNumber())) {
                        z = true;
                        break;
                    } else {
                        i++;
                    }
                }
                if (!z) {
                    arrayList2.add(device2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList2;
    }

    private ArrayList<Device> scanAccessPointForDevices() {
        ArrayList<Device> arrayList = new ArrayList<>();
        WifiApManager wifiApManager = new WifiApManager(context);
        if (wifiApManager.isWifiApEnabled()) {
            ArrayList<ClientScanResult> clientList = wifiApManager.getClientList(false, PathInterpolatorCompat.MAX_NUM_POINTS);
            String str = TAG;
            Log.d(str, "Access point scan completed.");
            if (clientList != null) {
                Log.d(str, "Found " + clientList.size() + " connected devices.");
                Iterator<ClientScanResult> it = clientList.iterator();
                while (it.hasNext()) {
                    ClientScanResult next = it.next();
                    Log.d(TAG, "Device: " + next.getDevice() + " HW Address: " + next.getHWAddr() + " IP Address:  " + next.getIpAddr());
                    try {
                        Device fromDevice = Device.Companion.fromDevice(QueryRequests.queryDeviceInfo("http://" + next.getIpAddr() + ":8060"));
                        fromDevice.setHost("http://" + next.getIpAddr() + ":8060");
                        arrayList.add(fromDevice);
                    } catch (IOException e) {
                        Log.e(TAG, "Invalid device: " + e.getMessage());
                    }
                }
            }
        }
        return arrayList;
    }
}
