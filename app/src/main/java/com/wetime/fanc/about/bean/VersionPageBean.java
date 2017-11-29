package com.wetime.fanc.about.bean;

/**
 * Created by zhoukang on 2017/11/29.
 */

public class VersionPageBean {


    /**
     * error : 0
     * msg :
     * data : {"version":{"name":"1.0.0","code":1}}
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
         * version : {"name":"1.0.0","code":1}
         */

        private VersionBean version;

        public VersionBean getVersion() {
            return version;
        }

        public void setVersion(VersionBean version) {
            this.version = version;
        }

        public static class VersionBean {
            /**
             * name : 1.0.0
             * code : 1
             */

            private String name;
            private int code;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }
        }
    }
}
