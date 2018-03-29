package com.wetime.fanc.my.bean;

import com.wetime.fanc.home.bean.HomeItemBean;

import java.util.List;

/**
 * Created by zhoukang on 2018/3/7.
 */

public class UserCardBean {

    /**
     * error : 0
     * msg :
     * data : {"user":{"id":"18","username":"王荣慰","avatar":"https://staticcdntest.fantuanlife.com/uimage/56/30/c1/8f/5630c18fdd3f4ffd2f65c59325cb93c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","uid":"18","is_new":true,"intro":"这里是个人名片简介，先写死~~"},"review_list":[{"mid":"33","merchant_name":"范团5555","score":5,"content":"Ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd","cover":["https://staticcdntest.fantuanlife.com/uimage/ef/0b/ce/ae/ef0bceaeb700993b20ad6c0e75ad9873.jpg","https://staticcdntest.fantuanlife.com/uimage/a9/59/80/d4/a95980d41f0f8e47bf7b02fbb3fdc105.jpg","https://staticcdntest.fantuanlife.com/uimage/ea/ca/01/be/eaca01be72c6cb80cec0f02c03bb23bc.jpg","https://staticcdntest.fantuanlife.com/uimage/f1/c1/a2/e7/f1c1a2e7ae082d7291591465dcf7aae0.jpg","https://staticcdntest.fantuanlife.com/uimage/46/72/20/f7/467220f70d0e7441b2ee2e0bf7ef7608.jpg"],"type":"29","reply":"dggshhaetjtjaetjaetjaetjaetjaetjaetjaetjaetjayjayjaryjsyjaryjsryksrykatkarykwrykwsryksryjryjryjrywjsryjrwyjwryjrwjwryjwry","year":"2018年","month":"3","day":"06"},{"mid":"33","merchant_name":"范团5555","score":5,"content":"232323233232323232323233r3r23r23rr23r23r23r23r32r23r23r23r23r23r23r23r23r32r23r23r","cover":["https://staticcdntest.fantuanlife.com/uimage/a9/59/80/d4/a95980d41f0f8e47bf7b02fbb3fdc105.jpg","https://staticcdntest.fantuanlife.com/uimage/a7/5b/5b/44/a75b5b441b3bc1bb260f4e35223ac621.jpg"],"type":"29","reply":"","year":"","month":"3","day":"06"},{"mid":"33","merchant_name":"范团5555","score":5,"content":"12345678bdndndddnnddnddsadadasdasdasdasasdasdadasdsadasdasdadasdasdasdasdadasdasdasasdasdasd","cover":[],"type":"29","reply":"","year":"","month":"3","day":"06"},{"mid":"33","merchant_name":"范团5555","score":5,"content":"Did dada dada dadadadfbbgbbfbdfdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdbdbdbdfb","cover":[],"type":"29","reply":"","year":"","month":"3","day":"06"},{"mid":"33","merchant_name":"范团5555","score":5,"content":"狗狗某娜继续继续继续继续增加","cover":[],"type":"29","reply":"","year":"","month":"1","day":"26"},{"mid":"33","merchant_name":"范团5555","score":5,"content":"个个儿童柔娜咯柔娜娜咯","cover":[],"type":"29","reply":"","year":"","month":"1","day":"26"},{"mid":"55","merchant_name":"范团13(龙华区)","score":5,"content":"你得默默默默默默","cover":[],"type":"29","reply":"1234","year":"2017年","month":"12","day":"26"},{"mid":"55","merchant_name":"范团13(龙华区)","score":5,"content":"哈哈哈啊哈哈哈哈爱和哈","cover":[],"type":"29","reply":"Wwwww\n","year":"","month":"12","day":"26"},{"mid":"55","merchant_name":"范团13(龙华区)","score":5,"content":"嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻","cover":[],"type":"29","reply":"头破就来咯","year":"","month":"12","day":"26"},{"mid":"55","merchant_name":"范团13(龙华区)","score":5,"content":"俄评价哈哈哈哈哈哈哈哈","cover":[],"type":"29","reply":"21334","year":"","month":"12","day":"26"}],"paging":{"total":"13","total_page":2,"limit":10,"pn":1,"is_end":false}}
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
         * user : {"id":"18","username":"王荣慰","avatar":"https://staticcdntest.fantuanlife.com/uimage/56/30/c1/8f/5630c18fdd3f4ffd2f65c59325cb93c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","uid":"18","is_new":true,"intro":"这里是个人名片简介，先写死~~"}
         * review_list : [{"mid":"33","merchant_name":"范团5555","score":5,"content":"Ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd","cover":["https://staticcdntest.fantuanlife.com/uimage/ef/0b/ce/ae/ef0bceaeb700993b20ad6c0e75ad9873.jpg","https://staticcdntest.fantuanlife.com/uimage/a9/59/80/d4/a95980d41f0f8e47bf7b02fbb3fdc105.jpg","https://staticcdntest.fantuanlife.com/uimage/ea/ca/01/be/eaca01be72c6cb80cec0f02c03bb23bc.jpg","https://staticcdntest.fantuanlife.com/uimage/f1/c1/a2/e7/f1c1a2e7ae082d7291591465dcf7aae0.jpg","https://staticcdntest.fantuanlife.com/uimage/46/72/20/f7/467220f70d0e7441b2ee2e0bf7ef7608.jpg"],"type":"29","reply":"dggshhaetjtjaetjaetjaetjaetjaetjaetjaetjaetjayjayjaryjsyjaryjsryksrykatkarykwrykwsryksryjryjryjrywjsryjrwyjwryjrwjwryjwry","year":"2018年","month":"3","day":"06"},{"mid":"33","merchant_name":"范团5555","score":5,"content":"232323233232323232323233r3r23r23rr23r23r23r23r32r23r23r23r23r23r23r23r23r32r23r23r","cover":["https://staticcdntest.fantuanlife.com/uimage/a9/59/80/d4/a95980d41f0f8e47bf7b02fbb3fdc105.jpg","https://staticcdntest.fantuanlife.com/uimage/a7/5b/5b/44/a75b5b441b3bc1bb260f4e35223ac621.jpg"],"type":"29","reply":"","year":"","month":"3","day":"06"},{"mid":"33","merchant_name":"范团5555","score":5,"content":"12345678bdndndddnnddnddsadadasdasdasdasasdasdadasdsadasdasdadasdasdasdasdadasdasdasasdasdasd","cover":[],"type":"29","reply":"","year":"","month":"3","day":"06"},{"mid":"33","merchant_name":"范团5555","score":5,"content":"Did dada dada dadadadfbbgbbfbdfdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdfbdbdbdbdfb","cover":[],"type":"29","reply":"","year":"","month":"3","day":"06"},{"mid":"33","merchant_name":"范团5555","score":5,"content":"狗狗某娜继续继续继续继续增加","cover":[],"type":"29","reply":"","year":"","month":"1","day":"26"},{"mid":"33","merchant_name":"范团5555","score":5,"content":"个个儿童柔娜咯柔娜娜咯","cover":[],"type":"29","reply":"","year":"","month":"1","day":"26"},{"mid":"55","merchant_name":"范团13(龙华区)","score":5,"content":"你得默默默默默默","cover":[],"type":"29","reply":"1234","year":"2017年","month":"12","day":"26"},{"mid":"55","merchant_name":"范团13(龙华区)","score":5,"content":"哈哈哈啊哈哈哈哈爱和哈","cover":[],"type":"29","reply":"Wwwww\n","year":"","month":"12","day":"26"},{"mid":"55","merchant_name":"范团13(龙华区)","score":5,"content":"嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻","cover":[],"type":"29","reply":"头破就来咯","year":"","month":"12","day":"26"},{"mid":"55","merchant_name":"范团13(龙华区)","score":5,"content":"俄评价哈哈哈哈哈哈哈哈","cover":[],"type":"29","reply":"21334","year":"","month":"12","day":"26"}]
         * paging : {"total":"13","total_page":2,"limit":10,"pn":1,"is_end":false}
         */

