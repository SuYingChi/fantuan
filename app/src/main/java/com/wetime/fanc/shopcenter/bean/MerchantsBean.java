package com.wetime.fanc.shopcenter.bean;

import java.util.List;

/**
 * Created by zhoukang on 2017/11/8.
 */

public class MerchantsBean {

    /**
     * mid : 24
     * name : 香川园
     * score : 0
     * average_spend : 0
     * logo : 138883
     * dinning : 0
     * cashier : 1
     * promotion_list : [{"ico":"","name":"1种优惠券可免费领取"},{"ico":"","name":"满20减3"},{"ico":"","name":"满20赠3"},{"ico":"","name":"买单立享8.00折优惠"}]
     * voucher_groupon_list : [{"pid":"35","amount":"160","sales":"0","name":"200元代金券1张"},{"pid":"36","amount":"160","sales":"0","name":"200元代金券1张"},{"pid":"41","amount":"160","sales":"0","name":"200元代金券1张"},{"pid":"106","amount":"160","sales":"0","name":"200元代金券1张"},{"pid":"108","name":"1111","amount":"50","sales":"0"}]
     */

    private String mid;
    private String name;
    private Float score;
    private String average_spend;
    private String logo;
    private String spider;
    private String distance;
    private String category_name;
    private String detail_url;
    private String floor;
    private String mall_name;
    private String mall_url;

    public String getMall_url() {
        return mall_url;
    }

    public void setMall_url(String mall_url) {
        this.mall_url = mall_url;
    }

    public String getMall_name() {
        return mall_name;
    }

    public void setMall_name(String mall_name) {
        this.mall_name = mall_name;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    private boolean zhe = true;

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public boolean isZhe() {
        return zhe;
    }

    public void setZhe(boolean zhe) {
        this.zhe = zhe;
    }


    public String getSpider() {
        return spider;
    }

    public void setSpider(String spider) {
        this.spider = spider;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    private List<PromotionListBean> promotion_list;
    private List<VoucherGrouponListBean> voucher_groupon_list;

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

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getAverage_spend() {
        return average_spend;
    }

    public void setAverage_spend(String average_spend) {
        this.average_spend = average_spend;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    public List<PromotionListBean> getPromotion_list() {
        return promotion_list;
    }

    public void setPromotion_list(List<PromotionListBean> promotion_list) {
        this.promotion_list = promotion_list;
    }

    public List<VoucherGrouponListBean> getVoucher_groupon_list() {
        return voucher_groupon_list;
    }

    public void setVoucher_groupon_list(List<VoucherGrouponListBean> voucher_groupon_list) {
        this.voucher_groupon_list = voucher_groupon_list;
    }

    public static class PromotionListBean {
        /**
         * ico :
         * name : 1种优惠券可免费领取
         */

        private String ico;
        private String name;

        public String getIco() {
            return ico;
        }

        public void setIco(String ico) {
            this.ico = ico;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class VoucherGrouponListBean {
        /**
         * pid : 35
         * amount : 160
         * sales : 0
         * name : 200元代金券1张
         */

        private String pid;
        private String amount;
        private String sales;
        private String name;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
