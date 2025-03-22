package com.connectsdk.core;


public class SubtitleInfo {
    private final String label;
    private final String language;
    private final String mimeType;
    private final String url;

    
    public static class Builder {
        private String label;
        private String language;
        private String mimeType;
        private String url;

        public Builder(String url) {
            this.url = url;
        }

        public Builder setMimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public Builder setLabel(String label) {
            this.label = label;
            return this;
        }

        public Builder setLanguage(String language) {
            this.language = language;
            return this;
        }

        public SubtitleInfo build() {
            return new SubtitleInfo(this);
        }
    }

    private SubtitleInfo(Builder builder) {
        this.url = builder.url;
        this.mimeType = builder.mimeType;
        this.label = builder.label;
        this.language = builder.language;
    }

    public String getUrl() {
        return this.url;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getLabel() {
        return this.label;
    }

    public String getLanguage() {
        return this.language;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubtitleInfo subtitleInfo = (SubtitleInfo) o;
        if (getUrl() == null ? subtitleInfo.getUrl() == null : getUrl().equals(subtitleInfo.getUrl())) {
            if (getMimeType() != null) {
                if (getMimeType().equals(subtitleInfo.getMimeType())) {
                    return true;
                }
            } else if (subtitleInfo.getMimeType() == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return ((getUrl() != null ? getUrl().hashCode() : 0) * 31) + (getMimeType() != null ? getMimeType().hashCode() : 0);
    }
}
