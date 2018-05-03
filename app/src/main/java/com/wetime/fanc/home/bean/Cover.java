package com.wetime.fanc.home.bean;

import java.io.Serializable;

public class Cover implements Serializable{

    /**
     * url : https://staticcdntest.fantuanlife.com/uimage/a5/e3/be/77/a5e3be77b660d832fa63c801a2a0cfa8.jpg
     * compress : https://staticcdntest.fantuanlife.com/uimage/a5/e3/be/77/a5e3be77b660d832fa63c801a2a0cfa8.jpg?x-oss-process=image/resize,m_mfit,h_200/format,jpg/interlace,1/quality,Q_20/
     * height : 0
     * width : 0
     * longCover : false
     */

    private String url;
    private String compress;
    private String height;
    private String width;
    private boolean longCover;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompress() {
        return compress;
    }

    public void setCompress(String compress) {
        this.compress = compress;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public boolean isLongCover() {
        return longCover;
    }

    public void setLongCover(boolean longCover) {
        this.longCover = longCover;
    }
}
