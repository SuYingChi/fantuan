package com.wetime.fanc.shop.bean;

import java.util.List;

/**
 * Created by zhoukang on 2017/12/27.
 */

public class ShopDetailBean {


    /**
     * error : 0
     * msg :
     * data : {"merchant":{"mid":"55","name":"范团13","score":"4.6","average_spend":"","address":"海口市琼山区忠介路2号铺面","phone":"18976267677","cid":"0","logo":"https://staticcdntest.fantuanlife.com/uimage/70/a7/70/64/70a77064e41d3b6182d260065a2537ae.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","spider":"0","sales":"月销量 999999","business_hours":"00:04-00:51 10:00-17:12 18:03-18:26 18:36-19:36 20:05-23:05","affiche":"这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公","distance":"距167m","post_url":"https://fanttest.fantuanlife.com/index.html#/merchant/55/picture","category_name":"其他","mall":"金棕榈广场","discounts":"优惠买单"},"mall":{"title":"所在购物中心","content":[{"name":"金棕榈广场","mall_url":"https://fanttest.fantuanlife.com/index.html#/mall?mall=12","logo_url":"https://staticcdntest.fantuanlife.com/uimage/c1/f7/b9/a6/c1f7b9a6b63ff2e7cdb4d84e9493779b.jpg"}]},"coupon":{"title":"买单优惠活动","content":[{"pid":"134","threshold":"2","amount":"1","is_get":false},{"pid":"135","threshold":"3","amount":"2","is_get":false},{"pid":"136","threshold":"4","amount":"3","is_get":false}],"activity":[{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/moneyOff.png","name":"满2减1、满3减2、满4减3、满5减4、满6减5、满7减6、满8减7、满9减8、满10减9、满11减10"},{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/cashCoupon.png","name":"满2赠1、满2赠1"},{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/payDiscount.png","name":"买单立享6.00折优惠"}]},"groupon":{"title":"团购套餐","content":[{"pid":"128","name":"翔帅专用团购","amount":"0.01","market_price":"500","total_sales":"0","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"},{"pid":"129","name":"翔帅专用团购2","amount":"0.01","market_price":"200","total_sales":"0","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"}]},"voucher":{"title":"代金券","content":[{"pid":"117","amount":"0.01","market_price":"500","total_sales":"20"},{"pid":"130","amount":"0.01","market_price":"150","total_sales":"0"},{"pid":"131","amount":"0.01","market_price":"300","total_sales":"0"}]},"post":{"title":"商家动态","content":[{"content":"带图的商家动态","create_at":"2017-12-26 15:16:20"}]},"focus":false,"review":{"totals":"16","title":"商家动态","content":[{"score":"5","uid":"23","content":"lost咯胡了holy咯啊配件给哦","image":"307138,307140,307141,307382","review_time":"2017-12-26 17:18:46","is_anonymous":"0","reply":null,"user":{"id":"23","username":"陈加尧","avatar":"https://staticcdntest.fantuanlife.com/uimage/84/57/36/c4/845736c4a0febc922c22b5e4fca24806.jpg?x-oss-process=image/resize,m_fill,h_112,w_112/format,jpg/interlace,1/quality,Q_70"},"imageUrl":["https://staticcdntest.fantuanlife.com/uimage/7f/ad/19/69/7fad1969e970f77948e416546aab6efa.jpg","https://staticcdntest.fantuanlife.com/uimage/71/31/6e/c3/71316ec379977d880250bbaa791c2ceb.jpg","https://staticcdntest.fantuanlife.com/uimage/56/89/1a/36/56891a3639c0fa4cad0af462a541b454.jpg"]}]}}
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
         * merchant : {"mid":"55","name":"范团13","score":"4.6","average_spend":"","address":"海口市琼山区忠介路2号铺面","phone":"18976267677","cid":"0","logo":"https://staticcdntest.fantuanlife.com/uimage/70/a7/70/64/70a77064e41d3b6182d260065a2537ae.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","spider":"0","sales":"月销量 999999","business_hours":"00:04-00:51 10:00-17:12 18:03-18:26 18:36-19:36 20:05-23:05","affiche":"这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公","distance":"距167m","post_url":"https://fanttest.fantuanlife.com/index.html#/merchant/55/picture","category_name":"其他","mall":"金棕榈广场","discounts":"优惠买单"}
         * mall : {"title":"所在购物中心","content":[{"name":"金棕榈广场","mall_url":"https://fanttest.fantuanlife.com/index.html#/mall?mall=12","logo_url":"https://staticcdntest.fantuanlife.com/uimage/c1/f7/b9/a6/c1f7b9a6b63ff2e7cdb4d84e9493779b.jpg"}]}
         * coupon : {"title":"买单优惠活动","content":[{"pid":"134","threshold":"2","amount":"1","is_get":false},{"pid":"135","threshold":"3","amount":"2","is_get":false},{"pid":"136","threshold":"4","amount":"3","is_get":false}],"activity":[{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/moneyOff.png","name":"满2减1、满3减2、满4减3、满5减4、满6减5、满7减6、满8减7、满9减8、满10减9、满11减10"},{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/cashCoupon.png","name":"满2赠1、满2赠1"},{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/payDiscount.png","name":"买单立享6.00折优惠"}]}
         * groupon : {"title":"团购套餐","content":[{"pid":"128","name":"翔帅专用团购","amount":"0.01","market_price":"500","total_sales":"0","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"},{"pid":"129","name":"翔帅专用团购2","amount":"0.01","market_price":"200","total_sales":"0","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"}]}
         * voucher : {"title":"代金券","content":[{"pid":"117","amount":"0.01","market_price":"500","total_sales":"20"},{"pid":"130","amount":"0.01","market_price":"150","total_sales":"0"},{"pid":"131","amount":"0.01","market_price":"300","total_sales":"0"}]}
         * post : {"title":"商家动态","content":[{"content":"带图的商家动态","create_at":"2017-12-26 15:16:20"}]}
         * focus : false
         * review : {"totals":"16","title":"商家动态","content":[{"score":"5","uid":"23","content":"lost咯胡了holy咯啊配件给哦","image":"307138,307140,307141,307382","review_time":"2017-12-26 17:18:46","is_anonymous":"0","reply":null,"user":{"id":"23","username":"陈加尧","avatar":"https://staticcdntest.fantuanlife.com/uimage/84/57/36/c4/845736c4a0febc922c22b5e4fca24806.jpg?x-oss-process=image/resize,m_fill,h_112,w_112/format,jpg/interlace,1/quality,Q_70"},"imageUrl":["https://staticcdntest.fantuanlife.com/uimage/7f/ad/19/69/7fad1969e970f77948e416546aab6efa.jpg","https://staticcdntest.fantuanlife.com/uimage/71/31/6e/c3/71316ec379977d880250bbaa791c2ceb.jpg","https://staticcdntest.fantuanlife.com/uimage/56/89/1a/36/56891a3639c0fa4cad0af462a541b454.jpg"]}]}
         */

