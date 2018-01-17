package com.wetime.fanc.wallet.bean;

import java.util.List;

/**
 * Created by zhoukang on 2018/1/17.
 */

public class InviteHisBean {


    /**
     * error : 0
     * msg :
     * data : {"total_invitee":"1","total_money":"0.00","list":[{"invitee_uid":"39","invitee_phone":"158****7794","invitee_money":"¥0.04","time":"2018-01-16 14:57:22"}],"page":{"total":1,"total_page":1,"limit":10,"pn":1,"is_end":true}}
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
         * total_invitee : 1
         * total_money : 0.00
         * list : [{"invitee_uid":"39","invitee_phone":"158****7794","invitee_money":"¥0.04","time":"2018-01-16 14:57:22"}]
         * page : {"total":1,"total_page":1,"limit":10,"pn":1,"is_end":true}
         */

        private String total_invitee;
        private String total_money;
        private PageBean page;
        private List<ListBean> list;

        public String getTotal_invitee() {
            return total_invitee;
        }

        public void setTotal_invitee(String total_invitee) {
            this.total_invitee = total_invitee;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PageBean {
            /**
             * total : 1
             * total_page : 1
             * limit : 10
             * pn : 1
             * is_end : true
             */

            private int total;
            private int total_page;
            private int limit;
            private int pn;
            private boolean is_end;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getTotal_page() {
                return total_page;
            }

            public void setTotal_page(int total_page) {
                this.total_page = total_page;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public int getPn() {
                return pn;
            }

            public void setPn(int pn) {
                this.pn = pn;
            }

            public boolean isIs_end() {
                return is_end;
            }

            public void setIs_end(boolean is_end) {
                this.is_end = is_end;
            }
        }

        public static class ListBean {
            /**
             * invitee_uid : 39
             * invitee_phone : 158****7794
             * invitee_money : ¥0.04
             * time : 2018-01-16 14:57:22
             */

            private String invitee_uid;
            private String invitee_phone;
            private String invitee_money;
            private String time;

            public String getInvitee_uid() {
                return invitee_uid;
            }

            public void setInvitee_uid(String invitee_uid) {
                this.invitee_uid = invitee_uid;
            }

            public String getInvitee_phone() {
                return invitee_phone;
            }

            public void setInvitee_phone(String invitee_phone) {
                this.invitee_phone = invitee_phone;
            }

            public String getInvitee_money() {
                return invitee_money;
            }

            public void setInvitee_money(String invitee_money) {
                this.invitee_money = invitee_money;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
