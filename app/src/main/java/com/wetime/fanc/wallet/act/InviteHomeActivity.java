package com.wetime.fanc.wallet.act;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.wallet.bean.InviteHomeBean;
import com.wetime.fanc.wallet.iviews.IGetInviteHomeView;
import com.wetime.fanc.wallet.presenter.GetInviteHomePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteHomeActivity extends BaseActivity implements IGetInviteHomeView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.tv_cashout)
    TextView tvCashout;
    @BindView(R.id.tv_inviteqr)
    TextView tvInviteqr;
    @BindView(R.id.tv_invitehistory)
    TextView tvInvitehistory;
    @BindView(R.id.tv_authentication)
    TextView tvAuthentication;
    @BindView(R.id.tv_rule)
    TextView tvRule;
    @BindView(R.id.tv_num)
    TextView tvNum;

    private GetInviteHomePresenter getInviteHomePresenter;
    private InviteHomeBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invatehome);
        ButterKnife.bind(this);
        tvTitle.setText("邀请有礼");

        getInviteHomePresenter = new GetInviteHomePresenter(this);
        getInviteHomePresenter.getInviteHome();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.tv_cashout, R.id.tv_inviteqr, R.id.tv_invitehistory,
            R.id.tv_authentication, R.id.tv_rule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_cashout:
                Tools.goActivity(mContext, MyWalletActivity.class);
                break;
            case R.id.tv_inviteqr:
                if (bean != null) {
                    Intent go = new Intent(mContext, InviteQRActivity.class);
                    go.putExtra("url", bean.getData().getUrl_qrcode());
                    go.putExtra("shareurl", bean.getData().getUrl_redbag());
                    startActivity(go);
                }
                break;
            case R.id.tv_invitehistory:
                if (bean != null) {
                    Tools.goActivity(mContext, InviteHistoryActivity.class);
                }
                break;
            case R.id.tv_authentication:
                if (bean != null) {
                    Tools.goWeb(mContext, bean.getData().getUrl_cert());
                }
                break;
            case R.id.tv_rule:
                if (bean != null) {
                    Tools.goWeb(mContext, bean.getData().getUrl_rule());
                }
                break;
        }
    }

    @Override
    public void onGetInvite(InviteHomeBean bean) {
        this.bean = bean;
        if (!TextUtils.isEmpty(bean.getData().getTips())) {
            tvTips.setVisibility(View.VISIBLE);
            tvTips.setText(bean.getData().getTips());
        }
        tvNum.setText(bean.getData().getTotal_money());


    }
}
