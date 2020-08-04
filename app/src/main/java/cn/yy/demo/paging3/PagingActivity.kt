package cn.yy.demo.paging3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.yy.demo.R
import cn.yy.demo.paging3.adapter.PagingAdapter
import cn.yy.demo.paging3.viewmodel.PagingViewModel
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingActivity : AppCompatActivity() {
    private val mAdapter by lazy {
        PagingAdapter()
    }
    private val viewModel by viewModels<PagingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        pagingRecyclerView?.apply {
            layoutManager = LinearLayoutManager(this@PagingActivity)
            adapter = mAdapter
        }

        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            viewModel.allStrings.collectLatest { mAdapter.submitData(it) }
        }
    }
}