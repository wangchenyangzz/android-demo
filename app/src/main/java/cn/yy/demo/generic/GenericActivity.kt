package cn.yy.demo.generic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cn.yy.demo.R

class GenericActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic)
        testGeneric()
    }

    private fun testGeneric() {
        Generic().test()
        val listObject = mutableListOf<Any>()
        val list: MutableList<Int>  = mutableListOf(12, 23, 34)
        val oneKt = OneKt<Number>()
        oneKt.add(list)
        oneKt.copy(listObject)
        listObject.forEach {
            Log.d("泛型测试", "$it")
        }
    }
}