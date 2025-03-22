package com.connectsdk.service.upnp;

import android.util.Xml;

import com.connectsdk.core.ImageInfo;
import com.connectsdk.core.MediaInfo;
import com.connectsdk.service.webos.lgcast.common.utils.StringUtil;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;


public class DLNAMediaInfoParser {
    private static final String ALBUM = "upnp:album";
    private static final String APOS = "&amp;apos;";
    private static final String ARTIST = "r:albumArtist";
    private static final String CREATOR = "dc:creator";
    private static final String GENRE = "upnp:genre";
    private static final String GT = "&gt;";
    private static final String LT = "&lt;";
    private static final String RADIOTITLE = "r:streamContent";
    private static final String THUMBNAIL = "upnp:albumArtURI";
    private static final String TITLE = "dc:title";

    private static String getData(String str, String data) {
        if (str.contains(toEndTag(data))) {
            return toString(str.substring(str.indexOf(toStartTag(data)) + toStartTag(data).length(), str.indexOf(toEndTag(data))));
        }
        if (str.contains(LT)) {
            return "";
        }
        XmlPullParser newPullParser = Xml.newPullParser();
        try {
            newPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            newPullParser.setInput(new StringReader(str));
            for (int nextTag = newPullParser.nextTag(); nextTag != 1; nextTag = newPullParser.next()) {
                if (nextTag == 2 && newPullParser.getName().equals(data) && newPullParser.next() == 4) {
                    return newPullParser.getText();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
        return "";
    }

    public static MediaInfo getMediaInfo(String str) {
        String url = getURL(str);
        String title = getTitle(str);
        String mimeType = getMimeType(str);
        String str2 = getArtist(str) + StringUtil.LF + getAlbum(str);
        String thumbnail = getThumbnail(str);
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(new ImageInfo(thumbnail));
        return new MediaInfo(url, mimeType, title, str2, arrayList);
    }

    public static MediaInfo getMediaInfo(String str, String baseUrl) {
        String url = getURL(str);
        String title = getTitle(str);
        String mimeType = getMimeType(str);
        String str2 = getArtist(str) + StringUtil.LF + getAlbum(str);
        String thumbnail = getThumbnail(str);
        try {
            new URL(thumbnail).openConnection().connect();
        } catch (Exception exception) {
            thumbnail = baseUrl + thumbnail;
        }
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(new ImageInfo(thumbnail));
        return new MediaInfo(url, mimeType, title, str2, arrayList);
    }

    public static String getTitle(String str) {
        return !getData(str, RADIOTITLE).equals("") ? getData(str, RADIOTITLE) : getData(str, TITLE);
    }

    public static String getArtist(String str) {
        return getData(str, CREATOR);
    }

    public static String getAlbum(String str) {
        return getData(str, ALBUM);
    }

    public static String getGenre(String str) {
        return getData(str, GENRE);
    }

    public static String getThumbnail(String str) {
        return URLDecoder.decode(getData(str, THUMBNAIL));
    }

    public static String getMimeType(String str) {
        if (str.contains("protocolInfo")) {
            int indexOf = str.indexOf("*:") + 2;
            return str.substring(indexOf, str.substring(indexOf).indexOf(":") + indexOf);
        }
        return "";
    }

    public static String getURL(String str) {
        if (str.contains(LT)) {
            return str.contains(toEndTag("res")) ? URLDecoder.decode(str.substring(str.substring(str.indexOf("&lt;res")).indexOf(GT) + str.indexOf("&lt;res") + 4, str.indexOf(toEndTag("res")))) : "";
        }
        return getData(str, "res");
    }

    private static String toStartTag(String str) {
        return LT + str + GT;
    }

    private static String toEndTag(String str) {
        return toStartTag("/" + str);
    }

    private static String toString(String text) {
        StringBuilder sb = new StringBuilder();
        if (text.contains(APOS)) {
            sb.append(text.substring(0, text.indexOf(APOS)));
            sb.append("'");
            sb.append(text.substring(text.indexOf(APOS) + 10));
            return sb.toString();
        }
        return text;
    }
}
