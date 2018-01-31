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
    String NEWS = BASEURL + "/news";




    String PROTOCOL = "http://fant.fantuanlife.com/index.html#/user/agreement";


}
