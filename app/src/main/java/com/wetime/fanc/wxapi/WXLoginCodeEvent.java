package com.wetime.fanc.wxapi;

/**
 * Created by zhoukang on 2017/11/6.
 */

public class WXLoginCodeEvent {
    private String code;

    public WXLoginCodeEvent(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
