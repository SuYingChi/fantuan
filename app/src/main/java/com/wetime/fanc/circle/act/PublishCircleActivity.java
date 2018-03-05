package com.wetime.fanc.circle.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishCircleActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSoftInPutMode();
        setContentView(R.layout.activity_publish_circle);
        ButterKnife.bind(this);
        tvTitle.setText("发动态");
    }

//    @Override
    protected void setSoftInPutMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE|WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    protected void initStateBar() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.white_lib)
                .statusBarDarkFont(true, 0.2f)
                .fitsSystemWindows(true)
                .keyboardEnable(true)
                .init();
    }
    @Override
    public void onBackPressed() {
        Tools.hideSoftInput(this);
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;

        }
    }

}
