package com.hitomi.sortricheditor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.hitomi.sortricheditor.view.editor.SEditorData;
import com.hitomi.sortricheditor.view.editor.SortRichEditor;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.multiimageselector.MultiImageSelectorActivity;
import com.wetime.fanc.main.act.BaseActivity;

import java.util.List;

import static com.wetime.fanc.utils.Tools.REQUEST_IMAGE;

public class EditLongTextActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvSort;
    private SortRichEditor editor;
    private ImageView ivGallery;
    private Button btnPosts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_longtext);

        tvSort = findViewById(R.id.tv_sort);
        editor = findViewById(R.id.richEditor);
        ivGallery = findViewById(R.id.iv_gallery);
        btnPosts = findViewById(R.id.btn_posts);

        tvSort.setOnClickListener(this);
        ivGallery.setOnClickListener(this);
        btnPosts.setOnClickListener(this);
    }

    /**
     * 负责处理编辑数据提交等事宜，请自行实现
     */
    private void dealEditData(List<SEditorData> editList) {
        for (SEditorData itemData : editList) {
            if (itemData.getInputStr() != null) {
                Log.d("RichEditor", "commit inputStr=" + itemData.getInputStr());
            } else if (itemData.getImagePath() != null) {
                Log.d("RichEditor", "commit imgePath=" + itemData.getImagePath());
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                if (editor.isSort()) tvSort.setText("排序");
                editor.addImageArray(data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sort:
                if (editor.sort()) {
                    tvSort.setText("完成");
                } else {
                    tvSort.setText("排序");
                }
                break;
            case R.id.iv_gallery:
                gotoSelectPic();
//                startActivityForResult(new Intent(this, PhotoPickerActivity.class), REQUEST_CODE_PICK_IMAGE);
                break;
            case R.id.btn_posts:
                List<SEditorData> editList = editor.buildEditData();
                // 下面的代码可以上传、或者保存，请自行实现
                dealEditData(editList);
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
}
