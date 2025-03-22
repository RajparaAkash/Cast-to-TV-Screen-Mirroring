package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpResponse {

    public static final String AUTHENTICATE = "WWW-Authenticate";
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONNECTION = "Connection";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CRLF = "\r\n";
    public static final String PRAGMA = "Pragma";
    public static final String SERVER = "Server";

    private final Map<String, String> headers;
    private final HttpStatus statusCode;

    public HttpResponse(HttpStatus httpStatus) {
        this.statusCode = httpStatus;
        this.headers = new HashMap<>();
        addHeader(SERVER, "LiveScreen");
    }

    public HttpResponse() {
        this(HttpStatus.OK);
    }

    public String getStatusLine() {
        return "HTTP/1.0 " + statusCode.getStatusCode() + " " + statusCode.getName() + CRLF;
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public List<String> getHeaders() {
        List<String> headerList = new ArrayList<>(headers.size());
        headers.forEach((key, value) -> headerList.add(toHeaderEntry(key, value)));
        return headerList;
    }

    public String toHeaderEntry(String name, String value) {
        return name + ": " + value + CRLF;
    }

    public void sendHeader(OutputStream outputStream) throws IOException {
        sendHeader(new OutputStreamWriter(outputStream));
    }

    public void sendHeader(OutputStreamWriter outputStreamWriter) throws IOException {
        outputStreamWriter.write(getStatusLine());
        for (String str : getHeaders()) {
            outputStreamWriter.write(str);
        }
        outputStreamWriter.write(CRLF);
        outputStreamWriter.flush();
    }
}
