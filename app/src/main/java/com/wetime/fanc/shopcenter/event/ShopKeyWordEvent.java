package com.wetime.fanc.shopcenter.event;

/**
 * Created by zhoukang on 2017/12/7.
 */

public class ShopKeyWordEvent {
    private String key;

    public ShopKeyWordEvent(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
