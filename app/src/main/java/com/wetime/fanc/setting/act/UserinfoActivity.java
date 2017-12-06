package com.wetime.fanc.setting.act;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.king.batterytest.fbaselib.customview.multiimageselector.MultiImageSelectorActivity;
import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.king.batterytest.fbaselib.utils.Tools;
import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.MyInfoBean;
import com.wetime.fanc.home.iviews.IGetMyInfoView;
import com.wetime.fanc.home.presenter.GetUserInfoPresenter;
import com.wetime.fanc.main.bean.PostFileResultBean;
import com.wetime.fanc.main.ivews.IPostMultiFileView;
import com.wetime.fanc.main.presenter.PostMultiFilePresenter;
import com.wetime.fanc.setting.event.ChangeUserInfoEvent;
import com.wetime.fanc.setting.iviews.ISetHeadImageView;
import com.wetime.fanc.setting.iviews.ISetUsernameView;
import com.wetime.fanc.setting.presenter.SetHeadImagePresenter;
import com.wetime.fanc.setting.presenter.SetUserNamePresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserinfoActivity extends BaseActivity implements IGetMyInfoView, IPostMultiFileView, ISetHeadImageView, ISetUsernameView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.tv_name)
    TextView tvName;

    private GetUserInfoPresenter getUserInfoPresenter;
    private PostMultiFilePresenter postMultiFilePresenter;
    private SetUserNamePresenter setUserNamePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ButterKnife.bind(this);
        tvTitle.setText("个人信息");
        getUserInfoPresenter = new GetUserInfoPresenter(this);
        getUserInfoPresenter.getUserInfo();
        postMultiFilePresenter = new PostMultiFilePresenter(this);
        setUserNamePresenter = new SetUserNamePresenter(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @OnClick({R.id.iv_back, R.id.civ_head, R.id.tv_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.civ_head:
                Tools.gotoSelectPic(this);
                break;
            case R.id.tv_name:
                showGuqingDialog();
                break;
        }
    }

    @Override
    public void onGetUserInfo(MyInfoBean bean) {
        tvName.setText(bean.getData().getUser().getUsername());
        Glide.with(this).load(bean.getData().getUser().getAvatar()).into(civHead);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == Tools.REQUEST_IMAGE && data != null) {
            final List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (path != null && path.size() > 0) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        postMultiFilePresenter.PostMultiFile(path);
                    }
                });

            }
        }
    }

    @Override
    public void onPostResult(PostFileResultBean bean) {
        if (bean.getError() != 0)
            return;

        SetHeadImagePresenter setHeadImagePresenter = new SetHeadImagePresenter(this);
        setHeadImagePresenter.setHeadImage(bean.getData().getId().get(0));
        Glide.with(this).load(bean.getData().getUrl().get(0)).into(civHead);
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


        Button btnCancel = layout.findViewById(R.id.dialog_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                tel_dialog.dismiss();

            }
        });


        Button btnOK = layout.findViewById(R.id.dialog_btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString().length() < 4) {
                    Tools.toastInBottom(mContext, "长度限制: 4-20个字符");
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
