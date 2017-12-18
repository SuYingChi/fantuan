package com.wetime.fanc.web;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.utils.Tools;
import com.secure.pay.PayService;
import com.wetime.fanc.R;
import com.wetime.fanc.home.act.HomeSearchActivity;
import com.wetime.fanc.home.event.SwichFragEvent;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.login.event.LogoutEvent;
import com.wetime.fanc.order.act.CommentOrderActivity;
import com.wetime.fanc.order.event.RefreshOrderEvent;
import com.wetime.fanc.shopcenter.act.ShopListActivity;
import com.wetime.fanc.shopcenter.act.ShopSearchActivity;
import com.wetime.fanc.web.event.FinishWebEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

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
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.iv_right)
    ImageView ivRight;

    private String weburl;
    private String type = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final LoginEvent event) {
        web.post(new Runnable() {
            @Override
            public void run() {
                web.loadUrl("javascript:receiveLoginResult('" + event.getStr() + "');");
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final FinishWebEvent event) {
        web.post(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
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
        web.post(new Runnable() {
            @Override
            public void run() {
                Intent gosearch = new Intent(getApplicationContext(), HomeSearchActivity.class);
                startActivity(gosearch);
            }
        });
    }

    // 去商城搜索页面
    @JavascriptInterface
    public void goShopSearchPage(final String id, final String name) {
        web.post(new Runnable() {
            @Override
            public void run() {
                Intent goSearch = new Intent(getApplicationContext(), ShopSearchActivity.class);
                goSearch.putExtra("id", id);
                goSearch.putExtra("id", name);
                startActivity(goSearch);
            }
        });
    }

    // 购物中心首页
    @JavascriptInterface
    public void goMallHomePage(final String title, final String floorId, final String cenerId, final String floorName) {
        web.post(new Runnable() {
            @Override
            public void run() {

                Intent gom = new Intent(getApplicationContext(), ShopListActivity.class);
                gom.putExtra("title", title);
                gom.putExtra("floorId", floorId);
                gom.putExtra("cenerId", cenerId);
                gom.putExtra("floorName", floorName);
                startActivity(gom);
            }
        });
    }


    // 评价订单
    @JavascriptInterface
    public void goReview(final String orderId) {
        web.post(new Runnable() {
            @Override
            public void run() {
                Intent goComment = new Intent(getApplicationContext(), CommentOrderActivity.class);
                goComment.putExtra("id", orderId);
                startActivity(goComment);
            }
        });
    }

    @JavascriptInterface
    public void setRightShopSearch(final String id, final String name) {
        web.post(new Runnable() {
            @Override
            public void run() {
                ivRight.setVisibility(View.VISIBLE);
                ivRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent goSearch = new Intent(getApplicationContext(), ShopSearchActivity.class);
                        goSearch.putExtra("id", id);
                        goSearch.putExtra("name", name);
                        startActivity(goSearch);
                    }
                });

            }
        });

    }

    @JavascriptInterface
    public void hideRightButton() {
        web.post(new Runnable() {
            @Override
            public void run() {
                tvRight.setVisibility(View.GONE);
                ivRight.setVisibility(View.GONE);
            }
        });

    }

    @JavascriptInterface
    public void goPayView(final String str) {
        web.post(new Runnable() {
            @Override
            public void run() {
                PayService.pay(WebActivity.this, mHandler, str, PayService.TYPE_NORMAL, 1);
            }
        });

    }

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
                    web.post(new Runnable() {
                        @Override
                        public void run() {
                            web.loadUrl("javascript:receivePayResult('" + result + "');");
                        }
                    });

                }
            }
        }
    };

    @JavascriptInterface
    public void pastToken() {
        spu.setToken("");
    }

    @JavascriptInterface
    public void goHomePage() {
        web.post(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new SwichFragEvent(0));
                finish();
            }
        });

    }

    @JavascriptInterface
    public void nativeLogin() {
        web.post(new Runnable() {
            @Override
            public void run() {
                Intent goLogin = new Intent(mContext, LoginActivity.class);
                startActivity(goLogin);
            }
        });
    }

    @JavascriptInterface
    public void reloadOrderData() {
        web.post(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new RefreshOrderEvent());
            }
        });

    }

    @JavascriptInterface
    public void showLoading() {
        web.post(new Runnable() {
            @Override
            public void run() {
                Tools.showWaitDialog(mContext);
            }
        });
    }

    @JavascriptInterface
    public void hideLoading() {
        web.post(new Runnable() {
            @Override
            public void run() {
                Tools.hideWaitDialog();
            }
        });
    }

    @JavascriptInterface
    public void showTipsDialog(final String tips, final String left, final String right, final String leftm, final String rightm) {
        web.post(new Runnable() {
            @Override
            public void run() {
                Tools.showTipsDialog(mContext, tips, left, right, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        web.post(new Runnable() {
                            @Override
                            public void run() {
                                web.loadUrl("javascript:" + leftm + "();");
                            }
                        });
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        web.post(new Runnable() {
                            @Override
                            public void run() {
                                web.loadUrl("javascript:" + rightm + "();");
                            }
                        });
                    }
                });
            }
        });
    }

    @JavascriptInterface
    public void logout() {
        web.post(new Runnable() {
            @Override
            public void run() {
                Tools.logout(mContext);
                EventBus.getDefault().post(new LogoutEvent());
            }
        });
    }


}


