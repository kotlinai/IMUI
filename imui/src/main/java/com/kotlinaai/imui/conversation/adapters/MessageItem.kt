package com.kotlinaai.imui.conversation.adapters

import androidx.compose.runtime.Composable
import com.kotlinaai.imui.conversation.pojos.UIMessage

interface MessageItem {
    fun isItemType(messageType: Int, originType: Int): Boolean

    @Composable
    fun MessageItem(message: UIMessage)
}