package com.wetime.fanc.web.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/8.
 */

public class NewsWebBean {

    /**
     * error : 0
     * msg :
     * data : {"list":[{"originTime":1492956973000,"is_readed":false,"author":"大猫体育2","atlasNum":"0","cause":"0","recommend":"0","type":"1","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=219","atlas_num":"0图","likeNum":"0","cover":"310030","commentNum":"0","score":"0","newsId":"8","spiderTime":1517482676000,"read_num":"0次浏览","readNum":"0","name":"纳达尔红土第50冠成历史第一 大师赛第29冠仅次于小德","id":"219","state":"1","time":"02-01","contentType":"0","cid":"1","covers":[{"url":"https://staticcdntest.fantuanlife.com/uimage/51/40/83/b9/514083b93a2ab5b5154bbaa72dc22b7f.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/51/40/83/b9/514083b93a2ab5b5154bbaa72dc22b7f.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/","height":"0","width":"0","longCover":false}]},{"originTime":1496358424000,"is_readed":false,"author":"大猫体育2","atlasNum":"0","cause":"0","recommend":"0","type":"1","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=212","atlas_num":"0图","likeNum":"0","cover":"310000","commentNum":"0","score":"20","newsId":"8","spiderTime":1517482650000,"read_num":"1次浏览","readNum":"1","name":"马龙张继科神同步都丢两局险胜 混双方博进四强抗日将战石川佳纯","id":"212","state":"1","time":"02-01","contentType":"0","cid":"1","covers":[{"url":"https://staticcdntest.fantuanlife.com/uimage/37/43/60/71/37436071844593ae7d716a27713c3e86.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/37/43/60/71/37436071844593ae7d716a27713c3e86.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/","height":"0","width":"0","longCover":false}]},{"originTime":1499610960000,"is_readed":false,"author":"大猫体育2","atlasNum":"0","cause":"0","recommend":"0","type":"1","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=140","atlas_num":"0图","likeNum":"0","cover":"309702","commentNum":"0","score":"20","newsId":"8","spiderTime":1517482392000,"read_num":"1次浏览","readNum":"1","name":"丁俊晖两胜特鲁姆普导演大逆转第二次夺冠 4-3翻盘中国完成世界杯三连冠","id":"140","state":"1","time":"02-01","contentType":"0","cid":"1","covers":[{"url":"https://staticcdntest.fantuanlife.com/uimage/75/af/bf/42/75afbf422b384599749490f368f3981d.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/75/af/bf/42/75afbf422b384599749490f368f3981d.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/","height":"0","width":"0","longCover":false}]},{"originTime":1492904187000,"is_readed":false,"author":"大猫体育2","atlasNum":"0","cause":"0","recommend":"0","type":"1","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=130","atlas_num":"0图","likeNum":"0","cover":"309662","commentNum":"0","score":"0","newsId":"8","spiderTime":1517482363000,"read_num":"0次浏览","readNum":"0","name":"丁俊晖18次战奥沙利文只赢4次 赔率不被看好有望13-11胜","id":"130","state":"1","time":"02-01","contentType":"0","cid":"1","covers":[{"url":"https://staticcdntest.fantuanlife.com/uimage/54/89/2d/26/54892d26a3fc3f4ef11e93159490789c.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/54/89/2d/26/54892d26a3fc3f4ef11e93159490789c.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/","height":"0","width":"0","longCover":false}]},{"originTime":1516550400000,"is_readed":false,"author":"竹子 海斌 秋秋","atlasNum":"0","cause":"0","recommend":"0","type":"1","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=59","atlas_num":"0图","likeNum":"0","cover":"307780","commentNum":"0","score":"20","newsId":"2","spiderTime":1516776131000,"read_num":"1次浏览","readNum":"1","name":"憋足180天，终于等到TA第二家分店！周末7点起床，就为这好吃又有颜の粤式茶点\u2026","id":"59","state":"1","time":"01-24","contentType":"0","cid":"1","covers":[{"url":"https://staticcdntest.fantuanlife.com/uimage/4f/4d/d0/c6/4f4dd0c6cbd51288b4bf4b42ea216d25.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/4f/4d/d0/c6/4f4dd0c6cbd51288b4bf4b42ea216d25.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/","height":"0","width":"0","longCover":false}]}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * originTime : 1492956973000
             * is_readed : false
             * author : 大猫体育2
             * atlasNum : 0
             * cause : 0
             * recommend : 0
             * type : 1
             * article_url : https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=219
             * atlas_num : 0图
             * likeNum : 0
             * cover : 310030
             * commentNum : 0
             * score : 0
             * newsId : 8
             * spiderTime : 1517482676000
             * read_num : 0次浏览
             * readNum : 0
             * name : 纳达尔红土第50冠成历史第一 大师赛第29冠仅次于小德
             * id : 219
             * state : 1
             * time : 02-01
             * contentType : 0
             * cid : 1
             * covers : [{"url":"https://staticcdntest.fantuanlife.com/uimage/51/40/83/b9/514083b93a2ab5b5154bbaa72dc22b7f.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/51/40/83/b9/514083b93a2ab5b5154bbaa72dc22b7f.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/","height":"0","width":"0","longCover":false}]
             */

