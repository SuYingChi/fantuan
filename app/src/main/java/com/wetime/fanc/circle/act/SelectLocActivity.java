package com.wetime.fanc.circle.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.adapter.LocListAdapter;
import com.wetime.fanc.circle.bean.LocItemBean;
import com.wetime.fanc.circle.bean.SelectLocListBean;
import com.wetime.fanc.circle.iviews.IGetLocListView;
import com.wetime.fanc.circle.presenter.GetLocListPresenter;
import com.wetime.fanc.main.act.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectLocActivity extends BaseActivity implements IGetLocListView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rcl_loc)
    RecyclerView rclLoc;
    private GetLocListPresenter getLocListPresenter;
    private int page = 1;
    private LocListAdapter adapter;
    private ArrayList<LocItemBean> list = new ArrayList<>();
    private AutoLoadMoreAdapter mAutoLoadMoreAdapter;
    private final int SEARCH_LOC = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_loc);
        ButterKnife.bind(this);
        tvTitle.setText("附近位置");
        rclLoc.setLayoutManager(new LinearLayoutManager(mContext));
        LocItemBean bean = (LocItemBean) getIntent().getSerializableExtra("loc");

        //不显示位置bean
        LocItemBean nolocbean = new LocItemBean();
        if (TextUtils.isEmpty(bean.getTitle())) {
            nolocbean.setSelected(true);
        }
        list.add(nolocbean);


        if (!TextUtils.isEmpty(bean.getTitle())) {
            list.add(bean);
        }
        adapter = new LocListAdapter(mContext, list);
        adapter.setOnItemClickLitener((view, position) -> {
            list.get(position).setSelected(false);
            Intent data = new Intent();
            data.putExtra("loc", list.get(position));
            setResult(RESULT_OK, data);
            finish();
        });

        mAutoLoadMoreAdapter = new AutoLoadMoreAdapter(mContext, adapter);
        mAutoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
            @Override
            public void onRetry() {

            }

            @Override
            public void onLoadMore() {
                page++;
                getLocListPresenter.getLoclist();
            }
        });
        rclLoc.setAdapter(mAutoLoadMoreAdapter);

        mAutoLoadMoreAdapter.notifyDataSetChanged();

        getLocListPresenter = new GetLocListPresenter(this);
        getLocListPresenter.getLoclist();


    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @OnClick({R.id.iv_back, R.id.ll_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.ll_search:
                Intent goSearch = new Intent(mContext, SearchLocActivity.class);
                goSearch.putExtra("loctitle", getLocTitle());
                startActivityForResult(goSearch, SEARCH_LOC);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_LOC) {
            if (resultCode == RESULT_OK) {
                Intent d = new Intent();
                d.putExtra("loc", data.getSerializableExtra("loc"));
                setResult(RESULT_OK, data);
                finish();
            }
        }
    }

    @Override
    public void onGetLocList(SelectLocListBean bean) {
        list.addAll(bean.getData().getList());
        mAutoLoadMoreAdapter.notifyDataSetChanged();
        mAutoLoadMoreAdapter.finishLoading();
        if (bean.getData().getList().size() == 0)
            mAutoLoadMoreAdapter.disable();
    }

    @Override
    public String getKeyWord() {
        return "";
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getLocTitle() {
        return ((LocItemBean) getIntent().getSerializableExtra("loc")).getTitle();
    }
}
