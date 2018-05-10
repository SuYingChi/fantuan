package com.wetime.fanc.home.frag;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.NormalTitlePagerAdapter;
import com.wetime.fanc.main.frag.BaseLazyFragment;

import java.util.ArrayList;


public class HomeMsgLazyFragmentV2 extends BaseLazyFragment {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ViewPager vp;
    private NormalTitlePagerAdapter mAdapter;
    private SlidingTabLayout slidingTabLayout;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_message_v2;
    }

    @Override
    protected void initView() {
        vp = mRootView.findViewById(R.id.vp);
        String[] mTitles = {"消息", "通知"};
        mFragments.add(new HomeMessageFragmentV2());
        mFragments.add(new HomeNotificationFragmentV2());


        mAdapter = new NormalTitlePagerAdapter(getChildFragmentManager(), mFragments, mTitles);
        vp.setAdapter(mAdapter);

        slidingTabLayout = mRootView.findViewById(R.id.tablayout);
        slidingTabLayout.setViewPager(vp);
    }

    @Override
    protected void initData() {

    }


}
