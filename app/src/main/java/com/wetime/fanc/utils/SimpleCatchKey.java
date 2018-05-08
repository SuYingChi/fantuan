package com.wetime.fanc.utils;

import com.wetime.fanc.application.FApp;

public interface SimpleCatchKey {
    String catchkey_home = "catch_homepage_" + Tools.getVerCode(FApp.getInstance());
    String catchkey_news = "catch_news_" + Tools.getVerCode(FApp.getInstance());
    String catchkey_news_header = "catch_news_homeheader_" + Tools.getVerCode(FApp.getInstance());
    String catchkey_circle_home = "catch_circle_home_" + Tools.getVerCode(FApp.getInstance());
    String catchkey_attention = "catch_attention_page_" + Tools.getVerCode(FApp.getInstance());
    String catch_recommend = "catch_recommend" + Tools.getVerCode(FApp.getInstance());
}
