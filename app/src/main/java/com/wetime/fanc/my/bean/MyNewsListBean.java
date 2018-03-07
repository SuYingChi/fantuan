package com.wetime.fanc.my.bean;

import com.wetime.fanc.home.bean.HomeItemBean;

import java.util.List;

/**
 * Created by zhoukang on 2018/3/7.
 */

public class MyNewsListBean {

    /**
     * error : 0
     * msg :
     * data : {"list":[{"id":"49","news_id":"1","cid":"1","name":"上海迪士尼花2.4万可插队有问题吗？","cover":["https://staticcdntest.fantuanlife.com/uimage/ce/42/21/ae/ce4221ae23f3f1d5a50174f641425e6f.jpg"],"author":"呆毛","read_num":"68次浏览","like_num":"0","comment_num":"0","cause":"","recommend":"1","state":"1","time":"02-05","origin_time":"2018-01-16 00:00:00","spider_time":"2018-01-24 14:40:45","type":"1","score":"0","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=49","news_name":"海口美食家"},{"id":"50","news_id":"1","cid":"1","name":"每次一进泰龙城小吃街，我的内心是懵逼的！","cover":["https://staticcdntest.fantuanlife.com/uimage/1a/6a/7c/4a/1a6a7c4ad81be969e13cfbb298732f1a.jpg","https://staticcdntest.fantuanlife.com/uimage/32/87/49/05/3287490574eaf88aa19cdad0e444c2c0.jpg","https://staticcdntest.fantuanlife.com/uimage/37/d1/1b/0d/37d11b0db407ce7ecc5b635cf21ca225.jpg"],"author":"呆毛","read_num":"11次浏览","like_num":"0","comment_num":"0","cause":"","recommend":"1","state":"1","time":"01-07","origin_time":"2018-01-07 00:00:00","spider_time":"2018-01-24 14:40:48","type":"3","score":"0","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=50","news_name":"海口美食家"},{"id":"52","news_id":"1","cid":"1","name":"阿vo，放下那碗绿豆先帮我搞炸炸可以啵？","cover":["https://staticcdntest.fantuanlife.com/uimage/e7/cd/d1/03/e7cdd10314887673dd248907c9c5de68.jpg"],"author":"呆毛","read_num":"1次浏览","like_num":"0","comment_num":"0","cause":"","recommend":"0","state":"1","time":"01-24","origin_time":"2018-01-02 00:00:00","spider_time":"2018-01-24 14:40:56","type":"1","score":"0","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=52","news_name":"海口美食家"},{"id":"53","news_id":"1","cid":"1","name":"一口一个草莓，这样的蛋糕根本没有理由让我拒绝！","cover":["https://staticcdntest.fantuanlife.com/uimage/da/d5/ce/6e/dad5ce6ebc687739db68628cd3cd9763.jpg"],"author":"呆毛","read_num":"8次浏览","like_num":"0","comment_num":"0","cause":"","recommend":"0","state":"1","time":"01-24","origin_time":"2017-12-30 00:00:00","spider_time":"2018-01-24 14:41:07","type":"1","score":"0","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=53","news_name":"海口美食家"}],"paging":{"total":4,"total_page":1,"limit":10,"pn":1,"is_end":true}}
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
         * list : [{"id":"49","news_id":"1","cid":"1","name":"上海迪士尼花2.4万可插队有问题吗？","cover":["https://staticcdntest.fantuanlife.com/uimage/ce/42/21/ae/ce4221ae23f3f1d5a50174f641425e6f.jpg"],"author":"呆毛","read_num":"68次浏览","like_num":"0","comment_num":"0","cause":"","recommend":"1","state":"1","time":"02-05","origin_time":"2018-01-16 00:00:00","spider_time":"2018-01-24 14:40:45","type":"1","score":"0","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=49","news_name":"海口美食家"},{"id":"50","news_id":"1","cid":"1","name":"每次一进泰龙城小吃街，我的内心是懵逼的！","cover":["https://staticcdntest.fantuanlife.com/uimage/1a/6a/7c/4a/1a6a7c4ad81be969e13cfbb298732f1a.jpg","https://staticcdntest.fantuanlife.com/uimage/32/87/49/05/3287490574eaf88aa19cdad0e444c2c0.jpg","https://staticcdntest.fantuanlife.com/uimage/37/d1/1b/0d/37d11b0db407ce7ecc5b635cf21ca225.jpg"],"author":"呆毛","read_num":"11次浏览","like_num":"0","comment_num":"0","cause":"","recommend":"1","state":"1","time":"01-07","origin_time":"2018-01-07 00:00:00","spider_time":"2018-01-24 14:40:48","type":"3","score":"0","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=50","news_name":"海口美食家"},{"id":"52","news_id":"1","cid":"1","name":"阿vo，放下那碗绿豆先帮我搞炸炸可以啵？","cover":["https://staticcdntest.fantuanlife.com/uimage/e7/cd/d1/03/e7cdd10314887673dd248907c9c5de68.jpg"],"author":"呆毛","read_num":"1次浏览","like_num":"0","comment_num":"0","cause":"","recommend":"0","state":"1","time":"01-24","origin_time":"2018-01-02 00:00:00","spider_time":"2018-01-24 14:40:56","type":"1","score":"0","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=52","news_name":"海口美食家"},{"id":"53","news_id":"1","cid":"1","name":"一口一个草莓，这样的蛋糕根本没有理由让我拒绝！","cover":["https://staticcdntest.fantuanlife.com/uimage/da/d5/ce/6e/dad5ce6ebc687739db68628cd3cd9763.jpg"],"author":"呆毛","read_num":"8次浏览","like_num":"0","comment_num":"0","cause":"","recommend":"0","state":"1","time":"01-24","origin_time":"2017-12-30 00:00:00","spider_time":"2018-01-24 14:41:07","type":"1","score":"0","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=53","news_name":"海口美食家"}]
         * paging : {"total":4,"total_page":1,"limit":10,"pn":1,"is_end":true}
         */

        private PagingBean paging;
        private List<HomeItemBean> list;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<HomeItemBean> getList() {
            return list;
        }

        public void setList(List<HomeItemBean> list) {
            this.list = list;
        }

        public static class PagingBean {
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


    }
}
