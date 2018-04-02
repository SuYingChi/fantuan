package com.wetime.fanc.news.bean;

/**
 * 频道实体类
 * Created by YoKeyword on 15/12/29.
 */
public class ChannelBean {
// "is_default": 1,
//         "is_show": 1,
//         "order": 1

    private String id;
    private String name;

    public ChannelBean(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
