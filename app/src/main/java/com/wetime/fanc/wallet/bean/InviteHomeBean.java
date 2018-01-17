package com.wetime.fanc.wallet.bean;

/**
 * Created by zhoukang on 2018/1/17.
 */

public class InviteHomeBean {


    /**
     * error : 0
     * msg :
     * data : {"total_money":"0.00","tips":"","url_cert":"https://fanttest.fantuanlife.com/index.html#/inviter/realinfo","url_rule":"https://fanttest.fantuanlife.com/index.html#/inviter/applyrule","url_qrcode":"https://staticcdntest.fantuanlife.com/uimage/46/a8/a2/91/46a8a29160a70977d6bcc9f28687c2e0.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"}
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
         * total_money : 0.00
         * tips :
         * url_cert : https://fanttest.fantuanlife.com/index.html#/inviter/realinfo
         * url_rule : https://fanttest.fantuanlife.com/index.html#/inviter/applyrule
         * url_qrcode : https://staticcdntest.fantuanlife.com/uimage/46/a8/a2/91/46a8a29160a70977d6bcc9f28687c2e0.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
         */

        private String total_money;
        private String tips;
        private String url_cert;
        private String url_rule;
        private String url_qrcode;
        private String url_redbag;

        public String getUrl_redbag() {
            return url_redbag;
        }

        public void setUrl_redbag(String url_redbag) {
            this.url_redbag = url_redbag;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public String getUrl_cert() {
            return url_cert;
        }

        public void setUrl_cert(String url_cert) {
            this.url_cert = url_cert;
        }

        public String getUrl_rule() {
            return url_rule;
        }

        public void setUrl_rule(String url_rule) {
            this.url_rule = url_rule;
        }

        public String getUrl_qrcode() {
            return url_qrcode;
        }

        public void setUrl_qrcode(String url_qrcode) {
            this.url_qrcode = url_qrcode;
        }
    }
}
