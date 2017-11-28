package com.wetime.fanc.web;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.king.batterytest.fbaselib.main.BaseActivity;
import com.wetime.fanc.R;
import com.wetime.fanc.home.act.HomeSearchActivity;
import com.wetime.fanc.order.act.CommentOrderActivity;
import com.wetime.fanc.shopcenter.act.ShopListActivity;
import com.wetime.fanc.shopcenter.act.ShopSearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WebActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.web_webshow)
    WebView web;
    @BindView(R.id.tv_right)
    TextView tvRight;

    private String weburl;
    private String type = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        weburl = intent.getStringExtra("url");
        web.loadUrl(weburl);
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

        webSetting();
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
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }


    @OnClick({R.id.btn_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.tv_right:


                break;
        }
    }


    @SuppressLint("JavascriptInterface")
    private void webSetting() {

        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                tvTitle.setText(title);

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
                tvTitle.setText(view.getTitle());
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
        return spu.getValue("token");
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
    public void finishWebView() {
        finish();
    }

    // 首页搜索
    @JavascriptInterface
    public void goHomeSearchPage() {
        Intent gosearch = new Intent(this, HomeSearchActivity.class);
        startActivity(gosearch);
    }

    // 去商城搜索页面
    @JavascriptInterface
    public void goShopSearchPage() {
        Intent goSearch = new Intent(this, ShopSearchActivity.class);
        startActivity(goSearch);
    }

    // 购物中心首页
    @JavascriptInterface
    public void goMallHomePage(String title, String floorId, String cenerId) {
        Intent gom = new Intent(this, ShopListActivity.class);
        gom.putExtra("title", title);
        gom.putExtra("floorId", floorId);
        gom.putExtra("cenerId", cenerId);
        startActivity(gom);
    }

    // 评价订单
    @JavascriptInterface
    public void goCommnetOrder(String orderId) {
        Intent goComment = new Intent(this, CommentOrderActivity.class);
        goComment.putExtra("id", orderId);
        startActivity(goComment);
    }
}


