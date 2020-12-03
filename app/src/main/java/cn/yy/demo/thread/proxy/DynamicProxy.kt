package cn.yy.demo.thread.proxy

import android.util.Log
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

/**
 *    author : cy.wang
 *    date   : 2020/11/3
 *    desc   :
 */
class DynamicProxy(private val obj: Any) : InvocationHandler {

    override fun invoke(proxy: Any, method: Method?, args: Array<out Any>?): Any? {
        Log.d("wcy", "start")
        val result = method!!.invoke(obj, *(args ?: arrayOfNulls<Any>(0)))
        Log.d("wcy", "end")
        return result
    }
}