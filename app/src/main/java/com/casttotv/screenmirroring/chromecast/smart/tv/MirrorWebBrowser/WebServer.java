package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.casttotv.screenmirroring.chromecast.smart.tv.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class WebServer {

    private final Context context;
    private String httpUsername;
    private String httpPassword;
    private final String indexHtmlPage;
    private boolean isHttpAuthEnabled;
    private boolean isRunning;
    private final MotionJpegStreamer motionJpegStreamer;
    private final int port;
    private ServerSocket serverSocket;
    private WebServerThread serverThread;
    private final SettingsManagerActivity settingsManager;

    public WebServer(Context context, int port, MotionJpegStreamer motionJpegStreamer) throws IOException {
        this.context = context.getApplicationContext();
        this.port = port;
        this.motionJpegStreamer = motionJpegStreamer;
        this.indexHtmlPage = FileUtil.readRawResourceFile(context, R.raw.wb_index);
        this.settingsManager = SettingsManagerActivity.getInstance(context);
        this.isHttpAuthEnabled = settingsManager.isHttpAuthEnabled();
        this.httpUsername = settingsManager.getHttpUsername();
        this.httpPassword = settingsManager.getHttpPassword();
    }

    public void start() throws IOException {
        if (!isRunning) {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(100);
            serverThread = new WebServerThread();
            serverThread.start();
            isRunning = true;
        }
    }

    public void stop() {
        serverThread.interrupt();
        while (isRunning()) {
            ThreadUtil.sleepMillis(5);
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getPort() {
        return port;
    }

    public void enableHttpAuthentication(String username, String password) {
        this.httpUsername = username;
        this.httpPassword = password;
        this.isHttpAuthEnabled = true;
    }

    public void disableHttpAuthentication() {
        this.isHttpAuthEnabled = false;
        this.httpPassword = null;
        this.httpUsername = null;
    }

    public class WebServerThread extends Thread {
        WebServerThread() {
        }

        public void run() {
            while (!isInterrupted()) {
                acceptClient();
            }
            try {
                WebServer.this.serverSocket.close();
                WebServer.this.isRunning = false;
                WebServer.this.serverSocket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void acceptClient() {
            try {
                Socket accept = WebServer.this.serverSocket.accept();
                serveClient(accept);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void serveClient(Socket socket) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                try {
                    serveRequest(socket, readLine.split(" ")[1], readHeader(bufferedReader));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }

        private Map<String, String> readHeader(BufferedReader bufferedReader) throws IOException {
            HashMap hashMap = new HashMap();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null || readLine.equals("")) {
                    return hashMap;
                }
                String[] splitKeySemicolonValuePair = splitKeySemicolonValuePair(readLine);
                if (splitKeySemicolonValuePair != null) {
                    hashMap.put(splitKeySemicolonValuePair[0], splitKeySemicolonValuePair[1]);
                }
            }
        }

        private String[] splitKeySemicolonValuePair(String str) {
            int indexOf = str.indexOf(58);
            if (indexOf < 0) {
                return null;
            }
            return new String[]{str.substring(0, indexOf), str.substring(indexOf + 1, str.length())};
        }

        private void serveRequest(Socket socket, String str, Map<String, String> map) throws IOException {
            if (!handleAuthentication(map)) {
                sendAccessDenied(socket);
            } else if (str.startsWith("/ping")) {
                sendJSONPPing(socket);
            } else if (str.startsWith("/stop")) {
                sendStop(socket);
            } else if (str.equals("/")) {
                sendHomepage(socket);
            } else if (str.equals("/screen.mjpeg")) {
                WebServer.this.motionJpegStreamer.add(socket);
            } else if (str.startsWith("/screen.jpeg")) {
                sendScreenJpeg(str, socket, WebServer.this.motionJpegStreamer.getLastImage());
            } else if (str.endsWith(".png")) {
                sendPng(str, socket);
            } else if (str.equals("/bootstrap.css")) {
                sendCss(str, socket);
            } else if (str.equals("/app.js")) {
                sendJs(str, socket);
            } else if (str.endsWith(".ico")) {
                sendIco(str, socket);
            } else {
                sendNotFound(socket);
            }
        }

        private void sendJSONPPing(Socket socket) throws IOException {
            HttpResponse httpResponse = new HttpResponse(HttpStatus.OK);
            httpResponse.addHeader(HttpResponse.CONNECTION, "close");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            httpResponse.sendHeader(outputStreamWriter);
            outputStreamWriter.write("callback({});");
            outputStreamWriter.write(HttpResponse.CRLF);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }

        private void sendStop(Socket socket) throws IOException {
            HttpResponse httpResponse = new HttpResponse(HttpStatus.OK);
            httpResponse.addHeader(HttpResponse.CONNECTION, "close");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            httpResponse.sendHeader(outputStreamWriter);
            outputStreamWriter.write("callback({});");
            outputStreamWriter.write(HttpResponse.CRLF);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }

        private boolean handleAuthentication(Map<String, String> map) {
            if (!WebServer.this.isHttpAuthEnabled) {
                return true;
            }
            if (!map.containsKey("Authorization")) {
                Log.d("TAG", "client did not sent authentication string");
            } else {
                String[] splitKeySemicolonValuePair = splitKeySemicolonValuePair(new String(Base64.decode(map.get("Authorization").replace("Basic ", ""), 0)));
                if (splitKeySemicolonValuePair == null) {
                    Log.d("TAG", "username password combination was malformed");
                } else {
                    String str = splitKeySemicolonValuePair[0];
                    String str2 = splitKeySemicolonValuePair[1];
                    if (WebServer.this.httpUsername.equals(str) && WebServer.this.httpPassword.equals(str2)) {
                        return true;
                    }
                    Log.d("TAG", "username and password did not match");
                }
            }
            return false;
        }

        private void sendAccessDenied(Socket socket) throws IOException {
            HttpResponse httpResponse = new HttpResponse(HttpStatus.UNAUTHORIZED);
            httpResponse.addHeader(HttpResponse.CONNECTION, "close");
            httpResponse.addHeader(HttpResponse.AUTHENTICATE, "BASIC realm=\"Please enter username and password\"");
            sendResponse(socket, httpResponse, "");
        }

        private void sendHomepage(Socket socket) throws IOException {
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.addHeader(HttpResponse.CONNECTION, "close");
            httpResponse.addHeader(HttpResponse.CONTENT_TYPE, "text/html");
            sendResponse(socket, httpResponse, WebServer.this.indexHtmlPage.replaceAll("__FORMAT__", WebServer.this.settingsManager.getFormat()));
        }

        private void sendPng(String str, Socket socket) {
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.addHeader(HttpResponse.CONNECTION, "close");
            httpResponse.addHeader(HttpResponse.CONTENT_TYPE, "image/png");
            try {
                sendPngResponse(socket, httpResponse, getRawId(getImageRawName(str)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void sendCss(String str, Socket socket) {
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.addHeader(HttpResponse.CONNECTION, "close");
            httpResponse.addHeader(HttpResponse.CONTENT_TYPE, "text/css");
            try {
                sendResponse(socket, httpResponse, FileUtil.readRawResourceFile(WebServer.this.context, R.raw.bootstrap));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void sendJs(String str, Socket socket) {
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.addHeader(HttpResponse.CONNECTION, "close");
            httpResponse.addHeader(HttpResponse.CONTENT_TYPE, "text/javascript");
            try {
                sendResponse(socket, httpResponse, FileUtil.readRawResourceFile(WebServer.this.context, R.raw.app_ss));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void sendIco(String str, Socket socket) {
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.addHeader(HttpResponse.CONNECTION, "close");
            httpResponse.addHeader(HttpResponse.CONTENT_TYPE, "image/ico");
            try {
                sendPngResponse(socket, httpResponse, getRawId(getImageRawName(str)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void sendScreenJpeg(String str, Socket socket, byte[] bArr) {
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.addHeader(HttpResponse.CONNECTION, "close");
            httpResponse.addHeader(HttpResponse.CONTENT_TYPE, "image/jpeg");
            httpResponse.addHeader(HttpResponse.CONTENT_DISPOSITION, "attachment; filename=livescreen-" + getTimeString() + ".jpeg");
            try {
                sendScreenJpegResponse(socket, httpResponse, bArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String getTimeString() {
            Date time = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat.format(time);
        }

        private void sendScreenJpegResponse(Socket socket, HttpResponse httpResponse, byte[] bArr) throws Exception {
            OutputStream outputStream = socket.getOutputStream();
            httpResponse.sendHeader(outputStream);
            outputStream.write(bArr);
            outputStream.write(10);
            outputStream.flush();
            outputStream.close();
        }

        private void sendPngResponse(Socket socket, HttpResponse httpResponse, int i) throws Exception {
            OutputStream outputStream = socket.getOutputStream();
            httpResponse.sendHeader(outputStream);
            Bitmap decodeResource = BitmapFactory.decodeResource(WebServer.this.context.getResources(), i);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            decodeResource.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.write(10);
            outputStream.flush();
            outputStream.close();
        }

        private String getImageRawName(String str) {
            return str.substring(1, str.length() - 4);
        }

        private int getRawId(String str) {
            try {
                Field declaredField = R.raw.class.getDeclaredField(str);
                return declaredField.getInt(declaredField);
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }

        private void sendResponse(Socket socket, HttpResponse httpResponse, String str) throws IOException {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            httpResponse.sendHeader(outputStreamWriter);
            outputStreamWriter.write(str);
            outputStreamWriter.write(HttpResponse.CRLF);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }

        private void sendNotFound(Socket socket) throws IOException {
            HttpResponse httpResponse = new HttpResponse(HttpStatus.NOT_FOUND);
            httpResponse.addHeader(HttpResponse.CONNECTION, "close");
            OutputStream outputStream = socket.getOutputStream();
            httpResponse.sendHeader(outputStream);
            outputStream.close();
        }
    }
}
