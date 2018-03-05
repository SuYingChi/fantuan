package com.wetime.fanc.circle.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

public class CircleDetailActivity extends BaseActivity implements IGetCircleDetailView {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_detail);
        ButterKnife.bind(this);
        tvTitle.setText("圈子资料");
        GetCircleDetailPresenter getCircleDetailPresenter = new GetCircleDetailPresenter(this);
        getCircleDetailPresenter.getCircleDetail();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.iv_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_user:
                Intent go = new Intent(mContext, UserCardActivity.class);
                go.putExtra("index", 0);
                startActivity(go);
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
    }

    @Override
    public String getCircleId() {
        return getIntent().getStringExtra("id");
    }
}
