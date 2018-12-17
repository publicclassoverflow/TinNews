package com.coollime.tinnews.common;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class LongPressGesture implements GestureDetector.OnGestureListener {
    private GestureListener gestureListener;

    public LongPressGesture(GestureListener gestureListener) {
        this.gestureListener = gestureListener;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        gestureListener.onPress();
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        gestureListener.onLongPress();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public interface GestureListener {
        // The GestureListener interface offers two features
        // Long press to delete a single selected news
        void onLongPress();
        // Short press/click to read the news detail
        void onPress();
    }
}
