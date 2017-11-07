package com.wetime.fanc.main.bean;

import java.util.List;

/**
 * Created by zhoukang on 2017/10/10.
 */

public class PostFileResultBean {


    /**
     * error : 0
     * msg :
     * data : {"id":["306732","306723","306722"],"url":["http://shoptest.weishike.net/uimage/48/21/a4/6f/4821a46f72a01a10a4ae58da41f57ebd.jpg","http://shoptest.weishike.net/uimage/72/b4/49/12/72b4491283ea7ac6a4971b554cfe5043.jpg","http://shoptest.weishike.net/uimage/13/4f/ce/9c/134fce9c18babead7b9329d2be176d09.jpg"]}
     */

    private int error;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> id;
        private List<String> url;

        public List<String> getId() {
            return id;
        }

        public void setId(List<String> id) {
            this.id = id;
        }

        public List<String> getUrl() {
            return url;
        }

        public void setUrl(List<String> url) {
            this.url = url;
        }
    }
}
