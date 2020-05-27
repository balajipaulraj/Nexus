package com.example.admin.bibleapp.webviewmarker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.drag.DragController;
import com.example.admin.bibleapp.drag.DragController.DragBehavior;
import com.example.admin.bibleapp.drag.DragLayer;
import com.example.admin.bibleapp.drag.DragListener;
import com.example.admin.bibleapp.drag.DragSource;
import com.example.admin.bibleapp.drag.MyAbsoluteLayout;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"DefaultLocale"})
public class TextSelectionSupport implements TextSelectionControlListener, OnTouchListener, OnLongClickListener, DragListener {
    private static final float CENTERING_SHORTER_MARGIN_RATIO = 0.25f;
    private static final int JACK_UP_PADDING = 2;
    private static final int SCROLLING_THRESHOLD = 10;
    private static final String TAG = "SelectionSupport";
    private Runnable drawSelectionHandlesHandler = new C03366();
    private Runnable endSelectionModeHandler = new C03322();
    private Activity mActivity;
    private int mContentWidth = 0;
    private DragController mDragController;
    private ImageView mEndSelectionHandle;
    private float mLastTouchX = 0.0f;
    private float mLastTouchY = 0.0f;
    private HandleType mLastTouchedSelectionHandle = HandleType.UNKNOWN;
    private float mScale = 1.0f;
    private float mScrollDiffX = 0.0f;
    private float mScrollDiffY = 0.0f;
    private boolean mScrolling = false;
    private Rect mSelectionBounds = null;
    private final Rect mSelectionBoundsTemp = new Rect();
    private TextSelectionController mSelectionController = null;
    private DragLayer mSelectionDragLayer;
    private SelectionListener mSelectionListener;
    private ImageView mStartSelectionHandle;
    private Runnable mStartSelectionModeHandler = new C03311();
    private WebView mWebView;
    private Runnable selectTextHandler = new C03333();

    class C03311 implements Runnable {
        C03311() {
        }

        public void run() {
            if (TextSelectionSupport.this.mSelectionBounds != null) {
                TextSelectionSupport.this.mWebView.addView(TextSelectionSupport.this.mSelectionDragLayer);
                TextSelectionSupport.this.drawSelectionHandles();
                int contentHeight = (int) Math.ceil((double) TextSelectionSupport.this.getDensityDependentValue((float) TextSelectionSupport.this.mWebView.getContentHeight(), TextSelectionSupport.this.mActivity));
                int contentWidth = TextSelectionSupport.this.mWebView.getWidth();
                LayoutParams layerParams = TextSelectionSupport.this.mSelectionDragLayer.getLayoutParams();
                layerParams.height = contentHeight;
                layerParams.width = Math.max(contentWidth, TextSelectionSupport.this.mContentWidth);
                TextSelectionSupport.this.mSelectionDragLayer.setLayoutParams(layerParams);
                if (TextSelectionSupport.this.mSelectionListener != null) {
                    TextSelectionSupport.this.mSelectionListener.startSelection();
                }
            }
        }
    }

    class C03322 implements Runnable {
        C03322() {
        }

        public void run() {
            TextSelectionSupport.this.mWebView.removeView(TextSelectionSupport.this.mSelectionDragLayer);
            TextSelectionSupport.this.mSelectionBounds = null;
            TextSelectionSupport.this.mLastTouchedSelectionHandle = HandleType.UNKNOWN;
            TextSelectionSupport.this.mWebView.loadUrl("javascript: android.selection.clearSelection();");
            if (TextSelectionSupport.this.mSelectionListener != null) {
                TextSelectionSupport.this.mSelectionListener.endSelection();
            }
        }
    }

    class C03333 implements Runnable {
        C03333() {
        }

