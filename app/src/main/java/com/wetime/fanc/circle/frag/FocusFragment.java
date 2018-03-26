package com.wetime.fanc.circle.frag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wetime.fanc.R;
import com.wetime.fanc.main.frag.BaseLazyFragment;


public class FocusFragment extends BaseLazyFragment {


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_empty;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }
}
