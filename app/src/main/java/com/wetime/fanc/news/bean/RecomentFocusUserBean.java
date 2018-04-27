package com.wetime.fanc.news.bean;

public class RecomentFocusUserBean {
    /**
     * uid : 261
     * intro :
     * username : 灌篮高手
     * avatar : https://staticcdntest.fantuanlife.com/image/fant/default_avatar.png
     * is_follow : false
     */
    private String uid;
    private String intro;
    private String username;
    private String avatar;
    private boolean is_follow;
    private boolean is_owner;

    public boolean isIs_owner() {
        return is_owner;
    }

    public void setIs_owner(boolean is_owner) {
        this.is_owner = is_owner;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public boolean isIs_follow() {
        return is_follow;
    }

    public void setIs_follow(boolean is_follow) {
        this.is_follow = is_follow;
    }
}
