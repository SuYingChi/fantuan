package com.wetime.fanc.news.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class ReplyCommentBean {


    /**
     * error : 0
     * msg :
     * data : {"like_num":"0","is_news":true,"is_author":false,"is_like":false,"reply_num":"2","id":"214","time":"04月02日 14:15","reply":[{"like_num":"0","is_news":false,"reply_object":null,"is_author":false,"is_like":false,"pid":"0","id":"239","time":"05月03日 18:05","is_following":false,"user":{"id":"319","username":"138****3230","avatar":"https://fanttest.fantuanlife.com/image/fant/default_avatar.png"},"content":"斤斤计较"},{"like_num":"0","is_news":false,"reply_object":{"uid":"319","is_news":false,"id":"239","avatar":"https://fanttest.fantuanlife.com/image/fant/default_avatar.png","is_following":false,"username":"138****3230"},"is_author":false,"is_like":false,"pid":"239","id":"240","time":"05月03日 18:05","is_following":false,"user":{"id":"319","username":"138****3230","avatar":"https://fanttest.fantuanlife.com/image/fant/default_avatar.png"},"content":"咯去搜"}],"aid":"10756","is_following":true,"user":{"id":"19","username":"1234567890","avatar":"https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"肉肉肉肉"}
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
         * like_num : 0
         * is_news : true
         * is_author : false
         * is_like : false
         * reply_num : 2
         * id : 214
         * time : 04月02日 14:15
         * reply : [{"like_num":"0","is_news":false,"reply_object":null,"is_author":false,"is_like":false,"pid":"0","id":"239","time":"05月03日 18:05","is_following":false,"user":{"id":"319","username":"138****3230","avatar":"https://fanttest.fantuanlife.com/image/fant/default_avatar.png"},"content":"斤斤计较"},{"like_num":"0","is_news":false,"reply_object":{"uid":"319","is_news":false,"id":"239","avatar":"https://fanttest.fantuanlife.com/image/fant/default_avatar.png","is_following":false,"username":"138****3230"},"is_author":false,"is_like":false,"pid":"239","id":"240","time":"05月03日 18:05","is_following":false,"user":{"id":"319","username":"138****3230","avatar":"https://fanttest.fantuanlife.com/image/fant/default_avatar.png"},"content":"咯去搜"}]
         * aid : 10756
         * is_following : true
         * user : {"id":"19","username":"1234567890","avatar":"https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"}
         * content : 肉肉肉肉
         */

        private String like_num;
        private boolean is_news;
        private boolean is_author;
        private boolean is_like;
        private String reply_num;
        private String id;
        private String time;
        private String aid;
        private boolean is_following;
        private UserBean user;
        private String content;
        private List<ReplyBean> reply;

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public boolean isIs_news() {
            return is_news;
        }

        public void setIs_news(boolean is_news) {
            this.is_news = is_news;
        }

        public boolean isIs_author() {
            return is_author;
        }

        public void setIs_author(boolean is_author) {
            this.is_author = is_author;
        }

        public boolean isIs_like() {
            return is_like;
        }

        public void setIs_like(boolean is_like) {
            this.is_like = is_like;
        }

        public String getReply_num() {
            return reply_num;
        }

        public void setReply_num(String reply_num) {
            this.reply_num = reply_num;
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

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public boolean isIs_following() {
            return is_following;
        }

        public void setIs_following(boolean is_following) {
            this.is_following = is_following;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<ReplyBean> getReply() {
            return reply;
        }

        public void setReply(List<ReplyBean> reply) {
            this.reply = reply;
        }

        public static class UserBean {
            /**
             * id : 19
             * username : 1234567890
             * avatar : https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/
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

        public static class ReplyBean {
            /**
             * like_num : 0
             * is_news : false
             * reply_object : null
             * is_author : false
             * is_like : false
             * pid : 0
             * id : 239
             * time : 05月03日 18:05
             * is_following : false
             * user : {"id":"319","username":"138****3230","avatar":"https://fanttest.fantuanlife.com/image/fant/default_avatar.png"}
             * content : 斤斤计较
             */

            private String like_num;
            private boolean is_news;
            private Reply_object reply_object;
            private boolean is_author;
            private boolean is_like;
            private String pid;
            private String id;
            private String time;
            private boolean is_following;
            private UserBeanX user;
            private String content;

            public String getLike_num() {
                return like_num;
            }

            public void setLike_num(String like_num) {
                this.like_num = like_num;
            }

            public boolean isIs_news() {
                return is_news;
            }

            public void setIs_news(boolean is_news) {
                this.is_news = is_news;
            }

            public Reply_object getReply_object() {
                return reply_object;
            }

            public void setReply_object(Reply_object reply_object) {
                this.reply_object = reply_object;
            }

            public boolean isIs_author() {
                return is_author;
            }

            public void setIs_author(boolean is_author) {
                this.is_author = is_author;
            }

            public boolean isIs_like() {
                return is_like;
            }

            public void setIs_like(boolean is_like) {
                this.is_like = is_like;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
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

            public boolean isIs_following() {
                return is_following;
            }

            public void setIs_following(boolean is_following) {
                this.is_following = is_following;
            }

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public static class UserBeanX {
                /**
                 * id : 319
                 * username : 138****3230
                 * avatar : https://fanttest.fantuanlife.com/image/fant/default_avatar.png
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
            public static class Reply_object {

                /**
                 * uid : 319
                 * is_news : false
                 * id : 239
                 * avatar : https://fanttest.fantuanlife.com/image/fant/default_avatar.png
                 * is_following : false
                 * username : 138****3230
                 */

                private String uid;
                private boolean is_news;
                private String id;
                private String avatar;
                private boolean is_following;
                private String username;

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

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public boolean isIs_following() {
                    return is_following;
                }

                public void setIs_following(boolean is_following) {
                    this.is_following = is_following;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }
            }
        }
    }
}
