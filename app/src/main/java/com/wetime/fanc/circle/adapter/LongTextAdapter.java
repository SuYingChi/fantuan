package com.wetime.fanc.circle.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.LongTextBean;
import com.wetime.fanc.utils.Tools;

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


    public LongTextAdapter(Activity context, List<LongTextBean> mData) {
        this.mData = mData;
        mContext = context;
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
        return mData.get(position).getType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        holder.setIsRecyclable(false);
        LongTextBean bean = mData.get(position);

        if (holder instanceof TitleViewHolder) {

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
            Glide.with(mContext)
                    .load(bean.getImageUrl())
                    .apply(new RequestOptions()
//                            .override(Tools.getScreenW(mContext), Tools.getScreenW(mContext))
                            .override(Tools.getScreenW(mContext), 200)
                            .centerCrop())
                    .into(((ImageViewHolder) holder).ivCover);
            if (mOnImgDeleteClickLitener != null) {
                ((ImageViewHolder) holder).ivDelete
                        .setOnClickListener(view ->
                                mOnImgDeleteClickLitener.onImageDeleteClick(view, holder.getAdapterPosition()));
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
        @BindView(R.id.et_des)
        EditText etDes;

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

