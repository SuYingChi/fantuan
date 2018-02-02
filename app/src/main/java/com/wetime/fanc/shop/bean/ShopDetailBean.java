package com.wetime.fanc.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoukang on 2017/12/27.
 */

public class ShopDetailBean implements Serializable {


    /**
     * error : 0
     * msg :
     * data : {"merchant":{"mid":"55","name":"范团13","score":"4.6","average_spend":"","address":"海口市琼山区忠介路2号铺面","phone":"18976267677","cid":"0","logo":"https://staticcdntest.fantuanlife.com/uimage/70/a7/70/64/70a77064e41d3b6182d260065a2537ae.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","spider":"0","sales":"","business_hours":"00:04-00:51 10:00-17:12 18:03-18:26 18:36-19:36 20:05-23:05","affiche":"这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公","distance":"距167m","picture_url":"https://fanttest.fantuanlife.com/index.html#/merchant/55/picture","category_name":"其他","mall":"金棕榈广场","discounts":"优惠买单","payment_url":"https://fanttest.fantuanlife.com/index.html#/payment/balance?mid=55"},"mall":{"title":"所在购物中心","content":[{"name":"金棕榈广场","mall_url":"https://fanttest.fantuanlife.com/index.html#/mall?mall=12","logo_url":"https://staticcdntest.fantuanlife.com/uimage/c1/f7/b9/a6/c1f7b9a6b63ff2e7cdb4d84e9493779b.jpg"}]},"coupon":{"title":"买单优惠活动","content":[{"pid":"134","threshold":"2","amount":"1","is_get":false},{"pid":"135","threshold":"3","amount":"2","is_get":false},{"pid":"136","threshold":"4","amount":"3","is_get":false},{"pid":"169","threshold":"100","amount":"99","is_get":false},{"pid":"170","threshold":"10","amount":"9.9","is_get":false},{"pid":"171","threshold":"100","amount":"99","is_get":false}],"activity":[{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/moneyOff.png","name":"满2减1、满3减2、满4减3、满5减4、满6减5、满7减6、满8减7、满9减8、满10减9、满11减10"},{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/cashCoupon.png","name":"满2赠1、满2赠1"},{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/payDiscount.png","name":"买单立享6.00折优惠"}]},"groupon":{"title":"团购套餐","content":[{"pid":"128","name":"翔帅专用团购","amount":"0.01","market_price":"500","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/128?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/128/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"},{"pid":"129","name":"翔帅专用团购2","amount":"0.01","market_price":"200","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/129?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/129/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"},{"pid":"145","name":"团购3","amount":"10","market_price":"100","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/145?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/145/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"},{"pid":"146","name":"团购4","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/146?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/146/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"147","name":"团购5","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/147?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/147/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"148","name":"翔专用团购6","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/148?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/148/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"149","name":"翔专用团购7","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/149?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/149/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"150","name":"翔专用团购8","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/150?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/150/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"151","name":"翔专用团购9","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/151?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/151/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"152","name":"翔专用团购10","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/152?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/152/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"153","name":"翔专用团购11","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/153?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/153/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"154","name":"翔专用团购12","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/154?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/154/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"155","name":"翔专用团购13","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/155?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/155/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"156","name":"翔专用团购14","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/156?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/156/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"157","name":"翔专用团购15","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/157?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/157/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"158","name":"翔专用团购16","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/158?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/158/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"159","name":"翔专用团购17","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/159?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/159/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"160","name":"翔专用团购18","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/160?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/160/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"161","name":"翔专用团购19","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/161?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/161/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"162","name":"翔专用团购20","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/162?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/162/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"}]},"voucher":{"title":"代金券","content":[{"pid":"130","name":"150元代金券","amount":"0.01","market_price":"150","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/130?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/130/order?mid=55"},{"pid":"131","name":"300元代金券","amount":"0.01","market_price":"300","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/131?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/131/order?mid=55"},{"pid":"163","name":"200元代金券","amount":"160","market_price":"200","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/163?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/163/order?mid=55"},{"pid":"164","name":"500元代金券","amount":"160","market_price":"500","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/164?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/164/order?mid=55"},{"pid":"165","name":"5000元代金券","amount":"160","market_price":"5000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/165?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/165/order?mid=55"}]},"post":{"title":"商家动态","post_url":"https://fanttest.fantuanlife.com/index.html#/merchant/55/moment","content":[{"content":"赶回去莫得的份由来轻七尺巨魔都是\n看看去我们\n5门武器\n5句我的几号回去莫得\n莫得我自己来取门口了\n绿叶看看几个人\n了的\n5颗额我自己回去\n5门中央音乐学院啰啰嗦嗦么看见啊就几个人普陀门徒旅进旅退看见了魔域垃圾垃圾李说啥了11测度图哦我的油嘴滑舌葡萄牙\n模具姥姥姥爷我们我的爱过刺","create_at":"2017-12-29 10:57"}]},"focus":false,"review":{"title":"用户评价","totals":"16","review_url":"https://fanttest.fantuanlife.com/index.html#/merchant/55/comment","content":[{"score":"5","uid":"23","content":"lost咯胡了holy咯啊配件给哦","image":"307138,307140,307141,307382","review_time":"2017-12-26 17:18","is_anonymous":"0","reply":"哈哈哈哈哈","user":{"id":"23","username":"陈加尧","avatar":"https://staticcdntest.fantuanlife.com/uimage/84/57/36/c4/845736c4a0febc922c22b5e4fca24806.jpg?x-oss-process=image/resize,m_fill,h_112,w_112/format,jpg/interlace,1/quality,Q_70"},"imageUrl":["https://staticcdntest.fantuanlife.com/uimage/7f/ad/19/69/7fad1969e970f77948e416546aab6efa.jpg","https://staticcdntest.fantuanlife.com/uimage/71/31/6e/c3/71316ec379977d880250bbaa791c2ceb.jpg","https://staticcdntest.fantuanlife.com/uimage/56/89/1a/36/56891a3639c0fa4cad0af462a541b454.jpg"]}]}}
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

    public static class DataBean implements Serializable {
        /**
         * merchant : {"mid":"55","name":"范团13","score":"4.6","average_spend":"","address":"海口市琼山区忠介路2号铺面","phone":"18976267677","cid":"0","logo":"https://staticcdntest.fantuanlife.com/uimage/70/a7/70/64/70a77064e41d3b6182d260065a2537ae.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","spider":"0","sales":"","business_hours":"00:04-00:51 10:00-17:12 18:03-18:26 18:36-19:36 20:05-23:05","affiche":"这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公","distance":"距167m","picture_url":"https://fanttest.fantuanlife.com/index.html#/merchant/55/picture","category_name":"其他","mall":"金棕榈广场","discounts":"优惠买单","payment_url":"https://fanttest.fantuanlife.com/index.html#/payment/balance?mid=55"}
         * mall : {"title":"所在购物中心","content":[{"name":"金棕榈广场","mall_url":"https://fanttest.fantuanlife.com/index.html#/mall?mall=12","logo_url":"https://staticcdntest.fantuanlife.com/uimage/c1/f7/b9/a6/c1f7b9a6b63ff2e7cdb4d84e9493779b.jpg"}]}
         * coupon : {"title":"买单优惠活动","content":[{"pid":"134","threshold":"2","amount":"1","is_get":false},{"pid":"135","threshold":"3","amount":"2","is_get":false},{"pid":"136","threshold":"4","amount":"3","is_get":false},{"pid":"169","threshold":"100","amount":"99","is_get":false},{"pid":"170","threshold":"10","amount":"9.9","is_get":false},{"pid":"171","threshold":"100","amount":"99","is_get":false}],"activity":[{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/moneyOff.png","name":"满2减1、满3减2、满4减3、满5减4、满6减5、满7减6、满8减7、满9减8、满10减9、满11减10"},{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/cashCoupon.png","name":"满2赠1、满2赠1"},{"ico":"https://staticcdntest.fantuanlife.com/image/fant/promotion/payDiscount.png","name":"买单立享6.00折优惠"}]}
         * groupon : {"title":"团购套餐","content":[{"pid":"128","name":"翔帅专用团购","amount":"0.01","market_price":"500","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/128?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/128/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"},{"pid":"129","name":"翔帅专用团购2","amount":"0.01","market_price":"200","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/129?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/129/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"},{"pid":"145","name":"团购3","amount":"10","market_price":"100","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/145?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/145/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"},{"pid":"146","name":"团购4","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/146?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/146/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"147","name":"团购5","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/147?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/147/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"148","name":"翔专用团购6","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/148?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/148/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"149","name":"翔专用团购7","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/149?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/149/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"150","name":"翔专用团购8","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/150?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/150/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"151","name":"翔专用团购9","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/151?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/151/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"152","name":"翔专用团购10","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/152?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/152/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"153","name":"翔专用团购11","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/153?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/153/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"154","name":"翔专用团购12","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/154?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/154/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"155","name":"翔专用团购13","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/155?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/155/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"156","name":"翔专用团购14","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/156?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/156/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"157","name":"翔专用团购15","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/157?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/157/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"158","name":"翔专用团购16","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/158?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/158/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"159","name":"翔专用团购17","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/159?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/159/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"160","name":"翔专用团购18","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/160?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/160/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"161","name":"翔专用团购19","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/161?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/161/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"162","name":"翔专用团购20","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/162?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/162/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"}]}
         * voucher : {"title":"代金券","content":[{"pid":"130","name":"150元代金券","amount":"0.01","market_price":"150","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/130?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/130/order?mid=55"},{"pid":"131","name":"300元代金券","amount":"0.01","market_price":"300","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/131?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/131/order?mid=55"},{"pid":"163","name":"200元代金券","amount":"160","market_price":"200","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/163?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/163/order?mid=55"},{"pid":"164","name":"500元代金券","amount":"160","market_price":"500","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/164?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/164/order?mid=55"},{"pid":"165","name":"5000元代金券","amount":"160","market_price":"5000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/165?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/165/order?mid=55"}]}
         * post : {"title":"商家动态","post_url":"https://fanttest.fantuanlife.com/index.html#/merchant/55/moment","content":[{"content":"赶回去莫得的份由来轻七尺巨魔都是\n看看去我们\n5门武器\n5句我的几号回去莫得\n莫得我自己来取门口了\n绿叶看看几个人\n了的\n5颗额我自己回去\n5门中央音乐学院啰啰嗦嗦么看见啊就几个人普陀门徒旅进旅退看见了魔域垃圾垃圾李说啥了11测度图哦我的油嘴滑舌葡萄牙\n模具姥姥姥爷我们我的爱过刺","create_at":"2017-12-29 10:57"}]}
         * focus : false
         * review : {"title":"用户评价","totals":"16","review_url":"https://fanttest.fantuanlife.com/index.html#/merchant/55/comment","content":[{"score":"5","uid":"23","content":"lost咯胡了holy咯啊配件给哦","image":"307138,307140,307141,307382","review_time":"2017-12-26 17:18","is_anonymous":"0","reply":"哈哈哈哈哈","user":{"id":"23","username":"陈加尧","avatar":"https://staticcdntest.fantuanlife.com/uimage/84/57/36/c4/845736c4a0febc922c22b5e4fca24806.jpg?x-oss-process=image/resize,m_fill,h_112,w_112/format,jpg/interlace,1/quality,Q_70"},"imageUrl":["https://staticcdntest.fantuanlife.com/uimage/7f/ad/19/69/7fad1969e970f77948e416546aab6efa.jpg","https://staticcdntest.fantuanlife.com/uimage/71/31/6e/c3/71316ec379977d880250bbaa791c2ceb.jpg","https://staticcdntest.fantuanlife.com/uimage/56/89/1a/36/56891a3639c0fa4cad0af462a541b454.jpg"]}]}
         */

