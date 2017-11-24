package com.wetime.fanc.setting.bean;

import java.io.Serializable;

/**
 * Created by zhoukang on 2017/11/3.
 */

public class SettingPageBean {


    /**
     * error : 0
     * msg :
     * data : {"username":"花心萝卜腿","avatar":"http://shoptest.weishike.net/uimage/a8/c9/45/da/a8c945da508e22bb2243e2f3772e69a0.jpg?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70","phone":"18976645599","weixin":{"nickname":"花心萝卜腿","avatar":"http://shoptest.weishike.net/uimage/a8/c9/45/da/a8c945da508e22bb2243e2f3772e69a0.jpg?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70"},"password":"","link":{"password_create":{"name":"设置密码","url":"https://www.baidu.com/"},"password_change":{"name":"修改密码","url":"https://www.baidu.com/"},"phone_bind":{"name":"绑定手机号","url":"https://www.baidu.com/"},"phone_change":{"name":"修改手机号","url":"https://www.baidu.com/"}}}
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
         * username : 花心萝卜腿
         * avatar : http://shoptest.weishike.net/uimage/a8/c9/45/da/a8c945da508e22bb2243e2f3772e69a0.jpg?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70
         * phone : 18976645599
         * weixin : {"nickname":"花心萝卜腿","avatar":"http://shoptest.weishike.net/uimage/a8/c9/45/da/a8c945da508e22bb2243e2f3772e69a0.jpg?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70"}
         * password :
         * link : {"password_create":{"name":"设置密码","url":"https://www.baidu.com/"},"password_change":{"name":"修改密码","url":"https://www.baidu.com/"},"phone_bind":{"name":"绑定手机号","url":"https://www.baidu.com/"},"phone_change":{"name":"修改手机号","url":"https://www.baidu.com/"}}
         */

        private String username;
        private String avatar;
        private String phone;
        private WeixinBean weixin;
        private String password;
        private LinkBean link;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public WeixinBean getWeixin() {
            return weixin;
        }

        public void setWeixin(WeixinBean weixin) {
            this.weixin = weixin;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public LinkBean getLink() {
            return link;
        }

        public void setLink(LinkBean link) {
            this.link = link;
        }

        public static class WeixinBean implements Serializable{
            /**
             * nickname : 花心萝卜腿
             * avatar : http://shoptest.weishike.net/uimage/a8/c9/45/da/a8c945da508e22bb2243e2f3772e69a0.jpg?x-oss-process=image/resize,m_fill,h_112,w_112,r_56/format,jpg/interlace,1/quality,Q_70
             */

            private String nickname;
            private String avatar;
            private String time;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }

        public static class LinkBean {
            /**
             * password_create : {"name":"设置密码","url":"https://www.baidu.com/"}
             * password_change : {"name":"修改密码","url":"https://www.baidu.com/"}
             * phone_bind : {"name":"绑定手机号","url":"https://www.baidu.com/"}
             * phone_change : {"name":"修改手机号","url":"https://www.baidu.com/"}
             */

            private PasswordCreateBean password_create;
            private PasswordChangeBean password_change;
            private PhoneBindBean phone_bind;
            private PhoneChangeBean phone_change;

            public PasswordCreateBean getPassword_create() {
                return password_create;
            }

            public void setPassword_create(PasswordCreateBean password_create) {
                this.password_create = password_create;
            }

            public PasswordChangeBean getPassword_change() {
                return password_change;
            }

            public void setPassword_change(PasswordChangeBean password_change) {
                this.password_change = password_change;
            }

            public PhoneBindBean getPhone_bind() {
                return phone_bind;
            }

            public void setPhone_bind(PhoneBindBean phone_bind) {
                this.phone_bind = phone_bind;
            }

            public PhoneChangeBean getPhone_change() {
                return phone_change;
            }

            public void setPhone_change(PhoneChangeBean phone_change) {
                this.phone_change = phone_change;
            }

            public static class PasswordCreateBean {
                /**
                 * name : 设置密码
                 * url : https://www.baidu.com/
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class PasswordChangeBean {
                /**
                 * name : 修改密码
                 * url : https://www.baidu.com/
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class PhoneBindBean {
                /**
                 * name : 绑定手机号
                 * url : https://www.baidu.com/
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class PhoneChangeBean {
                /**
                 * name : 修改手机号
                 * url : https://www.baidu.com/
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
