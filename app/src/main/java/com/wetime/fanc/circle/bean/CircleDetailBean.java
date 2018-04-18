package com.wetime.fanc.circle.bean;

import java.util.List;

/**
 * Created by zhoukang on 2018/3/5.
 */

public class CircleDetailBean {


    /**
     * error : 0
     * msg :
     * data : {"id":"8","name":"测试5","cover":"https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","time":"创建于2018-02-28","uid":"24","is_news":false,"username":"范范范24","avatar":"https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png","intro":"555","rule":"哈哈阿阿阿阿阿阿阿阿阿阿阿阿","is_follow":false,"follow_num":"2","dynamic_num":"2","managers":[{"id":"2","username":"哈哈哈哈","cover":"https://staticcdntest.fantuanlife.com/uimage/dd/d8/95/ca/ddd895cace5fd359cdbc15f62965ce73.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"}]}
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
         * id : 8
         * name : 测试5
         * cover : https://staticcdntest.fantuanlife.com/uimage/00/00/8d/03/00008d03f4117803248945a19d98402d.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
         * time : 创建于2018-02-28
         * uid : 24
         * is_news : false
         * username : 范范范24
         * avatar : https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png
         * intro : 555
         * rule : 哈哈阿阿阿阿阿阿阿阿阿阿阿阿
         * is_follow : false
         * follow_num : 2
         * dynamic_num : 2
         * managers : [{"id":"2","username":"哈哈哈哈","cover":"https://staticcdntest.fantuanlife.com/uimage/dd/d8/95/ca/ddd895cace5fd359cdbc15f62965ce73.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"}]
         */

        private String id;
        private String name;
        private String cover;
        private String time;
        private String uid;
        private boolean is_news;
        private String username;
        private String avatar;
        private String intro;
        private String rule;
        private boolean is_follow;
        private String follow_num;
        private String dynamic_num;
        private List<ManagersBean> managers;

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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }

        public boolean isIs_follow() {
            return is_follow;
        }

        public void setIs_follow(boolean is_follow) {
            this.is_follow = is_follow;
        }

        public String getFollow_num() {
            return follow_num;
        }

        public void setFollow_num(String follow_num) {
            this.follow_num = follow_num;
        }

        public String getDynamic_num() {
            return dynamic_num;
        }

        public void setDynamic_num(String dynamic_num) {
            this.dynamic_num = dynamic_num;
        }

        public List<ManagersBean> getManagers() {
            return managers;
        }

        public void setManagers(List<ManagersBean> managers) {
            this.managers = managers;
        }

        public static class ManagersBean {
            /**
             * id : 2
             * username : 哈哈哈哈
             * cover : https://staticcdntest.fantuanlife.com/uimage/dd/d8/95/ca/ddd895cace5fd359cdbc15f62965ce73.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
             */

            private String id;
            private String username;
            private String cover;

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

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
        }
    }
}
