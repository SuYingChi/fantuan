package com.wetime.fanc.shopcenter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.MallsBean;
import com.wetime.fanc.shopcenter.bean.CategoryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class LoaItemAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MallsBean> mData;
    private Context mContext;
    private String selectedid = "";

    public LoaItemAdapter(Context context, List<MallsBean> mData) {
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
        ViewHolder holder = null;
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item_category, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvname.setText(mData.get(position).getName());

        if (selectedid.equals("")) {
            if (position == 0) {
                holder.tvname.setTextColor(Color.parseColor("#ff3f53"));
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_right_red);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                holder.tvname.setCompoundDrawables(null, null, drawable, null);
                holder.rlbg.setBackgroundColor(Color.parseColor("#ffffff"));
            } else {
                holder.tvname.setTextColor(Color.parseColor("#333333"));
                holder.rlbg.setBackgroundColor(Color.parseColor("#f5f5f5"));
                holder.tvname.setCompoundDrawables(null, null, null, null);
            }
        } else {
            if (selectedid.equals(mData.get(position).getId())) {
                holder.tvname.setTextColor(Color.parseColor("#ff3f53"));
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_right_red);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                holder.tvname.setCompoundDrawables(null, null, drawable, null);
                holder.rlbg.setBackgroundColor(Color.parseColor("#ffffff"));
            } else {
                holder.tvname.setTextColor(Color.parseColor("#333333"));
                holder.rlbg.setBackgroundColor(Color.parseColor("#f5f5f5"));
                holder.tvname.setCompoundDrawables(null, null, null, null);
            }
        }




        return convertView;

    }

    public void setSelectedId(String selectedId) {
        this.selectedid = selectedId;
        notifyDataSetChanged();
    }

    public String getSelectedid() {
        return selectedid;
    }

    public class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvname;
        @BindView(R.id.rl_bg)
        RelativeLayout rlbg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

