package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import java.io.IOException;
import java.net.Socket;

public class StreamingClient {

    private final HttpStreamingResponse response = new HttpStreamingResponse();
    private final Socket socket;
    private long totalBytesSent;

    public StreamingClient(Socket socket2) {
        this.socket = socket2;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void sendHeader() throws IOException {
        this.response.sendHeader(this.socket.getOutputStream());
    }

    public void streamImage(byte[] bArr) throws IOException {
        this.response.streamImage(this.socket.getOutputStream(), bArr);
        this.totalBytesSent += (long) bArr.length;
    }

    public long getTotalBytesSent() {
        return this.totalBytesSent;
    }

    public String getIpAddress() {
        return this.socket.getInetAddress().getHostAddress();
    }
}
