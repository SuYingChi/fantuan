package com.wetime.fanc.news.bean;

/**
 * Created by Administrator on 2018/3/30.
 */

public class GalleryCommentBean {

    /**
     * error : 0
     * msg :
     * data : {"id":"184","content":"我在一起我也是","time":"刚刚","like_num":"0","reply_num":"0","score":"0","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png"},"is_author":true,"is_news":false}
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
         * id : 184
         * content : 我在一起我也是
         * time : 刚刚
         * like_num : 0
         * reply_num : 0
         * score : 0
         * user : {"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png"}
         * is_author : true
         * is_news : false
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

        public static class UserBean {
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
