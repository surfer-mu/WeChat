package com.util;

import com.po.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by mu on 2017/7/19.
 */
public class MessageUtil {
    public static final String MSGTYPE_TEXT = "text";
    public static final String MSGTYPE_NEWS = "news";
    public static final String MSGTYPE_IMAGE = "image";
    public static final String MSGTYPE_VOICE = "voice";
    public static final String MSGTYPE_MUSIC = "music";
    public static final String MSGTYPE_LOCATION = "location";
    public static final String MSGTYPE_LINK = "link";
    public static final String MSGTYPE_EVENT = "event";
    public static final String EVENT_SUBSCRIBE = "subscribe";
    public static final String EVENT_SCAN = "SCAN";
    public static final String EVENT_LOCATION = "location_select";
    public static final String EVENT_CLICK = "CLICK";
    public static final String EVENT_VIEW = "VIEW";
    public static final String EVENT_SCANCODE_PUSH = "scancode_push";
    public static Map<String,String> xml2map(HttpServletRequest request){
        Map<String,String> map=new HashMap<String,String>();
        SAXReader reader=new SAXReader();
        try {
            ServletInputStream inputStream = request.getInputStream();
            Document read = reader.read(inputStream);
            Element root = read.getRootElement();
            List<Element> list = root.elements();
            for(Element element:list){
                map.put(element.getName(),element.getText());
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return map;
    }
    public static String map2xml(BasicMessage message){
        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("xml",message.getClass());
        String s = xstream.toXML(message);
        return s;
    }
    public static String initCopy(String toUserName, String fromUserName, String content){
        BasicMessage xmlMessage=new BasicMessage();
        xmlMessage.setToUserName(fromUserName);
        xmlMessage.setFromUserName(toUserName);
        long time = new Date().getTime();
        xmlMessage.setCreateTime(time);
        xmlMessage.setMsgType("text");
        xmlMessage.setContent("你刚发送的消息是："+new Date(time).toString()+"—— " +content);
        String s = MessageUtil.map2xml(xmlMessage);
        return s;
    }
    public  static String initImageMessage(String toUser,String fromUser){
        String message=null;
        Image image=new Image();
        image.setMediaId("8KusCdWlJCLJVQujhiw5VXi-EbvMj1vRdRWRUdCoyq5QqsiDP_uq_vLaDp8waSvv");
        ImageMessage imageMessage=new ImageMessage();
        imageMessage.setFromUserName(toUser);
        imageMessage.setToUserName(fromUser);
        imageMessage.setMsgType(MSGTYPE_IMAGE);
        imageMessage.setCreateTime(new Date().getTime());
        imageMessage.setImage(image);
        message=imageMessageToXml(imageMessage);
        return  message;
    }

    private static String imageMessageToXml(BasicMessage imageMessage) {
        XStream xStream=new XStream(new StaxDriver());
       xStream.alias("xml",imageMessage.getClass());
        String s = xStream.toXML(imageMessage);
        return s;
    }
    private static String musicMessageToXml(BasicMessage musicMessage) {
        XStream xStream=new XStream(new StaxDriver());
        xStream.alias("xml",musicMessage.getClass());
        String s = xStream.toXML(musicMessage);
        return s;
    }
    public static String initMusicMessage(String toUserName,String fromUserName){
        String message=null;
        Music music=new Music();
        music.setThumbMediaId("NJvw3szBGVX4sPyKijlaz-acbaskGcDwFsdp3sYp8Shj0t34BslhrZZH83SFZ0zO");
        music.setTitle("Merry Christmas Mr. Lawrence");
        music.setDescription("演唱家：双成");

        music.setMusicUrl("http://r9it3f.natappfree.cc/music/FYI-MerryChristmasMr.Lawrence.mp3");
        music.setHQMusicUrl("http://r9it3f.natappfree.cc/music/FYI-MerryChristmasMr.Lawrence.mp3");
        MusicMessage musicMessage=new MusicMessage();
        musicMessage.setMusic(music);
        musicMessage.setFromUserName(toUserName);
        musicMessage.setToUserName(fromUserName);
        musicMessage.setCreateTime(new Date().getTime());
        musicMessage.setMsgType(MSGTYPE_MUSIC);
        message=musicMessageToXml(musicMessage);
        return  message;

    }
    /**
     * 回复多图文消息
     */
    public static String initTextMessage(String toUserName,String fromUserName){
        List<News> list=new ArrayList<News>();
        News news=new News();
        news.setDescription("这确实是一条图文描述");
        news.setPicUrl("http://r9it3f.natappfree.cc/image/Tulips.jpg");
        news.setTitle("删前必看！一群程序猿深夜未睡竟在干这事！");
        news.setUrl("www.baidu.com");
        list.add(news);
        news=new News();
        news.setDescription("这确实是第二条图文描述");
        news.setPicUrl("http://r9it3f.natappfree.cc/image/Penguins.jpg");
        news.setTitle("震惊！大学生竟要开发公众号，男默女泪！");
        news.setUrl("www.taobao.com");
        list.add(news);
        TextMessage textMessage=new TextMessage();
        textMessage.setArticles(list);
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MSGTYPE_NEWS);
        textMessage.setArticleCount(list.size());
        String message=newsMessageToXml(textMessage);
        return message;
    }

    /**
     * 回复图文转xml
     * @param textMessage
     * @return
     */
    public static String newsMessageToXml(TextMessage textMessage){
        XStream xStream=new XStream(new DomDriver());
        xStream.alias("xml",textMessage.getClass());
        xStream.alias("item",News.class);
        String xml = xStream.toXML(textMessage);
        return  xml;
    }

    /**
     * 自定义回复消息
     * @param toUserName
     * @param fromUserName
     * @param content  消息
     * @return
     */
    public static String initText(String toUserName,String fromUserName,String content){
        String message=null;
        BasicMessage basemessage=new BasicMessage();
        basemessage.setToUserName(fromUserName);
        basemessage.setFromUserName(toUserName);
        basemessage.setCreateTime(new Date().getTime());
        basemessage.setMsgType("text");
        basemessage.setContent(content);
        message=map2xml(basemessage);
        return message;
    }
    public static String initEventSubscribe(String toUserName,String fromUserName){
        String message=null;
        Event event=new Event();
        event.setToUserName(fromUserName);
        event.setFromUserName(toUserName);
        event.setCreateTime(new Date().getTime());
        event.setMsgType("event");
        event.setEvent("subscribe");
        message=textToXml(event);
        return message;
    }

    private static String textToXml(Event event) {
        XStream xStream=new XStream(new StaxDriver());
        xStream.alias("xml",Event.class);
        return xStream.toXML(event);
    }
    public static String mainMenu(){
        StringBuffer sb=new StringBuffer();
        sb.append("请根据提示回复获取信息\n");
        sb.append("1 获取图文消息\n");
        sb.append("2 获取西邮照片\n");
        sb.append("3 获取天籁之音\n");
        sb.append("回复其他学你说话\n");
        return sb.toString();
    }
}
