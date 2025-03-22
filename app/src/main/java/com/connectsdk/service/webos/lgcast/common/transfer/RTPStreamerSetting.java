package com.connectsdk.service.webos.lgcast.common.transfer;

import com.connectsdk.service.webos.lgcast.common.utils.Logger;

import java.util.ArrayList;

public class RTPStreamerSetting {

    public static class KeyInfo {
        byte[] masterKey;
        int masterKeyLen;
        byte[] mki;
        int mkiByteLen;
    }

    public static class StreamInfo {
        byte[] codecData;
        StreamRSCInfo dst;
        int maxpools;
        int poolSize;

        int pt;
        StreamRSCInfo src;
        int type;
        int codecDataLen = 0;
        boolean supportPool = false;
    }


    public static class StreamRSCInfo {
        String host;
        int port;
        int rscType;

        public StreamRSCInfo(RTPStreamerData.ResourceType rscType, String host, int port) {
            this.rscType = rscType.getType();
            this.host = host;
            this.port = port;
        }
    }


    public static class VideoStreamInfo extends StreamInfo {
        int bitRate;
        int frameRate;
        int height;
        private boolean isCompleted = false;
        int width;

        public VideoStreamInfo() {
            this.type = RTPStreamerData.VideoType.H264.getType();
            this.pt = RTPStreamerData.VideoType.H264.getPt();
        }

        public boolean isCompleted() {
            return this.isCompleted;
        }

        public void setVideoConfig(RTPStreamerConfig.VideoConfig config) {
            this.type = config.type.getType();
            this.pt = config.type.getPt();
            this.width = config.width;
            this.height = config.height;
            this.bitRate = config.bitrate;
            this.frameRate = config.framerate;
            if (config.codecData != null) {
                this.codecDataLen = config.codecData.length;
                this.codecData = config.codecData;
            }
            if (config.enableMP && config.mpUnitSize > 0) {
                this.supportPool = true;
                this.poolSize = config.mpUnitSize;
                this.maxpools = 100;
            } else {
                this.supportPool = false;
            }
            this.src = new StreamRSCInfo(config.resourceType, "", 0);
            this.isCompleted = true;
        }
    }


    public static class AudioStreamInfo extends StreamInfo {
        int channelCnt;
        private boolean isCompleted = false;
        int samplingRate;

        public AudioStreamInfo() {
            this.type = RTPStreamerData.AudioType.PCM.getType();
            this.pt = RTPStreamerData.AudioType.PCM.getPt();
        }

        public boolean isCompleted() {
            return this.isCompleted;
        }

        public void setAudioConfig(RTPStreamerConfig.AudioConfig config) {
            this.type = config.type.getType();
            this.pt = config.type.getPt();
            this.samplingRate = config.samplingRate;
            this.channelCnt = config.channelCnt;
            if (config.codecData != null) {
                this.codecDataLen = config.codecData.length;
                this.codecData = config.codecData;
            }
            if (config.enableMP && config.mpUnitSize > 0) {
                this.supportPool = true;
                this.poolSize = config.mpUnitSize;
                this.maxpools = 100;
            } else {
                this.supportPool = false;
            }
            this.src = new StreamRSCInfo(config.resourceType, "", 0);
            this.isCompleted = true;
        }
    }


    public static class SecurityInfo {
        int authType;
        int cipherType;
        int keyCount;
        ArrayList<KeyInfo> keyInfos;
        boolean enableSecurity = false;
        boolean enableMki = false;
        private boolean isCompleted = false;

        public boolean isCompleted() {
            if (this.enableSecurity) {
                return this.isCompleted;
            }
            return true;
        }

        public void setSecurityConfig(RTPStreamerConfig.SecurityConfig config) {
            boolean z = config.enableSecurity;
            this.enableSecurity = z;
            if (z) {
                this.enableMki = config.enableMki;
                this.authType = config.authType.type;
                if (setSecurityKey(config)) {
                    this.isCompleted = true;
                    this.keyCount = this.keyInfos.size();
                }
            }
        }

