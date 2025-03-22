package com.connectsdk.discovery.provider.ssdp;

import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;


public class SSDPDevice {

    public String ST;
    public String UDN;
    public String UUID;
    public String applicationURL;
    public String baseURL;
    public String deviceType;
    public String friendlyName;
    public Map<String, List<String>> headers;
    public String ipAddress;
    public String locationXML;
    public String manufacturer;
    public String modelDescription;
    public String modelName;
    public String modelNumber;
    public int port;
    public List<Service> serviceList;
    public String serviceURI;

    public SSDPDevice(String url, String ST) throws IOException, ParserConfigurationException, SAXException {
        this(new URL(url), ST);
    }

    public SSDPDevice(URL urlObject, String ST) throws IOException, ParserConfigurationException, SAXException {
        this.serviceList = new ArrayList<>();
        if (urlObject.getPort() == -1) {
            this.baseURL = String.format("%s://%s", urlObject.getProtocol(), urlObject.getHost());
        } else {
            this.baseURL = String.format("%s://%s:%d", urlObject.getProtocol(), urlObject.getHost(), Integer.valueOf(urlObject.getPort()));
        }
        this.ipAddress = urlObject.getHost();
        this.port = urlObject.getPort();
        this.UUID = null;
        this.serviceURI = String.format("%s://%s", urlObject.getProtocol(), urlObject.getHost());
        parse(urlObject);
    }

    public void parse(URL url) throws IOException, ParserConfigurationException, SAXException {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        SSDPDeviceDescriptionParser sSDPDeviceDescriptionParser = new SSDPDeviceDescriptionParser(this);
        URLConnection openConnection = url.openConnection();
        String headerField = openConnection.getHeaderField("Application-URL");
        this.applicationURL = headerField;
        if (headerField != null && !headerField.substring(headerField.length() - 1).equals("/")) {
            this.applicationURL = this.applicationURL.concat("/");
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
        Scanner scanner = null;
        try {
            scanner = new Scanner(bufferedInputStream).useDelimiter("\\A");
            this.locationXML = scanner.hasNext() ? scanner.next() : "";
            newInstance.newSAXParser().parse(new ByteArrayInputStream(this.locationXML.getBytes()), sSDPDeviceDescriptionParser);
            this.headers = openConnection.getHeaderFields();
        } finally {
            bufferedInputStream.close();
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public String toString() {
        return this.friendlyName;
    }
}
