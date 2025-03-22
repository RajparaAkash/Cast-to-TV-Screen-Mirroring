package com.connectsdk.service.capability;

import com.connectsdk.service.capability.listeners.ResponseListener;


public interface KeyControl extends CapabilityMethods {
    public static final String Any = "KeyControl.Any";
    public static final String Send_Key = "KeyControl.SendKey";

    public static final String up = "KeyControl.Up";
    public static final String Down = "KeyControl.Down";
    public static final String Left = "KeyControl.Left";
    public static final String Right = "KeyControl.Right";

    public static final String OK = "KeyControl.OK";
    public static final String Back = "KeyControl.Back";
    public static final String Home = "KeyControl.Home";
    public static final String KeyCode2 = "KeyControl.KeyCode";
    public static final String[] Capabilities = {up, Down, Left, Right, OK, Back, Home, KeyCode2};

    void back(ResponseListener<Object> listener);

    void down(ResponseListener<Object> listener);

    KeyControl getKeyControl();

    CapabilityPriorityLevel getKeyControlCapabilityLevel();

    void home(ResponseListener<Object> listener);

    void left(ResponseListener<Object> listener);

    void ok(ResponseListener<Object> listener);

    void right(ResponseListener<Object> listener);

    void sendKeyCode(KeyCode keycode, ResponseListener<Object> listener);

    void up(ResponseListener<Object> listener);

    public enum KeyCode {
        NUM_0(0),
        NUM_1(1),
        NUM_2(2),
        NUM_3(3),
        NUM_4(4),
        NUM_5(5),
        NUM_6(6),
        NUM_7(7),
        NUM_8(8),
        NUM_9(9),

        DASH(10),
        ENTER(11);

        private final int code;

        private static final KeyCode[] codes = {
                NUM_0, NUM_1, NUM_2, NUM_3, NUM_4, NUM_5, NUM_6, NUM_7, NUM_8, NUM_9, DASH, ENTER
        };

        KeyCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static KeyCode createFromInteger(int keyCode) {
            if (keyCode >= 0 && keyCode < codes.length) {
                return codes[keyCode];
            }
            return null;
        }
    }
}
