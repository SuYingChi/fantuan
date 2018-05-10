package com.wetime.fanc.setting.act;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.bean.PostFileResultBean;
import com.wetime.fanc.main.ivews.IPostMultiFileView;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.main.presenter.PostMultiFilePresenter;
import com.wetime.fanc.my.bean.MyInfoBean;
import com.wetime.fanc.my.iviews.IGetMyInfoView;
import com.wetime.fanc.my.presenter.GetUserInfoPresenter;
import com.wetime.fanc.setting.event.ChangeUserInfoEvent;
import com.wetime.fanc.setting.iviews.ISetHeadImageView;
import com.wetime.fanc.setting.iviews.ISetUsernameView;
import com.wetime.fanc.setting.presenter.SetHeadImagePresenter;
import com.wetime.fanc.setting.presenter.SetUserNamePresenter;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.shaohui.bottomdialog.BottomDialog;

public class UserinfoActivity extends BaseActivity implements IGetMyInfoView,
        IPostMultiFileView, ISetHeadImageView, ISetUsernameView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_noteq)
    TextView tvNoteq;
    @BindView(R.id.tv_note)
    TextView tvNote;

    private GetUserInfoPresenter getUserInfoPresenter;
    private PostMultiFilePresenter postMultiFilePresenter;
    private SetUserNamePresenter setUserNamePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.str_userinfo));

        getUserInfoPresenter = new GetUserInfoPresenter(this);
        getUserInfoPresenter.getUserInfo();
        postMultiFilePresenter = new PostMultiFilePresenter(this);
        setUserNamePresenter = new SetUserNamePresenter(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @OnClick({R.id.iv_back, R.id.tv_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;

            case R.id.tv_name:
                showGuqingDialog();
                break;
        }
    }

    private void gotoSelectPic() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .theme(R.style.picture_my_style)
                .previewImage(true)
                .isCamera(false)
                .compress(true)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onGetUserInfo(MyInfoBean bean) {
        tvName.setText(bean.getData().getUsername());
        Glide.with(this).load(bean.getData().getAvatar()).into(civHead);
        tvBirthday.setText(bean.getData().getBirthday());
        tvSex.setText(bean.getData().getSex());
        tvNote.setText(bean.getData().getSignature());
        civHead.setOnClickListener(v -> {
            if (TextUtils.isEmpty(bean.getData().getAvatar())) {
                showBottomDialog();
            } else {
                //查看大图
                UserBigHeadImageActivity.goUserBigHeadImageAct(
                        this
                        , bean.getData().getAvatar_url()
                        , bean.getData().getAvatar());
            }

        });
    }

    private void showBottomDialog() {
        BottomDialog mDeleteBottomDialog = BottomDialog.create(getSupportFragmentManager());
        mDeleteBottomDialog.setDimAmount(0.5f);
        mDeleteBottomDialog.setCancelOutside(true);
        mDeleteBottomDialog.setLayoutRes(R.layout.bottom_selectpic_dialog_layout);
        mDeleteBottomDialog.setViewListener(v11 -> {
            v11.findViewById(R.id.tv_takepic).setOnClickListener(v -> {
                mDeleteBottomDialog.dismiss();
            });
            v11.findViewById(R.id.tv_select).setOnClickListener(v -> {
                gotoSelectPic();
            });
            v11.findViewById(R.id.tv_cancel).setOnClickListener(v -> mDeleteBottomDialog.dismiss());

        });

        mDeleteBottomDialog.show();

    }
//    private long getFileSize(File file) throws Exception {
//        if (file == null) {
//            return 0;
//        }
//        long size = 0;
//        if (file.exists()) {
//            FileInputStream fis = null;
//            fis = new FileInputStream(file);
//            size = fis.available();
//        }
//        return size / 1024;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PictureConfig.CHOOSE_REQUEST) {
            if (resultCode == RESULT_OK) {
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                if (selectList != null && selectList.size() > 0) {

                    ArrayList<String> pathlist = new ArrayList<>();
                    if (selectList.get(0).isCompressed()) {
                        pathlist.add(selectList.get(0).getCompressPath());
                    } else {
                        pathlist.add(selectList.get(0).getPath());
                    }
                    // 测试信息
//                        if (BuildConfig.DEBUG) {
//                            try {
//                                File of = new File(selectList.get(0).getPath());
//                                Log.e("zk", "压缩前文件大小kb" + getFileSize(of));
//
//                                File nf = new File(selectList.get(0).getCompressPath());
//                                Log.e("zk", "压缩后文件大小kb" + getFileSize(nf));
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
                    postMultiFilePresenter.PostMultiFile(pathlist);
                    Glide.with(mContext).load(pathlist.get(0)).into(civHead);


                }
            }
        }
    }

    @Override
    public void onPostResult(PostFileResultBean bean) {
        if (bean.getError() != 0)
            return;

        SetHeadImagePresenter setHeadImagePresenter = new SetHeadImagePresenter(this);
        setHeadImagePresenter.setHeadImage(bean.getData().getId().get(0));
    }

    @Override
    public void onSetHeadImageResult(BaseBean bean) {
        if (bean.getError() == 0)
            EventBus.getDefault().post(new ChangeUserInfoEvent());
    }

    private void showGuqingDialog() {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        LinearLayout layout = (LinearLayout) inflaterDl.inflate(
                R.layout.dialog_setusername, null);
        final Dialog tel_dialog = new AlertDialog.Builder(mContext).create();

        tel_dialog.show();
        tel_dialog.getWindow().setContentView(layout);
        final EditText et = layout.findViewById(R.id.et_food_num);
        tel_dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        tel_dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        TextView btnCancel = layout.findViewById(R.id.dialog_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                tel_dialog.dismiss();

            }
        });


        TextView btnOK = layout.findViewById(R.id.dialog_btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString().length() == 0) {
                    Tools.toastInBottom(mContext, "长度限制: 1—10个字");
                    return;
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                tel_dialog.dismiss();
                tvName.setText(et.getText().toString());
                setUserNamePresenter.setUserName(et.getText().toString());
            }
        });
    }

    @Override
    public void onSetUsername(BaseBean bean) {
        if (bean.getError() == 0)
            EventBus.getDefault().post(new ChangeUserInfoEvent());
    }
}
