package com.wetime.fanc.my.bean;

/**
 * Created by zhoukang on 2017/11/3.
 */

public class MyInfoBean {


    /**
     * error : 0
     * msg :
     * data : {"birthday":"请选择","is_follow":false,"avatar_url":"https://staticcdntest.fantuanlife.com/uimage/40/b0/32/5b/40b0325b70b5b012c75fa70ef4159755.jpg","signature":"请填写","is_owner":true,"sex":"请选择","id":"38","avatar":"https://staticcdntest.fantuanlife.com/uimage/40/b0/32/5b/40b0325b70b5b012c75fa70ef4159755.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/","username":"起点"}
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
         * avatar_url : https://staticcdntest.fantuanlife.com/uimage/40/b0/32/5b/40b0325b70b5b012c75fa70ef4159755.jpg
         * signature : 请填写
         * is_owner : true
         * sex : 请选择
         * id : 38
         * avatar : https://staticcdntest.fantuanlife.com/uimage/40/b0/32/5b/40b0325b70b5b012c75fa70ef4159755.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/
         * username : 起点
         */

        private String birthday;
        private boolean is_follow;
        private String avatar_url;
        private String signature;
        private boolean is_owner;
        private String sex;
        private String id;
        private String avatar;
        private String username;

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

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}