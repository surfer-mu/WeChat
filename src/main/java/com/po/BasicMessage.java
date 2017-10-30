package com.po;

/**
 * Created by mu on 2017/7/20.
 */
public class BasicMessage {
    private String ToUserName;
    private String FromUserName;
    private String MsgType;
    private String Content;
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

    public void setContent(String content) {
        Content = content;
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

    public String getContent() {
        return Content;
    }

    public long getCreateTime() {
        return CreateTime;
    }
}
