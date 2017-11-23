package com.wetime.fanc.shopcenter.bean;

import java.util.List;

/**
 * Created by zhoukang on 2017/11/23.
 */

public class OrderPageBean {

    /**
     * error : 0
     * msg :
     * data : {"type_config":[{"name":"全部订单","val":0},{"name":"收银","val":3},{"name":"团购","val":4},{"name":"代金券","val":5}],"list":[{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"酱紫烤鱼200元代金券","type":"5","create_time":"2017-11-11 07:25:42","expired_time":"2017-11-30","order_no":"17031532715438","order_id":"6799","state":"1","stateName":"待使用","order_num":"3","actual_pay":"0.09","refund":"0.03","can_add_review":false},{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"中国第一火锅中国第一火锅","type":"4","create_time":"2017-11-10 16:31:51","expired_time":"2017-11-30","order_no":"3171031532715435","order_id":"6796","state":"1","stateName":"待使用","order_num":"3","actual_pay":"0.03","refund":"0.01","can_add_review":false}],"unpaid_num":"15","unused_num":"2","unreview_num":"6","paging":{"totals":1,"current":1,"is_end":true}}
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
         * type_config : [{"name":"全部订单","val":0},{"name":"收银","val":3},{"name":"团购","val":4},{"name":"代金券","val":5}]
         * list : [{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"酱紫烤鱼200元代金券","type":"5","create_time":"2017-11-11 07:25:42","expired_time":"2017-11-30","order_no":"17031532715438","order_id":"6799","state":"1","stateName":"待使用","order_num":"3","actual_pay":"0.09","refund":"0.03","can_add_review":false},{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"中国第一火锅中国第一火锅","type":"4","create_time":"2017-11-10 16:31:51","expired_time":"2017-11-30","order_no":"3171031532715435","order_id":"6796","state":"1","stateName":"待使用","order_num":"3","actual_pay":"0.03","refund":"0.01","can_add_review":false}]
         * unpaid_num : 15
         * unused_num : 2
         * unreview_num : 6
         * paging : {"totals":1,"current":1,"is_end":true}
         */

        private String unpaid_num;
        private String unused_num;
        private String unreview_num;
        private PagingBean paging;
        private List<TypeConfigBean> type_config;
        private List<ListBean> list;

        public String getUnpaid_num() {
            return unpaid_num;
        }

        public void setUnpaid_num(String unpaid_num) {
            this.unpaid_num = unpaid_num;
        }

        public String getUnused_num() {
            return unused_num;
        }

        public void setUnused_num(String unused_num) {
            this.unused_num = unused_num;
        }

        public String getUnreview_num() {
            return unreview_num;
        }

        public void setUnreview_num(String unreview_num) {
            this.unreview_num = unreview_num;
        }

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<TypeConfigBean> getType_config() {
            return type_config;
        }

        public void setType_config(List<TypeConfigBean> type_config) {
            this.type_config = type_config;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PagingBean {
            /**
             * totals : 1
             * current : 1
             * is_end : true
             */

            private int totals;
            private int current;
            private boolean is_end;

            public int getTotals() {
                return totals;
            }

            public void setTotals(int totals) {
                this.totals = totals;
            }

            public int getCurrent() {
                return current;
            }

            public void setCurrent(int current) {
                this.current = current;
            }

            public boolean isIs_end() {
                return is_end;
            }

            public void setIs_end(boolean is_end) {
                this.is_end = is_end;
            }
        }

        public static class TypeConfigBean {
            /**
             * name : 全部订单
             * val : 0
             */

            private String name;
            private int val;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getVal() {
                return val;
            }

            public void setVal(int val) {
                this.val = val;
            }
        }

        public static class ListBean {
            /**
             * merchant : {"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"}
             * promotion_name : 酱紫烤鱼200元代金券
             * type : 5
             * create_time : 2017-11-11 07:25:42
             * expired_time : 2017-11-30
             * order_no : 17031532715438
             * order_id : 6799
             * state : 1
             * stateName : 待使用
             * order_num : 3
             * actual_pay : 0.09
             * refund : 0.03
             * can_add_review : false
             */

            private MerchantBean merchant;
            private String promotion_name;
            private String type;
            private String create_time;
            private String expired_time;
            private String order_no;
            private String order_id;
            private String state;
            private String stateName;
            private String order_num;
            private String actual_pay;
            private String refund;
            private boolean can_add_review;

            public MerchantBean getMerchant() {
                return merchant;
            }

            public void setMerchant(MerchantBean merchant) {
                this.merchant = merchant;
            }

            public String getPromotion_name() {
                return promotion_name;
            }

            public void setPromotion_name(String promotion_name) {
                this.promotion_name = promotion_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getExpired_time() {
                return expired_time;
            }

            public void setExpired_time(String expired_time) {
                this.expired_time = expired_time;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getStateName() {
                return stateName;
            }

            public void setStateName(String stateName) {
                this.stateName = stateName;
            }

            public String getOrder_num() {
                return order_num;
            }

            public void setOrder_num(String order_num) {
                this.order_num = order_num;
            }

            public String getActual_pay() {
                return actual_pay;
            }

            public void setActual_pay(String actual_pay) {
                this.actual_pay = actual_pay;
            }

            public String getRefund() {
                return refund;
            }

            public void setRefund(String refund) {
                this.refund = refund;
            }

            public boolean isCan_add_review() {
                return can_add_review;
            }

            public void setCan_add_review(boolean can_add_review) {
                this.can_add_review = can_add_review;
            }

            public static class MerchantBean {
                /**
                 * mid : 29
                 * logo : http://shoptest.weishike.net/image/fant/m_logo.png
                 * name : 澳门仔茶餐厅
                 */

                private String mid;
                private String logo;
                private String name;

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
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
}
