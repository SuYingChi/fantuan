package com.wetime.fanc.login.bean;

/**
 * Created by zhoukang on 2017/11/2.
 */

public class LoginResultBean {

    /**
     * error : 0
     * msg :
     * data : {"token":"E9RWKbEiQmu3CWk0fZ2sQDfAtUUllDby"}
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
         * token : E9RWKbEiQmu3CWk0fZ2sQDfAtUUllDby
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
