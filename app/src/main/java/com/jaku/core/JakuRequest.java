package com.jaku.core;

import com.jaku.parser.JakuParser;

import java.io.IOException;

public class JakuRequest {
    private JakuParser jakuParser;
    private JakuRequestData jakuRequestData;

    public JakuRequest(JakuRequestData jakuRequestData2, JakuParser jakuParser2) {
        this.jakuRequestData = jakuRequestData2;
        this.jakuParser = jakuParser2;
    }

    public JakuResponse send() throws IOException {
        Request request;
        String endpointUrl = this.jakuRequestData.getEndpointUrl();
        JakuParser jakuParser2 = this.jakuParser;
        if (this.jakuRequestData.getMethod().equalsIgnoreCase("GET")) {
            request = new GETRequest(endpointUrl);
        } else if (this.jakuRequestData.getMethod().equalsIgnoreCase("POST")) {
            request = new POSTRequest(endpointUrl, "");
        } else {
            request = this.jakuRequestData.getMethod().equalsIgnoreCase("DISCOVERY") ? new DiscoveryRequest(endpointUrl) : null;
        }
        Response send = request.send();
        System.out.println("Request response: " + send.getData());
        return new JakuResponse(generateResponseData(send, jakuParser2));
    }

    private Object generateResponseData(Response response, JakuParser jakuParser2) {
        if (jakuParser2 == null) {
            return null;
        }
        return jakuParser2.parse(response);
    }
}
