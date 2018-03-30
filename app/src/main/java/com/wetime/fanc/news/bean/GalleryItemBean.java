package com.wetime.fanc.news.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class GalleryItemBean implements Serializable{

    /**
     * error : 0
     * msg :
     * data : {"id":"7241","news_id":"20","cid":"7","name":"冬季阳光体育大会：青少年亚布力切磋雪技 一同筑梦北京冬奥","author":"中国新闻网","read_num":"243","like_num":"2","comment_num":"0","recommend":"0","cause":"0","state":"1","time":"02-08","origin_time":"2018-02-08 21:53:28","spider_time":"2018-02-08 22:14:32","type":"0","score":"2335","content_type":"1","atlas_num":"2","atlas_content":[{"img_url":"https://www.baidu.com/img/bd_logo1.png","content":"百度"},{"img_url":"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2878042398,2115472320&fm=58&s=CFD77C9681A54D1118E7A556030000B3&bpow=121&bpoh=75","content":"腾讯"}],"is_like":false,"is_author":false,"is_collect":false,"is_following":false,"follower_num":"0","news_name":"中国新闻网","news_type":"1","uid":"250","avatar":"https://staticcdntest.fantuanlife.com/uimage/3e/83/42/ea/3e8342ea10a38fd2d911170cd5fb183b.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"}
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
         * id : 7241
         * news_id : 20
         * cid : 7
         * name : 冬季阳光体育大会：青少年亚布力切磋雪技 一同筑梦北京冬奥
         * author : 中国新闻网
         * read_num : 243
         * like_num : 2
         * comment_num : 0
         * recommend : 0
         * cause : 0
         * state : 1
         * time : 02-08
         * origin_time : 2018-02-08 21:53:28
         * spider_time : 2018-02-08 22:14:32
         * type : 0
         * score : 2335
         * content_type : 1
         * atlas_num : 2
         * atlas_content : [{"img_url":"https://www.baidu.com/img/bd_logo1.png","content":"百度"},{"img_url":"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2878042398,2115472320&fm=58&s=CFD77C9681A54D1118E7A556030000B3&bpow=121&bpoh=75","content":"腾讯"}]
         * is_like : false
         * is_author : false
         * is_collect : false
         * is_following : false
         * follower_num : 0
         * news_name : 中国新闻网
         * news_type : 1
         * uid : 250
         * avatar : https://staticcdntest.fantuanlife.com/uimage/3e/83/42/ea/3e8342ea10a38fd2d911170cd5fb183b.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
         */

        @SerializedName("id")
        private String idX;
        private String news_id;
        private String cid;
        private String name;
        private String author;
        private String read_num;
        private String like_num;
        private String comment_num;
        private String recommend;
        private String cause;
        private String state;
        private String time;
        private String origin_time;
        private String spider_time;
        @SerializedName("type")
        private String typeX;
        private String score;
        private String content_type;
        private String atlas_num;
        private boolean is_like;
        private boolean is_author;
        private boolean is_collect;
        private boolean is_following;
        private String follower_num;
        private String news_name;
        private String news_type;
        private String uid;
        private String avatar;
        private List<AtlasContentBean> atlas_content;

        public String getIdX() {
            return idX;
        }

        public void setIdX(String idX) {
            this.idX = idX;
        }

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
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

        public String getRead_num() {
            return read_num;
        }

        public void setRead_num(String read_num) {
            this.read_num = read_num;
        }

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
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

        public String getOrigin_time() {
            return origin_time;
        }

        public void setOrigin_time(String origin_time) {
            this.origin_time = origin_time;
        }

        public String getSpider_time() {
            return spider_time;
        }

        public void setSpider_time(String spider_time) {
            this.spider_time = spider_time;
        }

        public String getTypeX() {
            return typeX;
        }

        public void setTypeX(String typeX) {
            this.typeX = typeX;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getContent_type() {
            return content_type;
        }

        public void setContent_type(String content_type) {
            this.content_type = content_type;
        }

        public String getAtlas_num() {
            return atlas_num;
        }

        public void setAtlas_num(String atlas_num) {
            this.atlas_num = atlas_num;
        }

        public boolean isIs_like() {
            return is_like;
        }

        public void setIs_like(boolean is_like) {
            this.is_like = is_like;
        }

        public boolean isIs_author() {
            return is_author;
        }

        public void setIs_author(boolean is_author) {
            this.is_author = is_author;
        }

        public boolean isIs_collect() {
            return is_collect;
        }

        public void setIs_collect(boolean is_collect) {
            this.is_collect = is_collect;
        }

        public boolean isIs_following() {
            return is_following;
        }

        public void setIs_following(boolean is_following) {
            this.is_following = is_following;
        }

        public String getFollower_num() {
            return follower_num;
        }

        public void setFollower_num(String follower_num) {
            this.follower_num = follower_num;
        }

        public String getNews_name() {
            return news_name;
        }

        public void setNews_name(String news_name) {
            this.news_name = news_name;
        }

        public String getNews_type() {
            return news_type;
        }

        public void setNews_type(String news_type) {
            this.news_type = news_type;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public List<AtlasContentBean> getAtlas_content() {
            return atlas_content;
        }

        public void setAtlas_content(List<AtlasContentBean> atlas_content) {
            this.atlas_content = atlas_content;
        }

        public static class AtlasContentBean {
            /**
             * img_url : https://www.baidu.com/img/bd_logo1.png
             * content : 百度
             */

            private String img_url;
            private String content;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
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

