package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.tasks;

import android.content.Context;

import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.model.Device;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils.DBUtils;

import java.util.List;
import java.util.concurrent.Callable;

public class PairedDevicesTask implements Callable {
    private Context context;

    public PairedDevicesTask(Context context2) {
        this.context = context2;
    }

    @Override
    public List<Device> call() {
        return DBUtils.getAllDevices(this.context);
    }
}
