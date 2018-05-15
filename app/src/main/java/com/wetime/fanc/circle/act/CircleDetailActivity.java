package com.wetime.fanc.circle.act;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.adapter.CircleListAdapter;
import com.wetime.fanc.circle.adapter.CircleListAdapterV2;
import com.wetime.fanc.circle.bean.CircleHeadBean;
import com.wetime.fanc.circle.bean.CircleListBean;
import com.wetime.fanc.circle.iviews.IGetCircleDetailListView;
import com.wetime.fanc.circle.iviews.IGetCircleHeadView;
import com.wetime.fanc.circle.presenter.GetCircleDetailListPresenter;
import com.wetime.fanc.circle.presenter.GetCircleHeadPresenter;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.service.event.UploadEvent;
import com.wetime.fanc.service.event.UploadProgessEvent;
import com.wetime.fanc.utils.GlideRoundTransform;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CircleDetailActivity extends BaseActivity implements IGetCircleHeadView, OnLoadMoreListener, OnRefreshListener, IGetCircleDetailListView {

    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.tv_circleinfo)
    TextView tvCircleinfo;
    @BindView(R.id.iv_attention)
    TextView ivAttention;
    @BindView(R.id.progess)
    LinearLayout progess;
    @BindView(R.id.progess_title)
    TextView progessTitle;


    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.CollapsingToolbarLayout)
    CollapsingToolbarLayout CollapsingToolbarLayout;
    @BindView(R.id.ll_headinfo)
    LinearLayout llHeadinfo;
    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.FloatingActionButton)
    ImageView FloatingActionButton;
    private float rotationNub = 0f;
    private int page = 1;
    private GetCircleDetailListPresenter getCircleDetailListPresenter;
    private List<CircleListBean.DataBean.ListBean> list = new ArrayList<CircleListBean.DataBean.ListBean>();

    private GetCircleHeadPresenter getCircleHeadPresenter;
    private boolean issu = false;
    private CircleListAdapterV2 adapter;
    private boolean isOnly = false;
    private boolean isOne = false;
    private int lastverticalOffset = 0;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_detail);
        ButterKnife.bind(this);

        Tools.showEmptyLoading(this);

        initView();
        progess.setVisibility(View.GONE);
        getCircleHeadPresenter = new GetCircleHeadPresenter(this);
        getCircleHeadPresenter.getDefaultCircle();
        getCircleDetailListPresenter = new GetCircleDetailListPresenter(this);
        getCircleDetailListPresenter.getCircleDetailList();
    }

    @Override
    protected void setSoftInPutMode() {
//        super.setSoftInPutMode();
    }

    @Override
    protected void initStateBar() {
        ImmersionBar.with(this)
                .transparentStatusBar()
                .statusBarDarkFont(false)
                .fitsSystemWindows(false).init();
//        super.initStateBar();
//        ImmersionBar.with(this).statusBarColor(R.color.white_lib).statusBarDarkFont(true, 0f).fitsSystemWindows(true).init();
    }

    @OnClick({R.id.iv_attention, R.id.ll_headinfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ll_headinfo:
                Intent go = new Intent(this, CircleInfoActivity.class);
                go.putExtra("id", getCircleId());
                startActivity(go);
                break;
//                if (issu) {
//                    Toast.makeText(this, "文章上传中,请稍后再发~", Toast.LENGTH_SHORT).show();
//                } else {
//                    Tools.showPopWin(this, ivEdit, getCircleId(), tvCirclename.getText().toString(), this.getClass().getSimpleName());
//                }

            case R.id.iv_attention:
                if (TextUtils.isEmpty(spu.getToken())) {
                    Tools.toastInBottom(this, "请先登录");
                    Intent goLogin = new Intent(this, LoginActivity.class);
                    startActivity(goLogin);
                } else {
                    getCircleHeadPresenter.setCircleAttention(getCircleId(), "1");
                }
                break;
        }
    }

    private void initView() {
        rclCircle.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CircleListAdapterV2(list, this);
        rclCircle.setAdapter(adapter);
        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setOnRefreshListener(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        rclCircle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!isOne) {
                        isOne = true;
                        FloatingActionButton.animate().translationY(getMarginBottom(FloatingActionButton) + FloatingActionButton.getHeight()).setInterpolator(new AccelerateDecelerateInterpolator());
                    }
                } else {
                    if (isOne) {
                        isOne = false;
                        FloatingActionButton.animate().translationY(0).setInterpolator(new AccelerateDecelerateInterpolator());
                    }
                }
            }
        });
        FloatingActionButton.setOnClickListener(v -> {
            Animation operatingAnim = AnimationUtils.loadAnimation(CircleDetailActivity.this, R.anim.rotate_anim);
            LinearInterpolator lin = new LinearInterpolator();
            operatingAnim.setFillAfter(true);
            operatingAnim.setInterpolator(lin);
            FloatingActionButton.setAnimation(operatingAnim);
            FloatingActionButton.startAnimation(operatingAnim);
            PopupWindow popupWindow = Tools.showPopWin(CircleDetailActivity.this, FloatingActionButton, null, null, CircleDetailActivity.this.getClass().getSimpleName(), true);
            if (popupWindow != null) {
                popupWindow.setOnDismissListener(() -> {
//                    ObjectAnimator rotation1 = ObjectAnimator.ofFloat(FloatingActionButton, "rotation", rotationNub, rotationNub - 45);
//                    rotation1.setDuration(200);
//                    rotation1.start();
                    Animation operatingAnim1 = AnimationUtils.loadAnimation(CircleDetailActivity.this, R.anim.rotate_anim_out);
                    LinearInterpolator lin1 = new LinearInterpolator();
                    operatingAnim1.setInterpolator(lin1);
                    operatingAnim1.setFillAfter(true);
                    FloatingActionButton.setAnimation(operatingAnim1);
                    FloatingActionButton.startAnimation(operatingAnim1);
                });
            }
        });
    }

    private int getMarginBottom(View v) {
        int marginBottom = 0;
        final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginBottom = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return marginBottom;
    }

    @Override
    public void onGetCircleHead(CircleHeadBean bean) {

        if (bean.getError() != 0) {
            Tools.hideEmptyLoading();
            this.finish();
            return;
        }


        int sw = Tools.getScreenW(this);
        int w = sw - Tools.dip2px(this, 15 + 15);
        Double rate = 194.0 / 345;
        int h = (int) (w * rate);
        RequestOptions myOptions = new RequestOptions()
                .transform(new GlideRoundTransform(mContext, 5));
        Glide.with(getApplicationContext())
                .load(bean.getData().getCover_url())
                .apply(myOptions)
                .into(ivCover);

        Glide.with(getApplicationContext())
                .load(bean.getData().getCover_url())
                .apply(myOptions.override(w, h))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Bitmap bitmap = Tools.blurBitmap(CircleDetailActivity.this, Tools.drawableToBitmap(resource), 20);
                        llHeadinfo.setBackground(new BitmapDrawable(CircleDetailActivity.this.getResources(), bitmap));

                    }
                });


