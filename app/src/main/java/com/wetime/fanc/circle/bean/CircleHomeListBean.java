package com.wetime.fanc.circle.bean;

import com.wetime.fanc.home.bean.HomeItemBean;

import java.util.List;

/**
 * Created by zhoukang on 2018/3/1.
 */

public class CircleHomeListBean {



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
        private List<FollowCirclesBean> follow_circles;
        private List<CirclesBean> circles;
        private List<HomeItemBean> list;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<FollowCirclesBean> getFollow_circles() {
            return follow_circles;
        }

        public void setFollow_circles(List<FollowCirclesBean> follow_circles) {
            this.follow_circles = follow_circles;
        }

        public List<CirclesBean> getCircles() {
            return circles;
        }

        public void setCircles(List<CirclesBean> circles) {
            this.circles = circles;
        }

        public List<HomeItemBean> getList() {
            return list;
        }

        public void setList(List<HomeItemBean> list) {
            this.list = list;
        }

        public static class PagingBean {
            /**
             * total : 290
             * total_page : 15
             * limit : 20
             * pn : 1
             * is_end : false
             */

            private int total;
            private String total_page;
            private String limit;
            private String pn;
            private boolean is_end;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
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

        public static class FollowCirclesBean {
            /**
             * id : 2
             * name : 吃喝拉撒
             * cover : https://staticcdntest.fantuanlife.com/uimage/37/d1/1b/0d/37d11b0db407ce7ecc5b635cf21ca225.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
             */

            private String id;
            private String name;
            private String cover;

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

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
        }

        public static class CirclesBean {
            /**
             * id : 1
             * name : 吃喝玩乐1111
             * cover : https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
             */

            private String id;
            private String name;
            private String cover;

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

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
        }

        public static class ListBean {
            /**
             * id : 2
             * uid : 18
             * circle_id : 1
             * type : 19
             * title :
             * content : 这是一条很棒很棒的动态哦····不堵车了耶！！！
             * read_num : 697次浏览
             * like_num : 8
             * comment_num : 76
             * time : 02月26日
             * longitude : 0
             * latitude : 0
             * location :
             * username : 南海网21
             * avatar : https://staticcdntest.fantuanlife.com/uimage/37/4e/4c/ea/374e4cea66154aa1b43a75240e42109b.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
             * circle_name : 吃喝玩乐1111
             * cover : ["https://staticcdntest.fantuanlife.com/uimage/00/01/a1/5c/0001a15ccc4f3aa227220a94f20c29d3.jpg","https://staticcdntest.fantuanlife.com/uimage/00/01/a5/9f/0001a59f2b1e5babff2852ec8813593d.jpg"]
             * has_like : false
             * is_news : true
             */

            private String id;
            private String uid;
            private String circle_id;
            private String type;
            private String title;
            private String content;
            private String read_num;
            private String like_num;
            private String comment_num;
            private String time;
            private String longitude;
            private String latitude;
            private String location;
            private String username;
            private String avatar;
            private String circle_name;
            private boolean has_like;
            private boolean is_news;
            private List<String> cover;

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

            public boolean isHas_like() {
                return has_like;
            }

            public void setHas_like(boolean has_like) {
                this.has_like = has_like;
            }

            public boolean isIs_news() {
                return is_news;
            }

            public void setIs_news(boolean is_news) {
                this.is_news = is_news;
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
