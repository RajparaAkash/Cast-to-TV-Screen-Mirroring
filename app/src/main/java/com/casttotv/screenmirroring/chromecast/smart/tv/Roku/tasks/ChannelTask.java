package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.tasks;

import android.content.Context;

import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.jaku.api.QueryRequests;
import com.jaku.model.Channel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ChannelTask implements Callable {
    private Context context;

    public ChannelTask(Context context2) {
        this.context = context2;
    }

    @Override
    public List<Channel> call() {
        try {
            return QueryRequests.queryAppsRequest("http://" + TVConnectUtils.getInstance().getConnectableDevice().getIpAddress() + ":8060");
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }
}
