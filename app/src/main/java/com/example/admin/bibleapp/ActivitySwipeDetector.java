package com.example.admin.bibleapp;

/**
 * Created by Admin on 6/14/2018.
 */


import android.app.Activity;
        import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
        import android.view.View;

public class ActivitySwipeDetector {

    private float gt, lt;
    private Activity activity;
    static final int MIN_DISTANCE = 100;
    private float downX, downY, upX, upY;

    public ActivitySwipeDetector(final Activity activity) {
        this.activity = activity;
    }

    public final void onRightToLeftSwipe() {
        Log.i("RightToLeftSwipe!", "");
    }

    public void onLeftToRightSwipe() {
        Log.i("LeftToRightSwipe!", "");
    }

    public void onTopToBottomSwipe() {
        Log.i("onTopToBottomSwipe!", "");
    }

    public void onBottomToTopSwipe() {
        Log.i("onBottomToTopSwipe!", "");
    }

    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction( )) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX( );
                downY = event.getY( );
                //   return true;
            }
            case MotionEvent.ACTION_UP: {
                upX = event.getX( );
                upY = event.getY( );

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                // swipe horizontal?

            }
        }
        return false;

    }
}
