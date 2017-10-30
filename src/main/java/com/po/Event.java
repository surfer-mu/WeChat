package com.po;

/**
 * Created by mu on 2017/7/21.
 */
public class Event {
    private String ToUserName;
    private String FromUserName;
    private String MsgType;
    private String Event;
    private long CreateTime;

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getToUserName() {

        return ToUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public String getMsgType() {
        return MsgType;
    }

    public String getEvent() {
        return Event;
    }

    public long getCreateTime() {
        return CreateTime;
    }
}
