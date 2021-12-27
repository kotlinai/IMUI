package com.kotlinaai.imui.conversation.adapters.messageItems

import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.kotlinaai.imui.conversation.pojos.UIMessage
import com.kotlinaai.imui.databinding.MessageContentTextBinding

class TextMessageItem: BaseMessageItemContainer() {
    override fun addContent(inflater: LayoutInflater, frameContent: FrameLayout, type: Int) {
        val binding = MessageContentTextBinding.inflate(inflater, frameContent, false)

        frameContent.removeAllViews()
        frameContent.addView(binding.root)
    }

    override fun onBindView(contentView: View, message: UIMessage?) {
        if (message != null) {
            val binding = DataBindingUtil.getBinding<MessageContentTextBinding>(contentView)

            binding?.tvContent?.text = message.msg
        }
    }

    /*override fun onBindViewHolder(holder: RecyclerView.ViewHolder, message: UIMessage?) {
        if (message != null) {
            if (holder is RecyclerBindingVH<*>) {
                if (message.messageType == UIMessage.TYPE_TEXT_SEND) {
                    val binding = holder.binding as ItemTextMessageRBinding

                    //Glide.with(binding.avatar).load(message.avatar).circleCrop().error(R.drawable.head_placeholder).into(binding.avatar)
                    binding.tvContent.text = message.msg

                    if (message.isTimeShow) {
                        binding.tvTime.visibility = View.VISIBLE
                        binding.tvTime.text = DateUtils.getRelativeTimeSpanString(
                            holder.itemView.context,
                            message.time ?: 0L)
                    } else {
                        binding.tvTime.visibility = View.GONE
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

                } else {
                    val binding = holder.binding as ItemTextMessageLBinding

                    binding.tvContent.text = message.msg

                    if (message.time != null) {
                        binding.tvTime.visibility = View.VISIBLE
                        binding.tvTime.text = DateUtils.getRelativeTimeSpanString(
                            holder.itemView.context,
                            message.time ?: 0L)
                    } else {
                        binding.tvTime.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun isType(type: Int): Boolean {
        return type == UIMessage.TYPE_TEXT_SEND || type == UIMessage.TYPE_TEXT_RECEIVE
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        val binding =
            if (type == UIMessage.TYPE_TEXT_SEND)
                ItemTextMessageRBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            else
                ItemTextMessageLBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecyclerBindingVH(binding)
    }*/
    override fun isType(type: Int): Boolean {
        return type == UIMessage.TYPE_TEXT_SEND || type == UIMessage.TYPE_TEXT_RECEIVE
    }
}