package com.wetime.fanc.setting.act;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gyf.barlibrary.ImmersionBar;
import com.shizhefei.view.largeimage.LargeImageView;
import com.shizhefei.view.largeimage.factory.FileBitmapDecoderFactory;
import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserBigHeadImageActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.iv_large)
    ImageView ivLarge;
//    @BindView(R.id.iv_large)
//    LargeImageView ivLarge;

    public static void goUserBigHeadImageAct(Context context, String big, String small) {
        Intent go = new Intent(context, UserBigHeadImageActivity.class);
        go.putExtra("big", big);
        go.putExtra("small", small);
        context.startActivity(go);
    }

    @Override
    protected void initStateBar() {
        ImmersionBar.with(this).statusBarColor(R.color.black).fitsSystemWindows(true).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_userhead);
        ButterKnife.bind(this);
        tvTitle.setText("个人头像");


        Glide.with(this)
                .load(getIntent().getStringExtra("small"))
                .into(ivHead);

        Glide.with(this).load(getIntent().getStringExtra("big")).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                ivHead.setVisibility(View.GONE);
                ivLarge.setVisibility(View.VISIBLE);
                return false;
            }
        })
                .into(ivLarge);
//        Glide.with(this).downloadOnly()
//                .load(getIntent().getStringExtra("big"))
//                .listener(new RequestListener<File>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
//                        ivHead.setVisibility(View.GONE);
//                        ivLarge.setVisibility(View.VISIBLE);
////                        ivLarge.setImage(new FileBitmapDecoderFactory(resource));
//                        return false;
//                    }
//
//                }).submit();

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
