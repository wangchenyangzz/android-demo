package cn.yy.demo.view.custom

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import java.util.*


/**
 *    author : cy.wang
 *    date   : 2020/6/29
 *    desc   :
 */
class MyGroup @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attributeSet, defStyleAttr) {
    private lateinit var bounds: Array<Rect?>

    init {
        bounds = arrayOfNulls<Rect>(childCount)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        var horizontalUsed = 0
        var verticalUsed = 0

        if (bounds.size < childCount) {
            bounds = bounds.copyOf(childCount)
        }

        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            val childLayoutParams = childView.layoutParams
            val childWidthMeasureSpec: Int = when (childLayoutParams.width) {
                LayoutParams.MATCH_PARENT -> {
                    if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST) {
                        MeasureSpec.makeMeasureSpec(width - horizontalUsed, MeasureSpec.EXACTLY)
                    } else {
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    }
                }
                LayoutParams.WRAP_CONTENT -> {
                    if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST) {
                        MeasureSpec.makeMeasureSpec(width - horizontalUsed, MeasureSpec.AT_MOST)
                    } else {
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    }
                }
                else -> {
                    MeasureSpec.makeMeasureSpec(childLayoutParams.width, MeasureSpec.EXACTLY)
                }
            }

            val childHeightMeasureSpec: Int = when (childLayoutParams.height) {
                LayoutParams.MATCH_PARENT -> {
                    if (heightMode == MeasureSpec.EXACTLY || heightMode == MeasureSpec.AT_MOST) {
                        MeasureSpec.makeMeasureSpec(height - verticalUsed, MeasureSpec.EXACTLY)
                    } else {
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    }
                }
                LayoutParams.WRAP_CONTENT -> {
                    if (heightMode == MeasureSpec.EXACTLY || heightMode == MeasureSpec.AT_MOST) {
                        MeasureSpec.makeMeasureSpec(height - verticalUsed, MeasureSpec.AT_MOST)
                    } else {
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    }
                }
                else -> {
                    MeasureSpec.makeMeasureSpec(childLayoutParams.height, MeasureSpec.EXACTLY)
                }
            }
            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec)

            bounds[i]?.set(horizontalUsed, verticalUsed,horizontalUsed + childView.measuredWidth, verticalUsed + childView.measuredHeight)

            horizontalUsed += childView.measuredWidth
            verticalUsed += childView.measuredHeight
        }
        setMeasuredDimension(
            View.resolveSizeAndState(horizontalUsed, widthMeasureSpec, 0),
            View.resolveSizeAndState(verticalUsed, heightMeasureSpec, 0)
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            bounds[i]?.also {
                getChildAt(i).layout(it.left, it.top, it.right, it.bottom)
            }
        }
    }
}

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

/*
class TagLayout(context: Context?, attrs: AttributeSet?) :
    ViewGroup(context, attrs) {
    var childrenBounds: Array<Rect?>? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var lineWidthUsed = 0
        var heightUsed = 0
        val childCount = childCount
        var maxHeight = 0
        if (childrenBounds == null) {
            childrenBounds = arrayOfNulls<Rect>(childCount)
        } else if (childrenBounds!!.size < childCount) {
            childrenBounds = Arrays.copyOf(childrenBounds, childCount)
        }
        for (i in 0 until getChildCount()) {
            val child = getChildAt(i)
            var childBounds: Rect? = childrenBounds!![i]
            measureChildWithMargins(
                child,
                widthMeasureSpec,
                lineWidthUsed,
                heightMeasureSpec,
                heightUsed
            )
            // 判断另起一行
            if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED &&
                child.measuredWidth + lineWidthUsed + paddingStart + paddingEnd > MeasureSpec.getSize(
                    widthMeasureSpec
                )
            ) {
                lineWidthUsed = 0
                heightUsed += maxHeight
                maxHeight = 0
                measureChildWithMargins(
                    child,
                    widthMeasureSpec,
                    lineWidthUsed,
                    heightMeasureSpec,
                    heightUsed
                )
            }
            if (childBounds == null) {
                childrenBounds!![i] = Rect()
                childBounds = childrenBounds!![i]
            }
            childBounds.set(
                lineWidthUsed,
                heightUsed,
                lineWidthUsed + child.measuredWidth,
                heightUsed + child.measuredHeight
            )
            lineWidthUsed += child.measuredWidth
            widthUsed = Math.max(widthUsed, lineWidthUsed)
            maxHeight = Math.max(maxHeight, child.measuredHeight)
        }
        val width = widthUsed
        val height = heightUsed + maxHeight
        setMeasuredDimension(
            View.resolveSizeAndState(width, widthMeasureSpec, 0),
            View.resolveSizeAndState(height, heightMeasureSpec, 0)
        )
    }

    override fun onLayout(
        changed: Boolean,
        l: Int,
        t: Int,
        r: Int,
        b: Int
    ) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childBounds: Rect? = childrenBounds!![i]
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}*/
