package com.wetime.fanc.home.frag;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.AllCircleActivity;
import com.wetime.fanc.home.adapter.NormalTitlePagerAdapter;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.weex.WeexURLActivity;

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
            AllCircleActivity.goAllCircleAct(getContext());
        });
    }

    @Override
    protected void initData() {

    }


}
