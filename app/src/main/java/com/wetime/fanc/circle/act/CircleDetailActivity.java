package com.wetime.fanc.circle.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.SlidingTabLayout;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.CircleHeadBean;
import com.wetime.fanc.circle.frag.CircleDetailLazyFragment;
import com.wetime.fanc.circle.iviews.IGetCircleHeadView;
import com.wetime.fanc.circle.presenter.GetCircleHeadPresenter;
import com.wetime.fanc.home.adapter.NormalTitlePagerAdapter;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.service.event.UploadProgessEvent;
import com.wetime.fanc.service.event.uploadEvent;
import com.wetime.fanc.utils.GlideRoundTransform;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CircleDetailActivity extends BaseActivity implements IGetCircleHeadView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_edit)
    ImageView ivEdit;
    @BindView(R.id.ll_headinfo)
    LinearLayout llHeadinfo;
    @BindView(R.id.tablayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.tv_circlename)
    TextView tvCirclename;
    @BindView(R.id.tv_circleinfo)
    TextView tvCircleinfo;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_attention)
    ImageView ivAttention;
    @BindView(R.id.progess)
    LinearLayout progess;
    @BindView(R.id.progess_title)
    TextView progessTitle;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private GetCircleHeadPresenter getCircleHeadPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_detail);
        ButterKnife.bind(this);
        initView();
        progess.setVisibility(View.GONE);
        getCircleHeadPresenter = new GetCircleHeadPresenter(this);
        getCircleHeadPresenter.getDefaultCircle();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.iv_edit, R.id.ll_headinfo, R.id.iv_attention})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.ll_headinfo:
                Intent go = new Intent(this, CircleInfoActivity.class);
                go.putExtra("id", getCircleId());
                startActivity(go);
                break;
            case R.id.iv_edit:
                Tools.showPopWin(this, ivEdit, getCircleId());
                break;
            case R.id.iv_attention:
                getCircleHeadPresenter.setCircleAttention(getCircleId(), "1");
                break;
        }
    }

    private void initView() {

        String[] mTitles = {"热门动态", "最新动态", "最新回复"};

        Bundle b1 = new Bundle();
        b1.putString("type", "1");
        b1.putString("id", getCircleId());

        CircleDetailLazyFragment c1 = new CircleDetailLazyFragment();
        c1.setArguments(b1);
        mFragments.add(c1);

        Bundle b2 = new Bundle();
        b2.putString("type", "2");
        b2.putString("id", getCircleId());

        CircleDetailLazyFragment c2 = new CircleDetailLazyFragment();
        c2.setArguments(b2);
        mFragments.add(c2);

        Bundle b3 = new Bundle();
        b3.putString("type", "3");
        b3.putString("id", getCircleId());

        CircleDetailLazyFragment c3 = new CircleDetailLazyFragment();
        c3.setArguments(b3);
        mFragments.add(c3);


        NormalTitlePagerAdapter mAdapter = new NormalTitlePagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        vp.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(vp, mTitles);

    }

    @Override
    public void onGetCircleHead(CircleHeadBean bean) {
        RequestOptions myOptions = new RequestOptions()
                .transform(new GlideRoundTransform(mContext, 5));
        Glide.with(this)
                .load(bean.getData().getCover_url())
                .apply(myOptions)
                .into(ivCover);
        tvCirclename.setText(bean.getData().getName());
        tvCircleinfo.setText(bean.getData().getIntro());
        tvTitle.setText(bean.getData().getName());

        if (bean.getData().isIs_follow()) {
            ivAttention.setVisibility(View.GONE);
        } else {
            ivAttention.setVisibility(View.VISIBLE);
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UploadProgessEvent messageEvent) {
        progess.setVisibility(View.VISIBLE);
        String substring = String.valueOf(messageEvent.getPrgess() * 100).substring(0, String.valueOf(messageEvent.getPrgess() * 100).indexOf("."));
        if (messageEvent.getPrgess() < 1) {
            progessTitle.setText("文章上传中，请不要离开" + substring + "%…");
        } else if (messageEvent.getPrgess() >= 1) {
            progessTitle.setText("文章上传中，请不要离开" + 99 + "%…");
        }
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(uploadEvent messageEvent) {
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


}
