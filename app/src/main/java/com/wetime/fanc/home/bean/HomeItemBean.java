package com.wetime.fanc.home.bean;

import java.util.List;

/**
 * Created by zhoukang on 2018/3/1.
 */

public class HomeItemBean {
    /**
     * id : 2
     * uid : 18
     * circle_id : 1
     * content : 这是一条很棒很棒的动态哦····不堵车了耶！！！
     * read_num : 10
     * like_num : 1
     * comment_num : 13
     * time : 02月26日
     * username : 王荣慰
     * avatar : https://staticcdntest.fantuanlife.com/uimage/56/30/c1/8f/5630c18fdd3f4ffd2f65c59325cb93c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
     * circle_name : 吃喝玩乐
     * cover : ["https://staticcdntest.fantuanlife.com/uimage/00/01/a1/5c/0001a15ccc4f3aa227220a94f20c29d3.jpg","https://staticcdntest.fantuanlife.com/uimage/00/01/a5/9f/0001a59f2b1e5babff2852ec8813593d.jpg"]
     * type : 19
     * has_like : true
     *  "longitude": "0",
     "latitude": "0",
     "location": "",
     */

    private String id;
    private String uid;
    private String circle_id;
    private String longitude;
    private String latitude;
    private String location;
    private String content;
    private String read_num;
    private String like_num;
    private String comment_num;
    private String time;
    private String username;
    private String avatar;
    private String circle_name;
    private int type;
    private boolean has_like;
    private boolean is_news;
    private boolean circle_owner = false;
    private List<String> cover;


    //热评
    private String mid;
    private String merchant_name;
    private String score;
    private String reply;
    private String year;
    private String month;
    private String day;

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

//    private String id;
    private String name;
    private String news_name;
    //    private String time;
//    private String read_num;
    private String article_url;
    //    private int type;
//    private List<String> cover;
    private List<BannerBean> banner;
    private  String emptyType;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmptyType() {
        return emptyType;
    }

    public void setEmptyType(String emptyType) {
        this.emptyType = emptyType;
    }

    public boolean isIs_news() {
        return is_news;
    }

    public void setIs_news(boolean is_news) {
        this.is_news = is_news;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isCircle_owner() {
        return circle_owner;
    }

    public void setCircle_owner(boolean circle_owner) {
        this.circle_owner = circle_owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNews_name() {
        return news_name;
    }

    public void setNews_name(String news_name) {
        this.news_name = news_name;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCircle_id() {
        return circle_id;
    }

    public void setCircle_id(String circle_id) {
        this.circle_id = circle_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getCircle_name() {
        return circle_name;
    }

    public void setCircle_name(String circle_name) {
        this.circle_name = circle_name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isHas_like() {
        return has_like;
    }

    public void setHas_like(boolean has_like) {
        this.has_like = has_like;
    }

    public List<String> getCover() {
        return cover;
    }

    public void setCover(List<String> cover) {
        this.cover = cover;
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
}
