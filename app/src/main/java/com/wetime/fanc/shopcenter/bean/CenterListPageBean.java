package com.wetime.fanc.shopcenter.bean;

import java.util.List;

/**
 * Created by zhoukang on 2017/11/8.
 */

public class CenterListPageBean {


    /**
     * error : 0
     * msg :
     * data : {"merchants":[{"mid":"24","name":"香川园","score":"0","average_spend":"0","logo":"138883","dinning":"0","cashier":"1","promotion_list":[{"ico":"","name":"1种优惠券可免费领取"},{"ico":"","name":"满20减3"},{"ico":"","name":"满20赠3"},{"ico":"","name":"买单立享8.00折优惠"}],"voucher_groupon_list":[{"pid":"35","amount":"160","sales":"0","name":"200元代金券1张"},{"pid":"36","amount":"160","sales":"0","name":"200元代金券1张"},{"pid":"41","amount":"160","sales":"0","name":"200元代金券1张"},{"pid":"106","amount":"160","sales":"0","name":"200元代金券1张"},{"pid":"108","name":"1111","amount":"50","sales":"0"}]},{"mid":"25","name":"范团测sdfdsf试店铺01","score":"0","average_spend":"0","logo":"273208","dinning":"1","cashier":"0","promotion_list":[{"ico":"","name":"2种优惠券可免费领取"},{"ico":"","name":"满1减0.2、满2减0.5"},{"ico":"","name":"满20赠3"},{"ico":"","name":"买单立享8.00折优惠"}],"voucher_groupon_list":[{"pid":"35","amount":"160","sales":"0","name":"200元代金券1张"},{"pid":"36","amount":"160","sales":"0","name":"200元代金券1张"},{"pid":"41","amount":"160","sales":"0","name":"200元代金券1张"},{"pid":"106","amount":"160","sales":"0","name":"200元代金券1张"},{"pid":"108","name":"1111","amount":"50","sales":"0"}]}],"floor":[{"id":"31","name":"2层"},{"id":"38","name":"5层"},{"id":"43","name":"6层"},{"id":"46","name":"7层"},{"id":"72","name":"3层"},{"id":"88","name":"1层"},{"id":"131","name":"4层"}]}
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
        private List<FloorBean> floor;

        public List<MerchantsBean> getMerchants() {
            return merchants;
        }

        public void setMerchants(List<MerchantsBean> merchants) {
            this.merchants = merchants;
        }

        public List<FloorBean> getFloor() {
            return floor;
        }

        public void setFloor(List<FloorBean> floor) {
            this.floor = floor;
        }


        public static class FloorBean {
            /**
             * id : 31
             * name : 2层
             */

            private String id;
            private String name;

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
        }
    }
}
