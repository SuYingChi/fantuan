package com.wetime.fanc.main.act;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wetime.fanc.R;
import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.service.event.UploadProgessEvent;
import com.wetime.fanc.service.event.uploadEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2018/4/13.
 */

public abstract class InitialBaseActivity extends AppCompatActivity implements IBaseVIew {

    private boolean isAdd = false;
    private View framView;
    private TextView textView;

    //查找布局的底层
    protected static ViewGroup getRootView(Activity context) {
        return (ViewGroup) context.findViewById(android.R.id.content);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    protected int addPushProgressHeight() {
        return 80;
    }

    protected int addPushProgressMarginT() {
        return 80;
    }

    public void addPushProgress() {
        framView.setVisibility(View.VISIBLE);
    }

    public void removePushProgress() {
        framView.setVisibility(View.INVISIBLE);
    }

    public void setPushProgress(String progress) {
        if (isAdd) {
            textView.setText(progress);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UploadProgessEvent messageEvent) {
        if (this.getClass().getSimpleName().equals("PublishActActivity")) {
            this.finish();
        }
        addPushProgress();
        String substring = String.valueOf(messageEvent.getPrgess() * 100).substring(0, String.valueOf(messageEvent.getPrgess() * 100).indexOf("."));
        Log.e("xi", "onEvent: " + substring);
//        setPushProgress("文章上传中，请不要离开" + substring + "%…");
//        ViewGroup rootView = getRootView(this);
//        View childAt = rootView.getChildAt(rootView.getChildCount() - 1);
//        ((TextView) childAt.findViewById(R.id.progess_title)).setText("文章上传中，请不要离开" + substring + "%…");
        if (messageEvent.getPrgess() < 1) {
            setPushProgress("文章上传中，请不要离开" + substring + "%…");
        } else if (messageEvent.getPrgess() >= 1) {
            setPushProgress("文章上传中，请不要离开" + 99 + "%…");
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(uploadEvent messageEvent) {
        Log.e("xi", "uploadEvent: ");
        Toast.makeText(this, "文章上传成功", Toast.LENGTH_SHORT).show();
        this.framView.setVisibility(View.INVISIBLE);
//        Intent go = new Intent(this, ActDetailActivity.class);
//        go.putExtra("id", messageEvent.getId());
//        startActivity(go);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isAdd) {
            ViewGroup rootView = getRootView(this);
            framView = LayoutInflater.from(this).inflate(R.layout.layout_download, null);
            textView = ((TextView) framView.findViewById(R.id.progess_title));
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
        if (this.framView != null) {
            this.framView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.framView.setVisibility(View.INVISIBLE);
        EventBus.getDefault().unregister(this);
    }
}
