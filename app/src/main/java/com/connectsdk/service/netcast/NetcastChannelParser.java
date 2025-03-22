package com.connectsdk.service.netcast;

import com.connectsdk.core.ChannelInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class NetcastChannelParser extends DefaultHandler {
    public JSONObject channel;
    public final String CHANNEL_TYPE = "chtype";
    public final String MAJOR = "major";
    public final String MINOR = "minor";
    public final String DISPLAY_MAJOR = "displayMajor";
    public final String DISPLAY_MINOR = "displayMinor";
    public final String SOURCE_INDEX = "sourceIndex";
    public final String PHYSICAL_NUM = "physicalNum";
    public final String CHANNEL_NAME = "chname";
    public final String PROGRAM_NAME = "progName";
    public final String AUDIO_CHANNEL = "audioCh";
    public final String INPUT_SOURCE_NAME = "inputSourceName";
    public final String INPUT_SOURCE_TYPE = "inputSourceType";
    public final String LABEL_NAME = "labelName";
    public final String INPUT_SOURCE_INDEX = "inputSourceIdx";
    public JSONArray channelArray = new JSONArray();
    public String value = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("data")) {
            this.channel = new JSONObject();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            if (qName.equalsIgnoreCase("data")) {
                this.channelArray.put(this.channel);
            } else if (qName.equalsIgnoreCase("chtype")) {
                this.channel.put("channelModeName", this.value);
            } else if (qName.equalsIgnoreCase("major")) {
                this.channel.put("majorNumber", Integer.parseInt(this.value));
            } else if (qName.equalsIgnoreCase("displayMajor")) {
                this.channel.put("displayMajorNumber", Integer.parseInt(this.value));
            } else if (qName.equalsIgnoreCase("minor")) {
                this.channel.put("minorNumber", Integer.parseInt(this.value));
            } else if (qName.equalsIgnoreCase("displayMinor")) {
                this.channel.put("displayMinorNumber", Integer.parseInt(this.value));
            } else if (qName.equalsIgnoreCase("sourceIndex")) {
                this.channel.put("sourceIndex", this.value);
            } else if (qName.equalsIgnoreCase("physicalNum")) {
                this.channel.put("physicalNumber", Integer.parseInt(this.value));
            } else if (qName.equalsIgnoreCase("chname")) {
                this.channel.put("channelName", this.value);
            } else if (qName.equalsIgnoreCase("progName")) {
                this.channel.put("programName", this.value);
            } else if (qName.equalsIgnoreCase("audioCh")) {
                this.channel.put("audioCh", this.value);
            } else if (qName.equalsIgnoreCase("inputSourceName")) {
                this.channel.put("inputSourceName", this.value);
            } else if (qName.equalsIgnoreCase("inputSourceType")) {
                this.channel.put("inputSourceType", this.value);
            } else if (qName.equalsIgnoreCase("labelName")) {
                this.channel.put("labelName", this.value);
            } else if (qName.equalsIgnoreCase("inputSourceIdx")) {
                this.channel.put("inputSourceIndex", this.value);
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

    public JSONArray getJSONChannelArray() {
        return this.channelArray;
    }

    public static ChannelInfo parseRawChannelData(JSONObject channelRawData) {
        String format;
        ChannelInfo channelInfo = new ChannelInfo();
        channelInfo.setRawData(channelRawData);
        try {
            String str = !channelRawData.isNull("channelName") ? (String) channelRawData.get("channelName") : null;
            String str2 = channelRawData.isNull("channelId") ? null : (String) channelRawData.get("channelId");
            int intValue = !channelRawData.isNull("majorNumber") ? ((Integer) channelRawData.get("majorNumber")).intValue() : 0;
            int intValue2 = !channelRawData.isNull("minorNumber") ? ((Integer) channelRawData.get("minorNumber")).intValue() : 0;
            if (!channelRawData.isNull("channelNumber")) {
                format = (String) channelRawData.get("channelNumber");
            } else {
                format = String.format(String.valueOf(intValue) + "-" + String.valueOf(intValue2), new Object[0]);
            }
            channelInfo.setName(str);
            channelInfo.setId(str2);
            channelInfo.setNumber(format);
            channelInfo.setMajorNumber(intValue);
            channelInfo.setMinorNumber(intValue2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return channelInfo;
    }
}
