package com.khch.androidshapes

import android.graphics.*

/**
 * Created by kuanghaochuan on 2016/1/14.
 */
class ShapeFactory private constructor() {

    private var mCreatedBitmap: Bitmap? = null
    private var mOriginalBitmap: Bitmap? = null
    private var mCanvas: Canvas? = null
    private var mPaint: Paint? = null
    private var width: Int = 0
    private var height: Int = 0

    fun getBitmap(bitmap: Bitmap, width: Int, height: Int, shapeType: Int): Bitmap? {
        init(bitmap, width, height)

        when (shapeType) {
            SHAPE_RECT_ROUND_CORNER -> return createRectRoundCorner()
            SHAPE_CIRCLE -> return createCircle()
            SHAPE_SQUARE -> return createSquare()
            SHAPE_STAR -> return createStar()
            else -> return bitmap
        }
    }

    private fun init(bitmap: Bitmap, width: Int, height: Int) {
        this.width = width
        this.height = height

        mOriginalBitmap = bitmap
        mCreatedBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)

        mPaint = Paint()
        mPaint!!.isAntiAlias = true

        mCanvas = Canvas(mCreatedBitmap!!)
    }

    private fun drawBitmap() {
        mPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        mCanvas!!.drawBitmap(mOriginalBitmap!!, null, RectF(0f, 0f, width.toFloat(), height.toFloat()), mPaint)
    }

    private fun createCircle(): Bitmap? {

        val radius = if (width < height) width shr 1 else height shr 1
        mCanvas!!.drawCircle((width shr 1).toFloat(), (height shr 1).toFloat(), radius.toFloat(), mPaint!!)

        drawBitmap()

        return mCreatedBitmap
    }

    private fun createRectRoundCorner(): Bitmap? {
        val corner = 30
        mCanvas!!.drawRoundRect(
            RectF(0f, 0f, width.toFloat(), height.toFloat()),
            corner.toFloat(),
            corner.toFloat(),
            mPaint!!
        )

        drawBitmap()

        return mCreatedBitmap
    }

    private fun createSquare(): Bitmap? {
        val length = if (width < height) width else height
        mCanvas!!.drawRect(RectF(0f, 0f, length.toFloat(), length.toFloat()), mPaint!!)

        drawBitmap()

        return mCreatedBitmap
    }

    private fun createStar(): Bitmap? {
        val path = Path()
        path.moveTo((width shr 1).toFloat(), 0f)
        path.lineTo(0f, height.toFloat())
        path.lineTo(width.toFloat(), height.toFloat())
        path.close()

        mCanvas!!.drawPath(path, mPaint!!)

        drawBitmap()

        return mCreatedBitmap
    }

    companion object {
        val SHAPE_RECT_ROUND_CORNER = 0
        val SHAPE_CIRCLE = 1
        val SHAPE_SQUARE = 2
        private val SHAPE_STAR = 3

        private var mShapeFactory: ShapeFactory? = null

        fun getInstance(): ShapeFactory {
            return mShapeFactory ?: synchronized(this) {
                mShapeFactory ?: ShapeFactory().also {
                    mShapeFactory = it
                }
            }
        }
    }
}
