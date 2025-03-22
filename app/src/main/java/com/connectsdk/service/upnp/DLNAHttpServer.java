package com.connectsdk.service.upnp;

import com.connectsdk.core.MediaInfo;
import com.connectsdk.core.Util;
import com.connectsdk.service.CastService;
import com.connectsdk.service.DLNAService;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.URLServiceSubscription;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;


public class DLNAHttpServer {
    final int port = 49291;
    volatile boolean running = false;
    CopyOnWriteArrayList<URLServiceSubscription<?>> subscriptions = new CopyOnWriteArrayList<>();
    volatile ServerSocket welcomeSocket;

    public int getPort() {
        return 49291;
    }

    public synchronized void start() {
        if (this.running) {
            return;
        }
        this.running = true;
        try {
            Objects.requireNonNull(this);
            this.welcomeSocket = new ServerSocket(49291);
            Util.runInBackground(new Runnable() {
                @Override
                public void run() {
                    DLNAHttpServer.this.processRequests();
                }
            }, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stop() {
        if (this.running) {
            Iterator<URLServiceSubscription<?>> it = this.subscriptions.iterator();
            while (it.hasNext()) {
                it.next().unsubscribe();
            }
            this.subscriptions.clear();
            if (this.welcomeSocket != null && !this.welcomeSocket.isClosed()) {
                try {
                    this.welcomeSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.welcomeSocket = null;
            this.running = false;
        }
    }

    private void processRequests() {
        while (running) {
            if (welcomeSocket == null || welcomeSocket.isClosed()) {
                break;
            }

            Socket connectionSocket = null;
            BufferedReader inFromClient = null;
            DataOutputStream outToClient = null;

            try {
                connectionSocket = welcomeSocket.accept();
            } catch (IOException ex) {
                ex.printStackTrace();
                // this socket may have been closed, so we'll stop
                break;
            }

            int c = 0;

            String body = null;

            try {
                inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                StringBuilder sb = new StringBuilder();

                while ((c = inFromClient.read()) != -1) {
                    sb.append((char)c);

                    if (sb.toString().endsWith("\r\n\r\n"))
                        break;
                }
                sb = new StringBuilder();

                while ((c = inFromClient.read()) != -1) {
                    sb.append((char)c);
                    body = sb.toString();

                    if (body.endsWith("</e:propertyset>"))
                        break;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            PrintWriter out = null;

            try {
                outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                out = new PrintWriter(outToClient);
                out.println("HTTP/1.1 200 OK");
                out.println("Connection: Close");
                out.println("Content-Length: 0");
                out.println();
                out.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    inFromClient.close();
                    out.close();
                    outToClient.close();
                    connectionSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }

            if (body == null)
                continue;

            InputStream stream = null;

            try {
                stream = new ByteArrayInputStream(body.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }

            JSONArray propertySet;
            DLNANotifyParser parser = new DLNANotifyParser();

            try {
                propertySet = parser.parse(stream);

                for (int i = 0; i < propertySet.length(); i++) {
                    JSONObject property = propertySet.getJSONObject(i);

                    if (property.has("LastChange")) {
                        JSONObject lastChange = property.getJSONObject("LastChange");
                        handleLastChange(lastChange);
                    }
                }
            } catch (XmlPullParserException | IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleLastChange(JSONObject lastChange) throws JSONException {
        if (lastChange.has("InstanceID")) {
            JSONArray jSONArray = lastChange.getJSONArray("InstanceID");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    handleEntry(jSONArray2.getJSONObject(i2));
                }
            }
        }
    }

    private void handleEntry(JSONObject entry) throws JSONException {
        boolean parseBoolean;
        if (entry.has("TransportState")) {
            MediaControl.PlayStateStatus convertTransportStateToPlayStateStatus = MediaControl.PlayStateStatus.convertTransportStateToPlayStateStatus(entry.getString("TransportState"));
            Iterator<URLServiceSubscription<?>> it = this.subscriptions.iterator();
            while (it.hasNext()) {
                URLServiceSubscription<?> next = it.next();
                if (next.getTarget().equalsIgnoreCase(DLNAService.PLAY_STATE)) {
                    for (int i = 0; i < next.getListeners().size(); i++) {
                        Util.postSuccess((ResponseListener) next.getListeners().get(i), convertTransportStateToPlayStateStatus);
                    }
                }
            }
        }
        if ((entry.has("Volume") && !entry.has("channel")) || (entry.has("Volume") && entry.getString("channel").equals("Master"))) {
            float f = entry.getInt("Volume") / 100.0f;
            Iterator<URLServiceSubscription<?>> it2 = this.subscriptions.iterator();
            while (it2.hasNext()) {
                URLServiceSubscription<?> next2 = it2.next();
                if (next2.getTarget().equalsIgnoreCase("volume")) {
                    for (int i2 = 0; i2 < next2.getListeners().size(); i2++) {
                        Util.postSuccess((ResponseListener) next2.getListeners().get(i2), Float.valueOf(f));
                    }
                }
            }
        }
        if ((entry.has("Mute") && !entry.has("channel")) || (entry.has("Mute") && entry.getString("channel").equals("Master"))) {
            String string = entry.getString("Mute");
            try {
                parseBoolean = true;
                if (Integer.parseInt(string) != 1) {
                    parseBoolean = false;
                }
            } catch (NumberFormatException exception) {
                parseBoolean = Boolean.parseBoolean(string);
            }
            Iterator<URLServiceSubscription<?>> it3 = this.subscriptions.iterator();
            while (it3.hasNext()) {
                URLServiceSubscription<?> next3 = it3.next();
                if (next3.getTarget().equalsIgnoreCase(CastService.CAST_SERVICE_MUTE_SUBSCRIPTION_NAME)) {
                    for (int i3 = 0; i3 < next3.getListeners().size(); i3++) {
                        Util.postSuccess((ResponseListener) next3.getListeners().get(i3), Boolean.valueOf(parseBoolean));
                    }
                }
            }
        }
        if (entry.has("CurrentTrackMetaData")) {
            MediaInfo mediaInfo = DLNAMediaInfoParser.getMediaInfo(entry.getString("CurrentTrackMetaData"));
            Iterator<URLServiceSubscription<?>> it4 = this.subscriptions.iterator();
            while (it4.hasNext()) {
                URLServiceSubscription<?> next4 = it4.next();
                if (next4.getTarget().equalsIgnoreCase("info")) {
                    for (int i4 = 0; i4 < next4.getListeners().size(); i4++) {
                        Util.postSuccess((ResponseListener) next4.getListeners().get(i4), mediaInfo);
                    }
                }
            }
        }
    }

    public List<URLServiceSubscription<?>> getSubscriptions() {
        return this.subscriptions;
    }

    public void setSubscriptions(List<URLServiceSubscription<?>> subscriptions) {
        this.subscriptions = new CopyOnWriteArrayList<>(subscriptions);
    }

    public boolean isRunning() {
        return this.running;
    }
}
