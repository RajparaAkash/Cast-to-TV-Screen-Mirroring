package com.casttotv.screenmirroring.chromecast.smart.tv.Util.Volume;

import android.content.Context;

import com.jaku.api.DeviceRequests;
import com.jaku.api.QueryRequests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class AvailableDevicesTask implements Callable {

    private final Context context;
    private final boolean filterPairedDevices;

    public AvailableDevicesTask(Context context2, boolean z) {
        this.context = context2;
        this.filterPairedDevices = z;
    }

    @Override 
    public List<Device> call() {
        List<Device> arrayList = new ArrayList<>();
        if (this.filterPairedDevices) {
            arrayList = DBUtils.getAllDevices(this.context);
        }
        ArrayList<Device> arrayList2 = new ArrayList<>();
        try {
            ArrayList<Device> arrayList3 = new ArrayList<>();
            if (new WifiApManager(this.context).isWifiApEnabled()) {
                arrayList3.addAll(scanAccessPointForDevices());
            } else {
                for (com.jaku.model.Device device : DeviceRequests.discoverDevices()) {
                    arrayList3.add(Device.Companion.fromDevice(device));
                }
            }
            Iterator<Device> it = arrayList3.iterator();
            while (it.hasNext()) {
                Device device2 = it.next();
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= arrayList.size()) {
                        break;
                    } else if (arrayList.get(i).getSerialNumber().equals(device2.getSerialNumber())) {
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
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return arrayList2;
    }

    private ArrayList<Device> scanAccessPointForDevices() throws Throwable {
        ArrayList<ClientScanResult> clientList;
        ArrayList<Device> arrayList = new ArrayList<>();
        WifiApManager wifiApManager = new WifiApManager(this.context);
        if (wifiApManager.isWifiApEnabled() && (clientList = wifiApManager.getClientList(false, 3000)) != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Found ");
            sb.append(clientList.size());
            sb.append(" connected devices.");
            Iterator<ClientScanResult> it = clientList.iterator();
            while (it.hasNext()) {
                ClientScanResult next = it.next();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Device: ");
                sb2.append(next.getDevice());
                sb2.append(" HW Address: ");
                sb2.append(next.getHWAddr());
                sb2.append(" IP Address:  ");
                sb2.append(next.getIpAddr());
                try {
                    Device fromDevice = Device.Companion.fromDevice(QueryRequests.queryDeviceInfo("http://" + next.getIpAddr() + ":8060"));
                    fromDevice.setHost("http://" + next.getIpAddr() + ":8060");
                    arrayList.add(fromDevice);
                } catch (IOException e) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Invalid device: ");
                    sb3.append(e.getMessage());
                }
            }
        }
        return arrayList;
    }
}
