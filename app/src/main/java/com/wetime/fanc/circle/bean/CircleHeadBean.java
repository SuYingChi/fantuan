package com.wetime.fanc.circle.bean;

/**
 * Created by zhoukang on 2018/3/6.
 */

public class CircleHeadBean {


    /**
     * error : 0
     * msg :
     * data : {"id":"4","name":"测试测试测试","intro":"1111","cover_url":"https://staticcdntest.fantuanlife.com/uimage/04/36/be/a2/0436bea2bf66d31c08d3efed034e0c77.jpg","is_follow":false}
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
         * id : 4
         * name : 测试测试测试
         * intro : 1111
         * cover_url : https://staticcdntest.fantuanlife.com/uimage/04/36/be/a2/0436bea2bf66d31c08d3efed034e0c77.jpg
         * is_follow : false
         */

        private String id;
        private String name;
        private String intro;
        private String cover_url;
        private boolean is_follow;

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

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public boolean isIs_follow() {
            return is_follow;
        }

        public void setIs_follow(boolean is_follow) {
            this.is_follow = is_follow;
        }
    }
}
