package com.wetime.fanc.my.bean;

/**
 * Created by Administrator on 2018/3/28.
 */

public class AttentionBean {

    /**
     * error : 0
     * msg : 关注成功
     * data : null
     */

    private int error;
    private String msg;
    private Object data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
