package com.wetime.fanc.service.event;

/**
 * Created by Administrator on 2018/4/17.
 */

public class UploadEvent {
    /**
     * id : 84
     */

    private String id;

    public UploadEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
