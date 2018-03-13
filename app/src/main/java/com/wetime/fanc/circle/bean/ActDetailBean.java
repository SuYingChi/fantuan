package com.wetime.fanc.circle.bean;

import java.util.List;

/**
 * Created by zhoukang on 2018/3/12.
 */

public class ActDetailBean {

    /**
     * error : 0
     * msg :
     * data : {"id":"2","uid":"18","username":"王荣慰","avatar":"https://staticcdntest.fantuanlife.com/uimage/56/30/c1/8f/5630c18fdd3f4ffd2f65c59325cb93c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","circle_id":"1","circle_name":"吃喝玩乐","time":"02月26日","content":"这是一条很棒很棒的动态哦····不堵车了耶！！！","cover":["https://staticcdntest.fantuanlife.com/uimage/00/01/a1/5c/0001a15ccc4f3aa227220a94f20c29d3.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","https://staticcdntest.fantuanlife.com/uimage/00/01/a5/9f/0001a59f2b1e5babff2852ec8813593d.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"],"type":"19","read_num":"10次浏览","like_num":"3","comment_num":"30","dynamic_owner":true,"like_list":[{"uid":"18","avatar":"https://staticcdntest.fantuanlife.com/uimage/56/30/c1/8f/5630c18fdd3f4ffd2f65c59325cb93c7.jpg"},{"uid":"22","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg"},{"uid":"23","avatar":"https://staticcdntest.fantuanlife.com/uimage/84/57/36/c4/845736c4a0febc922c22b5e4fca24806.jpg"}],"has_like":true,"is_news":true,"comment_list":[{"id":"33","uid":"66","username":"188****8545","avatar":"https://staticcdntest.fantuanlife.com/uimage/09/b7/15/48/09b715489e902d79c76a9509b50685de.jpg","time":"03月09日","content":"我是真的没有","to_uid":"22","to_username":"爱吃土豆的小豆豆","is_news":false},{"id":"32","uid":"22","username":"爱吃土豆的小豆豆","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg","time":"03月09日","content":"晓明看到了吗，这是回复哦~~","to_uid":"22","to_username":"爱吃土豆的小豆豆","is_news":false},{"id":"31","uid":"22","username":"爱吃土豆的小豆豆","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg","time":"03月09日","content":"晓明看到了吗，这是评论哦~~","to_uid":"","to_username":"","is_news":false},{"id":"30","uid":"22","username":"爱吃土豆的小豆豆","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg","time":"03月09日","content":"说得什么鬼话，看不懂~~","to_uid":"21","to_username":"peggy","is_news":false},{"id":"29","uid":"66","username":"188****8545","avatar":"https://staticcdntest.fantuanlife.com/uimage/09/b7/15/48/09b715489e902d79c76a9509b50685de.jpg","time":"03月09日","content":"我就试试","to_uid":"18","to_username":"王荣慰","is_news":false},{"id":"28","uid":"66","username":"188****8545","avatar":"https://staticcdntest.fantuanlife.com/uimage/09/b7/15/48/09b715489e902d79c76a9509b50685de.jpg","time":"03月09日","content":"我就想睡觉","to_uid":"18","to_username":"王荣慰","is_news":false},{"id":"27","uid":"22","username":"爱吃土豆的小豆豆","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg","time":"03月02日","content":"说得什么鬼话，看不懂~~","to_uid":"21","to_username":"peggy","is_news":false},{"id":"26","uid":"21","username":"peggy","avatar":"https://staticcdntest.fantuanlife.com/uimage/34/a8/96/f1/34a896f13692732b061f0eb850c1537e.jpg","time":"03月02日","content":"你才是卖土豆的呢啊~~","to_uid":"21","to_username":"peggy","is_news":false},{"id":"25","uid":"21","username":"peggy","avatar":"https://staticcdntest.fantuanlife.com/uimage/34/a8/96/f1/34a896f13692732b061f0eb850c1537e.jpg","time":"03月02日","content":"楼上是卖加土豆的啊~~","to_uid":"22","to_username":"爱吃土豆的小豆豆","is_news":false},{"id":"24","uid":"21","username":"peggy","avatar":"https://staticcdntest.fantuanlife.com/uimage/34/a8/96/f1/34a896f13692732b061f0eb850c1537e.jpg","time":"03月02日","content":"楼上是卖土豆的吗？还是卖薯条的~~","to_uid":"","to_username":"","is_news":false}],"paging":{"total":26,"total_page":3,"limit":10,"pn":1,"is_end":false}}
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
         * id : 2
         * uid : 18
         * username : 王荣慰
         * avatar : https://staticcdntest.fantuanlife.com/uimage/56/30/c1/8f/5630c18fdd3f4ffd2f65c59325cb93c7.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70
         * circle_id : 1
         * circle_name : 吃喝玩乐
         * time : 02月26日
         * content : 这是一条很棒很棒的动态哦····不堵车了耶！！！
         * cover : ["https://staticcdntest.fantuanlife.com/uimage/00/01/a1/5c/0001a15ccc4f3aa227220a94f20c29d3.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70","https://staticcdntest.fantuanlife.com/uimage/00/01/a5/9f/0001a59f2b1e5babff2852ec8813593d.jpg?x-oss-process=image/resize,m_fill,h_200,w_200/format,jpg/interlace,1/quality,Q_70"]
         * type : 19
         * read_num : 10次浏览
         * like_num : 3
         * comment_num : 30
         * dynamic_owner : true
         * like_list : [{"uid":"18","avatar":"https://staticcdntest.fantuanlife.com/uimage/56/30/c1/8f/5630c18fdd3f4ffd2f65c59325cb93c7.jpg"},{"uid":"22","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg"},{"uid":"23","avatar":"https://staticcdntest.fantuanlife.com/uimage/84/57/36/c4/845736c4a0febc922c22b5e4fca24806.jpg"}]
         * has_like : true
         * is_news : true
         * comment_list : [{"id":"33","uid":"66","username":"188****8545","avatar":"https://staticcdntest.fantuanlife.com/uimage/09/b7/15/48/09b715489e902d79c76a9509b50685de.jpg","time":"03月09日","content":"我是真的没有","to_uid":"22","to_username":"爱吃土豆的小豆豆","is_news":false},{"id":"32","uid":"22","username":"爱吃土豆的小豆豆","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg","time":"03月09日","content":"晓明看到了吗，这是回复哦~~","to_uid":"22","to_username":"爱吃土豆的小豆豆","is_news":false},{"id":"31","uid":"22","username":"爱吃土豆的小豆豆","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg","time":"03月09日","content":"晓明看到了吗，这是评论哦~~","to_uid":"","to_username":"","is_news":false},{"id":"30","uid":"22","username":"爱吃土豆的小豆豆","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg","time":"03月09日","content":"说得什么鬼话，看不懂~~","to_uid":"21","to_username":"peggy","is_news":false},{"id":"29","uid":"66","username":"188****8545","avatar":"https://staticcdntest.fantuanlife.com/uimage/09/b7/15/48/09b715489e902d79c76a9509b50685de.jpg","time":"03月09日","content":"我就试试","to_uid":"18","to_username":"王荣慰","is_news":false},{"id":"28","uid":"66","username":"188****8545","avatar":"https://staticcdntest.fantuanlife.com/uimage/09/b7/15/48/09b715489e902d79c76a9509b50685de.jpg","time":"03月09日","content":"我就想睡觉","to_uid":"18","to_username":"王荣慰","is_news":false},{"id":"27","uid":"22","username":"爱吃土豆的小豆豆","avatar":"https://staticcdntest.fantuanlife.com/uimage/78/b9/23/43/78b9234394368dd0e4b4c88c30b1e11f.jpg","time":"03月02日","content":"说得什么鬼话，看不懂~~","to_uid":"21","to_username":"peggy","is_news":false},{"id":"26","uid":"21","username":"peggy","avatar":"https://staticcdntest.fantuanlife.com/uimage/34/a8/96/f1/34a896f13692732b061f0eb850c1537e.jpg","time":"03月02日","content":"你才是卖土豆的呢啊~~","to_uid":"21","to_username":"peggy","is_news":false},{"id":"25","uid":"21","username":"peggy","avatar":"https://staticcdntest.fantuanlife.com/uimage/34/a8/96/f1/34a896f13692732b061f0eb850c1537e.jpg","time":"03月02日","content":"楼上是卖加土豆的啊~~","to_uid":"22","to_username":"爱吃土豆的小豆豆","is_news":false},{"id":"24","uid":"21","username":"peggy","avatar":"https://staticcdntest.fantuanlife.com/uimage/34/a8/96/f1/34a896f13692732b061f0eb850c1537e.jpg","time":"03月02日","content":"楼上是卖土豆的吗？还是卖薯条的~~","to_uid":"","to_username":"","is_news":false}]
         * paging : {"total":26,"total_page":3,"limit":10,"pn":1,"is_end":false}
         */

