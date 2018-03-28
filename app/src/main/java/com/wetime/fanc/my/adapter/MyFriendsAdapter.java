package com.wetime.fanc.my.adapter;

import android.content.Context;
import android.view.View;

import com.wetime.fanc.R;
import com.wetime.fanc.my.bean.MyFriendsBaseBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/26.
 */

public class MyFriendsAdapter extends CommonAdapter<MyFriendsBaseBean> {

    public MyFriendsAdapter(Context context, int layoutId, List<MyFriendsBaseBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, MyFriendsBaseBean myFriendsBaseBean, int position) {
        holder.setText(R.id.friend_base_text, myFriendsBaseBean.getName());
        switch (myFriendsBaseBean.getType()) {
            case 0:
                holder.setImageResource(R.id.friend_base_image,R.drawable.icon);
                break;
            case 1:
                holder.setImageResource(R.id.friend_base_image,R.drawable.icon_attention);
                break;
            case 2:
                holder.setImageResource(R.id.friend_base_image,R.drawable.icon_nor);
                holder.setOnClickListener(R.id.friend_base_image, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.setImageResource(R.id.friend_base_image,R.drawable.icon_attention);
                    }
                });
                break;
        }
    }
}
