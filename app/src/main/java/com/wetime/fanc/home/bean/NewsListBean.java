package com.wetime.fanc.home.bean;

import java.util.List;

/**
 * Created by zhoukang on 2018/1/30.
 */

public class NewsListBean {

    /**
     * error : 0
     * msg :
     * data : {"list":[{"id":"87","name":"震惊！吃货团编辑部引发内部大战，竟是因吃饭问题！","cover":["https://staticcdntest.fantuanlife.com/uimage/dd/20/af/82/dd20af82ca287f4b6df6606f60b0aa83.jpg","https://staticcdntest.fantuanlife.com/uimage/c8/40/9f/36/c8409f360807fc916a4ce67b608405ff.jpg","https://staticcdntest.fantuanlife.com/uimage/f1/82/1e/2f/f1821e2fb202870799ce5d4ffae499fd.jpg"],"author":"大白菜","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"3"},{"id":"86","name":"一口绝对塞不完的超美味烤肉，在海口立足那么多年居然越来越火！","cover":["https://staticcdntest.fantuanlife.com/uimage/c8/40/9f/36/c8409f360807fc916a4ce67b608405ff.jpg"],"author":"阿喵","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"1"},{"id":"85","name":"海口这家店，只靠一锅鱼就火了13年，凭什么？！","cover":["https://staticcdntest.fantuanlife.com/uimage/98/d3/f8/49/98d3f849cf437f1330cd09116df91ca5.jpg"],"author":"阿喵","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"1"},{"id":"84","name":"这里有一份海口市的肠粉合集，你确定不看看吗？","cover":["https://staticcdntest.fantuanlife.com/uimage/dd/20/af/82/dd20af82ca287f4b6df6606f60b0aa83.jpg","https://staticcdntest.fantuanlife.com/uimage/a2/1b/9e/e9/a21b9ee9c6942ccebe1dca12ead93c5f.jpg","https://staticcdntest.fantuanlife.com/uimage/c8/40/9f/36/c8409f360807fc916a4ce67b608405ff.jpg"],"author":"大白菜","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"3"},{"id":"83","name":"吃货人手必备！海口12条美食街，我就不信你全都吃过！","cover":["https://staticcdntest.fantuanlife.com/uimage/c2/b8/fb/60/c2b8fb60198a6f999a778c4ca33abf4b.jpg"],"author":"阿秋","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"1"},{"id":"82","name":"做好了拍爆16G内存的准备，但是菜一端上来我就控寄不住我寄几\u2026\u2026","cover":["https://staticcdntest.fantuanlife.com/uimage/32/1d/00/25/321d0025cf72197b0607ea3c4e352bea.jpg"],"author":"肥团","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"1"},{"id":"81","name":"老饕必去海口9家烤肉店！接下来，就是见证味蕾炸裂的时刻！","cover":["https://staticcdntest.fantuanlife.com/uimage/dd/20/af/82/dd20af82ca287f4b6df6606f60b0aa83.jpg","https://staticcdntest.fantuanlife.com/uimage/7d/43/8d/ec/7d438decec9a01207d311a5f31a13864.jpg","https://staticcdntest.fantuanlife.com/uimage/c8/40/9f/36/c8409f360807fc916a4ce67b608405ff.jpg"],"author":"肥团","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"3"},{"id":"80","name":"去什么网红店！我更愿意来这家老牌糖水店撸一碗地瓜汤！","cover":["https://staticcdntest.fantuanlife.com/uimage/ca/3c/e3/07/ca3ce30783c21678d07df101f4b8bc64.jpg"],"author":"大白菜","time":"01-24","read_num":"68次浏览","article_url":"www.baidu.com","type":"1"},{"id":"79","name":"成都小姐姐亲测，在海口发现一家超正的串串店！","cover":["https://staticcdntest.fantuanlife.com/uimage/e2/36/55/ad/e23655ada69af30418208c5bde1e684a.jpg"],"author":"团花","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"1"},{"id":"78","name":"吃货的修炼手册，今天又解锁了一种吃鸡方式！！","cover":["https://staticcdntest.fantuanlife.com/uimage/aa/f7/8e/b2/aaf78eb268d080eb0311efa230edb32a.jpg"],"author":"夏羽","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"1"}],"update_num":"0","paging":{"total":"40","total_page":4,"limit":10,"pn":1,"is_end":0}}
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
         * list : [{"id":"87","name":"震惊！吃货团编辑部引发内部大战，竟是因吃饭问题！","cover":["https://staticcdntest.fantuanlife.com/uimage/dd/20/af/82/dd20af82ca287f4b6df6606f60b0aa83.jpg","https://staticcdntest.fantuanlife.com/uimage/c8/40/9f/36/c8409f360807fc916a4ce67b608405ff.jpg","https://staticcdntest.fantuanlife.com/uimage/f1/82/1e/2f/f1821e2fb202870799ce5d4ffae499fd.jpg"],"author":"大白菜","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"3"},{"id":"86","name":"一口绝对塞不完的超美味烤肉，在海口立足那么多年居然越来越火！","cover":["https://staticcdntest.fantuanlife.com/uimage/c8/40/9f/36/c8409f360807fc916a4ce67b608405ff.jpg"],"author":"阿喵","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"1"},{"id":"85","name":"海口这家店，只靠一锅鱼就火了13年，凭什么？！","cover":["https://staticcdntest.fantuanlife.com/uimage/98/d3/f8/49/98d3f849cf437f1330cd09116df91ca5.jpg"],"author":"阿喵","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"1"},{"id":"84","name":"这里有一份海口市的肠粉合集，你确定不看看吗？","cover":["https://staticcdntest.fantuanlife.com/uimage/dd/20/af/82/dd20af82ca287f4b6df6606f60b0aa83.jpg","https://staticcdntest.fantuanlife.com/uimage/a2/1b/9e/e9/a21b9ee9c6942ccebe1dca12ead93c5f.jpg","https://staticcdntest.fantuanlife.com/uimage/c8/40/9f/36/c8409f360807fc916a4ce67b608405ff.jpg"],"author":"大白菜","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"3"},{"id":"83","name":"吃货人手必备！海口12条美食街，我就不信你全都吃过！","cover":["https://staticcdntest.fantuanlife.com/uimage/c2/b8/fb/60/c2b8fb60198a6f999a778c4ca33abf4b.jpg"],"author":"阿秋","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"1"},{"id":"82","name":"做好了拍爆16G内存的准备，但是菜一端上来我就控寄不住我寄几\u2026\u2026","cover":["https://staticcdntest.fantuanlife.com/uimage/32/1d/00/25/321d0025cf72197b0607ea3c4e352bea.jpg"],"author":"肥团","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"1"},{"id":"81","name":"老饕必去海口9家烤肉店！接下来，就是见证味蕾炸裂的时刻！","cover":["https://staticcdntest.fantuanlife.com/uimage/dd/20/af/82/dd20af82ca287f4b6df6606f60b0aa83.jpg","https://staticcdntest.fantuanlife.com/uimage/7d/43/8d/ec/7d438decec9a01207d311a5f31a13864.jpg","https://staticcdntest.fantuanlife.com/uimage/c8/40/9f/36/c8409f360807fc916a4ce67b608405ff.jpg"],"author":"肥团","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"3"},{"id":"80","name":"去什么网红店！我更愿意来这家老牌糖水店撸一碗地瓜汤！","cover":["https://staticcdntest.fantuanlife.com/uimage/ca/3c/e3/07/ca3ce30783c21678d07df101f4b8bc64.jpg"],"author":"大白菜","time":"01-24","read_num":"68次浏览","article_url":"www.baidu.com","type":"1"},{"id":"79","name":"成都小姐姐亲测，在海口发现一家超正的串串店！","cover":["https://staticcdntest.fantuanlife.com/uimage/e2/36/55/ad/e23655ada69af30418208c5bde1e684a.jpg"],"author":"团花","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"1"},{"id":"78","name":"吃货的修炼手册，今天又解锁了一种吃鸡方式！！","cover":["https://staticcdntest.fantuanlife.com/uimage/aa/f7/8e/b2/aaf78eb268d080eb0311efa230edb32a.jpg"],"author":"夏羽","time":"01-24","read_num":"0次浏览","article_url":"www.baidu.com","type":"1"}]
         * update_num : 0
         * paging : {"total":"40","total_page":4,"limit":10,"pn":1,"is_end":0}
         */

