package com.wetime.fanc.news.bean;

import java.util.List;

public class RecomentFocusUserListBean {

    /**
     * error : 0
     * msg :
     * data : {"list":[{"uid":"261","intro":"","username":"灌篮高手","avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png","is_follow":false}],"paging":{"total":"1","total_page":"1","limit":"15","pn":"1","is_end":true}}
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
         * list : [{"uid":"261","intro":"","username":"灌篮高手","avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png","is_follow":false}]
         * paging : {"total":"1","total_page":"1","limit":"15","pn":"1","is_end":true}
         */

        private PagingBean paging;
        private List<RecomentFocusUserBean> list;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<RecomentFocusUserBean> getList() {
            return list;
        }

        public void setList(List<RecomentFocusUserBean> list) {
            this.list = list;
        }

        public static class PagingBean {
            /**
             * total : 1
             * total_page : 1
             * limit : 15
             * pn : 1
             * is_end : true
             */

            private String total;
            private String total_page;
            private String limit;
            private String pn;
            private boolean is_end;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getTotal_page() {
                return total_page;
            }

            public void setTotal_page(String total_page) {
                this.total_page = total_page;
            }

            public String getLimit() {
                return limit;
            }

            public void setLimit(String limit) {
                this.limit = limit;
            }

            public String getPn() {
                return pn;
            }

            public void setPn(String pn) {
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
