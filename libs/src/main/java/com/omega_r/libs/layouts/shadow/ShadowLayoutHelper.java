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

class ShadowLayoutHelper {

    private static final int ARRAYS_LENGTH = 4;
    private static final int LEFT_POSITION = 0;
    private static final int TOP_POSITION = 1;
    private static final int RIGHT_POSITION = 2;
    private static final int BOTTOM_POSITION = 3;
    private final Drawable[] drawables = new Drawable[ARRAYS_LENGTH];
    private final int[] sizeArray = new int[ARRAYS_LENGTH];

    private final Resources resources;
    private final DisplayMetrics displayMetrics;
    private final ViewGroup viewGroup;
    @ColorInt
    private final int defaultShadowColor;

    ShadowLayoutHelper(@NonNull ViewGroup viewGroup, @NonNull Context context, @Nullable AttributeSet attrs) {
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
        sizeArray[LEFT_POSITION] = styledAttrs.getDimensionPixelSize(R.styleable.ShadowLayout_shadowLeftHeight, 0);
        sizeArray[TOP_POSITION] = styledAttrs.getDimensionPixelSize(R.styleable.ShadowLayout_shadowTopHeight, 0);
        sizeArray[RIGHT_POSITION] = styledAttrs.getDimensionPixelSize(R.styleable.ShadowLayout_shadowRightHeight, 0);
        sizeArray[BOTTOM_POSITION] = styledAttrs.getDimensionPixelSize(R.styleable.ShadowLayout_shadowBottomHeight, 0);
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
        drawables[LEFT_POSITION] = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
        drawables[TOP_POSITION] = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        drawables[RIGHT_POSITION] = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors);
        drawables[BOTTOM_POSITION] = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors);
    }

    void setShadowColors(@ColorInt int startColor, @ColorInt int endColor) {
        initDrawables(new int[] {startColor, endColor});
    }

    void setShadowColors(@ColorInt int startColor, @ColorInt int centerColor, @ColorInt int endColor) {
        initDrawables(new int[] {startColor, centerColor, endColor});
    }

    void drawShadows(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        Drawable leftDrawable = drawables[LEFT_POSITION];
        leftDrawable.setBounds(0, 0, sizeArray[LEFT_POSITION], height);
        leftDrawable.draw(canvas);

        Drawable topDrawable = drawables[TOP_POSITION];
        topDrawable.setBounds(0, 0, width, sizeArray[TOP_POSITION]);
        topDrawable.draw(canvas);

        Drawable rightDrawable = drawables[RIGHT_POSITION];
        rightDrawable.setBounds(width - sizeArray[RIGHT_POSITION], 0, width, height);
        rightDrawable.draw(canvas);

        Drawable bottomDrawable = drawables[BOTTOM_POSITION];
        bottomDrawable.setBounds(0, height - sizeArray[BOTTOM_POSITION], width, height);
        bottomDrawable.draw(canvas);
    }

    void setShadowsHeightInPixels(int left, int top, int right, int bottom) {
        sizeArray[LEFT_POSITION] = convertPixelsToDp(left);
        sizeArray[TOP_POSITION] = convertPixelsToDp(top);
        sizeArray[RIGHT_POSITION] = convertPixelsToDp(right);
        sizeArray[BOTTOM_POSITION] = convertPixelsToDp(bottom);
        viewGroup.invalidate();
    }

    void setShadowsHeightDimens(@DimenRes int left, @DimenRes int top, @DimenRes int right, @DimenRes int bottom) {
        sizeArray[LEFT_POSITION] = resources.getDimensionPixelSize(left);
        sizeArray[TOP_POSITION] = resources.getDimensionPixelSize(top);
        sizeArray[RIGHT_POSITION] = resources.getDimensionPixelSize(right);
        sizeArray[BOTTOM_POSITION] = resources.getDimensionPixelSize(bottom);
        viewGroup.invalidate();
    }

    void setLeftShadowInPixels(int size) {
        updateShadowSize(LEFT_POSITION, convertPixelsToDp(size));
    }

    void setLeftShadowDimens(@DimenRes int resource) {
        updateShadowSize(LEFT_POSITION, resources.getDimensionPixelSize(resource));
    }

    void setRightShadowInPixels(int size) {
        updateShadowSize(RIGHT_POSITION, convertPixelsToDp(size));
    }

    void setRightShadowDimens(@DimenRes int resource) {
        updateShadowSize(RIGHT_POSITION, resources.getDimensionPixelSize(resource));
    }

    void setTopShadowInPixels(int size) {
        updateShadowSize(TOP_POSITION, convertPixelsToDp(size));
    }

    void setTopShadowDimens(@DimenRes int resource) {
        updateShadowSize(TOP_POSITION, resources.getDimensionPixelSize(resource));
    }

    void setBottomShadowInPixels(int size) {
        updateShadowSize(BOTTOM_POSITION, convertPixelsToDp(size));
    }

    void setBottomShadowDimens(@DimenRes int resource) {
        updateShadowSize(BOTTOM_POSITION, resources.getDimensionPixelSize(resource));
    }

    private void updateShadowSize(int position, int newSize) {
        int currentSize = sizeArray[position];
        if (currentSize != newSize) {
            sizeArray[position] = newSize;
            viewGroup.invalidate();
        }
    }

    private int convertPixelsToDp(int px) {
        return px / (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private int convertDpToPixel(int dp) {
        return dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
