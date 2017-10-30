package com.po;

/**
 * Created by mu on 2017/7/20.
 */
public class AccessToken {
    private String access_token;
    private String expires_in;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccess_token() {

        return access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }
}
