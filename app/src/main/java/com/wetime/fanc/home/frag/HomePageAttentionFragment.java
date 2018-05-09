package com.wetime.fanc.home.frag;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.HomePageAttentionAdapter;
import com.wetime.fanc.home.bean.HomePageAttentionBean;
import com.wetime.fanc.home.event.ReFreshCircleEvent;
import com.wetime.fanc.home.iviews.IHomePageAttentionView;
import com.wetime.fanc.home.presenter.HomePageAttentionFragmentPresenter;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

import butterknife.BindView;

/**
 * Created by admin on 2018/5/3.
 */

public class HomePageAttentionFragment extends BaseLazyFragment implements IHomePageAttentionView, OnRefreshListener {

    @BindView(R.id.rcl_home_attention)
    RecyclerView rcl;
    @BindView(R.id.refreshLayout_attention)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    @BindView(R.id.homepage_root)
    RelativeLayout homepage_root;
    HomePageAttentionFragmentPresenter homePageAttentionFragmentPresenter;
    List<HomePageAttentionBean.DataBean.ListBean> list = new ArrayList<HomePageAttentionBean.DataBean.ListBean>();
    HomePageAttentionAdapter adapter;
    int page = 1;
    AutoLoadMoreAdapter autoLoadMoreAdapter;
    Handler handler = new Handler();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home_page_attention;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
//        rclHome.setFocusableInTouchMode(false);
        Tools.showEmptyLoading(homepage_root);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadMore(false);
        homePageAttentionFragmentPresenter = new HomePageAttentionFragmentPresenter(this);
    }

    @Override
    protected void initData() {
        page = 1;
        homePageAttentionFragmentPresenter.getAttentionPage();
        Log.d("suyingchi", "initData: ");
    }


    @Override
    protected void onInvisible() {
        super.onInvisible();
        Tools.hideEmptyLoading(homepage_root);
        if(refreshLayout!=null){
            refreshLayout.finishRefresh();
        }
        Log.d("suyingchi", "onInvisible: ");

    }

    @Override
    public void onGetAttention(HomePageAttentionBean bean) {
        refreshLayout.finishRefresh(1000);
        Tools.hideEmptyLoading(homepage_root);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Tools.hideEmptyLoading(homepage_root);
            }
        },1000);
        if (adapter == null) {
            rcl.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new HomePageAttentionAdapter(list, getActivity());
            autoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
            autoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
                @Override
                public void onRetry() {

                }

                @Override
                public void onLoadMore() {
                    page++;
                    homePageAttentionFragmentPresenter.getAttentionPage();
                }
            });
            rcl.setAdapter(autoLoadMoreAdapter);
        }


        if (page == 1) {
            list.clear();
        } else {
            autoLoadMoreAdapter.finishLoading();
        }
        list.addAll(bean.getData().getList());
        if (bean.getData().getPaging().isIs_end()) {
            autoLoadMoreAdapter.disable();
        }
        if (list.size() > 0) {
            rlEmpty.setVisibility(View.GONE);
        } else {
            rlEmpty.setVisibility(View.VISIBLE);
        }
        autoLoadMoreAdapter.notifyDataSetChanged();
    }

    @Override
    public String getPage() {
        return String.valueOf(page);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        page = 1;
        homePageAttentionFragmentPresenter.getAttentionPage();
    }

    @Override
    public void onNetError() {
        super.onNetError();
        refreshLayout.finishLoadMore(1000);
        Tools.hideEmptyLoading(homepage_root);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReFreshCircleEvent event) {
        if (mIsVisible) {
            rcl.scrollToPosition(0);
            refreshLayout.autoRefresh();
        }
    }

    @Override
    public void onError(String s) {
        super.onError(s);
        refreshLayout.finishRefresh();
        rlEmpty.setVisibility(View.VISIBLE);
    }
}
