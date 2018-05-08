package com.wetime.fanc.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.web.adapter.NewsWebAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/8.
 */

public class NewsDetailWebActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.web_recyclerview)
    RecyclerView webRecyclerview;

    private String url = "";
    private String id = "";
    private String read_num = "";

    public static void starToWeb(Context context, String url, String id, String read_num) {
        Intent intent = new Intent(context, NewsDetailWebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("id", id);
        intent.putExtra("read_num", read_num);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetailweb);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        url = getIntent().getStringExtra("url");
        id = getIntent().getStringExtra("id");
        read_num = getIntent().getStringExtra("read_num");
        LinearLayoutManager layout = new LinearLayoutManager(this);
        webRecyclerview.setLayoutManager(layout);
        webRecyclerview.setAdapter(new NewsWebAdapter(this, url,null, null));
    }

}
