package com.po;

import java.util.List;

/**
 * Created by mu on 2017/7/20.
 */
public class TextMessage {
    private String ToUserName;
    private String FromUserName;
    private String MsgType;
    private long CreateTime;
    private int ArticleCount;
    private List<News> Articles;

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public int getArticleCount() {

        return ArticleCount;
    }

    public void setArticles(List<News> articles) {
        Articles = articles;
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

    public long getCreateTime() {
        return CreateTime;
    }



    public List<News> getArticles() {
        return Articles;
    }
}
