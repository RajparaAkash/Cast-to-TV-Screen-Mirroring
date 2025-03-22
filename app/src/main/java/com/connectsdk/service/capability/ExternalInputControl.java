package com.connectsdk.service.capability;

import com.connectsdk.core.ExternalInputInfo;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.sessions.LaunchSession;

import java.util.List;


public interface ExternalInputControl extends CapabilityMethods {
    public static final String Any = "ExternalInputControl.Any";
    public static final String Picker_Launch = "ExternalInputControl.Picker.Launch";
    public static final String Picker_Close = "ExternalInputControl.Picker.Close";
    public static final String List = "ExternalInputControl.List";
    public static final String Set = "ExternalInputControl.Set";
    public static final String[] Capabilities = {Picker_Launch, Picker_Close, List, Set};

    
    public interface ExternalInputListListener extends ResponseListener<List<ExternalInputInfo>> {
    }

    void closeInputPicker(LaunchSession launchSessionm, ResponseListener<Object> listener);

    ExternalInputControl getExternalInput();

    CapabilityPriorityLevel getExternalInputControlPriorityLevel();

    void getExternalInputList(ExternalInputListListener listener);

    void launchInputPicker(Launcher.AppLaunchListener listener);

    void setExternalInput(ExternalInputInfo input, ResponseListener<Object> listener);
}
