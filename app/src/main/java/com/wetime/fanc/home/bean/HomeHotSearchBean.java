package com.wetime.fanc.home.bean;

import java.util.List;

/**
 * Created by zhoukang on 2017/11/13.
 */

public class HomeHotSearchBean {


    /**
     * error : 0
     * msg :
     * data : {"merchant_hots":[{"mid":"2999","name":"七三指弹吉他工作室","distance":"0"},{"mid":"35","name":"秘汁道小龙虾(世贸店)","distance":"0"},{"mid":"457","name":"印度尼泊尔菜","distance":"0"},{"mid":"544","name":"探鱼（京华城店）","distance":"0"},{"mid":"271","name":"椰语堂（京华城店）","distance":"0"},{"mid":"1749","name":"哈根达斯（海口京华城店）","distance":"0"},{"mid":"2534","name":"沸爱哥意大利餐厅","distance":"0"},{"mid":"1621","name":"the Piece贝絲","distance":"0"},{"mid":"2529","name":"必胜客（玉沙店）","distance":"0"},{"mid":"1906","name":"德奈斯汉堡","distance":"0"}],"mall_hots":[{"id":"11","name":"京华城","distance":"100"},{"id":"4","name":"宜欣城","distance":"628"},{"id":"9","name":"上邦百汇城","distance":"925"},{"id":"24","name":"海秀890广场","distance":"1851"},{"id":"16","name":"亿圣和广场","distance":"2232"},{"id":"7","name":"万国大都会","distance":"2233"},{"id":"3","name":"友谊广场","distance":"2265"},{"id":"6","name":"香樟林风情街","distance":"2295"},{"id":"10","name":"骑楼小吃街","distance":"2307"},{"id":"8","name":"泰龙城","distance":"2373"}]}
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
        private List<MerchantHotsBean> merchant_hots;
        private List<MallHotsBean> mall_hots;

        public List<MerchantHotsBean> getMerchant_hots() {
            return merchant_hots;
        }

        public void setMerchant_hots(List<MerchantHotsBean> merchant_hots) {
            this.merchant_hots = merchant_hots;
        }

        public List<MallHotsBean> getMall_hots() {
            return mall_hots;
        }

        public void setMall_hots(List<MallHotsBean> mall_hots) {
            this.mall_hots = mall_hots;
        }

        public static class MerchantHotsBean {
            /**
             * mid : 2999
             * name : 七三指弹吉他工作室
             * distance : 0
             */

            private String mid;
            private String name;
            private String distance;

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
        }

        public static class MallHotsBean {
            /**
             * id : 11
             * name : 京华城
             * distance : 100
             */

            private String id;
            private String name;
            private String distance;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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
        }
    }
}
