package com.omega_r.libs.layouts.shadow;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import com.omega_r.libs.layouts.R;

public class ShadowLayoutHelper {

    private final Resources resources;
    private final DisplayMetrics displayMetrics;
    private final ViewGroup viewGroup;
    @ColorInt
    private final int defaultShadowColor;

    private Drawable shadowTopDrawable;
    private int shadowTopHeight;
    private Drawable shadowBottomDrawable;
    private int shadowBottomHeight;
    private Drawable shadowLeftDrawable;
    private int shadowLeftHeight;
    private Drawable shadowRightDrawable;
    private int shadowRightHeight;

    public ShadowLayoutHelper(@NonNull ViewGroup viewGroup, @NonNull Context context, @Nullable AttributeSet attrs) {
        this.viewGroup = viewGroup;
        resources = context.getResources();
        displayMetrics = resources.getDisplayMetrics();
        defaultShadowColor = ContextCompat.getColor(context, R.color.shadow_color_omega);
        if (attrs == null) {
            initDrawables(new int[]{defaultShadowColor, Color.TRANSPARENT});
        } else {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.ShadowLayout);
            initHeights(styledAttrs);
            initDrawables(styledAttrs);
            styledAttrs.recycle();
        }
    }

    private void initHeights(@NonNull TypedArray styledAttrs) {
        shadowTopHeight = styledAttrs.getDimensionPixelSize(R.styleable.ShadowLayout_shadowTopHeight, 0);
        shadowBottomHeight = styledAttrs.getDimensionPixelSize(R.styleable.ShadowLayout_shadowBottomHeight, 0);
        shadowLeftHeight = styledAttrs.getDimensionPixelSize(R.styleable.ShadowLayout_shadowLeftHeight, 0);
        shadowRightHeight = styledAttrs.getDimensionPixelSize(R.styleable.ShadowLayout_shadowRightHeight, 0);
    }

    private void initDrawables(@NonNull TypedArray styledAttrs) {
        int startColor = styledAttrs.getColor(R.styleable.ShadowLayout_startColor, defaultShadowColor);
        int endColor = styledAttrs.getColor(R.styleable.ShadowLayout_endColor, Color.TRANSPARENT);

        int[] colors = new int[] {startColor, endColor};
        if (styledAttrs.hasValue(R.styleable.ShadowLayout_centerColor)) {
            int centerColor = styledAttrs.getColor(R.styleable.ShadowLayout_centerColor, Color.TRANSPARENT);
            colors = new int[] {startColor, centerColor, endColor};
        }
        initDrawables(colors);
    }

    private void initDrawables(int[] colors) {
        shadowTopDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        shadowBottomDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors);
        shadowLeftDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
        shadowRightDrawable = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors);
    }

    public void setShadowColors(@ColorInt int startColor,
                                @ColorInt int endColor) {
        initDrawables(new int[] {startColor, endColor});
    }

    public void setShadowColors(@ColorInt int startColor,
                                @ColorInt int centerColor,
                                @ColorInt int endColor) {
        initDrawables(new int[] {startColor, centerColor, endColor});
    }

    public void drawShadows(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        shadowTopDrawable.setBounds(0, 0, width, shadowTopHeight);
        shadowTopDrawable.draw(canvas);
        shadowBottomDrawable.setBounds(0, height - shadowBottomHeight, width, height);
        shadowBottomDrawable.draw(canvas);
        shadowLeftDrawable.setBounds(0, 0, shadowLeftHeight, height);
        shadowLeftDrawable.draw(canvas);
        shadowRightDrawable.setBounds(width - shadowRightHeight, 0, width, height);
        shadowRightDrawable.draw(canvas);
    }

    public void setShadowsHeightInPixels(int left, int top, int right, int bottom) {
        shadowLeftHeight = convertPixelsToDp(left);
        shadowTopHeight = convertPixelsToDp(top);
        shadowRightHeight = convertPixelsToDp(right);
        shadowBottomHeight = convertPixelsToDp(bottom);
        viewGroup.invalidate();
    }

    public void setShadowsHeightDimens(@DimenRes int left, @DimenRes int top, @DimenRes int right, @DimenRes int bottom) {
        shadowLeftHeight = resources.getDimensionPixelSize(left);
        shadowTopHeight = resources.getDimensionPixelSize(top);
        shadowRightHeight = resources.getDimensionPixelSize(right);
        shadowBottomHeight = resources.getDimensionPixelSize(bottom);
        viewGroup.invalidate();
    }

    public void setLeftShadowInPixels(int size) {
        shadowLeftHeight = convertPixelsToDp(size);
        viewGroup.invalidate();
    }

    public void setLeftShadowDimens(@DimenRes int resource) {
        shadowLeftHeight = resources.getDimensionPixelSize(resource);
        viewGroup.invalidate();
    }

    public void setRightShadowInPixels(int size) {
        shadowRightHeight = convertPixelsToDp(size);
        viewGroup.invalidate();
    }

    public void setRightShadowDimens(@DimenRes int resource) {
        shadowRightHeight = resources.getDimensionPixelSize(resource);
        viewGroup.invalidate();
    }

    public void setTopShadowInPixels(int size) {
        shadowTopHeight = convertPixelsToDp(size);
        viewGroup.invalidate();
    }

    public void setTopShadowDimens(@DimenRes int resource) {
        shadowTopHeight = resources.getDimensionPixelSize(resource);
        viewGroup.invalidate();
    }

    public void setBottomShadowInPixels(int size) {
        shadowBottomHeight = convertPixelsToDp(size);
        viewGroup.invalidate();
    }

    public void setBottomShadowDimens(@DimenRes int resource) {
        shadowBottomHeight = resources.getDimensionPixelOffset(resource);
        viewGroup.invalidate();
    }

    private int convertPixelsToDp(int px) {
        return px / (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private int convertDpToPixel(int dp) {
        return dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
