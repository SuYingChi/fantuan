package com.wetime.fanc.order.act;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.batterytest.fbaselib.customview.GridViewForScrollView;
import com.king.batterytest.fbaselib.customview.multiimageselector.MultiImageSelectorActivity;
import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.king.batterytest.fbaselib.utils.Tools;
import com.wetime.fanc.R;
import com.wetime.fanc.main.bean.PostFileResultBean;
import com.wetime.fanc.main.ivews.IPostMultiFileView;
import com.wetime.fanc.main.presenter.PostMultiFilePresenter;
import com.wetime.fanc.order.MyRatingBar;
import com.wetime.fanc.order.adapter.ImageGridAdapter;
import com.wetime.fanc.order.event.RefreshOrderEvent;
import com.wetime.fanc.order.iviews.ICommentOrderView;
import com.wetime.fanc.order.presenter.CommentOrderPresenter;
import com.wetime.fanc.web.event.FinishWebEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.king.batterytest.fbaselib.utils.Tools.REQUEST_IMAGE;

public class CommentOrderActivity extends BaseActivity implements AdapterView.OnItemClickListener, IPostMultiFileView, ICommentOrderView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rb_socre)
    MyRatingBar rbSocre;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.appraise_img_gv)
    GridViewForScrollView gv;
    @BindView(R.id.cb_name)
    CheckBox cbName;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.tv_tips)
    TextView tvTips;

    private ArrayList<String> defaultDataArray = new ArrayList<>();
    private ImageGridAdapter mPicGridAdapter;
    private PostMultiFilePresenter postMultiFilePresenter;
    private String rating = "5";//默认五颗星

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentorder);
        ButterKnife.bind(this);
        tvTitle.setText("发表评价");
        initView();
        postMultiFilePresenter = new PostMultiFilePresenter(this);

    }

    private void initView() {
        rbSocre.setOnRatingChangeListener(new MyRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                rating = ratingCount + "";
                tvScore.setText(rating);
            }
        });
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvNum.setText(etContent.getText().toString().length() + "/300");
                if (etContent.getText().toString().length() > 0) {
                    tvComment.setBackgroundResource(R.drawable.bg_btn_red_corner);
                } else {
                    tvComment.setBackgroundResource(R.drawable.bg_btn_red_corner_enable);
                }
            }
        });
        mPicGridAdapter = new ImageGridAdapter(mContext, defaultDataArray);


        gv.setAdapter(mPicGridAdapter);
        gv.setOnItemClickListener(this);
        cbName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tvTips.setVisibility(View.VISIBLE);
                }else{
                    tvTips.setVisibility(View.GONE);
                }
            }
        });
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
                mPicGridAdapter.setOnDeleteClickLitener(new ImageGridAdapter.OnDeleteClickLitener() {
                    @Override
                    public void onDeleteClick(View view, int position) {
                        defaultDataArray.remove(position);
                        mPicGridAdapter.notifyDataSetChanged();
                    }
                });
                gv.setAdapter(mPicGridAdapter);
                mPicGridAdapter.notifyDataSetChanged();

            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.tv_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_comment:
                if (etContent.getText().toString().length() >= 8) {
                    postMultiFilePresenter.PostMultiFile(defaultDataArray);
                } else {
                    Tools.toastInBottom(this, "评论内容至少八个字哦");
                }
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        // TODO Auto-generated method stub
        if (position >= defaultDataArray.size()) {
            gotoSelectPic();
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
    public void onPostResult(PostFileResultBean bean) {
        CommentOrderPresenter commentOrderPresenter = new CommentOrderPresenter(this);
        commentOrderPresenter.getCommentResult(GsonUtils.getGsonInstance().toJson(bean.getData().getId()));
    }

    @Override
    public void onCommentResult(BaseBean bean) {
        EventBus.getDefault().post(new RefreshOrderEvent());
        EventBus.getDefault().post(new FinishWebEvent());
        finish();
    }

    @Override
    public String getOrderId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public String getScore() {
        return rating;
    }

    @Override
    public String getAnonymous() {
        return cbName.isChecked() ? "1" : "0";
    }

    @Override
    public String getContent() {
        return etContent.getText().toString();
    }
}
