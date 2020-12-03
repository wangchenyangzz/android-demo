package cn.yy.demo.paging3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.yy.demo.R
import cn.yy.demo.paging3.adapter.PagingAdapter
import cn.yy.demo.paging3.viewmodel.PagingViewModel
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.android.synthetic.main.item_loadmore.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingActivity : AppCompatActivity() {
    private val mAdapter by lazy {
        PagingAdapter()
    }
    private val viewModel by viewModels<PagingViewModel>()

    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

//        srl_content.apply {
//            setDragRate(0.4f)
//            setHeaderMaxDragRate(2.5f)
//            setEnableOverScrollBounce(true)
//            setEnableOverScrollDrag(true)
//        }

        pagingRecyclerView?.apply {
            layoutManager = LinearLayoutManager(this@PagingActivity)
            adapter = mAdapter.withLoadStateFooter(LoadmoreAdapter { mAdapter.retry() })
        }

        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            viewModel.allStrings.collectLatest { mAdapter.submitData(it) }
        }

        mAdapter.addDataRefreshListener {
        }

        mAdapter.addLoadStateListener { loadState ->
            // 这里的逻辑可以自由发挥
            when (loadState.refresh) {
//                is LoadState.Error -> statuslayout.switchLayout(StatusLayout.STATUS_ERROR)

                is LoadState.Loading -> {

                }
                is LoadState.NotLoading -> {
                }
            }
        }

        viewModel.test()
    }
}

class LoadmoreAdapter(val retrycallback: () -> Unit) : LoadStateAdapter<LoadmoreView>() {
    override fun onBindViewHolder(holder: LoadmoreView, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadmoreView {
        return LoadmoreView(parent, loadState, retrycallback)
    }
}

class LoadmoreView(
    parent: ViewGroup,
    loadState: LoadState,
    val retrycallback: () -> Unit
) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)!!.inflate(R.layout.item_loadmore, parent, false)
    ) {

    init {
        bindState(loadState)
    }

    fun bindState(loadState: LoadState) {
        itemView.loading_progress?.visibility = if(loadState is LoadState.Loading) View.VISIBLE else View.GONE
        itemView.btn_reload?.visibility = if(loadState !is LoadState.Loading) View.VISIBLE else View.GONE
    }
}