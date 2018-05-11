package com.wetime.fanc.home.bean;

import java.util.List;

public class HomeItemBeanV2 {

    /**
     * comment_num : 0
     * like_num : 1
     * circle_id : 0
     * latitude : 20.025535
     * range : 0
     * avatar : https://staticcdntest.fantuanlife.com/uimage/b8/f3/35/13/b8f33513cf04fc3aaa5dc958dfadb3ac.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/
     * type : 18
     * title : 好咯狗模
     * content : 女诺诺
     * uid : 38
     * is_news : true
     * read_num : 107
     * location : 财富广场(滨海大道)
     * id : 720
     * time : 04月28日 16:04
     * image_id : 418056
     * is_following : true
     * has_like : false
     * longitude : 110.309207
     * covers : [{"url":"https://staticcdntest.fantuanlife.com/uimage/c9/77/18/91/c9771891e4da7ec51c12369d5034b8bb.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/c9/77/18/91/c9771891e4da7ec51c12369d5034b8bb.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/","height":"1024","width":"2048","longCover":false}]
     * username : PPTV哦哦
     * circle_name : 吃喝玩乐1111
     */

    private String comment_num;
    private String like_num;
    private String circle_id;
    private String latitude;
    private String range;
    private String avatar;
    private int type;
    private String title;
    private String content;
    private String uid;
    private boolean is_news;
    private String read_num;
    private String location;
    private String id;
    private String time;
    private String image_id;
    private boolean is_following;
    private boolean has_like;
    private String longitude;
    private String username;
    private String circle_name;
    private List<CoversBean> covers;

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getCircle_id() {
        return circle_id;
    }

    public void setCircle_id(String circle_id) {
        this.circle_id = circle_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isIs_following() {
        return is_following;
    }

    public void setIs_following(boolean is_following) {
        this.is_following = is_following;
    }

    public boolean isHas_like() {
        return has_like;
    }

    public void setHas_like(boolean has_like) {
        this.has_like = has_like;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCircle_name() {
        return circle_name;
    }

    public void setCircle_name(String circle_name) {
        this.circle_name = circle_name;
    }

    public List<CoversBean> getCovers() {
        return covers;
    }

    public void setCovers(List<CoversBean> covers) {
        this.covers = covers;
    }

    public static class CoversBean {
        /**
         * url : https://staticcdntest.fantuanlife.com/uimage/c9/77/18/91/c9771891e4da7ec51c12369d5034b8bb.jpg
         * compress : https://staticcdntest.fantuanlife.com/uimage/c9/77/18/91/c9771891e4da7ec51c12369d5034b8bb.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/
         * height : 1024
         * width : 2048
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

