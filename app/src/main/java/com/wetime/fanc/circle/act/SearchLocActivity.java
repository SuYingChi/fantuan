package com.wetime.fanc.circle.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.adapter.LocListAdapter;
import com.wetime.fanc.circle.bean.LocItemBean;
import com.wetime.fanc.circle.bean.SelectLocListBean;
import com.wetime.fanc.circle.iviews.IGetLocListView;
import com.wetime.fanc.circle.presenter.GetLocListPresenter;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchLocActivity extends BaseActivity implements TextWatcher, IGetLocListView {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rcl_loc)
    RecyclerView rclLoc;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;

    private GetLocListPresenter getLocListPresenter;
    private int page = 1;
    private LocListAdapter adapter;
    private ArrayList<LocItemBean> list = new ArrayList<>();
    private AutoLoadMoreAdapter mAutoLoadMoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_loc);
        ButterKnife.bind(this);
        etSearch.addTextChangedListener(this);
        rclLoc.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new LocListAdapter(mContext, list);
        adapter.setOnItemClickLitener((view, position) -> {
            list.get(position).setSelected(false);
            Intent data = new Intent();
            data.putExtra("loc", list.get(position));
            setResult(RESULT_OK, data);
            Tools.hideSoftInput(this);
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


    }

    @Override
    protected void setSoftInPutMode() {

    }

    @Override
    protected void initStateBar() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.white_lib)
                .statusBarDarkFont(true, 0.2f)
                .fitsSystemWindows(true)
                .keyboardEnable(true)
                .keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .init();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        Tools.hideSoftInput(this);
        finish();
    }

    @OnClick({R.id.iv_back, R.id.iv_close, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_close:
                etSearch.setText("");
                llEmpty.setVisibility(View.GONE);
                break;
            case R.id.tv_cancel:
                onBackPressed();;
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().length() > 0) {
            ivClose.setVisibility(View.VISIBLE);
            page = 1;
            getLocListPresenter.getLoclist();
        } else {
            ivClose.setVisibility(View.GONE);
            list.clear();
            mAutoLoadMoreAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetLocList(SelectLocListBean bean) {
        if (page == 1) {
            list.clear();
        }
        list.addAll(bean.getData().getList());
        if (page == 1 && bean.getData().getList().size() == 0) {
            llEmpty.setVisibility(View.VISIBLE);
        } else {
            llEmpty.setVisibility(View.GONE);
        }

        mAutoLoadMoreAdapter.notifyDataSetChanged();
        mAutoLoadMoreAdapter.finishLoading();
        if (bean.getData().getList().size() == 0)
            mAutoLoadMoreAdapter.disable();
    }

    @Override
    public String getKeyWord() {
        return etSearch.getText().toString();
    }

    @Override
    public String getLocTitle() {
        return getIntent().getStringExtra("loctitle");
    }

    @Override
    public int getPage() {
        return page;
    }
}
