package com.wetime.fanc.web.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/8.
 */

public class NewsWebCommBean {

    /**
     * error : 0
     * msg :
     * data : {"paging":{"total":"0","total_page":"1","limit":"20","pn":"1","is_end":false},"list":[{"like_num":"3","is_news":true,"is_author":false,"is_like":false,"reply_num":"20","id":"192","time":"03月30日 11:52","aid":"7241","user":{"id":"19","username":"1234567890","avatar":"https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"起飞吧，郑总"},{"like_num":"2","is_news":false,"is_author":false,"is_like":false,"reply_num":"16","id":"220","time":"04月04日 13:50","aid":"7241","user":{"id":"37","username":"Jian","avatar":"https://staticcdntest.fantuanlife.com/uimage/e4/d5/fd/ff/e4d5fdffab49e67338bf06734e6a21bf.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"mr\u2006ni\u2006yi\u2006w\u2006s"},{"like_num":"3","is_news":true,"is_author":false,"is_like":false,"reply_num":"3","id":"209","time":"04月02日 13:49","aid":"7241","user":{"id":"19","username":"1234567890","avatar":"https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"额额额额额额"},{"like_num":"0","is_news":true,"is_author":false,"is_like":false,"reply_num":"1","id":"280","time":"04月09日 18:02","aid":"7241","user":{"id":"10","username":"winnercity","avatar":"https://staticcdntest.fantuanlife.com/uimage/28/09/a6/af/2809a6af67b7cf4d7f130547c0147e3d.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"看一下我的评论是不是在最上方"},{"like_num":"1","is_news":true,"is_author":false,"is_like":false,"reply_num":"0","id":"281","time":"04月09日 18:07","aid":"7241","user":{"id":"10","username":"winnercity","avatar":"https://staticcdntest.fantuanlife.com/uimage/28/09/a6/af/2809a6af67b7cf4d7f130547c0147e3d.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"是"},{"like_num":"1","is_news":true,"is_author":false,"is_like":false,"reply_num":"0","id":"176","time":"03月30日 08:39","aid":"7241","user":{"id":"19","username":"1234567890","avatar":"https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"111112346vhhhv大大大大大大大美女啊大大大的小宝贝你怎么知道自己喜欢什么🤔"},{"like_num":"1","is_news":true,"is_author":false,"is_like":false,"reply_num":"0","id":"171","time":"03月29日 16:27","aid":"7241","user":{"id":"19","username":"1234567890","avatar":"https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"Qqq "},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"297","time":"04月10日 13:42","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"Null"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"296","time":"04月10日 13:42","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"Jbdjcnkxns"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"295","time":"04月10日 08:57","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"T7bjd guhd"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"294","time":"04月10日 08:56","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"Tibjtt g"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"282","time":"04月09日 18:33","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"Ujsixbj"},{"like_num":"0","is_news":true,"is_author":false,"is_like":false,"reply_num":"0","id":"279","time":"04月09日 17:56","aid":"7241","user":{"id":"10","username":"winnercity","avatar":"https://staticcdntest.fantuanlife.com/uimage/28/09/a6/af/2809a6af67b7cf4d7f130547c0147e3d.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"思路睡觉觉了"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"274","time":"04月09日 15:59","aid":"7241","user":{"id":"37","username":"Jian","avatar":"https://staticcdntest.fantuanlife.com/uimage/e4/d5/fd/ff/e4d5fdffab49e67338bf06734e6a21bf.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"哦婆婆说"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"250","time":"04月04日 16:43","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"咯用狗年"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"249","time":"04月04日 16:43","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"myxedema"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"248","time":"04月04日 16:43","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"后天"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"247","time":"04月04日 16:43","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"咯哦"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"246","time":"04月04日 16:41","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"头哦XP"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"244","time":"04月04日 16:23","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"Vihj"}]}
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
         * paging : {"total":"0","total_page":"1","limit":"20","pn":"1","is_end":false}
         * list : [{"like_num":"3","is_news":true,"is_author":false,"is_like":false,"reply_num":"20","id":"192","time":"03月30日 11:52","aid":"7241","user":{"id":"19","username":"1234567890","avatar":"https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"起飞吧，郑总"},{"like_num":"2","is_news":false,"is_author":false,"is_like":false,"reply_num":"16","id":"220","time":"04月04日 13:50","aid":"7241","user":{"id":"37","username":"Jian","avatar":"https://staticcdntest.fantuanlife.com/uimage/e4/d5/fd/ff/e4d5fdffab49e67338bf06734e6a21bf.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"mr\u2006ni\u2006yi\u2006w\u2006s"},{"like_num":"3","is_news":true,"is_author":false,"is_like":false,"reply_num":"3","id":"209","time":"04月02日 13:49","aid":"7241","user":{"id":"19","username":"1234567890","avatar":"https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"额额额额额额"},{"like_num":"0","is_news":true,"is_author":false,"is_like":false,"reply_num":"1","id":"280","time":"04月09日 18:02","aid":"7241","user":{"id":"10","username":"winnercity","avatar":"https://staticcdntest.fantuanlife.com/uimage/28/09/a6/af/2809a6af67b7cf4d7f130547c0147e3d.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"看一下我的评论是不是在最上方"},{"like_num":"1","is_news":true,"is_author":false,"is_like":false,"reply_num":"0","id":"281","time":"04月09日 18:07","aid":"7241","user":{"id":"10","username":"winnercity","avatar":"https://staticcdntest.fantuanlife.com/uimage/28/09/a6/af/2809a6af67b7cf4d7f130547c0147e3d.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"是"},{"like_num":"1","is_news":true,"is_author":false,"is_like":false,"reply_num":"0","id":"176","time":"03月30日 08:39","aid":"7241","user":{"id":"19","username":"1234567890","avatar":"https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"111112346vhhhv大大大大大大大美女啊大大大的小宝贝你怎么知道自己喜欢什么🤔"},{"like_num":"1","is_news":true,"is_author":false,"is_like":false,"reply_num":"0","id":"171","time":"03月29日 16:27","aid":"7241","user":{"id":"19","username":"1234567890","avatar":"https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"Qqq "},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"297","time":"04月10日 13:42","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"Null"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"296","time":"04月10日 13:42","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"Jbdjcnkxns"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"295","time":"04月10日 08:57","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"T7bjd guhd"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"294","time":"04月10日 08:56","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"Tibjtt g"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"282","time":"04月09日 18:33","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"Ujsixbj"},{"like_num":"0","is_news":true,"is_author":false,"is_like":false,"reply_num":"0","id":"279","time":"04月09日 17:56","aid":"7241","user":{"id":"10","username":"winnercity","avatar":"https://staticcdntest.fantuanlife.com/uimage/28/09/a6/af/2809a6af67b7cf4d7f130547c0147e3d.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"思路睡觉觉了"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"274","time":"04月09日 15:59","aid":"7241","user":{"id":"37","username":"Jian","avatar":"https://staticcdntest.fantuanlife.com/uimage/e4/d5/fd/ff/e4d5fdffab49e67338bf06734e6a21bf.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"哦婆婆说"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"250","time":"04月04日 16:43","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"咯用狗年"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"249","time":"04月04日 16:43","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"myxedema"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"248","time":"04月04日 16:43","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"后天"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"247","time":"04月04日 16:43","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"咯哦"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"246","time":"04月04日 16:41","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"头哦XP"},{"like_num":"0","is_news":false,"is_author":false,"is_like":false,"reply_num":"0","id":"244","time":"04月04日 16:23","aid":"7241","user":{"id":"281","username":"183****3639","avatar":"https://staticcdntest.fantuanlife.com/uimage/d7/06/30/14/d706301410c4ea18b860a9906fdc511c.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"},"content":"Vihj"}]
         */

