package com.wetime.fanc.news.bean;


import com.wetime.fanc.home.bean.Cover;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoukang on 2018/3/1.
 */

public class NewsListItemBean implements Serializable {
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
     * "longitude": "0",
     * "latitude": "0",
     * "location": "",
     * atlas_num":"3图"
     */

    private String id;
    private String uid;
    private String title;
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
    private String atlas_num;
    private String circle_name;
    private int type;
    private boolean has_like;
    private boolean is_news;
    private boolean circle_owner;
    private boolean circle_admin;
    private List<Cover> covers;
    private boolean is_following;
    private boolean is_collected;
    private boolean is_like;
    private boolean is_readed;
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
    private String avatar;
    //    private int type;
//    private List<String> cover;
    private List<BannerBean> banner;
    private String emptyType;
    /**
     * special : {"id":"1","name":"测试专题名称测试","intro":"简介简介简介简介简介简介简介简介简介简介啦啦啦","coverStr":"https://staticcdntest.fantuanlife.com/uimage/ce/42/21/ae/ce4221ae23f3f1d5a50174f641425e6f.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","status":"1","createdAt":"2018-04-13 10:27:33","cover":["https://staticcdntest.fantuanlife.com/uimage/ce/42/21/ae/ce4221ae23f3f1d5a50174f641425e6f.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"]}
     * lastest : {"id":"12726","newsId":"47","cid":"1","name":"共创亚洲和世界的美好未来\u2014\u2014解读习近平主席在博鳌亚洲论坛2018年年会开幕式上的主旨演讲","cover":"393283","author":"南海网11","readNum":"0","likeNum":"0","commentNum":"0","recommend":"0","cause":"0","state":"1","time":"2018-04-11 09:06:00","originTime":"2018-04-11 09:06:00","spiderTime":"2018-04-11 19:46:52","type":"2","score":"1200","contentType":"0","atlasNum":"0","article_url":"https://staticcdntest.fantuanlife.com/index.html#/article/detail?article_id=12726"}
     * hottest : {"id":"49","newsId":"1","cid":"1","name":"上海迪士尼花2.4万可插队有问题吗？","cover":"309136","author":"呆毛","readNum":"228","likeNum":"0","commentNum":"11","recommend":"1","cause":"0","state":"1","time":"2018-04-09 15:47:44","originTime":"2018-01-16 00:00:00","spiderTime":"2018-01-24 14:40:45","type":"0","score":"4040","contentType":"1","atlasNum":"2","article_url":"https://staticcdntest.fantuanlife.com/index.html#/article/detail?article_id=49"}
     * elements : [{"id":"2","name":"测试要点二","specialId":"1","createdAt":"2018-04-13 10:27:43"},{"id":"9","name":"dfd","specialId":"1","createdAt":"2018-04-17 13:45:09"}]
     * focused : {"id":"56","newsId":"1","cid":"1","name":"是我太难搞？还是重庆老火锅就是这个味？","cover":"307778","author":"呆毛","readNum":"48","likeNum":"0","commentNum":"0","recommend":"1","cause":"0","state":"1","time":"2018-02-02 16:39:36","originTime":"2017-12-11 00:00:00","spiderTime":"2018-01-24 14:41:42","type":"0","score":"960","contentType":"0","atlasNum":"0","article_url":"https://staticcdntest.fantuanlife.com/index.html#/article/detail?article_id=56"}
     * type : 5
     * cover : ["https://staticcdntest.fantuanlife.com/uimage/e4/97/b9/a2/e497b9a26807858af1c52786a3433596.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","https://staticcdntest.fantuanlife.com/uimage/9e/65/87/11/9e658711b57eb62bee8c105539290681.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","https://staticcdntest.fantuanlife.com/uimage/e5/47/43/e8/e54743e8aedc28f2fb17a6d57ecf5150.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"]
     * read_num : 35次浏览
     * content_type : 0
     * name : 海南这个地方的春色美翻了，还上了央视《新闻联播》！你去了吗？
     * news_name : 连环八卦掌`
     * id : 7462
     * time : 04-12
     * atlas_num : 0图
     * article_url : https://staticcdntest.fantuanlife.com/index.html#/article/detail?article_id=7462
     * news_id : 56
     */

