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
    private String is_default;
    private String is_show;
    private String order;


    public ChannelBean() {
    }

    public ChannelBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
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
