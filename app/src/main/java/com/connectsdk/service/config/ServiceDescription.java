package com.connectsdk.service.config;

import com.connectsdk.discovery.provider.ssdp.Service;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class ServiceDescription implements Cloneable {

    public static final String KEY_FILTER = "filter";
    public static final String KEY_FRIENDLY = "friendlyName";
    public static final String KEY_IP_ADDRESS = "ipAddress";
    public static final String KEY_MODEL_NAME = "modelName";
    public static final String KEY_MODEL_NUMBER = "modelNumber";
    public static final String KEY_PORT = "port";
    public static final String KEY_SERVICE_ID = "serviceId";
    public static final String KEY_UUID = "uuid";
    public static final String KEY_VERSION = "version";
    String UUID;
    String applicationURL;
    Object device;
    String friendlyName;
    String ipAddress;
    long lastDetection = Long.MAX_VALUE;
    String locationXML;
    String manufacturer;
    String modelDescription;
    String modelName;
    String modelNumber;
    int port;
    Map<String, List<String>> responseHeaders;
    String serviceFilter;
    String serviceID;
    List<Service> serviceList;
    String serviceURI;
    String version;

    public ServiceDescription() {
    }

    public ServiceDescription(String serviceFilter, String UUID, String ipAddress) {
        this.serviceFilter = serviceFilter;
        this.UUID = UUID;
        this.ipAddress = ipAddress;
    }

    public ServiceDescription(JSONObject json) {
        this.serviceFilter = json.optString(KEY_FILTER, null);
        this.ipAddress = json.optString(KEY_IP_ADDRESS, null);
        this.UUID = json.optString("uuid", null);
        this.friendlyName = json.optString("friendlyName", null);
        this.modelName = json.optString("modelName", null);
        this.modelNumber = json.optString("modelNumber", null);
        this.port = json.optInt("port", -1);
        this.version = json.optString("version", null);
        this.serviceID = json.optString("serviceId", null);
    }

    public static ServiceDescription getDescription(JSONObject json) {
        return new ServiceDescription(json);
    }

    public String getServiceFilter() {
        return this.serviceFilter;
    }

    public void setServiceFilter(String serviceFilter) {
        this.serviceFilter = serviceFilter;
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setUUID(String uUID) {
        this.UUID = uUID;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String getIpAddress) {
        this.ipAddress = getIpAddress;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelNumber() {
        return this.modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelDescription() {
        return this.modelDescription;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public String getApplicationURL() {
        return this.applicationURL;
    }

    public void setApplicationURL(String applicationURL) {
        this.applicationURL = applicationURL;
    }

    public List<Service> getServiceList() {
        return this.serviceList;
    }

    public long getLastDetection() {
        return this.lastDetection;
    }

    public void setLastDetection(long last) {
        this.lastDetection = last;
    }

    public String getServiceID() {
        return this.serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.responseHeaders;
    }

    public void setResponseHeaders(Map<String, List<String>> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLocationXML() {
        return this.locationXML;
    }

    public void setLocationXML(String locationXML) {
        this.locationXML = locationXML;
    }

    public String getServiceURI() {
        return this.serviceURI;
    }

    public void setServiceURI(String serviceURI) {
        this.serviceURI = serviceURI;
    }

    public Object getDevice() {
        return this.device;
    }

    public void setDevice(Object device) {
        this.device = device;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(KEY_FILTER, this.serviceFilter);
            jSONObject.putOpt(KEY_IP_ADDRESS, this.ipAddress);
            jSONObject.putOpt("uuid", this.UUID);
            jSONObject.putOpt("friendlyName", this.friendlyName);
            jSONObject.putOpt("modelName", this.modelName);
            jSONObject.putOpt("modelNumber", this.modelNumber);
            jSONObject.putOpt("port", Integer.valueOf(this.port));
            jSONObject.putOpt("version", this.version);
            jSONObject.putOpt("serviceId", this.serviceID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public ServiceDescription clone() {
        ServiceDescription service = new ServiceDescription();
        service.setPort(this.port);

        // we can ignore all these NullPointerExceptions, it's OK if those properties don't have values
        try { service.setServiceID(this.serviceID); } catch (NullPointerException ex) { }
        try { service.setIpAddress(this.ipAddress); } catch (NullPointerException ex) { }
        try { service.setUUID(this.UUID); } catch (NullPointerException ex) { }
        try { service.setVersion(this.version); } catch (NullPointerException ex) { }
        try { service.setFriendlyName(this.friendlyName); } catch (NullPointerException ex) { }
        try { service.setManufacturer(this.manufacturer); } catch (NullPointerException ex) { }
        try { service.setModelName(this.modelName); } catch (NullPointerException ex) { }
        try { service.setModelNumber(this.modelNumber); } catch (NullPointerException ex) { }
        try { service.setModelDescription(this.modelDescription); } catch (NullPointerException ex) { }
        try { service.setApplicationURL(this.applicationURL); } catch (NullPointerException ex) { }
        try { service.setLocationXML(this.locationXML); } catch (NullPointerException ex) { }
        try { service.setResponseHeaders(this.responseHeaders); } catch (NullPointerException ex) { }
        try { service.setServiceList(this.serviceList); } catch (NullPointerException ex) { }
        try { service.setServiceFilter(this.serviceFilter); } catch (NullPointerException ex) { }

        return service;
    }
}