            private long originTime;
            private boolean is_readed;
            private String author;
            private String atlasNum;
            private String cause;
            private String recommend;
            private String type;
            private String article_url;
            private String atlas_num;
            private String likeNum;
            private String cover;
            private String commentNum;
            private String score;
            private String newsId;
            private long spiderTime;
            private String read_num;
            private String readNum;
            private String name;
            private String id;
            private String state;
            private String time;
            private String contentType;
            private String cid;
            private List<CoversBean> covers;

            public long getOriginTime() {
                return originTime;
            }

            public void setOriginTime(long originTime) {
                this.originTime = originTime;
            }

            public boolean isIs_readed() {
                return is_readed;
            }

            public void setIs_readed(boolean is_readed) {
                this.is_readed = is_readed;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getAtlasNum() {
                return atlasNum;
            }

            public void setAtlasNum(String atlasNum) {
                this.atlasNum = atlasNum;
            }

            public String getCause() {
                return cause;
            }

            public void setCause(String cause) {
                this.cause = cause;
            }

            public String getRecommend() {
                return recommend;
            }

            public void setRecommend(String recommend) {
                this.recommend = recommend;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getArticle_url() {
                return article_url;
            }

            public void setArticle_url(String article_url) {
                this.article_url = article_url;
            }

            public String getAtlas_num() {
                return atlas_num;
            }

            public void setAtlas_num(String atlas_num) {
                this.atlas_num = atlas_num;
            }

            public String getLikeNum() {
                return likeNum;
            }

            public void setLikeNum(String likeNum) {
                this.likeNum = likeNum;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(String commentNum) {
                this.commentNum = commentNum;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getNewsId() {
                return newsId;
            }

            public void setNewsId(String newsId) {
                this.newsId = newsId;
            }

            public long getSpiderTime() {
                return spiderTime;
            }

            public void setSpiderTime(long spiderTime) {
                this.spiderTime = spiderTime;
            }

            public String getRead_num() {
                return read_num;
            }

            public void setRead_num(String read_num) {
                this.read_num = read_num;
            }

            public String getReadNum() {
                return readNum;
            }

            public void setReadNum(String readNum) {
                this.readNum = readNum;
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

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContentType() {
                return contentType;
            }

            public void setContentType(String contentType) {
                this.contentType = contentType;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public List<CoversBean> getCovers() {
                return covers;
            }

            public void setCovers(List<CoversBean> covers) {
                this.covers = covers;
            }

            public static class CoversBean {
                /**
                 * url : https://staticcdntest.fantuanlife.com/uimage/51/40/83/b9/514083b93a2ab5b5154bbaa72dc22b7f.jpg
                 * compress : https://staticcdntest.fantuanlife.com/uimage/51/40/83/b9/514083b93a2ab5b5154bbaa72dc22b7f.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/
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
