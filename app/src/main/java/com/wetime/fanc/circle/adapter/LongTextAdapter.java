package com.wetime.fanc.circle.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.BuildConfig;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.LongTextBean;
import com.wetime.fanc.utils.Tools;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class LongTextAdapter extends RecyclerView.Adapter {

    private String TAG = "zk longtext";
    private List<LongTextBean> mData;
    private Activity mContext;
    private boolean sort = false;
    private final int sortHight;
    private final int maxHight;


    public LongTextAdapter(Activity context, List<LongTextBean> mData) {
        this.mData = mData;
        mContext = context;
        sortHight = Tools.dip2px(mContext, 84);
        maxHight = Tools.dip2px(mContext, 120);
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //0 title  1edite  2 image

        if (viewType == 0) {
            return new TitleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_longtext_tile, parent, false));
        } else if (viewType == 1) {
            return new EditViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_longtext_edit, parent, false));
        }
        return new ImageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_longtext_image, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.valueOf(mData.get(position).getType());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        holder.setIsRecyclable(false);
        LongTextBean bean = mData.get(position);

        if (holder instanceof TitleViewHolder) {
            if (isSort()) {
                ((TitleViewHolder) holder).etTitle.setEnabled(false);
            } else {
                ((TitleViewHolder) holder).etTitle.setEnabled(true);
            }

            ((TitleViewHolder) holder).etTitle.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    bean.setTitle(s.toString());
                }
            });
            ((TitleViewHolder) holder).etTitle.setText(bean.getTitle());
        }
        if (holder instanceof EditViewHolder) {
            if (isSort()) {
                ((EditViewHolder) holder).etText.setEnabled(false);
                ((EditViewHolder) holder).etText.setBackgroundResource(R.drawable.bg_edit_dash);
                ViewGroup.LayoutParams params = ((EditViewHolder) holder).etText.getLayoutParams();
                params.height = sortHight;
                ((EditViewHolder) holder).etText.setLayoutParams(params);
                ((EditViewHolder) holder).etText.setTextSize(12);

            } else {
                ViewGroup.LayoutParams params = ((EditViewHolder) holder).etText.getLayoutParams();

                if (mData.size() == 2) {
                    ((EditViewHolder) holder).etText.setHint("请输入正文");
                    params.height = maxHight;
                    ((EditViewHolder) holder).etText.setLayoutParams(params);
                } else {
                    ((EditViewHolder) holder).etText.setHint("");
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }
                ((EditViewHolder) holder).etText.setEnabled(true);
                ((EditViewHolder) holder).etText.setBackgroundResource(R.drawable.bg_edit_nodash);
                ((EditViewHolder) holder).etText.setLayoutParams(params);
                ((EditViewHolder) holder).etText.setTextSize(17);
            }


            ((EditViewHolder) holder).etText.addTextChangedListener(new SaveEditWatcher((EditViewHolder) holder));
            //通过设置tag，防止position紊乱
            ((EditViewHolder) holder).etText.setTag(position);
            ((EditViewHolder) holder).etText.setText(bean.getContent());
            ((EditViewHolder) holder).etText.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    Log.e(TAG, ((EditViewHolder) holder).etText.getSelectionStart() + "");
                    mSaveSelectionStartListener.saveSelectionStart(position, ((EditViewHolder) holder).etText);
                } else {
                    Log.e(TAG, ((EditViewHolder) holder).etText.getSelectionStart() + "nono");
                }

            });
        }
        if (holder instanceof ImageViewHolder) {
            if (isSort()) {
                ((ImageViewHolder) holder).etDes.setVisibility(View.GONE);
                ((ImageViewHolder) holder).ivCover.setBackgroundResource(R.drawable.bg_edit_dash);
                ((ImageViewHolder) holder).ivDelete.setVisibility(View.GONE);
                ((ImageViewHolder) holder).ivEdit.setVisibility(View.GONE);
                ((ImageViewHolder) holder).ivDrag.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                holder.itemView.setLayoutParams(params);
                Glide.with(mContext)
                        .load(bean.getImageUrl())
                        .apply(new RequestOptions()
//                            .override(Tools.getScreenW(mContext), Tools.getScreenW(mContext))
                                .override(Tools.getScreenW(mContext) - Tools.dip2px(mContext, 15 + 15), sortHight)
                                .centerCrop())
                        .into(((ImageViewHolder) holder).ivCover);


            } else {


                ((ImageViewHolder) holder).ivCover.setBackgroundResource(R.drawable.bg_edit_nodash);
                ((ImageViewHolder) holder).ivDelete.setVisibility(View.VISIBLE);
                ((ImageViewHolder) holder).ivEdit.setVisibility(View.VISIBLE);
                ((ImageViewHolder) holder).ivDrag.setVisibility(View.GONE);

                if (mOnImgDeleteClickLitener != null) {
                    ((ImageViewHolder) holder).ivDelete
                            .setOnClickListener(view ->
                                    mOnImgDeleteClickLitener.onImageDeleteClick(view, holder.getAdapterPosition()));
                }

                ((ImageViewHolder) holder).ivEdit.setOnClickListener(v -> {
                    ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    holder.itemView.setLayoutParams(params);
                    ((ImageViewHolder) holder).etDes.setVisibility(View.VISIBLE);

                    ((ImageViewHolder) holder).etDes.setFocusable(true);
                    ((ImageViewHolder) holder).etDes.setFocusableInTouchMode(true);
                    ((ImageViewHolder) holder).etDes.requestFocus();
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(((ImageViewHolder) holder).etDes, 0);


//                    ((ImageViewHolder) holder).etDes.requestFocus();
                });
                ((ImageViewHolder) holder).etDes.addTextChangedListener(new SaveDesEditWatcher((ImageViewHolder) holder));
                ((ImageViewHolder) holder).etDes.setTag(position);
                ((ImageViewHolder) holder).etDes.setText(bean.getDes());

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                Bitmap bmp = BitmapFactory.decodeFile(bean.getImageUrl(), options);//这里的bitmap是个空
                if (bmp == null) {
                    Log.e("通过options获取到的bitmap为空", "===");
                }
                int outHeight = options.outHeight;
                int outWidth = options.outWidth;
                if(BuildConfig.DEBUG){
                    String temp;
                    long size = new File(bean.getImageUrl()).length();
                    if(size>1014){
                        temp = size/1024+"kb";
                    }
                    else {
                        temp = size+"b";
                    }
                    ((ImageViewHolder) holder).tvInfo.setText(String.format("H:%s--W:%s--size=%s", outHeight, outWidth,temp));
                }



                int sw = Tools.getScreenW(mContext);
                int w = (sw - Tools.dip2px(mContext, 15 + 15));
                Double rate = outHeight * 1.0 / outWidth;

                int h = (int) (w * rate);

                ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();


                if (TextUtils.isEmpty(bean.getDes())) {
                    ((ImageViewHolder) holder).etDes.setVisibility(View.GONE);
                    params.height = h + Tools.dip2px(mContext, 10);
                } else {
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    ((ImageViewHolder) holder).etDes.setVisibility(View.VISIBLE);
                }


                holder.itemView.setLayoutParams(params);
                Glide.with(mContext)
                        .load(bean.getImageUrl())
                        .apply(new RequestOptions()
//                            .override(Tools.getScreenW(mContext), Tools.getScreenW(mContext))
                                .override(w, h).centerCrop())
                        .into(((ImageViewHolder) holder).ivCover);
            }


        }

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, holder.getAdapterPosition()));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.et_title)
        EditText etTitle;

        TitleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    class EditViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.et_text)
        EditText etText;

        EditViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;
        @BindView(R.id.iv_drag)
        ImageView ivDrag;
        @BindView(R.id.iv_edit)
        ImageView ivEdit;
        @BindView(R.id.et_des)
        EditText etDes;
        @BindView(R.id.tv_info)
        TextView tvInfo;

        ImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface SaveEditListener {
        void SaveEdit(int position, String string);
    }

    private SaveEditListener mSaveEditListener;


    public void setSaveEditListener(SaveEditListener mSaveEditListener) {
        this.mSaveEditListener = mSaveEditListener;
    }


    //监听 文本输入框
    class SaveEditWatcher implements TextWatcher {

        private EditViewHolder mHolder;

        public SaveEditWatcher(EditViewHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //用户输入完毕后，处理输入数据，回调给主界面处理
//            SaveEditListener listener = (SaveEditListener) mContext
            if (s != null && mSaveEditListener != null) {
                mSaveEditListener.SaveEdit(Integer.parseInt(mHolder.etText.getTag().toString()), s.toString());
            }
        }
    }

    public interface SaveDesEditListener {
        void saveDesEdit(int position, String string);
    }

    private SaveDesEditListener mSaveDesEditListener;


    public void setSaveDesEditListener(SaveDesEditListener mSaveDesEditListener) {
        this.mSaveDesEditListener = mSaveDesEditListener;
    }


    //监听 文本输入框
    class SaveDesEditWatcher implements TextWatcher {

        private ImageViewHolder mHolder;

        public SaveDesEditWatcher(ImageViewHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //用户输入完毕后，处理输入数据，回调给主界面处理
//            SaveEditListener listener = (SaveEditListener) mContext
            if (s != null && mSaveDesEditListener != null) {
                mSaveDesEditListener.saveDesEdit(Integer.parseInt(mHolder.etDes.getTag().toString()), s.toString());
            }
        }
    }

    private SaveSelectionStartListener mSaveSelectionStartListener;


    public void setSaveSelectionStartListener(SaveSelectionStartListener mSaveSelectionStartListener) {
        this.mSaveSelectionStartListener = mSaveSelectionStartListener;
    }

    public interface SaveSelectionStartListener {
        void saveSelectionStart(int position, EditText editText);
    }


    public interface OnImgDeleteClickLitener {
        void onImageDeleteClick(View view, int position);
    }


    private OnImgDeleteClickLitener mOnImgDeleteClickLitener;


    public void setOnImgDeleteClickLitener(OnImgDeleteClickLitener mOnImgDeleteClickLitener) {
        this.mOnImgDeleteClickLitener = mOnImgDeleteClickLitener;
    }

}

