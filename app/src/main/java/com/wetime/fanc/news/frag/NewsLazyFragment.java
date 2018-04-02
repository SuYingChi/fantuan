package com.wetime.fanc.news.frag;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.reflect.TypeToken;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.NewsPagerAdapter;
import com.wetime.fanc.home.event.ReFreshNewsEvent;
import com.wetime.fanc.home.event.ReFreshNewsTypeEvent;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.news.act.ChannelActivity;
import com.wetime.fanc.news.bean.AllChannelListBean;
import com.wetime.fanc.news.bean.ChannelBean;
import com.wetime.fanc.news.event.ChannelChangeEvent;
import com.wetime.fanc.news.iviews.IGetAllChannelView;
import com.wetime.fanc.news.iviews.IGetMyChannelView;
import com.wetime.fanc.news.presenter.GetAllChannelPresenter;
import com.wetime.fanc.news.presenter.GetMyChannelPresenter;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewsLazyFragment extends BaseLazyFragment implements IGetAllChannelView, IGetMyChannelView {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<ChannelBean> mChannels = new ArrayList<>();
    private ViewPager vp;
    private NewsPagerAdapter mAdapter;
    private SlidingTabLayout slidingTabLayout;

    private Map<String, Fragment> map = new HashMap<>();
    private int currentIndex = 0;
    private String currentId;

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
            Tools.goActivity(getContext(), ChannelActivity.class);
        });
        vp = mRootView.findViewById(R.id.vp);

    }

    @Override
    protected void initData() {
        //每次进来都有刷新一个所有频道，有可能会更新
        GetAllChannelPresenter getAllChannelPresenter = new GetAllChannelPresenter(this);
        getAllChannelPresenter.getAllChannelResult();


        if (!TextUtils.isEmpty(spu.getValue(ChannelActivity.LOCALCHANNAL))) {
            initFromLocal();
        } else {
            //已登录状态 拿
            if (!TextUtils.isEmpty(spu.getToken())) {
                GetMyChannelPresenter getMyChannelPresenter = new GetMyChannelPresenter(this);
                getMyChannelPresenter.getMyChannelResult();
            }
        }
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(ReFreshNewsEvent event) {
//        String[] mIndex = getResources().getStringArray(R.array.newstypeindex);
//        EventBus.getDefault().post(new ReFreshNewsTypeEvent(mIndex[vp.getCurrentItem()]));
//    }

    private void initFromLocal() {
        mChannels.clear();
        mChannels.addAll(GsonUtils.getGsonInstance().fromJson(spu.getValue(ChannelActivity.LOCALCHANNAL),
                new TypeToken<List<ChannelBean>>() {
                }.getType()));
        initTab();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ChannelChangeEvent event) {
        currentIndex = vp.getCurrentItem();
        currentId = mChannels.get(currentIndex).getId();
        initFromLocal();
    }

    @Override
    public void onGetAllChannel(AllChannelListBean bean) {
        spu.setValue(ChannelActivity.ALLCHANNAL, GsonUtils.getGsonInstance().toJson(bean.getData()));
        mChannels.clear();

        for (ChannelBean c : bean.getData()) {
            if (TextUtils.equals(c.getIs_default(), "1"))
                mChannels.add(c);
        }
        //没有登录 并且 本地没有数据 存到本地
        if (TextUtils.isEmpty(spu.getToken()) && TextUtils.isEmpty(spu.getValue(ChannelActivity.LOCALCHANNAL))) {
            spu.setValue(ChannelActivity.LOCALCHANNAL, GsonUtils.getGsonInstance().toJson(mChannels));
            initTab();
        }

    }

    @Override
    public void onGetMyChannel(AllChannelListBean bean) {
        if (bean.getData().size() == 0) {
            Tools.toastInBottom(getContext(), "上传本地数据");
            //上传本地数据

        } else {
            mChannels.clear();
            mChannels.addAll(bean.getData());
            // 存到本地
            spu.setValue(ChannelActivity.LOCALCHANNAL, GsonUtils.getGsonInstance().toJson(mChannels));

            initTab();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEvent event) {
        currentId = mChannels.get(currentIndex).getId();
        GetMyChannelPresenter getMyChannelPresenter = new GetMyChannelPresenter(this);
        getMyChannelPresenter.getMyChannelResult();
    }

    private void initTab() {
//        关注-1
//        推荐0
//                海南3
        /**
         * 架构师要求写到本地 不写活tmd
         */
        ChannelBean c0 = new ChannelBean("3", "海南");
        mChannels.add(0, c0);
        ChannelBean c1 = new ChannelBean("0", "推荐");
        mChannels.add(0, c1);
        ChannelBean c2 = new ChannelBean("-1", "关注");
        mChannels.add(0, c2);

        boolean isRefresh = mFragments.size() > 0;

        mFragments.clear();
        for (ChannelBean b : mChannels) {
            NewsTypeLazyFragment myFragment = new NewsTypeLazyFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", b.getId());
            myFragment.setArguments(bundle);
            mFragments.add(myFragment);
        }


        if (isRefresh) {
            currentIndex = 0;
            for (int i = 0; i < mChannels.size(); i++) {
                if (currentId.equals(mChannels.get(i).getId()))
                    currentIndex = i;
            }

            if (currentIndex == 0) {
                new Handler().postDelayed(() -> EventBus.getDefault().post(new ReFreshNewsTypeEvent("0")), 500);
            }
            mAdapter.recreateItems(mFragments, mChannels);
            vp.setCurrentItem(currentIndex);
            slidingTabLayout.setCurrentTab(currentIndex);
            slidingTabLayout.notifyDataSetChanged();
        } else {

            mAdapter = new NewsPagerAdapter(getChildFragmentManager(), mFragments, mChannels);
            vp.setAdapter(mAdapter);
            vp.setOffscreenPageLimit(20);

            slidingTabLayout = mRootView.findViewById(R.id.tablayout);
            slidingTabLayout.setViewPager(vp);
        }

    }


}
