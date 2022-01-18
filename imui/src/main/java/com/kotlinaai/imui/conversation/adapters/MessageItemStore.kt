package com.kotlinaai.imui.conversation.adapters

import com.kotlinaai.imui.conversation.adapters.messageItems.TextMessageItem

class MessageItemStore {

    private val items = arrayListOf<MessageItem>()

    fun addItem(item: MessageItem) {
        items.add(item)
    }

    fun getItem(messageType: Int, originType: Int): MessageItem? {
        return items.findLast { it.isItemType(messageType, originType) }
    }
}