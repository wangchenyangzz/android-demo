package cn.yy.demo.page.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.yy.demo.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_page.view.*

/**
 *    author : cy.wang
 *    date   : 2020/6/10
 *    desc   :
 */
class PageListAdapter : RecyclerView.Adapter<PageListAdapter.ViewHolder>() {
    private val source: MutableList<String> = mutableListOf()

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(text: String) {
            containerView.tv_text?.text = text
        }
    }

    fun bindData(data: List<String>, notify:Boolean = false) {
        source.clear()
        data.takeIf { it.isNotEmpty() }?.also {
            source.addAll(data)
        }?.takeIf { notify }?.apply {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        )
    }

    override fun getItemCount() = source.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(source[position])
    }
}