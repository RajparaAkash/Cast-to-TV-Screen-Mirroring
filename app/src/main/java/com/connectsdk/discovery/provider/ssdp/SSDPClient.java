package com.connectsdk.discovery.provider.ssdp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;


public class SSDPClient {
    public static final String ALIVE = "ssdp:alive";
    public static final String BYEBYE = "ssdp:byebye";
    public static final String MSEARCH = "M-SEARCH * HTTP/1.1";
    public static final String MULTICAST_ADDRESS = "239.255.255.250";

    static int MX = 5;
    public static final String NEWLINE = "\r\n";
    public static final String NOTIFY = "NOTIFY * HTTP/1.1";

    public static final String OK = "HTTP/1.1 200 OK";
    public static final int PORT = 1900;
    public static final String UPDATE = "ssdp:update";
    DatagramSocket datagramSocket;
    InetAddress localInAddress;
    SocketAddress multicastGroup;
    MulticastSocket multicastSocket;
    NetworkInterface networkInterface;
    int timeout;

    public SSDPClient(InetAddress source) throws IOException {
        this(source, new MulticastSocket(1900), new DatagramSocket((SocketAddress) null));
    }

    public SSDPClient(InetAddress source, MulticastSocket mcSocket, DatagramSocket dgSocket) throws IOException {
        this.timeout = 0;
        this.localInAddress = source;
        this.multicastSocket = mcSocket;
        this.datagramSocket = dgSocket;
        this.multicastGroup = new InetSocketAddress(MULTICAST_ADDRESS, 1900);
        NetworkInterface byInetAddress = NetworkInterface.getByInetAddress(this.localInAddress);
        this.networkInterface = byInetAddress;
        this.multicastSocket.joinGroup(this.multicastGroup, byInetAddress);
        this.datagramSocket.setReuseAddress(true);
        this.datagramSocket.bind(new InetSocketAddress(this.localInAddress, 0));
    }

    public void send(String data) throws IOException {
        this.datagramSocket.send(new DatagramPacket(data.getBytes(), data.length(), this.multicastGroup));
    }

    public DatagramPacket responseReceive() throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
        this.datagramSocket.receive(datagramPacket);
        return datagramPacket;
    }

    public DatagramPacket multicastReceive() throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
        this.multicastSocket.receive(datagramPacket);
        return datagramPacket;
    }

    public boolean isConnected() {
        DatagramSocket datagramSocket = this.datagramSocket;
        return datagramSocket != null && this.multicastSocket != null && datagramSocket.isConnected() && this.multicastSocket.isConnected();
    }

    public void close() {
        MulticastSocket multicastSocket = this.multicastSocket;
        if (multicastSocket != null) {
            try {
                multicastSocket.leaveGroup(this.multicastGroup, this.networkInterface);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.multicastSocket.close();
        }
        DatagramSocket datagramSocket = this.datagramSocket;
        if (datagramSocket != null) {
            datagramSocket.close();
        }
    }

    public void setTimeout(int timeout) throws SocketException {
        this.timeout = timeout;
        this.datagramSocket.setSoTimeout(timeout);
    }

    public static String getSSDPSearchMessage(String ST) {
        StringBuilder sb = new StringBuilder();
        sb.append("M-SEARCH * HTTP/1.1\r\n");
        sb.append("HOST: 239.255.255.250:1900\r\n");
        sb.append("MAN: \"ssdp:discover\"\r\n");
        sb.append("ST: ").append(ST).append("\r\n");
        sb.append("MX: ").append(MX).append("\r\n");
        if (ST.contains("udap")) {
            sb.append("USER-AGENT: UDAP/2.0\r\n");
        }
        sb.append("\r\n");
        return sb.toString();
    }
}
