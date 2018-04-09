package com.wetime.fanc.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.barlibrary.ImmersionBar;
import com.secure.pay.PayService;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.wetime.fanc.R;
import com.wetime.fanc.application.FApp;
import com.wetime.fanc.circle.act.ActDetailActivity;
import com.wetime.fanc.customview.multiimageselector.MultiImageSelectorActivity;
import com.wetime.fanc.home.act.HomeSearchActivity;
import com.wetime.fanc.home.event.BeInvaterSuccess;
import com.wetime.fanc.home.event.SwichFragEvent;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.login.event.LogoutEvent;
import com.wetime.fanc.login.event.WXBindPhoneEvent;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.bean.PostFileResultBean;
import com.wetime.fanc.main.ivews.IPostMultiFileView;
import com.wetime.fanc.main.presenter.PostMultiFilePresenter;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.order.act.CommentOrderActivity;
import com.wetime.fanc.order.event.RefreshOrderEvent;
import com.wetime.fanc.shop.act.ShopDetailActivity;
import com.wetime.fanc.shop.act.ShopNewsHomeActivity;
import com.wetime.fanc.shop.act.ShopSayActivity;
import com.wetime.fanc.shopcenter.act.ShopListActivity;
import com.wetime.fanc.shopcenter.act.ShopSearchActivity;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.wallet.act.InviteHomeActivity;
import com.wetime.fanc.wallet.act.MyWalletActivity;
import com.wetime.fanc.wallet.act.VerfyPhoneNumActivity;
import com.wetime.fanc.web.event.FinishWebEvent;
import com.wetime.fanc.weibo.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.shaohui.bottomdialog.BottomDialog;

import static com.wetime.fanc.utils.Tools.REQUEST_IMAGE;


public class WebActivity extends BaseActivity implements IPostMultiFileView, WbShareCallback {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.web_webshow)
    WebView web;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.progressBar1)
    ProgressBar pg1;
    @BindView(R.id.iv_temp)
    ImageView ivTemp;

    private String weburl;
    private String type = "0";//2 from news

    private BottomDialog mBottomDialog;
    private Intent intent;
    private ArrayList<String> defaultDataArray = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //商户需将同步返回的报文送至服务器端验签
            if (msg.what == PayService.PAY) {
                if (msg.obj == null || ((JSONObject) msg.obj).length() == 0) {
//                    mEtResult.setText("返回空");
//                    Tools.toastInBottom(mContext, "返回空");
                } else {
                    final String result = msg.obj.toString();
                    web.post(() -> web.loadUrl("javascript:receivePayResult('" + result + "');"));

                }
            }
        }
    };
    private WbShareHandler shareHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        intent = getIntent();
        weburl = intent.getStringExtra("url");
        web.loadUrl(weburl);
//        web.loadUrl("http:192.168.11.178:3001/#/inviter/redpack");
//        web.reload();
//        web.loadUrl("file:///android_asset/test.html");
//        Log.e("weburl", weburl);

        // type 1  商家动态
        if (intent.getStringExtra("type") != null) {
            type = intent.getStringExtra("type");
            if (type.equals("1")) {
                tvRight.setText("发布");
                tvRight.setVisibility(View.VISIBLE);
            }
        }
        if (intent.getStringExtra("title") != null) {
            tvTitle.setText(intent.getStringExtra("title"));
        }
        webSetting();
        WbSdk.install(this, new AuthInfo(this, Constants.APP_KEY, weburl, Constants.SCOPE));
        shareHandler = new WbShareHandler(this);
        shareHandler.registerApp();
    }

    @Override
    public void initStateBar() {
//        super.initStateBar();
        ImmersionBar.with(this).statusBarColor(R.color.white_lib)
                .statusBarDarkFont(true, 0.2f)
                .fitsSystemWindows(true).keyboardEnable(true).init();

    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) {
            web.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                defaultDataArray = data
                        .getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);

            }
            if (defaultDataArray.size() > 0)
                new PostMultiFilePresenter(this).PostMultiFile(defaultDataArray);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
