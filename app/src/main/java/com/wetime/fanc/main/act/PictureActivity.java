package com.wetime.fanc.main.act;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
    @BindView(R.id.tv_index_top)
    TextView tvIndexTop;
    @BindView(R.id.tv_desc_number)
    TextView tvDescNumber;
    @BindView(R.id.gallery_desc_layout)
    ScrollView galleryDescLayout;
    private ImageAdapter adapter;
    private List<String> allStr;
    private List<String> contents;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

        allStr = (List<String>) getIntent().getSerializableExtra("big_photo");
        contents = (List<String>) getIntent().getSerializableExtra("contents");

        page = getIntent().getIntExtra("page", 0);

        if (contents == null || contents.size() == 0) {
            tvIndex.setText((page + 1) + "/" + (allStr.size()));
            galleryDescLayout.setVisibility(View.GONE);
            tvIndexTop.setVisibility(View.GONE);
            tvIndex.setVisibility(View.VISIBLE);
        } else {
            galleryDescLayout.setVisibility(View.VISIBLE);
            tvIndexTop.setVisibility(View.VISIBLE);
            tvIndex.setVisibility(View.GONE);
            tvIndexTop.setText((page + 1) + "/" + (allStr.size()));
            if (TextUtils.isEmpty(contents.get(page))) {
                galleryDescLayout.setVisibility(View.GONE);
            } else {
                galleryDescLayout.setVisibility(View.VISIBLE);
                tvDescNumber.setText(contents.get(page));
            }
        }
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
                if (contents == null || contents.size() == 0) {
                    tvIndex.setText((position + 1) + "/" + (allStr.size()));
                } else {
                    tvIndexTop.setText((position + 1) + "/" + (allStr.size()));
                    if (TextUtils.isEmpty(contents.get(position))) {
                        galleryDescLayout.setVisibility(View.GONE);
                    } else {
                        galleryDescLayout.setVisibility(View.VISIBLE);
                        tvDescNumber.setText(contents.get(position));
                    }
                }
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
