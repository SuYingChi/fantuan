package com.wetime.fanc.my.event;

/**
 * Created by Administrator on 2018/3/28.
 */

public class AttentionFriendEvent {
    String type;
    String id;

    public AttentionFriendEvent(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
