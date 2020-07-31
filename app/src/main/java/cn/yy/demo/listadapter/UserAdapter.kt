package cn.yy.demo.listadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.yy.demo.R
import kotlinx.android.synthetic.main.item_page.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

/**
 *    author : cy.wang
 *    date   : 2020/7/28
 *    desc   :
 */
class UserAdapter : ListAdapter<User, UserAdapter.ViewHolder>(UserDiffCallback) {
    companion object {
        val DEFAULT_CONFIG =
            AsyncDifferConfig.Builder<User>(UserDiffCallback)
                .setBackgroundThreadExecutor(Dispatchers.Default.asExecutor())
                .build()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
            fun bind(data: User) {
                itemView.findViewById<TextView>(R.id.tv_text).text = data.name
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

data class User(val name: String, val sex: Int)

object UserDiffCallback: DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.name == newItem.name && oldItem.sex == newItem.sex
    }

}