        private MerchantBean merchant;
        private MallBean mall;
        private CouponBean coupon;
        private GrouponBean groupon;
        private VoucherBean voucher;
        private PostBean post;
        private NewsBean news;
        private boolean focus;
        private ReviewBean review;

        public NewsBean getNews() {
            return news;
        }

        public void setNews(NewsBean news) {
            this.news = news;
        }

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

        public static class MerchantBean implements Serializable {
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
             * sales :
             * business_hours : 00:04-00:51 10:00-17:12 18:03-18:26 18:36-19:36 20:05-23:05
             * affiche : 这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公告这是门店公
             * distance : 距167m
             * picture_url : https://fanttest.fantuanlife.com/index.html#/merchant/55/picture
             * category_name : 其他
             * mall : 金棕榈广场
             * discounts : 优惠买单
             * payment_url : https://fanttest.fantuanlife.com/index.html#/payment/balance?mid=55
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
            private String picture_url;
            private String category_name;
            private String mall;
            private String discounts;
            private String payment_url;

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

            public String getPicture_url() {
                return picture_url;
            }

            public void setPicture_url(String picture_url) {
                this.picture_url = picture_url;
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

            public String getPayment_url() {
                return payment_url;
            }

            public void setPayment_url(String payment_url) {
                this.payment_url = payment_url;
            }
        }

