package com.wetime.fanc.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.HomePageBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by yuxun on 2017/4/15.
 */

public class CenterAdapter extends CommonAdapter<HomePageBean.DataBean.MallsBean> {
    private Context mContext;

    public CenterAdapter(Context context, int layoutId, List<HomePageBean.DataBean.MallsBean> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, HomePageBean.DataBean.MallsBean mallsBean, int position) {
        holder.setText(R.id.tv_name, mallsBean.getName());
        holder.setText(R.id.tv_dis, mallsBean.getDistance());
        Glide.with(mContext).load(mallsBean.getLogo()).into((ImageView) holder.getConvertView().findViewById(R.id.iv_bg));
    }


}
