package com.jingjing.mytest.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Author: FangJing.
 * CreateTime:  2018/8/27 11:27
 * Email：smilefangjing@163.com.
 * Description:
 */
public class PathMeaView1 extends View {
    private static final String TAG = "PathMeaView1";
    private Path path;
    private Paint paint;
    private int width;
    private int height;
    private float startD = (float) (2 * Math.PI * 100 / 1000);
    private float stopD = (float) (2 * Math.PI * 100 / 100);
    private float[] pos;
    private float[] tan;
    private float percentLength;//path长度的百分比

    public PathMeaView1(Context context) {
        this(context, null);
    }

    public PathMeaView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        pos = new float[2];
        tan = new float[2];
        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width / 2, height / 2);
        path.reset();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        PathMeasure measure = new PathMeasure(path, false);
        percentLength += 0.005;
        if (percentLength >= 1) {
            percentLength = 0;
        }
        Log.i(TAG,"percentLength = "+percentLength);
        measure.getPosTan(measure.getLength() * percentLength, pos, tan);
        paint.setColor(Color.parseColor("#218868"));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(4);
        canvas.drawCircle(pos[0],pos[1],8,paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        canvas.drawPath(path, paint);
        invalidate();
    }
}