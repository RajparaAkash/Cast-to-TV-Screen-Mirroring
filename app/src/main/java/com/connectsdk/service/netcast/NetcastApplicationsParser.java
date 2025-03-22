package com.connectsdk.service.netcast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class NetcastApplicationsParser extends DefaultHandler {
    public JSONObject application;
    public final String DATA = "data";
    public final String AUID = "auid";
    public final String NAME = "name";
    public final String TYPE = "type";
    public final String CPID = "cpid";
    public final String ADULT = "adult";
    public final String ICON_NAME = "icon_name";
    public JSONArray applicationList = new JSONArray();
    public String value = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("data")) {
            this.application = new JSONObject();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            if (qName.equalsIgnoreCase("data")) {
                this.applicationList.put(this.application);
            } else if (qName.equalsIgnoreCase("auid")) {
                this.application.put("id", this.value);
            } else if (qName.equalsIgnoreCase("name")) {
                this.application.put("title", this.value);
            } else if (qName.equalsIgnoreCase("type")) {
                this.application.put("type", this.value);
            } else if (qName.equalsIgnoreCase("cpid")) {
                this.application.put("cpid", this.value);
            } else if (qName.equalsIgnoreCase("adult")) {
                this.application.put("adult", this.value);
            } else if (qName.equalsIgnoreCase("icon_name")) {
                this.application.put("icon_name", this.value);
            }
            this.value = null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.value = new String(ch, start, length);
    }

    public JSONArray getApplications() {
        return this.applicationList;
    }
}
