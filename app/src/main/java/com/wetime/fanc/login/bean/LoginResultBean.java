package com.wetime.fanc.login.bean;

/**
 * Created by zhoukang on 2017/11/2.
 */

public class LoginResultBean {


    /**
     * error : 0
     * msg :
     * data : {"token":"6XgLmNgY3Xgav2CJf-qF0XPWmTZbAzzM","phone":"","bind_phone_url":"https://fanttest.fantuanlife.com/index.html#/user/phone/create?token=6XgLmNgY3Xgav2CJf-qF0XPWmTZbAzzM"}
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
         * token : 6XgLmNgY3Xgav2CJf-qF0XPWmTZbAzzM
         * phone :
         * bind_phone_url : https://fanttest.fantuanlife.com/index.html#/user/phone/create?token=6XgLmNgY3Xgav2CJf-qF0XPWmTZbAzzM
         */

        private String token;
        private String phone;
        private String bind_phone_url;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBind_phone_url() {
            return bind_phone_url;
        }

        public void setBind_phone_url(String bind_phone_url) {
            this.bind_phone_url = bind_phone_url;
        }
    }
}
