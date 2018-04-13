package com.wetime.fanc.circle.act;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.CircleDetailBean;
import com.wetime.fanc.circle.iviews.IGetCircleDetailView;
import com.wetime.fanc.circle.presenter.GetCircleDetailPresenter;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.my.act.UserCardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CircleInfoActivity extends BaseActivity implements IGetCircleDetailView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_circlename)
    TextView tvCirclename;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_user)
    CircleImageView ivUser;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_rule)
    TextView tvRule;
    @BindView(R.id.iv_circle)
    CircleImageView ivCircle;
    @BindView(R.id.tv_attention)
    TextView tvAttention;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.rcl_my_tv)
    TextView rclMyTv;
    @BindView(R.id.rcl_my_recyclerView)
    RecyclerView rclMyRecyclerView;
    @BindView(R.id.rcl_my_linear)
    LinearLayout rclMyLinear;
    @BindView(R.id.bt_attrntion)
    Button btAttrntion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_detailinfo);
        ButterKnife.bind(this);
        tvTitle.setText("圈子资料");
        GetCircleDetailPresenter getCircleDetailPresenter = new GetCircleDetailPresenter(this);
        getCircleDetailPresenter.getCircleDetail();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.bt_attrntion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.bt_attrntion:
                if (String.valueOf(btAttrntion.getText()).equals("关注圈子")) {
                    btAttrntion.setText("取消关注圈子");
                    btAttrntion.setTextColor(Color.parseColor("#666666"));
                    btAttrntion.setBackgroundResource(R.drawable.rectangle_3_copy_cancel);
                } else {
                    btAttrntion.setText("关注圈子");
                    btAttrntion.setTextColor(Color.parseColor("#ff3f53"));
                    btAttrntion.setBackgroundResource(R.drawable.rectangle_3_copy);
                }
                break;
        }
    }

    @Override
    public void onGetCircleDetail(CircleDetailBean bean) {
        Glide.with(this).load(bean.getData().getCover()).into(ivCircle);
        tvCirclename.setText(bean.getData().getName());
        tvTime.setText(bean.getData().getTime());
        Glide.with(this).load(bean.getData().getAvatar()).into(ivUser);
        tvUsername.setText(bean.getData().getUsername());
        tvDes.setText(bean.getData().getIntro());
        tvRule.setText(bean.getData().getRule());
        ivUser.setOnClickListener(view -> {
            Intent go = new Intent(mContext, UserCardActivity.class);
            go.putExtra("index", 0);
            go.putExtra("id", bean.getData().getUid());
            go.putExtra("num", bean.getData().isIs_news() ? "3" : "2");
            startActivity(go);
        });
    }

    @Override
    public String getCircleId() {
        return getIntent().getStringExtra("id");
    }
}
