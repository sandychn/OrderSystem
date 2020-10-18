package com.example.pojo;

import java.io.Serializable;

public class Cookie implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userID; //用户ID
    private String cookieString;  //cookie字符串
    private long expireTime;  //过期时间

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCookieString() {
        return cookieString;
    }

    public void setCookieString(String cookieString) {
        this.cookieString = cookieString;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
