package com.example.admin.bibleapp.drag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import java.util.ArrayList;

public class DragController {
    public static final String TAG = "DragController";
    private Context mContext;
    private final int[] mCoordinatesTemp = new int[2];
    private DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    private Object mDragInfo;
    private DragSource mDragSource;
    private DragView mDragView;
    private boolean mDragging;
    private ArrayList<DropTarget> mDropTargets = new ArrayList();
    private InputMethodManager mInputMethodManager;
    private DropTarget mLastDropTarget;
    private DragListener mListener;
    private float mMotionDownX;
    private float mMotionDownY;
    private View mMoveTarget;
    private View mOriginator;
    private Rect mRectTemp = new Rect();
    private float mTouchOffsetX;
    private float mTouchOffsetY;
    private IBinder mWindowToken;

    public enum DragBehavior {
        MOVE,
        COPY
    }

    public DragController(Context context) {
        this.mContext = context;
    }

    public void startDrag(View v, DragSource source, Object dragInfo, DragBehavior dragBehavior) {
        if (source.allowDrag()) {
            this.mOriginator = v;
            Bitmap b = getViewBitmap(v);
            if (b != null) {
                int[] loc = this.mCoordinatesTemp;
                v.getLocationOnScreen(loc);
                startDrag(b, loc[0], loc[1], 0, 0, b.getWidth(), b.getHeight(), source, dragInfo, dragBehavior);
                b.recycle();
                if (dragBehavior == DragBehavior.MOVE) {
                    v.setVisibility(View.GONE);
                }
            }
        }
    }

    private void startDrag(Bitmap b, int screenX, int screenY, int textureLeft, int textureTop, int textureWidth, int textureHeight, DragSource source, Object dragInfo, DragBehavior dragBehavior) {
        if (this.mInputMethodManager == null) {
            this.mInputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
        }
        this.mInputMethodManager.hideSoftInputFromWindow(this.mWindowToken, 0);
        if (this.mListener != null) {
            this.mListener.onDragStart(source, dragInfo, dragBehavior);
        }
        int registrationX = ((int) this.mMotionDownX) - screenX;
        int registrationY = ((int) this.mMotionDownY) - screenY;
        this.mTouchOffsetX = this.mMotionDownX - ((float) screenX);
        this.mTouchOffsetY = this.mMotionDownY - ((float) screenY);
        this.mDragging = true;
        this.mDragSource = source;
        this.mDragInfo = dragInfo;
        this.mDragView = new DragView(this.mContext, b, registrationX, registrationY, textureLeft, textureTop, textureWidth, textureHeight);
        this.mDragView.show(this.mWindowToken, (int) this.mMotionDownX, (int) this.mMotionDownY);
    }

    private Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);
        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            Log.e(TAG, "failed getViewBitmap(" + v + ")", new RuntimeException());
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return this.mDragging;
    }

    public void cancelDrag() {
        endDrag();
    }

    private void endDrag() {
        if (this.mDragging) {
            this.mDragging = false;
            if (this.mOriginator != null) {
                this.mOriginator.setVisibility(0);
            }
            if (this.mListener != null) {
                this.mListener.onDragEnd();
            }
            if (this.mDragView != null) {
                this.mDragView.remove();
                this.mDragView = null;
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            recordScreenSize();
        }
        int screenX = clamp((int) ev.getRawX(), 0, this.mDisplayMetrics.widthPixels);
        int screenY = clamp((int) ev.getRawY(), 0, this.mDisplayMetrics.heightPixels);
        switch (action) {
            case 0:
                this.mMotionDownX = (float) screenX;
                this.mMotionDownY = (float) screenY;
                this.mLastDropTarget = null;
                break;
            case 1:
            case 3:
                if (this.mDragging) {
                    drop((float) screenX, (float) screenY);
                }
                endDrag();
                break;
        }
        return this.mDragging;
    }

    void setMoveTarget(View view) {
        this.mMoveTarget = view;
    }

    public boolean dispatchUnhandledMove(View focused, int direction) {
        return this.mMoveTarget != null && this.mMoveTarget.dispatchUnhandledMove(focused, direction);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.mDragging) {
            return false;
        }
        int action = ev.getAction();
        int screenX = clamp((int) ev.getRawX(), 0, this.mDisplayMetrics.widthPixels);
        int screenY = clamp((int) ev.getRawY(), 0, this.mDisplayMetrics.heightPixels);
        switch (action) {
            case 0:
                this.mMotionDownX = (float) screenX;
                this.mMotionDownY = (float) screenY;
                break;
            case 1:
                if (this.mDragging) {
                    drop((float) screenX, (float) screenY);
                }
                endDrag();
                break;
            case 2:
                this.mDragView.move((int) ev.getRawX(), (int) ev.getRawY());
                int[] coordinates = this.mCoordinatesTemp;
                DropTarget dropTarget = findDropTarget(screenX, screenY, coordinates);
                if (dropTarget != null) {
                    if (this.mLastDropTarget == dropTarget) {
                        dropTarget.onDragOver(this.mDragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.mDragView, this.mDragInfo);
                    } else {
                        if (this.mLastDropTarget != null) {
                            this.mLastDropTarget.onDragExit(this.mDragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.mDragView, this.mDragInfo);
                        }
                        dropTarget.onDragEnter(this.mDragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.mDragView, this.mDragInfo);
                    }
                } else if (this.mLastDropTarget != null) {
                    this.mLastDropTarget.onDragExit(this.mDragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.mDragView, this.mDragInfo);
                }
                this.mLastDropTarget = dropTarget;
                break;
            case 3:
                cancelDrag();
                break;
        }
        return true;
    }

    private boolean drop(float x, float y) {
        int[] coordinates = this.mCoordinatesTemp;
        DropTarget dropTarget = findDropTarget((int) x, (int) y, coordinates);
        if (dropTarget == null) {
            return false;
        }
        dropTarget.onDragExit(this.mDragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.mDragView, this.mDragInfo);
        if (dropTarget.acceptDrop(this.mDragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.mDragView, this.mDragInfo)) {
            dropTarget.onDrop(this.mDragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.mDragView, this.mDragInfo);
            this.mDragSource.onDropCompleted((View) dropTarget, true);
        } else {
            this.mDragSource.onDropCompleted((View) dropTarget, false);
        }
        return true;
    }

    private DropTarget findDropTarget(int x, int y, int[] dropCoordinates) {
        Rect r = this.mRectTemp;
        ArrayList<DropTarget> dropTargets = this.mDropTargets;
        for (int i = dropTargets.size() - 1; i >= 0; i--) {
            DropTarget target = (DropTarget) dropTargets.get(i);
            target.getHitRect(r);
            target.getLocationOnScreen(dropCoordinates);
            r.offset(dropCoordinates[0] - target.getLeft(), dropCoordinates[1] - target.getTop());
            if (r.contains(x, y)) {
                dropCoordinates[0] = x - dropCoordinates[0];
                dropCoordinates[1] = y - dropCoordinates[1];
                return target;
            }
        }
        return null;
    }

    private void recordScreenSize() {
        ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(this.mDisplayMetrics);
    }

    private static int clamp(int val, int min, int max) {
        if (val < min) {
            return min;
        }
        return val >= max ? max - 1 : val;
    }

    public void setDragListener(DragListener listener) {
        this.mListener = listener;
    }

    public void addDropTarget(DropTarget target) {
        this.mDropTargets.add(target);
    }

    public void removeDropTarget(DropTarget target) {
        this.mDropTargets.remove(target);
    }
}
