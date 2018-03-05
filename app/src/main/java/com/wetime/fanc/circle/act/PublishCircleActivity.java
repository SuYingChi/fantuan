package com.wetime.fanc.circle.act;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.DefaultCircleBean;
import com.wetime.fanc.circle.iviews.IGetDefaultCircleView;
import com.wetime.fanc.circle.iviews.IPublishCircleView;
import com.wetime.fanc.circle.presenter.GetDefaultCirclePresenter;
import com.wetime.fanc.circle.presenter.PublishCirclePresenter;
import com.wetime.fanc.customview.GridViewForScrollView;
import com.wetime.fanc.customview.multiimageselector.MultiImageSelectorActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.bean.PostFileResultBean;
import com.wetime.fanc.main.ivews.IPostMultiFileView;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.main.presenter.PostMultiFilePresenter;
import com.wetime.fanc.order.adapter.ImageGridAdapter;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wetime.fanc.utils.Tools.REQUEST_IMAGE;

public class PublishCircleActivity extends BaseActivity implements IPostMultiFileView, AdapterView.OnItemClickListener, IGetDefaultCircleView, IPublishCircleView {


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


    private ArrayList<String> defaultDataArray = new ArrayList<>();
    private ImageGridAdapter mPicGridAdapter;
    private String mCircleID;
    public static final int REQUEST_CIRCLE = 1008;

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
        Tools.hideSoftInput(this);
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.tv_publish, R.id.tv_select_circle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_publish:
                if (!TextUtils.isEmpty(etContent.getText().toString()) && defaultDataArray.size() > 0) {
                    PostMultiFilePresenter postMultiFilePresenter = new PostMultiFilePresenter(this);
                    postMultiFilePresenter.PostMultiFile(defaultDataArray);
                } else {
                    Tools.toastInBottom(mContext, "还没有输入任何内容");
                }
                break;
            case R.id.tv_select_circle:
                Intent goSelect = new Intent(mContext, SelectCircleActivity.class);
                startActivityForResult(goSelect, REQUEST_CIRCLE);
                break;
        }
    }

    @Override
    public void onPostResult(PostFileResultBean bean) {
        PublishCirclePresenter publishCirclePresenter = new PublishCirclePresenter(this);
        publishCirclePresenter.publishCircle((GsonUtils.getGsonInstance().toJson(bean.getData().getId())));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                defaultDataArray = data
                        .getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);

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
    }

    private void gotoSelectPic() {
        Intent intent = new Intent(mContext, MultiImageSelectorActivity.class);
        // 是否显示调用相机拍照
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 最大图片选择数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, getResources().getInteger(R.integer.most_pic_num));
        // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者
        // 多选/MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,
                MultiImageSelectorActivity.MODE_MULTI);
        // 默认选择图片,回填选项(支持String ArrayList)
        // ArrayList<String> temp = new ArrayList<String>();
        // for (int i = 0; i < defaultDataArray.size(); i++) {
        // temp.add(defaultDataArray.get(i));
        // }
        intent.putStringArrayListExtra(
                MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST,
                defaultDataArray);
        startActivityForResult(intent, REQUEST_IMAGE);
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
    }


    @Override
    public void onPublisResultCircle(BaseBean bean) {
        onBackPressed();
    }

    @Override
    public String getCircleId() {
        return mCircleID;
    }

    @Override
    public String getContent() {
        return etContent.getText().toString();
    }
}
