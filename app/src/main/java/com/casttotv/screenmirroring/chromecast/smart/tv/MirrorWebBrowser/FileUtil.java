package com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {

    public static String readRawResourceFile(Context context, int i) throws IOException {
        return readFile(context.getResources().openRawResource(i));
    }

    private static String readFile(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return sb.toString();
            }
            sb.append(readLine);
            sb.append("\n");
        }
    }
}
