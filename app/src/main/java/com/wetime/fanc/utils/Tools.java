package com.wetime.fanc.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.wetime.fanc.R;
import com.wetime.fanc.application.FApp;
import com.wetime.fanc.circle.act.LongTextEditActivity;
import com.wetime.fanc.circle.act.PublishActActivity;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.act.PictureActivity;
import com.wetime.fanc.web.WebActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class Tools {
    public static final int REQUEST_IMAGE = 1004;
    private static Toast toast;
    private static Dialog loadingDialog;
    private int shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;

    public static SharePreferenceUtil getSpu(Context mContext) {
        return new SharePreferenceUtil(mContext.getApplicationContext(), "wetime");
    }

    public static void toastInBottom(Context context, String msg) {
        if (context != null) {
            if (toast == null) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 300);
            toast.show();
        }
    }

    public static void logout(Context mContext) {
        SharePreferenceUtil spu = getSpu(mContext);
        spu.setToken("");

    }

    public static void hideSoftInput(Activity mActivity) {
        try {
            IBinder mIBinder = mActivity.getCurrentFocus().getWindowToken();
            if (mIBinder != null)
                if ((mActivity.getSystemService(INPUT_METHOD_SERVICE)) != null) {
                    ((InputMethodManager) mActivity.getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(mIBinder, InputMethodManager.HIDE_NOT_ALWAYS);
                }

        } catch (Exception e) {
            Log.e("zk", e.toString());
        }
    }


    public static void showWaitDialog(Context context, boolean cancelable) {

//        loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
//        View loadingview = LayoutInflater.from(context.getApplicationContext())
//                .inflate(R.layout.loading_dialog, null);
//        loadingDialog.setContentView(loadingview);
//        loadingDialog.setCancelable(cancelable);
//        loadingDialog.show();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.loading_dialog, null);
        builder.setView(view);
        loadingDialog = builder.show();
        loadingDialog.setCancelable(cancelable);
        loadingDialog.setCanceledOnTouchOutside(cancelable);


//        LinearLayout layout = (LinearLayout) v.findViewById(R.id.loading_dialog);// 加载布局


//        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(
//                loadingview.findViewById(R.id.fl_load), "scaleX", 1.0f, 1f);
//        scaleXAnim.setRepeatCount(-1);
//        scaleXAnim.setRepeatMode(ValueAnimator.REVERSE);
//
//
//        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(
//                loadingview.findViewById(R.id.fl_load), "scaleY", 1.0f, 0.8f);
//        scaleYAnim.setRepeatCount(-1);
//        scaleYAnim.setRepeatMode(ValueAnimator.REVERSE);
//
//        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(
//                loadingview.findViewById(R.id.fl_load), "translationY", 0f, Tools.dip2px(context, 10));
//        transYAnim.setRepeatCount(-1);
//        transYAnim.setRepeatMode(ValueAnimator.REVERSE);
//
//        ObjectAnimator scaleXAnimB = ObjectAnimator.ofFloat(
//                loadingview.findViewById(R.id.iv_loading_bottom), "scaleX", 0.8f, 1.1f);
//        scaleXAnimB.setRepeatCount(-1);
//        scaleXAnimB.setRepeatMode(ValueAnimator.REVERSE);
//
//
//        ObjectAnimator scaleYAnimB = ObjectAnimator.ofFloat(
//                loadingview.findViewById(R.id.iv_loading_bottom), "scaleY", 1.0f, 1.3f);
//        scaleYAnimB.setRepeatCount(-1);
//        scaleYAnimB.setRepeatMode(ValueAnimator.REVERSE);
//
//
//        AnimatorSet set = new AnimatorSet();
//        set.setInterpolator(new AccelerateInterpolator());
//        set.playTogether(scaleXAnim, scaleYAnim, transYAnim, scaleXAnimB, scaleYAnimB);
//        set.setDuration(800);
//
//        set.start();
    }

    public static void showWaitDialog(Context context) {
        showWaitDialog(context, true);
    }

    public static boolean checkPrice(String price) {
        if (price.contains(".")) {
            if (price.substring(0, 1).equals(".")) {
                price = "0" + price;
            }
            String[] array = price.split("\\.");
            if (array.length > 1 && array[1].length() > 2) {
                return false;
            }
        }
        return true;
    }

    public static void hideWaitDialog() {
        if (loadingDialog != null)
            loadingDialog.dismiss();
    }

    public static void showTipsDialog(Context mContext, String title, String tips,
                                      final View.OnClickListener onCancel,
                                      final View.OnClickListener onOK) {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        LinearLayout layout = (LinearLayout) inflaterDl.inflate(
                R.layout.dialog_tips, null);
        final AlertDialog tel_dialog = new AlertDialog.Builder(mContext).create();

        TextView tvTitle = layout.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        }

        TextView tvtips = layout.findViewById(R.id.tv_delete_tips);
        tvtips.setText(tips);
        tel_dialog.show();
        tel_dialog.getWindow().setContentView(layout);
        TextView btnCancel = layout.findViewById(R.id.dialog_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onCancel != null)
                    onCancel.onClick(v);

            }
        });

        TextView btnOK = layout.findViewById(R.id.dialog_btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onOK != null)
                    onOK.onClick(v);
            }
        });

    }

    public static void showTipsDialog(Context mContext, String title, String tips, String left, String right,
                                      final View.OnClickListener onCancel,
                                      final View.OnClickListener onOK) {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        LinearLayout layout = (LinearLayout) inflaterDl.inflate(
                R.layout.dialog_tips, null);
        final AlertDialog tel_dialog = new AlertDialog.Builder(mContext).create();

        TextView tvTitle = layout.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        }

        TextView tvtips = layout.findViewById(R.id.tv_delete_tips);
        tvtips.setText(tips);
        tel_dialog.show();
        tel_dialog.getWindow().setContentView(layout);
        TextView btnCancel = layout.findViewById(R.id.dialog_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onCancel != null)
                    onCancel.onClick(v);

            }
        });

        TextView btnOK = layout.findViewById(R.id.dialog_btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onOK != null)
                    onOK.onClick(v);
            }
        });
        btnCancel.setText(left);
        btnOK.setText(right);

    }

    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return verCode;
    }

    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {

        }

        return verName;
    }

    public static void goMarket(Context mContext) {
        try {

            Uri uri = Uri.parse("market://details?id=" + mContext.getPackageName());// id为包名
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(it);

        } catch (Exception e) {
            toastInBottom(mContext, "请打开应用市场，搜索 " + mContext.getString(R.string.app_name));
            e.printStackTrace();
        }
    }

    public static boolean isAppInstalled(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //    public static int getScreenW(Activity mActivity) {
//        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
//        mActivity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
//
//        return mDisplayMetrics.widthPixels;
//    }
    public static int getScreenW(Context mContext) {
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();

        return dm.widthPixels;
    }

    public static SpannableString matcherSearchText(int color, String text, String keyword) {
        SpannableString ss = new SpannableString(text);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }

    public static void goWeb(Context mContext, String url) {
        if (TextUtils.isEmpty(url))
            return;
        Intent goweb = new Intent(mContext, WebActivity.class);
        goweb.putExtra("url", url);
        mContext.startActivity(goweb);
    }

    public static void goActivity(Context mContext, Class<?> cls) {
        Intent go = new Intent(mContext, cls);
        mContext.startActivity(go);
    }

    public static void goLogin(Context mContext) {
        Intent go = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(go);
    }


    private static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 70, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 70;
        while (baos.toByteArray().length / 1024 > 20 && options > 0) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            Log.e("xi", "compressImage: " + (image.getRowBytes() * image.getHeight()));
            Log.e("xi", "compressImage: " + baos.toByteArray().length);
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        Log.e("xi", "compressImage: " + (getBitmapSize(bitmap) / 1000 > 31));
        if (getBitmapSize(bitmap) / 1000 > 31) {
            compressImage(bitmap);
        }
        return bitmap;
    }

    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
            return bitmap.getByteCount();
        }
        // 在低版本中用一行的字节x高度
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }

    public static void shareWx(Context mContext, Bitmap thumb, String url, int type, String title, String des) {

        shareWx(mContext, thumb, url, type, title, des, 1);
    }


    public static void shareWx(Context mContext, Bitmap thumb, String url, int type, String title, String des, int size) {

        if (!FApp.mWxApi.isWXAppInstalled()) {
            Tools.toastInBottom(mContext, "您没有安装微信");
            return;
        }
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = des;
        if (size != 0) {
            File file = saveImageToGallery(mContext, thumb);
            Luban.compress(mContext, file)
                    .putGear(Luban.CUSTOM_GEAR)
                    .setMaxSize(31)
                    .launch(new OnCompressListener() {
                        @Override
                        public void onStart() {
                            Log.e("xi", "start");
                        }

                        @Override
                        public void onSuccess(File file) {
                            Log.e("TAG", file.getAbsolutePath());
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                            Log.e("TAG", getBitmapSize(thumb) + "");
                            Log.e("TAG", getBitmapSize(bitmap) + "");
//                            mImageViews.get(0).setImageURI(Uri.fromFile(file));
                            msg.thumbData = bmpToByteArray(bitmap, true);
                            SendMessageToWX.Req req = new SendMessageToWX.Req();
                            req.transaction = buildTransaction("webpage");
                            req.message = msg;
                            req.scene = type;
                            IWXAPI mWxApi = WXAPIFactory.createWXAPI(mContext, "wx2fbcb61b6e5b1384", true);
                            boolean b = mWxApi.sendReq(req);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }
                    });

//            Luban.compress(mContext, file)
//                    .setMaxSize(32)                // limit the final image size（unit：Kb）
//                    .setMaxHeight(1920)             // limit image height
//                    .setMaxWidth(1080)              // limit image width
//                    .putGear(Luban.CUSTOM_GEAR)     // use CUSTOM GEAR compression mode
//                    .asObservable();
            return;
        }

        msg.thumbData = bmpToByteArray(thumb, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = type;
        IWXAPI mWxApi = WXAPIFactory.createWXAPI(mContext, "wx2fbcb61b6e5b1384", true);
        boolean b = mWxApi.sendReq(req);


    }

    public static void shareWb(Context mContext, WbShareHandler shareHandler, Bitmap bitmap, String url, String title, String des) {


        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();

//        weiboMessage.textObject = getTextObj(url, title, des);

        weiboMessage.imageObject = getImageObj(bitmap);

        weiboMessage.mediaObject = getWebpageObj(bitmap, url, title, des);

        shareHandler.shareMessage(weiboMessage, false);


    }

    /**
     * 创建文本消息对象。
     *
     * @return 文本消息对象。
     */
    private static TextObject getTextObj(String url, String title, String des) {
        TextObject textObject = new TextObject();
        textObject.text = des;
        textObject.title = title;
        textObject.actionUrl = url;
        return textObject;
    }

    private static ImageObject getImageObj(Bitmap bitmap) {
        ImageObject imageObject = new ImageObject();
        imageObject.setImageObject(bitmap);
        return imageObject;
    }

    private static WebpageObject getWebpageObj(Bitmap bitmap, String url, String title, String des) {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = title;
        mediaObject.description = des;
        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl = url;
        return mediaObject;
    }

    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void goPicGallery(Activity mActivity, List<String> list, int index) {
        Intent pic = new Intent(mActivity, PictureActivity.class);
        pic.putExtra("big_photo", (Serializable) list);
        pic.putExtra("page", index);
        mActivity.startActivity(pic);
        mActivity.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    public static void goPicGallery(Activity mActivity, List<String> list, List<String> contents, int index) {
        Intent pic = new Intent(mActivity, PictureActivity.class);
        pic.putExtra("big_photo", (Serializable) list);
        pic.putExtra("page", index);
        pic.putExtra("contents", (Serializable) contents);
        mActivity.startActivity(pic);
        mActivity.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    public static void shareQQ(Activity mContext, String url, String ImageUrl, String title, String des, IUiListener iUiListener) {
        Bundle bundle = new Bundle();
//这条分享消息被好友点击后的跳转URL。
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
//分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_	SUMMARY不能全为空，最少必须有一个是有值的。
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
//分享的消息摘要，最长50个字
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, des);
//手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
//        bundle.putString(Constants.PARAM_APPNAME, "??我在测试");
//标识该消息的来源应用，值为应用名称+AppId。
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, "范团");
//                 /*QQ分享增加ARK end*/
//
//        //分享的图片URL
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, ImageUrl);


        FApp.mTencent.shareToQQ(mContext, bundle, iUiListener);
    }

    public static void shareToQzone(Activity mContext, String url, String imageUrl, String title, String des, IUiListener iUiListener) {
//        Bundle bundle = new Bundle();
////分享类型
//        bundle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
//        bundle.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);//必填
//        bundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, des);//选填
//        bundle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);//必填
//        FApp.mTencent.shareToQzone(mContext, bundle, iUiListener);

        Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);//类型
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);//标题
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, des);//概要
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);
        //下面这个必须加上  不然无法调动 qq空间
        ArrayList<String> imageUrls = new ArrayList<String>();
        imageUrls.add(imageUrl);
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
        FApp.mTencent.shareToQzone(mContext, params, iUiListener);
    }

    public static void showPopWin(Context context, View ivEdit, String id, String name, String simpleName) {

        if (TextUtils.isEmpty(((BaseActivity) context).spu.getToken())) {
            Tools.toastInBottom(context, "请先登录");
            Intent goLogin = new Intent(context, LoginActivity.class);
            context.startActivity(goLogin);
            return;
        }


        View popupView = LayoutInflater.from(context).inflate(R.layout.layout_popupwindow, null);

        PopupWindow window = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        window.setAnimationStyle(R.style.popup_window_anim);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setElevation(20);
        }

        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));

        window.setFocusable(true);

        window.setOutsideTouchable(true);

        window.update();

        ivEdit.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        int mShowMorePopupWindowWidth = (int) (-ivEdit.getMeasuredWidth() * 2.2);

        Log.e("xi", "showPopWin: " + mShowMorePopupWindowWidth);

        window.showAsDropDown(ivEdit, mShowMorePopupWindowWidth, 0);

        popupView.findViewById(R.id.pop_duan).setOnClickListener(v -> {
            if (((BaseActivity) context).spu.getToken().equals("")) {
                Intent gologin = new Intent(context, LoginActivity.class);
                context.startActivity(gologin);
                if (window.isShowing()) {
                    window.dismiss();
                }
            } else {
                Intent goPublish = new Intent(context, PublishActActivity.class);
                String mCircleID = id;
                if (null == mCircleID) {
                    mCircleID = "";
                }
                goPublish.putExtra("id", mCircleID);
                goPublish.putExtra("simpleName", simpleName);
                context.startActivity(goPublish);
                if (window.isShowing()) {
                    window.dismiss();
                }
            }
        });

        popupView.findViewById(R.id.pop_chang).setOnClickListener(v -> {
            Intent gosearch = new Intent(context, LongTextEditActivity.class);
            // add by zhoukang
            gosearch.putExtra("name", name);
            gosearch.putExtra("id", id);
            gosearch.putExtra("simpleName", simpleName);
            context.startActivity(gosearch);
            if (window.isShowing()) {
                window.dismiss();
            }
        });
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return false;
        }
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }
        return true;
    }

    public static File saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();//注意小米手机必须这样获得public绝对路径
        String fileName = "ningjing";
        File appDir = new File(file, fileName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        fileName = System.currentTimeMillis() + ".jpg";
        File currentFile = new File(appDir, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    currentFile.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        // 最后通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
//                Uri.fromFile(new File(currentFile.getPath()))));
        return currentFile;
    }
}
