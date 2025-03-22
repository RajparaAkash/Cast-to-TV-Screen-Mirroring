package com.casttotv.screenmirroring.chromecast.smart.tv.Model;

public class Language {

    public String languageCode;
    public int languageIcon;
    public String languageName;
    public boolean isSelected;

    public Language(String languageCode, int languageIcon, String languageName, boolean isSelected) {
        this.languageCode = languageCode;
        this.languageIcon = languageIcon;
        this.languageName = languageName;
        this.isSelected = isSelected;
    }
}
