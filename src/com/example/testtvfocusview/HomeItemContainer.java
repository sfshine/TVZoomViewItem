package com.example.testtvfocusview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

public class HomeItemContainer extends RelativeLayout
{
    
    private Rect mBound;
    private Drawable mDrawable;
    private Rect mRect;
    private AnimatorSet mAnimatorSetZoomOut;
    private AnimatorSet mAnimatorSetZoomIn;
    private int SELECT_PADDING = 10;
    
    public HomeItemContainer(Context context) {
        super(context);
        init();
    }
    
    public HomeItemContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    
    public HomeItemContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    protected void init() {
        setWillNotDraw(false);
        mRect = new Rect();
        mBound = new Rect();
        mDrawable = getResources().getDrawable(R.drawable.btn_common_focused);//nav_focused_2,poster_shadow_4  
        setChildrenDrawingOrderEnabled(true);
        setFocusableInTouchMode(true);
        setClickable(true);
        setClipChildren(false);
        setClipToPadding(false);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }
    
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
    
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        if (hasFocus()) {
            System.out.println("HomeItemContainer focus : true ");
            super.getDrawingRect(mRect);
            mBound.set(mRect.left - SELECT_PADDING, mRect.top - SELECT_PADDING, mRect.right + SELECT_PADDING, mRect.bottom + SELECT_PADDING);
            mDrawable.setBounds(mBound);
            canvas.save();
            mDrawable.draw(canvas);
            canvas.restore();
        }
        super.onDraw(canvas);
    }
    
    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        Log.d("Fuck fosued", "onFocusChanged " + gainFocus);
        if (gainFocus) {
            bringToFront();
            zoomOut();
        }
        else {
            zoomIn();
        }
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        
    }
    
    private void zoomIn() {
        //缩小动画
        if (mAnimatorSetZoomIn == null) {
            mAnimatorSetZoomIn = new AnimatorSet();
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "scaleX", 1.2f, 1.0f);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "scaleY", 1.2f, 1.0f);
            animatorX.setDuration(300);
            animatorY.setDuration(300);
            mAnimatorSetZoomIn.playTogether(animatorX, animatorY);
        }
        mAnimatorSetZoomIn.start();
    }
    
    private void zoomOut() {
        //放大动画
        if (mAnimatorSetZoomOut == null) {
            mAnimatorSetZoomOut = new AnimatorSet();
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "scaleX", 1.0f, 1.2f);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "scaleY", 1.0f, 1.2f);
            animatorX.setDuration(300);
            animatorY.setDuration(300);
            mAnimatorSetZoomOut.playTogether(animatorX, animatorY);
        }
        mAnimatorSetZoomOut.start();
    }
}
