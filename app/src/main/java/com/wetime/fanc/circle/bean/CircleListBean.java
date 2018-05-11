package com.wetime.fanc.circle.bean;

import com.wetime.fanc.home.bean.Cover;

import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 */

public class CircleListBean {

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

        private String rule;
        private PagingBean paging;
        private String followed_num;
        private CoverBean cover;
        private String uid;
        private boolean is_like;
        private String intro;
        private String name;
        private String id;
        private String time;
        private String state;
        private String dynamic_num;
        private String cid;
        private List<ListBean> list;

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public String getFollowed_num() {
            return followed_num;
        }

        public void setFollowed_num(String followed_num) {
            this.followed_num = followed_num;
        }

        public CoverBean getCover() {
            return cover;
        }

        public void setCover(CoverBean cover) {
            this.cover = cover;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public boolean isIs_like() {
            return is_like;
        }

        public void setIs_like(boolean is_like) {
            this.is_like = is_like;
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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getDynamic_num() {
            return dynamic_num;
        }

        public void setDynamic_num(String dynamic_num) {
            this.dynamic_num = dynamic_num;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
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

        public static class CoverBean {
            /**
             * url : https://staticcdntest.fantuanlife.com/uimage/04/36/be/a2/0436bea2bf66d31c08d3efed034e0c77.jpg
             * compress : https://staticcdntest.fantuanlife.com/uimage/04/36/be/a2/0436bea2bf66d31c08d3efed034e0c77.jpg?x-oss-process=image/resize,m_mfit,h_600/format,jpg/interlace,1/quality,Q_70/
             * height : 1044
             * width : 658
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

        public static class ListBean {

            private String comment_num;
            private String like_num;
            private String circle_id;
            private String latitude;
            private String range;
            private String avatar;
            private String type;
            private String title;
            private String content;
            private String uid;
            private boolean is_circle_manager;
            private boolean is_news;
            private String read_num;
            private boolean is_circle_owner;
            private String location;
            private String id;
            private String time;
            private String image_id;
            private String circle_name;
            private boolean is_following;
            private boolean has_like;
            private String longitude;
            private String username;
            private List<Cover> covers;

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

            public boolean isIs_circle_manager() {
                return is_circle_manager;
            }

            public void setIs_circle_manager(boolean is_circle_manager) {
                this.is_circle_manager = is_circle_manager;
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

            public boolean isIs_circle_owner() {
                return is_circle_owner;
            }

            public void setIs_circle_owner(boolean is_circle_owner) {
                this.is_circle_owner = is_circle_owner;
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

            public String getCircle_name() {
                return circle_name;
            }

            public void setCircle_name(String circle_name) {
                this.circle_name = circle_name;
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

            public List<Cover> getCovers() {
                return covers;
            }

            public void setCovers(List<Cover> covers) {
                this.covers = covers;
            }

            public static class CoversBean {


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
