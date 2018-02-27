package com.wetime.fanc.news.act;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.news.adapter.ChannelAdapter;
import com.wetime.fanc.news.bean.ChannelBean;
import com.wetime.fanc.news.event.ChannelChangeEvent;
import com.wetime.fanc.news.helper.ItemDragHelperCallback;
import com.wetime.fanc.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 频道 增删改查 排序
 * Created by YoKeyword on 15/12/29.
 */
public class ChannelActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.recy)
    RecyclerView mRecy;
    private List<ChannelBean> items;
    private List<ChannelBean> otherItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channal_edit);
        ButterKnife.bind(this);

        init();
    }

    @Override
    public void onBackPressed() {
        for (ChannelBean channelBean : items) {
            Log.e("zkmy", channelBean.getName());
        }
        for (ChannelBean channelBean : otherItems) {
            Log.e("zkother", channelBean.getName());
        }

        spu.setValue("mychannal", GsonUtils.getGsonInstance().toJson(items));

        EventBus.getDefault().post(new ChannelChangeEvent());
        super.onBackPressed();
    }

    private void init() {
        String[] mTitles;
        String[] mIndex;

        // 推荐和海口 固定 不进来  前两个
        final int fixed = 2;
        if (TextUtils.isEmpty(spu.getValue("mychannal"))) {
            mTitles = getResources().getStringArray(R.array.newstype_default);
            mIndex = getResources().getStringArray(R.array.newstypeindex_default);
            items = new ArrayList<>();
            for (int i = fixed; i < mTitles.length; i++) {
                ChannelBean entity = new ChannelBean();
                entity.setName(mTitles[i]);
                entity.setId(mIndex[i]);
                items.add(entity);
            }

        } else {
            items = GsonUtils.getGsonInstance().fromJson(spu.getValue("mychannal"),
                    new TypeToken<List<ChannelBean>>() {
                    }.getType());
        }


        String[] mTitlesAll = getResources().getStringArray(R.array.newstype);
        String[] mIndexAll = getResources().getStringArray(R.array.newstypeindex);


        otherItems = new ArrayList<>();
        for (int i = fixed; i < mTitlesAll.length; i++) {
            boolean has = false;
            for (int ii = 0; ii < items.size(); ii++) {
                if (TextUtils.equals(mTitlesAll[i], items.get(ii).getName())) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                ChannelBean entity = new ChannelBean();
                entity.setName(mTitlesAll[i]);
                entity.setId(mIndexAll[i]);
                otherItems.add(entity);
            }
        }

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecy.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecy);

        final ChannelAdapter adapter = new ChannelAdapter(this, helper, items, otherItems);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRecy.setAdapter(adapter);
        adapter.startEditMode(mRecy);

        adapter.setOnMyChannelItemClickListener((v, position) -> Toast.makeText(ChannelActivity.this, items.get(position).getName(), Toast.LENGTH_SHORT).show());
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;

        }
    }
}
