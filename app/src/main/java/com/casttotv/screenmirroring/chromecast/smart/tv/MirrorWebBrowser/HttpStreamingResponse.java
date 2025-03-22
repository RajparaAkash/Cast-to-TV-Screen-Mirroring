package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class HttpStreamingResponse extends HttpResponse {
    public HttpStreamingResponse() {
        super(HttpStatus.OK);
        addHeader(HttpResponse.CONTENT_TYPE, "multipart/x-mixed-replace; boundary=myboundary");
        addHeader(HttpResponse.CACHE_CONTROL, "no-cache");
        addHeader(HttpResponse.PRAGMA, "no-cache");
        addHeader(HttpResponse.CONNECTION, "close");
    }

    public void streamImage(OutputStream outputStream, byte[] bArr) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write("--myboundary\r\n");
        outputStreamWriter.write(toHeaderEntry(HttpResponse.CONTENT_TYPE, "image/jpeg"));
        outputStreamWriter.write(toHeaderEntry(HttpResponse.CONTENT_LENGTH, String.valueOf(bArr.length)));
        outputStreamWriter.write(HttpResponse.CRLF);
        outputStreamWriter.flush();
        outputStream.write(bArr);
        outputStreamWriter.write(HttpResponse.CRLF);
        outputStreamWriter.flush();
    }
}
