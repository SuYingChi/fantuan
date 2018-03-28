package com.wetime.fanc.news.act;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/28.
 */

public class CommentActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.comment_listview)
    ListView commentListview;
    @BindView(R.id.comment_refreshLayout)
    SmartRefreshLayout commentRefreshLayout;
    @BindView(R.id.gallery_linear)
    LinearLayout galleryLinear;
    private int diffTest = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvTitle.setText("评论");
        View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(getGlobalLayoutListener(decorView, galleryLinear));
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }

    private ViewTreeObserver.OnGlobalLayoutListener getGlobalLayoutListener(final View decorView, final LinearLayout contentView) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                decorView.getWindowVisibleDisplayFrame(r);

                int height = decorView.getContext().getResources().getDisplayMetrics().heightPixels;
                int diff = height - r.bottom;

                Log.e("xi", "Frg onGlobalLayout:diff " + diff);
                Log.e("xi", "Frg onGlobalLayout:r.bottom " + r.bottom);

                if (diff != 0) {
//                        contentView.setPadding(0, 0, 0, diff);
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) contentView.getLayoutParams();
                    lp.setMargins(0, 0, 0, diff);
                    contentView.setLayoutParams(lp);

                } else {

                    if (diffTest != 0) {
                        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) contentView.getLayoutParams();
                        lp.setMargins(0, diffTest, 0, 0);
                        contentView.setLayoutParams(lp);
                    }
//                        contentView.setPadding(0, 0, 0, 0);
//                        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) contentView.getLayoutParams();
//
//                        contentView.setLayoutParams(lp);

                }
                diffTest = diff;
            }
        }

                ;
    }

}
