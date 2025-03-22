package com.connectsdk.device;

import android.content.Context;

import com.connectsdk.core.Util;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


public class DefaultConnectableDeviceStore implements ConnectableDeviceStore {
    static final String CLIENT_KEY = "clientKey";
    static final String CONFIG = "config";
    static final int CURRENT_VERSION = 0;
    static final String DEFAULT_SERVICE_NETCASTTV = "NetcastTVService";
    static final String DEFAULT_SERVICE_WEBOSTV = "WebOSTVService";
    static final String DESCRIPTION = "description";
    static final String FILENAME = "StoredDevices";
    static final String FILTER = "filter";
    static final String FRIENDLY_NAME = "friendlyName";
    static final String IP_ADDRESS = "ipAddress";
    public static final String KEY_CREATED = "created";
    public static final String KEY_DEVICES = "devices";
    public static final String KEY_UPDATED = "updated";
    public static final String KEY_VERSION = "version";
    static final String MODEL_NAME = "modelName";
    static final String MODEL_NUMBER = "modelNumber";
    static final String PAIRING_KEY = "pairingKey";
    static final String PORT = "port";
    static final String SERVER_CERTIFICATE = "serverCertificate";
    static final String SERVICES = "services";
    static final String SERVICE_UUID = "serviceUUID";
    static final String UUID = "uuid";
    public long created;
    private String fileFullPath;
    public long updated;
    public int version;
    public long maxStoreDuration = TimeUnit.DAYS.toSeconds(3);
    private Map<String, JSONObject> storedDevices = new ConcurrentHashMap();
    private Map<String, ConnectableDevice> activeDevices = new ConcurrentHashMap();
    private boolean waitToWrite = false;

    public DefaultConnectableDeviceStore(Context context) {
        this.fileFullPath = new File(context.getFilesDir(), FILENAME).getPath();
        load();
    }

    @Override
    public void addDevice(ConnectableDevice device) {
        if (device == null || device.getServices().size() == 0) {
            return;
        }
        if (!this.activeDevices.containsKey(device.getId())) {
            this.activeDevices.put(device.getId(), device);
        }
        if (getStoredDevice(device.getId()) != null) {
            updateDevice(device);
            return;
        }
        this.storedDevices.put(device.getId(), device.toJSONObject());
        store();
    }

    @Override
    public void removeDevice(ConnectableDevice device) {
        if (device == null) {
            return;
        }
        this.activeDevices.remove(device.getId());
        this.storedDevices.remove(device.getId());
        store();
    }

