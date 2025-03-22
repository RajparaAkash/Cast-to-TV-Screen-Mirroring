package com.connectsdk.discovery.provider.ssdp;

import java.net.DatagramPacket;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


public class SSDPPacket {
    static final Charset ASCII_CHARSET = Charset.forName("US-ASCII");
    static final String CRLF = "\r\n";

    static final String LF = "\n";
    Map<String, String> data = new HashMap();
    DatagramPacket datagramPacket;
    String type;

    public SSDPPacket(DatagramPacket datagramPacket) {
        int i;
        String substring;
        int i2;
        this.datagramPacket = datagramPacket;
        String str = new String(datagramPacket.getData(), ASCII_CHARSET);
        int indexOf = str.indexOf("\r\n");
        if (indexOf != -1) {
            i = indexOf + 2;
        } else {
            indexOf = str.indexOf("\n");
            if (indexOf == -1) {
                return;
            }
            i = indexOf + 1;
        }
        this.type = str.substring(0, indexOf);
        while (i < str.length()) {
            int indexOf2 = str.indexOf("\r\n", i);
            if (indexOf2 != -1) {
                substring = str.substring(i, indexOf2);
                i2 = indexOf2 + 2;
            } else {
                int indexOf3 = str.indexOf("\n", i);
                if (indexOf3 == -1) {
                    return;
                }
                substring = str.substring(i, indexOf3);
                i2 = indexOf3 + 1;
            }
            int indexOf4 = substring.indexOf(58);
            if (indexOf4 != -1) {
                this.data.put(asciiUpper(substring.substring(0, indexOf4)), substring.substring(indexOf4 + 1).trim());
            }
            i = i2;
        }
    }

    private static String asciiUpper(String text) {
        char[] charArray = text.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c >= 'a' && c <= 'z') {
                c = (char) (c - ' ');
            }
            charArray[i] = c;
        }
        return new String(charArray);
    }

    public DatagramPacket getDatagramPacket() {
        return this.datagramPacket;
    }

    public Map<String, String> getData() {
        return this.data;
    }

    public String getType() {
        return this.type;
    }
}
