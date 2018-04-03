package com.wetime.fanc.news.bean;

import java.util.List;

public class FocusTitleList {

    /**
     * error : 0
     * msg :
     * data : [{"id":"0","name":"推荐"},{"id":"4","name":"体育"},{"id":"14","name":"健身"},{"id":"6","name":"八卦"},{"id":"10","name":"影视"},{"id":"5","name":"搞笑"},{"id":"15","name":"时尚"},{"id":"13","name":"星座"},{"id":"8","name":"汽车"},{"id":"2","name":"活动"},{"id":"3","name":"海口"},{"id":"12","name":"游戏"},{"id":"7","name":"科技"},{"id":"1","name":"美食"},{"id":"9","name":"萌宠"}]
     */

    private int error;
    private String msg;
    private List<FocusTitleBean> data;

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

    public List<FocusTitleBean> getData() {
        return data;
    }

    public void setData(List<FocusTitleBean> data) {
        this.data = data;
    }

}
