package com.connectsdk.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MediaInfo {
    private List<ImageInfo> allImages;
    private String description;
    private long duration;
    private String mimeType;
    private SubtitleInfo subtitleInfo;
    private String title;
    private String url;

    
    public static class Builder {
        private List<ImageInfo> allImages;
        private String description;
        private String mimeType;
        private SubtitleInfo subtitleInfo;
        private String title;
        private String url;

        public Builder(String mediaUrl, String mediaMimeType) {
            this.url = mediaUrl;
            this.mimeType = mediaMimeType;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setIcon(String iconUrl) {
            if (iconUrl != null) {
                createImagesList();
                this.allImages.set(0, new ImageInfo(iconUrl));
            }
            return this;
        }

        public Builder setIcon(ImageInfo icon) {
            if (icon != null) {
                createImagesList();
                this.allImages.set(0, icon);
            }
            return this;
        }

        public Builder setSubtitleInfo(SubtitleInfo subtitleInfo) {
            this.subtitleInfo = subtitleInfo;
            return this;
        }

        public MediaInfo build() {
            return new MediaInfo(this);
        }

        private void createImagesList() {
            if (this.allImages == null) {
                this.allImages = new ArrayList(Collections.nCopies(1, null));
            }
        }
    }

    private MediaInfo(Builder builder) {
        this.url = builder.url;
        this.mimeType = builder.mimeType;
        this.title = builder.title;
        this.description = builder.description;
        this.subtitleInfo = builder.subtitleInfo;
        this.allImages = builder.allImages;
    }

    @Deprecated
    public MediaInfo(String url, String mimeType, String title, String description) {
        this.url = url;
        this.mimeType = mimeType;
        this.title = title;
        this.description = description;
    }

    @Deprecated
    public MediaInfo(String url, String mimeType, String title, String description, List<ImageInfo> allImages) {
        this(url, mimeType, title, description);
        this.allImages = allImages;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    @Deprecated
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getTitle() {
        return this.title;
    }

    @Deprecated
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    @Deprecated
    public void setDescription(String description) {
        this.description = description;
    }

    public List<ImageInfo> getImages() {
        return this.allImages;
    }

    @Deprecated
    public void setImages(List<ImageInfo> images) {
        this.allImages = images;
    }

    public long getDuration() {
        return this.duration;
    }

    @Deprecated
    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return this.url;
    }

    @Deprecated
    public void setUrl(String url) {
        this.url = url;
    }

    public SubtitleInfo getSubtitleInfo() {
        return this.subtitleInfo;
    }

    @Deprecated
    public void addImages(ImageInfo... images) {
        if (images == null) {
            return;
        }
        ArrayList arrayList = new ArrayList<>();
        Collections.addAll(arrayList, images);
        setImages(arrayList);
    }
}
