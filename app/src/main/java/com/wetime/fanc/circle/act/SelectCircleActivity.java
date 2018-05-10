package com.wetime.fanc.circle.act;

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
import com.wetime.fanc.circle.adapter.AllCircleAdapter;
import com.wetime.fanc.circle.bean.AllCircleListBean;
import com.wetime.fanc.circle.iviews.IGetAllCircleView;
import com.wetime.fanc.circle.presenter.GetAllCirclePresenter;
import com.wetime.fanc.main.act.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectCircleActivity extends BaseActivity implements OnLoadMoreListener, IGetAllCircleView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int page = 1;
    private GetAllCirclePresenter getAllCirclePresenter;
    private List<AllCircleListBean.DataBean.ListBean> list = new ArrayList<>();
    private AllCircleAdapter allCircleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_circle);
        ButterKnife.bind(this);
        tvTitle.setText("全部圈子");

        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setEnableRefresh(false);
        getAllCirclePresenter = new GetAllCirclePresenter(this);

        allCircleAdapter = new AllCircleAdapter(mContext, list);
        rclCircle.setLayoutManager(new LinearLayoutManager(this));
        rclCircle.setAdapter(allCircleAdapter);

        allCircleAdapter.setOnItemClickLitener((view, position) -> {
            Intent data = new Intent();
            data.putExtra("name", list.get(position).getName());
            data.putExtra("id", list.get(position).getId());
            setResult(RESULT_OK, data);
            finish();
        });
        allCircleAdapter.notifyDataSetChanged();

        getAllCirclePresenter.getDefaultCircle();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
    public void onLoadMore(RefreshLayout refreshlayout) {
        page++;
        getAllCirclePresenter.getDefaultCircle();

    }

    @Override
    public void onGetAllCircle(AllCircleListBean bean) {
        list.addAll(bean.getData().getList());
        allCircleAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadMore();
        refreshLayout.setEnableLoadMore(!bean.getData().getPaging().isIs_end());
    }

    @Override
    public int getPage() {
        return page;
    }
}
