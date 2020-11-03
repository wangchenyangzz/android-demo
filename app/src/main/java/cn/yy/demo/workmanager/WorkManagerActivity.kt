package cn.yy.demo.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import cn.yy.demo.R

class WorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        val toastWorkRequest = OneTimeWorkRequestBuilder<ToastWork>()
            .build()
        WorkManager.getInstance().enqueue(toastWorkRequest)
    }
}