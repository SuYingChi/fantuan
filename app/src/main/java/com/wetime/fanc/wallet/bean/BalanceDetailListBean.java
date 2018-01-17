package com.wetime.fanc.wallet.bean;

import java.util.List;

/**
 * Created by zhoukang on 2018/1/17.
 */

public class BalanceDetailListBean {


    /**
     * error : 0
     * msg :
     * data : {"list":[{"id":"5","uid":"38","money":"-¥0.01","time":"2018-01-17 08:52:50","title":"余额支付--范团64(友谊阳光城)","desc":"","url":"https://fanttest.fantuanlife.com/index.html#/user/wallet/detail?id=5"},{"id":"6","uid":"38","money":"-¥0.01","time":"2018-01-17 08:58:13","title":"余额支付--范团64(友谊阳光城)","desc":"","url":"https://fanttest.fantuanlife.com/index.html#/user/wallet/detail?id=6"},{"id":"7","uid":"38","money":"-¥0.01","time":"2018-01-17 08:58:27","title":"余额支付--范团64(友谊阳光城)","desc":"","url":"https://fanttest.fantuanlife.com/index.html#/user/wallet/detail?id=7"},{"id":"8","uid":"38","money":"-¥0.01","time":"2018-01-17 08:58:39","title":"余额支付--范团64(友谊阳光城)","desc":"","url":"https://fanttest.fantuanlife.com/index.html#/user/wallet/detail?id=8"}],"page":{"total":4,"total_page":1,"limit":10,"pn":1,"is_end":true}}
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
        /**
         * list : [{"id":"5","uid":"38","money":"-¥0.01","time":"2018-01-17 08:52:50","title":"余额支付--范团64(友谊阳光城)","desc":"","url":"https://fanttest.fantuanlife.com/index.html#/user/wallet/detail?id=5"},{"id":"6","uid":"38","money":"-¥0.01","time":"2018-01-17 08:58:13","title":"余额支付--范团64(友谊阳光城)","desc":"","url":"https://fanttest.fantuanlife.com/index.html#/user/wallet/detail?id=6"},{"id":"7","uid":"38","money":"-¥0.01","time":"2018-01-17 08:58:27","title":"余额支付--范团64(友谊阳光城)","desc":"","url":"https://fanttest.fantuanlife.com/index.html#/user/wallet/detail?id=7"},{"id":"8","uid":"38","money":"-¥0.01","time":"2018-01-17 08:58:39","title":"余额支付--范团64(友谊阳光城)","desc":"","url":"https://fanttest.fantuanlife.com/index.html#/user/wallet/detail?id=8"}]
         * page : {"total":4,"total_page":1,"limit":10,"pn":1,"is_end":true}
         */

        private PageBean page;
        private List<ListBean> list;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PageBean {
            /**
             * total : 4
             * total_page : 1
             * limit : 10
             * pn : 1
             * is_end : true
             */

            private int total;
            private int total_page;
            private int limit;
            private int pn;
            private boolean is_end;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getTotal_page() {
                return total_page;
            }

            public void setTotal_page(int total_page) {
                this.total_page = total_page;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public int getPn() {
                return pn;
            }

            public void setPn(int pn) {
                this.pn = pn;
            }

            public boolean isIs_end() {
                return is_end;
            }

            public void setIs_end(boolean is_end) {
                this.is_end = is_end;
            }
        }

        public static class ListBean {
            /**
             * id : 5
             * uid : 38
             * money : -¥0.01
             * time : 2018-01-17 08:52:50
             * title : 余额支付--范团64(友谊阳光城)
             * desc :
             * url : https://fanttest.fantuanlife.com/index.html#/user/wallet/detail?id=5
             */

            private String id;
            private String uid;
            private String money;
            private String time;
            private String title;
            private String desc;
            private String url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
