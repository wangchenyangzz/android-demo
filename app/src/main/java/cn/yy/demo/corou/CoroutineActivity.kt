//package cn.yy.demo.corou
//
//import android.content.Intent
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import cn.yy.demo.R
//import cn.yy.demo.view.ViewActivity
//import kotlinx.android.synthetic.main.activity_coroutine.*
//import kotlinx.coroutines.*
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//
//class CoroutineActivity : AppCompatActivity() {
//    companion object {
//        const val TAG = "1CoroutineActivity"
//    }
//
//    private var i = 0
//
//    private val baseUrl = "http://www.weather.com.cn/"
//
//    private val retrofit = Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(baseUrl)
//        .build()
//
//    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
//        Log.d(TAG, e.message ?: "")
//        e.printStackTrace()
//    }
//
//    private val mHandler = Handler(Looper.getMainLooper())
//
//    private val scope = CoroutineScope(Dispatchers.Main + CoroutineName("my") + exceptionHandler)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_coroutine)
//
//        testCoroutine()
//        title = "coroutine"
//        iv_image.setOnClickListener {
//            startActivity(Intent(this, ViewActivity::class.java))
//        }
//
//        scope.launch {
//            withTimeout(1000) {
////                getApiService().getUser("google")
//            }
//            Log.d(TAG, "out")
//        }
//    }
//
//    private fun testCoroutine() {
//        scope.launch {
////
////            GlobalScope.launch {
////                Log.d(TAG, Thread.currentThread().name)
////            }
////            Log.d(TAG, Thread.currentThread().name)
////
//
////
////            coroutineScope {
////                Log.d(TAG, Thread.currentThread().name)
////            }
////
////            supervisorScope {≤
////                Log.d(TAG, Thread.currentThread().name)
////            }
//
//
//            scope.launch {
//                try {
//                    val user = getApiService().getUser("google")
//                    resultTextView.text = user.name
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
//        scope.launch {
////            val data1 = async { getApiService().getUser("google") }
////            val data2 = async { getApiService().getUser("github") }
////            Log.d(TAG, "${data1.await()} + ${data2.await()}")
//        }
////
////    private suspend fun fetchAvatar() = suspendCoroutine<String> { continuation ->
////        if (true) {
////            continuation.resume("hello")
////        } else {
////            continuation.resumeWithException(IOException("io error"))
////        }
////    }
////
////    override fun onDestroy() {
////        super.onDestroy()
////        scope.cancel()
////    }
//    }
//
//
//    interface AipInterface {
//        @GET("data/cityinfo/101010100.html")
//        suspend fun getHomeList(): Weather
//    }
//
//}
