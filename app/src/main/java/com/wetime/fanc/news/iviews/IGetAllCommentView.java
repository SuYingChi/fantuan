package com.wetime.fanc.news.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.news.bean.CommentBean;
import com.wetime.fanc.news.bean.GalleryCommentBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetAllCommentView extends IBaseVIew {

    void onGetAllComment(CommentBean bean);

    void onClickLike(CommentBean bean);

    void onSendCommont(GalleryCommentBean bean);

    void onDeleteCommont(ErrorBean bean);
}
