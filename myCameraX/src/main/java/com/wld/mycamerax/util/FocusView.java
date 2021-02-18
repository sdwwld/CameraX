package com.wld.mycamerax.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class FocusView extends View {

    private int focusSize;//焦点框的大小
    private int focusColor;//焦点框的颜色
    private int focusTime;//焦点框显示的时长
    private int focusStrokeSize;//焦点框线条的尺寸
    private int cornerSize;//焦点框圆角尺寸
    private Handler handler;
    private Runnable runnable;
    private Paint mPaint;
    private RectF rect;

    public FocusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        handler = new Handler();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rect = new RectF();
        runnable = () -> {
            hideFocusView();
        };
    }

    public void setParam(int focusViewSize, int focusViewColor, int focusViewTime,
                         int focusViewStrokeSize, int cornerViewSize) {
        if (focusViewSize == -1) {
            this.focusSize = Tools.dp2px(getContext(), 60);
        } else {
            this.focusSize = focusViewSize;
        }

        if (focusViewColor == -1) {
            this.focusColor = Color.GREEN;
        } else {
            this.focusColor = focusViewColor;
        }
        this.focusTime = focusViewTime;

        if (focusViewStrokeSize == -1) {
            this.focusStrokeSize = Tools.dp2px(getContext(), 2);
        } else {
            this.focusStrokeSize = focusViewStrokeSize;
        }

        if (cornerViewSize == -1) {
            this.cornerSize = focusSize / 5;
        } else {
            this.cornerSize = cornerViewSize;
        }

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(focusStrokeSize);
        mPaint.setColor(focusColor);
        rect.left = rect.top = 0;
        rect.right = rect.bottom = this.focusSize;
    }

    public void showFocusView(int x, int y) {
        setVisibility(VISIBLE);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.leftMargin = x - focusSize / 2;
        layoutParams.topMargin = y - focusSize / 2;
        setLayoutParams(layoutParams);
        invalidate();
        handler.postDelayed(runnable, focusTime * 1000);
    }

    public void hideFocusView() {
        setVisibility(GONE);
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(focusSize, focusSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(rect, cornerSize, cornerSize, mPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
        super.onDetachedFromWindow();
    }
}
