package cn.yy.demo.jobservice

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.coroutines.*

/**
 *    author : cy.wang
 *    date   : 2020/8/6
 *    desc   : job service
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobService : JobService(){
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    companion object {
        val MYJOBSERVICE_JOB_ID = 0 //最为该jobservice的id标识
        val MYJOBSERVICE_JOB_OVERDIDE_DEADLINE = 1000 //延迟多少秒执行
        private val TAG = "MyJobService"
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("job service", "on stop jobs")
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("job service", "on start jobs")
        coroutineScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            jobFinished(params, false)
        }) {
            delay(5000)
            Toast.makeText(this@MyJobService, "job finished", Toast.LENGTH_SHORT).show()
            jobFinished(params, false)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("job service", "service destroy")
    }
}