package com.kotlinaai.imui.conversation.adapters.messageItems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinaai.imui.base.list.ListItemType
import com.kotlinaai.imui.conversation.composables.SystemMessage
import com.kotlinaai.imui.conversation.pojos.UIMessage

class SystemMessageItem: ListItemType<UIMessage> {
    override fun isItemType(item: UIMessage): Boolean {
        return item.messageType == UIMessage.TYPE_SYSTEM
    }

    @Composable
    override fun ListItem(item: UIMessage) {
        SystemMessage(message = item) {
            Text(
                modifier = Modifier.background(
                    color = Color.Gray.copy(alpha = .2f)
                ).padding(5.dp),
                text = item.msg ?: "",
                fontSize = 13.sp,
                color = Color.White
            )
        }
    }
}