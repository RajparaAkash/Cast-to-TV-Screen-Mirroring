package com.connectsdk.discovery.provider.ssdp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;


public class SSDPDeviceDescriptionParser extends DefaultHandler {
    public static final String TAG_DEVICE_TYPE = "deviceType";
    public static final String TAG_FRIENDLY_NAME = "friendlyName";
    public static final String TAG_ICON_LIST = "iconList";
    public static final String TAG_LOCATION = "location";
    public static final String TAG_MANUFACTURER = "manufacturer";
    public static final String TAG_MANUFACTURER_URL = "manufacturerURL";
    public static final String TAG_MODEL_DESCRIPTION = "modelDescription";
    public static final String TAG_MODEL_NAME = "modelName";
    public static final String TAG_MODEL_NUMBER = "modelNumber";
    public static final String TAG_MODEL_URL = "modelURL";
    public static final String TAG_PORT = "port";
    public static final String TAG_SEC_CAPABILITY = "sec:Capability";
    public static final String TAG_SERIAL_NUMBER = "serialNumber";
    public static final String TAG_SERVICE_LIST = "serviceList";
    public static final String TAG_UDN = "UDN";
    public static final String TAG_UPC = "UPC";
    Icon currentIcon;
    Service currentService;
    String currentValue = null;
    Map<String, String> data = new HashMap();
    SSDPDevice device;

    public SSDPDeviceDescriptionParser(SSDPDevice device) {
        this.device = device;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.currentValue == null) {
            this.currentValue = new String(ch, start, length);
        } else {
            this.currentValue += new String(ch, start, length);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("icon".equals(qName)) {
            this.currentIcon = new Icon();
        } else if ("service".equals(qName)) {
            Service service = new Service();
            this.currentService = service;
            service.baseURL = this.device.baseURL;
        } else if (TAG_SEC_CAPABILITY.equals(qName)) {
            String str = null;
            String str2 = null;
            for (int i = 0; i < attributes.getLength(); i++) {
                if ("port".equals(attributes.getLocalName(i))) {
                    str = attributes.getValue(i);
                } else if (TAG_LOCATION.equals(attributes.getLocalName(i))) {
                    str2 = attributes.getValue(i);
                }
            }
            if (str == null) {
                SSDPDevice sSDPDevice = this.device;
                sSDPDevice.serviceURI = String.format("%s%s", sSDPDevice.serviceURI, str2);
            } else {
                SSDPDevice sSDPDevice2 = this.device;
                sSDPDevice2.serviceURI = String.format("%s:%s%s", sSDPDevice2.serviceURI, str, str2);
            }
        }
        this.currentValue = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (TAG_DEVICE_TYPE.equals(qName)) {
            this.device.deviceType = this.currentValue;
        } else if ("friendlyName".equals(qName)) {
            this.device.friendlyName = this.currentValue;
        } else if (TAG_MANUFACTURER.equals(qName)) {
            this.device.manufacturer = this.currentValue;
        } else if (TAG_MODEL_DESCRIPTION.equals(qName)) {
            this.device.modelDescription = this.currentValue;
        } else if ("modelName".equals(qName)) {
            this.device.modelName = this.currentValue;
        } else if ("modelNumber".equals(qName)) {
            this.device.modelNumber = this.currentValue;
        } else if (TAG_UDN.equals(qName)) {
            this.device.UDN = this.currentValue;
        } else if (Service.TAG_SERVICE_TYPE.equals(qName)) {
            this.currentService.serviceType = this.currentValue;
        } else if ("serviceId".equals(qName)) {
            this.currentService.serviceId = this.currentValue;
        } else if (Service.TAG_SCPD_URL.equals(qName)) {
            this.currentService.SCPDURL = this.currentValue;
        } else if (Service.TAG_CONTROL_URL.equals(qName)) {
            this.currentService.controlURL = this.currentValue;
        } else if (Service.TAG_EVENTSUB_URL.equals(qName)) {
            this.currentService.eventSubURL = this.currentValue;
        } else if ("service".equals(qName)) {
            this.device.serviceList.add(this.currentService);
        }
        this.data.put(qName, this.currentValue);
        this.currentValue = null;
    }
}
