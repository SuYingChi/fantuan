package com.wetime.fanc.home.bean;

import java.util.List;

/**
 * Created by zhoukang on 2017/11/22.
 */

public class MallsBean {

    /**
     * id : 2
     * name : 龙华区
     * num : 1
     * subcates : [{"id":"2","name":"全部","num":"1"},{"id":"11","name":"京华城","num":"1"}]
     */

    private String id;
    private String name;
    private int num;
    private List<SubcatesBean> subcates;

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
         * name : 全部
         * num : 1
         */

        private String id;
        private String name;
        private String num;

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

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
