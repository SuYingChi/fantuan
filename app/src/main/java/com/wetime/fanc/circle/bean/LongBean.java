package com.wetime.fanc.circle.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class LongBean {


    /**
     * error : 0
     * msg :
     * data : {"comment_num":"0","circle_id":"0","reply_time":"2018-04-19 16:13:06","latitude":"0.0","type":"1","title":"长文测试","content":"","cover":["https://staticcdntest.fantuanlife.com/uimage/aa/48/cf/fe/aa48cffe2cac19a8d945260db051cbf5.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"],"uid":"2","score":"0","current_uid":"2","is_news":true,"read_num":"2","comment_list":[],"id":"406","state":"1","circle_name":"","longitude":"0.0","like_num":"0","is_follow":false,"is_owner":true,"paging":{"total":"0","total_page":"0","limit":"20","pn":"1","is_end":false},"avatar":"https://staticcdntest.fantuanlife.com/uimage/dd/d8/95/ca/ddd895cace5fd359cdbc15f62965ce73.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","is_manager":false,"contents":[{"content":"","des":"","imageUrl":"","title":"标题","type":"0"},{"content":"正文文字","des":"","imageUrl":"","title":"","type":"1"},{"content":"","des":"图片描述","imageUrl":"/storage/emulated/0/wandoujia/downloader/openscreen/open_screen_bg_img_1527.png","title":"","type":"2"},{"content":"振文","des":"","imageUrl":"","title":"","type":"1"},{"content":"","des":"描述","imageUrl":"/storage/emulated/0/wandoujia/downloader/openscreen/open_screen_bg_img_1527.png","title":"","type":"2"},{"content":"","des":"","imageUrl":"","title":"","type":"1"}],"current_avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png","location":"","time":"2018-04-19 16:13:06","image_id":"352703","like_list":[],"has_like":false,"username":"哈哈哈哈"}
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
         * comment_num : 0
         * circle_id : 0
         * reply_time : 2018-04-19 16:13:06
         * latitude : 0.0
         * type : 1
         * title : 长文测试
         * content :
         * cover : ["https://staticcdntest.fantuanlife.com/uimage/aa/48/cf/fe/aa48cffe2cac19a8d945260db051cbf5.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"]
         * uid : 2
         * score : 0
         * current_uid : 2
         * is_news : true
         * read_num : 2
         * comment_list : []
         * id : 406
         * state : 1
         * circle_name :
         * longitude : 0.0
         * like_num : 0
         * is_follow : false
         * is_owner : true
         * paging : {"total":"0","total_page":"0","limit":"20","pn":"1","is_end":false}
         * avatar : https://staticcdntest.fantuanlife.com/uimage/dd/d8/95/ca/ddd895cace5fd359cdbc15f62965ce73.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/
         * is_manager : false
         * contents : [{"content":"","des":"","imageUrl":"","title":"标题","type":"0"},{"content":"正文文字","des":"","imageUrl":"","title":"","type":"1"},{"content":"","des":"图片描述","imageUrl":"/storage/emulated/0/wandoujia/downloader/openscreen/open_screen_bg_img_1527.png","title":"","type":"2"},{"content":"振文","des":"","imageUrl":"","title":"","type":"1"},{"content":"","des":"描述","imageUrl":"/storage/emulated/0/wandoujia/downloader/openscreen/open_screen_bg_img_1527.png","title":"","type":"2"},{"content":"","des":"","imageUrl":"","title":"","type":"1"}]
         * current_avatar : https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png
         * location :
         * time : 2018-04-19 16:13:06
         * image_id : 352703
         * like_list : []
         * has_like : false
         * username : 哈哈哈哈
         */

        private String comment_num;
        private String circle_id;
        private String reply_time;
        private String latitude;
        private String type;
        private String title;
        private String content;
        private String uid;
        private String score;
        private String current_uid;
        private boolean is_news;
        private String read_num;
        private String id;
        private String state;
        private String circle_name;
        private String longitude;
        private String like_num;
        private boolean is_follow;
        private boolean is_owner;
        private PagingBean paging;
        private String avatar;
        private String article_url;
        private boolean is_manager;
        private boolean is_delete;
        private String current_avatar;
        private String location;
        private String time;
        private String image_id;
        private boolean has_like;
        private String username;
        private List<String> cover;
        private List<ActDetailBean.DataBean.CommentListBean> comment_list;
        private List<LongTextBean> contents;
        private List<ActDetailBean.DataBean.LikeListBean> like_list;


        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public boolean isIs_delete() {
            return is_delete;
        }

        public void setIs_delete(boolean is_delete) {
            this.is_delete = is_delete;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getCircle_id() {
            return circle_id;
        }

        public void setCircle_id(String circle_id) {
            this.circle_id = circle_id;
        }

        public String getReply_time() {
            return reply_time;
        }

        public void setReply_time(String reply_time) {
            this.reply_time = reply_time;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getCurrent_uid() {
            return current_uid;
        }

        public void setCurrent_uid(String current_uid) {
            this.current_uid = current_uid;
        }

        public boolean isIs_news() {
            return is_news;
        }

        public void setIs_news(boolean is_news) {
            this.is_news = is_news;
        }

        public String getRead_num() {
            return read_num;
        }

        public void setRead_num(String read_num) {
            this.read_num = read_num;
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

        public String getCircle_name() {
            return circle_name;
        }

        public void setCircle_name(String circle_name) {
            this.circle_name = circle_name;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public boolean isIs_follow() {
            return is_follow;
        }

        public void setIs_follow(boolean is_follow) {
            this.is_follow = is_follow;
        }

        public boolean isIs_owner() {
            return is_owner;
        }

        public void setIs_owner(boolean is_owner) {
            this.is_owner = is_owner;
        }

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public boolean isIs_manager() {
            return is_manager;
        }

        public void setIs_manager(boolean is_manager) {
            this.is_manager = is_manager;
        }

        public String getCurrent_avatar() {
            return current_avatar;
        }

        public void setCurrent_avatar(String current_avatar) {
            this.current_avatar = current_avatar;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getImage_id() {
            return image_id;
        }

        public void setImage_id(String image_id) {
            this.image_id = image_id;
        }

        public boolean isHas_like() {
            return has_like;
        }

        public void setHas_like(boolean has_like) {
            this.has_like = has_like;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<String> getCover() {
            return cover;
        }

        public void setCover(List<String> cover) {
            this.cover = cover;
        }

        public List<ActDetailBean.DataBean.CommentListBean> getComment_list() {
            return comment_list;
        }

        public void setComment_list(List<ActDetailBean.DataBean.CommentListBean> comment_list) {
            this.comment_list = comment_list;
        }

        public List<LongTextBean> getContents() {
            return contents;
        }

        public void setContents(List<LongTextBean> contents) {
            this.contents = contents;
        }

        public List<ActDetailBean.DataBean.LikeListBean> getLike_list() {
            return like_list;
        }

        public void setLike_list(List<ActDetailBean.DataBean.LikeListBean> like_list) {
            this.like_list = like_list;
        }

        public static class PagingBean {
            /**
             * total : 0
             * total_page : 0
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

        public static class ContentsBean {
            /**
             * content :
             * des :
             * imageUrl :
             * title : 标题
             * type : 0
             */

            private String content;
            private String des;
            private String imageUrl;
            private String title;
            private String type;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}

