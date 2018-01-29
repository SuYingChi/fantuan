package com.wetime.fanc.home.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseLazyFragment;
import com.wetime.fanc.utils.Tools;

/**
 * Created by zhoukang on 2018/1/29.
 */

public class NewsTypeFragment extends BaseLazyFragment {
    private String type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        type = bundle.getString("type");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_newstype;
    }

    @Override
    protected void initView() {
        TextView tv = mRootView.findViewById(R.id.test);
        tv.setText(type);

    }

    @Override
    protected void initData() {
        Tools.toastInBottom(getContext(), "initData" + type);
    }
}
