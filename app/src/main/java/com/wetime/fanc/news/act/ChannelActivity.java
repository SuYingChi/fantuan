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
import com.wetime.fanc.news.presenter.SaveMyChannelPresenter;
import com.wetime.fanc.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 频道 增删改查 排序
 * Created by zhoukang on 18/03/29.
 */
public class ChannelActivity extends BaseActivity {
    public final static String LOCALCHANNAL = "localchannal";
    public final static String ALLCHANNAL = "allchannal";


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
        //判断有没有变化
        boolean isChange = false;
        List<ChannelBean> olditems = GsonUtils.getGsonInstance().fromJson(spu.getValue(LOCALCHANNAL),
                new TypeToken<List<ChannelBean>>() {
                }.getType());

        if (items.size() != olditems.size()) {
            isChange = true;
        } else {
            for (int i = 0; i < items.size(); i++) {
                if (!TextUtils.equals(olditems.get(i).getId(), items.get(i).getId())) {
                    isChange = true;
                }
            }
        }

        if (isChange) {
            spu.setValue(LOCALCHANNAL, GsonUtils.getGsonInstance().toJson(items));
            SaveMyChannelPresenter saveMyChannelPresenter = new SaveMyChannelPresenter();
            saveMyChannelPresenter.saveMyChannel(spu.getToken(), GsonUtils.getGsonInstance().toJson(items));
            EventBus.getDefault().post(new ChannelChangeEvent());
        }

        super.onBackPressed();
    }

    private void init() {

        items = GsonUtils.getGsonInstance().fromJson(spu.getValue(LOCALCHANNAL),
                new TypeToken<List<ChannelBean>>() {
                }.getType());

        List<ChannelBean> allItems = GsonUtils.getGsonInstance().fromJson(spu.getValue(ALLCHANNAL),
                new TypeToken<List<ChannelBean>>() {
                }.getType());
        otherItems = new ArrayList<>();
        for (ChannelBean channelBean : allItems) {
            boolean has = false;
            for (ChannelBean c : items) {
                if (TextUtils.equals(channelBean.getId(), c.getId())) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                otherItems.add(channelBean);
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
