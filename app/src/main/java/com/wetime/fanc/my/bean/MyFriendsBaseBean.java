package com.wetime.fanc.my.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/26.
 */

public class MyFriendsBaseBean {


    /**
     * error : 0
     * msg :
     * data : {"list":[{"id":"21","username":"peggy","avatar":"https://staticcdntest.fantuanlife.com/uimage/34/a8/96/f1/34a896f13692732b061f0eb850c1537e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","is_mutual":false},{"id":"22","username":"爱吃土豆的小豆豆","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","is_mutual":false},{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","is_mutual":false},{"id":"24","username":"范范范24","avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png","is_mutual":false},{"id":"25","username":"范范范25","avatar":"https://staticcdntest.fantuanlife.com/uimage/3a/87/66/6b/3a87666bda7670fb3a8d8092b5828b80.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","is_mutual":true}],"paging":{"total":5,"total_page":"1","limit":"20","pn":"1","is_end":true}}
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
         * list : [{"id":"21","username":"peggy","avatar":"https://staticcdntest.fantuanlife.com/uimage/34/a8/96/f1/34a896f13692732b061f0eb850c1537e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","is_mutual":false},{"id":"22","username":"爱吃土豆的小豆豆","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","is_mutual":false},{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","is_mutual":false},{"id":"24","username":"范范范24","avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png","is_mutual":false},{"id":"25","username":"范范范25","avatar":"https://staticcdntest.fantuanlife.com/uimage/3a/87/66/6b/3a87666bda7670fb3a8d8092b5828b80.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","is_mutual":true}]
         * paging : {"total":5,"total_page":"1","limit":"20","pn":"1","is_end":true}
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
             * total : 5
             * total_page : 1
             * limit : 20
             * pn : 1
             * is_end : true
             */

            private int total;
            private String total_page;
            private String limit;
            private String pn;
            private boolean is_end;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
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

        public static class ListBean {
            /**
             * id : 21
             * username : peggy
             * avatar : https://staticcdntest.fantuanlife.com/uimage/34/a8/96/f1/34a896f13692732b061f0eb850c1537e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
             * is_mutual : false
             */

            private String id;
            private String username;
            private String avatar;
            private boolean is_mutual;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public boolean isIs_mutual() {
                return is_mutual;
            }

            public void setIs_mutual(boolean is_mutual) {
                this.is_mutual = is_mutual;
            }
        }
    }


}
