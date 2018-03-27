package com.wetime.fanc.circle.bean;

public class LocStrBean {

    /**
     * error : 0
     * msg :
     * data : {"title":"玉沙京华城(西北门店)","address":"海南省海口市龙华区金贸街道玉沙路30-4号玉沙京华城(西北门店)","lng":"110.318696","lat":"20.028002"}
     */

    private int error;
    private String msg;
    private LocItemBean data;

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

    public LocItemBean getData() {
        return data;
    }

    public void setData(LocItemBean data) {
        this.data = data;
    }


}
