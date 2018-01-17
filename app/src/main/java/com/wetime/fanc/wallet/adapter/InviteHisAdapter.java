package com.wetime.fanc.wallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.wallet.bean.BalanceDetailListBean;
import com.wetime.fanc.wallet.bean.InviteHisBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class InviteHisAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<InviteHisBean.DataBean.ListBean> mData;
    private Context mContext;

    public InviteHisAdapter(Context context, List<InviteHisBean.DataBean.ListBean> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        mContext = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return mData.get(arg0);
    }


    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_invite_his, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        InviteHisBean.DataBean.ListBean bean = mData.get(position);

        holder.tvTitle.setText(bean.getInvitee_phone());
        holder.tvTime.setText(bean.getTime());
//        holder.tvDes.setText(bean.getDesc());
        holder.tvNum.setText(bean.getInvitee_money());
        return convertView;

    }


    public class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
//        @BindView(R.id.tv_des)
//        TextView tvDes;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_num)
        TextView tvNum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

