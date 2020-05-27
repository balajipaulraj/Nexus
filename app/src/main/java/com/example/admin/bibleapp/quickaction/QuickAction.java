//package com.example.admin.bibleapp.quickaction;
//
//import android.content.Context;
//import android.graphics.Rect;
//import android.graphics.drawable.Drawable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.ViewGroup.LayoutParams;
//import android.view.ViewGroup.MarginLayoutParams;
//import android.widget.ImageView;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//import android.widget.ScrollView;
//import android.widget.TextView;
//
//
//import com.example.admin.bibleapp.C0246R;
//import com.example.admin.bibleapp.R;
//import java.util.ArrayList;
//import java.util.List;
//
//public class QuickAction extends PopupWindows implements QuickAction.OnDismissListener {
//    public static final int ANIM_AUTO = 5;
//    public static final int ANIM_GROW_FROM_CENTER = 3;
//    public static final int ANIM_GROW_FROM_LEFT = 1;
//    public static final int ANIM_GROW_FROM_RIGHT = 2;
//    public static final int ANIM_REFLECT = 4;
//    public static final int HORIZONTAL = 0;
//    public static final int VERTICAL = 1;
//    private List<ActionItem> mActionItems;
//    private int mAnimStyle;
//    PopupWindow r1;
//    private ImageView mArrowDown;
//    private ImageView mArrowUp;
//    private int mChildPos;
//    private boolean mDidAction;
//    private OnDismissListener mDismissListener;
//    private LayoutInflater mInflater;
//    private int mInsertPos;
//    private OnActionItemClickListener mItemClickListener;
//    private int mOrientation;
//    private View mRootView;
//    private int mRootWidth;
//    private ScrollView mScroller;
//    private ViewGroup mTrack;
//
//
//
//    @Override
//    public void onDismiss() {
//        if (!this.mDidAction && this.mDismissListener != null) {
//            this.mDismissListener.onDismiss();
//        }
//    }
//
//    public interface OnActionItemClickListener {
//        void onItemClick(QuickAction quickAction, int i, int i2);
//    }
//
//    public interface OnDismissListener {
//        void onDismiss();
//    }
//
//    public QuickAction(Context context) {
//        this(context, 1);
//    }
//
//    public QuickAction(Context context, int orientation) {
//        super(context);
//        this.mActionItems = new ArrayList();
//        this.mRootWidth = 0;
//        this.mOrientation = orientation;
//        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
//        if (this.mOrientation == 0) {
//            setRootViewId(R.layout.popup_horizontal);
//        } else {
//            setRootViewId(R.layout.popup_vertical);
//        }
//        this.mAnimStyle = 5;
//        this.mChildPos = 0;
//    }
//
//    public ActionItem getActionItem(int index) {
//        return (ActionItem) this.mActionItems.get(index);
//    }
//
//    public void setRootViewId(int id) {
//        this.mRootView = (ViewGroup) this.mInflater.inflate(id, null);
//        this.mTrack = (ViewGroup) this.mRootView.findViewById(R.id.tracks);
//        this.mArrowDown = (ImageView) this.mRootView.findViewById(R.id.arrow_down);
//        this.mArrowUp = (ImageView) this.mRootView.findViewById(R.id.arrow_up);
//        this.mScroller = (ScrollView) this.mRootView.findViewById(R.id.scroller);
//        this.mRootView.setLayoutParams(new LayoutParams(-2, -2));
//        setContentView(this.mRootView);
//    }
//
//    public void setAnimStyle(int mAnimStyle) {
//        this.mAnimStyle = mAnimStyle;
//    }
//
//    public void setOnActionItemClickListener(OnActionItemClickListener listener) {
//        this.mItemClickListener = listener;
//    }
//
//    public void addActionItem(ActionItem action) {
//        View container;
//        this.mActionItems.add(action);
//        String title = action.getTitle();
//        Drawable icon = action.getIcon();
//        if (this.mOrientation == 0) {
//            container = this.mInflater.inflate(R.layout.action_item_horizontal, null);
//        } else {
//            container = this.mInflater.inflate(R.layout.action_item_vertical, null);
//        }
//        TextView text = (TextView) container.findViewById(R.id.tv_title);
//        if (title != null) {
//            text.setText(title);
//        } else {
//            text.setVisibility(View.GONE);
//        }
//        final int pos = this.mChildPos;
//        final int actionId = action.getActionId();
//        container.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                if (QuickAction.this.mItemClickListener != null) {
//                    QuickAction.this.mItemClickListener.onItemClick(QuickAction.this, pos, actionId);
//                }
//                if (!QuickAction.this.getActionItem(pos).isSticky()) {
//                    QuickAction.this.mDidAction = true;
//                    QuickAction.this.dismiss();
//                }
//            }
//        });
//        container.setFocusable(true);
//        container.setClickable(true);
//        if (this.mOrientation == 0 && this.mChildPos != 0) {
//            View separator = this.mInflater.inflate(R.layout.horiz_separator, null);
//            separator.setLayoutParams(new RelativeLayout.LayoutParams(-2, -1));
//            separator.setPadding(5, 0, 5, 0);
//            this.mTrack.addView(separator, this.mInsertPos);
//            this.mInsertPos++;
//        }
//        this.mTrack.addView(container, this.mInsertPos);
//        this.mChildPos++;
//        this.mInsertPos++;
//    }
//
//    public void show(View anchor) {
//        int xPos;
//        int arrowPos;
//        int yPos;
//        preShow();
//        this.mDidAction = false;
//        int[] location = new int[2];
//        anchor.getLocationOnScreen(location);
//        Rect anchorRect = new Rect(location[0], location[1], location[0] + anchor.getWidth(), location[1] + anchor.getHeight());
//        this.mRootView.measure(-2, -2);
//        int rootHeight = this.mRootView.getMeasuredHeight();
//        if (this.mRootWidth == 0) {
//            this.mRootWidth = this.mRootView.getMeasuredWidth();
//        }
//        int screenWidth = this.mWindowManager.getDefaultDisplay().getWidth();
//        int screenHeight = this.mWindowManager.getDefaultDisplay().getHeight();
//        if (anchorRect.left + this.mRootWidth > screenWidth) {
//            xPos = anchorRect.left - (this.mRootWidth - anchor.getWidth());
//            if (xPos < 0) {
//                xPos = 0;
//            }
//            arrowPos = anchorRect.centerX() - xPos;
//        } else {
//            if (anchor.getWidth() > this.mRootWidth) {
//                xPos = anchorRect.centerX() - (this.mRootWidth / 2);
//            } else {
//                xPos = anchorRect.left;
//            }
//            arrowPos = anchorRect.centerX() - xPos;
//        }
//        int dyTop = anchorRect.top;
//        int dyBottom = screenHeight - anchorRect.bottom;
//        boolean onTop = dyTop > dyBottom;
//        if (!onTop) {
//            yPos = anchorRect.bottom;
//            if (rootHeight > dyBottom) {
//                this.mScroller.getLayoutParams().height = dyBottom;
//            }
//        } else if (rootHeight > dyTop) {
//            yPos = 15;
//            this.mScroller.getLayoutParams().height = dyTop - anchor.getHeight();
//        } else {
//            yPos = anchorRect.top - rootHeight;
//        }
//        showArrow(onTop ? R.id.arrow_down : R.id.arrow_up, arrowPos);
//        setAnimationStyle(screenWidth, anchorRect.centerX(), onTop);
//        this.mWindow.showAtLocation(anchor, 0, xPos, yPos);
//    }
//
//    public void show(View anchor, int width, int height) {
//        int xPos;
//        int arrowPos;
//        int yPos;
//        preShow();
//        int[] location = new int[2];
//        anchor.getLocationOnScreen(location);
//        Rect anchorRect = new Rect(location[0], location[1], location[0] + width, location[1] + height);
//        this.mDidAction = false;
//        this.mRootView.measure(-2, -2);
//        int rootHeight = this.mRootView.getMeasuredHeight();
//        if (this.mRootWidth == 0) {
//            this.mRootWidth = this.mRootView.getMeasuredWidth();
//        }
//        int screenWidth = this.mWindowManager.getDefaultDisplay().getWidth();
//        int screenHeight = this.mWindowManager.getDefaultDisplay().getHeight();
//        if (anchorRect.left + this.mRootWidth > screenWidth) {
//            xPos = anchorRect.left - (this.mRootWidth - width);
//            if (xPos < 0) {
//                xPos = 0;
//            }
//            arrowPos = anchorRect.centerX() - xPos;
//        } else {
//            if (width > this.mRootWidth) {
//                xPos = anchorRect.centerX() - (this.mRootWidth / 2);
//            } else {
//                xPos = anchorRect.left;
//            }
//            arrowPos = anchorRect.centerX() - xPos;
//        }
//        int dyTop = anchorRect.top;
//        int dyBottom = screenHeight - anchorRect.bottom;
//        boolean onTop = dyTop > dyBottom;
//        if (!onTop) {
//            yPos = anchorRect.bottom;
//            if (rootHeight > dyBottom) {
//                this.mScroller.getLayoutParams().height = dyBottom;
//            }
//        } else if (rootHeight > dyTop) {
//            yPos = 15;
//            this.mScroller.getLayoutParams().height = dyTop - height;
//        } else {
//            yPos = anchorRect.top - rootHeight;
//        }
//        showArrow(onTop ? R.id.arrow_down : R.id.arrow_up, arrowPos);
//        setAnimationStyle(screenWidth, anchorRect.centerX(), onTop);
//        this.mWindow.showAtLocation(anchor, 0, xPos, yPos);
//    }
//
//    private void setAnimationStyle(int screenWidth, int requestedX, boolean onTop) {
//        int i = C0246R.style.Animations_PopUpMenu_Left;
//        int i2 = C0246R.style.Animations_PopUpMenu_Center;
//        int i3 = C0246R.style.Animations_PopDownMenu_Right;
//        int arrowPos = requestedX - (this.mArrowUp.getMeasuredWidth() / 2);
//        PopupWindow popupWindow;
//        switch (this.mAnimStyle) {
//            case 1:
//                popupWindow = this.mWindow;
//                if (!onTop) {
//                    i = C0246R.style.Animations_PopDownMenu_Left;
//                }
//                popupWindow.setAnimationStyle(i);
//                return;
//            case 2:
//                this.mWindow.setAnimationStyle(onTop ? C0246R.style.Animations_PopDownMenu_Right : C0246R.style.Animations_PopDownMenu_Right);
//                return;
//            case 3:
//                this.mWindow.setAnimationStyle(onTop ? C0246R.style.Animations_PopUpMenu_Center : C0246R.style.Animations_PopDownMenu_Center);
//                return;
//            case 4:
//                this.mWindow.setAnimationStyle(onTop ? C0246R.style.Animations_PopUpMenu_Reflect : C0246R.style.Animations_PopUpMenu_Reflect);
//                return;
//            case 5:
//                if (arrowPos <= screenWidth / 4) {
//                    popupWindow = this.mWindow;
//                    if (!onTop) {
//                        i = C0246R.style.Animations_PopDownMenu_Left;
//                    }
//                    popupWindow.setAnimationStyle(i);
//                    return;
//                } else if (arrowPos <= screenWidth / 4 || arrowPos >= (screenWidth / 4) * 3) {
//                    r1 = this.mWindow;
//                    if (onTop) {
//                        i3 = C0246R.style.Animations_PopUpMenu_Right;
//                    }
//                    r1.setAnimationStyle(i3);
//                    return;
//                } else {
//                    r1 = this.mWindow;
//                    if (!onTop) {
//                        i2 = C0246R.style.Animations_PopDownMenu_Center;
//                    }
//                    r1.setAnimationStyle(i2);
//                    return;
//                }
//            default:
//                return;
//        }
//    }
//
//    private void showArrow(int whichArrow, int requestedX) {
//        View showArrow = whichArrow == R.id.arrow_up ? this.mArrowUp : this.mArrowDown;
//        View hideArrow = whichArrow == R.id.arrow_up ? this.mArrowDown : this.mArrowUp;
//        int arrowWidth = this.mArrowUp.getMeasuredWidth();
//        showArrow.setVisibility(View.VISIBLE);
//        ((MarginLayoutParams) showArrow.getLayoutParams()).leftMargin = requestedX - (arrowWidth / 2);
//        hideArrow.setVisibility(View.GONE);
//    }
//
//    public void setOnDismissListener(OnDismissListener listener) {
//        setOnDismissListener(this);
//        this.mDismissListener = listener;
//    }
//
//}
