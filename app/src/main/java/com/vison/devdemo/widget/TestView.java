package com.vison.devdemo.widget;

import android.content.Context;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by vison on 16/5/22.
 */
public class TestView extends View implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener{
    GestureDetector mGestureDetector = new GestureDetector(this);


    public TestView(Context context) {
        super(context);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取滑动速度
        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        int xVelocity = (int)velocityTracker.getXVelocity();
        int YVelocity = (int)velocityTracker.getYVelocity();

        mGestureDetector.setIsLongpressEnabled(true);
        boolean consume = mGestureDetector.onTouchEvent(event);

        return consume;
    }

    @Override
    //手指触摸屏幕一瞬间触发
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    //手指轻触明目,尚未松开或拖动
    public void onShowPress(MotionEvent e) {

    }

    @Override
    //手指松开触发
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    //手指按下屏幕并拖动
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    //长按
    public void onLongPress(MotionEvent e) {

    }

    @Override
    //按下触摸屏,快速滑动后触发
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    //严格的单击,而不是双击中的某一次
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    //双击
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    //表示发生了双击行为
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    RelativeLayout
}
