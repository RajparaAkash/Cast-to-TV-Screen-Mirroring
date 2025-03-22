package com.connectsdk.service.airplay.auth;

import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AuthUtils {
    static boolean isRetransmission = false;

    public static byte[] concatByteArrays(byte[]... byteArrays) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (byte[] bArr : byteArrays) {
            byteArrayOutputStream.write(bArr);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] createPList(Map<String, ? extends Object> properties) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        NSDictionary nSDictionary = new NSDictionary();
        for (Map.Entry<String, ? extends Object> entry : properties.entrySet()) {
            nSDictionary.put(entry.getKey(), entry.getValue());
        }
        PropertyListParser.saveAsBinary(nSDictionary, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static synchronized String putData(Socket socket, String path, String contentType, byte[] data) throws IOException {
        String readLine;
        String byteArrayOutputStream;
        int read;
        synchronized (AuthUtils.class) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeBytes("PUT " + path + " HTTP/1.0\r\n");
            dataOutputStream.writeBytes("User-Agent: ConnectSDK MediaControl/1.0\r\n");
            dataOutputStream.writeBytes("Connection: keep-alive\r\n");
            if (data != null) {
                dataOutputStream.writeBytes("Content-Length: " + data.length + "\r\n");
            } else {
                dataOutputStream.writeBytes("Content-Length: 0\r\n");
            }
            dataOutputStream.writeBytes("\r\n");
            if (data != null) {
                dataOutputStream.write(data);
            }
            dataOutputStream.flush();
            Pattern compile = Pattern.compile("HTTP[^ ]+ (\\d{3})");
            Pattern compile2 = Pattern.compile("Content-Length: (\\d+)");
            int i = 0;
            int i2 = 0;
            do {
                readLine = readLine(socket.getInputStream());
                if (readLine == null) {
                    break;
                }
                Matcher matcher = compile.matcher(readLine);
                if (matcher.find()) {
                    i = Integer.parseInt(matcher.group(1));
                }
                Matcher matcher2 = compile2.matcher(readLine);
                if (matcher2.find()) {
                    i2 = Integer.parseInt(matcher2.group(1));
                }
            } while (!readLine.trim().isEmpty());
            if (i != 200) {
                throw new IOException("Invalid status code " + i);
            }
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            byte[] bArr = new byte[65535];
            while (byteArrayOutputStream2.size() < i2 && (read = socket.getInputStream().read(bArr)) != -1) {
                byteArrayOutputStream2.write(bArr, 0, read);
            }
            byteArrayOutputStream2.flush();
            byteArrayOutputStream = byteArrayOutputStream2.toString("UTF-8");
            byteArrayOutputStream2.close();
        }
        return byteArrayOutputStream;
    }

    public static synchronized String getData(Socket socket, String path) throws IOException {
        String readLine;
        String byteArrayOutputStream;
        int read;
        synchronized (AuthUtils.class) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeBytes("GET " + path + " HTTP/1.0\r\n");
            dataOutputStream.writeBytes("User-Agent: ConnectSDK MediaControl/1.0\r\n");
            dataOutputStream.writeBytes("Connection: keep-alive\r\n");
            dataOutputStream.writeBytes("Content-Length: 0\r\n");
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.flush();
            Pattern compile = Pattern.compile("HTTP[^ ]+ (\\d{3})");
            Pattern compile2 = Pattern.compile("Content-Length: (\\d+)");
            int i = 0;
            int i2 = 0;
            do {
                readLine = readLine(socket.getInputStream());
                if (readLine == null) {
                    break;
                }
                Matcher matcher = compile.matcher(readLine);
                if (matcher.find()) {
                    i = Integer.parseInt(matcher.group(1));
                }
                Matcher matcher2 = compile2.matcher(readLine);
                if (matcher2.find()) {
                    i2 = Integer.parseInt(matcher2.group(1));
                }
            } while (!readLine.trim().isEmpty());
            if (i != 200) {
                throw new IOException("Invalid status code " + i);
            }
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            byte[] bArr = new byte[65535];
            while (byteArrayOutputStream2.size() < i2 && (read = socket.getInputStream().read(bArr)) != -1) {
                byteArrayOutputStream2.write(bArr, 0, read);
            }
            byteArrayOutputStream2.flush();
            byteArrayOutputStream = byteArrayOutputStream2.toString("UTF-8");
            byteArrayOutputStream2.close();
        }
        return byteArrayOutputStream;
    }

    public static synchronized byte[] postData(Socket socket, String path, String contentType, byte[] data) throws IOException {
        String readLine;
        byte[] byteArray;
        int read;
        synchronized (AuthUtils.class) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeBytes("POST " + path + " HTTP/1.0\r\n");
            dataOutputStream.writeBytes("User-Agent: ConnectSDK MediaControl/1.0\r\n");
            dataOutputStream.writeBytes("Connection: keep-alive\r\n");
            if (data != null) {
                dataOutputStream.writeBytes("Content-Length: " + data.length + "\r\n");
                dataOutputStream.writeBytes("Content-Type: " + contentType + "\r\n");
            }
            dataOutputStream.writeBytes("\r\n");
            if (data != null) {
                dataOutputStream.write(data);
            }
            dataOutputStream.flush();
            Pattern compile = Pattern.compile("HTTP[^ ]+ (\\d{3})");
            Pattern compile2 = Pattern.compile("Content-Length: (\\d+)");
            int i = 0;
            int i2 = 0;
            do {
                readLine = readLine(socket.getInputStream());
                if (readLine == null) {
                    break;
                }
                Matcher matcher = compile.matcher(readLine);
                if (matcher.find()) {
                    i = Integer.parseInt(matcher.group(1));
                }
                Matcher matcher2 = compile2.matcher(readLine);
                if (matcher2.find()) {
                    i2 = Integer.parseInt(matcher2.group(1));
                }
            } while (!readLine.trim().isEmpty());
            if (i != 200) {
                if (i == 500 && path.compareTo("/play") == 0 && !isRetransmission) {
                    isRetransmission = true;
                    postData(socket, path, contentType, data);
                } else {
                    throw new IOException("Invalid status code " + i);
                }
            }
            isRetransmission = false;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[65535];
            while (byteArrayOutputStream.size() < i2 && (read = socket.getInputStream().read(bArr)) != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
            byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        }
        return byteArray;
    }

    private static String readLine(InputStream inputStream) throws IOException {
        int read;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            read = inputStream.read();
            if (read == 10 || read == -1) {
                break;
            }
            byteArrayOutputStream.write(read);
        }
        if (read == -1 && byteArrayOutputStream.size() == 0) {
            return null;
        }
        return byteArrayOutputStream.toString("UTF-8");
    }

    public static String randomString(final int length) {
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(charArray[random.nextInt(charArray.length)]);
        }
        return sb.toString();
    }
}
