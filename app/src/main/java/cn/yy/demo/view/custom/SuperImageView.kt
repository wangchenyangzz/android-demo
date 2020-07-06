package cn.yy.demo.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView

/**
 *    author : cy.wang
 *    date   : 2020/6/28
 *    desc   :
 */
class SuperImageView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    AppCompatImageView(context, attributeSet, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = measuredWidth
        var height = measuredHeight
        if (width > height) {
            width = height
        } else {
            height = width
        }
        setMeasuredDimension(width, height)
    }
}