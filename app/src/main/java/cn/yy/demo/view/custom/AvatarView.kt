package cn.yy.demo.view.custom

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.withSave
import cn.yy.demo.R
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AvatarView
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatImageView(context, attrs, defStyleAttr) {
    private val bounds = RectF()
    private val paint = Paint()
    private val mPadding = 2.dip().toFloat()
    private val mode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private lateinit var bitmap: Bitmap

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bounds.set(mPadding, mPadding, width - mPadding, height - mPadding)
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)
    }

    override fun onDraw(canvas: Canvas) {
        //Canvas的离屏缓冲
        paint.color = Color.BLUE
        canvas.drawOval(mPadding, mPadding, width - mPadding, height - mPadding, paint)
        val count = canvas.saveLayer(bounds, paint)
        //KTX的扩展函数相当于对Canvas的 save 和 restore 操作
        canvas.drawOval(mPadding+10, mPadding+10, width - mPadding-10, height - mPadding-10, paint)
        paint.xfermode = mode
        canvas.drawBitmap(bitmap,mPadding,mPadding,paint)
        //把离屏缓冲的内容,绘制到View上去
        paint.xfermode = null
        canvas.restoreToCount(count)
        super.onDraw(canvas)
    }

    private fun Int.dip(): Float {
        return (resources.displayMetrics.density * this).roundToInt().toFloat()
    }
}
