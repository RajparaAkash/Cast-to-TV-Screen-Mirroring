package com.connectsdk.discovery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.connectsdk.DefaultPlatform;
import com.connectsdk.core.Util;
import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.device.ConnectableDeviceListener;
import com.connectsdk.device.ConnectableDeviceStore;
import com.connectsdk.device.DefaultConnectableDeviceStore;
import com.connectsdk.service.DLNAService;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.NetcastTVService;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DiscoveryManager implements ConnectableDeviceListener, DiscoveryProviderListener, ServiceConfig.ServiceConfigListener {

    private static final String TAG = DiscoveryManager.class.getSimpleName();
    public static String CONNECT_SDK_VERSION = "1.6.0";
    private static DiscoveryManager instance;
    private ConcurrentHashMap<String, ConnectableDevice> allDevices;
    List<CapabilityFilter> capabilityFilters;
    private ConcurrentHashMap<String, ConnectableDevice> compatibleDevices;
    ConnectableDeviceStore connectableDeviceStore;
    Context context;
    ConcurrentHashMap<String, Class<? extends DeviceService>> deviceClasses;
    private CopyOnWriteArrayList<DiscoveryManagerListener> discoveryListeners;
    CopyOnWriteArrayList<DiscoveryProvider> discoveryProviders;
    boolean isBroadcastReceiverRegistered;
    private boolean mSearching;
    WifiManager.MulticastLock multicastLock;
    PairingLevel pairingLevel;
    BroadcastReceiver receiver;
    int rescanInterval;
    Timer rescanTimer;
    private boolean serviceIntegrationEnabled;


    public enum PairingLevel {
        OFF,
        PROTECTED,
        ON
    }

    @Override
    public void onConnectionFailed(ConnectableDevice device, ServiceCommandError error) {
    }

    @Override
    public void onDeviceDisconnected(ConnectableDevice device) {
    }

    @Override
    public void onDeviceReady(ConnectableDevice device) {
    }

    @Override
    public void onPairingRequired(ConnectableDevice device, DeviceService service, DeviceService.PairingType pairingType) {
    }

    public void setServiceIntegration(boolean value) {
        this.serviceIntegrationEnabled = value;
    }

    public boolean isServiceIntegrationEnabled() {
        return this.serviceIntegrationEnabled;
    }

    private String getDeviceKey(ConnectableDevice device) {
        return isServiceIntegrationEnabled() ? device.getFriendlyName() + device.getIpAddress() : device.getFriendlyName() + device.getIpAddress() + device.getServiceId();
    }

    private String getDeviceKey(ServiceDescription srvDesc) {
        return isServiceIntegrationEnabled() ? srvDesc.getFriendlyName() + srvDesc.getIpAddress() : srvDesc.getFriendlyName() + srvDesc.getIpAddress() + srvDesc.getServiceID();
    }

    public static synchronized void init(Context context) {
        synchronized (DiscoveryManager.class) {
            instance = new DiscoveryManager(context);
        }
    }

    public static synchronized void destroy() {
        synchronized (DiscoveryManager.class) {
            DiscoveryManager discoveryManager = instance;
            if (discoveryManager != null) {
                discoveryManager.onDestroy();
            }
        }
    }

    public static synchronized void init1(Context context, ConnectableDeviceStore connectableDeviceStore) {
        synchronized (DiscoveryManager.class) {
            instance = new DiscoveryManager(context, connectableDeviceStore);
        }
    }

    public static synchronized DiscoveryManager getInstance() {
        DiscoveryManager discoveryManager;
        synchronized (DiscoveryManager.class) {
            discoveryManager = instance;
            if (discoveryManager == null) {
                throw new Error("Call DiscoveryManager.init(Context) first");
            }
        }
        return discoveryManager;
    }

    public DiscoveryManager(Context context) {
        this(context, new DefaultConnectableDeviceStore(context));
    }

    public DiscoveryManager(Context context, ConnectableDeviceStore connectableDeviceStore) {
        Log.e(TAG, "DiscoveryManager: init");
        this.rescanInterval = 10;
        this.isBroadcastReceiverRegistered = false;
        this.mSearching = false;
        this.serviceIntegrationEnabled = false;
        this.context = context;
        this.connectableDeviceStore = connectableDeviceStore;
        this.allDevices = new ConcurrentHashMap<>(8, 0.75f, 2);
        this.compatibleDevices = new ConcurrentHashMap<>(8, 0.75f, 2);
        this.deviceClasses = new ConcurrentHashMap<>(4, 0.75f, 2);
        this.discoveryProviders = new CopyOnWriteArrayList<>();
        this.discoveryListeners = new CopyOnWriteArrayList<>();
        WifiManager.MulticastLock createMulticastLock = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).createMulticastLock(Util.T);
        this.multicastLock = createMulticastLock;
        createMulticastLock.setReferenceCounted(true);
        this.capabilityFilters = new ArrayList<>();
        this.pairingLevel = PairingLevel.OFF;

        receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
                    NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);

                    switch (networkInfo.getState()) {
                        case CONNECTED:
                            if (mSearching) {
                                for (DiscoveryProvider provider : discoveryProviders) {
                                    provider.restart();
                                }
                            }

                            break;

                        case DISCONNECTED:
                            Log.w(Util.T, "Network connection is disconnected");

                            for (DiscoveryProvider provider : discoveryProviders) {
                                provider.reset();
                            }

                            allDevices.clear();

                            for (ConnectableDevice device: compatibleDevices.values()) {
                                handleDeviceLoss(device);
                            }
                            compatibleDevices.clear();

                            break;

                        case CONNECTING:
                            break;
                        case DISCONNECTING:
                            break;
                        case SUSPENDED:
                            break;
                        case UNKNOWN:
                            break;
                    }
                }
            }
        };

        registerBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        if (this.isBroadcastReceiverRegistered) {
            return;
        }
        this.isBroadcastReceiverRegistered = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        this.context.registerReceiver(this.receiver, intentFilter);
    }

    private void unregisterBroadcastReceiver() {
        if (this.isBroadcastReceiverRegistered) {
            this.isBroadcastReceiverRegistered = false;
            this.context.unregisterReceiver(this.receiver);
        }
    }

    public void addListener(DiscoveryManagerListener listener) {
        for (ConnectableDevice connectableDevice : this.compatibleDevices.values()) {
            listener.onDeviceAdded(this, connectableDevice);
        }
        this.discoveryListeners.add(listener);
    }

    public void removeListener(DiscoveryManagerListener listener) {
        this.discoveryListeners.remove(listener);
    }

    public List<CapabilityFilter> getCapabilityFilters() {
        return this.capabilityFilters;
    }

    public boolean deviceIsCompatible(ConnectableDevice device) {
        List<CapabilityFilter> list = this.capabilityFilters;
        if (list == null || list.size() == 0) {
            return true;
        }
        for (CapabilityFilter capabilityFilter : this.capabilityFilters) {
            if (device.hasCapabilities(capabilityFilter.capabilities)) {
                return true;
            }
        }
        return false;
    }

    public void registerDefaultDeviceTypes() {
        for (Map.Entry<String, String> stringEntry : DefaultPlatform.getDeviceServiceMap().entrySet()) {
            try {
                registerDeviceService((Class<DeviceService>) Class.forName(stringEntry.getKey()), (Class<DiscoveryProvider>) Class.forName(stringEntry.getValue()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerDeviceService(Class<? extends DeviceService> deviceClass, Class<? extends DiscoveryProvider> discoveryClass) {
        DiscoveryProvider discoveryProvider;
        if (DeviceService.class.isAssignableFrom(deviceClass) && DiscoveryProvider.class.isAssignableFrom(discoveryClass)) {
            try {
                Iterator<DiscoveryProvider> it = this.discoveryProviders.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        discoveryProvider = null;
                        break;
                    }
                    discoveryProvider = it.next();
                    if (discoveryProvider.getClass().isAssignableFrom(discoveryClass)) {
                        break;
                    }
                }
                if (discoveryProvider == null) {
                    discoveryProvider = discoveryClass.getConstructor(Context.class).newInstance(this.context);
                    discoveryProvider.addListener(this);
                    this.discoveryProviders.add(discoveryProvider);
                }
                DiscoveryFilter discoveryFilter = (DiscoveryFilter) deviceClass.getMethod("discoveryFilter", new Class[0]).invoke(null, new Object[0]);
                this.deviceClasses.put(discoveryFilter.getServiceId(), deviceClass);
                discoveryProvider.addDeviceFilter(discoveryFilter);
                if (this.mSearching) {
                    discoveryProvider.restart();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (InstantiationException e3) {
                e3.printStackTrace();
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
            } catch (SecurityException e5) {
                e5.printStackTrace();
            } catch (RuntimeException e6) {
                e6.printStackTrace();
            } catch (InvocationTargetException e7) {
                e7.printStackTrace();
            }
        }
    }

    public void unregisterDeviceService(Class<?> deviceClass, Class<?> discoveryClass) {
        DiscoveryProvider discoveryProvider;
        if (DeviceService.class.isAssignableFrom(deviceClass) && DiscoveryProvider.class.isAssignableFrom(discoveryClass)) {
            try {
                Iterator<DiscoveryProvider> it = this.discoveryProviders.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        discoveryProvider = null;
                        break;
                    }
                    discoveryProvider = it.next();
                    if (discoveryProvider.getClass().isAssignableFrom(discoveryClass)) {
                        break;
                    }
                }
                if (discoveryProvider == null) {
                    return;
                }
                DiscoveryFilter discoveryFilter = (DiscoveryFilter) deviceClass.getMethod("discoveryFilter", new Class[0]).invoke(null, new Object[0]);
                if (this.deviceClasses.remove(discoveryFilter.getServiceId()) == null) {
                    return;
                }
                discoveryProvider.removeDeviceFilter(discoveryFilter);
                if (discoveryProvider.isEmpty()) {
                    discoveryProvider.stop();
                    this.discoveryProviders.remove(discoveryProvider);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            } catch (SecurityException e4) {
                e4.printStackTrace();
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
            }
        }
    }

    public void start() {
        if (this.mSearching || this.discoveryProviders == null) {
            return;
        }
        this.mSearching = true;
        this.multicastLock.acquire();
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                if (DiscoveryManager.this.discoveryProviders.size() == 0) {
                    DiscoveryManager.this.registerDefaultDeviceTypes();
                }
                if (((ConnectivityManager) DiscoveryManager.this.context.getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(1).isConnected()) {
                    Iterator<DiscoveryProvider> it = DiscoveryManager.this.discoveryProviders.iterator();
                    while (it.hasNext()) {
                        it.next().start();
                    }
                    return;
                }
                Log.w(Util.T, "Wifi is not connected yet");
                Util.runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        Iterator it2 = DiscoveryManager.this.discoveryListeners.iterator();
                        while (it2.hasNext()) {
                            ((DiscoveryManagerListener) it2.next()).onDiscoveryFailed(DiscoveryManager.this, new ServiceCommandError(0, "No wifi connection", null));
                        }
                    }
                });
            }
        });
    }

    public void stop() {
        if (this.mSearching) {
            this.mSearching = false;
            Iterator<DiscoveryProvider> it = this.discoveryProviders.iterator();
            while (it.hasNext()) {
                it.next().stop();
            }
            if (this.multicastLock.isHeld()) {
                this.multicastLock.release();
            }
        }
    }

    public void setConnectableDeviceStore(ConnectableDeviceStore connectableDeviceStore) {
        this.connectableDeviceStore = connectableDeviceStore;
    }

    public ConnectableDeviceStore getConnectableDeviceStore() {
        return this.connectableDeviceStore;
    }

    public void handleDeviceAdd(ConnectableDevice device) {
        if (deviceIsCompatible(device)) {
            Log.e(TAG, "handleDeviceAdd: ");
            this.compatibleDevices.put(getDeviceKey(device), device);
            Iterator<DiscoveryManagerListener> it = this.discoveryListeners.iterator();
            while (it.hasNext()) {
                it.next().onDeviceAdded(this, device);
            }
        }
    }

    public void handleDeviceUpdate(ConnectableDevice device) {
        Log.e(TAG, "handleDeviceUpdate: ");
        String deviceKey = getDeviceKey(device);
        if (deviceIsCompatible(device)) {
            if (device.getIpAddress() != null && this.compatibleDevices.containsKey(deviceKey)) {
                Iterator<DiscoveryManagerListener> it = this.discoveryListeners.iterator();
                while (it.hasNext()) {
                    it.next().onDeviceUpdated(this, device);
                }
                return;
            }
            handleDeviceAdd(device);
            return;
        }
        this.compatibleDevices.remove(deviceKey);
        handleDeviceLoss(device);
    }

    public void handleDeviceLoss(ConnectableDevice device) {
        Iterator<DiscoveryManagerListener> it = this.discoveryListeners.iterator();
        while (it.hasNext()) {
            it.next().onDeviceRemoved(this, device);
        }
        device.disconnect();
    }

    public boolean isNetcast(ServiceDescription description) {
        String modelName = description.getModelName();
        String modelDescription = description.getModelDescription();
        return (modelName == null || !modelName.toUpperCase(Locale.US).equals("LG TV") || modelDescription == null || modelDescription.toUpperCase(Locale.US).contains("WEBOS") || !description.getServiceID().equals(NetcastTVService.f313ID)) ? false : true;
    }

    public Map<String, ConnectableDevice> getAllDevices() {
        return this.allDevices;
    }

    public ConnectableDevice getDeviceById(String deviceId) {
        if (deviceId != null) {
            for (ConnectableDevice connectableDevice : this.allDevices.values()) {
                if (deviceId.equals(connectableDevice.getId())) {
                    return connectableDevice;
                }
            }
            return null;
        }
        return null;
    }

    public ConnectableDevice getDeviceByIpAddress(String ipAddress) {
        if (ipAddress != null) {
            for (ConnectableDevice connectableDevice : this.allDevices.values()) {
                if (ipAddress.equals(connectableDevice.getIpAddress())) {
                    return connectableDevice;
                }
            }
            return null;
        }
        return null;
    }

    public Map<String, ConnectableDevice> getCompatibleDevices() {
        return this.compatibleDevices;
    }

    public PairingLevel getPairingLevel() {
        return this.pairingLevel;
    }

    public void setPairingLevel(PairingLevel pairingLevel) {
        this.pairingLevel = pairingLevel;
    }

    public Context getContext() {
        return this.context;
    }

    public void onDestroy() {
        unregisterBroadcastReceiver();
    }

    public List<DiscoveryProvider> getDiscoveryProviders() {
        return new ArrayList(this.discoveryProviders);
    }

    @Override
    public void onServiceConfigUpdate(ServiceConfig serviceConfig) {
        if (this.connectableDeviceStore == null) {
            return;
        }
        for (ConnectableDevice connectableDevice : getAllDevices().values()) {
            if (connectableDevice.getServiceWithUUID(serviceConfig.getServiceUUID()) != null) {
                this.connectableDeviceStore.updateDevice(connectableDevice);
            }
        }
    }

    @Override
    public void onCapabilityUpdated(ConnectableDevice device, List<String> added, List<String> removed) {
        handleDeviceUpdate(device);
    }

    @Override
    public void onServiceAdded(DiscoveryProvider provider, ServiceDescription serviceDescription) {
        Log.e(TAG, "onServiceAdded  :::  Service added: " + serviceDescription.getFriendlyName() + " (" + serviceDescription.getServiceID() + ")");
        String deviceKey = getDeviceKey(serviceDescription);
        boolean z = true;
        boolean z2 = !this.allDevices.containsKey(deviceKey);
        ConnectableDevice connectableDevice = null;

        Log.e(TAG, "onServiceAdded: 11");
        if (z2) {
            Log.e(TAG, "onServiceAdded: 55");
            if (connectableDeviceStore != null && (connectableDeviceStore.getDevice(serviceDescription.getUUID())) != null) {
                connectableDevice = connectableDeviceStore.getDevice(serviceDescription.getUUID());
                this.allDevices.put(deviceKey, connectableDevice);
                connectableDevice.setIpAddress(serviceDescription.getIpAddress());
                Log.e(TAG, "onServiceAdded: 66");
            }
        } else {
            Log.e(TAG, "onServiceAdded: 77");
            connectableDevice = this.allDevices.get(deviceKey);
        }

        Log.e(TAG, "onServiceAdded: 22");

        if (connectableDevice == null) {
            connectableDevice = new ConnectableDevice(serviceDescription);
            connectableDevice.setIpAddress(serviceDescription.getIpAddress());
            this.allDevices.put(deviceKey, connectableDevice);
        } else {
            z = z2;
        }

        Log.e(TAG, "onServiceAdded: 33");

        connectableDevice.setFriendlyName(serviceDescription.getFriendlyName());
        connectableDevice.setLastDetection(Util.getTime());
        connectableDevice.setLastKnownIPAddress(serviceDescription.getIpAddress());
        connectableDevice.setServiceId(serviceDescription.getServiceID());
        addServiceDescriptionToDevice(serviceDescription, connectableDevice);
        if (connectableDevice.getServices().size() == 0) {
            this.allDevices.remove(deviceKey);
        } else if (z) {
            handleDeviceAdd(connectableDevice);
        } else {
            handleDeviceUpdate(connectableDevice);
        }
    }

    @Override
    public void onServiceRemoved(DiscoveryProvider provider, ServiceDescription serviceDescription) {
        if (serviceDescription == null) {
            Log.w(Util.T, "onServiceRemoved: unknown service description");
            return;
        }
        Log.d(Util.T, "onServiceRemoved: friendlyName: " + serviceDescription.getFriendlyName());
        String deviceKey = getDeviceKey(serviceDescription);
        ConnectableDevice connectableDevice = this.allDevices.get(deviceKey);
        if (connectableDevice != null) {
            connectableDevice.removeServiceWithId(serviceDescription.getServiceID());
            if (connectableDevice.getServices().isEmpty()) {
                this.allDevices.remove(deviceKey);
                handleDeviceLoss(connectableDevice);
                return;
            }
            handleDeviceUpdate(connectableDevice);
        }
    }

    @Override
    public void onServiceDiscoveryFailed(DiscoveryProvider provider, ServiceCommandError error) {
        Log.w(Util.T, "DiscoveryProviderListener, Service Discovery Failed");
    }

    public void addServiceDescriptionToDevice(ServiceDescription desc, ConnectableDevice device) {
        boolean z;
        boolean z2;
        Log.d(Util.T, "Adding service " + desc.getServiceID() + " to device with address " + device.getIpAddress() + " and id " + device.getId());
        Class<? extends DeviceService> cls = this.deviceClasses.get(desc.getServiceID());
        if (cls == null) {
            return;
        }
        if (cls == DLNAService.class) {
            if (desc.getLocationXML() == null) {
                return;
            }
        } else if (cls == NetcastTVService.class && !isNetcast(desc)) {
            return;
        }
        ConnectableDeviceStore connectableDeviceStore = this.connectableDeviceStore;
        ServiceConfig serviceConfig = connectableDeviceStore != null ? connectableDeviceStore.getServiceConfig(desc) : null;
        if (serviceConfig == null) {
            serviceConfig = new ServiceConfig(desc);
        }
        serviceConfig.setListener(this);
        Iterator<DeviceService> it = device.getServices().iterator();
        while (true) {
            z = true;
            z2 = false;
            if (!it.hasNext()) {
                z = false;
                break;
            }
            DeviceService next = it.next();
            if (next.getServiceDescription().getServiceID().equals(desc.getServiceID())) {
                if (next.getServiceDescription().getUUID().equals(desc.getUUID())) {
                    z2 = true;
                }
            }
        }
        if (z) {
            if (z2) {
                device.setServiceDescription(desc);
                DeviceService serviceByName = device.getServiceByName(desc.getServiceID());
                if (serviceByName != null) {
                    serviceByName.setServiceDescription(desc);
                    return;
                }
                return;
            }
            device.removeServiceByName(desc.getServiceID());
        }
        DeviceService service = DeviceService.getService(cls, desc, serviceConfig);
        if (service != null) {
            service.setServiceDescription(desc);
            device.addService(service);
        }
    }
}
