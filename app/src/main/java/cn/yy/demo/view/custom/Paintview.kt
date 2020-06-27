package cn.yy.demo.view.custom

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import cn.yy.demo.R
import kotlin.math.roundToInt


/**
 *    author : cy.wang
 *    date   : 2020/6/25
 *    desc   :
 */
class Paintview
@JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val shader1 =
        LinearGradient(0f, 0f, 100f, 100f, Color.BLUE, Color.GREEN, Shader.TileMode.CLAMP)

    private val shader2 =
        RadialGradient(50f, 50f, 50f, Color.BLUE, Color.GREEN, Shader.TileMode.CLAMP)

    private val shader3 =
        SweepGradient(50f, 50f, Color.BLUE, Color.GREEN)

    private val bitmap =
        BitmapFactory.decodeResource(resources, R.drawable.batman)

    private val bitmap2 =
        BitmapFactory.decodeResource(resources, R.drawable.batman_logo)

    private val shader4 = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

    private val shader5 = BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

    private val shader6 = ComposeShader(shader4, shader5, PorterDuff.Mode.SRC_OVER)

    private val colorFilter1 = LightingColorFilter(0x00ffff, 0x000000)

    private val colorFilter2 = LightingColorFilter(0xffffff, 0x003000)

    private val colorFilter3 = ColorMatrixColorFilter(
        floatArrayOf(
            -1f, 0f, 0f, 0f, 255F,
            0f, -1f, 0f, 0f, 255F,
            0f, 0f, -1f, 0f, 255F,
            0f, 0f, 0f, 1f, 0f
        )
    )
    private val path = Path()


    private val xfermode: Xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //1. 用 Paint.setShader(shader) 设置一个 LinearGradient
        // LinearGradient 的参数：坐标：(100, 100) 到 (500, 500) ；颜色：#E91E63 到 #2196F3
        // 线性
//        paint.shader = shader1

        //2.        // 用 Paint.setShader(shader) 设置一个 RadialGradient
        //        // RadialGradient 的参数：圆心坐标：(300, 300)；半径：200；颜色：#E91E63 到 #2196F3
        //中心到四周
//        paint.shader = shader2

        //3. 用 Paint.setShader(shader) 设置一个 SweepGradient
        // SweepGradient 的参数：圆心坐标：(300, 300)；颜色：#E91E63 到 #2196F3
//        paint.shader = shader3

        //4. 用 Paint.setShader(shader) 设置一个 BitmapShader
        // Bitmap: R.drawable.batman
//        paint.shader = shader4

        //5. 用 Paint.setShader(shader) 设置一个 ComposeShader
        // Shader 1: BitmapShader 图片：R.drawable.batman
        // Shader 2: BitmapShader 图片：R.drawable.batman_logo
//        paint.shader = shader6
//        canvas?.drawCircle(300f, 300f, 300f, paint)

        //6. 使用 Paint.setColorFilter() 来设置 LightingColorFilter
        // 第一个 LightingColorFilter：去掉红色部分
//        paint.colorFilter = colorFilter1
//        canvas?.drawBitmap(bitmap, 0f, 0f, paint);
//        // 第二个 LightingColorFilter：增强绿色部分
//        paint.colorFilter = colorFilter2
//        canvas?.drawBitmap(bitmap, bitmap.width + 100f, 0f, paint);

        //7. 使用 setColorFilter() 设置一个 ColorMatrixColorFilter
        // 用 ColorMatrixColorFilter.setSaturation() 把饱和度去掉
//        paint.colorFilter = colorFilter3

        // 8.使用 paint.setXfermode() 设置不同的结合绘制效果
//        canvas?.saveLayer(null, null) //设置离屏缓冲
//        canvas?.drawBitmap(bitmap, 0f, 0f, paint);
//        paint.xfermode = xfermode
//        canvas?.drawBitmap(bitmap2, 0f, 0f, paint);

        //9. 使用 Paint.setStrokeCap() 来设置端点形状
//        paint.strokeWidth = 40f
//        paint.strokeCap = Paint.Cap.BUTT
//        canvas?.drawLine(10f, 0f, 10f, 100f, paint)
//        paint.strokeCap = Paint.Cap.ROUND
//        canvas?.drawLine(100f, 0f, 100f, 100f, paint)
//        paint.strokeCap = Paint.Cap.SQUARE
//        canvas?.drawLine(200f, 0f, 200f, 100f, paint)

        //10. 使用 Paint.setStrokeJoin() 来设置不同的拐角形状
//        paint.strokeWidth = 40f
//        paint.style = Paint.Style.STROKE
//        path.rLineTo(200f, 0f)
//        path.rLineTo(-160f, 120f)
//
//        canvas?.translate(100f, 100f)
//        // 第一种形状：MITER
//        paint.strokeJoin = Paint.Join.MITER
//        canvas?.drawPath(path, paint)
//
//        canvas?.translate(300f, 0f)
//        // 第二种形状：BEVEL
//        paint.strokeJoin = Paint.Join.BEVEL
//        canvas?.drawPath(path, paint)
//
//        canvas?.translate(300f, 0f)
//        // 第三种形状：ROUND
//        paint.strokeJoin = Paint.Join.ROUND
//        canvas?.drawPath(path, paint)

    }

    private fun Int.dip(): Float {
        return (resources.displayMetrics.density * this).roundToInt().toFloat()
    }
}