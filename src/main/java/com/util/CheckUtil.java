package com.util;

import java.util.Arrays;

/**
 * Created by mu on 2017/7/19.
 */
public class CheckUtil {
    public static final String token="msc";

    /**
     * 连接公众号sha1加密匹配
     * @param signnature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignnature(String signnature,String timestamp,String nonce){
        String[] arr = {token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuffer buffer=new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            buffer.append(arr[i]);
        }
        String sha1 = SHA1.sha1(buffer.toString());
        return sha1.equals(signnature);
    }

}
