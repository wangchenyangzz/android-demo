package cn.yy.demo.view

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.marginTop
import cn.yy.demo.R
import cn.yy.demo.view.custom.*
import kotlinx.android.synthetic.main.activity_view.*
import kotlinx.android.synthetic.main.in_party_view.*
import java.util.zip.Inflater
import kotlin.math.roundToInt

class ViewActivity : AppCompatActivity() {
    private var partyInView: PartyInView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        partyInView = PartyInView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                80.dip(),
                40.dip()
            )
            setOnClickListener {
                if (this.isRunning) {
                    this.stop()
                } else {
                    this.start()
                }
            }
        }
        cl_root?.addView(partyInView)
    }

    private fun Int.dip(): Int {
        return (resources.displayMetrics.density * this).roundToInt()
    }
}
