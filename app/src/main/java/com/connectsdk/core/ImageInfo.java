package com.connectsdk.core;


public class ImageInfo {
    private int height;
    private ImageType type;
    private String url;
    private int width;

    
    public enum ImageType {
        Thumb,
        Video_Poster,
        Album_Art,
        Unknown
    }

    public ImageInfo(String url) {
        this.url = url;
    }

    public ImageInfo(String url, ImageType type, int width, int height) {
        this(url);
        this.type = type;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageType getType() {
        return this.type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImageInfo imageInfo = (ImageInfo) o;
        return getUrl() != null ? getUrl().equals(imageInfo.getUrl()) : imageInfo.getUrl() == null;
    }

    public int hashCode() {
        if (getUrl() != null) {
            return getUrl().hashCode();
        }
        return 0;
    }
}
