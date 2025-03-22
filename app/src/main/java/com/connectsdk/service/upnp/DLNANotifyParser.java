package com.connectsdk.service.upnp;

import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;


public class DLNANotifyParser {

    private static final String ns = null;

    public JSONArray parse(InputStream in) throws XmlPullParserException, IOException, JSONException {
        try {
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            newPullParser.setInput(in, null);
            newPullParser.nextTag();
            return readPropertySet(newPullParser);
        } finally {
            in.close();
        }
    }

    private JSONArray readPropertySet(XmlPullParser parser) throws IOException, XmlPullParserException, JSONException {
        JSONArray jSONArray = new JSONArray();
        parser.require(2, ns, "e:propertyset");
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                if (parser.getName().equals("e:property")) {
                    jSONArray.put(readProperty(parser));
                } else {
                    skip(parser);
                }
            }
        }
        return jSONArray;
    }

    private JSONObject readProperty(XmlPullParser parser) throws IOException, XmlPullParserException, JSONException {
        JSONObject jSONObject = new JSONObject();
        parser.require(2, ns, "e:property");
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                String name = parser.getName();
                if (name.equals("LastChange")) {
                    ByteArrayInputStream byteArrayInputStream = null;
                    try {
                        byteArrayInputStream = new ByteArrayInputStream(readText(parser).getBytes("UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    jSONObject.put("LastChange", new DLNAEventParser().parse(byteArrayInputStream));
                } else {
                    jSONObject = readPropertyData(name, parser);
                }
            }
        }
        return jSONObject;
    }

    private JSONObject readPropertyData(String target, XmlPullParser parser) throws IOException, XmlPullParserException, JSONException {
        JSONObject jSONObject = new JSONObject();
        String str = ns;
        parser.require(2, str, target);
        jSONObject.put(target, readText(parser));
        parser.require(3, str, target);
        return jSONObject;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        if (parser.next() == 4) {
            String text = parser.getText();
            parser.nextTag();
            return text;
        }
        return "";
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
