package com.wetime.fanc.service.event;

/**
 * Created by Administrator on 2018/4/17.
 */

public class UploadProgessEvent {
    float prgess;

    public UploadProgessEvent(float prgess) {
        this.prgess = prgess;
    }

    public float getPrgess() {
        return prgess;
    }

    public void setPrgess(float prgess) {
        this.prgess = prgess;
    }
}
