package com.wetime.fanc.home.bean;

import java.util.List;

public class HomeRecListBeanV2 {



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


        private PagingBean paging;
        private List<CirclesBean> circles;
        private List<BannerBean> banner;
        private List<HomeItemBeanV2> list;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<CirclesBean> getCircles() {
            return circles;
        }

        public void setCircles(List<CirclesBean> circles) {
            this.circles = circles;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<HomeItemBeanV2> getList() {
            return list;
        }

        public void setList(List<HomeItemBeanV2> list) {
            this.list = list;
        }

        public static class PagingBean {
            /**
             * total : 0
             * total_page : 1
             * limit : 20
             * pn : 1
             * is_end : false
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

        public static class CirclesBean {
            /**
             * cover : {"url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/","height":"0","width":"0","longCover":false}
             * num : 5
             * name : 吃喝玩乐1111
             * id : 1
             */

            private Cover cover;
            private String num;
            private String name;
            private String id;

            public Cover getCover() {
                return cover;
            }

            public void setCover(Cover cover) {
                this.cover = cover;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }


        }

        public static class BannerBean {
            /**
             * cover : {"url":"https://staticcdntest.fantuanlife.com/uimage/b1/5c/11/3a/b15c113aeddbeb606d938010b88cf8e6.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/b1/5c/11/3a/b15c113aeddbeb606d938010b88cf8e6.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/","height":"258","width":"540","longCover":false}
             * type_id : 766
             * id : 7
             * type : 0
             * content : www
             */

            private Cover cover;
            private String type_id;
            private String id;
            private String type;
            private String content;

            public Cover getCover() {
                return cover;
            }

            public void setCover(Cover cover) {
                this.cover = cover;
            }

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }


        }


    }
}
