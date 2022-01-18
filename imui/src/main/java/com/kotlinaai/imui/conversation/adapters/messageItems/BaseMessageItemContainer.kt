/*
package com.kotlinaai.imui.conversation.adapters.messageItems

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.kotlinaai.imui.R
import com.kotlinaai.imui.base.RecyclerBindingVH
import com.kotlinaai.imui.conversation.adapters.MessageTypeAdapter
import com.kotlinaai.imui.conversation.pojos.UIMessage
import com.kotlinaai.imui.databinding.ItemBaseMessageContainerLBinding

abstract class BaseMessageItemContainer: MessageTypeAdapter() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, message: UIMessage?) {
        if (message != null) {
            if (holder is RecyclerBindingVH<*>) {
                val binding = holder.binding

                if (binding is ItemBaseMessageContainerLBinding) {
                    message.avatar?.let {
                        */
/*Glide.with(binding.avatar)
                            .load(it)
                            .into(binding.avatar)*//*

                    }
                    if (message.isTimeShow)
                        message.time?.let {
                            binding.tvTime.text = DateUtils.getRelativeTimeSpanString(it)
                        }

                    binding.imgFault.visibility =
                        if (message.status == UIMessage.STATUS_ERROR)
                            View.VISIBLE
                        else
                            View.GONE
                    binding.progressSending.visibility =
                        if (message.status == UIMessage.STATUS_SENDING)
                            View.VISIBLE
                        else
                            View.GONE

                    if (binding.frameContent.childCount > 0) {
                        val content = binding.frameContent.getChildAt(0)

                        onBindView(content, message)
                    }
                }
            }
        }
    }

    final override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemBaseMessageContainerLBinding.inflate(layoutInflater, parent, false)

        */
/*binding.constraintLayout.loadLayoutDescription(R.xml.item_base_message_container_state)

        binding.constraintLayout.setState(
            if (type > 0) R.id.left else R.id.right, 0, 0
        )*//*


        addContent(layoutInflater, binding.frameContent, type)

        return RecyclerBindingVH(binding)
    }

    abstract fun addContent(inflater: LayoutInflater, frameContent: FrameLayout, type: Int)
    abstract fun onBindView(contentView: View, message: UIMessage?)
}*/
