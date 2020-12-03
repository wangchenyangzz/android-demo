package cn.yy.demo

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference

/**
 *    author : cy.wang
 *    date   : 2020/10/12
 *    desc   :
 */
class MyApp: Application() {
    companion object {
        lateinit var application: Application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}