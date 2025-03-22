package com.connectsdk.device;

import android.util.Log;

import androidx.annotation.Keep;

import com.connectsdk.core.Util;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.capability.CapabilityMethods;
import com.connectsdk.service.capability.ExternalInputControl;
import com.connectsdk.service.capability.KeyControl;
import com.connectsdk.service.capability.Launcher;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.MouseControl;
import com.connectsdk.service.capability.PlaylistControl;
import com.connectsdk.service.capability.PowerControl;
import com.connectsdk.service.capability.RemoteCameraControl;
import com.connectsdk.service.capability.ScreenMirroringControl;
import com.connectsdk.service.capability.TVControl;
import com.connectsdk.service.capability.TextInputControl;
import com.connectsdk.service.capability.ToastControl;
import com.connectsdk.service.capability.VolumeControl;
import com.connectsdk.service.capability.WebAppLauncher;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.config.ServiceDescription;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Keep
public class ConnectableDevice implements DeviceService.DeviceServiceListener {
    public static final String KEY_FRIENDLY = "friendlyName";
    public static final String KEY_ID = "id";
    public static final String KEY_LAST_CONNECTED = "lastConnected";
    public static final String KEY_LAST_DETECTED = "lastDetection";
    public static final String KEY_LAST_IP = "lastKnownIPAddress";
    public static final String KEY_LAST_SEEN = "lastSeenOnWifi";
    public static final String KEY_MODEL_NAME = "modelName";
    public static final String KEY_MODEL_NUMBER = "modelNumber";
    public static final String KEY_SERVICES = "services";
    public boolean featuresReady;
    private String friendlyName;

    private String id;
    private String ipAddress;
    public boolean isConnecting;
    private long lastConnected;
    private long lastDetection;
    private String lastKnownIPAddress;
    private String lastSeenOnWifi;
    CopyOnWriteArrayList<ConnectableDeviceListener> listeners;
    private String modelName;
    private String modelNumber;
    private ServiceDescription serviceDescription;
    private String serviceId;
    Map<String, DeviceService> services;

    @Override 
    public void onConnectionRequired(DeviceService service) {
    }

    @Override 
    public void onPairingSuccess(DeviceService service) {
    }

    public ConnectableDevice() {
        this.listeners = new CopyOnWriteArrayList<>();
        this.isConnecting = false;
        this.featuresReady = false;
        this.services = new ConcurrentHashMap();
    }

    public ConnectableDevice(String ipAddress, String friendlyName, String modelName, String modelNumber) {
        this();
        this.ipAddress = ipAddress;
        this.friendlyName = friendlyName;
        this.modelName = modelName;
        this.modelNumber = modelNumber;
    }

    public ConnectableDevice(ServiceDescription description) {
        this();
        update(description);
    }

    public ConnectableDevice(JSONObject json) {
        this();
        setId(json.optString("id", null));
        setLastKnownIPAddress(json.optString(KEY_LAST_IP, null));
        setFriendlyName(json.optString("friendlyName", null));
        setModelName(json.optString("modelName", null));
        setModelNumber(json.optString("modelNumber", null));
        setLastSeenOnWifi(json.optString(KEY_LAST_SEEN, null));
        setLastConnected(json.optLong(KEY_LAST_CONNECTED, 0L));
        setLastDetection(json.optLong("lastDetection", 0L));
    }

    public static ConnectableDevice createFromConfigString(String ipAddress, String friendlyName, String modelName, String modelNumber) {
        return new ConnectableDevice(ipAddress, friendlyName, modelName, modelNumber);
    }

    public static ConnectableDevice createWithId(String id, String ipAddress, String friendlyName, String modelName, String modelNumber) {
        ConnectableDevice connectableDevice = new ConnectableDevice(ipAddress, friendlyName, modelName, modelNumber);
        connectableDevice.setId(id);
        return connectableDevice;
    }

    public ServiceDescription getServiceDescription() {
        return this.serviceDescription;
    }

