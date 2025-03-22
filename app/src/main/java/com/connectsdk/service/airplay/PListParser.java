package com.connectsdk.service.airplay;

import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;


public class PListParser {
    public static final String TAG_ARRAY = "array";
    public static final String TAG_DATA = "data";
    public static final String TAG_DATE = "date";
    public static final String TAG_DICT = "dict";
    public static final String TAG_FALSE = "false";
    public static final String TAG_INTEGER = "integer";
    public static final String TAG_KEY = "key";
    public static final String TAG_PLIST = "plist";
    public static final String TAG_REAL = "real";
    public static final String TAG_STRING = "string";
    public static final String TAG_TRUE = "true";
    private static final String ns = null;

    public JSONObject parse(String text) throws XmlPullParserException, IOException, JSONException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        newPullParser.setInput(new StringReader(text));
        newPullParser.nextTag();
        return readPlist(newPullParser);
    }

    public JSONObject parse(InputStream in) throws XmlPullParserException, IOException, JSONException {
        try {
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            newPullParser.setInput(in, null);
            newPullParser.nextTag();
            return readPlist(newPullParser);
        } finally {
            in.close();
        }
    }

    private JSONObject readPlist(XmlPullParser parser) throws XmlPullParserException, IOException, JSONException {
        parser.require(2, ns, TAG_PLIST);
        JSONObject jSONObject = null;
        while (parser.next() != 3) {
            if (parser.getEventType() == 2 && parser.getName().equals(TAG_DICT)) {
                jSONObject = readDict(parser);
            }
        }
        return jSONObject;
    }

    public JSONObject readDict(XmlPullParser parser) throws IOException, XmlPullParserException, JSONException {
        JSONObject jSONObject = new JSONObject();
        parser.require(2, ns, TAG_DICT);
        while (true) {
            String str = null;
            while (parser.next() != 3) {
                if (parser.getEventType() == 2) {
                    String name = parser.getName();
                    if (name.equals("key")) {
                        str = readKey(parser);
                    } else if (str != null) {
                        if (name.equals("data")) {
                            jSONObject.put(str, readData(parser));
                        } else if (name.equals(TAG_INTEGER)) {
                            jSONObject.put(str, readInteger(parser));
                        } else if (name.equals("string")) {
                            jSONObject.put(str, readString(parser));
                        } else if (name.equals(TAG_DATE)) {
                            jSONObject.put(str, readDate(parser));
                        } else if (name.equals(TAG_REAL)) {
                            jSONObject.put(str, readReal(parser));
                        } else if (name.equals(TAG_ARRAY)) {
                            jSONObject.put(str, readArray(parser));
                        } else if (name.equals(TAG_DICT)) {
                            jSONObject.put(str, readDict(parser));
                        } else if (name.equals(TAG_TRUE) || name.equals(TAG_FALSE)) {
                            jSONObject.put(str, Boolean.valueOf(name));
                            skip(parser);
                        }
                    }
                }
            }
            return jSONObject;
        }
    }

    private JSONArray readArray(XmlPullParser parser) throws IOException, XmlPullParserException, JSONException {
        JSONArray jSONArray = new JSONArray();
        parser.require(2, ns, TAG_ARRAY);
        while (parser.next() != 3) {
            if (parser.getEventType() == 2 && parser.getName().equals(TAG_DICT)) {
                jSONArray.put(readDict(parser));
            }
        }
        return jSONArray;
    }

    private String readKey(XmlPullParser parser) throws IOException, XmlPullParserException {
        String str = ns;
        parser.require(2, str, "key");
        String readText = readText(parser);
        parser.require(3, str, "key");
        return readText;
    }

    private String readData(XmlPullParser parser) throws IOException, XmlPullParserException {
        String str = ns;
        parser.require(2, str, "data");
        String readText = readText(parser);
        parser.require(3, str, "data");
        return readText;
    }

    private int readInteger(XmlPullParser parser) throws IOException, XmlPullParserException {
        String str = ns;
        parser.require(2, str, TAG_INTEGER);
        int intValue = Integer.valueOf(readText(parser)).intValue();
        parser.require(3, str, TAG_INTEGER);
        return intValue;
    }

    private double readReal(XmlPullParser parser) throws IOException, XmlPullParserException {
        String str = ns;
        parser.require(2, str, TAG_REAL);
        double doubleValue = Double.valueOf(readText(parser)).doubleValue();
        parser.require(3, str, TAG_REAL);
        return doubleValue;
    }

    private String readString(XmlPullParser parser) throws IOException, XmlPullParserException {
        String str = ns;
        parser.require(2, str, "string");
        String readText = readText(parser);
        parser.require(3, str, "string");
        return readText;
    }

    private String readDate(XmlPullParser parser) throws IOException, XmlPullParserException {
        String str = ns;
        parser.require(2, str, TAG_DATE);
        String readText = readText(parser);
        parser.require(3, str, TAG_DATE);
        return readText;
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
