package com.wetime.fanc.news.bean;

import java.util.List;

public class AllChannelListBean {


    /**
     * error : 0
     * msg :
     * data : {"user_news_category":[{"id":"1","name":"美食"},{"id":"4","name":"体育"},{"id":"5","name":"搞笑"},{"id":"6","name":"八卦"},{"id":"7","name":"科技"}],"all_news_category":[{"id":"4","name":"体育"},{"id":"14","name":"健身"},{"id":"6","name":"八卦"},{"id":"11","name":"彩票"},{"id":"10","name":"影视"},{"id":"5","name":"搞笑"},{"id":"15","name":"时尚"},{"id":"13","name":"星座"},{"id":"8","name":"汽车"},{"id":"2","name":"活动"},{"id":"3","name":"海口"},{"id":"12","name":"游戏"},{"id":"7","name":"科技"},{"id":"1","name":"美食"},{"id":"9","name":"萌宠"}]}
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
        private List<ChannelBean> user_news_category;
        private List<ChannelBean> all_news_category;

        public List<ChannelBean> getUser_news_category() {
            return user_news_category;
        }

        public void setUser_news_category(List<ChannelBean> user_news_category) {
            this.user_news_category = user_news_category;
        }

        public List<ChannelBean> getAll_news_category() {
            return all_news_category;
        }

        public void setAll_news_category(List<ChannelBean> all_news_category) {
            this.all_news_category = all_news_category;
        }


    }
}