        public void run() {
            if (TextSelectionSupport.this.mSelectionBounds != null) {
                TextSelectionSupport.this.mWebView.addView(TextSelectionSupport.this.mSelectionDragLayer);
                TextSelectionSupport.this.drawSelectionHandles();
                int contentHeight = (int) Math.ceil((double) TextSelectionSupport.this.getDensityDependentValue((float) TextSelectionSupport.this.mWebView.getContentHeight(), TextSelectionSupport.this.mActivity));
                int contentWidth = TextSelectionSupport.this.mWebView.getWidth();
                LayoutParams layerParams = TextSelectionSupport.this.mSelectionDragLayer.getLayoutParams();
                layerParams.height = contentHeight;
                layerParams.width = Math.max(contentWidth, TextSelectionSupport.this.mContentWidth);
                TextSelectionSupport.this.mSelectionDragLayer.setLayoutParams(layerParams);
                if (TextSelectionSupport.this.mSelectionListener != null) {
                    TextSelectionSupport.this.mSelectionListener.startSelection();
                }
            }
        }
    }

    class C03344 implements Runnable {
        C03344() {
        }

        public void run() {
            MyAbsoluteLayout.LayoutParams startHandleParams = (MyAbsoluteLayout.LayoutParams) TextSelectionSupport.this.mStartSelectionHandle.getLayoutParams();
            MyAbsoluteLayout.LayoutParams endHandleParams = (MyAbsoluteLayout.LayoutParams) TextSelectionSupport.this.mEndSelectionHandle.getLayoutParams();
            Context ctx = TextSelectionSupport.this.mActivity;
            float scale = TextSelectionSupport.this.getDensityIndependentValue(TextSelectionSupport.this.mScale, ctx);
            float startX = TextSelectionSupport.this.getDensityIndependentValue(((float) (startHandleParams.f8x - TextSelectionSupport.this.mWebView.getScrollX())) + (((float) TextSelectionSupport.this.mStartSelectionHandle.getWidth()) * 0.75f), ctx) / scale;
            float startY = TextSelectionSupport.this.getDensityIndependentValue((float) ((startHandleParams.f9y - TextSelectionSupport.this.mWebView.getScrollY()) - 2), ctx) / scale;
            float endX = TextSelectionSupport.this.getDensityIndependentValue(((float) (endHandleParams.f8x - TextSelectionSupport.this.mWebView.getScrollX())) + (((float) TextSelectionSupport.this.mEndSelectionHandle.getWidth()) * TextSelectionSupport.CENTERING_SHORTER_MARGIN_RATIO), ctx) / scale;
            float endY = TextSelectionSupport.this.getDensityIndependentValue((float) ((endHandleParams.f9y - TextSelectionSupport.this.mWebView.getScrollY()) - 2), ctx) / scale;
            if (TextSelectionSupport.this.mLastTouchedSelectionHandle == HandleType.START && startX > 0.0f && startY > 0.0f) {
                TextSelectionSupport.this.mWebView.loadUrl(String.format(Locale.US, "javascript: android.selection.setStartPos(%f, %f);", new Object[]{Float.valueOf(startX), Float.valueOf(startY)}));
            } else if (TextSelectionSupport.this.mLastTouchedSelectionHandle != HandleType.END || endX <= 0.0f || endY <= 0.0f) {
                TextSelectionSupport.this.mWebView.loadUrl("javascript: android.selection.restoreStartEndPos();");
            } else {
                TextSelectionSupport.this.mWebView.loadUrl(String.format(Locale.US, "javascript: android.selection.setEndPos(%f, %f);", new Object[]{Float.valueOf(endX), Float.valueOf(endY)}));
            }
        }
    }

