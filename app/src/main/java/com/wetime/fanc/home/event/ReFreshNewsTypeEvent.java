package com.wetime.fanc.home.event;

/**
 * Created by zhoukang on 2018/2/4.
 */

public class ReFreshNewsTypeEvent {
    private int type;

    public ReFreshNewsTypeEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
