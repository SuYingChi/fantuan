package com.wetime.fanc.wallet.act;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.DownLoadImageService;
import com.wetime.fanc.utils.Tools;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.shaohui.bottomdialog.BottomDialog;

public class InviteQRActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    private Handler mHandler;
    private BottomDialog mBottomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inviteqr);
        ButterKnife.bind(this);
        tvTitle.setText("邀请二维码");
        mHandler = new Handler();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.tv_down, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_down:
                downQRcode("");
                break;
            case R.id.iv_right:
                showShare();
                break;

        }
    }

    private void showShare() {
        if (mBottomDialog == null) {
            mBottomDialog = BottomDialog.create(getSupportFragmentManager());
            mBottomDialog.setLayoutRes(R.layout.item_share_bottom);
            mBottomDialog.setViewListener(new BottomDialog.ViewListener() {
                @Override
                public void bindView(View v) {
                    v.findViewById(R.id.ll_share_wx).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mBottomDialog.dismiss();
                            shareWx("http://www.baidu.com", SendMessageToWX.Req.WXSceneSession);
                        }
                    });
                    v.findViewById(R.id.ll_share_wxq).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mBottomDialog.dismiss();
                            shareWx("http://www.baidu.com", SendMessageToWX.Req.WXSceneTimeline);
                        }
                    });
                    v.findViewById(R.id.ll_share_copy).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mBottomDialog.dismiss();
                            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            // 将文本内容放到系统剪贴板里。
                            cm.setText("");
                            Tools.toastInBottom(mContext, "复制成功");
                        }
                    });
                    v.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mBottomDialog.dismiss();
                        }
                    });
                }
            });
        }
        mBottomDialog.show();

    }

    private void downQRcode(String url) {
        DownLoadImageService service = new DownLoadImageService(mContext.getApplicationContext(),
                url,
                new DownLoadImageService.ImageDownLoadCallBack() {

                    @Override
                    public void onDownLoadSuccess(File file) {
                    }

                    @Override
                    public void onDownLoadSuccess(Bitmap bitmap) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Tools.toastInBottom(mContext, "保存成功");
                            }
                        });
                    }

                    @Override
                    public void onDownLoadFailed() {
                        Tools.toastInBottom(mContext, "保存失败");
                    }
                });
        //启动图片下载线程
        new Thread(service).start();
    }

    /**
     * @param url
     * @param type SendMessageToWX.Req.WXSceneSession
     *             SendMessageToWX.Req.WXSceneTimeline
     */
    private void shareWx(String url, int type) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "下载范团APP，扫此二维码，就就有现金可拿";
        msg.description = "海南潮人城市生活指南APP";

        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        msg.thumbData = bmpToByteArray(thumb, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = type;
        IWXAPI mWxApi = WXAPIFactory.createWXAPI(this, "wx2fbcb61b6e5b1384", true);
        mWxApi.sendReq(req);

    }

    private String buildTransaction(final String type) {
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
}
