package com.wetime.fanc.news.act;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.LetToolBar;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.my.adapter.MyFriendsPagerAdapter;
import com.wetime.fanc.news.frag.SpecialTopicBaseFragment;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.weibo.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialTopicActivity extends BaseActivity implements View.OnClickListener, WbShareCallback {


    @BindView(R.id.tablayout)
    SlidingTabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.id_toolbar)
    LetToolBar idToolbar;
    @BindView(R.id.special_linear)
    LinearLayout specialLinear;

    private MyFriendsPagerAdapter mAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> mChannels = new ArrayList<>();
    private PopupWindow pop;
    private WbShareHandler shareHandler;

    public static void startToSpecialTopic(Context context, String specialTopicId) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra("specialTopicId", specialTopicId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_topic);
        ButterKnife.bind(this);
        WbSdk.install(this, new AuthInfo(this, Constants.APP_KEY, "url", Constants.SCOPE));
        shareHandler = new WbShareHandler(this);
        shareHandler.registerApp();
        initView();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        shareHandler.doResultIntent(intent, this);
    }

    private void initView() {

        idToolbar.setTitle("饭团专题");
        idToolbar.getLeftView().setOnClickListener(this);
        idToolbar.getRightView().setOnClickListener(this);
        idToolbar.getRightView().setVisibility(View.VISIBLE);


        mChannels.add("要点1");
        mChannels.add("要点2");
        mChannels.add("要点3");

        mFragments.add(new SpecialTopicBaseFragment());
        mFragments.add(new SpecialTopicBaseFragment());
        mFragments.add(new SpecialTopicBaseFragment());

        mAdapter = new MyFriendsPagerAdapter(getSupportFragmentManager(), mFragments, mChannels);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(mAdapter);
//        vp.setCurrentItem(1);

        tablayout.setViewPager(viewPager);

    }

    @Override
    protected void initStateBar() {

    }

    /**
     * Called when a view has been clicked.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_more:
                showPop();
                break;
            case R.id.ll_share_wx:
//                Glide.with(this).load(gallery.getData().getAtlas_content().get(0).getImg_url()).into(new SimpleTarget<Drawable>() {
//                    /**
//                     * The method that will be called when the resource load has finished.
//                     *
//                     * @param resource   the loaded resource.
//                     * @param transition
//                     */
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        Tools.shareWx(SpecialTopicActivity.this, drawableToBitmap(resource),"url", SendMessageToWX.Req.WXSceneSession, gallery.getData().getName(), gallery.getData().getAtlas_content().get(0).getContent());
//                    }
//                });
                break;
            case R.id.ll_share_wxq:
//                Glide.with(this).load(gallery.getData().getAtlas_content().get(0).getImg_url()).into(new SimpleTarget<Drawable>() {
//                    /**
//                     * The method that will be called when the resource load has finished.
//                     *
//                     * @param resource   the loaded resource.
//                     * @param transition
//                     */
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        Tools.shareWx(SpecialTopicActivity.this, drawableToBitmap(resource),"url", SendMessageToWX.Req.WXSceneTimeline, gallery.getData().getName(), gallery.getData().getAtlas_content().get(0).getContent());
//                    }
//                });

                break;
            case R.id.ll_share_wb:
//                Glide.with(this).load(gallery.getData().getAtlas_content().get(0).getImg_url()).into(new SimpleTarget<Drawable>() {
//                    /**
//                     * The method that will be called when the resource load has finished.
//                     *
//                     * @param resource   the loaded resource.
//                     * @param transition
//                     */
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        Tools.shareWb(this, shareHandler, drawableToBitmap(resource),"url", "分享来自范团APP的《" + gallery.getData().getName() + "》", "分享来自范团APP的《" + gallery.getData().getName() + "》");
//                    }
//                });

                break;
            case R.id.ll_share_qq:
//                Tools.shareQQ(this,"url", gallery.getData().getAtlas_content().get(0).getImg_url(), gallery.getData().getName(), gallery.getData().getAtlas_content().get(0).getContent(), new IUiListener() {
//                    @Override
//                    public void onComplete(Object o) {
//                        Toast.makeText(SpecialTopicActivity.this, "分享成功!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(UiError uiError) {
//                        Toast.makeText(SpecialTopicActivity.this, "未知错误!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Toast.makeText(SpecialTopicActivity.this, "分享取消!", Toast.LENGTH_SHORT).show();
//                    }
//                });
                break;
            case R.id.ll_share_qqkj:
//                Tools.shareToQzone(this,"url", gallery.getData().getAtlas_content().get(0).getImg_url(), gallery.getData().getName(), gallery.getData().getAtlas_content().get(0).getContent(), new IUiListener() {
//                    @Override
//                    public void onComplete(Object o) {
//                        Toast.makeText(SpecialTopicActivity.this, "分享成功!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(UiError uiError) {
//                        Toast.makeText(SpecialTopicActivity.this, "未知错误!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Toast.makeText(SpecialTopicActivity.this, "分享取消!", Toast.LENGTH_SHORT).show();
//                    }
//                });
                break;
            case R.id.ll_share_copy:
                ClipboardManager cm = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                if (cm != null) {
                    cm.setPrimaryClip(ClipData.newPlainText("text", "url"));
                    Tools.toastInBottom(SpecialTopicActivity.this, "复制成功");
                } else {
                    Tools.toastInBottom(SpecialTopicActivity.this, "复制失败");
                }

                break;
            case R.id.pop_cancel:
                if (pop.isShowing()) {
                    pop.dismiss();
                }
                break;
        }
    }

    private void showPop() {
        View shareView = LayoutInflater.from(this).inflate(R.layout.view_popupwindow, null);

        initPopListener(shareView);

        // 创建PopupWindow对象
        pop = new PopupWindow(shareView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        // 需要设置一下此参数，点击外边可消失
        pop.setBackgroundDrawable(new ColorDrawable());
        // 设置点击窗口外边窗口消失
        pop.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        pop.setFocusable(true);

        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.5f;
        this.getWindow().setAttributes(lp);

        pop.setOnDismissListener(() -> {
            // popupWindow隐藏时恢复屏幕正常透明度
            WindowManager.LayoutParams lp1 = this.getWindow().getAttributes();
            lp1.alpha = 1.0f;
            this.getWindow().setAttributes(lp1);
        });

        pop.showAtLocation(this.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    private void initPopListener(View shareView) {
        shareView.findViewById(R.id.ll_share_wx).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_wxq).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_wb).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_qq).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_qqkj).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_copy).setOnClickListener(this);
        shareView.findViewById(R.id.pop_cancel).setOnClickListener(this);
    }

    public Bitmap drawableToBitmap(Drawable drawable) {

        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        //注意，下面三行代码要用到，否则在View或者SurfaceView里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);

        return bitmap;
    }

    @Override
    public void onWbShareSuccess() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_success, Toast.LENGTH_LONG).show();
        hidePop();
    }

    @Override
    public void onWbShareCancel() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_canceled, Toast.LENGTH_LONG).show();
        hidePop();
    }


    @Override
    public void onWbShareFail() {
        Toast.makeText(this, getString(R.string.weibosdk_demo_toast_share_failed) + "Error Message: ", Toast.LENGTH_LONG).show();
        hidePop();
    }

    public void hidePop() {
        if (pop.isShowing()) {
            pop.dismiss();
        }
    }
}
