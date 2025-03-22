package com.connectsdk.service.capability;

import com.connectsdk.core.AppInfo;
import com.connectsdk.service.capability.listeners.ResponseListener;

import org.json.JSONObject;


public interface ToastControl extends CapabilityMethods {
    public static final String Any = "ToastControl.Any";
    public static final String Show_Toast = "ToastControl.Show";
    public static final String Show_Clickable_Toast_App = "ToastControl.Show.Clickable.App";
    public static final String Show_Clickable_Toast_App_Params = "ToastControl.Show.Clickable.App.Params";
    public static final String Show_Clickable_Toast_URL = "ToastControl.Show.Clickable.URL";
    public static final String[] Capabilities = {Show_Toast, Show_Clickable_Toast_App, Show_Clickable_Toast_App_Params, Show_Clickable_Toast_URL};

    ToastControl getToastControl();

    CapabilityPriorityLevel getToastControlCapabilityLevel();

    void showClickableToastForApp(String message, AppInfo appInfo, JSONObject params, ResponseListener<Object> listener);

    void showClickableToastForApp(String message, AppInfo appInfo, JSONObject params, String iconData, String iconExtension, ResponseListener<Object> listener);

    void showClickableToastForURL(String message, String url, ResponseListener<Object> listener);

    void showClickableToastForURL(String message, String url, String iconData, String iconExtension, ResponseListener<Object> listener);

    void showToast(String message, ResponseListener<Object> listener);

    void showToast(String message, String iconData, String iconExtension, ResponseListener<Object> listener);
}
