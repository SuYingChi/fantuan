package com.wetime.fanc.shop.act;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.news.bean.NewsListBean;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.shop.iviews.IGetShopNewsView;
import com.wetime.fanc.shop.presenter.GetShopNewsPresenter;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopNewsHomeActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener, IGetShopNewsView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rcl_news)
    RecyclerView rcl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int page = 1;

    private List<HomeItemBean> list;
    private HomeItemAdapter adapter;
    private GetShopNewsPresenter getNewsTypePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_news_home);
        ButterKnife.bind(this);
        tvTitle.setText("商圈头条");

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        list = new ArrayList<>();
        adapter = new HomeItemAdapter(list, this);
        rcl.setLayoutManager(new LinearLayoutManager(this));
        rcl.setAdapter(adapter);
//        adapter.setOnItemClickLitener((view, position) -> Tools.goWeb(this, list.get(position).getArticle_url()));
        getNewsTypePresenter = new GetShopNewsPresenter(this);
        getNewsTypePresenter.getNews();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;

        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getNewsTypePresenter.getNews();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getNewsTypePresenter.getNews();
    }

    @Override
    public void onGetNews(NewsListBean bean) {
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefresh();

        if (page == 1) {
            list.clear();
        }
        page++;
        
        refreshLayout.setEnableLoadmore(!bean.getData().getPaging().isIs_end());
        list.addAll(bean.getData().getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public String getMid() {
        return getIntent().getStringExtra("mid");
    }

    @Override
    public String getPage() {
        return page + "";
    }
}