//        tvCirclename.setText(bean.getData().getName());

        CollapsingToolbarLayout.setTitle(bean.getData().getName());
        CollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.myCollaTitleSize);
        CollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

        appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((lastverticalOffset - verticalOffset) > 0) {
                if (!isOne) {
                    isOne = true;
                    FloatingActionButton.animate().translationY(getMarginBottom(FloatingActionButton) + FloatingActionButton.getHeight()).setInterpolator(new AccelerateDecelerateInterpolator());
                }
            } else {
                if (isOne) {
                    isOne = false;
                    FloatingActionButton.animate().translationY(0).setInterpolator(new AccelerateDecelerateInterpolator());
                }
            }
            lastverticalOffset = verticalOffset;
            int totalScrollRange = appbar.getTotalScrollRange();
            if (Math.abs(verticalOffset) >= totalScrollRange / 2) {
                isOnly = true;
                ImmersionBar.with((Activity) mContext)
                        .statusBarColor(R.color.white_lib)
                        .statusBarDarkFont(true, 0.2f)
                        .fitsSystemWindows(false).init();

            } else {
                if (isOnly) {
                    ImmersionBar.with((Activity) mContext)
                            .transparentStatusBar()
                            .statusBarDarkFont(false)
                            .fitsSystemWindows(false).init();
                }
                isOnly = false;
            }
        });

        tvCircleinfo.setText(bean.getData().getIntro());

        if (bean.getData().isIs_follow()) {
            ivAttention.setVisibility(View.GONE);
        } else {
            ivAttention.setVisibility(View.VISIBLE);
        }

        CollapsingToolbarLayout.setVisibility(View.VISIBLE);
        Tools.hideEmptyLoading();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UploadProgessEvent messageEvent) {
        issu = true;
        if (this.getClass().getSimpleName().equals(messageEvent.getSimpleName())) {
            progess.setVisibility(View.VISIBLE);
            String substring = String.valueOf(messageEvent.getPrgess() * 100).substring(0, String.valueOf(messageEvent.getPrgess() * 100).indexOf("."));
            if (messageEvent.getPrgess() < 1) {
                progessTitle.setText("文章上传中，请不要离开" + substring + "%…");
            } else if (messageEvent.getPrgess() >= 1) {
                progessTitle.setText("文章上传中，请不要离开" + 99 + "%…");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UploadEvent messageEvent) {
        issu = false;
        progess.setVisibility(View.GONE);
    }


    @Override
    public String getCircleId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public void onSetCircleAttention(ErrorBean bean) {
        ivAttention.setVisibility(View.GONE);
    }

    @Override
    public void onGetCircleList(CircleListBean bean) {
        refreshLayout.setEnableLoadMore(!bean.getData().getPaging().isIs_end());
        if (page == 1) {
            list.clear();
        }
        list.addAll(bean.getData().getList());
        adapter.notifyDataSetChanged();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getType() {
        return "2";
    }


    @Override
    public void onLoadMore(RefreshLayout refreshlayout) {
        page++;
        getCircleDetailListPresenter.getCircleDetailList();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getCircleDetailListPresenter.getCircleDetailList();
    }


}
