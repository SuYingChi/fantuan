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

import com.king.batterytest.fbaselib.LogoutEvent;
import com.king.batterytest.fbaselib.customview.multiimageselector.MultiImageSelectorActivity;
import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.main.FApp;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.king.batterytest.fbaselib.utils.Tools;
import com.wetime.fanc.R;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.bean.PostFileResultBean;
import com.wetime.fanc.main.ivews.IPostMultiFileView;
import com.wetime.fanc.main.presenter.PostMultiFilePresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.king.batterytest.fbaselib.utils.Tools.REQUEST_IMAGE;


public class WebActivity extends BaseActivity implements IPostMultiFileView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.web_webshow)
    WebView web;
    @BindView(R.id.tv_right)
    TextView tvRight;

    private String weburl;
    private String type = "0";

    private ArrayList<String> defaultDataArray = new ArrayList<>();

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
            new PostMultiFilePresenter(this).PostMultiFile(defaultDataArray);
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
                    if (url.contains("/site/login")) {
                        Tools.logout(WebActivity.this);
                        FApp.getInstance().removeALLActivity();
                        Intent go = new Intent(WebActivity.this, LoginActivity.class);
                        startActivity(go);
                    } else {

                        view.loadUrl(url); // 在当前的webview中跳转到新的url
                    }
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


    @Override
    public void onPostResult(PostFileResultBean bean) {
        String ss = GsonUtils.getGsonInstance().toJson(bean);
        web.loadUrl("javascript:returnPicData('" + ss + "');");
    }


    private void gotoSelectPic() {
        Intent intent = new Intent(mContext, MultiImageSelectorActivity.class);
        // 是否显示调用相机拍照
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 最大图片选择数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, getResources().getInteger(R.integer.most_pic_num));
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
    public void goSelectPic() {
        defaultDataArray.clear();
        gotoSelectPic();
    }

    @JavascriptInterface
    public void logout() {
        Tools.logout(this);
        finish();
        EventBus.getDefault().post(new LogoutEvent());
    }

    @JavascriptInterface
    public void showLoading() {
        Tools.showWaitDialog(this);
    }

    @JavascriptInterface
    public void hideLoading() {
        Tools.hideWaitDialog();
    }

    @JavascriptInterface
    public void hideRightButton() {
        web.post(new Runnable() {
            @Override
            public void run() {
                tvRight.setVisibility(View.GONE);
            }
        });

    }

    @JavascriptInterface
    public void setRightButtonText(final String text) {
        web.post(new Runnable() {
            @Override
            public void run() {
                tvRight.setText(text);
                tvRight.setVisibility(View.VISIBLE);
            }
        });

    }
}