    class C03355 implements OnTouchListener {
        C03355() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() != 0) {
                return false;
            }
            boolean handledHere = TextSelectionSupport.this.startDrag(v);
            TextSelectionSupport.this.mLastTouchedSelectionHandle = (HandleType) v.getTag();
            return handledHere;
        }
    }

    class C03366 implements Runnable {
        C03366() {
        }

        public void run() {
            int i = 0;
            MyAbsoluteLayout.LayoutParams startParams = (MyAbsoluteLayout.LayoutParams) TextSelectionSupport.this.mStartSelectionHandle.getLayoutParams();
            int startWidth = TextSelectionSupport.this.mStartSelectionHandle.getDrawable().getIntrinsicWidth();
            startParams.f8x = (int) (((float) TextSelectionSupport.this.mSelectionBounds.left) - (((float) startWidth) * 0.75f));
            startParams.f9y = TextSelectionSupport.this.mSelectionBounds.top;
            int startMinLeft = -((int) (((float) startWidth) * 0.75f));
            if (startParams.f8x >= startMinLeft) {
                startMinLeft = startParams.f8x;
            }
            startParams.f8x = startMinLeft;
            startParams.f9y = startParams.f9y < 0 ? 0 : startParams.f9y;
            TextSelectionSupport.this.mStartSelectionHandle.setLayoutParams(startParams);
            MyAbsoluteLayout.LayoutParams endParams = (MyAbsoluteLayout.LayoutParams) TextSelectionSupport.this.mEndSelectionHandle.getLayoutParams();
            int endWidth = TextSelectionSupport.this.mEndSelectionHandle.getDrawable().getIntrinsicWidth();
            endParams.f8x = (int) (((float) TextSelectionSupport.this.mSelectionBounds.right) - (((float) endWidth) * TextSelectionSupport.CENTERING_SHORTER_MARGIN_RATIO));
            endParams.f9y = TextSelectionSupport.this.mSelectionBounds.bottom;
            int endMinLeft = -((int) (((float) endWidth) * 0.75f));
            if (endParams.f8x >= endMinLeft) {
                endMinLeft = endParams.f8x;
            }
            endParams.f8x = endMinLeft;
            if (endParams.f9y >= 0) {
                i = endParams.f9y;
            }
            endParams.f9y = i;
            TextSelectionSupport.this.mEndSelectionHandle.setLayoutParams(endParams);
        }
    }

    private enum HandleType {
        START,
        END,
        UNKNOWN
    }

    public interface SelectionListener {
        void endSelection();

        void selectionChanged(String str);

        void startSelection();
    }

    private TextSelectionSupport(Activity activity, WebView webview) {
        this.mActivity = activity;
        this.mWebView = webview;
    }

    public static TextSelectionSupport support(Activity activity, WebView webview) {
        TextSelectionSupport selectionSupport = new TextSelectionSupport(activity, webview);
        selectionSupport.setup();
        return selectionSupport;
    }

    public void onScaleChanged(float oldScale, float newScale) {
        this.mScale = newScale;
    }

    public void setSelectionListener(SelectionListener listener) {
        this.mSelectionListener = listener;
    }

    public void jsError(String error) {
        Log.e(TAG, "JSError: " + error);
    }

    public void jsLog(String message) {
        Log.d(TAG, "JSLog: " + message);
    }

    public void startSelectionMode() {
        this.mActivity.runOnUiThread(this.mStartSelectionModeHandler);
    }

    public void endSelectionMode() {
        this.mActivity.runOnUiThread(this.endSelectionModeHandler);
    }

    public void selecttext() {
        this.mActivity.runOnUiThread(this.selectTextHandler);
    }

    public void setContentWidth(float contentWidth) {
        this.mContentWidth = (int) getDensityDependentValue(contentWidth, this.mActivity);
    }

    public void selectionChanged(String range, String text, String handleBounds, boolean isReallyChanged) {
        Context ctx = this.mActivity;
        try {
            JSONObject selectionBoundsObject = new JSONObject(handleBounds);
            float scale = getDensityIndependentValue(this.mScale, ctx);
            Rect rect = this.mSelectionBoundsTemp;
            rect.left = (int) (getDensityDependentValue((float) selectionBoundsObject.getInt("left"), ctx) * scale);
            rect.top = (int) (getDensityDependentValue((float) selectionBoundsObject.getInt("top"), ctx) * scale);
            rect.right = (int) (getDensityDependentValue((float) selectionBoundsObject.getInt("right"), ctx) * scale);
            rect.bottom = (int) (getDensityDependentValue((float) selectionBoundsObject.getInt("bottom"), ctx) * scale);
            this.mSelectionBounds = rect;
            if (!isInSelectionMode()) {
                startSelectionMode();
            }
            drawSelectionHandles();
            if (this.mSelectionListener != null && isReallyChanged) {
                this.mSelectionListener.selectionChanged(text);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        Context ctx = this.mActivity;
        float xPoint = getDensityIndependentValue(event.getX(), ctx) / getDensityIndependentValue(this.mScale, ctx);
        float yPoint = getDensityIndependentValue(event.getY(), ctx) / getDensityIndependentValue(this.mScale, ctx);
        switch (event.getAction()) {
            case 0:
                String startTouchUrl = String.format(Locale.US, "javascript:android.selection.startTouch(%f, %f);", new Object[]{Float.valueOf(xPoint), Float.valueOf(yPoint)});
                this.mLastTouchX = xPoint;
                this.mLastTouchY = yPoint;
                this.mWebView.loadUrl(startTouchUrl);
                return false;
            case 1:
                if (!this.mScrolling) {
                    endSelectionMode();
                    if (VERSION.SDK_INT >= 19) {
                        return false;
                    }
                }
                this.mScrollDiffX = 0.0f;
                this.mScrollDiffY = 0.0f;
                this.mScrolling = false;
                if (VERSION.SDK_INT < 19 || !isInSelectionMode()) {
                    return false;
                }
                return true;
            case 2:
                this.mScrollDiffX += xPoint - this.mLastTouchX;
                this.mScrollDiffY += yPoint - this.mLastTouchY;
                this.mLastTouchX = xPoint;
                this.mLastTouchY = yPoint;
                if (Math.abs(this.mScrollDiffX) <= 10.0f && Math.abs(this.mScrollDiffY) <= 10.0f) {
                    return false;
                }
                this.mScrolling = true;
                return false;
            default:
                return false;
        }
    }

    public boolean onLongClick(View v) {
        if (!isInSelectionMode()) {
            this.mWebView.loadUrl("javascript:android.selection.longTouch();");
            this.mScrolling = true;
        }
        return true;
    }

    public void onDragStart(DragSource source, Object info, DragBehavior dragBehavior) {
    }

    public void onDragEnd() {
        this.mActivity.runOnUiThread(new C03344());
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void setup() {
        this.mScale = this.mActivity.getResources().getDisplayMetrics().density;
        this.mWebView.setOnLongClickListener(this);
        this.mWebView.setOnTouchListener(this);
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        this.mSelectionController = new TextSelectionController(this);
        this.mWebView.addJavascriptInterface(this.mSelectionController, TextSelectionController.INTERFACE_NAME);
        createSelectionLayer(this.mActivity);
    }

    private void createSelectionLayer(Context context) {
        this.mSelectionDragLayer = (DragLayer) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.selection_drag_layer, null);
        this.mDragController = new DragController(context);
        this.mDragController.setDragListener(this);
        this.mDragController.addDropTarget(this.mSelectionDragLayer);
        this.mSelectionDragLayer.setDragController(this.mDragController);
        this.mStartSelectionHandle = (ImageView) this.mSelectionDragLayer.findViewById(R.id.startHandle);
        this.mStartSelectionHandle.setTag(HandleType.START);
        this.mEndSelectionHandle = (ImageView) this.mSelectionDragLayer.findViewById(R.id.endHandle);
        this.mEndSelectionHandle.setTag(HandleType.END);
        OnTouchListener handleTouchListener = new C03355();
        this.mStartSelectionHandle.setOnTouchListener(handleTouchListener);
        this.mEndSelectionHandle.setOnTouchListener(handleTouchListener);
    }

    private void drawSelectionHandles() {
        this.mActivity.runOnUiThread(this.drawSelectionHandlesHandler);
    }

    private boolean isInSelectionMode() {
        return this.mSelectionDragLayer.getParent() != null;
    }

    private boolean startDrag(View v) {
        this.mDragController.startDrag(v, this.mSelectionDragLayer, v, DragBehavior.MOVE);
        return true;
    }

    private float getDensityDependentValue(float val, Context ctx) {
        Display display = ((WindowManager) ctx.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return (((float) metrics.densityDpi) / 160.0f) * val;
    }

    private float getDensityIndependentValue(float val, Context ctx) {
        Display display = ((WindowManager) ctx.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return val / (((float) metrics.densityDpi) / 160.0f);
    }
}
