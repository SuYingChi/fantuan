package com.wetime.fanc.customview;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public abstract class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private GestureDetectorCompat mGestureDetector;
    private RecyclerView recyclerView;

    public OnRecyclerItemClickListener(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new ItemTouchHelperGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            super.onDown(e);
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//            Log.d("zk", "X=" + e.getX());
//            Log.d("zk", "Width =" + child.getWidth());
            if (child != null && e.getX() > child.getWidth() * 0.8) {
                RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
                onItemTouchClick(vh);

            }
            return false;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
                onItemClick(vh);
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
//            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//            if (child != null) {
//                RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
//                onItemLongClick(vh);
//            }
        }
    }

    public abstract void onItemClick(RecyclerView.ViewHolder vh);

    public abstract void onItemLongClick(RecyclerView.ViewHolder vh);

    public abstract void onItemTouchClick(RecyclerView.ViewHolder vh);
}