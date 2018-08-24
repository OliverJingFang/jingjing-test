package com.jingjing.mytest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingjing.mytest.R;

/**
 * Author: KindyFung.
 * CreateTime:  2018/8/12 14:23
 * Email：smilefangjing@163.com.
 * Description:带有名字的ImaegView
 */
public class ArrowPathView extends View {

    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作
    private int width,height;
    private Paint paint;
    private Path path;
    private float percentLength;//path长度的百分比

    public ArrowPathView(Context context) {
        this(context,null);
    }

    public ArrowPathView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ArrowPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_arrow, options);
        mMatrix = new Matrix();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width / 2, height / 2);
        path.reset();
        path.addCircle(0,0,100, Path.Direction.CW);
        paint.setColor(Color.WHITE);
        canvas.drawPath(path, paint);
        PathMeasure pathMeasure = new PathMeasure(path, false);
        percentLength += 0.01;
        if (percentLength >= 1) {
            percentLength = 0;
        }
        pathMeasure.getPosTan(pathMeasure.getLength() * percentLength, pos, tan);
        mMatrix.reset();
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);
        canvas.drawBitmap(mBitmap,mMatrix,paint);
        //绘制view的原点坐标
        paint.setColor(Color.RED);
        canvas.drawPoint(0,0,paint);
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }
}
