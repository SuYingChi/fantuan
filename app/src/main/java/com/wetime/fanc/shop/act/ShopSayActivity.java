package com.wetime.fanc.shop.act;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.NewsPagerAdapter;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.order.MyRatingBar;
import com.wetime.fanc.shop.bean.ShopDetailBean;
import com.wetime.fanc.shop.frag.ShopActLazyFragment;
import com.wetime.fanc.shop.frag.ShopNewsLazyFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopSayActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rb_socre)
    MyRatingBar rbSocre;
    @BindView(R.id.ll_headinfo)
    LinearLayout llHeadinfo;
    @BindView(R.id.tablayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.vp)
    ViewPager vp;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopsay);
        ButterKnife.bind(this);
        initHead();


    }

    private void initHead() {
        tvTitle.setText("商家说");
        ShopDetailBean bean = (ShopDetailBean) getIntent().getSerializableExtra("data");
        if (bean != null) {
            Glide.with(this).load(bean.getData().getMerchant().getLogo()).into(ivCover);
            tvName.setText(bean.getData().getMerchant().getName());
            rbSocre.setStar(Float.valueOf(bean.getData().getMerchant().getScore()));
        } else {
            Glide.with(this).load(getIntent().getStringExtra("url")).into(ivCover);
            tvName.setText(getIntent().getStringExtra("name"));
            rbSocre.setStar(Float.valueOf(getIntent().getStringExtra("score")));
        }

        String[] mTitles = getResources().getStringArray(R.array.shopsay);

        Bundle bundle = new Bundle();
        bundle.putString("mid", getIntent().getStringExtra("mid"));

        ShopNewsLazyFragment shopNewsLazyFragment = new ShopNewsLazyFragment();
        shopNewsLazyFragment.setArguments(bundle);
        mFragments.add(shopNewsLazyFragment);

        ShopActLazyFragment actLazyFragment = new ShopActLazyFragment();
        actLazyFragment.setArguments(bundle);
        mFragments.add(actLazyFragment);




//        for (int i = 0; i < mTitles.length; i++) {
//            NewsTypeLazyFragment myFragment = new NewsTypeLazyFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("type", i + "");
//            myFragment.setArguments(bundle);
//            mFragments.add(myFragment);
//        }
        NewsPagerAdapter mAdapter = new NewsPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        vp.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(vp, mTitles);
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

}
