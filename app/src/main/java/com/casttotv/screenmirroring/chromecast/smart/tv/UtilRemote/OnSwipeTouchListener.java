package com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener {
    private final GestureDetector gestureDetector;

    public void onSingleTap() {
    }

    public OnSwipeTouchListener(Context context) {
        this.gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.getParent().requestDisallowInterceptTouchEvent(true);
        return this.gestureDetector.onTouchEvent(motionEvent);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        private GestureListener() {
        }

        public boolean onDown(MotionEvent motionEvent) {
            OnSwipeTouchListener.this.onDownF(motionEvent);
            return true;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            OnSwipeTouchListener.this.onSingleTap();
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return super.onSingleTapConfirmed(motionEvent);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            OnSwipeTouchListener.this.onDoubleTapF(motionEvent);
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            try {
                float y = motionEvent2.getY() - motionEvent.getY();
                float x = motionEvent2.getX() - motionEvent.getX();
                if (Math.abs(x) > Math.abs(y)) {
                    if (Math.abs(x) <= 100.0f || Math.abs(f) <= 100.0f) {
                        return false;
                    }
                    if (x > 0.0f) {
                        OnSwipeTouchListener.this.onSwipeRight();
                    } else {
                        OnSwipeTouchListener.this.onSwipeLeft();
                    }
                } else if (Math.abs(y) <= 100.0f || Math.abs(f2) <= 100.0f) {
                    return false;
                } else {
                    if (y > 0.0f) {
                        OnSwipeTouchListener.this.onSwipeBottom();
                    } else {
                        OnSwipeTouchListener.this.onSwipeTop();
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public void onDoubleTapF(MotionEvent motionEvent) {
        Log.d("111111111", "onDoubleTapF");
    }

    public void onDownF(MotionEvent motionEvent) {
        Log.d("111111111", "onDownF");
    }

    public void onSwipeRight() {
        Log.d("111111111", "Right");
    }

    public void onSwipeLeft() {
        Log.d("111111111", "Left");
    }

    public void onSwipeTop() {
        Log.d("111111111", "Top");
    }

    public void onSwipeBottom() {
        Log.d("111111111", "Bottom");
    }
}
