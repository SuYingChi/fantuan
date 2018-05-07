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
import com.wetime.fanc.home.adapter.RecommendAdapter;
import com.wetime.fanc.home.bean.HeadRecommendBean;
import com.wetime.fanc.home.event.ReFreshCircleEvent;
import com.wetime.fanc.home.iviews.IRecommendView;
import com.wetime.fanc.home.presenter.RecommendPresenter;
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

public class RecommendFragment extends BaseLazyFragment implements OnRefreshLoadMoreListener ,IRecommendView{
    @BindView(R.id.refreshLayout_recommend)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rcl_recommend)
    RecyclerView recyclerView;
    @BindView(R.id.rl_empty)
    RelativeLayout emptey;
    private int page=1;
    private RecommendAdapter adapter;
    private AutoLoadMoreAdapter autoLoadMoreAdapter;
    private List<HeadRecommendBean.DataBean.ListBean> list= new ArrayList<HeadRecommendBean.DataBean.ListBean>();

    private RecommendPresenter recommendPresenter;

    @Override
    protected void refresh() {
        super.refresh();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_first_recommend;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadMore(false);
        if (!TextUtils.isEmpty(spu.getValue(SimpleCatchKey.catch_recommend))) {
            onGetRecommend(GsonUtils.getGsonInstance().fromJson(
                    spu.getValue(SimpleCatchKey.catch_recommend), HeadRecommendBean.class));
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    protected void initData() {
        recommendPresenter =  new RecommendPresenter(this);
        recommendPresenter.getRecommend();
    }

    @Override
    public void onGetRecommend(HeadRecommendBean headRecommendBean) {
        refreshLayout.finishRefresh(1000);
        if (adapter == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new RecommendAdapter(headRecommendBean.getData().getBanner(), headRecommendBean.getData().getCircles(), list, getActivity());
            autoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
            autoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
                @Override
                public void onRetry() {

                }

                @Override
                public void onLoadMore() {
                    page++;
                    recommendPresenter.getRecommend();
                }
            });
            recyclerView.setAdapter(autoLoadMoreAdapter);
        }

//
        adapter.setCirccles(headRecommendBean.getData().getCircles());
        adapter.setBanner(headRecommendBean.getData().getBanner());


        if (page == 1) {
            list.clear();
            spu.setValue(SimpleCatchKey.catch_recommend, GsonUtils.getGsonInstance().toJson(headRecommendBean));
        }
        if (headRecommendBean.getData().getPaging().isIs_end()) {
            autoLoadMoreAdapter.disable();
        }
        list.addAll(headRecommendBean.getData().getList());
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
