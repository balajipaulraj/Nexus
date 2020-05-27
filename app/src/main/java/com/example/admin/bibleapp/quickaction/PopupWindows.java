package com.example.admin.bibleapp.quickaction;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public class PopupWindows {
    protected Drawable mBackground = null;
    protected Context mContext;
    protected View mRootView;
    protected PopupWindow mWindow;
    protected WindowManager mWindowManager;

    class C03291 implements OnTouchListener {
        C03291() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() != 4) {
                return false;
            }
            PopupWindows.this.mWindow.dismiss();
            return true;
        }
    }

    public PopupWindows(Context context) {
        this.mContext = context;
        this.mWindow = new PopupWindow(context);
        this.mWindow.setTouchInterceptor(new C03291());
        this.mWindowManager = (WindowManager) context.getSystemService("window");
    }

    protected void onDismiss() {
    }

    protected void onShow() {
    }

    protected void preShow() {
        if (this.mRootView == null) {
            throw new IllegalStateException("setContentView was not called with a view to display");
        }
        onShow();
        if (this.mBackground == null) {
            this.mWindow.setBackgroundDrawable(new BitmapDrawable());
        } else {
            this.mWindow.setBackgroundDrawable(this.mBackground);
        }
        this.mWindow.setWidth(-2);
        this.mWindow.setHeight(-2);
        this.mWindow.setTouchable(true);
        this.mWindow.setFocusable(true);
        this.mWindow.setOutsideTouchable(true);
        this.mWindow.setContentView(this.mRootView);
    }

    public void setBackgroundDrawable(Drawable background) {
        this.mBackground = background;
    }

    public void setContentView(View root) {
        this.mRootView = root;
        this.mWindow.setContentView(root);
    }

    public void setContentView(int layoutResID) {
        setContentView(((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(layoutResID, null));
    }

    public void setOnDismissListener(OnDismissListener listener) {
        this.mWindow.setOnDismissListener(listener);
    }

    public void dismiss() {
        this.mWindow.dismiss();
    }
}
