package cn.yy.demo.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.yy.demo.R
import cn.yy.demo.view.gift.RewardProgressBar
import kotlinx.android.synthetic.main.activity_view.*
import kotlin.math.roundToInt

class ViewActivity : AppCompatActivity() {
    private var progressBar: RewardProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        progressBar = RewardProgressBar(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                56.dip()
            )
        }
        cl_root?.addView(progressBar)
        progressBar?.isClickable = true
        progressBar?.update(
            listOf(
                RewardProgressBar.Reward(0),
                RewardProgressBar.Reward(0),
                RewardProgressBar.Reward(2, R.drawable.gift),
                RewardProgressBar.Reward(0),
                RewardProgressBar.Reward(2, R.drawable.gift),
                RewardProgressBar.Reward(0),
                RewardProgressBar.Reward(0),
                RewardProgressBar.Reward(1, R.drawable.gift1),
                RewardProgressBar.Reward(0),
                RewardProgressBar.Reward(0, R.drawable.gift0)
            )
        )
        progressBar?.receiveClick = { reward ->
            Log.d("wcy", "$reward")
        }

        avatarSelectView?.clickListener = {
            if (it) {
                Toast.makeText(this, "custom avatar", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "3d avatar", Toast.LENGTH_SHORT).show()
            }
        }

        threeDimensionalView?.setOnClickListener {
            val animate1 = ObjectAnimator.ofFloat(threeDimensionalView, "clipRotate", 0f, 45f).apply {
                duration = 1000
            }
            val animate2 = ObjectAnimator.ofFloat(threeDimensionalView, "clip", 0f, 360f).apply {
                duration = 3000
            }
            val animate3 = ObjectAnimator.ofFloat(threeDimensionalView, "clipRotate", 45f, 0f).apply {
                duration = 1000
            }
            AnimatorSet().apply {
                playSequentially(animate1, animate2, animate3)
                start()
            }
        }
    }

    private fun Int.dip(): Int {
        return (resources.displayMetrics.density * this).roundToInt()
    }
}
