package com.wetime.fanc.wallet.act;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.customview.Keyboard;
import com.wetime.fanc.customview.PayEditText;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.wallet.iviews.ISetPayPwdView;
import com.wetime.fanc.wallet.presenter.SetPayPwdPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPayPwdActivity extends BaseActivity implements ISetPayPwdView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.payedittext_pay)
    PayEditText payEditText;
    @BindView(R.id.keyboard_pay)
    Keyboard keyboard;


    private static final String[] KEY = new String[]{
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "<<", "0"
    };
    private SetPayPwdPresenter setPayPwdPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pay_pwd);
        ButterKnife.bind(this);
        tvTitle.setText(getIntent().getStringExtra("title"));
        initKeyBorad();
        setPayPwdPresenter = new SetPayPwdPresenter(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_ok:
                if (payEditText.getText().length() != 6) {
                    Tools.toastInBottom(mContext, "请输入完整密码");
                    return;
                }
                setPayPwdPresenter.setPayPwd(payEditText.getText());
                break;

        }
    }

    private void initKeyBorad() {
        //设置键盘
        keyboard.setKeyboardKeys(KEY);
        //键盘键的点击事件
        keyboard.setOnClickKeyboardListener(new Keyboard.OnClickKeyboardListener() {
            @Override
            public void onKeyClick(int position, String value) {
                if (position < 11 && position != 9) {
                    payEditText.add(value);
                } else if (position == 9) {
                    payEditText.remove();
                } else if (position == 11) {
//                    //当点击d键盘上的完成时候，也可以通过payEditText.getText()获取密码，此时不应该注册OnInputFinishedListener接口
//                    Toast.makeText(getApplication(), "您的密码是：" + payEditText.getText(), Toast.LENGTH_SHORT).show();
//                    finish();
                }
            }
        });

    }

    @Override
    public void onSetPayPwd(BaseBean bean) {
        finish();
    }
}
