package com.wetime.fanc.wallet.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wetime.fanc.R;
import com.wetime.fanc.application.FApp;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.setting.iviews.IWXBindView;
import com.wetime.fanc.setting.presenter.WXBindPresenter;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.wallet.bean.WalletBalanceBean;
import com.wetime.fanc.wallet.iviews.IGetWalletBalanceView;
import com.wetime.fanc.wallet.presenter.GetWalletBalancePresenter;
import com.wetime.fanc.wxapi.WXLoginCodeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends BaseActivity implements IGetWalletBalanceView, IWXBindView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.tv_pay_pwd)
    TextView tvPayPwd;
    @BindView(R.id.tv_cashout)
    TextView tvCashout;
    private GetWalletBalancePresenter getWalletBalancePresenter;
    private WXBindPresenter wxBindPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywallet);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        tvTitle.setText("我的钱包");
        getWalletBalancePresenter = new GetWalletBalancePresenter(this);
        wxBindPresenter = new WXBindPresenter(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWalletBalancePresenter.getWalletBalance();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back,R.id.tv_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_detail:
                Tools.goActivity(mContext,BalanceDetailActivity.class);
                break;
        }
    }

    @Override
    public void onGetWalletBalance(final WalletBalanceBean bean) {
        tvNum.setText(bean.getData().getMoney());
        tvTips.setText(String.format("余额需大于等于%s元才可提现哦~"
                , bean.getData().getWithdraw_money_min()));

        tvPayPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getData().getHas_set_pwd().equals("0")) {
                    Tools.showTipsDialog(mContext, "", "需设置支付密码后才可提现哦",
                            "取消", "设置支付密码", null,
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent go = new Intent(mContext, SetPayPwdActivity.class);
                                    go.putExtra("title", "设置支付密码");
                                    startActivity(go);
                                }
                            });
                } else {
                    Tools.showTipsDialog(mContext, "", "确定修改支付密码",
                            "取消", "修改", null,
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent go = new Intent(mContext, VerfyPhoneNumActivity.class);
                                    go.putExtra("phone", bean.getData().getPhone());
                                    startActivity(go);
                                }
                            });
                }
            }
        });


        tvCashout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getData().getHas_bind_wechat().equals("1")) {
                    Intent go = new Intent(mContext, CashOutActivity.class);
                    startActivity(go);
                } else {
                    Tools.showTipsDialog(mContext, "", "将提现到微信钱包中因此需要绑定微信号哦",
                            "取消", "立即绑定", null,
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    wxLogin();
                                }
                            });
                }
            }
        });
    }

    private void wxLogin() {
        if (!FApp.mWxApi.isWXAppInstalled()) {
            Tools.toastInBottom(this, "您没有安装微信");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        FApp.mWxApi.sendReq(req);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WXLoginCodeEvent event) {
        wxBindPresenter.getBindResult(event.getCode());
    }

    @Override
    public void onBindResult(BaseBean bean) {
        if (bean.getError() == 0) {
            getWalletBalancePresenter.getWalletBalance();
        }
    }
}
