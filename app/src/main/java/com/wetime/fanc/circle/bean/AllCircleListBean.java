package com.wetime.fanc.circle.bean;

import java.util.List;

/**
 * Created by zhoukang on 2018/3/5.
 */

public class AllCircleListBean {


    /**
     * error : 0
     * msg :
     * data : {"circle_list":[{"id":"17","name":"测试14","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"16","name":"测试13","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"15","name":"测试12","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"14","name":"测试11","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"13","name":"测试10","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"12","name":"测试9","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"11","name":"测试8","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"10","name":"测试7","intro":"7777","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"9","name":"测试6","intro":"666","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"8","name":"测试5","intro":"555","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"}],"paging":{"total":15,"total_page":2,"limit":10,"pn":1,"is_end":false}}
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
         * circle_list : [{"id":"17","name":"测试14","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"16","name":"测试13","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"15","name":"测试12","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"14","name":"测试11","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"13","name":"测试10","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"12","name":"测试9","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"11","name":"测试8","intro":"3333","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"10","name":"测试7","intro":"7777","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"9","name":"测试6","intro":"666","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"},{"id":"8","name":"测试5","intro":"555","cover_url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg"}]
         * paging : {"total":15,"total_page":2,"limit":10,"pn":1,"is_end":false}
         */

        private PagingBean paging;
        private List<CircleListBean> circle_list;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<CircleListBean> getCircle_list() {
            return circle_list;
        }

        public void setCircle_list(List<CircleListBean> circle_list) {
            this.circle_list = circle_list;
        }

        public static class PagingBean {
            /**
             * total : 15
             * total_page : 2
             * limit : 10
             * pn : 1
             * is_end : false
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

        public static class CircleListBean {
            /**
             * id : 17
             * name : 测试14
             * intro : 3333
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
