package com.example.admin.bibleapp.drag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import androidx.core.view.PointerIconCompat;

public class DragView extends View {
    private static final boolean DEBUG = false;
    private static final int PADDING_TO_SCALE = 0;
    private Bitmap mBitmap;
    private Paint mDebugPaint;
    private LayoutParams mLayoutParams;
    private final int mRegistrationX;
    private final int mRegistrationY;
    private WindowManager mWindowManager;

    public DragView(Context context) throws Exception {
        super(context);
        this.mDebugPaint = new Paint();
        this.mRegistrationX = 0;
        this.mRegistrationY = 0;
        throw new Exception("DragView constructor permits only programatical calling");
    }

    public DragView(Context context, Bitmap bitmap, int registrationX, int registrationY, int left, int top, int width, int height) {
        super(context);
        this.mDebugPaint = new Paint();
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mRegistrationX = registrationX + 0;
        this.mRegistrationY = registrationY + 0;
        float scaleFactor = (((float) width) + 0.0f) / ((float) width);
        Matrix scale = new Matrix();
        scale.setScale(scaleFactor, scaleFactor);
        this.mBitmap = Bitmap.createBitmap(bitmap, left, top, width, height, scale, true);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(this.mBitmap.getWidth(), this.mBitmap.getHeight());
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, null);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mBitmap.recycle();
    }

    void show(IBinder windowToken, int touchX, int touchY) {
        LayoutParams lp = new LayoutParams(-2, -2, touchX - this.mRegistrationX, touchY - this.mRegistrationY, PointerIconCompat.TYPE_HAND, 768, -3);
        lp.gravity = 51;
        lp.token = windowToken;
        lp.setTitle("DragView");
        this.mLayoutParams = lp;
        this.mWindowManager.addView(this, lp);
    }

    void move(int touchX, int touchY) {
        LayoutParams lp = this.mLayoutParams;
        lp.x = touchX - this.mRegistrationX;
        lp.y = touchY - this.mRegistrationY;
        this.mWindowManager.updateViewLayout(this, lp);
    }

    void remove() {
        this.mWindowManager.removeView(this);
    }
}