        public static class MallBean implements Serializable {
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

            public static class ContentBean implements Serializable {
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

        public static class CouponBean implements Serializable {
            /**
             * title : 买单优惠活动
             * content : [{"pid":"134","threshold":"2","amount":"1","is_get":false},{"pid":"135","threshold":"3","amount":"2","is_get":false},{"pid":"136","threshold":"4","amount":"3","is_get":false},{"pid":"169","threshold":"100","amount":"99","is_get":false},{"pid":"170","threshold":"10","amount":"9.9","is_get":false},{"pid":"171","threshold":"100","amount":"99","is_get":false}]
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

            public static class ContentBeanX implements Serializable {
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

            public static class ActivityBean implements Serializable {
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

        public static class GrouponBean implements Serializable {
            /**
             * title : 团购套餐
             * content : [{"pid":"128","name":"翔帅专用团购","amount":"0.01","market_price":"500","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/128?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/128/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"},{"pid":"129","name":"翔帅专用团购2","amount":"0.01","market_price":"200","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/129?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/129/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"},{"pid":"145","name":"团购3","amount":"10","market_price":"100","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/145?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/145/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/image/m_logo.png"},{"pid":"146","name":"团购4","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/146?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/146/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"147","name":"团购5","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/147?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/147/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"148","name":"翔专用团购6","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/148?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/148/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"149","name":"翔专用团购7","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/149?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/149/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"150","name":"翔专用团购8","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/150?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/150/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"151","name":"翔专用团购9","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/151?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/151/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"152","name":"翔专用团购10","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/152?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/152/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"153","name":"翔专用团购11","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/153?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/153/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"154","name":"翔专用团购12","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/154?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/154/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"155","name":"翔专用团购13","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/155?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/155/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"156","name":"翔专用团购14","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/156?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/156/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"157","name":"翔专用团购15","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/157?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/157/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"158","name":"翔专用团购16","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/158?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/158/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"159","name":"翔专用团购17","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/159?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/159/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"160","name":"翔专用团购18","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/160?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/160/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"161","name":"翔专用团购19","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/161?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/161/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},{"pid":"162","name":"翔专用团购20","amount":"500","market_price":"1000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/162?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/162/order?mid=55","imgUrl":"https://staticcdntest.fantuanlife.com/uimage/00/00/c6/7e/0000c67e3c0cd27d105444132b14c2c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"}]
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

            public static class ContentBeanXX implements Serializable {
                /**
                 * pid : 128
                 * name : 翔帅专用团购
                 * amount : 0.01
                 * market_price : 500
                 * total_sales :
                 * detail_url : https://fanttest.fantuanlife.com/index.html#/product/128?mid=55
                 * buy_url : https://fanttest.fantuanlife.com/index.html#/product/128/order?mid=55
                 * imgUrl : https://staticcdntest.fantuanlife.com/image/m_logo.png
                 */

