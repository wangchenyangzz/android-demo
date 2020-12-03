package cn.yy.demo.view.practice

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import cn.yy.demo.R
import kotlin.math.roundToInt

/**
 *    author : cy.wang
 *    date   : 12/2/20
 *    desc   :
 */
class ThreeDimensionalView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)
    private val camera = Camera()
    private val rectF = RectF()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var clipRotate = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var clip = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        camera.setLocation(0f, 0f, -8f * resources.displayMetrics.density)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(0f, 0f, width.toFloat(), height.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()
        canvas?.translate((bitmap.width / 2).toFloat() + 300, (bitmap.height / 2).toFloat() + 300)
        canvas?.rotate(-clip)
        canvas?.clipRect(-width, -height, width, 0)
        canvas?.rotate(clip)
        canvas?.translate(-(bitmap.width / 2).toFloat() - 300, -(bitmap.height / 2).toFloat() - 300)
        canvas?.drawBitmap(bitmap, 300f, 300f, paint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate((bitmap.width / 2).toFloat() + 300, (bitmap.height / 2).toFloat() + 300)
        canvas?.rotate(-clip)
        camera.save()
        camera.rotateX(clipRotate)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas?.clipRect(-width, 0, width, height)
        canvas?.rotate(clip)
        canvas?.translate(-(bitmap.width / 2).toFloat() - 300, -(bitmap.height / 2).toFloat() - 300)
        canvas?.drawBitmap(bitmap, 300f, 300f, paint)
        canvas?.restore()
    }

    private fun Int.dip(): Float {
        return (resources.displayMetrics.density * this).roundToInt().toFloat()
    }
}