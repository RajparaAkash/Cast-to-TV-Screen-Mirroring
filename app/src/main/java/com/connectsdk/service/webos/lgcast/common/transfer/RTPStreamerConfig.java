package com.connectsdk.service.webos.lgcast.common.transfer;

import java.util.ArrayList;


public class RTPStreamerConfig {

    
    public static class SecurityKey {
        public byte[] masterKey;
        public byte[] mki;
    }

    
    public static class SecurityConfig {
        ArrayList<SecurityKey> keys;
        boolean enableSecurity = true;
        public boolean enableMki = true;
        RTPStreamerData.SRTPAuthType authType = RTPStreamerData.SRTPAuthType.HMAC_SHA1_80;
        RTPStreamerData.SRTPCipherType cipherType = RTPStreamerData.SRTPCipherType.AES_128_ICM;

        public boolean isEnableSecurity() {
            return this.enableSecurity;
        }

        public void setEnableSecurity(boolean enableSecurity) {
            this.enableSecurity = enableSecurity;
        }

        public RTPStreamerData.SRTPAuthType getAuthType() {
            return this.authType;
        }

        public void setAuthType(RTPStreamerData.SRTPAuthType authType) {
            this.authType = authType;
        }

        public RTPStreamerData.SRTPCipherType getCipherType() {
            return this.cipherType;
        }

        public void setCipherType(RTPStreamerData.SRTPCipherType cipherType) {
            this.cipherType = cipherType;
        }

        public ArrayList<SecurityKey> getKeys() {
            return this.keys;
        }

        public void setKeys(ArrayList<SecurityKey> keys) {
            this.keys = keys;
        }
    }

    
    public static class VideoConfig {
        int bitrate;
        int framerate;
        int height;
        int mpUnitSize;
        int width;
        RTPStreamerData.VideoType type = RTPStreamerData.VideoType.H264;
        RTPStreamerData.ResourceType resourceType = RTPStreamerData.ResourceType.APP;
        byte[] codecData = null;
        boolean enableMP = false;

        public RTPStreamerData.VideoType getType() {
            return this.type;
        }

        public void setType(RTPStreamerData.VideoType type) {
            this.type = type;
        }

        public RTPStreamerData.ResourceType getResourceType() {
            return this.resourceType;
        }

        public void setResourceType(RTPStreamerData.ResourceType resourceType) {
            this.resourceType = resourceType;
        }

        public int getWidth() {
            return this.width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getFramerate() {
            return this.framerate;
        }

        public void setFramerate(int framerate) {
            this.framerate = framerate;
        }

        public int getBitrate() {
            return this.bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public byte[] getCodecData() {
            return this.codecData;
        }

        public void setCodecData(byte[] codecData) {
            this.codecData = codecData;
        }

        public boolean isEnableMP() {
            return this.enableMP;
        }

        public void setEnableMP(boolean enableMP) {
            this.enableMP = enableMP;
        }

        public int getMpUnitSize() {
            return this.mpUnitSize;
        }

        public void setMpUnitSize(int mpUnitSize) {
            this.mpUnitSize = mpUnitSize;
        }
    }

    
    public static class AudioConfig {
        int channelCnt;
        int mpUnitSize;
        int samplingRate;
        RTPStreamerData.AudioType type = RTPStreamerData.AudioType.AAC;
        RTPStreamerData.ResourceType resourceType = RTPStreamerData.ResourceType.APP;
        byte[] codecData = null;
        boolean enableMP = false;

        public RTPStreamerData.AudioType getType() {
            return this.type;
        }

        public void setType(RTPStreamerData.AudioType type) {
            this.type = type;
        }

        public RTPStreamerData.ResourceType getResourceType() {
            return this.resourceType;
        }

        public void setResourceType(RTPStreamerData.ResourceType resourceType) {
            this.resourceType = resourceType;
        }

        public byte[] getCodecData() {
            return this.codecData;
        }

        public void setCodecData(byte[] codecData) {
            this.codecData = codecData;
        }

        public boolean isEnableMP() {
            return this.enableMP;
        }

        public void setEnableMP(boolean enableMP) {
            this.enableMP = enableMP;
        }

        public int getMpUnitSize() {
            return this.mpUnitSize;
        }

        public void setMpUnitSize(int mpUnitSize) {
            this.mpUnitSize = mpUnitSize;
        }

        public int getSamplingRate() {
            return this.samplingRate;
        }

        public void setSamplingRate(int samplingRate) {
            this.samplingRate = samplingRate;
        }

        public int getChannelCnt() {
            return this.channelCnt;
        }

        public void setChannelCnt(int channelCnt) {
            this.channelCnt = channelCnt;
        }
    }
}
