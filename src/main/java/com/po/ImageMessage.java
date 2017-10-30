package com.po;

/**
 * Created by mu on 2017/7/20.
 */
public class ImageMessage extends  BasicMessage {
    private Image Image;

    public void setImage(com.po.Image image) {
        Image = image;
    }

    public com.po.Image getImage() {

        return Image;
    }

    @Override
    public String toString() {
        return "ImageMessage{" +
                "Image=" + Image +
                '}';
    }
}
