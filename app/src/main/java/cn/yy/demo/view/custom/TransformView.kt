package cn.yy.demo.view.custom

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import cn.yy.demo.R
import cn.yy.demo.corou.CoroutineActivity


/**
 *    author : cy.wang
 *    date   : 2020/6/26
 *    desc   :
 */

class TransformView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG) //抗锯齿

    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)

    private val mat = Matrix()

    private val path = Path()

    private val camera = Camera()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            save()
            //1: 裁切矩形
//            clipRect(0, 0, 200, 200)

            //2：裁切clip
//            path.addCircle(200f,200f,200f, Path.Direction.CW)
//            clipPath(path)

            //3: 平移view
//            canvas.translate(50f, 50f)

            //4: 缩放view
//            canvas.scale(1.5f, 1.5f, bitmap.width.toFloat() / 2, bitmap.height.toFloat() / 2)

            //5: 旋转view
//            canvas.rotate(30f, bitmap.width.toFloat() / 2, bitmap.height.toFloat() / 2)

            //6: 错切view
//            canvas.skew(0f, 0.5f)

            //7: 使用matrix
//            mat.reset()
//            mat.postTranslate(50f, 50f)
//            mat.postScale(1.5f, 1.5f, bitmap.width.toFloat() / 2, bitmap.height.toFloat() / 2)
//            mat.postRotate(30f, bitmap.width.toFloat() / 2, bitmap.height.toFloat() / 2)
//            mat.postScale(0f, 0.5f)
//            canvas.concat(mat)

            //8: 使用camera
            canvas.translate(bitmap.width.toFloat() / 2, bitmap.height.toFloat() / 2)
            camera.rotateX(30f) //设置旋转角度
            camera.applyToCanvas(canvas) //应用
            canvas.translate(-bitmap.width.toFloat() / 2, -bitmap.height.toFloat() / 2)
            drawBitmap(bitmap, 0f, 0f, paint)
            restore()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val animator1: ObjectAnimator = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
        val animator2: ObjectAnimator = ObjectAnimator.ofFloat(this, "translationX", -200f, 200f)
        val animator3: ObjectAnimator = ObjectAnimator.ofFloat(this, "rotation", 0f, 1080f)
        animator3.duration = 1000
        this.setOnClickListener {
            //1.平移
//            it.animate().translationX(500f)
//                .start()

            //2。旋转
//            it.animate().rotation(30f)
//                .start()

//            //3. 缩放
//            it.animate().scaleX(1.5f)
//                .scaleY(1.5f)
//                .start()

            //4. 透明度
//            it.animate().alpha(0f)
//                .start()

            //5.muti
//            it.animate().alpha(0f)
//                .translationX(300f)
//                .start()

            //6。objectAnimator
//            val animator = ObjectAnimator.ofFloat(it, "translationX", 300f)
//            animator.start()

            //7.PropertyValuesHolder
//            val animator1 = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f)
//            val animator2 = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f)
//            ObjectAnimator.ofPropertyValuesHolder(it, animator1, animator2).start()

            //8.animatorSet
//            val animatorSet = AnimatorSet()
//            animatorSet.play(animator1)
//                .before(animator2)
//            animatorSet.playTogether(animator2, animator3)
//            animatorSet.start()

            //9.PropertyValuesHolder
            val keyframe1 = Keyframe.ofFloat(0f, 0f) // 开始：progress 为 0

            val keyframe2 = Keyframe.ofFloat(0.5f, 100f) // 进行到一半是，progres 为 100

            val keyframe3 = Keyframe.ofFloat(1f, 80f) // 结束时倒回到 80

            val holder =
                PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2, keyframe3)

            val animator: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(it, holder)
            animator.duration = 2000
            animator.interpolator = FastOutSlowInInterpolator()
            animator.start()

            it.context.apply {
                startActivity(Intent(this, CoroutineActivity::class.java))
            }
        }
    }
}