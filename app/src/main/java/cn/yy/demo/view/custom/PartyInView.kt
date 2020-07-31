package cn.yy.demo.view.custom

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.Animatable
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import androidx.constraintlayout.widget.ConstraintLayout
import cn.yy.demo.R
import kotlinx.android.synthetic.main.in_party_view.view.*

/**
 *    author : cy.wang
 *    date   : 2020/7/17
 *    desc   :
 */
class PartyInView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr), Animatable {
    private lateinit var drawable: AudioDrawable
    private var mObjectAnimator: ObjectAnimator? = null

    init {
        View.inflate(context, R.layout.in_party_view, this)
        drawable = AudioDrawable()
        imageView?.setImageDrawable(drawable)
    }

    override fun start() {
        drawable.start()
        val p1 = PropertyValuesHolder.ofKeyframe(
            "scaleX",
            Keyframe.ofFloat(0f, 0.9f),
            Keyframe.ofFloat(0.5f, 1.0f),
            Keyframe.ofFloat(1f, 0.9f)
        )
        val p2 = PropertyValuesHolder.ofKeyframe(
            "scaleY",
            Keyframe.ofFloat(0f, 0.9f),
            Keyframe.ofFloat(0.5f, 1.0f),
            Keyframe.ofFloat(1f, 0.9f)
        )
        mObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, p1, p2).apply {
            duration = 1200L
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            start()
        }
    }

    override fun stop() {
        drawable.stop()
        mObjectAnimator?.cancel()
        mObjectAnimator = null
    }

    override fun isRunning(): Boolean {
        return drawable.isRunning && mObjectAnimator?.isRunning ?: false
    }
}