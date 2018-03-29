package com.wetime.fanc.news.bean;

import java.util.List;

public class AllChannelListBean {


    /**
     * error : 0
     * msg :
     * data : [{"id":"1","name":"美食","is_default":"1","is_show":"1","order":"1"},{"id":"4","name":"体育","is_default":"1","is_show":"1","order":"1"},{"id":"6","name":"八卦","is_default":"1","is_show":"1","order":"1"},{"id":"5","name":"搞笑","is_default":"1","is_show":"1","order":"1"},{"id":"7","name":"科技","is_default":"1","is_show":"1","order":"1"},{"id":"8","name":"汽车","is_default":"0","is_show":"1","order":"0"},{"id":"9","name":"萌宠","is_default":"0","is_show":"1","order":"0"},{"id":"10","name":"影视","is_default":"0","is_show":"1","order":"0"},{"id":"11","name":"彩票","is_default":"0","is_show":"1","order":"0"},{"id":"12","name":"游戏","is_default":"0","is_show":"1","order":"0"},{"id":"13","name":"星座","is_default":"0","is_show":"1","order":"0"},{"id":"14","name":"健身","is_default":"0","is_show":"1","order":"0"},{"id":"15","name":"时尚","is_default":"0","is_show":"1","order":"0"}]
     */

    private int error;
    private String msg;
    private List<ChannelBean> data;

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

    public List<ChannelBean> getData() {
        return data;
    }

    public void setData(List<ChannelBean> data) {
        this.data = data;
    }


}
