package com.wetime.fanc.wallet.act;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.wallet.adapter.BalanceDetailListAdapter;
import com.wetime.fanc.wallet.bean.BalanceDetailListBean;
import com.wetime.fanc.wallet.iviews.IGetBalanceDetailListView;
import com.wetime.fanc.wallet.presenter.GetBalanceDetailListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BalanceDetailActivity extends BaseActivity implements IGetBalanceDetailListView, OnLoadmoreListener {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.lvbalance)
    ListView lvbalance;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    private GetBalanceDetailListPresenter getBalanceDetailListPresenter;
    private int page = 1;
    private List<BalanceDetailListBean.DataBean.ListBean> list = new ArrayList<>();
    private BalanceDetailListAdapter adapter;
    private View headView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashoutlist);
        ButterKnife.bind(this);
        tvTitle.setText("余额明细");
        adapter = new BalanceDetailListAdapter(mContext, list);
        lvbalance.setAdapter(adapter);
        getBalanceDetailListPresenter = new GetBalanceDetailListPresenter(this);
        getBalanceDetailListPresenter.getbalancelist();

        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.setEnableRefresh(false);
        lvbalance.setOnItemClickListener((parent, view, position, id) -> Tools.goWeb(mContext,list.get(position-1).getUrl()));
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
    public String getPage() {
        return "" + page;
    }

    @Override
    public void onGetList(BalanceDetailListBean bean) {
        if (headView == null) {
            headView = LayoutInflater.from(mContext).inflate(R.layout.item_balance_list_head, null);
            TextView tvtotal = headView.findViewById(R.id.tv_totalnum);
            tvtotal.setText(String.format("共%d笔", bean.getData().getPage().getTotal()));
            lvbalance.addHeaderView(headView);
            if (bean.getData().getList().size() != 0) {
                rlEmpty.setVisibility(View.GONE);
            }
        }
        list.addAll(bean.getData().getList());
        adapter.notifyDataSetChanged();
        refreshLayout.setEnableLoadmore(!bean.getData().getPage().isIs_end());
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        getBalanceDetailListPresenter.getbalancelist();
    }
}
