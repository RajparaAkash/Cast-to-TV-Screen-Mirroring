package com.casttotv.screenmirroring.chromecast.smart.tv.Util.Volume;

import android.content.Context;
import android.preference.PreferenceManager;

import com.jaku.core.JakuRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Callable;

public class RxRequestTask implements Callable {

    private Context context;
    private JakuRequest request;
    private RokuRequestTypes rokuRequestType;

    public RxRequestTask(Context context2, JakuRequest jakuRequest, RokuRequestTypes rokuRequestTypes) {
        this.context = context2;
        this.request = jakuRequest;
        this.rokuRequestType = rokuRequestTypes;
    }

    public class Result {
        Object mResultValue;

        public Result(RxRequestTask rxRequestTask, Object obj) {
            this.mResultValue = obj;
        }

        public Result(RxRequestTask rxRequestTask, Exception exc) {
        }
    }

    @Override 
    public Result call() {
        try {
            if (this.rokuRequestType.equals(RokuRequestTypes.query_active_app)) {
                return new Result(this, (List) this.request.send().getResponseData());
            }
            if (this.rokuRequestType.equals(RokuRequestTypes.query_device_info)) {
                return new Result(this, (Device) this.request.send().getResponseData());
            }
            if (this.rokuRequestType.equals(RokuRequestTypes.query_icon)) {
                return new Result(this, ((ByteArrayOutputStream) this.request.send().getResponseData()).toByteArray());
            }
            return new Result(this, this.request.send().getResponseData());
        } catch (IOException e) {
            if (e instanceof UnknownHostException) {
                handleUnknownHostException();
            }
            e.printStackTrace();
            return new Result(this, (Exception) e);
        }
    }

    private void handleUnknownHostException() {
        List<Device> call = new AvailableDevicesTask(this.context, false).call();
        try {
            Device connectedDevice = getConnectedDevice(this.context);
            for (Device device : call) {
                if (device.getSerialNumber().equals(connectedDevice.getSerialNumber())) {
                    DBUtils.updateDevice(this.context, device);
                    return;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Device getConnectedDevice(Context context) throws Exception {
        Device device = DBUtils.getDevice(context, PreferenceManager.getDefaultSharedPreferences(context).getString("serial_number", null));
        if (device != null) {
            return device;
        }
        throw new Exception("Device not connected");
    }
}