    public void setServiceDescription(ServiceDescription serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public void setPairingType(DeviceService.PairingType pairingType) {
        for (DeviceService deviceService : getServices()) {
            deviceService.setPairingType(pairingType);
        }
    }

    public void addService(DeviceService service) {
        final List<String> mismatchCapabilities = getMismatchCapabilities(service.getCapabilities(), getCapabilities());
        service.setListener(this);
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                Iterator<ConnectableDeviceListener> it = ConnectableDevice.this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().onCapabilityUpdated(ConnectableDevice.this, mismatchCapabilities, new ArrayList());
                }
            }
        });
        this.services.put(service.getServiceName(), service);
    }

    public void removeService(DeviceService service) {
        removeServiceWithId(service.getServiceName());
    }

    public void removeServiceWithId(String serviceId) {
        DeviceService deviceService = this.services.get(serviceId);
        if (deviceService == null) {
            return;
        }
        deviceService.disconnect();
        this.services.remove(serviceId);
        final List<String> mismatchCapabilities = getMismatchCapabilities(deviceService.getCapabilities(), getCapabilities());
        Util.runOnUI(new Runnable() {
            @Override 
            public void run() {
                Iterator<ConnectableDeviceListener> it = ConnectableDevice.this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().onCapabilityUpdated(ConnectableDevice.this, new ArrayList<>(), mismatchCapabilities);
                }
            }
        });
    }

    private synchronized List<String> getMismatchCapabilities(List<String> capabilities, List<String> allCapabilities) {
        ArrayList arrayList;
        arrayList = new ArrayList<>();
        for (String str : capabilities) {
            if (!allCapabilities.contains(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public Collection<DeviceService> getServices() {
        return this.services.values();
    }

    public DeviceService getServiceByName(String serviceName) {
        for (DeviceService deviceService : getServices()) {
            if (deviceService.getServiceName().equals(serviceName)) {
                return deviceService;
            }
        }
        return null;
    }

    public void removeServiceByName(String serviceName) {
        removeService(getServiceByName(serviceName));
    }

    public DeviceService getServiceWithUUID(String serviceUUID) {
        for (DeviceService deviceService : getServices()) {
            if (deviceService.getServiceDescription().getUUID().equals(serviceUUID)) {
                return deviceService;
            }
        }
        return null;
    }

    public void addListener(ConnectableDeviceListener listener) {
        if (this.listeners.contains(listener)) {
            return;
        }
        this.listeners.add(listener);
    }

    @Deprecated
    public void setListener(ConnectableDeviceListener listener) {
        CopyOnWriteArrayList<ConnectableDeviceListener> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        this.listeners = copyOnWriteArrayList;
        if (listener != null) {
            copyOnWriteArrayList.add(listener);
        }
    }

    public void removeListener(ConnectableDeviceListener listener) {
        this.listeners.remove(listener);
    }

    public List<ConnectableDeviceListener> getListeners() {
        return this.listeners;
    }

    public void connect() {
        new Thread(new Runnable() {
            @Override 
            public void run() {
                ConnectableDevice.this.isConnecting = true;
                for (DeviceService deviceService : ConnectableDevice.this.services.values()) {
                    if (!deviceService.isConnected()) {
                        deviceService.connect();
                    }
                }
                ConnectableDevice.this.isConnecting = false;
            }
        }).start();
    }

    public void disconnect() {
        for (DeviceService deviceService : this.services.values()) {
            deviceService.disconnect();
        }
        Util.runOnUI(new Runnable() {
            @Override 
            public void run() {
                Iterator<ConnectableDeviceListener> it = ConnectableDevice.this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().onDeviceDisconnected(ConnectableDevice.this);
                }
            }
        });
    }

    public boolean isConnected() {
        int i = 0;
        for (DeviceService deviceService : this.services.values()) {
            if (!deviceService.isConnectable() || deviceService.isConnected()) {
                i++;
            }
        }
        return i >= 1;
    }

    public boolean isConnectable() {
        for (DeviceService deviceService : this.services.values()) {
            if (deviceService.isConnectable()) {
                return true;
            }
        }
        return false;
    }

    public void sendPairingKey(String pairingKey) {
        for (DeviceService deviceService : this.services.values()) {
            deviceService.sendPairingKey(pairingKey);
        }
    }

    public void cancelPairing() {
        for (DeviceService deviceService : this.services.values()) {
            deviceService.cancelPairing();
        }
    }

    public synchronized List<String> getCapabilities() {
        ArrayList arrayList;
        arrayList = new ArrayList<>();
        for (DeviceService deviceService : this.services.values()) {
            for (String str : deviceService.getCapabilities()) {
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    public boolean hasCapability(String capability) {
        for (DeviceService deviceService : this.services.values()) {
            if (deviceService.hasCapability(capability)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAnyCapability(String... capabilities) {
        for (DeviceService deviceService : this.services.values()) {
            if (deviceService.hasAnyCapability(capabilities)) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean hasCapabilities(List<String> capabilities) {
        String[] strArr;
        strArr = new String[capabilities.size()];
        capabilities.toArray(strArr);
        return hasCapabilities(strArr);
    }

    public synchronized boolean hasCapabilities(String... capabilites) {
        boolean z;
        int length = capabilites.length;
        z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            } else if (!hasCapability(capabilites[i])) {
                break;
            } else {
                i++;
            }
        }
        return z;
    }

    @Deprecated
    public Launcher getLauncher() {
        return (Launcher) getCapability(Launcher.class);
    }

    @Deprecated
    public MediaPlayer getMediaPlayer() {
        return (MediaPlayer) getCapability(MediaPlayer.class);
    }

    @Deprecated
    public MediaControl getMediaControl() {
        return (MediaControl) getCapability(MediaControl.class);
    }

    @Deprecated
    public PlaylistControl getPlaylistControl() {
        return (PlaylistControl) getCapability(PlaylistControl.class);
    }

    @Deprecated
    public VolumeControl getVolumeControl() {
        return (VolumeControl) getCapability(VolumeControl.class);
    }

    @Deprecated
    public WebAppLauncher getWebAppLauncher() {
        return (WebAppLauncher) getCapability(WebAppLauncher.class);
    }

    @Deprecated
    public TVControl getTVControl() {
        return (TVControl) getCapability(TVControl.class);
    }

    @Deprecated
    public ToastControl getToastControl() {
        return (ToastControl) getCapability(ToastControl.class);
    }

    @Deprecated
    public TextInputControl getTextInputControl() {
        return (TextInputControl) getCapability(TextInputControl.class);
    }

    @Deprecated
    public MouseControl getMouseControl() {
        return (MouseControl) getCapability(MouseControl.class);
    }

    @Deprecated
    public ExternalInputControl getExternalInputControl() {
        return (ExternalInputControl) getCapability(ExternalInputControl.class);
    }

    @Deprecated
    public PowerControl getPowerControl() {
        return (PowerControl) getCapability(PowerControl.class);
    }

    @Deprecated
    public KeyControl getKeyControl() {
        return (KeyControl) getCapability(KeyControl.class);
    }

    @Deprecated
    public ScreenMirroringControl getScreenMirroringControl() {
        return (ScreenMirroringControl) getCapability(ScreenMirroringControl.class);
    }

    @Deprecated
    public RemoteCameraControl getRemoteCameraControl() {
        return (RemoteCameraControl) getCapability(RemoteCameraControl.class);
    }

    public <T extends CapabilityMethods> T getCapability(Class<T> controllerClass) {
        CapabilityMethods.CapabilityPriorityLevel capabilityPriorityLevel = CapabilityMethods.CapabilityPriorityLevel.NOT_SUPPORTED;
        T t = null;
        for (DeviceService deviceService : this.services.values()) {
            if (deviceService.getAPI(controllerClass) != null) {
                T api = deviceService.getAPI(controllerClass);
                CapabilityMethods.CapabilityPriorityLevel priorityLevel = deviceService.getPriorityLevel(controllerClass);
                if (t == null) {
                    if (priorityLevel == null || priorityLevel == CapabilityMethods.CapabilityPriorityLevel.NOT_SUPPORTED) {
                        Log.w(Util.T, "We found a mathcing capability class, but no priority level for the class. Please check \"getPriorityLevel()\" in your class");
                    }
                } else if (priorityLevel != null && capabilityPriorityLevel != null && priorityLevel.getValue() > capabilityPriorityLevel.getValue()) {
                }
                capabilityPriorityLevel = priorityLevel;
                t = api;
            }
        }
        return t;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public void setLastKnownIPAddress(String lastKnownIPAddress) {
        this.lastKnownIPAddress = lastKnownIPAddress;
    }

    public String getLastKnownIPAddress() {
        return this.lastKnownIPAddress;
    }

    public void setLastSeenOnWifi(String lastSeenOnWifi) {
        this.lastSeenOnWifi = lastSeenOnWifi;
    }

    public String getLastSeenOnWifi() {
        return this.lastSeenOnWifi;
    }

    public void setLastConnected(long lastConnected) {
        this.lastConnected = lastConnected;
    }

    public long getLastConnected() {
        return this.lastConnected;
    }

    public void setLastDetection(long lastDetection) {
        this.lastDetection = lastDetection;
    }

    public long getLastDetection() {
        return this.lastDetection;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getModelNumber() {
        return this.modelNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        Log.e("###TAG", "getId: " + this.id);
        return this.id;
    }

    public String getConnectedServiceNames() {
        int size = getServices().size();
        if (size <= 0) {
            return null;
        }
        String[] strArr = new String[size];
        int i = 0;
        for (DeviceService deviceService : getServices()) {
            strArr[i] = deviceService.getServiceName();
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < size; i2++) {
            String str = strArr[i2];
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public void update(ServiceDescription description) {
        setIpAddress(description.getIpAddress());
        setFriendlyName(description.getFriendlyName());
        setModelName(description.getModelName());
        setModelNumber(description.getModelNumber());
        setLastConnected(description.getLastDetection());
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", getId());
            jSONObject.put(KEY_LAST_IP, getIpAddress());
            jSONObject.put("friendlyName", getFriendlyName());
            jSONObject.put("modelName", getModelName());
            jSONObject.put("modelNumber", getModelNumber());
            jSONObject.put(KEY_LAST_SEEN, getLastSeenOnWifi());
            jSONObject.put(KEY_LAST_CONNECTED, getLastConnected());
            jSONObject.put("lastDetection", getLastDetection());
            JSONObject jSONObject2 = new JSONObject();
            for (DeviceService deviceService : this.services.values()) {
                jSONObject2.put(deviceService.getServiceConfig().getServiceUUID(), deviceService.toJSONObject());
            }
            jSONObject.put("services", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }

    @Override 
    public void onCapabilitiesUpdated(DeviceService service, List<String> added, List<String> removed) {
        DiscoveryManager.getInstance().onCapabilityUpdated(this, added, removed);
    }

    @Override 
    public void onConnectionFailure(DeviceService service, Error error) {
        onDisconnect(service, error);
    }

    @Override 
    public void onConnectionSuccess(DeviceService service) {
        if (isConnected()) {
            ConnectableDeviceStore connectableDeviceStore = DiscoveryManager.getInstance().getConnectableDeviceStore();
            if (connectableDeviceStore != null) {
                connectableDeviceStore.addDevice(this);
            }
            Util.runOnUI(new Runnable() {
                @Override 
                public void run() {
                    Iterator<ConnectableDeviceListener> it = ConnectableDevice.this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onDeviceReady(ConnectableDevice.this);
                    }
                }
            });
            setLastConnected(Util.getTime());
        }
    }

    @Override 
    public void onDisconnect(DeviceService service, Error error) {
        if (getConnectedServiceCount() == 0 || this.services.size() == 0) {
            Iterator<ConnectableDeviceListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onDeviceDisconnected(this);
            }
        }
    }

    @Override 
    public void onPairingFailed(DeviceService service, Error error) {
        Iterator<ConnectableDeviceListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onConnectionFailed(this, new ServiceCommandError(0, "Failed to pair with service " + service.getServiceName(), null));
        }
    }

    @Override 
    public void onPairingRequired(DeviceService service, DeviceService.PairingType pairingType, Object pairingData) {
        Iterator<ConnectableDeviceListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onPairingRequired(this, service, pairingType);
        }
    }

    private int getConnectedServiceCount() {
        int i = 0;
        for (DeviceService deviceService : this.services.values()) {
            if (!deviceService.isConnectable() || deviceService.isConnected()) {
                i++;
            }
        }
        return i;
    }

    public void setServiceId(String srvId) {
        this.serviceId = srvId;
    }

    public String getServiceId() {
        return this.serviceId;
    }
}
