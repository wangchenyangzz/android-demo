//package cn.yy.demo.rwx
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.lifecycle.lifecycleScope
//import cn.yy.demo.R
//import kotlinx.coroutines.CoroutineExceptionHandler
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class PracticeActivity1 : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_practice1)
//
//        val exceptionHandler = CoroutineExceptionHandler { _, e ->
//            e.printStackTrace()
//        }
//
//        //lifecycleScope 是andorid官方提供的支持lifecyle的CoroutineScope
//        lifecycleScope.launch(Dispatchers.Main + exceptionHandler) {
//            val data = getData()
//            val processedData = processData(data)
//            // 练习内容：用协程让上面 ↑ 这两行放在后台执行，然后把代码截图贴到腾讯课堂的作业里
//            textView.text = processedData
//        }
//    }
//
//    // 耗时函数 1
//    private suspend fun getData() = withContext(Dispatchers.IO) {
//            // 假设这个函数比较耗时，需要放在后台
//            return@withContext "hen_coder"
//        }
//
//    // 耗时函数 2
//    private suspend fun processData(data: String) = withContext(Dispatchers.IO) {
//        // 假设这个函数也比较耗时，需要放在后台
//        return@withContext data.split("_") // 把 "hen_coder" 拆成 ["hen", "coder"]
//            .map { it.capitalize() } // 把 ["hen", "coder"] 改成 ["Hen", "Coder"]
//            .reduce { acc, s -> acc + s } // 把 ["Hen", "Coder"] 改成 "HenCoder"
//    }
//}
