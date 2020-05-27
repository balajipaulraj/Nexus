package com.example.admin.bibleapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

public class UnderlinedTextView extends TextView {
    private int mColor;
    private float mDensity;
    private Paint mPaint;
    private Rect mRect;
    private float mStrokeWidth;

    public UnderlinedTextView(Context context) {
        this(context, null, 0);
    }

    public UnderlinedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderlinedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyle) {
        this.mDensity = context.getResources().getDisplayMetrics().density;
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, C0246R.styleable.UnderlinedTextView, defStyle, 0);
        this.mStrokeWidth = typedArray.getDimension(0, this.mDensity * 2.0f);
        typedArray.recycle();
        this.mRect = new Rect();
        this.mPaint = new Paint();
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setColor(this.mColor);
        this.mPaint.setStrokeWidth(this.mStrokeWidth);
    }

    public int getUnderLineColor() {
        return this.mColor;
    }

    public void setUnderLineColor(int mColor) {
        this.mColor = mColor;
        this.mRect = new Rect();
        this.mPaint = new Paint();
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setColor(mColor);
        this.mPaint.setStrokeWidth(this.mStrokeWidth);
        invalidate();
    }

    public float getUnderlineWidth() {
        return this.mStrokeWidth;
    }

    public void setUnderlineWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        int count = getLineCount();
        Layout layout = getLayout();
        for (int i = 0; i < count; i++) {
            int baseline = getLineBounds(i, this.mRect);
            int firstCharInLine = layout.getLineStart(i);
            int lastCharInLine = layout.getLineEnd(i);
            float xStart = layout.getPrimaryHorizontal(firstCharInLine);
            float f = this.mStrokeWidth + ((float) baseline);
            Canvas canvas2 = canvas;
            canvas2.drawLine(xStart, f, layout.getPrimaryHorizontal(lastCharInLine - 1) + (layout.getPrimaryHorizontal(firstCharInLine + 1) - xStart), this.mStrokeWidth + ((float) baseline), this.mPaint);
        }
        super.onDraw(canvas);
    }
}
