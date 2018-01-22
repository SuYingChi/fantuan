package com.wetime.fanc.home.bean;

import java.util.List;

/**
 * Created by zhoukang on 2017/11/23.
 */

public class OrderPageBean {


    /**
     * error : 0
     * msg :
     * data : {"type_config":[{"name":"全部订单","val":0},{"name":"收银","val":3},{"name":"团购","val":4},{"name":"代金券","val":5}],"list":[{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"酱紫烤鱼200元代金券","type":"5","type_name":"代金券","create_time":"2017-11-14 07:31:24","expired_time":"2017-11-30","order_no":"17031532715441","order_id":"6807","order_detail_url":"http://www.baidu.com","state":"22","stateName":"已退款","order_num":"1","refund_tip":"","order_qrcode_url":"","price_type_name":"退款金额:","price":"¥0.09","action_type_name":"删除订单"},{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"中国第一火锅中国第一火锅","type":"4","type_name":"团购","create_time":"2017-10-31 14:47:51","expired_time":"2017-11-30","order_no":"3171031532715438","order_id":"6806","order_detail_url":"http://www.baidu.com","state":"22","stateName":"已退款","order_num":"1","refund_tip":"","order_qrcode_url":"","price_type_name":"退款金额:","price":"¥0.01","action_type_name":"删除订单"},{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"中国第一火锅中国第一火锅","type":"4","type_name":"团购","create_time":"2017-11-11 07:25:42","expired_time":"2017-11-30","order_no":"3171031532715437","order_id":"6805","order_detail_url":"http://www.baidu.com","state":"32","stateName":"已取消","order_num":"1","refund_tip":"","order_qrcode_url":"","price_type_name":"实付金额:","price":"¥0.03","action_type_name":"删除订单"},{"merchant":{"mid":"166","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"靖哥收钱吧便利店"},"promotion_name":"靖哥收钱吧便利店","type":"3","type_name":"收银","create_time":"2017-11-14 14:47:51","expired_time":"","order_no":"3171031532715435","order_id":"6804","order_detail_url":"http://www.baidu.com","state":"11","stateName":"已完成","order_num":0,"refund_tip":"有退款","order_qrcode_url":"","price_type_name":"实付金额:","price":"¥0.6","action_type_name":"去评价"},{"merchant":{"mid":"166","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"靖哥收钱吧便利店"},"promotion_name":"靖哥收钱吧便利店","type":"3","type_name":"收银","create_time":"2017-11-14 14:47:51","expired_time":"","order_no":"3171031532715434","order_id":"6803","order_detail_url":"http://www.baidu.com","state":"22","stateName":"已退款","order_num":0,"refund_tip":"","order_qrcode_url":"","price_type_name":"退款金额:","price":"¥1","action_type_name":"删除订单"},{"merchant":{"mid":"132","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"靖哥便利店(龙昆南分店)"},"promotion_name":"靖哥便利店(龙昆南分店)","type":"3","type_name":"收银","create_time":"2017-11-11 11:01:34","expired_time":"","order_no":"17031532715441","order_id":"6802","order_detail_url":"http://www.baidu.com","state":"11","stateName":"已完成","order_num":0,"refund_tip":"","order_qrcode_url":"","price_type_name":"实付金额:","price":"¥0.01","action_type_name":"去评价"},{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"酱紫烤鱼200元代金券","type":"5","type_name":"代金券","create_time":"2017-11-11 08:36:21","expired_time":"2017-11-30","order_no":"17031532715440","order_id":"6801","order_detail_url":"http://www.baidu.com","state":"32","stateName":"已取消","order_num":"1","refund_tip":"","order_qrcode_url":"","price_type_name":"实付金额:","price":"¥0.03","action_type_name":"删除订单"},{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"酱紫烤鱼200元代金券","type":"5","type_name":"代金券","create_time":"2017-11-11 07:25:42","expired_time":"2017-11-30","order_no":"17031532715438","order_id":"6799","order_detail_url":"http://www.baidu.com","state":"11","stateName":"待使用","order_num":"3","refund_tip":"","order_qrcode_url":"http://www.baidu.com","price_type_name":"实付金额:","price":"¥0.09","action_type_name":"查看券码"},{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"中国第一火锅中国第一火锅","type":"4","type_name":"团购","create_time":"2017-11-10 16:31:51","expired_time":"2017-11-30","order_no":"3171031532715435","order_id":"6796","order_detail_url":"http://www.baidu.com","state":"11","stateName":"待使用","order_num":"3","refund_tip":"","order_qrcode_url":"http://www.baidu.com","price_type_name":"实付金额:","price":"¥0.03","action_type_name":"查看券码"},{"merchant":{"mid":"166","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"靖哥收钱吧便利店"},"promotion_name":"靖哥收钱吧便利店","type":"3","type_name":"收银","create_time":"2017-10-31 14:47:51","expired_time":"","order_no":"3171031532715433","order_id":"6794","order_detail_url":"http://www.baidu.com","state":"11","stateName":"已完成","order_num":0,"refund_tip":"","order_qrcode_url":"","price_type_name":"实付金额:","price":"¥0.01","action_type_name":"去评价"}],"unpaid_num":15,"unused_num":2,"unreview_num":6,"paging":{"totals":1,"current":1,"is_end":true}}
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
         * list : [{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"酱紫烤鱼200元代金券","type":"5","type_name":"代金券","create_time":"2017-11-14 07:31:24","expired_time":"2017-11-30","order_no":"17031532715441","order_id":"6807","order_detail_url":"http://www.baidu.com","state":"22","stateName":"已退款","order_num":"1","refund_tip":"","order_qrcode_url":"","price_type_name":"退款金额:","price":"¥0.09","action_type_name":"删除订单"},{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"中国第一火锅中国第一火锅","type":"4","type_name":"团购","create_time":"2017-10-31 14:47:51","expired_time":"2017-11-30","order_no":"3171031532715438","order_id":"6806","order_detail_url":"http://www.baidu.com","state":"22","stateName":"已退款","order_num":"1","refund_tip":"","order_qrcode_url":"","price_type_name":"退款金额:","price":"¥0.01","action_type_name":"删除订单"},{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"中国第一火锅中国第一火锅","type":"4","type_name":"团购","create_time":"2017-11-11 07:25:42","expired_time":"2017-11-30","order_no":"3171031532715437","order_id":"6805","order_detail_url":"http://www.baidu.com","state":"32","stateName":"已取消","order_num":"1","refund_tip":"","order_qrcode_url":"","price_type_name":"实付金额:","price":"¥0.03","action_type_name":"删除订单"},{"merchant":{"mid":"166","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"靖哥收钱吧便利店"},"promotion_name":"靖哥收钱吧便利店","type":"3","type_name":"收银","create_time":"2017-11-14 14:47:51","expired_time":"","order_no":"3171031532715435","order_id":"6804","order_detail_url":"http://www.baidu.com","state":"11","stateName":"已完成","order_num":0,"refund_tip":"有退款","order_qrcode_url":"","price_type_name":"实付金额:","price":"¥0.6","action_type_name":"去评价"},{"merchant":{"mid":"166","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"靖哥收钱吧便利店"},"promotion_name":"靖哥收钱吧便利店","type":"3","type_name":"收银","create_time":"2017-11-14 14:47:51","expired_time":"","order_no":"3171031532715434","order_id":"6803","order_detail_url":"http://www.baidu.com","state":"22","stateName":"已退款","order_num":0,"refund_tip":"","order_qrcode_url":"","price_type_name":"退款金额:","price":"¥1","action_type_name":"删除订单"},{"merchant":{"mid":"132","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"靖哥便利店(龙昆南分店)"},"promotion_name":"靖哥便利店(龙昆南分店)","type":"3","type_name":"收银","create_time":"2017-11-11 11:01:34","expired_time":"","order_no":"17031532715441","order_id":"6802","order_detail_url":"http://www.baidu.com","state":"11","stateName":"已完成","order_num":0,"refund_tip":"","order_qrcode_url":"","price_type_name":"实付金额:","price":"¥0.01","action_type_name":"去评价"},{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"酱紫烤鱼200元代金券","type":"5","type_name":"代金券","create_time":"2017-11-11 08:36:21","expired_time":"2017-11-30","order_no":"17031532715440","order_id":"6801","order_detail_url":"http://www.baidu.com","state":"32","stateName":"已取消","order_num":"1","refund_tip":"","order_qrcode_url":"","price_type_name":"实付金额:","price":"¥0.03","action_type_name":"删除订单"},{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"酱紫烤鱼200元代金券","type":"5","type_name":"代金券","create_time":"2017-11-11 07:25:42","expired_time":"2017-11-30","order_no":"17031532715438","order_id":"6799","order_detail_url":"http://www.baidu.com","state":"11","stateName":"待使用","order_num":"3","refund_tip":"","order_qrcode_url":"http://www.baidu.com","price_type_name":"实付金额:","price":"¥0.09","action_type_name":"查看券码"},{"merchant":{"mid":"29","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"澳门仔茶餐厅"},"promotion_name":"中国第一火锅中国第一火锅","type":"4","type_name":"团购","create_time":"2017-11-10 16:31:51","expired_time":"2017-11-30","order_no":"3171031532715435","order_id":"6796","order_detail_url":"http://www.baidu.com","state":"11","stateName":"待使用","order_num":"3","refund_tip":"","order_qrcode_url":"http://www.baidu.com","price_type_name":"实付金额:","price":"¥0.03","action_type_name":"查看券码"},{"merchant":{"mid":"166","logo":"http://shoptest.weishike.net/image/fant/m_logo.png","name":"靖哥收钱吧便利店"},"promotion_name":"靖哥收钱吧便利店","type":"3","type_name":"收银","create_time":"2017-10-31 14:47:51","expired_time":"","order_no":"3171031532715433","order_id":"6794","order_detail_url":"http://www.baidu.com","state":"11","stateName":"已完成","order_num":0,"refund_tip":"","order_qrcode_url":"","price_type_name":"实付金额:","price":"¥0.01","action_type_name":"去评价"}]
         * unpaid_num : 15
         * unused_num : 2
         * unreview_num : 6
         * paging : {"totals":1,"current":1,"is_end":true}
         */

