package cn.yy.demo.thread

import android.util.Log

/**
 *    author : cy.wang
 *    date   : 2020/11/12
 *    desc   :
 */
class MapDelagate(private val map: Map<String, String>): Map<String, String> by map{
    private val s = "MapDelagate"

    override val size: Int
        get(): Int {
            Log.d("aaa", s)
            return map.size
        }
}