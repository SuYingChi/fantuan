package com.wetime.fanc.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.my.bean.MyFriendsBaseBean;
import com.wetime.fanc.my.presenter.GetMyFriendPresenter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/3/26.
 */

public class MyFriendsAdapter extends CommonAdapter<MyFriendsBaseBean.DataBean.ListBean> {
    private Context context;
    private int type = -1;
    private GetMyFriendPresenter getMyFriendPresenter;

    public MyFriendsAdapter(Context context, int layoutId, List<MyFriendsBaseBean.DataBean.ListBean> datas, int type, GetMyFriendPresenter getMyFriendPresenter) {
        super(context, layoutId, datas);
        this.context = context;
        this.type = type;
        this.getMyFriendPresenter = getMyFriendPresenter;
    }

    @Override
    protected void convert(ViewHolder holder, MyFriendsBaseBean.DataBean.ListBean myFriendsBaseBean, int position) {
        switch (type) {
            case 0:
                holder.setVisible(R.id.friend_base_image, false);
                break;
            case 1:
                chageType(holder, myFriendsBaseBean.isIs_mutual(), 1);
                break;
            case 2:
                chageType(holder, myFriendsBaseBean.isIs_mutual(), 2);
                break;
        }
        holder.setText(R.id.friend_base_text, myFriendsBaseBean.getUsername());
        Glide.with(context).load(myFriendsBaseBean.getAvatar()).into((CircleImageView) holder.getView(R.id.friend_base_head));
        holder.getConvertView().setOnClickListener(v -> {
            Intent go = new Intent(context, UserCardActivity.class);
            go.putExtra("num", "3");
            go.putExtra("index", 0);
            go.putExtra("id", myFriendsBaseBean.getId());
            context.startActivity(go);
        });
        if (type == 0) return;
        holder.setTag(R.id.friend_base_image, "false");
        holder.setOnClickListener(R.id.friend_base_image, v -> {//0、取消1、关注
            switch (type) {
                case 1:
                    if (holder.getView(R.id.friend_base_image).getTag() == null || !holder.getView(R.id.friend_base_image).getTag().equals("true")) {
                        getMyFriendPresenter.setAttention(String.valueOf(0), myFriendsBaseBean.getId());
                        holder.setImageResource(R.id.friend_base_image, R.drawable.icon_nor);
                        holder.setTag(R.id.friend_base_image, "true");
                    } else {
                        getMyFriendPresenter.setAttention(String.valueOf(1), myFriendsBaseBean.getId());
                        holder.setTag(R.id.friend_base_image, "false");
                        if (myFriendsBaseBean.isIs_mutual()) {
                            holder.setImageResource(R.id.friend_base_image, R.drawable.icon);
                        } else {
                            holder.setImageResource(R.id.friend_base_image, R.drawable.icon_attention);
                        }
                    }
                    break;
                case 2:
                    if (myFriendsBaseBean.isIs_mutual()) {
                        getMyFriendPresenter.setAttention(String.valueOf(0), myFriendsBaseBean.getId());
                        holder.setImageResource(R.id.friend_base_image, R.drawable.icon_nor);
                        myFriendsBaseBean.setIs_mutual(false);
                    } else {
                        getMyFriendPresenter.setAttention(String.valueOf(1), myFriendsBaseBean.getId());
                        holder.setImageResource(R.id.friend_base_image, R.drawable.icon);
                        myFriendsBaseBean.setIs_mutual(true);
                    }
                    break;
            }

        });

    }

    private void chageType(ViewHolder holder, boolean is_mutual, int type) {
        switch (type) {
            case 1:
                if (is_mutual) {
                    holder.setImageResource(R.id.friend_base_image, R.drawable.icon);
                } else {
                    holder.setImageResource(R.id.friend_base_image, R.drawable.icon_attention);
                }

                break;
            case 2:
                if (is_mutual) {
                    holder.setImageResource(R.id.friend_base_image, R.drawable.icon);
                } else {
                    holder.setImageResource(R.id.friend_base_image, R.drawable.icon_nor);
                }
                break;
        }
    }
}

