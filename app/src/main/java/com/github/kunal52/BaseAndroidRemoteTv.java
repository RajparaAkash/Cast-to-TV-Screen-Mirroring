package com.github.kunal52;


public abstract class BaseAndroidRemoteTv {
    private final AndroidRemoteContext androidRemoteContext = AndroidRemoteContext.getInstance();

    BaseAndroidRemoteTv() {
    }

    public String getServiceName() {
        return this.androidRemoteContext.getServiceName();
    }

    public void setServiceName(String str) {
        this.androidRemoteContext.setServiceName(str);
    }

    public String getClientName() {
        return this.androidRemoteContext.getClientName();
    }

    public void setClientName(String str) {
        this.androidRemoteContext.setClientName(str);
    }

    public char[] getKeyStorePass() {
        return this.androidRemoteContext.getKeyStorePass();
    }

    public void setKeyStorePass(String str) {
        this.androidRemoteContext.setKeyStorePass(str.toCharArray());
    }
}
