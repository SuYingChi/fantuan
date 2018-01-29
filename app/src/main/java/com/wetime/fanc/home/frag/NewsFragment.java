package com.wetime.fanc.home.frag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.NewsPagerAdapter;
import com.wetime.fanc.main.frag.BaseFragment;

import java.util.ArrayList;


public class NewsFragment extends BaseFragment {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "推荐", "美食", "活动"
            , "海口", "体育", "段子", "娱乐"
    };
    private NewsPagerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news, null);
        for (int i = 0; i < mTitles.length; i++) {
            NewsTypeFragment myFragment = new NewsTypeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", i + "");
            myFragment.setArguments(bundle);
            mFragments.add(myFragment);
        }
        ViewPager vp = v.findViewById(R.id.vp);
        mAdapter = new NewsPagerAdapter(getChildFragmentManager(), mFragments, mTitles);
        vp.setAdapter(mAdapter);

        SlidingTabLayout slidingTabLayout = v.findViewById(R.id.tablayout);

        slidingTabLayout.setViewPager(vp, mTitles);
        return v;
    }
}
