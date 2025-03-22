package com.connectsdk.service.upnp;

import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;


public class DLNAEventParser {

    private static final String ns = null;

    public JSONObject parse(InputStream in) throws XmlPullParserException, IOException, JSONException {
        try {
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            newPullParser.setInput(in, null);
            newPullParser.nextTag();
            return readEvent(newPullParser);
        } finally {
            in.close();
        }
    }

    private JSONObject readEvent(XmlPullParser parser) throws IOException, XmlPullParserException, JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        parser.require(2, ns, "Event");
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                String name = parser.getName();
                if (name.equals("InstanceID")) {
                    jSONArray.put(readInstanceID(parser));
                } else if (name.equals("QueueID")) {
                    jSONArray2.put(readQueueID(parser));
                } else {
                    skip(parser);
                }
            }
        }
        if (jSONArray.length() > 0) {
            jSONObject.put("InstanceID", jSONArray);
        }
        if (jSONArray2.length() > 0) {
            jSONObject.put("QueueID", jSONArray2);
        }
        return jSONObject;
    }

    private JSONArray readInstanceID(XmlPullParser parser) throws IOException, XmlPullParserException, JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        parser.require(2, ns, "InstanceID");
        jSONObject.put("value", parser.getAttributeValue(null, "val"));
        jSONArray.put(jSONObject);
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                jSONArray.put(readEntry(parser.getName(), parser));
            }
        }
        return jSONArray;
    }

    private JSONArray readQueueID(XmlPullParser parser) throws IOException, XmlPullParserException, JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        parser.require(2, ns, "QueueID");
        jSONObject.put("value", parser.getAttributeValue(null, "val"));
        jSONArray.put(jSONObject);
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                jSONArray.put(readEntry(parser.getName(), parser));
            }
        }
        return jSONArray;
    }

    private JSONObject readEntry(String target, XmlPullParser parser) throws IOException, XmlPullParserException, JSONException {
        String str = ns;
        parser.require(2, str, target);
        String attributeValue = parser.getAttributeValue(null, "val");
        String attributeValue2 = parser.getAttributeValue(null, "channel");
        parser.nextTag();
        parser.require(3, str, target);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(target, attributeValue);
        if (attributeValue2 != null) {
            jSONObject.put("channel", attributeValue2);
        }
        return jSONObject;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int i = 1;
        while (i != 0) {
            int next = parser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }
}
