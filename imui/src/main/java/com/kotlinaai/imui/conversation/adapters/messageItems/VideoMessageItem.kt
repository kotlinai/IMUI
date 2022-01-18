package com.kotlinaai.imui.conversation.adapters.messageItems

import android.view.View
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.kotlinaai.imui.base.list.ListItemType
import com.kotlinaai.imui.conversation.composables.ImageContent
import com.kotlinaai.imui.conversation.composables.ReceivingMessage
import com.kotlinaai.imui.conversation.composables.SendingMessage
import com.kotlinaai.imui.conversation.composables.VideoContent
import com.kotlinaai.imui.conversation.pojos.UIMessage

class VideoMessageItem(
    val onAvatarClicked: (UIMessage) -> Unit = {},
    val onContentClicked: (View?, UIMessage) -> Unit = {_,_->}): ListItemType<UIMessage> {
    override fun isItemType(item: UIMessage): Boolean {
        return item.messageType == UIMessage.TYPE_VIDEO
    }

    @Composable
    override fun ListItem(item: UIMessage) {
        if (item.originType == UIMessage.ORIGIN_TYPE_RECEIVING) {
            ReceivingMessage(
                item,
                onAvatarClicked = { onAvatarClicked(item)},
                onContentClicked = { onContentClicked(it, item)}
            ) {
                VideoContent(thumbPath = item.video?.thumb, backgroundColor = MaterialTheme.colors.surface)
            }
        } else {
            SendingMessage(item, onContentClicked = { onContentClicked(it, item)}) {
                VideoContent(thumbPath = item.video?.thumb, backgroundColor = MaterialTheme.colors.primary)
            }
        }
    }
}