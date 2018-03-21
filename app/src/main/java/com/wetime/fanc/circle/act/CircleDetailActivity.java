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
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.GlideRoundTransform;

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

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_detail);
        ButterKnife.bind(this);
        initView();
        GetCircleHeadPresenter getCircleHeadPresenter = new GetCircleHeadPresenter(this);
        getCircleHeadPresenter.getDefaultCircle();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.iv_edit, R.id.ll_headinfo})
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
                if (spu.getToken().equals("")) {
                    Intent gologin = new Intent(mContext, LoginActivity.class);
                    startActivity(gologin);
                } else {
                    Intent goPublish = new Intent(this, PublishActActivity.class);
                    goPublish.putExtra("id", getCircleId());
                    startActivity(goPublish);
                }
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
    }

    @Override
    public String getCircleId() {
        return getIntent().getStringExtra("id");
    }
}
