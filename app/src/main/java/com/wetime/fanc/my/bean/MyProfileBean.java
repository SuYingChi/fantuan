package com.wetime.fanc.my.bean;

public class MyProfileBean {


    /**
     * error : 0
     * msg :
     * data : {"birthday":"请选择","is_follow":false,"has_new_fans":true,"signature":"请填写","is_owner":true,"sex":"请选择","follow_num":"0","avatar":"https://staticcdntest.fantuanlife.com/uimage/40/b0/32/5b/40b0325b70b5b012c75fa70ef4159755.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","fans_num":"2","is_news":true,"avatar_url":"https://staticcdntest.fantuanlife.com/uimage/40/b0/32/5b/40b0325b70b5b012c75fa70ef4159755.jpg","intro":"科技前沿，商业资讯，深度特稿...每一条新闻都有价值","id":"38","dynamic_num":"345","username":"起点","toutiao_apply_url":"https://fanttest.fantuanlife.com/index.html#/user/toutiao/apply"}
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
         * birthday : 请选择
         * is_follow : false
         * has_new_fans : true
         * signature : 请填写
         * is_owner : true
         * sex : 请选择
         * follow_num : 0
         * avatar : https://staticcdntest.fantuanlife.com/uimage/40/b0/32/5b/40b0325b70b5b012c75fa70ef4159755.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/
         * fans_num : 2
         * is_news : true
         * avatar_url : https://staticcdntest.fantuanlife.com/uimage/40/b0/32/5b/40b0325b70b5b012c75fa70ef4159755.jpg
         * intro : 科技前沿，商业资讯，深度特稿...每一条新闻都有价值
         * id : 38
         * dynamic_num : 345
         * username : 起点
         * toutiao_apply_url : https://fanttest.fantuanlife.com/index.html#/user/toutiao/apply
         */

        private String birthday;
        private boolean is_follow;
        private boolean has_new_fans;
        private String signature;
        private boolean is_owner;
        private String sex;
        private String follow_num;
        private String avatar;
        private String fans_num;
        private boolean is_news;
        private String avatar_url;
        private String intro;
        private String id;
        private String dynamic_num;
        private String username;
        private String toutiao_apply_url;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public boolean isIs_follow() {
            return is_follow;
        }

        public void setIs_follow(boolean is_follow) {
            this.is_follow = is_follow;
        }

        public boolean isHas_new_fans() {
            return has_new_fans;
        }

        public void setHas_new_fans(boolean has_new_fans) {
            this.has_new_fans = has_new_fans;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public boolean isIs_owner() {
            return is_owner;
        }

        public void setIs_owner(boolean is_owner) {
            this.is_owner = is_owner;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getFollow_num() {
            return follow_num;
        }

        public void setFollow_num(String follow_num) {
            this.follow_num = follow_num;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getFans_num() {
            return fans_num;
        }

        public void setFans_num(String fans_num) {
            this.fans_num = fans_num;
        }

        public boolean isIs_news() {
            return is_news;
        }

        public void setIs_news(boolean is_news) {
            this.is_news = is_news;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDynamic_num() {
            return dynamic_num;
        }

        public void setDynamic_num(String dynamic_num) {
            this.dynamic_num = dynamic_num;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getToutiao_apply_url() {
            return toutiao_apply_url;
        }

        public void setToutiao_apply_url(String toutiao_apply_url) {
            this.toutiao_apply_url = toutiao_apply_url;
        }
    }
}
