package com.su.mac.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by mac on 2018/5/24.
 */

public class MyView extends View {

    private Paint mPaint;
    private Paint mPaint2;
    private int circleWidth;
    private int circleHight;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        circleWidth = typedArray.getInt(R.styleable.MyView_circleWidth, 0);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);//控件
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);//控件
        circleWidth = MeasureSpec.getSize(widthMeasureSpec);
        circleHight = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {//wrap_content
            circleWidth = 100;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            circleHight = 100;
        }
        setMeasuredDimension(circleWidth, circleHight);
    }


    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(50);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.GRAY);
        mPaint2.setStrokeWidth(50);
    }

    private boolean isFirshEnter = true;
    private int number = 1;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0, 0, circleWidth, circleWidth);
//        canvas.drawRect(rectF, mPaint);
        canvas.drawArc(rectF, -90, 360, true, mPaint2);
        canvas.drawArc(rectF, -90, number, true, mPaint);
        number++;
        if (isFirshEnter) {
            isFirshEnter = false;
        } else {
            if (number < 300) {
                invalidate();
            }
        }

//        canvas.save();
    }

    public void startUpdate() {
        number = 1;
        invalidate();
    }
}
