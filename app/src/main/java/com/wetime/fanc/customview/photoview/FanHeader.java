package com.wetime.fanc.customview.photoview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.wetime.fanc.R;

public class FanHeader extends LinearLayout implements RefreshHeader {
    private View mHeadView;
    private ImageView mHeaderImage;
    private  Animation rotate;
    public FanHeader(Context context) {
        super(context);
        mHeadView = LayoutInflater.from(context).inflate(R.layout.item_fanheader, null);
        mHeaderImage= mHeadView.findViewById(R.id.iv_header);
        rotate = AnimationUtils.loadAnimation(context, R.anim.anim_referesh_header);
    }

    @NonNull
    @Override
    public View getView() {
        return mHeadView;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        mHeaderImage.setImageResource(R.drawable.ic_fan_header_refreshing);
        mHeaderImage.startAnimation(rotate);
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        mHeaderImage.clearAnimation();
        return 0;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        if(newState==RefreshState.PullDownToRefresh){
            mHeaderImage.setImageResource(R.drawable.ic_fan_header_noraml);
        }
    }
}
