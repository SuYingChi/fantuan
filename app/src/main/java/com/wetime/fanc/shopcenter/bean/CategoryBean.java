package com.wetime.fanc.shopcenter.bean;

import java.util.List;

/**
 * Created by zhoukang on 2017/11/9.
 */

public class CategoryBean {

    /**
     * id : 1
     * cid : 0
     * name : 美食
     * imgurl :
     * level : 1
     * num : 9
     * subcates : [{"id":"2","cid":"1","name":"火锅","imgurl":"","level":"0","num":"3"},{"id":"3","cid":"1","name":"烧烤烤肉","imgurl":"","level":"0","num":"2"},{"id":"4","cid":"1","name":"甜点饮品","imgurl":"","level":"0","num":"2"},{"id":"15","cid":"1","name":"西餐","imgurl":"","level":"0","num":"1"},{"id":"17","cid":"1","name":"咖啡","imgurl":"","level":"0","num":"1"}]
     */

    private String id;
    private String cid;
    private String name;
    private String imgurl;
    private String level;
    private int num;
    private List<SubcatesBean> subcates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<SubcatesBean> getSubcates() {
        return subcates;
    }

    public void setSubcates(List<SubcatesBean> subcates) {
        this.subcates = subcates;
    }

    public static class SubcatesBean {
        /**
         * id : 2
         * cid : 1
         * name : 火锅
         * imgurl :
         * level : 0
         * num : 3
         */

        private String id;
        private String cid;
        private String name;
        private String imgurl;
        private String level;
        private String num;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