                private String pid;
                private String name;
                private String amount;
                private String market_price;
                private String total_sales;
                private String detail_url;
                private String buy_url;
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

                public String getDetail_url() {
                    return detail_url;
                }

                public void setDetail_url(String detail_url) {
                    this.detail_url = detail_url;
                }

                public String getBuy_url() {
                    return buy_url;
                }

                public void setBuy_url(String buy_url) {
                    this.buy_url = buy_url;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }
            }
        }

        public static class VoucherBean implements Serializable {
            /**
             * title : 代金券
             * content : [{"pid":"130","name":"150元代金券","amount":"0.01","market_price":"150","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/130?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/130/order?mid=55"},{"pid":"131","name":"300元代金券","amount":"0.01","market_price":"300","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/131?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/131/order?mid=55"},{"pid":"163","name":"200元代金券","amount":"160","market_price":"200","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/163?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/163/order?mid=55"},{"pid":"164","name":"500元代金券","amount":"160","market_price":"500","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/164?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/164/order?mid=55"},{"pid":"165","name":"5000元代金券","amount":"160","market_price":"5000","total_sales":"","detail_url":"https://fanttest.fantuanlife.com/index.html#/product/165?mid=55","buy_url":"https://fanttest.fantuanlife.com/index.html#/product/165/order?mid=55"}]
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

            public static class ContentBeanXXX implements Serializable {
                /**
                 * pid : 130
                 * name : 150元代金券
                 * amount : 0.01
                 * market_price : 150
                 * total_sales :
                 * detail_url : https://fanttest.fantuanlife.com/index.html#/product/130?mid=55
                 * buy_url : https://fanttest.fantuanlife.com/index.html#/product/130/order?mid=55
                 */

