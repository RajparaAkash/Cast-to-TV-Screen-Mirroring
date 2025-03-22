package com.connectsdk.device;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.connectsdk.core.Util;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.discovery.DiscoveryManagerListener;
import com.connectsdk.service.command.ServiceCommandError;


public class DevicePickerListView extends ListView implements DiscoveryManagerListener {
    DevicePickerAdapter pickerAdapter;

    public DevicePickerListView(Context context) {
        super(context);
        DevicePickerAdapter devicePickerAdapter = new DevicePickerAdapter(context);
        this.pickerAdapter = devicePickerAdapter;
        setAdapter((ListAdapter) devicePickerAdapter);
        DiscoveryManager.getInstance().addListener(this);
    }

    @Override 
    public void onDiscoveryFailed(DiscoveryManager manager, ServiceCommandError error) {
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                DevicePickerListView.this.pickerAdapter.clear();
            }
        });
    }

    @Override 
    public void onDeviceAdded(final DiscoveryManager manager, final ConnectableDevice device) {
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    if (i >= DevicePickerListView.this.pickerAdapter.getCount()) {
                        i = -1;
                        break;
                    }
                    ConnectableDevice item = DevicePickerListView.this.pickerAdapter.getItem(i);
                    String friendlyName = device.getFriendlyName();
                    String friendlyName2 = item.getFriendlyName();
                    if (friendlyName == null) {
                        friendlyName = device.getModelName();
                    }
                    if (friendlyName2 == null) {
                        friendlyName2 = item.getModelName();
                    }
                    if (item.getIpAddress().equals(device.getIpAddress()) && item.getFriendlyName().equals(device.getFriendlyName()) && !manager.isServiceIntegrationEnabled() && item.getServiceId().equals(device.getServiceId())) {
                        DevicePickerListView.this.pickerAdapter.remove(item);
                        DevicePickerListView.this.pickerAdapter.insert(device, i);
                        return;
                    } else if (friendlyName.compareToIgnoreCase(friendlyName2) < 0) {
                        DevicePickerListView.this.pickerAdapter.insert(device, i);
                        break;
                    } else {
                        i++;
                    }
                }
                if (i == -1) {
                    DevicePickerListView.this.pickerAdapter.add(device);
                }
            }
        });
    }

    @Override 
    public void onDeviceUpdated(DiscoveryManager manager, final ConnectableDevice device) {
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                DevicePickerListView.this.pickerAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override 
    public void onDeviceRemoved(DiscoveryManager manager, final ConnectableDevice device) {
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                DevicePickerListView.this.pickerAdapter.remove(device);
            }
        });
    }
}
