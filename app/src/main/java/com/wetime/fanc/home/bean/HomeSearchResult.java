package com.wetime.fanc.home.bean;

import com.wetime.fanc.shopcenter.bean.CategoryBean;
import com.wetime.fanc.shopcenter.bean.MerchantsBean;
import com.wetime.fanc.shopcenter.bean.SortMethordBean;

import java.util.List;

/**
 * Created by zhoukang on 2017/11/22.
 */

public class HomeSearchResult {

    /**
     * error : 0
     * msg :
     * data : {"merchants":[{"mid":"2069","name":"澳门皇茶","score":"50","average_spend":"￥1800/人","logo":"http://shoptest.weishike.net/uimage/02/dd/70/ff/02dd70ff804dd91f534577cf942c8b39.jpg?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70","spider":"0","distance":"距741m","mall":null,"category_name":"甜点饮品","floor":"","mall_name":"","voucher_groupon_list":[{"pid":"123","amount":"￥160","market_price":"300","sales":"","type":5,"name":"300元代金券1张"},{"pid":"124","amount":"￥160","market_price":"600","sales":"","type":5,"name":"600元代金券1张"}],"promotion_list":[]},{"mid":"2504","name":"伟氏澳门茶餐厅（龙华路店）","score":"50","average_spend":"￥3700/人","logo":"http://shoptest.weishike.net/uimage/25/c0/75/2f/25c0752fe8a143cb399ea933439fc6db.jpg?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70","spider":"0","distance":"距1.86km","mall":null,"category_name":"西餐","floor":"","mall_name":"","voucher_groupon_list":[],"promotion_list":[]},{"mid":"29","name":"澳门仔茶餐厅","score":"0","average_spend":"","logo":"http://shoptest.weishike.net/uimage/96/e3/4e/6d/96e34e6d4aa2d5cd68c2c103f9c6cb54.jpg?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70","spider":"1","distance":"距167m","mall":{"id":"11","cid":"1","pid":"2","name":"京华城","logo_rectangle":"0","logo_square":"0","level":"2","lng":"110.31858","lat":"20.02759"},"category_name":"小吃快餐","floor":"金城国际","mall_name":"京华城","voucher_groupon_list":[{"pid":"20","amount":"￥160","market_price":"200","sales":"","type":5,"name":"200元代金券1张"},{"pid":"21","amount":"￥160","market_price":"200","sales":"","type":5,"name":"200元代金券1张"},{"pid":"35","amount":"￥160","market_price":"200","sales":"","type":5,"name":"200元代金券1张"},{"pid":"36","amount":"￥160","market_price":"200","sales":"","type":5,"name":"200元代金券1张"},{"pid":"106","amount":"￥160","market_price":"200","sales":"","type":5,"name":"200元代金券1张"},{"pid":"17","name":"中国第一火锅","amount":"￥50","sales":"","type":4},{"pid":"108","name":"1111","amount":"￥50","sales":"","type":4}],"promotion_list":[{"ico":"http://shoptest.weishike.net/image/fant/promotion/coupon.png","name":"9种优惠券可免费领取","type":3},{"ico":"http://shoptest.weishike.net/image/fant/promotion/moneyOff.png","name":"满1减0.2、满2减0.5","type":6},{"ico":"http://shoptest.weishike.net/image/fant/promotion/cashCoupon.png","name":"满20赠3","type":2},{"ico":"http://shoptest.weishike.net/image/fant/promotion/payDiscount.png","name":"买单立享9.00折优惠","type":7}]}],"malls":[{"id":"","name":"全部","num":"1","subcates":[]},{"id":"2","name":"龙华区","num":1,"subcates":[{"id":"2","name":"全部","num":"1"},{"id":"11","name":"京华城","num":"1"}]}],"category":[{"id":"","name":"全部"},{"id":"1","cid":"0","name":"美食","imgurl":"","level":"1","num":3,"subcates":[{"id":"1","name":"全部","num":3},{"id":"4","cid":"1","name":"甜点饮品","imgurl":"","level":"0","num":"1"},{"id":"6","cid":"1","name":"小吃快餐","imgurl":"","level":"0","num":"1"},{"id":"15","cid":"1","name":"西餐","imgurl":"","level":"0","num":"1"}]}],"sort":[{"sort":"0","name":"智能排序"},{"sort":"1","name":"按距离排序"},{"sort":"2","name":"按人均排序"}],"paging":{"totals":1,"current":1,"is_end":true}}
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
         * merchants : [{"mid":"2069","name":"澳门皇茶","score":"50","average_spend":"￥1800/人","logo":"http://shoptest.weishike.net/uimage/02/dd/70/ff/02dd70ff804dd91f534577cf942c8b39.jpg?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70","spider":"0","distance":"距741m","mall":null,"category_name":"甜点饮品","floor":"","mall_name":"","voucher_groupon_list":[{"pid":"123","amount":"￥160","market_price":"300","sales":"","type":5,"name":"300元代金券1张"},{"pid":"124","amount":"￥160","market_price":"600","sales":"","type":5,"name":"600元代金券1张"}],"promotion_list":[]},{"mid":"2504","name":"伟氏澳门茶餐厅（龙华路店）","score":"50","average_spend":"￥3700/人","logo":"http://shoptest.weishike.net/uimage/25/c0/75/2f/25c0752fe8a143cb399ea933439fc6db.jpg?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70","spider":"0","distance":"距1.86km","mall":null,"category_name":"西餐","floor":"","mall_name":"","voucher_groupon_list":[],"promotion_list":[]},{"mid":"29","name":"澳门仔茶餐厅","score":"0","average_spend":"","logo":"http://shoptest.weishike.net/uimage/96/e3/4e/6d/96e34e6d4aa2d5cd68c2c103f9c6cb54.jpg?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70","spider":"1","distance":"距167m","mall":{"id":"11","cid":"1","pid":"2","name":"京华城","logo_rectangle":"0","logo_square":"0","level":"2","lng":"110.31858","lat":"20.02759"},"category_name":"小吃快餐","floor":"金城国际","mall_name":"京华城","voucher_groupon_list":[{"pid":"20","amount":"￥160","market_price":"200","sales":"","type":5,"name":"200元代金券1张"},{"pid":"21","amount":"￥160","market_price":"200","sales":"","type":5,"name":"200元代金券1张"},{"pid":"35","amount":"￥160","market_price":"200","sales":"","type":5,"name":"200元代金券1张"},{"pid":"36","amount":"￥160","market_price":"200","sales":"","type":5,"name":"200元代金券1张"},{"pid":"106","amount":"￥160","market_price":"200","sales":"","type":5,"name":"200元代金券1张"},{"pid":"17","name":"中国第一火锅","amount":"￥50","sales":"","type":4},{"pid":"108","name":"1111","amount":"￥50","sales":"","type":4}],"promotion_list":[{"ico":"http://shoptest.weishike.net/image/fant/promotion/coupon.png","name":"9种优惠券可免费领取","type":3},{"ico":"http://shoptest.weishike.net/image/fant/promotion/moneyOff.png","name":"满1减0.2、满2减0.5","type":6},{"ico":"http://shoptest.weishike.net/image/fant/promotion/cashCoupon.png","name":"满20赠3","type":2},{"ico":"http://shoptest.weishike.net/image/fant/promotion/payDiscount.png","name":"买单立享9.00折优惠","type":7}]}]
         * malls : [{"id":"","name":"全部","num":"1","subcates":[]},{"id":"2","name":"龙华区","num":1,"subcates":[{"id":"2","name":"全部","num":"1"},{"id":"11","name":"京华城","num":"1"}]}]
         * category : [{"id":"","name":"全部"},{"id":"1","cid":"0","name":"美食","imgurl":"","level":"1","num":3,"subcates":[{"id":"1","name":"全部","num":3},{"id":"4","cid":"1","name":"甜点饮品","imgurl":"","level":"0","num":"1"},{"id":"6","cid":"1","name":"小吃快餐","imgurl":"","level":"0","num":"1"},{"id":"15","cid":"1","name":"西餐","imgurl":"","level":"0","num":"1"}]}]
         * sort : [{"sort":"0","name":"智能排序"},{"sort":"1","name":"按距离排序"},{"sort":"2","name":"按人均排序"}]
         * paging : {"totals":1,"current":1,"is_end":true}
         */

        private PagingBean paging;
        private List<MerchantsBean> merchants;
        private List<MallsBean> malls;
        private List<CategoryBean> category;
        private List<SortMethordBean> sort;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<MerchantsBean> getMerchants() {
            return merchants;
        }

        public void setMerchants(List<MerchantsBean> merchants) {
            this.merchants = merchants;
        }

        public List<MallsBean> getMalls() {
            return malls;
        }

        public void setMalls(List<MallsBean> malls) {
            this.malls = malls;
        }

        public List<CategoryBean> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryBean> category) {
            this.category = category;
        }

        public List<SortMethordBean> getSort() {
            return sort;
        }

        public void setSort(List<SortMethordBean> sort) {
            this.sort = sort;
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








    }
}
