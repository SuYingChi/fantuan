package com.wetime.fanc.web;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.recycview.NestedScrollingLinearLayoutManager;
import com.wetime.fanc.customview.recycview.NestedScrollingRecyclerView;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.my.bean.AttentionBean;
import com.wetime.fanc.news.bean.CommentBean;
import com.wetime.fanc.news.bean.GalleryCommentBean;
import com.wetime.fanc.news.iviews.IGetAllCommentView;
import com.wetime.fanc.news.presenter.GetAllCommentPresenter;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.web.adapter.NewsWebAdapter;
import com.wetime.fanc.web.bean.NewsWebBean;
import com.wetime.fanc.web.iviews.IGetRecommenDarticles;
import com.wetime.fanc.web.presenter.GetAllRecommenDarticles;
import com.wetime.fanc.weibo.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/5/8.
 */

public class NewsDetailWebActivity extends BaseActivity implements IGetRecommenDarticles, IGetAllCommentView, View.OnClickListener, WbShareCallback {


    public String url = "";
    public String id = "";
    public String avatar = "";
    public String username = "";
    public String uid = "";
    public String like_num = "";
    public String article_url = "";
    public String compress = "";
    public String content = "";
    public boolean has_like;
    @BindView(R.id.web_recyclerview)
    NestedScrollingRecyclerView webRecyclerview;
    @BindView(R.id.gallery_linear)
    LinearLayout galleryLinear;
    @BindView(R.id.gallery_curr_EditText)
    EditText galleryCurrEditText;
    @BindView(R.id.gallery_curr_LinearLayout)
    LinearLayout galleryCurrLinearLayout;
    @BindView(R.id.friend_base_head)
    CircleImageView friendBaseHead;
    @BindView(R.id.friend_base_title)
    TextView friendBaseTitle;
    @BindView(R.id.tv_focus)
    ImageView tvFocus;
    @BindView(R.id.gallery_text)
    TextView galleryText;
    private GetAllRecommenDarticles getAllRecommenDarticles;
    private GetAllCommentPresenter getAllCommentPresenter;
    private NewsWebAdapter adapter;
    private boolean isShowInput = false;
    private PopupWindow pop;
    private WbShareHandler shareHandler;

    public static void starToWeb(Context context, String url, String id, String uid, String content, String username, String avatar, String article_url, String compress, String like_num, boolean has_like) {
        Intent intent = new Intent(context, NewsDetailWebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("id", id);
        intent.putExtra("uid", uid);
        intent.putExtra("content", content);
        intent.putExtra("avatar", avatar);
        intent.putExtra("article_url", article_url);
        intent.putExtra("compress", compress);
        intent.putExtra("username", username);
        intent.putExtra("like_num", like_num);
        intent.putExtra("has_like", has_like);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetailweb);
        ButterKnife.bind(this);
        initView();
        iniData();
    }

