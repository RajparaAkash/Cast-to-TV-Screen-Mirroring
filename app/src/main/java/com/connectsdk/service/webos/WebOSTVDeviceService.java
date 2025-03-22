package com.connectsdk.service.webos;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import com.connectsdk.core.AppInfo;
import com.connectsdk.core.ChannelInfo;
import com.connectsdk.core.ProgramInfo;
import com.connectsdk.core.ProgramList;
import com.connectsdk.core.Util;
import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.CastService;
import com.connectsdk.service.DLNAService;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.capability.Launcher;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.PowerControl;
import com.connectsdk.service.capability.TVControl;
import com.connectsdk.service.capability.VolumeControl;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommand;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.command.URLServiceSubscription;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.sessions.LaunchSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Map;


public class WebOSTVDeviceService extends DeviceService implements PowerControl, MediaControl {
    static String APP_STATE = "ssap://system.launcher/getAppState";
    static String CHANNEL = "ssap://tv/getCurrentChannel";
    static String CHANNEL_LIST = "ssap://tv/getChannelList";
    static String FOREGROUND_APP = "ssap://com.webos.applicationManager/getForegroundAppInfo";
    static String MUTE = "ssap://audio/getMute";
    static String PROGRAM = "ssap://tv/getChannelProgramInfo";
    static String VOLUME = "ssap://audio/getVolume";
    static String VOLUME_STATUS = "ssap://audio/getStatus";
    public static final String[] kWebOSTVServiceOpenPermissions = {"LAUNCH", "LAUNCH_WEBAPP", "APP_TO_APP", "CONTROL_AUDIO", "CONTROL_INPUT_MEDIA_PLAYBACK", "UPDATE_FROM_REMOTE_APP"};
    public static final String[] kWebOSTVServiceProtectedPermissions = {"CONTROL_POWER", "READ_INSTALLED_APPS", "CONTROL_DISPLAY", "CONTROL_INPUT_JOYSTICK", "CONTROL_INPUT_MEDIA_RECORDING", "CONTROL_INPUT_TV", "READ_INPUT_DEVICE_LIST", "READ_NETWORK_STATE", "READ_TV_CHANNEL_LIST", "WRITE_NOTIFICATION_TOAST", "CONTROL_BLUETOOTH", "CHECK_BLUETOOTH_DEVICE", "CONTROL_USER_INFO", "CONTROL_TIMER_INFO", "READ_SETTINGS", "CONTROL_TV_SCREEN"};
    public static final String[] kWebOSTVServicePersonalActivityPermissions = {"CONTROL_INPUT_TEXT", "CONTROL_MOUSE_AND_KEYBOARD", "READ_CURRENT_CHANNEL", "READ_RUNNING_APPS"};

    public interface WebOSTVServicePermission {
        public static final WebOSTVServicePermission[] OPEN = {Open.LAUNCH, Open.LAUNCH_WEB, Open.APP_TO_APP, Open.CONTROL_AUDIO, Open.CONTROL_INPUT_MEDIA_PLAYBACK};
        public static final WebOSTVServicePermission[] PROTECTED = {Protected.CONTROL_POWER, Protected.READ_INSTALLED_APPS, Protected.CONTROL_DISPLAY, Protected.CONTROL_INPUT_JOYSTICK, Protected.CONTROL_INPUT_MEDIA_RECORDING, Protected.CONTROL_INPUT_TV, Protected.READ_INPUT_DEVICE_LIST, Protected.READ_NETWORK_STATE, Protected.READ_TV_CHANNEL_LIST, Protected.WRITE_NOTIFICATION_TOAST, Protected.CONTROL_BLUETOOTH, Protected.CHECK_BLUETOOTH_DEVICE, Protected.CONTROL_TV_SCREEN};
        public static final WebOSTVServicePermission[] PERSONAL_ACTIVITY = {PersonalActivity.CONTROL_INPUT_TEXT, PersonalActivity.CONTROL_MOUSE_AND_KEYBOARD, PersonalActivity.READ_CURRENT_CHANNEL, PersonalActivity.READ_RUNNING_APPS};


        public enum Open implements WebOSTVServicePermission {
            LAUNCH,
            LAUNCH_WEB,
            APP_TO_APP,
            CONTROL_AUDIO,
            CONTROL_INPUT_MEDIA_PLAYBACK
        }


        public enum PersonalActivity implements WebOSTVServicePermission {
            CONTROL_INPUT_TEXT,
            CONTROL_MOUSE_AND_KEYBOARD,
            READ_CURRENT_CHANNEL,
            READ_RUNNING_APPS
        }


