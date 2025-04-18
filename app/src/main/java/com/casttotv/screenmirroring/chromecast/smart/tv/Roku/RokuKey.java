package com.casttotv.screenmirroring.chromecast.smart.tv.Roku;

public enum RokuKey {
    HOME("Home"),
    REV("Rev"),
    FWD("Fwd"),
    PLAY("Play"),
    SELECT("Select"),
    LEFT("Left"),
    RIGHT("Right"),
    DOWN("Down"),
    UP("Up"),
    BACK("Back"),
    INTANT_REPLAY("InstantReplay"),
    INFO("Info"),
    BACKSPACE("Backspace"),
    SEARCH("Search"),
    ENTER("Enter"),
    FIND_REMOTE("FindRemote"),
    VOLUME_DOWN("VolumeDown"),
    VOLUME_MUTE("VolumeMute"),
    VOLUME_UP("VolumeUp"),
    POWER_OFF("PowerOff"),
    POWER_ON("PowerOn"),
    CHANNELUP("ChannelUp"),
    CHANNELDOWN("ChannelDown"),
    INPUTTUNER("InputTuner"),
    INPUTHDMI1("InputHDMI1"),
    INPUTHDMI2("InputHDMI2"),
    INPUTHDMI3("InputHDMI3"),
    INPUTHDMI4("InputHDMI4"),
    INPUTAV1("InputAV1"),
    LIT_("Lit_");

    private final String key;

    private RokuKey(String str) {
        this.key = str;
    }

    public final String getKey() {
        return this.key;
    }
}
