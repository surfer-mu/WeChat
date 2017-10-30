package com.po;

/**
 * Created by mu on 2017/7/20.
 */
public class Music {
    private String Title;
    private String Description;
    private String MusicUrl;
    private String HQMusicUrl;
    private String ThumbMediaId;

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    public String getTitle() {

        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    @Override
    public String toString() {
        return "Music{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", MusicUrl='" + MusicUrl + '\'' +
                ", HQMusicUrl='" + HQMusicUrl + '\'' +
                ", ThumbMediaId='" + ThumbMediaId + '\'' +
                '}';
    }
}
