package com.wetime.fanc.news.frag;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.photoview.MyViewPager;
import com.wetime.fanc.handler.CommonHandler;
import com.wetime.fanc.handler.IHandlerMessage;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.my.bean.AttentionBean;
import com.wetime.fanc.news.act.CommentActivity;
import com.wetime.fanc.news.act.GalleryActivity;
import com.wetime.fanc.news.adapter.GalleryAdapter;
import com.wetime.fanc.news.bean.GalleryCommentBean;
import com.wetime.fanc.news.bean.GalleryItemBean;
import com.wetime.fanc.news.iviews.IGetNewsDetailView;
import com.wetime.fanc.news.presenter.GetNewsDetailPresenter;
import com.wetime.fanc.utils.ToastUtils;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends BaseLazyFragment implements IHandlerMessage, View.OnClickListener, DialogInterface.OnShowListener, DialogInterface.OnDismissListener,
        ViewPager.OnPageChangeListener, GalleryAdapter.OnGalleryAdapterCallback, IGetNewsDetailView {

    private static final int LOAD_STATE_FAIL = 1;
    private static final int LOAD_STATE_SUCC = 2;
    private static final int LOAD_STATE_LOADING = 3;
    private static final int MSG_CALL_DATA_FAIL = 21;
    private static final int MSG_CALL_DATA_SUCC = 22;
    private static final int MSG_CALL_NO_DATA = 23;
    private static final int MSG_PIC_SAVE_SUCC = 24;
    private static final int MSG_PIC_SAVE_FAIL = 25;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public boolean isShowInput = false;
    private CommonHandler<GalleryFragment> handler;
    private View rootView;
    private MyViewPager viewPager;
    private TextView mDescNumber;
    private View mSavePicParent;
    private LinearLayout mComment;
    private TextView mSaveCurrPos;
    private View mSaveBtn;
    private View mDescParent;
    private GalleryAdapter adapter;
    private ArrayList<GalleryItemBean.DataBean.AtlasContentBean> data = new ArrayList<>();
    private GalleryItemBean gallery = null;
    private long id;
    private boolean isDismiss;
    private OnPhotoTapListener onPhotoTapListener;
    private int loadState = LOAD_STATE_LOADING;
    private int fini_rate;//0-100
    private int diffTest = 0;
    private String galleryId = "";
    private GetNewsDetailPresenter getNewsDetailPresenter;
    private GalleryActivity mGalleryActivity;
    private TextView mGalleryText;
    private EditText mGalleryEdit;
    private LinearLayout mGalleryLinear;
    private EditText mGalleryCurrEdit;
    private TextView mGalleryTextView;
    private ImageView collertImage;
    private View shareImage;
    private PopupWindow pop;

    public static GalleryFragment newInstance(@Nullable Bundle bundle) {
        GalleryFragment fragment = new GalleryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mGalleryActivity = (GalleryActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
            initView(rootView);
        }
        galleryId = this.getArguments().getString("galleryId");
        initLisner();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        initData();

    }

    public void initLisner() {
        mDescParent.setOnClickListener(this);
        mSaveBtn.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
        rootView.findViewById(R.id.gallery_imageview).setOnClickListener(this);
        mGalleryText.setOnClickListener(this);
        collertImage.setOnClickListener(this);
        shareImage.setOnClickListener(this);
        mGalleryEdit.setOnClickListener(this);
        mGalleryTextView.setOnClickListener(this);
    }

    private void initView(View rootView) {
        handler = new CommonHandler<GalleryFragment>(this);
        mSaveCurrPos = (TextView) rootView.findViewById(R.id.gallery_curr_pos);
        mSaveBtn = rootView.findViewById(R.id.gallery_save_btn);
        mSavePicParent = rootView.findViewById(R.id.gallery_save_layout);
        collertImage = rootView.findViewById(R.id.gallery_collect);
        shareImage = rootView.findViewById(R.id.gallery_share);
        mGalleryText = rootView.findViewById(R.id.gallery_text);
        mComment = rootView.findViewById(R.id.gallery_linear);
        viewPager = (MyViewPager) rootView.findViewById(R.id.fvp_gallery);
        mDescNumber = (TextView) rootView.findViewById(R.id.tv_desc_number);
        mDescParent = rootView.findViewById(R.id.gallery_desc_layout);
        mGalleryEdit = rootView.findViewById(R.id.gallery_editText);
        mGalleryLinear = rootView.findViewById(R.id.gallery_curr_LinearLayout);
        mGalleryCurrEdit = rootView.findViewById(R.id.gallery_curr_EditText);
        mGalleryTextView = rootView.findViewById(R.id.gallery_curr_TextView);

        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
        adapter = new GalleryAdapter(this.getContext(), this);
        viewPager.setAdapter(adapter);

        View decorView = getActivity().getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(getGlobalLayoutListener(decorView, mGalleryLinear));

    }

    @Override
    protected int setLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        super.initView();
        requestData();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    protected boolean isLazyLoad() {
        return super.isLazyLoad();
    }

    private ViewTreeObserver.OnGlobalLayoutListener getGlobalLayoutListener(final View decorView, final LinearLayout contentView) {
        return () -> {
            Rect r = new Rect();
            decorView.getWindowVisibleDisplayFrame(r);

            int height = decorView.getContext().getResources().getDisplayMetrics().heightPixels;
            int diff = height - r.bottom;
            if (diff > 0) {
                mGalleryLinear.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) layoutParams;
                    lp.setMargins(0, 0, 0, diff);
                    contentView.setLayoutParams(lp);
                } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) layoutParams;
                    lp.setMargins(0, 0, 0, diff);
                    contentView.setLayoutParams(lp);
                }

                mGalleryCurrEdit.setFocusable(true);
                mGalleryCurrEdit.setFocusableInTouchMode(true);
                mGalleryCurrEdit.requestFocus();
            } else {
                mGalleryLinear.setVisibility(View.GONE);
            }
            diffTest = diff;
        }

                ;
    }

    private void requestData() {
        getNewsDetailPresenter = new GetNewsDetailPresenter(this);
        getNewsDetailPresenter.getNewsDetail(galleryId);
    }

    private void updateGalleryItems(List<GalleryItemBean.DataBean.AtlasContentBean> items) {
        adapter.update(items);
        onPageSelected(0);
    }

    @Override
    public void handlerCallback(Message msg) {
        switch (msg.what) {
            case MSG_CALL_DATA_SUCC:
                if (data != null && data.size() > 0) {
                    loadState = LOAD_STATE_SUCC;
                    updateGalleryItems(data);
                }
                break;
            case MSG_CALL_DATA_FAIL:
                loadState = LOAD_STATE_FAIL;
                break;
            case MSG_CALL_NO_DATA:
                loadState = LOAD_STATE_SUCC;
                break;
            case MSG_PIC_SAVE_SUCC:
                ToastUtils.toastCenter(getActivity(), R.string.save_image_succ);
                break;
            case MSG_PIC_SAVE_FAIL:
                ToastUtils.toastCenter(getActivity(), R.string.save_image_fail);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rootView = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onPageSelected(int position) {
        mDescNumber.setText((position + 1) + "/" + data.size() + "   " + data.get(position).getContent());
        if (gallery != null) {
            mGalleryText.setText(gallery.getData().getComment_num());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gallery_desc_layout:
                onPhotoTap(null);
                break;
            case R.id.gallery_save_btn:
                break;
            case R.id.gallery_imageview:
            case R.id.gallery_text:
                CommentActivity.startToComment(getActivity(), galleryId);
                break;
            case R.id.gallery_collect:
                if (gallery.getData().isIs_collect()) {
                    getNewsDetailPresenter.collectNews(galleryId, "0");
                } else {
                    getNewsDetailPresenter.collectNews(galleryId, "1");
                }
                break;
            case R.id.gallery_share:
                showPop();
                break;
            case R.id.gallery_curr_TextView:
                String s = String.valueOf(mGalleryCurrEdit.getText());
                if (s.equals("null")) {
                    Toast.makeText(mGalleryActivity, "请先填写您的评论", Toast.LENGTH_SHORT).show();
                    return;
                }
                getNewsDetailPresenter.sendCommonet(galleryId, s);
                break;
            case R.id.gallery_editText:
                isShowInput = true;
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.ll_share_wx:
                Tools.shareWx(getContext(), gallery.getData().getAtlas_url(), SendMessageToWX.Req.WXSceneSession, "Test", "Test");
                break;
            case R.id.ll_share_wxq:
                Tools.shareWx(getContext(), gallery.getData().getAtlas_url(), SendMessageToWX.Req.WXSceneTimeline, "Test", "Test");
                break;
            case R.id.ll_share_wb:
                Toast.makeText(mGalleryActivity, "功能正在开发中!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_share_qq:
                Tools.shareQQ(getActivity(), gallery.getData().getAtlas_url(), "Test", "Test", new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        Toast.makeText(mGalleryActivity, "分享成功!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Toast.makeText(mGalleryActivity, "未知错误!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(mGalleryActivity, "分享取消!", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.ll_share_qqkj:
                Tools.shareToQzone(getActivity(), gallery.getData().getAtlas_url(), "Test", "Test", new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        Toast.makeText(mGalleryActivity, "分享成功!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Toast.makeText(mGalleryActivity, "未知错误!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(mGalleryActivity, "分享取消!", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.ll_share_copy:
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setPrimaryClip(ClipData.newPlainText("text", gallery.getData().getAtlas_url()));
                Tools.toastInBottom(getContext(), "复制成功");
                break;
            case R.id.pop_cancel:
                if (pop.isShowing()) {
                    pop.dismiss();
                }
                break;
        }
    }

    private void showPop() {
        View shareView = LayoutInflater.from(getActivity()).inflate(R.layout.view_popupwindow, null);

        initPopListener(shareView);

        // 创建PopupWindow对象
        pop = new PopupWindow(shareView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        // 需要设置一下此参数，点击外边可消失
        pop.setBackgroundDrawable(new ColorDrawable());
        // 设置点击窗口外边窗口消失
        pop.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        pop.setFocusable(true);

        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        getActivity().getWindow().setAttributes(lp);

        pop.setOnDismissListener(() -> {
            // popupWindow隐藏时恢复屏幕正常透明度
            WindowManager.LayoutParams lp1 = getActivity().getWindow().getAttributes();
            lp1.alpha = 1.0f;
            getActivity().getWindow().setAttributes(lp1);
        });

        pop.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    private void initPopListener(View shareView) {
        shareView.findViewById(R.id.ll_share_wx).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_wxq).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_wb).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_qq).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_qqkj).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_copy).setOnClickListener(this);
        shareView.findViewById(R.id.pop_cancel).setOnClickListener(this);
    }

    public void hideInput() {
        if (isShowInput) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mGalleryEdit.getWindowToken(), 0);
            isShowInput = false;
        }
    }

    public void AttentionFriends() {

        if (mGalleryActivity.getTextString().equals("已关注")) {//0、取消1、关注
            getNewsDetailPresenter.attentionFriends("0", gallery.getData().getUid());
        } else {
            getNewsDetailPresenter.attentionFriends("1", gallery.getData().getUid());
        }


    }


    @Override
    public void onPhotoTap(GalleryItemBean.DataBean.AtlasContentBean item) {

        if (isShowInput) {
            hideInput();
            return;
        }

        if (isDismiss) {
            //保存相关
            mSavePicParent.setVisibility(View.GONE);

            //下方描述输入相关
            mDescParent.setVisibility(View.VISIBLE);
            mComment.setVisibility(View.VISIBLE);
            if (onPhotoTapListener != null) {
                onPhotoTapListener.onShowView();
            }
        } else {
            mSavePicParent.setVisibility(View.VISIBLE);

            if (viewPager != null && data != null) {
                mSaveCurrPos.setText((viewPager.getCurrentItem() + 1) + "/" + data.size());
            }

            //下方描述输入相关
            mDescParent.setVisibility(View.GONE);
            mComment.setVisibility(View.GONE);
            if (onPhotoTapListener != null) {
                onPhotoTapListener.onDismissView();
            }
        }
        isDismiss = !isDismiss;
    }

    private void saveImage() {
        if (data == null) {
            ToastUtils.toastCenter(getActivity(), R.string.save_image_fail);
            return;
        }
//        final String url = data.get(viewPager.getCurrentItem()).getImage();
//        ThreadPoolUtils.execute(new Runnable() {
//            @Override
//            public void run() {
//                ImageLoaderUtil.getInstance().saveImage(getActivity(), url,
//                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/gallery",
//                        "pic" + System.currentTimeMillis(), new ImageSaveListener() {
//                            @Override
//                            public void onSaveSuccess() {
//                                handler.obtainMessage(MSG_PIC_SAVE_SUCC).sendToTarget();
//                            }
//
//                            @Override
//                            public void onSaveFail() {
//                                handler.obtainMessage(MSG_PIC_SAVE_FAIL).sendToTarget();
//                            }
//                        });
//            }
//        });
    }

    public boolean isLastItem() {
        return data == null || viewPager.getCurrentItem() == data.size() - 1;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {

    }

    @Override
    public void onShow(DialogInterface dialog) {

    }

    public void setOnPhotoTapListener(OnPhotoTapListener listener) {
        this.onPhotoTapListener = listener;
    }


    public void onGetNewDetail(GalleryItemBean bean) {
        mGalleryActivity.drawingView(bean);
        gallery = bean;
        if (bean.getError() == 0) {
            if (bean.getData().getAtlas_content().size() == 0) {
                handler.obtainMessage(MSG_CALL_NO_DATA).sendToTarget();
            } else {

                if (gallery.getData().isIs_collect()) {
                    collertImage.setImageResource(R.drawable.icon_collert);
                } else {
                    collertImage.setImageResource(R.drawable.star_2);
                }

                data.addAll(bean.getData().getAtlas_content());
                handler.obtainMessage(MSG_CALL_DATA_SUCC).sendToTarget();
            }
        } else {
            handler.obtainMessage(MSG_CALL_DATA_FAIL).sendToTarget();
        }
    }

    @Override
    public void onAttentionFriends(AttentionBean bean) {
        if (bean.getError() == 0) mGalleryActivity.drawingAttring(bean);
    }

    @Override
    public void onCollectNews() {
        if (gallery.getData().isIs_collect()) {
            collertImage.setImageResource(R.drawable.star_2);
            gallery.getData().setIs_collect(false);
        } else {
            collertImage.setImageResource(R.drawable.icon_collert);
            gallery.getData().setIs_collect(true);
        }
    }

    @Override
    public void onSendCommont(GalleryCommentBean bean) {
        if (bean.getError() == 0) {
            Toast.makeText(mGalleryActivity, "评论成功", Toast.LENGTH_SHORT).show();
            hideInput();
            mGalleryCurrEdit.setText("");
        }
    }

    public interface OnPhotoTapListener {
        void onShowView();

        void onDismissView();
    }
}
