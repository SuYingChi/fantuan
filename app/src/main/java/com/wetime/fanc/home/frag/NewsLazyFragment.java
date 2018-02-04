package com.wetime.fanc.home.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.NewsPagerAdapter;
import com.wetime.fanc.home.event.ReFreshNewsEvent;
import com.wetime.fanc.home.event.ReFreshNewsTypeEvent;
import com.wetime.fanc.main.frag.BaseLazyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


public class NewsLazyFragment extends BaseLazyFragment {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ViewPager vp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String[] mTitles = getResources().getStringArray(R.array.newstype);
        String[] mIndex = getResources().getStringArray(R.array.newstypeindex);
        for (int i = 0; i < mTitles.length; i++) {
            NewsTypeLazyFragment myFragment = new NewsTypeLazyFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", mIndex[i]);
            myFragment.setArguments(bundle);
            mFragments.add(myFragment);
        }



        vp = mRootView.findViewById(R.id.vp);
        NewsPagerAdapter mAdapter = new NewsPagerAdapter(getChildFragmentManager(), mFragments, mTitles);
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(7);

        SlidingTabLayout slidingTabLayout = mRootView.findViewById(R.id.tablayout);

        slidingTabLayout.setViewPager(vp, mTitles);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReFreshNewsEvent event) {
        String[] mIndex = getResources().getStringArray(R.array.newstypeindex);
        EventBus.getDefault().post(new ReFreshNewsTypeEvent(mIndex[vp.getCurrentItem()]));
    }

}
