package com.connectsdk.discovery.provider;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;

import com.connectsdk.core.Util;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.discovery.DiscoveryProvider;
import com.connectsdk.discovery.DiscoveryProviderListener;
import com.connectsdk.service.CastService;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.config.ServiceDescription;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastMediaControlIntent;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CastDiscoveryProvider implements DiscoveryProvider {

    private static final long ROUTE_REMOVE_INTERVAL = 3000;
    private MediaRouteSelector mMediaRouteSelector;
    private final MediaRouter mMediaRouter;
    private Timer removeRoutesTimer;
    private final List<String> removedUUID = new CopyOnWriteArrayList<>();
    boolean isRunning = false;
    protected MediaRouter.Callback mMediaRouterCallback = new MediaRouterCallback();
    protected ConcurrentHashMap<String, ServiceDescription> foundServices = new ConcurrentHashMap<>(8, 0.75f, 2);
    protected CopyOnWriteArrayList<DiscoveryProviderListener> serviceListeners = new CopyOnWriteArrayList<>();

    @Override
    public void addDeviceFilter(DiscoveryFilter filter) {
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void removeDeviceFilter(DiscoveryFilter filter) {
    }

    @Override
    public void setFilters(List<DiscoveryFilter> filters) {
    }

    public CastDiscoveryProvider(Context context) {
        this.mMediaRouter = createMediaRouter(context);
    }

    protected MediaRouter createMediaRouter(Context context) {
        return MediaRouter.getInstance(context);
    }

    @Override
    public void start() {
        if (this.isRunning) {
            return;
        }
        this.isRunning = true;
        if (this.mMediaRouteSelector == null) {
            try {
                this.mMediaRouteSelector = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(CastService.getApplicationID())).build();
            } catch (IllegalArgumentException exception) {
                Log.w(Util.T, "Invalid application ID: " + CastService.getApplicationID());
                Iterator<DiscoveryProviderListener> it = this.serviceListeners.iterator();
                while (it.hasNext()) {
                    it.next().onServiceDiscoveryFailed(this, new ServiceCommandError(0, "Invalid application ID: " + CastService.getApplicationID(), null));
                }
                return;
            }
        }
        rescan();
    }

    @Override
    public void stop() {
        this.isRunning = false;
        Timer timer = this.removeRoutesTimer;
        if (timer != null) {
            timer.cancel();
            this.removeRoutesTimer = null;
        }
        if (this.mMediaRouter != null) {
            Util.runOnUI(new Runnable() {
                @Override
                public void run() {
                    CastDiscoveryProvider.this.mMediaRouter.removeCallback(CastDiscoveryProvider.this.mMediaRouterCallback);
                }
            });
        }
    }

    @Override
    public void restart() {
        stop();
        start();
    }

    @Override
    public void reset() {
        stop();
        this.foundServices.clear();
    }

    @Override
    public void rescan() {
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                mMediaRouter.addCallback(mMediaRouteSelector, mMediaRouterCallback, MediaRouter.CALLBACK_FLAG_REQUEST_DISCOVERY);
            }
        });
    }

    @Override
    public void addListener(DiscoveryProviderListener listener) {
        this.serviceListeners.add(listener);
    }

    @Override
    public void removeListener(DiscoveryProviderListener listener) {
        this.serviceListeners.remove(listener);
    }

    private class MediaRouterCallback extends MediaRouter.Callback {
        private MediaRouterCallback() {
        }

        @Override
        public void onRouteAdded(@NonNull MediaRouter router, @NonNull MediaRouter.RouteInfo route) {

            super.onRouteAdded(router, route);
            try {
                CastDevice fromBundle = CastDevice.getFromBundle(route.getExtras());
                String deviceId = fromBundle.getDeviceId();
                CastDiscoveryProvider.this.removedUUID.remove(deviceId);
                ServiceDescription serviceDescription = CastDiscoveryProvider.this.foundServices.get(deviceId);
                boolean z = false;
                boolean z2 = true;
                if (serviceDescription == null) {
                    serviceDescription = new ServiceDescription(CastService.ID, deviceId, fromBundle.getIpAddress().getHostAddress());
                    serviceDescription.setFriendlyName(fromBundle.getFriendlyName());
                    serviceDescription.setModelName(fromBundle.getModelName());
                    serviceDescription.setModelNumber(fromBundle.getDeviceVersion());
                    serviceDescription.setModelDescription(route.getDescription());
                    serviceDescription.setPort(fromBundle.getServicePort());
                    serviceDescription.setServiceID(CastService.ID);
                    serviceDescription.setDevice(fromBundle);
                } else {
                    if (!serviceDescription.getFriendlyName().equals(fromBundle.getFriendlyName())) {
                        serviceDescription.setFriendlyName(fromBundle.getFriendlyName());
                        z = true;
                    }
                    serviceDescription.setDevice(fromBundle);
                    z2 = z;
                }
                serviceDescription.setLastDetection(new Date().getTime());
                CastDiscoveryProvider.this.foundServices.put(deviceId, serviceDescription);
                if (z2) {
                    Iterator<DiscoveryProviderListener> it = CastDiscoveryProvider.this.serviceListeners.iterator();
                    while (it.hasNext()) {
                        it.next().onServiceAdded(CastDiscoveryProvider.this, serviceDescription);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // Change Method
        public void onRouteChanged(@NonNull MediaRouter mediaRouter, @NonNull MediaRouter.RouteInfo routeInfo) {
            boolean z;
            super.onRouteChanged(mediaRouter, routeInfo);
            CastDevice fromBundle = CastDevice.getFromBundle(routeInfo.getExtras());
            String deviceId = fromBundle.getDeviceId();
            ServiceDescription serviceDescription = CastDiscoveryProvider.this.foundServices.get(deviceId);
            boolean z2 = true;
            boolean z3 = false;
            boolean z4 = serviceDescription == null;
            if (z4) {
                if (fromBundle.getIpAddress() == null || fromBundle.getIpAddress().getHostAddress() == null) {
                    Log.e("CastDiscoveryProvider", "IP Address is null for device: " + fromBundle.getFriendlyName());
                    return;
                }
                serviceDescription = new ServiceDescription(CastService.ID, deviceId, Objects.requireNonNull(fromBundle.getIpAddress()).getHostAddress());
                serviceDescription.setFriendlyName(fromBundle.getFriendlyName());
                serviceDescription.setServiceID(CastService.ID);
                z = true;
            } else {
                boolean z5 = z4;
                z = false;
                z3 = z5;
            }
            if (z3) {
                return;
            }
            serviceDescription.setIpAddress(Objects.requireNonNull(fromBundle.getIpAddress()).getHostAddress());
            serviceDescription.setModelName(fromBundle.getModelName());
            serviceDescription.setModelNumber(fromBundle.getDeviceVersion());
            serviceDescription.setModelDescription(routeInfo.getDescription());
            serviceDescription.setPort(fromBundle.getServicePort());
            serviceDescription.setDevice(fromBundle);
            if (serviceDescription.getFriendlyName().equals(fromBundle.getFriendlyName())) {
                z2 = z;
            } else {
                serviceDescription.setFriendlyName(fromBundle.getFriendlyName());
            }
            serviceDescription.setLastDetection(new Date().getTime());
            CastDiscoveryProvider.this.foundServices.put(deviceId, serviceDescription);
            if (z2) {
                Iterator<DiscoveryProviderListener> it = CastDiscoveryProvider.this.serviceListeners.iterator();
                while (it.hasNext()) {
                    it.next().onServiceAdded(CastDiscoveryProvider.this, serviceDescription);
                }
            }
        }

        @Override
        public void onRoutePresentationDisplayChanged(@NonNull MediaRouter router, MediaRouter.RouteInfo route) {
            Log.d(Util.T, "onRoutePresentationDisplayChanged: [" + route.getName() + "] [" + route.getDescription() + "]");
            super.onRoutePresentationDisplayChanged(router, route);
        }

        @Override
        public void onRouteRemoved(@NonNull final MediaRouter router, @NonNull final MediaRouter.RouteInfo route) {
            super.onRouteRemoved(router, route);
            CastDiscoveryProvider.this.removedUUID.add(CastDevice.getFromBundle(route.getExtras()).getDeviceId());
            if (CastDiscoveryProvider.this.removeRoutesTimer == null) {
                CastDiscoveryProvider.this.removeRoutesTimer = new Timer();
                CastDiscoveryProvider.this.removeRoutesTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        MediaRouterCallback.this.removeServices(route);
                    }
                }, ROUTE_REMOVE_INTERVAL);
            }
        }

        @Override
        public void onRouteVolumeChanged(@NonNull MediaRouter router, MediaRouter.RouteInfo route) {
            Log.d(Util.T, "onRouteVolumeChanged: [" + route.getName() + "] [" + route.getDescription() + "]");
            super.onRouteVolumeChanged(router, route);
        }

        public void removeServices(MediaRouter.RouteInfo route) {
            for (String str : CastDiscoveryProvider.this.removedUUID) {
                final ServiceDescription serviceDescription = CastDiscoveryProvider.this.foundServices.get(str);
                if (serviceDescription != null) {
                    Log.d(Util.T, "Service [" + route.getName() + "] has been removed");
                    Util.runOnUI(new Runnable() {
                        @Override
                        public void run() {
                            Iterator<DiscoveryProviderListener> it = CastDiscoveryProvider.this.serviceListeners.iterator();
                            while (it.hasNext()) {
                                it.next().onServiceRemoved(CastDiscoveryProvider.this, serviceDescription);
                            }
                        }
                    });
                    CastDiscoveryProvider.this.foundServices.remove(str);
                }
            }
            CastDiscoveryProvider.this.removedUUID.clear();
        }
    }
}
