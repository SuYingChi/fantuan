package com.wetime.fanc.home.bean;

/**
 * Created by zhoukang on 2017/11/3.
 */

public class MyInfoBean {


    /**
     * error : 0
     * msg :
     * data : {"user":{"username":"花心萝卜腿","avatar":"306766?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70"},"link":{"reward":{"name":"我的范票","url":"https://www.baidu.com/"},"coupon":{"name":"我的优惠券","url":"https://www.baidu.com/"},"focus":{"name":"我的关注","url":"https://www.baidu.com/"},"notice":{"name":"我的消息","url":"https://www.baidu.com/"},"review":{"name":"我的评价","url":"https://www.baidu.com/"},"address":{"name":"我的地址","url":"https://www.baidu.com/"}}}
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
         * user : {"username":"花心萝卜腿","avatar":"306766?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70"}
         * link : {"reward":{"name":"我的范票","url":"https://www.baidu.com/"},"coupon":{"name":"我的优惠券","url":"https://www.baidu.com/"},"focus":{"name":"我的关注","url":"https://www.baidu.com/"},"notice":{"name":"我的消息","url":"https://www.baidu.com/"},"review":{"name":"我的评价","url":"https://www.baidu.com/"},"address":{"name":"我的地址","url":"https://www.baidu.com/"}}
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
             * username : 花心萝卜腿
             * avatar : 306766?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70
             */

            private String username;
            private String avatar;

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
        }

        public static class LinkBean {
            /**
             * reward : {"name":"我的范票","url":"https://www.baidu.com/"}
             * coupon : {"name":"我的优惠券","url":"https://www.baidu.com/"}
             * focus : {"name":"我的关注","url":"https://www.baidu.com/"}
             * notice : {"name":"我的消息","url":"https://www.baidu.com/"}
             * review : {"name":"我的评价","url":"https://www.baidu.com/"}
             * address : {"name":"我的地址","url":"https://www.baidu.com/"}
             */

            private RewardBean reward;
            private CouponBean coupon;
            private FocusBean focus;
            private NoticeBean notice;
            private ReviewBean review;
            private AddressBean address;

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

            public FocusBean getFocus() {
                return focus;
            }

            public void setFocus(FocusBean focus) {
                this.focus = focus;
            }

            public NoticeBean getNotice() {
                return notice;
            }

            public void setNotice(NoticeBean notice) {
                this.notice = notice;
            }

            public ReviewBean getReview() {
                return review;
            }

            public void setReview(ReviewBean review) {
                this.review = review;
            }

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
            }

            public static class RewardBean {
                /**
                 * name : 我的范票
                 * url : https://www.baidu.com/
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
                 * url : https://www.baidu.com/
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

            public static class FocusBean {
                /**
                 * name : 我的关注
                 * url : https://www.baidu.com/
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

            public static class NoticeBean {
                /**
                 * name : 我的消息
                 * url : https://www.baidu.com/
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

            public static class ReviewBean {
                /**
                 * name : 我的评价
                 * url : https://www.baidu.com/
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

            public static class AddressBean {
                /**
                 * name : 我的地址
                 * url : https://www.baidu.com/
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
