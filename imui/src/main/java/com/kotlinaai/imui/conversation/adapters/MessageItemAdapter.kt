package com.kotlinaai.imui.conversation.adapters

import android.content.Context
import android.view.View
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.kotlinaai.imui.base.list.ListItemType
import com.kotlinaai.imui.conversation.pojos.UIMessage

abstract class MessageItemAdapter: ListItemType<UIMessage> {
    abstract fun onCreateView(context: Context): View
    abstract fun onUpdate(view: View, message: UIMessage)

    @Composable
    override fun ListItem(item: UIMessage) {
        AndroidView(modifier = Modifier.fillMaxWidth(), factory = {onCreateView(it)}) {
            onUpdate(it, item)
        }
    }
}