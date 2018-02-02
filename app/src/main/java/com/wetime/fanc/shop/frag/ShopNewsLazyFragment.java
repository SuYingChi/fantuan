package com.wetime.fanc.shop.frag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.NewsAdapter;
import com.wetime.fanc.home.bean.NewsListBean;
import com.wetime.fanc.home.iviews.IGetShopNewsTypeView;
import com.wetime.fanc.home.presenter.GetShopNewsTypePresenter;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ShopNewsLazyFragment extends BaseLazyFragment implements OnLoadmoreListener, IGetShopNewsTypeView {


    @BindView(R.id.rcl_news)
    RecyclerView rcl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_no)
    RelativeLayout rlNo;

    private List<NewsListBean.DataBean.ListBean> list;
    private NewsAdapter adapter;
    private GetShopNewsTypePresenter getNewsTypePresenter;
    private int page = 1;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shopnews;
    }

    @Override
    protected void initView() {
        refreshLayout = mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnLoadmoreListener(this);
        list = new ArrayList<>();
        adapter = new NewsAdapter(list, getContext());
        adapter.setListtype(1);
        rcl.setLayoutManager(new LinearLayoutManager(getContext()));
        rcl.setAdapter(adapter);
        adapter.setOnItemClickLitener((view, position) -> Tools.goWeb(getContext(), list.get(position).getArticle_url()));
    }

    @Override
    protected void initData() {
        getNewsTypePresenter = new GetShopNewsTypePresenter(this);
        getNewsTypePresenter.getNews();
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        getNewsTypePresenter.getNews();
    }

    @Override
    public void onGetNews(NewsListBean bean) {
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

}
