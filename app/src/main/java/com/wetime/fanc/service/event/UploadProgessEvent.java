package com.wetime.fanc.service.event;

/**
 * Created by Administrator on 2018/4/17.
 */

public class UploadProgessEvent {
    float prgess;
    String simpleName;

    public UploadProgessEvent(float prgess, String simpleName) {
        this.prgess = prgess;
        this.simpleName = simpleName;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public float getPrgess() {
        return prgess;
    }

    public void setPrgess(float prgess) {
        this.prgess = prgess;
    }
}
