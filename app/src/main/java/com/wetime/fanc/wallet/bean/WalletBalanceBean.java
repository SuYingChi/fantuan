package com.wetime.fanc.wallet.bean;

/**
 * Created by zhoukang on 2018/1/9.
 */

public class WalletBalanceBean {

    /**
     * error : 0
     * msg :
     * data : {"money":"0","phone":"18889948050","withdraw_money_min":"0","has_bind_wechat":"0","wechat_nickname":"","has_set_pwd":"1"}
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
         * money : 0
         * phone : 18889948050
         * withdraw_money_min : 0
         * has_bind_wechat : 0
         * wechat_nickname :
         * has_set_pwd : 1
         */

        private String money;
        private String phone;
        private String withdraw_money_min;
        private String has_bind_wechat;
        private String wechat_nickname;
        private String has_set_pwd;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getWithdraw_money_min() {
            return withdraw_money_min;
        }

        public void setWithdraw_money_min(String withdraw_money_min) {
            this.withdraw_money_min = withdraw_money_min;
        }

        public String getHas_bind_wechat() {
            return has_bind_wechat;
        }

        public void setHas_bind_wechat(String has_bind_wechat) {
            this.has_bind_wechat = has_bind_wechat;
        }

        public String getWechat_nickname() {
            return wechat_nickname;
        }

        public void setWechat_nickname(String wechat_nickname) {
            this.wechat_nickname = wechat_nickname;
        }

        public String getHas_set_pwd() {
            return has_set_pwd;
        }

        public void setHas_set_pwd(String has_set_pwd) {
            this.has_set_pwd = has_set_pwd;
        }
    }
}
