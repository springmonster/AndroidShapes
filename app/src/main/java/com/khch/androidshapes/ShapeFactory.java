package com.khch.androidshapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/**
 * Created by kuanghaochuan on 2016/1/14.
 */
public class ShapeFactory {
    public static final int SHAPE_RECT_ROUND_CORNER = 0;
    public static final int SHAPE_CIRCLE = 1;
    public static final int SHAPE_SQUARE = 2;
    private static final int SHAPE_STAR = 3;

    private static ShapeFactory mShapeFactory;

    private Bitmap mCreatedBitmap = null;
    private Bitmap mOriginalBitmap = null;
    private Canvas mCanvas;
    private Paint mPaint;
    private int width;
    private int height;

    private ShapeFactory() {
    }

    public static ShapeFactory getInstance() {
        if (mShapeFactory == null) {
            mShapeFactory = new ShapeFactory();
        }
        return mShapeFactory;
    }

    public Bitmap getBitmap(Bitmap bitmap, int width, int height, int shapeType) {
        init(bitmap, width, height);

        switch (shapeType) {
            case SHAPE_RECT_ROUND_CORNER:
                return createRectRoundCorner();
            case SHAPE_CIRCLE:
                return createCircle();
            case SHAPE_SQUARE:
                return createSquare();
            case SHAPE_STAR:
                return createStar();
            default:
                return bitmap;
        }
    }

    private void init(Bitmap bitmap, int width, int height) {
        this.width = width;
        this.height = height;

        mOriginalBitmap = bitmap;
        mCreatedBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mCanvas = new Canvas(mCreatedBitmap);
    }

    private void drawBitmap() {
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mCanvas.drawBitmap(mOriginalBitmap, null, new RectF(0, 0, width, height), mPaint);
    }

    private Bitmap createCircle() {

        int radius = width < height ? width >> 1 : height >> 1;
        mCanvas.drawCircle(width >> 1, height >> 1, radius, mPaint);

        drawBitmap();

        return mCreatedBitmap;
    }

    private Bitmap createRectRoundCorner() {
        final int corner = 30;
        mCanvas.drawRoundRect(new RectF(0, 0, width, height), corner, corner, mPaint);

        drawBitmap();

        return mCreatedBitmap;
    }

    private Bitmap createSquare() {
        final int length = width < height ? width : height;
        mCanvas.drawRect(new RectF(0, 0, length, length), mPaint);

        drawBitmap();

        return mCreatedBitmap;
    }

    private Bitmap createStar() {
        Path path = new Path();
        path.moveTo(width >> 1, 0);
        path.lineTo(0, height);
        path.lineTo(width, height);
        path.close();

        mCanvas.drawPath(path, mPaint);

        drawBitmap();

        return mCreatedBitmap;
    }

}
