package com.wetime.fanc.shopcenter.act;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.batterytest.fbaselib.main.BaseActivity;
import com.wetime.fanc.R;
import com.wetime.fanc.shopcenter.bean.CenterListPageBean;
import com.wetime.fanc.shopcenter.bean.MerchantsBean;
import com.wetime.fanc.shopcenter.iviews.IGetShopCenterListView;
import com.wetime.fanc.shopcenter.presenter.GetShopCenterListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CenterListActivity extends BaseActivity implements IGetShopCenterListView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.rcv_lsit)
    RecyclerView rcvLsit;

    private GetShopCenterListPresenter getShopCenterListPresenter;
    private CenterListAdapter adapter;
    private List<MerchantsBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centerlist);
        ButterKnife.bind(this);
        tvTitle.setText("h5传值过来");
        adapter = new CenterListAdapter(list, mContext);
        rcvLsit.setLayoutManager(new LinearLayoutManager(this));
        rcvLsit.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        getShopCenterListPresenter = new GetShopCenterListPresenter(this);
        getShopCenterListPresenter.getShopCenterList();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_search:
                break;

        }
    }

    @Override
    public String getJd() {
        return spu.getValue("jd");
    }

    @Override
    public String getWd() {
        return spu.getValue("wd");
    }

    @Override
    public String getMailId() {
        return "24";
    }

    @Override
    public String getFloaId() {
        return "1";
    }

    @Override
    public void onGetShopCenterListBean(CenterListPageBean bean) {
        list.addAll(bean.getData().getMerchants());
        adapter.notifyDataSetChanged();
    }
}
