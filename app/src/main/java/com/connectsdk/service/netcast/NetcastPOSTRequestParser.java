package com.connectsdk.service.netcast;

import com.connectsdk.service.airplay.PListParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class NetcastPOSTRequestParser extends DefaultHandler {
    boolean textEditMode = false;
    boolean keyboardVisibleMode = false;
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
    public final String VALUE = "value";
    public final String MODE = "mode";
    public final String STATE = "state";
    public JSONObject object = new JSONObject();
    public JSONObject subObject = new JSONObject();
    public String value = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            System.out.println("XML key: " + qName + ", value: " + this.value);
            if (qName.equalsIgnoreCase("chtype")) {
                this.object.put("channelModeName", this.value);
            } else if (qName.equalsIgnoreCase("major")) {
                this.object.put("majorNumber", Integer.parseInt(this.value));
            } else if (qName.equalsIgnoreCase("displayMajor")) {
                this.object.put("displayMajorNumber", Integer.parseInt(this.value));
            } else if (qName.equalsIgnoreCase("minor")) {
                this.object.put("minorNumber", Integer.parseInt(this.value));
            } else if (qName.equalsIgnoreCase("displayMinor")) {
                this.object.put("displayMinorNumber", Integer.parseInt(this.value));
            } else if (qName.equalsIgnoreCase("sourceIndex")) {
                this.object.put("sourceIndex", this.value);
            } else if (qName.equalsIgnoreCase("physicalNum")) {
                this.object.put("physicalNumber", Integer.parseInt(this.value));
            } else if (qName.equalsIgnoreCase("chname")) {
                this.object.put("channelName", this.value);
            } else if (qName.equalsIgnoreCase("progName")) {
                this.object.put("programName", this.value);
            } else if (qName.equalsIgnoreCase("audioCh")) {
                this.object.put("audioCh", this.value);
            } else if (qName.equalsIgnoreCase("inputSourceName")) {
                this.object.put("inputSourceName", this.value);
            } else if (qName.equalsIgnoreCase("inputSourceType")) {
                this.object.put("inputSourceType", this.value);
            } else if (qName.equalsIgnoreCase("labelName")) {
                this.object.put("labelName", this.value);
            } else if (qName.equalsIgnoreCase("inputSourceIdx")) {
                this.object.put("inputSourceIndex", this.value);
            } else if (qName.equalsIgnoreCase("value")) {
                if (this.keyboardVisibleMode) {
                    if (this.value.equalsIgnoreCase(PListParser.TAG_TRUE)) {
                        this.subObject.put("focus", true);
                    } else {
                        this.subObject.put("focus", false);
                    }
                    this.object.put("currentWidget", this.subObject);
                } else {
                    this.object.put("value", this.value);
                }
            } else if (qName.equalsIgnoreCase("mode")) {
                if (this.keyboardVisibleMode) {
                    if (this.value.equalsIgnoreCase("default")) {
                        this.subObject.put("hiddenText", false);
                    } else {
                        this.subObject.put("hiddenText", true);
                    }
                    this.object.put("currentWidget", this.subObject);
                }
            } else if (!qName.equalsIgnoreCase("state")) {
                String str = this.value;
                if (str != null && str.equalsIgnoreCase("KeyboardVisible")) {
                    this.keyboardVisibleMode = true;
                    try {
                        this.subObject.put("contentType", "normal");
                        this.subObject.put("focus", false);
                        this.subObject.put("hiddenText", false);
                        this.subObject.put("predictionEnabled", false);
                        this.subObject.put("correctionEnabled", false);
                        this.subObject.put("autoCapitalization", false);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    String str2 = this.value;
                    if (str2 != null && str2.equalsIgnoreCase("TextEdited")) {
                        this.textEditMode = true;
                    }
                }
            }
            this.value = null;
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.value = new String(ch, start, length);
    }

    public JSONObject getJSONObject() {
        return this.object;
    }
}
