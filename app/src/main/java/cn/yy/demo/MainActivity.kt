package cn.yy.demo

//import cn.yy.demo.corou.CoroutineActivity
import android.Manifest
import android.animation.ObjectAnimator
import android.app.Activity
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.ContentValues
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Debug
import android.util.ArrayMap
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import cn.yy.demo.banner.BannerActivity
import cn.yy.demo.broadcast.MyBroadCastReceiver
import cn.yy.demo.dagger.module.Car
import cn.yy.demo.dagger.module.DaggerMainComponent
import cn.yy.demo.dagger.module.MainComponent
import cn.yy.demo.databinding.DataBindActivity
import cn.yy.demo.db.BookDbHelper
import cn.yy.demo.generic.GenericActivity
import cn.yy.demo.jobservice.MyJobService
import cn.yy.demo.leetcode.Solution
import cn.yy.demo.listadapter.ListActivity
import cn.yy.demo.page.PageActivity
import cn.yy.demo.paging3.PagingActivity
import cn.yy.demo.view.ViewActivity
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var car: Car

    private val cl = CompositeDisposable()

    private var function: ((String) -> Unit)? = null

    private val receiver = MyBroadCastReceiver()

    @Volatile
    private var num = 0

    // Storage Permissions
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        Debug.startMethodTracing("trace")
        super.onCreate(savedInstanceState)

        val mainComponent: MainComponent = DaggerMainComponent.create()
        mainComponent.inject(this)

        Log.d("wcy", "lalala $car")

        function = {
            Log.d("wcy", it)
        }

//        function = {
//            Log.d("wcy", it)
//        }

        Log.d("wcy", "onCreate")

        Log.d("solution", Solution().threeSumClosest(intArrayOf(-1, 2, 1, -4), 1).toString())

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

        bt_banner?.setOnClickListener {
            startActivity(Intent(this, BannerActivity::class.java))
        }

        bt_list?.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

        bt_paging_list?.setOnClickListener {
            startActivity(Intent(this, PagingActivity::class.java))
        }

        function?.invoke("function invoke")

        bt_job_service?.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val myJobServiceComponentName = ComponentName(this, MyJobService::class.java)

                val jobBuilder =
                    JobInfo.Builder(MyJobService.MYJOBSERVICE_JOB_ID, myJobServiceComponentName)
                jobBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                val myJob = jobBuilder.build()

                val scheduler = this.getSystemService(JobScheduler::class.java)

                @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                scheduler.schedule(myJob)
            }
        }

        dataBindingButton?.setOnClickListener {
            startActivity(Intent(this, DataBindActivity::class.java))
        }

        arrayMapButton?.setOnClickListener {
//            testArrayMap()
            Log.d("volatile", "num is $num")
        }

        volatileButton?.setOnClickListener {
//            testVolatile()
            val helper = BookDbHelper(this, "book.db", 1)
            helper.writableDatabase.insert("Books", null, ContentValues().apply {
                put("name", "1")
            })
            helper.close()
        }

        receiverButton?.setOnClickListener {
//            val filter = IntentFilter(Intent.ACTION_TIME_TICK)
//            registerReceiver(receiver, filter)
//            val packageName = opPackageName
//            sendBroadcast(Intent("com.yy.test.action.toast").setPackage(packageName))
        }

        genericButton?.setOnClickListener {
            startActivity(Intent(this, GenericActivity::class.java))
        }
//        testRx()
        Debug.stopMethodTracing()
    }

    private fun testVolatile() {
        for (i in 0..9) {
            thread {
                synchronized(this) {
                    for (j in 0..999) {
                        num++
                    }
                }
            }
        }
    }

    private fun testArrayMap() {
//        val arrayMap1 = ArrayMap<String, String>(8)
//        val arrayMap2 = ArrayMap<String, String>(8)
//        val arrayMap3 = ArrayMap<String, String>(8)
//        arrayMap1.clear()
//        arrayMap2.clear()
//        arrayMap3.clear()
        val arrayMap4 = ArrayMap<String, String>(8)
        arrayMap4["1"] = "1"
        arrayMap4["2"] = "2"
        arrayMap4["3"] = "3"
        arrayMap4["4"] = "4"
        arrayMap4["5"] = "5"
        arrayMap4["6"] = "6"
        arrayMap4["7"] = "7"
        arrayMap4["8"] = "8"
        Log.d("wcy", arrayMap4.toString())
        arrayMap4.clear()
        val arrayMap1 = ArrayMap<String, String>(8)

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

        Observable.just("wang", "chen", "yang").flatMapCompletable {
            Log.d("wcy", "out ${Thread.currentThread()}")
            Completable.fromCallable {
                Log.d("wcy", "in ${Thread.currentThread()}")
                it.substring(0, it.length - 2)
            }.subscribeOn(Schedulers.io())
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
                it.printStackTrace()
            }).addTo(cl)
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
        unregisterReceiver(receiver)
    }

    val animate = ObjectAnimator.ofFloat()
}

fun Disposable.addTo(cl: CompositeDisposable) {
    cl.add(this)
}
