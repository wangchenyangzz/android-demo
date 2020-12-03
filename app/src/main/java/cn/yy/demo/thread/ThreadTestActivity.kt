package cn.yy.demo.thread

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import cn.yy.demo.MyApp
import cn.yy.demo.R
import cn.yy.demo.thread.proxy.DynamicProxy
import cn.yy.demo.thread.proxy.PrintSell
import cn.yy.demo.thread.proxy.Sell
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException
import java.lang.ref.WeakReference
import java.lang.reflect.Proxy
import kotlin.math.roundToInt

class ThreadTestActivity : AppCompatActivity() {
    private var threadLocal = ThreadLocal<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_test)

        val discount = 158.times(10.0).div(198).roundToInt()
        Log.d("zzz", discount.toString())

//        threadLocal.set("wcy")
//        lifecycleScope.launch {
//            Log.d("yy", threadLocal.get())
//            withContext(Dispatchers.Default) {
//                Log.d("yy", threadLocal.get() ?: "null")
//                threadLocal.set("ycw")
//                Log.d("yy", threadLocal.get())
//            }
//            Log.d("yy", threadLocal.get())
//        }

        val proxy = DynamicProxy(PrintSell())
        System.getProperties()["sun.misc.ProxyGenerator.saveGeneratedFiles"] = "true";

        val sell = Proxy.newProxyInstance(Sell::class.java.classLoader, arrayOf(Sell::class.java), proxy) as Sell
        sell.sell()
        sell.ad()

        try {
            ExceptionTest.writeIO()
            ExceptionTest.readIO()
        } catch (e: IOException) {
            Log.d("ccc", e.toString())
        } finally {
            Log.d("ccc", "finaly")
        }

        val delgate = MapDelagate(mutableMapOf("123" to "213", "456" to "465"))
        val a = delgate.size
        val b = delgate["123"]
        Log.d("aaa", "$a   $b")

        val okHttpClient = OkHttpClient()
        okHttpClient.newCall(Request.Builder().url("https:www.baidu.com").build())
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("wcy", "failure")
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d("wcy", "success ${response.body()}")
                }
            })
    }
}