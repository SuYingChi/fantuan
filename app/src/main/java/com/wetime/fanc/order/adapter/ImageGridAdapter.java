package com.wetime.fanc.order.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.customview.multiimageselector.adapter.SquaredImageView;
import com.wetime.fanc.R;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mlist = new ArrayList<String>();
    private LayoutInflater inflater = null;
//    private final int mGridWidth;

    public ImageGridAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.mlist = list;

        inflater = LayoutInflater.from(mContext);

//        WindowManager wm = (WindowManager) mContext
//                .getSystemService(Context.WINDOW_SERVICE);
//        int width = 0;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//            Point size = new Point();
//            wm.getDefaultDisplay().getSize(size);
//            width = size.x;
//        } else {
//            width = wm.getDefaultDisplay().getWidth();
//        }
//        mGridWidth = width / 4;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (mlist.size() < mContext.getResources().getInteger(R.integer.most_pic_num))
            return mlist.size() + 1;
        else
            return mlist.size();

    }

    public void setList(List<String> list) {
        mlist.clear();
        this.mlist = list;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.img_item, null);
            holder = new Holder();
            holder.iv = (SquaredImageView) convertView.findViewById(R.id.img);
            holder.ivDelete = (ImageView) convertView.findViewById(R.id.iv_image_delete);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if (position == mlist.size()) {
            Glide.with(mContext).load(R.drawable.ic_addpic)
                    .apply(new RequestOptions() .centerCrop())
                    .into(holder.iv);
            holder.ivDelete.setVisibility(View.GONE);
        } else {
            File imageFile = new File(mlist.get(position));

            if (imageFile.exists()) {
                // 显示图片
                Glide.with(mContext).load(imageFile)
                        .apply(new RequestOptions() .centerCrop())
                        .into(holder.iv);
            } else {
                Glide.with(mContext).load(R.drawable.default_error)
                        .into(holder.iv);
            }

        }
        if (mOnDeleteClickLitener != null) {
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnDeleteClickLitener.onDeleteClick(holder.ivDelete, position);
                }
            });
        }
        return convertView;
    }

    private class Holder {
        private SquaredImageView iv;
        private ImageView ivDelete;
    }
    public interface OnDeleteClickLitener {
        void onDeleteClick(View view, int position);
    }



    private OnDeleteClickLitener mOnDeleteClickLitener;




    public void setOnDeleteClickLitener(OnDeleteClickLitener mOnDeleteClickLitener) {
        this.mOnDeleteClickLitener = mOnDeleteClickLitener;
    }

}
