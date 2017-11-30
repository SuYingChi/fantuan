package com.wetime.fanc.push.event;

/**
 * Created by zhoukang on 2017/11/29.
 */

public class RegistPushSuccessEvent {

    private String id;

    public RegistPushSuccessEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
