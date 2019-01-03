package com.khch.androidshapes

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * Created by kuanghaochuan on 2016/1/14.
 */
class ShapeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    AppCompatImageView(context, attrs) {
    lateinit var mBitmap: Bitmap
    private var mContext: Context? = null
    private var mShapeType: Int = 0
    private var typedArray: TypedArray? = null

    init {
        init(attrs, context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : this(context, attrs) {}

    private fun init(attrs: AttributeSet?, context: Context) {
        mContext = context

        val bitmapDrawable = drawable as BitmapDrawable
        mBitmap = bitmapDrawable.bitmap

        try {
            typedArray = mContext!!.obtainStyledAttributes(attrs, R.styleable.ShapeView)
            mShapeType = typedArray!!.getInt(R.styleable.ShapeView_shape_type, -1)
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            typedArray!!.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        val bitmap = ShapeFactory.getInstance().getBitmap(mBitmap, width, height, mShapeType)
        canvas.drawBitmap(bitmap!!, 0f, 0f, null)
    }
}
