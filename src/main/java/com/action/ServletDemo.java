package com.action;

import com.po.BasicMessage;
import com.util.CheckUtil;
import com.util.MessageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * Created by mu on 2017/7/19.
 */
public class ServletDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String repMsg=null;
        try {
            Map<String, String> map = MessageUtil.xml2map(request);

            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            if("text".equals(msgType)){
                if("1".equals(content)){
                    repMsg=MessageUtil.initTextMessage(toUserName,fromUserName);
                }
                else
                if("2".equals(content)){
                    repMsg=MessageUtil.initImageMessage(toUserName,fromUserName);
                }
                else if (("3".equals(content))){
                    repMsg=MessageUtil.initMusicMessage(toUserName,fromUserName);
                }
                else{
                    repMsg =MessageUtil.initCopy(toUserName,fromUserName,content);
                }
            }
            else if(MessageUtil.MSGTYPE_EVENT.equals(msgType)){
                String event=map.get("Event");
                if(MessageUtil.EVENT_SUBSCRIBE.equals(event)){
                    repMsg=MessageUtil.initText(toUserName,fromUserName,MessageUtil.mainMenu());
                }
                else if (MessageUtil.EVENT_CLICK.equals(event)){
                    repMsg=MessageUtil.initText(toUserName,fromUserName,MessageUtil.mainMenu());
                }
                else if(MessageUtil.EVENT_VIEW.equals(event)){
                    String url=map.get("EventKey");
                    repMsg=MessageUtil.initText(toUserName,fromUserName,url);
                }
                else if(MessageUtil.EVENT_LOCATION.equals(event)){
                    String key=map.get("EventKey");
                    repMsg=MessageUtil.initText(toUserName,fromUserName,key);
                }
            }
            else if(MessageUtil.MSGTYPE_LOCATION.equals(msgType)){
                String label=map.get("Label");
                repMsg=MessageUtil.initText(toUserName,fromUserName,label);
            }
            response.getWriter().print(repMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String echostr = request.getParameter("echostr");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        if(CheckUtil.checkSignnature(signature,timestamp,nonce)){
            writer.print(echostr);
        }
    }
}
