package com.connectsdk.service.sessions;

import android.util.Log;

import com.connectsdk.core.Util;
import com.connectsdk.service.CastService;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.URLServiceSubscription;
import com.connectsdk.service.google_cast.CastServiceChannel;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONObject;

import java.io.IOException;


public class CastWebAppSession extends WebAppSession {
    private CastServiceChannel castServiceChannel;
    private ApplicationMetadata metadata;
    private CastService service;

    @Override
    public MediaPlayer getMediaPlayer() {
        return this;
    }

    public CastWebAppSession(LaunchSession launchSession, DeviceService service) {
        super(launchSession, service);
        this.service = (CastService) service;
    }

    @Override
    public void connect(final ResponseListener<Object> listener) {
        if (this.castServiceChannel != null) {
            disconnectFromWebApp();
        }
        this.castServiceChannel = new CastServiceChannel(this.launchSession.getAppId(), this);
        try {
            Cast.CastApi.setMessageReceivedCallbacks(this.service.getApiClient(), this.castServiceChannel.getNamespace(), this.castServiceChannel);
            Util.postSuccess(listener, null);
        } catch (IOException exception) {
            this.castServiceChannel = null;
            Util.postError(listener, new ServiceCommandError(0, "Failed to create channel", null));
        }
    }

    @Override
    public void join(ResponseListener<Object> connectionListener) {
        connect(connectionListener);
    }

    @Override
    public void disconnectFromWebApp() {
        if (this.castServiceChannel == null) {
            return;
        }
        try {
            Cast.CastApi.removeMessageReceivedCallbacks(this.service.getApiClient(), this.castServiceChannel.getNamespace());
            this.castServiceChannel = null;
        } catch (IOException e) {
            Log.e(Util.T, "Exception while removing application", e);
        }
        Cast.CastApi.leaveApplication(this.service.getApiClient());
    }

    public void handleAppClose() {
        for (URLServiceSubscription<?> uRLServiceSubscription : this.service.getSubscriptions()) {
            if (uRLServiceSubscription.getTarget().equalsIgnoreCase(CastService.PLAY_STATE)) {
                for (int i = 0; i < uRLServiceSubscription.getListeners().size(); i++) {
                    Util.postSuccess((ResponseListener) uRLServiceSubscription.getListeners().get(i), PlayStateStatus.Idle);
                }
            }
        }
        if (getWebAppSessionListener() != null) {
            getWebAppSessionListener().onWebAppSessionDisconnect(this);
        }
    }

    @Override
    public void sendMessage(String message, final ResponseListener<Object> listener) {
        if (message == null) {
            Util.postError(listener, new ServiceCommandError(0, "Cannot send null message", null));
        } else if (this.castServiceChannel == null) {
            Util.postError(listener, new ServiceCommandError(0, "Cannot send a message to the web app without first connecting", null));
        } else {
            Cast.CastApi.sendMessage(this.service.getApiClient(), this.castServiceChannel.getNamespace(), message).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status result) {
                    if (result.isSuccess()) {
                        Util.postSuccess(listener, null);
                    } else {
                        Util.postError(listener, new ServiceCommandError(result.getStatusCode(), result.toString(), result));
                    }
                }
            });
        }
    }

    @Override
    public void sendMessage(JSONObject message, ResponseListener<Object> listener) {
        sendMessage(message.toString(), listener);
    }

    @Override
    public void close(ResponseListener<Object> listener) {
        this.launchSession.close(listener);
    }

    @Override
    public CapabilityPriorityLevel getMediaPlayerCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void playMedia(String url, String mimeType, String title, String description, String iconSrc, boolean shouldLoop, MediaPlayer.LaunchListener listener) {
        this.service.playMedia(url, mimeType, title, description, iconSrc, shouldLoop, listener);
    }

    @Override
    public void closeMedia(LaunchSession launchSession, ResponseListener<Object> listener) {
        close(listener);
    }

    public ApplicationMetadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(ApplicationMetadata metadata) {
        this.metadata = metadata;
    }
}
