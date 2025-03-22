package com.jaku.parser;

import com.jaku.core.Response;
import com.jaku.model.Device;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class DeviceParser extends JakuParser {
    public Object parse(Response response) {
        Device device = new Device();
        if (!(response == null || response.getData() == null)) {
            try {
                Element rootElement = new SAXBuilder().build((Reader) new StringReader(response.getData().toString())).getRootElement();
                device.setUdn(checkValue(rootElement.getChild("udn")));
                device.setSerialNumber(checkValue(rootElement.getChild("serial-number")));
                device.setDeviceId(checkValue(rootElement.getChild("device-id")));
                device.setVendorName(checkValue(rootElement.getChild("vendor-name")));
                device.setModelNumber(checkValue(rootElement.getChild("model-number")));
                device.setModelName(checkValue(rootElement.getChild("model-name")));
                device.setWifiMac(checkValue(rootElement.getChild("wifi-mac")));
                device.setEthernetMac(checkValue(rootElement.getChild("ethernet-mac")));
                device.setNetworkType(checkValue(rootElement.getChild("network-type")));
                device.setUserDeviceName(checkValue(rootElement.getChild("user-device-name")));
                device.setSoftwareVersion(checkValue(rootElement.getChild("software-version")));
                device.setSoftwareBuild(checkValue(rootElement.getChild("software-build")));
                device.setSecureDevice(checkValue(rootElement.getChild("secure-device")));
                device.setLanguage(checkValue(rootElement.getChild("language")));
                device.setCountry(checkValue(rootElement.getChild("country")));
                device.setLocale(checkValue(rootElement.getChild("locale")));
                device.setTimeZone(checkValue(rootElement.getChild("time-zone")));
                device.setTimeZoneOffset(checkValue(rootElement.getChild("time-zone-offset")));
                device.setPowerMode(checkValue(rootElement.getChild("power-mode")));
                device.setSupportsSuspend(checkValue(rootElement.getChild("supports-suspend")));
                device.setSupportsFindRemote(checkValue(rootElement.getChild("supports-find-remote")));
                device.setSupportsAudioGuide(checkValue(rootElement.getChild("supports-audio-guide")));
                device.setDeveloperEnabled(checkValue(rootElement.getChild("developer-enabled")));
                device.setKeyedDeveloperId(checkValue(rootElement.getChild("keyed-developer-id")));
                device.setSearchEnabled(checkValue(rootElement.getChild("search-enabled")));
                device.setVoiceSearchEnabled(checkValue(rootElement.getChild("voice-search-enabled")));
                device.setNotificationsEnabled(checkValue(rootElement.getChild("notifications-enabled")));
                device.setNotificationsFirstUse(checkValue(rootElement.getChild("notifications-first-use")));
                device.setSupportsPrivateListening(checkValue(rootElement.getChild("supports-private-listening")));
                device.setHeadphonesConnected(checkValue(rootElement.getChild("headphones-connected")));
                device.setIsTv(checkValue(rootElement.getChild("is-tv")));
                device.setIsStick(checkValue(rootElement.getChild("is-stick")));
            } catch (JDOMException | IOException e) {
                e.printStackTrace();
            }
        }
        return device;
    }

    private String checkValue(Element element) {
        if (element == null) {
            return null;
        }
        return element.getValue();
    }
}
