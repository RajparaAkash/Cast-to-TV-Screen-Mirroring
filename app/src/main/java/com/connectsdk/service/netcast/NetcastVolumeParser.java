package com.connectsdk.service.netcast;

import com.connectsdk.service.CastService;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NetcastVolumeParser extends DefaultHandler {
    public final String MUTE = CastService.CAST_SERVICE_MUTE_SUBSCRIPTION_NAME;
    public final String MIN_LEVEL = "minLevel";
    public final String MAX_LEVEL = "maxLevel";
    public final String LEVEL = "level";
    public JSONObject volumeStatus = new JSONObject();
    public String value = null;

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            if (qName.equalsIgnoreCase(CastService.CAST_SERVICE_MUTE_SUBSCRIPTION_NAME)) {
                this.volumeStatus.put(CastService.CAST_SERVICE_MUTE_SUBSCRIPTION_NAME, Boolean.parseBoolean(this.value));
            } else if (qName.equalsIgnoreCase("minLevel")) {
                this.volumeStatus.put("minLevel", Integer.parseInt(this.value));
            } else if (qName.equalsIgnoreCase("maxLevel")) {
                this.volumeStatus.put("maxLevel", Integer.parseInt(this.value));
            } else if (qName.equalsIgnoreCase("level")) {
                this.volumeStatus.put("level", Integer.parseInt(this.value));
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

    public JSONObject getVolumeStatus() {
        return this.volumeStatus;
    }
}
