//package com.example.admin.bibleapp;
//
//import android.content.Context;
//import android.view.GestureDetector;
//import android.view.GestureDetector.SimpleOnGestureListener;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnTouchListener;
//
//public class OnSwipeTouchListener implements OnTouchListener {
//    private final GestureDetector gestureDetector;
//
//    private final class GestureListener extends GestureListener {
//        private static final int SWIPE_THRESHOLD = 100;
//        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
//
//        private GestureListener() {
//        }
//
//        public boolean onDown(MotionEvent e) {
//            return true;
//        }
//
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            try {
//                float diffY = e2.getY() - e1.getY();
//                float diffX = e2.getX() - e1.getX();
//                if (Math.abs(diffX) > Math.abs(diffY)) {
//                    if (Math.abs(diffX) <= 100.0f || Math.abs(velocityX) <= 100.0f) {
//                        return false;
//                    }
//                    if (diffX > 0.0f) {
//                        OnSwipeTouchListener.this.onSwipeRight();
//                    } else {
//                        OnSwipeTouchListener.this.onSwipeLeft();
//                    }
//                    return true;
//                } else if (Math.abs(diffY) <= 100.0f || Math.abs(velocityY) <= 100.0f) {
//                    return false;
//                } else {
//                    if (diffY > 0.0f) {
//                        OnSwipeTouchListener.this.onSwipeBottom();
//                    } else {
//                        OnSwipeTouchListener.this.onSwipeTop();
//                    }
//                    return true;
//                }
//            } catch (Exception exception) {
//                exception.printStackTrace();
//                return false;
//            }
//        }
//    }
//
//    public OnSwipeTouchListener(Context ctx) {
//        this.gestureDetector = new GestureDetector(ctx, new GestureListener());
//    }
//
//    public boolean onTouch(View v, MotionEvent event) {
//        return this.gestureDetector.onTouchEvent(event);
//    }
//
//    public void onSwipeRight() {
//    }
//
//    public void onSwipeLeft() {
//    }
//
//    public void onSwipeTop() {
//    }
//
//    public void onSwipeBottom() {
//    }
//}
package com.example.admin.bibleapp;



import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener2 implements OnTouchListener {


    private final GestureDetector gestureDetector;
    private Context context;

    /* (non-Javadoc)
    * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
    */
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    /**
     * Gets the gesture detector.
     *
     * @return the gesture detector
     */
    public GestureDetector getGestureDetector(){
        return  gestureDetector;
    }

    /**
     * Instantiates a new on swipe touch listener.
     *
     * @param context
     *            the context
     */
    public OnSwipeTouchListener2(Context context) {
        super();
        this.context = context;
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        /* (non-Javadoc)
         * @see android.view.GestureDetector.SimpleOnGestureListener#onDown(android.view.MotionEvent)
         */
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

    /* (non-Javadoc)
     * @see android.view.GestureDetector.SimpleOnGestureListener#onFling(android.view.MotionEvent, android.view.MotionEvent, float, float)
     */

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getRawY() - e1.getRawY();
                float diffX = e2.getRawX() - e1.getRawX();
                if ((Math.abs(diffX) - Math.abs(diffY)) > SWIPE_THRESHOLD) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                    }
                }
            } catch (Exception e) {

            }
            return result;
        }
    }

    /**
     * On swipe right.
     */
    public void onSwipeRight() {
    }

    /**
     * On swipe left.
     */
    public void onSwipeLeft() {
    }

    /**
     * On swipe top.
     */
    public void onSwipeTop() {
    }

    /**
     * On swipe bottom.
     */
    public void onSwipeBottom() {
    }

}