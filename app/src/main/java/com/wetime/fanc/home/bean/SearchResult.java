package com.wetime.fanc.home.bean;

import java.util.List;

/**
 * Created by zhoukang on 2017/11/13.
 */

public class SearchResult {


    /**
     * error : 0
     * msg :
     * data : {"merchants":[{"mid":"29","name":"澳门仔茶餐厅","distance":"距167m","url":"http://www.baidu.com"},{"mid":"2069","name":"澳门皇茶","distance":"距741m","url":"http://www.baidu.com"},{"mid":"2504","name":"伟氏澳门茶餐厅（龙华路店）","distance":"距1.86km","url":"http://www.baidu.com"}]}
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
        private List<MerchantsBean> merchants;

        public List<MerchantsBean> getMerchants() {
            return merchants;
        }

        public void setMerchants(List<MerchantsBean> merchants) {
            this.merchants = merchants;
        }

        public static class MerchantsBean {
            /**
             * mid : 29
             * name : 澳门仔茶餐厅
             * distance : 距167m
             * url : http://www.baidu.com
             */

            private String mid;
            private String name;
            private String distance;
            private String url;

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
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
