package com.connectsdk.service.roku;

import com.connectsdk.core.AppInfo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class RokuApplicationListParser extends DefaultHandler {

    public AppInfo appInfo;
    public final String APP = "app";

    public final String ID = "id";
    public String value = null;
    public List<AppInfo> appList = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, final Attributes attributes) throws SAXException {
        int index;
        if (!qName.equalsIgnoreCase("app") || (index = attributes.getIndex("id")) == -1) {
            return;
        }
        this.appInfo = new AppInfo() {
            {
                setId(attributes.getValue(index));
            }
        };
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("app")) {
            this.appInfo.setName(this.value);
            this.appList.add(this.appInfo);
        }
        this.value = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.value = new String(ch, start, length);
    }

    public List<AppInfo> getApplicationList() {
        return this.appList;
    }
}
