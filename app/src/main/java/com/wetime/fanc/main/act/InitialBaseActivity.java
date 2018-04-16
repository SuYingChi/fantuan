package com.wetime.fanc.main.act;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wetime.fanc.R;

/**
 * Created by Administrator on 2018/4/13.
 */

public class InitialBaseActivity extends AppCompatActivity {

    private boolean isAdd = false;

    //查找布局的底层
    protected static ViewGroup getRootView(Activity context) {
        return (ViewGroup) context.findViewById(android.R.id.content);
    }

    protected boolean addPushProgress() {
        return false;
    }

    protected int addPushProgressHeight() {
        return 80;
    }

    protected int addPushProgressMarginT() {
        return 80;
    }

    @Override
    protected void onStart() {
        //判断是否已经添加过
        if (addPushProgress()) {
            if (!isAdd) {
                ViewGroup rootView = getRootView(this);
                View framView = LayoutInflater.from(this).inflate(R.layout.layout_download, null);
                rootView.addView(framView);
                ViewGroup.LayoutParams layoutParams = framView.getLayoutParams();
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) layoutParams;
                    lp.setMargins(0, addPushProgressMarginT(), 0, 0);
                    lp.height = addPushProgressHeight();
                    framView.setLayoutParams(lp);
                } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) layoutParams;
                    lp.setMargins(0, addPushProgressMarginT(), 0, 0);
                    lp.height = addPushProgressHeight();
                    framView.setLayoutParams(lp);
                } else if (layoutParams instanceof FrameLayout.LayoutParams) {
                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) layoutParams;
                    lp.setMargins(0, addPushProgressMarginT(), 0, 0);
                    lp.height = addPushProgressHeight();
                    framView.setLayoutParams(lp);
                }
                isAdd = true;
            }
        }
        super.onStart();

    }

}