        private boolean setSecurityKey(RTPStreamerConfig.SecurityConfig config) {
            switch (config.cipherType) {
                case NONE:
                    cipherType = RTPStreamerData.SRTPCipherType.NONE.type;
                    keyCount = 0;
                    return true;
                case AES_128_GCM:
                case AES_256_GCM:
                case AES_128_ICM:
                case AES_256_ICM:
                    keyInfos = new ArrayList<KeyInfo>();
                    for (RTPStreamerConfig.SecurityKey key : config.keys) {
                        KeyInfo keyInfo = addMasterkey(key, config.cipherType.getLength());
                        if (keyInfo != null) {
                            keyInfos.add(keyInfo);
                        } else {
                            keyInfos = null;
                            return false;
                        }
                    }
                    return true;
                default:
                    return false;
            }
        }

        private void addMkiInfo(RTPStreamerConfig.SecurityKey key, KeyInfo keyInfo) {
            if (key.mki != null && key.mki.length > 0) {
                keyInfo.mkiByteLen = Math.min(key.mki.length, 128);
                keyInfo.mki = new byte[keyInfo.mkiByteLen];
                System.arraycopy(key.mki, 0, keyInfo.mki, 0, keyInfo.mkiByteLen);
                return;
            }
            keyInfo.mkiByteLen = 0;
            keyInfo.mki = null;
        }

        private KeyInfo addMasterkey(RTPStreamerConfig.SecurityKey key, int keyLen) {
            if (key.masterKey != null && key.masterKey.length == keyLen) {
                KeyInfo keyInfo = new KeyInfo();
                keyInfo.masterKeyLen = keyLen;
                keyInfo.masterKey = new byte[keyInfo.masterKeyLen];
                System.arraycopy(key.masterKey, 0, keyInfo.masterKey, 0, keyInfo.masterKeyLen);
                if (this.enableMki) {
                    addMkiInfo(key, keyInfo);
                }
                return keyInfo;
            }
            Logger.error("please check master key info !!", new Object[0]);
            return null;
        }
    }

    public static class StreamSetting {
        AudioStreamInfo audioStreamInfo;
        int mediaType;
        int protocolType;
        SecurityInfo securityInfo;
        long ssrc;
        VideoStreamInfo videoStreamInfo;
        boolean isLibDebug = true;
        boolean isGLibDebug = false;

        public StreamSetting(RTPStreamerData.ProtocolType protocolType, RTPStreamerData.MediaType mediaType, long ssrc) {
            this.protocolType = protocolType.getType();
            this.mediaType = mediaType.getType();
            this.ssrc = ssrc;
            if (mediaType == RTPStreamerData.MediaType.VIDEO || mediaType == RTPStreamerData.MediaType.AV) {
                this.videoStreamInfo = new VideoStreamInfo();
            }
            if (mediaType == RTPStreamerData.MediaType.AUDIO || mediaType == RTPStreamerData.MediaType.AV) {
                this.audioStreamInfo = new AudioStreamInfo();
            }
            this.securityInfo = new SecurityInfo();
        }

        public void setSourceStream(RTPStreamerData.MediaType mediaType, RTPStreamerData.ResourceType srcType, String host, int port) {
            if (mediaType != RTPStreamerData.MediaType.VIDEO && mediaType != RTPStreamerData.MediaType.AUDIO) {
                Logger.error("can't set SRC source(media type: %d) resource info ", Integer.valueOf(mediaType.getType()));
                return;
            }
            StreamRSCInfo streamRSCInfo = new StreamRSCInfo(srcType, host, port);
            if (mediaType == RTPStreamerData.MediaType.VIDEO) {
                this.videoStreamInfo.src = streamRSCInfo;
            } else if (mediaType == RTPStreamerData.MediaType.AUDIO) {
                this.audioStreamInfo.src = streamRSCInfo;
            }
        }

        public void setDestinationStream(RTPStreamerData.MediaType mediaType, String host, int port) {
            if (mediaType != RTPStreamerData.MediaType.VIDEO && mediaType != RTPStreamerData.MediaType.AUDIO) {
                Logger.error("can't set DST source(media type: %d) resource info ", Integer.valueOf(mediaType.getType()));
                return;
            }
            StreamRSCInfo streamRSCInfo = new StreamRSCInfo(RTPStreamerData.ResourceType.SOCKET, host, port);
            if (mediaType == RTPStreamerData.MediaType.VIDEO) {
                this.videoStreamInfo.dst = streamRSCInfo;
            } else if (mediaType == RTPStreamerData.MediaType.AUDIO) {
                this.audioStreamInfo.dst = streamRSCInfo;
            }
        }
    }
}
