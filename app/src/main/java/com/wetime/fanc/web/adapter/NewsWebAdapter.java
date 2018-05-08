package com.wetime.fanc.web.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.wetime.fanc.R;
import com.wetime.fanc.web.bean.NewsWebBean;
import com.wetime.fanc.web.bean.NewsWebCommBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/8.
 */

public class NewsWebAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<NewsWebCommBean.DataBean.ListBean> dataComm = new ArrayList<>();
    private List<NewsWebBean.DataBean.ListBean> data = new ArrayList<>();
    private LayoutInflater inflater;
    private String url;

    public NewsWebAdapter(Context context, String url, List<NewsWebCommBean.DataBean.ListBean> dataComm, List<NewsWebBean.DataBean.ListBean> data) {
        this.context = context;
        this.url = url;
        this.dataComm = dataComm;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("xi", "onCreateViewHolder: " + viewType);

        if (viewType == 0) {
            NewsHolder0 newsHolder0 = new NewsHolder0(inflater.inflate(R.layout.item_web, parent, false));
//            initWebView(newsHolder0.webview);
//            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            newsHolder0.webview.setLayoutParams(lp);
            return newsHolder0;
        } else {
            return new NewsHolder1(inflater.inflate(R.layout.item_web_ather, parent, false));
        }
//        if (viewType == 0) {
//            return new NewsHolder1(inflater.inflate(R.layout.item_web_ather, parent, false));
//        } else {
//            return new NewsHolder1(inflater.inflate(R.layout.item_web_ather, parent, false));
//        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e("xi", "onBindViewHolder: " + position);
        Log.e("xi", "onBindViewHolder: " + (holder instanceof NewsHolder0));

        if (holder instanceof NewsHolder0) {
            ((NewsHolder0) holder).webview.loadUrl(url);
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    public void initWebView(WebView mWebView) {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setLoadsImagesAutomatically(true);
        mWebView.getSettings().setLayoutAlgorithm
                (WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
    }

    // 一张小图
    class NewsHolder0 extends RecyclerView.ViewHolder {
        @BindView(R.id.web_webview)
        WebView webview;


        NewsHolder0(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    // 小图加文字
    class NewsHolder1 extends RecyclerView.ViewHolder {


        NewsHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