                private String pid;
                private String name;
                private String amount;
                private String market_price;
                private String total_sales;
                private String detail_url;
                private String buy_url;

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

                public String getDetail_url() {
                    return detail_url;
                }

                public void setDetail_url(String detail_url) {
                    this.detail_url = detail_url;
                }

                public String getBuy_url() {
                    return buy_url;
                }

                public void setBuy_url(String buy_url) {
                    this.buy_url = buy_url;
                }
            }
        }

        public static class PostBean implements Serializable {
            /**
             * title : 商家动态
             * post_url : https://fanttest.fantuanlife.com/index.html#/merchant/55/moment
             * content : [{"content":"赶回去莫得的份由来轻七尺巨魔都是\n看看去我们\n5门武器\n5句我的几号回去莫得\n莫得我自己来取门口了\n绿叶看看几个人\n了的\n5颗额我自己回去\n5门中央音乐学院啰啰嗦嗦么看见啊就几个人普陀门徒旅进旅退看见了魔域垃圾垃圾李说啥了11测度图哦我的油嘴滑舌葡萄牙\n模具姥姥姥爷我们我的爱过刺","create_at":"2017-12-29 10:57"}]
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

            public static class ContentBeanXXXX implements Serializable {
                /**
                 * content : 赶回去莫得的份由来轻七尺巨魔都是
                 * 看看去我们
                 * 5门武器
                 * 5句我的几号回去莫得
                 * 莫得我自己来取门口了
                 * 绿叶看看几个人
                 * 了的
                 * 5颗额我自己回去
                 * 5门中央音乐学院啰啰嗦嗦么看见啊就几个人普陀门徒旅进旅退看见了魔域垃圾垃圾李说啥了11测度图哦我的油嘴滑舌葡萄牙
                 * 模具姥姥姥爷我们我的爱过刺
                 * create_at : 2017-12-29 10:57
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

        public static class NewsBean implements Serializable{
            /**
             * title : 商家说
             * type : 2
             * content : [{"id":"48","news_id":"1","name":"八小炸炸！我对学校旁边的各家炸炸有执念","cover":["https://staticcdntest.fantuanlife.com/uimage/dd/20/af/82/dd20af82ca287f4b6df6606f60b0aa83.jpg"],"time":"01-24","read_num":"0次浏览","news_name":"海口美食家","article_url":"https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=48"}]
             */

            private String title;
            private String type;
            private List<ContentBeanXXXX> content;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<ContentBeanXXXX> getContent() {
                return content;
            }

            public void setContent(List<ContentBeanXXXX> content) {
                this.content = content;
            }

            public static class ContentBeanXXXX implements Serializable{
                /**
                 * id : 48
                 * news_id : 1
                 * name : 八小炸炸！我对学校旁边的各家炸炸有执念
                 * cover : ["https://staticcdntest.fantuanlife.com/uimage/dd/20/af/82/dd20af82ca287f4b6df6606f60b0aa83.jpg"]
                 * time : 01-24
                 * read_num : 0次浏览
                 * news_name : 海口美食家
                 * article_url : https://fanttest.fantuanlife.com/index.html#/article/detail?article_id=48
                 */