    private SpecialBean special;
    private LastestBean lastest;
    private HottestBean hottest;
    private FocusedBean focused;
    private String content_type;
    private String news_type;
    private String news_id;
    private List<ElementsBean> elements;

    public boolean isIs_following() {
        return is_following;
    }

    public void setIs_following(boolean is_following) {
        this.is_following = is_following;
    }

    public boolean isIs_collected() {
        return is_collected;
    }

    public void setIs_collected(boolean is_collected) {
        this.is_collected = is_collected;
    }

    public boolean isIs_like() {
        return is_like;
    }

    public void setIs_like(boolean is_like) {
        this.is_like = is_like;
    }

    public boolean isIs_readed() {
        return is_readed;
    }

    public void setIs_readed(boolean is_readed) {
        this.is_readed = is_readed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCircle_admin() {
        return circle_admin;
    }

    public void setCircle_admin(boolean circle_admin) {
        this.circle_admin = circle_admin;
    }

    public String getAtlas_num() {
        return atlas_num;
    }

    public void setAtlas_num(String atlas_num) {
        this.atlas_num = atlas_num;
    }

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

    public List<Cover> getCover() {
        return covers;
    }

    public void setCover(List<Cover> cover) {
        this.covers = cover;
    }

    public String getNews_type() {
        return news_type;
    }

    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }

    public List<Cover> getCovers() {
        return covers;
    }

    public void setCovers(List<Cover> covers) {
        this.covers = covers;
    }

    public List<ElementsBean> getElements() {
        return elements;
    }

    public void setElements(List<ElementsBean> elements) {
        this.elements = elements;
    }

    public SpecialBean getSpecial() {
        return special;
    }

    public void setSpecial(SpecialBean special) {
        this.special = special;
    }

    public LastestBean getLastest() {
        return lastest;
    }

    public void setLastest(LastestBean lastest) {
        this.lastest = lastest;
    }

    public HottestBean getHottest() {
        return hottest;
    }

    public void setHottest(HottestBean hottest) {
        this.hottest = hottest;
    }

    public FocusedBean getFocused() {
        return focused;
    }

