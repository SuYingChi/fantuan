package com.wetime.fanc.circle.act;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.adapter.ActDetailAdapter;
import com.wetime.fanc.circle.bean.ActDetailBean;
import com.wetime.fanc.circle.iviews.IGetActDetailView;
import com.wetime.fanc.circle.presenter.GetActDetailPresenter;
import com.wetime.fanc.main.act.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActDetailActivity extends BaseActivity implements IGetActDetailView, OnLoadmoreListener {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_gocomment)
    TextView tvGocomment;
    @BindView(R.id.tv_zan)
    TextView tvZan;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;


    private GetActDetailPresenter getActDetailPresenter;
    private int page = 1;
    private ActDetailAdapter actDetailAdapter;
    private ActDetailBean actbean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSoftInPutMode();
        setContentView(R.layout.activity_actdetail);
        ButterKnife.bind(this);
        tvTitle.setText("动态详情");
        getActDetailPresenter = new GetActDetailPresenter(this);
        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.setEnableRefresh(false);
        rclCircle.setLayoutManager(new LinearLayoutManager(this));

        getActDetailPresenter.getActDetail();
    }
    @Override
    protected void setSoftInPutMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.tv_gocomment, R.id.tv_zan, R.id.rl_bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_gocomment:
                showKeyborad();
                break;
            case R.id.tv_zan:

                break;
            case R.id.rl_bottom:
                hideKeyboard();
                break;

        }
    }

    @Override
    public void onGetActDetail(ActDetailBean bean) {
        if (page == 1) {
            actbean = bean;
            if (actDetailAdapter == null) {
                actDetailAdapter = new ActDetailAdapter(this, actbean);
                rclCircle.setAdapter(actDetailAdapter);
            }
        } else {
            actbean.getData().getComment_list().addAll(bean.getData().getComment_list());
        }
        actDetailAdapter.notifyDataSetChanged();
        refreshLayout.setEnableLoadmore(!bean.getData().getPaging().isIs_end());
        refreshLayout.finishLoadmore();
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        getActDetailPresenter.getActDetail();
    }
    protected void initStateBar() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.white_lib)
                .statusBarDarkFont(true, 0.2f)
                .fitsSystemWindows(true)
                .keyboardEnable(true)
                .init();
    }
    private void showKeyborad() {
        rlBottom.setVisibility(View.VISIBLE);
        etContent.requestFocus();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etContent, InputMethodManager.SHOW_FORCED);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etContent.getWindowToken(), 0); //强制隐藏键盘
        rlBottom.setVisibility(View.GONE);
    }
}
