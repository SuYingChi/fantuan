package com.wetime.fanc.my.bean;

import java.util.List;

/**
 * Created by zhoukang on 2018/3/7.
 */

public class MyShopListBean {

    /**
     * error : 0
     * msg :
     * data : {"list":[{"mid":"55","name":"范团13(龙华区)","score":"4.6","logo":"https://staticcdntest.fantuanlife.com/uimage/70/a7/70/64/70a77064e41d3b6182d260065a2537ae.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","average_spend":"","address":"海口龙华区宜欣广场","longitude":"110.312941","latitude":"20.024648","cate_name":"火锅","mall_name":"宜欣城","distance":"距671m"},{"mid":"2984","name":"骑行天涯自行车（桂林洋店）","score":"3.5","logo":"https://staticcdntest.fantuanlife.com/uimage/bd/b6/c9/38/bdb6c938e359436d954d9480c3169578.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","average_spend":"","address":"海口市美兰区桂林洋开发区大学城校际2号路（琼台学校大门内侧）","longitude":"110.49437","latitude":"20.02043","cate_name":"运动健身","mall_name":"","distance":"距18.44km"}],"paging":{"total":2,"total_page":1,"limit":10,"pn":1,"is_end":true}}
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
         * list : [{"mid":"55","name":"范团13(龙华区)","score":"4.6","logo":"https://staticcdntest.fantuanlife.com/uimage/70/a7/70/64/70a77064e41d3b6182d260065a2537ae.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","average_spend":"","address":"海口龙华区宜欣广场","longitude":"110.312941","latitude":"20.024648","cate_name":"火锅","mall_name":"宜欣城","distance":"距671m"},{"mid":"2984","name":"骑行天涯自行车（桂林洋店）","score":"3.5","logo":"https://staticcdntest.fantuanlife.com/uimage/bd/b6/c9/38/bdb6c938e359436d954d9480c3169578.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","average_spend":"","address":"海口市美兰区桂林洋开发区大学城校际2号路（琼台学校大门内侧）","longitude":"110.49437","latitude":"20.02043","cate_name":"运动健身","mall_name":"","distance":"距18.44km"}]
         * paging : {"total":2,"total_page":1,"limit":10,"pn":1,"is_end":true}
         */

        private PagingBean paging;
        private List<ListBean> list;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PagingBean {
            /**
             * total : 2
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
             * mid : 55
             * name : 范团13(龙华区)
             * score : 4.6
             * logo : https://staticcdntest.fantuanlife.com/uimage/70/a7/70/64/70a77064e41d3b6182d260065a2537ae.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
             * average_spend :
             * address : 海口龙华区宜欣广场
             * longitude : 110.312941
             * latitude : 20.024648
             * cate_name : 火锅
             * mall_name : 宜欣城
             * distance : 距671m
             */

            private String mid;
            private String name;
            private String score;
            private String logo;
            private String average_spend;
            private String address;
            private String longitude;
            private String latitude;
            private String cate_name;
            private String mall_name;
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

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getAverage_spend() {
                return average_spend;
            }

            public void setAverage_spend(String average_spend) {
                this.average_spend = average_spend;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }

            public String getMall_name() {
                return mall_name;
            }

            public void setMall_name(String mall_name) {
                this.mall_name = mall_name;
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
