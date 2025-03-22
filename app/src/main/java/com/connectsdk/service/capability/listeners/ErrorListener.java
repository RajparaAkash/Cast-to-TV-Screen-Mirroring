package com.connectsdk.service.capability.listeners;

import com.connectsdk.service.command.ServiceCommandError;


public interface ErrorListener {
    void onError(ServiceCommandError error);
}
