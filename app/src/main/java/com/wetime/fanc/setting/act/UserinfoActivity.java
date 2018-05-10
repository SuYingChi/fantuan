package com.wetime.fanc.setting.act;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
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
import com.wetime.fanc.setting.iviews.ISetUserInfoView;
import com.wetime.fanc.setting.presenter.SetUserInfoPresenter;
import com.wetime.fanc.utils.Tools;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import de.hdodenhof.circleimageview.CircleImageView;
import me.shaohui.bottomdialog.BottomDialog;

public class UserinfoActivity extends BaseActivity implements IGetMyInfoView,
        IPostMultiFileView, ISetUserInfoView {


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
    private SetUserInfoPresenter setUserInfoPresenter;

    private PostMultiFilePresenter postMultiFilePresenter;
//    private SetUserNamePresenter setUserNamePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.str_userinfo));

        getUserInfoPresenter = new GetUserInfoPresenter(this);
        getUserInfoPresenter.getUserInfo();
        postMultiFilePresenter = new PostMultiFilePresenter(this);
//        setUserNamePresenter = new SetUserNamePresenter(this);

        setUserInfoPresenter = new SetUserInfoPresenter(this);
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
        if (!TextUtils.isEmpty(bean.getData().getAvatar()))
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
        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onYearMonthDayPicker(bean.getData().getBirthday());
            }
        });
    }

    public void onYearMonthDayPicker(String birthday) {
        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(20);
        Calendar now = Calendar.getInstance();
        System.out.println("年: " + now.get(Calendar.YEAR));
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));

        picker.setRangeEnd(now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1, now.get(Calendar.DAY_OF_MONTH));
        picker.setRangeStart(1900, 1, 1);
        if (TextUtils.equals(birthday, "请选择")) {
            picker.setSelectedItem(1990, 1, 1);
        } else {
            String array[] = birthday.split("-");
            picker.setSelectedItem(Integer.valueOf(array[0]), Integer.valueOf(array[1]), Integer.valueOf(array[2]));
        }

        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener((DatePicker.OnYearMonthDayPickListener) (year, month, day) -> {
            String resutl = year + "-" + month + "-" + day;
            setUserInfoPresenter.setUserInfo("birthday", resutl);
//            Tools.toastInBottom(this, resutl);
        });

        picker.show();
    }

    private void showBottomDialog() {
        BottomDialog mDeleteBottomDialog = BottomDialog.create(getSupportFragmentManager());
        mDeleteBottomDialog.setDimAmount(0.5f);
        mDeleteBottomDialog.setCancelOutside(true);
        mDeleteBottomDialog.setLayoutRes(R.layout.bottom_selectpic_dialog_layout);
        mDeleteBottomDialog.setViewListener(v11 -> {
            v11.findViewById(R.id.tv_takepic).setOnClickListener(v -> {
                mDeleteBottomDialog.dismiss();
                PictureSelector.create(this)
                        .openCamera(PictureMimeType.ofImage())
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            });
            v11.findViewById(R.id.tv_select).setOnClickListener(v -> {
                mDeleteBottomDialog.dismiss();
                gotoSelectPic();
            });
            v11.findViewById(R.id.tv_cancel).setOnClickListener(v -> mDeleteBottomDialog.dismiss());

        });
        mDeleteBottomDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST && data != null) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            ArrayList<String> path = new ArrayList<>();
            if (selectList.get(0).isCut()) {
                path.add(selectList.get(0).getCompressPath());
            } else {
                path.add(selectList.get(0).getPath());
            }

            if (path.size() > 0) {
                new Handler().post(() -> {
                    Uri uri_crop = Uri.fromFile(new File(path.get(0)));
                    //裁剪后保存到文件中
                    String pt = Environment.getExternalStorageDirectory().getPath() + "/fantuan/iamge/";
                    File des = new File(pt);
                    if (!des.exists()) {
                        des.mkdirs();
                    }
                    Uri destinationUri = Uri.fromFile(new File(pt + System.currentTimeMillis() + ".jpg"));
                    UCrop.Options options = new UCrop.Options();
                    options.setHideBottomControls(true);
                    //设置toolbar颜色
                    options.setToolbarColor(ContextCompat.getColor(mContext, R.color.black));
                    //设置状态栏颜色
                    options.setStatusBarColor(ContextCompat.getColor(mContext, R.color.black));
                    UCrop.of(uri_crop, destinationUri)
                            .withOptions(options)
                            .withAspectRatio(1, 1)
//                            .withMaxResultSize(maxWidth, maxHeight)
                            .start(this);

                });

            }
        }
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            if (postMultiFilePresenter == null)
                postMultiFilePresenter = new PostMultiFilePresenter(this);
            final List<String> pathlist = new ArrayList<>();
            pathlist.add(resultUri.getPath());
            postMultiFilePresenter.PostMultiFile(pathlist);

        }


    }

    @Override
    public void onPostResult(PostFileResultBean bean) {
        if (bean.getError() != 0)
            return;
        Glide.with(mContext).load(bean.getData().getUrl().get(0)).into(civHead);


        setUserInfoPresenter.setUserInfo("avatar", bean.getData().getId().get(0));
    }

    @Override
    public void onSetUserInfoResult(BaseBean bean) {
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
        btnOK.setOnClickListener(v -> {
            if (et.getText().toString().length() == 0) {
                Tools.toastInBottom(mContext, "长度限制: 1—10个字");
                return;
            }
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);
            imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
            tel_dialog.dismiss();
            tvName.setText(et.getText().toString());
            setUserInfoPresenter.setUserInfo("username", et.getText().toString());
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ChangeUserInfoEvent messageEvent) {
        getUserInfoPresenter.getUserInfo();
    }

}
