package com.connectsdk.service.command;

import java.util.List;


public interface ServiceSubscription<T> {
    T addListener(T listener);

    List<T> getListeners();

    void removeListener(T listener);

    void unsubscribe();
}
