package com.wetime.fanc.home.frag;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.CustomViewPager;
import com.wetime.fanc.home.adapter.FirstFragmentPagerAdapter;
import com.wetime.fanc.home.adapter.HomeFragmentPagerAdapter;
import com.wetime.fanc.home.adapter.NormalTitlePagerAdapter;
import com.wetime.fanc.home.bean.TabEntity;
import com.wetime.fanc.main.frag.BaseFragment;
import com.wetime.fanc.main.frag.BaseLazyFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/5/3.
 */

public class FirstFragment extends BaseLazyFragment {

    @BindView(R.id.first_vp)
    ViewPager vp;
    @BindView(R.id.firt_tablayout)
    SlidingTabLayout slidingTabLayout;
    private ArrayList<Fragment> list_fragment = new ArrayList<Fragment>();
    private AttentionFragment f0;
    private RecommendFragment f1;
    private CircleFragment f2;
    private String[] mTitles = {"关注", "推荐","圈子"};




    protected void initView() {
/*        f0 = new AttentionFragment();
//        f1 = new SortActivity();
        f1 = new RecommendFragment();
        f2 = new CircleFragment();

        list_fragment.add(f0);
        list_fragment.add(f1);
        list_fragment.add(f2);

        vp.setAdapter(new FirstFragmentPagerAdapter(getActivity().getSupportFragmentManager(), list_fragment));
        vp.setCurrentItem(spu.getValue("FirstCitem")==""?0:Integer.valueOf(spu.getValue("FirstCitem")));
//        vp.setOffscreenPageLimit(3);
        vp.setScanScroll(true);
        vp.setPageTransformer(true, null);
        for (String mTitle : mTitles) {
            mTabEntities.add(new TabEntity(mTitle));
        }
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                sortPos = position;
                refresh(sortPos);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        commonTabLayout.setCurrentTab(spu.getValue("FirstCitem")==""?0:Integer.valueOf(spu.getValue("FirstCitem")));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

        AttentionFragment attentFrag = new AttentionFragment();
        list_fragment.add(attentFrag);

        RecommendFragment recommendFragment = new RecommendFragment();
        list_fragment.add(recommendFragment);

        CircleFragment circleFragment = new CircleFragment();
        list_fragment.add(circleFragment);

        NormalTitlePagerAdapter mAdapter = new NormalTitlePagerAdapter(getChildFragmentManager(), list_fragment, mTitles);
        vp.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(vp, mTitles);
        slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
              refreshChildFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void refreshChildFragment(int sortPos) {
        if(sortPos==0){
            f0.refresh();
        }else if(sortPos==1){
            f1.refresh();
        }else {
            f2.refresh();
        }
    }


    @Override
    public void onDestroy() {
        list_fragment.clear();
        f0 = null;
        f1 = null;
        f2 = null;
        spu.setValue("FirstCitem",String.valueOf(vp.getCurrentItem()));
        System.gc();
        super.onDestroy();

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_first;
    }
}
