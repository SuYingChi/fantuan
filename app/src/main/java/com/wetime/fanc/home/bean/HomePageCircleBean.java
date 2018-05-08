package com.wetime.fanc.home.bean;

import java.util.List;

/**
 * Created by admin on 2018/5/7.
 */

public class HomePageCircleBean {

    /**
     * error : 0
     * msg :
     * data : {"notmiss":[{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"intro":"好，不错","name":"吃喝玩乐1111","id":"1","following_num":"7"},{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/04/36/be/a2/0436bea2bf66d31c08d3efed034e0c77.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/04/36/be/a2/0436bea2bf66d31c08d3efed034e0c77.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"intro":"1111","name":"测试测试测试","id":"4","following_num":"6"},{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/1d/64/2f/e1/1d642fe16246a9fe16aa22e27dffa201.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/1d/64/2f/e1/1d642fe16246a9fe16aa22e27dffa201.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"intro":"winner专属","name":"winner专属","id":"42","following_num":"5"},{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"intro":"333","name":"测试3","id":"6","following_num":"5"},{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/12/64/ba/36/1264ba3644b453316b9ba4418c90fd98.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/12/64/ba/36/1264ba3644b453316b9ba4418c90fd98.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"intro":"铲屎官的日常","name":"铲屎官","id":"49","following_num":"5"},{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/dd/de/4e/35/ddde4e35208474aaace0375f6453bea5.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/dd/de/4e/35/ddde4e35208474aaace0375f6453bea5.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"intro":"花花专属","name":"花花专属","id":"43","following_num":"5"}],"paging":{"total":"0","total_page":"1","limit":"2147483647","pn":"1","is_end":true},"list":[{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"today_num":"0","name":"测试5","id":"8"}]}
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
         * notmiss : [{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"intro":"好，不错","name":"吃喝玩乐1111","id":"1","following_num":"7"},{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/04/36/be/a2/0436bea2bf66d31c08d3efed034e0c77.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/04/36/be/a2/0436bea2bf66d31c08d3efed034e0c77.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"intro":"1111","name":"测试测试测试","id":"4","following_num":"6"},{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/1d/64/2f/e1/1d642fe16246a9fe16aa22e27dffa201.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/1d/64/2f/e1/1d642fe16246a9fe16aa22e27dffa201.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"intro":"winner专属","name":"winner专属","id":"42","following_num":"5"},{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"intro":"333","name":"测试3","id":"6","following_num":"5"},{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/12/64/ba/36/1264ba3644b453316b9ba4418c90fd98.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/12/64/ba/36/1264ba3644b453316b9ba4418c90fd98.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"intro":"铲屎官的日常","name":"铲屎官","id":"49","following_num":"5"},{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/dd/de/4e/35/ddde4e35208474aaace0375f6453bea5.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/dd/de/4e/35/ddde4e35208474aaace0375f6453bea5.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"intro":"花花专属","name":"花花专属","id":"43","following_num":"5"}]
         * paging : {"total":"0","total_page":"1","limit":"2147483647","pn":"1","is_end":true}
         * list : [{"cover":{"url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false},"today_num":"0","name":"测试5","id":"8"}]
         */

        private PagingBean paging;
        private List<NotmissBean> notmiss;
        private List<ListBean> list;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<NotmissBean> getNotmiss() {
            return notmiss;
        }

        public void setNotmiss(List<NotmissBean> notmiss) {
            this.notmiss = notmiss;
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
             * limit : 2147483647
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

        public static class NotmissBean {
            /**
             * cover : {"url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false}
             * intro : 好，不错
             * name : 吃喝玩乐1111
             * id : 1
             * following_num : 7
             */

            private CoverBean cover;
            private String intro;
            private String name;
            private String id;
            private String following_num;

            public CoverBean getCover() {
                return cover;
            }

            public void setCover(CoverBean cover) {
                this.cover = cover;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
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

            public String getFollowing_num() {
                return following_num;
            }

            public void setFollowing_num(String following_num) {
                this.following_num = following_num;
            }

            public static class CoverBean {
                /**
                 * url : https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg
                 * compress : https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/
                 * height : 0
                 * width : 0
                 * longCover : false
                 */

                private String url;
                private String compress;
                private String height;
                private String width;
                private boolean longCover;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getCompress() {
                    return compress;
                }

                public void setCompress(String compress) {
                    this.compress = compress;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public boolean isLongCover() {
                    return longCover;
                }

                public void setLongCover(boolean longCover) {
                    this.longCover = longCover;
                }
            }
        }

        public static class ListBean {
            /**
             * cover : {"url":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/","height":"0","width":"0","longCover":false}
             * today_num : 0
             * name : 测试5
             * id : 8
             */

            private CoverBeanX cover;
            private String today_num;
            private String name;
            private String id;

            public CoverBeanX getCover() {
                return cover;
            }

            public void setCover(CoverBeanX cover) {
                this.cover = cover;
            }

            public String getToday_num() {
                return today_num;
            }

            public void setToday_num(String today_num) {
                this.today_num = today_num;
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

            public static class CoverBeanX {
                /**
                 * url : https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg
                 * compress : https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/
                 * height : 0
                 * width : 0
                 * longCover : false
                 */

                private String url;
                private String compress;
                private String height;
                private String width;
                private boolean longCover;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getCompress() {
                    return compress;
                }

                public void setCompress(String compress) {
                    this.compress = compress;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public boolean isLongCover() {
                    return longCover;
                }

                public void setLongCover(boolean longCover) {
                    this.longCover = longCover;
                }
            }
        }
    }
}
