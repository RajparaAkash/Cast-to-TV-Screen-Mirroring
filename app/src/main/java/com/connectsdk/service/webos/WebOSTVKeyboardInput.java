package com.connectsdk.service.webos;

import com.connectsdk.core.TextInputStatusInfo;
import com.connectsdk.core.Util;
import com.connectsdk.service.WebOSTVService;
import com.connectsdk.service.capability.TextInputControl;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommand;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.URLServiceSubscription;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class WebOSTVKeyboardInput {
    static String DELETE = "DELETE";
    static String ENTER = "ENTER";
    static String KEYBOARD_INPUT = "ssap://com.webos.service.ime/registerRemoteKeyboard";
    WebOSTVService service;
    boolean canReplaceText = false;
    boolean waiting = false;
    List<String> toSend = new ArrayList<>();

    public WebOSTVKeyboardInput(WebOSTVService service) {
        this.service = service;
    }

    public void addToQueue(String input) {
        this.toSend.add(input);
        if (this.waiting) {
            return;
        }
        sendData();
    }

    public void sendEnter() {
        this.toSend.add(ENTER);
        if (this.waiting) {
            return;
        }
        sendData();
    }

    public void sendDel() {
        if (this.toSend.size() == 0) {
            this.toSend.add(DELETE);
            if (this.waiting) {
                return;
            }
            sendData();
            return;
        }
        List<String> list = this.toSend;
        list.remove(list.size() - 1);
    }


    public void sendData() {
        String str;
        this.waiting = true;
        String str2 = this.toSend.get(0);
        JSONObject jSONObject = new JSONObject();
        if (str2.equals(ENTER)) {
            this.toSend.remove(0);
            str = "ssap://com.webos.service.ime/sendEnterKey";
        } else if (str2.equals(DELETE)) {
            int i = 0;
            while (this.toSend.size() > 0 && this.toSend.get(0).equals(DELETE)) {
                this.toSend.remove(0);
                i++;
            }
            try {
                jSONObject.put("count", i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            str = "ssap://com.webos.service.ime/deleteCharacters";
        } else {
            StringBuilder sb = new StringBuilder();
            while (this.toSend.size() > 0 && !this.toSend.get(0).equals(DELETE) && !this.toSend.get(0).equals(ENTER)) {
                sb.append(this.toSend.get(0));
                this.toSend.remove(0);
            }
            try {
                jSONObject.put("text", sb.toString());
                jSONObject.put("replace", 0);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            str = "ssap://com.webos.service.ime/insertText";
        }
        new ServiceCommand(this.service, str, jSONObject, true, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                WebOSTVKeyboardInput.this.waiting = false;
                if (WebOSTVKeyboardInput.this.toSend.size() > 0) {
                    WebOSTVKeyboardInput.this.sendData();
                }
            }

            @Override
            public void onError(ServiceCommandError error) {
                WebOSTVKeyboardInput.this.waiting = false;
                if (WebOSTVKeyboardInput.this.toSend.size() > 0) {
                    WebOSTVKeyboardInput.this.sendData();
                }
            }
        }).send();
    }

    public URLServiceSubscription<TextInputControl.TextInputStatusListener> connect(final TextInputControl.TextInputStatusListener listener) {
        URLServiceSubscription<TextInputControl.TextInputStatusListener> uRLServiceSubscription = new URLServiceSubscription<>(this.service, KEYBOARD_INPUT, null, true, new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                Util.postSuccess(listener, WebOSTVKeyboardInput.this.parseRawKeyboardData((JSONObject) response));
            }

            @Override
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
        uRLServiceSubscription.send();
        return uRLServiceSubscription;
    }


    public TextInputStatusInfo parseRawKeyboardData(JSONObject rawData) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        TextInputStatusInfo textInputStatusInfo = new TextInputStatusInfo();
        textInputStatusInfo.setRawData(rawData);
        boolean z6 = false;
        String r9 = "";
        try {
            if (rawData.has("currentWidget")) {
                JSONObject jSONObject = (JSONObject) rawData.get("currentWidget");
                z5 = ((Boolean) jSONObject.get("focus")).booleanValue();
                try {
                    r9 = jSONObject.has("contentType") ? (String) jSONObject.get("contentType") : null;
                    z4 = jSONObject.has("predictionEnabled") ? ((Boolean) jSONObject.get("predictionEnabled")).booleanValue() : false;
                    try {
                        z3 = jSONObject.has("correctionEnabled") ? ((Boolean) jSONObject.get("correctionEnabled")).booleanValue() : false;
                        try {
                            z2 = jSONObject.has("autoCapitalization") ? ((Boolean) jSONObject.get("autoCapitalization")).booleanValue() : false;
                        } catch (JSONException e) {
                            z2 = false;
                        }
                    } catch (JSONException e2) {
                        z2 = false;
                        z3 = false;
                    }
                    try {
                        z = jSONObject.has("hiddenText") ? ((Boolean) jSONObject.get("hiddenText")).booleanValue() : false;
                    } catch (JSONException e3) {
                        z = false;
                        textInputStatusInfo.setFocused(z5);
                        textInputStatusInfo.setContentType(r9);
                        textInputStatusInfo.setPredictionEnabled(z4);
                        textInputStatusInfo.setCorrectionEnabled(z3);
                        textInputStatusInfo.setAutoCapitalization(z2);
                        textInputStatusInfo.setHiddenText(z);
                        textInputStatusInfo.setFocusChanged(z6);
                        return textInputStatusInfo;
                    }
                } catch (JSONException e4) {
                    z = false;
                    z2 = false;
                    z3 = false;
                    z4 = false;
                }
            } else {
                z = false;
                z2 = false;
                z3 = false;
                z4 = false;
                z5 = false;
            }
        } catch (JSONException e5) {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
        }
        try {
            if (rawData.has("focusChanged")) {
                z6 = ((Boolean) rawData.get("focusChanged")).booleanValue();
            }
        } catch (JSONException e6) {
            textInputStatusInfo.setFocused(z5);
            textInputStatusInfo.setContentType(r9);
            textInputStatusInfo.setPredictionEnabled(z4);
            textInputStatusInfo.setCorrectionEnabled(z3);
            textInputStatusInfo.setAutoCapitalization(z2);
            textInputStatusInfo.setHiddenText(z);
            textInputStatusInfo.setFocusChanged(z6);
            return textInputStatusInfo;
        }
        textInputStatusInfo.setFocused(z5);
        textInputStatusInfo.setContentType(r9);
        textInputStatusInfo.setPredictionEnabled(z4);
        textInputStatusInfo.setCorrectionEnabled(z3);
        textInputStatusInfo.setAutoCapitalization(z2);
        textInputStatusInfo.setHiddenText(z);
        textInputStatusInfo.setFocusChanged(z6);
        return textInputStatusInfo;
    }
}
