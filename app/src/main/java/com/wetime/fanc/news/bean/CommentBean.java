package com.wetime.fanc.news.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class CommentBean implements Serializable {

    /**
     * error : 0
     * msg :
     * data : {"list":[{"id":"190","content":"也一生一世我在破","time":"2小时前","like_num":"0","reply_num":"0","score":"0","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png"},"is_author":false,"is_news":false,"is_like":false},{"id":"184","content":"我在一起我也是","time":"2小时前","like_num":"0","reply_num":"0","score":"0","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png"},"is_author":false,"is_news":false,"is_like":false},{"id":"181","content":"1","time":"2小时前","like_num":"0","reply_num":"0","score":"0","user":{"id":"19","username":"Yun'''。","avatar":"https://staticcdntest.fantuanlife.com/uimage/9b/d6/6a/14/9bd66a14f66671c6e1bccd1ef217fceb.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"is_author":false,"is_news":true,"is_like":false}],"paging":{"total":"3","total_page":"1","limit":"15","pn":"1","is_end":true}}
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

    public static class DataBean implements Serializable {
        /**
         * list : [{"id":"190","content":"也一生一世我在破","time":"2小时前","like_num":"0","reply_num":"0","score":"0","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png"},"is_author":false,"is_news":false,"is_like":false},{"id":"184","content":"我在一起我也是","time":"2小时前","like_num":"0","reply_num":"0","score":"0","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png"},"is_author":false,"is_news":false,"is_like":false},{"id":"181","content":"1","time":"2小时前","like_num":"0","reply_num":"0","score":"0","user":{"id":"19","username":"Yun'''。","avatar":"https://staticcdntest.fantuanlife.com/uimage/9b/d6/6a/14/9bd66a14f66671c6e1bccd1ef217fceb.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"is_author":false,"is_news":true,"is_like":false}]
         * paging : {"total":"3","total_page":"1","limit":"15","pn":"1","is_end":true}
         */

        private PagingBean paging;
        private List<ListBean> list;

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

        public static class PagingBean implements Serializable {
            /**
             * total : 3
             * total_page : 1
             * limit : 15
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

        public static class ListBean implements Serializable {

            /**
             * id : 190
             * content : 也一生一世我在破
             * time : 2小时前
             * like_num : 0
             * reply_num : 0
             * score : 0
             * user : {"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png"}
             * is_author : false
             * is_news : false
             * is_like : false
             */

            private String id;
            private String content;
            private String time;
            private String like_num;
            private String reply_num;
            private String score;
            private UserBean user;
            private boolean is_author;
            private boolean is_news;
            private boolean is_like;

            @Override
            public String toString() {
                return "ListBean{" +
                        "id='" + id + '\'' +
                        ", content='" + content + '\'' +
                        ", time='" + time + '\'' +
                        ", like_num='" + like_num + '\'' +
                        ", reply_num='" + reply_num + '\'' +
                        ", score='" + score + '\'' +
                        ", user=" + user +
                        ", is_author=" + is_author +
                        ", is_news=" + is_news +
                        ", is_like=" + is_like +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getLike_num() {
                return like_num;
            }

            public void setLike_num(String like_num) {
                this.like_num = like_num;
            }

            public String getReply_num() {
                return reply_num;
            }

            public void setReply_num(String reply_num) {
                this.reply_num = reply_num;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public boolean isIs_author() {
                return is_author;
            }

            public void setIs_author(boolean is_author) {
                this.is_author = is_author;
            }

            public boolean isIs_news() {
                return is_news;
            }

            public void setIs_news(boolean is_news) {
                this.is_news = is_news;
            }

            public boolean isIs_like() {
                return is_like;
            }

            public void setIs_like(boolean is_like) {
                this.is_like = is_like;
            }

            public static class UserBean implements Serializable {
                /**
                 * id : 281
                 * username : 183****3639
                 * avatar : https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png
                 */

                private String id;
                private String username;
                private String avatar;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
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
            }
        }
    }
}
