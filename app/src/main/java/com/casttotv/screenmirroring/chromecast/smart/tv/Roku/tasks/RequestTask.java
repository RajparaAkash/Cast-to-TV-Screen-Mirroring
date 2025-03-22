package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.tasks;

import android.os.AsyncTask;

import com.jaku.core.JakuRequest;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.model.Device;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils.RokuRequestTypes;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class RequestTask extends AsyncTask<RokuRequestTypes, Void, RequestTask.Result> {
    private RequestCallback mCallback;
    private JakuRequest request;
    private RokuRequestTypes rokuRequestType;

    
    public void onCancelled(Result result) {
    }

    
    public void onPreExecute() {
    }

    public RequestTask(JakuRequest jakuRequest, RequestCallback requestCallback) {
        this.request = jakuRequest;
        setCallback(requestCallback);
    }

    
    public void setCallback(RequestCallback requestCallback) {
        this.mCallback = requestCallback;
    }

    public static class Result {
        public Exception mException;
        public Object mResultValue;

        public Result(Object obj) {
            this.mResultValue = obj;
        }

        public Result(Exception exc) {
            this.mException = exc;
        }
    }

    
    public Result doInBackground(RokuRequestTypes... rokuRequestTypesArr) {
        Result result;
        if (isCancelled() || rokuRequestTypesArr == null || rokuRequestTypesArr.length <= 0) {
            return null;
        }
        RokuRequestTypes rokuRequestTypes = rokuRequestTypesArr[0];
        try {
            if (rokuRequestTypes.equals(RokuRequestTypes.query_active_app)) {
                result = new Result((List) this.request.send().getResponseData());
            } else if (rokuRequestTypes.equals(RokuRequestTypes.query_device_info)) {
                result = new Result(Device.Companion.fromDevice((com.jaku.model.Device) this.request.send().getResponseData()));
            } else if (rokuRequestTypes.equals(RokuRequestTypes.query_icon)) {
                result = new Result(((ByteArrayOutputStream) this.request.send().getResponseData()).toByteArray());
            } else {
                this.request.send();
                return null;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(e);
        }
    }

    
    public void onPostExecute(Result result) {
        if (result != null && this.mCallback != null) {
            if (result.mException != null) {
                this.mCallback.onErrorResponse(result);
            } else if (result.mResultValue != null) {
                this.mCallback.requestResult(this.rokuRequestType, result);
            }
        }
    }
}
