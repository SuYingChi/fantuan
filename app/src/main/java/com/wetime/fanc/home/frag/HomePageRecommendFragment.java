package com.wetime.fanc.home.frag;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.HomePageRecommendAdapter;
import com.wetime.fanc.home.bean.HomePageRecommendBean;
import com.wetime.fanc.home.event.ReFreshCircleEvent;
import com.wetime.fanc.home.iviews.IHomePageRecommendView;
import com.wetime.fanc.home.presenter.HomePageRecommendFragmentPresenter;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.SimpleCatchKey;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by admin on 2018/5/3.
 */

public class HomePageRecommendFragment extends BaseLazyFragment implements OnRefreshLoadMoreListener ,IHomePageRecommendView {
    @BindView(R.id.refreshLayout_recommend)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rcl_recommend)
    RecyclerView recyclerView;
    @BindView(R.id.rl_empty)
    RelativeLayout emptey;
    private int page=1;
    private HomePageRecommendAdapter adapter;
    private AutoLoadMoreAdapter autoLoadMoreAdapter;
    private List<HomePageRecommendBean.DataBean.ListBean> list= new ArrayList<HomePageRecommendBean.DataBean.ListBean>();

    private HomePageRecommendFragmentPresenter homePageRecommendFragmentPresenter;



    @Override
    protected void refresh() {
        onRefresh(refreshLayout);
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home_page_recommend;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadMore(false);
        if (!TextUtils.isEmpty(spu.getValue(SimpleCatchKey.catch_recommend))) {
            onGetRecommend(GsonUtils.getGsonInstance().fromJson(
                    spu.getValue(SimpleCatchKey.catch_recommend), HomePageRecommendBean.class));
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        homePageRecommendFragmentPresenter.getRecommend();
    }

    @Override
    protected void initData() {
        homePageRecommendFragmentPresenter =  new HomePageRecommendFragmentPresenter(this);
        homePageRecommendFragmentPresenter.getRecommend();
    }

    @Override
    public void onGetRecommend(HomePageRecommendBean homePageRecommendBean) {
        refreshLayout.finishRefresh(1000);
        if (adapter == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new HomePageRecommendAdapter(homePageRecommendBean.getData().getBanner(), homePageRecommendBean.getData().getCircles(), list, getActivity());
            autoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
            autoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
                @Override
                public void onRetry() {

                }

                @Override
                public void onLoadMore() {
                    page++;
                    homePageRecommendFragmentPresenter.getRecommend();
                }
            });
            recyclerView.setAdapter(autoLoadMoreAdapter);
        }

//
        adapter.setCirccles(homePageRecommendBean.getData().getCircles());
        adapter.setBanner(homePageRecommendBean.getData().getBanner());


        if (page == 1) {
            list.clear();
            spu.setValue(SimpleCatchKey.catch_recommend, GsonUtils.getGsonInstance().toJson(homePageRecommendBean));
        }
        if (homePageRecommendBean.getData().getPaging().isIs_end()) {
            autoLoadMoreAdapter.disable();
        }
        list.addAll(homePageRecommendBean.getData().getList());
        autoLoadMoreAdapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();

        emptey.setVisibility(View.GONE);
        if (page > 1) {
            autoLoadMoreAdapter.finishLoading();
        }
    }

    @Override
    public int getPage() {
        return page;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReFreshCircleEvent event) {
        if (mIsVisible) {
            recyclerView.scrollToPosition(0);
            refreshLayout.autoRefresh();
        }
    }
}
