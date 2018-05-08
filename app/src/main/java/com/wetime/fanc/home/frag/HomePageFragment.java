package com.wetime.fanc.home.frag;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.NormalTitlePagerAdapter;
import com.wetime.fanc.main.frag.BaseLazyFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by admin on 2018/5/3.
 */

public class HomePageFragment extends BaseLazyFragment {

    @BindView(R.id.first_vp)
    ViewPager vp;
    @BindView(R.id.firt_tablayout)
    SlidingTabLayout slidingTabLayout;
    private ArrayList<Fragment> list_fragment = new ArrayList<Fragment>();
    private HomePageAttentionFragment f0;
    private HomePageRecommendFragment f1;
    private HomePageCircleFragment f2;
    private String[] mTitles = {"关注", "推荐","圈子"};

    protected void initView() {
        f0 = new HomePageAttentionFragment();
        list_fragment.add(f0);

        f1 = new HomePageRecommendFragment();
        list_fragment.add(f1);

        f2 = new HomePageCircleFragment();
        list_fragment.add(f2 );

        NormalTitlePagerAdapter mAdapter = new NormalTitlePagerAdapter(getChildFragmentManager(), list_fragment, mTitles);
        vp.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(vp, mTitles);
        slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int homePageCitem) {
                refreshChildFragment(homePageCitem);
                spu.setValue("HomePageCitem",String.valueOf(homePageCitem));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
       if(TextUtils.isEmpty(spu.getValue("HomePageCitem"))){
           slidingTabLayout.setCurrentTab(0);
       }else {
           slidingTabLayout.setCurrentTab(Integer.valueOf(spu.getValue("HomePageCitem")));
        }

    }

    private void refreshChildFragment(int homePageCitem) {
        if(homePageCitem==0){
            f0.refresh();
        }else if(homePageCitem==1){
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
        spu.setValue("HomePageCitem",String.valueOf(vp.getCurrentItem()));
        System.gc();
        super.onDestroy();

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home_page_layout;
    }
}
