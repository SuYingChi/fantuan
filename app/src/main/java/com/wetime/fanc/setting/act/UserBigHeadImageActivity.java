package com.wetime.fanc.setting.act;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gyf.barlibrary.ImmersionBar;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.bean.PostFileResultBean;
import com.wetime.fanc.main.ivews.IPostMultiFileView;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.main.presenter.PostMultiFilePresenter;
import com.wetime.fanc.setting.event.ChangeUserInfoEvent;
import com.wetime.fanc.setting.iviews.ISetUserInfoView;
import com.wetime.fanc.setting.presenter.SetUserInfoPresenter;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.shaohui.bottomdialog.BottomDialog;

public class UserBigHeadImageActivity extends BaseActivity implements IPostMultiFileView, ISetUserInfoView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.iv_large)
    ImageView ivLarge;
    private SetUserInfoPresenter setUserInfoPresenter;
    private PostMultiFilePresenter postMultiFilePresenter;

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
//        postMultiFilePresenter = new PostMultiFilePresenter(this);
//        setUserNamePresenter = new SetUserNamePresenter(this);


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

    @OnClick({R.id.iv_back, R.id.iv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_more:
                showBottomDialog();
                break;

        }
    }

    private void showBottomDialog() {
        BottomDialog mDeleteBottomDialog = BottomDialog.create(getSupportFragmentManager());
        mDeleteBottomDialog.setDimAmount(0.5f);
        mDeleteBottomDialog.setCancelOutside(true);
        mDeleteBottomDialog.setLayoutRes(R.layout.bottom_selectpic_dialog_layout);
        mDeleteBottomDialog.setViewListener(v11 -> {
            v11.findViewById(R.id.tv_takepic).setOnClickListener(v -> {
                mDeleteBottomDialog.dismiss();
                PictureSelector.create(this)
                        .openCamera(PictureMimeType.ofImage())
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            });
            v11.findViewById(R.id.tv_select).setOnClickListener(v -> {
                mDeleteBottomDialog.dismiss();
                gotoSelectPic();
            });
            v11.findViewById(R.id.tv_cancel).setOnClickListener(v -> mDeleteBottomDialog.dismiss());

        });
        mDeleteBottomDialog.show();
    }

    private void gotoSelectPic() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .theme(R.style.picture_my_style)
                .previewImage(true)
                .isCamera(false)
                .compress(true)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST && data != null) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            ArrayList<String> path = new ArrayList<>();
            if (selectList.get(0).isCut()) {
                path.add(selectList.get(0).getCompressPath());
            } else {
                path.add(selectList.get(0).getPath());
            }

            if (path.size() > 0) {
                new Handler().post(() -> {
                    Uri uri_crop = Uri.fromFile(new File(path.get(0)));
                    //裁剪后保存到文件中
                    String pt = Environment.getExternalStorageDirectory().getPath() + "/fantuan/iamge/";
                    File des = new File(pt);
                    if (!des.exists()) {
                        des.mkdirs();
                    }
                    Uri destinationUri = Uri.fromFile(new File(pt + System.currentTimeMillis() + ".jpg"));
                    UCrop.Options options = new UCrop.Options();
                    options.setHideBottomControls(true);
                    //设置toolbar颜色
                    options.setToolbarColor(ContextCompat.getColor(mContext, R.color.black));
                    //设置状态栏颜色
                    options.setStatusBarColor(ContextCompat.getColor(mContext, R.color.black));
                    UCrop.of(uri_crop, destinationUri)
                            .withOptions(options)
                            .withAspectRatio(1, 1)
//                            .withMaxResultSize(maxWidth, maxHeight)
                            .start(this);

                });

            }
        }
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            if (postMultiFilePresenter == null)
                postMultiFilePresenter = new PostMultiFilePresenter(this);
            final List<String> pathlist = new ArrayList<>();
            pathlist.add(resultUri.getPath());
            postMultiFilePresenter.PostMultiFile(pathlist);

        }
    }

    @Override
    public void onPostResult(PostFileResultBean bean) {
        if (bean.getError() != 0)
            return;
        Glide.with(mContext).load(bean.getData().getUrl().get(0)).into(ivLarge);
        if (setUserInfoPresenter == null)
            setUserInfoPresenter = new SetUserInfoPresenter(this);
        setUserInfoPresenter.setUserInfo("avatar", bean.getData().getId().get(0));
    }

    @Override
    public void onSetUserInfoResult(BaseBean bean) {
        if (bean.getError() == 0)
            EventBus.getDefault().post(new ChangeUserInfoEvent());
    }
}
