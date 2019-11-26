package com.sablasvegas.shadout

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.AttributeSet
import android.widget.FrameLayout
import com.sablasvegas.shadout.R

class Shadout : FrameLayout {

    private var mShadowColor = 0
    private var mShadowRadius = 0f
    private var mCornerRadius = 0f
    private var mDx = 0f
    private var mDy = 0f
    private val mInvalidateShadowOnSizeChanged = true
    private var mForceInvalidateShadow = false


    constructor(context: Context) : super(context) {
        initView(context,null)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context,attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    {
        initView(context, attrs)
    }



    override fun getSuggestedMinimumWidth(): Int {
        return 0
    }

    override fun getSuggestedMinimumHeight(): Int {
        return 0
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && h > 0 && (background == null || mInvalidateShadowOnSizeChanged || mForceInvalidateShadow)) {
            mForceInvalidateShadow = false
            setBackgroundCompat(w, h)
        }
    }

    override fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        super.onLayout(changed, left, top, right, bottom)
        if (mForceInvalidateShadow) {
            mForceInvalidateShadow = false
            setBackgroundCompat(right - left, bottom - top)
        }
    }

    private fun initView(
        context: Context,
        attrs: AttributeSet?
    ) {
        if (attrs != null) {
            initAttributes(context, attrs)
        }
        val xPadding = (mShadowRadius + Math.abs(mDx)).toInt()
        val yPadding = (mShadowRadius + Math.abs(mDy)).toInt()
        setPadding(xPadding, yPadding, xPadding, yPadding)
    }

    private fun setBackgroundCompat(
        w: Int,
        h: Int
    ) { if (isInEditMode) {
            setBackgroundColor(mShadowColor)
            return
        }
        val bitmap = createShadowBitmap(
            w,
            h,
            mCornerRadius,
            mShadowRadius,
            mDx,
            mDy,
            mShadowColor,
            Color.TRANSPARENT
        )
        val drawable = BitmapDrawable(resources, bitmap)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(drawable)
        } else {
            background = drawable
        }
    }


    private fun initAttributes(
        context: Context,
        attrs: AttributeSet
    ) {
        val attr = getTypedArray(context, attrs, R.styleable.Shadout) ?: return
        try {
            mCornerRadius = attr.getDimension(
                R.styleable.Shadout_cornerRadius,
                resources.getDimension(R.dimen.def_corner_radius)
            )
            mShadowRadius = attr.getDimension(
                R.styleable.Shadout_shadowRadius,
                resources.getDimension(R.dimen.def_shadow_radius)
            )
            mDx = attr.getDimension(R.styleable.Shadout_Dx, 0f)
            mDy = attr.getDimension(R.styleable.Shadout_Dy, 0f)
            mShadowColor = attr.getColor(
                R.styleable.Shadout_shadowColor,
                resources.getColor(R.color.color_blue_shadow)
            )
        } finally {
            attr.recycle()
        }
    }

    private fun getTypedArray(
        context: Context,
        attributeSet: AttributeSet,
        attr: IntArray
    ): TypedArray? {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0)
    }

    private fun createShadowBitmap(
        shadowWidth: Int, shadowHeight: Int, cornerRadius: Float, shadowRadius: Float,
        dx: Float, dy: Float, shadowColor: Int, fillColor: Int
    ): Bitmap {
        val output = Bitmap.createBitmap(shadowWidth, shadowHeight, Bitmap.Config.ARGB_4444)
        val canvas = Canvas(output)
        val shadowRect = RectF(
            shadowRadius,
            shadowRadius,
            shadowWidth - shadowRadius,
            shadowHeight - shadowRadius
        )
        if (dy > 0) {
            shadowRect.top += dy
            shadowRect.bottom -= dy
        } else if (dy < 0) {
            shadowRect.top += Math.abs(dy)
            shadowRect.bottom -= Math.abs(dy)
        }
        if (dx > 0) {
            shadowRect.left += dx
            shadowRect.right -= dx
        } else if (dx < 0) {
            shadowRect.left += Math.abs(dx)
            shadowRect.right -= Math.abs(dx)
        }
        val shadowPaint = Paint()
        shadowPaint.isAntiAlias = true
        shadowPaint.color = fillColor
        shadowPaint.style = Paint.Style.FILL
        if (!isInEditMode) {
            shadowPaint.setShadowLayer(shadowRadius, dx, dy, shadowColor)
        }
        canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint)
        return output
    }



}