package com.po;

/**
 * Created by mu on 2017/7/20.
 */
public class Image {
    public String MediaId;

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getMediaId() {

        return MediaId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "MediaId='" + MediaId + '\'' +
                '}';
    }
}
