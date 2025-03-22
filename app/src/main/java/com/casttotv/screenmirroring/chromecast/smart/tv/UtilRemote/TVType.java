package com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote;

import com.amazon.whisperlink.impl.ServiceEndpointImpl;
import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.service.NewAndroidService;
import com.connectsdk.service.config.ServiceDescription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TVType {
    public static String AndroidTV = "AndroidTV";
    public static String ChromeCastTV = "ChromeCastTV";
    public static String FireTV = "FireTV";
    public static String LG = "LG";
    public static String RokuTV = "RokuTV";
    public static String SamsungTV = "SamsungTV";
    public static String SonyTV = "SonyTV";
    public static String TclTV = "TclTV";
    public static int TypeFireTV = 4;
    public static int TypeLG = 2;
    public static int TypeRoku = 1;
    public static int TypeSamsung = 3;
    public static int TypeSony = 5;

    public static boolean isFireTV(ConnectableDevice connectableDevice) {
        return connectableDevice != null && getTVType(connectableDevice).equals(FireTV);
    }

    public static boolean isLGTV(ConnectableDevice connectableDevice) {
        return connectableDevice != null && getTVType(connectableDevice).equals(LG);
    }

    public static boolean checkService(ConnectableDevice connectableDevice, String str) {
        if (connectableDevice == null) {
            return false;
        }
        try {
            List arrayList = new ArrayList();
            if (str.contains(ServiceEndpointImpl.SEPARATOR)) {
                arrayList = Arrays.asList(str.split(ServiceEndpointImpl.SEPARATOR));
            } else {
                arrayList.add(str);
            }
            String lowerCase = connectableDevice.getConnectedServiceNames() != null ? connectableDevice.getConnectedServiceNames().toLowerCase(Locale.getDefault()) : "";
            ServiceDescription serviceDescription = connectableDevice.getServiceDescription();
            if (serviceDescription != null) {
                String manufacturer = serviceDescription.getManufacturer();
                String modelName = serviceDescription.getModelName();
                String serviceId = connectableDevice.getServiceId();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (lowerCase.contains((CharSequence) arrayList.get(i)) || manufacturer.toLowerCase().contains((CharSequence) arrayList.get(i)) || modelName.toLowerCase().contains((CharSequence) arrayList.get(i)) || serviceId.toLowerCase().contains((CharSequence) arrayList.get(i))) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkName(ConnectableDevice connectableDevice, String str) {
        if (connectableDevice == null) {
            return false;
        }
        List arrayList = new ArrayList();
        if (str.contains(ServiceEndpointImpl.SEPARATOR)) {
            arrayList = Arrays.asList(str.split(ServiceEndpointImpl.SEPARATOR));
        } else {
            arrayList.add(str);
        }

        String lowerCase = "";
        if (connectableDevice != null && connectableDevice.getConnectedServiceNames() != null) {
            if (connectableDevice.getFriendlyName() != null) {
                lowerCase = connectableDevice.getFriendlyName().toLowerCase(Locale.getDefault());
                for (int i = 0; i < arrayList.size(); i++) {
                    if (lowerCase.contains((CharSequence) arrayList.get(i))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkModelName(ConnectableDevice connectableDevice, String str) {
        if (connectableDevice == null) {
            return false;
        }
        List arrayList = new ArrayList();
        if (str.contains(ServiceEndpointImpl.SEPARATOR)) {
            arrayList = Arrays.asList(str.split(ServiceEndpointImpl.SEPARATOR));
        } else {
            arrayList.add(str);
        }
        String lowerCase = connectableDevice.getModelName() != null ? connectableDevice.getModelName().toLowerCase(Locale.getDefault()) : "";
        for (int i = 0; i < arrayList.size(); i++) {
            if (lowerCase.contains((CharSequence) arrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isChromecastTV(ConnectableDevice connectableDevice) {
        String connectedServiceNames;
        if (connectableDevice == null || (connectedServiceNames = connectableDevice.getConnectedServiceNames()) == null) {
            return false;
        }
        return connectedServiceNames.toLowerCase().contains(NameDevice.CHROMECAST);
    }

    public static boolean isAndroidTV(ConnectableDevice connectableDevice) {
        String connectedServiceNames;
        if (connectableDevice == null || (connectedServiceNames = connectableDevice.getConnectedServiceNames()) == null) {
            return false;
        }
        return connectedServiceNames.toLowerCase().contains("androidtv");
    }

    public static boolean isNewAndroidTV(ConnectableDevice connectableDevice) {
        String connectedServiceNames;
        if (connectableDevice == null || (connectedServiceNames = connectableDevice.getConnectedServiceNames()) == null) {
            return false;
        }
        if (connectedServiceNames.contains(NewAndroidService.ID)) {
            return true;
        }
        return connectedServiceNames.toLowerCase().contains("androidtv2");
    }

    public static boolean isRokuTV(ConnectableDevice connectableDevice) {
        return connectableDevice != null && getTVType(connectableDevice).equals(RokuTV);
    }

    public static boolean isSamsungTV(ConnectableDevice connectableDevice) {
        return connectableDevice != null && getTVType(connectableDevice).equals(SamsungTV);
    }

    public static boolean isSonyTV(ConnectableDevice connectableDevice) {
        return getTVType(connectableDevice).equals(SonyTV);
    }

    public static String getTVType(ConnectableDevice connectableDevice) {
        if (connectableDevice == null) {
            return "Other";
        }
        if (checkService(connectableDevice, NameDevice.ROKU)) {
            return RokuTV;
        }
        if (checkService(connectableDevice, NameDevice.LG)) {
            return LG;
        }
        if (checkService(connectableDevice, NameDevice.SAMSUNG)) {
            return SamsungTV;
        }
        if (checkService(connectableDevice, NameDevice.SONY)) {
            return SonyTV;
        }
        if (checkService(connectableDevice, NameDevice.CHROMECAST)) {
            return ChromeCastTV;
        }
        if (checkService(connectableDevice, NameDevice.TCL)) {
            return TclTV;
        }
        if (checkService(connectableDevice, NameDevice.FIRETV)) {
            return FireTV;
        }
        if (checkName(connectableDevice, NameDevice.ROKU)) {
            return RokuTV;
        }
        if (checkName(connectableDevice, NameDevice.LG)) {
            return LG;
        }
        if (checkName(connectableDevice, NameDevice.SAMSUNG)) {
            return SamsungTV;
        }
        if (checkName(connectableDevice, NameDevice.SONY)) {
            return SonyTV;
        }
        if (checkName(connectableDevice, NameDevice.CHROMECAST)) {
            return ChromeCastTV;
        }
        if (checkName(connectableDevice, NameDevice.TCL)) {
            return TclTV;
        }
        if (checkName(connectableDevice, NameDevice.FIRETV)) {
            return FireTV;
        }
        if (checkModelName(connectableDevice, NameDevice.SONY)) {
            return SonyTV;
        }
        if (connectableDevice.getModelName() == null || connectableDevice.getFriendlyName() == null || (!connectableDevice.getModelName().toLowerCase().contains("kdl") && !connectableDevice.getFriendlyName().toLowerCase().contains("kdl"))) {
            return "Other";
        }
        return SonyTV;
    }

}
