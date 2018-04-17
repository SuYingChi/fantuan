package com.wetime.fanc.circle.act;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.circle.adapter.LongTextAdapter;
import com.wetime.fanc.circle.bean.LocItemBean;
import com.wetime.fanc.circle.bean.LongTextBean;
import com.wetime.fanc.customview.OnRecyclerItemClickListener;
import com.wetime.fanc.customview.multiimageselector.MultiImageSelectorActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.KeyboardChangeListener;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static com.wetime.fanc.circle.act.PublishActActivity.REQUEST_LOC;
import static com.wetime.fanc.utils.Tools.REQUEST_IMAGE;

public class LongTextEditActivity extends BaseActivity implements LongTextAdapter.SaveEditListener,
        LongTextAdapter.SaveSelectionStartListener, LongTextAdapter.OnImgDeleteClickLitener, LongTextAdapter.SaveDesEditListener, KeyboardChangeListener.KeyBoardListener {


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
    @BindView(R.id.iv_keyboard)
    ImageView ivKeyboard;
    @BindView(R.id.tv_addres)
    TextView tvAddres;
    @BindView(R.id.iv_close)
    ImageView ivClose;

    private ArrayList<LongTextBean> list = new ArrayList<>();
    private LongTextAdapter adapter;
    // 记录光标位置  添加图片处理
    private int pos = 0;// 默认为1 既在光标在头部， 在末尾增加 图片
    private int index = 0;//光标 在第几个字后面
    private EditText lasteditText;
    private ItemTouchHelper mItemTouchHelper;
    private boolean isKeyboadShow;
    private LocItemBean locBean = new LocItemBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longtext2);
        ButterKnife.bind(this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rclContent.setLayoutManager(lm);
        LongTextBean titleLB = new LongTextBean();
        titleLB.setType("0");
        list.add(titleLB);

        LongTextBean firstLB = new LongTextBean();
        firstLB.setType("1");
        list.add(firstLB);
        isKeyboadShow = true;
        KeyboardChangeListener mKeyboardChangeListener = new KeyboardChangeListener(this);
        mKeyboardChangeListener.setKeyBoardListener(this);


        adapter = new LongTextAdapter(this, list);
        adapter.setSaveEditListener(this);
        adapter.setSaveSelectionStartListener(this);
        adapter.setOnImgDeleteClickLitener(this);
        adapter.setSaveDesEditListener(this);
        rclContent.setAdapter(adapter);

        rclContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //The RecyclerView is currently being dragged by outside input such as user touch input.
                if (newState == SCROLL_STATE_DRAGGING)
                    Tools.hideSoftInput(LongTextEditActivity.this);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        setDrag();
    }

    @Override
    protected void setSoftInPutMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
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
                        lb.setType("2");
                        list.add(lb);
                    }
                } else {
                    //光标在文本的最前面 在当前的 前面加数据
                    if (index == 0) {
                        for (String path : data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT)) {
                            LongTextBean lb = new LongTextBean();
                            lb.setImageUrl(path);
                            lb.setType("2");
                            list.add(pos, lb);
                        }
                    } else {//拆分当前
                        //pos 在末尾 直接在后面添加
                        if (index == list.get(pos).getContent().length()) {
                            for (String path : data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT)) {
                                LongTextBean lb = new LongTextBean();
                                lb.setImageUrl(path);
                                lb.setType("2");
                                list.add(pos + 1, lb);
                            }
                        } else {
                            String all = list.get(pos).getContent();
                            String start = all.substring(0, index);
                            String end = all.substring(index, all.length());
                            list.get(pos).setContent(start);

                            LongTextBean elb = new LongTextBean();
                            elb.setType("1");
                            elb.setContent(end);
                            list.add(pos + 1, elb);
                            for (String path : data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT)) {
                                LongTextBean lb = new LongTextBean();
                                lb.setImageUrl(path);
                                lb.setType("2");
                                list.add(pos + 1, lb);
                            }
                        }
                    }
                }
                initNormalData();
                adapter.notifyDataSetChanged();
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
                showKeyboard();
            }
        }
    }

    private void initSortData() {
        //第一个 为 title 不排
        for (int i = 1; i < list.size(); i++) {
            int type = Integer.valueOf(list.get(i).getType());
            //当文本为空的时候 不参与排序
            if (type == 1 && TextUtils.isEmpty(list.get(i).getContent())) {
                list.remove(i);
            }
        }

    }

    private void initNormalData() {
        //第一个 为 title 不排
        for (int i = 1; i < list.size(); i++) {
            int type = Integer.valueOf(list.get(i).getType());

            if (type == 1) {
                if (i + 1 < list.size()) {//下一个还有
                    LongTextBean nlb = list.get(i + 1);
                    //并且下一个也为edit 合并
                    if (Integer.valueOf(nlb.getType()) == 1) {
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
                    if (Integer.valueOf(nlb.getType()) == 2) {
                        LongTextBean elb = new LongTextBean();
                        elb.setType("1");
                        list.add(i + 1, elb);
                    }
                    //没有下一个 底部 许增加一个 输入框
                } else {
                    LongTextBean elb = new LongTextBean();
                    elb.setType("1");
                    list.add(elb);

                }
                //头部 第一个为图片的情况  再在上面加一个 edit 更具需求 增删
                if (i == 1) {
                    LongTextBean elb = new LongTextBean();
                    elb.setType("1");
                    list.add(1, elb);
                }
            }

        }

    }

    @OnClick({R.id.tv_addres,R.id.iv_close,R.id.iv_back, R.id.iv_gopic, R.id.tv_sort, R.id.tv_ok, R.id.tv_publish, R.id.iv_keyboard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                tvAddres.setText(getString(R.string.str_where_are_you));
                ivClose.setVisibility(View.GONE);
                Drawable drawable = getResources().getDrawable(R.drawable.ic_loc_off);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                tvAddres.setCompoundDrawables(drawable, null, null, null);
                tvAddres.setTextColor(ContextCompat.getColor(mContext, R.color.text_hint));
                break;
            case R.id.tv_addres:
                Intent goloc = new Intent(mContext, SelectLocActivity.class);
                locBean.setSelected(true);
                goloc.putExtra("loc", locBean);
                startActivityForResult(goloc, REQUEST_LOC);
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_gopic:
                if (lasteditText != null)
                    this.index = lasteditText.getSelectionStart();
                gotoSelectPic();
                break;
            case R.id.tv_ok:
                initNormalData();
                adapter.setSort(false);
                adapter.notifyDataSetChanged();
                tvSort.setVisibility(View.VISIBLE);
                tvPublish.setVisibility(View.VISIBLE);
                tvOk.setVisibility(View.GONE);
                break;
            case R.id.tv_sort:
                Tools.hideSoftInput(this);
                initSortData();
                adapter.setSort(true);
                adapter.notifyDataSetChanged();

                tvSort.setVisibility(View.GONE);
                tvPublish.setVisibility(View.GONE);
                tvOk.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_publish:
                for (LongTextBean b : list) {
                    Log.e("zk", "type=" + b.getType() + "--title=" + b.getTitle() + "--con=" + b.getContent() + "--des=" + b.getDes());
                }
                Log.d("zk", GsonUtils.getGsonInstance().toJson(list));
                break;
            case R.id.iv_keyboard:
                if (isKeyboadShow) {
                    Tools.hideSoftInput(this);
                } else {
//                    LinearLayoutManager lm= (LinearLayoutManager) rclContent.getLayoutManager();
//                    lm.scrollToPositionWithOffset(pos, 0);
//                  lm.scrollToPositionWithOffset(pos, 0);
                    rclContent.scrollToPosition(pos);
//                    rclContent.smoothScrollToPosition(pos);
                    Log.e("zk", "pos: " + pos);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showKeyboard();
                        }
                    }, 200);
                }
                break;
        }
    }

    private void showKeyboard() {
        EditText et;
        if (pos == 0) {
            et = rclContent.getLayoutManager().findViewByPosition(pos).findViewById(R.id.et_text);
        } else {
            et = rclContent.getLayoutManager().findViewByPosition(pos).findViewById(R.id.et_text);
        }
        if (et != null) {
            et.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(et, 0);
            }
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
        Log.e("zk", "position: " + position);
        Log.e("zk", "index: " + index);
    }

    @Override
    public void onImageDeleteClick(View view, int position) {
        Tools.hideSoftInput(this);
        list.remove(position);
        initNormalData();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void saveDesEdit(int position, String string) {
        list.get(position).setDes(string);
    }

    private void setDrag() {
        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            /**
             * 是否处理滑动事件 以及拖拽和滑动的方向 如果是列表类型的RecyclerView的只存在UP和DOWN，如果是网格类RecyclerView则还应该多有LEFT和RIGHT
             * @param recyclerView
             * @param viewHolder
             * @return
             */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = 0;
//                    final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (toPosition == 0) {// 第一个不参与排序
                    return true;
                }
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(list, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(list, i, i - 1);
                    }
                }
                adapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
//                myAdapter.notifyItemRemoved(position);
//                datas.remove(position);
            }

            /**
             * 重写拖拽可用
             * @return
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            /**
             * 长按选中Item的时候开始调用
             *
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setAlpha(0.8f);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 手指松开的时候还原
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setAlpha(1f);
            }
        });

        mItemTouchHelper.attachToRecyclerView(rclContent);
        rclContent.addOnItemTouchListener(new OnRecyclerItemClickListener(rclContent) {
            @Override
            public void onItemTouchClick(RecyclerView.ViewHolder vh) {
                //判断被拖拽的是否是第一个，如果不是则执行拖拽 ，并且是 图片才可以拖拽
                if (adapter.isSort()
                        && vh.getLayoutPosition() != 0
                        && adapter.getItemViewType(vh.getLayoutPosition()) == 2) {
                    mItemTouchHelper.startDrag(vh);
                }

            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
//                Toast.makeText(mContext, currentfoodlist.get(vh.getLayoutPosition()).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {

            }
        });
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        isKeyboadShow = isShow;
        if (isShow) {
            ivKeyboard.setImageResource(R.drawable.ic_hide_keyborad);
        } else {
            ivKeyboard.setImageResource(R.drawable.ic_show_keyboard);
        }
    }
}
