package com.wetime.fanc.utils;

import android.text.TextUtils;
import android.util.Log;

import com.fan.http.okhttp.callback.StringCallback;
import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.ErrorBean;

import okhttp3.Call;
import okhttp3.Request;


/**
 * Created by zhoukang on 2017/5/3.
 */

public class DataStringCallback extends StringCallback {
    private boolean showDialog;
    private boolean showErrorToast;
    private boolean showSuccessToast;
    private IBaseVIew iview;
    private boolean isJudge;
    private boolean canCancel;

    public DataStringCallback(IBaseVIew iview) {
        this.iview = iview;
        this.showDialog = true;
        this.showErrorToast = true;
        this.isJudge = true;
        this.showSuccessToast = true;
        this.canCancel = true;
    }

    public DataStringCallback(IBaseVIew iview, boolean showDialog) {
        this(iview);
        this.showDialog = showDialog;
    }

    public DataStringCallback(IBaseVIew iview, boolean showDialog, boolean showErrorToast) {
        this(iview);
        this.showDialog = showDialog;
        this.showErrorToast = showErrorToast;
    }

    public DataStringCallback(IBaseVIew iview, boolean showDialog, boolean showErrorToast, boolean showSuccessToast) {
        this(iview);
        this.showDialog = showDialog;
        this.showErrorToast = showErrorToast;
        this.showSuccessToast = showSuccessToast;
    }

    public DataStringCallback(IBaseVIew iview, boolean showDialog, boolean showErrorToast, boolean showSuccessToast, boolean isJudge) {
        this(iview);
        this.showDialog = showDialog;
        this.showErrorToast = showErrorToast;
        this.showSuccessToast = showSuccessToast;
        this.isJudge = isJudge;
    }

    public DataStringCallback(IBaseVIew iview, boolean showDialog, boolean showErrorToast, boolean showSuccessToast, boolean isJudge, boolean canCancel) {
        this(iview);
        this.showDialog = showDialog;
        this.showErrorToast = showErrorToast;
        this.showSuccessToast = showSuccessToast;
        this.isJudge = isJudge;
        this.canCancel = canCancel;
    }


    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        if (showDialog) {
            if (canCancel) {
                iview.showLoading();
            } else {
                iview.showLoading(false);
            }
        }

    }


    @Override
    public void onError(Call call, Exception e, int i) {
        Log.e("xi", "onError: " + e.getCause());
        Log.e("xi", "onError: " + e.getMessage());
        if (showDialog) {
            iview.dismissLoading();
        }
        if (showErrorToast) {
            iview.onNetError();
        }

    }

    @Override
    public void onResponse(String s, int i) {
        if (showDialog) {
            iview.dismissLoading();
        }

        ErrorBean errorBean = GsonUtils.getGsonInstance().fromJson(s, ErrorBean.class);


        if (errorBean.getError() != 0) {
            if (showErrorToast) {
                iview.onError(errorBean.getMsg());
            }
            iview.onNoPermission();
        }

        if (errorBean.getError() == 405) {
            iview.onNoPermission();
        }

        if (isJudge && errorBean.getError() == 403) {
            iview.onTimeOut();
        }
        if (errorBean.getError() == 0) {
            if (showSuccessToast) {
                if (!TextUtils.isEmpty(errorBean.getMsg())) {
                    iview.onError(errorBean.getMsg());
                }
            }
        }


    }
}
