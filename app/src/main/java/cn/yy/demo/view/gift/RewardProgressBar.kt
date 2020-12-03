package cn.yy.demo.view.gift

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.LruCache
import android.view.MotionEvent
import android.view.View
import androidx.annotation.DrawableRes
import kotlin.math.roundToInt


/**
 *    author : cy.wang
 *    date   : 11/24/20
 *    desc   : 奖励进度条
 */
class RewardProgressBar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {
    companion object {
        private const val REWARD_SIZE = 10
    }

    private var mPinkToViolet: LinearGradient? = null
    private var mChangedStep = false

    private val mRewardList = mutableListOf<Reward>()
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mStep = 6

    private var lruCache = LruCache<Int, Bitmap>(6)

    var receiveClick: ((Reward) -> Unit)? = null

    fun setStep(step: Int) {
        if (mStep != step) {
            mStep = step
            mChangedStep = true
        }
    }

    fun update(list: List<Reward>) {
        mRewardList.clear()
        mRewardList.addAll(list)
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val num = findRewardLocation(event)
            if (num > -1) {
                mRewardList[num]?.takeIf { it.resId != null && it.hadGet == 1 }?.also { reward ->
                    receiveClick?.invoke(reward)
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val every = (width - 32.dip()) / (mRewardList.size - 1)

        if (mPinkToViolet == null || mChangedStep) {
            mPinkToViolet = LinearGradient(
                16.dip(), 14.dip(), 16.dip() + every * (mStep - 1), 18.dip(),
                Color.parseColor("#FFA6EF"),
                Color.parseColor("#6449B7"),
                Shader.TileMode.MIRROR
            )
            mChangedStep = false
        }

        mPaint.style = Paint.Style.FILL
        mPaint.shader = mPinkToViolet
        if (mStep > 1) {
            canvas?.drawRect(16.dip(), 14.dip(), 16.dip() + every * (mStep - 1), 18.dip(), mPaint)
        }
        mPaint.shader = null
        mPaint.color = Color.parseColor("#36326B")
        if (mStep < 9) {
            canvas?.drawRect(
                16.dip() + every * (mStep - 1),
                14.dip(),
                16.dip() + every * 9,
                18.dip(),
                mPaint
            )
        }

        for ((i, reward) in mRewardList.withIndex()) {
            reward.resId?.also { id ->
                canvas?.drawBitmap(getBitMap(id), every * i, 0f, mPaint)
            } ?: {
                mPaint.style = Paint.Style.FILL
                when (reward.hadGet) {
                    0 -> {
                        mPaint.color = Color.parseColor("#615E94")
                    }
                    1 -> {
                        mPaint.color = Color.WHITE
                    }
                }
                canvas?.drawCircle(i * every + 16.dip(), 16.dip(), 4.dip(), mPaint)
            }()
            mPaint.color = Color.WHITE
            mPaint.textSize = 12.dip()
            mPaint.textAlign = Paint.Align.CENTER;
            canvas?.drawText((i + 1).toString(), i * every + 16.dip(), 40.dip(), mPaint)
        }
    }

    private fun getBitMap(@DrawableRes resId: Int): Bitmap {
        return if (lruCache[resId] != null) {
            lruCache[resId]
        } else {
            val bitmap = BitmapFactory.decodeResource(resources, resId)
            lruCache.put(resId, bitmap)
            bitmap
        }
    }

    private fun findRewardLocation(event: MotionEvent): Int {
        val every = (width - 32.dip()) / (mRewardList.size - 1)
        val x = event.x
        val y = event.y
        if (y < 0.dip() || y > 32.dip()) return -1
        val loc = x % every
        if (loc in 0.dip()..32.dip()) {
            return (x / every).toInt()
        }
        return -1
    }

    private fun Int.dip(): Float {
        return (resources.displayMetrics.density * this).roundToInt().toFloat()
    }

    /**
     * @param hadGet 0- 未得到 1- 得到未打开 2-得到并打开
     * @param resId 资源id
     * 若资源id为null,则为空奖励
     */
    data class Reward(var hadGet: Int, @DrawableRes var resId: Int? = null)
}