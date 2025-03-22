package com.jaku.core;

import com.jaku.api.QueryRequests;
import com.jaku.model.Device;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryRequest extends Request {
    public DiscoveryRequest(String str) {
        super(str, (String) null);
    }

    public Response send() throws IOException {
        ArrayList<Device> arrayList = new ArrayList<>();
        for (String next : scanForAllRokus(new URL(this.url))) {
            Device queryDeviceInfo = QueryRequests.queryDeviceInfo("http://" + next + ":8060");
            queryDeviceInfo.setHost("http://" + next + ":8060");
            arrayList.add(queryDeviceInfo);
        }
        Response response = new Response();
        response.setData(arrayList);
        return response;
    }

    private String scanForRoku(URL url) throws IOException {
        byte[] bytes = ("M-SEARCH * HTTP/1.1\nHost: " + url.getHost() + ":" + url.getPort() + "\nMan: \"ssdp:discover\"\nST: roku:ecp\n").getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(url.getHost()), url.getPort());
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(datagramPacket);
        DatagramPacket datagramPacket2 = new DatagramPacket(new byte[1024], 1024);
        datagramSocket.receive(datagramPacket2);
        String str = new String(datagramPacket2.getData());
        datagramSocket.close();
        return str.toLowerCase().split("location:")[1].split("\n")[0].split("http://")[1].split(":")[0].trim();
    }

    private List<String> scanForAllRokus(URL url) throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String scanForRoku = scanForRoku(url);
            if (!arrayList.contains(scanForRoku)) {
                arrayList.add(scanForRoku);
            }
        }
        return arrayList;
    }
}
