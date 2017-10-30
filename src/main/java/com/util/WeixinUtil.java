package com.util;


import com.menu.Button;
import com.menu.ClickButton;
import com.menu.Menu;
import com.menu.ViewButton;
import com.po.AccessToken;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by mu on 2017/7/19.
 */
public class WeixinUtil {
    public static final String MESSAGGE_IMAGE="image";
    public static final String AppID="wxcd60bef4dff8529e";
    public static final String AppSecret= "ac9c8df2943e28805931c4a8d2269dd2";
    public static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static final String UPLOAD_URL="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    public static final String CREATE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public static final String Query_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    public static final String DELETE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    /**
     * get请求微信
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url){
        CloseableHttpClient httpClient=HttpClientBuilder.create().build();
        HttpGet httpGet =new HttpGet(url);
        JSONObject jsonObject=null;
        try {
            HttpResponse response=httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity!=null){
                String result= EntityUtils.toString(entity,"UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * post请求微信
     * @param url
     * @param outStr
     * @return
     */
    public static JSONObject doPostStr(String url,String outStr){
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        HttpPost httpPost=new HttpPost(url);
        JSONObject jsonObject=null;
        try {
            httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8") ;
            jsonObject = JSONObject.fromObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  jsonObject;
    }
    /**
     * 请求得到token
     * @param
     * @return
     */
    public static AccessToken getAccessToken(){
        AccessToken accessToken=new AccessToken();
        String url=ACCESS_TOKEN_URL.replace("APPID",AppID).replace("APPSECRET",AppSecret);
        JSONObject jsonObject = doGetStr(url);
        System.out.println(url);
        System.out.println(jsonObject.toString());;
        if(jsonObject!=null){
            accessToken.setAccess_token(jsonObject.getString("access_token"));
            accessToken.setExpires_in(jsonObject.getString("expires_in"));
        }
        return accessToken;
    }
    public static String upload(String filePath, String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在");
        }

        String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);

        URL urlObj = new URL(url);
        //连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);

        //设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");

        //设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");

        //获取输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        //输出表头
        out.write(head);

        //文件正文部分
        //把文件以流文件的方式推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();

        //结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分割线

        out.write(foot);

        out.flush();
        out.close();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try {
            //定义BufferedReader输入流来读取url的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        System.out.println(result);

        JSONObject jsonObj = JSONObject.fromObject(result);
        System.out.println(jsonObj);
        String typeName = "media_id";
        if(!"image".equals(type)){
            typeName = type + "_media_id";
        }
        String mediaId = jsonObj.getString(typeName);
        return mediaId;
    }
    public static Menu initMenu(){
        Menu menu=new Menu();

        ClickButton button11=new ClickButton();
        button11.setName("click菜单");
        button11.setType("click");
        button11.setKey("11");
     /*   ClickButton button12=new ClickButton();
        button12.setName("扫码");
        button12.setType("scancode_push");
        button12.setKey("12");
        button11.setSub_button(new Button[]{button12});*/

        ViewButton button21=new ViewButton();
        button21.setName("view菜单");
        button21.setType("view");
        button21.setUrl("https://git.oschina.net/eatup");
        ClickButton button31=new ClickButton();
        button31.setName("扫码事件");
        button31.setType("scancode_push");
        button31.setKey("31");
        ClickButton button32=new ClickButton();
        button32.setName("地理位置");
        button32.setType("location_select");
        button32.setKey("32");

        Button button=new Button();
        button.setName("菜单");
        button.setSub_button(new Button[]{button31,button32});
        menu.setButton(new Button[]{button11,button21,button});
        return menu;
    }
    public static int createMenu(String token,String menu){
        int result =1;
        String url=CREATE_MENU_URL.replace("ACCESS_TOKEN",token);
        JSONObject jsonObject=doPostStr(url,menu);
        if(jsonObject!=null)
        {
            result= jsonObject.getInt("errcode");
        }
        return  result;
    }


    public static String getMenu(String accessToken) {
        String url=Query_MENU_URL.replace("ACCESS_TOKEN",accessToken);
        JSONObject jsonObject=doGetStr(url);
        return jsonObject.toString();
    }
    public static  final int deleteMenu(String accessToken){
       int result=1;
        String url=DELETE_MENU_URL.replace("ACCESS_TOKEN",accessToken);
        JSONObject jsonObject=doGetStr(url);
        if(jsonObject!=null){
            result=jsonObject.getInt("errcode");
        }
        return result;
    }
}
