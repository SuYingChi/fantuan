package com.wetime.fanc.news.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.photoview.PhotoView;
import com.wetime.fanc.customview.photoview.PhotoViewAttacher;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.news.bean.GalleryItemBean;
import com.wetime.fanc.utils.Tools;

import java.util.List;

import me.shaohui.bottomdialog.BottomDialog;

public class GalleryAdapter extends PagerAdapter {


    private List<GalleryItemBean.DataBean.AtlasContentBean> items;
    private Context context;
    private OnGalleryAdapterCallback callback;
    private Object mPrimaryItem;

    public GalleryAdapter(Context context, OnGalleryAdapterCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void update(List<GalleryItemBean.DataBean.AtlasContentBean> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (items == null) {
            return 0;
        } else {
            return items.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object != null) {
            container.removeView((View) object);
        }
    }

    @SuppressLint("InflateParams")
    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        final View view = LayoutInflater.from(context).inflate(R.layout.gallery_photo_view, container, false);
        final PhotoView photoView = (PhotoView) view.findViewById(R.id.photo_view);
        final RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.item_relativeLayout);
        final TextView progressStr = (TextView) view.findViewById(R.id.photo_progress);
        final View progressLayout = view.findViewById(R.id.photo_progress_layout);
        final View retryBtn = view.findViewById(R.id.gallery_load_fail_root);
        final GalleryItemBean.DataBean.AtlasContentBean item = getGalleryItem(position);
        if (item == null) {
            return null;
        }
        showImage(retryBtn, item, photoView, progressStr, progressLayout);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retryBtn.setVisibility(View.GONE);
                showImage(retryBtn, item, photoView, progressStr, progressLayout);
            }
        });
        photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                callback.onPhotoTap(item);
            }
        });
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                callback.onPhotoTap(item);
            }
        });

        ViewGroup parent = ((ViewGroup) view.getParent());
        if (parent != null) {
            parent.removeAllViews();
        }
        container.addView(view);
        return view;
    }

    public GalleryItemBean.DataBean.AtlasContentBean getGalleryItem(int position) {
        if (items == null || items.size() == 0) {
            return null;
        }
        return items.get(position % items.size());
    }

    public int getPosition(int position) {
        if (items == null || items.size() == 0) {
            return 0;
        }
        return (position % items.size());
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mPrimaryItem = object;
    }

    @Override
    public int getItemPosition(Object object) {
        if (object == mPrimaryItem) {
            return POSITION_NONE;
        }
        return POSITION_UNCHANGED;
    }

    private void showImage(final View retryView, GalleryItemBean.DataBean.AtlasContentBean item, final PhotoView photoView, final TextView progressStr, final View progressLayout) {
        Bitmap bitmap;
        if (item == null) {
            return;
        }
        String url = item.getImg_url();

        Glide.with(context).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                retryView.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressLayout.setVisibility(View.GONE);
                return false;
            }

        }).into(photoView);
        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                BottomDialog mDeleteBottomDialog = BottomDialog.create(((BaseActivity) context).getSupportFragmentManager());
                mDeleteBottomDialog.setDimAmount(0.5f);
                mDeleteBottomDialog.setCancelOutside(true);
                mDeleteBottomDialog.setLayoutRes(R.layout.bottom_save_dialog_layout);
                mDeleteBottomDialog.setViewListener(v11 -> {
                    v11.findViewById(R.id.tv_item1).setOnClickListener(v1 -> {
                        boolean b = Tools.saveImage(context, photoView.getVisibleRectangleBitmap());
                        if (b) {
                            Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
                            mDeleteBottomDialog.dismiss();
                        } else {
                            Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
                            mDeleteBottomDialog.dismiss();
                        }
                    });
                    v11.findViewById(R.id.tv_item6).setOnClickListener(v2 -> {
                        mDeleteBottomDialog.dismiss();
                    });
                });

                mDeleteBottomDialog.show();

                return false;
            }
        });
    }

    public interface OnGalleryAdapterCallback {
        void onPhotoTap(GalleryItemBean.DataBean.AtlasContentBean item);
    }
}
