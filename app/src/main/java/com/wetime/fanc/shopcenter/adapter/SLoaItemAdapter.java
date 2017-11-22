package com.wetime.fanc.shopcenter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class SLoaItemAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MallsBean.SubcatesBean> mData;
    private Context mContext;
    private String selectedid = "";
    private String cid = "";

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }


    public SLoaItemAdapter(Context context, List<MallsBean.SubcatesBean> mData) {
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

            convertView = mInflater.inflate(R.layout.item_scate, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvname.setText(mData.get(position).getName());
        holder.tvnum.setText("(" + mData.get(position).getNum() + ")");

        if (selectedid.equals("") && cid.equals("")) {
            if (position == 0) {
                holder.tvname.setTextColor(Color.parseColor("#ff3f53"));
                holder.tvnum.setTextColor(Color.parseColor("#ff3f53"));
            } else {
                holder.tvname.setTextColor(Color.parseColor("#333333"));
                holder.tvnum.setTextColor(Color.parseColor("#999999"));
            }
        } else {
            if (selectedid.equals(mData.get(position).getId()) && cid.equals(mData.get(position).getId())) {
                holder.tvname.setTextColor(Color.parseColor("#ff3f53"));
                holder.tvnum.setTextColor(Color.parseColor("#ff3f53"));
            } else {
                holder.tvname.setTextColor(Color.parseColor("#333333"));
                holder.tvnum.setTextColor(Color.parseColor("#999999"));
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
        @BindView(R.id.tv_num)
        TextView tvnum;

//        @BindView(R.id.rl_bg)
//        RelativeLayout rlbg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