        public enum Protected implements WebOSTVServicePermission {
            CONTROL_POWER,
            READ_INSTALLED_APPS,
            CONTROL_DISPLAY,
            CONTROL_INPUT_JOYSTICK,
            CONTROL_INPUT_MEDIA_RECORDING,
            CONTROL_INPUT_TV,
            READ_INPUT_DEVICE_LIST,
            READ_NETWORK_STATE,
            READ_TV_CHANNEL_LIST,
            WRITE_NOTIFICATION_TOAST,
            CONTROL_BLUETOOTH,
            CHECK_BLUETOOTH_DEVICE,
            CONTROL_TV_SCREEN
        }
    }

    @Override
    public MediaControl getMediaControl() {
        return null;
    }

    @Override
    public PowerControl getPowerControl() {
        return this;
    }

    public DeviceService getDLNAService() {
        Map<String, ConnectableDevice> allDevices = DiscoveryManager.getInstance().getAllDevices();
        ConnectableDevice connectableDevice = (allDevices == null || allDevices.size() <= 0) ? null : allDevices.get(this.serviceDescription.getIpAddress());
        if (connectableDevice != null) {
            return connectableDevice.getServiceByName(DLNAService.ID);
        }
        return null;
    }

    public WebOSTVDeviceService(ServiceDescription serviceDescription, ServiceConfig serviceConfig) {
        super(serviceDescription, serviceConfig);
        this.serviceDescription = serviceDescription;
        this.serviceConfig = serviceConfig;
    }

