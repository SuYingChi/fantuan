package com.wetime.fanc.utils;

import com.wetime.fanc.BuildConfig;

/**
 * Created by zhoukang on 2017/8/29.
 */

public interface Const {
//    String BASEURL = "https://fanttest.fantuanlife.com";

    String BASEURL = BuildConfig.DEBUG ? "https://fanttest.fantuanlife.com" : "https://fant.fantuanlife.com";

    String PUSH_BIND = BASEURL + "/push/bind";
    String POSTMUTILEFILE = BASEURL + "/upload/image";

    String LOGIN_VCODE = BASEURL + "/login/vcode";
    String LOGIN_PSW = BASEURL + "/login";
    String LOGIN_WX = BASEURL + "/login/wexin";
    String WEIXIN_BIND = BASEURL + "/user/weixin/bind";
    String USER_PROFILE = BASEURL + "/user/profile";
    String LOGOUT = BASEURL + "/logout";
    String USER_PROFILE_UPDATEAVATAR = BASEURL + "/user/profile/updateavatar";
    String USER_PROFILE_UPDATENAME = BASEURL + "/user/profile/updatename";
    String USER_PROFILE_SETTING = BASEURL + "/user/profile/setting";
    String MALL = BASEURL + "/mall";
    String CATEGORY = BASEURL + "/category";
    String MALL_LIST = BASEURL + "/mall/list";
    String MALL_SEARCH_RESULT = BASEURL + "/mall/search/result";
    String SEARCH_RESULT = BASEURL + "/search/result";

    String HOME_SEARCH = BASEURL + "/search";
    String MALL_SEARCH = BASEURL + "/mall/search";
    String SEARCH_SUGGEST = BASEURL + "/search/suggest";
    String MALL_SEARCH_SUGGEST = BASEURL + "/mall/search/suggest";
    String HOMEPAGE = BASEURL + "";
    String ORDER_LIST = BASEURL + "/order/list";
    String WEIXIN_UNBIND = BASEURL + "/user/weixin/unbind";
    String ORDER_DELETE = BASEURL + "/order/delete";
    String ORDER_REVIEW = BASEURL + "/order/review";
    String VERSION = BASEURL + "/version";
    String SHOP = BASEURL + "/shop";
    String SHOP_FOCUS = BASEURL + "/shop/focus";
    String SHOP_FOCUS_CANCEL = BASEURL + "/shop/focus/cancel";
    String SHOP_COUPON_GET = BASEURL + "/shop/coupon/get";
    String WALLET_BALANCE = BASEURL + "/wallet/balance";
    String WALLET_PASSWORD_SET = BASEURL + "/wallet/password/set";
    String SMS_SEND = BASEURL + "/sms/send";
    String SMS_VERIFY = BASEURL + "/sms/verify";
    String WALLET_BALANCE_LIST = BASEURL + "/wallet/balance/list";
    String USER_INVITER = BASEURL + "/user/inviter";
    String USER_INVITER_HISTORY = BASEURL + "/user/inviter/history";
    String user_inviter_redbag = BASEURL + "/user/inviter/redbag";
//    String NEWS = BASEURL + "/news";

    String SPECIAL_NEWS = BASEURL + "/jv/anonymous/newsarticle/elementarticles";
    String NEWS_MALL = BASEURL + "/news/mall";
    String SHOP_POST_ARTICLE = BASEURL + "/shop/post/article";
    String SHOP_POST_LIST = BASEURL + "/shop/post/list";

    String CIRCLE = BASEURL + "/circle";
    String DYNAMIC_PUBLISH_INITFORM = BASEURL + "/dynamic/publish/initform";
    String DYNAMIC_PUBLISH_SAVE = BASEURL + "/dynamic/publish/save";
    String DYNAMIC_PUBLISH_LIST = BASEURL + "/dynamic/publish/list";
    String CIRCLE_INFO = BASEURL + "/circle/info";
    String CIRCLE_ATTRNTION = BASEURL + "/jv/qz/following";
    String DYNAMIC_LIST_CIRCLE = BASEURL + "/dynamic/list/circle";
    String DYNAMIC_LIST = BASEURL + "/dynamic/list";
    String USER_COLLECT_LIST = BASEURL + "/user/collect/list";
    String NEWS_COLLECT_CANCEL = BASEURL + "/news/collect/cancel";
    String USER_FOCUS_LIST = BASEURL + "/user/focus/list";
    String SOCIAL = BASEURL + "/social";
    String DYNAMIC_DETAIL = BASEURL + "/dynamic/detail";
    String LONG_DETAIL = BASEURL + "/jv/anonymous/qz/dynamicarticle";
    String DYNAMIC_COMMENT = BASEURL + "/dynamic/comment";
//    String DYNAMIC_COMMENT_DELETE = BASEURL + "/dynamic/comment/delete";
    String DYNAMIC_COMMENT_DELETE = BASEURL + "/jv/qz/deleterecord";
    String REPORT = BASEURL + "/jv/qz/report";
    String DYNAMIC_DELETE = BASEURL + "/dynamic/delete";
    String DYNAMIC_LIKE = BASEURL + "/dynamic/like";
    String USER_NOTICE_UNREAD = BASEURL + "/user/notice/unread";
    String MSG_URL = BASEURL + "/index.html#/user/message";
    String DYNAMIC_ADDRESS = BASEURL + "/dynamic/address";
    String DYNAMIC_ADDRESS_SEARCH = BASEURL + "/dynamic/address/search";
    String USER_FOLLOW = BASEURL + "/user/follow";
    String CIRCLE_FOLLOW = BASEURL + "/circle/follow";
    String USER_PROFILE_UPDATECOVER = BASEURL + "/user/profile/updatecover";
    String NEWS_CATEGORY_SAVE = BASEURL + "/news/category/save";
    String NEWS_CATEGORY_LOAD = BASEURL + "/news/category/load";
    String NEWS_CATEGORY_LIST = BASEURL + "/news/category/list";
    String NEWS_CATEGORY_RECOMMEND = BASEURL + "/news/category/recommend";


    String PROTOCOL = BASEURL + "/index.html#/user/agreement";
    String GTT_MY_FRIENDS = BASEURL + "/user/follow/list";
    String ATTENTION = BASEURL + "/user/follow";
    String GET_NEWS_DETAIL = BASEURL + "/news/detail";
    String ATTENTION_FRIENDS = BASEURL + "/user/follow";
    String SEND_COMMONET = BASEURL + "/news/comment/issue";
    String COLLECT_NEWS = BASEURL + "/news/collect";
    String GRT_ALL_COMMENT = BASEURL + "/news/detail/comment";
    String CLICK_LIKE = BASEURL + "/news/comment/like";
    String REPLY_CLICK_LIKE = BASEURL + "/news/reply/like";
    String GET_COMMENT_REPLY = BASEURL + "/news/comment";
    String SEND_COMMENT_REPLY = BASEURL + "/news/reply";
    String DELETE_COMMENT_REPLY = BASEURL + "/news/reply/delete";
    String DELETE_COMMENT = BASEURL + "/news/comment/delete";


    // java的写到这里
    String DYNAMIC_LONGARTICLE = BASEURL + "/jv/qz/publish/dynamicarticle";
    String NEWS = BASEURL + "/jv/anonymous/newsarticle/articles";



}
