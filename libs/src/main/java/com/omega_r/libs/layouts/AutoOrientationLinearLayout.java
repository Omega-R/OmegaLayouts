package com.omega_r.libs.layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class AutoOrientationLinearLayout extends LinearLayout {

    public AutoOrientationLinearLayout(Context context) {
        super(context);
    }

    public AutoOrientationLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoOrientationLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth = getPaddingLeft() + getPaddingRight();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int measuredChildWidth = child.getMeasuredWidth();
            maxWidth += measuredChildWidth;
        }

        if (getOrientation() == HORIZONTAL) {
            if (maxWidth >= MeasureSpec.getSize(widthMeasureSpec)) {
                setOrientation(VERTICAL);
            }
        } else {
            if (maxWidth < MeasureSpec.getSize(widthMeasureSpec)) {
                setOrientation(HORIZONTAL);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
