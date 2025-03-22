package com.github.kunal52;

public class AndroidRemoteContext {
    private static volatile AndroidRemoteContext instance;
    private String clientName = "RemoteTV";
    private String host = "";
    private String keyStoreFileName = "androidtv.keystore";
    private char[] keyStorePass = "KeyStore_Password".toCharArray();
    private String model = "RemoteTV";
    private String serviceName = "com.github.kunal52/RemoteTV";
    private String vendor = "github";

    private AndroidRemoteContext() {
    }

    public static AndroidRemoteContext getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (AndroidRemoteContext.class) {
            if (instance == null) {
                instance = new AndroidRemoteContext();
            }
        }
        return instance;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String str) {
        this.serviceName = str;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String str) {
        this.clientName = str;
    }

    public String getKeyStoreFileName() {
        return this.keyStoreFileName;
    }

    public void setKeyStoreFileName(String str) {
        this.keyStoreFileName = str;
    }

    public char[] getKeyStorePass() {
        return this.keyStorePass;
    }

    public void setKeyStorePass(char[] cArr) {
        this.keyStorePass = cArr;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getVendor() {
        return this.vendor;
    }

    public void setVendor(String str) {
        this.vendor = str;
    }
}
