package com.connectsdk.service;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.util.Xml;

import com.connectsdk.core.MediaInfo;
import com.connectsdk.core.SubtitleInfo;
import com.connectsdk.core.Util;
import com.connectsdk.discovery.DiscoveryFilter;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.discovery.provider.ssdp.Service;
import com.connectsdk.etc.helper.DeviceServiceReachability;
import com.connectsdk.etc.helper.HttpConnection;
import com.connectsdk.etc.helper.HttpMessage;
import com.connectsdk.service.capability.CapabilityMethods;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.PlaylistControl;
import com.connectsdk.service.capability.VolumeControl;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommand;
import com.connectsdk.service.command.ServiceCommandError;
import com.connectsdk.service.command.ServiceSubscription;
import com.connectsdk.service.command.URLServiceSubscription;
import com.connectsdk.service.config.ServiceConfig;
import com.connectsdk.service.config.ServiceDescription;
import com.connectsdk.service.sessions.LaunchSession;
import com.connectsdk.service.upnp.DLNAHttpServer;
import com.connectsdk.service.upnp.DLNAMediaInfoParser;
import com.connectsdk.service.webos.lgcast.remotecamera.service.CameraProperty;

import org.jdom2.JDOMConstants;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DLNAService extends DeviceService implements PlaylistControl, MediaControl, MediaPlayer, VolumeControl {

    protected static final String AV_TRANSPORT = "AVTransport";
    public static final String AV_TRANSPORT_URN = "urn:schemas-upnp-org:service:AVTransport:1";
    protected static final String CONNECTION_MANAGER = "ConnectionManager";
    public static final String CONNECTION_MANAGER_URN = "urn:schemas-upnp-org:service:ConnectionManager:1";
    public static final String DEFAULT_SUBTITLE_MIMETYPE = "text/srt";
    public static final String DEFAULT_SUBTITLE_TYPE = "srt";
    protected static final String GROUP_RENDERING_CONTROL = "GroupRenderingControl";

    public static final String ID = "DLNA";
    public static final String PLAY_STATE = "playState";
    protected static final String RENDERING_CONTROL = "RenderingControl";
    public static final String RENDERING_CONTROL_URN = "urn:schemas-upnp-org:service:RenderingControl:1";
    protected static final String SUBSCRIBE = "SUBSCRIBE";
    private static int TIMEOUT = 300;
    protected static final String UNSUBSCRIBE = "UNSUBSCRIBE";
    Map<String, String> SIDList;
    String avTransportURL;
    String connectionControlURL;
    Context context;
    DLNAHttpServer httpServer;
    String renderingControlURL;
    Timer resubscriptionTimer;

    public interface PositionInfoListener {
        void onGetPositionInfoFailed(ServiceCommandError error);

        void onGetPositionInfoSuccess(String positionInfoXml);
    }

    @Override 
    public MediaControl getMediaControl() {
        return this;
    }

    @Override 
    public MediaPlayer getMediaPlayer() {
        return this;
    }

    @Override 
    public PlaylistControl getPlaylistControl() {
        return this;
    }

    @Override 
    public VolumeControl getVolumeControl() {
        return this;
    }

    @Override 
    public boolean isConnectable() {
        return true;
    }

    public DLNAService(ServiceDescription serviceDescription, ServiceConfig serviceConfig) {
        this(serviceDescription, serviceConfig, DiscoveryManager.getInstance().getContext(), new DLNAHttpServer());
    }

    public DLNAService(ServiceDescription serviceDescription, ServiceConfig serviceConfig, Context context, DLNAHttpServer dlnaServer) {
        super(serviceDescription, serviceConfig);
        this.context = context;
        this.SIDList = new HashMap();
        updateControlURL();
        this.httpServer = dlnaServer;
    }

    public static DiscoveryFilter discoveryFilter() {
        return new DiscoveryFilter(ID, "urn:schemas-upnp-org:device:MediaRenderer:1");
    }

    @Override 
    public CapabilityPriorityLevel getPriorityLevel(Class<? extends CapabilityMethods> clazz) {
        if (clazz.equals(MediaPlayer.class)) {
            return getMediaPlayerCapabilityLevel();
        }
        if (clazz.equals(MediaControl.class)) {
            return getMediaControlCapabilityLevel();
        }
        if (clazz.equals(VolumeControl.class)) {
            return getVolumeControlCapabilityLevel();
        }
        if (clazz.equals(PlaylistControl.class)) {
            return getPlaylistControlCapabilityLevel();
        }
        return CapabilityPriorityLevel.NOT_SUPPORTED;
    }

    @Override 
    public void setServiceDescription(ServiceDescription serviceDescription) {
        super.setServiceDescription(serviceDescription);
        updateControlURL();
    }

    private void updateControlURL() {
        List<Service> serviceList = this.serviceDescription.getServiceList();
        if (serviceList != null) {
            for (int i = 0; i < serviceList.size(); i++) {
                if (!serviceList.get(i).baseURL.endsWith("/")) {
                    StringBuilder sb = new StringBuilder();
                    Service service = serviceList.get(i);
                    service.baseURL = sb.append(service.baseURL).append("/").toString();
                }
                if (serviceList.get(i).serviceType.contains(AV_TRANSPORT)) {
                    this.avTransportURL = makeControlURL(serviceList.get(i).baseURL, serviceList.get(i).controlURL);
                } else if (serviceList.get(i).serviceType.contains(RENDERING_CONTROL) && !serviceList.get(i).serviceType.contains(GROUP_RENDERING_CONTROL)) {
                    this.renderingControlURL = makeControlURL(serviceList.get(i).baseURL, serviceList.get(i).controlURL);
                } else if (serviceList.get(i).serviceType.contains(CONNECTION_MANAGER)) {
                    this.connectionControlURL = makeControlURL(serviceList.get(i).baseURL, serviceList.get(i).controlURL);
                }
            }
        }
    }

    String makeControlURL(String base, String path) {
        if (base == null || path == null) {
            return null;
        }
        if (path.startsWith("/")) {
            return base + path.substring(1);
        }
        return base + path;
    }

    @Override 
    public CapabilityPriorityLevel getMediaPlayerCapabilityLevel() {
        return CapabilityPriorityLevel.NORMAL;
    }

    @Override 
    public void getMediaInfo(final MediaInfoListener listener) {
        getPositionInfo(new PositionInfoListener() {
            @Override
            public void onGetPositionInfoSuccess(final String positionInfoXml) {
                Util.runInBackground(new Runnable() {
                    @Override
                    public void run() {
                        Util.postSuccess(listener, DLNAMediaInfoParser.getMediaInfo(DLNAService.this.parseData(positionInfoXml, "TrackMetaData"), "http://" + DLNAService.this.getServiceDescription().getIpAddress() + ":" + DLNAService.this.getServiceDescription().getPort()));
                    }
                });
            }

            @Override
            public void onGetPositionInfoFailed(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }
    @Override
    public ServiceSubscription<MediaInfoListener> subscribeMediaInfo(MediaInfoListener listener) {
        URLServiceSubscription<MediaInfoListener> request = new URLServiceSubscription<MediaInfoListener>(this, "info", null, null);
        request.addListener(listener);
        addSubscription(request);
        return request;
    }
    
    @Deprecated
    public void displayMedia(String url, String mimeType, String title, String description, String iconSrc, final LaunchListener listener) {
        displayMedia(url, null, mimeType, title, description, iconSrc, listener);
    }

    private void displayMedia(String url, SubtitleInfo subtitle, String mimeType, String title, String description, String iconSrc, final LaunchListener listener) {
        String[] split = mimeType.split("/");
        String str = split[0];
        String str2 = split[1];
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            Util.postError(listener, new ServiceCommandError(0, "You must provide a valid mimeType (audio/*,  video/*, etc)", null));
            return;
        }
        if ("mp3".equals(str2)) {
            str2 = "mpeg";
        }
        String format = String.format("%s/%s", str, str2);
        ResponseListener<Object> responseListener = new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                HashMap hashMap = new HashMap();
                hashMap.put("Speed", "1");
                new ServiceCommand(DLNAService.this, "Play", DLNAService.this.getMessageXml(DLNAService.AV_TRANSPORT_URN, "Play", "0", hashMap), new ResponseListener<Object>() {
                    @Override
                    public void onSuccess(Object response2) {
                        LaunchSession launchSession = new LaunchSession();
                        launchSession.setService(DLNAService.this);
                        launchSession.setSessionType(LaunchSession.LaunchSessionType.Media);
                        Util.postSuccess(listener, new MediaLaunchObject(launchSession, DLNAService.this, DLNAService.this));
                    }

                    @Override 
                    public void onError(ServiceCommandError error) {
                        Util.postError(listener, error);
                    }
                }).send();
            }

            @Override 
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        };
        String metadata = getMetadata(url, subtitle, format, title, description, iconSrc);
        if (metadata == null) {
            Util.postError(listener, ServiceCommandError.getError(500));
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            linkedHashMap.put("CurrentURI", encodeURL(url));
            linkedHashMap.put("CurrentURIMetaData", metadata);
            new ServiceCommand(this, "SetAVTransportURI", getMessageXml(AV_TRANSPORT_URN, "SetAVTransportURI", "0", linkedHashMap), responseListener).send();
        } catch (Exception exception) {
            Util.postError(listener, ServiceCommandError.getError(500));
        }
    }

    @Override 
    public void displayImage(String url, String mimeType, String title, String description, String iconSrc, LaunchListener listener) {
        displayMedia(url, null, mimeType, title, description, iconSrc, listener);
    }

    @Override 
    public void displayImage(MediaInfo mediaInfo, LaunchListener listener) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = null;
        if (mediaInfo != null) {
            String url = mediaInfo.getUrl();
            String mimeType = mediaInfo.getMimeType();
            String title = mediaInfo.getTitle();
            String description = mediaInfo.getDescription();
            if (mediaInfo.getImages() != null && mediaInfo.getImages().size() > 0) {
                str6 = mediaInfo.getImages().get(0).getUrl();
            }
            str5 = str6;
            str = url;
            str2 = mimeType;
            str3 = title;
            str4 = description;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
        }
        displayImage(str, str2, str3, str4, str5, listener);
    }

    @Override 
    public void playMedia(String url, String mimeType, String title, String description, String iconSrc, boolean shouldLoop, LaunchListener listener) {
        displayMedia(url, null, mimeType, title, description, iconSrc, listener);
    }

    @Override 
    public void playMedia(MediaInfo mediaInfo, boolean shouldLoop, LaunchListener listener) {
        String str;
        SubtitleInfo subtitleInfo;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = null;
        if (mediaInfo != null) {
            String url = mediaInfo.getUrl();
            SubtitleInfo subtitleInfo2 = mediaInfo.getSubtitleInfo();
            String mimeType = mediaInfo.getMimeType();
            String title = mediaInfo.getTitle();
            String description = mediaInfo.getDescription();
            if (mediaInfo.getImages() != null && mediaInfo.getImages().size() > 0) {
                str6 = mediaInfo.getImages().get(0).getUrl();
            }
            str5 = str6;
            str = url;
            subtitleInfo = subtitleInfo2;
            str2 = mimeType;
            str3 = title;
            str4 = description;
        } else {
            str = null;
            subtitleInfo = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
        }
        displayMedia(str, subtitleInfo, str2, str3, str4, str5, listener);
    }

    @Override 
    public void closeMedia(LaunchSession launchSession, ResponseListener<Object> listener) {
        if (launchSession.getService() instanceof DLNAService) {
            ((DLNAService) launchSession.getService()).stop(listener);
        }
    }

    @Override 
    public CapabilityPriorityLevel getMediaControlCapabilityLevel() {
        return CapabilityPriorityLevel.NORMAL;
    }

    @Override 
    public void play(ResponseListener<Object> listener) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Speed", "1");
        new ServiceCommand(this, "Play", getMessageXml(AV_TRANSPORT_URN, "Play", "0", linkedHashMap), listener).send();
    }

    @Override 
    public void pause(ResponseListener<Object> listener) {
        new ServiceCommand(this, "Pause", getMessageXml(AV_TRANSPORT_URN, "Pause", "0", null), listener).send();
    }

    @Override 
    public void stop(ResponseListener<Object> listener) {
        new ServiceCommand(this, "Stop", getMessageXml(AV_TRANSPORT_URN, "Stop", "0", null), listener).send();
    }

    @Override 
    public void rewind(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override 
    public void fastForward(ResponseListener<Object> listener) {
        Util.postError(listener, ServiceCommandError.notSupported());
    }

    @Override 
    public CapabilityPriorityLevel getPlaylistControlCapabilityLevel() {
        return CapabilityPriorityLevel.NORMAL;
    }

    @Override
    public void previous(ResponseListener<Object> listener) {
        new ServiceCommand(this, "Previous", getMessageXml(AV_TRANSPORT_URN, "Previous", "0", null), listener).send();
    }

    @Override
    public void next(ResponseListener<Object> listener) {
        new ServiceCommand(this, "Next", getMessageXml(AV_TRANSPORT_URN, "Next", "0", null), listener).send();
    }

    @Override 
    public void jumpToTrack(long index, ResponseListener<Object> listener) {
        seek("TRACK_NR", Long.toString(index + 1), listener);
    }
    
    @Override
    public void setPlayMode(PlayMode playMode, ResponseListener<Object> listener) {
        String method = "SetPlayMode";
        String instanceId = "0";
        String mode;

        switch (playMode) {
            case RepeatAll:
                mode = "REPEAT_ALL";
                break;
            case RepeatOne:
                mode = "REPEAT_ONE";
                break;
            case Shuffle:
                mode = "SHUFFLE";
                break;
            default:
                mode = "NORMAL";
        }

        Map<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("NewPlayMode", mode);

        String payload = getMessageXml(AV_TRANSPORT_URN, method, instanceId, parameters);

        ServiceCommand<ResponseListener<Object>> request = new ServiceCommand<ResponseListener<Object>>(this, method, payload, listener);
        request.send();
    }

    @Override
    public void seek(long position, ResponseListener<Object> listener) {
        seek("REL_TIME", String.format(Locale.US, "%02d:%02d:%02d", Long.valueOf((position / 3600000) % 24), Long.valueOf((position / 60000) % 60), Long.valueOf((position / 1000) % 60)), listener);
    }

    private void getPositionInfo(final PositionInfoListener listener) {
        new ServiceCommand(this, "GetPositionInfo", getMessageXml(AV_TRANSPORT_URN, "GetPositionInfo", "0", null), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                PositionInfoListener positionInfoListener = listener;
                if (positionInfoListener != null) {
                    positionInfoListener.onGetPositionInfoSuccess((String) response);
                }
            }

            @Override 
            public void onError(ServiceCommandError error) {
                PositionInfoListener positionInfoListener = listener;
                if (positionInfoListener != null) {
                    positionInfoListener.onGetPositionInfoFailed(error);
                }
            }
        }).send();
    }

    @Override 
    public void getDuration(final DurationListener listener) {
        getPositionInfo(new PositionInfoListener() {
            @Override
            public void onGetPositionInfoSuccess(String positionInfoXml) {
                String parseData = DLNAService.this.parseData(positionInfoXml, "TrackDuration");
                MediaInfo mediaInfo = DLNAMediaInfoParser.getMediaInfo(DLNAService.this.parseData(positionInfoXml, "TrackMetaData"));
                if (!parseData.equals("0:00:00") || mediaInfo.getMimeType().contains("image")) {
                    Util.postSuccess(listener, Long.valueOf(DLNAService.this.convertStrTimeFormatToLong(parseData)));
                    return;
                }
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        DLNAService.this.getDuration(listener);
                    }
                }, 1000L);
            }

            @Override
            public void onGetPositionInfoFailed(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override 
    public void getPosition(final PositionListener listener) {
        getPositionInfo(new PositionInfoListener() {
            @Override
            public void onGetPositionInfoSuccess(String positionInfoXml) {
                Util.postSuccess(listener, Long.valueOf(DLNAService.this.convertStrTimeFormatToLong(DLNAService.this.parseData(positionInfoXml, "RelTime"))));
            }

            @Override
            public void onGetPositionInfoFailed(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    protected void seek(String unit, String target, ResponseListener<Object> listener) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Unit", unit);
        linkedHashMap.put("Target", target);
        new ServiceCommand(this, "Seek", getMessageXml(AV_TRANSPORT_URN, "Seek", "0", linkedHashMap), listener).send();
    }

    protected String getMessageXml(String serviceURN, String method, String instanceId, Map<String, String> params) {
        try {
            Document newDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            newDocument.setXmlStandalone(true);
            newDocument.setXmlVersion("1.0");
            Element createElement = newDocument.createElement("s:Envelope");
            Element createElement2 = newDocument.createElement("s:Body");
            Element createElementNS = newDocument.createElementNS(serviceURN, "u:" + method);
            Element createElement3 = newDocument.createElement("InstanceID");
            createElement.setAttribute("s:encodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
            createElement.setAttribute("xmlns:s", "http://schemas.xmlsoap.org/soap/envelope/");
            newDocument.appendChild(createElement);
            createElement.appendChild(createElement2);
            createElement2.appendChild(createElementNS);
            if (instanceId != null) {
                createElement3.setTextContent(instanceId);
                createElementNS.appendChild(createElement3);
            }
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    Element createElement4 = newDocument.createElement(entry.getKey());
                    createElement4.setTextContent(entry.getValue());
                    createElementNS.appendChild(createElement4);
                }
            }
            return xmlToString(newDocument, true);
        } catch (Exception exception) {
            return null;
        }
    }

    protected String getMetadata(String mediaURL, SubtitleInfo subtitle, String mime, String title, String description, String iconUrl) {
        Document document;
        try {
            String str = "";
            if (mime.startsWith("image")) {
                str = "object.item.imageItem";
            } else if (mime.startsWith("video")) {
                str = "object.item.videoItem";
            } else if (mime.startsWith(CameraProperty.AUDIO)) {
                str = "object.item.audioItem";
            }
            Document newDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element createElement = newDocument.createElement("DIDL-Lite");
            Element createElement2 = newDocument.createElement("item");
            Element createElement3 = newDocument.createElement("dc:title");
            Element createElement4 = newDocument.createElement("dc:description");
            Element createElement5 = newDocument.createElement("res");
            Element createElement6 = newDocument.createElement("upnp:albumArtURI");
            Element createElement7 = newDocument.createElement("upnp:class");
            createElement.appendChild(createElement2);
            createElement2.appendChild(createElement3);
            createElement2.appendChild(createElement4);
            createElement2.appendChild(createElement5);
            createElement2.appendChild(createElement6);
            createElement2.appendChild(createElement7);
            createElement.setAttributeNS(JDOMConstants.NS_URI_XMLNS, JDOMConstants.NS_PREFIX_XMLNS, "urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/");
            createElement.setAttributeNS(JDOMConstants.NS_URI_XMLNS, "xmlns:upnp", "urn:schemas-upnp-org:metadata-1-0/upnp/");
            createElement.setAttributeNS(JDOMConstants.NS_URI_XMLNS, "xmlns:dc", "http://purl.org/dc/elements/1.1/");
            createElement.setAttributeNS(JDOMConstants.NS_URI_XMLNS, "xmlns:sec", "http://www.sec.co.kr/");
            createElement3.setTextContent(title);
            createElement4.setTextContent(description);
            createElement5.setTextContent(encodeURL(mediaURL));
            createElement6.setTextContent(encodeURL(iconUrl));
            createElement7.setTextContent(str);
            createElement2.setAttribute("id", "1000");
            createElement2.setAttribute("parentID", "0");
            createElement2.setAttribute("restricted", "0");
            createElement5.setAttribute("protocolInfo", "http-get:*:" + mime + ":DLNA.ORG_OP=01");
            if (subtitle != null) {
                String mimeType = subtitle.getMimeType();
                String str2 = DEFAULT_SUBTITLE_TYPE;
                String mimeType2 = mimeType == null ? DEFAULT_SUBTITLE_TYPE : subtitle.getMimeType();
                String[] split = mimeType2.split("/");
                if (split == null || split.length != 2) {
                    mimeType2 = DEFAULT_SUBTITLE_MIMETYPE;
                } else {
                    str2 = split[1];
                }
                createElement5.setAttributeNS(JDOMConstants.NS_URI_XMLNS, "xmlns:pv", "http://www.pv.com/pvns/");
                createElement5.setAttribute("pv:subtitleFileUri", subtitle.getUrl());
                createElement5.setAttribute("pv:subtitleFileType", str2);
                document = newDocument;
                Element createElement8 = document.createElement("res");
                createElement8.setAttribute("protocolInfo", "http-get:*:smi/caption");
                createElement8.setTextContent(subtitle.getUrl());
                createElement2.appendChild(createElement8);
                Element createElement9 = document.createElement("res");
                createElement9.setAttribute("protocolInfo", "http-get:*:" + mimeType2 + ":");
                createElement9.setTextContent(subtitle.getUrl());
                createElement2.appendChild(createElement9);
                Element createElement10 = document.createElement("sec:CaptionInfoEx");
                createElement10.setAttribute("sec:type", str2);
                createElement10.setTextContent(subtitle.getUrl());
                createElement2.appendChild(createElement10);
                Element createElement11 = document.createElement("sec:CaptionInfo");
                createElement11.setAttribute("sec:type", str2);
                createElement11.setTextContent(subtitle.getUrl());
                createElement2.appendChild(createElement11);
            } else {
                document = newDocument;
            }
            document.appendChild(createElement);
            return xmlToString(document, false);
        } catch (Exception exception) {
            return null;
        }
    }

    String encodeURL(String mediaURL) throws MalformedURLException, URISyntaxException, UnsupportedEncodingException {
        if (mediaURL == null || mediaURL.isEmpty()) {
            return "";
        }
        if (URLDecoder.decode(mediaURL, "UTF-8").equals(mediaURL)) {
            URL url = new URL(mediaURL);
            return new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef()).toASCIIString();
        }
        return mediaURL;
    }

    String xmlToString(Node source, boolean xmlDeclaration) throws TransformerException {
        DOMSource dOMSource = new DOMSource(source);
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
        if (!xmlDeclaration) {
            newTransformer.setOutputProperty("omit-xml-declaration", "yes");
        }
        newTransformer.transform(dOMSource, streamResult);
        return stringWriter.toString();
    }

    @Override
    public void sendCommand(final ServiceCommand<?> mCommand) {
        Util.runInBackground(new Runnable() {
            @Override
            public void run() {
                String str;
                ServiceCommand serviceCommand = mCommand;
                String target = serviceCommand.getTarget();
                String str2 = (String) serviceCommand.getPayload();
                if (str2 == null) {
                    Util.postError(serviceCommand.getResponseListener(), new ServiceCommandError(0, "Cannot process the command, \"payload\" is missed", null));
                    return;
                }
                String str3 = DLNAService.AV_TRANSPORT_URN;
                if (str2.contains(DLNAService.AV_TRANSPORT_URN)) {
                    str = DLNAService.this.avTransportURL;
                } else if (str2.contains(DLNAService.RENDERING_CONTROL_URN)) {
                    str = DLNAService.this.renderingControlURL;
                    str3 = DLNAService.RENDERING_CONTROL_URN;
                } else if (str2.contains(DLNAService.CONNECTION_MANAGER_URN)) {
                    str = DLNAService.this.connectionControlURL;
                    str3 = DLNAService.CONNECTION_MANAGER_URN;
                } else {
                    str3 = null;
                    str = null;
                }
                if (str3 == null) {
                    Util.postError(serviceCommand.getResponseListener(), new ServiceCommandError(0, "Cannot process the command, \"serviceURN\" is missed", null));
                } else if (str == null) {
                    Util.postError(serviceCommand.getResponseListener(), new ServiceCommandError(0, "Cannot process the command, \"targetURL\" is missed", null));
                } else {
                    try {
                        HttpConnection createHttpConnection = DLNAService.this.createHttpConnection(str);
                        createHttpConnection.setHeader("Content-Type", HttpMessage.CONTENT_TYPE_TEXT_XML);
                        createHttpConnection.setHeader("SOAPAction", String.format("\"%s#%s\"", str3, target));
                        createHttpConnection.setMethod(HttpConnection.Method.POST);
                        createHttpConnection.setPayload(str2);
                        createHttpConnection.execute();
                        int responseCode = createHttpConnection.getResponseCode();
                        if (responseCode == 200) {
                            Util.postSuccess(serviceCommand.getResponseListener(), createHttpConnection.getResponseString());
                        } else {
                            Util.postError(serviceCommand.getResponseListener(), ServiceCommandError.getError(responseCode));
                        }
                    } catch (IOException e) {
                        Util.postError(serviceCommand.getResponseListener(), new ServiceCommandError(0, e.getMessage(), null));
                    }
                }
            }
        });
    }

    HttpConnection createHttpConnection(String targetURL) throws IOException {
        return HttpConnection.newInstance(URI.create(targetURL));
    }

    @Override 
    protected void updateCapabilities() {
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(MediaPlayer.Display_Image);
        arrayList.add("MediaPlayer.Play.Video");
        arrayList.add("MediaPlayer.Play.Audio");
        arrayList.add(MediaPlayer.Play_Playlist);
        arrayList.add(MediaPlayer.Close);
        arrayList.add(MediaPlayer.Subtitle_SRT);
        arrayList.add(MediaPlayer.MetaData_Title);
        arrayList.add(MediaPlayer.MetaData_MimeType);
        arrayList.add(MediaPlayer.MediaInfo_Get);
        arrayList.add(MediaPlayer.MediaInfo_Subscribe);
        arrayList.add(MediaControl.Play);
        arrayList.add(MediaControl.Pause);
        arrayList.add(MediaControl.Stop);
        arrayList.add(MediaControl.Seek);
        arrayList.add(MediaControl.Position);
        arrayList.add(MediaControl.Duration);
        arrayList.add(MediaControl.PlayState);
        arrayList.add(MediaControl.PlayState_Subscribe);
        arrayList.add(MediaControl.Next);
        arrayList.add(MediaControl.Previous);
        arrayList.add(PlaylistControl.Next);
        arrayList.add(PlaylistControl.Previous);
        arrayList.add(PlaylistControl.JumpToTrack);
        arrayList.add(PlaylistControl.SetPlayMode);
        arrayList.add(VolumeControl.Volume_Set);
        arrayList.add(VolumeControl.Volume_Get);
        arrayList.add(VolumeControl.Volume_Up_Down);
        arrayList.add(VolumeControl.Volume_Subscribe);
        arrayList.add(VolumeControl.Mute_Get);
        arrayList.add(VolumeControl.Mute_Set);
        arrayList.add(VolumeControl.Mute_Subscribe);
        setCapabilities(arrayList);
    }

    @Override 
    public LaunchSession decodeLaunchSession(String type, JSONObject sessionObj) throws JSONException {
        if (type.equals("dlna")) {
            LaunchSession launchSessionFromJSONObject = LaunchSession.launchSessionFromJSONObject(sessionObj);
            launchSessionFromJSONObject.setService(this);
            return launchSessionFromJSONObject;
        }
        return null;
    }

    private boolean isXmlEncoded(final String xml) {
        if (xml == null || xml.length() < 4) {
            return false;
        }
        return xml.trim().substring(0, 4).equals("&lt;");
    }

    String parseData(String response, String key) {
        int next;
        if (isXmlEncoded(response)) {
            response = Html.fromHtml(response).toString();
        }
        XmlPullParser newPullParser = Xml.newPullParser();
        try {
            newPullParser.setInput(new StringReader(response));
            boolean z = false;
            do {
                next = newPullParser.next();
                if (next == 2) {
                    if (key.equals(newPullParser.getName())) {
                        z = true;
                        continue;
                    } else {
                        continue;
                    }
                } else if (next == 4 && z) {
                    return newPullParser.getText();
                }
            } while (next != 1);
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    long convertStrTimeFormatToLong(String strTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            return simpleDateFormat.parse(strTime).getTime() - simpleDateFormat.parse("00:00:00").getTime();
        } catch (NullPointerException exception) {
            Log.w(Util.T, "Null time argument");
            return 0L;
        } catch (ParseException unused2) {
            Log.w(Util.T, "Invalid Time Format: " + strTime);
            return 0L;
        }
    }

    @Override 
    public void getPlayState(final PlayStateListener listener) {
        new ServiceCommand(this, "GetTransportInfo", getMessageXml(AV_TRANSPORT_URN, "GetTransportInfo", "0", null), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                Util.postSuccess(listener, PlayStateStatus.convertTransportStateToPlayStateStatus(DLNAService.this.parseData((String) response, "CurrentTransportState")));
            }

            @Override 
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }

    @Override
    public ServiceSubscription<PlayStateListener> subscribePlayState(PlayStateListener listener) {
        URLServiceSubscription<PlayStateListener> request = new URLServiceSubscription<PlayStateListener>(this, PLAY_STATE, null, null);
        request.addListener(listener);
        addSubscription(request);
        return request;
    }

    private void addSubscription(URLServiceSubscription<?> subscription) {
        if (!this.httpServer.isRunning()) {
            Util.runInBackground(new Runnable() {
                @Override
                public void run() {
                    DLNAService.this.httpServer.start();
                }
            });
            subscribeServices();
        }
        this.httpServer.getSubscriptions().add(subscription);
    }

    @Override
    public void unsubscribe(URLServiceSubscription<?> subscription) {
        this.httpServer.getSubscriptions().remove(subscription);
        if (this.httpServer.getSubscriptions().isEmpty()) {
            unsubscribeServices();
        }
    }

    @Override 
    public boolean isConnected() {
        return this.connected;
    }

    @Override 
    public void connect() {
        this.connected = true;
        reportConnected(true);
    }

    private void getDeviceCapabilities(final PositionInfoListener listener) {
        new ServiceCommand(this, "GetDeviceCapabilities", getMessageXml(AV_TRANSPORT_URN, "GetDeviceCapabilities", "0", null), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                PositionInfoListener positionInfoListener = listener;
                if (positionInfoListener != null) {
                    positionInfoListener.onGetPositionInfoSuccess((String) response);
                }
            }

            @Override 
            public void onError(ServiceCommandError error) {
                PositionInfoListener positionInfoListener = listener;
                if (positionInfoListener != null) {
                    positionInfoListener.onGetPositionInfoFailed(error);
                }
            }
        }).send();
    }

    private void getProtocolInfo(final PositionInfoListener listener) {
        new ServiceCommand(this, "GetProtocolInfo", getMessageXml(CONNECTION_MANAGER_URN, "GetProtocolInfo", null, null), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                PositionInfoListener positionInfoListener = listener;
                if (positionInfoListener != null) {
                    positionInfoListener.onGetPositionInfoSuccess((String) response);
                }
            }

            @Override 
            public void onError(ServiceCommandError error) {
                PositionInfoListener positionInfoListener = listener;
                if (positionInfoListener != null) {
                    positionInfoListener.onGetPositionInfoFailed(error);
                }
            }
        }).send();
    }

    @Override 
    public void disconnect() {
        this.connected = false;
        if (this.mServiceReachability != null) {
            this.mServiceReachability.stop();
        }
        Util.runOnUI(new Runnable() {
            @Override
            public void run() {
                if (DLNAService.this.listener != null) {
                    DLNAService.this.listener.onDisconnect(DLNAService.this, null);
                }
            }
        });
        Util.runInBackground(new Runnable() {
            @Override
            public void run() {
                DLNAService.this.httpServer.stop();
            }
        }, true);
    }

    @Override
    
    public void onLoseReachability(DeviceServiceReachability reachability) {
        if (this.connected) {
            disconnect();
        } else {
            this.mServiceReachability.stop();
        }
    }

    public void subscribeServices() {
        Util.runInBackground(new Runnable() {
            @Override
            public void run() {
                String str;
                try {
                    str = Util.getIpAddress(DLNAService.this.context).getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    str = null;
                }
                List<Service> serviceList = DLNAService.this.serviceDescription.getServiceList();
                if (serviceList != null) {
                    for (int i = 0; i < serviceList.size(); i++) {
                        String makeControlURL = DLNAService.this.makeControlURL("/", serviceList.get(i).eventSubURL);
                        if (makeControlURL != null) {
                            try {
                                HttpConnection newSubscriptionInstance = HttpConnection.newSubscriptionInstance(new URI("http", "", DLNAService.this.serviceDescription.getIpAddress(), DLNAService.this.serviceDescription.getPort(), makeControlURL, "", ""));
                                newSubscriptionInstance.setMethod(HttpConnection.Method.SUBSCRIBE);
                                newSubscriptionInstance.setHeader("CALLBACK", "<http://" + str + ":" + DLNAService.this.httpServer.getPort() + makeControlURL + ">");
                                newSubscriptionInstance.setHeader("NT", "upnp:event");
                                newSubscriptionInstance.setHeader("TIMEOUT", "Second-" + DLNAService.TIMEOUT);
                                newSubscriptionInstance.setHeader("Connection", "close");
                                newSubscriptionInstance.setHeader("Content-length", "0");
                                newSubscriptionInstance.setHeader("USER-AGENT", "Android UPnp/1.1 ConnectSDK");
                                newSubscriptionInstance.execute();
                                if (newSubscriptionInstance.getResponseCode() == 200) {
                                    DLNAService.this.SIDList.put(serviceList.get(i).serviceType, newSubscriptionInstance.getResponseHeader("SID"));
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        resubscribeServices();
    }

    public void resubscribeServices() {
        Timer timer = new Timer();
        this.resubscriptionTimer = timer;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Util.runInBackground(new Runnable() {
                    @Override
                    public void run() {
                        List<Service> serviceList = DLNAService.this.serviceDescription.getServiceList();
                        if (serviceList != null) {
                            for (int i = 0; i < serviceList.size(); i++) {
                                String makeControlURL = DLNAService.this.makeControlURL("/", serviceList.get(i).eventSubURL);
                                if (makeControlURL != null) {
                                    String str = DLNAService.this.SIDList.get(serviceList.get(i).serviceType);
                                    try {
                                        HttpConnection newSubscriptionInstance = HttpConnection.newSubscriptionInstance(new URI("http", "", DLNAService.this.serviceDescription.getIpAddress(), DLNAService.this.serviceDescription.getPort(), makeControlURL, "", ""));
                                        newSubscriptionInstance.setMethod(HttpConnection.Method.SUBSCRIBE);
                                        newSubscriptionInstance.setHeader("TIMEOUT", "Second-" + DLNAService.TIMEOUT);
                                        newSubscriptionInstance.setHeader("SID", str);
                                        newSubscriptionInstance.execute();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                });
            }
        };
        int i = TIMEOUT;
        timer.scheduleAtFixedRate(timerTask, (i / 2) * 1000, (i / 2) * 1000);
    }

    public void unsubscribeServices() {
        Timer timer = this.resubscriptionTimer;
        if (timer != null) {
            timer.cancel();
        }
        Util.runInBackground(new Runnable() {
            @Override
            public void run() {
                List<Service> serviceList = DLNAService.this.serviceDescription.getServiceList();
                if (serviceList != null) {
                    for (int i = 0; i < serviceList.size(); i++) {
                        String makeControlURL = DLNAService.this.makeControlURL("/", serviceList.get(i).eventSubURL);
                        if (makeControlURL != null) {
                            String str = DLNAService.this.SIDList.get(serviceList.get(i).serviceType);
                            try {
                                HttpConnection newSubscriptionInstance = HttpConnection.newSubscriptionInstance(new URI("http", "", DLNAService.this.serviceDescription.getIpAddress(), DLNAService.this.serviceDescription.getPort(), makeControlURL, "", ""));
                                newSubscriptionInstance.setMethod(HttpConnection.Method.UNSUBSCRIBE);
                                newSubscriptionInstance.setHeader("SID", str);
                                newSubscriptionInstance.execute();
                                if (newSubscriptionInstance.getResponseCode() == 200) {
                                    DLNAService.this.SIDList.remove(serviceList.get(i).serviceType);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    @Override 
    public CapabilityPriorityLevel getVolumeControlCapabilityLevel() {
        return CapabilityPriorityLevel.NORMAL;
    }

    @Override 
    public void volumeUp(final ResponseListener<Object> listener) {
        getVolume(new VolumeListener() {
            @Override
            public void onSuccess(final Float volume) {
                if (volume.floatValue() >= 1.0d) {
                    Util.postSuccess(listener, null);
                    return;
                }
                float floatValue = (float) (volume.floatValue() + 0.01d);
                if (floatValue > 1.0d) {
                    floatValue = 1.0f;
                }
                DLNAService.this.setVolume(floatValue, listener);
                Util.postSuccess(listener, null);
            }

            @Override 
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override 
    public void volumeDown(final ResponseListener<Object> listener) {
        getVolume(new VolumeListener() {
            @Override
            public void onSuccess(final Float volume) {
                if (volume.floatValue() <= 0.0d) {
                    Util.postSuccess(listener, null);
                    return;
                }
                float floatValue = (float) (volume.floatValue() - 0.01d);
                if (floatValue < 0.0d) {
                    floatValue = 0.0f;
                }
                DLNAService.this.setVolume(floatValue, listener);
                Util.postSuccess(listener, null);
            }

            @Override 
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        });
    }

    @Override 
    public void setVolume(float volume, ResponseListener<Object> listener) {
        String valueOf = String.valueOf((int) (volume * 100.0f));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Channel", "Master");
        linkedHashMap.put("DesiredVolume", valueOf);
        new ServiceCommand(this, "SetVolume", getMessageXml(RENDERING_CONTROL_URN, "SetVolume", "0", linkedHashMap), listener).send();
    }

    @Override 
    public void getVolume(final VolumeListener listener) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Channel", "Master");
        new ServiceCommand(this, "GetVolume", getMessageXml(RENDERING_CONTROL_URN, "GetVolume", "0", linkedHashMap), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                try {
                    Integer.parseInt(DLNAService.this.parseData((String) response, "CurrentVolume"));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
                Util.postSuccess(listener, Float.valueOf((float) (0 / 100.0d)));
            }

            @Override 
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }

    @Override 
    public void setMute(boolean isMute, ResponseListener<Object> listener) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Channel", "Master");
        linkedHashMap.put("DesiredMute", String.valueOf(isMute ? 1 : 0));
        new ServiceCommand(this, "SetMute", getMessageXml(RENDERING_CONTROL_URN, "SetMute", "0", linkedHashMap), listener).send();
    }

    @Override 
    public void getMute(final MuteListener listener) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Channel", "Master");
        new ServiceCommand(this, "GetMute", getMessageXml(RENDERING_CONTROL_URN, "GetMute", "0", linkedHashMap), new ResponseListener<Object>() {
            @Override
            public void onSuccess(Object response) {
                Util.postSuccess(listener, Boolean.valueOf(Boolean.parseBoolean(DLNAService.this.parseData((String) response, "CurrentMute"))));
            }

            @Override 
            public void onError(ServiceCommandError error) {
                Util.postError(listener, error);
            }
        }).send();
    }
    @Override
    public ServiceSubscription<VolumeListener> subscribeVolume(VolumeListener listener) {
        URLServiceSubscription<VolumeListener> request = new URLServiceSubscription<VolumeListener>(this, "volume", null, null);
        request.addListener(listener);
        addSubscription(request);
        return request;
    }
    @Override
    public ServiceSubscription<MuteListener> subscribeMute(MuteListener listener) {
        URLServiceSubscription<MuteListener> request = new URLServiceSubscription<MuteListener>(this, "mute", null, null);
        request.addListener(listener);
        addSubscription(request);
        return request;
    }
}