        private UserBean user;
        private PagingBean paging;
        private List<HomeItemBean> list;

        public List<HomeItemBean> getList() {
            return list;
        }

        public void setList(List<HomeItemBean> list) {
            this.list = list;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }


        public static class UserBean {
            /**
             * id : 18
             * username : 王荣慰
             * avatar : https://staticcdntest.fantuanlife.com/uimage/56/30/c1/8f/5630c18fdd3f4ffd2f65c59325cb93c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
             * uid : 18
             * is_new : true
             * intro : 这里是个人名片简介，先写死~~
             */

            private String id;
            private String username;
            private String avatar;
            private String uid;
            private String cover;
            private boolean is_new;
            private String intro;
            private String follow_num;
            private String fans_num;
            private boolean is_follow;
            private boolean is_owner;
//            "follow_num": "5",
//                    "fans_num": "1",
//                    "is_follow": false,
//                    "is_owner": true

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getFollow_num() {
                return follow_num;
            }

            public void setFollow_num(String follow_num) {
                this.follow_num = follow_num;
            }

            public String getFans_num() {
                return fans_num;
            }

            public void setFans_num(String fans_num) {
                this.fans_num = fans_num;
            }

            public boolean isFollow() {
                return is_follow;
            }

            public void setFollow(boolean follow) {
                this.is_follow = follow;
            }

            public boolean isOwner() {
                return is_owner;
            }

            public void setOwner(boolean owner) {
                this.is_owner = owner;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public boolean isIs_new() {
                return is_new;
            }

            public void setIs_new(boolean is_new) {
                this.is_new = is_new;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }
        }

        public static class PagingBean {
            /**
             * total : 13
             * total_page : 2
             * limit : 10
             * pn : 1
             * is_end : false
             */

            private String total;
            private int total_page;
            private int limit;
            private int pn;
            private boolean is_end;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
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
    }
}
