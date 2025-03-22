package com.connectsdk.discovery.provider;

import android.content.Context;

import com.amazon.whisperplay.fling.media.controller.DiscoveryController;
import com.amazon.whisperplay.fling.media.controller.RemoteMediaPlayer;
import com.connectsdk.core.Util;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.discovery.DiscoveryProvider;
import com.connectsdk.discovery.DiscoveryProviderListener;
import com.connectsdk.service.FireTVService;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.config.ServiceDescription;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class FireTVDiscoveryProvider implements DiscoveryProvider {
    private DiscoveryController discoveryController;
    DiscoveryController.IDiscoveryListener fireTVListener;
    ConcurrentHashMap<String, ServiceDescription> foundServices;
    private boolean isRunning;
    CopyOnWriteArrayList<DiscoveryProviderListener> serviceListeners;

    @Override 
    public void addDeviceFilter(DiscoveryFilter filter) {
    }

    @Override 
    public void removeDeviceFilter(DiscoveryFilter filter) {
    }

    @Override 
    public void setFilters(List<DiscoveryFilter> filters) {
    }

    public FireTVDiscoveryProvider(Context context) {
        this(new DiscoveryController(context));
    }

    public FireTVDiscoveryProvider(DiscoveryController discoveryController) {
        this.foundServices = new ConcurrentHashMap<>();
        this.serviceListeners = new CopyOnWriteArrayList<>();
        this.discoveryController = discoveryController;
        this.fireTVListener = new FireTVDiscoveryListener();
    }

    @Override 
    public void start() {
        if (this.isRunning) {
            return;
        }
        this.discoveryController.start(this.fireTVListener);
        this.isRunning = true;
    }

    @Override 
    public void stop() {
        try {
            if (this.isRunning) {
                this.discoveryController.stop();
                this.isRunning = false;
            }
            for (ServiceDescription serviceDescription : this.foundServices.values()) {
                notifyListenersThatServiceLost(serviceDescription);
            }
            this.foundServices.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override 
    public void restart() {
        stop();
        start();
    }

    @Override 
    public void rescan() {
        restart();
    }

    @Override 
    public void reset() {
        this.foundServices.clear();
        stop();
    }

    @Override 
    public void addListener(DiscoveryProviderListener listener) {
        this.serviceListeners.add(listener);
    }

    @Override 
    public void removeListener(DiscoveryProviderListener listener) {
        this.serviceListeners.remove(listener);
    }

    @Override 
    public boolean isEmpty() {
        return this.foundServices.isEmpty();
    }


    public void notifyListenersThatServiceAdded(final ServiceDescription serviceDescription) {
        Util.runOnUI(new Runnable() {
            @Override 
            public void run() {
                Iterator<DiscoveryProviderListener> it = FireTVDiscoveryProvider.this.serviceListeners.iterator();
                while (it.hasNext()) {
                    it.next().onServiceAdded(FireTVDiscoveryProvider.this, serviceDescription);
                }
            }
        });
    }

    
    public void notifyListenersThatServiceLost(final ServiceDescription serviceDescription) {
        Util.runOnUI(new Runnable() {
            @Override 
            public void run() {
                Iterator<DiscoveryProviderListener> it = FireTVDiscoveryProvider.this.serviceListeners.iterator();
                while (it.hasNext()) {
                    it.next().onServiceRemoved(FireTVDiscoveryProvider.this, serviceDescription);
                }
            }
        });
    }
    
    public void notifyListenersThatDiscoveryFailed(final ServiceCommandError error) {
        Util.runOnUI(new Runnable() {
            @Override 
            public void run() {
                Iterator<DiscoveryProviderListener> it = FireTVDiscoveryProvider.this.serviceListeners.iterator();
                while (it.hasNext()) {
                    it.next().onServiceDiscoveryFailed(FireTVDiscoveryProvider.this, error);
                }
            }
        });
    }

    class FireTVDiscoveryListener implements DiscoveryController.IDiscoveryListener {
        FireTVDiscoveryListener() {
        }

        @Override
        public void playerDiscovered(RemoteMediaPlayer remoteMediaPlayer) {
            if (remoteMediaPlayer == null) {
                return;
            }
            String uniqueIdentifier = remoteMediaPlayer.getUniqueIdentifier();
            ServiceDescription serviceDescription = FireTVDiscoveryProvider.this.foundServices.get(uniqueIdentifier);
            if (serviceDescription == null) {
                ServiceDescription serviceDescription2 = new ServiceDescription();
                updateServiceDescription(serviceDescription2, remoteMediaPlayer);
                FireTVDiscoveryProvider.this.foundServices.put(uniqueIdentifier, serviceDescription2);
                FireTVDiscoveryProvider.this.notifyListenersThatServiceAdded(serviceDescription2);
                return;
            }
            updateServiceDescription(serviceDescription, remoteMediaPlayer);
        }

        @Override
        public void playerLost(RemoteMediaPlayer remoteMediaPlayer) {
            ServiceDescription serviceDescription;
            if (remoteMediaPlayer == null || (serviceDescription = FireTVDiscoveryProvider.this.foundServices.get(remoteMediaPlayer.getUniqueIdentifier())) == null) {
                return;
            }
            FireTVDiscoveryProvider.this.notifyListenersThatServiceLost(serviceDescription);
            FireTVDiscoveryProvider.this.foundServices.remove(remoteMediaPlayer.getUniqueIdentifier());
        }

        @Override
        public void discoveryFailure() {
            FireTVDiscoveryProvider.this.notifyListenersThatDiscoveryFailed(new ServiceCommandError("FireTV discovery failure"));
        }

        private void updateServiceDescription(ServiceDescription serviceDescription, RemoteMediaPlayer remoteMediaPlayer) {
            String uniqueIdentifier = remoteMediaPlayer.getUniqueIdentifier();
            serviceDescription.setDevice(remoteMediaPlayer);
            serviceDescription.setFriendlyName(remoteMediaPlayer.getName());
            serviceDescription.setIpAddress(uniqueIdentifier);
            serviceDescription.setServiceID(FireTVService.ID);
            serviceDescription.setUUID(uniqueIdentifier);
        }
    }
}
