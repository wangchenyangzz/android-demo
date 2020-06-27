package cn.yy.demo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.marginTop
import cn.yy.demo.R
import cn.yy.demo.view.custom.MyView
import cn.yy.demo.view.custom.Paintview
import cn.yy.demo.view.custom.TransformView
import kotlinx.android.synthetic.main.activity_view.*
import kotlin.math.roundToInt

class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
//        cl_root?.addView(MyView(this), 300, 300)

//        cl_root?.addView(Paintview(this), LinearLayout.LayoutParams(500.dip(), 500.dip()).also {
//            it.topMargin = 100
//        })

        cl_root?.addView(TransformView(this), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun Int.dip(): Int {
        return (resources.displayMetrics.density * this).roundToInt()
    }
}
