package cn.yy.demo.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import cn.yy.demo.R
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_banner.*
import java.util.concurrent.TimeUnit

class BannerActivity : AppCompatActivity() {
    private var mDisposable: Disposable? = null
    private var page = 1

    companion object {
        const val TV_TAG = "cn.yy.demo.banner.textview"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)

        initBanner()
    }

    private fun initBanner() {
        val mAdapter = BannerAdapter()
        v_banner?.adapter = mAdapter
        mAdapter.list = mutableListOf("0", "1", "2")
        v_banner?.setCurrentItem(1, false)
        mDisposable = Observable.interval(1500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                v_banner?.setCurrentItem((it % mAdapter.list.size).toInt(), true)
            }, {
                it.printStackTrace()
            })
    }

    class BannerAdapter : RecyclerView.Adapter<BannerViewHolder>() {
        var list = mutableListOf<String>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
            val itemView = parent.context.let {
                AppCompatImageView(it).apply {
                        layoutParams = FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT
                        ).apply {
                            gravity = Gravity.CENTER
                        }
                    }
                }
            return BannerViewHolder(itemView)
        }

        override fun getItemCount() = when (list.size) {
            0 -> 0
            1 -> 1
            else -> Int.MAX_VALUE
        }

        override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
            holder.bind(list[position % list.size])
        }

    }

    class BannerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
            fun bind(str: String) {
                itemView.also {
                    Glide.with(itemView.context).load("https://xiaored.oss-cn-hangzhou.aliyuncs.com/backend/party/emoji/%E5%BF%83%E5%8A%A8.png").into(it as AppCompatImageView)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable == null
    }
}
