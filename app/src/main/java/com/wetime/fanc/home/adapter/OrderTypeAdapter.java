package com.wetime.fanc.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.OrderPageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class OrderTypeAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<OrderPageBean.DataBean.TypeConfigBean> mData;
    private Context mContext;
    private int selectedid = 0;


    public OrderTypeAdapter(Context context, List<OrderPageBean.DataBean.TypeConfigBean> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        mContext = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (mData == null)
            return 0;
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
        ViewHolder holder = null;
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item_ordertype, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvname.setText(mData.get(position).getName());

        if (position == selectedid) {
            holder.tvname.setTextColor(Color.parseColor("#ff3f53"));
        } else {
            holder.tvname.setTextColor(Color.parseColor("#333333"));
        }


        return convertView;

    }

    public void setSelectedId(int selectedId) {
        this.selectedid = selectedId;
        notifyDataSetChanged();
    }

    public int getSelectedid() {
        return selectedid;
    }

    public class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvname;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

