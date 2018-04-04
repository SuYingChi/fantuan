package com.wetime.fanc.main.act;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.gyf.barlibrary.ImmersionBar;
import com.wetime.fanc.R;
import com.wetime.fanc.main.adapter.ImageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PictureActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.photo_all)
    RelativeLayout photoAll;
    @BindView(R.id.tv_index)
    TextView tvIndex;
    private ImageAdapter adapter;
    private List<String> allStr;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

        allStr = (List<String>) getIntent().getSerializableExtra("big_photo");

        page = getIntent().getIntExtra("page", 0);

        tvIndex.setText((page + 1) + "/" + (allStr.size()));
        adapter = new ImageAdapter(this, allStr);
        vp.setAdapter(adapter);
        vp.setCurrentItem(page);
        adapter.setOnClickListener(this);
        vp.setOnClickListener(this);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvIndex.setText((position + 1) + "/" + (allStr.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initStateBar() {
        ImmersionBar.with(this).statusBarColor(R.color.black).fitsSystemWindows(true).init();
    }

    @Override
    public void onClick(View v) {
        finish();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }
}
