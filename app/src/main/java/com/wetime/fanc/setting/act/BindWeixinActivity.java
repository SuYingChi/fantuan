package com.wetime.fanc.setting.act;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.king.batterytest.fbaselib.utils.Tools;
import com.wetime.fanc.R;
import com.wetime.fanc.setting.bean.SettingPageBean;
import com.wetime.fanc.setting.event.UnBindWXEvent;
import com.wetime.fanc.setting.iviews.IUnBindView;
import com.wetime.fanc.setting.presenter.UnBindWeiXinPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class BindWeixinActivity extends BaseActivity implements IUnBindView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;

    private SettingPageBean.DataBean.WeixinBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unbindwx);
        ButterKnife.bind(this);
        tvTitle.setText("微信绑定");
        bean = (SettingPageBean.DataBean.WeixinBean) getIntent().getSerializableExtra("weixin");
        tvName.setText(bean.getNickname());
        Glide.with(this).load(bean.getAvatar()).into(civHead);
        tvTime.setText(bean.getTime());


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.tv_unbind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_unbind:
                Tools.showTipsDialog(this, "","是否解除绑定微信号?","取消","解除绑定", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UnBindWeiXinPresenter unBindWeiXinPresenter = new UnBindWeiXinPresenter(BindWeixinActivity.this);
                        unBindWeiXinPresenter.unBindWeixin();
                    }
                });
                break;

        }
    }

    @Override
    public void onUnbindWeixin(BaseBean bean) {
        if (bean.getError() == 0) {
            EventBus.getDefault().post(new UnBindWXEvent());
            finish();
        }
    }
}
