package com.wetime.fanc.home.event;

/**
 * Created by zhoukang on 2018/2/4.
 */

public class ReFreshNewsTypeEvent {
    private String type;

    public ReFreshNewsTypeEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