        private int unpaid_num;
        private int unused_num;
        private int unreview_num;
        private PagingBean paging;
        private List<TypeConfigBean> type_config;
        private List<ListBean> list;

        public int getUnpaid_num() {
            return unpaid_num;
        }

        public void setUnpaid_num(int unpaid_num) {
            this.unpaid_num = unpaid_num;
        }

        public int getUnused_num() {
            return unused_num;
        }

        public void setUnused_num(int unused_num) {
            this.unused_num = unused_num;
        }

        public int getUnreview_num() {
            return unreview_num;
        }

        public void setUnreview_num(int unreview_num) {
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
             * type_name : 代金券
             * create_time : 2017-11-14 07:31:24
             * expired_time : 2017-11-30
             * order_no : 17031532715441
             * order_id : 6807
             * order_detail_url : http://www.baidu.com
             * state : 22
             * stateName : 已退款
             * order_num : 1
             * refund_tip :
             * order_qrcode_url :
             * price_type_name : 退款金额:
             * price : ¥0.09
             * action_type_name : 删除订单
             */

            private MerchantBean merchant;
            private String promotion_name;
            private String type;
            private String type_name;
            private String time;
            private String order_no;
            private String order_id;
            private String order_detail_url;
            private String state;
            private String stateName;
            private String order_num;
            private String refund_tip;
            private String order_qrcode_url;
            private String price_type_name;
            private String price;
            private String action_type_name;
            private String action_url;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getAction_url() {
                return action_url;
            }

            public void setAction_url(String action_url) {
                this.action_url = action_url;
            }

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

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
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

            public String getOrder_detail_url() {
                return order_detail_url;
            }

            public void setOrder_detail_url(String order_detail_url) {
                this.order_detail_url = order_detail_url;
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

            public String getRefund_tip() {
                return refund_tip;
            }

            public void setRefund_tip(String refund_tip) {
                this.refund_tip = refund_tip;
            }

            public String getOrder_qrcode_url() {
                return order_qrcode_url;
            }

            public void setOrder_qrcode_url(String order_qrcode_url) {
                this.order_qrcode_url = order_qrcode_url;
            }

            public String getPrice_type_name() {
                return price_type_name;
            }

            public void setPrice_type_name(String price_type_name) {
                this.price_type_name = price_type_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getAction_type_name() {
                return action_type_name;
            }

            public void setAction_type_name(String action_type_name) {
                this.action_type_name = action_type_name;
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
