package com.wetime.fanc.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wetime.fanc.R;
import com.wetime.fanc.utils.SharePreferenceUtil;
import com.wetime.fanc.utils.Tools;

public class ListActivity extends AppCompatActivity {
    AllTokenAdapter adapter;
    SharePreferenceUtil spu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView recyclerView = findViewById(R.id.lv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AllTokenAdapter(this);
        recyclerView.setAdapter(adapter);
        spu = Tools.getSpu(this);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickLitener((view, position) -> {
//                spu.setToken("asdasasda");
            spu.setToken(getResources().getStringArray(R.array.token)[position]);
            Log.d("zkzk", spu.getToken());
            Tools.toastInBottom(ListActivity.this, "当前用户：" +
                    getResources().getStringArray(R.array.name)[position]);
            finish();
        });
    }
}
