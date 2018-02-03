package com.wetime.fanc.shop.frag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.wetime.fanc.R;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.shop.adapter.ShopSayActAdapter;
import com.wetime.fanc.shop.bean.ShopActListBean;
import com.wetime.fanc.shop.iviews.IGetShopActTypeView;
import com.wetime.fanc.shop.presenter.GetShopActPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ShopActLazyFragment extends BaseLazyFragment implements OnLoadmoreListener, IGetShopActTypeView {

    @BindView(R.id.rcl_news)
    RecyclerView rcl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_no)
    RelativeLayout rlNo;

    private List<ShopActListBean.DataBean.ListBean> list;
    private ShopSayActAdapter adapter;
    private GetShopActPresenter getShopActPresenter;
    private int page = 1;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shopact;
    }

    @Override
    protected void initView() {
        refreshLayout = mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnLoadmoreListener(this);
        list = new ArrayList<>();
        adapter = new ShopSayActAdapter(list, getContext());

        rcl.setLayoutManager(new LinearLayoutManager(getContext()));
        rcl.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        getShopActPresenter = new GetShopActPresenter(this);
        getShopActPresenter.getShopAct();
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    public void onGetACt(ShopActListBean bean) {
        refreshLayout.finishLoadmore();
        refreshLayout.setEnableLoadmore(!bean.getData().getPaging().isIs_end());
        list.addAll(bean.getData().getList());
        adapter.notifyDataSetChanged();
        if (list.size() > 0)
            rlNo.setVisibility(View.GONE);
    }

    @Override
    public String getMid() {
        return getArguments().getString("mid");
    }


    @Override
    public String getPage() {
        return page + "";
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        getShopActPresenter.getShopAct();
    }
}