        private PagingBean paging;
        private List<ListBean> list;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PagingBean {
            /**
             * total : 0
             * total_page : 1
             * limit : 20
             * pn : 1
             * is_end : false
             */

            private String total;
            private String total_page;
            private String limit;
            private String pn;
            private boolean is_end;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getTotal_page() {
                return total_page;
            }

            public void setTotal_page(String total_page) {
                this.total_page = total_page;
            }

            public String getLimit() {
                return limit;
            }

            public void setLimit(String limit) {
                this.limit = limit;
            }

            public String getPn() {
                return pn;
            }

            public void setPn(String pn) {
                this.pn = pn;
            }

            public boolean isIs_end() {
                return is_end;
            }

            public void setIs_end(boolean is_end) {
                this.is_end = is_end;
            }
        }

        public static class ListBean {
            /**
             * like_num : 3
             * is_news : true
             * is_author : false
             * is_like : false
             * reply_num : 20
             * id : 192
             * time : 03月30日 11:52
             * aid : 7241
             * user : {"id":"19","username":"1234567890","avatar":"https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/"}
             * content : 起飞吧，郑总
             */

            private String like_num;
            private boolean is_news;
            private boolean is_author;
            private boolean is_like;
            private String reply_num;
            private String id;
            private String time;
            private String aid;
            private UserBean user;
            private String content;

            public String getLike_num() {
                return like_num;
            }

            public void setLike_num(String like_num) {
                this.like_num = like_num;
            }

            public boolean isIs_news() {
                return is_news;
            }

            public void setIs_news(boolean is_news) {
                this.is_news = is_news;
            }

            public boolean isIs_author() {
                return is_author;
            }

            public void setIs_author(boolean is_author) {
                this.is_author = is_author;
            }

            public boolean isIs_like() {
                return is_like;
            }

            public void setIs_like(boolean is_like) {
                this.is_like = is_like;
            }

            public String getReply_num() {
                return reply_num;
            }

            public void setReply_num(String reply_num) {
                this.reply_num = reply_num;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public static class UserBean {
                /**
                 * id : 19
                 * username : 1234567890
                 * avatar : https://staticcdntest.fantuanlife.com/uimage/f5/2d/0f/78/f52d0f7842df9f03a1488bcff1031b70.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70/
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
