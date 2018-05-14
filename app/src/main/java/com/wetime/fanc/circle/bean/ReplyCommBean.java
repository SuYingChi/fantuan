package com.wetime.fanc.circle.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class ReplyCommBean {

    /**
     * error : 0
     * msg :
     * data : {"paging":{"total":"0","total_page":"1","limit":"10","pn":"1","is_end":true},"list":[{"uid":"2","puid":"2","is_owner":true,"pid":"2","id":"32","time":"05月04日 10:37","pusername":"卡卡西","is_manager":false,"content":"回复测试","username":"卡卡西","is_delete":true}]}
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
         * paging : {"total":"0","total_page":"1","limit":"10","pn":"1","is_end":true}
         * list : [{"uid":"2","puid":"2","is_owner":true,"pid":"2","id":"32","time":"05月04日 10:37","pusername":"卡卡西","is_manager":false,"content":"回复测试","username":"卡卡西","is_delete":true}]
         */

        private PagingBean paging;
        private List<ListBean> list;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PagingBean {
            /**
             * total : 0
             * total_page : 1
             * limit : 10
             * pn : 1
             * is_end : true
             */

            private String total;
            private String total_page;
            private String limit;
            private String pn;
            private boolean is_end;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getTotal_page() {
                return total_page;
            }

            public void setTotal_page(String total_page) {
                this.total_page = total_page;
            }

            public String getLimit() {
                return limit;
            }

            public void setLimit(String limit) {
                this.limit = limit;
            }

            public String getPn() {
                return pn;
            }

            public void setPn(String pn) {
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
             * uid : 2
             * puid : 2
             * is_owner : true
             * pid : 2
             * id : 32
             * time : 05月04日 10:37
             * pusername : 卡卡西
             * is_manager : false
             * content : 回复测试
             * username : 卡卡西
             * is_delete : true
             */

            private String uid;
            private String puid;
            private boolean is_owner;
            private String pid;
            private String id;
            private String time;
            private String pusername;
            private boolean is_manager;
            private String content;
            private String username;
            private boolean is_delete;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getPuid() {
                return puid;
            }

            public void setPuid(String puid) {
                this.puid = puid;
            }

            public boolean isIs_owner() {
                return is_owner;
            }

            public void setIs_owner(boolean is_owner) {
                this.is_owner = is_owner;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getPusername() {
                return pusername;
            }

            public void setPusername(String pusername) {
                this.pusername = pusername;
            }

            public boolean isIs_manager() {
                return is_manager;
            }

            public void setIs_manager(boolean is_manager) {
                this.is_manager = is_manager;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public boolean isIs_delete() {
                return is_delete;
            }

            public void setIs_delete(boolean is_delete) {
                this.is_delete = is_delete;
            }
        }
    }
}
