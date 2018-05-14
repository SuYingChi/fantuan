package com.wetime.fanc.circle.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.callback.StringCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.ClickNumBean;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2018/5/14.
 */

public class CliclLikeActivity extends BaseActivity implements OnRefreshListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcl_clicklike)
    RecyclerView rclClicklike;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    public static void startToClickLike(Context context, String id) {
        Intent intent = new Intent(context, CliclLikeActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicklike);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText("谁点赞了");
        refreshLayout.setOnRefreshListener(this);
        rclClicklike.setLayoutManager(new LinearLayoutManager(this));
        getClickNub();

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    public void getClickNub() {// 评论需要滚动到评论
        OkHttpUtils
                .post()
                .url(Const.CLICK_NUMBER)
                .addParams("pn", "1")
                .addParams("limit", "10")
                .addParams("id", getIntent().getStringExtra("id"))
                .addHeader("token", spu.getToken())
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        ClickNumBean bean = GsonUtils.getGsonInstance().fromJson(s, ClickNumBean.class);
                        rclClicklike.setAdapter(new CommonAdapter<ClickNumBean.DataBean.ListBean>(CliclLikeActivity.this, R.layout.item_myfriendsbase, bean.getData().getList()) {
                            @Override
                            protected void convert(ViewHolder holder, ClickNumBean.DataBean.ListBean listBean, int position) {
                                holder.itemView.setBackgroundColor(getResources().getColor(R.color.white));
                                holder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent go = new Intent(CliclLikeActivity.this, UserCardActivity.class);
                                        go.putExtra("num", "3");
                                        go.putExtra("index", 0);
                                        go.putExtra("id", listBean.getUid());
                                        CliclLikeActivity.this.startActivity(go);
                                    }
                                });
                                holder.setText(R.id.friend_base_text, listBean.getUsername());
                                holder.setVisible(R.id.friend_base_image, false);
                                Glide.with(CliclLikeActivity.this).load(listBean.getAvatar()).into(((ImageView) holder.getView(R.id.friend_base_head)));
                            }
                        });
                    }
                });
    }

}
