package com.wetime.fanc.circle.bean;

import java.util.List;

/**
 * Created by zhoukang on 2018/3/5.
 */

public class AllCircleListBean {


    /**
     * error : 0
     * msg :
     * data : {"circle_list":[{"id":"10","name":"测试7","intro":"7777","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"9","name":"测试6","intro":"666","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"8","name":"测试5","intro":"555","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"7","name":"测试4","intro":"444","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"6","name":"测试3","intro":"333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"5","name":"测试2","intro":"2222","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"4","name":"测试1","intro":"1111","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"3","name":"吃喝拉撒","intro":"胖慰的圈子","cover_url":"https://staticcdntest.fantuanlife.com/uimage/37/d1/1b/0d/37d11b0db407ce7ecc5b635cf21ca225.jpg"}],"page":{"total":8,"total_page":1,"limit":10,"pn":1,"is_end":1}}
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
         * circle_list : [{"id":"10","name":"测试7","intro":"7777","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"9","name":"测试6","intro":"666","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"8","name":"测试5","intro":"555","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"7","name":"测试4","intro":"444","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"6","name":"测试3","intro":"333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"5","name":"测试2","intro":"2222","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"4","name":"测试1","intro":"1111","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"3","name":"吃喝拉撒","intro":"胖慰的圈子","cover_url":"https://staticcdntest.fantuanlife.com/uimage/37/d1/1b/0d/37d11b0db407ce7ecc5b635cf21ca225.jpg"}]
         * page : {"total":8,"total_page":1,"limit":10,"pn":1,"is_end":1}
         */

        private PageBean page;
        private List<CircleListBean> circle_list;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<CircleListBean> getCircle_list() {
            return circle_list;
        }

        public void setCircle_list(List<CircleListBean> circle_list) {
            this.circle_list = circle_list;
        }

        public static class PageBean {
            /**
             * total : 8
             * total_page : 1
             * limit : 10
             * pn : 1
             * is_end : 1
             */

            private int total;
            private int total_page;
            private int limit;
            private int pn;
            private int is_end;

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

            public int getIs_end() {
                return is_end;
            }

            public void setIs_end(int is_end) {
                this.is_end = is_end;
            }
        }

        public static class CircleListBean {
            /**
             * id : 10
             * name : 测试7
             * intro : 7777
             * cover_url : https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg
             */

            private String id;
            private String name;
            private String intro;
            private String cover_url;

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

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getCover_url() {
                return cover_url;
            }

            public void setCover_url(String cover_url) {
                this.cover_url = cover_url;
            }
        }
    }
}
