package com.connectsdk.service.webos.lgcast.common.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;

import com.connectsdk.service.airplay.PListParser;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;


public class StringUtil {

    public static final String CR = "\r";
    public static final String CRLF = "\r\n";
    public static final String EMPTY = "";

    public static final String LF = "\n";
    public static final String SPACE = " ";
    public static final String TAB = "\t";

    public static String toString(boolean value) {
        return value ? PListParser.TAG_TRUE : PListParser.TAG_FALSE;
    }

    public static boolean empty(String string) {
        return string == null || string.length() == 0;
    }

    public static String format(String format, Object... args) {
        if (args != null) {
            try {
                return args.length != 0 ? String.format(format, args) : format;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return format;
    }

    public static int length(String value) {
        if (value != null) {
            return value.length();
        }
        return -1;
    }

    public static int toInteger(String value) {
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public static int toInteger(String value, int defaultValue) {
        if (value != null) {
            try {
                return value.length() != 0 ? Integer.parseInt(value) : defaultValue;
            } catch (Exception e) {
                e.printStackTrace();
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static long toLong(String value) {
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (Exception e) {
                e.printStackTrace();
                return -1L;
            }
        }
        return -1L;
    }

    public static long toLong(String value, long defaultValue) {
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (Exception e) {
                e.printStackTrace();
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static long toLong(String value, int radix, long defaultValue) {
        if (value != null) {
            try {
                return Long.parseLong(value, radix);
            } catch (Exception e) {
                e.printStackTrace();
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static byte[] toBytes(String value) {
        if (value != null) {
            return value.getBytes();
        }
        return null;
    }

    public static String toString(int value) {
        try {
            return Integer.toString(value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toString(long value) {
        try {
            return Long.toString(value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toString(Intent intent) {
        StringBuffer stringBuffer = new StringBuffer();
        String action = intent != null ? intent.getAction() : null;
        Bundle extras = intent != null ? intent.getExtras() : null;
        if (action != null) {
            stringBuffer.append("Action: " + action + LF);
        }
        if (extras != null) {
            for (String str : extras.keySet()) {
                stringBuffer.append("Data: " + str + "=" + extras.get(str) + LF);
            }
        }
        return stringBuffer.toString();
    }

    public static String toString(Bundle bundle) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                stringBuffer.append(str + "=" + bundle.get(str) + LF);
            }
        }
        return stringBuffer.toString();
    }

    public static String toString(List<?> list, String delim) {
        StringBuffer stringBuffer = new StringBuffer();
        if (list != null) {
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                stringBuffer.append((stringBuffer.length() > 0 ? delim : "") + it.next().toString());
            }
        }
        return stringBuffer.toString();
    }

    public static String toString(Object[] array, String delim) {
        StringBuffer stringBuffer = new StringBuffer();
        if (array != null) {
            int length = array.length;
            for (int i = 0; i < length; i++) {
                stringBuffer.append(array[i].toString() + delim);
            }
        }
        return stringBuffer.toString();
    }

    public static String toString(byte[] bytes) {
        int i = 0;
        if (bytes != null) {
            int length = bytes.length;
            int i2 = 0;
            while (i < length) {
                i2 += bytes[i];
                i++;
            }
            i = i2;
        }
        if (i != 0) {
            return new String(bytes);
        }
        return null;
    }

    public static String toHexString(byte[] bytes) {
        return toHexString(bytes, SPACE);
    }

    public static String toHexString(byte[] bytes, String delim) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bytes != null) {
            int length = bytes.length;
            for (int i = 0; i < length; i++) {
                stringBuffer.append((stringBuffer.length() > 0 ? delim : "") + String.format("%02x", Integer.valueOf(bytes[i] & 255)));
            }
        }
        return stringBuffer.toString();
    }

    public static String toLower(String value) {
        return toLower(value, null);
    }

    public static String toLower(String value, String defaultValue) {
        return value != null ? value.toLowerCase(Locale.US) : defaultValue;
    }

    public static String toUpperCase(String value) {
        return toUpperCase(value, null);
    }

    public static String toUpperCase(String value, String defaultValue) {
        return value != null ? value.toUpperCase(Locale.US) : defaultValue;
    }

    public static ArrayList<String> split(String message, String delim) {
        return split(message, delim, 0);
    }

    public static ArrayList<String> split(String message, String delim, int limit) {
        if (message == null || delim == null) {
            return new ArrayList<>();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        String[] split = limit > 0 ? message.split(delim, limit) : message.split(delim);
        if (split != null) {
            for (String str : split) {
                if (!empty(str.trim())) {
                    arrayList.add(str.trim());
                }
            }
        }
        return arrayList;
    }

    public static String strip(String data) {
        String trim = !empty(data) ? data.trim() : null;
        if (empty(trim)) {
            return null;
        }
        return trim;
    }

    public static String encodeURL(String url) {
        if (url != null) {
            try {
                return URLEncoder.encode(url, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static String decodeURL(String url) {
        if (url != null) {
            try {
                return URLDecoder.decode(url, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static String appendURLParameter(String url, String name, String value) {
        return (url == null || name == null) ? url : Uri.parse(url).buildUpon().appendQueryParameter(name, value).toString();
    }

    public static String encodeBase64(String message) {
        return encodeBase64(message, 0);
    }

    public static String encodeBase64(String message, int flags) {
        if (message != null) {
            try {
                return Base64.encodeToString(message.getBytes(StandardCharsets.UTF_8), flags);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static String decodeBase64(String message) {
        return decodeBase64(message, 0);
    }

    public static String decodeBase64(String message, int flags) {
        if (message != null) {
            try {
                return new String(Base64.decode(message, flags), StandardCharsets.UTF_8);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static String toXmlString(String message) {
        CharBuffer wrap = CharBuffer.wrap(message);
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < wrap.length(); i++) {
            char c = wrap.get(i);
            if (c == '\"') {
                stringBuffer.append("&quot;");
            } else if (c == '<') {
                stringBuffer.append("&lt;");
            } else if (c == '>') {
                stringBuffer.append("&gt;");
            } else if (c == '&') {
                stringBuffer.append("&amp;");
            } else if (c == '\'') {
                stringBuffer.append("&apos;");
            } else {
                stringBuffer.append(wrap.get(i));
            }
        }
        return stringBuffer.toString();
    }

    public static String toRandomCase(String value) {
        if (value == null) {
            return null;
        }
        char[] charArray = value.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = ((int) (Math.random() * 100.0d)) % 2 == 0 ? Character.toLowerCase(charArray[i]) : Character.toUpperCase(charArray[i]);
        }
        return new String(charArray);
    }

    public static String toUnicode(String string) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = string != null ? string.length() : 0;
        for (int i = 0; i < length; i++) {
            char charAt = string.charAt(i);
            if (charAt >= ' ' && charAt <= '~') {
                stringBuffer.append(charAt);
            } else {
                stringBuffer.append(format("\\u%04x", Integer.valueOf(charAt & 65535)));
            }
        }
        return stringBuffer.toString();
    }

    public static String toHumanReadableSize(long byteSize) {
        float f = (float) byteSize;
        float f2 = f / 1024.0f;
        float f3 = f / 1048576.0f;
        float f4 = f / 1.07374182E9f;
        return ((int) f4) > 0 ? String.format(Locale.US, "%.02fGB", Float.valueOf(f4)) : ((int) f3) > 0 ? String.format(Locale.US, "%.02fMB", Float.valueOf(f3)) : ((int) f2) > 0 ? String.format(Locale.US, "%.02fKB", Float.valueOf(f2)) : String.format(Locale.US, "%dB", Long.valueOf(byteSize));
    }

    public static String toHumanReadableSize2(long size) {
        float f = (float) size;
        float f2 = f / 1024.0f;
        float f3 = f / 1048576.0f;
        float f4 = f / 1.07374182E9f;
        return ((int) f4) > 0 ? String.format(Locale.US, "%.02fG", Float.valueOf(f4)) : ((int) f3) > 0 ? String.format(Locale.US, "%.02fM", Float.valueOf(f3)) : ((int) f2) > 0 ? String.format(Locale.US, "%.02fK", Float.valueOf(f2)) : String.format(Locale.US, "%d", Long.valueOf(size));
    }

    public static String after(String string, String after) {
        int indexOf = (string == null || after == null) ? -1 : string.indexOf(after);
        return indexOf != -1 ? string.substring(indexOf + after.length()) : string;
    }
}
