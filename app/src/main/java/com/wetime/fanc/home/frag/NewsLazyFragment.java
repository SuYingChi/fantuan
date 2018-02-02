package com.wetime.fanc.home.frag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.NewsPagerAdapter;
import com.wetime.fanc.main.frag.BaseLazyFragment;

import java.util.ArrayList;


public class NewsLazyFragment extends BaseLazyFragment {

    private ArrayList<Fragment> mFragments = new ArrayList<>();


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void initData() {
        String[] mTitles = getResources().getStringArray(R.array.newstype);
        for (int i = 0; i < mTitles.length; i++) {
            NewsTypeLazyFragment myFragment = new NewsTypeLazyFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", i + "");
            myFragment.setArguments(bundle);
            mFragments.add(myFragment);
        }
        ViewPager vp = mRootView.findViewById(R.id.vp);
        NewsPagerAdapter mAdapter = new NewsPagerAdapter(getChildFragmentManager(), mFragments, mTitles);
        vp.setAdapter(mAdapter);

        SlidingTabLayout slidingTabLayout = mRootView.findViewById(R.id.tablayout);

        slidingTabLayout.setViewPager(vp, mTitles);
    }
}
