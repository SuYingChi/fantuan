package com.wetime.fanc.login.event;

/**
 * Created by zhoukang on 2017/11/2.
 */

public class LoginEvent {
    private String str;

    public LoginEvent(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
