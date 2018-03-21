package com.wetime.fanc.my.frag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.my.bean.MyNewsListBean;
import com.wetime.fanc.my.iviews.IGetMyNewsView;
import com.wetime.fanc.my.presenter.GetMyNewsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MyCollectNewsLazyFragment extends BaseLazyFragment
        implements OnLoadMoreListener, OnRefreshListener, IGetMyNewsView {
    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;


    private int page = 1;
    private GetMyNewsPresenter getMyNewsPresenter;
    private List<HomeItemBean> list;
    private HomeItemAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mynews;
    }

    @Override
    protected void initView() {
        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setOnRefreshListener(this);

    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    protected void initData() {
        rclCircle.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new HomeItemAdapter(list, getActivity());
        rclCircle.setAdapter(adapter);

        getMyNewsPresenter = new GetMyNewsPresenter(this);
        getMyNewsPresenter.getMyNews();

    }

    @Override
    public void onLoadMore(RefreshLayout refreshlayout) {
        page++;
        getMyNewsPresenter.getMyNews();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getMyNewsPresenter.getMyNews();
    }

    @Override
    public void onGetMyNews(MyNewsListBean bean) {
        if (page == 1) {
            list.clear();
        }
        list.addAll(bean.getData().getList());
        adapter.notifyDataSetChanged();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        refreshLayout.setEnableLoadMore(!bean.getData().getPaging().isIs_end());
        if (bean.getData().getPaging().getTotal() != 0) {
            rlEmpty.setVisibility(View.GONE);
        } else {
            rlEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public String getPage() {
        return String.valueOf(page);
    }
}