    public ServiceCommand<ResponseListener<Object>> getCurrentSWInfo(final ResponseListener<Object> listener) {
        ServiceCommand<ResponseListener<Object>> serviceCommand = new ServiceCommand<>(this, "ssap://com.webos.service.update/getCurrentSWInformation", null, true, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                Util.postSuccess(listener, (JSONObject) response);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
        serviceCommand.send();
        return serviceCommand;
    }

    @Override
    public void setPairingType(PairingType pairingType) {
        this.pairingType = pairingType;
    }

    public ServiceCommand<ResponseListener<Object>> getMuteStatus(boolean isSubscription, final VolumeControl.MuteListener listener) {
        ServiceCommand<ResponseListener<Object>> serviceCommand;
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                try {
                    Util.postSuccess(listener, Boolean.valueOf(((Boolean) ((JSONObject) response).get(CastService.CAST_SERVICE_MUTE_SUBSCRIPTION_NAME)).booleanValue()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        if (isSubscription) {
            serviceCommand = new URLServiceSubscription<>(this, MUTE, null, true, responseListener);
        } else {
            serviceCommand = new ServiceCommand<>(this, MUTE, null, true, responseListener);
        }
        serviceCommand.send();
        return serviceCommand;
    }

    public ServiceCommand<ResponseListener<Object>> getVolumeStatus(boolean isSubscription, final VolumeControl.VolumeStatusListener listener) {
        ServiceCommand<ResponseListener<Object>> serviceCommand;
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                try {
                    JSONObject jSONObject = (JSONObject) response;
                    boolean booleanValue = ((Boolean) jSONObject.get(CastService.CAST_SERVICE_MUTE_SUBSCRIPTION_NAME)).booleanValue();
                    int i = 0;
                    if (jSONObject.has("volume")) {
                        i = ((Integer) jSONObject.get("volume")).intValue();
                    } else if (jSONObject.has("volumeStatus")) {
                        i = ((Integer) jSONObject.getJSONObject("volumeStatus").get("volume")).intValue();
                    }
                    Util.postSuccess(listener, new VolumeControl.VolumeStatus(booleanValue, (float) (i / 100.0d)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        if (isSubscription) {
            serviceCommand = new URLServiceSubscription<>(this, VOLUME_STATUS, null, true, responseListener);
        } else {
            serviceCommand = new ServiceCommand<>(this, VOLUME_STATUS, null, true, responseListener);
        }
        serviceCommand.send();
        return serviceCommand;
    }

    protected ProgramInfo parseRawProgramInfo(JSONObject programRawData) {
        ProgramInfo programInfo = new ProgramInfo();
        programInfo.setRawData(programRawData);
        String optString = programRawData.optString("programId");
        String optString2 = programRawData.optString("programName");
        ChannelInfo parseRawChannelData = parseRawChannelData(programRawData);
        programInfo.setId(optString);
        programInfo.setName(optString2);
        programInfo.setChannelInfo(parseRawChannelData);
        return programInfo;
    }

    protected ChannelInfo parseRawChannelData(JSONObject channelRawData) {
        int parseMajorNumber;
        int parseMinorNumber;
        ChannelInfo channelInfo = new ChannelInfo();
        channelInfo.setRawData(channelRawData);
        try {
            String str = !channelRawData.isNull("channelName") ? (String) channelRawData.get("channelName") : null;
            String str2 = channelRawData.isNull("channelId") ? null : (String) channelRawData.get("channelId");
            String optString = channelRawData.optString("channelNumber");
            if (!channelRawData.isNull("majorNumber")) {
                parseMajorNumber = ((Integer) channelRawData.get("majorNumber")).intValue();
            } else {
                parseMajorNumber = parseMajorNumber(optString);
            }
            if (!channelRawData.isNull("minorNumber")) {
                parseMinorNumber = ((Integer) channelRawData.get("minorNumber")).intValue();
            } else {
                parseMinorNumber = parseMinorNumber(optString);
            }
            channelInfo.setName(str);
            channelInfo.setId(str2);
            channelInfo.setNumber(optString);
            channelInfo.setMajorNumber(parseMajorNumber);
            channelInfo.setMinorNumber(parseMinorNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return channelInfo;
    }

    protected int parseMinorNumber(String channelNumber) {
        if (channelNumber == null || channelNumber.isEmpty()) {
            return 0;
        }
        String[] split = channelNumber.split("-");
        return Integer.valueOf(split[split.length - 1]).intValue();
    }

    protected int parseMajorNumber(String channelNumber) {
        if (channelNumber == null || channelNumber.isEmpty()) {
            return 0;
        }
        return Integer.valueOf(channelNumber.split("-")[0]).intValue();
    }

    public ServiceCommand<ResponseListener<Object>> getCurrentChannel(boolean isSubscription, final TVControl.ChannelListener listener) {
        ServiceCommand<ResponseListener<Object>> serviceCommand;
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                Util.postSuccess(listener, WebOSTVDeviceService.this.parseRawChannelData((JSONObject) response));
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        if (isSubscription) {
            serviceCommand = new URLServiceSubscription<>(this, CHANNEL, null, true, responseListener);
        } else {
            serviceCommand = new ServiceCommand<>(this, CHANNEL, null, true, responseListener);
        }
        serviceCommand.send();
        return serviceCommand;
    }

    public ServiceCommand<ResponseListener<Object>> getChannelList(boolean isSubscription, final TVControl.ChannelListListener listener) {
        ServiceCommand<ResponseListener<Object>> serviceCommand;
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                try {
                    ArrayList arrayList = new ArrayList<>();
                    JSONArray jSONArray = (JSONArray) ((JSONObject) response).get("channelList");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(WebOSTVDeviceService.this.parseRawChannelData((JSONObject) jSONArray.get(i)));
                    }
                    Util.postSuccess(listener, arrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        if (isSubscription) {
            serviceCommand = new URLServiceSubscription<>(this, CHANNEL_LIST, null, true, responseListener);
        } else {
            serviceCommand = new ServiceCommand<>(this, CHANNEL_LIST, null, true, responseListener);
        }
        serviceCommand.send();
        return serviceCommand;
    }

    public ServiceCommand<ResponseListener<Object>> getChannelCurrentProgramInfo(boolean isSubscription, final TVControl.ProgramInfoListener listener) {
        ServiceCommand<ResponseListener<Object>> serviceCommand;
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                Util.postSuccess(listener, WebOSTVDeviceService.this.parseRawProgramInfo((JSONObject) response));
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        if (isSubscription) {
            serviceCommand = new URLServiceSubscription<>(this, "ssap://tv/getChannelCurrentProgramInfo", null, true, responseListener);
        } else {
            serviceCommand = new ServiceCommand<>(this, "ssap://tv/getChannelCurrentProgramInfo", null, true, responseListener);
        }
        serviceCommand.send();
        return serviceCommand;
    }

    public ServiceCommand<ResponseListener<Object>> getProgramList(boolean isSubscription, final TVControl.ProgramListListener listener) {
        ServiceCommand<ResponseListener<Object>> serviceCommand;
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                try {
                    JSONObject jSONObject = (JSONObject) response;
                    Util.postSuccess(listener, new ProgramList(WebOSTVDeviceService.this.parseRawChannelData((JSONObject) jSONObject.get("channel")), (JSONArray) jSONObject.get("programList")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        if (isSubscription) {
            serviceCommand = new URLServiceSubscription<>(this, PROGRAM, null, true, responseListener);
        } else {
            serviceCommand = new ServiceCommand<>(this, PROGRAM, null, true, responseListener);
        }
        serviceCommand.send();
        return serviceCommand;
    }

    protected ServiceCommand<Launcher.AppInfoListener> getRunningApp(boolean isSubscription, final Launcher.AppInfoListener listener) {
        ServiceCommand<Launcher.AppInfoListener> request;

        ResponseListener<Object> responseListener = new ResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {
                final JSONObject jsonObj = (JSONObject) response;
                AppInfo app = new AppInfo() {{
                    setId(jsonObj.optString("appId"));
                    setName(jsonObj.optString("appName"));
                    setRawData(jsonObj);
                }};

                Util.postSuccess(listener, app);
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        if (isSubscription)
            request = new URLServiceSubscription<Launcher.AppInfoListener>(this, FOREGROUND_APP, null, true, responseListener);
        else
            request = new ServiceCommand<Launcher.AppInfoListener>(this, FOREGROUND_APP, null, true, responseListener);

        request.send();

        return request;
    }

    public ServiceCommand<Launcher.AppStateListener> getAppState(boolean subscription, LaunchSession launchSession, final Launcher.AppStateListener listener) {
        ServiceCommand<Launcher.AppStateListener> serviceCommand;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", launchSession.getAppId());
            jSONObject.put("sessionId", launchSession.getSessionId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }

            @Override
            public void onSuccess(Object object) {
                JSONObject jSONObject2 = (JSONObject) object;
                try {
                    Util.postSuccess(listener, new Launcher.AppState(jSONObject2.getBoolean("running"), jSONObject2.getBoolean("visible")));
                } catch (JSONException e2) {
                    Util.postError(listener, new ServiceCommandError(0, "Malformed JSONObject", null));
                    e2.printStackTrace();
                }
            }
        };
        if (subscription) {
            serviceCommand = new URLServiceSubscription<>(this, APP_STATE, jSONObject, true, responseListener);
        } else {
            serviceCommand = new ServiceCommand<>(this, APP_STATE, jSONObject, true, responseListener);
        }
        serviceCommand.send();
        return serviceCommand;
    }

    public void sendToast(JSONObject payload, ResponseListener<Object> listener) {
        if (!payload.has("iconData")) {
            Context context = DiscoveryManager.getInstance().getContext();
            try {
                Drawable applicationIcon = context.getPackageManager().getApplicationIcon(context.getPackageName());
                if (applicationIcon != null) {
                    Bitmap bitmap = ((BitmapDrawable) applicationIcon).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    payload.put("iconData", new String(Base64.encode(byteArrayOutputStream.toByteArray(), 2)));
                    payload.put("iconExtension", "png");
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        new ServiceCommand(this, "palm://system.notifications/createToast", payload, true, listener).send();
    }

    public ServiceCommand<VolumeControl.VolumeListener> getVolume(boolean isSubscription, final VolumeControl.VolumeListener listener) {
        ServiceCommand<VolumeControl.VolumeListener> serviceCommand;
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                try {
                    JSONObject jSONObject = (JSONObject) response;
                    int i = 0;
                    if (jSONObject.has("volume")) {
                        i = ((Integer) jSONObject.get("volume")).intValue();
                    } else if (jSONObject.has("volumeStatus")) {
                        i = ((Integer) jSONObject.getJSONObject("volumeStatus").get("volume")).intValue();
                    }
                    Util.postSuccess(listener, Float.valueOf((float) (i / 100.0d)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        if (isSubscription) {
            serviceCommand = new URLServiceSubscription<>(this, VOLUME, null, true, responseListener);
        } else {
            serviceCommand = new ServiceCommand<>(this, VOLUME, null, true, responseListener);
        }
        serviceCommand.send();
        return serviceCommand;
    }

    @Override
    public CapabilityPriorityLevel getPowerControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void powerOff(ResponseListener<Object> listener) {
        new ServiceCommand(this, "ssap://system/turnOff", null, true, new ResponseListener<Object>() {
            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(Object response) {
            }
        }).send();
    }

    @Override
    public void powerOn(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public CapabilityPriorityLevel getMediaControlCapabilityLevel() {
        return CapabilityPriorityLevel.HIGH;
    }

    @Override
    public void play(ResponseListener<Object> listener) {
        new ServiceCommand(this, "ssap://media.controls/play", null, true, listener).send();
    }

    @Override
    public void pause(ResponseListener<Object> listener) {
        new ServiceCommand(this, "ssap://media.controls/pause", null, true, listener).send();
    }

    @Override
    public void stop(ResponseListener<Object> listener) {
        new ServiceCommand(this, "ssap://media.controls/stop", null, true, listener).send();
    }

    @Override
    public void rewind(ResponseListener<Object> listener) {
        new ServiceCommand(this, "ssap://media.controls/rewind", null, true, listener).send();
    }

    @Override
    public void fastForward(ResponseListener<Object> listener) {
        new ServiceCommand(this, "ssap://media.controls/fastForward", null, true, listener).send();
    }

    @Override
    public void previous(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void next(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void seek(long position, ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void getDuration(DurationListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override
    public void getPosition(PositionListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    public void getPlayState(PlayStateListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    public ServiceSubscription<PlayStateListener> subscribePlayState(PlayStateListener listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
        return null;
    }
}
