package com.wetime.fanc.news.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class ReplyCommentBean {

    /**
     * error : 0
     * msg :
     * data : {"comment":{"id":"1","uid":"15","content":"家光好帅啊，惊呆了我啊！！！！！","like_num":"2","time":"02月28日","user":{"id":"15","username":"枫释","avatar":"https://staticcdntest.fantuanlife.com/uimage/83/16/2a/64/83162a64e3bc72da90cf9cec1176d4e0.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"is_author":false,"is_like":true,"is_news":false},"reply":[{"id":"112","comment_id":"1","uid":"23","content":"ddd","time":"03月29日","like_num":"1","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"111","comment_id":"1","uid":"23","content":"aaaaaaaaaa","time":"2018-03-29 10:29:41","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}},"is_author":true,"is_news":true,"is_like":true},{"id":"111","comment_id":"1","uid":"23","content":"aaaaaaaaaa","time":"03月29日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"110","comment_id":"1","uid":"23","content":"sdfsd","time":"03月29日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"109","comment_id":"1","uid":"23","content":"dfg","time":"03月29日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"96","comment_id":"1","uid":"23","content":"testsssssss","time":"03月27日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"22","comment_id":"1","uid":"23","content":"sdf","time":"03月07日","like_num":"1","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"20","comment_id":"1","uid":"23","content":"wert","time":"03月07日","like_num":"1","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"19","comment_id":"1","uid":"23","content":"srt","time":"2018-03-07 09:21:21","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}},"is_author":true,"is_news":true,"is_like":true},{"id":"19","comment_id":"1","uid":"23","content":"srt","time":"03月07日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"18","comment_id":"1","uid":"23","content":"ee4","time":"03月07日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"17","comment_id":"1","uid":"23","content":"dff","time":"03月07日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"15","comment_id":"1","uid":"23","content":"水电费","time":"03月06日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"2","comment_id":"1","uid":"20","content":"好假啊逗比","time":"2018-02-28 15:29:14","like_num":"1","user":{"id":"20","username":"188****6435","avatar":"353783","is_author":false}},"is_author":true,"is_news":true,"is_like":false},{"id":"9","comment_id":"1","uid":"23","content":"测试回复的回复","time":"03月06日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"8","comment_id":"1","uid":"23","content":"测试回复","time":"2018-03-06 10:59:20","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}},"is_author":true,"is_news":true,"is_like":false},{"id":"7","comment_id":"1","uid":"23","content":"我就@你，测试@交互","time":"03月06日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"6","comment_id":"1","uid":"23","content":"测试回复，不@任何人","time":"2018-03-06 10:45:11","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}},"is_author":true,"is_news":true,"is_like":false},{"id":"6","comment_id":"1","uid":"23","content":"测试回复，不@任何人","time":"03月06日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"4","comment_id":"1","uid":"22","content":"别老是说假话","time":"03月05日","like_num":"0","user":{"id":"22","username":"爱吃土豆的小豆豆","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"2","comment_id":"1","uid":"20","content":"好假啊逗比","time":"2018-02-28 15:29:14","like_num":"1","user":{"id":"20","username":"188****6435","avatar":"353783","is_author":false}},"is_author":false,"is_news":false,"is_like":false},{"id":"3","comment_id":"1","uid":"21","content":"长得还行","time":"03月05日","like_num":"0","user":{"id":"21","username":"peggy","avatar":"https://staticcdntest.fantuanlife.com/uimage/34/a8/96/f1/34a896f13692732b061f0eb850c1537e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"1","comment_id":"1","uid":"23","content":"谢谢你哦，说了大实话！！！","time":"2018-02-28 10:15:29","like_num":"2","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}},"is_author":false,"is_news":true,"is_like":false},{"id":"2","comment_id":"1","uid":"20","content":"好假啊逗比","time":"02月28日","like_num":"1","user":{"id":"20","username":"188****6435","avatar":"https://staticcdntest.fantuanlife.com/uimage/3c/6b/bb/ea/3c6bbbeaa553cdf66d8ed4418f156676.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"1","comment_id":"1","uid":"23","content":"谢谢你哦，说了大实话！！！","time":"2018-02-28 10:15:29","like_num":"2","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}},"is_author":false,"is_news":false,"is_like":true},{"id":"1","comment_id":"1","uid":"23","content":"谢谢你哦，说了大实话！！！","time":"02月28日","like_num":"2","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":true}]}
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
         * comment : {"id":"1","uid":"15","content":"家光好帅啊，惊呆了我啊！！！！！","like_num":"2","time":"02月28日","user":{"id":"15","username":"枫释","avatar":"https://staticcdntest.fantuanlife.com/uimage/83/16/2a/64/83162a64e3bc72da90cf9cec1176d4e0.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"is_author":false,"is_like":true,"is_news":false}
         * reply : [{"id":"112","comment_id":"1","uid":"23","content":"ddd","time":"03月29日","like_num":"1","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"111","comment_id":"1","uid":"23","content":"aaaaaaaaaa","time":"2018-03-29 10:29:41","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}},"is_author":true,"is_news":true,"is_like":true},{"id":"111","comment_id":"1","uid":"23","content":"aaaaaaaaaa","time":"03月29日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"110","comment_id":"1","uid":"23","content":"sdfsd","time":"03月29日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"109","comment_id":"1","uid":"23","content":"dfg","time":"03月29日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"96","comment_id":"1","uid":"23","content":"testsssssss","time":"03月27日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"22","comment_id":"1","uid":"23","content":"sdf","time":"03月07日","like_num":"1","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"20","comment_id":"1","uid":"23","content":"wert","time":"03月07日","like_num":"1","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"19","comment_id":"1","uid":"23","content":"srt","time":"2018-03-07 09:21:21","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}},"is_author":true,"is_news":true,"is_like":true},{"id":"19","comment_id":"1","uid":"23","content":"srt","time":"03月07日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"18","comment_id":"1","uid":"23","content":"ee4","time":"03月07日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"17","comment_id":"1","uid":"23","content":"dff","time":"03月07日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"15","comment_id":"1","uid":"23","content":"水电费","time":"03月06日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"2","comment_id":"1","uid":"20","content":"好假啊逗比","time":"2018-02-28 15:29:14","like_num":"1","user":{"id":"20","username":"188****6435","avatar":"353783","is_author":false}},"is_author":true,"is_news":true,"is_like":false},{"id":"9","comment_id":"1","uid":"23","content":"测试回复的回复","time":"03月06日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"8","comment_id":"1","uid":"23","content":"测试回复","time":"2018-03-06 10:59:20","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}},"is_author":true,"is_news":true,"is_like":false},{"id":"7","comment_id":"1","uid":"23","content":"我就@你，测试@交互","time":"03月06日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"6","comment_id":"1","uid":"23","content":"测试回复，不@任何人","time":"2018-03-06 10:45:11","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}},"is_author":true,"is_news":true,"is_like":false},{"id":"6","comment_id":"1","uid":"23","content":"测试回复，不@任何人","time":"03月06日","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":false},{"id":"4","comment_id":"1","uid":"22","content":"别老是说假话","time":"03月05日","like_num":"0","user":{"id":"22","username":"爱吃土豆的小豆豆","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"2","comment_id":"1","uid":"20","content":"好假啊逗比","time":"2018-02-28 15:29:14","like_num":"1","user":{"id":"20","username":"188****6435","avatar":"353783","is_author":false}},"is_author":false,"is_news":false,"is_like":false},{"id":"3","comment_id":"1","uid":"21","content":"长得还行","time":"03月05日","like_num":"0","user":{"id":"21","username":"peggy","avatar":"https://staticcdntest.fantuanlife.com/uimage/34/a8/96/f1/34a896f13692732b061f0eb850c1537e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"1","comment_id":"1","uid":"23","content":"谢谢你哦，说了大实话！！！","time":"2018-02-28 10:15:29","like_num":"2","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}},"is_author":false,"is_news":true,"is_like":false},{"id":"2","comment_id":"1","uid":"20","content":"好假啊逗比","time":"02月28日","like_num":"1","user":{"id":"20","username":"188****6435","avatar":"https://staticcdntest.fantuanlife.com/uimage/3c/6b/bb/ea/3c6bbbeaa553cdf66d8ed4418f156676.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":{"id":"1","comment_id":"1","uid":"23","content":"谢谢你哦，说了大实话！！！","time":"2018-02-28 10:15:29","like_num":"2","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}},"is_author":false,"is_news":false,"is_like":true},{"id":"1","comment_id":"1","uid":"23","content":"谢谢你哦，说了大实话！！！","time":"02月28日","like_num":"2","user":{"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"},"reply_object":[],"is_author":true,"is_news":true,"is_like":true}]
         */

        private CommentBean comment;
        private List<ReplyBean> reply;

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public List<ReplyBean> getReply() {
            return reply;
        }

        public void setReply(List<ReplyBean> reply) {
            this.reply = reply;
        }

        public static class CommentBean {
            /**
             * id : 1
             * uid : 15
             * content : 家光好帅啊，惊呆了我啊！！！！！
             * like_num : 2
             * time : 02月28日
             * user : {"id":"15","username":"枫释","avatar":"https://staticcdntest.fantuanlife.com/uimage/83/16/2a/64/83162a64e3bc72da90cf9cec1176d4e0.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"}
             * is_author : false
             * is_like : true
             * is_news : false
             */

            private String id;
            private String uid;
            private String content;
            private String like_num;
            private String time;
            private UserBean user;
            private boolean is_author;
            private boolean is_like;
            private boolean is_news;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getLike_num() {
                return like_num;
            }

            public void setLike_num(String like_num) {
                this.like_num = like_num;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
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

            public boolean isIs_news() {
                return is_news;
            }

            public void setIs_news(boolean is_news) {
                this.is_news = is_news;
            }

            public static class UserBean {
                /**
                 * id : 15
                 * username : 枫释
                 * avatar : https://staticcdntest.fantuanlife.com/uimage/83/16/2a/64/83162a64e3bc72da90cf9cec1176d4e0.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
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

        public static class ReplyBean {
            /**
             * id : 112
             * comment_id : 1
             * uid : 23
             * content : ddd
             * time : 03月29日
             * like_num : 1
             * user : {"id":"23","username":"神经病的日常","avatar":"https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"}
             * reply_object : {"id":"111","comment_id":"1","uid":"23","content":"aaaaaaaaaa","time":"2018-03-29 10:29:41","like_num":"0","user":{"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}}
             * is_author : true
             * is_news : true
             * is_like : true
             */

            private String id;
            private String comment_id;
            private String uid;
            private String content;
            private String time;
            private String like_num;
            private UserBeanX user;
            private ReplyObjectBean reply_object;
            private boolean is_author;
            private boolean is_news;
            private boolean is_like;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getComment_id() {
                return comment_id;
            }

            public void setComment_id(String comment_id) {
                this.comment_id = comment_id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getLike_num() {
                return like_num;
            }

            public void setLike_num(String like_num) {
                this.like_num = like_num;
            }

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
            }

            public ReplyObjectBean getReply_object() {
                return reply_object;
            }

            public void setReply_object(ReplyObjectBean reply_object) {
                this.reply_object = reply_object;
            }

            public boolean isIs_author() {
                return is_author;
            }

            public void setIs_author(boolean is_author) {
                this.is_author = is_author;
            }

            public boolean isIs_news() {
                return is_news;
            }

            public void setIs_news(boolean is_news) {
                this.is_news = is_news;
            }

            public boolean isIs_like() {
                return is_like;
            }

            public void setIs_like(boolean is_like) {
                this.is_like = is_like;
            }

            public static class UserBeanX {
                /**
                 * id : 23
                 * username : 神经病的日常
                 * avatar : https://staticcdntest.fantuanlife.com/uimage/30/34/00/f4/303400f48e12c0009dce7ad0d861b20e.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
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

            public static class ReplyObjectBean {
                /**
                 * id : 111
                 * comment_id : 1
                 * uid : 23
                 * content : aaaaaaaaaa
                 * time : 2018-03-29 10:29:41
                 * like_num : 0
                 * user : {"id":"23","username":"神经病的日常","avatar":"353614","is_author":true}
                 */

                private String id;
                private String comment_id;
                private String uid;
                private String content;
                private String time;
                private String like_num;
                private UserBeanXX user;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getComment_id() {
                    return comment_id;
                }

                public void setComment_id(String comment_id) {
                    this.comment_id = comment_id;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getLike_num() {
                    return like_num;
                }

                public void setLike_num(String like_num) {
                    this.like_num = like_num;
                }

                public UserBeanXX getUser() {
                    return user;
                }

                public void setUser(UserBeanXX user) {
                    this.user = user;
                }

                public static class UserBeanXX {
                    /**
                     * id : 23
                     * username : 神经病的日常
                     * avatar : 353614
                     * is_author : true
                     */

                    private String id;
                    private String username;
                    private String avatar;
                    private boolean is_author;

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

                    public boolean isIs_author() {
                        return is_author;
                    }

                    public void setIs_author(boolean is_author) {
                        this.is_author = is_author;
                    }
                }
            }
        }
    }
}