    private ViewTreeObserver.OnGlobalLayoutListener getGlobalLayoutListener(final View decorView, final LinearLayout contentView) {
        return () -> {
            Rect r = new Rect();
            decorView.getWindowVisibleDisplayFrame(r);
            int height = decorView.getResources().getDisplayMetrics().heightPixels;
            int diff = height - r.bottom;

            if (diff > 0) {
                galleryCurrLinearLayout.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) layoutParams;
                    lp.setMargins(0, 0, 0, diff);
                    contentView.setLayoutParams(lp);
                } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) layoutParams;
                    lp.setMargins(0, 0, 0, diff);
                    contentView.setLayoutParams(lp);
                }

                galleryCurrEditText.setFocusable(true);
                galleryCurrEditText.setFocusableInTouchMode(true);
                galleryCurrEditText.requestFocus();

            } else {
                galleryCurrLinearLayout.setVisibility(View.GONE);
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideInput();
        return super.onTouchEvent(event);
    }


    private void iniData() {
        getAllRecommenDarticles = new GetAllRecommenDarticles(this);
        getAllRecommenDarticles.getRecommenDarticles(id);
        getAllCommentPresenter = new GetAllCommentPresenter(this);
        getAllCommentPresenter.getAllComment(id, String.valueOf(1));

    }

    public void showTool() {
        friendBaseHead.setVisibility(View.VISIBLE);
        friendBaseTitle.setVisibility(View.VISIBLE);
        tvFocus.setVisibility(View.VISIBLE);

    }

    public void hideTool(){
        friendBaseHead.setVisibility(View.GONE);
        friendBaseTitle.setVisibility(View.GONE);
        tvFocus.setVisibility(View.GONE);
    }


    public void clickLike(String comment_id, String like) {
        getAllCommentPresenter.clickLike(comment_id, like);
    }

    public void deleteComment(String comment_id) {
        getAllCommentPresenter.deleteCommonet(comment_id);
    }

    private void initView() {
        WbSdk.install(this, new AuthInfo(this, Constants.APP_KEY, article_url, Constants.SCOPE));
        shareHandler = new WbShareHandler(this);
        shareHandler.registerApp();
        url = getIntent().getStringExtra("url");
        id = getIntent().getStringExtra("id");
        uid = getIntent().getStringExtra("uid");
        avatar = getIntent().getStringExtra("avatar");
        username = getIntent().getStringExtra("username");
        like_num = getIntent().getStringExtra("like_num");
        compress = getIntent().getStringExtra("compress");
        content = getIntent().getStringExtra("compress");
        article_url = getIntent().getStringExtra("article_url");
        has_like = getIntent().getBooleanExtra("has_like", false);

        Glide.with(this).load(adapter).into(friendBaseHead);
        friendBaseTitle.setText(username);
//        if () {
//            tvFocus.setVisibility(View.GONE);
//        } else {
//            tvFocus.setVisibility(View.VISIBLE);
//        }

        View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(getGlobalLayoutListener(decorView, galleryCurrLinearLayout));
        webRecyclerview.setLayoutManager(new NestedScrollingLinearLayoutManager(this));
        adapter = new NewsWebAdapter(this,
                "https://www.baidu.com/",
                null, null);
        webRecyclerview.setAdapter(adapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        shareHandler.doResultIntent(intent, this);
    }

    @Override
    public void onRecommenDarticles(NewsWebBean bean) {
        if (bean.getError() == 0) {
            adapter.setData(bean.getData().getList());
        } else {
            Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAttentionFriends(AttentionBean bean) {
        if (bean.getError() != 0) {
            Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
            tvFocus.setVisibility(View.VISIBLE);
            return;
        }
        tvFocus.setVisibility(View.GONE);
        Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetAllComment(CommentBean bean) {
        if (bean.getError() != 0) {
            Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
            return;
        }
        galleryText.setText(bean.getData().getList().size() + "");
        adapter.setDataComm(bean.getData().getList());
    }

    @Override
    public void onClickLike(CommentBean bean) {

    }

    @Override
    public void onSendCommont(GalleryCommentBean bean) {
        if (bean.getError() == 0) {
            Toast.makeText(this, "评论成功", Toast.LENGTH_SHORT).show();
            hideInput();
            galleryCurrEditText.setText("");
            getAllCommentPresenter.getAllComment(id, String.valueOf(1));
        }
    }

    @Override
    public void onDeleteCommont(ErrorBean bean) {
        if (bean.getError() == 0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
            getAllCommentPresenter.getAllComment(id, String.valueOf(1));
        }
    }

    public void hideInput() {
        if (isShowInput) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(galleryCurrEditText.getWindowToken(), 0);
            isShowInput = false;
        }
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

    @OnClick({R.id.iv_back, R.id.tv_focus, R.id.gallery_share, R.id.gallery_editText, R.id.gallery_curr_TextView, R.id.gallery_imageview, R.id.gallery_text, R.id.gallery_collect})
    public void onViewClicked(View view) {
        Log.e("xi", "onViewClicked: " );
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_focus:
                if (spu.getToken().equals("")) {
                    Intent go1 = new Intent(this, LoginActivity.class);
                    startActivity(go1);
                } else {
                    getAllRecommenDarticles.attentionFriends("1", uid);
                }
                break;
            case R.id.gallery_imageview:
            case R.id.gallery_text:
                webRecyclerview.scrollToPosition(2);
                break;
            case R.id.gallery_collect:
//                if (spu.getToken().equals("")) {
//                    Intent go1 = new Intent(this, LoginActivity.class);
//                    startActivity(go1);
//                } else {
//                    if () {
//                        getNewsDetailPresenter.collectNews(galleryId, "0");
//                    } else {
//                        getNewsDetailPresenter.collectNews(galleryId, "1");
//                    }
//                }
                break;
            case R.id.gallery_share:
                showPop();
                break;
            case R.id.gallery_curr_TextView:
                if (spu.getToken().equals("")) {
                    Intent go1 = new Intent(this, LoginActivity.class);
                    startActivity(go1);
                } else {
                    String s = String.valueOf(galleryCurrEditText.getText());
                    if (s == null || s.isEmpty()) {
                        Toast.makeText(this, "评论不能为空哦~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    s = s.replace("\n", " ");
                    getAllCommentPresenter.sendCommonet(id, s);
                }
                break;
            case R.id.gallery_editText:
                Log.e("xi", "gallery_editText: " );
                isShowInput = true;
                InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_share_wx:
                Glide.with(this).load(compress).into(new SimpleTarget<Drawable>() {
                    /**
                     * The method that will be called when the resource load has finished.
                     *
                     * @param resource   the loaded resource.
                     * @param transition
                     */
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Tools.shareWx(NewsDetailWebActivity.this, drawableToBitmap(resource), article_url, SendMessageToWX.Req.WXSceneSession, username, content);
                    }
                });
                break;
            case R.id.ll_share_wxq:
                Glide.with(this).load(compress).into(new SimpleTarget<Drawable>() {
                    /**
                     * The method that will be called when the resource load has finished.
                     *
                     * @param resource   the loaded resource.
                     * @param transition
                     */
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Tools.shareWx(NewsDetailWebActivity.this, drawableToBitmap(resource), article_url, SendMessageToWX.Req.WXSceneTimeline, username, content);
                    }
                });

                break;
            case R.id.ll_share_wb:
                Glide.with(this).load(compress).into(new SimpleTarget<Drawable>() {
                    /**
                     * The method that will be called when the resource load has finished.
                     *
                     * @param resource   the loaded resource.
                     * @param transition
                     */
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Tools.shareWb(NewsDetailWebActivity.this, shareHandler, drawableToBitmap(resource), article_url, "分享来自范团APP的《" + username + "》", "分享来自范团APP的《" + content + "》");
                    }
                });

                break;
            case R.id.ll_share_qq:
                Tools.shareQQ(this, article_url, compress, username, content, new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        Toast.makeText(NewsDetailWebActivity.this, "分享成功!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Toast.makeText(NewsDetailWebActivity.this, "未知错误!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(NewsDetailWebActivity.this, "分享取消!", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.ll_share_qqkj:
                Tools.shareToQzone(this, article_url, compress, username, content, new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        Toast.makeText(NewsDetailWebActivity.this, "分享成功!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Toast.makeText(NewsDetailWebActivity.this, "未知错误!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(NewsDetailWebActivity.this, "分享取消!", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.ll_share_copy:
                ClipboardManager cm = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                if (cm != null) {
                    cm.setPrimaryClip(ClipData.newPlainText("text", article_url));
                    Tools.toastInBottom(NewsDetailWebActivity.this, "复制成功");
                } else {
                    Tools.toastInBottom(NewsDetailWebActivity.this, "复制失败");
                }

                break;
            case R.id.pop_cancel:
                if (pop.isShowing()) {
                    pop.dismiss();
                }
                break;
        }
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

    public void hidePop() {
        if (pop.isShowing()) {
            pop.dismiss();
        }
    }

    @Override
    public void onWbShareSuccess() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_success, Toast.LENGTH_LONG).show();
        this.hidePop();
    }

    @Override
    public void onWbShareCancel() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_canceled, Toast.LENGTH_LONG).show();
        this.hidePop();
    }


    @Override
    public void onWbShareFail() {
        Toast.makeText(this, getString(R.string.weibosdk_demo_toast_share_failed) + "Error Message: ", Toast.LENGTH_LONG).show();
        this.hidePop();
    }
}
