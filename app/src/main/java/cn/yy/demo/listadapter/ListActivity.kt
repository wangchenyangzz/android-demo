package cn.yy.demo.listadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import cn.yy.demo.R
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {
    private val mAdapter by lazy {
        UserAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        userRecyclerView?.layoutManager = LinearLayoutManager(this)
        userRecyclerView?.adapter = mAdapter
        mAdapter.submitList(listOf(
            User("qsda", 1),
            User("wdsaadsasd", 1),
            User("edsadasdasdsa", 1),
            User("rasddsasdadas", 1),
            User("tasdasddsasda", 1)
        ))

        userRecyclerView.postDelayed({
            mAdapter.submitList(listOf(
                User("rasddsasdadas", 1),
                User("tasdasddsasda", 1),
                User("1111", 1)
                ))
        }, 1000)
        Log.d("1", "2")
    }
}