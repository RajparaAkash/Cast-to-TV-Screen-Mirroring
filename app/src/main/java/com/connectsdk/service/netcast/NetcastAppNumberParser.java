package com.connectsdk.service.netcast;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class NetcastAppNumberParser extends DefaultHandler {
    int count;
    public final String TYPE = "type";
    public final String NUMBER = "number";
    public String value = null;

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (!qName.equalsIgnoreCase("type") && qName.equalsIgnoreCase("number")) {
            this.count = Integer.parseInt(this.value);
        }
        this.value = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.value = new String(ch, start, length);
    }

    public int getApplicationNumber() {
        return this.count;
    }
}
