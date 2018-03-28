package com.wetime.fanc.my.bean;

/**
 * Created by zhoukang on 2017/11/3.
 */

public class MyInfoBean {


    /**
     * error : 0
     * msg :
     * data : {"user":{"username":"Yun'''。","phone":"18689789691","avatar":"https://staticcdntest.fantuanlife.com/uimage/55/5e/8c/12/555e8c12849a191cc42cfab16f904c54.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"link":{"reward":{"name":"我的范票","url":"https://fanttest.fantuanlife.com/index.html#/user/reward"},"coupon":{"name":"我的优惠券","url":"https://fanttest.fantuanlife.com/index.html#/user/coupon"},"focus":{"name":"我的关注","url":"https://fanttest.fantuanlife.com/index.html#/user/follow"},"notice":{"name":"我的消息","url":"https://fanttest.fantuanlife.com/index.html#/user/message"},"review":{"name":"我的评价","url":"https://fanttest.fantuanlife.com/index.html#/user/comment"},"inviter":{"name":"邀请有礼","url":""}},"notice_num":"0"}
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
         * user : {"username":"Yun'''。","phone":"18689789691","avatar":"https://staticcdntest.fantuanlife.com/uimage/55/5e/8c/12/555e8c12849a191cc42cfab16f904c54.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"}
         * link : {"reward":{"name":"我的范票","url":"https://fanttest.fantuanlife.com/index.html#/user/reward"},"coupon":{"name":"我的优惠券","url":"https://fanttest.fantuanlife.com/index.html#/user/coupon"},"focus":{"name":"我的关注","url":"https://fanttest.fantuanlife.com/index.html#/user/follow"},"notice":{"name":"我的消息","url":"https://fanttest.fantuanlife.com/index.html#/user/message"},"review":{"name":"我的评价","url":"https://fanttest.fantuanlife.com/index.html#/user/comment"},"inviter":{"name":"邀请有礼","url":""}}
         * notice_num : 0
         */

        private UserBean user;
        private LinkBean link;


        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public LinkBean getLink() {
            return link;
        }

        public void setLink(LinkBean link) {
            this.link = link;
        }


        public static class UserBean {
            /**
             * username : Yun'''。
             * phone : 18689789691
             * avatar : https://staticcdntest.fantuanlife.com/uimage/55/5e/8c/12/555e8c12849a191cc42cfab16f904c54.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
             * "news_article_num": "75",
             " dynamic_num": "6",
             " order_review_num": "0"
             **/

            private String news_article_num;
            private String dynamic_num;
            private String order_review_num;
            private String username;
            private String phone;
            private String avatar;
            private String uid;
            private boolean has_news;

            public String getNews_article_num() {
                return news_article_num;
            }

            public void setNews_article_num(String news_article_num) {
                this.news_article_num = news_article_num;
            }

            public String getDynamic_num() {
                return dynamic_num;
            }

            public void setDynamic_num(String dynamic_num) {
                this.dynamic_num = dynamic_num;
            }

            public String getOrder_review_num() {
                return order_review_num;
            }

            public void setOrder_review_num(String order_review_num) {
                this.order_review_num = order_review_num;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public boolean isHas_news() {
                return has_news;
            }

            public void setHas_news(boolean has_news) {
                this.has_news = has_news;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }

        public static class LinkBean {
            /**
             * reward : {"name":"我的范票","url":"https://fanttest.fantuanlife.com/index.html#/user/reward"}
             * coupon : {"name":"我的优惠券","url":"https://fanttest.fantuanlife.com/index.html#/user/coupon"}
             * focus : {"name":"我的关注","url":"https://fanttest.fantuanlife.com/index.html#/user/follow"}
             * notice : {"name":"我的消息","url":"https://fanttest.fantuanlife.com/index.html#/user/message"}
             * review : {"name":"我的评价","url":"https://fanttest.fantuanlife.com/index.html#/user/comment"}
             * inviter : {"name":"邀请有礼","url":""}
             */

            private RewardBean reward;
            private CouponBean coupon;
            private NewsBean news;

            public NewsBean getNews() {
                return news;
            }

            public void setNews(NewsBean news) {
                this.news = news;
            }

            public RewardBean getReward() {
                return reward;
            }

            public void setReward(RewardBean reward) {
                this.reward = reward;
            }

            public CouponBean getCoupon() {
                return coupon;
            }

            public void setCoupon(CouponBean coupon) {
                this.coupon = coupon;
            }


            public static class RewardBean {
                /**
                 * name : 我的范票
                 * url : https://fanttest.fantuanlife.com/index.html#/user/reward
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class CouponBean {
                /**
                 * name : 我的优惠券
                 * url : https://fanttest.fantuanlife.com/index.html#/user/coupon
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }


            public static class NewsBean {
                /**
                 * name : 我的评价
                 * url : https://fanttest.fantuanlife.com/index.html#/user/comment
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

        }
    }
}
