package com.po;

/**
 * Created by mu on 2017/7/20.
 */
public class MusicMessage extends  BasicMessage {
    private Music Music;

    public void setMusic(com.po.Music music) {
        Music = music;
    }

    public com.po.Music getMusic() {

        return Music;
    }
}
