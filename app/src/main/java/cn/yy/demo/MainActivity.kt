package cn.yy.demo

import android.Manifest
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Debug
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import cn.yy.demo.page.PageActivity
import cn.yy.demo.view.ViewActivity
import com.bumptech.glide.Glide
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private val cl = CompositeDisposable()

    // Storage Permissions
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        Debug.startMethodTracing("trace")
        super.onCreate(savedInstanceState)
        Log.d("wcy", "onCreate")

        verifyStoragePermissions(this)
        setContentView(R.layout.activity_main)

        bt_page_list?.setOnClickListener {
            startActivity(Intent(this, PageActivity::class.java))
        }

        bt_view?.setOnClickListener {
            startActivity(Intent(this, ViewActivity::class.java))
        }

        bt_coroutine?.setOnClickListener {
//            startActivity(Intent(this, CoroutineActivity::class.java))
        }

//        testRx()

        Debug.stopMethodTracing()
    }

    fun verifyStoragePermissions(activity: Activity?) { // Check if we have write permission
        val permission =
            ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        if (permission != PackageManager.PERMISSION_GRANTED) { // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }

    private fun testRx() {
        // 创建被观察者
        Observable.create<String> {
            it.onNext("w")
            it.onNext("c")
            it.onNext("y")
        }.subscribe({
            Log.i("wcy", "string: $it")
        }, {
            Log.i("wcy", "error")
        }, {
            Log.i("wcy", "监听完毕")
        }, {
            Log.i("wcy", "准备监听")
        }).addTo(cl)

        //delay 延迟 发送0
        Observable.timer(1000, TimeUnit.MILLISECONDS)
            .subscribe {
                Log.i("wcy", "delay 1000")
            }.addTo(cl)

        //interval 类似定时器，每隔一段时间，发送一个事件，从0开始，每次+1
//        Observable.interval(1000, TimeUnit.MILLISECONDS)
//            .subscribe {
//                Log.i("wcy", "interval $it")
//            }

        //intervalRange 与interval相同，指定初始结尾
        Observable.intervalRange(100, 3, 0, 1000, TimeUnit.MILLISECONDS)
            .subscribe {
                Log.i("wcy", "intervalRange $it")
            }.addTo(cl)

        //range 发送范围内的事件 rangeLong相同数据类型为long
        Observable.range(0, 10)
            .subscribe {
                Log.i("wcy", "range $it")
            }.addTo(cl)

        //never()：不发送任何事件
        //error()：发送 onError() 事件
        //empty() ： 直接发送 onComplete() 事件

        //map 数据转换
        Observable.just(100)
            .map {
                "num: ".plus(it.toString())
            }.subscribe {
                Log.i("wcy", "map $it")
            }.addTo(cl)

        //flatMap 返回的是一个新的被观察者
        Observable.just(100, 200, 300)
            .flatMap {
                Observable.just(it.toString())
            }.subscribe {
                Log.i("wcy", "flatMap $it")
            }.addTo(cl)

        //concat 将多个被观察者组合在一起发送，为串行发送，一次最多发送四个 concatArray 可发送多于4个
        Observable.concat(
            Observable.just(1),
            Observable.just(1, 2),
            Observable.just(1, 2, 3),
            Observable.just(1, 2, 3, 4)
        ).subscribe {
            Log.d("wcy", "num: $it")
        }.addTo(cl)

        //merge 与concat作用相同，为并行发送，一次最多发送四个 mergeArray 可发送多于4个
        Observable.merge(
            Observable.just(1),
            Observable.just(1, 2),
            Observable.just(1, 2, 3),
            Observable.just(1, 2, 3, 4)
        ).subscribe {
            Log.d("wcy", "num: $it")
        }.addTo(cl)

        //zip 用于将多个数据源合并,遵从数据源少的
        Observable.zip(
            Observable.just(100, 200),
            Observable.just("100", "200", "300"),
            BiFunction<Int, String, String> { t, u ->
                t.toString() + u
            }).subscribe {
            Log.d("wcy", "zip: $it")
        }.addTo(cl)

        //startWith && startWithArray 在发送事件前提前发送一个事件
        Completable.fromAction {
            Log.d("wcy", "from action")
        }.andThen {
            Log.d("wcy", "from and then")
        }.andThen {
            Log.d("wcy", "from and then then")
        }.subscribe()

        Completable.create {
            Log.d("crt", "create ${Thread.currentThread()}")
            Thread.sleep(1000)
            it.onComplete()
        }.subscribeOn(Schedulers.io())
            .andThen(Completable.fromAction {
                Log.d("crt", "create from and then ${Thread.currentThread()}")
            }).subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("crt", "oncomplete ${Thread.currentThread()}")
            }, {

            })
            .addTo(cl)


    }

    override fun onStart() {
        super.onStart()
        Log.d("wcy", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("wcy", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("wcy", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("wcy", "onStop")
        cl.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("wcy", "onDestroy")
    }

    val animate = ObjectAnimator.ofFloat()
}

fun Disposable.addTo(cl: CompositeDisposable) {
    cl.add(this)
}

class Solution {

    /**
     * hashmap双循环
     */
//    fun threeSum(nums: IntArray): List<List<Int>> {
//        if (nums.size < 3) return emptyList()
//        nums.sort()
//        val list = arrayListOf<List<Int>>()
//        val map = hashMapOf<Int, Int>()
//        for (i in nums.indices) {
//            if (i > 0 && nums[i] == nums[i - 1])
//                continue
//            val nums_1 = -nums[i]
//            for (j in i + 1 until nums.size) {
//                val value = nums_1 - nums[j]
//                if (map.containsKey(value)) {
//                    if(map[value] != Int.MAX_VALUE) {
//                        list.add(listOf(nums[i], value, map[value] ?: 0))
//                    }
//                    map[value] = Int.MAX_VALUE
//                } else {
//                    map[nums[j]] = value
//                }
//            }
//            map.clear()
//        }
//        return list;
//    }

    /**
     * 双指针
     * @param nums IntArray
     * @return List<List<Int>>
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        if (nums.size < 3) return emptyList()
        nums.sort()
        var m: Int
        var n: Int
        val list = arrayListOf<List<Int>>()
        for (i in 0 until nums.size - 2) {
            if (i > 0 && nums[i] == nums[i - 1]) continue
            m = i + 1
            n = nums.size - 1
            while (m < n) {
                when {
                    nums[m] + nums[n] < -nums[i] -> m++
                    nums[m] + nums[n] > -nums[i] -> n--
                    else -> {
                        list.add(listOf(nums[i], nums[m], nums[n]))
                        while (nums[n] == nums[n - 1] && m < n) {
                            n--
                        }
                        while (nums[m] == nums[m + 1] && m < n) {
                            m++
                        }
                        n--
                    }
                }
            }
        }
        return list
    }
}
