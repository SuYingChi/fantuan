package com.wetime.fanc.circle.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 */

public class ClickNumBean {

    /**
     * error : 0
     * msg :
     * data : {"paging":{"total":"0","total_page":"1","limit":"10","pn":"1","is_end":true},"list":[{"uid":"21","is_news":true,"avatar":"https://staticcdntest.fantuanlife.com/uimage/eb/d9/d2/95/ebd9d295eafca45290c070f2334d4725.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":false,"username":"扒一扒"},{"uid":"23","is_news":true,"avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":false,"username":"神经病的日常"},{"uid":"68","is_news":true,"avatar":"https://staticcdntest.fantuanlife.com/uimage/2a/61/06/42/2a610642e02da6deafe2937020ac0002.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":true,"username":"海獭海獭海獭啊"},{"uid":"30","is_news":false,"avatar":"https://staticcdntest.fantuanlife.com/uimage/32/f8/be/14/32f8be14431608e3503f5ae0f899c535.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":false,"username":"🐠"},{"uid":"38","is_news":true,"avatar":"https://staticcdntest.fantuanlife.com/uimage/b8/f3/35/13/b8f33513cf04fc3aaa5dc958dfadb3ac.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":true,"username":"PPTV哦哦"},{"uid":"57","is_news":false,"avatar":"https://fanttest.fantuanlife.com/image/fant/default_avatar.png","is_following":false,"username":"157****1977"},{"uid":"33","is_news":false,"avatar":"https://staticcdntest.fantuanlife.com/uimage/c2/b5/bc/1d/c2b5bc1d5e90e490b819065217c86ed4.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":false,"username":"不告诉你我是谁"},{"uid":"37","is_news":false,"avatar":"https://staticcdntest.fantuanlife.com/uimage/e4/d5/fd/ff/e4d5fdffab49e67338bf06734e6a21bf.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":false,"username":"Jian"}]}
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
         * paging : {"total":"0","total_page":"1","limit":"10","pn":"1","is_end":true}
         * list : [{"uid":"21","is_news":true,"avatar":"https://staticcdntest.fantuanlife.com/uimage/eb/d9/d2/95/ebd9d295eafca45290c070f2334d4725.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":false,"username":"扒一扒"},{"uid":"23","is_news":true,"avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":false,"username":"神经病的日常"},{"uid":"68","is_news":true,"avatar":"https://staticcdntest.fantuanlife.com/uimage/2a/61/06/42/2a610642e02da6deafe2937020ac0002.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":true,"username":"海獭海獭海獭啊"},{"uid":"30","is_news":false,"avatar":"https://staticcdntest.fantuanlife.com/uimage/32/f8/be/14/32f8be14431608e3503f5ae0f899c535.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":false,"username":"🐠"},{"uid":"38","is_news":true,"avatar":"https://staticcdntest.fantuanlife.com/uimage/b8/f3/35/13/b8f33513cf04fc3aaa5dc958dfadb3ac.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":true,"username":"PPTV哦哦"},{"uid":"57","is_news":false,"avatar":"https://fanttest.fantuanlife.com/image/fant/default_avatar.png","is_following":false,"username":"157****1977"},{"uid":"33","is_news":false,"avatar":"https://staticcdntest.fantuanlife.com/uimage/c2/b5/bc/1d/c2b5bc1d5e90e490b819065217c86ed4.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":false,"username":"不告诉你我是谁"},{"uid":"37","is_news":false,"avatar":"https://staticcdntest.fantuanlife.com/uimage/e4/d5/fd/ff/e4d5fdffab49e67338bf06734e6a21bf.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_following":false,"username":"Jian"}]
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
             * total : 0
             * total_page : 1
             * limit : 10
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

        public static class ListBean {
            /**
             * uid : 21
             * is_news : true
             * avatar : https://staticcdntest.fantuanlife.com/uimage/eb/d9/d2/95/ebd9d295eafca45290c070f2334d4725.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/
             * is_following : false
             * username : 扒一扒
             */

            private String uid;
            private boolean is_news;
            private String avatar;
            private boolean is_following;
            private String username;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public boolean isIs_news() {
                return is_news;
            }

            public void setIs_news(boolean is_news) {
                this.is_news = is_news;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public boolean isIs_following() {
                return is_following;
            }

            public void setIs_following(boolean is_following) {
                this.is_following = is_following;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
