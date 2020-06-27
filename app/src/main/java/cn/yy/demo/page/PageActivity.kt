package cn.yy.demo.page

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class PageActivity : AppCompatActivity() {
    private fun testCoroutine() {
        GlobalScope.launch(Dispatchers.Main) {

            val data1 = GlobalScope.async {
                1
            }

            val data2 = GlobalScope.async {
                2
            }
            val i = data1.await()
            val j = data2.await()
            println(i + j)
        }
    }
}


