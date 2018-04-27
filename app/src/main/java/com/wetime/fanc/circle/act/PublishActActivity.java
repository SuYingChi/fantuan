package com.wetime.fanc.circle.act;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.DefaultCircleBean;
import com.wetime.fanc.circle.bean.LocItemBean;
import com.wetime.fanc.circle.bean.LocStrBean;
import com.wetime.fanc.circle.bean.PublishResultBean;
import com.wetime.fanc.circle.iviews.IGetDefaultCircleView;
import com.wetime.fanc.circle.iviews.IGetLocStrView;
import com.wetime.fanc.circle.iviews.IPublishCircleView;
import com.wetime.fanc.circle.presenter.GetDefaultCirclePresenter;
import com.wetime.fanc.circle.presenter.GetLocStrPresenter;
import com.wetime.fanc.customview.GridViewForScrollView;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.bean.PostFileResultBean;
import com.wetime.fanc.main.ivews.IPostMultiFileView;
import com.wetime.fanc.order.adapter.ImageGridAdapter;
import com.wetime.fanc.service.UploadImageService;
import com.wetime.fanc.utils.Tools;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishActActivity extends BaseActivity implements IPostMultiFileView, AdapterView.OnItemClickListener, IGetDefaultCircleView, IPublishCircleView, IGetLocStrView {


    public static final int REQUEST_CIRCLE = 1008;
    public static final int REQUEST_LOC = 1009;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_publish)
    TextView tvPublish;
    @BindView(R.id.tv_circlename)
    TextView tvCirclename;
    @BindView(R.id.tv_select_circle)
    TextView tvSelectCircle;
    @BindView(R.id.llbo)
    LinearLayout llbo;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.appraise_img_gv)
    GridViewForScrollView gv;
    @BindView(R.id.tv_addres)
    TextView tvAddres;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    private ArrayList<String> defaultDataArray = new ArrayList<>();
    private ImageGridAdapter mPicGridAdapter;
    private String mCircleID;
    private LocItemBean locBean = new LocItemBean();
    private List<LocalMedia> selectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSoftInPutMode();
        setContentView(R.layout.activity_publish_circle);
        ButterKnife.bind(this);
        initView();
        mCircleID = getIntent().getStringExtra("id");
        if (null == mCircleID) {
            mCircleID = "";
        }
        GetDefaultCirclePresenter getDefaultCirclePresenter = new GetDefaultCirclePresenter(this);
        getDefaultCirclePresenter.getDefaultCircle();
    }

    private void initView() {
        tvTitle.setText("发动态");
        mPicGridAdapter = new ImageGridAdapter(mContext, defaultDataArray);
        gv.setAdapter(mPicGridAdapter);
        gv.setOnItemClickListener(this);
    }

    @Override
    protected void setSoftInPutMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    protected void initStateBar() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.white_lib)
                .statusBarDarkFont(true, 0.2f)
                .fitsSystemWindows(true)
                .keyboardEnable(true)
                .init();
    }

    @Override
    public void onBackPressed() {
        if (etContent.getText().toString().length() == 0) {
            Tools.hideSoftInput(this);
            super.onBackPressed();
        } else {
            Tools.showTipsDialog(mContext, "", "确定要退出编辑?", null, v -> {
                Tools.hideSoftInput(PublishActActivity.this);
                finish();
            });
        }
    }

    @OnClick({R.id.iv_close, R.id.iv_back, R.id.tv_publish, R.id.tv_select_circle, R.id.tv_addres})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_close:
                tvAddres.setText(getString(R.string.str_where_are_you));
                ivClose.setVisibility(View.GONE);
                Drawable drawable = getResources().getDrawable(R.drawable.ic_loc_off);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                tvAddres.setCompoundDrawables(drawable, null, null, null);
                tvAddres.setTextColor(ContextCompat.getColor(mContext, R.color.text_hint));
                break;
            case R.id.tv_publish:
                if (etContent.getText().toString().length() >= 10 || defaultDataArray.size() > 0) {
//                    PostMultiFilePresenter postMultiFilePresenter = new PostMultiFilePresenter(this);
//                    postMultiFilePresenter.PostMultiFile(defaultDataArray);
                    Intent intent = new Intent(this, UploadImageService.class);
                    intent.putStringArrayListExtra("defaultDataArray", defaultDataArray);
                    intent.putExtra("token", getToken());
                    intent.putExtra("CircleId", getCircleId());
                    intent.putExtra("CJd", getCJd());
                    intent.putExtra("CWd", getCWd());
                    intent.putExtra("Loc", getLoc());
                    intent.putExtra("content", getContent());
                    intent.putExtra("simpleName", getIntent().getStringExtra("simpleName"));
                    startService(intent);
                    Tools.hideSoftInput(PublishActActivity.this);
                    finish();
                } else {
                    Tools.toastInBottom(mContext, "内容还不够10个字~");
                }
                break;
            case R.id.tv_select_circle:
                Intent goSelect = new Intent(mContext, SelectCircleActivity.class);
                startActivityForResult(goSelect, REQUEST_CIRCLE);
                break;
            case R.id.tv_addres:
                AndPermission.with(this)
                        .permission(Permission.Group.LOCATION, Permission.Group.STORAGE)
                        .onGranted(permissions -> {
                            // TODO what to do.
                            Intent goloc = new Intent(mContext, SelectLocActivity.class);
                            locBean.setSelected(true);
                            goloc.putExtra("loc", locBean);
                            startActivityForResult(goloc, REQUEST_LOC);
                        }).onDenied(permissions -> {
                    // TODO what to do
                    Tools.toastInBottom(mContext, "为了更好使用范团，请赋予权限");
                })
                        .start();


                break;
        }
    }

    @Override
    public void onPostResult(PostFileResultBean bean) {
//        PublishCirclePresenter publishCirclePresenter = new PublishCirclePresenter(this);
//        publishCirclePresenter.publishCircle((GsonUtils.getGsonInstance().toJson(bean.getData().getId())));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PictureConfig.CHOOSE_REQUEST) {
            if (resultCode == RESULT_OK) {
                selectList = PictureSelector.obtainMultipleResult(data);
                ArrayList<String> pathlist = new ArrayList<>();
                for (LocalMedia lm : selectList) {
                    if (lm.isCompressed()) {
                        pathlist.add(lm.getCompressPath());
                    } else {
                        pathlist.add(lm.getPath());
                    }
                }
                defaultDataArray.clear();
                defaultDataArray.addAll(pathlist);
                mPicGridAdapter = new ImageGridAdapter(mContext,
                        defaultDataArray);
                mPicGridAdapter.setOnDeleteClickLitener((view, position) -> {
                    defaultDataArray.remove(position);
                    mPicGridAdapter.notifyDataSetChanged();
                });
                gv.setAdapter(mPicGridAdapter);
                mPicGridAdapter.notifyDataSetChanged();
            }
        }

        if (requestCode == REQUEST_CIRCLE) {
            if (resultCode == RESULT_OK) {
                tvCirclename.setText(data.getStringExtra("name"));
                mCircleID = data.getStringExtra("id");
            }
        }
        if (requestCode == REQUEST_LOC) {
            if (resultCode == RESULT_OK) {
                locBean = (LocItemBean) data.getSerializableExtra("loc");
                if (TextUtils.isEmpty(locBean.getTitle())) {
                    tvAddres.setText(getString(R.string.str_where_are_you));
                    ivClose.setVisibility(View.GONE);
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_loc_off);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                    tvAddres.setCompoundDrawables(drawable, null, null, null);
                    tvAddres.setTextColor(ContextCompat.getColor(mContext, R.color.text_hint));
                } else {
                    if (locBean.getTitle().length() > 8) {
                        tvAddres.setText(String.format("%s...", locBean.getTitle().substring(0, 8)));
                    } else {
                        tvAddres.setText(locBean.getTitle());
                    }
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_loc_on);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                    tvAddres.setCompoundDrawables(drawable, null, null, null);
                    tvAddres.setTextColor(ContextCompat.getColor(mContext, R.color.text_blue));
                    ivClose.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    private void gotoSelectPic() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(9)
                .theme(R.style.picture_my_style)
                .previewImage(true)
                .isCamera(true)
                .compress(true)
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (position >= defaultDataArray.size()) {
            gotoSelectPic();
        }
    }

    @Override
    public void onGetDefaultCircle(DefaultCircleBean bean) {
        mCircleID = bean.getData().getId();
        tvCirclename.setText(bean.getData().getName());
        if (bean.getData().isLast_position()) {
            if (!TextUtils.isEmpty(getJd()) && !TextUtils.isEmpty(getWd())) {
                GetLocStrPresenter getLocStrPresenter = new GetLocStrPresenter(this);
                getLocStrPresenter.getLocStr();
            }
        }
    }

    @Override
    public void onPublisResultCircle(PublishResultBean bean) {
        finish();
        Intent go = new Intent(mContext, ActDetailActivity.class);
        go.putExtra("id", bean.getData().getId());
        startActivity(go);
    }


    @Override
    public String getCircleId() {
        return mCircleID;
    }

    @Override
    public String getContent() {
        return etContent.getText().toString();
    }

    @Override
    public String getCJd() {
        if (locBean == null
                || TextUtils.equals(getString(R.string.str_where_are_you), tvAddres.getText().toString())) {
            return "";
        }
        return locBean.getLng();
    }

    @Override
    public String getCWd() {
        if (locBean == null
                || TextUtils.equals(getString(R.string.str_where_are_you), tvAddres.getText().toString())) {
            return "";
        }
        return locBean.getLat();
    }

    @Override
    public String getLoc() {
        if (locBean == null
                || TextUtils.equals(getString(R.string.str_where_are_you), tvAddres.getText().toString())) {
            return "";
        }
        return locBean.getTitle();
    }

    @Override
    public void onGetLocStr(LocStrBean bean) {
        this.locBean = bean.getData();
        if (bean.getData().getTitle().length() > 8) {
            tvAddres.setText(String.format("%s...", bean.getData().getTitle().substring(0, 8)));
        } else {
            tvAddres.setText(bean.getData().getTitle());
        }
        Drawable drawable = getResources().getDrawable(R.drawable.ic_loc_on);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
        tvAddres.setCompoundDrawables(drawable, null, null, null);
        tvAddres.setTextColor(ContextCompat.getColor(mContext, R.color.text_blue));
        ivClose.setVisibility(View.VISIBLE);
    }
}
