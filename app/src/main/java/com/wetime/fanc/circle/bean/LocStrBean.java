package com.wetime.fanc.circle.bean;

public class LocStrBean {

    /**
     * error : 0
     * msg :
     * data : {"title":"玉沙京华城(西北门店)","address":"海南省海口市龙华区金贸街道玉沙路30-4号玉沙京华城(西北门店)","lng":"110.318696","lat":"20.028002"}
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
         * title : 玉沙京华城(西北门店)
         * address : 海南省海口市龙华区金贸街道玉沙路30-4号玉沙京华城(西北门店)
         * lng : 110.318696
         * lat : 20.028002
         */

        private String title;
        private String address;
        private String lng;
        private String lat;

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
}
