package com.po;

/**
 * Created by mu on 2017/7/20.
 */
public class News {
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setTitle(String title) {

        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