        private MerchantBean merchant;
        private MallBean mall;
        private CouponBean coupon;
        private GrouponBean groupon;
        private VoucherBean voucher;
        private PostBean post;
        private boolean focus;
        private ReviewBean review;

        public MerchantBean getMerchant() {
            return merchant;
        }

        public void setMerchant(MerchantBean merchant) {
            this.merchant = merchant;
        }

        public MallBean getMall() {
            return mall;
        }

        public void setMall(MallBean mall) {
            this.mall = mall;
        }

        public CouponBean getCoupon() {
            return coupon;
        }

        public void setCoupon(CouponBean coupon) {
            this.coupon = coupon;
        }

        public GrouponBean getGroupon() {
            return groupon;
        }

        public void setGroupon(GrouponBean groupon) {
            this.groupon = groupon;
        }

        public VoucherBean getVoucher() {
            return voucher;
        }

        public void setVoucher(VoucherBean voucher) {
            this.voucher = voucher;
        }

        public PostBean getPost() {
            return post;
        }

        public void setPost(PostBean post) {
            this.post = post;
        }

        public boolean isFocus() {
            return focus;
        }

        public void setFocus(boolean focus) {
            this.focus = focus;
        }

        public ReviewBean getReview() {
            return review;
        }

        public void setReview(ReviewBean review) {
            this.review = review;
        }

