package com.wetime.fanc.circle.bean;

import java.io.Serializable;

public class LocItemBean implements Serializable {


    public LocItemBean() {
        this.title = "";
        this.address = "";
        this.lng = "";
        this.lat = "";
    }

    /**
     * title : 酱紫沙拉店(京华城店)
     * address : 海南省海口市龙华区京华城玉沙路23号金城国际架空层4号门市的商铺(宝丽餐厅楼下)
     * lng : 110.319553
     * lat : 20.028709
     */

    private String title;
    private String address;
    private String lng;
    private String lat;
    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