//        mWebViewContainer.removeView(web);
//        web.stopLoading();
//        web.getSettings().setJavaScriptEnabled(false);
//        web.clearHistory();
//        web.removeAllViews();
//        web.destroy();
        super.onDestroy();
    }

    @OnClick({R.id.btn_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.tv_right:
                web.post(() -> web.loadUrl("javascript:window.onSaveButtonClick();"));

                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final LoginEvent event) {
        web.post(() -> web.loadUrl("javascript:receiveLoginResult('" + event.getStr() + "');"));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final FinishWebEvent event) {
        web.post(() -> finish());
    }

    @SuppressLint("JavascriptInterface")
    private void webSetting() {

        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (!type.equals("2")) {
                    tvTitle.setText(view.getTitle());
                }
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根
                if (intent.getStringExtra("type") != null
                        && intent.getStringExtra("type").equals("2")) {
                    if (newProgress == 100) {
                        pg1.setVisibility(View.GONE);//加载完网页进度条消失
                        ivTemp.setVisibility(View.GONE);
                    } else {
                        ivTemp.setVisibility(View.VISIBLE);
                        pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                        pg1.setProgress(newProgress);//设置进度值
                    }
                }
            }
        };

        // 触摸焦点起作用
        web.requestFocus();
        // 取消滚动条
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings setting = web.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setAllowFileAccess(true);// 设置允许访问文件数据
        setting.setSupportZoom(true);
        setting.setBuiltInZoomControls(false);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setCacheMode(WebSettings.LOAD_DEFAULT);
        setting.setDomStorageEnabled(true);
        setting.setAppCacheEnabled(true);
        setting.setLoadWithOverviewMode(true);
        setting.setDatabaseEnabled(true);
        web.addJavascriptInterface(this, "android");
        web.setWebChromeClient(wvcc);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (!type.equals("2")) {
                    tvTitle.setText(view.getTitle());
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("zk", "url=" + url);
                if (url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else {
                    view.loadUrl(url); // 在当前的webview中跳转到新的url
                }
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
//                view.loadUrl("file:///android_asset/error_tip.html");
            }
        });

    }

    @JavascriptInterface
    public String getToken() {
        return spu.getToken();
    }

    @JavascriptInterface
    public String getMid() {
        return spu.getValue("mid");
    }

    @JavascriptInterface
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @JavascriptInterface
    public String getLongitude() {
        return spu.getValue("jd");
    }

    @JavascriptInterface
    public String getLatitude() {
        return spu.getValue("wd");
    }

    @JavascriptInterface
    public String isFanTuan() {
        return "1";
    }

    @JavascriptInterface
    public void finishWebView() {
        finish();
    }

    // 首页搜索
    @JavascriptInterface
    public void goHomeSearchPage() {
        web.post(() -> {
            Intent gosearch = new Intent(getApplicationContext(), HomeSearchActivity.class);
            startActivity(gosearch);
        });
    }

    // 去商城搜索页面
    @JavascriptInterface
    public void goShopSearchPage(final String id, final String name) {
        web.post(() -> {
            Intent goSearch = new Intent(getApplicationContext(), ShopSearchActivity.class);
            goSearch.putExtra("id", id);
            goSearch.putExtra("id", name);
            startActivity(goSearch);
        });
    }

    // 购物中心首页
    @JavascriptInterface
    public void goMallHomePage(final String title, final String floorId, final String cenerId, final String floorName) {
        web.post(() -> {

            Intent gom = new Intent(getApplicationContext(), ShopListActivity.class);
            gom.putExtra("title", title);
            gom.putExtra("floorId", floorId);
            gom.putExtra("cenerId", cenerId);
            gom.putExtra("floorName", floorName);
            startActivity(gom);
        });
    }

    // 评价订单
    @JavascriptInterface
    public void goReview(final String orderId) {
        web.post(() -> {
            Intent goComment = new Intent(getApplicationContext(), CommentOrderActivity.class);
            goComment.putExtra("id", orderId);
            startActivity(goComment);
        });
    }

    @JavascriptInterface
    public void setRightShopSearch(final String id, final String name) {
        web.post(() -> {
            ivRight.setVisibility(View.VISIBLE);
            ivRight.setOnClickListener(view -> {
                Intent goSearch = new Intent(getApplicationContext(), ShopSearchActivity.class);
                goSearch.putExtra("id", id);
                goSearch.putExtra("name", name);
                startActivity(goSearch);
            });

        });

    }

    @JavascriptInterface
    public void hideRightButton() {
        web.post(() -> {
            tvRight.setVisibility(View.GONE);
            ivRight.setVisibility(View.GONE);
        });

    }

    @JavascriptInterface
    public void goPayView(final String str) {
        web.post(() -> PayService.pay(WebActivity.this, mHandler, str, PayService.TYPE_NORMAL, 1));

    }

    @JavascriptInterface
    public void pastToken() {
        spu.setToken("");
    }

    @JavascriptInterface
    public void goHomePage() {
        web.post(() -> {
            EventBus.getDefault().post(new SwichFragEvent(0));
            finish();
        });

    }

    @JavascriptInterface
    public void nativeLogin() {
        web.post(() -> {
            Intent goLogin = new Intent(mContext, LoginActivity.class);
            startActivity(goLogin);
        });
    }

    @JavascriptInterface
    public void reloadOrderData() {
        web.post(() -> EventBus.getDefault().post(new RefreshOrderEvent()));

    }

    @JavascriptInterface
    public void showLoading() {
        web.post(() -> Tools.showWaitDialog(mContext));
    }

    @JavascriptInterface
    public void hideLoading() {
        web.post(() -> Tools.hideWaitDialog());
    }

    @JavascriptInterface
    public void showTipsDialog(final String tips, final String left, final String right, final String leftm, final String rightm) {
        web.post(() -> Tools.showTipsDialog(mContext, "", tips, left, right, v -> web.post(() -> web.loadUrl("javascript:" + leftm + "();")), v -> web.post(() -> web.loadUrl("javascript:" + rightm + "();"))));
    }

    @JavascriptInterface
    public void logout() {
        web.post(() -> {
            Tools.logout(mContext);
            EventBus.getDefault().post(new LogoutEvent());
            FApp.getInstance().removeALLActivity();
        });
    }

    @JavascriptInterface
    public void setRightButtonText(final String text) {
        web.post(() -> {
            tvRight.setText(text);
            tvRight.setVisibility(View.VISIBLE);
            if (text.equals(""))
                tvRight.setVisibility(View.GONE);
        });
    }

    @JavascriptInterface
    public void goShopDetail(final String mid) {
        web.post(() -> {
            Intent goShop = new Intent(mContext, ShopDetailActivity.class);
            goShop.putExtra("mid", mid);
            startActivity(goShop);
        });
    }

    @JavascriptInterface
    public void goShopSay(String mid, String name, String score, String imageUrl) {
        web.post(() -> {
            Intent goShop = new Intent(mContext, ShopSayActivity.class);
            goShop.putExtra("mid", mid);
            goShop.putExtra("url", imageUrl);
            goShop.putExtra("name", name);
            goShop.putExtra("score", score);
            startActivity(goShop);
        });
    }

    @JavascriptInterface
    public void wxbindPhoneSuccess() {
        web.post(() -> EventBus.getDefault().post(new WXBindPhoneEvent()));
    }

    @JavascriptInterface
    public void beInvateSuccess() {
        web.post(() -> EventBus.getDefault().post(new BeInvaterSuccess()));
    }

    @JavascriptInterface
    public void goInviterCenter() {
        web.post(() -> Tools.goActivity(mContext, InviteHomeActivity.class));
    }

    @JavascriptInterface
    public void goUserWallet() {
        web.post(() -> Tools.goActivity(mContext, MyWalletActivity.class));
    }

    @JavascriptInterface
    public void goModifyPassword(String phone) {
        web.post(() -> {
            Intent go = new Intent(mContext, VerfyPhoneNumActivity.class);
            go.putExtra("phone", phone);
            startActivity(go);
        });
    }

    @JavascriptInterface
    public void goShopNews(String mid) {
        web.post(() -> {
            Intent goShopnews = new Intent(this, ShopNewsHomeActivity.class);
            goShopnews.putExtra("mid", mid);
            startActivity(goShopnews);
        });
    }

    @JavascriptInterface
    public void goUserCard(String uid, boolean isNews, int index) {
        web.post(() -> {
            Intent go = new Intent(mContext, UserCardActivity.class);
            go.putExtra("num", isNews ? "3" : "2");
            go.putExtra("index", index);
            go.putExtra("id", uid);
            startActivity(go);
        });
    }

    @JavascriptInterface
    public void goActDetail(String id) {
        web.post(() -> {
            Intent goDet = new Intent(mContext, ActDetailActivity.class);
            goDet.putExtra("id", id);
            startActivity(goDet);
        });
    }

    @JavascriptInterface
    public void shareWXFriends(String url, String title, String des,String imageUrl) {
        web.post(() -> {
            Glide.with(mContext).load(imageUrl).into(new SimpleTarget<Drawable>() {
                /**
                 * The method that will be called when the resource load has finished.
                 *
                 * @param resource   the loaded resource.
                 * @param transition
                 */
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    Tools.shareWx(mContext, drawableToBitmap(resource), weburl, SendMessageToWX.Req.WXSceneSession, title, des);
                }
            });
        });
    }

    @JavascriptInterface
    public void shareWXTimeline(String url, String title, String des,String imageUrl) {
        web.post(() -> {
            Glide.with(mContext).load(imageUrl).into(new SimpleTarget<Drawable>() {
                /**
                 * The method that will be called when the resource load has finished.
                 *
                 * @param resource   the loaded resource.
                 * @param transition
                 */
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    Tools.shareWx(mContext, drawableToBitmap(resource), weburl, SendMessageToWX.Req.WXSceneTimeline, title, des);
                }
            });
        });
    }

    @JavascriptInterface
    public void copyUrl(String url) {
        web.post(() -> {
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // 将文本内容放到系统剪贴板里。
            cm.setPrimaryClip(ClipData.newPlainText("text", url));
            Tools.toastInBottom(mContext, "复制成功");
        });
    }

    @Override
    public void onPostResult(PostFileResultBean bean) {
        final String ss = GsonUtils.getGsonInstance().toJson(bean.getData());
        web.post(() -> web.loadUrl("javascript:returnPicData('" + ss + "');"));
    }

    private void gotoSelectPic(String num) {
        int n = Integer.valueOf(num);
        if (n <= 0)
            return;

        Intent intent = new Intent(mContext, MultiImageSelectorActivity.class);
        // 是否显示调用相机拍照
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 最大图片选择数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, n);
        // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者
        // 多选/MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,
                MultiImageSelectorActivity.MODE_MULTI);
        // 默认选择图片,回填选项(支持String ArrayList)
        // ArrayList<String> temp = new ArrayList<String>();
        // for (int i = 0; i < defaultDataArray.size(); i++) {
        // temp.add(defaultDataArray.get(i));
        // }
        intent.putStringArrayListExtra(
                MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST,
                defaultDataArray);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @JavascriptInterface
    public void goSelectPic(final String num) {
        web.post(() -> {
            defaultDataArray.clear();
            gotoSelectPic(num);
        });

    }

    @JavascriptInterface
    public void shareView(String title, String des, String url) {
        web.post(() -> {
            if (mBottomDialog == null) {
                mBottomDialog = BottomDialog.create(getSupportFragmentManager());
                mBottomDialog.setLayoutRes(R.layout.item_share_bottom);
                mBottomDialog.setViewListener(v -> {
                    v.findViewById(R.id.ll_share_wx).setOnClickListener(v1 -> {
                        mBottomDialog.dismiss();
                        Glide.with(mContext).load(url).into(new SimpleTarget<Drawable>() {
                            /**
                             * The method that will be called when the resource load has finished.
                             *
                             * @param resource   the loaded resource.
                             * @param transition
                             */
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                Tools.shareWx(mContext, drawableToBitmap(resource), weburl, SendMessageToWX.Req.WXSceneSession, title, des);
                            }
                        });

                    });
                    v.findViewById(R.id.ll_share_wxq).setOnClickListener(v12 -> {
                        mBottomDialog.dismiss();
                        Log.e("xi", "shareView: "+url );
                        Glide.with(mContext).load(url).into(new SimpleTarget<Drawable>() {
                            /**
                             * The method that will be called when the resource load has finished.
                             *
                             * @param resource   the loaded resource.
                             * @param transition
                             */
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                Tools.shareWx(mContext, drawableToBitmap(resource), weburl, SendMessageToWX.Req.WXSceneTimeline, title, des);
                            }
                        });

                    });
                    v.findViewById(R.id.ll_share_wb).setOnClickListener(v12 -> {
                        mBottomDialog.dismiss();
                        Glide.with(mContext).load(url).into(new SimpleTarget<Drawable>() {
                            /**
                             * The method that will be called when the resource load has finished.
                             *
                             * @param resource   the loaded resource.
                             * @param transition
                             */
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                Tools.shareWb(mContext, shareHandler, drawableToBitmap(resource), weburl, title, des);
                            }
                        });

//                        Toast.makeText(mContext, "功能正在开发中!", Toast.LENGTH_SHORT).show();
                    });
                    v.findViewById(R.id.ll_share_qq).setOnClickListener(v12 -> {
                        mBottomDialog.dismiss();
                        Tools.shareQQ((Activity) mContext, weburl, url, title, des, new IUiListener() {
                            @Override
                            public void onComplete(Object o) {
                                Toast.makeText(mContext, "分享成功!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(UiError uiError) {
                                Toast.makeText(mContext, "未知错误!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(mContext, "分享取消!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    });
                    v.findViewById(R.id.ll_share_qqkj).setOnClickListener(v12 -> {
                        mBottomDialog.dismiss();
                        Tools.shareToQzone((Activity) mContext, weburl, url, title, des, new IUiListener() {
                            @Override
                            public void onComplete(Object o) {
                                Toast.makeText(mContext, "分享成功!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(UiError uiError) {
                                Toast.makeText(mContext, "未知错误!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(mContext, "分享取消!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    });
                    v.findViewById(R.id.ll_share_copy).setOnClickListener(v13 -> {
                        mBottomDialog.dismiss();
                        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        // 将文本内容放到系统剪贴板里。
                        cm.setPrimaryClip(ClipData.newPlainText("text", weburl));
                        Tools.toastInBottom(mContext, "复制成功");
                    });
                    v.findViewById(R.id.tv_cancel).setOnClickListener(v14 -> mBottomDialog.dismiss());
                });
            }
            mBottomDialog.show();
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);


        shareHandler.doResultIntent(intent, this);

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

    @JavascriptInterface
    public void allowLocation() {
        web.post(() -> Tools.showTipsDialog(mContext, "", "需开启定位且位置为海南才可领取红包",
                "取消", "开启定位", null, v -> {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
                    startActivity(intent);
                }));
    }


    @Override
    public void onWbShareSuccess() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_success, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onWbShareCancel() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_canceled, Toast.LENGTH_LONG).show();

    }


    @Override
    public void onWbShareFail() {
        Toast.makeText(this, getString(R.string.weibosdk_demo_toast_share_failed) + "Error Message: ", Toast.LENGTH_LONG).show();

    }
}


