package cn.yy.demo.corou

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import cn.yy.demo.R
import cn.yy.demo.view.ViewActivity
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CoroutineActivity : AppCompatActivity() {
    companion object {
        const val TAG = "1CoroutineActivity"
    }

    private var i = 0

    private val baseUrl = "http://www.weather.com.cn/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
    }

    private val scope = CoroutineScope(Dispatchers.Main + CoroutineName("my") + exceptionHandler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        testCoroutine()
        title = "coroutine"
        iv_image.setOnClickListener {
            startActivity(Intent(this, ViewActivity::class.java))
        }
    }

    private fun testCoroutine() {
        scope.launch {
            Log.d(TAG, Thread.currentThread().name)
//
//            GlobalScope.launch {
//                Log.d(TAG, Thread.currentThread().name)
//            }
//            Log.d(TAG, Thread.currentThread().name)
//

            val data1 = async {
                Log.d(TAG, "1")
                fetchInfo()
            }

            val data2 = async {
                Log.d(TAG, "2")
                delay(1000)
                throw IOException()
                fetchInfo()
            }

            Log.d(TAG, "${data1.await()}+ ${Thread.currentThread().name}")
            Log.d(TAG, "${data2.await()}+ ${Thread.currentThread().name}")

//
//            coroutineScope {
//                Log.d(TAG, Thread.currentThread().name)
//            }
//
//            supervisorScope {
//                Log.d(TAG, Thread.currentThread().name)
//            }
        }

//        lifecycleScope.launch(Dispatchers.Main + exceptionHandler) {
//            val data1 = async { fetchInfo() }
//            val data2 = async { fetchInfo() }
//            Log.d(TAG, "${data1.await()} + ${data2.await()}")
//        }
    }

    private suspend fun fetchInfo(): Weather = withContext(Dispatchers.IO) {
        return@withContext retrofit.create(AipInterface::class.java).getHomeList()
    }
//
//    private suspend fun fetchAvatar() = suspendCoroutine<String> { continuation ->
//        if (true) {
//            continuation.resume("hello")
//        } else {
//            continuation.resumeWithException(IOException("io error"))
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        scope.cancel()
//    }
}


interface AipInterface {
    @GET("data/cityinfo/101010100.html")
    suspend fun getHomeList(): Weather
}