    @Override
    public void updateDevice(ConnectableDevice device) {
        JSONObject storedDevice;
        if (device == null || device.getServices().size() == 0 || (storedDevice = getStoredDevice(device.getId())) == null) {
            return;
        }
        try {
            storedDevice.put(ConnectableDevice.KEY_LAST_IP, device.getLastKnownIPAddress());
            storedDevice.put(ConnectableDevice.KEY_LAST_SEEN, device.getLastSeenOnWifi());
            storedDevice.put(ConnectableDevice.KEY_LAST_CONNECTED, device.getLastConnected());
            storedDevice.put("lastDetection", device.getLastDetection());
            JSONObject optJSONObject = storedDevice.optJSONObject("services");
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
            }
            for (DeviceService deviceService : device.getServices()) {
                JSONObject jSONObject = deviceService.toJSONObject();
                if (jSONObject != null) {
                    optJSONObject.put(deviceService.getServiceDescription().getUUID(), jSONObject);
                }
            }
            storedDevice.put("services", optJSONObject);
            this.storedDevices.put(device.getId(), storedDevice);
            this.activeDevices.put(device.getId(), device);
            store();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAll() {
        this.activeDevices.clear();
        this.storedDevices.clear();
        store();
    }

    @Override
    public JSONObject getStoredDevices() {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, JSONObject> entry : this.storedDevices.entrySet()) {
            try {
                jSONObject.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    @Override
    public ConnectableDevice getDevice(String uuid) {
        JSONObject storedDevice;
        if (uuid == null || uuid.length() == 0) {
            return null;
        }
        ConnectableDevice activeDevice = getActiveDevice(uuid);
        return (activeDevice != null || (storedDevice = getStoredDevice(uuid)) == null) ? activeDevice : new ConnectableDevice(storedDevice);
    }

    private ConnectableDevice getActiveDevice(String uuid) {
        ConnectableDevice connectableDevice = this.activeDevices.get(uuid);
        if (connectableDevice == null) {
            for (ConnectableDevice connectableDevice2 : this.activeDevices.values()) {
                for (DeviceService deviceService : connectableDevice2.getServices()) {
                    if (uuid.equals(deviceService.getServiceDescription().getUUID())) {
                        return connectableDevice2;
                    }
                }
            }
        }
        return connectableDevice;
    }

    private JSONObject getStoredDevice(String uuid) {
        JSONObject jSONObject = this.storedDevices.get(uuid);
        if (jSONObject == null) {
            for (JSONObject jSONObject2 : this.storedDevices.values()) {
                JSONObject optJSONObject = jSONObject2.optJSONObject("services");
                if (optJSONObject != null && optJSONObject.has(uuid)) {
                    return jSONObject2;
                }
            }
        }
        return jSONObject;
    }

    @Override
    public ServiceConfig getServiceConfig(ServiceDescription serviceDescription) {
        String uuid;
        JSONObject storedDevice;
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        JSONObject optJSONObject3;
        if (serviceDescription == null || (uuid = serviceDescription.getUUID()) == null || uuid.length() == 0 || (storedDevice = getStoredDevice(uuid)) == null || (optJSONObject = storedDevice.optJSONObject("services")) == null || (optJSONObject2 = optJSONObject.optJSONObject(uuid)) == null || (optJSONObject3 = optJSONObject2.optJSONObject("config")) == null) {
            return null;
        }
        return ServiceConfig.getConfig(optJSONObject3);
    }

    private void load() {
        File file = new File(this.fileFullPath);
        if (!file.exists()) {
            this.version = 0;
            this.created = Util.getTime();
            this.updated = Util.getTime();
            return;
        }
        boolean z = true;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            JSONObject jSONObject = new JSONObject(sb.toString());
            JSONArray optJSONArray = jSONObject.optJSONArray(KEY_DEVICES);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    this.storedDevices.put(jSONObject2.getString("id"), jSONObject2);
                }
            }
            this.version = jSONObject.optInt("version", 0);
            this.created = jSONObject.optLong(KEY_CREATED, 0L);
            this.updated = jSONObject.optLong(KEY_UPDATED, 0L);
            z = false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (z && this.storedDevices == null) {
            file.delete();
            this.version = 0;
            this.created = Util.getTime();
            this.updated = Util.getTime();
        }
    }

    private void store() {
        this.updated = Util.getTime();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.version);
            jSONObject.put(KEY_CREATED, this.created);
            jSONObject.put(KEY_UPDATED, this.updated);
            jSONObject.put(KEY_DEVICES, new JSONArray((Collection<?>) this.storedDevices.values()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (this.waitToWrite) {
            return;
        }
        writeStoreToDisk(jSONObject);
    }

    
    public synchronized void writeStoreToDisk(final JSONObject deviceStore) {
        final long j = this.updated;
        this.waitToWrite = true;
        Util.runInBackground(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        File file = new File(DefaultConnectableDeviceStore.this.fileFullPath);
                        if (!file.exists()) {
                            file.getParentFile().mkdirs();
                        }
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write(deviceStore.toString());
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    DefaultConnectableDeviceStore.this.waitToWrite = false;
                    if (j < DefaultConnectableDeviceStore.this.updated) {
                        DefaultConnectableDeviceStore.this.writeStoreToDisk(deviceStore);
                    }
                } catch (Throwable th) {
                    DefaultConnectableDeviceStore.this.waitToWrite = false;
                    throw th;
                }
            }
        });
    }
}