    public void setFocused(FocusedBean focused) {
        this.focused = focused;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public static class BannerBean implements Serializable {
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

    public static class SpecialBean implements Serializable {
        /**
         * id : 1
         * name : 测试专题名称测试
         * intro : 简介简介简介简介简介简介简介简介简介简介啦啦啦
         * coverStr : https://staticcdntest.fantuanlife.com/uimage/ce/42/21/ae/ce4221ae23f3f1d5a50174f641425e6f.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/
         * status : 1
         * createdAt : 2018-04-13 10:27:33
         * cover : ["https://staticcdntest.fantuanlife.com/uimage/ce/42/21/ae/ce4221ae23f3f1d5a50174f641425e6f.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"]
         */

        private String id;
        private String name;
        private String intro;
        private String coverStr;
        private String status;
        private String createdAt;
        private List<Cover> covers;
        private String article_url;

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
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

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getCoverStr() {
            return coverStr;
        }

        public void setCoverStr(String coverStr) {
            this.coverStr = coverStr;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public List<Cover> getCover() {
            return covers;
        }

        public void setCover(List<Cover> cover) {
            this.covers = cover;
        }
    }

    public static class LastestBean implements Serializable {

        /**
         * comment_num : 2
         * nullusername :
         * is_readed : true
         * nullavatar :
         * cause : 0
         * recommend : 1
         * type : 2
         * atlas_num : 5图
         * news_id : 61
         * cover : 371077
         * score : 1789
         * read_num : 128次浏览
         * spider_time : 2018-04-02 11:14:17
         * content_type : 1
         * news_type : 1
         * is_like : false
         * news_name : null
         * origin_time : 2018-04-02 10:52:00
         * id : 10756
         * state : 1
         * covers : [{"url":"https://staticcdntest.fantuanlife.com/uimage/44/fb/0a/dc/44fb0adc35f3c9f23c382e26572cd098.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/44/fb/0a/dc/44fb0adc35f3c9f23c382e26572cd098.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/","height":"367","width":"550","longCover":false}]
         * like_num : 0
         * author : 光明网
         * is_collected : false
         * avatar : []
         * article_url : https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=10756
         * name : 在荒滩上播撒新希望——酒泉市大力发展戈壁农业
         * time : null
         * is_following : false
         * cid : 5
         */

        private String comment_num;
        private String nullusername;
        private boolean is_readed;
        private String nullavatar;
        private String cause;
        private String uid;
        private String recommend;
        private String type;
        private String atlas_num;
        private String news_id;
        private String cover;
        private String score;
        private String read_num;
        private String spider_time;
        private String content_type;
        private String news_type;
        private boolean is_like;
        private Object news_name;
        private String origin_time;
        private String id;
        private String state;
        private String like_num;
        private String author;
        private boolean is_collected;
        private String article_url;
        private String name;
        private Object time;
        private boolean is_following;
        private String cid;
        private List<Cover> covers;
        private String avatar;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getNullusername() {
            return nullusername;
        }

        public void setNullusername(String nullusername) {
            this.nullusername = nullusername;
        }

        public boolean isIs_readed() {
            return is_readed;
        }

        public void setIs_readed(boolean is_readed) {
            this.is_readed = is_readed;
        }

        public String getNullavatar() {
            return nullavatar;
        }

        public void setNullavatar(String nullavatar) {
            this.nullavatar = nullavatar;
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

        public String getAtlas_num() {
            return atlas_num;
        }

        public void setAtlas_num(String atlas_num) {
            this.atlas_num = atlas_num;
        }

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getRead_num() {
            return read_num;
        }

        public void setRead_num(String read_num) {
            this.read_num = read_num;
        }

        public String getSpider_time() {
            return spider_time;
        }

        public void setSpider_time(String spider_time) {
            this.spider_time = spider_time;
        }

        public String getContent_type() {
            return content_type;
        }

        public void setContent_type(String content_type) {
            this.content_type = content_type;
        }

        public String getNews_type() {
            return news_type;
        }

        public void setNews_type(String news_type) {
            this.news_type = news_type;
        }

        public boolean isIs_like() {
            return is_like;
        }

        public void setIs_like(boolean is_like) {
            this.is_like = is_like;
        }

        public Object getNews_name() {
            return news_name;
        }

        public void setNews_name(Object news_name) {
            this.news_name = news_name;
        }

        public String getOrigin_time() {
            return origin_time;
        }

        public void setOrigin_time(String origin_time) {
            this.origin_time = origin_time;
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

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public boolean isIs_collected() {
            return is_collected;
        }

        public void setIs_collected(boolean is_collected) {
            this.is_collected = is_collected;
        }

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
            this.time = time;
        }

        public boolean isIs_following() {
            return is_following;
        }

        public void setIs_following(boolean is_following) {
            this.is_following = is_following;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public List<Cover> getCovers() {
            return covers;
        }

        public void setCovers(List<Cover> covers) {
            this.covers = covers;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

    }

    public static class HottestBean implements Serializable {

        /**
         * comment_num : 2
         * nullusername :
         * is_readed : true
         * nullavatar :
         * cause : 0
         * recommend : 1
         * type : 2
         * atlas_num : 5图
         * news_id : 61
         * cover : 371077
         * score : 1789
         * read_num : 128次浏览
         * spider_time : 2018-04-02 11:14:17
         * content_type : 1
         * news_type : 1
         * is_like : false
         * news_name : null
         * origin_time : 2018-04-02 10:52:00
         * id : 10756
         * state : 1
         * covers : [{"url":"https://staticcdntest.fantuanlife.com/uimage/44/fb/0a/dc/44fb0adc35f3c9f23c382e26572cd098.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/44/fb/0a/dc/44fb0adc35f3c9f23c382e26572cd098.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/","height":"367","width":"550","longCover":false}]
         * like_num : 0
         * author : 光明网
         * is_collected : false
         * avatar : []
         * article_url : https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=10756
         * name : 在荒滩上播撒新希望——酒泉市大力发展戈壁农业
         * time : null
         * is_following : false
         * cid : 5
         */

        private String comment_num;
        private String nullusername;
        private boolean is_readed;
        private String nullavatar;
        private String cause;
        private String uid;
        private String recommend;
        private String type;
        private String atlas_num;
        private String news_id;
        private String cover;
        private String score;
        private String read_num;
        private String spider_time;
        private String content_type;
        private String news_type;
        private boolean is_like;
        private Object news_name;
        private String origin_time;
        private String id;
        private String state;
        private String like_num;
        private String author;
        private boolean is_collected;
        private String article_url;
        private String name;
        private Object time;
        private boolean is_following;
        private String cid;
        private List<Cover> covers;
        private String avatar;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getNullusername() {
            return nullusername;
        }

        public void setNullusername(String nullusername) {
            this.nullusername = nullusername;
        }

        public boolean isIs_readed() {
            return is_readed;
        }

        public void setIs_readed(boolean is_readed) {
            this.is_readed = is_readed;
        }

        public String getNullavatar() {
            return nullavatar;
        }

        public void setNullavatar(String nullavatar) {
            this.nullavatar = nullavatar;
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

        public String getAtlas_num() {
            return atlas_num;
        }

        public void setAtlas_num(String atlas_num) {
            this.atlas_num = atlas_num;
        }

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getRead_num() {
            return read_num;
        }

        public void setRead_num(String read_num) {
            this.read_num = read_num;
        }

        public String getSpider_time() {
            return spider_time;
        }

        public void setSpider_time(String spider_time) {
            this.spider_time = spider_time;
        }

        public String getContent_type() {
            return content_type;
        }

        public void setContent_type(String content_type) {
            this.content_type = content_type;
        }

        public String getNews_type() {
            return news_type;
        }

        public void setNews_type(String news_type) {
            this.news_type = news_type;
        }

        public boolean isIs_like() {
            return is_like;
        }

        public void setIs_like(boolean is_like) {
            this.is_like = is_like;
        }

        public Object getNews_name() {
            return news_name;
        }

        public void setNews_name(Object news_name) {
            this.news_name = news_name;
        }

        public String getOrigin_time() {
            return origin_time;
        }

        public void setOrigin_time(String origin_time) {
            this.origin_time = origin_time;
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

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public boolean isIs_collected() {
            return is_collected;
        }

        public void setIs_collected(boolean is_collected) {
            this.is_collected = is_collected;
        }

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
            this.time = time;
        }

        public boolean isIs_following() {
            return is_following;
        }

        public void setIs_following(boolean is_following) {
            this.is_following = is_following;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public List<Cover> getCovers() {
            return covers;
        }

        public void setCovers(List<Cover> covers) {
            this.covers = covers;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }


    }

    public static class FocusedBean implements Serializable {

        /**
         * comment_num : 2
         * nullusername :
         * is_readed : true
         * nullavatar :
         * cause : 0
         * recommend : 1
         * type : 2
         * atlas_num : 5图
         * news_id : 61
         * cover : 371077
         * score : 1789
         * read_num : 128次浏览
         * spider_time : 2018-04-02 11:14:17
         * content_type : 1
         * news_type : 1
         * is_like : false
         * news_name : null
         * origin_time : 2018-04-02 10:52:00
         * id : 10756
         * state : 1
         * covers : [{"url":"https://staticcdntest.fantuanlife.com/uimage/44/fb/0a/dc/44fb0adc35f3c9f23c382e26572cd098.jpg","compress":"https://staticcdntest.fantuanlife.com/uimage/44/fb/0a/dc/44fb0adc35f3c9f23c382e26572cd098.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/","height":"367","width":"550","longCover":false}]
         * like_num : 0
         * author : 光明网
         * is_collected : false
         * avatar : []
         * article_url : https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=10756
         * name : 在荒滩上播撒新希望——酒泉市大力发展戈壁农业
         * time : null
         * is_following : false
         * cid : 5
         */

        private String comment_num;
        private String nullusername;
        private boolean is_readed;
        private String nullavatar;
        private String cause;
        private String uid;
        private String recommend;
        private String type;
        private String atlas_num;
        private String news_id;
        private String cover;
        private String score;
        private String read_num;
        private String spider_time;
        private String content_type;
        private String news_type;
        private boolean is_like;
        private Object news_name;
        private String origin_time;
        private String id;
        private String state;
        private String like_num;
        private String author;
        private boolean is_collected;
        private String article_url;
        private String name;
        private Object time;
        private boolean is_following;
        private String cid;
        private List<Cover> covers;
        private String avatar;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getNullusername() {
            return nullusername;
        }

        public void setNullusername(String nullusername) {
            this.nullusername = nullusername;
        }

        public boolean isIs_readed() {
            return is_readed;
        }

        public void setIs_readed(boolean is_readed) {
            this.is_readed = is_readed;
        }

        public String getNullavatar() {
            return nullavatar;
        }

        public void setNullavatar(String nullavatar) {
            this.nullavatar = nullavatar;
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

        public String getAtlas_num() {
            return atlas_num;
        }

        public void setAtlas_num(String atlas_num) {
            this.atlas_num = atlas_num;
        }

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getRead_num() {
            return read_num;
        }

        public void setRead_num(String read_num) {
            this.read_num = read_num;
        }

        public String getSpider_time() {
            return spider_time;
        }

        public void setSpider_time(String spider_time) {
            this.spider_time = spider_time;
        }

        public String getContent_type() {
            return content_type;
        }

        public void setContent_type(String content_type) {
            this.content_type = content_type;
        }

        public String getNews_type() {
            return news_type;
        }

        public void setNews_type(String news_type) {
            this.news_type = news_type;
        }

        public boolean isIs_like() {
            return is_like;
        }

        public void setIs_like(boolean is_like) {
            this.is_like = is_like;
        }

        public Object getNews_name() {
            return news_name;
        }

        public void setNews_name(Object news_name) {
            this.news_name = news_name;
        }

        public String getOrigin_time() {
            return origin_time;
        }

        public void setOrigin_time(String origin_time) {
            this.origin_time = origin_time;
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

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public boolean isIs_collected() {
            return is_collected;
        }

        public void setIs_collected(boolean is_collected) {
            this.is_collected = is_collected;
        }

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
            this.time = time;
        }

        public boolean isIs_following() {
            return is_following;
        }

        public void setIs_following(boolean is_following) {
            this.is_following = is_following;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public List<Cover> getCovers() {
            return covers;
        }

        public void setCovers(List<Cover> covers) {
            this.covers = covers;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

    }

    public static class ElementsBean implements Serializable {
        /**
         * id : 2
         * name : 测试要点二
         * specialId : 1
         * createdAt : 2018-04-13 10:27:43
         */

        private String id;
        private String name;
        private String specialId;
        private String createdAt;

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

        public String getSpecialId() {
            return specialId;
        }

        public void setSpecialId(String specialId) {
            this.specialId = specialId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

}