                private String id;
                private String news_id;
                private String name;
                private String time;
                private String read_num;
                private String news_name;
                private String article_url;
                private List<String> cover;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getNews_id() {
                    return news_id;
                }

                public void setNews_id(String news_id) {
                    this.news_id = news_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getRead_num() {
                    return read_num;
                }

                public void setRead_num(String read_num) {
                    this.read_num = read_num;
                }

                public String getNews_name() {
                    return news_name;
                }

                public void setNews_name(String news_name) {
                    this.news_name = news_name;
                }

                public String getArticle_url() {
                    return article_url;
                }

                public void setArticle_url(String article_url) {
                    this.article_url = article_url;
                }

                public List<String> getCover() {
                    return cover;
                }

                public void setCover(List<String> cover) {
                    this.cover = cover;
                }
            }
        }

        public static class ReviewBean implements Serializable {
            /**
             * title : 用户评价
             * totals : 16
             * review_url : https://fanttest.fantuanlife.com/index.html#/merchant/55/comment
             * content : [{"score":"5","uid":"23","content":"lost咯胡了holy咯啊配件给哦","image":"307138,307140,307141,307382","review_time":"2017-12-26 17:18","is_anonymous":"0","reply":"哈哈哈哈哈","user":{"id":"23","username":"陈加尧","avatar":"https://staticcdntest.fantuanlife.com/uimage/84/57/36/c4/845736c4a0febc922c22b5e4fca24806.jpg?x-oss-process=image/resize,m_fill,h_112,w_112/format,jpg/interlace,1/quality,Q_70"},"imageUrl":["https://staticcdntest.fantuanlife.com/uimage/7f/ad/19/69/7fad1969e970f77948e416546aab6efa.jpg","https://staticcdntest.fantuanlife.com/uimage/71/31/6e/c3/71316ec379977d880250bbaa791c2ceb.jpg","https://staticcdntest.fantuanlife.com/uimage/56/89/1a/36/56891a3639c0fa4cad0af462a541b454.jpg"]}]
             */

            private String title;
            private String totals;
            private String review_url;
            private List<ContentBeanXXXXX> content;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTotals() {
                return totals;
            }

            public void setTotals(String totals) {
                this.totals = totals;
            }

            public String getReview_url() {
                return review_url;
            }

            public void setReview_url(String review_url) {
                this.review_url = review_url;
            }

            public List<ContentBeanXXXXX> getContent() {
                return content;
            }

            public void setContent(List<ContentBeanXXXXX> content) {
                this.content = content;
            }

            public static class ContentBeanXXXXX implements Serializable {
                /**
                 * score : 5
                 * uid : 23
                 * content : lost咯胡了holy咯啊配件给哦
                 * image : 307138,307140,307141,307382
                 * review_time : 2017-12-26 17:18
                 * is_anonymous : 0
                 * reply : 哈哈哈哈哈
                 * user : {"id":"23","username":"陈加尧","avatar":"https://staticcdntest.fantuanlife.com/uimage/84/57/36/c4/845736c4a0febc922c22b5e4fca24806.jpg?x-oss-process=image/resize,m_fill,h_112,w_112/format,jpg/interlace,1/quality,Q_70"}
                 * imageUrl : ["https://staticcdntest.fantuanlife.com/uimage/7f/ad/19/69/7fad1969e970f77948e416546aab6efa.jpg","https://staticcdntest.fantuanlife.com/uimage/71/31/6e/c3/71316ec379977d880250bbaa791c2ceb.jpg","https://staticcdntest.fantuanlife.com/uimage/56/89/1a/36/56891a3639c0fa4cad0af462a541b454.jpg"]
                 */

                private String score;
                private String uid;
                private String content;
                private String image;
                private String review_time;
                private String is_anonymous;
                private String reply;
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

                public String getReply() {
                    return reply;
                }

                public void setReply(String reply) {
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

                public static class UserBean implements Serializable {
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
