package com.wetime.fanc.home.event;

/**
 * Created by zhoukang on 2017/11/29.
 */

public class SwichFragEvent {
    private int pos;

    public SwichFragEvent(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
