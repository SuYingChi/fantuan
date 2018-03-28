package com.wetime.fanc.circle.bean;

/**
 * Created by zhoukang on 2018/3/5.
 */

public class DefaultCircleBean {

    /**
     * error : 0
     * msg :
     * data : {"id":"4","name":"测试1"}
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
         * name : 测试1
         */

        private String id;
        private String name;
        private boolean last_position;

        public boolean isLast_position() {
            return last_position;
        }

        public void setLast_position(boolean last_position) {
            this.last_position = last_position;
        }

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
    }
}
