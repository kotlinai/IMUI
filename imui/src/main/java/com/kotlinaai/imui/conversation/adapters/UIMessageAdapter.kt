package com.kotlinaai.imui.conversation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlinaai.imui.conversation.adapters.messageItems.TextMessageItem
import com.kotlinaai.imui.conversation.pojos.UIMessage

class UIMessageAdapter: PagingDataAdapter<UIMessage, RecyclerView.ViewHolder>(MessageCompare) {

    object MessageCompare: DiffUtil.ItemCallback<UIMessage>() {
        override fun areItemsTheSame(oldItem: UIMessage, newItem: UIMessage): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UIMessage, newItem: UIMessage): Boolean {
            return oldItem.status == newItem.status
        }
    }

    private val adapters = arrayListOf<MessageTypeAdapter>(TextMessageItem())

    fun addMessageTypeAdapter(adapter: MessageTypeAdapter) {
        if (!adapters.contains(adapter))
            adapters.add(adapter)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        adapters.findLast {
            it.isType(getItemViewType(position))
        }?.onBindViewHolder(holder, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return adapters.findLast { it.isType(viewType) }?.onCreateViewHolder(parent, viewType) ?: DefaultViewHolder(
            View(parent.context)
        )
    }

    /**
     * > 0 接收消息
     * < 0 发出消息
     */
    override fun getItemViewType(position: Int): Int {

        return getItem(position)?.messageType ?: -1
    }

    class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}