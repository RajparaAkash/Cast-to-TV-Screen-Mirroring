package com.connectsdk.etc.helper;

import com.connectsdk.service.webos.lgcast.common.utils.StringUtil;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public abstract class HttpConnection {


    public enum Method {
        GET,
        POST,
        PUT,
        DELETE,
        SUBSCRIBE,
        UNSUBSCRIBE
    }

    public abstract void execute() throws IOException;

    public abstract int getResponseCode() throws IOException;

    public abstract String getResponseHeader(String name);

    public abstract String getResponseString() throws IOException;

    public abstract void setHeader(String name, String value);

    public abstract void setMethod(Method method) throws ProtocolException;

    public abstract void setPayload(String payload);

    public abstract void setPayload(byte[] payload);

    public static HttpConnection newInstance(URI uri) throws IOException {
        return new HttpURLConnectionClient(uri);
    }

    public static HttpConnection newSubscriptionInstance(URI uri) throws IOException {
        return new CustomConnectionClient(uri);
    }


    private static class HttpURLConnectionClient extends HttpConnection {
        private final HttpURLConnection connection;
        private byte[] payload;
        private String response;
        private int responseCode;

        private HttpURLConnectionClient(URI uri) throws IOException {
            this.connection = (HttpURLConnection) uri.toURL().openConnection();
        }

        @Override
        public void setMethod(Method method) throws ProtocolException {
            this.connection.setRequestMethod(method.name());
        }

        @Override
        public int getResponseCode() throws IOException {
            return this.responseCode;
        }

        @Override
        public String getResponseString() throws IOException {
            return this.response;
        }

        @Override
        public void execute() throws IOException {
            try {
                if (this.payload != null) {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.connection.getOutputStream());
                    bufferedOutputStream.write(this.payload);
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                }
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                        sb.append("\r\n");
                    }
                    bufferedReader.close();
                    this.response = sb.toString();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                this.responseCode = this.connection.getResponseCode();
            } finally {
                this.connection.disconnect();
            }
        }

        @Override
        public void setPayload(String payload) {
            this.payload = payload.getBytes();
            this.connection.setDoOutput(true);
        }

        @Override
        public void setPayload(byte[] payload) {
            this.payload = payload;
            this.connection.setDoOutput(true);
        }

        @Override
        public void setHeader(String name, String value) {
            this.connection.setRequestProperty(name, value);
        }

        @Override
        public String getResponseHeader(String name) {
            return this.connection.getHeaderField(name);
        }
    }


    private static class CustomConnectionClient extends HttpConnection {
        private int code;
        private Map<String, String> headers;
        private Method method;
        private String payload;
        private String response;
        private Map<String, String> responseHeaders;
        private final URI uri;

        private CustomConnectionClient(URI uri) {
            this.headers = new LinkedHashMap();
            this.responseHeaders = new HashMap();
            this.uri = uri;
        }

        @Override
        public void setMethod(Method method) throws ProtocolException {
            this.method = method;
        }

        @Override
        public int getResponseCode() throws IOException {
            return this.code;
        }

        @Override
        public String getResponseString() throws IOException {
            return this.response;
        }

        @Override
        public void execute() throws IOException {
            int port = this.uri.getPort() > 0 ? this.uri.getPort() : 80;
            Socket socket = new Socket(this.uri.getHost(), port);
            PrintWriter printWriter = new PrintWriter((Writer) new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter.print(this.method.name());
            printWriter.print(StringUtil.SPACE);
            printWriter.print(this.uri.getPath());
            printWriter.print(this.uri.getQuery().isEmpty() ? "" : "?" + this.uri.getQuery());
            printWriter.print(" HTTP/1.1\r\n");
            printWriter.print("Host:");
            printWriter.print(this.uri.getHost());
            printWriter.print(":");
            printWriter.print(port);
            printWriter.print("\r\n");
            for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                printWriter.print(entry.getKey());
                printWriter.print(":");
                printWriter.print(entry.getValue());
                printWriter.print("\r\n");
            }
            printWriter.print("\r\n");
            String str = this.payload;
            if (str != null) {
                printWriter.print(str);
            }
            printWriter.flush();
            StringBuilder sb = new StringBuilder();
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                String[] split = readLine.split(StringUtil.SPACE);
                if (split.length > 2) {
                    this.code = Integer.parseInt(split[1]);
                }
            }
            while (true) {
                String readLine2 = bufferedReader.readLine();
                if (readLine2 == null || readLine2.isEmpty()) {
                    break;
                }
                String[] split2 = readLine2.split(":", 2);
                if (split2 != null && split2.length == 2) {
                    this.responseHeaders.put(split2[0].trim(), split2[1].trim());
                }
            }
            while (true) {
                String readLine3 = bufferedReader.readLine();
                if (readLine3 != null) {
                    sb.append(readLine3);
                    sb.append("\r\n");
                } else {
                    this.response = sb.toString();
                    socket.close();
                    return;
                }
            }
        }

        @Override
        public void setPayload(String payload) {
            this.payload = payload;
        }

        @Override
        public void setPayload(byte[] payload) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setHeader(String name, String value) {
            if (name == null || value == null) {
                return;
            }
            this.headers.put(name.trim(), value.trim());
        }

        @Override
        public String getResponseHeader(String name) {
            return this.responseHeaders.get(name);
        }
    }
}
