package com.wetime.fanc.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wetime.fanc.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class HomeShopActAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<String> mData;
    private Context mContext;
    private boolean iszhe = true;

    public boolean isIszhe() {
        if (iszhe && mData.size() > 2) {
            return iszhe;
        } else {
            return !iszhe;
        }

    }

    public void setIszhe(boolean iszhe) {
        this.iszhe = iszhe;
//        notifyDataSetChanged();
    }

    public HomeShopActAdapter(Context context, List<String> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        mContext = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (mData.size() > 2) {
            if (iszhe) {
                return 2;
            } else {
                return mData.size();
            }
        } else {
            return mData.size();
        }


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

            convertView = mInflater.inflate(R.layout.item_home_shopact, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvname.setText(mData.get(position));

        return convertView;

    }


    public class ViewHolder {
        @BindView(R.id.tv_titlename)
        TextView tvname;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

