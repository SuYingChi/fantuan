package com.wetime.fanc.home.frag;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.flyco.tablayout.SlidingTabLayout;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.LongTextEditActivity;
import com.wetime.fanc.circle.act.PublishActActivity;
import com.wetime.fanc.home.adapter.NormalTitlePagerAdapter;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;


public class HomePageLazyFragmentV2 extends BaseLazyFragment {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ViewPager vp;
    private NormalTitlePagerAdapter mAdapter;
    private SlidingTabLayout slidingTabLayout;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home_v2;
    }

    @Override
    protected void initView() {
        vp = mRootView.findViewById(R.id.vp);
        String[] mTitles = {"关注", "推荐", "圈子"};
        mFragments.add(new HomeFocusLazyFragmentV2());
        mFragments.add(new HomeRecomentLazyFragmentV2());
        mFragments.add(new HomeCircleLazyFragmentV2());


        mAdapter = new NormalTitlePagerAdapter(getChildFragmentManager(), mFragments, mTitles);
        vp.setAdapter(mAdapter);

        slidingTabLayout = mRootView.findViewById(R.id.tablayout);
        slidingTabLayout.setViewPager(vp);
        mRootView.findViewById(R.id.iv_edit).setOnClickListener(view -> {

            View popupView = getLayoutInflater().inflate(R.layout.layout_popupwindow_home, null);
            PopupWindow window = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            popupView.findViewById(R.id.pop_duan).setOnClickListener(v -> {
                window.dismiss();
                PublishActActivity.goPublishActAct(getContext(), "");
            });
            popupView.findViewById(R.id.pop_chang).setOnClickListener(v -> {
                window.dismiss();
                LongTextEditActivity.goLongTextEditAct(getContext(), "");
            });

            window.setAnimationStyle(R.style.popup_window_anim);
            window.setFocusable(true);
            window.setOutsideTouchable(true);
            window.update();
            if (getContext() != null)
                window.showAsDropDown(mRootView.findViewById(R.id.iv_edit),
                        0 - Tools.dip2px(getContext(), 0), 0);
        });
    }

    @Override
    protected void initData() {

    }


}
