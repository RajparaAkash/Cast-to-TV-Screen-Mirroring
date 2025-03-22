package com.connectsdk.service;

import android.util.SparseArray;

import com.connectsdk.core.Util;
import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.etc.helper.DeviceServiceReachability;
import com.connectsdk.service.capability.CapabilityMethods;
import com.connectsdk.service.capability.ExternalInputControl;
import com.connectsdk.service.capability.Launcher;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.WebAppLauncher;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommand;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.command.URLServiceSubscription;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.sessions.LaunchSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class DeviceService implements DeviceServiceReachability.DeviceServiceReachabilityListener, ServiceCommand.ServiceCommandProcessor {

    public static final String KEY_CLASS = "class";
    public static final String KEY_CONFIG = "config";
    public static final String KEY_DESC = "description";
    private ServiceCommand.ServiceCommandProcessor commandProcessor;
    protected DeviceServiceListener listener;
    protected DeviceServiceReachability mServiceReachability;
    protected ServiceConfig serviceConfig;
    protected ServiceDescription serviceDescription;
    protected PairingType pairingType = PairingType.NONE;
    protected boolean connected = false;
    public SparseArray<ServiceCommand<? extends Object>> requests = new SparseArray<>();
    List<String> mCapabilities = new ArrayList<>();

    
    public interface DeviceServiceListener {
        void onCapabilitiesUpdated(DeviceService service, List<String> added, List<String> removed);

        void onConnectionFailure(DeviceService service, Error error);

        void onConnectionRequired(DeviceService service);

        void onConnectionSuccess(DeviceService service);

        void onDisconnect(DeviceService service, Error error);

        void onPairingFailed(DeviceService service, Error error);

        void onPairingRequired(DeviceService service, PairingType pairingType, Object pairingData);

        void onPairingSuccess(DeviceService service);
    }

    
    public enum PairingType {
        NONE,
        FIRST_SCREEN,
        PIN_CODE,
        MIXED
    }

    public static DiscoveryFilter discoveryFilter() {
        return null;
    }

    public void cancelPairing() {
    }

    public void connect() {
    }

    public LaunchSession decodeLaunchSession(String type, JSONObject sessionObj) throws JSONException {
        return null;
    }

    public void disconnect() {
    }

    public boolean isConnectable() {
        return false;
    }

    public boolean isConnected() {
        return true;
    }

    @Override
    public void onLoseReachability(DeviceServiceReachability reachability) {
    }

    public void sendCommand(ServiceCommand<?> command) {
    }

    public void sendPairingKey(String pairingKey) {
    }

    public void setPairingType(PairingType pairingType) {
    }

    @Override 
    public void unsubscribe(ServiceSubscription<?> subscription) {
    }

    public void unsubscribe(URLServiceSubscription<?> subscription) {
    }

    protected void updateCapabilities() {
    }

    public DeviceService(ServiceDescription serviceDescription, ServiceConfig serviceConfig) {
        this.serviceDescription = serviceDescription;
        this.serviceConfig = serviceConfig;
        updateCapabilities();
    }

    public DeviceService(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
        updateCapabilities();
    }

    public ServiceCommand.ServiceCommandProcessor getCommandProcessor() {
        ServiceCommand.ServiceCommandProcessor serviceCommandProcessor = this.commandProcessor;
        return serviceCommandProcessor == null ? this : serviceCommandProcessor;
    }

    void setCommandProcessor(ServiceCommand.ServiceCommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    public static DeviceService getService(JSONObject json) {
        try {
            String optString = json.optString("class");
            if (optString.equalsIgnoreCase("DLNAService") || optString.equalsIgnoreCase(CastService.ID)) {
                return null;
            }
            Constructor<?> constructor = Class.forName(DeviceService.class.getPackage().getName() + "." + optString).getConstructor(ServiceDescription.class, ServiceConfig.class);
            JSONObject optJSONObject = json.optJSONObject("config");
            ServiceConfig config = optJSONObject != null ? ServiceConfig.getConfig(optJSONObject) : null;
            JSONObject optJSONObject2 = json.optJSONObject("description");
            ServiceDescription description = optJSONObject2 != null ? ServiceDescription.getDescription(optJSONObject2) : null;
            if (config != null && description != null) {
                return (DeviceService) constructor.newInstance(description, config);
            }
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return null;
        } catch (InstantiationException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            return null;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    public static DeviceService getService(Class<? extends DeviceService> clazz, ServiceConfig serviceConfig) {
        try {
            return clazz.getConstructor(ServiceConfig.class).newInstance(serviceConfig);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    public static DeviceService getService(Class<? extends DeviceService> clazz, ServiceDescription serviceDescription, ServiceConfig serviceConfig) {
        try {
            return clazz.getConstructor(ServiceDescription.class, ServiceConfig.class).newInstance(serviceDescription, serviceConfig);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    public PairingType getPairingType() {
        return this.pairingType;
    }

    public <T extends CapabilityMethods> T getAPI(Class<?> clazz) {
        if (clazz.isAssignableFrom(getClass())) {
            return (T) this;
        }
        return null;
    }

    public CapabilityMethods.CapabilityPriorityLevel getPriorityLevel(Class<? extends CapabilityMethods> clazz) {
        return CapabilityMethods.CapabilityPriorityLevel.NOT_SUPPORTED;
    }

    
    public void reportConnected(boolean ready) {
        DeviceServiceListener deviceServiceListener = this.listener;
        if (deviceServiceListener == null) {
            return;
        }
        if (deviceServiceListener instanceof ConnectableDevice) {
            deviceServiceListener.onConnectionSuccess(this);
        } else {
            Util.runOnUI(new Runnable() {
                @Override 
                public void run() {
                    if (DeviceService.this.listener != null) {
                        DeviceService.this.listener.onConnectionSuccess(DeviceService.this);
                    }
                }
            });
        }
    }

    public List<String> getCapabilities() {
        return this.mCapabilities;
    }

    
    public void setCapabilities(List<String> newCapabilities) {
        List<String> list = this.mCapabilities;
        this.mCapabilities = newCapabilities;
        final ArrayList arrayList = new ArrayList<>();
        for (String str : list) {
            if (!newCapabilities.contains(str)) {
                arrayList.add(str);
            }
        }
        final ArrayList arrayList2 = new ArrayList<>();
        for (String str2 : newCapabilities) {
            if (!list.contains(str2)) {
                arrayList2.add(str2);
            }
        }
        if (this.listener != null) {
            Util.runOnUI(new Runnable() {
                @Override
                public void run() {
                    DeviceService.this.listener.onCapabilitiesUpdated(DeviceService.this, arrayList2, arrayList);
                }
            });
        }
    }

    public boolean hasCapability(String capability) {
        Matcher matcher = CapabilityMethods.ANY_PATTERN.matcher(capability);
        if (matcher.find()) {
            String group = matcher.group();
            for (String str : this.mCapabilities) {
                if (str.contains(group)) {
                    return true;
                }
            }
            return false;
        }
        return this.mCapabilities.contains(capability);
    }

    public boolean hasAnyCapability(String... capabilities) {
        for (String str : capabilities) {
            if (hasCapability(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCapabilities(List<String> capabilities) {
        String[] strArr = new String[capabilities.size()];
        capabilities.toArray(strArr);
        return hasCapabilities(strArr);
    }

    public boolean hasCapabilities(String... capabilities) {
        for (String str : capabilities) {
            if (!hasCapability(str)) {
                return false;
            }
        }
        return true;
    }

    public void setServiceDescription(ServiceDescription serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public ServiceDescription getServiceDescription() {
        return this.serviceDescription;
    }

    public void setServiceConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    public ServiceConfig getServiceConfig() {
        return this.serviceConfig;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("class", getClass().getSimpleName());
            jSONObject.put("description", this.serviceDescription.toJSONObject());
            jSONObject.put("config", this.serviceConfig.toJSONObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String getServiceName() {
        return this.serviceDescription.getServiceID();
    }

    public DeviceServiceListener getListener() {
        return this.listener;
    }

    public void setListener(DeviceServiceListener listener) {
        this.listener = listener;
    }

    public void closeLaunchSession(LaunchSession launchSession, ResponseListener<Object> listener) {
        if (launchSession == null) {
            Util.postError(listener, new ServiceCommandError(0, "You must provide a valid LaunchSession", null));
            return;
        }

        DeviceService service = launchSession.getService();
        if (service == null) {
            Util.postError(listener, new ServiceCommandError(0, "There is no service attached to this launch session", null));
            return;
        }

        switch (launchSession.getSessionType()) {
            case App:
                if (service instanceof Launcher)
                    ((Launcher) service).closeApp(launchSession, listener);
                break;
            case Media:
                if (service instanceof MediaPlayer)
                    ((MediaPlayer) service).closeMedia(launchSession, listener);
                break;
            case ExternalInputPicker:
                if (service instanceof ExternalInputControl)
                    ((ExternalInputControl) service).closeInputPicker(launchSession, listener);
                break;
            case WebApp:
                if (service instanceof WebAppLauncher)
                    ((WebAppLauncher) service).closeWebApp(launchSession, listener);
                break;
            case Unknown:
            default:
                Util.postError(listener, new ServiceCommandError(0, "This DeviceService does not know ho to close this LaunchSession", null));
                break;
        }
    }

    public void addCapability(final String capability) {
        if (capability == null || capability.length() == 0 || this.mCapabilities.contains(capability)) {
            return;
        }
        this.mCapabilities.add(capability);
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                ArrayList arrayList = new ArrayList<>();
                arrayList.add(capability);
                if (DeviceService.this.listener != null) {
                    DeviceService.this.listener.onCapabilitiesUpdated(DeviceService.this, arrayList, new ArrayList());
                }
            }
        });
    }

    public void addCapabilities(final List<String> capabilities) {
        if (capabilities == null) {
            return;
        }
        for (String str : capabilities) {
            if (str != null && str.length() != 0 && !this.mCapabilities.contains(str)) {
                this.mCapabilities.add(str);
            }
        }
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                if (DeviceService.this.listener != null) {
                    DeviceService.this.listener.onCapabilitiesUpdated(DeviceService.this, capabilities, new ArrayList());
                }
            }
        });
    }

    public void addCapabilities(String... capabilities) {
        addCapabilities(Arrays.asList(capabilities));
    }

    public void removeCapability(final String capability) {
        if (capability == null) {
            return;
        }
        this.mCapabilities.remove(capability);
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                ArrayList arrayList = new ArrayList<>();
                arrayList.add(capability);
                if (DeviceService.this.listener != null) {
                    DeviceService.this.listener.onCapabilitiesUpdated(DeviceService.this, new ArrayList<>(), arrayList);
                }
            }
        });
    }

    public void removeCapabilities(final List<String> capabilities) {
        if (capabilities == null) {
            return;
        }
        for (String str : capabilities) {
            this.mCapabilities.remove(str);
        }
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                if (DeviceService.this.listener != null) {
                    DeviceService.this.listener.onCapabilitiesUpdated(DeviceService.this, new ArrayList<>(), capabilities);
                }
            }
        });
    }

    public void removeCapabilities(String... capabilities) {
        removeCapabilities(Arrays.asList(capabilities));
    }
}
