package cn.yy.demo.thread.proxy

import android.util.Log

/**
 *    author : cy.wang
 *    date   : 2020/11/3
 *    desc   :
 */
class PrintSell: Sell {
    override fun sell() {
        Log.d("wcy", "log sell")
    }

    override fun ad() {
        Log.d("wcy", "log ad")
    }
}