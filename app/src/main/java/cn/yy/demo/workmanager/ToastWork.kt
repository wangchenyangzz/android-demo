package cn.yy.demo.workmanager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *    author : cy.wang
 *    date   : 2020/11/3
 *    desc   :
 */
class ToastWork(context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {
    override fun doWork(): Result {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(applicationContext, "work doing ${Thread.currentThread().name}", Toast.LENGTH_LONG).show()
        }
        Log.d("wcy", "work doing ${Thread.currentThread().name}")
        return Result.success()
    }
}