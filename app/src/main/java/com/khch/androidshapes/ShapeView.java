package com.khch.androidshapes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by kuanghaochuan on 2016/1/14.
 */
public class ShapeView extends ImageView {
    private Bitmap mBitmap;
    private Context mContext;
    private int mShapeType;
    private TypedArray typedArray;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);
    }

    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    private void init(AttributeSet attrs, Context context) {
        mContext = context;

        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
        mBitmap = bitmapDrawable.getBitmap();

        try {
            typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.ShapeView);
            mShapeType = typedArray.getInt(R.styleable.ShapeView_shape_type, -1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = (ShapeFactory.getInstance().getBitmap(mBitmap, getWidth(), getHeight(), mShapeType));
        canvas.drawBitmap(bitmap, 0, 0, null);
    }
}
