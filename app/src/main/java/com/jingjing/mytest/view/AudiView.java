package com.jingjing.mytest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: FangJing.
 * CreateTime:  2018/8/23 11:44
 * Email：smilefangjing@163.com.
 * Description:
 */
public class AudiView extends View {

    private Path mPath;
    private Paint mPaint;

    public AudiView(Context context) {
        this(context, null);
    }

    public AudiView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AudiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPath = new Path();
//        mPath.setFillType(Path.FillType.EVEN_ODD);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.offset(50, 50);
        for (int i = 0; i < 3; i++) {
            mPath.addCircle((i + 1) * 90, 40, 40, Path.Direction.CW);
            canvas.drawPath(mPath, mPaint);
        }
        mPath.addCircle(135, 80, 40, Path.Direction.CW);
        canvas.drawPath(mPath, mPaint);
        mPath.addCircle(225, 80, 40, Path.Direction.CW);
        canvas.drawPath(mPath, mPaint);
        RectF rectF = new RectF();
        mPath.computeBounds(rectF,false);
        canvas.drawPath(mPath,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF,mPaint);//绘制矩形 path所占的区域
    }
}
