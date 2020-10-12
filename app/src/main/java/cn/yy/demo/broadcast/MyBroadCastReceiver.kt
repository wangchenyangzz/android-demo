package cn.yy.demo.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import cn.yy.demo.MyApp

/**
 *    author : cy.wang
 *    date   : 2020/10/12
 *    desc   : 广播接收器
 */
class MyBroadCastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(MyApp.application, "receive broadcast", Toast.LENGTH_SHORT).show()
    }
}