package com.wetime.fanc.customview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wetime.fanc.R;

/**
 * Created by Administrator on 2018/4/12.
 */

public class BottomAnimDialog extends Dialog {


    private final Context mContext;

    private boolean isReport = false;


    private BottonAnimDialogListener mListener;
    private BottonShareAnimDialogListener mListenerShare;
    private TextView mTvItem1;
    private TextView mTvItem2;
    private TextView mTvItem3;
    private TextView mTvItem4;
    private TextView mTvItem5;
    private TextView mTvItem6;

    public BottomAnimDialog(Context context, boolean isReport) {

        super(context, R.style.BottomAnimDialogStyle);

        this.mContext = context;

        this.isReport = isReport;

        initView();

    }

    private void initView() {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bottom_anim_dialog_layout, null);

        Window window = this.getWindow();
        if (window != null) { //设置dialog的布局样式 让其位于底部
            window.setGravity(Gravity.BOTTOM);
//            WindowManager.LayoutParams lp = window.getAttributes();
//            lp.y = 10; //设置居于底部的距离
//            window.setAttributes(lp);
        }

        mTvItem1 = (TextView) view.findViewById(R.id.tv_item1);
        mTvItem2 = (TextView) view.findViewById(R.id.tv_item2);
        mTvItem3 = (TextView) view.findViewById(R.id.tv_item3);
        mTvItem4 = (TextView) view.findViewById(R.id.tv_item4);
        mTvItem5 = (TextView) view.findViewById(R.id.tv_item5);
        mTvItem6 = (TextView) view.findViewById(R.id.tv_item6);


        mTvItem1.setOnClickListener(new clickListener());
        mTvItem2.setOnClickListener(new clickListener());
        mTvItem3.setOnClickListener(new clickListener());
        mTvItem4.setOnClickListener(new clickListener());
        mTvItem5.setOnClickListener(new clickListener());
        mTvItem6.setOnClickListener(new clickListener());

        if (!isReport) {
            mTvItem3.setVisibility(View.GONE);
            mTvItem4.setVisibility(View.GONE);
            mTvItem5.setVisibility(View.GONE);
            mTvItem2.setVisibility(View.GONE);
            view.findViewById(R.id.tv_view1).setBackgroundResource(R.color.white);
            view.findViewById(R.id.tv_view2).setBackgroundResource(R.color.white);
            view.findViewById(R.id.tv_view3).setBackgroundResource(R.color.white);
            view.findViewById(R.id.tv_view4).setBackgroundResource(R.color.white);
            mTvItem1.setText("分享");
            mTvItem1.setTextColor(Color.parseColor("#ff3b30"));
        } else {
            mTvItem1.setText("请选择举报原因");
            mTvItem1.setTextColor(Color.parseColor("#666666"));
        }

        setContentView(view);
    }


    public void setItem1TextColor(int colorId) {//设置item的字体颜色
        if (mTvItem1 != null) {
            mTvItem1.setTextColor(colorId);
        }
    }

    public void setItem2TextColor(int colorId) {
        if (mTvItem2 != null) {
            mTvItem2.setTextColor(colorId);
        }
    }

    public void setItem3TextColor(int colorId) {
        if (mTvItem3 != null) {
            mTvItem3.setTextColor(colorId);
        }
    }

    public void setClickListener(BottonAnimDialogListener listener) {
        this.mListener = listener;
    }

    public void setClickListener(BottonShareAnimDialogListener listener) {
        this.mListenerShare = listener;
    }

    public interface BottonAnimDialogListener {
        void onItem2Listener();

        void onItem3Listener();

        void onItem4Listener();

        void onItem5Listener();

        void onItem6Listener();
    }

    public interface BottonShareAnimDialogListener {
        void onItem1Listener();

        void onItem6Listener();
    }


    private class clickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_item1:
                    if (mListenerShare != null) {
                        if (!isReport) {
                            mListenerShare.onItem1Listener();
                        }
                    }
                    break;
                case R.id.tv_item2:
                    if (mListener != null) {
                        mListener.onItem2Listener();
                    }
                    break;
                case R.id.tv_item3:
                    if (mListener != null) {
                        mListener.onItem3Listener();
                    }
                    break;
                case R.id.tv_item4:
                    if (mListener != null) {
                        mListener.onItem4Listener();
                    }
                    break;
                case R.id.tv_item5:
                    if (mListener != null) {
                        mListener.onItem5Listener();
                    }
                    break;
                case R.id.tv_item6:
                    if (!isReport) {
                        mListenerShare.onItem6Listener();
                    } else {
                        mListener.onItem6Listener();
                    }
                    break;
            }
        }
    }
}
