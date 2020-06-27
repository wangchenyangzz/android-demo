package cn.yy.demo.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import cn.yy.demo.R

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
}