        private String update_num;
        private PagingBean paging;
        private List<ListBean> list;

        public String getUpdate_num() {
            return update_num;
        }

        public void setUpdate_num(String update_num) {
            this.update_num = update_num;
        }

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
             * total : 40
             * total_page : 4
             * limit : 10
             * pn : 1
             * is_end : 0
             */

            private String total;
            private int total_page;
            private int limit;
            private int pn;
            private boolean is_end;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
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
             * id : 87
             * name : 震惊！吃货团编辑部引发内部大战，竟是因吃饭问题！
             * cover : ["https://staticcdntest.fantuanlife.com/uimage/dd/20/af/82/dd20af82ca287f4b6df6606f60b0aa83.jpg","https://staticcdntest.fantuanlife.com/uimage/c8/40/9f/36/c8409f360807fc916a4ce67b608405ff.jpg","https://staticcdntest.fantuanlife.com/uimage/f1/82/1e/2f/f1821e2fb202870799ce5d4ffae499fd.jpg"]
             * author : 大白菜
             * time : 01-24
             * read_num : 0次浏览
             * article_url : www.baidu.com
             * type : 3
             */

            private String id;
            private String name;
            private String author;
            private String time;
            private String read_num;
            private String article_url;
            private int type;
            private List<String> cover;
            private List<BannerBean> banner;

            public List<BannerBean> getBanner() {
                return banner;
            }

            public void setBanner(List<BannerBean> banner) {
                this.banner = banner;
            }

            public static class BannerBean {
                /**
                 * url :
                 * img : https://staticcdntest.fantuanlife.com/image/fant/news_banner.png
                 */

                private String url;
                private String img;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }

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

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getRead_num() {
                return read_num;
            }

            public void setRead_num(String read_num) {
                this.read_num = read_num;
            }

            public String getArticle_url() {
                return article_url;
            }

            public void setArticle_url(String article_url) {
                this.article_url = article_url;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<String> getCover() {
                return cover;
            }

            public void setCover(List<String> cover) {
                this.cover = cover;
            }
        }
    }
}
