package com.wetime.fanc.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.king.batterytest.fbaselib.customview.AlignTextView;
import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.OrderPageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuxun on 2017/4/15.
 */

public class OrderAdapter extends RecyclerView.Adapter {
    private List<OrderPageBean.DataBean.ListBean> list;
    private Context mActivity;
//    private String filter = "";
    private String type = "";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
//    public String getFilter() {
//        return filter;
//    }
//
//    public void setFilter(String filter) {
//        this.filter = filter;
//    }

    public OrderAdapter(List<OrderPageBean.DataBean.ListBean> list, Context mActivity) {
        this.list = list;
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_order, parent, false));

    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public interface OnActionClickLitener {
        void onItemClick(View view, int position);
    }


    private OnActionClickLitener mOnActionClickLitener;


    public void setOnActionClickLitener(OnActionClickLitener mOnActionClickLitener) {
        this.mOnActionClickLitener = mOnActionClickLitener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final OrderViewHolder oholder = (OrderViewHolder) holder;
        OrderPageBean.DataBean.ListBean bean = list.get(position);
//        Glide.with(mActivity).load(bean.getMerchant().getLogo()).into(oholder.civHead);
        oholder.tvName.setText(bean.getPromotion_name());

        oholder.tvNum.setText(bean.getOrder_num());
        if (bean.getExpired_time().equals("")) {
            oholder.llTime.setVisibility(View.GONE);
        } else {
            oholder.llTime.setVisibility(View.VISIBLE);
            oholder.tvTime.setText(bean.getExpired_time());
        }

        oholder.tvPrice.setText(bean.getPrice());
        oholder.tvPriceTitle.setAlingText(bean.getPrice_type_name());

        if (type.equals("0")) {
            oholder.tvHead.setVisibility(View.VISIBLE);
            oholder.tvHead.setText(bean.getType_name());
        } else {
            oholder.tvHead.setVisibility(View.GONE);
        }
        // 变色
        oholder.tvState.setText(bean.getStateName());
        if (bean.getPromotion_name().equals("待付款")
                || bean.getPromotion_name().equals("待使用")
                ) {
            oholder.tvState.setTextColor(Color.parseColor("#ff3f53"));
        } else {
            oholder.tvState.setTextColor(Color.parseColor("#666666"));
        }
        oholder.tvOpper.setText(bean.getAction_type_name());
        if (bean.getAction_type_name().equals("去付款") ||
                bean.getAction_type_name().equals("查看券码")) {
            oholder.tvOpper.setBackgroundResource(R.drawable.bg_btn_red_corner);
            oholder.tvOpper.setTextColor(Color.parseColor("#ffffff"));
        } else {
            oholder.tvOpper.setBackgroundResource(R.drawable.bg_btn_white_gray_corner);
            oholder.tvOpper.setTextColor(Color.parseColor("#666666"));
        }

        if (mOnItemClickLitener != null) {
            oholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(view, position);
                }
            });
        }
        if (mOnActionClickLitener != null) {
            oholder.tvOpper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnActionClickLitener.onItemClick(view, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_head)
        CircleImageView civHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_head)
        TextView tvHead;
        @BindView(R.id.tv_pricetitle)
        AlignTextView tvPriceTitle;
        @BindView(R.id.tv_opper)
        TextView tvOpper;
        @BindView(R.id.ll_time)
        LinearLayout llTime;

        public OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
