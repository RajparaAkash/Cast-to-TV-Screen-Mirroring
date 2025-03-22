package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.tasks;

import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils.RokuRequestTypes;

public abstract class RequestCallback {
    public abstract void onErrorResponse(RequestTask.Result result);

    public abstract void requestResult(RokuRequestTypes rokuRequestTypes, RequestTask.Result result);
}