        private String id;
        private String uid;
        private String username;
        private String avatar;
        private String circle_id;
        private String circle_name;
        private String time;
        private String content;
        private int type;
        private String read_num;
        private String like_num;
        private int comment_num;
        private boolean dynamic_owner;
        private boolean has_like;
        private boolean is_news;
        private boolean is_owner;
        private PagingBean paging;
        private List<String> cover;
        private List<LikeListBean> like_list;
        private List<CommentListBean> comment_list;

        public boolean isIs_owner() {
            return is_owner;
        }

        public void setIs_owner(boolean is_owner) {
            this.is_owner = is_owner;
        }

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

        public String getCircle_id() {
            return circle_id;
        }

        public void setCircle_id(String circle_id) {
            this.circle_id = circle_id;
        }

        public String getCircle_name() {
            return circle_name;
        }

        public void setCircle_name(String circle_name) {
            this.circle_name = circle_name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getRead_num() {
            return read_num;
        }

        public void setRead_num(String read_num) {
            this.read_num = read_num;
        }

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public boolean isDynamic_owner() {
            return dynamic_owner;
        }

        public void setDynamic_owner(boolean dynamic_owner) {
            this.dynamic_owner = dynamic_owner;
        }

        public boolean isHas_like() {
            return has_like;
        }

        public void setHas_like(boolean has_like) {
            this.has_like = has_like;
        }

        public boolean isIs_news() {
            return is_news;
        }

        public void setIs_news(boolean is_news) {
            this.is_news = is_news;
        }

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<String> getCover() {
            return cover;
        }

        public void setCover(List<String> cover) {
            this.cover = cover;
        }

        public List<LikeListBean> getLike_list() {
            return like_list;
        }

        public void setLike_list(List<LikeListBean> like_list) {
            this.like_list = like_list;
        }

        public List<CommentListBean> getComment_list() {
            return comment_list;
        }

        public void setComment_list(List<CommentListBean> comment_list) {
            this.comment_list = comment_list;
        }

        public static class PagingBean {
            /**
             * total : 26
             * total_page : 3
             * limit : 10
             * pn : 1
             * is_end : false
             */

            private int total;
            private int total_page;
            private int limit;
            private int pn;
            private boolean is_end;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getTotal_page() {
                return total_page;
            }

            public void setTotal_page(int total_page) {
                this.total_page = total_page;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public int getPn() {
                return pn;
            }

            public void setPn(int pn) {
                this.pn = pn;
            }

            public boolean isIs_end() {
                return is_end;
            }

            public void setIs_end(boolean is_end) {
                this.is_end = is_end;
            }
        }

        public static class LikeListBean {
            /**
             * uid : 18
             * avatar : https://staticcdntest.fantuanlife.com/uimage/56/30/c1/8f/5630c18fdd3f4ffd2f65c59325cb93c7.jpg
             */

            private String uid;
            private String avatar;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }

        public static class CommentListBean {
            /**
             * id : 33
             * uid : 66
             * username : 188****8545
             * avatar : https://staticcdntest.fantuanlife.com/uimage/09/b7/15/48/09b715489e902d79c76a9509b50685de.jpg
             * time : 03月09日
             * content : 我是真的没有
             * to_uid : 22
             * to_username : 爱吃土豆的小豆豆
             * is_news : false
             */

            private String id;
            private String uid;
            private String username;
            private String avatar;
            private String time;
            private String content;
            private String to_uid;
            private String to_username;
            private boolean is_news;
            private boolean is_owner;
            private boolean to_news;

            public boolean isTo_news() {
                return to_news;
            }

            public void setTo_news(boolean to_news) {
                this.to_news = to_news;
            }

            public boolean isIs_owner() {
                return is_owner;
            }

            public void setIs_owner(boolean is_owner) {
                this.is_owner = is_owner;
            }

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

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTo_uid() {
                return to_uid;
            }

            public void setTo_uid(String to_uid) {
                this.to_uid = to_uid;
            }

            public String getTo_username() {
                return to_username;
            }

            public void setTo_username(String to_username) {
                this.to_username = to_username;
            }

            public boolean isIs_news() {
                return is_news;
            }

            public void setIs_news(boolean is_news) {
                this.is_news = is_news;
            }
        }
    }
}