        public static class MerchantBean {
            /**
             * mid : 55
             * name : 范团13
             * score : 4.6
             * average_spend :
             * address : 海口市琼山区忠介路2号铺面
             * phone : 18976267677
             * cid : 0
             * logo : https://staticcdntest.fantuanlife.com/uimage/70/a7/70/64/70a77064e41d3b6182d260065a2537ae.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
             * spider : 0
             * sales : 月销量 999999
             * business_hours : 00:04-00:51 10:00-17:12 18:03-18:26 18:36-19:36 20:05-23:05
             * affiche : 这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公
             * distance : 距167m
             * post_url : https://fanttest.fantuanlife.com/index.html#/merchant/55/picture
             * category_name : 其他
             * mall : 金棕榈广场
             * discounts : 优惠买单
             */

            private String mid;
            private String name;
            private String score;
            private String average_spend;
            private String address;
            private String phone;
            private String cid;
            private String logo;
            private String spider;
            private String sales;
            private String business_hours;
            private String affiche;
            private String distance;
            private String post_url;
            private String category_name;
            private String mall;
            private String discounts;

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getAverage_spend() {
                return average_spend;
            }

            public void setAverage_spend(String average_spend) {
                this.average_spend = average_spend;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getSpider() {
                return spider;
            }

            public void setSpider(String spider) {
                this.spider = spider;
            }

            public String getSales() {
                return sales;
            }

            public void setSales(String sales) {
                this.sales = sales;
            }

            public String getBusiness_hours() {
                return business_hours;
            }

            public void setBusiness_hours(String business_hours) {
                this.business_hours = business_hours;
            }

            public String getAffiche() {
                return affiche;
            }

            public void setAffiche(String affiche) {
                this.affiche = affiche;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getPost_url() {
                return post_url;
            }

            public void setPost_url(String post_url) {
                this.post_url = post_url;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getMall() {
                return mall;
            }

            public void setMall(String mall) {
                this.mall = mall;
            }

            public String getDiscounts() {
                return discounts;
            }

            public void setDiscounts(String discounts) {
                this.discounts = discounts;
            }
        }

        public static class MallBean {
            /**
             * title : 所在购物中心
             * content : [{"name":"金棕榈广场","mall_url":"https://fanttest.fantuanlife.com/index.html#/mall?mall=12","logo_url":"https://staticcdntest.fantuanlife.com/uimage/c1/f7/b9/a6/c1f7b9a6b63ff2e7cdb4d84e9493779b.jpg"}]
             */

            private String title;
            private List<ContentBean> content;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ContentBean> getContent() {
                return content;
            }

            public void setContent(List<ContentBean> content) {
                this.content = content;
            }

            public static class ContentBean {
                /**
                 * name : 金棕榈广场
                 * mall_url : https://fanttest.fantuanlife.com/index.html#/mall?mall=12
                 * logo_url : https://staticcdntest.fantuanlife.com/uimage/c1/f7/b9/a6/c1f7b9a6b63ff2e7cdb4d84e9493779b.jpg
                 */

                private String name;
                private String mall_url;
                private String logo_url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getMall_url() {
                    return mall_url;
                }

                public void setMall_url(String mall_url) {
                    this.mall_url = mall_url;
                }

                public String getLogo_url() {
                    return logo_url;
                }

                public void setLogo_url(String logo_url) {
                    this.logo_url = logo_url;
                }
            }
        }

        public static class CouponBean {
            /**
             * title : 买单优惠活动
             * content : [{"pid":"134","threshold":"2","amount":"1","is_get":false},{"pid":"135","threshold":"3","amount":"2","is_get":false},{"pid":"136","threshold":"4","amount":"3","is_get":false}]
             * activity : [{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/moneyOff.png","name":"满2减1、满3减2、满4减3、满5减4、满6减5、满7减6、满8减7、满9减8、满10减9、满11减10"},{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/cashCoupon.png","name":"满2赠1、满2赠1"},{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/payDiscount.png","name":"买单立享6.00折优惠"}]
             */

            private String title;
            private List<ContentBeanX> content;
            private List<ActivityBean> activity;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ContentBeanX> getContent() {
                return content;
            }

            public void setContent(List<ContentBeanX> content) {
                this.content = content;
            }

            public List<ActivityBean> getActivity() {
                return activity;
            }

            public void setActivity(List<ActivityBean> activity) {
                this.activity = activity;
            }

            public static class ContentBeanX {
                /**
                 * pid : 134
                 * threshold : 2
                 * amount : 1
                 * is_get : false
                 */

                private String pid;
                private String threshold;
                private String amount;
                private boolean is_get;

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getThreshold() {
                    return threshold;
                }

                public void setThreshold(String threshold) {
                    this.threshold = threshold;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public boolean isIs_get() {
                    return is_get;
                }

                public void setIs_get(boolean is_get) {
                    this.is_get = is_get;
                }
            }

            public static class ActivityBean {
                /**
                 * ico : https://staticcdntest.fantuanlife.com/image/fant/promotion/moneyOff.png
                 * name : 满2减1、满3减2、满4减3、满5减4、满6减5、满7减6、满8减7、满9减8、满10减9、满11减10
                 */

                private String ico;
                private String name;

                public String getIco() {
                    return ico;
                }

                public void setIco(String ico) {
                    this.ico = ico;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class GrouponBean {
            /**
             * title : 团购套餐
             * content : [{"pid":"128","name":"翔帅专用团购","amount":"0.01","market_price":"500","total_sales":"0","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"},{"pid":"129","name":"翔帅专用团购2","amount":"0.01","market_price":"200","total_sales":"0","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"}]
             */

            private String title;
            private List<ContentBeanXX> content;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ContentBeanXX> getContent() {
                return content;
            }

            public void setContent(List<ContentBeanXX> content) {
                this.content = content;
            }

            public static class ContentBeanXX {
                /**
                 * pid : 128
                 * name : 翔帅专用团购
                 * amount : 0.01
                 * market_price : 500
                 * total_sales : 0
                 * imgUrl : https://staticcdntest.fantuanlife.com/image/m_logo.png
                 */

                private String pid;
                private String name;
                private String amount;
                private String market_price;
                private String total_sales;
                private String imgUrl;

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getMarket_price() {
                    return market_price;
                }

                public void setMarket_price(String market_price) {
                    this.market_price = market_price;
                }

                public String getTotal_sales() {
                    return total_sales;
                }

                public void setTotal_sales(String total_sales) {
                    this.total_sales = total_sales;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }
            }
        }

        public static class VoucherBean {
            /**
             * title : 代金券
             * content : [{"pid":"117","amount":"0.01","market_price":"500","total_sales":"20"},{"pid":"130","amount":"0.01","market_price":"150","total_sales":"0"},{"pid":"131","amount":"0.01","market_price":"300","total_sales":"0"}]
             */

            private String title;
            private List<ContentBeanXXX> content;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ContentBeanXXX> getContent() {
                return content;
            }

            public void setContent(List<ContentBeanXXX> content) {
                this.content = content;
            }

            public static class ContentBeanXXX {
                /**
                 * pid : 117
                 * amount : 0.01
                 * market_price : 500
                 * total_sales : 20
                 */

                private String pid;
                private String amount;
                private String market_price;
                private String total_sales;

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getMarket_price() {
                    return market_price;
                }

                public void setMarket_price(String market_price) {
                    this.market_price = market_price;
                }

                public String getTotal_sales() {
                    return total_sales;
                }

                public void setTotal_sales(String total_sales) {
                    this.total_sales = total_sales;
                }
            }
        }

        public static class PostBean {
            /**
             * title : 商家动态
             * content : [{"content":"带图的商家动态","create_at":"2017-12-26 15:16:20"}]
             */

            private String title;
            private List<ContentBeanXXXX> content;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ContentBeanXXXX> getContent() {
                return content;
            }

            public void setContent(List<ContentBeanXXXX> content) {
                this.content = content;
            }

            public static class ContentBeanXXXX {
                /**
                 * content : 带图的商家动态
                 * create_at : 2017-12-26 15:16:20
                 */

                private String content;
                private String create_at;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getCreate_at() {
                    return create_at;
                }

                public void setCreate_at(String create_at) {
                    this.create_at = create_at;
                }
            }
        }

        public static class ReviewBean {
            /**
             * totals : 16
             * title : 商家动态
             * content : [{"score":"5","uid":"23","content":"lost咯胡了holy咯啊配件给哦","image":"307138,307140,307141,307382","review_time":"2017-12-26 17:18:46","is_anonymous":"0","reply":null,"user":{"id":"23","username":"陈加尧","avatar":"https://staticcdntest.fantuanlife.com/uimage/84/57/36/c4/845736c4a0febc922c22b5e4fca24806.jpg?x-oss-process=image/resize,m_fill,h_112,w_112/format,jpg/interlace,1/quality,Q_70"},"imageUrl":["https://staticcdntest.fantuanlife.com/uimage/7f/ad/19/69/7fad1969e970f77948e416546aab6efa.jpg","https://staticcdntest.fantuanlife.com/uimage/71/31/6e/c3/71316ec379977d880250bbaa791c2ceb.jpg","https://staticcdntest.fantuanlife.com/uimage/56/89/1a/36/56891a3639c0fa4cad0af462a541b454.jpg"]}]
             */

            private String totals;
            private String title;
            private List<ContentBeanXXXXX> content;

            public String getTotals() {
                return totals;
            }

            public void setTotals(String totals) {
                this.totals = totals;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ContentBeanXXXXX> getContent() {
                return content;
            }

            public void setContent(List<ContentBeanXXXXX> content) {
                this.content = content;
            }

            public static class ContentBeanXXXXX {
                /**
                 * score : 5
                 * uid : 23
                 * content : lost咯胡了holy咯啊配件给哦
                 * image : 307138,307140,307141,307382
                 * review_time : 2017-12-26 17:18:46
                 * is_anonymous : 0
                 * reply : null
                 * user : {"id":"23","username":"陈加尧","avatar":"https://staticcdntest.fantuanlife.com/uimage/84/57/36/c4/845736c4a0febc922c22b5e4fca24806.jpg?x-oss-process=image/resize,m_fill,h_112,w_112/format,jpg/interlace,1/quality,Q_70"}
                 * imageUrl : ["https://staticcdntest.fantuanlife.com/uimage/7f/ad/19/69/7fad1969e970f77948e416546aab6efa.jpg","https://staticcdntest.fantuanlife.com/uimage/71/31/6e/c3/71316ec379977d880250bbaa791c2ceb.jpg","https://staticcdntest.fantuanlife.com/uimage/56/89/1a/36/56891a3639c0fa4cad0af462a541b454.jpg"]
                 */

                private String score;
                private String uid;
                private String content;
                private String image;
                private String review_time;
                private String is_anonymous;
                private Object reply;
                private UserBean user;
                private List<String> imageUrl;

                public String getScore() {
                    return score;
                }

                public void setScore(String score) {
                    this.score = score;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getReview_time() {
                    return review_time;
                }

                public void setReview_time(String review_time) {
                    this.review_time = review_time;
                }

                public String getIs_anonymous() {
                    return is_anonymous;
                }

                public void setIs_anonymous(String is_anonymous) {
                    this.is_anonymous = is_anonymous;
                }

                public Object getReply() {
                    return reply;
                }

                public void setReply(Object reply) {
                    this.reply = reply;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public List<String> getImageUrl() {
                    return imageUrl;
                }

                public void setImageUrl(List<String> imageUrl) {
                    this.imageUrl = imageUrl;
                }

                public static class UserBean {
                    /**
                     * id : 23
                     * username : 陈加尧
                     * avatar : https://staticcdntest.fantuanlife.com/uimage/84/57/36/c4/845736c4a0febc922c22b5e4fca24806.jpg?x-oss-process=image/resize,m_fill,h_112,w_112/format,jpg/interlace,1/quality,Q_70
                     */

                    private String id;
                    private String username;
                    private String avatar;

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
                }
            }
        }
    }
}
