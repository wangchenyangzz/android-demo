package cn.yy.demo.view.gift

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.roundToInt

/**
 *    author : cy.wang
 *    date   : 12/2/20
 *    desc   :
 */
class AvatarSelectView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint()

    private var animator: ObjectAnimator? = null

    private val rect = RectF()
    private val selectRect = RectF()
    private val textRect = Rect()

    private val leftTitle = "自定义头像"
    private val rightTitle = "3d头像"
    private var useCustomAvatar = false
        set(value) {
            field = value
            startAnimal(value)
        }

    private var horMargin = 0f
        set(value) {
            field = value
            invalidate()
        }

    var clickListener: ((Boolean) -> Unit)? = null

    init {
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = context.dip(12)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val dip1_2 = context.dip(1).toFloat() / 2
        rect.set(
            dip1_2,
            dip1_2,
            width.toFloat() - dip1_2,
            height.toFloat() - dip1_2

        )
        textPaint.getTextBounds(leftTitle, 0, leftTitle.length, textRect)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val radius = context.dip(17)
        val dip1 = context.dip(1)
        paint.color = Color.parseColor("#33ffffff")
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = dip1
        canvas?.drawRoundRect(rect, radius, radius, paint)
        if (useCustomAvatar) {
            selectRect.set(
                dip1 + horMargin,
                dip1,
                width / 2 + horMargin - dip1,
                height - dip1
            )

            textPaint.color = Color.WHITE
            canvas?.drawText(
                leftTitle,
                (width / 4).toFloat(),
                ((textRect.top + textRect.bottom) / 2).toFloat() + (height - context.dip(8)),
                textPaint
            )
            textPaint.color = Color.parseColor("#ccffffff")
            canvas?.drawText(
                rightTitle,
                (width / 4 * 3).toFloat(),
                ((textRect.top + textRect.bottom) / 2).toFloat() + (height - context.dip(8)),
                textPaint
            )
        } else {
            selectRect.set(
                width / 2 - horMargin + dip1,
                dip1,
                width - horMargin - dip1,
                height - dip1
            )
            textPaint.color = Color.parseColor("#ccffffff")
            canvas?.drawText(
                leftTitle,
                (width / 4).toFloat(),
                ((textRect.top + textRect.bottom) / 2).toFloat() + (height - context.dip(8)),
                textPaint
            )
            textPaint.color = Color.WHITE
            canvas?.drawText(
                rightTitle,
                (width / 4 * 3).toFloat(),
                ((textRect.top + textRect.bottom) / 2).toFloat() + (height - context.dip(8)),
                textPaint
            )
        }
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 0f
        canvas?.drawRoundRect(selectRect, radius, radius, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val isCustomAvatar = clickCustomAvatar(event)
            if (useCustomAvatar != isCustomAvatar) {
                clickListener?.invoke(isCustomAvatar)
                useCustomAvatar = isCustomAvatar
            }
            return true
        }
        return super.onTouchEvent(event)
    }

    private fun clickCustomAvatar(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        val left = 0
        val right = width / 2
        val top = 0
        val bottom = height
        //view.isClickable() &&
        return y >= top && y <= bottom && x >= left && x <= right
    }

    private fun startAnimal(value: Boolean) {
        animator?.cancel()
        animator = ObjectAnimator.ofFloat(this, "horMargin", (width / 2).toFloat(), 0f).apply {
            duration = 200
            start()
        }
    }

    private fun Context.dip(value: Int): Float {
        return (resources.displayMetrics.density * value).roundToInt().toFloat()
    }
}