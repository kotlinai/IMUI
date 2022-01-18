package com.kotlinaai.app.messagetype

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.kotlinaai.app.R
import com.kotlinaai.imui.conversation.adapters.MessageItemAdapter
import com.kotlinaai.imui.conversation.pojos.UIMessage

/*
class TextMessageItemL: MessageItemAdapter() {
    override fun onCreateView(context: Context): View {
        return LayoutInflater.from(context).inflate(R.layout.item_text_l, null)
    }

    override fun onUpdate(view: View, message: UIMessage) {
        view.findViewById<TextView>(R.id.tvContent).text = message.msg
    }

    override fun isItemType(messageType: Int, originType: Int): Boolean {
        return messageType == UIMessage.TYPE_TEXT && originType == UIMessage.ORIGIN_TYPE_RECEIVING
    }
}*/
