package cn.yy.demo.generic

import java.util.*

/**
 * author : cy.wang
 * date   : 2020/10/16
 * desc   :
 */
internal class OneKt<T> {
    var one: T? = null
        private set
    val list: MutableList<T> = mutableListOf()

    fun setOne(one: T) {
        this.one = one
    }

    fun add(l: MutableList<out T>) {
        list.addAll(l)
    }

    fun copy(listObject: MutableList<in T>) {
        listObject.addAll(list)
    }
}