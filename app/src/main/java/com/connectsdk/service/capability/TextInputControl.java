package com.connectsdk.service.capability;

import com.connectsdk.core.TextInputStatusInfo;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceSubscription;


public interface TextInputControl extends CapabilityMethods {
    public static final String Any = "TextInputControl.Any";
    public static final String Send = "TextInputControl.Send";
    public static final String Send_Enter = "TextInputControl.Enter";
    public static final String Send_Delete = "TextInputControl.Delete";
    public static final String Subscribe = "TextInputControl.Subscribe";
    public static final String[] Capabilities = {Send, Send_Enter, Send_Delete, Subscribe};

    
    public interface TextInputStatusListener extends ResponseListener<TextInputStatusInfo> {
    }

    TextInputControl getTextInputControl();

    CapabilityPriorityLevel getTextInputControlCapabilityLevel();

    void sendDelete();

    void sendEnter();

    void sendText(String input);

    ServiceSubscription<TextInputStatusListener> subscribeTextInputStatus(TextInputStatusListener listener);
}
