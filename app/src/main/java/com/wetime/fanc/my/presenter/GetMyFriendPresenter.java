package com.wetime.fanc.my.presenter;

import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.my.bean.AttentionBean;
import com.wetime.fanc.my.bean.MyFriendsBaseBean;
import com.wetime.fanc.my.iviews.IGetMyFriendsView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by Administrator on 2018/3/28.
 */

public class GetMyFriendPresenter {
    private IGetMyFriendsView iGetMyFriendsView;

    public GetMyFriendPresenter(IGetMyFriendsView iGetMyFriendsView) {
        this.iGetMyFriendsView = iGetMyFriendsView;
    }

    public void getFriends(int page) {
        OkHttpUtils.post().url(Const.GTT_MY_FRIENDS)
                .addParams("token", "f_p0tkc2_64rOSV_R5G03Snt2VXzRY6Q")
                .addParams("type", iGetMyFriendsView.onGetType())
                .addParams("pn", String.valueOf(page))
                .build()
                .execute(new DataStringCallback(iGetMyFriendsView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        MyFriendsBaseBean myFriendsBaseBean = GsonUtils.getGsonInstance().fromJson(s, MyFriendsBaseBean.class);
                        iGetMyFriendsView.onGetUserFriend(myFriendsBaseBean);
                    }
                });
    }

    public void setAttention(String follow, String following_id) {
        OkHttpUtils.post().url(Const.ATTENTION)
                .addParams("token", "YlUhGvfphqft7DmirjYxbYVu_gqLuHLc")
                .addParams("follow", follow)
                .addParams("following_id", following_id)
                .build()
                .execute(new DataStringCallback(iGetMyFriendsView, false,false,false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        AttentionBean myFriendsBaseBean = GsonUtils.getGsonInstance().fromJson(s, AttentionBean.class);
                        iGetMyFriendsView.onAttention(myFriendsBaseBean);
                    }
                });
    }

}
