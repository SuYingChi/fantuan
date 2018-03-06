package com.wetime.fanc.circle.frag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.CircleDetailListBean;
import com.wetime.fanc.circle.iviews.IGetCircleDetailListView;
import com.wetime.fanc.circle.presenter.GetCircleDetailListPresenter;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.main.frag.BaseLazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CircleDetailLazyFragment extends BaseLazyFragment implements IGetCircleDetailListView, OnLoadmoreListener, OnRefreshListener {
    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int page = 1;
    private GetCircleDetailListPresenter getCircleDetailListPresenter;

    private List<HomeItemBean> list = new ArrayList<>();
    private HomeItemAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_circle_detail;
    }

    @Override
    protected void initView() {
        rclCircle.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeItemAdapter(list, getActivity(), true);
        rclCircle.setAdapter(adapter);
        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {

        getCircleDetailListPresenter = new GetCircleDetailListPresenter(this);
        getCircleDetailListPresenter.getCircleDetailList();


    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    public void onGetCircleList(CircleDetailListBean bean) {
        refreshLayout.setEnableLoadmore(!bean.getData().getPaging().isIs_end());
        if (page == 1) {
            list.clear();
        }
        list.addAll(bean.getData().getList());
        adapter.notifyDataSetChanged();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getType() {
        return getArguments().getString("type");
    }

    @Override
    public String getCircleId() {
        return getArguments().getString("id");
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        getCircleDetailListPresenter.getCircleDetailList();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getCircleDetailListPresenter.getCircleDetailList();
    }
}
