package cn.yy.demo.paging3

import android.util.Log
import androidx.paging.PagingSource

/**
 *    author : cy.wang
 *    date   : 2020/8/3
 *    desc   :
 */
class  MainSource : PagingSource<Int, String>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        val page = params.key ?: 0
        Log.d("123", "加载第$page 页")

        return LoadResult.Page(
            data = getString().map {
                (it.toInt() + page * 30).toString()
            },
            prevKey = if (page == 0) null else page - 1,
            nextKey = if (page == 10) null else page + 1
        )
    }

    private fun getString(): List<String> {
        return listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
            "26",
            "27",
            "28",
            "29",
            "30"
        )
    }
}