package com.lovely.pig.vector_drawable.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.AppCompatCheckedTextView
import android.util.AttributeSet
import com.lovely.pig.vector_drawable.R

/**
 * 作者 swg
 * 时间 2019/3/22 15:39
 * 文件 AndroidAnim
 * 描述
 */
class VectorTextView : AppCompatCheckedTextView {

    private var isTintDrawableInTextColor: Boolean = false
    private var mDrawableCompatColor: Int = 0
    private var isDrawableAdjustTextWidth: Boolean = false
    private var isDrawableAdjustTextHeight: Boolean = false
    private var isDrawableAdjustViewWidth: Boolean = false
    private var isDrawableAdjustViewHeight: Boolean = false
    private var mDrawableWidth: Int = 0
    private var mDrawableHeight: Int = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        initAttrs(context, attrs)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.VectorCompatTextView)
            var dl: Drawable? = null
            var dt: Drawable? = null
            var dr: Drawable? = null
            var db: Drawable? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dl = a.getDrawable(R.styleable.VectorCompatTextView_drawableLeftCompat)
                dt = a.getDrawable(R.styleable.VectorCompatTextView_drawableTopCompat)
                dr = a.getDrawable(R.styleable.VectorCompatTextView_drawableRightCompat)
                db = a.getDrawable(R.styleable.VectorCompatTextView_drawableBottomCompat)
            } else {
                val dlId = a.getResourceId(R.styleable.VectorCompatTextView_drawableLeftCompat, -1)
                val dtId = a.getResourceId(R.styleable.VectorCompatTextView_drawableTopCompat, -1)
                val drId = a.getResourceId(R.styleable.VectorCompatTextView_drawableRightCompat, -1)
                val dbId = a.getResourceId(R.styleable.VectorCompatTextView_drawableBottomCompat, -1)

                if (dlId != -1) {
                    dl = AppCompatResources.getDrawable(context, dlId)
                }
                if (dtId != -1) {
                    dt = AppCompatResources.getDrawable(context, dtId)
                }
                if (drId != -1) {
                    dr = AppCompatResources.getDrawable(context, drId)
                }
                if (dbId != -1) {
                    db = AppCompatResources.getDrawable(context, dbId)
                }
            }

            isTintDrawableInTextColor = a.getBoolean(R.styleable.VectorCompatTextView_tintDrawableInTextColor, false)
            mDrawableCompatColor = a.getColor(R.styleable.VectorCompatTextView_drawableCompatColor, 0)
            isDrawableAdjustTextWidth = a.getBoolean(R.styleable.VectorCompatTextView_drawableAdjustTextWidth, false)
            isDrawableAdjustTextHeight = a.getBoolean(R.styleable.VectorCompatTextView_drawableAdjustTextHeight, false)
            isDrawableAdjustViewWidth = a.getBoolean(R.styleable.VectorCompatTextView_drawableAdjustViewWidth, false)
            isDrawableAdjustViewHeight = a.getBoolean(R.styleable.VectorCompatTextView_drawableAdjustViewHeight, false)
            mDrawableWidth = a.getDimensionPixelSize(R.styleable.VectorCompatTextView_drawableWidth, 0)
            mDrawableHeight = a.getDimensionPixelSize(R.styleable.VectorCompatTextView_drawableHeight, 0)
            a.recycle()

            if (mDrawableWidth < 0) {
                mDrawableWidth = 0
            }
            if (mDrawableHeight < 0) {
                mDrawableHeight = 0
            }
            if (isDrawableAdjustTextWidth) {
                isDrawableAdjustViewWidth = false
            }
            if (isDrawableAdjustTextHeight) {
                isDrawableAdjustViewHeight = false
            }

            initDrawable(dl, dt, dr, db)
        }
    }

    private fun initDrawable(vararg drawables: Drawable?) {
        for (drawable in drawables) {
            tintDrawable(drawable)
        }

        if (!isDrawableAdjustTextWidth
                && !isDrawableAdjustTextHeight
                && !isDrawableAdjustViewWidth
                && !isDrawableAdjustViewHeight
                && mDrawableWidth == 0
                && mDrawableHeight == 0) {
            setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3])
        } else {
            if (isDrawableAdjustTextWidth
                    || isDrawableAdjustTextHeight
                    || isDrawableAdjustViewWidth
                    || isDrawableAdjustViewHeight) {
                val invalid = ((isDrawableAdjustTextWidth
                        || isDrawableAdjustViewWidth)
                        && (drawables[0] != null
                        || drawables[2] != null))
                        || ((isDrawableAdjustTextHeight
                        || isDrawableAdjustViewHeight)
                        && (drawables[1] != null
                        || drawables[3] != null))
                if (invalid) {
                    if (mDrawableWidth > 0 || mDrawableHeight > 0) {
                        resizeDrawable(drawables[0], drawables[1], drawables[2], drawables[3])
                    } else {
                        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3])
                    }
                }
            }
        }
    }

    private fun resizeDrawable(vararg drawables: Drawable?) {

    }

    private fun tintDrawable(drawable: Drawable?) {
        if (drawable != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                DrawableCompat.wrap(drawable).mutate()
            }
            if (isTintDrawableInTextColor) {
                DrawableCompat.setTint(drawable, currentTextColor)
            } else if (mDrawableCompatColor != 0) {
                DrawableCompat.setTint(drawable, mDrawableCompatColor)
            }
        }
    }

}