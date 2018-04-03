package com.wetime.fanc.news.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.news.bean.CommentBean;
import com.wetime.fanc.news.bean.ReplyCommentBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetCommentReplyView extends IBaseVIew {

    void onGetCommentReply(ReplyCommentBean bean);

    void onSendCommentReply(String bean);

    void onClickLike(CommentBean bean);
}
