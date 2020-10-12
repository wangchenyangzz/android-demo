//package cn.yy.demo.view.custom
//
//import android.graphics.*
//import android.graphics.drawable.Drawable
//import android.os.Parcel
//
///**
// *    author : cy.wang
// *    date   : 2020/8/31
// *    desc   :
// */
//
//class AuctionDrawable() : Drawable() {
//
//    companion object {
//        private const val COLOR_SELECT_START = 0x1AFFFFFF
//        private const val COLOR_SELECT_END = 0x00FFFFFF
//
//        private const val warp = 0
//    }
//
//    private val paint = Paint()
//
//    private val path = Path()
//
//    private val rect = RectF()
//
//    private var shader: LinearGradient? = null
//
//    constructor(parcel: Parcel) : this() {
//
//    }
//
//    override fun draw(canvas: Canvas) {
//        rect.set(0f, 0f, intrinsicWidth.toFloat(), intrinsicHeight.toFloat())
//        if (shader == null) {
//            shader = LinearGradient(
//                0f,
//                0f,
//                0f,
//                rect.bottom,
//                COLOR_SELECT_START,
//                COLOR_SELECT_END,
//                Shader.TileMode.CLAMP
//            )
//        }
//        paint.setShader(shader)
//        path.addRoundRect(rect, 8f, 8f, Path.Direction.CW)
//        path.addCircle((intrinsicWidth / 2).toFloat(), 0f, 21f, Path.Direction.CW)
//        path.fillType = Path.FillType.EVEN_ODD
//        canvas.drawPath(path, paint)
//    }
//
//    override fun setAlpha(alpha: Int) {
//    }
//
//    override fun getOpacity(): Int {
//    }
//
//    override fun onBoundsChange(bounds: Rect?) {
//        super.onBoundsChange(bounds)
//    }
//
//    override fun setColorFilter(colorFilter: ColorFilter?) {
//    }
//}
