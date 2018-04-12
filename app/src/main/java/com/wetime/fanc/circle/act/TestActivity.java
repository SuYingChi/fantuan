package com.wetime.fanc.circle.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.adapter.LongTextAdapter;
import com.wetime.fanc.circle.bean.LongTextBean;
import com.wetime.fanc.customview.multiimageselector.MultiImageSelectorActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wetime.fanc.utils.Tools.REQUEST_IMAGE;

public class TestActivity extends BaseActivity implements LongTextAdapter.SaveEditListener,
        LongTextAdapter.SaveSelectionStartListener, LongTextAdapter.OnImgDeleteClickLitener {



    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rcl_content)
    RecyclerView rclContent;
    @BindView(R.id.tv_sort)
    TextView tvSort;
    @BindView(R.id.tv_publish)
    TextView tvPublish;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    private ArrayList<LongTextBean> list = new ArrayList<>();
    private LongTextAdapter adapter;
    // 记录光标位置  添加图片处理
    private int pos = 0;// 默认为1 既在光标在头部， 在末尾增加 图片
    private int index = 0;//光标 在第几个字后面
    private EditText lasteditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longtext2);
        ButterKnife.bind(this);
        rclContent.setLayoutManager(new LinearLayoutManager(this));
        LongTextBean titleLB = new LongTextBean();
        titleLB.setType(0);
        list.add(titleLB);

        LongTextBean firstLB = new LongTextBean();
        firstLB.setType(1);
        list.add(firstLB);

        adapter = new LongTextAdapter(this, list);
        adapter.setSaveEditListener(this);
        adapter.setSaveSelectionStartListener(this);
        adapter.setOnImgDeleteClickLitener(this);
        rclContent.setAdapter(adapter);


        rclContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Tools.hideSoftInput(TestActivity.this);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("zk", "position: " + pos);
        Log.e("zk", "index: " + index);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                //光标在头部 在list尾部加数据
                if (pos == 0) {
                    for (String path : data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT)) {
                        LongTextBean lb = new LongTextBean();
                        lb.setImageUrl(path);
                        lb.setType(2);
                        list.add(lb);
                    }
                } else {
                    //光标在文本的最前面 在当前的 前面加数据
                    if (index == 0) {
                        for (String path : data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT)) {
                            LongTextBean lb = new LongTextBean();
                            lb.setImageUrl(path);
                            lb.setType(2);
                            list.add(pos, lb);
                        }
                    } else {//拆分当前
                        //pos 在末尾 直接在后面添加
                        if (index == list.get(pos).getContent().length()) {
                            for (String path : data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT)) {
                                LongTextBean lb = new LongTextBean();
                                lb.setImageUrl(path);
                                lb.setType(2);
                                list.add(pos + 1, lb);
                            }
                        } else {
                            String all = list.get(pos).getContent();
                            String start = all.substring(0, index);
                            String end = all.substring(index, all.length());
                            list.get(pos).setContent(start);

                            LongTextBean elb = new LongTextBean();
                            elb.setType(1);
                            elb.setContent(end);
                            list.add(pos + 1, elb);
                            for (String path : data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT)) {
                                LongTextBean lb = new LongTextBean();
                                lb.setImageUrl(path);
                                lb.setType(2);
                                list.add(pos + 1, lb);
                            }
                        }
                    }
                }

                sortNormalData();
            }
        }
    }

    private void sortNormalData() {
        //第一个 为 title 不排
        for (int i = 1; i < list.size(); i++) {
            int type = list.get(i).getType();

            if (type == 1) {
                if (i + 1 < list.size()) {//下一个还有
                    LongTextBean nlb = list.get(i + 1);
                    //并且下一个也为edit 合并
                    if (nlb.getType() == 1) {
                        // 下一个不为空的时候加 回车  合并，否则 直接rmove掉
                        if (!TextUtils.isEmpty(nlb.getContent())) {
                            list.get(i).setContent(list.get(i).getContent() + "\n" + nlb.getContent());
                        }
                        list.remove(i + 1);
                    }
                }
            } else if (type == 2) {
                if (i + 1 < list.size()) {//下一个还有
                    LongTextBean nlb = list.get(i + 1);
                    //并且下一个也为 图片 需要插入一个 edit
                    if (nlb.getType() == 2) {
                        LongTextBean elb = new LongTextBean();
                        elb.setType(1);
                        list.add(i + 1, elb);
                    }
                    //没有下一个 底部 许增加一个 输入框
                } else {
                    LongTextBean elb = new LongTextBean();
                    elb.setType(1);
                    list.add(elb);

                }
                //头部 第一个为图片的情况  再在上面加一个 edit 更具需求 增删
                if (i == 1) {
                    LongTextBean elb = new LongTextBean();
                    elb.setType(1);
                    list.add(1, elb);
                }
            }

        }
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.iv_back, R.id.iv_gopic, R.id.tv_sort, R.id.tv_ok, R.id.tv_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_gopic:
                if (lasteditText != null)
                    this.index = lasteditText.getSelectionStart();
                gotoSelectPic();
                break;
            case R.id.tv_ok:
                tvSort.setVisibility(View.VISIBLE);
                tvPublish.setVisibility(View.VISIBLE);
                tvOk.setVisibility(View.GONE);
                break;
            case R.id.tv_sort:
                tvSort.setVisibility(View.GONE);
                tvPublish.setVisibility(View.GONE);
                tvOk.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_publish:
                for (LongTextBean b : list) {
                    Log.e("zk", "type=" + b.getType() + "--title=" + b.getTitle() + "--contetn=" + b.getContent());
                }
                break;


        }
    }

    private void gotoSelectPic() {
        Intent intent = new Intent(this, MultiImageSelectorActivity.class);
        // 是否显示调用相机拍照
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 最大图片选择数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 30);
        // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者
        // 多选/MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,
                MultiImageSelectorActivity.MODE_MULTI);
        // 默认选择图片,回填选项(支持String ArrayList)
        // ArrayList<String> temp = new ArrayList<String>();
        // for (int i = 0; i < defaultDataArray.size(); i++) {
        // temp.add(defaultDataArray.get(i));
        // }
//        intent.putStringArrayListExtra(
//                MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST,
//                defaultDataArray);
        startActivityForResult(intent, REQUEST_IMAGE);
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
        super.onBackPressed();
    }

    @Override
    public void SaveEdit(int position, String string) {
        list.get(position).setContent(string);
    }

    @Override
    public void saveSelectionStart(int position, EditText editText) {
        this.pos = position;
        this.lasteditText = editText;
//        this.index = editText.ge;
//        Log.e("zk", "position: " + position);
//        Log.e("zk", "index: " + index);
    }

    @Override
    public void onImageDeleteClick(View view, int position) {
        Tools.hideSoftInput(this);
        list.remove(position);
        sortNormalData();
    }
}
