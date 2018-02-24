package com.wetime.fanc.news.act;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.widget.Toast;

import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.news.adapter.ChannelAdapter;
import com.wetime.fanc.news.bean.ChannelBean;
import com.wetime.fanc.news.helper.ItemDragHelperCallback;

import java.util.ArrayList;
import java.util.List;


/**
 * 频道 增删改查 排序
 * Created by YoKeyword on 15/12/29.
 */
public class ChannelActivity extends BaseActivity {

    private RecyclerView mRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channal_edit);

        mRecy = findViewById(R.id.recy);
        init();
    }

    private void init() {

        String[] mTitles = getResources().getStringArray(R.array.newstype_default);
        String[] mIndex = getResources().getStringArray(R.array.newstypeindex_default);

        String[] mTitlesAll = getResources().getStringArray(R.array.newstype);
        String[] mIndexAll = getResources().getStringArray(R.array.newstypeindex);

        final List<ChannelBean> items = new ArrayList<>();
        // 推荐和海口 固定 不进来  前两个
        final int fixed = 2;
        for (int i = fixed; i < mTitles.length; i++) {
            ChannelBean entity = new ChannelBean();
            entity.setName(mTitles[i]);
            entity.setId(mIndex[i]);
            items.add(entity);
        }

        final List<ChannelBean> otherItems = new ArrayList<>();
        for (int i = fixed; i < mTitlesAll.length; i++) {
            boolean has = false;
            for (int ii = fixed; ii < mTitles.length; ii++) {
                if (TextUtils.equals(mTitlesAll[i], mTitles[ii])){
                    has =true;
                    break;
                }
            }
            if(!has){
                ChannelBean entity = new ChannelBean();
                entity.setName(mTitlesAll[i]);
                entity.setId(mTitlesAll[i]);
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


        adapter.setOnMyChannelItemClickListener((v, position) -> Toast.makeText(ChannelActivity.this, items.get(position).getName(), Toast.LENGTH_SHORT).show());
    }
}
