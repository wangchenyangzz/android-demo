package cn.yy.demo.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import cn.yy.demo.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ThreadTestActivity : AppCompatActivity() {
    private var threadLocal = ThreadLocal<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_test)
        threadLocal.set("wcy")
        lifecycleScope.launch {
            Log.d("yy", threadLocal.get())
            withContext(Dispatchers.Default) {
                Log.d("yy", threadLocal.get() ?: "null")
                threadLocal.set("ycw")
                Log.d("yy", threadLocal.get())
            }
            Log.d("yy", threadLocal.get())
        }
    }
}