package cn.yy.demo.paging3.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import cn.yy.demo.paging3.MainSource
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.Exception

/**
 *    author : cy.wang
 *    date   : 2020/8/3
 *    desc   :
 */
class PagingViewModel: ViewModel() {
    val allStrings = Pager(
        PagingConfig(
            /**
             * A good page size is a value that fills at least a few screens worth of content on a
             * large device so the User is unlikely to see a null item.
             * You can play with this constant to observe the paging behavior.
             *
             * It's possible to vary this with list device size, but often unnecessary, unless a
             * user scrolling on a large device is expected to scroll through items more quickly
             * than a small device, such as when the large device uses a grid layout of items.
             */
            pageSize = 20,
            prefetchDistance = 1,
            /**
             * If placeholders are enabled, PagedList will report the full size but some items might
             * be null in onBind method (PagedListAdapter triggers a rebind when data is loaded).
             *
             * If placeholders are disabled, onBind will never receive null but as more pages are
             * loaded, the scrollbars will jitter as new pages are loaded. You should probably
             * disable scrollbars if you disable placeholders.
             */
            enablePlaceholders = true,

            /**
             * Maximum number of items a PagedList should hold in memory at once.
             *
             * This number triggers the PagedList to start dropping distant pages as more are loaded.
             */
            maxSize = 330
        )
    ) {
        MainSource()
    }.flow

    fun test() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            Log.d("coroutine wcy", throwable.message)
        }) {
            val firstName = supervisorScope { getFirstName() }
            val lastName = supervisorScope { getLastName() }
            val name = firstName + lastName
            Log.d("coroutine wcy", name)
            Log.d("coroutine wcy", "wcy")
        }
    }

    private suspend fun getFirstName() = withContext(Dispatchers.IO) {
        delay(1000)
        throw Exception("123")
        "wang"
    }

    private suspend fun getLastName() = withContext(Dispatchers.IO) {
        delay(2000)
        throw Exception("456")
        "chenyang"
    }
}