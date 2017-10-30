package com.test;

import com.po.AccessToken;
import com.util.WeixinUtil;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by mu on 2017/7/20.
 */
public class WeixinTest {
    public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        AccessToken accessToken = getAccessToken();
        //upload(accessToken);
        menu(accessToken);
      //  getMenu(accessToken);
       // deleteMenu(accessToken);
    }

    private static void getMenu(AccessToken accessToken) {
        String menu = WeixinUtil.getMenu(accessToken.getAccess_token());
        System.out.println(menu);

    }
    private static void deleteMenu(AccessToken accessToken) {
        int result = WeixinUtil.deleteMenu(accessToken.getAccess_token());
        if (result==0){
            System.out.println("菜单删除成功");
        }


    }
    /**
     * 获取accessToken
     * @
     */
    private static AccessToken getAccessToken() {
        AccessToken accessToken= WeixinUtil.getAccessToken();
        System.out.println("票据"+accessToken.getAccess_token());
        System.out.println("时间"+accessToken.getExpires_in());
        return accessToken;
    }

    /**
     * 上传文件
     * @param accessToken
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws KeyManagementException
     */
    private static void upload(AccessToken accessToken) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
       String path="E:/照片.jpg";
        String medieID = WeixinUtil.upload(path,accessToken.getAccess_token(),"thumb");
        System.out.println(medieID);
    }

    /**
     * 设置菜单
     * @param accessToken
     */
    private static void menu(AccessToken accessToken) {
        String menu= JSONObject.fromObject(WeixinUtil.initMenu()).toString();
        System.out.println(menu);
        int result = WeixinUtil.createMenu(accessToken.getAccess_token(), menu);
        if(result==0)
        {
            System.out.println("创建菜单成功");
        }
        else{
            System.out.println("错误码："+result);
        }
    }
}
