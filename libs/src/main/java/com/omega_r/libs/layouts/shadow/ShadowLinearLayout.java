package com.omega_r.libs.layouts.shadow;

import android.content.Context;
import android.graphics.Canvas;
import androidx.annotation.ColorInt;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import android.util.AttributeSet;

public class ShadowLinearLayout extends LinearLayoutCompat {

    private ShadowLayoutHelper shadowLayoutHelper;

    public ShadowLinearLayout(Context context) {
        super(context);
        init(context, null);
    }

    public ShadowLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShadowLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        shadowLayoutHelper = new ShadowLayoutHelper(this, context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        shadowLayoutHelper.drawShadows(canvas);
    }

    public void setShadowColors(@ColorInt int startColor, @ColorInt int endColor) {
        shadowLayoutHelper.setShadowColors(startColor, endColor);
    }

    public void setShadowColors(@ColorInt int startColor, @ColorInt int centerColor, @ColorInt int endColor) {
        shadowLayoutHelper.setShadowColors(startColor, centerColor, endColor);
    }

    public void setShadowsHeightInPixels(int left, int top, int right, int bottom) {
        shadowLayoutHelper.setShadowsHeightInPixels(left, top, right, bottom);
    }

    public void setShadowsHeightDimens(@DimenRes int left, @DimenRes int top, @DimenRes int right, @DimenRes int bottom) {
        shadowLayoutHelper.setShadowsHeightDimens(left, top, right, bottom);
    }

    public void setLeftShadowInPixels(int size) {
        shadowLayoutHelper.setLeftShadowInPixels(size);
    }

    public void setLeftShadowDimens(@DimenRes int resource) {
        shadowLayoutHelper.setLeftShadowDimens(resource);
    }

    public void setRightShadowInPixels(int size) {
        shadowLayoutHelper.setRightShadowInPixels(size);
    }

    public void setRightShadowDimens(@DimenRes int resource) {
        shadowLayoutHelper.setRightShadowDimens(resource);
    }

    public void setTopShadowInPixels(int size) {
        shadowLayoutHelper.setTopShadowInPixels(size);
    }

    public void setTopShadowDimens(@DimenRes int resource) {
        shadowLayoutHelper.setTopShadowDimens(resource);
    }

    public void setBottomShadowInPixels(int size) {
        shadowLayoutHelper.setBottomShadowInPixels(size);
    }

    public void setBottomShadowDimens(@DimenRes int resource) {
        shadowLayoutHelper.setBottomShadowDimens(resource);
    }
}
