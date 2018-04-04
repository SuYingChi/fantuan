package com.wetime.fanc.circle.frag;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.PublishActActivity;
import com.wetime.fanc.home.adapter.NormalTitlePagerAdapter;
import com.wetime.fanc.home.event.RefreshRedNumEvent;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.login.event.LogoutEvent;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.my.frag.MyCollectNewsLazyFragment;
import com.wetime.fanc.my.frag.MyCollectShopLazyFragment;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;


public class CircleHomeFragment extends BaseLazyFragment {
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.iv_edit)
    ImageView ivEdit;
    @BindView(R.id.tablayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private QBadgeView qBadgeMsg;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_circle_home;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        qBadgeMsg = new QBadgeView(getContext());
        qBadgeMsg.setBadgeTextSize(11, true);
        qBadgeMsg.bindTarget(ivMsg);
        String[] mTitles = {"兴趣圈子", "关注"};


        CircleFragment circle = new CircleFragment();
        mFragments.add(circle);

        FocusFragment focus = new FocusFragment();
        mFragments.add(focus);


        NormalTitlePagerAdapter mAdapter = new NormalTitlePagerAdapter(getChildFragmentManager(), mFragments, mTitles);
        vp.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(vp, mTitles);

    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogoutEvent event) {
        qBadgeMsg.setBadgeNumber(0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshRedNumEvent event) {
        qBadgeMsg.setBadgeNumber(event.getNum());
    }

    @OnClick({R.id.iv_msg, R.id.iv_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_msg:
                if (!TextUtils.isEmpty(spu.getToken()))
                    Tools.goWeb(getContext(), Const.MSG_URL);
                else {
                    Tools.toastInBottom(getContext(), "请先登录");
                    Intent goLogin = new Intent(getContext(), LoginActivity.class);
                    startActivity(goLogin);
                }
                break;
            case R.id.iv_edit:
                if (spu.getToken().equals("")) {
                    Intent gologin = new Intent(getContext(), LoginActivity.class);
                    startActivity(gologin);
                } else {
                    Intent goPublish = new Intent(getContext(), PublishActActivity.class);
                    startActivity(goPublish);
                }
                break;
        }
    }
}
