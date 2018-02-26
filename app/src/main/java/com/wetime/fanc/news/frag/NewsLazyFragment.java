package com.wetime.fanc.news.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.reflect.TypeToken;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.NewsPagerAdapter;
import com.wetime.fanc.home.event.ReFreshNewsEvent;
import com.wetime.fanc.home.event.ReFreshNewsTypeEvent;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.news.act.ChannelActivity;
import com.wetime.fanc.news.bean.ChannelBean;
import com.wetime.fanc.news.event.ChannelChangeEvent;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewsLazyFragment extends BaseLazyFragment {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<ChannelBean> mChannels = new ArrayList<>();
    private ViewPager vp;
    private NewsPagerAdapter mAdapter;
    private SlidingTabLayout slidingTabLayout;

    private Map<String, Fragment> map = new HashMap<>();

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
        mRootView.findViewById(R.id.iv_edit).setOnClickListener(view -> {
            Log.e("zk", "");
            Tools.goActivity(getContext(), ChannelActivity.class);
        });

    }

    @Override
    protected void initData() {
        if (TextUtils.isEmpty(spu.getValue("mychannal"))) {
            String[] mTitles = getResources().getStringArray(R.array.newstype_default);
            String[] mIndex = getResources().getStringArray(R.array.newstypeindex_default);
            for (int i = 0; i < mTitles.length; i++) {
                NewsTypeLazyFragment myFragment = new NewsTypeLazyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("type", mIndex[i]);
                myFragment.setArguments(bundle);
                mFragments.add(myFragment);

                map.put(mIndex[i], myFragment);

                ChannelBean entity = new ChannelBean();
                entity.setName(mTitles[i]);
                entity.setId(mIndex[i]);
                mChannels.add(entity);

            }
        } else {
            mChannels.addAll(GsonUtils.getGsonInstance().fromJson(spu.getValue("mychannal"),
                    new TypeToken<List<ChannelBean>>() {
                    }.getType()));

            ChannelBean d1 = new ChannelBean("0", "推荐");
            ChannelBean d2 = new ChannelBean("3", "海南");
            mChannels.add(0, d2);
            mChannels.add(0, d1);
            for (ChannelBean bean : mChannels) {
                NewsTypeLazyFragment myFragment = new NewsTypeLazyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("type", bean.getId());
                myFragment.setArguments(bundle);
                mFragments.add(myFragment);
                map.put(bean.getId(), myFragment);
            }
        }


        vp = mRootView.findViewById(R.id.vp);
        mAdapter = new NewsPagerAdapter(getChildFragmentManager(), mFragments, mChannels);
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(20);

        slidingTabLayout = mRootView.findViewById(R.id.tablayout);
        slidingTabLayout.setViewPager(vp);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReFreshNewsEvent event) {
        String[] mIndex = getResources().getStringArray(R.array.newstypeindex);
        EventBus.getDefault().post(new ReFreshNewsTypeEvent(mIndex[vp.getCurrentItem()]));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ChannelChangeEvent event) {
        String currentId = mChannels.get(vp.getCurrentItem()).getId();
        int currentIndex = 0;

        mChannels.clear();
        mFragments = new ArrayList<>();
//        mFragments.clear();

        mChannels.addAll(GsonUtils.getGsonInstance().fromJson(spu.getValue("mychannal"),
                new TypeToken<List<ChannelBean>>() {
                }.getType()));

        ChannelBean d1 = new ChannelBean("0", "推荐");
        ChannelBean d2 = new ChannelBean("3", "海南");
        mChannels.add(0, d2);
        mChannels.add(0, d1);

//        for (ChannelBean bean : mChannels) {
        for (int i = 0; i < mChannels.size(); i++) {
            ChannelBean bean = mChannels.get(i);

            NewsTypeLazyFragment myFragment;
            if (map.containsKey(bean.getId())) {
                myFragment = (NewsTypeLazyFragment) map.get(bean.getId());
            } else {
                myFragment = new NewsTypeLazyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("type", bean.getId());
                myFragment.setArguments(bundle);
            }
            mFragments.add(myFragment);

//
//            NewsTypeLazyFragment myFragment = new NewsTypeLazyFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("type", bean.getId());
//            myFragment.setArguments(bundle);
//            mFragments.add(myFragment);
            if (TextUtils.equals(bean.getId(), currentId)) {
                currentIndex = i;
            }

        }
//        vp.removeAllViews();

        mAdapter = new NewsPagerAdapter(getChildFragmentManager(), mFragments, mChannels);
        vp.setAdapter(mAdapter);
//        vp.setOffscreenPageLimit(mFragments.size());
//        mAdapter.notifyDataSetChanged();

        mAdapter.recreateItems(mFragments, mChannels);
        vp.setCurrentItem(currentIndex);
        slidingTabLayout.setCurrentTab(currentIndex);
        slidingTabLayout.notifyDataSetChanged();


//        SlidingTabLayout slidingTabLayout = mRootView.findViewById(R.id.tablayout);
//        slidingTabLayout.setViewPager(vp);


//        slidingTabLayout.setCurrentTab(0);


    }

}
