package com.wetime.fanc.utils;

import com.wetime.fanc.application.FApp;

public interface SimpleCatchKey {
    String catchkey_home = "catch_homepage_" + Tools.getVerCode(FApp.getInstance());
    String catchkey_news_header = "catch_news_homeheader" + Tools.getVerCode(FApp.getInstance());
}
