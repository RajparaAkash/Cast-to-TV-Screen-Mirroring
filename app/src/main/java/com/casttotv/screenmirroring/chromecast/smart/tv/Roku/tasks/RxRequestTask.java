package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.tasks;

import android.content.Context;
import android.util.Log;

import com.jaku.core.JakuRequest;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.model.Device;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils.DBUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils.PreferenceUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils.RokuRequestTypes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Callable;

public class RxRequestTask implements Callable {
    private static final String TAG = "RxRequestTask";
    private Context context;
    private JakuRequest request;
    private RokuRequestTypes rokuRequestType;

    public RxRequestTask(Context context2, JakuRequest jakuRequest, RokuRequestTypes rokuRequestTypes) {
        this.context = context2;
        this.request = jakuRequest;
        this.rokuRequestType = rokuRequestTypes;
    }

    public class Result {
        Exception mException;
        Object mResultValue;

        public Result(Object obj) {
            this.mResultValue = obj;
        }

        public Result(Exception exc) {
            this.mException = exc;
        }
    }

    @Override
    public Result call() {
        try {
            if (this.rokuRequestType.equals(RokuRequestTypes.query_active_app)) {
                return new Result((List) this.request.send().getResponseData());
            }
            if (this.rokuRequestType.equals(RokuRequestTypes.query_device_info)) {
                return new Result((Device) this.request.send().getResponseData());
            }
            if (this.rokuRequestType.equals(RokuRequestTypes.query_icon)) {
                return new Result(((ByteArrayOutputStream) this.request.send().getResponseData()).toByteArray());
            }
            return new Result(this.request.send().getResponseData());
        } catch (IOException e) {
            if (e instanceof UnknownHostException) {
                handleUnknownHostException();
            }
            e.printStackTrace();
            return new Result((Exception) e);
        }
    }

    private void handleUnknownHostException() {
        List<Device> call = new AvailableDevicesTask(this.context, false).call();
        try {
            Device connectedDevice = PreferenceUtils.getConnectedDevice(this.context);
            for (Device device : call) {
                if (device.getSerialNumber().equals(connectedDevice.getSerialNumber())) {
                    DBUtils.updateDevice(this.context, device);
                    return;
                }
            }
        } catch (Exception unused) {
            Log.e(TAG, "Device not found");
        }
    }
}
