package com.connectsdk.discovery.provider;

import android.content.Context;
import android.util.Log;

import com.connectsdk.core.Util;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.discovery.DiscoveryProvider;
import com.connectsdk.discovery.DiscoveryProviderListener;
import com.connectsdk.discovery.provider.ssdp.SSDPClient;
import com.connectsdk.discovery.provider.ssdp.SSDPDevice;
import com.connectsdk.discovery.provider.ssdp.SSDPPacket;
import com.connectsdk.service.config.ServiceDescription;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

public class SSDPDiscoveryProvider implements DiscoveryProvider {

    Context context;
    private Thread notifyThread;
    private Thread responseThread;
    private Timer scanTimer;
    private SSDPClient ssdpClient;
    boolean needToStartSearch = false;
    ConcurrentHashMap<String, ServiceDescription> foundServices = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, ServiceDescription> discoveredServices = new ConcurrentHashMap<>();
    boolean isRunning = false;
    private Runnable mResponseHandler = new Runnable() {
        @Override
        public void run() {
            while (SSDPDiscoveryProvider.this.ssdpClient != null) {
                try {
                    SSDPDiscoveryProvider.this.handleSSDPPacket(new SSDPPacket(SSDPDiscoveryProvider.this.ssdpClient.responseReceive()));
                } catch (IOException | RuntimeException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    };
    private Runnable mRespNotifyHandler = new Runnable() {
        @Override 
        public void run() {
            while (SSDPDiscoveryProvider.this.ssdpClient != null) {
                try {
                    SSDPDiscoveryProvider.this.handleSSDPPacket(new SSDPPacket(SSDPDiscoveryProvider.this.ssdpClient.multicastReceive()));
                } catch (IOException | RuntimeException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    };
    private Pattern uuidReg = Pattern.compile("(?<=uuid:)(.+?)(?=(::)|$)");
    private CopyOnWriteArrayList<DiscoveryProviderListener> serviceListeners = new CopyOnWriteArrayList<>();
    List<DiscoveryFilter> serviceFilters = new CopyOnWriteArrayList();

    public boolean containsServicesWithFilter(SSDPDevice device, String filter) {
        return true;
    }

    public SSDPDiscoveryProvider(Context context) {
        this.context = context;
    }

    private void openSocket() {
        SSDPClient sSDPClient = this.ssdpClient;
        if (sSDPClient == null || !sSDPClient.isConnected()) {
            try {
                InetAddress ipAddress = Util.getIpAddress(this.context);
                if (ipAddress == null) {
                    return;
                }
                this.ssdpClient = createSocket(ipAddress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    protected SSDPClient createSocket(InetAddress source) throws IOException {
        return new SSDPClient(source);
    }

    @Override 
    public void start() {
        if (this.isRunning) {
            return;
        }
        this.isRunning = true;
        openSocket();
        Timer timer = new Timer();
        this.scanTimer = timer;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SSDPDiscoveryProvider.this.sendSearch();
            }
        }, 100L, 10000L);
        this.responseThread = new Thread(this.mResponseHandler);
        this.notifyThread = new Thread(this.mRespNotifyHandler);
        this.responseThread.start();
        this.notifyThread.start();
    }

    public void sendSearch() {
        ArrayList<String> arrayList = new ArrayList<>();
        long time = new Date().getTime() - 60000;
        for (String str : this.foundServices.keySet()) {
            ServiceDescription serviceDescription = this.foundServices.get(str);
            if (serviceDescription == null || serviceDescription.getLastDetection() < time) {
                arrayList.add(str);
            }
        }
        for (String str2 : arrayList) {
            ServiceDescription serviceDescription2 = this.foundServices.get(str2);
            if (serviceDescription2 != null) {
                notifyListenersOfLostService(serviceDescription2);
            }
            if (this.foundServices.containsKey(str2)) {
                this.foundServices.remove(str2);
            }
        }
        rescan();
    }

    @Override 
    public void stop() {
        this.isRunning = false;
        Timer timer = this.scanTimer;
        if (timer != null) {
            timer.cancel();
            this.scanTimer = null;
        }
        Thread thread = this.responseThread;
        if (thread != null) {
            thread.interrupt();
            this.responseThread = null;
        }
        Thread thread2 = this.notifyThread;
        if (thread2 != null) {
            thread2.interrupt();
            this.notifyThread = null;
        }
        SSDPClient sSDPClient = this.ssdpClient;
        if (sSDPClient != null) {
            sSDPClient.close();
            this.ssdpClient = null;
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
        this.discoveredServices.clear();
    }

    @Override
    public void rescan() {
        for (DiscoveryFilter discoveryFilter : this.serviceFilters) {
            final String sSDPSearchMessage = SSDPClient.getSSDPSearchMessage(discoveryFilter.getServiceFilter());
            Timer timer = new Timer();
            for (int i = 0; i < 3; i++) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            if (SSDPDiscoveryProvider.this.ssdpClient != null) {
                                SSDPDiscoveryProvider.this.ssdpClient.send(sSDPSearchMessage);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, i * 1000);
            }
        }
    }

    @Override 
    public void addDeviceFilter(DiscoveryFilter filter) {
        if (filter.getServiceFilter() == null) {
            Log.e(Util.T, "This device filter does not have ssdp filter info");
        } else {
            this.serviceFilters.add(filter);
        }
    }

    @Override 
    public void removeDeviceFilter(DiscoveryFilter filter) {
        this.serviceFilters.remove(filter);
    }

    @Override 
    public void setFilters(List<DiscoveryFilter> filters) {
        this.serviceFilters = filters;
    }

    @Override 
    public boolean isEmpty() {
        return this.serviceFilters.isEmpty();
    }

    
    public void handleSSDPPacket(SSDPPacket ssdpPacket) {
        String str;
        if (ssdpPacket == null || ssdpPacket.getData().isEmpty() || ssdpPacket.getType() == null) {
            return;
        }
        String str2 = ssdpPacket.getData().get(ssdpPacket.getType().equals(SSDPClient.NOTIFY) ? "NT" : "ST");
        if (str2 == null || SSDPClient.MSEARCH.equals(ssdpPacket.getType()) || !isSearchingForFilter(str2) || (str = ssdpPacket.getData().get("USN")) == null || str.length() == 0) {
            return;
        }
        Matcher matcher = this.uuidReg.matcher(str);
        if (matcher.find()) {
            String group = matcher.group();
            if (SSDPClient.BYEBYE.equals(ssdpPacket.getData().get("NTS"))) {
                ServiceDescription serviceDescription = this.foundServices.get(group);
                if (serviceDescription != null) {
                    this.foundServices.remove(group);
                    notifyListenersOfLostService(serviceDescription);
                    return;
                }
                return;
            }
            String str3 = ssdpPacket.getData().get("LOCATION");
            if (str3 == null || str3.isEmpty()) {
                return;
            }
            ServiceDescription serviceDescription2 = this.foundServices.get(group);
            if (serviceDescription2 == null && this.discoveredServices.get(group) == null) {
                serviceDescription2 = new ServiceDescription();
                serviceDescription2.setUUID(group);
                serviceDescription2.setServiceFilter(str2);
                serviceDescription2.setIpAddress(ssdpPacket.getDatagramPacket().getAddress().getHostAddress());
                serviceDescription2.setPort(3001);
                this.discoveredServices.put(group, serviceDescription2);
                getLocationData(str3, group, str2);
            }
            if (serviceDescription2 != null) {
                serviceDescription2.setLastDetection(new Date().getTime());
            }
        }
    }

    public void getLocationData(final String location, final String uuid, final String serviceFilter) {
        try {
            getLocationData(new URL(location), uuid, serviceFilter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getLocationData(final URL location, final String uuid, final String serviceFilter) {
        Util.runInBackground(new Runnable() {
            @Override
            public void run() {
                SSDPDevice device = null;
                try {
                    device = new SSDPDevice(location, serviceFilter);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

                if (device != null) {
                    device.UUID = uuid;
                    boolean hasServices = containsServicesWithFilter(device, serviceFilter);

                    if (hasServices) {
                        final ServiceDescription service = discoveredServices.get(uuid);

                        if (service != null) {
                            service.setServiceFilter(serviceFilter);
                            service.setFriendlyName(device.friendlyName);
                            service.setModelName(device.modelName);
                            service.setModelNumber(device.modelNumber);
                            service.setModelDescription(device.modelDescription);
                            service.setManufacturer(device.manufacturer);
                            service.setApplicationURL(device.applicationURL);
                            service.setServiceList(device.serviceList);
                            service.setResponseHeaders(device.headers);
                            service.setLocationXML(device.locationXML);
                            service.setServiceURI(device.serviceURI);
                            service.setPort(device.port);

                            foundServices.put(uuid, service);

                            notifyListenersOfNewService(service);
                        }
                    }
                }

                discoveredServices.remove(uuid);
            }
            }, true);
    }

    private void notifyListenersOfNewService(ServiceDescription service) {
        List<String> serviceIds = serviceIdsForFilter(service.getServiceFilter());

        for (String serviceId : serviceIds) {
            ServiceDescription _newService = service.clone();
            _newService.setServiceID(serviceId);

            final ServiceDescription newService = _newService;

            Util.runOnUI(new Runnable() {

                @Override
                public void run() {

                    for (DiscoveryProviderListener listener : serviceListeners) {
                        listener.onServiceAdded(SSDPDiscoveryProvider.this, newService);
                    }
                }
            });
        }
    }

    private void notifyListenersOfLostService(ServiceDescription service) {
        List<String> serviceIds = serviceIdsForFilter(service.getServiceFilter());

        for (String serviceId : serviceIds) {
            ServiceDescription _newService = service.clone();
            _newService.setServiceID(serviceId);

            final ServiceDescription newService = _newService;

            Util.runOnUI(new Runnable() {

                @Override
                public void run() {
                    for (DiscoveryProviderListener listener : serviceListeners) {
                        listener.onServiceRemoved(SSDPDiscoveryProvider.this, newService);
                    }
                }
            });
        }
    }

    public List<String> serviceIdsForFilter(String filter) {
        String serviceId;
        ArrayList arrayList = new ArrayList<>();
        for (DiscoveryFilter discoveryFilter : this.serviceFilters) {
            if (discoveryFilter.getServiceFilter().equals(filter) && (serviceId = discoveryFilter.getServiceId()) != null) {
                arrayList.add(serviceId);
            }
        }
        return arrayList;
    }

    public boolean isSearchingForFilter(String filter) {
        for (DiscoveryFilter discoveryFilter : this.serviceFilters) {
            if (discoveryFilter.getServiceFilter().equals(filter)) {
                return true;
            }
        }
        return false;
    }

    @Override 
    public void addListener(DiscoveryProviderListener listener) {
        this.serviceListeners.add(listener);
    }

    @Override 
    public void removeListener(DiscoveryProviderListener listener) {
        this.serviceListeners.remove(listener);
    }
}
