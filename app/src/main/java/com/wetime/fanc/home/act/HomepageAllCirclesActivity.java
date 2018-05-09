package com.wetime.fanc.home.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.CircleDetailActivity;
import com.wetime.fanc.circle.adapter.AllCircleAdapter;
import com.wetime.fanc.circle.iviews.IGetAllCircleView;
import com.wetime.fanc.circle.presenter.GetAllCirclePresenter;
import com.wetime.fanc.home.adapter.HomepageAllCircleAdapter;
import com.wetime.fanc.home.bean.HomepageAllCirclesBean;
import com.wetime.fanc.home.iviews.IHomePageAllCirclesView;
import com.wetime.fanc.home.presenter.HomepageAllCirclesPresenter;
import com.wetime.fanc.main.act.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomepageAllCirclesActivity extends BaseActivity implements  IHomePageAllCirclesView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;

    private HomepageAllCirclesPresenter getAllCirclePresenter;
    private List<HomepageAllCirclesBean.DataBean.ListBean> list = new ArrayList<HomepageAllCirclesBean.DataBean.ListBean>();
    private HomepageAllCircleAdapter allCircleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_all_circles_activity_layout);
        ButterKnife.bind(this);
        tvTitle.setText("全部圈子");
        getAllCirclePresenter = new HomepageAllCirclesPresenter(this);

        allCircleAdapter = new HomepageAllCircleAdapter(mContext, list);
        rclCircle.setLayoutManager(new LinearLayoutManager(this));
        rclCircle.setAdapter(allCircleAdapter);
        allCircleAdapter.notifyDataSetChanged();

        getAllCirclePresenter.getAllCircle();
    }



    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                setResult(RESULT_CANCELED);
                finish();
                break;

        }
    }

    @Override
    public void onGetAllCircle(HomepageAllCirclesBean bean ) {
        list.clear();
        list.addAll(bean.getData().getList());
        allCircleAdapter.notifyDataSetChanged();
    }

}
