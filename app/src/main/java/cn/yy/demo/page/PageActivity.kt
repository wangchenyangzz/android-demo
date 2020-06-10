package cn.yy.demo.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.yy.demo.R
import cn.yy.demo.page.adapter.PageListAdapter
import kotlinx.android.synthetic.main.activity_page.*

class PageActivity : AppCompatActivity() {
     private val mAdapter by lazy {
         PageListAdapter()
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)

        rv_page?.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(context, 3, RecyclerView.HORIZONTAL, false)
        }

        mAdapter.bindData(listOf(
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads",
            "asdads"
        ), true)
    }
}
