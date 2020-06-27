package cn.yy.demo.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 *    author : cy.wang
 *    date   : 2020/6/20
 *    desc   : 自定义View
 */
class MyView : View {
    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val rect = Rect(200, 200, 400, 400)

    private val rectF = RectF(100f, 100f, 200f, 200f)

    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = Color.parseColor("#7FFFD4")
        canvas?.drawRect(0f, 0f, 600f, 600f, paint)

        //        练习内容：使用 canvas.drawColor() 方法把 View 涂成黄色
        //        黄色： Color.YELLOW
        //        canvas?.drawColor(Color.parseColor("#FFFF00"))

        //        练习内容：使用 canvas.drawCircle() 方法画圆
        //        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
//        paint.color = Color.parseColor("#000000")
//        paint.style = Paint.Style.FILL
//        canvas?.drawCircle(100f, 100f, 100f, paint)
//
//        paint.style = Paint.Style.STROKE
//        canvas?.drawCircle(400f, 100f, 100f, paint)
//
//        paint.style = Paint.Style.FILL
//        paint.color = Color.parseColor("#8DEEEE")
//        canvas?.drawCircle(100f, 400f, 100f, paint)
//
//        paint.style = Paint.Style.STROKE
//        paint.strokeWidth = 20f
//        canvas?.drawCircle(400f, 400f, 100f, paint)


        //        练习内容：使用 canvas.drawRect() 方法画矩形
//        paint.style = Paint.Style.STROKE
//        paint.strokeWidth = 20f
//        canvas?.drawRect(20f, 20f, 100f, 100f, paint)
//
//        canvas?.drawRect(rect, paint)


//        练习内容：使用 canvas.drawPoint() 方法画点
        paint.color = Color.parseColor("#8DEEEE")
        //圆点和方点的切换使用 paint.setStrokeCap(cap)：`ROUND` 是圆点，`BUTT` 或 `SQUARE` 是方点
//        paint.strokeCap = Paint.Cap.ROUND
//        paint.strokeWidth = 20f
//        canvas?.drawPoint(100f, 100f, paint)
//        paint.strokeCap = Paint.Cap.SQUARE
//        canvas?.drawPoint(200f, 200f, paint)


        //        练习内容：使用 canvas.drawOval() 方法画椭圆
        //        canvas?.drawOval(rectF, paint)


        //        练习内容：使用 canvas.drawLine() 方法画直线
//        canvas?.drawLine(0f, 0f, 100f, 100f, paint)


        //        练习内容：使用 canvas.drawRoundRect() 方法画圆角矩形
//        canvas?.drawRoundRect(0f, 0f, 300f, 300f, 50f, 50f, paint)


        //        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
//        canvas?.drawArc(rectF, 0f, 90f, true, paint)


        //        练习内容：使用 canvas.drawPath() 方法画心形
//        path.addArc(rectF, -225f, 225f)
//        path.arcTo(200f, 0f, 400f, 200f, 180f, 225f, false)
//        path.lineTo(200f, 342f)
//        canvas?.drawPath(path, paint)


        //        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图


        //        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        paint.color = Color.WHITE
        canvas?.drawArc(rectF, -180f, 135f, true, paint)
        paint.color = Color.RED
        canvas?.drawArc(110f, 110f, 210f, 210f, -45f, 45f, true, paint)
        paint.color = Color.YELLOW
        canvas?.drawArc(110f, 110f, 210f, 210f, 0f, 30f, true, paint)
        paint.color = Color.GREEN
        canvas?.drawArc(110f, 110f, 210f, 210f, 30f, 50f, true, paint)
        paint.color = Color.GRAY
        canvas?.drawArc(110f, 110f, 210f, 210f, 80f, 100f, true, paint)

        paint.textSize = 20f
        paint.color = Color.WHITE
        canvas?.drawText("饼图", 150f, 250f, paint)

        paint.color = Color.GREEN
        canvas?.drawText("Lollipop", 0f, 110f, paint)
        canvas?.drawLine(80f, 110f, 100f, 110f, paint)
        canvas?.drawLine(100f, 110f, 120f, 120f, paint)
    }
}