package com.connectsdk.service.webos.lgcast.screenmirroring.uibc;

import java.util.HashMap;


public class UibcKeyCode {
    public static final int TV_KEYCODE_0 = 48;
    public static final int TV_KEYCODE_1 = 49;
    public static final int TV_KEYCODE_2 = 50;
    public static final int TV_KEYCODE_3 = 51;
    public static final int TV_KEYCODE_4 = 52;
    public static final int TV_KEYCODE_5 = 53;
    public static final int TV_KEYCODE_6 = 54;
    public static final int TV_KEYCODE_7 = 55;
    public static final int TV_KEYCODE_8 = 56;
    public static final int TV_KEYCODE_9 = 57;
    public static final int TV_KEYCODE_BACK = 461;
    public static final int TV_KEYCODE_DOWN = 40;
    public static final int TV_KEYCODE_FORWARD = 417;
    public static final int TV_KEYCODE_LEFT = 37;
    public static final int TV_KEYCODE_PAUSE = 19;
    public static final int TV_KEYCODE_PLAY = 415;
    public static final int TV_KEYCODE_REWIND = 412;
    public static final int TV_KEYCODE_RIGHT = 39;
    public static final int TV_KEYCODE_STOP = 413;
    public static final int TV_KEYCODE_UP = 38;
    private static final HashMap<Integer, Integer> mKeycodeMap;

    static {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        mKeycodeMap = hashMap;
        hashMap.put(48, 7);
        hashMap.put(49, 8);
        hashMap.put(50, 9);
        hashMap.put(51, 10);
        hashMap.put(52, 11);
        hashMap.put(53, 12);
        hashMap.put(54, 13);
        hashMap.put(55, 14);
        hashMap.put(56, 15);
        hashMap.put(57, 16);
        hashMap.put(37, 21);
        hashMap.put(38, 19);
        hashMap.put(39, 22);
        hashMap.put(40, 20);
        hashMap.put(413, 86);
        hashMap.put(412, 89);
        hashMap.put(415, 126);
        hashMap.put(19, 127);
        hashMap.put(417, 90);
        hashMap.put(461, 4);
    }

    public static int getSystemKeyCode(int tvKeyCode) {
        return mKeycodeMap.getOrDefault(Integer.valueOf(tvKeyCode), -1).intValue();
    }
}
